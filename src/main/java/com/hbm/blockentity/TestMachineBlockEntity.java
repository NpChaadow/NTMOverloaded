package com.hbm.blockentity;

import com.hbm.machine.BaseMachineBlockEntity;
import com.hbm.menu.TestMachineMenu;
import com.hbm.recipe.TestMachineRecipe;
import com.hbm.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class TestMachineBlockEntity extends BaseMachineBlockEntity {

    public TestMachineBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.TEST_MACHINE.get(),
                pos,
                state,
                100_000, // energy
                1_000,   // IO
                2        // slots
        );
    }

    @Override
    protected int getEnergyPerTick() {
        return 0;
    }

    @Override
    protected int getMaxProgress() {
        TestMachineRecipe recipe =
                getMatchingRecipe();
        return recipe != null ? recipe.getProcessingTime() : 200;
    }

    @Override
    protected void produceOutputs() {

    }

    @Override
    protected String getDisplayNameKey() {
        return "block.hbm.test_machine";
    }

    @Override
    protected AbstractContainerMenu createMenuInternal(
            int id,
            Inventory playerInv
    )
    {
        return new TestMachineMenu(id, playerInv, this);
    }

    @Override
    protected boolean canProcess() {
        TestMachineRecipe recipe = getMatchingRecipe();
        if (recipe == null) return false;

        ItemStack input = inventory.getStackInSlot(0);
        if (input.isEmpty()) return false;

        ItemStack output = inventory.getStackInSlot(1);
        ItemStack result = recipe.getResult();

        return output.isEmpty()
                || (ItemStack.isSameItemSameTags(output, result)
                && output.getCount() + result.getCount() <= output.getMaxStackSize());
    }


    @Override
    protected void process() {
        TestMachineRecipe recipe =
                getMatchingRecipe();

        if (recipe == null) return;

        inventory.extractItem(0, 1, false);

        ItemStack out = inventory.getStackInSlot(1);
        if (out.isEmpty()) {
            inventory.setStackInSlot(1, recipe.getResult());
        } else {
            out.grow(recipe.getResult().getCount());
        }
    }

}
