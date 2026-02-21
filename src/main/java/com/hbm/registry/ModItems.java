package com.hbm.registry;

import com.hbm.HBMMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HBMMod.MODID);

    // =========================
    // 🧪 TEST ITEMS
    // =========================

    public static final RegistryObject<Item> TEST_ITEM =
            ITEMS.register("test_item",
                    () -> new Item(new Item.Properties()));

    // =========================
    // ⚙ MATERIALS (HBM-style examples)
    // =========================

    public static final RegistryObject<Item> SCHRABIDIUM_INGOT =
            ITEMS.register("schrabidium_ingot",
                    () -> new Item(new Item.Properties()
                            .rarity(Rarity.RARE)));

    public static final RegistryObject<Item> TITANIUM_INGOT =
            ITEMS.register("titanium_ingot",
                    () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> URANIUM_INGOT =
            ITEMS.register("uranium_ingot",
                    () -> new Item(new Item.Properties()));

    // =========================
    // ☢ FOOD / CONSUMABLE EXAMPLE
    // =========================

    public static final RegistryObject<Item> CANNED_FOOD =
            ITEMS.register("canned_food",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(6)
                                    .saturationMod(0.6f)
                                    .build())));

}

