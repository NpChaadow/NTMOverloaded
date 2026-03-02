package com.hbm.item.interfaces;

/**
 * Implement this on any Item to make it emit radiation to the holder.
 *
 * ── USAGE ─────────────────────────────────────────────────────────────────
 *
 *   public class UraniumIngotItem extends Item implements RadioactiveItem {
 *       @Override
 *       public float getRadiationPerTick() {
 *           return 0.002f; // rads per tick while in inventory
 *       }
 *   }
 *
 * ── RADIATION SCALE GUIDE ─────────────────────────────────────────────────
 *   0.0001f  — very low,  safe to carry briefly
 *   0.001f   — low,       safe with protection
 *   0.01f    — moderate,  dangerous over time
 *   0.1f     — high,      quickly lethal
 *   1.0f     — extreme,   instantly lethal
 */
public interface RadioactiveItem {

    /**
     * Returns how many rads this item adds to the holder per tick.
     * Called once per tick per stack found anywhere in the player's inventory.
     * Multiply by stack size inside if you want stacking to matter.
     */
    float getRadiationPerTick();
}