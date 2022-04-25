package io.github.tt432.emerald.data;

import io.github.tt432.emerald.Emerald;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EmeraldSounds {

    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Emerald.MOD_ID);

    public static final RegistryObject<SoundEvent> EMERALD_TABLET_THROW = SOUNDS.register("item.emerald_tablet.throw",
            () -> new SoundEvent(new ResourceLocation(Emerald.MOD_ID,"item.emerald_tablet.throw")));

    public static final RegistryObject<SoundEvent> EMERALD_TABLET_HIT = SOUNDS.register("item.emerald_tablet.hit",
            () -> new SoundEvent(new ResourceLocation(Emerald.MOD_ID,"item.emerald_tablet.hit")));

    public static final RegistryObject<SoundEvent> EMERALD_TABLET_HIT_GROUND = SOUNDS.register("item.emerald_tablet.hit_ground",
            () -> new SoundEvent(new ResourceLocation(Emerald.MOD_ID,"item.emerald_tablet.hit_ground")));

    public static void register(IEventBus bus) {
        SOUNDS.register(bus);
    }


}
