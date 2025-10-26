package com.benbenlaw.opoliscompat.kubejs.smelting;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidStackComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.List;

public interface MixingRecipeJS {

    RecipeKey<List<FluidStack>> FLUID_INPUTS =  FluidStackComponent.FLUID_STACK.instance().asList().inputKey("inputs");
    RecipeKey<FluidStack> OUTPUT = FluidStackComponent.FLUID_STACK.outputKey("output");

    RecipeSchema SCHEMA = new RecipeSchema(OUTPUT, FLUID_INPUTS);
}