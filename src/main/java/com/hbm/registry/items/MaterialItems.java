package com.hbm.registry.items;

import com.hbm.HBMMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MaterialItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HBMMod.MODID);

    // ============================================================
    // HELPER
    // ============================================================

    private static RegistryObject<Item> material(String name) {
        return ITEMS.register(name,
                () -> new Item(new Item.Properties()));
    }

    private static RegistryObject<Item> rareMaterial(String name) {
        return ITEMS.register(name,
                () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    }

    private static RegistryObject<Item> epicMaterial(String name) {
        return ITEMS.register(name,
                () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    }

    private static RegistryObject<Item> legendaryMaterial(String name) {
        return ITEMS.register(name,
                () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    }

    // ============================================================
    // INGOTS
    // ============================================================

    public static final RegistryObject<Item> URANIUM_INGOT = rareMaterial("uranium_ingot");
    public static final RegistryObject<Item> U233_INGOT = rareMaterial("u233_ingot");
    public static final RegistryObject<Item> U235_INGOT = rareMaterial("u235_ingot");
    public static final RegistryObject<Item> U238_INGOT = rareMaterial("u238_ingot");

    public static final RegistryObject<Item> PLUTONIUM_INGOT = epicMaterial("plutonium_ingot");
    public static final RegistryObject<Item> PU238_INGOT = epicMaterial("pu238_ingot");
    public static final RegistryObject<Item> PU239_INGOT = epicMaterial("pu239_ingot");
    public static final RegistryObject<Item> PU240_INGOT = epicMaterial("pu240_ingot");
    public static final RegistryObject<Item> PU241_INGOT = epicMaterial("pu241_ingot");

    public static final RegistryObject<Item> THORIUM_INGOT = rareMaterial("thorium_ingot");
    public static final RegistryObject<Item> NEPTUNIUM_INGOT = epicMaterial("neptunium_ingot");
    public static final RegistryObject<Item> AMERICIUM_INGOT = epicMaterial("americium_ingot");
    public static final RegistryObject<Item> POLONIUM_INGOT = epicMaterial("polonium_ingot");

    public static final RegistryObject<Item> TITANIUM_INGOT = material("titanium_ingot");
    public static final RegistryObject<Item> COBALT_INGOT = material("cobalt_ingot");
    public static final RegistryObject<Item> TUNGSTEN_INGOT = material("tungsten_ingot");
    public static final RegistryObject<Item> ALUMINIUM_INGOT = material("aluminium_ingot");
    public static final RegistryObject<Item> BERYLLIUM_INGOT = material("beryllium_ingot");
    public static final RegistryObject<Item> ZIRCONIUM_INGOT = material("zirconium_ingot");

    public static final RegistryObject<Item> STEEL_INGOT = material("steel_ingot");
    public static final RegistryObject<Item> ADVANCED_ALLOY_INGOT = rareMaterial("advanced_alloy_ingot");
    public static final RegistryObject<Item> COMBINE_STEEL_INGOT = rareMaterial("combine_steel_ingot");

    public static final RegistryObject<Item> SCHRABIDIUM_INGOT = legendaryMaterial("schrabidium_ingot");
    public static final RegistryObject<Item> AUSTRALIUM_INGOT = legendaryMaterial("australium_ingot");
    public static final RegistryObject<Item> DESH_INGOT = epicMaterial("desh_ingot");
    public static final RegistryObject<Item> STARMETAL_INGOT = epicMaterial("starmetal_ingot");
    public static final RegistryObject<Item> DINEUTRONIUM_INGOT = legendaryMaterial("dineutronium_ingot");
    public static final RegistryObject<Item> TETRANEUTRONIUM_INGOT = legendaryMaterial("tetraneutronium_ingot");
    public static final RegistryObject<Item> EUPHEMIUM_INGOT = legendaryMaterial("euphemium_ingot");

    // ============================================================
    // NUGGETS
    // ============================================================

    public static final RegistryObject<Item> URANIUM_NUGGET = material("uranium_nugget");
    public static final RegistryObject<Item> PLUTONIUM_NUGGET = material("plutonium_nugget");
    public static final RegistryObject<Item> THORIUM_NUGGET = material("thorium_nugget");
    public static final RegistryObject<Item> SCHRABIDIUM_NUGGET = rareMaterial("schrabidium_nugget");
    public static final RegistryObject<Item> DESH_NUGGET = material("desh_nugget");
    public static final RegistryObject<Item> AUSTRALIUM_NUGGET = rareMaterial("australium_nugget");
    public static final RegistryObject<Item> EUPHEMIUM_NUGGET = rareMaterial("euphemium_nugget");

    // ============================================================
    // PLATES
    // ============================================================

    public static final RegistryObject<Item> TITANIUM_PLATE = material("titanium_plate");
    public static final RegistryObject<Item> STEEL_PLATE = material("steel_plate");
    public static final RegistryObject<Item> ALUMINIUM_PLATE = material("aluminium_plate");
    public static final RegistryObject<Item> COBALT_PLATE = material("cobalt_plate");
    public static final RegistryObject<Item> TUNGSTEN_PLATE = material("tungsten_plate");
    public static final RegistryObject<Item> SCHRABIDIUM_PLATE = epicMaterial("schrabidium_plate");
    public static final RegistryObject<Item> DESH_PLATE = rareMaterial("desh_plate");
    public static final RegistryObject<Item> ADVANCED_ALLOY_PLATE = rareMaterial("advanced_alloy_plate");

    // ============================================================
    // BILLETS
    // ============================================================

    public static final RegistryObject<Item> URANIUM_BILLET = rareMaterial("uranium_billet");
    public static final RegistryObject<Item> PLUTONIUM_BILLET = epicMaterial("plutonium_billet");
    public static final RegistryObject<Item> THORIUM_BILLET = rareMaterial("thorium_billet");
    public static final RegistryObject<Item> SCHRABIDIUM_BILLET = legendaryMaterial("schrabidium_billet");
    public static final RegistryObject<Item> AUSTRALIUM_BILLET = legendaryMaterial("australium_billet");
    public static final RegistryObject<Item> DESH_BILLET = epicMaterial("desh_billet");
    public static final RegistryObject<Item> EUPHEMIUM_BILLET = legendaryMaterial("euphemium_billet");

    // ============================================================
    // POWDERS / DUST
    // ============================================================

    public static final RegistryObject<Item> URANIUM_POWDER = material("uranium_powder");
    public static final RegistryObject<Item> PLUTONIUM_POWDER = material("plutonium_powder");
    public static final RegistryObject<Item> THORIUM_POWDER = material("thorium_powder");
    public static final RegistryObject<Item> TITANIUM_POWDER = material("titanium_powder");
    public static final RegistryObject<Item> TUNGSTEN_POWDER = material("tungsten_powder");
    public static final RegistryObject<Item> COBALT_POWDER = material("cobalt_powder");
    public static final RegistryObject<Item> SCHRABIDIUM_POWDER = rareMaterial("schrabidium_powder");
    public static final RegistryObject<Item> DESH_POWDER = rareMaterial("desh_powder");
    public static final RegistryObject<Item> STARMETAL_POWDER = rareMaterial("starmetal_powder");
    public static final RegistryObject<Item> EUPHEMIUM_POWDER = epicMaterial("euphemium_powder");

    public static final RegistryObject<Item> GENERIC_DUST = material("dust");
    public static final RegistryObject<Item> TINY_DUST = material("tiny_dust");

    // ============================================================
    // CRYSTALS
    // ============================================================

    public static final RegistryObject<Item> URANIUM_CRYSTAL = rareMaterial("uranium_crystal");
    public static final RegistryObject<Item> THORIUM_CRYSTAL = rareMaterial("thorium_crystal");
    public static final RegistryObject<Item> PLUTONIUM_CRYSTAL = epicMaterial("plutonium_crystal");
    public static final RegistryObject<Item> TITANIUM_CRYSTAL = material("titanium_crystal");
    public static final RegistryObject<Item> SCHRABIDIUM_CRYSTAL = legendaryMaterial("schrabidium_crystal");
    public static final RegistryObject<Item> STARMETAL_CRYSTAL = epicMaterial("starmetal_crystal");

    // ============================================================
    // GEMS
    // ============================================================

    public static final RegistryObject<Item> SODALITE_GEM = material("sodalite_gem");
    public static final RegistryObject<Item> TANTALIUM_GEM = material("tantalium_gem");
    public static final RegistryObject<Item> VOLCANIC_GEM = rareMaterial("volcanic_gem");
    public static final RegistryObject<Item> RAD_GEM = rareMaterial("rad_gem");
    public static final RegistryObject<Item> ALEXANDRITE_GEM = epicMaterial("alexandrite_gem");
}