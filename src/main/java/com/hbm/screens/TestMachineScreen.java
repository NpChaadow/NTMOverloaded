package com.hbm.screens;

import com.hbm.menu.TestMachineMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.resources.ResourceLocation;

public class TestMachineScreen extends AbstractContainerScreen<TestMachineMenu> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hbm","textures/gui/gui_press.png");

    public TestMachineScreen(TestMachineMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        imageWidth = 176;
        imageHeight = 166;
    }

    @Override
    protected void renderBg(net.minecraft.client.gui.GuiGraphics gfx, float partialTicks, int x, int y) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        gfx.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);



        int energy = menu.getData().get(0);;
        int max = menu.getData().get(1);

        int barHeight = (int) (15f * energy / max);

        gfx.blit(
                TEXTURE,
                79,
                35 + (15 - barHeight),
                193,
                15 - barHeight,
                15,
                barHeight
        );

    }
}
