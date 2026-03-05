package com.hbm.block.generic;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.item.ItemStack;
import java.util.Collections;
import java.util.List;

/**
 * HBM 1.20.1 - replaces BlockNoDrop (1.7.10).
 * A block that drops nothing when broken.
 * Used for: oil_pipe (hidden pipe segment), floodlight_beam, spotlight_beam.
 */
public class HBMNoDropBlock extends HBMBlock {
    public HBMNoDropBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    public List<ItemStack> getDrops(net.minecraft.world.level.block.state.BlockState state,
                                     LootParams.Builder params) {
        return Collections.emptyList();
    }
}