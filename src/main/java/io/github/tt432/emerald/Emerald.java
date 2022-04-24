package io.github.tt432.emerald;

import io.github.tt432.emerald.entity.EmeraldEntities;
import io.github.tt432.emerald.item.EmeraldItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(Emerald.MOD_ID)
public class Emerald {
    public static final String MOD_ID = "emerald";

    public static final CreativeModeTab ITEM_TAB = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(EmeraldItems.EMERALD_TABLET.get());
        }
    };

    public Emerald() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EmeraldItems.register(bus);
        EmeraldEntities.register(bus);
    }
}
