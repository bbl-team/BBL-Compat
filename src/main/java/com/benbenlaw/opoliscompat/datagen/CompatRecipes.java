package com.benbenlaw.opoliscompat.datagen;

import biomesoplenty.api.block.BOPBlocks;
import blusunrize.immersiveengineering.api.IETags;
import blusunrize.immersiveengineering.api.crafting.MultiblockRecipe;
import blusunrize.immersiveengineering.common.register.IEItems;
import com.benbenlaw.caveopolis.item.CaveopolisItems;
import com.benbenlaw.cloche.Cloche;
import com.benbenlaw.cloche.data.recipe.ClocheRecipeProvider;
import com.benbenlaw.core.item.CoreDataComponents;
import com.benbenlaw.core.recipe.ChanceResult;
import com.benbenlaw.core.util.ColorList;
import com.benbenlaw.opoliscompat.Compat;
import com.benbenlaw.opoliscompat.datagen.cloche.BiomesOPlentyResultsLists;
import com.benbenlaw.opoliscompat.datagen.cloche.CompatResultLists;
import com.benbenlaw.opoliscompat.datagen.cloche.MysticalResources;
import com.benbenlaw.opoliscompat.datagen.cloche.TwilightResultLists;
import com.blakebr0.mysticalagradditions.MysticalAgradditions;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import twilightforest.init.TFBlocks;

import java.util.concurrent.CompletableFuture;

import static com.benbenlaw.colors.block.ColorsBlocks.*;
import static com.benbenlaw.colors.item.ColorsItems.APPLES;
import static com.benbenlaw.opoliscompat.datagen.cloche.CompatResultLists.INFERIUM_FARMLAND;

public class CompatRecipes extends RecipeProvider {

    public static NonNullList<ChanceResult> createColoredSaplingResults(String color) {
        NonNullList<ChanceResult> coloredSaplingResults = NonNullList.create();

        coloredSaplingResults.add(new ChanceResult(
                itemWithColor(new ItemStack(CaveopolisItems.COLORED_LOG.get(), 2), color), 1.0f));
        coloredSaplingResults.add(new ChanceResult(
                itemWithColor(new ItemStack(CaveopolisItems.COLORED_SAPLING.get()), color), 0.2f));
        coloredSaplingResults.add(new ChanceResult(
                itemWithColor(new ItemStack(CaveopolisItems.COLORED_APPLE.get()), color), 0.2f));
        coloredSaplingResults.add(new ChanceResult(new ItemStack(Items.STICK), 0.1f));

        return coloredSaplingResults;
    }

    public static NonNullList<ChanceResult> createColoredSaplingResultsColors(String color) {
        NonNullList<ChanceResult> coloredSaplingResultsColors = NonNullList.create();

        coloredSaplingResultsColors.add(new ChanceResult(
                new ItemStack(LOGS.get(color + "_log"), 2), 1.0f));
        coloredSaplingResultsColors.add(new ChanceResult(
                new ItemStack(SAPLINGS.get(color + "_sapling"), 1), 0.2f));
        coloredSaplingResultsColors.add(new ChanceResult(
                new ItemStack(APPLES.get(color + "_apple").get(), 1), 0.2f));
        coloredSaplingResultsColors.add(new ChanceResult(new ItemStack(Items.STICK), 0.1f));

        return coloredSaplingResultsColors;
    }

