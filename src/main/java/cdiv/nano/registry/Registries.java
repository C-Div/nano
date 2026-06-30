package cdiv.nano.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;

public class Registries {
    public static UniqueRegistry<EntityType<?>> PathfindingScaling = new UniqueRegistry<>(net.minecraft.registry.Registries.ENTITY_TYPE::getId);
    public static UniqueRegistry<Item> FoodScaling = new UniqueRegistry<>(net.minecraft.registry.Registries.ITEM::getId);
}
