package cdiv.nano.mixin;

import cdiv.nano.Components;
import cdiv.nano.util.helper.ItemHelper;
import cdiv.nano.util.helper.MixinHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

@org.spongepowered.asm.mixin.Mixin(PlayerEntity.class)
public class PlayerEntityDropItemMixin {
    @Inject(
        at = @At("TAIL"),
        method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;"
    )
    private void nano$dropItem$itemScaler(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> callbackInfoReturnable) {
        ItemEntity itemEntity = callbackInfoReturnable.getReturnValue();

        if (itemEntity == null)
            return;

        ScaleData throwerScaleData = ScaleTypes.BASE.getScaleData(MixinHelper.asEntity(this));
        Double itemScale = stack.get(Components.ITEM_SCALE);

        if (itemScale == null) {
            itemScale = ItemHelper.resolveScale(stack, () -> (double) throwerScaleData.getTargetScale());
            stack.set(Components.ITEM_SCALE, itemScale);
        }

        ScaleData itemScaleData = ScaleTypes.BASE.getScaleData(itemEntity);

        final float finalItemScale = itemScaleData.getScale() * itemScale.floatValue();
        float throwerScale = throwerScaleData.getScale();

        itemEntity.setVelocity(itemEntity.getVelocity().multiply(Math.min(1.0, throwerScale / itemScale))); // NOTE: Not very accurate, but good enough
        itemScaleData.setScale(finalItemScale);
    }
}
