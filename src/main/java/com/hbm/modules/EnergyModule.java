package com.hbm.modules;

import net.minecraftforge.energy.EnergyStorage;

public class EnergyModule implements IMachineModule {

    private final EnergyStorage energy;
    private final int costPerTick;

    public EnergyModule(EnergyStorage energy, int costPerTick) {
        this.energy = energy;
        this.costPerTick = costPerTick;
    }

    @Override
    public boolean canRun() {
        return energy.getEnergyStored() >= costPerTick;
    }

    @Override
    public void consume() {
        energy.extractEnergy(costPerTick, false);
    }
}
