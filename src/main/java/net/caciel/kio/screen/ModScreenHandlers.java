package net.caciel.kio.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static ScreenHandlerType<CoinCastingScreenHandler>
            COIN_CASTING_SCREEN_HANDLER;

    public static final Identifier COIN_CASTING =
            new Identifier("kio", "coin_casting");

    public static void registerAllScreenHandlers() {
        COIN_CASTING_SCREEN_HANDLER =
                ScreenHandlerRegistry.registerSimple(
                        COIN_CASTING,
                        CoinCastingScreenHandler::new
                );
    }
}
