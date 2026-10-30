package com.pumpkinpal;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class LivingPumpkinModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(PumpkinPal.MODID, "living_pumpkin"), "main");

    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public LivingPumpkinModel(ModelPart root) {
        super(root);
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 0).addBox(-6f, -12f, -6f, 12f, 12f, 12f),
                PartPose.offset(0f, 21f, 0f));
        root.addOrReplaceChild("stem",
                CubeListBuilder.create().texOffs(0, 24).addBox(-1f, -15f, -1f, 2f, 3f, 2f),
                PartPose.offset(0f, 21f, 0f));
        root.addOrReplaceChild("left_leg",
                CubeListBuilder.create().texOffs(0, 32).addBox(-1.5f, 0f, -1.5f, 3f, 3f, 3f),
                PartPose.offset(3f, 21f, 0f));
        root.addOrReplaceChild("right_leg",
                CubeListBuilder.create().texOffs(0, 32).addBox(-1.5f, 0f, -1.5f, 3f, 3f, 3f),
                PartPose.offset(-3f, 21f, 0f));
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        this.rightLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662f) * 1.4f * state.walkAnimationSpeed;
        this.leftLeg.xRot = Mth.cos(state.walkAnimationPos * 0.6662f + (float) Math.PI) * 1.4f * state.walkAnimationSpeed;
    }
}
