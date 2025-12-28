package net.slimeyfellow.kio.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.slimeyfellow.kio.KioEconomyMod;

public class ModItems {

    public static final Item COIN = registerItem("coin",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item COIN_BLANK = registerItem("coin_blank",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(KioEconomyMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        KioEconomyMod.LOGGER.info("Registering Mod Items for" + KioEconomyMod.MOD_ID);
    }
}
