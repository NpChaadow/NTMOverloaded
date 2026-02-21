package com.hbm.radiation;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;

public class RadiationSavedData extends SavedData {

    private final Map<Long, Float> radiationMap = new HashMap<>();

    /* ===================== */
    /* 📦 LOAD / SAVE        */
    /* ===================== */

    public static RadiationSavedData load(CompoundTag tag) {
        RadiationSavedData data = new RadiationSavedData();

        CompoundTag map = tag.getCompound("radiation");
        for (String key : map.getAllKeys()) {
            data.radiationMap.put(Long.parseLong(key), map.getFloat(key));
        }

        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        CompoundTag map = new CompoundTag();

        for (Map.Entry<Long, Float> entry : radiationMap.entrySet()) {
            map.putFloat(Long.toString(entry.getKey()), entry.getValue());
        }

        tag.put("radiation", map);
        return tag;
    }

    /* ===================== */
    /* ☢️ INTERNAL API       */
    /* ===================== */

    float get(long chunkKey) {
        return radiationMap.getOrDefault(chunkKey, 0f);
    }

    void set(long chunkKey, float value) {
        if (value <= 0f) {
            radiationMap.remove(chunkKey);
        } else {
            radiationMap.put(chunkKey, value);
        }
        setDirty();
    }

    void add(long chunkKey, float delta) {
        set(chunkKey, get(chunkKey) + delta);
    }

    /* ===================== */
    /* 🌍 ACCESS             */
    /* ===================== */

    static RadiationSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                RadiationSavedData::load,
                RadiationSavedData::new,
                "hbm_radiation"
        );
    }
}
