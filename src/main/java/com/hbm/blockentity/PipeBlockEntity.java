package com.hbm.blockentity;

import com.hbm.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class PipeBlockEntity extends BlockEntity {

    // Stored as 0xRRGGBB — default white means no visible tint on the overlay
    private int fluidColor = 0xFFFFFF;

    // Optional: track whether the pipe is actually carrying fluid
    private boolean hasFluid = false;

    public PipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PIPE.get(), pos, state);
    }

    // ── Color API ─────────────────────────────────────────────────────────────

    /**
     * Returns the fluid color for the overlay tint.
     * Called by the BlockColor handler registered in ClientEvents.
     *
     * Returns 0xFFFFFF (white = no tint) when empty,
     * or the stored fluid color when carrying fluid.
     */
    public int getFluidColor() {
        return hasFluid ? fluidColor : 0xFFFFFF;
    }

    /**
     * Set the fluid this pipe is carrying and sync to clients.
     * Call this from your fluid network logic whenever the pipe's contents change.
     *
     * @param color  0xRRGGBB fluid color (e.g. 0x3F76E4 for water, 0xFF6600 for lava)
     * @param hasFluid true if the pipe is carrying fluid
     */
    public void setFluid(int color, boolean hasFluid) {
        this.fluidColor = color;
        this.hasFluid   = hasFluid;
        setChanged();
        if (level != null && !level.isClientSide()) {
            // Sync to clients so the overlay color updates in-world
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    // ── NBT serialization ─────────────────────────────────────────────────────

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("fluid_color", fluidColor);
        tag.putBoolean("has_fluid", hasFluid);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        fluidColor = tag.getInt("fluid_color");
        hasFluid   = tag.getBoolean("has_fluid");
    }

    // ── Client sync ───────────────────────────────────────────────────────────

    @Override
    public CompoundTag getUpdateTag() {
        // Called when the chunk is first sent to the client
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        // Called by sendBlockUpdated() to push changes to nearby clients
        return ClientboundBlockEntityDataPacket.create(this);
    }
}