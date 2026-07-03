package cdiv.nano;

import cdiv.nano.util.helper.EntityHelper;
import cdiv.nano.util.helper.ItemHelper;
import cdiv.nano.scale.modifiers.FilteredScaleModifier;
import cdiv.nano.scale.modifiers.NanoScaleModifier;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
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
                        existingItemScale = ItemHelper.resolveScale(itemStack, () -> (double) entityScaleData.getTargetScale());
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
                (ScaleData scaleData, LivingEntity entity, float modifiedScale, float delta) -> { // FUTURE: Extract this to a generic method
                    OptionalDouble baseDamageOptional = EntityHelper.getAttributeBaseValue(entity, EntityAttributes.GENERIC_ATTACK_DAMAGE);

                    if (baseDamageOptional.isEmpty())
                        return modifiedScale;

                    float baseDamage = (float) baseDamageOptional.getAsDouble();
                    float extraDamage = (float) ItemHelper.getAttributeValue(EntityHelper.getHeldItemStack(entity), EntityAttributes.GENERIC_ATTACK_DAMAGE)
                        .orElse(0.0D);

                    float totalDamage = baseDamage + extraDamage;

                    if (totalDamage == 0.0F) // Prevent division by 0
                        return modifiedScale;

                    float scale = ScaleTypes.BASE.getScaleData(entity).getScale(delta);
                    float itemScale = ScaleTypes.HELD_ITEM.getScaleData(entity).getScale(delta);
                    float totalScaledDamage = baseDamage * scale + extraDamage * itemScale;

                    return modifiedScale + (totalScaledDamage / totalDamage - 1.0F); // Subtract one since we're adding instead of multiplying
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
                    OptionalDouble speed = ItemHelper.getAttributeValue(EntityHelper.getHeldItemStack(entity), EntityAttributes.GENERIC_ATTACK_SPEED);

                    if (speed.isEmpty() || speed.getAsDouble() == 0.0)
                        return modifiedScale;

                    return modifiedScale + (1.0F / ScaleTypes.HELD_ITEM.getScaleData(entity).getScale(delta) - 1.0F);
                }
            ),
            Map.entry(cdiv.nano.api.config.ScaleModifiers.attackSpeedScaleModifierEnabled, true)
        )
    );

    @SuppressWarnings("CommentedOutCode")
    public static final ScaleModifier MINING_SPEED_MULTIPLIER = register(
        "mining_speed_multiplier",
        new NanoScaleModifier<>(
            new FilteredScaleModifier<>(
                Set.of(ScaleTypes.MINING_SPEED),
                Set.of(PlayerEntity.class),
                (ScaleData scaleData, PlayerEntity entity, float modifiedScale, float delta) -> { // Why the fuck is tool mining speed so complicated
                    ItemStack itemStack = EntityHelper.getHeldItemStack(entity);

                    if (itemStack == null)
                        return modifiedScale;

                    float scale = ScaleTypes.BASE.getScaleData(entity).getScale(delta);
                    OptionalDouble baseBlockBreakSpeedOptional = EntityHelper.getAttributeBaseValue(entity, EntityAttributes.PLAYER_BLOCK_BREAK_SPEED);

                    if (baseBlockBreakSpeedOptional.isEmpty())
                        return modifiedScale;

                    float baseBlockBreakSpeed = (float) baseBlockBreakSpeedOptional.getAsDouble();
                    float scaledBlockBreakSpeed = baseBlockBreakSpeed * scale;

                    ToolComponent toolComponent = itemStack.get(DataComponentTypes.TOOL);
                    BlockPos miningPosition = SharedInteractions.getMiningPosition(entity).orElse(null);
//                    Nano.LOGGER.info("Block Break Speed: {}, Scaled Block Break Speed: {}", baseBlockBreakSpeed, scaledBlockBreakSpeed);

                    if (toolComponent == null) {
//                        Nano.LOGGER.info("Added: {}, Total: {}", scaledBlockBreakSpeed / baseBlockBreakSpeed - 1, modifiedScale + scaledBlockBreakSpeed / baseBlockBreakSpeed - 1);
                        return modifiedScale + scaledBlockBreakSpeed / baseBlockBreakSpeed - 1;
                    }

                    float itemScale = ScaleTypes.HELD_ITEM.getScaleData(entity).getScale(delta);
                    float baseItemBreakSpeed = (miningPosition == null) ? toolComponent.defaultMiningSpeed() : toolComponent.getSpeed(entity.getWorld().getBlockState(miningPosition));
                    float scaledItemBreakSpeed = baseItemBreakSpeed * itemScale;

                    float totalMiningSpeed = baseItemBreakSpeed * baseBlockBreakSpeed;
                    float totalScaledMiningSpeed = scaledItemBreakSpeed * scaledBlockBreakSpeed;
//                    Nano.LOGGER.info("Item Break Speed: {}, Scaled Item Break Speed: {}",  baseItemBreakSpeed, scaledItemBreakSpeed);
//                    Nano.LOGGER.info("Added: {}, Total: {}", totalScaledMiningSpeed / totalMiningSpeed - 1, modifiedScale + totalScaledMiningSpeed / totalMiningSpeed - 1);

                    return modifiedScale + totalScaledMiningSpeed / totalMiningSpeed - 1;
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
            Nano.id(name),
            scaleModifier
        );
    }
}
