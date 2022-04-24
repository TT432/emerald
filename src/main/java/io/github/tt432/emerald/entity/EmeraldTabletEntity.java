package io.github.tt432.emerald.entity;

import io.github.tt432.emerald.item.EmeraldItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
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
        float damage = 10f; //todo add multiplier to speed

        Entity owner = this.getOwner();
        DamageSource damageSource = new IndirectEntityDamageSource("emerald_tablet", this, (owner == null ? this : owner)).setProjectile();
        this.dealtDamage = true;
        if (target.hurt(damageSource, damage)) {
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
        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
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
