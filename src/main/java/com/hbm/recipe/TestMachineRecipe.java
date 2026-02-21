package com.hbm.recipe;

import com.hbm.registry.ModRecipes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class TestMachineRecipe implements Recipe<net.minecraft.world.Container>, MachineRecipe {

    private final ResourceLocation id;
    private final ItemStack input;
    private final ItemStack output;
    private final int energy;
    private final int time;

    public TestMachineRecipe(
            ResourceLocation id,
            ItemStack input,
            ItemStack output,
            int energy,
            int time
    ) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.energy = energy;
        this.time = time;
    }

    @Override
    public boolean matches(ItemStack stack, Level level) {
        return ItemStack.isSameItemSameTags(stack, input);
    }

    @Override
    public ItemStack getResult() {
        return output.copy();
    }

    @Override
    public int getEnergyCost() {
        return energy;
    }

    @Override
    public int getProcessingTime() {
        return time;
    }

    /* ===== Vanilla Recipe ===== */

    @Override
    public boolean matches(net.minecraft.world.Container container, Level level) {
        return matches(container.getItem(0), level);
    }

    @Override
    public ItemStack assemble(Container pContainer, RegistryAccess pRegistryAccess) {
        return null;
    }

    @Override
    public ItemStack assemble(net.minecraft.world.Container container) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return null;
    }

    @Override
    public ItemStack getResultItem() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.TEST_MACHINE_TYPE.get();
    }

    @Override
    public net.minecraft.world.item.crafting.RecipeSerializer<?> getSerializer() {
        return ModRecipes.TEST_MACHINE_SERIALIZER.get();
    }
}
