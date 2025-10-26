package com.benbenlaw.opoliscompat.kubejs;

import com.benbenlaw.inworldrecipes.recipes.BlockTarget;
import com.benbenlaw.inworldrecipes.recipes.BlockTargetCodec;
import com.benbenlaw.inworldrecipes.util.ClickType;
import com.benbenlaw.inworldrecipes.util.ClickTypeCodec;
import com.benbenlaw.opoliscompat.Compat;
import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.RecipeScriptContext;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.kubejs.recipe.filter.RecipeMatchContext;
import dev.latvian.mods.kubejs.recipe.match.ReplacementMatchInfo;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.TagType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class BlockTargetComponent implements RecipeComponent<BlockTarget> {

    public static final BlockTargetComponent INSTANCE = new BlockTargetComponent();
    public static final RecipeComponentType<?> CONDITION = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath(Compat.MOD_ID, "block_target"), INSTANCE);
    private static final Codec<BlockTarget> CODEC = BlockTargetCodec.CODEC;

    @Override
    public RecipeComponentType<?> type() {
        return CONDITION;
    }

    @Override
    public Codec<BlockTarget> codec() {
        return CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(BlockTarget.class);
    }

    @Override
    public BlockTarget wrap(RecipeScriptContext cx, Object from) {

        if (from instanceof BlockTarget target) {
            return target;
        }

        if (from instanceof String s) {
            if (s.startsWith("#")) {
                // Tag
                String tagId = s.substring(1);
                TagKey<Block> tag = TagKey.create(Registries.BLOCK, ResourceLocation.parse(tagId));
                return new BlockTarget.Tag(tag);
            } else {
                // Block or BlockState
                String blockPart = s;
                String propsPart = null;

                int bracketIndex = s.indexOf('[');
                if (bracketIndex >= 0 && s.endsWith("]")) {
                    blockPart = s.substring(0, bracketIndex);
                    propsPart = s.substring(bracketIndex + 1, s.length() - 1);
                }

                Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(blockPart));
                if (block == Blocks.AIR) throw new RuntimeException("Unknown block: " + blockPart);

                BlockState state = block.defaultBlockState();

                if (propsPart != null) {
                    String[] entries = propsPart.split(",");
                    for (String entry : entries) {
                        String[] kv = entry.split("=");
                        if (kv.length != 2) continue;
                        String key = kv[0].trim();
                        String value = kv[1].trim();
                        Property<?> property = block.getStateDefinition().getProperty(key);
                        if (property == null) continue;

                        if (property != null) {
                            // Use raw types to bypass generics safely
                            @SuppressWarnings({"rawtypes"})
                            Comparable val = property.getValue(value).orElse(null);
                            if (val != null) {
                                @SuppressWarnings({"rawtypes"})
                                Property rawProperty = property;
                                state = state.setValue(rawProperty, val);
                            }
                        }
                    }
                }

                return new BlockTarget.Single(state);
            }
        }

        throw new RuntimeException("Cannot wrap object into BlockTarget: " + from);
    }

    @Override
    public boolean matches(RecipeMatchContext cx, BlockTarget value, ReplacementMatchInfo match) {
        return true;
    }

}
