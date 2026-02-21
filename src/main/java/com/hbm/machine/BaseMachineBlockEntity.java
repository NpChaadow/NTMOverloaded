package com.hbm.machine;

import com.hbm.modules.IMachineModule;
import com.hbm.recipe.MachineRecipe;
import com.hbm.recipe.TestMachineRecipe;
import com.hbm.registry.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMachineBlockEntity extends BlockEntity implements MenuProvider {

    protected abstract void produceOutputs();

    /* ===================== */
    /* 🔋 ENERGY             */
    /* ===================== */
    /**
     * Translation key for the machine GUI title.
     * Example: "block.hbm.test_machine"
     */
    protected abstract String getDisplayNameKey();

    /**
     * Create the menu for this machine.
     */
    protected abstract AbstractContainerMenu createMenuInternal(
            int id,
            Inventory playerInv
    );

    protected final List<IMachineModule> modules = new ArrayList<>();

    protected boolean requirementsMet() {
        for (IMachineModule module : modules) {
            if (!module.canRun()) return false;
        }
        return true;
    }

    protected void consumeInputs() {
        for (IMachineModule module : modules) {
            module.consume();
        }
    }

    protected final EnergyStorage energy;
    protected LazyOptional<EnergyStorage> energyCap;

    /* ===================== */
    /* 📦 INVENTORY          */
    /* ===================== */

    protected final ItemStackHandler inventory;
    protected LazyOptional<IItemHandler> itemCap;

    protected int progress = 0;


    protected BaseMachineBlockEntity(
            BlockEntityType<?> type,
            BlockPos pos,
            BlockState state,
            int energyCapacity,
            int energyIO,
            int slotCount
    ) {
        super(type, pos, state);

        this.energy = new EnergyStorage(energyCapacity, energyIO, energyIO);
        this.energyCap = LazyOptional.of(() -> energy);

        this.inventory = new ItemStackHandler(slotCount) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
        this.itemCap = LazyOptional.of(() -> inventory);
    }

    /* ===================== */
    /* 🔁 TICKING            */
    /* ===================== */

    public final void tickServer() {

        if (!requirementsMet()) {
            progress = 0;
            return;
        }

        consumeInputs();
        progress++;

        if (progress >= getMaxProgress()) {
            produceOutputs();
            progress = 0;
        }
    }

    /* ===================== */
    /* 🔧 ABSTRACT HOOKS     */
    /* ===================== */

    protected abstract boolean canProcess();
    protected abstract void process();
    protected abstract int getEnergyPerTick();
    protected abstract int getMaxProgress();

    /* ===================== */
    /* 📦 CAPABILITIES       */
    /* ===================== */

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(
            @NotNull Capability<T> cap,
            @Nullable Direction side) {

        if (cap == ForgeCapabilities.ENERGY) {
            return energyCap.cast();
        }
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return itemCap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyCap.invalidate();
        itemCap.invalidate();
    }

    /* ===================== */
    /* 💾 NBT                */
    /* ===================== */

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", inventory.serializeNBT());
        tag.putInt("Energy", energy.getEnergyStored());
        tag.putInt("Progress", progress);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        inventory.deserializeNBT(tag.getCompound("Inventory"));
        energy.receiveEnergy(tag.getInt("Energy"), false);
        progress = tag.getInt("Progress");
    }

    /* ===================== */
    /* 🔎 HELPERS            */
    /* ===================== */

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public int getEnergyStored() {
        return energy.getEnergyStored();
    }

    public int getMaxEnergyStored() {
        return energy.getMaxEnergyStored();
    }

    public int getProgress() {
        return progress;
    }

    protected final ContainerData data = new ContainerData() {

        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> energy.getEnergyStored();
                case 1 -> energy.getMaxEnergyStored();
                case 2 -> progress;
                default -> getCustomData(index);
            };
        }

        @Override
        public void set(int index, int value) {
            // client-side only, usually unused
        }

        @Override
        public int getCount() {
            // 3 base values + machine-specific values
            return 3 + getCustomDataCount();
        }
    };


    /**
     * Override to expose additional machine-specific data.
     * Index starts at 3.
     */
    protected int getCustomData(int index) {
        return 0;
    }

    /**
     * Override to declare how many custom data fields exist.
     */
    protected int getCustomDataCount() {
        return 0;
    }

    public ContainerData getData() {
        return data;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(getDisplayNameKey());
    }

    @Override
    public AbstractContainerMenu createMenu(
            int id,
            Inventory playerInv,
            Player player
    ) {
        return createMenuInternal(id, playerInv);
    }

    public void dropInventory(Level level) {
        if (level.isClientSide) return;

        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                Containers.dropItemStack(
                        level,
                        worldPosition.getX(),
                        worldPosition.getY(),
                        worldPosition.getZ(),
                        stack
                );
            }
        }
    }
    protected TestMachineRecipe getMatchingRecipe() {
        if (level == null || level.isClientSide) return null;

        // 🔒 Registry safety check
        if (!ModRecipes.TEST_MACHINE_TYPE.isPresent()) {
            return null;
        }

        return level.getRecipeManager()
                .getRecipesFor(
                        ModRecipes.TEST_MACHINE_TYPE.get(),
                        new MachineContainer(inventory),
                        level
                )
                .stream()
                .findFirst()
                .orElse(null);
    }


}
