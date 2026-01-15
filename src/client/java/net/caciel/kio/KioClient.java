package net.caciel.kio;

import net.caciel.kio.block.entity.ModBlockEntities;
import net.caciel.kio.block.entity.client.CoinCastingStationBlockEntityRenderer;
import net.caciel.kio.networking.ModMessages;
import net.caciel.kio.screen.CoinCastingScreen;
import net.caciel.kio.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class KioClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        HandledScreens.register(ModScreenHandlers.COIN_CASTING_SCREEN_HANDLER, CoinCastingScreen::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.COIN_CASTING_STATION, CoinCastingStationBlockEntityRenderer::new);
	}
}