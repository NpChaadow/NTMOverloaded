package com.hbm.blockentity.machine;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * HBMStubMachineBlockEntity - placeholder BlockEntity for unported machines.
 *
 * Provides a 9-slot inventory + IItemHandler capability so items survive
 * saves and hoppers can interact.  Right-click opens nothing (createMenu
 * returns null).  Items are dropped on player-break via dropContents().
 *
 * When porting a real machine:
 *   1. Subclass this (or replace the type in ModBlockEntities).
 *   2. Implement createMenu() to return the real AbstractContainerMenu.
 *   3. Add server tick logic via the block's getTicker().
 */
public class HBMStubMachineBlockEntity extends BlockEntity implements MenuProvider {

    protected final ItemStackHandler inventory;
    private final LazyOptional<IItemHandler> itemCap;

    public HBMStubMachineBlockEntity(BlockEntityType<?> type, BlockPos pos,
                                     BlockState state, int slots) {
        super(type, pos, state);
        this.inventory = new ItemStackHandler(slots) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
        this.itemCap = LazyOptional.of(() -> inventory);
    }

    /** Default: 9-slot inventory. */
    public HBMStubMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        this(type, pos, state, 9);
    }

    /**
     * Two-arg constructor used by BlockEntityType.Builder.of() via ::new.
     * The type is passed as null here; Forge does not inject it post-build,
     * but getType() is not called on stubs so this is safe for now.
     * Replace with a real typed constructor when porting each machine.
     */
    public HBMStubMachineBlockEntity(BlockPos pos, BlockState state) {
        this(null, pos, state, 9);
    }

    // --- Drop helper (called by block on player-break) ---------------------

    public void dropContents(Level level, BlockPos pos) {
        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                Containers.dropItemStack(level,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        stack);
            }
        }
    }

    // --- NBT ----------------------------------------------------------------

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inventory", inventory.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("inventory")) {
            inventory.deserializeNBT(tag.getCompound("inventory"));
        }
    }

    // --- Capabilities -------------------------------------------------------

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap,
                                             @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) return itemCap.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemCap.invalidate();
    }

    // --- MenuProvider -------------------------------------------------------

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }

    /** Returns null until a real menu is ported. NetworkHooks handles null gracefully. */
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inv, Player player) {
        // TODO: return new RealMachineMenu(windowId, inv, this);
        return null;
    }
}