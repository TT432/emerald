package io.github.tt432.emerald.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.tt432.emerald.Emerald;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

/**
 * @author LocusAzzurro
 * Made with Blockbench 4.2.3
 */
public class EmeraldTabletModel<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Emerald.MOD_ID, "emerald_tablet"), "main");
    private final ModelPart tablet;

    public EmeraldTabletModel(ModelPart root) {
        this.tablet = root.getChild("tablet");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition tablet = partdefinition.addOrReplaceChild("tablet", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -4.5F, -0.5F, 12.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-7.0F, 4.5F, -0.5F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-7.0F, -6.5F, -0.5F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 18).addBox(-6.0F, 6.5F, -0.5F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-6.0F, -7.5F, -0.5F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.5F, 0.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.translate(0f, -0.75f, 0f);
        tablet.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

}
