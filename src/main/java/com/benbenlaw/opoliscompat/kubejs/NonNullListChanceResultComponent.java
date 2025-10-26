package com.benbenlaw.opoliscompat.kubejs;

import com.benbenlaw.core.recipe.ChanceResult;
import com.benbenlaw.opoliscompat.Compat;
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

public class NonNullListChanceResultComponent implements RecipeComponent<NonNullList<ChanceResult>> {

    private final RecipeComponent<ChanceResult> baseComponent;
    private final Codec<NonNullList<ChanceResult>> codec;
    private final TypeInfo typeInfo;
    public static final NonNullListChanceResultComponent NON_NULL_LIST_CHANCE_RESULT_COMPONENT = new NonNullListChanceResultComponent(ChanceResultComponent.CHANCE_RESULT);

    public static final RecipeComponentType<?> CONDITION = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "nonnull_list_chance_result"), NON_NULL_LIST_CHANCE_RESULT_COMPONENT);


    public NonNullListChanceResultComponent(RecipeComponent<ChanceResult> baseComponent) {
        this.baseComponent = baseComponent;
        this.codec = baseComponent.codec().listOf().xmap(
                list -> {
                    NonNullList<ChanceResult> nnList = NonNullList.create();
                    nnList.addAll(list);
                    return nnList;
                },
                List::copyOf
        );
        this.typeInfo = TypeInfo.of(NonNullList.class);
    }

    @Override
    public RecipeComponentType<?> type() {
        return CONDITION;
    }

    @Override
    public Codec<NonNullList<ChanceResult>> codec() {
        return codec;
    }

    @Override
    public TypeInfo typeInfo() {
        return typeInfo;
    }

    @Override
    public NonNullList<ChanceResult> wrap(RecipeScriptContext cx, Object from) {

        NonNullList<ChanceResult> nnList = NonNullList.create();

        if (from instanceof NonNullList<?> nnl) {
            // Direct cast if possible
            return (NonNullList<ChanceResult>) nnl;
        } else if (from instanceof List<?> list) {
            for (Object o : list) {
                // Handle each element which could be:
                // - A NativeArray like ["3x minecraft:stone", 0.75]
                // - A NativeObject like {item: ..., chance: ...}
                // - Or any other format accepted by baseComponent.wrap

                if (o instanceof dev.latvian.mods.rhino.NativeArray nativeArray) {
                    if (nativeArray.getLength() < 1) {
                        throw new IllegalArgumentException("ChanceResult array element must have at least one item (item string)");
                    }

                    // First element = item string
                    Object itemStr = nativeArray.get(0);

                    // Second element (optional) = chance float
                    float chance = 1.0f;
                    if (nativeArray.getLength() > 1) {
                        Object chanceObj = nativeArray.get(1);
                        try {
                            chance = Float.parseFloat(String.valueOf(chanceObj));
                        } catch (NumberFormatException ignored) {
                        }
                    }

                    // Construct a ChanceResult from the item string and chance
                    ChanceResult cr = baseComponent.wrap(cx, itemStr);

                    // Create a new ChanceResult with overridden chance
                    nnList.add(new ChanceResult(cr.stack(), chance));

                } else {
                    // Otherwise just delegate to base component wrap (which can handle NativeObject or String)
                    nnList.add(baseComponent.wrap(cx, o));
                }
            }
            return nnList;
        } else {
            // Single element wrapped into a list
            nnList.add(baseComponent.wrap(cx, from));
            return nnList;
        }
    }


}
