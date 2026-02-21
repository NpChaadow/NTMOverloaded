package com.hbm.block;

import com.hbm.blockentity.TestMachineBlockEntity;
import com.hbm.machine.BaseMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TestMachineBlock extends BaseMachineBlock {

    public TestMachineBlock(Properties props) {
        super(props);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TestMachineBlockEntity(pos, state);
    }
}

