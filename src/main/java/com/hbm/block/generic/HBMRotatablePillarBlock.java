package com.hbm.block.generic;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * HBM 1.20.1 — replaces BlockRotatablePillar (1.7.10).
 *
 * Stores an AXIS blockstate property (X / Y / Z) so the block can be
 * placed horizontally or vertically, matching the original pillar behaviour.
 * The block model should use the standard pillar model referencing the
 * "axis" property (e.g. minecraft:block/cube_column).
 *
 * Used for: concrete_pillar, and any other axis-rotatable structural block.
 */
public class HBMRotatablePillarBlock extends RotatedPillarBlock {

    public HBMRotatablePillarBlock(BlockBehaviour.Properties props) {
        super(props);
    }
}