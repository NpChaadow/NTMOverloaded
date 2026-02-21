package com.hbm.registry;

import com.hbm.HBMMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB
                    , HBMMod.MODID);

    // =========================
    // 🧱 BLOCKS
    // =========================
    public static final RegistryObject<CreativeModeTab> BLOCKS_TAB =
            TABS.register("blocks", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.hbm.blocks"))
                    .icon(() -> new ItemStack(ModBlocks.TEST_BLOCK.get()))
                    .displayItems((params, output) -> {
                        ModBlocks.BLOCKS.getEntries()
                                .forEach(block -> output.accept(block.get()));
                    })
                    .build());

    // =========================
    // ⚙ MACHINES
    // =========================
    public static final RegistryObject<CreativeModeTab> MACHINES_TAB =
            TABS.register("machines", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.hbm.machines"))
                    .icon(() -> new ItemStack(ModBlocks.MACHINE_CASING.get()))
                    .displayItems((params, output) -> {
                        // Later: only machine blocks
                        ModBlocks.BLOCKS.getEntries()
                                .forEach(block -> output.accept(block.get()));
                    })
                    .build());

    // =========================
    // 🔩 PARTS
    // =========================
    public static final RegistryObject<CreativeModeTab> PARTS_TAB =
            TABS.register("parts", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.hbm.parts"))
                    .icon(() -> new ItemStack(ModItems.SCHRABIDIUM_INGOT.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.SCHRABIDIUM_INGOT.get());
                        output.accept(ModItems.TITANIUM_INGOT.get());
                        output.accept(ModItems.URANIUM_INGOT.get());
                    })
                    .build());

    // =========================
    // 🔫 WEAPONS
    // =========================
    public static final RegistryObject<CreativeModeTab> WEAPONS_TAB =
            TABS.register("weapons", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.hbm.weapons"))
                    .icon(() -> new ItemStack(ModItems.TEST_ITEM.get()))
                    .displayItems((params, output) -> {
                        // Later: guns, bombs, launchers
                        output.accept(ModItems.TEST_ITEM.get());
                    })
                    .build());

    // =========================
    // 🚀 MISSILES
    // =========================
    public static final RegistryObject<CreativeModeTab> MISSILES_TAB =
            TABS.register("missiles", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.hbm.missiles"))
                    .icon(() -> new ItemStack(ModItems.TEST_ITEM.get()))
                    .displayItems((params, output) -> {
                        // Placeholder for missile items
                        output.accept(ModItems.TEST_ITEM.get());
                    })
                    .build());

    // =========================
    // ☢ NUKES
    // =========================
    public static final RegistryObject<CreativeModeTab> NUKES_TAB =
            TABS.register("nukes", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.hbm.nukes"))
                    .icon(() -> new ItemStack(ModItems.URANIUM_INGOT.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.URANIUM_INGOT.get());
                    })
                    .build());

    // =========================
    // 🍖 CONSUMABLES
    // =========================
    public static final RegistryObject<CreativeModeTab> CONSUMABLES_TAB =
            TABS.register("consumables", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.hbm.consumables"))
                    .icon(() -> new ItemStack(ModItems.CANNED_FOOD.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.CANNED_FOOD.get());
                    })
                    .build());

    // =========================
    // 🎛 CONTROL / LOGIC
    // =========================
    public static final RegistryObject<CreativeModeTab> CONTROL_TAB =
            TABS.register("control", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.hbm.control"))
                    .icon(() -> new ItemStack(ModBlocks.TEST_BLOCK.get()))
                    .displayItems((params, output) -> {
                        // Redstone, logic, controllers later
                        ModBlocks.BLOCKS.getEntries()
                                .forEach(block -> output.accept(block.get()));
                    })
                    .build());

    // =========================
    // 🧪 TEMPLATES / DEBUG
    // =========================
    public static final RegistryObject<CreativeModeTab> TEMPLATE_TAB =
            TABS.register("template", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.hbm.template"))
                    .icon(() -> new ItemStack(ModItems.TEST_ITEM.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.TEST_ITEM.get());
                    })
                    .build());
}
