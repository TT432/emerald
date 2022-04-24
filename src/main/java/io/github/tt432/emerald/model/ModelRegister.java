package io.github.tt432.emerald.model;

import io.github.tt432.emerald.entity.EmeraldEntities;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelRegister {
    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        ForgeHooksClient.registerLayerDefinition(TabletHoldModel.LAYER_LOCATION, TabletHoldModel::createBodyLayer);
        ForgeHooksClient.registerLayerDefinition(EmeraldTabletModel.LAYER_LOCATION, EmeraldTabletModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        registerEntityRenderer(EmeraldEntities.EMERALD_TABLET.get(), EmeraldTabletRenderer::new);
    }
    private static <E extends Entity> void registerEntityRenderer(EntityType<E> type, EntityRendererProvider<E> renderer){
        EntityRenderers.register(type, renderer);
    }
}
