package io.github.tt432.emerald.data;

import io.github.tt432.emerald.world.EmeraldTabletEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class EmeraldDamageSources {

    public static DamageSource EMERALD_TABLET (@Nullable Entity owner, EmeraldTabletEntity tablet){
        return new IndirectEntityDamageSource("emerald_tablet", tablet, (owner == null ? tablet : owner)).setProjectile();
    }

}
