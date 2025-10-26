package com.benbenlaw.opoliscompat.kubejs;

import com.benbenlaw.caveopolis.config.RecipeConfig;
import com.benbenlaw.core.Core;
import com.benbenlaw.core.recipe.ChanceResult;
import com.benbenlaw.opoliscompat.Compat;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.RecipeScriptContext;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.core.NonNullList;

import net.minecraft.core.NonNullList;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Function;


import com.benbenlaw.core.recipe.ChanceResult;
import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ChanceResultComponent implements RecipeComponent<ChanceResult> {

    public static final ChanceResultComponent CHANCE_RESULT = new ChanceResultComponent();

    public static final RecipeComponentType<?> CONDITION = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "chance_result"), CHANCE_RESULT);

    @Override
    public RecipeComponentType<?> type() {
        return CONDITION;
    }

    @Override
    public Codec<ChanceResult> codec() {
        return ChanceResult.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.OBJECT;
    }

    public RecipeComponent<NonNullList<ChanceResult>> asNonNullList() {
        return new NonNullListChanceResultComponent(this);
    }

    @Override
    public ChanceResult wrap(RecipeScriptContext cx, Object from) {
        if (from instanceof ChanceResult cr) {
            return cr;
        }

        // === Handle array input ===
        if (from instanceof dev.latvian.mods.rhino.NativeArray nativeArray) {
            int length = Math.toIntExact(nativeArray.getLength());
            if (length < 1) {
                throw new IllegalArgumentException("ChanceResult array must have at least 1 element (item)");
            }

            Object itemObj = nativeArray.get(0);
            ItemStack stack;

            if (itemObj instanceof String itemStr) {
                stack = parseCountedItemString(itemStr);
            } else {
                throw new IllegalArgumentException("First element in array must be a string like 'item' or '3x item'");
            }

            float chance = 1.0f;
            if (length > 1) {
                try {
                    chance = Float.parseFloat(String.valueOf(nativeArray.get(1)));
                } catch (NumberFormatException ignored) {}
            }

            return new ChanceResult(stack, chance);
        }

        // === Handle object input ===
        if (from instanceof dev.latvian.mods.rhino.NativeObject nativeObj) {
            Object itemObj = nativeObj.get("item");
            Object chanceObj = nativeObj.get("chance");
            Object countAtTopLevel = nativeObj.get("count");

            if (itemObj == null) {
                throw new IllegalArgumentException("ChanceResult missing 'item' field");
            }

            ItemStack stack;

            if (itemObj instanceof String itemStr) {
                stack = parseCountedItemString(itemStr);
                if (countAtTopLevel != null) {
                    try {
                        double d = Double.parseDouble(String.valueOf(countAtTopLevel));
                        stack.setCount((int) d);
                    } catch (NumberFormatException ignored) {}
                }
            } else if (itemObj instanceof dev.latvian.mods.rhino.NativeObject itemNativeObj) {
                Object idObj = itemNativeObj.get("id");
                Object countObj = itemNativeObj.get("count");

                if (idObj == null) {
                    throw new IllegalArgumentException("ChanceResult item missing 'id' field");
                }

                String idStr = String.valueOf(idObj);
                int count = 1;
                if (countObj != null) {
                    try {
                        double d = Double.parseDouble(String.valueOf(countObj));
                        count = (int) d;
                    } catch (NumberFormatException ignored) {}
                }

                var rl = ResourceLocation.parse(idStr);
                var item = BuiltInRegistries.ITEM.get(rl);
                if (item == null) {
                    throw new IllegalArgumentException("Invalid item ID: " + idStr);
                }

                stack = new ItemStack(item, count);
            } else {
                throw new IllegalArgumentException("Invalid 'item' field type: " + itemObj.getClass());
            }

            float chance = 1.0f;
            if (chanceObj != null) {
                try {
                    chance = Float.parseFloat(String.valueOf(chanceObj));
                } catch (NumberFormatException ignored) {}
            }

            return new ChanceResult(stack, chance);
        }

        // === Handle simple string ===
        if (from instanceof String s) {
            ItemStack stack = parseCountedItemString(s);
            return new ChanceResult(stack, 1.0f);
        }

        throw new IllegalArgumentException("Cannot convert object to ChanceResult: " + from);
    }

    private ItemStack parseCountedItemString(String input) {
        input = input.trim();

        if (input.matches("^\\d+x\\s+.+$")) {
            String[] parts = input.split("\\s+", 2);
            int count = Integer.parseInt(parts[0].replace("x", ""));
            String itemId = parts[1];

            var rl = ResourceLocation.parse(itemId);
            var item = BuiltInRegistries.ITEM.get(rl);
            if (item == null) {
                throw new IllegalArgumentException("Invalid item ID: " + itemId);
            }

            return new ItemStack(item, count);
        } else {
            var rl = ResourceLocation.parse(input);
            var item = BuiltInRegistries.ITEM.get(rl);
            if (item == null) {
                throw new IllegalArgumentException("Invalid item ID: " + input);
            }
            return new ItemStack(item);
        }
    }

}

