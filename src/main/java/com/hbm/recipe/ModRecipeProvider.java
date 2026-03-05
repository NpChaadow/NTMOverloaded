package com.hbm.recipe;


import com.hbm.registry.ModBlocks;
import com.hbm.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

/**
 * Data-generated recipes for HBM's Nuclear Tech Mod (1.20.1 port).
 *
 * Covers:
 *  - Smelting / blasting  (193 recipes)
 *  - Block ↔ ingot ↔ nugget compression  (118 recipes)
 *  - Billet ↔ nugget / ingot conversions  (168 recipes)
 *  - Explicit nugget ↔ ingot pairs  (59 recipes)
 *  - Basic tool patterns  (20 recipes)
 *  - Basic armor patterns  (12 recipes)
 *
 * NOTE: Recipes using OreDictionary keys (STEEL.ingot(), ANY_RUBBER.ingot(),
 *       KEY_PLANKS, etc.) are NOT generated here — they require Forge tags.
 *       Add those manually in a TagRecipeProvider or by hand.
 */
public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    private static ResourceLocation loc(String path) {
        return ResourceLocation.fromNamespaceAndPath("hbm", path);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        addSmeltingRecipes(writer);
        addCompressionRecipes(writer);
        addBilletRecipes(writer);
        addNuggetIngotRecipes(writer);
        addToolRecipes(writer);
        addArmorRecipes(writer);
    }

    // ────────────────────────────────────────────────────────────────────
    // Smelting & blasting — ores, powders, crystals → ingots  (193 entries)
    // ────────────────────────────────────────────────────────────────────
    private void addSmeltingRecipes(Consumer<FinishedRecipe> writer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.GLYPHID_MEAT.get()), RecipeCategory.MISC, ModItems.GLYPHID_MEAT_GRILLED.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.GLYPHID_MEAT.get())).save(writer, loc("smelt_glyphid_meat"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_THORIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TH232.get(), 3.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_THORIUM.get())).save(writer, loc("smelt_ore_thorium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_THORIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TH232.get(), 3.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_THORIUM.get())).save(writer, loc("blast_ore_thorium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_URANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 6.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_URANIUM.get())).save(writer, loc("smelt_ore_uranium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_URANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 6.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_URANIUM.get())).save(writer, loc("blast_ore_uranium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_URANIUM_SCORCHED.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 6.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_URANIUM_SCORCHED.get())).save(writer, loc("smelt_ore_uranium_scorched"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_URANIUM_SCORCHED.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 6.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_URANIUM_SCORCHED.get())).save(writer, loc("blast_ore_uranium_scorched"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_NETHER_URANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 12.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_URANIUM.get())).save(writer, loc("smelt_ore_nether_uranium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_NETHER_URANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 12.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_URANIUM.get())).save(writer, loc("blast_ore_nether_uranium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_NETHER_URANIUM_SCORCHED.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 12.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_URANIUM_SCORCHED.get())).save(writer, loc("smelt_ore_nether_uranium_scorched"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_NETHER_URANIUM_SCORCHED.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 12.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_URANIUM_SCORCHED.get())).save(writer, loc("blast_ore_nether_uranium_scorched"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_NETHER_PLUTONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM.get(), 24.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_PLUTONIUM.get())).save(writer, loc("smelt_ore_nether_plutonium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_NETHER_PLUTONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM.get(), 24.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_PLUTONIUM.get())).save(writer, loc("blast_ore_nether_plutonium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_TITANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TITANIUM.get(), 3.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_TITANIUM.get())).save(writer, loc("smelt_ore_titanium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_TITANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TITANIUM.get(), 3.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_TITANIUM.get())).save(writer, loc("blast_ore_titanium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_COPPER.get()), RecipeCategory.MISC, ModItems.INGOT_COPPER.get(), 2.5f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_COPPER.get())).save(writer, loc("smelt_ore_copper"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_COPPER.get()), RecipeCategory.MISC, ModItems.INGOT_COPPER.get(), 2.5f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_COPPER.get())).save(writer, loc("blast_ore_copper"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_TUNGSTEN.get()), RecipeCategory.MISC, ModItems.INGOT_TUNGSTEN.get(), 6.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_TUNGSTEN.get())).save(writer, loc("smelt_ore_tungsten"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_TUNGSTEN.get()), RecipeCategory.MISC, ModItems.INGOT_TUNGSTEN.get(), 6.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_TUNGSTEN.get())).save(writer, loc("blast_ore_tungsten"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_NETHER_TUNGSTEN.get()), RecipeCategory.MISC, ModItems.INGOT_TUNGSTEN.get(), 12.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_TUNGSTEN.get())).save(writer, loc("smelt_ore_nether_tungsten"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_NETHER_TUNGSTEN.get()), RecipeCategory.MISC, ModItems.INGOT_TUNGSTEN.get(), 12.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_TUNGSTEN.get())).save(writer, loc("blast_ore_nether_tungsten"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_LEAD.get()), RecipeCategory.MISC, ModItems.INGOT_LEAD.get(), 3.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_LEAD.get())).save(writer, loc("smelt_ore_lead"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_LEAD.get()), RecipeCategory.MISC, ModItems.INGOT_LEAD.get(), 3.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_LEAD.get())).save(writer, loc("blast_ore_lead"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_BERYLLIUM.get()), RecipeCategory.MISC, ModItems.INGOT_BERYLLIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_BERYLLIUM.get())).save(writer, loc("smelt_ore_beryllium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_BERYLLIUM.get()), RecipeCategory.MISC, ModItems.INGOT_BERYLLIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_BERYLLIUM.get())).save(writer, loc("blast_ore_beryllium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_SCHRABIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get(), 128.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_SCHRABIDIUM.get())).save(writer, loc("smelt_ore_schrabidium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_SCHRABIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get(), 128.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_SCHRABIDIUM.get())).save(writer, loc("blast_ore_schrabidium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_NETHER_SCHRABIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get(), 256.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_SCHRABIDIUM.get())).save(writer, loc("smelt_ore_nether_schrabidium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_NETHER_SCHRABIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get(), 256.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_SCHRABIDIUM.get())).save(writer, loc("blast_ore_nether_schrabidium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_COBALT.get()), RecipeCategory.MISC, ModItems.INGOT_COBALT.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_COBALT.get())).save(writer, loc("smelt_ore_cobalt"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_COBALT.get()), RecipeCategory.MISC, ModItems.INGOT_COBALT.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_COBALT.get())).save(writer, loc("blast_ore_cobalt"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_NETHER_COBALT.get()), RecipeCategory.MISC, ModItems.INGOT_COBALT.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_COBALT.get())).save(writer, loc("smelt_ore_nether_cobalt"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_NETHER_COBALT.get()), RecipeCategory.MISC, ModItems.INGOT_COBALT.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_NETHER_COBALT.get())).save(writer, loc("blast_ore_nether_cobalt"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_GNEISS_IRON.get()), RecipeCategory.MISC, Items.IRON_INGOT, 5.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_IRON.get())).save(writer, loc("smelt_ore_gneiss_iron"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_GNEISS_IRON.get()), RecipeCategory.MISC, Items.IRON_INGOT, 5.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_IRON.get())).save(writer, loc("blast_ore_gneiss_iron"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_GNEISS_GOLD.get()), RecipeCategory.MISC, Items.GOLD_INGOT, 5.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_GOLD.get())).save(writer, loc("smelt_ore_gneiss_gold"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_GNEISS_GOLD.get()), RecipeCategory.MISC, Items.GOLD_INGOT, 5.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_GOLD.get())).save(writer, loc("blast_ore_gneiss_gold"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_GNEISS_URANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 12.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_URANIUM.get())).save(writer, loc("smelt_ore_gneiss_uranium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_GNEISS_URANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 12.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_URANIUM.get())).save(writer, loc("blast_ore_gneiss_uranium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_GNEISS_URANIUM_SCORCHED.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 12.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_URANIUM_SCORCHED.get())).save(writer, loc("smelt_ore_gneiss_uranium_scorched"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_GNEISS_URANIUM_SCORCHED.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 12.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_URANIUM_SCORCHED.get())).save(writer, loc("blast_ore_gneiss_uranium_scorched"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_GNEISS_COPPER.get()), RecipeCategory.MISC, ModItems.INGOT_COPPER.get(), 5f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_COPPER.get())).save(writer, loc("smelt_ore_gneiss_copper"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_GNEISS_COPPER.get()), RecipeCategory.MISC, ModItems.INGOT_COPPER.get(), 5f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_COPPER.get())).save(writer, loc("blast_ore_gneiss_copper"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_GNEISS_LITHIUM.get()), RecipeCategory.MISC, ModItems.LITHIUM.get(), 10f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_LITHIUM.get())).save(writer, loc("smelt_ore_gneiss_lithium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_GNEISS_LITHIUM.get()), RecipeCategory.MISC, ModItems.LITHIUM.get(), 10f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_LITHIUM.get())).save(writer, loc("blast_ore_gneiss_lithium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_GNEISS_SCHRABIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get(), 256.0f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_SCHRABIDIUM.get())).save(writer, loc("smelt_ore_gneiss_schrabidium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_GNEISS_SCHRABIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get(), 256.0f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_GNEISS_SCHRABIDIUM.get())).save(writer, loc("blast_ore_gneiss_schrabidium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ORE_AUSTRALIUM.get()), RecipeCategory.MISC, ModItems.NUGGET_AUSTRALIUM.get(), 2.5f, 200)
                .unlockedBy("has_item", has(ModBlocks.ORE_AUSTRALIUM.get())).save(writer, loc("smelt_ore_australium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ORE_AUSTRALIUM.get()), RecipeCategory.MISC, ModItems.NUGGET_AUSTRALIUM.get(), 2.5f, 100)
                .unlockedBy("has_item", has(ModBlocks.ORE_AUSTRALIUM.get())).save(writer, loc("blast_ore_australium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_AUSTRALIUM.get()), RecipeCategory.MISC, ModItems.INGOT_AUSTRALIUM.get(), 5.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_AUSTRALIUM.get())).save(writer, loc("smelt_powder_australium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_AUSTRALIUM.get()), RecipeCategory.MISC, ModItems.INGOT_AUSTRALIUM.get(), 5.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_AUSTRALIUM.get())).save(writer, loc("blast_powder_australium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_LEAD.get()), RecipeCategory.MISC, ModItems.INGOT_LEAD.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_LEAD.get())).save(writer, loc("smelt_powder_lead"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_LEAD.get()), RecipeCategory.MISC, ModItems.INGOT_LEAD.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_LEAD.get())).save(writer, loc("blast_powder_lead"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_NEPTUNIUM.get()), RecipeCategory.MISC, ModItems.INGOT_NEPTUNIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_NEPTUNIUM.get())).save(writer, loc("smelt_powder_neptunium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_NEPTUNIUM.get()), RecipeCategory.MISC, ModItems.INGOT_NEPTUNIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_NEPTUNIUM.get())).save(writer, loc("blast_powder_neptunium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_POLONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_POLONIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_POLONIUM.get())).save(writer, loc("smelt_powder_polonium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_POLONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_POLONIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_POLONIUM.get())).save(writer, loc("blast_powder_polonium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_SCHRABIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get(), 5.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_SCHRABIDIUM.get())).save(writer, loc("smelt_powder_schrabidium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_SCHRABIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get(), 5.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_SCHRABIDIUM.get())).save(writer, loc("blast_powder_schrabidium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_SCHRABIDATE.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDATE.get(), 5.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_SCHRABIDATE.get())).save(writer, loc("smelt_powder_schrabidate"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_SCHRABIDATE.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDATE.get(), 5.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_SCHRABIDATE.get())).save(writer, loc("blast_powder_schrabidate"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_EUPHEMIUM.get()), RecipeCategory.MISC, ModItems.INGOT_EUPHEMIUM.get(), 10.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_EUPHEMIUM.get())).save(writer, loc("smelt_powder_euphemium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_EUPHEMIUM.get()), RecipeCategory.MISC, ModItems.INGOT_EUPHEMIUM.get(), 10.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_EUPHEMIUM.get())).save(writer, loc("blast_powder_euphemium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_ALUMINIUM.get()), RecipeCategory.MISC, ModItems.INGOT_ALUMINIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_ALUMINIUM.get())).save(writer, loc("smelt_powder_aluminium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_ALUMINIUM.get()), RecipeCategory.MISC, ModItems.INGOT_ALUMINIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_ALUMINIUM.get())).save(writer, loc("blast_powder_aluminium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_BERYLLIUM.get()), RecipeCategory.MISC, ModItems.INGOT_BERYLLIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_BERYLLIUM.get())).save(writer, loc("smelt_powder_beryllium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_BERYLLIUM.get()), RecipeCategory.MISC, ModItems.INGOT_BERYLLIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_BERYLLIUM.get())).save(writer, loc("blast_powder_beryllium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_COPPER.get()), RecipeCategory.MISC, ModItems.INGOT_COPPER.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_COPPER.get())).save(writer, loc("smelt_powder_copper"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_COPPER.get()), RecipeCategory.MISC, ModItems.INGOT_COPPER.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_COPPER.get())).save(writer, loc("blast_powder_copper"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_GOLD.get()), RecipeCategory.MISC, Items.GOLD_INGOT, 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_GOLD.get())).save(writer, loc("smelt_powder_gold"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_GOLD.get()), RecipeCategory.MISC, Items.GOLD_INGOT, 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_GOLD.get())).save(writer, loc("blast_powder_gold"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_IRON.get()), RecipeCategory.MISC, Items.IRON_INGOT, 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_IRON.get())).save(writer, loc("smelt_powder_iron"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_IRON.get()), RecipeCategory.MISC, Items.IRON_INGOT, 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_IRON.get())).save(writer, loc("blast_powder_iron"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_TITANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TITANIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_TITANIUM.get())).save(writer, loc("smelt_powder_titanium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_TITANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TITANIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_TITANIUM.get())).save(writer, loc("blast_powder_titanium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_COBALT.get()), RecipeCategory.MISC, ModItems.INGOT_COBALT.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_COBALT.get())).save(writer, loc("smelt_powder_cobalt"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_COBALT.get()), RecipeCategory.MISC, ModItems.INGOT_COBALT.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_COBALT.get())).save(writer, loc("blast_powder_cobalt"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_TUNGSTEN.get()), RecipeCategory.MISC, ModItems.INGOT_TUNGSTEN.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_TUNGSTEN.get())).save(writer, loc("smelt_powder_tungsten"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_TUNGSTEN.get()), RecipeCategory.MISC, ModItems.INGOT_TUNGSTEN.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_TUNGSTEN.get())).save(writer, loc("blast_powder_tungsten"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_URANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_URANIUM.get())).save(writer, loc("smelt_powder_uranium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_URANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_URANIUM.get())).save(writer, loc("blast_powder_uranium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_THORIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TH232.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_THORIUM.get())).save(writer, loc("smelt_powder_thorium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_THORIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TH232.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_THORIUM.get())).save(writer, loc("blast_powder_thorium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_PLUTONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_PLUTONIUM.get())).save(writer, loc("smelt_powder_plutonium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_PLUTONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_PLUTONIUM.get())).save(writer, loc("blast_powder_plutonium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_ADVANCED_ALLOY.get()), RecipeCategory.MISC, ModItems.INGOT_ADVANCED_ALLOY.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_ADVANCED_ALLOY.get())).save(writer, loc("smelt_powder_advanced_alloy"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_ADVANCED_ALLOY.get()), RecipeCategory.MISC, ModItems.INGOT_ADVANCED_ALLOY.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_ADVANCED_ALLOY.get())).save(writer, loc("blast_powder_advanced_alloy"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_COMBINE_STEEL.get()), RecipeCategory.MISC, ModItems.INGOT_COMBINE_STEEL.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_COMBINE_STEEL.get())).save(writer, loc("smelt_powder_combine_steel"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_COMBINE_STEEL.get()), RecipeCategory.MISC, ModItems.INGOT_COMBINE_STEEL.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_COMBINE_STEEL.get())).save(writer, loc("blast_powder_combine_steel"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_MAGNETIZED_TUNGSTEN.get()), RecipeCategory.MISC, ModItems.INGOT_MAGNETIZED_TUNGSTEN.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_MAGNETIZED_TUNGSTEN.get())).save(writer, loc("smelt_powder_magnetized_tungsten"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_MAGNETIZED_TUNGSTEN.get()), RecipeCategory.MISC, ModItems.INGOT_MAGNETIZED_TUNGSTEN.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_MAGNETIZED_TUNGSTEN.get())).save(writer, loc("blast_powder_magnetized_tungsten"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_RED_COPPER.get()), RecipeCategory.MISC, ModItems.INGOT_RED_COPPER.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_RED_COPPER.get())).save(writer, loc("smelt_powder_red_copper"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_RED_COPPER.get()), RecipeCategory.MISC, ModItems.INGOT_RED_COPPER.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_RED_COPPER.get())).save(writer, loc("blast_powder_red_copper"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_STEEL.get()), RecipeCategory.MISC, ModItems.INGOT_STEEL.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_STEEL.get())).save(writer, loc("smelt_powder_steel"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_STEEL.get()), RecipeCategory.MISC, ModItems.INGOT_STEEL.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_STEEL.get())).save(writer, loc("blast_powder_steel"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_LITHIUM.get()), RecipeCategory.MISC, ModItems.LITHIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_LITHIUM.get())).save(writer, loc("smelt_powder_lithium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_LITHIUM.get()), RecipeCategory.MISC, ModItems.LITHIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_LITHIUM.get())).save(writer, loc("blast_powder_lithium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_DURA_STEEL.get()), RecipeCategory.MISC, ModItems.INGOT_DURA_STEEL.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_DURA_STEEL.get())).save(writer, loc("smelt_powder_dura_steel"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_DURA_STEEL.get()), RecipeCategory.MISC, ModItems.INGOT_DURA_STEEL.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_DURA_STEEL.get())).save(writer, loc("blast_powder_dura_steel"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_POLYMER.get()), RecipeCategory.MISC, ModItems.INGOT_POLYMER.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_POLYMER.get())).save(writer, loc("smelt_powder_polymer"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_POLYMER.get()), RecipeCategory.MISC, ModItems.INGOT_POLYMER.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_POLYMER.get())).save(writer, loc("blast_powder_polymer"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_BAKELITE.get()), RecipeCategory.MISC, ModItems.INGOT_BAKELITE.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_BAKELITE.get())).save(writer, loc("smelt_powder_bakelite"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_BAKELITE.get()), RecipeCategory.MISC, ModItems.INGOT_BAKELITE.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_BAKELITE.get())).save(writer, loc("blast_powder_bakelite"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_LANTHANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_LANTHANIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_LANTHANIUM.get())).save(writer, loc("smelt_powder_lanthanium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_LANTHANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_LANTHANIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_LANTHANIUM.get())).save(writer, loc("blast_powder_lanthanium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_ACTINIUM.get()), RecipeCategory.MISC, ModItems.INGOT_ACTINIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_ACTINIUM.get())).save(writer, loc("smelt_powder_actinium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_ACTINIUM.get()), RecipeCategory.MISC, ModItems.INGOT_ACTINIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_ACTINIUM.get())).save(writer, loc("blast_powder_actinium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_BORON.get()), RecipeCategory.MISC, ModItems.INGOT_BORON.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_BORON.get())).save(writer, loc("smelt_powder_boron"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_BORON.get()), RecipeCategory.MISC, ModItems.INGOT_BORON.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_BORON.get())).save(writer, loc("blast_powder_boron"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_DESH.get()), RecipeCategory.MISC, ModItems.INGOT_DESH.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_DESH.get())).save(writer, loc("smelt_powder_desh"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_DESH.get()), RecipeCategory.MISC, ModItems.INGOT_DESH.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_DESH.get())).save(writer, loc("blast_powder_desh"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_DINEUTRONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_DINEUTRONIUM.get(), 5.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_DINEUTRONIUM.get())).save(writer, loc("smelt_powder_dineutronium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_DINEUTRONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_DINEUTRONIUM.get(), 5.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_DINEUTRONIUM.get())).save(writer, loc("blast_powder_dineutronium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_ASBESTOS.get()), RecipeCategory.MISC, ModItems.INGOT_ASBESTOS.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_ASBESTOS.get())).save(writer, loc("smelt_powder_asbestos"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_ASBESTOS.get()), RecipeCategory.MISC, ModItems.INGOT_ASBESTOS.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_ASBESTOS.get())).save(writer, loc("blast_powder_asbestos"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_ZIRCONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_ZIRCONIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_ZIRCONIUM.get())).save(writer, loc("smelt_powder_zirconium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_ZIRCONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_ZIRCONIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_ZIRCONIUM.get())).save(writer, loc("blast_powder_zirconium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_TCALLOY.get()), RecipeCategory.MISC, ModItems.INGOT_TCALLOY.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_TCALLOY.get())).save(writer, loc("smelt_powder_tcalloy"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_TCALLOY.get()), RecipeCategory.MISC, ModItems.INGOT_TCALLOY.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_TCALLOY.get())).save(writer, loc("blast_powder_tcalloy"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_AU198.get()), RecipeCategory.MISC, ModItems.INGOT_AU198.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_AU198.get())).save(writer, loc("smelt_powder_au198"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_AU198.get()), RecipeCategory.MISC, ModItems.INGOT_AU198.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_AU198.get())).save(writer, loc("blast_powder_au198"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_SR90.get()), RecipeCategory.MISC, ModItems.INGOT_SR90.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_SR90.get())).save(writer, loc("smelt_powder_sr90"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_SR90.get()), RecipeCategory.MISC, ModItems.INGOT_SR90.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_SR90.get())).save(writer, loc("blast_powder_sr90"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_RA226.get()), RecipeCategory.MISC, ModItems.INGOT_RA226.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_RA226.get())).save(writer, loc("smelt_powder_ra226"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_RA226.get()), RecipeCategory.MISC, ModItems.INGOT_RA226.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_RA226.get())).save(writer, loc("blast_powder_ra226"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_TANTALIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TANTALIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_TANTALIUM.get())).save(writer, loc("smelt_powder_tantalium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_TANTALIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TANTALIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_TANTALIUM.get())).save(writer, loc("blast_powder_tantalium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_NIOBIUM.get()), RecipeCategory.MISC, ModItems.INGOT_NIOBIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_NIOBIUM.get())).save(writer, loc("smelt_powder_niobium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_NIOBIUM.get()), RecipeCategory.MISC, ModItems.INGOT_NIOBIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_NIOBIUM.get())).save(writer, loc("blast_powder_niobium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_BISMUTH.get()), RecipeCategory.MISC, ModItems.INGOT_BISMUTH.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_BISMUTH.get())).save(writer, loc("smelt_powder_bismuth"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_BISMUTH.get()), RecipeCategory.MISC, ModItems.INGOT_BISMUTH.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_BISMUTH.get())).save(writer, loc("blast_powder_bismuth"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_CALCIUM.get()), RecipeCategory.MISC, ModItems.INGOT_CALCIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_CALCIUM.get())).save(writer, loc("smelt_powder_calcium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_CALCIUM.get()), RecipeCategory.MISC, ModItems.INGOT_CALCIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_CALCIUM.get())).save(writer, loc("blast_powder_calcium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POWDER_CADMIUM.get()), RecipeCategory.MISC, ModItems.INGOT_CADMIUM.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.POWDER_CADMIUM.get())).save(writer, loc("smelt_powder_cadmium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.POWDER_CADMIUM.get()), RecipeCategory.MISC, ModItems.INGOT_CADMIUM.get(), 1.0f, 100)
                .unlockedBy("has_item", has(ModItems.POWDER_CADMIUM.get())).save(writer, loc("blast_powder_cadmium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.BALL_RESIN.get()), RecipeCategory.MISC, ModItems.INGOT_BIORUBBER.get(), 0.1f, 200)
                .unlockedBy("has_item", has(ModItems.BALL_RESIN.get())).save(writer, loc("smelt_ball_resin"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.COMBINE_SCRAP.get()), RecipeCategory.MISC, ModItems.INGOT_COMBINE_STEEL.get(), 1.0f, 200)
                .unlockedBy("has_item", has(ModItems.COMBINE_SCRAP.get())).save(writer, loc("smelt_combine_scrap"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAG_DAMP.get()), RecipeCategory.MISC, ModItems.RAG.get(), 0.1f, 200)
                .unlockedBy("has_item", has(ModItems.RAG_DAMP.get())).save(writer, loc("smelt_rag_damp"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RAG_PISS.get()), RecipeCategory.MISC, ModItems.RAG.get(), 0.1f, 200)
                .unlockedBy("has_item", has(ModItems.RAG_PISS.get())).save(writer, loc("smelt_rag_piss"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.BALL_FIRECLAY.get()), RecipeCategory.MISC, ModItems.INGOT_FIREBRICK.get(), 0.1f, 200)
                .unlockedBy("has_item", has(ModItems.BALL_FIRECLAY.get())).save(writer, loc("smelt_ball_fireclay"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.INGOT_SCHRARANIUM.get()), RecipeCategory.MISC, ModItems.NUGGET_SCHRABIDIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.INGOT_SCHRARANIUM.get())).save(writer, loc("smelt_ingot_schraranium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.INGOT_SCHRARANIUM.get()), RecipeCategory.MISC, ModItems.NUGGET_SCHRABIDIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.INGOT_SCHRARANIUM.get())).save(writer, loc("blast_ingot_schraranium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.LODESTONE.get()), RecipeCategory.MISC, ModItems.CRYSTAL_IRON.get(), 5.0f, 200)
                .unlockedBy("has_item", has(ModItems.LODESTONE.get())).save(writer, loc("smelt_lodestone"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_IRON.get()), RecipeCategory.MISC, Items.IRON_INGOT, 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_IRON.get())).save(writer, loc("smelt_crystal_iron"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_IRON.get()), RecipeCategory.MISC, Items.IRON_INGOT, 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_IRON.get())).save(writer, loc("blast_crystal_iron"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_GOLD.get()), RecipeCategory.MISC, Items.GOLD_INGOT, 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_GOLD.get())).save(writer, loc("smelt_crystal_gold"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_GOLD.get()), RecipeCategory.MISC, Items.GOLD_INGOT, 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_GOLD.get())).save(writer, loc("blast_crystal_gold"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_DIAMOND.get()), RecipeCategory.MISC, Items.DIAMOND, 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_DIAMOND.get())).save(writer, loc("smelt_crystal_diamond"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_DIAMOND.get()), RecipeCategory.MISC, Items.DIAMOND, 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_DIAMOND.get())).save(writer, loc("blast_crystal_diamond"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_URANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_URANIUM.get())).save(writer, loc("smelt_crystal_uranium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_URANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_URANIUM.get())).save(writer, loc("blast_crystal_uranium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_THORIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TH232.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_THORIUM.get())).save(writer, loc("smelt_crystal_thorium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_THORIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TH232.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_THORIUM.get())).save(writer, loc("blast_crystal_thorium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_PLUTONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_PLUTONIUM.get())).save(writer, loc("smelt_crystal_plutonium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_PLUTONIUM.get()), RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_PLUTONIUM.get())).save(writer, loc("blast_crystal_plutonium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_TITANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TITANIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_TITANIUM.get())).save(writer, loc("smelt_crystal_titanium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_TITANIUM.get()), RecipeCategory.MISC, ModItems.INGOT_TITANIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_TITANIUM.get())).save(writer, loc("blast_crystal_titanium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_SULFUR.get()), RecipeCategory.MISC, ModItems.SULFUR.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_SULFUR.get())).save(writer, loc("smelt_crystal_sulfur"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_SULFUR.get()), RecipeCategory.MISC, ModItems.SULFUR.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_SULFUR.get())).save(writer, loc("blast_crystal_sulfur"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_NITER.get()), RecipeCategory.MISC, ModItems.NITER.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_NITER.get())).save(writer, loc("smelt_crystal_niter"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_NITER.get()), RecipeCategory.MISC, ModItems.NITER.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_NITER.get())).save(writer, loc("blast_crystal_niter"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_COPPER.get()), RecipeCategory.MISC, ModItems.INGOT_COPPER.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_COPPER.get())).save(writer, loc("smelt_crystal_copper"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_COPPER.get()), RecipeCategory.MISC, ModItems.INGOT_COPPER.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_COPPER.get())).save(writer, loc("blast_crystal_copper"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_TUNGSTEN.get()), RecipeCategory.MISC, ModItems.INGOT_TUNGSTEN.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_TUNGSTEN.get())).save(writer, loc("smelt_crystal_tungsten"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_TUNGSTEN.get()), RecipeCategory.MISC, ModItems.INGOT_TUNGSTEN.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_TUNGSTEN.get())).save(writer, loc("blast_crystal_tungsten"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_ALUMINIUM.get()), RecipeCategory.MISC, ModItems.INGOT_ALUMINIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_ALUMINIUM.get())).save(writer, loc("smelt_crystal_aluminium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_ALUMINIUM.get()), RecipeCategory.MISC, ModItems.INGOT_ALUMINIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_ALUMINIUM.get())).save(writer, loc("blast_crystal_aluminium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_FLUORITE.get()), RecipeCategory.MISC, ModItems.FLUORITE.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_FLUORITE.get())).save(writer, loc("smelt_crystal_fluorite"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_FLUORITE.get()), RecipeCategory.MISC, ModItems.FLUORITE.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_FLUORITE.get())).save(writer, loc("blast_crystal_fluorite"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_BERYLLIUM.get()), RecipeCategory.MISC, ModItems.INGOT_BERYLLIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_BERYLLIUM.get())).save(writer, loc("smelt_crystal_beryllium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_BERYLLIUM.get()), RecipeCategory.MISC, ModItems.INGOT_BERYLLIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_BERYLLIUM.get())).save(writer, loc("blast_crystal_beryllium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_LEAD.get()), RecipeCategory.MISC, ModItems.INGOT_LEAD.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_LEAD.get())).save(writer, loc("smelt_crystal_lead"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_LEAD.get()), RecipeCategory.MISC, ModItems.INGOT_LEAD.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_LEAD.get())).save(writer, loc("blast_crystal_lead"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_SCHRARANIUM.get()), RecipeCategory.MISC, ModItems.NUGGET_SCHRABIDIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_SCHRARANIUM.get())).save(writer, loc("smelt_crystal_schraranium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_SCHRARANIUM.get()), RecipeCategory.MISC, ModItems.NUGGET_SCHRABIDIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_SCHRARANIUM.get())).save(writer, loc("blast_crystal_schraranium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_SCHRABIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_SCHRABIDIUM.get())).save(writer, loc("smelt_crystal_schrabidium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_SCHRABIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_SCHRABIDIUM.get())).save(writer, loc("blast_crystal_schrabidium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_RARE.get()), RecipeCategory.MISC, ModItems.POWDER_DESH_MIX.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_RARE.get())).save(writer, loc("smelt_crystal_rare"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_RARE.get()), RecipeCategory.MISC, ModItems.POWDER_DESH_MIX.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_RARE.get())).save(writer, loc("blast_crystal_rare"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_PHOSPHORUS.get()), RecipeCategory.MISC, ModItems.POWDER_FIRE.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_PHOSPHORUS.get())).save(writer, loc("smelt_crystal_phosphorus"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_PHOSPHORUS.get()), RecipeCategory.MISC, ModItems.POWDER_FIRE.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_PHOSPHORUS.get())).save(writer, loc("blast_crystal_phosphorus"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_LITHIUM.get()), RecipeCategory.MISC, ModItems.LITHIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_LITHIUM.get())).save(writer, loc("smelt_crystal_lithium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_LITHIUM.get()), RecipeCategory.MISC, ModItems.LITHIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_LITHIUM.get())).save(writer, loc("blast_crystal_lithium"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_COBALT.get()), RecipeCategory.MISC, ModItems.INGOT_COBALT.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_COBALT.get())).save(writer, loc("smelt_crystal_cobalt"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_COBALT.get()), RecipeCategory.MISC, ModItems.INGOT_COBALT.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_COBALT.get())).save(writer, loc("blast_crystal_cobalt"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_STARMETAL.get()), RecipeCategory.MISC, ModItems.INGOT_STARMETAL.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_STARMETAL.get())).save(writer, loc("smelt_crystal_starmetal"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_STARMETAL.get()), RecipeCategory.MISC, ModItems.INGOT_STARMETAL.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_STARMETAL.get())).save(writer, loc("blast_crystal_starmetal"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_TRIXITE.get()), RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_TRIXITE.get())).save(writer, loc("smelt_crystal_trixite"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_TRIXITE.get()), RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_TRIXITE.get())).save(writer, loc("blast_crystal_trixite"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_CINNEBAR.get()), RecipeCategory.MISC, ModItems.CINNEBAR.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_CINNEBAR.get())).save(writer, loc("smelt_crystal_cinnebar"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_CINNEBAR.get()), RecipeCategory.MISC, ModItems.CINNEBAR.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_CINNEBAR.get())).save(writer, loc("blast_crystal_cinnebar"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CRYSTAL_OSMIRIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_OSMIRIDIUM.get(), 2.0f, 200)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_OSMIRIDIUM.get())).save(writer, loc("smelt_crystal_osmiridium"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItems.CRYSTAL_OSMIRIDIUM.get()), RecipeCategory.MISC, ModItems.INGOT_OSMIRIDIUM.get(), 2.0f, 100)
                .unlockedBy("has_item", has(ModItems.CRYSTAL_OSMIRIDIUM.get())).save(writer, loc("blast_crystal_osmiridium"));
    }

    // ────────────────────────────────────────────────────────────────────
    // Block ↔ ingot / ingot ↔ nugget compression  (118 entries)
    // ────────────────────────────────────────────────────────────────────
    private void addCompressionRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DUST.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.DUST_TINY.get())
                .unlockedBy("has_item", has(ModItems.DUST_TINY.get())).save(writer, loc("compress_dust_tiny_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DUST_TINY.get(), 9)
                .requires(ModItems.DUST.get())
                .unlockedBy("has_item", has(ModItems.DUST.get())).save(writer, loc("decompress_dust"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_COAL.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_COAL_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_COAL_TINY.get())).save(writer, loc("compress_powder_coal_tiny_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.POWDER_COAL_TINY.get(), 9)
                .requires(ModItems.POWDER_COAL.get())
                .unlockedBy("has_item", has(ModItems.POWDER_COAL.get())).save(writer, loc("decompress_powder_coal"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_MERCURY.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_MERCURY.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_MERCURY.get())).save(writer, loc("compress_nugget_mercury_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_MERCURY.get(), 9)
                .requires(ModItems.INGOT_MERCURY.get())
                .unlockedBy("has_item", has(ModItems.INGOT_MERCURY.get())).save(writer, loc("decompress_ingot_mercury"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_ALUMINIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_ALUMINIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_ALUMINIUM.get())).save(writer, loc("compress_ingot_aluminium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_ALUMINIUM.get(), 9)
                .requires(ModBlocks.BLOCK_ALUMINIUM.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_ALUMINIUM.get())).save(writer, loc("decompress_block_aluminium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_GRAPHITE.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_GRAPHITE.get())
                .unlockedBy("has_item", has(ModItems.INGOT_GRAPHITE.get())).save(writer, loc("compress_ingot_graphite_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_GRAPHITE.get(), 9)
                .requires(ModBlocks.BLOCK_GRAPHITE.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_GRAPHITE.get())).save(writer, loc("decompress_block_graphite"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_BORON.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_BORON.get())
                .unlockedBy("has_item", has(ModItems.INGOT_BORON.get())).save(writer, loc("compress_ingot_boron_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_BORON.get(), 9)
                .requires(ModBlocks.BLOCK_BORON.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_BORON.get())).save(writer, loc("decompress_block_boron"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_SCHRARANIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_SCHRARANIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SCHRARANIUM.get())).save(writer, loc("compress_ingot_schraranium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_SCHRARANIUM.get(), 9)
                .requires(ModBlocks.BLOCK_SCHRARANIUM.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_SCHRARANIUM.get())).save(writer, loc("decompress_block_schraranium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_LANTHANIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_LANTHANIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_LANTHANIUM.get())).save(writer, loc("compress_ingot_lanthanium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_LANTHANIUM.get(), 9)
                .requires(ModBlocks.BLOCK_LANTHANIUM.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_LANTHANIUM.get())).save(writer, loc("decompress_block_lanthanium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_RA226.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_RA226.get())
                .unlockedBy("has_item", has(ModItems.INGOT_RA226.get())).save(writer, loc("compress_ingot_ra226_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_RA226.get(), 9)
                .requires(ModBlocks.BLOCK_RA226.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_RA226.get())).save(writer, loc("decompress_block_ra226"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_ACTINIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_ACTINIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_ACTINIUM.get())).save(writer, loc("compress_ingot_actinium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_ACTINIUM.get(), 9)
                .requires(ModBlocks.BLOCK_ACTINIUM.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_ACTINIUM.get())).save(writer, loc("decompress_block_actinium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_SCHRABIDATE.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_SCHRABIDATE.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SCHRABIDATE.get())).save(writer, loc("compress_ingot_schrabidate_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_SCHRABIDATE.get(), 9)
                .requires(ModBlocks.BLOCK_SCHRABIDATE.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_SCHRABIDATE.get())).save(writer, loc("decompress_block_schrabidate"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_COLTAN.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.FRAGMENT_COLTAN.get())
                .unlockedBy("has_item", has(ModItems.FRAGMENT_COLTAN.get())).save(writer, loc("compress_fragment_coltan_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FRAGMENT_COLTAN.get(), 9)
                .requires(ModBlocks.BLOCK_COLTAN.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_COLTAN.get())).save(writer, loc("decompress_block_coltan"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_SMORE.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_SMORE.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SMORE.get())).save(writer, loc("compress_ingot_smore_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_SMORE.get(), 9)
                .requires(ModBlocks.BLOCK_SMORE.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_SMORE.get())).save(writer, loc("decompress_block_smore"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_SEMTEX.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_SEMTEX.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SEMTEX.get())).save(writer, loc("compress_ingot_semtex_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_SEMTEX.get(), 9)
                .requires(ModBlocks.BLOCK_SEMTEX.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_SEMTEX.get())).save(writer, loc("decompress_block_semtex"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_C4.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_C4.get())
                .unlockedBy("has_item", has(ModItems.INGOT_C4.get())).save(writer, loc("compress_ingot_c4_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_C4.get(), 9)
                .requires(ModBlocks.BLOCK_C4.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_C4.get())).save(writer, loc("decompress_block_c4"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_POLYMER.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_POLYMER.get())
                .unlockedBy("has_item", has(ModItems.INGOT_POLYMER.get())).save(writer, loc("compress_ingot_polymer_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_POLYMER.get(), 9)
                .requires(ModBlocks.BLOCK_POLYMER.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_POLYMER.get())).save(writer, loc("decompress_block_polymer"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_BAKELITE.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_BAKELITE.get())
                .unlockedBy("has_item", has(ModItems.INGOT_BAKELITE.get())).save(writer, loc("compress_ingot_bakelite_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_BAKELITE.get(), 9)
                .requires(ModBlocks.BLOCK_BAKELITE.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_BAKELITE.get())).save(writer, loc("decompress_block_bakelite"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_RUBBER.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_RUBBER.get())
                .unlockedBy("has_item", has(ModItems.INGOT_RUBBER.get())).save(writer, loc("compress_ingot_rubber_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_RUBBER.get(), 9)
                .requires(ModBlocks.BLOCK_RUBBER.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_RUBBER.get())).save(writer, loc("decompress_block_rubber"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_CADMIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_CADMIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_CADMIUM.get())).save(writer, loc("compress_ingot_cadmium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_CADMIUM.get(), 9)
                .requires(ModBlocks.BLOCK_CADMIUM.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_CADMIUM.get())).save(writer, loc("decompress_block_cadmium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_TCALLOY.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_TCALLOY.get())
                .unlockedBy("has_item", has(ModItems.INGOT_TCALLOY.get())).save(writer, loc("compress_ingot_tcalloy_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_TCALLOY.get(), 9)
                .requires(ModBlocks.BLOCK_TCALLOY.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_TCALLOY.get())).save(writer, loc("decompress_block_tcalloy"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_CDALLOY.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_CDALLOY.get())
                .unlockedBy("has_item", has(ModItems.INGOT_CDALLOY.get())).save(writer, loc("compress_ingot_cdalloy_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_CDALLOY.get(), 9)
                .requires(ModBlocks.BLOCK_CDALLOY.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_CDALLOY.get())).save(writer, loc("decompress_block_cdalloy"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_SILICON.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_SILICON.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_SILICON.get())).save(writer, loc("compress_nugget_silicon_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_SILICON.get(), 9)
                .requires(ModItems.INGOT_SILICON.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SILICON.get())).save(writer, loc("decompress_ingot_silicon"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_BORON.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_BORON_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_BORON_TINY.get())).save(writer, loc("compress_powder_boron_tiny_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.POWDER_BORON_TINY.get(), 9)
                .requires(ModItems.POWDER_BORON.get())
                .unlockedBy("has_item", has(ModItems.POWDER_BORON.get())).save(writer, loc("decompress_powder_boron"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_SR90.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_SR90_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_SR90_TINY.get())).save(writer, loc("compress_powder_sr90_tiny_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.POWDER_SR90_TINY.get(), 9)
                .requires(ModItems.POWDER_SR90.get())
                .unlockedBy("has_item", has(ModItems.POWDER_SR90.get())).save(writer, loc("decompress_powder_sr90"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_XE135.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_XE135_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_XE135_TINY.get())).save(writer, loc("compress_powder_xe135_tiny_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.POWDER_XE135_TINY.get(), 9)
                .requires(ModItems.POWDER_XE135.get())
                .unlockedBy("has_item", has(ModItems.POWDER_XE135.get())).save(writer, loc("decompress_powder_xe135"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_CS137.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_CS137_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_CS137_TINY.get())).save(writer, loc("compress_powder_cs137_tiny_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.POWDER_CS137_TINY.get(), 9)
                .requires(ModItems.POWDER_CS137.get())
                .unlockedBy("has_item", has(ModItems.POWDER_CS137.get())).save(writer, loc("decompress_powder_cs137"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_I131.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_I131_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_I131_TINY.get())).save(writer, loc("compress_powder_i131_tiny_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.POWDER_I131_TINY.get(), 9)
                .requires(ModItems.POWDER_I131.get())
                .unlockedBy("has_item", has(ModItems.POWDER_I131.get())).save(writer, loc("decompress_powder_i131"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_TECHNETIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_TECHNETIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_TECHNETIUM.get())).save(writer, loc("compress_nugget_technetium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_TECHNETIUM.get(), 9)
                .requires(ModItems.INGOT_TECHNETIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_TECHNETIUM.get())).save(writer, loc("decompress_ingot_technetium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_CO60.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_CO60.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_CO60.get())).save(writer, loc("compress_nugget_co60_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_CO60.get(), 9)
                .requires(ModItems.INGOT_CO60.get())
                .unlockedBy("has_item", has(ModItems.INGOT_CO60.get())).save(writer, loc("decompress_ingot_co60"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_SR90.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_SR90.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_SR90.get())).save(writer, loc("compress_nugget_sr90_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_SR90.get(), 9)
                .requires(ModItems.INGOT_SR90.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SR90.get())).save(writer, loc("decompress_ingot_sr90"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_AU198.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AU198.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AU198.get())).save(writer, loc("compress_nugget_au198_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AU198.get(), 9)
                .requires(ModItems.INGOT_AU198.get())
                .unlockedBy("has_item", has(ModItems.INGOT_AU198.get())).save(writer, loc("decompress_ingot_au198"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_PB209.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PB209.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PB209.get())).save(writer, loc("compress_nugget_pb209_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PB209.get(), 9)
                .requires(ModItems.INGOT_PB209.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PB209.get())).save(writer, loc("decompress_ingot_pb209"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_RA226.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_RA226.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_RA226.get())).save(writer, loc("compress_nugget_ra226_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_RA226.get(), 9)
                .requires(ModItems.INGOT_RA226.get())
                .unlockedBy("has_item", has(ModItems.INGOT_RA226.get())).save(writer, loc("decompress_ingot_ra226"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_ACTINIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_ACTINIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_ACTINIUM.get())).save(writer, loc("compress_nugget_actinium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_ACTINIUM.get(), 9)
                .requires(ModItems.INGOT_ACTINIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_ACTINIUM.get())).save(writer, loc("decompress_ingot_actinium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_ARSENIC.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_ARSENIC.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_ARSENIC.get())).save(writer, loc("compress_nugget_arsenic_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_ARSENIC.get(), 9)
                .requires(ModItems.INGOT_ARSENIC.get())
                .unlockedBy("has_item", has(ModItems.INGOT_ARSENIC.get())).save(writer, loc("decompress_ingot_arsenic"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_PU241.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PU241.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PU241.get())).save(writer, loc("compress_nugget_pu241_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PU241.get(), 9)
                .requires(ModItems.INGOT_PU241.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PU241.get())).save(writer, loc("decompress_ingot_pu241"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_AM241.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AM241.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AM241.get())).save(writer, loc("compress_nugget_am241_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AM241.get(), 9)
                .requires(ModItems.INGOT_AM241.get())
                .unlockedBy("has_item", has(ModItems.INGOT_AM241.get())).save(writer, loc("decompress_ingot_am241"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_AM242.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AM242.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AM242.get())).save(writer, loc("compress_nugget_am242_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AM242.get(), 9)
                .requires(ModItems.INGOT_AM242.get())
                .unlockedBy("has_item", has(ModItems.INGOT_AM242.get())).save(writer, loc("decompress_ingot_am242"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_AM_MIX.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AM_MIX.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AM_MIX.get())).save(writer, loc("compress_nugget_am_mix_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AM_MIX.get(), 9)
                .requires(ModItems.INGOT_AM_MIX.get())
                .unlockedBy("has_item", has(ModItems.INGOT_AM_MIX.get())).save(writer, loc("decompress_ingot_am_mix"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_AMERICIUM_FUEL.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AMERICIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AMERICIUM_FUEL.get())).save(writer, loc("compress_nugget_americium_fuel_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AMERICIUM_FUEL.get(), 9)
                .requires(ModItems.INGOT_AMERICIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_AMERICIUM_FUEL.get())).save(writer, loc("decompress_ingot_americium_fuel"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_GH336.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_GH336.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_GH336.get())).save(writer, loc("compress_nugget_gh336_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_GH336.get(), 9)
                .requires(ModItems.INGOT_GH336.get())
                .unlockedBy("has_item", has(ModItems.INGOT_GH336.get())).save(writer, loc("decompress_ingot_gh336"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_FALLOUT.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.FALLOUT.get())
                .unlockedBy("has_item", has(ModItems.FALLOUT.get())).save(writer, loc("compress_fallout_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FALLOUT.get(), 9)
                .requires(ModBlocks.BLOCK_FALLOUT.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_FALLOUT.get())).save(writer, loc("decompress_block_fallout"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_NEPTUNIUM_FUEL.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_NEPTUNIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_NEPTUNIUM_FUEL.get())).save(writer, loc("compress_nugget_neptunium_fuel_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_NEPTUNIUM_FUEL.get(), 9)
                .requires(ModItems.INGOT_NEPTUNIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_NEPTUNIUM_FUEL.get())).save(writer, loc("decompress_ingot_neptunium_fuel"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_PALEOGENITE.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_PALEOGENITE_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_PALEOGENITE_TINY.get())).save(writer, loc("compress_powder_paleogenite_tiny_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.POWDER_PALEOGENITE_TINY.get(), 9)
                .requires(ModItems.POWDER_PALEOGENITE.get())
                .unlockedBy("has_item", has(ModItems.POWDER_PALEOGENITE.get())).save(writer, loc("decompress_powder_paleogenite"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_OSMIRIDIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_OSMIRIDIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_OSMIRIDIUM.get())).save(writer, loc("compress_nugget_osmiridium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_OSMIRIDIUM.get(), 9)
                .requires(ModItems.INGOT_OSMIRIDIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_OSMIRIDIUM.get())).save(writer, loc("decompress_ingot_osmiridium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_NIOBIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_NIOBIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_NIOBIUM.get())).save(writer, loc("compress_ingot_niobium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_NIOBIUM.get(), 9)
                .requires(ModBlocks.BLOCK_NIOBIUM.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_NIOBIUM.get())).save(writer, loc("decompress_block_niobium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_NIOBIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_NIOBIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_NIOBIUM.get())).save(writer, loc("compress_nugget_niobium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_NIOBIUM.get(), 9)
                .requires(ModItems.INGOT_NIOBIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_NIOBIUM.get())).save(writer, loc("decompress_ingot_niobium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_BISMUTH.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_BISMUTH.get())
                .unlockedBy("has_item", has(ModItems.INGOT_BISMUTH.get())).save(writer, loc("compress_ingot_bismuth_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_BISMUTH.get(), 9)
                .requires(ModBlocks.BLOCK_BISMUTH.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_BISMUTH.get())).save(writer, loc("decompress_block_bismuth"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_BISMUTH.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_BISMUTH.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_BISMUTH.get())).save(writer, loc("compress_nugget_bismuth_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_BISMUTH.get(), 9)
                .requires(ModItems.INGOT_BISMUTH.get())
                .unlockedBy("has_item", has(ModItems.INGOT_BISMUTH.get())).save(writer, loc("decompress_ingot_bismuth"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_TANTALIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_TANTALIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_TANTALIUM.get())).save(writer, loc("compress_ingot_tantalium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_TANTALIUM.get(), 9)
                .requires(ModBlocks.BLOCK_TANTALIUM.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_TANTALIUM.get())).save(writer, loc("decompress_block_tantalium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_TANTALIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_TANTALIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_TANTALIUM.get())).save(writer, loc("compress_nugget_tantalium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_TANTALIUM.get(), 9)
                .requires(ModItems.INGOT_TANTALIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_TANTALIUM.get())).save(writer, loc("decompress_ingot_tantalium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_ZIRCONIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_ZIRCONIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_ZIRCONIUM.get())).save(writer, loc("compress_ingot_zirconium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_ZIRCONIUM.get(), 9)
                .requires(ModBlocks.BLOCK_ZIRCONIUM.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_ZIRCONIUM.get())).save(writer, loc("decompress_block_zirconium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_ZIRCONIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_ZIRCONIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_ZIRCONIUM.get())).save(writer, loc("compress_nugget_zirconium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_ZIRCONIUM.get(), 9)
                .requires(ModItems.INGOT_ZIRCONIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_ZIRCONIUM.get())).save(writer, loc("decompress_ingot_zirconium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_DINEUTRONIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_DINEUTRONIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_DINEUTRONIUM.get())).save(writer, loc("compress_ingot_dineutronium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_DINEUTRONIUM.get(), 9)
                .requires(ModBlocks.BLOCK_DINEUTRONIUM.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_DINEUTRONIUM.get())).save(writer, loc("decompress_block_dineutronium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_DINEUTRONIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_DINEUTRONIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_DINEUTRONIUM.get())).save(writer, loc("compress_nugget_dineutronium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_DINEUTRONIUM.get(), 9)
                .requires(ModItems.INGOT_DINEUTRONIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_DINEUTRONIUM.get())).save(writer, loc("decompress_ingot_dineutronium"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_WASTE_VITRIFIED.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUCLEAR_WASTE_VITRIFIED.get())
                .unlockedBy("has_item", has(ModItems.NUCLEAR_WASTE_VITRIFIED.get())).save(writer, loc("compress_nuclear_waste_vitrified_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUCLEAR_WASTE_VITRIFIED.get(), 9)
                .requires(ModBlocks.BLOCK_WASTE_VITRIFIED.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_WASTE_VITRIFIED.get())).save(writer, loc("decompress_block_waste_vitrified"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NUCLEAR_WASTE_VITRIFIED.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUCLEAR_WASTE_VITRIFIED_TINY.get())
                .unlockedBy("has_item", has(ModItems.NUCLEAR_WASTE_VITRIFIED_TINY.get())).save(writer, loc("compress_nuclear_waste_vitrified_tiny_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUCLEAR_WASTE_VITRIFIED_TINY.get(), 9)
                .requires(ModItems.NUCLEAR_WASTE_VITRIFIED.get())
                .unlockedBy("has_item", has(ModItems.NUCLEAR_WASTE_VITRIFIED.get())).save(writer, loc("decompress_nuclear_waste_vitrified"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK_PU_MIX.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.INGOT_PU_MIX.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PU_MIX.get())).save(writer, loc("compress_ingot_pu_mix_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_PU_MIX.get(), 9)
                .requires(ModBlocks.BLOCK_PU_MIX.get())
                .unlockedBy("has_item", has(ModBlocks.BLOCK_PU_MIX.get())).save(writer, loc("decompress_block_pu_mix"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_PU_MIX.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PU_MIX.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PU_MIX.get())).save(writer, loc("compress_nugget_pu_mix_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PU_MIX.get(), 9)
                .requires(ModItems.INGOT_PU_MIX.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PU_MIX.get())).save(writer, loc("decompress_ingot_pu_mix"));
    }

    // ────────────────────────────────────────────────────────────────────
    // Billet ↔ nugget / ingot conversions  (168 entries)
    // ────────────────────────────────────────────────────────────────────
    private void addBilletRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_COBALT.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_COBALT.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_COBALT.get())).save(writer, loc("billet_billet_cobalt_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_COBALT.get(), 6)
                .requires(ModItems.BILLET_COBALT.get())
                .unlockedBy("has_item", has(ModItems.BILLET_COBALT.get())).save(writer, loc("billet_billet_cobalt_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_COBALT.get(), 2)
                .requires(ModItems.BILLET_COBALT.get()).requires(ModItems.BILLET_COBALT.get()).requires(ModItems.BILLET_COBALT.get())
                .unlockedBy("has_item", has(ModItems.BILLET_COBALT.get())).save(writer, loc("billet_billet_cobalt_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_COBALT.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_COBALT.get())
                .unlockedBy("has_item", has(ModItems.INGOT_COBALT.get())).save(writer, loc("billet_billet_cobalt_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_CO60.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_CO60.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_CO60.get())).save(writer, loc("billet_billet_co60_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_CO60.get(), 6)
                .requires(ModItems.BILLET_CO60.get())
                .unlockedBy("has_item", has(ModItems.BILLET_CO60.get())).save(writer, loc("billet_billet_co60_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_CO60.get(), 2)
                .requires(ModItems.BILLET_CO60.get()).requires(ModItems.BILLET_CO60.get()).requires(ModItems.BILLET_CO60.get())
                .unlockedBy("has_item", has(ModItems.BILLET_CO60.get())).save(writer, loc("billet_billet_co60_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_CO60.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_CO60.get())
                .unlockedBy("has_item", has(ModItems.INGOT_CO60.get())).save(writer, loc("billet_billet_co60_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_SR90.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_SR90.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_SR90.get())).save(writer, loc("billet_billet_sr90_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_SR90.get(), 6)
                .requires(ModItems.BILLET_SR90.get())
                .unlockedBy("has_item", has(ModItems.BILLET_SR90.get())).save(writer, loc("billet_billet_sr90_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_SR90.get(), 2)
                .requires(ModItems.BILLET_SR90.get()).requires(ModItems.BILLET_SR90.get()).requires(ModItems.BILLET_SR90.get())
                .unlockedBy("has_item", has(ModItems.BILLET_SR90.get())).save(writer, loc("billet_billet_sr90_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_SR90.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_SR90.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SR90.get())).save(writer, loc("billet_billet_sr90_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_URANIUM.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_URANIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_URANIUM.get())).save(writer, loc("billet_billet_uranium_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_URANIUM.get(), 6)
                .requires(ModItems.BILLET_URANIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_URANIUM.get())).save(writer, loc("billet_billet_uranium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_URANIUM.get(), 2)
                .requires(ModItems.BILLET_URANIUM.get()).requires(ModItems.BILLET_URANIUM.get()).requires(ModItems.BILLET_URANIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_URANIUM.get())).save(writer, loc("billet_billet_uranium_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_URANIUM.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_URANIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_URANIUM.get())).save(writer, loc("billet_billet_uranium_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_U233.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_U233.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_U233.get())).save(writer, loc("billet_billet_u233_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_U233.get(), 6)
                .requires(ModItems.BILLET_U233.get())
                .unlockedBy("has_item", has(ModItems.BILLET_U233.get())).save(writer, loc("billet_billet_u233_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_U233.get(), 2)
                .requires(ModItems.BILLET_U233.get()).requires(ModItems.BILLET_U233.get()).requires(ModItems.BILLET_U233.get())
                .unlockedBy("has_item", has(ModItems.BILLET_U233.get())).save(writer, loc("billet_billet_u233_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_U233.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_U233.get())
                .unlockedBy("has_item", has(ModItems.INGOT_U233.get())).save(writer, loc("billet_billet_u233_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_U235.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_U235.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_U235.get())).save(writer, loc("billet_billet_u235_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_U235.get(), 6)
                .requires(ModItems.BILLET_U235.get())
                .unlockedBy("has_item", has(ModItems.BILLET_U235.get())).save(writer, loc("billet_billet_u235_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_U235.get(), 2)
                .requires(ModItems.BILLET_U235.get()).requires(ModItems.BILLET_U235.get()).requires(ModItems.BILLET_U235.get())
                .unlockedBy("has_item", has(ModItems.BILLET_U235.get())).save(writer, loc("billet_billet_u235_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_U235.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_U235.get())
                .unlockedBy("has_item", has(ModItems.INGOT_U235.get())).save(writer, loc("billet_billet_u235_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_U238.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_U238.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_U238.get())).save(writer, loc("billet_billet_u238_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_U238.get(), 6)
                .requires(ModItems.BILLET_U238.get())
                .unlockedBy("has_item", has(ModItems.BILLET_U238.get())).save(writer, loc("billet_billet_u238_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_U238.get(), 2)
                .requires(ModItems.BILLET_U238.get()).requires(ModItems.BILLET_U238.get()).requires(ModItems.BILLET_U238.get())
                .unlockedBy("has_item", has(ModItems.BILLET_U238.get())).save(writer, loc("billet_billet_u238_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_U238.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_U238.get())
                .unlockedBy("has_item", has(ModItems.INGOT_U238.get())).save(writer, loc("billet_billet_u238_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_TH232.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_TH232.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_TH232.get())).save(writer, loc("billet_billet_th232_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_TH232.get(), 6)
                .requires(ModItems.BILLET_TH232.get())
                .unlockedBy("has_item", has(ModItems.BILLET_TH232.get())).save(writer, loc("billet_billet_th232_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_TH232.get(), 2)
                .requires(ModItems.BILLET_TH232.get()).requires(ModItems.BILLET_TH232.get()).requires(ModItems.BILLET_TH232.get())
                .unlockedBy("has_item", has(ModItems.BILLET_TH232.get())).save(writer, loc("billet_billet_th232_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_TH232.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_TH232.get())
                .unlockedBy("has_item", has(ModItems.INGOT_TH232.get())).save(writer, loc("billet_billet_th232_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PLUTONIUM.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PLUTONIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PLUTONIUM.get())).save(writer, loc("billet_billet_plutonium_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PLUTONIUM.get(), 6)
                .requires(ModItems.BILLET_PLUTONIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PLUTONIUM.get())).save(writer, loc("billet_billet_plutonium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM.get(), 2)
                .requires(ModItems.BILLET_PLUTONIUM.get()).requires(ModItems.BILLET_PLUTONIUM.get()).requires(ModItems.BILLET_PLUTONIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PLUTONIUM.get())).save(writer, loc("billet_billet_plutonium_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PLUTONIUM.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_PLUTONIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PLUTONIUM.get())).save(writer, loc("billet_billet_plutonium_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PU238.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PU238.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PU238.get())).save(writer, loc("billet_billet_pu238_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PU238.get(), 6)
                .requires(ModItems.BILLET_PU238.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PU238.get())).save(writer, loc("billet_billet_pu238_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_PU238.get(), 2)
                .requires(ModItems.BILLET_PU238.get()).requires(ModItems.BILLET_PU238.get()).requires(ModItems.BILLET_PU238.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PU238.get())).save(writer, loc("billet_billet_pu238_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PU238.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_PU238.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PU238.get())).save(writer, loc("billet_billet_pu238_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PU239.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PU239.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PU239.get())).save(writer, loc("billet_billet_pu239_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PU239.get(), 6)
                .requires(ModItems.BILLET_PU239.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PU239.get())).save(writer, loc("billet_billet_pu239_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_PU239.get(), 2)
                .requires(ModItems.BILLET_PU239.get()).requires(ModItems.BILLET_PU239.get()).requires(ModItems.BILLET_PU239.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PU239.get())).save(writer, loc("billet_billet_pu239_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PU239.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_PU239.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PU239.get())).save(writer, loc("billet_billet_pu239_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PU240.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PU240.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PU240.get())).save(writer, loc("billet_billet_pu240_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PU240.get(), 6)
                .requires(ModItems.BILLET_PU240.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PU240.get())).save(writer, loc("billet_billet_pu240_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_PU240.get(), 2)
                .requires(ModItems.BILLET_PU240.get()).requires(ModItems.BILLET_PU240.get()).requires(ModItems.BILLET_PU240.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PU240.get())).save(writer, loc("billet_billet_pu240_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PU240.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_PU240.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PU240.get())).save(writer, loc("billet_billet_pu240_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PU241.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PU241.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PU241.get())).save(writer, loc("billet_billet_pu241_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PU241.get(), 6)
                .requires(ModItems.BILLET_PU241.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PU241.get())).save(writer, loc("billet_billet_pu241_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_PU241.get(), 2)
                .requires(ModItems.BILLET_PU241.get()).requires(ModItems.BILLET_PU241.get()).requires(ModItems.BILLET_PU241.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PU241.get())).save(writer, loc("billet_billet_pu241_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PU241.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_PU241.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PU241.get())).save(writer, loc("billet_billet_pu241_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PU_MIX.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PU_MIX.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PU_MIX.get())).save(writer, loc("billet_billet_pu_mix_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PU_MIX.get(), 6)
                .requires(ModItems.BILLET_PU_MIX.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PU_MIX.get())).save(writer, loc("billet_billet_pu_mix_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_PU_MIX.get(), 2)
                .requires(ModItems.BILLET_PU_MIX.get()).requires(ModItems.BILLET_PU_MIX.get()).requires(ModItems.BILLET_PU_MIX.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PU_MIX.get())).save(writer, loc("billet_billet_pu_mix_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PU_MIX.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_PU_MIX.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PU_MIX.get())).save(writer, loc("billet_billet_pu_mix_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AM241.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AM241.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AM241.get())).save(writer, loc("billet_billet_am241_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AM241.get(), 6)
                .requires(ModItems.BILLET_AM241.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AM241.get())).save(writer, loc("billet_billet_am241_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_AM241.get(), 2)
                .requires(ModItems.BILLET_AM241.get()).requires(ModItems.BILLET_AM241.get()).requires(ModItems.BILLET_AM241.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AM241.get())).save(writer, loc("billet_billet_am241_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AM241.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_AM241.get())
                .unlockedBy("has_item", has(ModItems.INGOT_AM241.get())).save(writer, loc("billet_billet_am241_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AM242.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AM242.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AM242.get())).save(writer, loc("billet_billet_am242_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AM242.get(), 6)
                .requires(ModItems.BILLET_AM242.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AM242.get())).save(writer, loc("billet_billet_am242_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_AM242.get(), 2)
                .requires(ModItems.BILLET_AM242.get()).requires(ModItems.BILLET_AM242.get()).requires(ModItems.BILLET_AM242.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AM242.get())).save(writer, loc("billet_billet_am242_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AM242.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_AM242.get())
                .unlockedBy("has_item", has(ModItems.INGOT_AM242.get())).save(writer, loc("billet_billet_am242_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AM_MIX.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AM_MIX.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AM_MIX.get())).save(writer, loc("billet_billet_am_mix_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AM_MIX.get(), 6)
                .requires(ModItems.BILLET_AM_MIX.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AM_MIX.get())).save(writer, loc("billet_billet_am_mix_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_AM_MIX.get(), 2)
                .requires(ModItems.BILLET_AM_MIX.get()).requires(ModItems.BILLET_AM_MIX.get()).requires(ModItems.BILLET_AM_MIX.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AM_MIX.get())).save(writer, loc("billet_billet_am_mix_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AM_MIX.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_AM_MIX.get())
                .unlockedBy("has_item", has(ModItems.INGOT_AM_MIX.get())).save(writer, loc("billet_billet_am_mix_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_NEPTUNIUM.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_NEPTUNIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_NEPTUNIUM.get())).save(writer, loc("billet_billet_neptunium_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_NEPTUNIUM.get(), 6)
                .requires(ModItems.BILLET_NEPTUNIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_NEPTUNIUM.get())).save(writer, loc("billet_billet_neptunium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_NEPTUNIUM.get(), 2)
                .requires(ModItems.BILLET_NEPTUNIUM.get()).requires(ModItems.BILLET_NEPTUNIUM.get()).requires(ModItems.BILLET_NEPTUNIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_NEPTUNIUM.get())).save(writer, loc("billet_billet_neptunium_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_NEPTUNIUM.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_NEPTUNIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_NEPTUNIUM.get())).save(writer, loc("billet_billet_neptunium_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_POLONIUM.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_POLONIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_POLONIUM.get())).save(writer, loc("billet_billet_polonium_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_POLONIUM.get(), 6)
                .requires(ModItems.BILLET_POLONIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_POLONIUM.get())).save(writer, loc("billet_billet_polonium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_POLONIUM.get(), 2)
                .requires(ModItems.BILLET_POLONIUM.get()).requires(ModItems.BILLET_POLONIUM.get()).requires(ModItems.BILLET_POLONIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_POLONIUM.get())).save(writer, loc("billet_billet_polonium_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_POLONIUM.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_POLONIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_POLONIUM.get())).save(writer, loc("billet_billet_polonium_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_TECHNETIUM.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_TECHNETIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_TECHNETIUM.get())).save(writer, loc("billet_billet_technetium_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_TECHNETIUM.get(), 6)
                .requires(ModItems.BILLET_TECHNETIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_TECHNETIUM.get())).save(writer, loc("billet_billet_technetium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_TECHNETIUM.get(), 2)
                .requires(ModItems.BILLET_TECHNETIUM.get()).requires(ModItems.BILLET_TECHNETIUM.get()).requires(ModItems.BILLET_TECHNETIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_TECHNETIUM.get())).save(writer, loc("billet_billet_technetium_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_TECHNETIUM.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_TECHNETIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_TECHNETIUM.get())).save(writer, loc("billet_billet_technetium_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AU198.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AU198.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AU198.get())).save(writer, loc("billet_billet_au198_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AU198.get(), 6)
                .requires(ModItems.BILLET_AU198.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AU198.get())).save(writer, loc("billet_billet_au198_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_AU198.get(), 2)
                .requires(ModItems.BILLET_AU198.get()).requires(ModItems.BILLET_AU198.get()).requires(ModItems.BILLET_AU198.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AU198.get())).save(writer, loc("billet_billet_au198_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AU198.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_AU198.get())
                .unlockedBy("has_item", has(ModItems.INGOT_AU198.get())).save(writer, loc("billet_billet_au198_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PB209.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PB209.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PB209.get())).save(writer, loc("billet_billet_pb209_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PB209.get(), 6)
                .requires(ModItems.BILLET_PB209.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PB209.get())).save(writer, loc("billet_billet_pb209_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_PB209.get(), 2)
                .requires(ModItems.BILLET_PB209.get()).requires(ModItems.BILLET_PB209.get()).requires(ModItems.BILLET_PB209.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PB209.get())).save(writer, loc("billet_billet_pb209_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PB209.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_PB209.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PB209.get())).save(writer, loc("billet_billet_pb209_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_RA226.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_RA226.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_RA226.get())).save(writer, loc("billet_billet_ra226_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_RA226.get(), 6)
                .requires(ModItems.BILLET_RA226.get())
                .unlockedBy("has_item", has(ModItems.BILLET_RA226.get())).save(writer, loc("billet_billet_ra226_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_RA226.get(), 2)
                .requires(ModItems.BILLET_RA226.get()).requires(ModItems.BILLET_RA226.get()).requires(ModItems.BILLET_RA226.get())
                .unlockedBy("has_item", has(ModItems.BILLET_RA226.get())).save(writer, loc("billet_billet_ra226_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_RA226.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_RA226.get())
                .unlockedBy("has_item", has(ModItems.INGOT_RA226.get())).save(writer, loc("billet_billet_ra226_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_ACTINIUM.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_ACTINIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_ACTINIUM.get())).save(writer, loc("billet_billet_actinium_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_ACTINIUM.get(), 6)
                .requires(ModItems.BILLET_ACTINIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_ACTINIUM.get())).save(writer, loc("billet_billet_actinium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_ACTINIUM.get(), 2)
                .requires(ModItems.BILLET_ACTINIUM.get()).requires(ModItems.BILLET_ACTINIUM.get()).requires(ModItems.BILLET_ACTINIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_ACTINIUM.get())).save(writer, loc("billet_billet_actinium_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_ACTINIUM.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_ACTINIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_ACTINIUM.get())).save(writer, loc("billet_billet_actinium_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_SCHRABIDIUM.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_SCHRABIDIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_SCHRABIDIUM.get())).save(writer, loc("billet_billet_schrabidium_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_SCHRABIDIUM.get(), 6)
                .requires(ModItems.BILLET_SCHRABIDIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_SCHRABIDIUM.get())).save(writer, loc("billet_billet_schrabidium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get(), 2)
                .requires(ModItems.BILLET_SCHRABIDIUM.get()).requires(ModItems.BILLET_SCHRABIDIUM.get()).requires(ModItems.BILLET_SCHRABIDIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_SCHRABIDIUM.get())).save(writer, loc("billet_billet_schrabidium_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_SCHRABIDIUM.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_SCHRABIDIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SCHRABIDIUM.get())).save(writer, loc("billet_billet_schrabidium_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_SOLINIUM.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_SOLINIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_SOLINIUM.get())).save(writer, loc("billet_billet_solinium_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_SOLINIUM.get(), 6)
                .requires(ModItems.BILLET_SOLINIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_SOLINIUM.get())).save(writer, loc("billet_billet_solinium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_SOLINIUM.get(), 2)
                .requires(ModItems.BILLET_SOLINIUM.get()).requires(ModItems.BILLET_SOLINIUM.get()).requires(ModItems.BILLET_SOLINIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_SOLINIUM.get())).save(writer, loc("billet_billet_solinium_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_SOLINIUM.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_SOLINIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SOLINIUM.get())).save(writer, loc("billet_billet_solinium_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_GH336.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_GH336.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_GH336.get())).save(writer, loc("billet_billet_gh336_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_GH336.get(), 6)
                .requires(ModItems.BILLET_GH336.get())
                .unlockedBy("has_item", has(ModItems.BILLET_GH336.get())).save(writer, loc("billet_billet_gh336_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_GH336.get(), 2)
                .requires(ModItems.BILLET_GH336.get()).requires(ModItems.BILLET_GH336.get()).requires(ModItems.BILLET_GH336.get())
                .unlockedBy("has_item", has(ModItems.BILLET_GH336.get())).save(writer, loc("billet_billet_gh336_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_GH336.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_GH336.get())
                .unlockedBy("has_item", has(ModItems.INGOT_GH336.get())).save(writer, loc("billet_billet_gh336_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_URANIUM_FUEL.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_URANIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_URANIUM_FUEL.get())).save(writer, loc("billet_billet_uranium_fuel_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_URANIUM_FUEL.get(), 6)
                .requires(ModItems.BILLET_URANIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_URANIUM_FUEL.get())).save(writer, loc("billet_billet_uranium_fuel_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_URANIUM_FUEL.get(), 2)
                .requires(ModItems.BILLET_URANIUM_FUEL.get()).requires(ModItems.BILLET_URANIUM_FUEL.get()).requires(ModItems.BILLET_URANIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_URANIUM_FUEL.get())).save(writer, loc("billet_billet_uranium_fuel_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_URANIUM_FUEL.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_URANIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_URANIUM_FUEL.get())).save(writer, loc("billet_billet_uranium_fuel_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_THORIUM_FUEL.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_THORIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_THORIUM_FUEL.get())).save(writer, loc("billet_billet_thorium_fuel_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_THORIUM_FUEL.get(), 6)
                .requires(ModItems.BILLET_THORIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_THORIUM_FUEL.get())).save(writer, loc("billet_billet_thorium_fuel_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_THORIUM_FUEL.get(), 2)
                .requires(ModItems.BILLET_THORIUM_FUEL.get()).requires(ModItems.BILLET_THORIUM_FUEL.get()).requires(ModItems.BILLET_THORIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_THORIUM_FUEL.get())).save(writer, loc("billet_billet_thorium_fuel_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_THORIUM_FUEL.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_THORIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_THORIUM_FUEL.get())).save(writer, loc("billet_billet_thorium_fuel_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PLUTONIUM_FUEL.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PLUTONIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PLUTONIUM_FUEL.get())).save(writer, loc("billet_billet_plutonium_fuel_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PLUTONIUM_FUEL.get(), 6)
                .requires(ModItems.BILLET_PLUTONIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PLUTONIUM_FUEL.get())).save(writer, loc("billet_billet_plutonium_fuel_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM_FUEL.get(), 2)
                .requires(ModItems.BILLET_PLUTONIUM_FUEL.get()).requires(ModItems.BILLET_PLUTONIUM_FUEL.get()).requires(ModItems.BILLET_PLUTONIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_PLUTONIUM_FUEL.get())).save(writer, loc("billet_billet_plutonium_fuel_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_PLUTONIUM_FUEL.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_PLUTONIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PLUTONIUM_FUEL.get())).save(writer, loc("billet_billet_plutonium_fuel_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_NEPTUNIUM_FUEL.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_NEPTUNIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_NEPTUNIUM_FUEL.get())).save(writer, loc("billet_billet_neptunium_fuel_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_NEPTUNIUM_FUEL.get(), 6)
                .requires(ModItems.BILLET_NEPTUNIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_NEPTUNIUM_FUEL.get())).save(writer, loc("billet_billet_neptunium_fuel_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_NEPTUNIUM_FUEL.get(), 2)
                .requires(ModItems.BILLET_NEPTUNIUM_FUEL.get()).requires(ModItems.BILLET_NEPTUNIUM_FUEL.get()).requires(ModItems.BILLET_NEPTUNIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_NEPTUNIUM_FUEL.get())).save(writer, loc("billet_billet_neptunium_fuel_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_NEPTUNIUM_FUEL.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_NEPTUNIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_NEPTUNIUM_FUEL.get())).save(writer, loc("billet_billet_neptunium_fuel_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_MOX_FUEL.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_MOX_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_MOX_FUEL.get())).save(writer, loc("billet_billet_mox_fuel_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_MOX_FUEL.get(), 6)
                .requires(ModItems.BILLET_MOX_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_MOX_FUEL.get())).save(writer, loc("billet_billet_mox_fuel_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_MOX_FUEL.get(), 2)
                .requires(ModItems.BILLET_MOX_FUEL.get()).requires(ModItems.BILLET_MOX_FUEL.get()).requires(ModItems.BILLET_MOX_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_MOX_FUEL.get())).save(writer, loc("billet_billet_mox_fuel_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_MOX_FUEL.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_MOX_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_MOX_FUEL.get())).save(writer, loc("billet_billet_mox_fuel_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_LES.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_LES.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_LES.get())).save(writer, loc("billet_billet_les_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_LES.get(), 6)
                .requires(ModItems.BILLET_LES.get())
                .unlockedBy("has_item", has(ModItems.BILLET_LES.get())).save(writer, loc("billet_billet_les_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_LES.get(), 2)
                .requires(ModItems.BILLET_LES.get()).requires(ModItems.BILLET_LES.get()).requires(ModItems.BILLET_LES.get())
                .unlockedBy("has_item", has(ModItems.BILLET_LES.get())).save(writer, loc("billet_billet_les_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_LES.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_LES.get())
                .unlockedBy("has_item", has(ModItems.INGOT_LES.get())).save(writer, loc("billet_billet_les_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_SCHRABIDIUM_FUEL.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_SCHRABIDIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_SCHRABIDIUM_FUEL.get())).save(writer, loc("billet_billet_schrabidium_fuel_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_SCHRABIDIUM_FUEL.get(), 6)
                .requires(ModItems.BILLET_SCHRABIDIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_SCHRABIDIUM_FUEL.get())).save(writer, loc("billet_billet_schrabidium_fuel_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM_FUEL.get(), 2)
                .requires(ModItems.BILLET_SCHRABIDIUM_FUEL.get()).requires(ModItems.BILLET_SCHRABIDIUM_FUEL.get()).requires(ModItems.BILLET_SCHRABIDIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.BILLET_SCHRABIDIUM_FUEL.get())).save(writer, loc("billet_billet_schrabidium_fuel_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_SCHRABIDIUM_FUEL.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_SCHRABIDIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SCHRABIDIUM_FUEL.get())).save(writer, loc("billet_billet_schrabidium_fuel_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_HES.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_HES.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_HES.get())).save(writer, loc("billet_billet_hes_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_HES.get(), 6)
                .requires(ModItems.BILLET_HES.get())
                .unlockedBy("has_item", has(ModItems.BILLET_HES.get())).save(writer, loc("billet_billet_hes_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_HES.get(), 2)
                .requires(ModItems.BILLET_HES.get()).requires(ModItems.BILLET_HES.get()).requires(ModItems.BILLET_HES.get())
                .unlockedBy("has_item", has(ModItems.BILLET_HES.get())).save(writer, loc("billet_billet_hes_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_HES.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_HES.get())
                .unlockedBy("has_item", has(ModItems.INGOT_HES.get())).save(writer, loc("billet_billet_hes_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AUSTRALIUM.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AUSTRALIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AUSTRALIUM.get())).save(writer, loc("billet_billet_australium_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AUSTRALIUM.get(), 6)
                .requires(ModItems.BILLET_AUSTRALIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AUSTRALIUM.get())).save(writer, loc("billet_billet_australium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_AUSTRALIUM.get(), 2)
                .requires(ModItems.BILLET_AUSTRALIUM.get()).requires(ModItems.BILLET_AUSTRALIUM.get()).requires(ModItems.BILLET_AUSTRALIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AUSTRALIUM.get())).save(writer, loc("billet_billet_australium_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AUSTRALIUM.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_AUSTRALIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_AUSTRALIUM.get())).save(writer, loc("billet_billet_australium_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_NUCLEAR_WASTE.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUCLEAR_WASTE_TINY.get())
                .unlockedBy("has_item", has(ModItems.NUCLEAR_WASTE_TINY.get())).save(writer, loc("billet_billet_nuclear_waste_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUCLEAR_WASTE_TINY.get(), 6)
                .requires(ModItems.BILLET_NUCLEAR_WASTE.get())
                .unlockedBy("has_item", has(ModItems.BILLET_NUCLEAR_WASTE.get())).save(writer, loc("billet_billet_nuclear_waste_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUCLEAR_WASTE.get(), 2)
                .requires(ModItems.BILLET_NUCLEAR_WASTE.get()).requires(ModItems.BILLET_NUCLEAR_WASTE.get()).requires(ModItems.BILLET_NUCLEAR_WASTE.get())
                .unlockedBy("has_item", has(ModItems.BILLET_NUCLEAR_WASTE.get())).save(writer, loc("billet_billet_nuclear_waste_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_NUCLEAR_WASTE.get(), 3)
                .pattern("##")
                .define('#', ModItems.NUCLEAR_WASTE.get())
                .unlockedBy("has_item", has(ModItems.NUCLEAR_WASTE.get())).save(writer, loc("billet_billet_nuclear_waste_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_BERYLLIUM.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_BERYLLIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_BERYLLIUM.get())).save(writer, loc("billet_billet_beryllium_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_BERYLLIUM.get(), 6)
                .requires(ModItems.BILLET_BERYLLIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_BERYLLIUM.get())).save(writer, loc("billet_billet_beryllium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_BERYLLIUM.get(), 2)
                .requires(ModItems.BILLET_BERYLLIUM.get()).requires(ModItems.BILLET_BERYLLIUM.get()).requires(ModItems.BILLET_BERYLLIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_BERYLLIUM.get())).save(writer, loc("billet_billet_beryllium_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_BERYLLIUM.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_BERYLLIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_BERYLLIUM.get())).save(writer, loc("billet_billet_beryllium_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_ZIRCONIUM.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_ZIRCONIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_ZIRCONIUM.get())).save(writer, loc("billet_billet_zirconium_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_ZIRCONIUM.get(), 6)
                .requires(ModItems.BILLET_ZIRCONIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_ZIRCONIUM.get())).save(writer, loc("billet_billet_zirconium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_ZIRCONIUM.get(), 2)
                .requires(ModItems.BILLET_ZIRCONIUM.get()).requires(ModItems.BILLET_ZIRCONIUM.get()).requires(ModItems.BILLET_ZIRCONIUM.get())
                .unlockedBy("has_item", has(ModItems.BILLET_ZIRCONIUM.get())).save(writer, loc("billet_billet_zirconium_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_ZIRCONIUM.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_ZIRCONIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_ZIRCONIUM.get())).save(writer, loc("billet_billet_zirconium_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_BISMUTH.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_BISMUTH.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_BISMUTH.get())).save(writer, loc("billet_billet_bismuth_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_BISMUTH.get(), 6)
                .requires(ModItems.BILLET_BISMUTH.get())
                .unlockedBy("has_item", has(ModItems.BILLET_BISMUTH.get())).save(writer, loc("billet_billet_bismuth_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_BISMUTH.get(), 2)
                .requires(ModItems.BILLET_BISMUTH.get()).requires(ModItems.BILLET_BISMUTH.get()).requires(ModItems.BILLET_BISMUTH.get())
                .unlockedBy("has_item", has(ModItems.BILLET_BISMUTH.get())).save(writer, loc("billet_billet_bismuth_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_BISMUTH.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_BISMUTH.get())
                .unlockedBy("has_item", has(ModItems.INGOT_BISMUTH.get())).save(writer, loc("billet_billet_bismuth_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_SILICON.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_SILICON.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_SILICON.get())).save(writer, loc("billet_billet_silicon_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_SILICON.get(), 6)
                .requires(ModItems.BILLET_SILICON.get())
                .unlockedBy("has_item", has(ModItems.BILLET_SILICON.get())).save(writer, loc("billet_billet_silicon_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INGOT_SILICON.get(), 2)
                .requires(ModItems.BILLET_SILICON.get()).requires(ModItems.BILLET_SILICON.get()).requires(ModItems.BILLET_SILICON.get())
                .unlockedBy("has_item", has(ModItems.BILLET_SILICON.get())).save(writer, loc("billet_billet_silicon_to_ingots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_SILICON.get(), 3)
                .pattern("##")
                .define('#', ModItems.INGOT_SILICON.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SILICON.get())).save(writer, loc("billet_billet_silicon_from_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AUSTRALIUM_GREATER.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AUSTRALIUM_GREATER.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AUSTRALIUM_GREATER.get())).save(writer, loc("billet_billet_australium_greater_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AUSTRALIUM_GREATER.get(), 6)
                .requires(ModItems.BILLET_AUSTRALIUM_GREATER.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AUSTRALIUM_GREATER.get())).save(writer, loc("billet_billet_australium_greater_to_nuggets"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BILLET_AUSTRALIUM_LESSER.get())
                .pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AUSTRALIUM_LESSER.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AUSTRALIUM_LESSER.get())).save(writer, loc("billet_billet_australium_lesser_from_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AUSTRALIUM_LESSER.get(), 6)
                .requires(ModItems.BILLET_AUSTRALIUM_LESSER.get())
                .unlockedBy("has_item", has(ModItems.BILLET_AUSTRALIUM_LESSER.get())).save(writer, loc("billet_billet_australium_lesser_to_nuggets"));
    }

    // ────────────────────────────────────────────────────────────────────
    // Explicit nugget ↔ ingot pairs from MineralRecipes  (59 entries)
    // ────────────────────────────────────────────────────────────────────
    private void addNuggetIngotRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PLUTONIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PLUTONIUM.get())).save(writer, loc("compress_nugget_plutonium_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_PU238.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PU238.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PU238.get())).save(writer, loc("compress_nugget_pu238_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_PU239.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PU239.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PU239.get())).save(writer, loc("compress_nugget_pu239_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_PU240.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PU240.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PU240.get())).save(writer, loc("compress_nugget_pu240_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_TH232.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_TH232.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_TH232.get())).save(writer, loc("compress_nugget_th232_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_URANIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_URANIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_URANIUM.get())).save(writer, loc("compress_nugget_uranium_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_U233.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_U233.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_U233.get())).save(writer, loc("compress_nugget_u233_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_U235.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_U235.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_U235.get())).save(writer, loc("compress_nugget_u235_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_U238.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_U238.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_U238.get())).save(writer, loc("compress_nugget_u238_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_NEPTUNIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_NEPTUNIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_NEPTUNIUM.get())).save(writer, loc("compress_nugget_neptunium_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_POLONIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_POLONIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_POLONIUM.get())).save(writer, loc("compress_nugget_polonium_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_LEAD.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_LEAD.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_LEAD.get())).save(writer, loc("compress_nugget_lead_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_BERYLLIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_BERYLLIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_BERYLLIUM.get())).save(writer, loc("compress_nugget_beryllium_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_SCHRABIDIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_SCHRABIDIUM.get())).save(writer, loc("compress_nugget_schrabidium_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_URANIUM_FUEL.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_URANIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_URANIUM_FUEL.get())).save(writer, loc("compress_nugget_uranium_fuel_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_THORIUM_FUEL.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_THORIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_THORIUM_FUEL.get())).save(writer, loc("compress_nugget_thorium_fuel_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_PLUTONIUM_FUEL.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_PLUTONIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_PLUTONIUM_FUEL.get())).save(writer, loc("compress_nugget_plutonium_fuel_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_MOX_FUEL.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_MOX_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_MOX_FUEL.get())).save(writer, loc("compress_nugget_mox_fuel_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_SCHRABIDIUM_FUEL.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_SCHRABIDIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_SCHRABIDIUM_FUEL.get())).save(writer, loc("compress_nugget_schrabidium_fuel_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_HES.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_HES.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_HES.get())).save(writer, loc("compress_nugget_hes_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_LES.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_LES.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_LES.get())).save(writer, loc("compress_nugget_les_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_AUSTRALIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_AUSTRALIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_AUSTRALIUM.get())).save(writer, loc("compress_nugget_australium_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_STEEL.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_STEEL_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_STEEL_TINY.get())).save(writer, loc("compress_powder_steel_tiny_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_LITHIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_LITHIUM_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_LITHIUM_TINY.get())).save(writer, loc("compress_powder_lithium_tiny_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_COBALT.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_COBALT_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_COBALT_TINY.get())).save(writer, loc("compress_powder_cobalt_tiny_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_NEODYMIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_NEODYMIUM_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_NEODYMIUM_TINY.get())).save(writer, loc("compress_powder_neodymium_tiny_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_NIOBIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_NIOBIUM_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_NIOBIUM_TINY.get())).save(writer, loc("compress_powder_niobium_tiny_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_CERIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_CERIUM_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_CERIUM_TINY.get())).save(writer, loc("compress_powder_cerium_tiny_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_LANTHANIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_LANTHANIUM_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_LANTHANIUM_TINY.get())).save(writer, loc("compress_powder_lanthanium_tiny_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_ACTINIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_ACTINIUM_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_ACTINIUM_TINY.get())).save(writer, loc("compress_powder_actinium_tiny_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.POWDER_METEORITE.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.POWDER_METEORITE_TINY.get())
                .unlockedBy("has_item", has(ModItems.POWDER_METEORITE_TINY.get())).save(writer, loc("compress_powder_meteorite_tiny_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_SOLINIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_SOLINIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_SOLINIUM.get())).save(writer, loc("compress_nugget_solinium_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NUCLEAR_WASTE.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUCLEAR_WASTE_TINY.get())
                .unlockedBy("has_item", has(ModItems.NUCLEAR_WASTE_TINY.get())).save(writer, loc("compress_nuclear_waste_tiny_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EGG_BALEFIRE.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.EGG_BALEFIRE_SHARD.get())
                .unlockedBy("has_item", has(ModItems.EGG_BALEFIRE_SHARD.get())).save(writer, loc("compress_egg_balefire_shard_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INGOT_EUPHEMIUM.get())
                .pattern("###").pattern("###").pattern("###")
                .define('#', ModItems.NUGGET_EUPHEMIUM.get())
                .unlockedBy("has_item", has(ModItems.NUGGET_EUPHEMIUM.get())).save(writer, loc("compress_nugget_euphemium_ingot"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PLUTONIUM.get(), 9)
                .requires(ModItems.INGOT_PLUTONIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PLUTONIUM.get())).save(writer, loc("decompress_ingot_plutonium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PU238.get(), 9)
                .requires(ModItems.INGOT_PU238.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PU238.get())).save(writer, loc("decompress_ingot_pu238_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PU239.get(), 9)
                .requires(ModItems.INGOT_PU239.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PU239.get())).save(writer, loc("decompress_ingot_pu239_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PU240.get(), 9)
                .requires(ModItems.INGOT_PU240.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PU240.get())).save(writer, loc("decompress_ingot_pu240_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_TH232.get(), 9)
                .requires(ModItems.INGOT_TH232.get())
                .unlockedBy("has_item", has(ModItems.INGOT_TH232.get())).save(writer, loc("decompress_ingot_th232_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_URANIUM.get(), 9)
                .requires(ModItems.INGOT_URANIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_URANIUM.get())).save(writer, loc("decompress_ingot_uranium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_U233.get(), 9)
                .requires(ModItems.INGOT_U233.get())
                .unlockedBy("has_item", has(ModItems.INGOT_U233.get())).save(writer, loc("decompress_ingot_u233_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_U235.get(), 9)
                .requires(ModItems.INGOT_U235.get())
                .unlockedBy("has_item", has(ModItems.INGOT_U235.get())).save(writer, loc("decompress_ingot_u235_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_U238.get(), 9)
                .requires(ModItems.INGOT_U238.get())
                .unlockedBy("has_item", has(ModItems.INGOT_U238.get())).save(writer, loc("decompress_ingot_u238_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_NEPTUNIUM.get(), 9)
                .requires(ModItems.INGOT_NEPTUNIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_NEPTUNIUM.get())).save(writer, loc("decompress_ingot_neptunium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_POLONIUM.get(), 9)
                .requires(ModItems.INGOT_POLONIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_POLONIUM.get())).save(writer, loc("decompress_ingot_polonium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_LEAD.get(), 9)
                .requires(ModItems.INGOT_LEAD.get())
                .unlockedBy("has_item", has(ModItems.INGOT_LEAD.get())).save(writer, loc("decompress_ingot_lead_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_BERYLLIUM.get(), 9)
                .requires(ModItems.INGOT_BERYLLIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_BERYLLIUM.get())).save(writer, loc("decompress_ingot_beryllium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_SCHRABIDIUM.get(), 9)
                .requires(ModItems.INGOT_SCHRABIDIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SCHRABIDIUM.get())).save(writer, loc("decompress_ingot_schrabidium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_URANIUM_FUEL.get(), 9)
                .requires(ModItems.INGOT_URANIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_URANIUM_FUEL.get())).save(writer, loc("decompress_ingot_uranium_fuel_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_THORIUM_FUEL.get(), 9)
                .requires(ModItems.INGOT_THORIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_THORIUM_FUEL.get())).save(writer, loc("decompress_ingot_thorium_fuel_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_PLUTONIUM_FUEL.get(), 9)
                .requires(ModItems.INGOT_PLUTONIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_PLUTONIUM_FUEL.get())).save(writer, loc("decompress_ingot_plutonium_fuel_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_MOX_FUEL.get(), 9)
                .requires(ModItems.INGOT_MOX_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_MOX_FUEL.get())).save(writer, loc("decompress_ingot_mox_fuel_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_SCHRABIDIUM_FUEL.get(), 9)
                .requires(ModItems.INGOT_SCHRABIDIUM_FUEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SCHRABIDIUM_FUEL.get())).save(writer, loc("decompress_ingot_schrabidium_fuel_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_HES.get(), 9)
                .requires(ModItems.INGOT_HES.get())
                .unlockedBy("has_item", has(ModItems.INGOT_HES.get())).save(writer, loc("decompress_ingot_hes_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_LES.get(), 9)
                .requires(ModItems.INGOT_LES.get())
                .unlockedBy("has_item", has(ModItems.INGOT_LES.get())).save(writer, loc("decompress_ingot_les_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_AUSTRALIUM.get(), 9)
                .requires(ModItems.INGOT_AUSTRALIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_AUSTRALIUM.get())).save(writer, loc("decompress_ingot_australium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_SOLINIUM.get(), 9)
                .requires(ModItems.INGOT_SOLINIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_SOLINIUM.get())).save(writer, loc("decompress_ingot_solinium_to_nuggets"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.NUGGET_EUPHEMIUM.get(), 9)
                .requires(ModItems.INGOT_EUPHEMIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_EUPHEMIUM.get())).save(writer, loc("decompress_ingot_euphemium_to_nuggets"));
    }

    // ────────────────────────────────────────────────────────────────────
    // Basic tool patterns — steel/titanium/cobalt/desh sets  (20 entries)
    // ────────────────────────────────────────────────────────────────────
    private void addToolRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.STEEL_SWORD.get())
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .define('X', ModItems.INGOT_STEEL.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_STEEL.get()))
                .save(writer, loc("tool_steel_sword"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STEEL_PICKAXE.get())
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', ModItems.INGOT_STEEL.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_STEEL.get()))
                .save(writer, loc("tool_steel_pickaxe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STEEL_AXE.get())
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .define('X', ModItems.INGOT_STEEL.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_STEEL.get()))
                .save(writer, loc("tool_steel_axe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STEEL_SHOVEL.get())
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .define('X', ModItems.INGOT_STEEL.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_STEEL.get()))
                .save(writer, loc("tool_steel_shovel"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STEEL_HOE.get())
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .define('X', ModItems.INGOT_STEEL.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_STEEL.get()))
                .save(writer, loc("tool_steel_hoe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_SWORD.get())
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .define('X', ModItems.INGOT_TITANIUM.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_TITANIUM.get()))
                .save(writer, loc("tool_titanium_sword"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_PICKAXE.get())
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', ModItems.INGOT_TITANIUM.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_TITANIUM.get()))
                .save(writer, loc("tool_titanium_pickaxe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_AXE.get())
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .define('X', ModItems.INGOT_TITANIUM.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_TITANIUM.get()))
                .save(writer, loc("tool_titanium_axe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_SHOVEL.get())
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .define('X', ModItems.INGOT_TITANIUM.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_TITANIUM.get()))
                .save(writer, loc("tool_titanium_shovel"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_HOE.get())
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .define('X', ModItems.INGOT_TITANIUM.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_TITANIUM.get()))
                .save(writer, loc("tool_titanium_hoe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.COBALT_SWORD.get())
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .define('X', ModItems.INGOT_COBALT.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_COBALT.get()))
                .save(writer, loc("tool_cobalt_sword"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.COBALT_PICKAXE.get())
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', ModItems.INGOT_COBALT.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_COBALT.get()))
                .save(writer, loc("tool_cobalt_pickaxe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.COBALT_AXE.get())
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .define('X', ModItems.INGOT_COBALT.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_COBALT.get()))
                .save(writer, loc("tool_cobalt_axe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.COBALT_SHOVEL.get())
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .define('X', ModItems.INGOT_COBALT.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_COBALT.get()))
                .save(writer, loc("tool_cobalt_shovel"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.COBALT_HOE.get())
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .define('X', ModItems.INGOT_COBALT.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_COBALT.get()))
                .save(writer, loc("tool_cobalt_hoe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DESH_SWORD.get())
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .define('X', ModItems.INGOT_DESH.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_DESH.get()))
                .save(writer, loc("tool_desh_sword"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DESH_PICKAXE.get())
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', ModItems.INGOT_DESH.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_DESH.get()))
                .save(writer, loc("tool_desh_pickaxe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DESH_AXE.get())
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .define('X', ModItems.INGOT_DESH.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_DESH.get()))
                .save(writer, loc("tool_desh_axe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DESH_SHOVEL.get())
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .define('X', ModItems.INGOT_DESH.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_DESH.get()))
                .save(writer, loc("tool_desh_shovel"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DESH_HOE.get())
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .define('X', ModItems.INGOT_DESH.get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(ModItems.INGOT_DESH.get()))
                .save(writer, loc("tool_desh_hoe"));
    }

    // ────────────────────────────────────────────────────────────────────
    // Basic armor patterns — steel/titanium/cobalt sets  (12 entries)
    // ────────────────────────────────────────────────────────────────────
    private void addArmorRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.STEEL_HELMET.get())
                .pattern("XXX")
                .pattern("X X")
                .define('X', ModItems.INGOT_STEEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_STEEL.get()))
                .save(writer, loc("armor_steel_helmet"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.STEEL_PLATE.get())
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.INGOT_STEEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_STEEL.get()))
                .save(writer, loc("armor_steel_chestplate"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.STEEL_LEGS.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', ModItems.INGOT_STEEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_STEEL.get()))
                .save(writer, loc("armor_steel_leggings"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.STEEL_BOOTS.get())
                .pattern("X X")
                .pattern("X X")
                .define('X', ModItems.INGOT_STEEL.get())
                .unlockedBy("has_item", has(ModItems.INGOT_STEEL.get()))
                .save(writer, loc("armor_steel_boots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_HELMET.get())
                .pattern("XXX")
                .pattern("X X")
                .define('X', ModItems.INGOT_TITANIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_TITANIUM.get()))
                .save(writer, loc("armor_titanium_helmet"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_PLATE.get())
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.INGOT_TITANIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_TITANIUM.get()))
                .save(writer, loc("armor_titanium_chestplate"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_LEGS.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', ModItems.INGOT_TITANIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_TITANIUM.get()))
                .save(writer, loc("armor_titanium_leggings"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_BOOTS.get())
                .pattern("X X")
                .pattern("X X")
                .define('X', ModItems.INGOT_TITANIUM.get())
                .unlockedBy("has_item", has(ModItems.INGOT_TITANIUM.get()))
                .save(writer, loc("armor_titanium_boots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.COBALT_HELMET.get())
                .pattern("XXX")
                .pattern("X X")
                .define('X', ModItems.INGOT_COBALT.get())
                .unlockedBy("has_item", has(ModItems.INGOT_COBALT.get()))
                .save(writer, loc("armor_cobalt_helmet"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.COBALT_PLATE.get())
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItems.INGOT_COBALT.get())
                .unlockedBy("has_item", has(ModItems.INGOT_COBALT.get()))
                .save(writer, loc("armor_cobalt_chestplate"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.COBALT_LEGS.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', ModItems.INGOT_COBALT.get())
                .unlockedBy("has_item", has(ModItems.INGOT_COBALT.get()))
                .save(writer, loc("armor_cobalt_leggings"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.COBALT_BOOTS.get())
                .pattern("X X")
                .pattern("X X")
                .define('X', ModItems.INGOT_COBALT.get())
                .unlockedBy("has_item", has(ModItems.INGOT_COBALT.get()))
                .save(writer, loc("armor_cobalt_boots"));
    }

}