package com.hbm.world;

import com.hbm.registry.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ModWorldGen {

    public static final String MOD_ID = "hbm";

    // ── Resource keys ─────────────────────────────────────────────────────

    private static ResourceKey<ConfiguredFeature<?, ?>> cfKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
    }

    private static ResourceKey<PlacedFeature> pfKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
    }

    // ── ConfiguredFeature keys ────────────────────────────────────────────

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_URANIUM_CF            = cfKey("ore_uranium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_URANIUM_SCORCHED_CF   = cfKey("ore_uranium_scorched");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TITANIUM_CF           = cfKey("ore_titanium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SULFUR_CF             = cfKey("ore_sulfur");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_THORIUM_CF            = cfKey("ore_thorium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NITER_CF              = cfKey("ore_niter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_COPPER_CF             = cfKey("ore_copper");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TUNGSTEN_CF           = cfKey("ore_tungsten");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALUMINIUM_CF          = cfKey("ore_aluminium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_FLUORITE_CF           = cfKey("ore_fluorite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LEAD_CF               = cfKey("ore_lead");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SCHRABIDIUM_CF        = cfKey("ore_schrabidium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BERYLLIUM_CF          = cfKey("ore_beryllium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_AUSTRALIUM_CF         = cfKey("ore_australium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RARE_CF               = cfKey("ore_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_COBALT_CF             = cfKey("ore_cobalt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CINNEBAR_CF           = cfKey("ore_cinnebar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_COLTAN_CF             = cfKey("ore_coltan");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALEXANDRITE_CF        = cfKey("ore_alexandrite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LIGNITE_CF            = cfKey("ore_lignite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ASBESTOS_CF           = cfKey("ore_asbestos");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TIKITE_CF             = cfKey("ore_tikite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_OIL_CF                = cfKey("ore_oil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_OIL_EMPTY_CF          = cfKey("ore_oil_empty");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_OIL_SAND_CF           = cfKey("ore_oil_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DEPTH_CINNEBAR_CF     = cfKey("ore_depth_cinnebar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DEPTH_ZIRCONIUM_CF    = cfKey("ore_depth_zirconium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DEPTH_BORAX_CF        = cfKey("ore_depth_borax");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DEPTH_NETHER_NEODYMIUM_CF = cfKey("ore_depth_nether_neodymium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GNEISS_IRON_CF        = cfKey("ore_gneiss_iron");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GNEISS_GOLD_CF        = cfKey("ore_gneiss_gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GNEISS_URANIUM_CF     = cfKey("ore_gneiss_uranium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GNEISS_URANIUM_SCORCHED_CF = cfKey("ore_gneiss_uranium_scorched");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GNEISS_COPPER_CF      = cfKey("ore_gneiss_copper");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GNEISS_ASBESTOS_CF    = cfKey("ore_gneiss_asbestos");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GNEISS_LITHIUM_CF     = cfKey("ore_gneiss_lithium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GNEISS_SCHRABIDIUM_CF = cfKey("ore_gneiss_schrabidium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GNEISS_RARE_CF        = cfKey("ore_gneiss_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GNEISS_GAS_CF         = cfKey("ore_gneiss_gas");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_COAL_CF        = cfKey("ore_nether_coal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_SMOLDERING_CF  = cfKey("ore_nether_smoldering");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_URANIUM_CF     = cfKey("ore_nether_uranium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_URANIUM_SCORCHED_CF = cfKey("ore_nether_uranium_scorched");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_PLUTONIUM_CF   = cfKey("ore_nether_plutonium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_TUNGSTEN_CF    = cfKey("ore_nether_tungsten");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_SULFUR_CF      = cfKey("ore_nether_sulfur");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_FIRE_CF        = cfKey("ore_nether_fire");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_COBALT_CF      = cfKey("ore_nether_cobalt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NETHER_SCHRABIDIUM_CF = cfKey("ore_nether_schrabidium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BASALT_CF             = cfKey("ore_basalt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_VOLCANO_CF            = cfKey("ore_volcano");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_METEOR_CF             = cfKey("ore_meteor");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BEDROCK_CF            = cfKey("ore_bedrock");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BEDROCK_OIL_CF        = cfKey("ore_bedrock_oil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_GNEISS_CF           = cfKey("stone_gneiss");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_DEPTH_CF            = cfKey("stone_depth");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_DEPTH_NETHER_CF     = cfKey("stone_depth_nether");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BASALT_CF                 = cfKey("basalt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLUSTER_IRON_CF           = cfKey("cluster_iron");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLUSTER_TITANIUM_CF       = cfKey("cluster_titanium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLUSTER_ALUMINIUM_CF      = cfKey("cluster_aluminium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLUSTER_COPPER_CF         = cfKey("cluster_copper");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLUSTER_DEPTH_IRON_CF     = cfKey("cluster_depth_iron");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLUSTER_DEPTH_TITANIUM_CF = cfKey("cluster_depth_titanium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLUSTER_DEPTH_TUNGSTEN_CF = cfKey("cluster_depth_tungsten");

    // ── PlacedFeature keys ────────────────────────────────────────────────

    public static final ResourceKey<PlacedFeature> ORE_URANIUM_PF            = pfKey("ore_uranium");
    public static final ResourceKey<PlacedFeature> ORE_URANIUM_SCORCHED_PF   = pfKey("ore_uranium_scorched");
    public static final ResourceKey<PlacedFeature> ORE_TITANIUM_PF           = pfKey("ore_titanium");
    public static final ResourceKey<PlacedFeature> ORE_SULFUR_PF             = pfKey("ore_sulfur");
    public static final ResourceKey<PlacedFeature> ORE_THORIUM_PF            = pfKey("ore_thorium");
    public static final ResourceKey<PlacedFeature> ORE_NITER_PF              = pfKey("ore_niter");
    public static final ResourceKey<PlacedFeature> ORE_COPPER_PF             = pfKey("ore_copper");
    public static final ResourceKey<PlacedFeature> ORE_TUNGSTEN_PF           = pfKey("ore_tungsten");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINIUM_PF          = pfKey("ore_aluminium");
    public static final ResourceKey<PlacedFeature> ORE_FLUORITE_PF           = pfKey("ore_fluorite");
    public static final ResourceKey<PlacedFeature> ORE_LEAD_PF               = pfKey("ore_lead");
    public static final ResourceKey<PlacedFeature> ORE_SCHRABIDIUM_PF        = pfKey("ore_schrabidium");
    public static final ResourceKey<PlacedFeature> ORE_BERYLLIUM_PF          = pfKey("ore_beryllium");
    public static final ResourceKey<PlacedFeature> ORE_AUSTRALIUM_PF         = pfKey("ore_australium");
    public static final ResourceKey<PlacedFeature> ORE_RARE_PF               = pfKey("ore_rare");
    public static final ResourceKey<PlacedFeature> ORE_COBALT_PF             = pfKey("ore_cobalt");
    public static final ResourceKey<PlacedFeature> ORE_CINNEBAR_PF           = pfKey("ore_cinnebar");
    public static final ResourceKey<PlacedFeature> ORE_COLTAN_PF             = pfKey("ore_coltan");
    public static final ResourceKey<PlacedFeature> ORE_ALEXANDRITE_PF        = pfKey("ore_alexandrite");
    public static final ResourceKey<PlacedFeature> ORE_LIGNITE_PF            = pfKey("ore_lignite");
    public static final ResourceKey<PlacedFeature> ORE_ASBESTOS_PF           = pfKey("ore_asbestos");
    public static final ResourceKey<PlacedFeature> ORE_TIKITE_PF             = pfKey("ore_tikite");
    public static final ResourceKey<PlacedFeature> ORE_OIL_PF                = pfKey("ore_oil");
    public static final ResourceKey<PlacedFeature> ORE_OIL_EMPTY_PF          = pfKey("ore_oil_empty");
    public static final ResourceKey<PlacedFeature> ORE_OIL_SAND_PF           = pfKey("ore_oil_sand");
    public static final ResourceKey<PlacedFeature> ORE_DEPTH_CINNEBAR_PF     = pfKey("ore_depth_cinnebar");
    public static final ResourceKey<PlacedFeature> ORE_DEPTH_ZIRCONIUM_PF    = pfKey("ore_depth_zirconium");
    public static final ResourceKey<PlacedFeature> ORE_DEPTH_BORAX_PF        = pfKey("ore_depth_borax");
    public static final ResourceKey<PlacedFeature> ORE_DEPTH_NETHER_NEODYMIUM_PF = pfKey("ore_depth_nether_neodymium");
    public static final ResourceKey<PlacedFeature> ORE_GNEISS_IRON_PF        = pfKey("ore_gneiss_iron");
    public static final ResourceKey<PlacedFeature> ORE_GNEISS_GOLD_PF        = pfKey("ore_gneiss_gold");
    public static final ResourceKey<PlacedFeature> ORE_GNEISS_URANIUM_PF     = pfKey("ore_gneiss_uranium");
    public static final ResourceKey<PlacedFeature> ORE_GNEISS_URANIUM_SCORCHED_PF = pfKey("ore_gneiss_uranium_scorched");
    public static final ResourceKey<PlacedFeature> ORE_GNEISS_COPPER_PF      = pfKey("ore_gneiss_copper");
    public static final ResourceKey<PlacedFeature> ORE_GNEISS_ASBESTOS_PF    = pfKey("ore_gneiss_asbestos");
    public static final ResourceKey<PlacedFeature> ORE_GNEISS_LITHIUM_PF     = pfKey("ore_gneiss_lithium");
    public static final ResourceKey<PlacedFeature> ORE_GNEISS_SCHRABIDIUM_PF = pfKey("ore_gneiss_schrabidium");
    public static final ResourceKey<PlacedFeature> ORE_GNEISS_RARE_PF        = pfKey("ore_gneiss_rare");
    public static final ResourceKey<PlacedFeature> ORE_GNEISS_GAS_PF         = pfKey("ore_gneiss_gas");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_COAL_PF        = pfKey("ore_nether_coal");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_SMOLDERING_PF  = pfKey("ore_nether_smoldering");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_URANIUM_PF     = pfKey("ore_nether_uranium");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_URANIUM_SCORCHED_PF = pfKey("ore_nether_uranium_scorched");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_PLUTONIUM_PF   = pfKey("ore_nether_plutonium");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_TUNGSTEN_PF    = pfKey("ore_nether_tungsten");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_SULFUR_PF      = pfKey("ore_nether_sulfur");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_FIRE_PF        = pfKey("ore_nether_fire");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_COBALT_PF      = pfKey("ore_nether_cobalt");
    public static final ResourceKey<PlacedFeature> ORE_NETHER_SCHRABIDIUM_PF = pfKey("ore_nether_schrabidium");
    public static final ResourceKey<PlacedFeature> ORE_BASALT_PF             = pfKey("ore_basalt");
    public static final ResourceKey<PlacedFeature> ORE_VOLCANO_PF            = pfKey("ore_volcano");
    public static final ResourceKey<PlacedFeature> ORE_METEOR_PF             = pfKey("ore_meteor");
    public static final ResourceKey<PlacedFeature> ORE_BEDROCK_PF            = pfKey("ore_bedrock");
    public static final ResourceKey<PlacedFeature> ORE_BEDROCK_OIL_PF        = pfKey("ore_bedrock_oil");
    public static final ResourceKey<PlacedFeature> STONE_GNEISS_PF           = pfKey("stone_gneiss");
    public static final ResourceKey<PlacedFeature> STONE_DEPTH_PF            = pfKey("stone_depth");
    public static final ResourceKey<PlacedFeature> STONE_DEPTH_NETHER_PF     = pfKey("stone_depth_nether");
    public static final ResourceKey<PlacedFeature> BASALT_PF                 = pfKey("basalt");
    public static final ResourceKey<PlacedFeature> CLUSTER_IRON_PF           = pfKey("cluster_iron");
    public static final ResourceKey<PlacedFeature> CLUSTER_TITANIUM_PF       = pfKey("cluster_titanium");
    public static final ResourceKey<PlacedFeature> CLUSTER_ALUMINIUM_PF      = pfKey("cluster_aluminium");
    public static final ResourceKey<PlacedFeature> CLUSTER_COPPER_PF         = pfKey("cluster_copper");
    public static final ResourceKey<PlacedFeature> CLUSTER_DEPTH_IRON_PF     = pfKey("cluster_depth_iron");
    public static final ResourceKey<PlacedFeature> CLUSTER_DEPTH_TITANIUM_PF = pfKey("cluster_depth_titanium");
    public static final ResourceKey<PlacedFeature> CLUSTER_DEPTH_TUNGSTEN_PF = pfKey("cluster_depth_tungsten");

    // ── Bootstrap: ConfiguredFeatures ────────────────────────────────────

    public static void bootstrapConfiguredFeatures(BootstapContext<ConfiguredFeature<?, ?>> ctx) {
        var stone     = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        var deepslate = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        var nether    = new BlockMatchTest(net.minecraft.world.level.block.Blocks.NETHERRACK);
        var sand      = new BlockMatchTest(net.minecraft.world.level.block.Blocks.SAND);

        // Overworld ores — replace stone
        reg(ctx, ORE_URANIUM_CF,            ore(ModBlocks.ORE_URANIUM.get(),            8,  stone));
        reg(ctx, ORE_URANIUM_SCORCHED_CF,   ore(ModBlocks.ORE_URANIUM_SCORCHED.get(),   6,  stone));
        reg(ctx, ORE_TITANIUM_CF,           ore(ModBlocks.ORE_TITANIUM.get(),           8,  stone));
        reg(ctx, ORE_SULFUR_CF,             ore(ModBlocks.ORE_SULFUR.get(),             8,  stone));
        reg(ctx, ORE_THORIUM_CF,            ore(ModBlocks.ORE_THORIUM.get(),            6,  stone));
        reg(ctx, ORE_NITER_CF,              ore(ModBlocks.ORE_NITER.get(),              8,  stone));
        reg(ctx, ORE_COPPER_CF,             ore(ModBlocks.ORE_COPPER.get(),             10, stone));
        reg(ctx, ORE_TUNGSTEN_CF,           ore(ModBlocks.ORE_TUNGSTEN.get(),           6,  stone));
        reg(ctx, ORE_ALUMINIUM_CF,          ore(ModBlocks.ORE_ALUMINIUM.get(),          10, stone));
        reg(ctx, ORE_FLUORITE_CF,           ore(ModBlocks.ORE_FLUORITE.get(),           7,  stone));
        reg(ctx, ORE_LEAD_CF,               ore(ModBlocks.ORE_LEAD.get(),               8,  stone));
        reg(ctx, ORE_SCHRABIDIUM_CF,        ore(ModBlocks.ORE_SCHRABIDIUM.get(),        4,  stone));
        reg(ctx, ORE_BERYLLIUM_CF,          ore(ModBlocks.ORE_BERYLLIUM.get(),          6,  stone));
        reg(ctx, ORE_AUSTRALIUM_CF,         ore(ModBlocks.ORE_AUSTRALIUM.get(),         3,  stone));
        reg(ctx, ORE_RARE_CF,               ore(ModBlocks.ORE_RARE.get(),               5,  stone));
        reg(ctx, ORE_COBALT_CF,             ore(ModBlocks.ORE_COBALT.get(),             6,  stone));
        reg(ctx, ORE_CINNEBAR_CF,           ore(ModBlocks.ORE_CINNEBAR.get(),           5,  stone));
        reg(ctx, ORE_COLTAN_CF,             ore(ModBlocks.ORE_COLTAN.get(),             5,  stone));
        reg(ctx, ORE_ALEXANDRITE_CF,        ore(ModBlocks.ORE_ALEXANDRITE.get(),        4,  stone));
        reg(ctx, ORE_LIGNITE_CF,            ore(ModBlocks.ORE_LIGNITE.get(),            16, stone));
        reg(ctx, ORE_ASBESTOS_CF,           ore(ModBlocks.ORE_ASBESTOS.get(),           8,  stone));
        reg(ctx, ORE_TIKITE_CF,             ore(ModBlocks.ORE_TIKITE.get(),             4,  stone));
        reg(ctx, ORE_OIL_CF,                ore(ModBlocks.ORE_OIL.get(),                12, stone));
        reg(ctx, ORE_OIL_EMPTY_CF,          ore(ModBlocks.ORE_OIL_EMPTY.get(),          8,  stone));
        reg(ctx, ORE_OIL_SAND_CF,           ore(ModBlocks.ORE_OIL_SAND.get(),           10, sand));
        reg(ctx, ORE_BASALT_CF,             ore(ModBlocks.ORE_BASALT.get(),             8,  stone));
        reg(ctx, ORE_VOLCANO_CF,            ore(ModBlocks.ORE_VOLCANO.get(),            8,  stone));
        reg(ctx, ORE_METEOR_CF,             ore(ModBlocks.ORE_METEOR.get(),             6,  stone));
        reg(ctx, ORE_BEDROCK_CF,            ore(ModBlocks.ORE_BEDROCK.get(),            4,  stone));
        reg(ctx, ORE_BEDROCK_OIL_CF,        ore(ModBlocks.ORE_BEDROCK_OIL.get(),        4,  stone));

        // Deepslate ores — replace deepslate
        reg(ctx, ORE_DEPTH_CINNEBAR_CF,          ore(ModBlocks.ORE_DEPTH_CINNEBAR.get(),          5,  deepslate));
        reg(ctx, ORE_DEPTH_ZIRCONIUM_CF,         ore(ModBlocks.ORE_DEPTH_ZIRCONIUM.get(),         5,  deepslate));
        reg(ctx, ORE_DEPTH_BORAX_CF,             ore(ModBlocks.ORE_DEPTH_BORAX.get(),             6,  deepslate));
        reg(ctx, ORE_DEPTH_NETHER_NEODYMIUM_CF,  ore(ModBlocks.ORE_DEPTH_NETHER_NEODYMIUM.get(),  4,  deepslate));
        reg(ctx, ORE_GNEISS_IRON_CF,             ore(ModBlocks.ORE_GNEISS_IRON.get(),             10, stone));
        reg(ctx, ORE_GNEISS_GOLD_CF,             ore(ModBlocks.ORE_GNEISS_GOLD.get(),             6,  stone));
        reg(ctx, ORE_GNEISS_URANIUM_CF,          ore(ModBlocks.ORE_GNEISS_URANIUM.get(),          6,  stone));
        reg(ctx, ORE_GNEISS_URANIUM_SCORCHED_CF, ore(ModBlocks.ORE_GNEISS_URANIUM_SCORCHED.get(), 4,  stone));
        reg(ctx, ORE_GNEISS_COPPER_CF,           ore(ModBlocks.ORE_GNEISS_COPPER.get(),           8,  stone));
        reg(ctx, ORE_GNEISS_ASBESTOS_CF,         ore(ModBlocks.ORE_GNEISS_ASBESTOS.get(),         6,  stone));
        reg(ctx, ORE_GNEISS_LITHIUM_CF,          ore(ModBlocks.ORE_GNEISS_LITHIUM.get(),          5,  stone));
        reg(ctx, ORE_GNEISS_SCHRABIDIUM_CF,      ore(ModBlocks.ORE_GNEISS_SCHRABIDIUM.get(),      3,  stone));
        reg(ctx, ORE_GNEISS_RARE_CF,             ore(ModBlocks.ORE_GNEISS_RARE.get(),             4,  stone));
        reg(ctx, ORE_GNEISS_GAS_CF,              ore(ModBlocks.ORE_GNEISS_GAS.get(),              8,  stone));
        reg(ctx, CLUSTER_DEPTH_IRON_CF,          ore(ModBlocks.CLUSTER_DEPTH_IRON.get(),          5,  deepslate));
        reg(ctx, CLUSTER_DEPTH_TITANIUM_CF,      ore(ModBlocks.CLUSTER_DEPTH_TITANIUM.get(),      4,  deepslate));
        reg(ctx, CLUSTER_DEPTH_TUNGSTEN_CF,      ore(ModBlocks.CLUSTER_DEPTH_TUNGSTEN.get(),      4,  deepslate));

        // Nether ores — replace netherrack
        reg(ctx, ORE_NETHER_COAL_CF,             ore(ModBlocks.ORE_NETHER_COAL.get(),             12, nether));
        reg(ctx, ORE_NETHER_SMOLDERING_CF,       ore(ModBlocks.ORE_NETHER_SMOLDERING.get(),       8,  nether));
        reg(ctx, ORE_NETHER_URANIUM_CF,          ore(ModBlocks.ORE_NETHER_URANIUM.get(),          6,  nether));
        reg(ctx, ORE_NETHER_URANIUM_SCORCHED_CF, ore(ModBlocks.ORE_NETHER_URANIUM_SCORCHED.get(), 5,  nether));
        reg(ctx, ORE_NETHER_PLUTONIUM_CF,        ore(ModBlocks.ORE_NETHER_PLUTONIUM.get(),        4,  nether));
        reg(ctx, ORE_NETHER_TUNGSTEN_CF,         ore(ModBlocks.ORE_NETHER_TUNGSTEN.get(),         6,  nether));
        reg(ctx, ORE_NETHER_SULFUR_CF,           ore(ModBlocks.ORE_NETHER_SULFUR.get(),           10, nether));
        reg(ctx, ORE_NETHER_FIRE_CF,             ore(ModBlocks.ORE_NETHER_FIRE.get(),             8,  nether));
        reg(ctx, ORE_NETHER_COBALT_CF,           ore(ModBlocks.ORE_NETHER_COBALT.get(),           6,  nether));
        reg(ctx, ORE_NETHER_SCHRABIDIUM_CF,      ore(ModBlocks.ORE_NETHER_SCHRABIDIUM.get(),      3,  nether));
        reg(ctx, STONE_DEPTH_NETHER_CF,          ore(ModBlocks.STONE_DEPTH_NETHER.get(),          24, nether));

        // Stone/cluster ambient generation
        reg(ctx, STONE_GNEISS_CF,       ore(ModBlocks.STONE_GNEISS.get(),       32, stone));
        reg(ctx, STONE_DEPTH_CF,        ore(ModBlocks.STONE_DEPTH.get(),        32, stone));
        reg(ctx, BASALT_CF,             ore(ModBlocks.BASALT.get(),             24, stone));
        reg(ctx, CLUSTER_IRON_CF,       ore(ModBlocks.CLUSTER_IRON.get(),       6,  stone));
        reg(ctx, CLUSTER_TITANIUM_CF,   ore(ModBlocks.CLUSTER_TITANIUM.get(),   5,  stone));
        reg(ctx, CLUSTER_ALUMINIUM_CF,  ore(ModBlocks.CLUSTER_ALUMINIUM.get(),  6,  stone));
        reg(ctx, CLUSTER_COPPER_CF,     ore(ModBlocks.CLUSTER_COPPER.get(),     6,  stone));
    }

    // ── Bootstrap: PlacedFeatures ─────────────────────────────────────────

    public static void bootstrapPlacedFeatures(BootstapContext<PlacedFeature> ctx) {
        var cfLookup = ctx.lookup(Registries.CONFIGURED_FEATURE);

        // Overworld
        place(ctx, cfLookup, ORE_URANIUM_PF,          ORE_URANIUM_CF,          4,  -64,  64);
        place(ctx, cfLookup, ORE_URANIUM_SCORCHED_PF,  ORE_URANIUM_SCORCHED_CF, 2,  -64,  32);
        place(ctx, cfLookup, ORE_TITANIUM_PF,          ORE_TITANIUM_CF,         4,  -64,  64);
        place(ctx, cfLookup, ORE_SULFUR_PF,            ORE_SULFUR_CF,           6,   32,  96);
        place(ctx, cfLookup, ORE_THORIUM_PF,           ORE_THORIUM_CF,          3,  -64,  48);
        place(ctx, cfLookup, ORE_NITER_PF,             ORE_NITER_CF,            5,   32,  96);
        place(ctx, cfLookup, ORE_COPPER_PF,            ORE_COPPER_CF,           8,  -16,  96);
        place(ctx, cfLookup, ORE_TUNGSTEN_PF,          ORE_TUNGSTEN_CF,         3,  -64,  16);
        place(ctx, cfLookup, ORE_ALUMINIUM_PF,         ORE_ALUMINIUM_CF,        6,   16,  80);
        place(ctx, cfLookup, ORE_FLUORITE_PF,          ORE_FLUORITE_CF,         4,  -32,  48);
        place(ctx, cfLookup, ORE_LEAD_PF,              ORE_LEAD_CF,             5,  -64,  48);
        place(ctx, cfLookup, ORE_SCHRABIDIUM_PF,       ORE_SCHRABIDIUM_CF,      1,  -64,   0);
        place(ctx, cfLookup, ORE_BERYLLIUM_PF,         ORE_BERYLLIUM_CF,        3,  -32,  32);
        place(ctx, cfLookup, ORE_AUSTRALIUM_PF,        ORE_AUSTRALIUM_CF,       1,  -64, -16);
        place(ctx, cfLookup, ORE_RARE_PF,              ORE_RARE_CF,             2,  -64,  16);
        place(ctx, cfLookup, ORE_COBALT_PF,            ORE_COBALT_CF,           3,  -64,  32);
        place(ctx, cfLookup, ORE_CINNEBAR_PF,          ORE_CINNEBAR_CF,         3,    0,  48);
        place(ctx, cfLookup, ORE_COLTAN_PF,            ORE_COLTAN_CF,           2,  -64,  16);
        place(ctx, cfLookup, ORE_ALEXANDRITE_PF,       ORE_ALEXANDRITE_CF,      2,  -64,  16);
        place(ctx, cfLookup, ORE_LIGNITE_PF,           ORE_LIGNITE_CF,          8,    0,  80);
        place(ctx, cfLookup, ORE_ASBESTOS_PF,          ORE_ASBESTOS_CF,         4,  -32,  48);
        place(ctx, cfLookup, ORE_TIKITE_PF,            ORE_TIKITE_CF,           1,  -64, -32);
        place(ctx, cfLookup, ORE_OIL_PF,               ORE_OIL_CF,              3,  -64,  48);
        place(ctx, cfLookup, ORE_OIL_EMPTY_PF,         ORE_OIL_EMPTY_CF,        2,  -64,  48);
        place(ctx, cfLookup, ORE_OIL_SAND_PF,          ORE_OIL_SAND_CF,         3,   48,  80);
        place(ctx, cfLookup, ORE_BASALT_PF,            ORE_BASALT_CF,           4,  -64,  64);
        place(ctx, cfLookup, ORE_VOLCANO_PF,           ORE_VOLCANO_CF,          2,   48,  96);
        place(ctx, cfLookup, ORE_METEOR_PF,            ORE_METEOR_CF,           1,   32,  96);
        place(ctx, cfLookup, ORE_BEDROCK_PF,           ORE_BEDROCK_CF,          1,  -64, -56);
        place(ctx, cfLookup, ORE_BEDROCK_OIL_PF,       ORE_BEDROCK_OIL_CF,      1,  -64, -56);
        place(ctx, cfLookup, STONE_GNEISS_PF,          STONE_GNEISS_CF,         4,  -64,   0);
        place(ctx, cfLookup, STONE_DEPTH_PF,           STONE_DEPTH_CF,          6,  -64,   0);
        place(ctx, cfLookup, BASALT_PF,                BASALT_CF,               3,  -32,  64);
        place(ctx, cfLookup, CLUSTER_IRON_PF,          CLUSTER_IRON_CF,         4,  -64,  32);
        place(ctx, cfLookup, CLUSTER_TITANIUM_PF,      CLUSTER_TITANIUM_CF,     3,  -64,  32);
        place(ctx, cfLookup, CLUSTER_ALUMINIUM_PF,     CLUSTER_ALUMINIUM_CF,    3,   16,  80);
        place(ctx, cfLookup, CLUSTER_COPPER_PF,        CLUSTER_COPPER_CF,       4,  -16,  80);

        // Deepslate
        place(ctx, cfLookup, ORE_DEPTH_CINNEBAR_PF,         ORE_DEPTH_CINNEBAR_CF,         2, -64,  0);
        place(ctx, cfLookup, ORE_DEPTH_ZIRCONIUM_PF,        ORE_DEPTH_ZIRCONIUM_CF,        2, -64,  0);
        place(ctx, cfLookup, ORE_DEPTH_BORAX_PF,            ORE_DEPTH_BORAX_CF,            3, -64,  0);
        place(ctx, cfLookup, ORE_DEPTH_NETHER_NEODYMIUM_PF, ORE_DEPTH_NETHER_NEODYMIUM_CF, 1, -64,  0);
        place(ctx, cfLookup, ORE_GNEISS_IRON_PF,            ORE_GNEISS_IRON_CF,            6, -64,  0);
        place(ctx, cfLookup, ORE_GNEISS_GOLD_PF,            ORE_GNEISS_GOLD_CF,            3, -64,  0);
        place(ctx, cfLookup, ORE_GNEISS_URANIUM_PF,         ORE_GNEISS_URANIUM_CF,         3, -64,  0);
        place(ctx, cfLookup, ORE_GNEISS_URANIUM_SCORCHED_PF,ORE_GNEISS_URANIUM_SCORCHED_CF,2, -64,  0);
        place(ctx, cfLookup, ORE_GNEISS_COPPER_PF,          ORE_GNEISS_COPPER_CF,          5, -64,  0);
        place(ctx, cfLookup, ORE_GNEISS_ASBESTOS_PF,        ORE_GNEISS_ASBESTOS_CF,        3, -64,  0);
        place(ctx, cfLookup, ORE_GNEISS_LITHIUM_PF,         ORE_GNEISS_LITHIUM_CF,         2, -64,  0);
        place(ctx, cfLookup, ORE_GNEISS_SCHRABIDIUM_PF,     ORE_GNEISS_SCHRABIDIUM_CF,     1, -64,-32);
        place(ctx, cfLookup, ORE_GNEISS_RARE_PF,            ORE_GNEISS_RARE_CF,            2, -64,  0);
        place(ctx, cfLookup, ORE_GNEISS_GAS_PF,             ORE_GNEISS_GAS_CF,             2, -64,  0);
        place(ctx, cfLookup, CLUSTER_DEPTH_IRON_PF,         CLUSTER_DEPTH_IRON_CF,         3, -64,  0);
        place(ctx, cfLookup, CLUSTER_DEPTH_TITANIUM_PF,     CLUSTER_DEPTH_TITANIUM_CF,     2, -64,  0);
        place(ctx, cfLookup, CLUSTER_DEPTH_TUNGSTEN_PF,     CLUSTER_DEPTH_TUNGSTEN_CF,     2, -64,  0);

        // Nether
        place(ctx, cfLookup, ORE_NETHER_COAL_PF,            ORE_NETHER_COAL_CF,            8,  10, 120);
        place(ctx, cfLookup, ORE_NETHER_SMOLDERING_PF,      ORE_NETHER_SMOLDERING_CF,      4,  10, 120);
        place(ctx, cfLookup, ORE_NETHER_URANIUM_PF,         ORE_NETHER_URANIUM_CF,         3,  10,  80);
        place(ctx, cfLookup, ORE_NETHER_URANIUM_SCORCHED_PF,ORE_NETHER_URANIUM_SCORCHED_CF,2,  10,  80);
        place(ctx, cfLookup, ORE_NETHER_PLUTONIUM_PF,       ORE_NETHER_PLUTONIUM_CF,       1,  10,  48);
        place(ctx, cfLookup, ORE_NETHER_TUNGSTEN_PF,        ORE_NETHER_TUNGSTEN_CF,        3,  10,  80);
        place(ctx, cfLookup, ORE_NETHER_SULFUR_PF,          ORE_NETHER_SULFUR_CF,          6,  10, 120);
        place(ctx, cfLookup, ORE_NETHER_FIRE_PF,            ORE_NETHER_FIRE_CF,            4,  10, 120);
        place(ctx, cfLookup, ORE_NETHER_COBALT_PF,          ORE_NETHER_COBALT_CF,          3,  10,  80);
        place(ctx, cfLookup, ORE_NETHER_SCHRABIDIUM_PF,     ORE_NETHER_SCHRABIDIUM_CF,     1,  10,  48);
        place(ctx, cfLookup, STONE_DEPTH_NETHER_PF,         STONE_DEPTH_NETHER_CF,         4,  10, 120);
    }

    // ── Helpers ───────────────────────────────────────────────────────────

    private static OreConfiguration ore(net.minecraft.world.level.block.Block block, int size,
            net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest target) {
        return new OreConfiguration(target, block.defaultBlockState(), size);
    }

    private static void reg(BootstapContext<ConfiguredFeature<?, ?>> ctx,
            ResourceKey<ConfiguredFeature<?, ?>> key, OreConfiguration cfg) {
        ctx.register(key, new ConfiguredFeature<>(Feature.ORE, cfg));
    }

    private static void place(BootstapContext<PlacedFeature> ctx,
            net.minecraft.core.HolderGetter<ConfiguredFeature<?, ?>> lookup,
            ResourceKey<PlacedFeature> pfKey,
            ResourceKey<ConfiguredFeature<?, ?>> cfKey,
            int count, int minY, int maxY) {
        ctx.register(pfKey, new PlacedFeature(
            lookup.getOrThrow(cfKey),
            List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(minY),
                    VerticalAnchor.absolute(maxY)
                )
            )
        ));
    }
}