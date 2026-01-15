package net.caciel.kio.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModRecipes {

    public static void registerRecipes() {

        Registry.register(
                Registries.RECIPE_SERIALIZER,
                new Identifier("kio", "coin_casting"),
                CoinCastingRecipe.Serializer.INSTANCE
        );

        Registry.register(
                Registries.RECIPE_TYPE,
                new Identifier("kio", "coin_casting"),
                CoinCastingRecipe.Type.INSTANCE
        );
    }
}
