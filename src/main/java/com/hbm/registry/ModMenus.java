package com.hbm.registry;

import com.hbm.HBMMod;
import com.hbm.menu.TestMachineMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.network.IContainerFactory;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, HBMMod.MODID);

    public static final RegistryObject<MenuType<TestMachineMenu>> TEST_MACHINE =
            MENUS.register("test_machine",
                    () -> new MenuType<>(
                            (IContainerFactory<TestMachineMenu>) TestMachineMenu::new, FeatureFlags.DEFAULT_FLAGS));
}
