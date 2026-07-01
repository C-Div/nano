package cdiv.nano.mixin;

import cdiv.nano.Components;
import cdiv.nano.DamageSources;
import cdiv.nano.api.config.Food;
import cdiv.nano.util.helper.ItemHelper;
import cdiv.nano.util.helper.MixinHelper;
import cdiv.nano.registry.Registries;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

@org.spongepowered.asm.mixin.Mixin(PlayerEntity.class)
public abstract class PlayerEntityFoodScaling {
    @Shadow
    public abstract HungerManager getHungerManager();

    @Shadow
    protected HungerManager hungerManager;

    @Inject(
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/HungerManager;eat(Lnet/minecraft/component/type/FoodComponent;)V"
        ),

        method = "eatFood(Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/component/type/FoodComponent;)Lnet/minecraft/item/ItemStack;"
    )
    public void nano$eatFood$foodScaler(World world, ItemStack stack, FoodComponent foodComponent, CallbackInfoReturnable<ItemStack> callbackInfoReturnable) {
        if (!Food.foodScalingEnabled.get() || (!Food.foodSaturationScalingEnabled.get() && !Food.foodNutritionScalingEnabled.get()))
            return;

        Item item = stack.getItem();

        if (ItemHelper.isModdedItem(item) && !Registries.FoodScaling.has(item))
            return;

        Entity entity = MixinHelper.asEntity(this);
        Double itemScale = stack.get(Components.ITEM_SCALE);
        final ScaleData scaleData = ScaleTypes.BASE.getScaleData(entity);

        if (itemScale == null) {
            itemScale = (double) scaleData.getTargetScale();
            stack.set(Components.ITEM_SCALE, itemScale);
        }

        final double relativeScale = itemScale / scaleData.getScale();

        final int nutritionGain = (Food.foodNutritionScalingEnabled.get())
            ? (int) Math.round(foodComponent.nutrition() * relativeScale) - foodComponent.nutrition()
            : 0;

        final int clampedNutrition = (Food.overeatingEnabled.get())
            ? hungerManager.getFoodLevel() + nutritionGain - 20
            : 0;

        final float saturationGain = (Food.foodSaturationScalingEnabled.get())
            ? (float) (foodComponent.saturation() * relativeScale - foodComponent.saturation())
            : 0;

        if (clampedNutrition > 0 && relativeScale > Food.overeatingThreshold.get())
            entity.damage(
                DamageSources.getOvereatingDamage(world),
                clampedNutrition
            );

        HungerManager hungerManager = getHungerManager();
        hungerManager.eat(new FoodComponent(
            nutritionGain,
            saturationGain,
            foodComponent.canAlwaysEat(),
            foodComponent.eatSeconds(),
            foodComponent.usingConvertsTo(),
            foodComponent.effects()
        ));
    }
}
