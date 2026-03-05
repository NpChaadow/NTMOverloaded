package com.hbm.block.generic;

import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

/**
 * HBM 1.20.1 — replaces BlockFalling usage in HBM (1.7.10).
 *
 * Used for blocks like gravel_obsidian, ore_oil_sand, concrete_super_broken
 * that fall when unsupported.
 */
public class HBMFallingBlock extends FallingBlock {

    public HBMFallingBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    /** Convenience constructor — lets you pass a dust MapColor for the falling particle. */
    public HBMFallingBlock(MapColor dustColor, BlockBehaviour.Properties props) {
        // FallingBlock uses the block's own MapColor for the dust particle automatically
        super(props.mapColor(dustColor));
    }
}