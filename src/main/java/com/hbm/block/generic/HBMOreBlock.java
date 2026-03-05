package com.hbm.block.generic;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * HBM 1.20.1 — replaces BlockOre (1.7.10).
 *
 * Extends DropExperienceBlock so the block drops XP when mined without Silk Touch,
 * matching original HBM ore behaviour.
 *
 * Usage examples:
 *   new HBMOreBlock(props)                       // drops 0 XP (stone-like ore)
 *   new HBMOreBlock(props, UniformInt.of(2, 5))  // drops 2–5 XP (gemstone ore)
 */
public class HBMOreBlock extends DropExperienceBlock {

    public HBMOreBlock(BlockBehaviour.Properties props) {
        super(props, UniformInt.of(0, 0));
    }

    public HBMOreBlock(BlockBehaviour.Properties props, UniformInt xpRange) {
        super(props, xpRange);
    }

}