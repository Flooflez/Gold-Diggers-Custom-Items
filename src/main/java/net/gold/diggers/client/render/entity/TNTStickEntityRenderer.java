package net.gold.diggers.client.render.entity;

import net.gold.diggers.ItemInit;
import net.gold.diggers.entities.TNTStickEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

public class TNTStickEntityRenderer extends EntityRenderer<TNTStickEntity>{
    public TNTStickEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(TNTStickEntity StickEntity, float f, float g, MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider, int i) {

        matrixStack.push();
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, StickEntity.prevYaw, StickEntity.getYaw()) - 90.0f));
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, StickEntity.prevPitch, StickEntity.getPitch()) - 90.0f));
        
        BakedModel bakedModel = MinecraftClient.getInstance().getItemRenderer().getModel(new ItemStack(ItemInit.TNT_STICK), StickEntity.world, null, StickEntity.getId());
        MinecraftClient.getInstance().getItemRenderer().renderItem(new ItemStack(ItemInit.HANSOLOPEWPEW_AXE), ModelTransformation.Mode.FIXED, false, matrixStack, vertexConsumerProvider, i, OverlayTexture.DEFAULT_UV, bakedModel);

        matrixStack.pop();
        super.render(StickEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(TNTStickEntity entity) {
        return null;
    }

}
