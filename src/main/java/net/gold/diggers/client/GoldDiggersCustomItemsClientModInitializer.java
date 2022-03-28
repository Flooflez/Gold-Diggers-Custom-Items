package net.gold.diggers.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.gold.diggers.EntityInit;
import net.gold.diggers.client.render.entity.HansolopewpewAxeEntityRenderer;
import net.gold.diggers.client.render.entity.TNTStickEntityRenderer;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class GoldDiggersCustomItemsClientModInitializer implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
		EntityRendererRegistry.register(EntityInit.HansolopewpewAxeEntityType, HansolopewpewAxeEntityRenderer::new);
        EntityRendererRegistry.register(EntityInit.TNTStickEntityType, TNTStickEntityRenderer::new);
    }
    


}
