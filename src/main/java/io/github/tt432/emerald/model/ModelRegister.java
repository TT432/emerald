package io.github.tt432.emerald.model;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelRegister {
    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        ForgeHooksClient.registerLayerDefinition(steve.LAYER_LOCATION, steve::createBodyLayer);
    }
}
