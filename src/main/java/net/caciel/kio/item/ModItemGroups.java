package net.caciel.kio.item;

import net.caciel.kio.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final RegistryKey<ItemGroup> KIO_GROUP = RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier("kio", "kio"));

    public static void registerItemGroups() {

        Registry.register(Registries.ITEM_GROUP, KIO_GROUP, FabricItemGroup.builder()
                .icon(() -> new ItemStack(ModItems.GOLD_COIN))
                .displayName(Text.translatable("itemgroup.kio"))
                .build());

        ItemGroupEvents.modifyEntriesEvent(KIO_GROUP)
                .register(fabricItemGroupEntries -> {

                    //Blocks
                    fabricItemGroupEntries.add(ModBlocks.SILICA_SAND);
                    fabricItemGroupEntries.add(ModBlocks.COIN_CASTING_STATION);

                    //Items
                    fabricItemGroupEntries.add(ModItems.ADMIN_MINTING_TEMPLATE);
                    fabricItemGroupEntries.add(ModItems.MINTING_TEMPLATE);

                    fabricItemGroupEntries.add(ModItems.GOLD_INGOT_BUCKET);
                    fabricItemGroupEntries.add(ModItems.MOLTEN_GOLD_BUCKET);
                    fabricItemGroupEntries.add(ModItems.GOLD_COIN_BLANK);
                    fabricItemGroupEntries.add(ModItems.GOLD_COIN);
                    fabricItemGroupEntries.add(ModItems.GOLD_COIN_FAKE);

                    fabricItemGroupEntries.add(ModItems.COPPER_INGOT_BUCKET);
                    fabricItemGroupEntries.add(ModItems.MOLTEN_COPPER_BUCKET);
                    fabricItemGroupEntries.add(ModItems.COPPER_COIN_BLANK);
                    fabricItemGroupEntries.add(ModItems.COPPER_COIN);
                    fabricItemGroupEntries.add(ModItems.COPPER_COIN_FAKE);

                    fabricItemGroupEntries.add(ModItems.OBSIDIAN_INGOT_BUCKET);
                    fabricItemGroupEntries.add(ModItems.MOLTEN_OBSIDIAN_BUCKET);
                    fabricItemGroupEntries.add(ModItems.OBSIDIAN_COIN_BLANK);
                    fabricItemGroupEntries.add(ModItems.OBSIDIAN_COIN);
                    fabricItemGroupEntries.add(ModItems.OBSIDIAN_COIN_FAKE);

                    fabricItemGroupEntries.add(ModItems.EMERALD_INGOT_BUCKET);
                    fabricItemGroupEntries.add(ModItems.MOLTEN_EMERALD_BUCKET);
                    fabricItemGroupEntries.add(ModItems.EMERALD_COIN_BLANK);
                    fabricItemGroupEntries.add(ModItems.EMERALD_COIN);
                    fabricItemGroupEntries.add(ModItems.EMERALD_COIN_FAKE);

                    fabricItemGroupEntries.add(ModItems.LEATHER_INGOT);
                    fabricItemGroupEntries.add(ModItems.LEATHER_INGOT_BUCKET);
                    fabricItemGroupEntries.add(ModItems.MOLTEN_LEATHER_BUCKET);
                    fabricItemGroupEntries.add(ModItems.LEATHER_COIN_BLANK);
                    fabricItemGroupEntries.add(ModItems.LEATHER_COIN);
                    fabricItemGroupEntries.add(ModItems.LEATHER_COIN_FAKE);

                    fabricItemGroupEntries.add(ModItems.PAPER_INGOT);
                    fabricItemGroupEntries.add(ModItems.PAPER_INGOT_BUCKET);
                    fabricItemGroupEntries.add(ModItems.MOLTEN_PAPER_BUCKET);
                    fabricItemGroupEntries.add(ModItems.PAPER_COIN_BLANK);
                    fabricItemGroupEntries.add(ModItems.PAPER_COIN);
                    fabricItemGroupEntries.add(ModItems.PAPER_COIN_FAKE);

                });
    }
}

