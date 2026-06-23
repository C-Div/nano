package cdiv.nano;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item.Settings;

import java.util.function.Function;

import static cdiv.nano.Nano.LOGGER;
import static cdiv.nano.Nano.MOD_ID;

public class Items {
    public static final Item example = register("example", Item::new, new Item.Settings());

    public static void initialize() {
        LOGGER.debug("Registering items...");
    }

    public static <GenericItem extends Item>
    GenericItem register(String name, Function<Settings, GenericItem> itemFactory, Settings settings) {
        RegistryKey<Item> itemKey = getItemRegistryKey(name);
        GenericItem item = itemFactory.apply(settings);
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static <GenericItem extends Item>
    GenericItem register(String name, GenericItem item) {
        RegistryKey<Item> itemKey = getItemRegistryKey(name);
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static RegistryKey<Item> getItemRegistryKey(String name) {
        return RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(MOD_ID, name));
    }
}
