package cdiv.nano;

import cdiv.nano.helper.Item;
import cdiv.nano.scale.modifiers.FilteredScaleModifier;
import cdiv.nano.scale.modifiers.NanoScaleModifier;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import virtuoel.pehkui.api.*;

import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;

public class ScaleModifiers {
    public static final ScaleModifier HELD_ITEM_MULTIPLIER = register(
        "held_item_multiplier",
        new NanoScaleModifier<>(
            new FilteredScaleModifier<>(
                Set.of(ScaleTypes.HELD_ITEM),
                Set.of(LivingEntity.class),
                (ScaleData scaleData, LivingEntity entity, float modifiedScale, float delta) -> {
                    ScaleData entityScaleData = ScaleTypes.BASE.getScaleData(entity);
                    Hand activeHand = entity.getActiveHand();
                    ItemStack itemStack = entity.getStackInHand(activeHand);

                    double entityScale = entityScaleData.getScale(delta);
                    Double existingItemScale = itemStack.get(Components.ITEM_SCALE);

                    if (existingItemScale == null) {
                        existingItemScale = Item.resolveScale(itemStack, () -> (double) entityScaleData.getTargetScale());
                        itemStack.set(Components.ITEM_SCALE, existingItemScale);
                    }

                    return (float) (modifiedScale * (existingItemScale / entityScale));
                }
            ),
            Map.entry(cdiv.nano.api.config.ScaleModifiers.heldItemScaleModifierEnabled, true),
            Map.entry(cdiv.nano.api.config.Item.itemScalingEnabled, true)
        )
    );

    public static final ScaleModifier ATTACK_DAMAGE_MULTIPLIER = register(
        "attack_damage_multiplier",
        new NanoScaleModifier<>(
            new FilteredScaleModifier<>(
                Set.of(ScaleTypes.ATTACK),
                Set.of(LivingEntity.class),
                (ScaleData scaleData, LivingEntity entity, float modifiedScale, float delta) -> {
                    float scale = ScaleTypes.BASE.getScaleData(entity).getScale(delta);
                    double result = modifiedScale * scale;
                    OptionalDouble damage = Item.getFinalAttributeValue(cdiv.nano.helper.Entity.getHeldItemStack(entity), EntityAttributes.GENERIC_ATTACK_DAMAGE);

                    if (damage.isEmpty() || damage.getAsDouble() == 0.0)
                        return (float) result;

                    return (float) (result * ScaleTypes.HELD_ITEM.getScaleData(entity).getScale(delta));
                }
            ),
            Map.entry(cdiv.nano.api.config.ScaleModifiers.attackDamageScaleModifierEnabled, true)
        )
    );

    public static final ScaleModifier ATTACK_SPEED_MULTIPLIER = register(
        "attack_speed_multiplier",
        new NanoScaleModifier<>(
            new FilteredScaleModifier<>(
                Set.of(ScaleTypes.ATTACK_SPEED),
                Set.of(LivingEntity.class),
                (ScaleData scaleData, LivingEntity entity, float modifiedScale, float delta) -> {
                    OptionalDouble speed = Item.getFinalAttributeValue(cdiv.nano.helper.Entity.getHeldItemStack(entity), EntityAttributes.GENERIC_ATTACK_SPEED);

                    if (speed.isEmpty() || speed.getAsDouble() == 0.0)
                        return modifiedScale;

                    return modifiedScale / ScaleTypes.HELD_ITEM.getScaleData(entity).getScale(delta);
                }
            ),
            Map.entry(cdiv.nano.api.config.ScaleModifiers.attackSpeedScaleModifierEnabled, true)
        )
    );

    public static final ScaleModifier MINING_SPEED_MULTIPLIER = register(
        "mining_speed_multiplier",
        new NanoScaleModifier<>(
            new FilteredScaleModifier<>(
                Set.of(ScaleTypes.MINING_SPEED),
                Set.of(LivingEntity.class),
                (ScaleData scaleData, LivingEntity entity, float modifiedScale, float delta) -> {
                    float scale = ScaleTypes.BASE.getScaleData(entity).getScale(delta);
                    double result = modifiedScale * scale;

                    ItemStack itemStack = cdiv.nano.helper.Entity.getHeldItemStack(entity);

                    if (itemStack == null || itemStack.get(DataComponentTypes.TOOL) == null)
                        return (float) result;

                    return (float) (result * ScaleTypes.HELD_ITEM.getScaleData(entity).getScale(delta));
                }
            ),
            Map.entry(cdiv.nano.api.config.ScaleModifiers.miningSpeedScaleModifierEnabled, true)
        )
    );

    public static final ScaleModifier KNOCKBACK_MULTIPLIER = register(
        "knockback_multiplier",
        new NanoScaleModifier<>(
            virtuoel.pehkui.api.ScaleModifiers.BASE_MULTIPLIER,
            Map.entry(cdiv.nano.api.config.ScaleModifiers.knockbackScaleModifierEnabled, true)
        )
    );

    public static final ScaleModifier DROPS_BASE_MULTIPLIER = register(
        "drops_base_multiplier",
        new NanoScaleModifier<>(
            virtuoel.pehkui.api.ScaleModifiers.BASE_MULTIPLIER,
            Map.entry(cdiv.nano.api.config.Item.itemDropScalingEnabled, false),
            Map.entry(cdiv.nano.api.config.ScaleModifiers.dropsBaseScaleModifierEnabled, true)
        )
    );

    private static ScaleModifier register(String name, ScaleModifier scaleModifier)
    {
        return ScaleRegistries.register(
            ScaleRegistries.SCALE_MODIFIERS,
            Identifier.of(Nano.MOD_ID, name),
            scaleModifier
        );
    }
}
