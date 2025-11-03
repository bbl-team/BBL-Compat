package com.benbenlaw.opoliscompat.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CompatStartupConfig {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.ConfigValue<Boolean> worldConversion;
    public static final ModConfigSpec.ConfigValue<Boolean> oreRemoval;



    static {

        BUILDER.comment("BBL Compat Startup Config")
                .push("BBL Compat");

        worldConversion = BUILDER.comment("Enables the in world conversion of caveopolis to colors blocks, will increase load times and may cause lag")
                .define("Caveopolis to Colors World Conversion", false);

        oreRemoval = BUILDER.comment("When enables all ores will be removed from world generation, only triggers during new chunk generation")
                .define("Disable Ore Gen", false);


        BUILDER.pop();

        //LAST
        SPEC = BUILDER.build();

    }
}
