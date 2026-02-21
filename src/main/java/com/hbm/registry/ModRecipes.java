package com.hbm.registry;

import com.hbm.HBMMod;
import com.hbm.recipe.TestMachineRecipe;
import com.hbm.recipe.TestMachineRecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, HBMMod.MODID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, HBMMod.MODID);

    public static final RegistryObject<RecipeSerializer<TestMachineRecipe>> TEST_MACHINE_SERIALIZER =
            SERIALIZERS.register("test_machine", TestMachineRecipeSerializer::new);

    public static final RegistryObject<RecipeType<TestMachineRecipe>> TEST_MACHINE_TYPE =
            TYPES.register("test_machine",
                    () -> new RecipeType<>() {
                        public String toString() {
                            return ResourceLocation.fromNamespaceAndPath(HBMMod.MODID, "test_machine").toString();
                        }
                    });
}
