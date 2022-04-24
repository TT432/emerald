package io.github.tt432.emerald.model;

import io.github.tt432.emerald.Emerald;
import io.github.tt432.emerald.entity.EmeraldTabletEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EmeraldTabletRenderer extends EntityRenderer<EmeraldTabletEntity> {

    private final EmeraldTabletModel<EmeraldTabletEntity> model;
    private static final ResourceLocation TEXTURE = new ResourceLocation(Emerald.MOD_ID, "textures/entity/steel_spear.png");

    public EmeraldTabletRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new EmeraldTabletModel<>(context.bakeLayer(EmeraldTabletModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(EmeraldTabletEntity entity) {
        return TEXTURE;
    }
}
