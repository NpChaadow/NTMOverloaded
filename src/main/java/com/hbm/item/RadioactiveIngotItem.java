package com.hbm.item;

import com.hbm.item.interfaces.RadioactiveItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class RadioactiveIngotItem extends Item implements RadioactiveItem {

    private final float radsPerTick;

    public RadioactiveIngotItem(float radsPerTick, Item.Properties props) {
        super(props);
        this.radsPerTick = radsPerTick;
    }

    @Override
    public float getRadiationPerTick() {
        return radsPerTick;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        float perItem = radsPerTick * 20f; // convert ticks → per second
        float total   = perItem * stack.getCount();
        tooltip.add(Component.literal(
                String.format(ChatFormatting.GREEN + "[Radioactive]")
        ));
        // Line 1 — radiation per item per second
        tooltip.add(Component.literal(
                        String.format("%.4f RAD/s", perItem))
                .withStyle(ChatFormatting.YELLOW));

        // Line 2 — total stack radiation per second (only meaningful if count > 1)
        if (stack.getCount() > 1) {
            tooltip.add(Component.literal(
                            String.format("Stack: %.4f RAD/s", total))
                    .withStyle(ChatFormatting.YELLOW));
        }
    }
}