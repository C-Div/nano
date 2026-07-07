package cdiv.nano.client.integration;

import cdiv.nano.Components;
import cdiv.nano.api.config.Food;
import cdiv.nano.api.client.config.AppleSkin;
import cdiv.nano.util.helper.ItemHelper;
import cdiv.nano.registry.Registries;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import squeek.appleskin.api.AppleSkinApi;
import squeek.appleskin.api.event.FoodValuesEvent;
import virtuoel.pehkui.api.ScaleTypes;

public class AppleSkinIntegration implements AppleSkinApi {
    @Override
    public void registerEvents() {
        FoodValuesEvent.EVENT.register(foodValues -> {
            if (Boolean.FALSE.equals(AppleSkin.integrationEnabled.get())
                || (Boolean.FALSE.equals(Food.foodNutritionScalingEnabled.get()) && Boolean.FALSE.equals(Food.foodSaturationScalingEnabled.get())))
                return;

            ItemStack itemStack = foodValues.itemStack;
            Item item = itemStack.getItem();
            Double itemScale = itemStack.get(Components.ITEM_SCALE);

            if (itemScale == null || (ItemHelper.isModdedItem(item) && !Registries.FoodScaling.has(item)))
                return;

            final FoodComponent defaultFoodComponent = foodValues.defaultFoodComponent;
            final FoodComponent modifiedFoodComponent = foodValues.modifiedFoodComponent;
            final double relativeScale = itemScale / ScaleTypes.BASE.getScaleData(foodValues.player).getScale();

            final int relativeNutritionGain = (Boolean.TRUE.equals(Food.foodNutritionScalingEnabled.get()))
                ? (int) Math.round(defaultFoodComponent.nutrition() * relativeScale) - defaultFoodComponent.nutrition()
                : defaultFoodComponent.nutrition();

            final float relativeSaturationGain = (Boolean.TRUE.equals(Food.foodSaturationScalingEnabled.get()))
                ? (float) (defaultFoodComponent.saturation() * relativeScale) -  defaultFoodComponent.saturation()
                : defaultFoodComponent.saturation();

            foodValues.modifiedFoodComponent = new FoodComponent(
                modifiedFoodComponent.nutrition() + relativeNutritionGain,
                modifiedFoodComponent.saturation() + relativeSaturationGain,
                modifiedFoodComponent.canAlwaysEat(),
                modifiedFoodComponent.eatSeconds(),
                modifiedFoodComponent.usingConvertsTo(),
                modifiedFoodComponent.effects()
            );
        });
    }
}
