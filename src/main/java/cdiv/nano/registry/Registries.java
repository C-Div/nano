package cdiv.nano.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;

public class Registries {
    public static final UniqueRegistry<EntityType<?>> PathfindingScaling = new UniqueRegistry<>(net.minecraft.registry.Registries.ENTITY_TYPE::getId);
    public static final UniqueRegistry<EntityType<?>> LootScaling = new UniqueRegistry<>(net.minecraft.registry.Registries.ENTITY_TYPE::getId);
    public static final UniqueRegistry<Item> FoodScaling = new UniqueRegistry<>(net.minecraft.registry.Registries.ITEM::getId);
}
