package io.github.tt432.emerald.data;

import io.github.tt432.emerald.Emerald;
import io.github.tt432.emerald.world.EmeraldTabletEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EmeraldEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Emerald.MOD_ID);

    public static final RegistryObject<EntityType<EmeraldTabletEntity>> EMERALD_TABLET = ENTITY_TYPES.register("emerald_tablet",
            () -> EntityType.Builder.<EmeraldTabletEntity>of(EmeraldTabletEntity::new, MobCategory.MISC)
                    .sized(0.6F, 0.6F).fireImmune().build("emerald_tablet"));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
