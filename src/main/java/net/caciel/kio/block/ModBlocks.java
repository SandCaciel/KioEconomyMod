package net.caciel.kio.block;

import net.caciel.kio.Kio;
import net.caciel.kio.block.custom.CoinCastingStationBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block SILICA_SAND = registerBlock("silica_sand",
            new Block(FabricBlockSettings.copyOf(Blocks.SAND).strength(0.5F, 0.5F).sounds(BlockSoundGroup.SAND)));

    public static Block COIN_CASTING_STATION = registerBlock("coin_casting_station",
            new CoinCastingStationBlock(FabricBlockSettings.copyOf(Blocks.CYAN_CONCRETE).strength(4.0F).requiresTool().nonOpaque()));

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Kio.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(Kio.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Kio.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Kio.LOGGER.info("Registering ModBlocks for " + Kio.MOD_ID);
    }
}
