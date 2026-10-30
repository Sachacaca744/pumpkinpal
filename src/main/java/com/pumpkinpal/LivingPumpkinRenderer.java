package com.pumpkinpal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class LivingPumpkinRenderer extends MobRenderer<LivingPumpkin, LivingEntityRenderState, LivingPumpkinModel> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(PumpkinPal.MODID, "textures/entity/living_pumpkin.png");

    public LivingPumpkinRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new LivingPumpkinModel(ctx.bakeLayer(LivingPumpkinModel.LAYER)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
