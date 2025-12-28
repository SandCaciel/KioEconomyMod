package net.slimeyfellow.kio;

import net.fabricmc.api.ModInitializer;
import net.slimeyfellow.kio.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KioEconomyMod implements ModInitializer {
	public static final String MOD_ID = "kio";
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}
