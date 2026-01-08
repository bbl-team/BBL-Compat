package com.benbenlaw.opoliscompat.config;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompatStartupConfig {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.ConfigValue<Boolean> worldConversion;
    public static final ModConfigSpec.ConfigValue<Boolean> oreRemoval;
    public static final ModConfigSpec.ConfigValue<String> blockTag;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> blockReplacements;



    static {

        BUILDER.comment("BBL Compat Startup Config")
                .push("BBL Compat");

        worldConversion = BUILDER.comment("Enables the in world conversion of caveopolis to colors blocks, will increase load times and may cause lag")
                .define("Caveopolis to Colors World Conversion", false);

        oreRemoval = BUILDER.comment("When enables all ores will be removed from world generation, only triggers during new chunk generation")
                .define("Disable Ore Gen", false);

        blockTag = BUILDER.comment("The block tag used to determine which blocks to replace when ore removal is enabled")
                .define("Block Tag for Ore Removal", "c:ores");

        blockReplacements = BUILDER.comment("A list of block replacements in the format 'block_to_replace, replacement_block'. E.g minecraft:iron_ore=minecraft:stone")
                .defineList("Block Replacements", List.of(""),
                        obj -> obj instanceof String);


        BUILDER.pop();

        //LAST
        SPEC = BUILDER.build();

    }

    public static Map<Block, Block> getBlockReplacementMap() {

        Map<Block, Block> map = new HashMap<>();

        for (String entry : blockReplacements.get()) {
            String[] parts = entry.split("=");
            if (parts.length != 2) continue;

            String fromStr = parts[0].trim();
            String toStr = parts[1].trim();

            Block from = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(fromStr));
            Block to = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(toStr));

            if (from != null && to != null) {
                map.put(from, to);
            }
        }
        return map;

    }

}
