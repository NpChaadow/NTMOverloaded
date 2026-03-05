package com.hbm.block.machine;

import com.hbm.blockentity.machine.HBMStubMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * HBMStubMachineBlock – generic EntityBlock stub for the ~300+ HBM machine
 * blocks that don't yet have a ported BlockEntity / GUI.
 *
 * Using this class (instead of a plain Block) for machine registrations means:
 *   - Every machine has a real BlockEntity from day one, so ModBlockEntities
 *     registration is consistent.
 *   - When the real TE class is ported, only the BlockEntityType supplier
 *     changes — the block registration stays identical.
 *   - Right-click does nothing (createMenu returns null), so no crash.
 *
 * Usage in ModBlocks.java:
 *   public static final RegistryObject<HBMStubMachineBlock> MACHINE_FURNACE_IRON =
 *       registerBlock("machine_furnace_iron",
 *           () -> new HBMStubMachineBlock(
 *               metal(5f, 10f),
 *               ModBlockEntities.MACHINE_FURNACE_IRON));
 */
public class HBMStubMachineBlock extends Block implements EntityBlock {

    private final Supplier<BlockEntityType<? extends HBMStubMachineBlockEntity>> beTypeSupplier;

    public HBMStubMachineBlock(BlockBehaviour.Properties props,
                                Supplier<BlockEntityType<? extends HBMStubMachineBlockEntity>> beTypeSupplier) {
        super(props);
        this.beTypeSupplier = beTypeSupplier;
    }

    // --- EntityBlock ---------------------------------------------------------

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return beTypeSupplier.get().create(pos, state);
    }

    /** No tick until real logic is ported. */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level, BlockState state, BlockEntityType<T> type) {
        return null;
    }

    // --- Interaction ---------------------------------------------------------

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                  Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof HBMStubMachineBlockEntity stub && player instanceof ServerPlayer sp) {
            NetworkHooks.openScreen(sp, stub, pos);
        }
        return InteractionResult.CONSUME;
    }

    // --- Cleanup -------------------------------------------------------------

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity be = level.getBlockEntity(pos);
        if (!level.isClientSide() && be instanceof HBMStubMachineBlockEntity stub) {
            stub.dropContents(level, pos);
        }
        super.playerWillDestroy(level, pos, state, player);
    }
}