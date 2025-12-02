package com.benbenlaw.opoliscompat.event;

import com.benbenlaw.opoliscompat.Compat;
import com.benbenlaw.opoliscompat.config.CompatStartupConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.level.ChunkEvent;

@EventBusSubscriber(modid = Compat.MOD_ID)
public class BlockReplacingEvent {

    /// Add this to BBL Core in the future 1.21.10 +
    @Deprecated(since = "5.2.0")
    @SubscribeEvent
    public static void onChunkGenerationRemovals(ChunkEvent.Load event) {

        if (!CompatStartupConfig.oreRemoval.get()) return;
        TagKey<Block> blockTag = TagKey.create(Registries.BLOCK, ResourceLocation.parse(CompatStartupConfig.blockTag.get()));

        boolean isNewChunk = event.isNewChunk();

        if (!isNewChunk) return;

        ChunkAccess chunk = event.getChunk();

        int startX = chunk.getPos().x << 4;
        int startZ = chunk.getPos().z << 4;

        for (int x = startX; x < startX + 16; x++) {
            for (int z = startZ; z < startZ + 16; z++) {
                for (int y = -64; y < 256; y++) {
                    BlockPos pos = new BlockPos(x, y, z);

                    BlockState originalState = chunk.getBlockState(pos);

                    Block block = originalState.getBlock();

                    if (block.builtInRegistryHolder().is(blockTag)) {

                        BlockState newBlockState = getReplacementBlockState(chunk, pos);
                        //BlockState newBlockState = Blocks.DIAMOND_BLOCK.defaultBlockState();

                        chunk.setBlockState(pos, newBlockState, false);


                    }
                }
            }
        }
    }

    ///setblock -286 -22 255
    private static BlockState getReplacementBlockState(ChunkAccess chunkAccess, BlockPos pos) {

        Direction[] directions = Direction.values();

        for (Direction direction : directions) {
            BlockPos neighborPos = pos.relative(direction);
            BlockState neighborState = chunkAccess.getBlockState(neighborPos);
            //FluidState neighborFluidState = level.getFluidState(neighborPos);

            if (!neighborState.isAir()
                    && neighborState.getFluidState().isEmpty()
                    && neighborState.isSolid()
                    && !neighborState.is(Tags.Blocks.ORES)
                    && neighborState.getCollisionShape(chunkAccess, neighborPos).isEmpty() == false) {
                return neighborState;
            }


        }

        return Blocks.STONE.defaultBlockState();
    }
}


