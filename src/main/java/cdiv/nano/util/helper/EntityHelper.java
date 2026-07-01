package cdiv.nano.util.helper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.OptionalDouble;

public class EntityHelper {
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

    /**
     * <p>Returns the base value of the given attribute for the target entity, or empty if the entity does not have that attribute</p>
     * @param entity The target entity
     * @param attribute The target attribute
     * @return Optional of the base value of the target attribute
     */
    public static OptionalDouble getAttributeBaseValue(LivingEntity entity, RegistryEntry<EntityAttribute> attribute) {
        EntityAttributeInstance entityAttributeInstance = entity.getAttributeInstance(attribute);

        if (entityAttributeInstance == null)
            return OptionalDouble.empty();

        return OptionalDouble.of(entityAttributeInstance.getBaseValue());
    }
}
