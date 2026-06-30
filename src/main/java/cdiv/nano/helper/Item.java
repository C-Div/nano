package cdiv.nano.helper;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.OptionalDouble;
import java.util.function.DoubleSupplier;

public class Item {
    public static double resolveScale(ItemStack stack, final double scale) {
        net.minecraft.item.Item item = stack.getItem();

        return (item instanceof BlockItem || item instanceof BucketItem)
            ? 1
            : scale;
    }

    public static double resolveScale(ItemStack stack, final DoubleSupplier supplier) {
        net.minecraft.item.Item item = stack.getItem();

        return (item instanceof BlockItem || item instanceof BucketItem)
            ? 1
            : supplier.getAsDouble();
    }

    public static OptionalDouble getFinalAttributeValue(@Nullable ItemStack stack, RegistryEntry<EntityAttribute> attribute) {
        if (stack == null)
            return OptionalDouble.empty();

        AttributeModifiersComponent attributeModifiers = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);

        if (attributeModifiers == null)
            return OptionalDouble.empty();

        double result = 0.0;

        for (AttributeModifiersComponent.Entry entry : attributeModifiers.modifiers()) {
            if (!entry.attribute().equals(attribute))
                continue;

            result += entry.modifier().value();
        }

        return OptionalDouble.of(result);
    }

    /**
     * @deprecated Use generic {@link #getFinalAttributeValue(ItemStack, RegistryEntry)} instead
     */
    @Deprecated
    public static OptionalDouble getAttackDamage(ItemStack stack) {
        return getFinalAttributeValue(stack, EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    public static boolean isVanillaItem(net.minecraft.item.Item item) {
        Identifier identifier = Registries.ITEM.getId(item);
        return identifier.getNamespace().equals("minecraft");
    }

    @SuppressWarnings("unused")
    public static boolean isVanillaItem(ItemStack itemStack) {
        return isVanillaItem(itemStack.getItem());
    }

    public static boolean isModdedItem(net.minecraft.item.Item item) {
        return !isVanillaItem(item);
    }

    @SuppressWarnings("unused")
    public static boolean isModdedItem(ItemStack itemStack) {
        return isModdedItem(itemStack.getItem());
    }
}
