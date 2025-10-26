package com.benbenlaw.opoliscompat.kubejs;

import com.benbenlaw.opoliscompat.Compat;
import com.benbenlaw.strainers.recipe.MeshChanceResult;
import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.RecipeScriptContext;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class NonNullListMeshChanceResultComponent implements RecipeComponent<NonNullList<MeshChanceResult>> {

    private final RecipeComponent<MeshChanceResult> baseComponent;
    private final Codec<NonNullList<MeshChanceResult>> codec;
    private final TypeInfo typeInfo;

    public static final NonNullListMeshChanceResultComponent MESH_CHANCE_RESULT = new NonNullListMeshChanceResultComponent(MeshChanceResultComponent.MESH_CHANCE_RESULT);
    public static final RecipeComponentType<?> CONDITION = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "nonnull_list_mesh_chance_result"), MESH_CHANCE_RESULT);


    public NonNullListMeshChanceResultComponent(RecipeComponent<MeshChanceResult> baseComponent) {
        this.baseComponent = baseComponent;
        this.codec = baseComponent.codec().listOf().xmap(
                list -> {
                    NonNullList<MeshChanceResult> nnList = NonNullList.create();
                    nnList.addAll(list);
                    return nnList;
                },
                List::copyOf
        );
        this.typeInfo = TypeInfo.of(NonNullList.class);
    }

    @Override
    public RecipeComponentType<?> type() {
        return null;
    }

    @Override
    public Codec<NonNullList<MeshChanceResult>> codec() {
        return codec;
    }

    @Override
    public TypeInfo typeInfo() {
        return typeInfo;
    }

    @Override
    public NonNullList<MeshChanceResult> wrap(RecipeScriptContext cx, Object from) {

        NonNullList<MeshChanceResult> nnList = NonNullList.create();

        if (from instanceof NonNullList<?> nnl) {
            // Safe unchecked cast, since we know contents are MeshChanceResult
            return (NonNullList<MeshChanceResult>) nnl;
        } else if (from instanceof List<?> list) {
            for (Object o : list) {
                // Just delegate to baseComponent, which already handles
                // NativeArray, NativeObject, String, etc.
                nnList.add(baseComponent.wrap(cx, o));
            }
            return nnList;
        } else {
            // Single element wrapped into a list
            nnList.add(baseComponent.wrap(cx, from));
            return nnList;
        }
    }
}