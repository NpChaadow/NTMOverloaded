package com.hbm.network.packets;

import com.hbm.radiation.ClientRadiationData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Sent from server → client whenever a player's radiation value changes.
 * Stored client-side in ClientRadiationData for HUD rendering.
 */
public class RadiationSyncPacket {

    private final float radiation;

    public RadiationSyncPacket(float radiation) {
        this.radiation = radiation;
    }

    // ── Encode / Decode ───────────────────────────────────────────────────

    public static void encode(RadiationSyncPacket packet, FriendlyByteBuf buf) {
        buf.writeFloat(packet.radiation);
    }

    public static RadiationSyncPacket decode(FriendlyByteBuf buf) {
        return new RadiationSyncPacket(buf.readFloat());
    }

    // ── Handler ───────────────────────────────────────────────────────────

    public static void handle(RadiationSyncPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // This runs on the CLIENT main thread
            ClientRadiationData.setRadiation(packet.radiation);
        });
        ctx.get().setPacketHandled(true);
    }
}