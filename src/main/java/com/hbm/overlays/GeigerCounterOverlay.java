package com.hbm.overlays;

import java.lang.Math;
import com.hbm.HBMMod;
import com.hbm.radiation.ClientRadiationData;
import com.hbm.registry.ModItems;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HBMMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class GeigerCounterOverlay {

    // Path to your overlay texture
    // Place it at: assets/hbm/textures/gui/overlay/geiger_counter.png
    private static final ResourceLocation OVERLAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hbm", "textures/misc/overlay_misc.png");

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        // Only render after the hotbar so we draw on top of vanilla HUD
        if (event.getOverlay() != VanillaGuiOverlay.HOTBAR.type()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        if (mc.options.hideGui) return; // respect F1

        // Check if player has a geiger counter anywhere in their inventory
        if (!hasGeigerCounter(mc.player.getInventory())) return;

        GuiGraphics graphics = event.getGuiGraphics();

        // Draw the overlay fullscreen
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f); // white = no tint, change alpha for transparency

        int screenWidth  = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        int screenX = 20;
        int screenY = screenHeight - 18 - 2;

        graphics.pose().pushPose();
        graphics.pose().scale(1, 1, 1.0f);

        graphics.blit(OVERLAY_TEXTURE,
                (int) (screenX), (int) (screenY),            // x, y on screen
                0, 0,             // u, v in texture
                93,      // width on screen
                18,     // height on screen
                258,      // texture width
                258      // texture height
        );

        float rads = ClientRadiationData.getRadiation();
        String text = String.format("%.1f rad", rads);

        graphics.blit(OVERLAY_TEXTURE,
                (int) (screenX), (int) (screenY),
                0,18,
                (int) (75* Math.min(1,rads/800)),17,
                258,258
        );
        graphics.pose().popPose();
        RenderSystem.disableBlend();
    }

    /**
     * Returns true if the player has a geiger counter anywhere in their inventory
     * (main inventory, hotbar, offhand — not armor).
     */
    private static boolean hasGeigerCounter(Inventory inventory) {
        // Check main inventory + hotbar
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && stack.is(ModItems.GEIGER_COUNTER.get())) {
                return true;
            }
        }
        // Check offhand
        if (inventory.offhand.stream().anyMatch(s -> s.is(ModItems.GEIGER_COUNTER.get()))) {
            return true;
        }
        return false;
    }
}