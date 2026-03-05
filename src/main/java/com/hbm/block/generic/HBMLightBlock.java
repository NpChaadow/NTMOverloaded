package com.hbm.block.generic;

import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * HBM 1.20.1 - Simple light-emitting block with no tile entity.
 * Used for: reinforced_lamp_on, lamp_tritium_*_on, asphalt_light, reinforced_light.
 * Pass lightLevel via BlockBehaviour.Properties.lightLevel(s -> value).
 */
public class HBMLightBlock extends HBMBlock {
    public HBMLightBlock(BlockBehaviour.Properties props) {
        super(props);
    }
}