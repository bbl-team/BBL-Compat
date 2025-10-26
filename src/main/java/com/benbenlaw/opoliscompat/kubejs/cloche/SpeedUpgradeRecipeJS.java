package com.benbenlaw.opoliscompat.kubejs.cloche;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.crafting.Ingredient;

public interface SpeedUpgradeRecipeJS {

    RecipeKey<Ingredient> UPGRADE_ITEM = IngredientComponent.INGREDIENT.inputKey("ingredient");
    RecipeKey<String> MODIFIER_TYPE = StringComponent.STRING.inputKey("modifier_type");
    RecipeKey<Integer> DURATION =  NumberComponent.INT.inputKey("modifier");

    RecipeSchema SCHEMA = new RecipeSchema(UPGRADE_ITEM, MODIFIER_TYPE, DURATION);

}
