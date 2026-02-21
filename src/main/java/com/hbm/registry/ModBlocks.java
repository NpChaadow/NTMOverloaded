package com.hbm.registry;

import com.hbm.HBMMod;
import com.hbm.block.CableBlock;
import com.hbm.block.CreativeGeneratorBlock;
import com.hbm.block.TestMachineBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HBMMod.MODID);

    // 🔹 Helper method: registers a block AND its BlockItem
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier) {
        RegistryObject<T> block = BLOCKS.register(name, blockSupplier);
        ModItems.ITEMS.register(name, () ->
                new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutItem(String name, Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    // =========================
    // 🧱 TEST / STARTER BLOCKS
    // =========================

    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(3.0f, 6.0f)
                    .requiresCorrectToolForDrops()));

    // =========================
    // 🏭 EXAMPLE HBM-STYLE BLOCK
    // (Simple metal machine casing)
    // =========================

    public static final RegistryObject<Block> MACHINE_CASING = registerBlock("machine_casing",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5.0f, 10.0f)
                    .requiresCorrectToolForDrops()
                    .sound(net.minecraft.world.level.block.SoundType.METAL)));

    //Test block Entity

    public static final RegistryObject<Block> TEST_MACHINE =
            registerBlock("test_machine",
                    () -> new TestMachineBlock(
                            BlockBehaviour.Properties.of()
                                    .strength(4.0f)
                                    .requiresCorrectToolForDrops()
                    ));

    //Creative Generator

    public static final RegistryObject<Block> CREATIVE_GENERATOR =
            registerBlock("creative_generator",
                    () -> new CreativeGeneratorBlock(
                            BlockBehaviour.Properties.of()
                                    .strength(-1.0f)
                                    .requiresCorrectToolForDrops()
                    ));
    public static final RegistryObject<Block> CABLE = registerBlock("cable",
            () -> new CableBlock(BlockBehaviour.Properties.of()
                    .strength(1.5f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()

            )
    );

}
