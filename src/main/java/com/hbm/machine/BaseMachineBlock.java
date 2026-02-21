package com.hbm.machine;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public abstract class BaseMachineBlock extends Block implements EntityBlock {

    protected BaseMachineBlock(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult use(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hit
    ) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);

            if (be instanceof BaseMachineBlockEntity machine
                    && player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {

                NetworkHooks.openScreen(serverPlayer, machine, pos);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }


    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level,
            BlockState state,
            BlockEntityType<T> type
    ) {
        return level.isClientSide ? null : (lvl, pos, st, be) -> {
            if (be instanceof BaseMachineBlockEntity machine) {
                machine.tickServer();
            }
        };
    }


    @Override
    public void playerWillDestroy(
            Level level,
            BlockPos pos,
            BlockState state,
            Player player
    ) {
        BlockEntity be = level.getBlockEntity(pos);

        if (!level.isClientSide && be instanceof BaseMachineBlockEntity machine) {
            machine.dropInventory(level);
        }

        super.playerWillDestroy(level, pos, state, player);
    }


}
