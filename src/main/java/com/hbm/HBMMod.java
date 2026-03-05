package com.hbm;

import com.hbm.fluids.ModFluids;
import com.hbm.network.ModNetwork;
import com.hbm.registry.*;

import com.hbm.world.DataGenerators;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod(HBMMod.MODID)
public class HBMMod {

    public static final String MODID = "hbm";

    public HBMMod(FMLJavaModLoadingContext context) {
        IEventBus modBus = context.getModEventBus();

        // 🔹 Register all Deferred Registers here
        ModFluids.register(modBus);
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modBus);
        ModMenus.MENUS.register(modBus);
        ModCreativeTabs.TABS.register(modBus);
        ModRecipes.TYPES.register(modBus);
        ModRecipes.SERIALIZERS.register(modBus);
        ModNetwork.register();

        // 🔹 Data generators (worldgen, biome modifiers, etc.)
        modBus.addListener(DataGenerators::gatherData);

        // 🔹 Setup events
        modBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Networking, capabilities, etc. will go here later
    }

    /**
     * Client-only setup (renderers, screens, keybinds, etc.)
     */
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}