    public CompatRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {

        //Biomes O' Plenty Cloche Recipes
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.ORIGIN_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.ORIGIN_SAPLING_RESULTS, new ItemStack(BOPBlocks.ORIGIN_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/origin_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.FLOWERING_OAK_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.FLOWERING_OAK_SAPLING_RESULTS, new ItemStack(BOPBlocks.FLOWERING_OAK_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/flowering_oak_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.CYPRESS_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.CYPRESS_SAPLING_RESULTS, new ItemStack(BOPBlocks.CYPRESS_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/cypress_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.SNOWBLOSSOM_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.SNOWBLOSSOM_SAPLING_RESULTS, new ItemStack(BOPBlocks.SNOWBLOSSOM_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/snowblossom_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.RAINBOW_BIRCH_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.RAINBOW_BIRCH_SAPLING_RESULTS, new ItemStack(BOPBlocks.RAINBOW_BIRCH_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/rainbow_birch_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.FIR_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.FIR_SAPLING_RESULTS, new ItemStack(BOPBlocks.FIR_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/fir_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.PINE_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.PINE_SAPLING_RESULTS, new ItemStack(BOPBlocks.PINE_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/pine_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.RED_MAPLE_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.RED_MAPLE_SAPLING_RESULTS, new ItemStack(BOPBlocks.RED_MAPLE_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/red_maple_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.ORANGE_MAPLE_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.ORANGE_MAPLE_SAPLING_RESULTS, new ItemStack(BOPBlocks.ORANGE_MAPLE_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/orange_maple_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.YELLOW_MAPLE_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.YELLOW_MAPLE_SAPLING_RESULTS, new ItemStack(BOPBlocks.YELLOW_MAPLE_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/yellow_maple_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.MAHOGANY_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.MAHOGANY_SAPLING_RESULTS, new ItemStack(BOPBlocks.MAHOGANY_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/mahogany_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.REDWOOD_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.REDWOOD_SAPLING_RESULTS, new ItemStack(BOPBlocks.REDWOOD_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/redwood_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.JACARANDA_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.JACARANDA_SAPLING_RESULTS, new ItemStack(BOPBlocks.JACARANDA_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/jacaranda_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.PALM_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.PALM_SAPLING_RESULTS, new ItemStack(BOPBlocks.PALM_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/palm_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.WILLOW_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.WILLOW_SAPLING_RESULTS, new ItemStack(BOPBlocks.WILLOW_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/willow_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.DEAD_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.DEAD_SAPLING_RESULTS, new ItemStack(BOPBlocks.DEAD_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/dead_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.MAGIC_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.MAGIC_SAPLING_RESULTS, new ItemStack(BOPBlocks.MAGIC_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/magic_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.UMBRAN_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.UMBRAN_SAPLING_RESULTS, new ItemStack(BOPBlocks.UMBRAN_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/umbran_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.HELLBARK_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.HELLBARK_SAPLING_RESULTS, new ItemStack(BOPBlocks.HELLBARK_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/hellbark_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(BOPBlocks.EMPYREAL_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, BiomesOPlentyResultsLists.EMPYREAL_SAPLING_RESULTS, new ItemStack(BOPBlocks.EMPYREAL_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/empereal_sapling"));

        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.ROSE.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "rose", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.VIOLET.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "lily", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.LAVENDER.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "lavender", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.TALL_LAVENDER.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "tall_lavender", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.WHITE_LAVENDER.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "white_lavender", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.TALL_WHITE_LAVENDER.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "tall_white_lavender", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.BLUE_HYDRANGEA.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "blue_hydrangea", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.GOLDENROD.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "goldenrod", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.ORANGE_COSMOS.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "orange_cosmos", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.PINK_DAFFODIL.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "pink_daffodil", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.PINK_HIBISCUS.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "pink_hibiscus", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.WILDFLOWER.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "wildflower", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.WHITE_PETALS.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "white_petals", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.ICY_IRIS.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "icy_iris", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.GLOWFLOWER.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "glowflower", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.WILTED_LILY.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "wilted_lily", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.BURNING_BLOSSOM.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "burning_blossom", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.ENDBLOOM.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "endbloom", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.SPROUT.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "sprout", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.BUSH.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "bush", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.HIGH_GRASS.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "high_grass", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.CLOVER.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "clover", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.HUGE_CLOVER_PETAL.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "huge_clover_petal", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.WATERLILY.asItem()), Ingredient.of(Items.WATER_BUCKET), 1200, "waterlily", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.DUNE_GRASS.asItem()), Ingredient.of(ItemTags.SAND), 1200, "dune_grass", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.DESERT_GRASS.asItem()), Ingredient.of(ItemTags.SAND), 1200, "desert_grass", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.DEAD_GRASS.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "dead_grass", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.TUNDRA_SHRUB.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "tundra_shrub", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.ENDERPHYTE.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "enderphyte", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.LUMALOOP.asItem()), Ingredient.of(Items.END_STONE), 1200, "lumaloop", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.BARLEY.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "barley", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.SEA_OATS.asItem()), Ingredient.of(Items.WATER_BUCKET), 1200, "sea_oats", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.CATTAIL.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "cattail", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.REED.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "reed", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.WATERGRASS.asItem()), Ingredient.of(Items.WATER_BUCKET), 1200, "watergrass", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.TINY_CACTUS.asItem()), Ingredient.of(ItemTags.SAND), 1200, "tiny_cactus", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.BRAMBLE.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "bramble", consumer);
        createSimpleClocheBiomesOPlentyRecipe(Ingredient.of(BOPBlocks.BRAMBLE_LEAVES.asItem()), Ingredient.of(ItemTags.DIRT), 1200, "bramble_leaves", consumer);




        //Twilight Forest Cloche Recipes
        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(TFBlocks.TWILIGHT_OAK_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, TwilightResultLists.TWILIGHT_OAK_SAPLING_RESULTS, new ItemStack (TFBlocks.TWILIGHT_OAK_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/twilight_oak_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(TFBlocks.CANOPY_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, TwilightResultLists.CANOPY_SAPLING_RESULTS, new ItemStack (TFBlocks.CANOPY_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/canopy_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(TFBlocks.MANGROVE_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, TwilightResultLists.MANGROVE_SAPLING_RESULTS, new ItemStack (TFBlocks.MANGROVE_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/mangrove_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(TFBlocks.DARKWOOD_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, TwilightResultLists.DARKWOOD_SAPLING_RESULTS, new ItemStack (TFBlocks.DARK_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/darkwood_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(TFBlocks.TIME_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, TwilightResultLists.TIME_SAPLING_RESULTS, new ItemStack (TFBlocks.TIME_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/time_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(TFBlocks.TRANSFORMATION_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, TwilightResultLists.TRANSFORMATION_SAPLING_RESULTS, new ItemStack (TFBlocks.TRANSFORMATION_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/transformation_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(TFBlocks.MINING_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, TwilightResultLists.MINERS_SAPLING_RESULTS, new ItemStack (TFBlocks.MINING_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/mining_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(TFBlocks.SORTING_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, TwilightResultLists.SORTING_SAPLING_RESULTS, new ItemStack (TFBlocks.SORTING_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/sorting_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(TFBlocks.HOLLOW_OAK_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, TwilightResultLists.ROBUST_OAK_SAPLING_RESULTS, new ItemStack (TFBlocks.TWILIGHT_OAK_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/robust_oak_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(TFBlocks.RAINBOW_OAK_SAPLING), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, TwilightResultLists.RAINBOW_OAK_RESULTS, new ItemStack (TFBlocks.RAINBOW_OAK_LEAVES.asItem().getDefaultInstance().getItemHolder(), 2))
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/rainbow_oak_sapling"));

        ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(TFBlocks.TORCHBERRY_PLANT), Ingredient.of(ItemTags.DIRT), null,
                        null, 1200, TwilightResultLists.TORCHBERRY_RESULTS, null)
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/torchberry_bush"));

        createSimpleClocheTwilightForestRecipe(Ingredient.of(TFBlocks.MUSHGLOOM.get().asItem()), Ingredient.of(ItemTags.DIRT), 1200, "mushgloom", consumer);
        createSimpleClocheTwilightForestRecipe(Ingredient.of(TFBlocks.MOSS_PATCH.get().asItem()), Ingredient.of(ItemTags.DIRT), 1200, "moss_patch", consumer);
        createSimpleClocheTwilightForestRecipe(Ingredient.of(TFBlocks.MAYAPPLE.get().asItem()), Ingredient.of(ItemTags.DIRT), 1200, "mayapple", consumer);
        createSimpleClocheTwilightForestRecipe(Ingredient.of(TFBlocks.CLOVER_PATCH.get().asItem()), Ingredient.of(ItemTags.DIRT), 1200, "clover_patch", consumer);
        createSimpleClocheTwilightForestRecipe(Ingredient.of(TFBlocks.FIDDLEHEAD.get().asItem()), Ingredient.of(ItemTags.DIRT), 1200, "fiddlehead", consumer);
        createSimpleClocheTwilightForestRecipe(Ingredient.of(TFBlocks.HUGE_LILY_PAD.get().asItem()), Ingredient.of(Items.WATER_BUCKET), 1200, "huge_lily_pad", consumer);
        createSimpleClocheTwilightForestRecipe(Ingredient.of(TFBlocks.HUGE_WATER_LILY.get().asItem()), Ingredient.of(Items.WATER_BUCKET), 1200, "huge_water_lily", consumer);
        createSimpleClocheTwilightForestRecipe(Ingredient.of(TFBlocks.THORN_ROSE.get().asItem()), Ingredient.of(ItemTags.DIRT), 1200, "thorn_rose", consumer);
        createSimpleClocheTwilightForestRecipe(Ingredient.of(TFBlocks.GREEN_THORNS.get().asItem()), Ingredient.of(ItemTags.DIRT), 1200, "green_thorns", consumer);
        createSimpleClocheTwilightForestRecipe(Ingredient.of(TFBlocks.BROWN_THORNS.get().asItem()), Ingredient.of(ItemTags.DIRT), 1200, "brown_thorns", consumer);


        //Mystical Cloche Recipes
        for (String type : MysticalResources.TIER_1_SEEDS) {
            Item seed = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MysticalAgriculture.MOD_ID, type + "_seeds"));

            ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(seed), Ingredient.of(INFERIUM_FARMLAND), null,
                            null, 2400, CompatResultLists.RESULTS_LIST_BY_TYPE.get(type), null)
                    .save(consumer.withConditions(new ModLoadedCondition("mysticalagriculture")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/mysticalagriculture/" + type + "_seed"));
        }

        for (String type : MysticalResources.TIER_2_SEEDS) {
            Item seed = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MysticalAgriculture.MOD_ID, type + "_seeds"));

            ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(seed), Ingredient.of(CompatResultLists.PRUDENTIUM_FARMLAND), null,
                            null, 2400, CompatResultLists.RESULTS_LIST_BY_TYPE.get(type), null)
                    .save(consumer.withConditions(new ModLoadedCondition("mysticalagriculture")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/mysticalagriculture/" + type + "_seed"));
        }

        for (String type : MysticalResources.TIER_3_SEEDS) {
            Item seed = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MysticalAgriculture.MOD_ID, type + "_seeds"));

            ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(seed), Ingredient.of(CompatResultLists.TERTIUM_FARMLAND), null,
                            null, 2400, CompatResultLists.RESULTS_LIST_BY_TYPE.get(type), null)
                    .save(consumer.withConditions(new ModLoadedCondition("mysticalagriculture")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/mysticalagriculture/" + type + "_seed"));
        }

        for (String type : MysticalResources.TIER_4_SEEDS) {
            Item seed = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MysticalAgriculture.MOD_ID, type + "_seeds"));

            ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(seed), Ingredient.of(CompatResultLists.IMPERIUM_FARMLAND), null,
                            null, 2400, CompatResultLists.RESULTS_LIST_BY_TYPE.get(type), null)
                    .save(consumer.withConditions(new ModLoadedCondition("mysticalagriculture")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/mysticalagriculture/" + type + "_seed"));
        }

        for (String type : MysticalResources.TIER_5_SEEDS) {
            Item seed = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MysticalAgriculture.MOD_ID, type + "_seeds"));

            ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(seed), Ingredient.of(CompatResultLists.SUPREMIUM_FARMLAND), null,
                            null, 2400, CompatResultLists.RESULTS_LIST_BY_TYPE.get(type), null)
                    .save(consumer.withConditions(new ModLoadedCondition("mysticalagriculture")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/mysticalagriculture/" + type + "_seed"));
        }

       for (String type : MysticalResources.TIER_6_SEEDS) {
           Item seed = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MysticalAgriculture.MOD_ID, type + "_seeds"));
           Item crux = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MysticalAgradditions.MOD_ID, type + "_crux"));

           ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(seed), Ingredient.of(CompatResultLists.INSANIUM_FARMLAND), Ingredient.of(crux),
                           null, 3000, CompatResultLists.RESULTS_LIST_BY_TYPE.get(type), null)
                   .save(consumer.withConditions(new ModLoadedCondition("mysticalagradditions")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/mysticalagriculture/" + type + "_seed"));
       }



        for (String color : ColorList.COLORS) {

            NonNullList<ChanceResult> resultsWithColor = createColoredSaplingResults(color);

            //Saplings in Cloche - Caveopolis
            createClocheRecipe(consumer, color, Ingredient.of(CaveopolisItems.COLORED_SAPLING.get()),
                    Ingredient.of(ItemTags.DIRT), null, null, 1200, resultsWithColor,
                    itemWithColor(CaveopolisItems.COLORED_LEAVES.get().getDefaultInstance(), color));

            //Poppy in Cloche - Caveopolis
            createSimpleClocheRecipe(CaveopolisItems.COLORED_POPPY.get().getDefaultInstance(),
                    Ingredient.of(ItemTags.DIRT), 1200, color, "_poppy", consumer);

            //Dandelion in Cloche - Caveopolis
            createSimpleClocheRecipe(CaveopolisItems.COLORED_DANDELION.get().getDefaultInstance(),
                    Ingredient.of(ItemTags.DIRT), 1200, color, "_dandelion", consumer);

            //Saplings in Cloche - Colors

            NonNullList<ChanceResult> resultsColors = createColoredSaplingResultsColors(color);

            ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(SAPLINGS.get(color + "_sapling")), Ingredient.of(ItemTags.DIRT), null,
                            null, 1200, resultsColors, new ItemStack(LEAVES.get(color + "_leaves").toStack().getItemHolder(), 2))
                    .save(consumer.withConditions(new ModLoadedCondition("colors")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/colors/" + color + "_sapling"));

            //Poppy in Cloche - Colors

            NonNullList<ChanceResult> poppy = NonNullList.create();
            poppy.add(new ChanceResult(new ItemStack(POPPY.get(color + "_poppy")), 1.0f));

            ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(POPPY.get(color + "_poppy")), Ingredient.of(ItemTags.DIRT), null,
                            null, 1200, poppy, null)
                    .save(consumer.withConditions(new ModLoadedCondition("colors")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/colors/" + color + "_poppy"));

            //Dandelion in Cloche - Colors

            NonNullList<ChanceResult> dandelion = NonNullList.create();
            dandelion.add(new ChanceResult(new ItemStack(DANDELION.get(color + "_dandelion")), 1.0f));

            ClocheRecipeProvider.ClocheRecipeBuilder(Ingredient.of(DANDELION.get(color + "_dandelion")), Ingredient.of(ItemTags.DIRT), null,
                            null, 1200, dandelion, null)
                    .save(consumer.withConditions(new ModLoadedCondition("colors")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/colors/" + color + "_dandelion"));

        }

        //Immersive Metal Press


    }


    private void createClocheRecipe(RecipeOutput consumer, String color, Ingredient seed, Ingredient soil, Ingredient catalyst, String dimension, int duration, NonNullList<ChanceResult> results, ItemStack shearsResult) {
        final Ingredient saplingColor = DataComponentIngredient.of(false, DataComponentPredicate.builder()
                        .expect(CoreDataComponents.COLOR.get(), color).build(),
                itemWithColor(new ItemStack(seed.getItems()[0].getItem()), color).getItem());
        ItemStack shearsResultColor = itemWithColor(shearsResult, color);
        shearsResultColor.setCount(2);

        ClocheRecipeProvider.ClocheRecipeBuilder(saplingColor, soil, catalyst,
                        dimension, duration, results, shearsResultColor)
                .save(consumer.withConditions(new ModLoadedCondition("caveopolis")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/caveopolis/" + color + "_sapling"));
    }
    private void createSimpleClocheRecipe(ItemStack seed, Ingredient soil, int duration, String color, String name, RecipeOutput consumer) {

        final Ingredient flowerColor = DataComponentIngredient.of(false, DataComponentPredicate.builder()
                        .expect(CoreDataComponents.COLOR.get(), color).build(),
                itemWithColor(new ItemStack(seed.getItem()), color).getItem());

        NonNullList<ChanceResult> SINGLE_ITEM_RESULTS = NonNullList.create();
        SINGLE_ITEM_RESULTS.add(new ChanceResult(itemWithColor(new ItemStack(seed.getItem()), color), 1.0f));


        ClocheRecipeProvider.ClocheRecipeBuilder(flowerColor, soil, null,
                        null, duration, SINGLE_ITEM_RESULTS, null)
                .save(consumer.withConditions(new ModLoadedCondition("caveopolis")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/caveopolis/" + color + name));
    }

    private void createSimpleClocheTwilightForestRecipe(Ingredient seed, Ingredient soil, int duration, String name, RecipeOutput consumer) {

        NonNullList<ChanceResult> SINGLE_ITEM_RESULTS = NonNullList.create();
        SINGLE_ITEM_RESULTS.add(new ChanceResult(new ItemStack(seed.getItems()[0].getItem(), 1), 1.0f));


        ClocheRecipeProvider.ClocheRecipeBuilder(seed, soil, null,
                        null, duration, SINGLE_ITEM_RESULTS, null)
                .save(consumer.withConditions(new ModLoadedCondition("twilightforest")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/twilightforest/" + name));
    }
    private void createSimpleClocheBiomesOPlentyRecipe(Ingredient seed, Ingredient soil, int duration, String name, RecipeOutput consumer) {

        NonNullList<ChanceResult> SINGLE_ITEM_RESULTS = NonNullList.create();
        SINGLE_ITEM_RESULTS.add(new ChanceResult(new ItemStack(seed.getItems()[0].getItem(), 1), 1.0f));


        ClocheRecipeProvider.ClocheRecipeBuilder(seed, soil, null,
                        null, duration, SINGLE_ITEM_RESULTS, null)
                .save(consumer.withConditions(new ModLoadedCondition("biomesoplenty")).withConditions(new ModLoadedCondition("cloche")), ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "cloche/biomesoplenty/" + name));
    }

    private static ItemStack itemWithColor(ItemStack item, String color) {
        item.set(CoreDataComponents.COLOR, color);
        item.set(CoreDataComponents.LIT, false);
        return item;
    }


}
