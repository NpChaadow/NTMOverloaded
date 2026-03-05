package com.hbm.block.generic;

import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * HBM 1.20.1 - Simple glass pane / iron-bar style block.
 * Used for: reinforced_glass_pane, reinforced_laminate_pane.
 * Extends IronBarsBlock which handles the 6-directional connection logic.
 */
public class HBMGlassPaneBlock extends IronBarsBlock {
    public HBMGlassPaneBlock(BlockBehaviour.Properties props) {
        super(props);
    }
}