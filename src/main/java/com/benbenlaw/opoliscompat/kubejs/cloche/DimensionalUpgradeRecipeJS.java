package com.benbenlaw.opoliscompat.kubejs.cloche;

import com.benbenlaw.core.recipe.ChanceResult;
import com.benbenlaw.opoliscompat.kubejs.ChanceResultComponent;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public interface DimensionalUpgradeRecipeJS {

    RecipeKey<Ingredient> UPGRADE_ITEM = IngredientComponent.INGREDIENT.inputKey("ingredient");
    RecipeKey<String> DIMENSION =  StringComponent.STRING.inputKey("dimension");

    RecipeSchema SCHEMA = new RecipeSchema(UPGRADE_ITEM, DIMENSION);

}
