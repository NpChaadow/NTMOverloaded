package com.hbm.modules;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Stores per-player radiation levels, persisted across sessions.
 * Saved per-dimension under "hbm_player_radiation".
 *
 * Access via PlayerRadiationSavedData.get(serverLevel).
 */
public class PlayerRadiationSavedData extends SavedData {

    /** Player UUID → accumulated radiation (rads) */
    private final Map<UUID, Float> playerRads = new HashMap<>();

    // ── Load / Save ───────────────────────────────────────────────────────

    public static PlayerRadiationSavedData load(CompoundTag tag) {
        PlayerRadiationSavedData data = new PlayerRadiationSavedData();
        CompoundTag map = tag.getCompound("player_radiation");
        for (String key : map.getAllKeys()) {
            try {
                data.playerRads.put(UUID.fromString(key), map.getFloat(key));
            } catch (IllegalArgumentException ignored) {} // skip malformed keys
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        CompoundTag map = new CompoundTag();
        playerRads.forEach((uuid, rads) -> map.putFloat(uuid.toString(), rads));
        tag.put("player_radiation", map);
        return tag;
    }

    // ── Internal API ──────────────────────────────────────────────────────

    public float get(UUID playerId) {
        return playerRads.getOrDefault(playerId, 0f);
    }

    public void set(UUID playerId, float value) {
        if (value <= 0f) {
            playerRads.remove(playerId);
        } else {
            playerRads.put(playerId, value);
        }
        setDirty();
    }

    public void add(UUID playerId, float delta) {
        set(playerId, get(playerId) + delta);
    }

    // ── Access ────────────────────────────────────────────────────────────

    public static PlayerRadiationSavedData get(ServerLevel level) {
        // Store on the overworld so it persists regardless of which dimension the player is in
        ServerLevel overworld = level.getServer().overworld();
        return overworld.getDataStorage().computeIfAbsent(
                PlayerRadiationSavedData::load,
                PlayerRadiationSavedData::new,
                "hbm_player_radiation"
        );
    }
}