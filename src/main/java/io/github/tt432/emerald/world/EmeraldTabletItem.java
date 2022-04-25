package io.github.tt432.emerald.world;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.tt432.emerald.Emerald;
import io.github.tt432.emerald.data.EmeraldSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author LocusAzzurro
 */
@ParametersAreNonnullByDefault
public class EmeraldTabletItem extends Item {

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    public EmeraldTabletItem() {
        super(new Item.Properties().tab(Emerald.ITEM_TAB).stacksTo(1).rarity(Rarity.RARE).fireResistant());
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 5.0D, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -3.0F, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    @NotNull
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        EmeraldTabletEntity tablet = new EmeraldTabletEntity(worldIn, playerIn);
        tablet.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.0F, 1.5F);
        if (playerIn.getAbilities().instabuild) {
            tablet.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }
        worldIn.addFreshEntity(tablet);
        worldIn.playSound(null, tablet, EmeraldSounds.EMERALD_TABLET_THROW.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        if (!playerIn.getAbilities().instabuild) {
            playerIn.getInventory().removeItem(itemStack);
        }
        return InteractionResultHolder.consume(itemStack);
    }

    @SuppressWarnings("deprecation")
    @NotNull
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

}
