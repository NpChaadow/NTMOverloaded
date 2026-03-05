package com.hbm.block.rbmk;

import com.hbm.blockentity.rbmk.RBMKColumnBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * RBMKColumnBlock – 1.20.1 port of RBMKBase (1.7.10).
 *
 * In 1.7.10, each RBMK column was a multiblock: a 1×N column of dummy
 * blocks sharing one TileEntity at the base ("core").  For this port the
 * multiblock system is deferred — every RBMK block is a simple 1×1
 * EntityBlock that owns its own BlockEntity.  Neutron simulation, column-
 * height logic, and the lid screwdriver mechanic will be re-added once
 * TileEntityRBMKBase and its subclasses are ported.
 *
 * All 21 RBMK column variants share this single block class; they differ
 * only by registry name and their BlockEntityType.
 *
 *   Variants: rbmk_rod, rbmk_rod_mod, rbmk_rod_reasim, rbmk_rod_reasim_mod,
 *             rbmk_control, rbmk_control_mod, rbmk_control_auto, rbmk_blank,
 *             rbmk_boiler, rbmk_reflector, rbmk_absorber, rbmk_moderator,
 *             rbmk_outgasser, rbmk_storage, rbmk_cooler, rbmk_heater,
 *             rbmk_console, rbmk_crane_console, rbmk_autoloader, rbmk_loader,
 *             rbmk_steam_inlet
 *
 * Usage example in ModBlocks.java:
 * <pre>
 *   public static final RegistryObject&lt;RBMKColumnBlock&gt; RBMK_ROD =
 *       registerBlock("rbmk_rod",
 *           () -&gt; new RBMKColumnBlock(ModBlockEntities.RBMK_ROD));
 * </pre>
 */
public class RBMKColumnBlock extends Block implements EntityBlock {

    /** Properties matching the original: hardness 3, blast resistance 30. */
    public static final BlockBehaviour.Properties DEFAULT_PROPS =
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(3f, 30f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL);

    // Lazy supplier so registration order doesn't matter
    private final Supplier<BlockEntityType<? extends RBMKColumnBlockEntity>> beTypeSupplier;

    public RBMKColumnBlock(
            Supplier<BlockEntityType<? extends RBMKColumnBlockEntity>> beTypeSupplier) {
        super(DEFAULT_PROPS);
        this.beTypeSupplier = beTypeSupplier;
    }

    // ─── EntityBlock ──────────────────────────────────────────────────────

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return beTypeSupplier.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return (lvl, pos, st, be) -> {
            if (be instanceof RBMKColumnBlockEntity rbmk) {
                rbmk.serverTick(lvl, pos, st);
            }
        };
    }

    // ─── Right-click → open GUI when BE is ready ──────────────────────────

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                  Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof RBMKColumnBlockEntity rbmk && player instanceof ServerPlayer sp) {
            NetworkHooks.openScreen(sp, rbmk, pos);
        }
        return InteractionResult.CONSUME;
    }

    // ─── Drop inventory on break ──────────────────────────────────────────

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity be = level.getBlockEntity(pos);
        if (!level.isClientSide() && be instanceof RBMKColumnBlockEntity rbmk) {
            rbmk.dropContents(level, pos);
        }
        super.playerWillDestroy(level, pos, state, player);
    }
}