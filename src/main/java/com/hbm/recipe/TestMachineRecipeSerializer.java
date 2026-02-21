package com.hbm.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

public class TestMachineRecipeSerializer implements RecipeSerializer<TestMachineRecipe> {

    @Override
    public TestMachineRecipe fromJson(ResourceLocation id, JsonObject json) {
        ItemStack input = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "input"));
        ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
        int energy = GsonHelper.getAsInt(json, "energy");
        int time = GsonHelper.getAsInt(json, "time");

        return new TestMachineRecipe(id, input, output, energy, time);
    }

    @Override
    public @Nullable TestMachineRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        ItemStack input = buf.readItem();
        ItemStack output = buf.readItem();
        int energy = buf.readInt();
        int time = buf.readInt();

        return new TestMachineRecipe(id, input, output, energy, time);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, TestMachineRecipe recipe) {
        buf.writeItem(recipe.getResult());
        buf.writeItem(recipe.getResult());
        buf.writeInt(recipe.getEnergyCost());
        buf.writeInt(recipe.getProcessingTime());
    }
}
