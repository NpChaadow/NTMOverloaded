package com.hbm.modules;

import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class FluidModule implements IMachineModule {

    private final FluidTank tank;
    private final int amountPerTick;

    public FluidModule(FluidTank tank, int amountPerTick) {
        this.tank = tank;
        this.amountPerTick = amountPerTick;
    }

    @Override
    public boolean canRun() {
        return tank.getFluidAmount() >= amountPerTick;
    }

    @Override
    public void consume() {
        tank.drain(amountPerTick, IFluidHandler.FluidAction.EXECUTE);
    }
}
