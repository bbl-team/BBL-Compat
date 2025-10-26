package com.benbenlaw.opoliscompat.kubejs.strainers;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

public interface StrainerRecipeJS {
    RecipeKey<Ingredient> INPUT = IngredientComponent.INGREDIENT.inputKey("input");
    RecipeKey<String> ABOVE_BLOCK = StringComponent.STRING.key("aboveBlock", ComponentRole.INPUT);
    RecipeKey<SizedIngredient> OUTPUT = SizedIngredientComponent.FLAT.outputKey("output");
    RecipeKey<Integer> MIN_MESH_TIER = NumberComponent.INT.inputKey("minMeshTier");
    RecipeKey<Double> CHANCE = NumberComponent.DOUBLE.outputKey("chance");

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, ABOVE_BLOCK, OUTPUT, MIN_MESH_TIER, CHANCE);
}