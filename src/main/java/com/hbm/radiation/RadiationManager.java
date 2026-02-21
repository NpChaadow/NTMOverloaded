package com.hbm.radiation;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;

public final class RadiationManager {

    private RadiationManager() {} // no instances

    /* ===================== */
    /* ☢️ QUERY              */
    /* ===================== */

    public static float get(ServerLevel level, ChunkPos pos) {
        return RadiationSavedData.get(level).get(pos.toLong());
    }

    /* ===================== */
    /* ☢️ MODIFY             */
    /* ===================== */

    public static void add(ServerLevel level, ChunkPos pos, float amount) {
        RadiationSavedData.get(level).add(pos.toLong(), amount);
    }

    public static void set(ServerLevel level, ChunkPos pos, float value) {
        RadiationSavedData.get(level).set(pos.toLong(), value);
    }
}
