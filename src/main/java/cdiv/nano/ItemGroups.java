package cdiv.nano;

import cdiv.nano.util.helper.TranslationHelper;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ItemGroups {
    @SuppressWarnings("unused")
    public static final ItemGroup NANO = register(
        "nano", Items.SCALE_COMBINER,
        Items.SCALE_COMBINER,
        Items.SCALE_NORMALIZER
    );

    @SafeVarargs
    public static <GenericItem extends Item>
    ItemGroup register(String name, GenericItem icon, GenericItem... items) {
        Identifier identifier = Nano.id(name);
        ItemGroup itemGroup = FabricItemGroup.builder()
            .icon(() -> new ItemStack(icon))
            .displayName(TranslationHelper.itemGroup(identifier.getPath()))
            .build();

        Registry.register(Registries.ITEM_GROUP, identifier, itemGroup);

        ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(Registries.ITEM_GROUP.getKey(), identifier)).register(groupEntries -> {
            for (GenericItem item : items) {
                groupEntries.add(item);
            }
        });

        return itemGroup;
    }

    public static void initialize() {}
}
