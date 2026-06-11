package org.ragingzombies.flintnpowder.entity.client.models;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.ragingzombies.flintnpowder.Flintnpowder;
import org.ragingzombies.flintnpowder.entity.custom.MortarEntity;

// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class MortarModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart barrel;
	private final ModelPart ironsight;
	private final ModelPart bone2;

	public MortarModel(ModelPart root) {
		this.barrel = root.getChild("barrel");
		this.ironsight = this.barrel.getChild("iron sight");
		this.bone2 = root.getChild("bone2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition barrel = partdefinition.addOrReplaceChild("barrel", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(32, 31).addBox(-1.0F, 2.0F, 10.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(32, 20).addBox(-2.0F, 2.0F, 5.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 23.0F, -7.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition ironsight = barrel.addOrReplaceChild("iron sight", CubeListBuilder.create().texOffs(32, 27).addBox(-1.1667F, -1.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(32, 38).addBox(-1.1667F, -5.5F, -1.5F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(36, 38).addBox(-0.1667F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.8333F, 1.5F, 7.5F));

		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -1.0F, -11.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-8.0F, -11.0F, 4.0F, 16.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(32, 35).addBox(-1.0F, -2.0F, -8.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.0F, 0.0F, 3.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(7.0F, 0.0F, 3.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.barrel.xRot = (float) Math.toRadians(180 + ((MortarEntity) entity).weapon.rotationX);
		this.ironsight.xRot = (float) Math.toRadians(90 + ((MortarEntity) entity).weapon.rotationX);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		barrel.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return bone2;
	}
}