package com.benbenlaw.opoliscompat.event;

import com.benbenlaw.caveopolis.block.CaveopolisBlocks;
import com.benbenlaw.colors.block.ColorsBlocks;
import com.benbenlaw.core.block.colored.*;
import com.benbenlaw.core.block.colored.flammable.*;
import com.benbenlaw.opoliscompat.Compat;
import com.benbenlaw.opoliscompat.config.CompatStartupConfig;
import net.minecraft.client.telemetry.events.WorldLoadEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static com.benbenlaw.core.util.ColorList.COLORS;

@EventBusSubscriber(modid = Compat.MOD_ID)
public class WorldReplaceEvent {


    @Deprecated(since = "5.2.0")
    @SubscribeEvent
    public static void onWorldReplaceEvent(ChunkEvent.Load event) {

        Level level = event.getChunk().getLevel();

        assert level != null;
        if (!level.isClientSide()) {

            if (CompatStartupConfig.worldConversion.get()) {

                //Caveopolis to Colors conversion

                // Get the chunk from the event
                ChunkAccess chunk = event.getChunk();

                // Loop through each block in the chunk, for example, 16x16 blocks in the chunk
                int startX = chunk.getPos().x << 4; // Get chunk start X position
                int startZ = chunk.getPos().z << 4; // Get chunk start Z position

                for (int x = startX; x < startX + 16; x++) {
                    for (int z = startZ; z < startZ + 16; z++) {
                        for (int y = -64; y < 256; y++) { // Check all Y levels (height 0 to 255)
                            BlockPos pos = new BlockPos(x, y, z);

                            // Get the original block state at this position
                            BlockState originalState = chunk.getBlockState(pos);

                            // Get the Block object from the original state
                            Block block = originalState.getBlock();

                            // Check if the block belongs to Caveopolis by checking its registry name directly
                            // Get the ResourceLocation of the block (in the form "modid:block_name")
                            ResourceLocation blockResourceLocation = BuiltInRegistries.BLOCK.getKey(block);

                            // Check if the block is from Caveopolis by its resource location
                            if (blockResourceLocation.getNamespace().equals("caveopolis")) {
                                String blockName = blockResourceLocation.getPath(); // Get the block name

                                // Check if it's a colored block, starting with "colored_"
                                if (blockName.startsWith("colored_")) {
                                    String color = null;

                                    // Dynamically determine the color property based on the block type
                                    if (originalState.hasProperty(ColoredBlock.COLOR)) {
                                        color = originalState.getValue(ColoredBlock.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredStairs.COLOR)) {
                                        color = originalState.getValue(ColoredStairs.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredCraftingTable.COLOR)) {
                                        color = originalState.getValue(ColoredCraftingTable.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredDoor.COLOR)) {
                                        color = originalState.getValue(ColoredDoor.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredDoublePlantBlock.COLOR)) {
                                        color = originalState.getValue(ColoredDoublePlantBlock.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredFence.COLOR)) {
                                        color = originalState.getValue(ColoredFence.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredFenceGate.COLOR)) {
                                        color = originalState.getValue(ColoredFenceGate.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredFlower.COLOR)) {
                                        color = originalState.getValue(ColoredFlower.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredFlowerPot.COLOR)) {
                                        color = originalState.getValue(ColoredFlowerPot.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredGrassBlock.COLOR)) {
                                        color = originalState.getValue(ColoredGrassBlock.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredLeaves.COLOR)) {
                                        color = originalState.getValue(ColoredLeaves.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredLog.COLOR)) {
                                        color = originalState.getValue(ColoredLog.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredPressurePlate.COLOR)) {
                                        color = originalState.getValue(ColoredPressurePlate.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredSapling.COLOR)) {
                                        color = originalState.getValue(ColoredSapling.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredSlab.COLOR)) {
                                        color = originalState.getValue(ColoredSlab.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredSnowyDirtBlock.COLOR)) {
                                        color = originalState.getValue(ColoredSnowyDirtBlock.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredSpreadingSnowyDirtBlock.COLOR)) {
                                        color = originalState.getValue(ColoredSpreadingSnowyDirtBlock.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredStairs.COLOR)) {
                                        color = originalState.getValue(ColoredStairs.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredTallGrassBlock.COLOR)) {
                                        color = originalState.getValue(ColoredTallGrassBlock.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredTrapDoor.COLOR)) {
                                        color = originalState.getValue(ColoredTrapDoor.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredWall.COLOR)) {
                                        color = originalState.getValue(ColoredWall.COLOR).toString();
                                    } else if (originalState.hasProperty(ColoredButton.COLOR)) {
                                        color = originalState.getValue(ColoredButton.COLOR).toString();
                                    } else if (originalState.hasProperty(FlammableColoredStairs.COLOR)) {
                                        color = originalState.getValue(FlammableColoredStairs.COLOR).toString();
                                    } else if (originalState.hasProperty(FlammableColoredSlabs.COLOR)) {
                                        color = originalState.getValue(FlammableColoredSlabs.COLOR).toString();
                                    } else if (originalState.hasProperty(FlammableColoredLog.COLOR)) {
                                        color = originalState.getValue(FlammableColoredLog.COLOR).toString();
                                    } else if (originalState.hasProperty(FlammableColoredLeaves.COLOR)) {
                                        color = originalState.getValue(FlammableColoredLeaves.COLOR).toString();
                                    } else if (originalState.hasProperty(FlammableColoredFenceGate.COLOR)) {
                                        color = originalState.getValue(FlammableColoredFenceGate.COLOR).toString();
                                    } else if (originalState.hasProperty(FlammableColoredFence.COLOR)) {
                                        color = originalState.getValue(FlammableColoredFence.COLOR).toString();
                                    } else if (originalState.hasProperty(FlammableColoredBlock.COLOR)) {
                                        color = originalState.getValue(FlammableColoredBlock.COLOR).toString();
                                    }


                                    // Add more `else if` clauses here for other block types, e.g., ColoredSlab, etc.

                                    // If a color is found, proceed with the replacement
                                    if (color != null) {
                                        // Generate the corresponding Colors mod block name by replacing "colored_" with "<color>_"


                                        if (blockName.contains("colored_polished_stone")) {
                                            blockName = "colored_polished";
                                        }
                                        if (blockName.contains("colored_polished_stone_stairs")) {
                                            blockName = "colored_polished_stairs";
                                        }
                                        if (blockName.contains("colored_polished_stone_slab")) {
                                            blockName = "colored_polished_slab";
                                        }
                                        if (blockName.contains("colored_polished_stone_wall")) {
                                            blockName = "colored_polished_wall";
                                        }
                                        if (blockName.contains("colored_polished_stone_button")) {
                                            blockName = "colored_polished_button";
                                        }
                                        if (blockName.contains("colored_polished_stone_pressure_plate")) {
                                            blockName = "colored_polished_pressure_plate";
                                        }

                                        String newBlockName = "colors:" + blockName.replace("colored_", color + "_");

                                        // Get the block state for the new Colors mod block
                                        BlockState newBlockState = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(newBlockName)).defaultBlockState();

                                        // If the block is found in the Colors mod, replace it
                                        if (newBlockState != null) {
                                            // Copy the properties of the original block state to the new block state
                                            for (Property<?> property : originalState.getProperties()) {
                                                // Ensure the new block state has this property and copy the value
                                                if (newBlockState.hasProperty(property)) {
                                                    newBlockState = newBlockState.setValue((Property) property, originalState.getValue(property));
                                                }
                                            }

                                            // Set the new block state at the position
                                            chunk.setBlockState(pos, newBlockState, false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }





}
