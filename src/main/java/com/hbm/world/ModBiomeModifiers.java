package com.hbm.world;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {

    public static final String MOD_ID = "hbm";

    private static ResourceKey<BiomeModifier> key(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
    }

    public static final ResourceKey<BiomeModifier> ADD_OVERWORLD_ORES  = key("add_overworld_ores");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_ORES  = key("add_deepslate_ores");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_ORES     = key("add_nether_ores");

    public static void bootstrap(BootstapContext<BiomeModifier> ctx) {
        var pfLookup    = ctx.lookup(Registries.PLACED_FEATURE);
        var biomeLookup = ctx.lookup(Registries.BIOME);

        // Pass the tag HolderSet directly — do NOT wrap in HolderSet.direct()
        // HolderSet.direct() takes individual Holder<Biome> entries, not a HolderSet
        var overworld = biomeLookup.getOrThrow(BiomeTags.IS_OVERWORLD);
        var nether    = biomeLookup.getOrThrow(BiomeTags.IS_NETHER);

        // ── Overworld ores ─────────────────────────────────────────────────
        ctx.register(ADD_OVERWORLD_ORES, new AddFeaturesBiomeModifier(
                overworld,   // <-- direct tag, not HolderSet.direct(overworld)
                HolderSet.direct(
                        pfLookup.getOrThrow(ModWorldGen.ORE_URANIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_URANIUM_SCORCHED_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_TITANIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_SULFUR_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_THORIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_NITER_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_COPPER_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_TUNGSTEN_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_ALUMINIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_FLUORITE_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_LEAD_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_SCHRABIDIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_BERYLLIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_AUSTRALIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_RARE_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_COBALT_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_CINNEBAR_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_COLTAN_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_ALEXANDRITE_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_LIGNITE_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_ASBESTOS_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_TIKITE_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_OIL_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_OIL_EMPTY_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_OIL_SAND_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_BASALT_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_VOLCANO_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_METEOR_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_BEDROCK_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_BEDROCK_OIL_PF),
                        pfLookup.getOrThrow(ModWorldGen.STONE_GNEISS_PF),
                        pfLookup.getOrThrow(ModWorldGen.STONE_DEPTH_PF),
                        pfLookup.getOrThrow(ModWorldGen.BASALT_PF),
                        pfLookup.getOrThrow(ModWorldGen.CLUSTER_IRON_PF),
                        pfLookup.getOrThrow(ModWorldGen.CLUSTER_TITANIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.CLUSTER_ALUMINIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.CLUSTER_COPPER_PF)
                ),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        // ── Deepslate / gneiss ores ────────────────────────────────────────
        ctx.register(ADD_DEEPSLATE_ORES, new AddFeaturesBiomeModifier(
                overworld,   // <-- direct tag
                HolderSet.direct(
                        pfLookup.getOrThrow(ModWorldGen.ORE_DEPTH_CINNEBAR_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_DEPTH_ZIRCONIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_DEPTH_BORAX_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_DEPTH_NETHER_NEODYMIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_GNEISS_IRON_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_GNEISS_GOLD_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_GNEISS_URANIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_GNEISS_URANIUM_SCORCHED_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_GNEISS_COPPER_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_GNEISS_ASBESTOS_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_GNEISS_LITHIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_GNEISS_SCHRABIDIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_GNEISS_RARE_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_GNEISS_GAS_PF),
                        pfLookup.getOrThrow(ModWorldGen.CLUSTER_DEPTH_IRON_PF),
                        pfLookup.getOrThrow(ModWorldGen.CLUSTER_DEPTH_TITANIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.CLUSTER_DEPTH_TUNGSTEN_PF)
                ),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        // ── Nether ores ────────────────────────────────────────────────────
        ctx.register(ADD_NETHER_ORES, new AddFeaturesBiomeModifier(
                nether,      // <-- direct tag
                HolderSet.direct(
                        pfLookup.getOrThrow(ModWorldGen.ORE_NETHER_COAL_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_NETHER_SMOLDERING_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_NETHER_URANIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_NETHER_URANIUM_SCORCHED_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_NETHER_PLUTONIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_NETHER_TUNGSTEN_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_NETHER_SULFUR_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_NETHER_FIRE_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_NETHER_COBALT_PF),
                        pfLookup.getOrThrow(ModWorldGen.ORE_NETHER_SCHRABIDIUM_PF),
                        pfLookup.getOrThrow(ModWorldGen.STONE_DEPTH_NETHER_PF)
                ),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }
}