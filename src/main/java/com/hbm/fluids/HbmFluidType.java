package com.hbm.fluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;

import java.util.function.Consumer;

public class HbmFluidType extends FluidType {

    /** Carrier that bundles colour + textures + FluidType.Properties together,
     *  so FluidEntry can pass everything in one argument. */
    public static class Props {
        final int color;
        final ResourceLocation still;
        final ResourceLocation flowing;
        final FluidType.Properties fluidTypeProps;

        public Props(int color, ResourceLocation still, ResourceLocation flowing,
                     FluidType.Properties fluidTypeProps) {
            this.color      = 0xFF000000 | color;
            this.still      = still;
            this.flowing    = flowing;
            this.fluidTypeProps = fluidTypeProps;
        }
    }

    private final int color;
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;

    public HbmFluidType(Props props) {
        super(props.fluidTypeProps);
        this.color          = props.color;
        this.stillTexture   = props.still;
        this.flowingTexture = props.flowing;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override public ResourceLocation getStillTexture()   { return stillTexture; }
            @Override public ResourceLocation getFlowingTexture() { return flowingTexture; }
            @Override public int getTintColor()                   { return color; }
        });
    }
}