package net.caciel.kio;

import net.caciel.kio.block.ModBlocks;
import net.caciel.kio.block.entity.ModBlockEntities;
import net.caciel.kio.item.ModItemGroups;
import net.caciel.kio.item.ModItems;
import net.caciel.kio.recipe.ModRecipes;
import net.caciel.kio.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kio implements ModInitializer {
    public static final String MOD_ID = "kio";
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntity();
        ModScreenHandlers.registerAllScreenHandlers();
        ModRecipes.registerRecipes();
        ModItemGroups.registerItemGroups();
    }
}