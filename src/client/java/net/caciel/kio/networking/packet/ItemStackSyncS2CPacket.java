package net.caciel.kio.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.entity.BlockEntity;
import net.caciel.kio.block.entity.CoinCastingBlockEntity;

public class ItemStackSyncS2CPacket {

    public static void receive(
            MinecraftClient minecraftClient,
            ClientPlayNetworkHandler handler,
            PacketByteBuf buf,
            PacketSender responseSender
    ) {
        int size = buf.readInt();

        DefaultedList<ItemStack> list =
                DefaultedList.ofSize(size, ItemStack.EMPTY);

        for (int i = 0; i < size; i++) {
            list.set(i, buf.readItemStack());
        }

        BlockPos position = buf.readBlockPos();

        assert minecraftClient.world != null;
        BlockEntity blockEntity = minecraftClient.world.getBlockEntity(position);

        if (blockEntity instanceof CoinCastingBlockEntity coinCastingBE) {
            coinCastingBE.setInventory(list);
        }
    }
}

