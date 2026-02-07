package com.benbenlaw.opoliscompat.kubejs;

import com.benbenlaw.opoliscompat.kubejs.cloche.ClocheRecipeJS;
import com.benbenlaw.opoliscompat.kubejs.cloche.DimensionalUpgradeRecipeJS;
import com.benbenlaw.opoliscompat.kubejs.inworldrecipes.BlockInteractionRecipeJS;
import com.benbenlaw.opoliscompat.kubejs.market.MarketRecipeJS;
import com.benbenlaw.opoliscompat.kubejs.smelting.*;
import com.benbenlaw.opoliscompat.kubejs.strainers.MeshUpgradeRecipeJS;
import com.benbenlaw.opoliscompat.kubejs.strainers.OutputUpgradeRecipeJS;
import com.benbenlaw.opoliscompat.kubejs.strainers.StrainerRecipeJS;
import com.benbenlaw.opoliscompat.kubejs.strainers.UpdatedStrainerRecipeJS;
import com.benbenlaw.opoliscompat.kubejs.utilities.*;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentTypeRegistry;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

public class KubeJSOpolisPlugin implements KubeJSPlugin {

    public static EventGroup GROUP = EventGroup.of("OpolisCompatEvents");

    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry event) {

        //Opolis Utilities
        event.register(ResourceLocation.fromNamespaceAndPath("opolisutilities", "catalogue"), CatalogueRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("opolisutilities", "drying_table"), DryingRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("opolisutilities", "fluid_generator"), FluidGenRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("opolisutilities", "resource_generator"), ResourceGenRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("opolisutilities", "soaking_table"), SoakingRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("opolisutilities", "speed_upgrades"), SpeedUpgradeRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("opolisutilities", "summoning_block"), SummoningRecipeJS.SCHEMA);

        //Strainers
        if (ModList.get().isLoaded("strainers")) {

            String version = ModList.get().getModFileById("strainers").versionString();

            if (version.startsWith("5.")) {
                System.out.println("Loading Strainers 5.x compatibility for OpolisCompat KubeJS plugin");
                event.register(ResourceLocation.fromNamespaceAndPath("strainers", "strainer"), UpdatedStrainerRecipeJS.SCHEMA);

            } else {
                event.register(ResourceLocation.fromNamespaceAndPath("strainers", "strainer"), StrainerRecipeJS.SCHEMA);
                event.register(ResourceLocation.fromNamespaceAndPath("strainers", "mesh_upgrade"), MeshUpgradeRecipeJS.SCHEMA);
                event.register(ResourceLocation.fromNamespaceAndPath("strainers", "output_upgrade"), OutputUpgradeRecipeJS.SCHEMA);
            }
        }

        //Casting
        event.register(ResourceLocation.fromNamespaceAndPath("casting", "fuel"), FuelRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("casting", "melting"), MeltingRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("casting", "mixing"), MixingRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("casting", "solidifier"), SolidifierRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("casting", "coolant"), CoolantRecipeJS.SCHEMA);

        //Market
        event.register(ResourceLocation.fromNamespaceAndPath("market", "market"), MarketRecipeJS.SCHEMA);

        //In World Recipes
        if (ModList.get().isLoaded("inworldrecipes")) {
            event.register(ResourceLocation.fromNamespaceAndPath("inworldrecipes", "block_interaction"), BlockInteractionRecipeJS.SCHEMA);
        }
        //Cloche
        event.register(ResourceLocation.fromNamespaceAndPath("cloche", "cloche"), ClocheRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("cloche", "dimensional_upgrade"), DimensionalUpgradeRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("cloche", "speed_upgrade"), com.benbenlaw.opoliscompat.kubejs.cloche.SpeedUpgradeRecipeJS.SCHEMA);


    }

    @Override
    public void registerRecipeComponents(RecipeComponentTypeRegistry registry) {
        if (ModList.get().isLoaded("inworldrecipes")) {
            registry.register(ClickTypeComponent.CONDITION);
            registry.register(BlockTargetComponent.CONDITION);
        }

        if (ModList.get().isLoaded("strainers")) {
            registry.register(MeshChanceResultComponent.CONDITION);
        }

        registry.register(ChanceResultComponent.CONDITION);
    }


    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(GROUP);
    }
}