package com.benbenlaw.opoliscompat;

import com.benbenlaw.core.config.ColorTintIndexConfig;
import com.benbenlaw.opoliscompat.config.CompatStartupConfig;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Compat.MOD_ID)
public class Compat {

    public static final String MOD_ID = "bblcompat";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Compat(final IEventBus eventBus, final ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.STARTUP, CompatStartupConfig.SPEC, "bbl/compat/startup.toml");
    }
}
