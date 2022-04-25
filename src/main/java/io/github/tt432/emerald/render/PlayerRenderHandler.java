package io.github.tt432.emerald.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import io.github.tt432.emerald.Emerald;
import io.github.tt432.emerald.data.EmeraldItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(Dist.CLIENT)
public class PlayerRenderHandler {
    static boolean init;
    static TabletHoldModel<?> model;

    private static final ResourceLocation TEXTURE = new ResourceLocation(Emerald.MOD_ID, "textures/entity/emerald_tablet.png");

    @SubscribeEvent
    public static void playerRender(RenderPlayerEvent.Pre event) {
        AbstractClientPlayer player = (AbstractClientPlayer) event.getPlayer();

        if (!init) {
            model = new TabletHoldModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(TabletHoldModel.LAYER_LOCATION));
            init = true;
        }

        if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == EmeraldItems.EMERALD_TABLET.get()) {
            PoseStack poseStack = event.getPoseStack();

            poseStack.pushPose();

            poseStack.scale(-1.0F, -1.0F, 1.0F);
            poseStack.translate(0.0D, (double)-1.501F, 0.0D);

            poseStack.mulPose(Vector3f.YP.rotationDegrees(player.yBodyRot + 180));

            RenderType renderType = RenderType.itemEntityTranslucentCull(player.getSkinTextureLocation());
            VertexConsumer vertexConsumer = event.getMultiBufferSource().getBuffer(renderType);
            model.renderToBuffer(poseStack, vertexConsumer, event.getPackedLight(), OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);

            RenderType renderType2 = RenderType.itemEntityTranslucentCull(TEXTURE);
            VertexConsumer vertexConsumer2 = event.getMultiBufferSource().getBuffer(renderType2);
            model.renderToBuffer2(poseStack, vertexConsumer2, event.getPackedLight(), OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);

            poseStack.popPose();

            event.setCanceled(true);
        }
    }
}
