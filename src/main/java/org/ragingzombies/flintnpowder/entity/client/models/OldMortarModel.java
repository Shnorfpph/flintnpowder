package org.ragingzombies.flintnpowder.entity.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import org.ragingzombies.flintnpowder.entity.custom.MortarEntity;
import org.ragingzombies.flintnpowder.entity.custom.OldMortarEntity;


public class OldMortarModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart lathe;
	private final ModelPart bone;
	private final ModelPart cannon;

	public OldMortarModel(ModelPart root) {
		this.lathe = root.getChild("lathe");
		this.bone = this.lathe.getChild("bone");
		this.cannon = root.getChild("cannon");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition lathe = partdefinition.addOrReplaceChild("lathe", CubeListBuilder.create().texOffs(0, 82).addBox(8.0F, -3.1429F, -18.4286F, 4.0F, 12.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(45, 82).addBox(-12.0F, -3.1429F, -18.4286F, 4.0F, 12.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(97, 0).addBox(8.0F, -7.1429F, -17.4286F, 4.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(97, 21).addBox(-12.0F, -7.1429F, -17.4286F, 4.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 113).addBox(8.0F, -10.1429F, -15.4286F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(33, 113).addBox(-12.0F, -10.1429F, -15.4286F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(90, 82).addBox(8.0F, -1.1429F, -0.4286F, 4.0F, 10.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(93, 49).addBox(-12.0F, -1.1429F, -0.4286F, 4.0F, 10.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(17, 129).addBox(-12.0F, -0.1429F, 15.5714F, 4.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(32, 129).addBox(8.0F, -0.1429F, 15.5714F, 4.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(66, 124).addBox(-12.0F, -3.1429F, 3.5714F, 4.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(99, 124).addBox(8.0F, -3.1429F, 3.5714F, 4.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 49).addBox(-8.0F, 6.8571F, -14.4286F, 16.0F, 2.0F, 30.0F, new CubeDeformation(0.0F))
				.texOffs(90, 109).addBox(-8.0F, -3.1429F, -14.4286F, 16.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.1429F, -1.5714F));

		PartDefinition bone = lathe.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cannon = partdefinition.addOrReplaceChild("cannon", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -21.0833F, 16.0F, 16.0F, 32.0F, new CubeDeformation(0.0F))
				.texOffs(66, 113).addBox(-12.0F, -2.0F, -2.0833F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(47, 129).addBox(8.0F, -2.0F, -2.0833F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0833F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r1 = cannon.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 129).addBox(1.0F, -4.0F, -1.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.8F, 11.9F, -1.2654F, 0.0F, 0.0F));

		PartDefinition cube_r2 = cannon.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(93, 76).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 11.4F, 0.3054F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 144, 144);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.cannon.xRot = (float) Math.toRadians(((OldMortarEntity) entity).weapon.rotationX);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		lathe.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		cannon.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return lathe;
	}
}