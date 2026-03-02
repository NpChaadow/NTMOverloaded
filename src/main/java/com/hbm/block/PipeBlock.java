// ═══════════════════════════════════════════════════════════════════════════
// CableBlock.java  —  Example block with 6 directional Boolean properties
// ═══════════════════════════════════════════════════════════════════════════
package com.hbm.block;

import com.hbm.blockentity.PipeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class PipeBlock extends Block implements EntityBlock {

    // Six directional connection flags — matched by name in group_conditions JSON
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST  = BooleanProperty.create("east");
    public static final BooleanProperty WEST  = BooleanProperty.create("west");
    public static final BooleanProperty UP    = BooleanProperty.create("up");
    public static final BooleanProperty DOWN  = BooleanProperty.create("down");
    private static final VoxelShape CORE = Block.box(5, 5, 5, 11, 11, 11); // adjust to taste

    public PipeBlock(Properties props) {
        super(props);
        // Default: disconnected in all directions
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, false).setValue(SOUTH, false)
                .setValue(EAST,  false).setValue(WEST,  false)
                .setValue(UP,    false).setValue(DOWN,  false));
    }

    private static final Map<BooleanProperty, Direction> PROP_TO_DIR = Map.of(
            NORTH, Direction.NORTH,
            SOUTH, Direction.SOUTH,
            EAST,  Direction.EAST,
            WEST,  Direction.WEST,
            UP,    Direction.UP,
            DOWN,  Direction.DOWN
    );

    private static final Map<Direction, VoxelShape> ARM_SHAPES = Map.of(
            Direction.NORTH, Block.box(5, 5, 0,  11, 11, 5),
            Direction.SOUTH, Block.box(5, 5, 11, 11, 11, 16),
            Direction.EAST,  Block.box(11, 5, 5, 16, 11, 11),
            Direction.WEST,  Block.box(0,  5, 5, 5,  11, 11),
            Direction.UP,    Block.box(5, 11, 5, 11, 16, 11),
            Direction.DOWN,  Block.box(5, 0,  5, 11, 5,  11)
    );

    private static final Map<BlockState, VoxelShape> SHAPE_CACHE = new HashMap<>();

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_CACHE.computeIfAbsent(state, this::computeShape);
    }

    private VoxelShape computeShape(BlockState state) {
        VoxelShape shape = CORE;
        for (var entry : PROP_TO_DIR.entrySet()) {
            if (state.getValue(entry.getKey())) {
                shape = Shapes.or(shape, ARM_SHAPES.get(entry.getValue()));
            }
        }
        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return computeState(super.getStateForPlacement(ctx), ctx.getLevel(), ctx.getClickedPos());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir,
                                  BlockState neighborState, LevelAccessor level,
                                  BlockPos pos, BlockPos neighborPos) {
        return computeState(state, level, pos);
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        return false; // never cull neighbour faces
    }

    /**
     * Recalculate all six connections.
     * Replace the canConnect() check with your own logic
     * (e.g., check for cable tile entities, capability sides, etc.)
     */
    private BlockState computeState(BlockState state, BlockGetter level, BlockPos pos) {
        return state
                .setValue(NORTH, canConnect(level, pos, Direction.NORTH))
                .setValue(SOUTH, canConnect(level, pos, Direction.SOUTH))
                .setValue(EAST,  canConnect(level, pos, Direction.EAST))
                .setValue(WEST,  canConnect(level, pos, Direction.WEST))
                .setValue(UP,    canConnect(level, pos, Direction.UP))
                .setValue(DOWN,  canConnect(level, pos, Direction.DOWN));
    }

    private boolean canConnect(BlockGetter level, BlockPos pos, Direction dir) {
        BlockState neighbor = level.getBlockState(pos.relative(dir));
        // Example: connect to other cables or solid blocks. Customize as needed.
        return neighbor.getBlock() instanceof CableBlock
                || neighbor.isFaceSturdy(level, pos.relative(dir), dir.getOpposite());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PipeBlockEntity(pos, state);
    }
}


// ═══════════════════════════════════════════════════════════════════════════
// assets/yourmodid/models/block/cable.json  (the model JSON)
// ═══════════════════════════════════════════════════════════════════════════
/*
{
  "loader": "yourmodid:obj_group_loader",
  "model":  "yourmodid:models/block/cable.obj",
  "texture": "yourmodid:block/cable",

  "always_visible": ["core"],

  "group_conditions": {
    "north": { "true": "arm_north" },
    "south": { "true": "arm_south" },
    "east":  { "true": "arm_east"  },
    "west":  { "true": "arm_west"  },
    "up":    { "true": "arm_up"    },
    "down":  { "true": "arm_down"  }
  }
}
*/


// ═══════════════════════════════════════════════════════════════════════════
// assets/yourmodid/blockstates/cable.json
// — Minecraft needs a blockstates file, but since our loader handles everything
//   dynamically, we just point all variants to the same model.
// ═══════════════════════════════════════════════════════════════════════════
/*
{
  "variants": {
    "": { "model": "yourmodid:block/cable" }
  }
}
*/


// ═══════════════════════════════════════════════════════════════════════════
// cable.obj  —  Recommended group layout
//
// Model space: 0.0 to 1.0 = one full block.
// Keep the core centred at (0.5, 0.5, 0.5).
// Arms extend outward from the core face to the block edge (1.0 or 0.0).
//
// Blender tip: model at 1m scale, then export with these settings:
//   Forward: -Z, Up: Y, Apply Modifiers: ON, Triangulate: OFF
// ═══════════════════════════════════════════════════════════════════════════
/*
# Cable OBJ — one group per connection arm + a core group

g core
# ... vertices for the centre cube (e.g. 0.375 to 0.625 on all axes)
v 0.375 0.375 0.375
v 0.625 0.375 0.375
... (full cube verts, UVs, normals, faces)

g arm_north
# ... vertices for the north arm (Z from 0.0 to 0.375, XY centred)
v 0.375 0.375 0.0
v 0.625 0.375 0.0
v 0.625 0.625 0.0
v 0.375 0.625 0.0
... (tube going towards Z=0)

g arm_south
# ... (tube going towards Z=1)

g arm_east
# ... (tube going towards X=1)

g arm_west
# ... (tube going towards X=0)

g arm_up
# ... (tube going towards Y=1)

g arm_down
# ... (tube going towards Y=0)
*/