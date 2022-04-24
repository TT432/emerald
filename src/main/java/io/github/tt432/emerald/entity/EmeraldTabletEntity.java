package io.github.tt432.emerald.entity;

import io.github.tt432.emerald.item.EmeraldItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class EmeraldTabletEntity extends AbstractArrow {

    protected EmeraldTabletEntity(EntityType<? extends EmeraldTabletEntity> type, Level world) {
        super(type, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(EmeraldItems.EMERALD_TABLET.get());
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
