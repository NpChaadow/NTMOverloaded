package com.hbm.radiation;

import com.hbm.modules.IMachineModule;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;

public class RadiationModule implements IMachineModule {

    private final ServerLevel level;
    private final BlockPos pos;
    private final float minRadiation;

    public RadiationModule(ServerLevel level, BlockPos pos, float minRadiation) {
        this.level = level;
        this.pos = pos;
        this.minRadiation = minRadiation;
    }

    @Override
    public boolean canRun() {
        return RadiationManager.get(level, new ChunkPos(pos)) >= minRadiation;
    }

    @Override
    public void consume() {
        RadiationManager.add(level, new ChunkPos(pos), -minRadiation * 0.1f);
    }
}
