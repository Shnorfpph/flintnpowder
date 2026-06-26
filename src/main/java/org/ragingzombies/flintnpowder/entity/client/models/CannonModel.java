package org.ragingzombies.flintnpowder.entity.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.ragingzombies.flintnpowder.Flintnpowder;
import org.ragingzombies.flintnpowder.entity.custom.CannonEntity;
import org.ragingzombies.flintnpowder.entity.custom.MortarEntity;

public class CannonModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart Barrel;
    private final ModelPart Base;

    public CannonModel(ModelPart root) {
        this.Barrel = root.getChild("Barrel");
        this.Base = root.getChild("Base");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Barrel = partdefinition.addOrReplaceChild("Barrel", CubeListBuilder.create().texOffs(0, 83).addBox(-7.0F, -8.0F, -36.0F, 14.0F, 14.0F, 26.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-8.0F, -9.0F, -10.0F, 16.0F, 16.0F, 32.0F, new CubeDeformation(0.0F))
                .texOffs(44, 150).addBox(-10.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(150, 69).addBox(8.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -2.0F));

        PartDefinition cube_r1 = Barrel.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 141).addBox(1.0F, -4.0F, -7.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.2F, 25.9F, -1.0036F, 0.0F, 0.0F));

        PartDefinition cube_r2 = Barrel.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(132, 125).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 22.0F, -1.0036F, 0.0F, 0.0F));

        PartDefinition Base = partdefinition.addOrReplaceChild("Base", CubeListBuilder.create().texOffs(80, 83).addBox(8.0F, -6.4559F, -18.2794F, 2.0F, 9.0F, 33.0F, new CubeDeformation(0.0F))
                .texOffs(96, 0).addBox(-10.0F, -6.4559F, -18.2794F, 2.0F, 9.0F, 33.0F, new CubeDeformation(0.0F))
                .texOffs(98, 42).addBox(8.0F, -7.4559F, -18.2794F, 2.0F, 1.0F, 26.0F, new CubeDeformation(0.0F))
                .texOffs(0, 123).addBox(-10.0F, -7.4559F, -18.2794F, 2.0F, 1.0F, 26.0F, new CubeDeformation(0.0F))
                .texOffs(72, 141).addBox(8.0F, -9.4559F, -4.2794F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(90, 141).addBox(-10.0F, -9.4559F, -4.2794F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(16, 150).addBox(8.0F, -8.4559F, 2.7206F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(30, 150).addBox(-10.0F, -8.4559F, 2.7206F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(56, 129).addBox(-10.0F, -9.4559F, -18.2794F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(80, 129).addBox(8.0F, -9.4559F, -18.2794F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(98, 69).addBox(-10.0F, -4.4559F, 14.7206F, 20.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-8.0F, 0.5441F, -18.2794F, 16.0F, 2.0F, 33.0F, new CubeDeformation(0.0F))
                .texOffs(104, 129).addBox(-13.0F, -0.4559F, -15.2794F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(124, 129).addBox(9.0F, -0.4559F, -15.2794F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(140, 141).addBox(-13.0F, -1.4559F, -14.2794F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(144, 130).addBox(9.0F, -1.4559F, -14.2794F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(144, 125).addBox(-13.0F, 5.5441F, -14.2794F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(144, 135).addBox(9.0F, 5.5441F, -14.2794F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(72, 150).addBox(-13.0F, 0.5441F, -16.2794F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(82, 150).addBox(9.0F, 0.5441F, -16.2794F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(150, 77).addBox(-13.0F, 0.5441F, -9.2794F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(150, 82).addBox(9.0F, 0.5441F, -9.2794F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(108, 141).addBox(-12.0F, 1.5441F, 11.7206F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(150, 87).addBox(-12.0F, 1.5441F, 15.7206F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(92, 150).addBox(-12.0F, 1.5441F, 10.7206F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(140, 146).addBox(-12.0F, 5.5441F, 11.7206F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(108, 149).addBox(-12.0F, 0.5441F, 11.7206F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(124, 141).addBox(8.0F, 1.5441F, 11.7206F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 150).addBox(8.0F, 0.5441F, 11.7206F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(150, 97).addBox(8.0F, 1.5441F, 15.7206F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(150, 92).addBox(8.0F, 1.5441F, 10.7206F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(124, 149).addBox(8.0F, 5.5441F, 11.7206F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(96, 125).addBox(-8.0F, 2.5441F, 12.7206F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 125).addBox(-9.0F, 1.5441F, -13.2794F, 18.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.4559F, 4.2794F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float targetRotation = (float) Math.toRadians(((CannonEntity) entity).weapon.getRotationX());
        this.Barrel.xRot = Mth.rotLerp(ageInTicks-entity.tickCount, ((CannonEntity) entity).oldXRot, targetRotation);
        //this.Barrel.xRot = targetRotation;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Barrel.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return Base;
    }
}