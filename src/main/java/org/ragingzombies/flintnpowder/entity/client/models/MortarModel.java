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

public class MortarModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart mortar;

	public MortarModel(ModelPart root) {
		this.mortar = root.getChild("Morta");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Morta = partdefinition.addOrReplaceChild("Morta", CubeListBuilder.create().texOffs(0, 0).addBox(7.75F, -5.0F, -6.0F, 0.5F, 13.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.0F, 7.0F, -15.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 16.0F, 8.0F));

		PartDefinition cube_r1 = Morta.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -17.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -17.0F, -1.0F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 7.0F, -13.0F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		mortar.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return mortar;
	}
}