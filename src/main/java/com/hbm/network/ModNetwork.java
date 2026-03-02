package com.hbm.network;

import com.hbm.HBMMod;
import com.hbm.network.packets.RadiationSyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * Central network channel for HBM packets.
 *
 * ── REGISTRATION ─────────────────────────────────────────────────────────
 *   Call ModNetwork.register() once in your main mod constructor:
 *
 *   public HBMMod() {
 *       ModNetwork.register();
 *       ...
 *   }
 */
public class ModNetwork {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(HBMMod.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int id = 0;

    public static void register() {
        CHANNEL.registerMessage(
                id++,
                RadiationSyncPacket.class,
                RadiationSyncPacket::encode,
                RadiationSyncPacket::decode,
                RadiationSyncPacket::handle,
                java.util.Optional.of(NetworkDirection.PLAY_TO_CLIENT)
        );
        // Register more packets here as needed:
        // CHANNEL.registerMessage(id++, MyOtherPacket.class, ...);
    }

    /** Send a packet to a specific player. */
    public static void sendToPlayer(ServerPlayer player, Object packet) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }
}