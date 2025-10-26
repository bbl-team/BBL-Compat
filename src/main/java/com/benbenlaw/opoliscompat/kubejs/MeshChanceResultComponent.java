package com.benbenlaw.opoliscompat.kubejs;

import com.benbenlaw.core.recipe.ChanceResult;
import com.benbenlaw.opoliscompat.Compat;
import com.benbenlaw.strainers.recipe.MeshChanceResult;
import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.RecipeScriptContext;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.NativeObject;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class MeshChanceResultComponent implements RecipeComponent<MeshChanceResult> {

    public static final MeshChanceResultComponent MESH_CHANCE_RESULT = new MeshChanceResultComponent();

    public static final RecipeComponentType<?> CONDITION = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "mesh_chance_result"), MESH_CHANCE_RESULT);


    @Override
    public RecipeComponentType<?> type() {
        return CONDITION;
    }

    @Override
    public Codec<MeshChanceResult> codec() {
        return MeshChanceResult.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.OBJECT;
    }

    @Override
    public MeshChanceResult wrap(RecipeScriptContext cx, Object from) {

        if (from instanceof MeshChanceResult mcr) return mcr;

        if (from instanceof dev.latvian.mods.rhino.NativeArray arr) {
            if (arr.getLength() < 1) {
                throw new IllegalArgumentException("MeshChanceResult array must have at least 1 element (item)");
            }

            ItemStack stack = parseCountedItemString(String.valueOf(arr.get(0)));
            float chance = arr.getLength() > 1 ? Float.parseFloat(String.valueOf(arr.get(1))) : 1.0f;
            Ingredient mesh = arr.getLength() > 2 ? parseMeshObject(arr.get(2)) : Ingredient.EMPTY;

            return new MeshChanceResult(new ChanceResult(stack, chance), mesh);
        }

        if (from instanceof NativeObject obj) {
            Object itemObj = obj.get("item");
            Object chanceObj = obj.get("chance");
            Object countAtTopLevel = obj.get("count");
            Object meshObj = obj.get("mesh");

            if (itemObj == null) {
                throw new IllegalArgumentException("MeshChanceResult missing 'item' field");
            }

            ItemStack stack;
            if (itemObj instanceof String itemStr) {
                stack = parseCountedItemString(itemStr);
                if (countAtTopLevel != null) {
                    stack.setCount((int) Double.parseDouble(String.valueOf(countAtTopLevel)));
                }
            } else if (itemObj instanceof dev.latvian.mods.rhino.NativeObject itemNativeObj) {
                String idStr = String.valueOf(itemNativeObj.get("id"));
                int count = itemNativeObj.get("count") != null ? (int) Double.parseDouble(String.valueOf(itemNativeObj.get("count"))) : 1;
                var item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(idStr));
                if (item == null) throw new IllegalArgumentException("Invalid item ID: " + idStr);
                stack = new ItemStack(item, count);
            } else {
                throw new IllegalArgumentException("Invalid 'item' field type: " + itemObj.getClass());
            }

            float chance = chanceObj != null ? Float.parseFloat(String.valueOf(chanceObj)) : 1.0f;
            Ingredient mesh = meshObj != null ? parseMeshObject(meshObj) : Ingredient.EMPTY;

            return new MeshChanceResult(new ChanceResult(stack, chance), mesh);
        }

        // === Handle simple string ===
        if (from instanceof String s) {
            ItemStack stack = parseCountedItemString(s);
            return new MeshChanceResult(new ChanceResult(stack, 1.0f), Ingredient.EMPTY);
        }

        throw new IllegalArgumentException("Cannot convert object to MeshChanceResult: " + from);
    }

    private ItemStack parseCountedItemString(String input) {
        input = input.trim();
        if (input.matches("^\\d+x\\s+.+$")) {
            String[] parts = input.split("\\s+", 2);
            int count = Integer.parseInt(parts[0].replace("x", ""));
            String itemId = parts[1];
            var item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemId));
            if (item == null) throw new IllegalArgumentException("Invalid item ID: " + itemId);
            return new ItemStack(item, count);
        } else {
            var item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(input));
            if (item == null) throw new IllegalArgumentException("Invalid item ID: " + input);
            return new ItemStack(item);
        }
    }

    private Ingredient parseMeshObject(Object meshObj) {
        if (meshObj instanceof String meshStr) {
            meshStr = meshStr.trim();
            if (meshStr.startsWith("#")) {
                return Ingredient.of(net.minecraft.tags.TagKey.create(
                        net.minecraft.core.registries.Registries.ITEM,
                        ResourceLocation.parse(meshStr.substring(1))
                ));
            } else {
                var item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(meshStr));
                if (item == null) throw new IllegalArgumentException("Invalid mesh item: " + meshStr);
                return Ingredient.of(item);
            }
        }
        else if (meshObj instanceof dev.latvian.mods.rhino.NativeArray arr) {
            var ingredients = new java.util.ArrayList<Ingredient>();
            for (Object o : arr) {
                if (o instanceof String s) ingredients.add(parseMeshObject(s));
            }
            var stacks = ingredients.stream().flatMap(i -> java.util.Arrays.stream(i.getItems())).toArray(ItemStack[]::new);
            return Ingredient.of(stacks);
        }
        return Ingredient.EMPTY;
    }
}