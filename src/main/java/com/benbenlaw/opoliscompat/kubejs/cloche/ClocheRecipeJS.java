package com.benbenlaw.opoliscompat.kubejs.cloche;

import com.benbenlaw.core.recipe.ChanceResult;
import com.benbenlaw.opoliscompat.kubejs.ChanceResultComponent;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public interface ClocheRecipeJS {

    RecipeKey<Ingredient> SEED = IngredientComponent.INGREDIENT.inputKey("seed");
    RecipeKey<Ingredient> SOIL = IngredientComponent.INGREDIENT.inputKey("soil");
    RecipeKey<Integer> DURATION =  NumberComponent.INT.inputKey("duration");
    RecipeKey<List<ChanceResult>> OUTPUTS = ChanceResultComponent.CHANCE_RESULT.asList().inputKey("results");
    RecipeKey<String> DIMENSION =  StringComponent.STRING.inputKey("dimension").defaultOptional();
    RecipeKey<Ingredient> CATALYST = IngredientComponent.INGREDIENT.inputKey("catalyst").defaultOptional();
    RecipeKey<ItemStack> SHEARS_RESULT = ItemStackComponent.ITEM_STACK.inputKey("shears_result")
            .functionNames(List.of("shears_result", "shearsResult")).defaultOptional();;

    RecipeSchema SCHEMA = new RecipeSchema(SEED, SOIL, DURATION, OUTPUTS, DIMENSION, CATALYST, SHEARS_RESULT);

}
