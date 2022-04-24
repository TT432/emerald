package io.github.tt432.emerald.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import io.github.tt432.emerald.Emerald;
import io.github.tt432.emerald.entity.EmeraldTabletEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class EmeraldTabletRenderer extends EntityRenderer<EmeraldTabletEntity> {

    private final EmeraldTabletModel<EmeraldTabletEntity> model;
    private static final ResourceLocation TEXTURE = new ResourceLocation(Emerald.MOD_ID, "textures/entity/emerald_tablet.png");

    public EmeraldTabletRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new EmeraldTabletModel<>(context.bakeLayer(EmeraldTabletModel.LAYER_LOCATION));
    }

    @Override
    public void render(EmeraldTabletEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        stack.pushPose();
        stack.translate(0f, -0.5f, 0f);
        stack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        stack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) + 90.0F));
        VertexConsumer vertexBuilder = ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(this.getTextureLocation(entity)), false, false);
        this.model.renderToBuffer(stack, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        stack.popPose();
        super.render(entity, entityYaw, partialTicks, stack, buffer, packedLight);
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(EmeraldTabletEntity entity) {
        return TEXTURE;
    }
}
