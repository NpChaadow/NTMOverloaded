package com.hbm.registry;

import com.hbm.registry.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        for (RegistryObject<Block> blockObj : ModBlocks.BLOCKS.getEntries()) {
            Block block = blockObj.get();
            // Skip blocks with no loot table (HBMNoDropBlock etc.)
            if (block.getLootTable().equals(BuiltInLootTables.EMPTY)) continue;
            dropSelf(block);
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(block -> !block.getLootTable().equals(BuiltInLootTables.EMPTY))
                .collect(Collectors.toList());
    }
}