package io.github.tt432.emerald;

import io.github.tt432.emerald.data.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;

@Mod(Emerald.MOD_ID)
public class Emerald {
    public static final String MOD_ID = "emerald";

    public static final CreativeModeTab ITEM_TAB = new CreativeModeTab(MOD_ID) {
        @Override
        @NotNull
        public ItemStack makeIcon() {
            return new ItemStack(EmeraldItems.EMERALD_TABLET.get());
        }
    };

    public Emerald() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EmeraldItems.register(bus);
        EmeraldEntities.register(bus);
        EmeraldSounds.register(bus);
    }
}
