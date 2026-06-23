package cdiv.nano.registry;

import net.minecraft.entity.EntityType;

public class Registries {
    public static UniqueRegistry<EntityType<?>> PathfindingScaling = new UniqueRegistry<>(net.minecraft.registry.Registries.ENTITY_TYPE::getId);
}
