package com.hbm.radiation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Stores the last radiation value received from the server.
 * Only exists on the client — never reference this on the server side.
 */
@OnlyIn(Dist.CLIENT)
public class ClientRadiationData {

    private static float radiation = 0f;

    /** Called by RadiationSyncPacket when a new value arrives from the server. */
    public static void setRadiation(float value) {
        radiation = value;
    }

    /** Used by GeigerCounterOverlay to read the current radiation for HUD display. */
    public static float getRadiation() {
        return radiation;
    }
}