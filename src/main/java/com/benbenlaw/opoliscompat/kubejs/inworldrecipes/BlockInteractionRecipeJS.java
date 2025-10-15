package com.benbenlaw.opoliscompat.kubejs.inworldrecipes;

import com.benbenlaw.core.recipe.ChanceResult;
import com.benbenlaw.inworldrecipes.recipes.BlockTarget;
import com.benbenlaw.inworldrecipes.util.ClickType;
import com.benbenlaw.opoliscompat.kubejs.BlockTargetComponent;
import com.benbenlaw.opoliscompat.kubejs.ChanceResultComponent;
import com.benbenlaw.opoliscompat.kubejs.ClickTypeComponent;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeOptional;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.crafting.SizedIngredient;

import java.util.List;

public interface BlockInteractionRecipeJS {

    RecipeKey<ClickType> CLICK_TYPE = ClickTypeComponent.INSTANCE.inputKey("click_type");
    RecipeKey<BlockTarget> TARGET_BLOCK_STATE = BlockTargetComponent.INSTANCE.inputKey("target_block_state");
    RecipeKey<SizedIngredient> HELD_ITEM = SizedIngredientComponent.FLAT.inputKey("held_item");
    RecipeKey<Boolean> DAMAGE_HELD_ITEM = BooleanComponent.BOOLEAN.inputKey("damage_held_item");
    RecipeKey<Boolean> CONSUME_HELD_ITEM = BooleanComponent.BOOLEAN.inputKey("consume_held_item");
    RecipeKey<Boolean> POP_ITEMS = BooleanComponent.BOOLEAN.inputKey("pop_items");

    RecipeKey<Boolean> IGNORE_BLOCK_STATE = BooleanComponent.BOOLEAN.inputKey("ignore_block_state").defaultOptional();
    RecipeKey<NonNullList<ChanceResult>> RESULTS = ChanceResultComponent.CHANCE_RESULT.asNonNullList().inputKey("results").defaultOptional();
    RecipeKey<BlockState> OUTPUT_BLOCK_STATE = BlockStateComponent.BLOCK.inputKey("output_block_state")
            .functionNames(List.of("output_block_state", "outputBlockState")).defaultOptional();


    RecipeSchema SCHEMA = new RecipeSchema(
            CLICK_TYPE,
            TARGET_BLOCK_STATE,
            HELD_ITEM,
            DAMAGE_HELD_ITEM,
            CONSUME_HELD_ITEM,
            POP_ITEMS,
            IGNORE_BLOCK_STATE,
            RESULTS,
            OUTPUT_BLOCK_STATE


    );

}
