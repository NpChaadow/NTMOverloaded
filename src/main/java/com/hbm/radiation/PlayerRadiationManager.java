package com.hbm.radiation;

import com.hbm.modules.PlayerRadiationSavedData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;

/**
 * Public API for reading and modifying per-player radiation.
 *
 * ── USAGE ─────────────────────────────────────────────────────────────────
 *
 *   // Get a player's current radiation level
 *   float rads = PlayerRadiationManager.get(serverPlayer);
 *
 *   // Add radiation to a player
 *   PlayerRadiationManager.add(serverPlayer, 0.5f);
 *
 *   // Set radiation directly
 *   PlayerRadiationManager.set(serverPlayer, 0f); // clear radiation
 *
 * ── HEALTH EFFECTS ────────────────────────────────────────────────────────
 *   Radiation is accumulated in rads. You can use the threshold constants
 *   below to apply effects. Hook into your existing damage/effect system.
 */
public final class PlayerRadiationManager {

    private PlayerRadiationManager() {}

    // ── Radiation thresholds (rads) ───────────────────────────────────────

    public static final float THRESHOLD_LOW      = 100f;  // minor nausea
    public static final float THRESHOLD_MODERATE = 300f;  // nausea + slowness
    public static final float THRESHOLD_HIGH     = 600f;  // damage over time
    public static final float THRESHOLD_LETHAL   = 1000f; // rapid death

    // ── Query ─────────────────────────────────────────────────────────────

    public static float get(ServerPlayer player) {
        return PlayerRadiationSavedData.get((ServerLevel) player.level())
                .get(player.getUUID());
    }

    // ── Modify ────────────────────────────────────────────────────────────

    public static void add(ServerPlayer player, float amount) {
        PlayerRadiationSavedData.get((ServerLevel) player.level())
                .add(player.getUUID(), amount);
    }

    public static void set(ServerPlayer player, float value) {
        PlayerRadiationSavedData.get((ServerLevel) player.level())
                .set(player.getUUID(), value);
    }

    /**
     * Reduces radiation over time (natural decay).
     * Call this on a slow timer (e.g. every 20 ticks) to let radiation fade.
     *
     * @param decayRate fraction to remove per call, e.g. 0.001f = 0.1% per call
     */
    public static void decay(ServerPlayer player, float decayRate) {
        float current = get(player);
        if (current > 0f) {
            set(player, Math.max(0f, current - current * decayRate));
        }
    }
}