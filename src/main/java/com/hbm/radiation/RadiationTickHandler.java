package com.hbm.radiation;

import com.hbm.HBMMod;
import com.hbm.item.interfaces.RadioactiveItem;
import com.hbm.network.ModNetwork;
import com.hbm.network.packets.RadiationSyncPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HBMMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RadiationTickHandler {

    private static final int DECAY_INTERVAL  = 20;
    private static final int EFFECT_INTERVAL = 40;
    private static final int SYNC_INTERVAL   = 10; // sync to client every 10 ticks

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.player instanceof ServerPlayer player)) return;
        if (player.level().isClientSide()) return;

        int tick = (int)(player.level().getGameTime() % Long.MAX_VALUE);

        // Step 1: Scan inventory for radioactive items
        float radiationThisTick = collectInventoryRadiation(player);
        if (radiationThisTick > 0f) {
            PlayerRadiationManager.add(player, radiationThisTick);
        }

        // Step 2: Natural decay (once per second)
        if (tick % DECAY_INTERVAL == 0) {
            PlayerRadiationManager.decay(player, 0.001f);
        }

        // Step 3: Apply effects (every 2 seconds)
        if (tick % EFFECT_INTERVAL == 0) {
            applyRadiationEffects(player);
        }

        // Step 4: Sync radiation value to client (every 10 ticks)
        if (tick % SYNC_INTERVAL == 0) {
            float rads = PlayerRadiationManager.get(player);
            ModNetwork.sendToPlayer(player, new RadiationSyncPacket(rads));
        }
    }

    private static float collectInventoryRadiation(ServerPlayer player) {
        float total = 0f;
        Inventory inv = player.getInventory();

        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof RadioactiveItem radioactive) {
                total += radioactive.getRadiationPerTick() * stack.getCount();
            }
        }
        for (ItemStack stack : player.getArmorSlots()) {
            if (!stack.isEmpty() && stack.getItem() instanceof RadioactiveItem radioactive) {
                total += radioactive.getRadiationPerTick() * stack.getCount();
            }
        }
        ItemStack offhand = player.getOffhandItem();
        if (!offhand.isEmpty() && offhand.getItem() instanceof RadioactiveItem radioactive) {
            total += radioactive.getRadiationPerTick() * offhand.getCount();
        }

        return total;
    }

    private static void applyRadiationEffects(ServerPlayer player) {
        float rads = PlayerRadiationManager.get(player);

        if (rads >= PlayerRadiationManager.THRESHOLD_LETHAL) {
            player.hurt(player.damageSources().magic(), 4.0f);
            applyEffect(player, MobEffects.WITHER, 60, 1);
            applyEffect(player, MobEffects.WEAKNESS, 60, 2);
            applyEffect(player, MobEffects.MOVEMENT_SLOWDOWN, 60, 2);

        } else if (rads >= PlayerRadiationManager.THRESHOLD_HIGH) {
            player.hurt(player.damageSources().magic(), 1.0f);
            applyEffect(player, MobEffects.CONFUSION, 100, 1);
            applyEffect(player, MobEffects.WEAKNESS, 60, 1);
            applyEffect(player, MobEffects.MOVEMENT_SLOWDOWN, 60, 1);
            applyEffect(player, MobEffects.HUNGER, 60, 1);

        } else if (rads >= PlayerRadiationManager.THRESHOLD_MODERATE) {
            applyEffect(player, MobEffects.CONFUSION, 100, 0);
            applyEffect(player, MobEffects.WEAKNESS, 60, 0);
            applyEffect(player, MobEffects.HUNGER, 60, 0);

        } else if (rads >= PlayerRadiationManager.THRESHOLD_LOW) {
            applyEffect(player, MobEffects.CONFUSION, 80, 0);
        }
    }

    private static void applyEffect(ServerPlayer player,
                                    net.minecraft.world.effect.MobEffect effect,
                                    int durationTicks, int amplifier) {
        player.addEffect(new MobEffectInstance(effect, durationTicks, amplifier, false, true, true));
    }
}