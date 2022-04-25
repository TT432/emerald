package io.github.tt432.emerald.world;

import io.github.tt432.emerald.data.EmeraldDamageSources;
import io.github.tt432.emerald.data.EmeraldEntities;
import io.github.tt432.emerald.data.EmeraldItems;
import io.github.tt432.emerald.data.EmeraldSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class EmeraldTabletEntity extends AbstractArrow {

    private boolean dealtDamage;
    public EmeraldTabletEntity(EntityType<? extends EmeraldTabletEntity> type, Level world) {
        super(type, world);
    }

    public EmeraldTabletEntity(Level worldIn, LivingEntity owner){
        super(EmeraldEntities.EMERALD_TABLET.get(), owner, worldIn);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }
        super.tick();
    }

    @Override
    @Nullable
    protected EntityHitResult findHitEntity(Vec3 p_213866_1_, Vec3 p_213866_2_) {
        return this.dealtDamage ? null : super.findHitEntity(p_213866_1_, p_213866_2_);
    }

    @Override
    protected void onHitEntity(EntityHitResult ray) {
        Entity target = ray.getEntity();
        Vec3 mov = this.getDeltaMovement();
        final float baseDamage = 10f;

        Entity owner = this.getOwner();
        DamageSource damageSource = EmeraldDamageSources.EMERALD_TABLET(owner, this);
        this.dealtDamage = true;
        if (target.hurt(damageSource, (float) (baseDamage * mov.length()))) {
            if (target.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (target instanceof LivingEntity targetLiving) {
                if (owner instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(targetLiving, owner);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)owner, targetLiving);
                }
                this.doPostHurtEffects(targetLiving);
            }
        }
        this.setDeltaMovement(mov.multiply(-0.01D, -0.1D, -0.01D));
        this.playSound(EmeraldSounds.EMERALD_TABLET_HIT.get(), 1.0F, 1.0F);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return EmeraldSounds.EMERALD_TABLET_HIT_GROUND.get();
    }
    @Override
    public void playerTouch(Player playerIn) {
        Entity owner = this.getOwner();
        if (owner == null || owner.getUUID() == playerIn.getUUID()) {
            super.playerTouch(playerIn);
        }
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != AbstractArrow.Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(EmeraldItems.EMERALD_TABLET.get());
    }


    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.dealtDamage = nbt.getBoolean("DealtDamage");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("DealtDamage", this.dealtDamage);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public boolean shouldRender(double p_145770_1_, double p_145770_3_, double p_145770_5_) {
        return true;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
