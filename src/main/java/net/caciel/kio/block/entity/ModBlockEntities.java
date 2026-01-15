package net.caciel.kio.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.caciel.kio.block.ModBlocks;

public class ModBlockEntities {

    public static BlockEntityType<CoinCastingBlockEntity> COIN_CASTING_STATION;

    public static void registerBlockEntity() {
        COIN_CASTING_STATION = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("kio", "coin_casting_station"),
                FabricBlockEntityTypeBuilder
                        .create(CoinCastingBlockEntity::new, ModBlocks.COIN_CASTING_STATION)
                        .build(null)
        );
    }
}
