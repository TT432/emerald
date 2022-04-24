package io.github.tt432.emerald.item;

import io.github.tt432.emerald.Emerald;
import io.github.tt432.emerald.entity.EmeraldTabletEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class EmeraldTabletItem extends Item {

    public EmeraldTabletItem() {
        super(new Item.Properties().tab(Emerald.ITEM_TAB).stacksTo(1).rarity(Rarity.RARE).fireResistant());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        EmeraldTabletEntity tablet = new EmeraldTabletEntity(worldIn, playerIn);
        tablet.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 2.5F, 1.0F);
        if (playerIn.getAbilities().instabuild) {
            tablet.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }
        worldIn.addFreshEntity(tablet);
        if (!playerIn.getAbilities().instabuild) {
            playerIn.getInventory().removeItem(itemStack);
        }
        return InteractionResultHolder.consume(itemStack);
    }


}
