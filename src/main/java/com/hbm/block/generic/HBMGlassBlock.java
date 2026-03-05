package com.hbm.block.generic;

import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * HBM 1.20.1 - Simple glass-like block (no texture culling between same type).
 * Used for: reinforced_glass, reinforced_laminate.
 */
public class HBMGlassBlock extends GlassBlock {
    public HBMGlassBlock(BlockBehaviour.Properties props) {
        super(props);
    }
}