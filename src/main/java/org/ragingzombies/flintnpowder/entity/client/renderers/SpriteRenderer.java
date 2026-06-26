package org.ragingzombies.flintnpowder.entity.client.renderers;

import com.livelandr.flintcore.Flintcore;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.joml.Matrix4f;
import org.ragingzombies.flintnpowder.Flintnpowder;
import org.ragingzombies.flintnpowder.entity.custom.SmokeEntity;

public class SpriteRenderer<T extends SmokeEntity> extends EntityRenderer<T> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Flintnpowder.MOD_ID, "textures/entities/smoke.png");

    public SpriteRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
    private static void vertex(VertexConsumer consumer, Matrix4f matrix, float x, float y, float z, float u, float v, int light, int r, int g, int b, int a) {
        consumer.vertex(matrix, x, y, z)
                .color(r, g, b, a)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(0.0F, 1.0F, 0.0F)
                .endVertex();
    }

    @Override
    public void render(T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entity)));
        PoseStack.Pose lastPose = poseStack.last();
        Matrix4f matrix4f = lastPose.pose();

        int r = entity.getR();
        int g = entity.getG();
        int b = entity.getB();
        int a = entity.a;

        float frame = (float) ((entity.frameOffset + Math.floor(entity.tickCount / (20F * 0.75F))) % 4);

        float minV = 0.25F * frame;
        float maxV = 0.25F * (1F+frame);

        vertex(vertexConsumer, matrix4f, -1, -1, 0.0F, 0.0F, maxV, packedLight, r, g, b, a);
        vertex(vertexConsumer, matrix4f, 1, -1, 0.0F, 1F, maxV, packedLight, r, g, b, a);
        vertex(vertexConsumer, matrix4f, 1, 1, 0.0F, 1F, minV, packedLight, r, g, b, a);
        vertex(vertexConsumer, matrix4f, -1, 1, 0.0F, 0.0F, minV, packedLight, r, g, b, a);

        float size = entity.spriteSize;
        poseStack.scale(size, size, size);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return TEXTURE;
    }
}
