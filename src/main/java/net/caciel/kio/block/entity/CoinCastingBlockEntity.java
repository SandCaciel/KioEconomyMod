package net.caciel.kio.block.entity;

import java.util.Optional;

import net.caciel.kio.screen.CoinCastingScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.caciel.kio.networking.ModPackets;
import net.caciel.kio.recipe.CoinCastingRecipe;

import org.jetbrains.annotations.Nullable;

public class CoinCastingBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;

    private int progress = 0;
    private int maxProgress = 72;

    public CoinCastingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COIN_CASTING_STATION, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CoinCastingBlockEntity.this.progress;
                    case 1 -> CoinCastingBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CoinCastingBlockEntity.this.progress = value;
                    case 1 -> CoinCastingBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    /* ---------- Rendering ---------- */

    public ItemStack getRenderStack() {
        if (getStack(1).isEmpty()) {
            return getStack(0);
        }
        return getStack(1);
    }

    /* ---------- Inventory ---------- */

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public void setInventory(DefaultedList<ItemStack> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, inventory.get(i));
        }
    }

    /* ---------- Sync ---------- */

    @Override
    public void markDirty() {
        assert world != null;
        if (!world.isClient) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeInt(this.inventory.size());

            for (ItemStack stack : this.inventory) {
                buf.writeItemStack(stack);
            }

            buf.writeBlockPos(pos);

            for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, pos)) {
                ServerPlayNetworking.send(player, ModPackets.ITEM_SYNC, buf);
            }
        }
        super.markDirty();
    }

    /* ---------- Screen ---------- */

    @Override
    public Text getDisplayName() {
        return Text.literal("Coin Casting Station");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CoinCastingScreenHandler(syncId, inv, this, propertyDelegate);
    }

    /* ---------- NBT ---------- */

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("coin_casting_station.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("coin_casting_station.progress");
    }

    /* ---------- Logic ---------- */

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(World world, BlockPos pos, BlockState state, CoinCastingBlockEntity entity) {

        if (world.isClient) return;

        if (hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, pos, state);

            if (entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private static void craftItem(CoinCastingBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());

        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        assert entity.world != null;
        Optional<CoinCastingRecipe> recipe = entity.world.getRecipeManager().getFirstMatch(CoinCastingRecipe.Type.INSTANCE, inventory, entity.world);

        if (hasRecipe(entity)) {
            entity.removeStack(0, 1);

            entity.setStack(1, new ItemStack(recipe.get().getOutput(null).getItem(),
                    entity.getStack(1).getCount() + recipe.get().getOutput(null).getCount()));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(CoinCastingBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());

        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        assert entity.world != null;
        Optional<CoinCastingRecipe> match = entity.world.getRecipeManager().getFirstMatch(CoinCastingRecipe.Type.INSTANCE, inventory, entity.world);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory) && entity.canInsertItemIntoOutputSlot(inventory, match.get().getOutput(null).getItem());
    }

    private boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(1).isEmpty() || inventory.getStack(1).getItem() == output;
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(1).getMaxCount() > inventory.getStack(1).getCount();
    }
}

