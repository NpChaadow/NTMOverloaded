package com.hbm.registry;

import com.hbm.HBMMod;
import com.hbm.blockentity.CreativeGeneratorBlockEntity;
import com.hbm.blockentity.PipeBlockEntity;
import com.hbm.blockentity.TestMachineBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HBMMod.MODID);

    public static final RegistryObject<BlockEntityType<TestMachineBlockEntity>> TEST_MACHINE =
            BLOCK_ENTITIES.register("test_machine",
                    () -> BlockEntityType.Builder.of(
                            TestMachineBlockEntity::new,
                            ModBlocks.TEST_MACHINE.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<CreativeGeneratorBlockEntity>> CREATIVE_GENERATOR =
            BLOCK_ENTITIES.register("creative_generator",
                    () -> BlockEntityType.Builder.of(
                            CreativeGeneratorBlockEntity::new,
                            ModBlocks.CREATIVE_GENERATOR.get()
                    ).build(null)
            );

    public static final RegistryObject<BlockEntityType<PipeBlockEntity>> PIPE =
            BLOCK_ENTITIES.register("pipe",
                    () -> BlockEntityType.Builder.of(
                            PipeBlockEntity::new,
                            ModBlocks.PIPE.get()
                    ).build(null)
            );


}
