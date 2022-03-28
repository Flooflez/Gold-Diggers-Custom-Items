package net.gold.diggers.client.render.entity;

import net.fabricmc.api.Environment;
import net.gold.diggers.ItemInit;
import net.gold.diggers.entities.HansolopewpewAxeEntity;
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
import net.fabricmc.api.EnvType;

//import net.minecraft.client.render.model.json.ModelTransformation;

@Environment(EnvType.CLIENT)
public class HansolopewpewAxeEntityRenderer extends EntityRenderer<HansolopewpewAxeEntity>{

    public HansolopewpewAxeEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(HansolopewpewAxeEntity AxeEntity, float f, float g, MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider, int i) {

            /**MinecraftClient.getInstance().getItemRenderer().renderItem(
            new ItemStack(ItemInit.HANSOLOPEWPEW_AXE),
            //new ItemStack(Items.ACACIA_BOAT), 
            ModelTransformation.Mode.GROUND, 
            i, 
            OverlayTexture.DEFAULT_UV, 
            matrixStack, 
            vertexConsumerProvider, 
            0
        ); */
        matrixStack.push();
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, AxeEntity.prevYaw, AxeEntity.getYaw()) - 90.0f));
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, AxeEntity.prevPitch, AxeEntity.getPitch()) - 90.0f));
        
        BakedModel bakedModel = MinecraftClient.getInstance().getItemRenderer().getModel(new ItemStack(ItemInit.HANSOLOPEWPEW_AXE), AxeEntity.world, null, AxeEntity.getId());
        MinecraftClient.getInstance().getItemRenderer().renderItem(new ItemStack(ItemInit.HANSOLOPEWPEW_AXE), ModelTransformation.Mode.FIXED, false, matrixStack, vertexConsumerProvider, i, OverlayTexture.DEFAULT_UV, bakedModel);

        matrixStack.pop();
        super.render(AxeEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(HansolopewpewAxeEntity entity) {
        return null;
    }
}
