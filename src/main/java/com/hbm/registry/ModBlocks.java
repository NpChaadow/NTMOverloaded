package com.hbm.registry;

import com.hbm.HBMMod;
import com.hbm.block.CableBlock;
import com.hbm.block.generic.HBMFallingBlock;
import com.hbm.block.generic.HBMGlassBlock;
import com.hbm.block.generic.HBMGlassPaneBlock;
import com.hbm.block.generic.HBMLightBlock;
import com.hbm.block.generic.HBMNoDropBlock;
import com.hbm.block.generic.HBMOreBlock;
import com.hbm.block.generic.HBMRotatablePillarBlock;
import com.hbm.block.generic.HBMSpeedBlock;
import com.hbm.block.machine.HBMStubMachineBlock;
import com.hbm.block.rbmk.RBMKColumnBlock;
import com.hbm.fluids.ModFluids;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * HBM's Nuclear Tech Mod - Block Registry (1.20.1 Forge Port)
 *
 * This file covers all SIMPLE blocks (no BlockEntity / TileEntity).
 * Machine blocks with BlockEntities will be added in a separate pass
 * once their BE counterparts are ported.
 *
 * 1.7.10 -> 1.20.1 property mapping:
 *   setHardness(h) / setResistance(r) -> .strength(h, r)
 *   setBlockUnbreakable()             -> .strength(-1f, 3_600_000f)
 *   Material.rock                     -> MapColor.STONE + requiresCorrectToolForDrops() + SoundType.STONE
 *   Material.iron                     -> MapColor.METAL + requiresCorrectToolForDrops() + SoundType.METAL
 *   Block.soundTypeMetal              -> SoundType.METAL
 *   Block.soundTypeGravel             -> SoundType.GRAVEL
 *   Block.soundTypeSand               -> SoundType.SAND
 *   Block.soundTypeWood               -> SoundType.WOOD
 *   setLightLevel(1F)                 -> .lightLevel(s -> 15)
 */
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HBMMod.MODID);

    // =========================================================================
    // Registry helpers
    // =========================================================================

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> supplier) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    /** Register a block with no pickup item (internal/structural blocks). */
    private static <T extends Block> RegistryObject<T> registerBlockNoItem(String name, Supplier<T> supplier) {
        return BLOCKS.register(name, supplier);
    }

    // Shorthand property builders matching original HBM material groups
    private static BlockBehaviour.Properties rock(float hardness, float resistance) {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE).strength(hardness, resistance);
                //.requiresCorrectToolForDrops().sound(SoundType.STONE);
    }

    private static BlockBehaviour.Properties metal(float hardness, float resistance) {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL).strength(hardness, resistance)
                .requiresCorrectToolForDrops().sound(SoundType.METAL);
    }

    private static BlockBehaviour.Properties metalC(MapColor color, float hardness, float resistance) {
        return BlockBehaviour.Properties.of()
                .mapColor(color).strength(hardness, resistance)
                .requiresCorrectToolForDrops().sound(SoundType.METAL);
    }

    // =========================================================================
    // DEV / TEST (keep from existing port)
    // =========================================================================

    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(3f, 6f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MACHINE_CASING = registerBlock("machine_casing",
            () -> new Block(metal(5f, 10f)));



    // =========================================================================
    // NETWORK - cables / pipes (kept from existing port)
    // =========================================================================

    public static final RegistryObject<CableBlock> CABLE = registerBlock("cable",
            () -> new CableBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f).requiresCorrectToolForDrops()
                    .noOcclusion().isViewBlocking((s, l, p) -> false).isSuffocating((s, l, p) -> false)));

    public static final RegistryObject<CableBlock> PIPE = registerBlockNoItem("pipe",
            () -> new CableBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f).requiresCorrectToolForDrops()
                    .noOcclusion().isViewBlocking((s, l, p) -> false).isSuffocating((s, l, p) -> false)));

    // =========================================================================
    // ORES - overworld
    // =========================================================================

    public static final RegistryObject<HBMOreBlock> ORE_URANIUM = registerBlock("ore_uranium",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(1, 4)));
    public static final RegistryObject<HBMOreBlock> ORE_URANIUM_SCORCHED = registerBlock("ore_uranium_scorched",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(1, 4)));
    public static final RegistryObject<HBMOreBlock> ORE_TITANIUM = registerBlock("ore_titanium",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_SULFUR = registerBlock("ore_sulfur",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(2, 5)));
    public static final RegistryObject<HBMOreBlock> ORE_THORIUM = registerBlock("ore_thorium",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(1, 3)));
    public static final RegistryObject<HBMOreBlock> ORE_NITER = registerBlock("ore_niter",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(2, 5)));
    public static final RegistryObject<HBMOreBlock> ORE_COPPER = registerBlock("ore_copper",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_TUNGSTEN = registerBlock("ore_tungsten",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_ALUMINIUM = registerBlock("ore_aluminium",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_FLUORITE = registerBlock("ore_fluorite",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(2, 5)));
    public static final RegistryObject<HBMOreBlock> ORE_LEAD = registerBlock("ore_lead",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_SCHRABIDIUM = registerBlock("ore_schrabidium",
            () -> new HBMOreBlock(rock(15f, 600f), UniformInt.of(3, 7)));
    public static final RegistryObject<HBMOreBlock> ORE_BERYLLIUM = registerBlock("ore_beryllium",
            () -> new HBMOreBlock(rock(5f, 15f)));
    public static final RegistryObject<HBMOreBlock> ORE_AUSTRALIUM = registerBlock("ore_australium",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(2, 5)));
    public static final RegistryObject<HBMOreBlock> ORE_RARE = registerBlock("ore_rare",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_COBALT = registerBlock("ore_cobalt",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_CINNEBAR = registerBlock("ore_cinnebar",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(1, 3)));
    public static final RegistryObject<HBMOreBlock> ORE_COLTAN = registerBlock("ore_coltan",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_ALEXANDRITE = registerBlock("ore_alexandrite",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(2, 5)));
    public static final RegistryObject<HBMOreBlock> ORE_TIKITE = registerBlock("ore_tikite",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_LIGNITE = registerBlock("ore_lignite",
            () -> new HBMOreBlock(rock(5f, 15f)));
    public static final RegistryObject<HBMOreBlock> ORE_ASBESTOS = registerBlock("ore_asbestos",
            () -> new HBMOreBlock(rock(5f, 15f)));

    // Bedrock-level ores
    public static final RegistryObject<Block> ORE_BEDROCK = registerBlock("ore_bedrock",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .strength(-1f, 3_600_000f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORE_BEDROCK_OIL = registerBlock("ore_bedrock_oil",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .strength(-1f, 1_000_000f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORE_VOLCANO = registerBlock("ore_volcano",
            () -> new Block(rock(5f, 10f)));

    // Oil pockets
    public static final RegistryObject<Block> ORE_OIL = registerBlock("ore_oil",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> ORE_OIL_EMPTY = registerBlock("ore_oil_empty",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<HBMFallingBlock> ORE_OIL_SAND = registerBlock("ore_oil_sand",
            () -> new HBMFallingBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND).strength(0.5f, 1f).sound(SoundType.SAND)));

    // =========================================================================
    // ORES - nether
    // =========================================================================

    public static final RegistryObject<HBMOreBlock> ORE_NETHER_COAL = registerBlock("ore_nether_coal",
            () -> new HBMOreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS)));
    public static final RegistryObject<HBMOreBlock> ORE_NETHER_SMOLDERING = registerBlock("ore_nether_smoldering",
            () -> new HBMOreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS)));
    public static final RegistryObject<HBMOreBlock> ORE_NETHER_URANIUM = registerBlock("ore_nether_uranium",
            () -> new HBMOreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS),
                    UniformInt.of(1, 4)));
    public static final RegistryObject<HBMOreBlock> ORE_NETHER_URANIUM_SCORCHED = registerBlock("ore_nether_uranium_scorched",
            () -> new HBMOreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS),
                    UniformInt.of(1, 4)));
    public static final RegistryObject<HBMOreBlock> ORE_NETHER_PLUTONIUM = registerBlock("ore_nether_plutonium",
            () -> new HBMOreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS),
                    UniformInt.of(1, 3)));
    public static final RegistryObject<HBMOreBlock> ORE_NETHER_TUNGSTEN = registerBlock("ore_nether_tungsten",
            () -> new HBMOreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS)));
    public static final RegistryObject<HBMOreBlock> ORE_NETHER_SULFUR = registerBlock("ore_nether_sulfur",
            () -> new HBMOreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS),
                    UniformInt.of(2, 5)));
    public static final RegistryObject<HBMOreBlock> ORE_NETHER_FIRE = registerBlock("ore_nether_fire",
            () -> new HBMOreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS)));
    public static final RegistryObject<HBMOreBlock> ORE_NETHER_COBALT = registerBlock("ore_nether_cobalt",
            () -> new HBMOreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS)));
    public static final RegistryObject<HBMOreBlock> ORE_NETHER_SCHRABIDIUM = registerBlock("ore_nether_schrabidium",
            () -> new HBMOreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(15f, 600f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS),
                    UniformInt.of(3, 7)));

    // =========================================================================
    // ORES - gneiss biome
    // =========================================================================

    public static final RegistryObject<Block> STONE_GNEISS = registerBlock("stone_gneiss",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_GNEISS_IRON = registerBlock("ore_gneiss_iron",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_GNEISS_GOLD = registerBlock("ore_gneiss_gold",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(1, 5)));
    public static final RegistryObject<HBMOreBlock> ORE_GNEISS_URANIUM = registerBlock("ore_gneiss_uranium",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(1, 4)));
    public static final RegistryObject<HBMOreBlock> ORE_GNEISS_URANIUM_SCORCHED = registerBlock("ore_gneiss_uranium_scorched",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(1, 4)));
    public static final RegistryObject<HBMOreBlock> ORE_GNEISS_COPPER = registerBlock("ore_gneiss_copper",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_GNEISS_ASBESTOS = registerBlock("ore_gneiss_asbestos",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_GNEISS_LITHIUM = registerBlock("ore_gneiss_lithium",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_GNEISS_SCHRABIDIUM = registerBlock("ore_gneiss_schrabidium",
            () -> new HBMOreBlock(rock(15f, 600f), UniformInt.of(3, 7)));
    public static final RegistryObject<HBMOreBlock> ORE_GNEISS_RARE = registerBlock("ore_gneiss_rare",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_GNEISS_GAS = registerBlock("ore_gneiss_gas",
            () -> new HBMOreBlock(rock(5f, 10f)));

    // Gneiss decorative
    public static final RegistryObject<Block> GNEISS_BRICK = registerBlock("gneiss_brick",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> GNEISS_TILE = registerBlock("gneiss_tile",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> GNEISS_CHISELED = registerBlock("gneiss_chiseled",
            () -> new Block(rock(5f, 10f)));

    // =========================================================================
    // ORES - depth layer
    // =========================================================================

    public static final RegistryObject<Block> STONE_DEPTH = registerBlock("stone_depth",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_DEPTH_CINNEBAR = registerBlock("ore_depth_cinnebar",
            () -> new HBMOreBlock(rock(5f, 10f), UniformInt.of(1, 3)));
    public static final RegistryObject<HBMOreBlock> ORE_DEPTH_ZIRCONIUM = registerBlock("ore_depth_zirconium",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_DEPTH_BORAX = registerBlock("ore_depth_borax",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> CLUSTER_DEPTH_IRON = registerBlock("cluster_depth_iron",
            () -> new HBMOreBlock(rock(5f, 15f)));
    public static final RegistryObject<HBMOreBlock> CLUSTER_DEPTH_TITANIUM = registerBlock("cluster_depth_titanium",
            () -> new HBMOreBlock(rock(5f, 15f)));
    public static final RegistryObject<HBMOreBlock> CLUSTER_DEPTH_TUNGSTEN = registerBlock("cluster_depth_tungsten",
            () -> new HBMOreBlock(rock(5f, 15f)));
    public static final RegistryObject<Block> STONE_DEPTH_NETHER = registerBlock("stone_depth_nether",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS)));
    public static final RegistryObject<HBMOreBlock> ORE_DEPTH_NETHER_NEODYMIUM = registerBlock("ore_depth_nether_neodymium",
            () -> new HBMOreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS)));

    // Surface ore clusters
    public static final RegistryObject<HBMOreBlock> CLUSTER_IRON = registerBlock("cluster_iron",
            () -> new HBMOreBlock(rock(5f, 15f)));
    public static final RegistryObject<HBMOreBlock> CLUSTER_TITANIUM = registerBlock("cluster_titanium",
            () -> new HBMOreBlock(rock(5f, 15f)));
    public static final RegistryObject<HBMOreBlock> CLUSTER_ALUMINIUM = registerBlock("cluster_aluminium",
            () -> new HBMOreBlock(rock(5f, 15f)));
    public static final RegistryObject<HBMOreBlock> CLUSTER_COPPER = registerBlock("cluster_copper",
            () -> new HBMOreBlock(rock(5f, 15f)));

    // Meteor ore
    public static final RegistryObject<HBMOreBlock> ORE_METEOR = registerBlock("ore_meteor",
            () -> new HBMOreBlock(rock(10f, 20f), UniformInt.of(2, 6)));

    // =========================================================================
    // BASALT
    // =========================================================================

    public static final RegistryObject<Block> BASALT = registerBlock("basalt",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<HBMOreBlock> ORE_BASALT = registerBlock("ore_basalt",
            () -> new HBMOreBlock(rock(5f, 10f)));
    public static final RegistryObject<Block> BASALT_SMOOTH = registerBlock("basalt_smooth",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BASALT_BRICK = registerBlock("basalt_brick",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BASALT_POLISHED = registerBlock("basalt_polished",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BASALT_TILES = registerBlock("basalt_tiles",
            () -> new Block(rock(5f, 10f)));

    // =========================================================================
    // DEPTH BRICKS
    // =========================================================================

    public static final RegistryObject<Block> DEPTH_BRICK = registerBlock("depth_brick",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> DEPTH_TILES = registerBlock("depth_tiles",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> DEPTH_NETHER_BRICK = registerBlock("depth_nether_brick",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS)));
    public static final RegistryObject<Block> DEPTH_NETHER_TILES = registerBlock("depth_nether_tiles",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.NETHER_BRICKS)));
    public static final RegistryObject<Block> DEPTH_DNT = registerBlock("depth_dnt",
            () -> new Block(rock(5f, 10f)));

    // =========================================================================
    // METAL / MATERIAL STORAGE BLOCKS
    // =========================================================================

    // Uranium & fissile materials
    public static final RegistryObject<Block> BLOCK_URANIUM = registerBlock("block_uranium",
            () -> new Block(metalC(MapColor.COLOR_GREEN, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_U233 = registerBlock("block_u233",
            () -> new Block(metalC(MapColor.COLOR_LIGHT_GREEN, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_U235 = registerBlock("block_u235",
            () -> new Block(metalC(MapColor.COLOR_GREEN, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_U238 = registerBlock("block_u238",
            () -> new Block(metalC(MapColor.COLOR_GREEN, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_URANIUM_FUEL = registerBlock("block_uranium_fuel",
            () -> new Block(metalC(MapColor.COLOR_YELLOW, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_THORIUM = registerBlock("block_thorium",
            () -> new Block(metalC(MapColor.COLOR_LIGHT_GRAY, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_THORIUM_FUEL = registerBlock("block_thorium_fuel",
            () -> new Block(metalC(MapColor.COLOR_LIGHT_GRAY, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_NEPTUNIUM = registerBlock("block_neptunium",
            () -> new Block(metal(5f, 50f)));
    public static final RegistryObject<Block> BLOCK_POLONIUM = registerBlock("block_polonium",
            () -> new Block(metal(5f, 50f)));
    public static final RegistryObject<Block> BLOCK_MOX_FUEL = registerBlock("block_mox_fuel",
            () -> new Block(metalC(MapColor.COLOR_ORANGE, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_PLUTONIUM = registerBlock("block_plutonium",
            () -> new Block(metalC(MapColor.COLOR_LIGHT_GRAY, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_PU238 = registerBlock("block_pu238",
            () -> new Block(metalC(MapColor.COLOR_LIGHT_GRAY, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_PU239 = registerBlock("block_pu239",
            () -> new Block(metalC(MapColor.COLOR_LIGHT_GRAY, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_PU240 = registerBlock("block_pu240",
            () -> new Block(metalC(MapColor.COLOR_LIGHT_GRAY, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_PU_MIX = registerBlock("block_pu_mix",
            () -> new Block(metalC(MapColor.COLOR_LIGHT_GRAY, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_PLUTONIUM_FUEL = registerBlock("block_plutonium_fuel",
            () -> new Block(metalC(MapColor.COLOR_LIGHT_GRAY, 5f, 50f)));

    // Common industrial metals
    public static final RegistryObject<Block> BLOCK_TITANIUM = registerBlock("block_titanium",
            () -> new Block(metal(5f, 50f)));
    public static final RegistryObject<Block> BLOCK_SULFUR = registerBlock("block_sulfur",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_NITER = registerBlock("block_niter",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_COPPER = registerBlock("block_copper",
            () -> new Block(metalC(MapColor.COLOR_ORANGE, 5f, 20f)));
    public static final RegistryObject<Block> BLOCK_RED_COPPER = registerBlock("block_red_copper",
            () -> new Block(metalC(MapColor.COLOR_RED, 5f, 25f)));
    public static final RegistryObject<Block> BLOCK_TUNGSTEN = registerBlock("block_tungsten",
            () -> new Block(metal(5f, 20f)));
    public static final RegistryObject<Block> BLOCK_ALUMINIUM = registerBlock("block_aluminium",
            () -> new Block(metal(5f, 20f)));
    public static final RegistryObject<Block> BLOCK_FLUORITE = registerBlock("block_fluorite",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_STEEL = registerBlock("block_steel",
            () -> new Block(metal(5f, 50f)));
    public static final RegistryObject<Block> BLOCK_TCALLOY = registerBlock("block_tcalloy",
            () -> new Block(metal(5f, 70f)));
    public static final RegistryObject<Block> BLOCK_CDALLOY = registerBlock("block_cdalloy",
            () -> new Block(metal(5f, 70f)));
    public static final RegistryObject<Block> BLOCK_LEAD = registerBlock("block_lead",
            () -> new Block(metalC(MapColor.COLOR_BLUE, 5f, 50f)));
    public static final RegistryObject<Block> BLOCK_BISMUTH = registerBlock("block_bismuth",
            () -> new Block(metal(5f, 30f)));
    public static final RegistryObject<Block> BLOCK_CADMIUM = registerBlock("block_cadmium",
            () -> new Block(metal(5f, 30f)));
    public static final RegistryObject<Block> BLOCK_COLTAN = registerBlock("block_coltan",
            () -> new Block(metal(5f, 30f)));
    public static final RegistryObject<Block> BLOCK_TANTALIUM = registerBlock("block_tantalium",
            () -> new Block(metal(5f, 40f)));
    public static final RegistryObject<Block> BLOCK_NIOBIUM = registerBlock("block_niobium",
            () -> new Block(metal(5f, 40f)));
    public static final RegistryObject<Block> BLOCK_BERYLLIUM = registerBlock("block_beryllium",
            () -> new Block(metal(5f, 30f)));
    public static final RegistryObject<Block> BLOCK_COBALT = registerBlock("block_cobalt",
            () -> new Block(metalC(MapColor.COLOR_BLUE, 5f, 30f)));
    public static final RegistryObject<Block> BLOCK_LITHIUM = registerBlock("block_lithium",
            () -> new Block(metalC(MapColor.COLOR_PINK, 5f, 20f)));
    public static final RegistryObject<Block> BLOCK_ZIRCONIUM = registerBlock("block_zirconium",
            () -> new Block(metal(5f, 30f)));
    public static final RegistryObject<Block> BLOCK_ADVANCED_ALLOY = registerBlock("block_advanced_alloy",
            () -> new Block(metal(5f, 60f)));
    public static final RegistryObject<Block> BLOCK_MAGNETIZED_TUNGSTEN = registerBlock("block_magnetized_tungsten",
            () -> new Block(metal(5f, 40f)));
    public static final RegistryObject<Block> BLOCK_COMBINE_STEEL = registerBlock("block_combine_steel",
            () -> new Block(metal(5f, 80f)));
    public static final RegistryObject<Block> BLOCK_DESH = registerBlock("block_desh",
            () -> new Block(metal(5f, 60f)));
    public static final RegistryObject<Block> BLOCK_DURA_STEEL = registerBlock("block_dura_steel",
            () -> new Block(metal(5f, 90f)));
    public static final RegistryObject<Block> BLOCK_STARMETAL = registerBlock("block_starmetal",
            () -> new Block(metal(5f, 100f)));
    public static final RegistryObject<Block> BLOCK_POLYMER = registerBlock("block_polymer",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .strength(5f, 30f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_BAKELITE = registerBlock("block_bakelite",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN)
                    .strength(5f, 20f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_RUBBER = registerBlock("block_rubber",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.WOOL)));
    public static final RegistryObject<Block> BLOCK_YELLOWCAKE = registerBlock("block_yellowcake",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW)
                    .strength(5f, 20f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_INSULATOR = registerBlock("block_insulator",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_FIBERGLASS = registerBlock("block_fiberglass",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ)
                    .strength(5f, 15f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_ASBESTOS = registerBlock("block_asbestos",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .strength(5f, 15f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_WHITE_PHOSPHORUS = registerBlock("block_white_phosphorus",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_RED_PHOSPHORUS = registerBlock("block_red_phosphorus",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    // Schrabidium family (exotic endgame metals)
    public static final RegistryObject<Block> BLOCK_SCHRARANIUM = registerBlock("block_schraranium",
            () -> new Block(metalC(MapColor.COLOR_CYAN, 5f, 150f)));
    public static final RegistryObject<Block> BLOCK_SCHRABIDIUM = registerBlock("block_schrabidium",
            () -> new Block(metalC(MapColor.COLOR_CYAN, 5f, 200f)));
    public static final RegistryObject<Block> BLOCK_SCHRABIDATE = registerBlock("block_schrabidate",
            () -> new Block(metalC(MapColor.COLOR_CYAN, 5f, 200f)));
    public static final RegistryObject<Block> BLOCK_SOLINIUM = registerBlock("block_solinium",
            () -> new Block(metalC(MapColor.COLOR_CYAN, 5f, 300f)));
    public static final RegistryObject<Block> BLOCK_SCHRABIDIUM_FUEL = registerBlock("block_schrabidium_fuel",
            () -> new Block(metalC(MapColor.COLOR_CYAN, 5f, 200f)));
    public static final RegistryObject<Block> BLOCK_EUPHEMIUM = registerBlock("block_euphemium",
            () -> new Block(metalC(MapColor.COLOR_CYAN, 5f, 400f)));
    public static final RegistryObject<Block> BLOCK_SCHRABIDIUM_CLUSTER = registerBlock("block_schrabidium_cluster",
            () -> new Block(metalC(MapColor.COLOR_CYAN, 5f, 200f)));
    public static final RegistryObject<Block> BLOCK_EUPHEMIUM_CLUSTER = registerBlock("block_euphemium_cluster",
            () -> new Block(metalC(MapColor.COLOR_CYAN, 5f, 400f)));
    public static final RegistryObject<Block> BLOCK_DINEUTRONIUM = registerBlock("block_dineutronium",
            () -> new Block(metalC(MapColor.COLOR_GRAY, 5f, 800f)));
    public static final RegistryObject<Block> BLOCK_AUSTRALIUM = registerBlock("block_australium",
            () -> new Block(metalC(MapColor.GOLD, 5f, 100f)));
    public static final RegistryObject<Block> BLOCK_CAP = registerBlock("block_cap",
            () -> new Block(metal(5f, 20f)));

    // Waste & nuclear byproducts
    public static final RegistryObject<Block> BLOCK_TRINITITE = registerBlock("block_trinitite",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_WASTE = registerBlock("block_waste",
            () -> new Block(metalC(MapColor.COLOR_GREEN, 5f, 20f)));
    public static final RegistryObject<Block> BLOCK_WASTE_PAINTED = registerBlock("block_waste_painted",
            () -> new Block(metalC(MapColor.COLOR_YELLOW, 5f, 20f)));
    public static final RegistryObject<Block> BLOCK_WASTE_VITRIFIED = registerBlock("block_waste_vitrified",
            () -> new Block(metalC(MapColor.COLOR_GRAY, 5f, 20f)));
    public static final RegistryObject<Block> ANCIENT_SCRAP = registerBlock("ancient_scrap",
            () -> new Block(metal(5f, 40f)));
    public static final RegistryObject<Block> BLOCK_CORIUM = registerBlock("block_corium",
            () -> new Block(metalC(MapColor.TERRACOTTA_ORANGE, 5f, 30f)));
    public static final RegistryObject<Block> BLOCK_CORIUM_COBBLE = registerBlock("block_corium_cobble",
            () -> new Block(metalC(MapColor.TERRACOTTA_ORANGE, 5f, 20f)));
    public static final RegistryObject<Block> BLOCK_SCRAP = registerBlock("block_scrap",
            () -> new Block(metal(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_ELECTRICAL_SCRAP = registerBlock("block_electrical_scrap",
            () -> new Block(metal(5f, 10f)));

    // Carbon & nuclear moderator materials
    public static final RegistryObject<Block> BLOCK_FALLOUT = registerBlock("block_fallout",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_FOAM = registerBlock("block_foam",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .strength(5f, 5f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_COKE = registerBlock("block_coke",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_GRAPHITE = registerBlock("block_graphite",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_GRAPHITE_DRILLED = registerBlock("block_graphite_drilled",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_GRAPHITE_FUEL = registerBlock("block_graphite_fuel",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_GRAPHITE_PLUTONIUM = registerBlock("block_graphite_plutonium",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_GRAPHITE_ROD = registerBlock("block_graphite_rod",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_GRAPHITE_SOURCE = registerBlock("block_graphite_source",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_GRAPHITE_LITHIUM = registerBlock("block_graphite_lithium",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_GRAPHITE_TRITIUM = registerBlock("block_graphite_tritium",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_GRAPHITE_DETECTOR = registerBlock("block_graphite_detector",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_BORON = registerBlock("block_boron",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_LANTHANIUM = registerBlock("block_lanthanium",
            () -> new Block(metal(5f, 30f)));
    public static final RegistryObject<Block> BLOCK_RA226 = registerBlock("block_ra226",
            () -> new Block(metal(5f, 40f)));
    public static final RegistryObject<Block> BLOCK_ACTINIUM = registerBlock("block_actinium",
            () -> new Block(metal(5f, 40f)));
    public static final RegistryObject<Block> BLOCK_TRITIUM = registerBlock("block_tritium",
            () -> new Block(metal(5f, 30f)));
    public static final RegistryObject<Block> BLOCK_SEMTEX = registerBlock("block_semtex",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)
                    .strength(5f, 5f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_C4 = registerBlock("block_c4",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .strength(5f, 5f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> BLOCK_SMORE = registerBlock("block_smore",
            () -> new Block(metal(5f, 10f)));
    public static final RegistryObject<Block> BLOCK_SLAG = registerBlock("block_slag",
            () -> new Block(rock(5f, 10f)));

    // =========================================================================
    // DECORATIVE METAL BLOCKS (deco_*)
    // =========================================================================

    public static final RegistryObject<Block> DECO_TITANIUM = registerBlock("deco_titanium",
            () -> new Block(metal(5f, 50f)));
    public static final RegistryObject<Block> DECO_RED_COPPER = registerBlock("deco_red_copper",
            () -> new Block(metalC(MapColor.COLOR_RED, 5f, 40f)));
    public static final RegistryObject<Block> DECO_TUNGSTEN = registerBlock("deco_tungsten",
            () -> new Block(metal(5f, 50f)));
    public static final RegistryObject<Block> DECO_ALUMINIUM = registerBlock("deco_aluminium",
            () -> new Block(metal(5f, 40f)));
    public static final RegistryObject<Block> DECO_STEEL = registerBlock("deco_steel",
            () -> new Block(metal(5f, 60f)));
    public static final RegistryObject<Block> DECO_RUSTY_STEEL = registerBlock("deco_rusty_steel",
            () -> new Block(metal(5f, 50f)));
    public static final RegistryObject<Block> DECO_LEAD = registerBlock("deco_lead",
            () -> new Block(metalC(MapColor.COLOR_BLUE, 5f, 50f)));
    public static final RegistryObject<Block> DECO_BERYLLIUM = registerBlock("deco_beryllium",
            () -> new Block(metal(5f, 40f)));
    public static final RegistryObject<Block> DECO_ASBESTOS = registerBlock("deco_asbestos",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .strength(5f, 30f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> DECO_RBMK = registerBlock("deco_rbmk",
            () -> new Block(metal(5f, 80f)));
    public static final RegistryObject<Block> DECO_RBMK_SMOOTH = registerBlock("deco_rbmk_smooth",
            () -> new Block(metal(5f, 100f)));

    // =========================================================================
    // CONCRETE & BRICKS
    // =========================================================================

    public static final RegistryObject<Block> REINFORCED_STONE = registerBlock("reinforced_stone",
            () -> new Block(rock(15f, 100f)));
    public static final RegistryObject<Block> CONCRETE_SMOOTH = registerBlock("concrete_smooth",
            () -> new Block(rock(15f, 140f)));
    public static final RegistryObject<Block> CONCRETE = registerBlock("concrete",
            () -> new Block(rock(15f, 140f)));
    public static final RegistryObject<Block> CONCRETE_ASBESTOS = registerBlock("concrete_asbestos",
            () -> new Block(rock(15f, 150f)));
    public static final RegistryObject<Block> CONCRETE_REBAR = registerBlock("concrete_rebar",
            () -> new Block(rock(50f, 240f)));
    public static final RegistryObject<HBMFallingBlock> CONCRETE_SUPER_BROKEN = registerBlock("concrete_super_broken",
            () -> new HBMFallingBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(10f, 20f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<HBMRotatablePillarBlock> CONCRETE_PILLAR = registerBlock("concrete_pillar",
            () -> new HBMRotatablePillarBlock(rock(15f, 180f)));
    public static final RegistryObject<Block> BRICK_CONCRETE = registerBlock("brick_concrete",
            () -> new Block(rock(15f, 160f)));
    public static final RegistryObject<Block> BRICK_CONCRETE_MOSSY = registerBlock("brick_concrete_mossy",
            () -> new Block(rock(15f, 160f)));
    public static final RegistryObject<Block> DUCRETE_SMOOTH = registerBlock("ducrete_smooth",
            () -> new Block(rock(10f, 120f)));
    public static final RegistryObject<Block> DUCRETE = registerBlock("ducrete",
            () -> new Block(rock(10f, 120f)));

    // =========================================================================
    // REINFORCED BLOCKS
    // =========================================================================

    public static final RegistryObject<Block> REINFORCED_BRICK = registerBlock("reinforced_brick",
            () -> new Block(rock(15f, 120f)));
    public static final RegistryObject<Block> REINFORCED_DUCRETE = registerBlock("reinforced_ducrete",
            () -> new Block(rock(15f, 150f)));
    public static final RegistryObject<HBMGlassBlock> REINFORCED_GLASS = registerBlock("reinforced_glass",
            () -> new HBMGlassBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NONE).strength(15f, 120f)
                    .noOcclusion().sound(SoundType.GLASS)));
    public static final RegistryObject<HBMGlassPaneBlock> REINFORCED_GLASS_PANE = registerBlock("reinforced_glass_pane",
            () -> new HBMGlassPaneBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NONE).strength(15f, 120f)
                    .noOcclusion().sound(SoundType.GLASS)));
    public static final RegistryObject<HBMLightBlock> REINFORCED_LIGHT = registerBlock("reinforced_light",
            () -> new HBMLightBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.QUARTZ).strength(15f, 120f)
                    .requiresCorrectToolForDrops().lightLevel(s -> 15).sound(SoundType.STONE)));
    public static final RegistryObject<Block> REINFORCED_SAND = registerBlock("reinforced_sand",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND)
                    .strength(15f, 120f).requiresCorrectToolForDrops().sound(SoundType.SAND)));
    public static final RegistryObject<Block> REINFORCED_LAMP_OFF = registerBlock("reinforced_lamp_off",
            () -> new Block(metal(15f, 120f)));
    public static final RegistryObject<HBMLightBlock> REINFORCED_LAMP_ON = registerBlock("reinforced_lamp_on",
            () -> new HBMLightBlock(metal(15f, 120f).lightLevel(s -> 15)));
    public static final RegistryObject<HBMGlassBlock> REINFORCED_LAMINATE = registerBlock("reinforced_laminate",
            () -> new HBMGlassBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NONE).strength(15f, 120f)
                    .noOcclusion().sound(SoundType.GLASS)));
    public static final RegistryObject<HBMGlassPaneBlock> REINFORCED_LAMINATE_PANE = registerBlock("reinforced_laminate_pane",
            () -> new HBMGlassPaneBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NONE).strength(15f, 120f)
                    .noOcclusion().sound(SoundType.GLASS)));

    // =========================================================================
    // BARRIERS & STRUCTURES
    // =========================================================================

    public static final RegistryObject<Block> SANDBAGS = registerBlock("sandbags",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.SAND)));
    public static final RegistryObject<Block> WOOD_BARRIER = registerBlock("wood_barrier",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WOOD_STRUCTURE = registerBlock("wood_structure",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
                    .strength(5f, 10f).requiresCorrectToolForDrops().sound(SoundType.WOOD)));

    // =========================================================================
    // GRAVEL VARIANTS
    // =========================================================================

    public static final RegistryObject<HBMFallingBlock> GRAVEL_OBSIDIAN = registerBlock("gravel_obsidian",
            () -> new HBMFallingBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK).strength(5f, 240f)
                    .requiresCorrectToolForDrops().sound(SoundType.GRAVEL)));
    public static final RegistryObject<HBMFallingBlock> GRAVEL_DIAMOND = registerBlock("gravel_diamond",
            () -> new HBMFallingBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIAMOND).strength(0.6f)
                    .requiresCorrectToolForDrops().sound(SoundType.GRAVEL)));

    // =========================================================================
    // ASPHALT (speed-boosting ground blocks)
    // =========================================================================

    public static final RegistryObject<HBMSpeedBlock> ASPHALT = registerBlock("asphalt",
            () -> new HBMSpeedBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK).strength(15f, 120f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE), 1.5));
    public static final RegistryObject<HBMSpeedBlock> ASPHALT_LIGHT = registerBlock("asphalt_light",
            () -> new HBMSpeedBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK).strength(15f, 120f)
                    .requiresCorrectToolForDrops().lightLevel(s -> 15).sound(SoundType.STONE), 1.5));

    // =========================================================================
    // LAMPS (simple on/off pairs - no BlockEntity)
    // =========================================================================

    public static final RegistryObject<Block> LAMP_TRITIUM_GREEN_OFF = registerBlock("lamp_tritium_green_off",
            () -> new Block(metal(5f, 10f)));
    public static final RegistryObject<HBMLightBlock> LAMP_TRITIUM_GREEN_ON = registerBlock("lamp_tritium_green_on",
            () -> new HBMLightBlock(metal(5f, 10f).lightLevel(s -> 12)));
    public static final RegistryObject<Block> LAMP_TRITIUM_BLUE_OFF = registerBlock("lamp_tritium_blue_off",
            () -> new Block(metal(5f, 10f)));
    public static final RegistryObject<HBMLightBlock> LAMP_TRITIUM_BLUE_ON = registerBlock("lamp_tritium_blue_on",
            () -> new HBMLightBlock(metal(5f, 10f).lightLevel(s -> 12)));
    public static final RegistryObject<HBMLightBlock> LAMP_DEMON = registerBlock("lamp_demon",
            () -> new HBMLightBlock(metal(5f, 10f).lightLevel(s -> 15)));

    // Beam / internal blocks - no item, no drops
    public static final RegistryObject<HBMNoDropBlock> SPOTLIGHT_BEAM = registerBlockNoItem("spotlight_beam",
            () -> new HBMNoDropBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NONE).strength(-1f, 3_600_000f)
                    .noOcclusion().noLootTable()));
    public static final RegistryObject<HBMNoDropBlock> FLOODLIGHT_BEAM = registerBlockNoItem("floodlight_beam",
            () -> new HBMNoDropBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NONE).strength(-1f, 3_600_000f)
                    .noOcclusion().noLootTable()));
    public static final RegistryObject<HBMNoDropBlock> OIL_PIPE = registerBlockNoItem("oil_pipe",
            () -> new HBMNoDropBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK).strength(5f, 10f)
                    .requiresCorrectToolForDrops().noOcclusion()));

    // =========================================================================
    // MISC STONE TYPES
    // =========================================================================

    public static final RegistryObject<Block> STONE_KEYHOLE = registerBlock("stone_keyhole",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> STONE_KEYHOLE_META = registerBlock("stone_keyhole_meta",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> STONE_POROUS = registerBlock("stone_porous",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> STONE_RESOURCE = registerBlock("stone_resource",
            () -> new Block(rock(5f, 10f)));
    public static final RegistryObject<Block> STONE_BIOME = registerBlock("stone_biome",
            () -> new Block(rock(5f, 10f)));

    // =========================================================================
    // RBMK REACTOR COLUMNS  (EntityBlock - RBMKColumnBlock)
    // All 21 column types share one block class; each gets its own
    // BlockEntityType in ModBlockEntities.java.
    // =========================================================================

    public static final RegistryObject<RBMKColumnBlock> RBMK_ROD = registerBlock("rbmk_rod",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_ROD::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_ROD_MOD = registerBlock("rbmk_rod_mod",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_ROD_MOD::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_ROD_REASIM = registerBlock("rbmk_rod_reasim",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_ROD_REASIM::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_ROD_REASIM_MOD = registerBlock("rbmk_rod_reasim_mod",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_ROD_REASIM_MOD::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_CONTROL = registerBlock("rbmk_control",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_CONTROL::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_CONTROL_MOD = registerBlock("rbmk_control_mod",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_CONTROL_MOD::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_CONTROL_AUTO = registerBlock("rbmk_control_auto",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_CONTROL_AUTO::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_BLANK = registerBlock("rbmk_blank",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_BLANK::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_BOILER = registerBlock("rbmk_boiler",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_BOILER::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_REFLECTOR = registerBlock("rbmk_reflector",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_REFLECTOR::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_ABSORBER = registerBlock("rbmk_absorber",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_ABSORBER::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_MODERATOR = registerBlock("rbmk_moderator",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_MODERATOR::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_OUTGASSER = registerBlock("rbmk_outgasser",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_OUTGASSER::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_STORAGE = registerBlock("rbmk_storage",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_STORAGE::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_COOLER = registerBlock("rbmk_cooler",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_COOLER::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_HEATER = registerBlock("rbmk_heater",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_HEATER::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_CONSOLE = registerBlock("rbmk_console",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_CONSOLE::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_CRANE_CONSOLE = registerBlock("rbmk_crane_console",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_CRANE_CONSOLE::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_AUTOLOADER = registerBlock("rbmk_autoloader",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_AUTOLOADER::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_LOADER = registerBlock("rbmk_loader",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_LOADER::get));
    public static final RegistryObject<RBMKColumnBlock> RBMK_STEAM_INLET = registerBlock("rbmk_steam_inlet",
            () -> new RBMKColumnBlock(ModBlockEntities.RBMK_STEAM_INLET::get));

    // =========================================================================
    // FURNACES & HEATERS  (EntityBlock stubs)
    // =========================================================================

    public static final RegistryObject<HBMStubMachineBlock> FURNACE_IRON = registerBlock("furnace_iron",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.FURNACE_IRON::get));
    public static final RegistryObject<HBMStubMachineBlock> FURNACE_STEEL = registerBlock("furnace_steel",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.FURNACE_STEEL::get));
    public static final RegistryObject<HBMStubMachineBlock> HEATER_FIREBOX = registerBlock("heater_firebox",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.HEATER_FIREBOX::get));
    public static final RegistryObject<HBMStubMachineBlock> HEATER_ELECTRIC = registerBlock("heater_electric",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.HEATER_ELECTRIC::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_FURNACE_BRICK_OFF = registerBlock("machine_furnace_brick_off",
            () -> new HBMStubMachineBlock(rock(5f, 10f), ModBlockEntities.MACHINE_FURNACE_BRICK_OFF::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_FURNACE_BRICK_ON = registerBlock("machine_furnace_brick_on",
            () -> new HBMStubMachineBlock(rock(5f, 10f).lightLevel(s -> 12), ModBlockEntities.MACHINE_FURNACE_BRICK_ON::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_DIFURNACE_OFF = registerBlock("machine_difurnace_off",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_DIFURNACE_OFF::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_DIFURNACE_ON = registerBlock("machine_difurnace_on",
            () -> new HBMStubMachineBlock(metal(5f, 10f).lightLevel(s -> 12), ModBlockEntities.MACHINE_DIFURNACE_ON::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ELECTRIC_FURNACE_OFF = registerBlock("machine_electric_furnace_off",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ELECTRIC_FURNACE_OFF::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ELECTRIC_FURNACE_ON = registerBlock("machine_electric_furnace_on",
            () -> new HBMStubMachineBlock(metal(5f, 10f).lightLevel(s -> 12), ModBlockEntities.MACHINE_ELECTRIC_FURNACE_ON::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_MICROWAVE = registerBlock("machine_microwave",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_MICROWAVE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ARC_FURNACE = registerBlock("machine_arc_furnace",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ARC_FURNACE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ROTARY_FURNACE = registerBlock("machine_rotary_furnace",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ROTARY_FURNACE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_PYROOVEN = registerBlock("machine_pyrooven",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_PYROOVEN::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_WOOD_BURNER = registerBlock("machine_wood_burner",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_WOOD_BURNER::get));

    // =========================================================================
    // STEAM & ENGINES
    // =========================================================================

    public static final RegistryObject<HBMStubMachineBlock> MACHINE_STIRLING_OFF = registerBlock("machine_stirling_off",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_STIRLING_OFF::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_STIRLING_ON = registerBlock("machine_stirling_on",
            () -> new HBMStubMachineBlock(metal(5f, 10f).lightLevel(s -> 8), ModBlockEntities.MACHINE_STIRLING_ON::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_STIRLING_GENERATOR_OFF = registerBlock("machine_stirling_generator_off",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_STIRLING_GENERATOR_OFF::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_STIRLING_GENERATOR_ON = registerBlock("machine_stirling_generator_on",
            () -> new HBMStubMachineBlock(metal(5f, 10f).lightLevel(s -> 8), ModBlockEntities.MACHINE_STIRLING_GENERATOR_ON::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_BOILER = registerBlock("machine_boiler",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_BOILER::get));
    public static final RegistryObject<HBMStubMachineBlock> PUMP_STEAM = registerBlock("pump_steam",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.PUMP_STEAM::get));
    public static final RegistryObject<HBMStubMachineBlock> PUMP_ELECTRIC = registerBlock("pump_electric",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.PUMP_ELECTRIC::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_STEAM_ENGINE = registerBlock("machine_steam_engine",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_STEAM_ENGINE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_TURBINE = registerBlock("machine_turbine",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_TURBINE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_LARGE_TURBINE = registerBlock("machine_large_turbine",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_LARGE_TURBINE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_INDUSTRIAL_TURBINE = registerBlock("machine_industrial_turbine",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_INDUSTRIAL_TURBINE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CHUNGUS = registerBlock("machine_chungus",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CHUNGUS::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CONDENSER = registerBlock("machine_condenser",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CONDENSER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_TOWER_SMALL = registerBlock("machine_tower_small",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_TOWER_SMALL::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_TOWER_LARGE = registerBlock("machine_tower_large",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_TOWER_LARGE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_TURBOFAN = registerBlock("machine_turbofan",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_TURBOFAN::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_TURBINEGAS = registerBlock("machine_turbinegas",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_TURBINEGAS::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_LPW2 = registerBlock("machine_lpw2",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_LPW2::get));

    // =========================================================================
    // PROCESSING
    // =========================================================================

    public static final RegistryObject<HBMStubMachineBlock> MACHINE_SAWMILL = registerBlock("machine_sawmill",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_SAWMILL::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CRUCIBLE = registerBlock("machine_crucible",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CRUCIBLE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CENTRIFUGE = registerBlock("machine_centrifuge",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CENTRIFUGE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_GASCENT = registerBlock("machine_gascent",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_GASCENT::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_FEL = registerBlock("machine_fel",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_FEL::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_SILEX = registerBlock("machine_silex",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_SILEX::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CRYSTALLIZER = registerBlock("machine_crystallizer",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CRYSTALLIZER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_MIXER = registerBlock("machine_mixer",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_MIXER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_STRAND_CASTER = registerBlock("machine_strand_caster",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_STRAND_CASTER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_SHREDDER = registerBlock("machine_shredder",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_SHREDDER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_AUTOSAW = registerBlock("machine_autosaw",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_AUTOSAW::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_EXCAVATOR = registerBlock("machine_excavator",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_EXCAVATOR::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ORE_SLOPPER = registerBlock("machine_ore_slopper",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ORE_SLOPPER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ANNIHILATOR = registerBlock("machine_annihilator",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ANNIHILATOR::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_MINING_LASER = registerBlock("machine_mining_laser",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_MINING_LASER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ARC_WELDER = registerBlock("machine_arc_welder",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ARC_WELDER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_SOLDERING_STATION = registerBlock("machine_soldering_station",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_SOLDERING_STATION::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_AUTOCRAFTER = registerBlock("machine_autocrafter",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_AUTOCRAFTER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_FUNNEL = registerBlock("machine_funnel",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_FUNNEL::get));

    // =========================================================================
    // CHEMICAL & GAS
    // =========================================================================

    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CHEMICAL_PLANT = registerBlock("machine_chemical_plant",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CHEMICAL_PLANT::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CHEMICAL_FACTORY = registerBlock("machine_chemical_factory",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CHEMICAL_FACTORY::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_PUREX = registerBlock("machine_purex",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_PUREX::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_INTAKE = registerBlock("machine_intake",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_INTAKE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_COMPRESSOR = registerBlock("machine_compressor",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_COMPRESSOR::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_COMPRESSOR_ELECTRIC = registerBlock("machine_compressor_electric",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_COMPRESSOR_ELECTRIC::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ELECTROLYSER = registerBlock("machine_electrolyser",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ELECTROLYSER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_LIQUEFACTOR = registerBlock("machine_liquefactor",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_LIQUEFACTOR::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_SOLIDIFIER = registerBlock("machine_solidifier",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_SOLIDIFIER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_DEUTERIUM_EXTRACTOR = registerBlock("machine_deuterium_extractor",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_DEUTERIUM_EXTRACTOR::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_DEUTERIUM_TOWER = registerBlock("machine_deuterium_tower",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_DEUTERIUM_TOWER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_FLUIDTANK = registerBlock("machine_fluidtank",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_FLUIDTANK::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_BAT9000 = registerBlock("machine_bat9000",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_BAT9000::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ORBUS = registerBlock("machine_orbus",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ORBUS::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_DRAIN = registerBlock("machine_drain",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_DRAIN::get));

    // =========================================================================
    // NUCLEAR / ISOTOPE / RTG
    // =========================================================================

    public static final RegistryObject<HBMStubMachineBlock> MACHINE_UF6_TANK = registerBlock("machine_uf6_tank",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_UF6_TANK::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_PUF6_TANK = registerBlock("machine_puf6_tank",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_PUF6_TANK::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_REACTOR_BREEDING = registerBlock("machine_reactor_breeding",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_REACTOR_BREEDING::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_RTG_GREY = registerBlock("machine_rtg_grey",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_RTG_GREY::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_MINIRTG = registerBlock("machine_minirtg",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_MINIRTG::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_POWERRTG = registerBlock("machine_powerrtg",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_POWERRTG::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_RADIOLYSIS = registerBlock("machine_radiolysis",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_RADIOLYSIS::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_HEPHAESTUS = registerBlock("machine_hephaestus",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_HEPHAESTUS::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_INDUSTRIAL_GENERATOR = registerBlock("machine_industrial_generator",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_INDUSTRIAL_GENERATOR::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CYCLOTRON = registerBlock("machine_cyclotron",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CYCLOTRON::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_EXPOSURE_CHAMBER = registerBlock("machine_exposure_chamber",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_EXPOSURE_CHAMBER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_RADGEN = registerBlock("machine_radgen",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_RADGEN::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_DIESEL = registerBlock("machine_diesel",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_DIESEL::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_COMBUSTION_ENGINE = registerBlock("machine_combustion_engine",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_COMBUSTION_ENGINE::get));

    // =========================================================================
    // OIL & REFINERY
    // =========================================================================

    public static final RegistryObject<HBMStubMachineBlock> MACHINE_WELL = registerBlock("machine_well",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_WELL::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_PUMPJACK = registerBlock("machine_pumpjack",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_PUMPJACK::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_FRACKING_TOWER = registerBlock("machine_fracking_tower",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_FRACKING_TOWER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_REFINERY = registerBlock("machine_refinery",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_REFINERY::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_VACUUM_DISTILL = registerBlock("machine_vacuum_distill",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_VACUUM_DISTILL::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_FRACTION_TOWER = registerBlock("machine_fraction_tower",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_FRACTION_TOWER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CATALYTIC_CRACKER = registerBlock("machine_catalytic_cracker",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CATALYTIC_CRACKER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CATALYTIC_REFORMER = registerBlock("machine_catalytic_reformer",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CATALYTIC_REFORMER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_HYDROTREATER = registerBlock("machine_hydrotreater",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_HYDROTREATER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_COKER = registerBlock("machine_coker",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_COKER::get));

    // =========================================================================
    // ASSEMBLY & PRESSES
    // =========================================================================

    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ASSEMBLY_MACHINE = registerBlock("machine_assembly_machine",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ASSEMBLY_MACHINE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ASSEMBLY_FACTORY = registerBlock("machine_assembly_factory",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ASSEMBLY_FACTORY::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_PRECASS = registerBlock("machine_precass",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_PRECASS::get));
    public static final RegistryObject<HBMStubMachineBlock> PRESS_PREHEATER = registerBlock("press_preheater",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.PRESS_PREHEATER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_PRESS = registerBlock("machine_press",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_PRESS::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_EPRESS = registerBlock("machine_epress",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_EPRESS::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CONVEYOR_PRESS = registerBlock("machine_conveyor_press",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CONVEYOR_PRESS::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_AMMO_PRESS = registerBlock("machine_ammo_press",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_AMMO_PRESS::get));

    // =========================================================================
    // POWER / BATTERIES / TRANSFORMERS
    // =========================================================================

    public static final RegistryObject<HBMStubMachineBlock> MACHINE_TRANSFORMER = registerBlock("machine_transformer",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_TRANSFORMER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CONVERTER_HE_RF = registerBlock("machine_converter_he_rf",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CONVERTER_HE_RF::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CONVERTER_RF_HE = registerBlock("machine_converter_rf_he",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CONVERTER_RF_HE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_BATTERY_SOCKET = registerBlock("machine_battery_socket",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_BATTERY_SOCKET::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_BATTERY_REDD = registerBlock("machine_battery_redd",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_BATTERY_REDD::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_BATTERY_POTATO = registerBlock("machine_battery_potato",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_BATTERY_POTATO::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_LITHIUM_BATTERY = registerBlock("machine_lithium_battery",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_LITHIUM_BATTERY::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_SCHRABIDIUM_BATTERY = registerBlock("machine_schrabidium_battery",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_SCHRABIDIUM_BATTERY::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_DINEUTRONIUM_BATTERY = registerBlock("machine_dineutronium_battery",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_DINEUTRONIUM_BATTERY::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_FENSU = registerBlock("machine_fensu",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_FENSU::get));
    public static final RegistryObject<HBMStubMachineBlock> CAPACITOR_BUS = registerBlock("capacitor_bus",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.CAPACITOR_BUS::get));
    public static final RegistryObject<HBMStubMachineBlock> CAPACITOR_COPPER = registerBlock("capacitor_copper",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.CAPACITOR_COPPER::get));
    public static final RegistryObject<HBMStubMachineBlock> CAPACITOR_GOLD = registerBlock("capacitor_gold",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.CAPACITOR_GOLD::get));
    public static final RegistryObject<HBMStubMachineBlock> CAPACITOR_NIOBIUM = registerBlock("capacitor_niobium",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.CAPACITOR_NIOBIUM::get));
    public static final RegistryObject<HBMStubMachineBlock> CAPACITOR_TANTALIUM = registerBlock("capacitor_tantalium",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.CAPACITOR_TANTALIUM::get));
    public static final RegistryObject<HBMStubMachineBlock> CAPACITOR_SCHRABIDATE = registerBlock("capacitor_schrabidate",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.CAPACITOR_SCHRABIDATE::get));

    // =========================================================================
    // SPECIAL MACHINES / CONTROL
    // =========================================================================

    public static final RegistryObject<HBMStubMachineBlock> MACHINE_CONTROLLER = registerBlock("machine_controller",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_CONTROLLER::get));
    public static final RegistryObject<HBMStubMachineBlock> REACTOR_RESEARCH = registerBlock("reactor_research",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.REACTOR_RESEARCH::get));
    public static final RegistryObject<HBMStubMachineBlock> REACTOR_ZIRNOX = registerBlock("reactor_zirnox",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.REACTOR_ZIRNOX::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_SIREN = registerBlock("machine_siren",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_SIREN::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_RADAR = registerBlock("machine_radar",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_RADAR::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_RADAR_LARGE = registerBlock("machine_radar_large",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_RADAR_LARGE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_SATLINKER = registerBlock("machine_satlinker",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_SATLINKER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_KEYFORGE = registerBlock("machine_keyforge",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_KEYFORGE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ARMOR_TABLE = registerBlock("machine_armor_table",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ARMOR_TABLE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_WEAPON_TABLE = registerBlock("machine_weapon_table",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_WEAPON_TABLE::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_FORCEFIELD = registerBlock("machine_forcefield",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_FORCEFIELD::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_TELEPORTER = registerBlock("machine_teleporter",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_TELEPORTER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_WASTE_DRUM = registerBlock("machine_waste_drum",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_WASTE_DRUM::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_STORAGE_DRUM = registerBlock("machine_storage_drum",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_STORAGE_DRUM::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_DETECTOR = registerBlock("machine_detector",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_DETECTOR::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_SOLAR_BOILER = registerBlock("machine_solar_boiler",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_SOLAR_BOILER::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_MISSILE_ASSEMBLY = registerBlock("machine_missile_assembly",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_MISSILE_ASSEMBLY::get));
    public static final RegistryObject<HBMStubMachineBlock> MACHINE_ICF_PRESS = registerBlock("machine_icf_press",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.MACHINE_ICF_PRESS::get));

    // =========================================================================
    // FUSION / ITER / PLASMA
    // =========================================================================

    public static final RegistryObject<HBMStubMachineBlock> FUSION_HEATER = registerBlock("fusion_heater",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.FUSION_HEATER::get));
    public static final RegistryObject<HBMStubMachineBlock> PLASMA_HEATER = registerBlock("plasma_heater",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.PLASMA_HEATER::get));
    public static final RegistryObject<HBMStubMachineBlock> ITER = registerBlock("iter",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.ITER::get));
    public static final RegistryObject<HBMStubMachineBlock> ICF_LASER_CONTROLLER = registerBlock("icf_laser_controller",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.ICF_LASER_CONTROLLER::get));

    // =========================================================================
    // FOUNDRY
    // =========================================================================

    public static final RegistryObject<HBMStubMachineBlock> FOUNDRY_MOLD = registerBlock("foundry_mold",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.FOUNDRY_MOLD::get));
    public static final RegistryObject<HBMStubMachineBlock> FOUNDRY_BASIN = registerBlock("foundry_basin",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.FOUNDRY_BASIN::get));
    public static final RegistryObject<HBMStubMachineBlock> FOUNDRY_CHANNEL = registerBlock("foundry_channel",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.FOUNDRY_CHANNEL::get));
    public static final RegistryObject<HBMStubMachineBlock> FOUNDRY_TANK = registerBlock("foundry_tank",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.FOUNDRY_TANK::get));
    public static final RegistryObject<HBMStubMachineBlock> FOUNDRY_OUTLET = registerBlock("foundry_outlet",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.FOUNDRY_OUTLET::get));
    public static final RegistryObject<HBMStubMachineBlock> FOUNDRY_SLAGTAP = registerBlock("foundry_slagtap",
            () -> new HBMStubMachineBlock(metal(5f, 10f), ModBlockEntities.FOUNDRY_SLAGTAP::get));

// ── Add to ModBlocks.java ──────────────────────────────────────────────────
// Imports needed:
//   import com.hbm.fluid.ModFluids;
//   import net.minecraft.world.level.block.LiquidBlock;
//   import net.minecraft.world.level.block.state.BlockBehaviour;
//   import net.minecraft.world.level.material.PushReaction;
//
// RegistryObject<ForgeFlowingFluid> implements Supplier<ForgeFlowingFluid>
// and only resolves after the fluid RegisterEvent fires, so it is safe to
// pass directly to LiquidBlock. The ModFluids back-references to ModBlocks
// are all inside lambdas so there is no class-init deadlock.

    public static final RegistryObject<Block> ACID_BLOCK = BLOCKS.register("acid_block",
            () -> new LiquidBlock(ModFluids.ACID_SOURCE,
                    BlockBehaviour.Properties.of().noCollission().replaceable()
                            .pushReaction(PushReaction.DESTROY).strength(100f).noLootTable().liquid()));

    public static final RegistryObject<Block> TOXIC_BLOCK = BLOCKS.register("toxic_block",
            () -> new LiquidBlock(ModFluids.TOXIC_SOURCE,
                    BlockBehaviour.Properties.of().noCollission().replaceable()
                            .pushReaction(PushReaction.DESTROY).strength(100f).noLootTable().liquid()));

    public static final RegistryObject<Block> SCHRABIDIC_BLOCK = BLOCKS.register("schrabidic_block",
            () -> new LiquidBlock(ModFluids.SCHRABIDIC_SOURCE,
                    BlockBehaviour.Properties.of().noCollission().replaceable()
                            .pushReaction(PushReaction.DESTROY).strength(100f).noLootTable().liquid()));

    public static final RegistryObject<Block> MUD_BLOCK = BLOCKS.register("mud_block",
            () -> new LiquidBlock(ModFluids.MUD_SOURCE,
                    BlockBehaviour.Properties.of().noCollission().replaceable()
                            .pushReaction(PushReaction.DESTROY).strength(100f).noLootTable().liquid()));

    public static final RegistryObject<Block> VOLCANIC_LAVA_BLOCK = BLOCKS.register("volcanic_lava_block",
            () -> new LiquidBlock(ModFluids.VOLCANIC_SOURCE,
                    BlockBehaviour.Properties.of().noCollission().replaceable()
                            .pushReaction(PushReaction.DESTROY).strength(100f).noLootTable().liquid()
                            .lightLevel(state -> 15)));

    public static final RegistryObject<Block> RAD_LAVA_BLOCK = BLOCKS.register("rad_lava_block",
            () -> new LiquidBlock(ModFluids.RAD_LAVA_SOURCE,
                    BlockBehaviour.Properties.of().noCollission().replaceable()
                            .pushReaction(PushReaction.DESTROY).strength(100f).noLootTable().liquid()
                            .lightLevel(state -> 15)));

    public static final RegistryObject<Block> CORIUM_FLUID_BLOCK = BLOCKS.register("corium_fluid_block",
            () -> new LiquidBlock(ModFluids.CORIUM_SOURCE,
                    BlockBehaviour.Properties.of().noCollission().replaceable()
                            .pushReaction(PushReaction.DESTROY).strength(100f).noLootTable().liquid()
                            .lightLevel(state -> 10)));
}