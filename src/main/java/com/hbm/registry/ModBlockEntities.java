package com.hbm.registry;

import com.hbm.HBMMod;
import com.hbm.blockentity.PipeBlockEntity;
import com.hbm.blockentity.machine.HBMStubMachineBlockEntity;
import com.hbm.blockentity.rbmk.RBMKColumnBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * ModBlockEntities – BlockEntityType registry for HBM Nuclear Tech (1.20.1).
 *
 * All machine block entities are registered as either:
 *   HBMStubMachineBlockEntity  – no GUI yet, 9-slot inventory stub
 *   RBMKColumnBlockEntity      – RBMK columns, 1-slot stub + server tick hook
 *
 * When a real TileEntity class is ported, change ONLY the ::new reference
 * below from the stub class to the real one.  Block registrations in
 * ModBlocks.java do NOT change.
 *
 * Wire up in HBMMod constructor:
 *   ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
 */
public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HBMMod.MODID);

    // =========================================================================
    // Helpers
    // =========================================================================

    private static RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>> stub(
            String name, Supplier<Block> block) {
        return BLOCK_ENTITIES.register(name, () ->
                BlockEntityType.Builder
                        .of(HBMStubMachineBlockEntity::new, block.get())
                        .build(null));
    }

    private static RegistryObject<BlockEntityType<RBMKColumnBlockEntity>> rbmk(
            String name, Supplier<Block> block) {
        return BLOCK_ENTITIES.register(name, () ->
                BlockEntityType.Builder
                        .of(RBMKColumnBlockEntity::new, block.get())
                        .build(null));
    }

    public static final RegistryObject<BlockEntityType<PipeBlockEntity>> PIPE =
            BLOCK_ENTITIES.register("pipe",
                    () -> BlockEntityType.Builder.of(
                            PipeBlockEntity::new,
                            ModBlocks.PIPE.get()
                    ).build(null)
            );

    // =========================================================================
    // RBMK Columns (21 types)
    // =========================================================================

    public static final RegistryObject<BlockEntityType<RBMKColumnBlockEntity>>
        RBMK_ROD            = rbmk("rbmk_rod",            () -> ModBlocks.RBMK_ROD.get()),
        RBMK_ROD_MOD        = rbmk("rbmk_rod_mod",        () -> ModBlocks.RBMK_ROD_MOD.get()),
        RBMK_ROD_REASIM     = rbmk("rbmk_rod_reasim",     () -> ModBlocks.RBMK_ROD_REASIM.get()),
        RBMK_ROD_REASIM_MOD = rbmk("rbmk_rod_reasim_mod", () -> ModBlocks.RBMK_ROD_REASIM_MOD.get()),
        RBMK_CONTROL        = rbmk("rbmk_control",        () -> ModBlocks.RBMK_CONTROL.get()),
        RBMK_CONTROL_MOD    = rbmk("rbmk_control_mod",    () -> ModBlocks.RBMK_CONTROL_MOD.get()),
        RBMK_CONTROL_AUTO   = rbmk("rbmk_control_auto",   () -> ModBlocks.RBMK_CONTROL_AUTO.get()),
        RBMK_BLANK          = rbmk("rbmk_blank",          () -> ModBlocks.RBMK_BLANK.get()),
        RBMK_BOILER         = rbmk("rbmk_boiler",         () -> ModBlocks.RBMK_BOILER.get()),
        RBMK_REFLECTOR      = rbmk("rbmk_reflector",      () -> ModBlocks.RBMK_REFLECTOR.get()),
        RBMK_ABSORBER       = rbmk("rbmk_absorber",       () -> ModBlocks.RBMK_ABSORBER.get()),
        RBMK_MODERATOR      = rbmk("rbmk_moderator",      () -> ModBlocks.RBMK_MODERATOR.get()),
        RBMK_OUTGASSER      = rbmk("rbmk_outgasser",      () -> ModBlocks.RBMK_OUTGASSER.get()),
        RBMK_STORAGE        = rbmk("rbmk_storage",        () -> ModBlocks.RBMK_STORAGE.get()),
        RBMK_COOLER         = rbmk("rbmk_cooler",         () -> ModBlocks.RBMK_COOLER.get()),
        RBMK_HEATER         = rbmk("rbmk_heater",         () -> ModBlocks.RBMK_HEATER.get()),
        RBMK_CONSOLE        = rbmk("rbmk_console",        () -> ModBlocks.RBMK_CONSOLE.get()),
        RBMK_CRANE_CONSOLE  = rbmk("rbmk_crane_console",  () -> ModBlocks.RBMK_CRANE_CONSOLE.get()),
        RBMK_AUTOLOADER     = rbmk("rbmk_autoloader",     () -> ModBlocks.RBMK_AUTOLOADER.get()),
        RBMK_LOADER         = rbmk("rbmk_loader",         () -> ModBlocks.RBMK_LOADER.get()),
        RBMK_STEAM_INLET    = rbmk("rbmk_steam_inlet",    () -> ModBlocks.RBMK_STEAM_INLET.get());

    // =========================================================================
    // Furnaces / Heaters
    // =========================================================================

    public static final RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>>
        FURNACE_IRON                  = stub("furnace_iron",                  () -> ModBlocks.FURNACE_IRON.get()),
        FURNACE_STEEL                 = stub("furnace_steel",                 () -> ModBlocks.FURNACE_STEEL.get()),
        HEATER_FIREBOX                = stub("heater_firebox",                () -> ModBlocks.HEATER_FIREBOX.get()),
        HEATER_ELECTRIC               = stub("heater_electric",               () -> ModBlocks.HEATER_ELECTRIC.get()),
        MACHINE_FURNACE_BRICK_OFF     = stub("machine_furnace_brick_off",     () -> ModBlocks.MACHINE_FURNACE_BRICK_OFF.get()),
        MACHINE_FURNACE_BRICK_ON      = stub("machine_furnace_brick_on",      () -> ModBlocks.MACHINE_FURNACE_BRICK_ON.get()),
        MACHINE_DIFURNACE_OFF         = stub("machine_difurnace_off",         () -> ModBlocks.MACHINE_DIFURNACE_OFF.get()),
        MACHINE_DIFURNACE_ON          = stub("machine_difurnace_on",          () -> ModBlocks.MACHINE_DIFURNACE_ON.get()),
        MACHINE_ELECTRIC_FURNACE_OFF  = stub("machine_electric_furnace_off",  () -> ModBlocks.MACHINE_ELECTRIC_FURNACE_OFF.get()),
        MACHINE_ELECTRIC_FURNACE_ON   = stub("machine_electric_furnace_on",   () -> ModBlocks.MACHINE_ELECTRIC_FURNACE_ON.get()),
        MACHINE_MICROWAVE             = stub("machine_microwave",             () -> ModBlocks.MACHINE_MICROWAVE.get()),
        MACHINE_ARC_FURNACE           = stub("machine_arc_furnace",           () -> ModBlocks.MACHINE_ARC_FURNACE.get()),
        MACHINE_ROTARY_FURNACE        = stub("machine_rotary_furnace",        () -> ModBlocks.MACHINE_ROTARY_FURNACE.get()),
        MACHINE_PYROOVEN              = stub("machine_pyrooven",              () -> ModBlocks.MACHINE_PYROOVEN.get()),
        MACHINE_WOOD_BURNER           = stub("machine_wood_burner",           () -> ModBlocks.MACHINE_WOOD_BURNER.get());

    // =========================================================================
    // Steam / Engines
    // =========================================================================

    public static final RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>>
        MACHINE_STIRLING_OFF            = stub("machine_stirling_off",            () -> ModBlocks.MACHINE_STIRLING_OFF.get()),
        MACHINE_STIRLING_ON             = stub("machine_stirling_on",             () -> ModBlocks.MACHINE_STIRLING_ON.get()),
        MACHINE_STIRLING_GENERATOR_OFF  = stub("machine_stirling_generator_off",  () -> ModBlocks.MACHINE_STIRLING_GENERATOR_OFF.get()),
        MACHINE_STIRLING_GENERATOR_ON   = stub("machine_stirling_generator_on",   () -> ModBlocks.MACHINE_STIRLING_GENERATOR_ON.get()),
        MACHINE_BOILER                  = stub("machine_boiler",                  () -> ModBlocks.MACHINE_BOILER.get()),
        PUMP_STEAM                      = stub("pump_steam",                      () -> ModBlocks.PUMP_STEAM.get()),
        PUMP_ELECTRIC                   = stub("pump_electric",                   () -> ModBlocks.PUMP_ELECTRIC.get()),
        MACHINE_STEAM_ENGINE            = stub("machine_steam_engine",            () -> ModBlocks.MACHINE_STEAM_ENGINE.get()),
        MACHINE_TURBINE                 = stub("machine_turbine",                 () -> ModBlocks.MACHINE_TURBINE.get()),
        MACHINE_LARGE_TURBINE           = stub("machine_large_turbine",           () -> ModBlocks.MACHINE_LARGE_TURBINE.get()),
        MACHINE_INDUSTRIAL_TURBINE      = stub("machine_industrial_turbine",      () -> ModBlocks.MACHINE_INDUSTRIAL_TURBINE.get()),
        MACHINE_CHUNGUS                 = stub("machine_chungus",                 () -> ModBlocks.MACHINE_CHUNGUS.get()),
        MACHINE_CONDENSER               = stub("machine_condenser",               () -> ModBlocks.MACHINE_CONDENSER.get()),
        MACHINE_TOWER_SMALL             = stub("machine_tower_small",             () -> ModBlocks.MACHINE_TOWER_SMALL.get()),
        MACHINE_TOWER_LARGE             = stub("machine_tower_large",             () -> ModBlocks.MACHINE_TOWER_LARGE.get()),
        MACHINE_TURBOFAN                = stub("machine_turbofan",                () -> ModBlocks.MACHINE_TURBOFAN.get()),
        MACHINE_TURBINEGAS              = stub("machine_turbinegas",              () -> ModBlocks.MACHINE_TURBINEGAS.get()),
        MACHINE_LPW2                    = stub("machine_lpw2",                    () -> ModBlocks.MACHINE_LPW2.get());

    // =========================================================================
    // Processing
    // =========================================================================

    public static final RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>>
        MACHINE_SAWMILL              = stub("machine_sawmill",              () -> ModBlocks.MACHINE_SAWMILL.get()),
        MACHINE_CRUCIBLE             = stub("machine_crucible",             () -> ModBlocks.MACHINE_CRUCIBLE.get()),
        MACHINE_CENTRIFUGE           = stub("machine_centrifuge",           () -> ModBlocks.MACHINE_CENTRIFUGE.get()),
        MACHINE_GASCENT              = stub("machine_gascent",              () -> ModBlocks.MACHINE_GASCENT.get()),
        MACHINE_FEL                  = stub("machine_fel",                  () -> ModBlocks.MACHINE_FEL.get()),
        MACHINE_SILEX                = stub("machine_silex",                () -> ModBlocks.MACHINE_SILEX.get()),
        MACHINE_CRYSTALLIZER         = stub("machine_crystallizer",         () -> ModBlocks.MACHINE_CRYSTALLIZER.get()),
        MACHINE_MIXER                = stub("machine_mixer",                () -> ModBlocks.MACHINE_MIXER.get()),
        MACHINE_STRAND_CASTER        = stub("machine_strand_caster",        () -> ModBlocks.MACHINE_STRAND_CASTER.get()),
        MACHINE_SHREDDER             = stub("machine_shredder",             () -> ModBlocks.MACHINE_SHREDDER.get()),
        MACHINE_AUTOSAW              = stub("machine_autosaw",              () -> ModBlocks.MACHINE_AUTOSAW.get()),
        MACHINE_EXCAVATOR            = stub("machine_excavator",            () -> ModBlocks.MACHINE_EXCAVATOR.get()),
        MACHINE_ORE_SLOPPER          = stub("machine_ore_slopper",          () -> ModBlocks.MACHINE_ORE_SLOPPER.get()),
        MACHINE_ANNIHILATOR          = stub("machine_annihilator",          () -> ModBlocks.MACHINE_ANNIHILATOR.get()),
        MACHINE_MINING_LASER         = stub("machine_mining_laser",         () -> ModBlocks.MACHINE_MINING_LASER.get()),
        MACHINE_ARC_WELDER           = stub("machine_arc_welder",           () -> ModBlocks.MACHINE_ARC_WELDER.get()),
        MACHINE_SOLDERING_STATION    = stub("machine_soldering_station",    () -> ModBlocks.MACHINE_SOLDERING_STATION.get()),
        MACHINE_AUTOCRAFTER          = stub("machine_autocrafter",          () -> ModBlocks.MACHINE_AUTOCRAFTER.get()),
        MACHINE_FUNNEL               = stub("machine_funnel",               () -> ModBlocks.MACHINE_FUNNEL.get());

    // =========================================================================
    // Chemical / Gas
    // =========================================================================

    public static final RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>>
        MACHINE_CHEMICAL_PLANT       = stub("machine_chemical_plant",       () -> ModBlocks.MACHINE_CHEMICAL_PLANT.get()),
        MACHINE_CHEMICAL_FACTORY     = stub("machine_chemical_factory",     () -> ModBlocks.MACHINE_CHEMICAL_FACTORY.get()),
        MACHINE_PUREX                = stub("machine_purex",                () -> ModBlocks.MACHINE_PUREX.get()),
        MACHINE_INTAKE               = stub("machine_intake",               () -> ModBlocks.MACHINE_INTAKE.get()),
        MACHINE_COMPRESSOR           = stub("machine_compressor",           () -> ModBlocks.MACHINE_COMPRESSOR.get()),
        MACHINE_COMPRESSOR_ELECTRIC  = stub("machine_compressor_electric",  () -> ModBlocks.MACHINE_COMPRESSOR_ELECTRIC.get()),
        MACHINE_ELECTROLYSER         = stub("machine_electrolyser",         () -> ModBlocks.MACHINE_ELECTROLYSER.get()),
        MACHINE_LIQUEFACTOR          = stub("machine_liquefactor",          () -> ModBlocks.MACHINE_LIQUEFACTOR.get()),
        MACHINE_SOLIDIFIER           = stub("machine_solidifier",           () -> ModBlocks.MACHINE_SOLIDIFIER.get()),
        MACHINE_DEUTERIUM_EXTRACTOR  = stub("machine_deuterium_extractor",  () -> ModBlocks.MACHINE_DEUTERIUM_EXTRACTOR.get()),
        MACHINE_DEUTERIUM_TOWER      = stub("machine_deuterium_tower",      () -> ModBlocks.MACHINE_DEUTERIUM_TOWER.get()),
        MACHINE_FLUIDTANK            = stub("machine_fluidtank",            () -> ModBlocks.MACHINE_FLUIDTANK.get()),
        MACHINE_BAT9000              = stub("machine_bat9000",              () -> ModBlocks.MACHINE_BAT9000.get()),
        MACHINE_ORBUS                = stub("machine_orbus",                () -> ModBlocks.MACHINE_ORBUS.get()),
        MACHINE_DRAIN                = stub("machine_drain",                () -> ModBlocks.MACHINE_DRAIN.get());

    // =========================================================================
    // Nuclear / Isotope / RTG
    // =========================================================================

    public static final RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>>
        MACHINE_UF6_TANK             = stub("machine_uf6_tank",             () -> ModBlocks.MACHINE_UF6_TANK.get()),
        MACHINE_PUF6_TANK            = stub("machine_puf6_tank",            () -> ModBlocks.MACHINE_PUF6_TANK.get()),
        MACHINE_REACTOR_BREEDING     = stub("machine_reactor_breeding",     () -> ModBlocks.MACHINE_REACTOR_BREEDING.get()),
        MACHINE_RTG_GREY             = stub("machine_rtg_grey",             () -> ModBlocks.MACHINE_RTG_GREY.get()),
        MACHINE_MINIRTG              = stub("machine_minirtg",              () -> ModBlocks.MACHINE_MINIRTG.get()),
        MACHINE_POWERRTG             = stub("machine_powerrtg",             () -> ModBlocks.MACHINE_POWERRTG.get()),
        MACHINE_RADIOLYSIS           = stub("machine_radiolysis",           () -> ModBlocks.MACHINE_RADIOLYSIS.get()),
        MACHINE_HEPHAESTUS           = stub("machine_hephaestus",           () -> ModBlocks.MACHINE_HEPHAESTUS.get()),
        MACHINE_INDUSTRIAL_GENERATOR = stub("machine_industrial_generator", () -> ModBlocks.MACHINE_INDUSTRIAL_GENERATOR.get()),
        MACHINE_CYCLOTRON            = stub("machine_cyclotron",            () -> ModBlocks.MACHINE_CYCLOTRON.get()),
        MACHINE_EXPOSURE_CHAMBER     = stub("machine_exposure_chamber",     () -> ModBlocks.MACHINE_EXPOSURE_CHAMBER.get()),
        MACHINE_RADGEN               = stub("machine_radgen",               () -> ModBlocks.MACHINE_RADGEN.get()),
        MACHINE_DIESEL               = stub("machine_diesel",               () -> ModBlocks.MACHINE_DIESEL.get()),
        MACHINE_COMBUSTION_ENGINE    = stub("machine_combustion_engine",    () -> ModBlocks.MACHINE_COMBUSTION_ENGINE.get());

    // =========================================================================
    // Oil / Refinery
    // =========================================================================

    public static final RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>>
        MACHINE_WELL                 = stub("machine_well",                 () -> ModBlocks.MACHINE_WELL.get()),
        MACHINE_PUMPJACK             = stub("machine_pumpjack",             () -> ModBlocks.MACHINE_PUMPJACK.get()),
        MACHINE_FRACKING_TOWER       = stub("machine_fracking_tower",       () -> ModBlocks.MACHINE_FRACKING_TOWER.get()),
        MACHINE_REFINERY             = stub("machine_refinery",             () -> ModBlocks.MACHINE_REFINERY.get()),
        MACHINE_VACUUM_DISTILL       = stub("machine_vacuum_distill",       () -> ModBlocks.MACHINE_VACUUM_DISTILL.get()),
        MACHINE_FRACTION_TOWER       = stub("machine_fraction_tower",       () -> ModBlocks.MACHINE_FRACTION_TOWER.get()),
        MACHINE_CATALYTIC_CRACKER    = stub("machine_catalytic_cracker",    () -> ModBlocks.MACHINE_CATALYTIC_CRACKER.get()),
        MACHINE_CATALYTIC_REFORMER   = stub("machine_catalytic_reformer",   () -> ModBlocks.MACHINE_CATALYTIC_REFORMER.get()),
        MACHINE_HYDROTREATER         = stub("machine_hydrotreater",         () -> ModBlocks.MACHINE_HYDROTREATER.get()),
        MACHINE_COKER                = stub("machine_coker",                () -> ModBlocks.MACHINE_COKER.get());

    // =========================================================================
    // Assembly / Presses
    // =========================================================================

    public static final RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>>
        MACHINE_ASSEMBLY_MACHINE     = stub("machine_assembly_machine",     () -> ModBlocks.MACHINE_ASSEMBLY_MACHINE.get()),
        MACHINE_ASSEMBLY_FACTORY     = stub("machine_assembly_factory",     () -> ModBlocks.MACHINE_ASSEMBLY_FACTORY.get()),
        MACHINE_PRECASS              = stub("machine_precass",              () -> ModBlocks.MACHINE_PRECASS.get()),
        PRESS_PREHEATER              = stub("press_preheater",              () -> ModBlocks.PRESS_PREHEATER.get()),
        MACHINE_PRESS                = stub("machine_press",                () -> ModBlocks.MACHINE_PRESS.get()),
        MACHINE_EPRESS               = stub("machine_epress",              () -> ModBlocks.MACHINE_EPRESS.get()),
        MACHINE_CONVEYOR_PRESS       = stub("machine_conveyor_press",       () -> ModBlocks.MACHINE_CONVEYOR_PRESS.get()),
        MACHINE_AMMO_PRESS           = stub("machine_ammo_press",           () -> ModBlocks.MACHINE_AMMO_PRESS.get());

    // =========================================================================
    // Power / Batteries / Transformers
    // =========================================================================

    public static final RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>>
        MACHINE_TRANSFORMER          = stub("machine_transformer",          () -> ModBlocks.MACHINE_TRANSFORMER.get()),
        MACHINE_CONVERTER_HE_RF      = stub("machine_converter_he_rf",      () -> ModBlocks.MACHINE_CONVERTER_HE_RF.get()),
        MACHINE_CONVERTER_RF_HE      = stub("machine_converter_rf_he",      () -> ModBlocks.MACHINE_CONVERTER_RF_HE.get()),
        MACHINE_BATTERY_SOCKET       = stub("machine_battery_socket",       () -> ModBlocks.MACHINE_BATTERY_SOCKET.get()),
        MACHINE_BATTERY_REDD         = stub("machine_battery_redd",         () -> ModBlocks.MACHINE_BATTERY_REDD.get()),
        MACHINE_BATTERY_POTATO       = stub("machine_battery_potato",       () -> ModBlocks.MACHINE_BATTERY_POTATO.get()),
        MACHINE_LITHIUM_BATTERY      = stub("machine_lithium_battery",      () -> ModBlocks.MACHINE_LITHIUM_BATTERY.get()),
        MACHINE_SCHRABIDIUM_BATTERY  = stub("machine_schrabidium_battery",  () -> ModBlocks.MACHINE_SCHRABIDIUM_BATTERY.get()),
        MACHINE_DINEUTRONIUM_BATTERY = stub("machine_dineutronium_battery", () -> ModBlocks.MACHINE_DINEUTRONIUM_BATTERY.get()),
        MACHINE_FENSU                = stub("machine_fensu",                () -> ModBlocks.MACHINE_FENSU.get()),
        CAPACITOR_BUS                = stub("capacitor_bus",                () -> ModBlocks.CAPACITOR_BUS.get()),
        CAPACITOR_COPPER             = stub("capacitor_copper",             () -> ModBlocks.CAPACITOR_COPPER.get()),
        CAPACITOR_GOLD               = stub("capacitor_gold",               () -> ModBlocks.CAPACITOR_GOLD.get()),
        CAPACITOR_NIOBIUM            = stub("capacitor_niobium",            () -> ModBlocks.CAPACITOR_NIOBIUM.get()),
        CAPACITOR_TANTALIUM          = stub("capacitor_tantalium",          () -> ModBlocks.CAPACITOR_TANTALIUM.get()),
        CAPACITOR_SCHRABIDATE        = stub("capacitor_schrabidate",        () -> ModBlocks.CAPACITOR_SCHRABIDATE.get());

    // =========================================================================
    // Special machines / Control
    // =========================================================================

    public static final RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>>
        MACHINE_CONTROLLER           = stub("machine_controller",           () -> ModBlocks.MACHINE_CONTROLLER.get()),
        REACTOR_RESEARCH             = stub("reactor_research",             () -> ModBlocks.REACTOR_RESEARCH.get()),
        REACTOR_ZIRNOX               = stub("reactor_zirnox",               () -> ModBlocks.REACTOR_ZIRNOX.get()),
        MACHINE_SIREN                = stub("machine_siren",                () -> ModBlocks.MACHINE_SIREN.get()),
        MACHINE_RADAR                = stub("machine_radar",                () -> ModBlocks.MACHINE_RADAR.get()),
        MACHINE_RADAR_LARGE          = stub("machine_radar_large",          () -> ModBlocks.MACHINE_RADAR_LARGE.get()),
        MACHINE_SATLINKER            = stub("machine_satlinker",            () -> ModBlocks.MACHINE_SATLINKER.get()),
        MACHINE_KEYFORGE             = stub("machine_keyforge",             () -> ModBlocks.MACHINE_KEYFORGE.get()),
        MACHINE_ARMOR_TABLE          = stub("machine_armor_table",          () -> ModBlocks.MACHINE_ARMOR_TABLE.get()),
        MACHINE_WEAPON_TABLE         = stub("machine_weapon_table",         () -> ModBlocks.MACHINE_WEAPON_TABLE.get()),
        MACHINE_FORCEFIELD           = stub("machine_forcefield",           () -> ModBlocks.MACHINE_FORCEFIELD.get()),
        MACHINE_TELEPORTER           = stub("machine_teleporter",           () -> ModBlocks.MACHINE_TELEPORTER.get()),
        MACHINE_WASTE_DRUM           = stub("machine_waste_drum",           () -> ModBlocks.MACHINE_WASTE_DRUM.get()),
        MACHINE_STORAGE_DRUM         = stub("machine_storage_drum",         () -> ModBlocks.MACHINE_STORAGE_DRUM.get()),
        MACHINE_DETECTOR             = stub("machine_detector",             () -> ModBlocks.MACHINE_DETECTOR.get()),
        MACHINE_SOLAR_BOILER         = stub("machine_solar_boiler",         () -> ModBlocks.MACHINE_SOLAR_BOILER.get()),
        MACHINE_MISSILE_ASSEMBLY     = stub("machine_missile_assembly",     () -> ModBlocks.MACHINE_MISSILE_ASSEMBLY.get()),
        MACHINE_ICF_PRESS            = stub("machine_icf_press",            () -> ModBlocks.MACHINE_ICF_PRESS.get());

    // =========================================================================
    // Fusion / ITER / Plasma
    // =========================================================================

    public static final RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>>
        FUSION_HEATER                = stub("fusion_heater",                () -> ModBlocks.FUSION_HEATER.get()),
        PLASMA_HEATER                = stub("plasma_heater",                () -> ModBlocks.PLASMA_HEATER.get()),
        ITER                         = stub("iter",                         () -> ModBlocks.ITER.get()),
        ICF_LASER_CONTROLLER         = stub("icf_laser_controller",         () -> ModBlocks.ICF_LASER_CONTROLLER.get());

    // =========================================================================
    // Foundry
    // =========================================================================

    public static final RegistryObject<BlockEntityType<HBMStubMachineBlockEntity>>
        FOUNDRY_MOLD                 = stub("foundry_mold",                 () -> ModBlocks.FOUNDRY_MOLD.get()),
        FOUNDRY_BASIN                = stub("foundry_basin",                () -> ModBlocks.FOUNDRY_BASIN.get()),
        FOUNDRY_CHANNEL              = stub("foundry_channel",              () -> ModBlocks.FOUNDRY_CHANNEL.get()),
        FOUNDRY_TANK                 = stub("foundry_tank",                 () -> ModBlocks.FOUNDRY_TANK.get()),
        FOUNDRY_OUTLET               = stub("foundry_outlet",               () -> ModBlocks.FOUNDRY_OUTLET.get()),
        FOUNDRY_SLAGTAP              = stub("foundry_slagtap",              () -> ModBlocks.FOUNDRY_SLAGTAP.get());
}