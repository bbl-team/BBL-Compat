package com.benbenlaw.opoliscompat.kubejs.utilities;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.SizedIngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

public interface SummoningRecipeJS {
    RecipeKey<SizedIngredient> INPUT = SizedIngredientComponent.FLAT.inputKey("input");
    RecipeKey<Ingredient> CATALYST = IngredientComponent.INGREDIENT.inputKey("catalyst");
    RecipeKey<String> MOB = StringComponent.STRING.key("mob", ComponentRole.OTHER);

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, CATALYST, MOB);
}