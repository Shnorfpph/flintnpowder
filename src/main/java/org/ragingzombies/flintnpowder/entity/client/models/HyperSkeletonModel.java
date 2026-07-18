package org.ragingzombies.flintnpowder.entity.client.models;

import com.livelandr.flintcore.core.guns.GunBase;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class HyperSkeletonModel<T extends LivingEntity> extends HumanoidModel<T> {
    public final ModelPart jacket;
    public final ModelPart leftSleeve;
    public final ModelPart rightSleeve;
    public final ModelPart leftPants;
    public final ModelPart rightPants;

    public HyperSkeletonModel(ModelPart pRoot) {
        super(pRoot);
        this.jacket = pRoot.getChild("jacket");
        this.leftSleeve = pRoot.getChild("left_sleeve");
        this.rightSleeve = pRoot.getChild("right_sleeve");
        this.leftPants = pRoot.getChild("left_pants");
        this.rightPants = pRoot.getChild("right_pants");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("jacket",
                net.minecraft.client.model.geom.builders.CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
                net.minecraft.client.model.geom.PartPose.ZERO);

        partdefinition.addOrReplaceChild("left_sleeve",
                net.minecraft.client.model.geom.builders.CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
                net.minecraft.client.model.geom.PartPose.ZERO);

        partdefinition.addOrReplaceChild("right_sleeve",
                net.minecraft.client.model.geom.builders.CubeListBuilder.create().texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
                net.minecraft.client.model.geom.PartPose.ZERO);

        partdefinition.addOrReplaceChild("left_pants",
                net.minecraft.client.model.geom.builders.CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
                net.minecraft.client.model.geom.PartPose.ZERO);

        partdefinition.addOrReplaceChild("right_pants",
                net.minecraft.client.model.geom.builders.CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
                net.minecraft.client.model.geom.PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

        this.jacket.copyFrom(this.body);
        this.leftSleeve.copyFrom(this.leftArm);
        this.rightSleeve.copyFrom(this.rightArm);
        this.leftPants.copyFrom(this.leftLeg);
        this.rightPants.copyFrom(this.rightLeg);

        ItemStack mainHandItem = pEntity.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack offHandItem = pEntity.getItemInHand(InteractionHand.OFF_HAND);

        if (!mainHandItem.isEmpty()) {
            this.rightArmPose = getArmPose(pEntity, InteractionHand.MAIN_HAND, mainHandItem);
        }
    }

    private static final HumanoidModel.ArmPose GUN_AIM = HumanoidModel.ArmPose.create("GUN_AIM", true, (model, entity, arm) -> {
        if (arm == HumanoidArm.RIGHT) {
            model.rightArm.xRot = model.head.xRot - (float) Math.PI / 2F;
            model.rightArm.yRot = model.head.yRot;

            model.rightArm.x = -4;
            model.rightArm.z = -1;

            model.leftArm.xRot = model.head.xRot - (float) Math.PI / 2F;
            model.leftArm.yRot = model.body.yRot / 2F + (float) Math.PI / 4F ;
        } else {
            model.leftArm.xRot = model.head.xRot - (float) Math.PI / 2F;
            model.leftArm.yRot = model.head.yRot;

            model.leftArm.x = 4;
            model.leftArm.z = -1;

            model.rightArm.xRot = model.head.xRot - (float) Math.PI / 2F;
            model.rightArm.yRot = model.body.yRot / 2F - (float) Math.PI / 4F ;
        }
    });
    private static final HumanoidModel.ArmPose GUN_RELOAD = HumanoidModel.ArmPose.create("GUN_RELOAD", true, (model, entity, arm) -> {
        if (arm == HumanoidArm.RIGHT) {
            model.rightArm.xRot = (float) (-Math.PI*0.25F);
            model.rightArm.yRot = (float) -(Math.PI*0.15F);
            model.rightArm.zRot = (float) -(Math.PI*0.05F);

            model.leftArm.xRot = (float) (-Math.PI*0.25F);
            model.leftArm.yRot = (float) (Math.PI*0.25F);
        } else {
            model.leftArm.xRot = (float) (-Math.PI*0.25F);
            model.leftArm.yRot = (float) (Math.PI*0.15F);
            model.leftArm.zRot = (float) (Math.PI*0.05F);

            model.rightArm.xRot = (float) (-Math.PI*0.25F);
            model.rightArm.yRot = (float) -(Math.PI*0.25F);
        }
    });

    // Pose setter
    public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            if (itemStack.getOrCreateTag().getBoolean("IsAiming")) {
                return GUN_AIM;
            } else {
                return GUN_RELOAD;
            }
        }
        return HumanoidModel.ArmPose.EMPTY;
    }

    @Override
    protected java.lang.Iterable<ModelPart> bodyParts() {
        return com.google.common.collect.Iterables.concat(super.bodyParts(), java.util.List.of(this.jacket, this.leftSleeve, this.rightSleeve, this.leftPants, this.rightPants));
    }
}
