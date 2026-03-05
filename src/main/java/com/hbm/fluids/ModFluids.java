package com.hbm.fluids;


import com.hbm.HBMMod;
import com.hbm.fluids.HbmFluidType;
import com.hbm.registry.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {

    private static final String MODID = HBMMod.MODID;

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MODID);
    public static final DeferredRegister<net.minecraft.world.level.material.Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);
    public static final DeferredRegister<Item> BUCKET_ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    private static ResourceLocation still(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, "block/" + name + "_still");
    }
    private static ResourceLocation flowing(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, "block/" + name + "_flowing");
    }
    private static FluidType.Properties liquidProps(int density, int viscosity) {
        return FluidType.Properties.create().density(density).viscosity(viscosity);
    }
    private static FluidType.Properties hotLiquidProps(int temperature) {
        return FluidType.Properties.create()
                .density(3000).viscosity(6000).temperature(temperature)
                .lightLevel(15).motionScale(0.0023D);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // 1. ACID
    // ═══════════════════════════════════════════════════════════════════════

    public static final RegistryObject<FluidType> ACID_TYPE = FLUID_TYPES.register("acid_fluid",
            () -> new HbmFluidType(new HbmFluidType.Props(0xB0AA64,
                    still("acid"), flowing("acid"), liquidProps(1200, 1500))));
    public static final RegistryObject<ForgeFlowingFluid> ACID_SOURCE = FLUIDS.register("acid_fluid",
            () -> new ForgeFlowingFluid.Source(acidProps()));
    public static final RegistryObject<ForgeFlowingFluid> ACID_FLOWING = FLUIDS.register("acid_fluid_flowing",
            () -> new ForgeFlowingFluid.Flowing(acidProps()));
    public static final RegistryObject<Item> ACID_BUCKET = BUCKET_ITEMS.register("bucket_acid",
            () -> new BucketItem(ACID_SOURCE, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));
    private static ForgeFlowingFluid.Properties acidProps() {
        return new ForgeFlowingFluid.Properties(ACID_TYPE, ACID_SOURCE, ACID_FLOWING)
                .slopeFindDistance(4).levelDecreasePerBlock(2)
                .block(() -> (LiquidBlock) ModBlocks.ACID_BLOCK.get())
                .bucket(ACID_BUCKET);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // 2. TOXIC
    // ═══════════════════════════════════════════════════════════════════════

    public static final RegistryObject<FluidType> TOXIC_TYPE = FLUID_TYPES.register("toxic_fluid",
            () -> new HbmFluidType(new HbmFluidType.Props(0x3A5C1E,
                    still("toxic"), flowing("toxic"), liquidProps(1300, 2000))));
    public static final RegistryObject<ForgeFlowingFluid> TOXIC_SOURCE = FLUIDS.register("toxic_fluid",
            () -> new ForgeFlowingFluid.Source(toxicProps()));
    public static final RegistryObject<ForgeFlowingFluid> TOXIC_FLOWING = FLUIDS.register("toxic_fluid_flowing",
            () -> new ForgeFlowingFluid.Flowing(toxicProps()));
    public static final RegistryObject<Item> TOXIC_BUCKET = BUCKET_ITEMS.register("bucket_toxic",
            () -> new BucketItem(TOXIC_SOURCE, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));
    private static ForgeFlowingFluid.Properties toxicProps() {
        return new ForgeFlowingFluid.Properties(TOXIC_TYPE, TOXIC_SOURCE, TOXIC_FLOWING)
                .slopeFindDistance(4).levelDecreasePerBlock(2)
                .block(() -> (LiquidBlock) ModBlocks.TOXIC_BLOCK.get())
                .bucket(TOXIC_BUCKET);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // 3. SCHRABIDIC
    // ═══════════════════════════════════════════════════════════════════════

    public static final RegistryObject<FluidType> SCHRABIDIC_TYPE = FLUID_TYPES.register("schrabidic_fluid",
            () -> new HbmFluidType(new HbmFluidType.Props(0x006B6B,
                    still("schrabidic_acid"), flowing("schrabidic_acid"), liquidProps(1400, 2500))));
    public static final RegistryObject<ForgeFlowingFluid> SCHRABIDIC_SOURCE = FLUIDS.register("schrabidic_fluid",
            () -> new ForgeFlowingFluid.Source(schrabidicProps()));
    public static final RegistryObject<ForgeFlowingFluid> SCHRABIDIC_FLOWING = FLUIDS.register("schrabidic_fluid_flowing",
            () -> new ForgeFlowingFluid.Flowing(schrabidicProps()));
    public static final RegistryObject<Item> SCHRABIDIC_BUCKET = BUCKET_ITEMS.register("bucket_schrabidic_acid",
            () -> new BucketItem(SCHRABIDIC_SOURCE, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));
    private static ForgeFlowingFluid.Properties schrabidicProps() {
        return new ForgeFlowingFluid.Properties(SCHRABIDIC_TYPE, SCHRABIDIC_SOURCE, SCHRABIDIC_FLOWING)
                .slopeFindDistance(4).levelDecreasePerBlock(2)
                .block(() -> (LiquidBlock) ModBlocks.SCHRABIDIC_BLOCK.get())
                .bucket(SCHRABIDIC_BUCKET);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // 4. MUD
    // ═══════════════════════════════════════════════════════════════════════

    public static final RegistryObject<FluidType> MUD_TYPE = FLUID_TYPES.register("mud_fluid",
            () -> new HbmFluidType(new HbmFluidType.Props(0x3B2A14,
                    still("mud"), flowing("mud"), liquidProps(1500, 3000))));
    public static final RegistryObject<ForgeFlowingFluid> MUD_SOURCE = FLUIDS.register("mud_fluid",
            () -> new ForgeFlowingFluid.Source(mudProps()));
    public static final RegistryObject<ForgeFlowingFluid> MUD_FLOWING = FLUIDS.register("mud_fluid_flowing",
            () -> new ForgeFlowingFluid.Flowing(mudProps()));
    public static final RegistryObject<Item> MUD_BUCKET = BUCKET_ITEMS.register("bucket_mud",
            () -> new BucketItem(MUD_SOURCE, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));
    private static ForgeFlowingFluid.Properties mudProps() {
        return new ForgeFlowingFluid.Properties(MUD_TYPE, MUD_SOURCE, MUD_FLOWING)
                .slopeFindDistance(4).levelDecreasePerBlock(2)
                .block(() -> (LiquidBlock) ModBlocks.MUD_BLOCK.get())
                .bucket(MUD_BUCKET);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // 5. VOLCANIC LAVA
    // ═══════════════════════════════════════════════════════════════════════

    public static final RegistryObject<FluidType> VOLCANIC_TYPE = FLUID_TYPES.register("volcanic_lava_fluid",
            () -> new HbmFluidType(new HbmFluidType.Props(0xD34C00,
                    still("volcanic_lava"), flowing("volcanic_lava"), hotLiquidProps(1300))));
    public static final RegistryObject<ForgeFlowingFluid> VOLCANIC_SOURCE = FLUIDS.register("volcanic_lava_fluid",
            () -> new ForgeFlowingFluid.Source(volcanicProps()));
    public static final RegistryObject<ForgeFlowingFluid> VOLCANIC_FLOWING = FLUIDS.register("volcanic_lava_fluid_flowing",
            () -> new ForgeFlowingFluid.Flowing(volcanicProps()));
    public static final RegistryObject<Item> VOLCANIC_BUCKET = BUCKET_ITEMS.register("bucket_volcanic_lava",
            () -> new BucketItem(VOLCANIC_SOURCE, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET).fireResistant()));
    private static ForgeFlowingFluid.Properties volcanicProps() {
        return new ForgeFlowingFluid.Properties(VOLCANIC_TYPE, VOLCANIC_SOURCE, VOLCANIC_FLOWING)
                .slopeFindDistance(2).levelDecreasePerBlock(2)
                .block(() -> (LiquidBlock) ModBlocks.VOLCANIC_LAVA_BLOCK.get())
                .bucket(VOLCANIC_BUCKET);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // 6. RAD LAVA
    // ═══════════════════════════════════════════════════════════════════════

    public static final RegistryObject<FluidType> RAD_LAVA_TYPE = FLUID_TYPES.register("rad_lava_fluid",
            () -> new HbmFluidType(new HbmFluidType.Props(0xA0FF00,
                    still("rad_lava"), flowing("rad_lava"), hotLiquidProps(1500))));
    public static final RegistryObject<ForgeFlowingFluid> RAD_LAVA_SOURCE = FLUIDS.register("rad_lava_fluid",
            () -> new ForgeFlowingFluid.Source(radLavaProps()));
    public static final RegistryObject<ForgeFlowingFluid> RAD_LAVA_FLOWING = FLUIDS.register("rad_lava_fluid_flowing",
            () -> new ForgeFlowingFluid.Flowing(radLavaProps()));
    public static final RegistryObject<Item> RAD_LAVA_BUCKET = BUCKET_ITEMS.register("bucket_rad_lava",
            () -> new BucketItem(RAD_LAVA_SOURCE, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET).fireResistant()));
    private static ForgeFlowingFluid.Properties radLavaProps() {
        return new ForgeFlowingFluid.Properties(RAD_LAVA_TYPE, RAD_LAVA_SOURCE, RAD_LAVA_FLOWING)
                .slopeFindDistance(2).levelDecreasePerBlock(2)
                .block(() -> (LiquidBlock) ModBlocks.RAD_LAVA_BLOCK.get())
                .bucket(RAD_LAVA_BUCKET);
    }

    // ═══════════════════════════════════════════════════════════════════════
    // 7. CORIUM
    // ═══════════════════════════════════════════════════════════════════════

    public static final RegistryObject<FluidType> CORIUM_TYPE = FLUID_TYPES.register("corium_fluid",
            () -> new HbmFluidType(new HbmFluidType.Props(0x44A832,
                    still("corium"), flowing("corium"),
                    FluidType.Properties.create().density(4000).viscosity(8000)
                            .temperature(2000).lightLevel(10).motionScale(0.0023D))));
    public static final RegistryObject<ForgeFlowingFluid> CORIUM_SOURCE = FLUIDS.register("corium_fluid",
            () -> new ForgeFlowingFluid.Source(coriumProps()));
    public static final RegistryObject<ForgeFlowingFluid> CORIUM_FLOWING = FLUIDS.register("corium_fluid_flowing",
            () -> new ForgeFlowingFluid.Flowing(coriumProps()));
    public static final RegistryObject<Item> CORIUM_BUCKET = BUCKET_ITEMS.register("bucket_corium",
            () -> new BucketItem(CORIUM_SOURCE, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET).fireResistant()));
    private static ForgeFlowingFluid.Properties coriumProps() {
        return new ForgeFlowingFluid.Properties(CORIUM_TYPE, CORIUM_SOURCE, CORIUM_FLOWING)
                .slopeFindDistance(2).levelDecreasePerBlock(2)
                .block(() -> (LiquidBlock) ModBlocks.CORIUM_FLUID_BLOCK.get())
                .bucket(CORIUM_BUCKET);
    }

    // ── Registration ────────────────────────────────────────────────────────

    public static void register(IEventBus modBus) {
        FLUID_TYPES.register(modBus);
        FLUIDS.register(modBus);
        BUCKET_ITEMS.register(modBus);
    }
}