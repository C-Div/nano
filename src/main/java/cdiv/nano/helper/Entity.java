package cdiv.nano.helper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class Entity {
    @Nullable
    public static ItemStack getHeldItemStack(LivingEntity entity) {
        return entity.getStackInHand(entity.getActiveHand());
    }

    public static boolean isVanillaEntity(EntityType<?> entityType) {
        Identifier identifier = Registries.ENTITY_TYPE.getId(entityType);
        return identifier.getNamespace().equals("minecraft");
    }

    @SuppressWarnings("unused")
    public static boolean isVanillaEntity(net.minecraft.entity.Entity entity) {
        return isVanillaEntity(entity.getType());
    }

    public static boolean isModdedEntity(EntityType<?> entityType) {
        return !isVanillaEntity(entityType);
    }

    public static boolean isModdedEntity(net.minecraft.entity.Entity entity) {
        return isModdedEntity(entity.getType());
    }
}
