package com.hbm.machine;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public abstract class BaseMachineMenu extends AbstractContainerMenu {

    protected final BaseMachineBlockEntity machine;
    protected final ContainerData data;
    protected final int machineSlotCount;
    protected final int machineSlotStart = 0;
    protected final int machineSlotEnd;

    protected final int playerInvStart;
    protected final int playerInvEnd;


    protected BaseMachineMenu(
            MenuType<?> type,
            int id,
            Inventory playerInv,
            BaseMachineBlockEntity machine,
            int machineSlotCount
    ) {
        super(type, id);
        this.machine = machine;
        this.data = new ContainerData() {

            private final int[] values = new int[3];

            @Override
            public int get(int index) {
                return values[index];
            }

            @Override
            public void set(int index, int value) {
                values[index] = value;
            }

            @Override
            public int getCount() {
                return 3;
            }
        };


        this.machineSlotCount = machineSlotCount;
        this.machineSlotEnd = machineSlotStart + machineSlotCount;

        this.playerInvStart = machineSlotEnd;
        this.playerInvEnd = playerInvStart + 36; // 27 inv + 9 hotbar

        addDataSlots(this.data);
        addPlayerInventory(playerInv);
        addPlayerHotbar(playerInv);
    }




    /* ===================== */
    /* 📦 PLAYER INVENTORY  */
    /* ===================== */

    protected void addPlayerInventory(Inventory inv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(
                        inv,
                        col + row * 9 + 9,
                        8 + col * 18,
                        84 + row * 18
                ));
            }
        }
    }

    protected void addPlayerHotbar(Inventory inv) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(
                    inv,
                    col,
                    8 + col * 18,
                    142
            ));
        }
    }

    /* ===================== */
    /* 🔎 ACCESSORS         */
    /* ===================== */

    public BaseMachineBlockEntity getMachine() {
        return machine;
    }

    public ContainerData getData() {
        return data;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }


    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack ret = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot == null || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ret = stack.copy();

        // 🔁 Machine → Player
        if (index < machineSlotEnd) {
            if (!this.moveItemStackTo(
                    stack,
                    playerInvStart,
                    playerInvEnd,
                    true
            )) {
                return ItemStack.EMPTY;
            }
        }
        // 🔁 Player → Machine
        else {
            if (!this.moveItemStackTo(
                    stack,
                    machineSlotStart,
                    machineSlotEnd,
                    false
            )) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return ret;
    }


    @Override
    public void broadcastChanges() {
        super.broadcastChanges();

        if (machine != null) {
            data.set(0, machine.getEnergyStored());
            data.set(1, machine.getMaxEnergyStored());
            data.set(2, machine.getProgress());
        }
    }

}
