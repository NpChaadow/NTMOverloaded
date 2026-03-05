package com.hbm.block.generic;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * HBM 1.20.1 — replaces BlockGeneric / BlockBeaconable (1.7.10).
 *
 * BlockGeneric was a plain Block with no extra logic.
 * BlockBeaconable added beacon-base detection; in 1.20.1 this is handled
 * automatically by vanilla beacons when the block has a suitable MapColor,
 * so no override is needed.
 *
 * Use this as the base for simple blocks that need no TileEntity.
 * Blocks with truly no custom logic can be registered with a plain Block directly.
 */
public class HBMBlock extends Block {
    public HBMBlock(BlockBehaviour.Properties props) {
        super(props);
    }
}