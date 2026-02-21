package com.hbm.registry;

import com.hbm.renderer.ObjGroupModelLoader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "hbm", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onRegisterGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
        System.out.println("[ObjGroupModelLoader] Registering loader!");
        event.register("obj_loader", ObjGroupModelLoader.INSTANCE);
    }
}