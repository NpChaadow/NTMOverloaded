package com.hbm.blockentity.rbmk;

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
 * RBMKColumnBlockEntity – placeholder BlockEntity for all RBMK column blocks.
 *
 * Holds a small item inventory so fuel rods, lid items, etc. survive until
 * the real TileEntityRBMK* subclasses are ported.  Tick logic, neutron flux,
 * heat simulation, and GUI menus are all TODO.
 *
 * Suggested slot counts when subclassing:
 *   rod / rod_mod / rod_reasim variants : 1 slot  (fuel rod)
 *   storage / outgasser                  : 9 slots
 *   autoloader / loader                  : 4 slots  (fuel staging)
 *   everything else                      : 0 slots
 */
public class RBMKColumnBlockEntity extends BlockEntity implements MenuProvider {

    protected final ItemStackHandler inventory;
    private final LazyOptional<IItemHandler> itemCap;

    // ─── Constructors ─────────────────────────────────────────────────────

    public RBMKColumnBlockEntity(BlockEntityType<?> type, BlockPos pos,
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

    /** Convenience: 1-slot default (fits a fuel rod). */
    public RBMKColumnBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        this(type, pos, state, 1);
    }

    /** Two-arg constructor required by BlockEntityType.Builder.of() via ::new. */
    public RBMKColumnBlockEntity(BlockPos pos, BlockState state) {
        this(null, pos, state, 1);
    }

    // ─── Server tick ─────────────────────────────────────────────────────

    /**
     * Called every server tick by {@link com.hbm.block.rbmk.RBMKColumnBlock}.
     * TODO: neutron flux, heat accumulation, meltdown checks.
     */
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        // placeholder – no-op until real RBMK logic is ported
    }

    // ─── Inventory helpers ────────────────────────────────────────────────

    /** Ejects all held items into the world (called on player-break). */
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

    // ─── NBT ─────────────────────────────────────────────────────────────

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

    // ─── Capabilities ─────────────────────────────────────────────────────

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

    // ─── MenuProvider ─────────────────────────────────────────────────────

    @Override
    public Component getDisplayName() {
        // TODO: return Component.translatable(getBlockState().getBlock().getDescriptionId())
        return Component.literal("RBMK Column");
    }

    /**
     * Returns null until a real menu class is ported.
     * NetworkHooks.openScreen will silently do nothing rather than crash.
     */
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inv, Player player) {
        // TODO: return new RBMKColumnMenu(windowId, inv, this);
        return null;
    }
}