package io.github.tt432.emerald.item;

import io.github.tt432.emerald.Emerald;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 **/
public class EmeraldItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Emerald.MOD_ID);

    public static final RegistryObject<Item> EMERALD_TABLET = ITEMS.register("emerald_tablet", () -> new Item(defaultProperties()));

    private static Item.Properties defaultProperties() {
        return new Item.Properties().tab(Emerald.ITEM_TAB);
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
