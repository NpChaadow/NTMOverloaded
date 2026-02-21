package com.hbm.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface MachineRecipe {

    boolean matches(ItemStack input, Level level);

    ItemStack getResult();

    int getEnergyCost();

    int getProcessingTime();

    ItemStack assemble(net.minecraft.world.Container container);

    ItemStack getResultItem();
}
