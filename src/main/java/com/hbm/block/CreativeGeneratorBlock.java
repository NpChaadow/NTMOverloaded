package com.hbm.block;

import com.hbm.blockentity.CreativeGeneratorBlockEntity;
import com.hbm.machine.BaseMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CreativeGeneratorBlock extends Block implements EntityBlock {

    public CreativeGeneratorBlock(Properties props) {
        super(props);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CreativeGeneratorBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level,
            BlockState state,
            BlockEntityType<T> type
    ) {
        return level.isClientSide ? null : (lvl, pos, st, be) -> {
            if (be instanceof CreativeGeneratorBlockEntity gen) {
                gen.tickServer();
            }
        };
    }
}
