package com.hbm.modules;

public class HeatModule implements IMachineModule {

    private int heat;
    private final int minHeat;

    public HeatModule(int minHeat) {
        this.minHeat = minHeat;
    }

    public void addHeat(int amount) {
        heat += amount;
    }

    @Override
    public boolean canRun() {
        return heat >= minHeat;
    }

    @Override
    public void consume() {
        heat -= minHeat / 10; // slow decay
    }
}
