package com.benbenlaw.opoliscompat.kubejs;

import com.benbenlaw.inworldrecipes.util.ClickType;
import com.benbenlaw.inworldrecipes.util.ClickTypeCodec;
import com.benbenlaw.opoliscompat.Compat;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.latvian.mods.kubejs.recipe.RecipeScriptContext;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.kubejs.recipe.filter.RecipeMatchContext;
import dev.latvian.mods.rhino.type.TypeInfo;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.match.ReplacementMatchInfo;
import net.minecraft.resources.ResourceLocation;

public class ClickTypeComponent implements RecipeComponent<ClickType> {

    public static final ClickTypeComponent INSTANCE = new ClickTypeComponent();
    public static final RecipeComponentType<?> CONDITION = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "click_type"), INSTANCE);

    private static final Codec<ClickType> CODEC = ClickTypeCodec.CLICK_TYPE_CODEC;

    @Override
    public RecipeComponentType<?> type() {
        return CONDITION;
    }

    @Override
    public Codec<ClickType> codec() {
        return CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(ClickType.class);
    }

    @Override
    public ClickType wrap(RecipeScriptContext cx, Object from) {

        if (from instanceof String s) {
            String lower = s.toLowerCase();
            if (lower.equals("left") || lower.equals("left_click")) return ClickType.LEFT_CLICK;
            if (lower.equals("right") || lower.equals("right_click")) return ClickType.RIGHT_CLICK;
            throw new IllegalArgumentException("Invalid click type: " + s);
        }
        throw new IllegalArgumentException("Cannot convert to ClickType: " + from);
    }

    @Override
    public boolean matches(RecipeMatchContext cx, ClickType value, ReplacementMatchInfo match) {
        return true;
    }

}
