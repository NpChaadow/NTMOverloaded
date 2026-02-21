package com.hbm.blockentity;

import com.hbm.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class CreativeGeneratorBlockEntity extends BlockEntity {

    private static final int CAPACITY = 1_000_000;
    private static final int IO = 50_000;

    private final EnergyStorage energy = new EnergyStorage(CAPACITY, IO, IO);
    private final LazyOptional<IEnergyStorage> energyCap =
            LazyOptional.of(() -> energy);

    public CreativeGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.CREATIVE_GENERATOR.get(),
                pos,
                state
        );
    }

    public void tickServer() {
        if (level == null || level.isClientSide) return;

        // Always full
        energy.receiveEnergy(IO, false);

        // Push energy to neighbors
        for (Direction dir : Direction.values()) {
            BlockEntity target = level.getBlockEntity(worldPosition.relative(dir));
            if (target == null) continue;

            target.getCapability(
                    ForgeCapabilities.ENERGY,
                    dir.getOpposite()
            ).ifPresent(handler -> {
                int extractable = energy.extractEnergy(IO, true);
                int accepted = handler.receiveEnergy(extractable, false);
                energy.extractEnergy(accepted, false);
            });
        }
    }
    @Override
    public <T> LazyOptional<T> getCapability(
            net.minecraftforge.common.capabilities.Capability<T> cap,
            Direction side
    ) {
        if (cap == ForgeCapabilities.ENERGY) {
            return energyCap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyCap.invalidate();
    }
}
