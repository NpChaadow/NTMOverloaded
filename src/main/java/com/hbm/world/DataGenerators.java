package com.hbm.world;

import com.hbm.loottable.ModLootTableProvider;
import com.hbm.recipe.ModRecipeProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class DataGenerators {

    public static void gatherData(GatherDataEvent event) {
        var generator    = event.getGenerator();
        var packOutput   = generator.getPackOutput();
        var lookupFuture = event.getLookupProvider();

        // Loot tables
        generator.addProvider(
            event.includeServer(),
            ModLootTableProvider.create(packOutput)
        );

        // Worldgen + biome modifiers
        var registryBuilder = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModWorldGen::bootstrapConfiguredFeatures)
            .add(Registries.PLACED_FEATURE,     ModWorldGen::bootstrapPlacedFeatures)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

        generator.addProvider(
            event.includeServer(),
            new net.minecraftforge.common.data.DatapackBuiltinEntriesProvider(
                packOutput,
                lookupFuture,
                registryBuilder,
                Set.of("hbm")
            )
        );

        generator.addProvider(event.includeServer(),new ModRecipeProvider(packOutput));
    }
}