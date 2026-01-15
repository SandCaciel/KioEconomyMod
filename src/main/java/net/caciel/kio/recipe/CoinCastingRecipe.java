package net.caciel.kio.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.minecraft.network.PacketByteBuf;

public class CoinCastingRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public CoinCastingRecipe(
            Identifier id,
            ItemStack output,
            DefaultedList<Ingredient> recipeItems
    ) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }
        return recipeItems.get(0).test(inventory.getStack(0));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public DefaultedList<Ingredient> getIngredients() {
        return recipeItems;
    }

    // =============================
    // Recipe Type
    // =============================
    public static class Type implements RecipeType<CoinCastingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "coin_casting";
    }

    // =============================
    // Recipe Serializer
    // =============================
    public static class Serializer implements RecipeSerializer<CoinCastingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "coin_casting";

        @Override
        public CoinCastingRecipe read(Identifier id, JsonObject json) {
            ItemStack output =
                    ShapedRecipe.outputFromJson(
                            JsonHelper.getObject(json, "output")
                    );

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs =
                    DefaultedList.ofSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new CoinCastingRecipe(id, output, inputs);
        }

        @Override
        public CoinCastingRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs =
                    DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));

            ItemStack output = buf.readItemStack();
            return new CoinCastingRecipe(id, output, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, CoinCastingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }

            buf.writeItemStack(recipe.getOutput(DynamicRegistryManager.EMPTY));
        }
    }
}
