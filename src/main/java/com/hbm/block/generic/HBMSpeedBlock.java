package com.hbm.block.generic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

/**
 * HBM 1.20.1 - replaces BlockSpeedy (1.7.10).
 * Used for: asphalt, asphalt_light.
 */
public class HBMSpeedBlock extends HBMBlock {

    private final double speedMultiplier;

    public HBMSpeedBlock(BlockBehaviour.Properties props, double speedMultiplier) {
        super(props);
        this.speedMultiplier = speedMultiplier;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity instanceof LivingEntity) {
            entity.setDeltaMovement(
                entity.getDeltaMovement().multiply(speedMultiplier, 1.0, speedMultiplier)
            );
        }
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }
}