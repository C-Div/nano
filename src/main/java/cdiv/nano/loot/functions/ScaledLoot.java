package cdiv.nano.loot.functions;

import cdiv.nano.Components;
import cdiv.nano.LootFunctions;
import cdiv.nano.api.config.Loot;
import cdiv.nano.api.event.LootEvents;
import cdiv.nano.helper.Item;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

import java.util.*;

public class ScaledLoot extends ConditionalLootFunction {
    public static final MapCodec<ScaledLoot> CODEC =
        RecordCodecBuilder.mapCodec(instance ->
            addConditionsField(instance)
                .apply(instance, ScaledLoot::new)
        );

    public ScaledLoot(List<LootCondition> conditions) {
        super(conditions);
    }

    @Override
    protected ItemStack process(ItemStack stack, LootContext context) {
        if (!Loot.lootScalingEnabled.get() || Boolean.TRUE.equals(stack.get(Components.LOOT_SCALED)))
            return stack;

        stack.set(Components.LOOT_SCALED, true);
        stack.set(Components.ITEM_SCALE, 1.0D);

        Entity entity = context.get(LootContextParameters.THIS_ENTITY);

        if (entity == null)
            return stack;

        ScaleData scaleData = ScaleTypes.BASE.getScaleData(entity);

        if (scaleData.isReset())
            return stack;

        final double currentScale = scaleData.getScale();
        final double multiplier = LootEvents.SCALE.invoker().scaleLoot(stack, context);

        if (multiplier <= 0.0F)
            return stack;

        final double finalScale = currentScale * multiplier;
        stack.set(Components.ITEM_SCALE, Item.resolveScale(stack, finalScale));

        if (stack.getMaxCount() == 1)
            return stack;

        stack.setCount((int) Math.min(Integer.MAX_VALUE, Math.round(stack.getCount() * finalScale)));
        return stack;
    }

    @Override
    public LootFunctionType<? extends ConditionalLootFunction> getType() {
        return LootFunctions.SCALED_LOOT;
    }
}