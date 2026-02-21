package com.hbm.menu;

import com.hbm.blockentity.TestMachineBlockEntity;
import com.hbm.machine.BaseMachineMenu;
import com.hbm.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;

public class TestMachineMenu extends BaseMachineMenu {

    public TestMachineMenu(
            int id,
            Inventory inv,
            TestMachineBlockEntity machine
    ) {
        super(
                ModMenus.TEST_MACHINE.get(),
                id,
                inv,
                machine,
                2 // machine slot count
        );

        addSlot(new SlotItemHandler(machine.getInventory(), 0, 56, 35));
        addSlot(new SlotItemHandler(machine.getInventory(), 1, 116, 35));
    }

    public TestMachineMenu(
            int id,
            Inventory inv,
            FriendlyByteBuf buf
    ) {
        this(id, inv, getMachineFromBuf(inv, buf));
    }

    /* ===================== */
    /* 🔧 STATIC HELPER      */
    /* ===================== */

    private static TestMachineBlockEntity getMachineFromBuf(
            Inventory inv,
            FriendlyByteBuf buf
    ) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity be = inv.player.level().getBlockEntity(pos);

        if (!(be instanceof TestMachineBlockEntity machine)) {
            throw new IllegalStateException("Expected TestMachineBlockEntity");
        }

        return machine;
    }
}
