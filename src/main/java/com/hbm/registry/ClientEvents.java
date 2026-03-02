package com.hbm.registry;

import com.hbm.blockentity.PipeBlockEntity;
import com.hbm.renderer.ObjGroupModelLoader;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "hbm", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onRegisterGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
        System.out.println("[ObjGroupModelLoader] Registering loader!");
        event.register("obj_loader", ObjGroupModelLoader.INSTANCE);

    }

    @SubscribeEvent
    public static void onRegisterBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(
                (state, level, pos, tintIndex) -> {
                    if (tintIndex == 0 && level != null && pos != null) {
                        BlockEntity be = level.getBlockEntity(pos);
                        if (be instanceof PipeBlockEntity pipe) {
                            return pipe.getFluidColor();
                        }
                    }
                    return 0xFFFFFF; // white = no tint when no BE or tintIndex != 0
                },
                ModBlocks.PIPE.get()
        );
    }

}