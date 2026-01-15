package net.caciel.kio.block.entity.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.caciel.kio.block.custom.CoinCastingStationBlock;
import net.caciel.kio.block.entity.CoinCastingBlockEntity;

public class CoinCastingStationBlockEntityRenderer implements BlockEntityRenderer<CoinCastingBlockEntity> {

    public CoinCastingStationBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(CoinCastingBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = entity.getRenderStack();
        if (itemStack.isEmpty()) {
            return;
        }

        matrices.push();

        // Position item on top of block
        matrices.translate(0.5D, 0.7D, 0.5D);
        matrices.scale(0.5F, 0.5F, 0.5F);

        // Lay flat
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90));

        // Rotate based on block facing
        Direction facing = entity.getCachedState().get(CoinCastingStationBlock.FACING);

        switch (facing) {
            case NORTH -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
            case EAST -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(270));
            case SOUTH -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(0));
            case WEST -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90));
        }

        assert entity.getWorld() != null;
        itemRenderer.renderItem(itemStack, ModelTransformationMode.GUI, getLightLevel(entity.getWorld(), entity.getPos()),
                OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);

        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int blockLight = world.getLightLevel(LightType.BLOCK, pos);
        int skyLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(blockLight, skyLight);
    }
}
