package net.caciel.kio.item;

import net.caciel.kio.Kio;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item ADMIN_MINTING_TEMPLATE = registerItem("admin_minting_template", new Item(new FabricItemSettings()));
    
    public static final Item RUBY_COIN = registerItem("ruby_coin", new Item(new FabricItemSettings()));

    public static final Item RUBY_COIN_FAKE = registerItem("ruby_coin_fake", new Item(new FabricItemSettings()));

    public static final Item RUBY_COIN_BLANK = registerItem("ruby_coin_blank", new Item(new FabricItemSettings()));

    public static final Item MINTING_TEMPLATE = registerItem("minting_template", new Item(new FabricItemSettings()));

    public static final Item GOLD_INGOT_BUCKET = registerItem("gold_ingot_bucket", new Item((new FabricItemSettings()).maxCount(1)));

    public static final Item MOLTEN_GOLD_BUCKET = registerItem("molten_gold_bucket", new Item((new FabricItemSettings()).maxCount(1).recipeRemainder(Items.BUCKET)));

    public static final Item COPPER_INGOT_BUCKET = registerItem("copper_ingot_bucket", new Item((new FabricItemSettings()).maxCount(1)));

    public static final Item MOLTEN_COPPER_BUCKET = registerItem("molten_copper_bucket", new Item((new FabricItemSettings()).maxCount(1).recipeRemainder(Items.BUCKET)));

    public static final Item OBSIDIAN_INGOT_BUCKET = registerItem("obsidian_bucket", new Item((new FabricItemSettings()).maxCount(1)));

    public static final Item MOLTEN_OBSIDIAN_BUCKET = registerItem("molten_obsidian_bucket", new Item((new FabricItemSettings()).maxCount(1).recipeRemainder(Items.BUCKET)));

    public static final Item EMERALD_INGOT_BUCKET = registerItem("emerald_bucket", new Item((new FabricItemSettings()).maxCount(1)));

    public static final Item MOLTEN_EMERALD_BUCKET = registerItem("molten_emerald_bucket", new Item((new FabricItemSettings()).maxCount(1).recipeRemainder(Items.BUCKET)));

    public static final Item LEATHER_INGOT_BUCKET = registerItem("leather_ingot_bucket", new Item((new FabricItemSettings()).maxCount(1)));

    public static final Item MOLTEN_LEATHER_BUCKET = registerItem("molten_leather_bucket", new Item((new FabricItemSettings()).maxCount(1).recipeRemainder(Items.BUCKET)));

    public static final Item PAPER_INGOT_BUCKET = registerItem("paper_ingot_bucket", new Item((new FabricItemSettings()).maxCount(1)));

    public static final Item MOLTEN_PAPER_BUCKET = registerItem("molten_paper_bucket", new Item((new FabricItemSettings()).maxCount(1).recipeRemainder(Items.BUCKET)));

    public static final Item GOLD_COIN = registerItem("gold_coin", new Item(new FabricItemSettings()));

    public static final Item COPPER_COIN = registerItem("copper_coin", new Item(new FabricItemSettings()));

    public static final Item OBSIDIAN_COIN = registerItem("obsidian_coin", new Item(new FabricItemSettings()));

    public static final Item EMERALD_COIN = registerItem("emerald_coin", new Item(new FabricItemSettings()));

    public static final Item GOLD_COIN_FAKE = registerItem("gold_coin_fake", new Item(new FabricItemSettings()));

    public static final Item COPPER_COIN_FAKE = registerItem("copper_coin_fake", new Item(new FabricItemSettings()));

    public static final Item OBSIDIAN_COIN_FAKE = registerItem("obsidian_coin_fake", new Item(new FabricItemSettings()));

    public static final Item EMERALD_COIN_FAKE = registerItem("emerald_coin_fake", new Item(new FabricItemSettings()));

    public static final Item GOLD_COIN_BLANK = registerItem("gold_coin_blank", new Item(new FabricItemSettings()));

    public static final Item COPPER_COIN_BLANK = registerItem("copper_coin_blank", new Item(new FabricItemSettings()));

    public static final Item OBSIDIAN_COIN_BLANK = registerItem("obsidian_coin_blank", new Item(new FabricItemSettings()));

    public static final Item EMERALD_COIN_BLANK = registerItem("emerald_coin_blank", new Item(new FabricItemSettings()));

    public static final Item LEATHER_INGOT = registerItem("leather_ingot", new Item(new FabricItemSettings()));

    public static final Item LEATHER_COIN = registerItem("leather_coin", new Item(new FabricItemSettings()));

    public static final Item LEATHER_COIN_FAKE = registerItem("leather_coin_fake", new Item(new FabricItemSettings()));

    public static final Item LEATHER_COIN_BLANK = registerItem("leather_coin_blank", new Item(new FabricItemSettings()));

    public static final Item PAPER_INGOT = registerItem("paper_ingot", new Item(new FabricItemSettings()));

    public static final Item PAPER_COIN = registerItem("paper_coin", new Item(new FabricItemSettings()));

    public static final Item PAPER_COIN_FAKE = registerItem("paper_coin_fake", new Item(new FabricItemSettings()));

    public static final Item PAPER_COIN_BLANK = registerItem("paper_coin_blank", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Kio.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Kio.LOGGER.info("Registering Mod Items for " + Kio.MOD_ID);
    }

}
