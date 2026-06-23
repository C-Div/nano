package cdiv.nano.mixin;

import cdiv.nano.Components;
import cdiv.nano.helper.Item;
import cdiv.nano.helper.Mixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

@org.spongepowered.asm.mixin.Mixin(Entity.class)
public class EntityDropItemMixin {
    @Inject(
        at = @At("TAIL"),
        method = "dropStack(Lnet/minecraft/item/ItemStack;F)Lnet/minecraft/entity/ItemEntity;"
    )
    private void nano$dropStack$itemScaler(ItemStack stack, float yOffset, CallbackInfoReturnable<ItemEntity> callbackInfoReturnable) {
        ItemEntity itemEntity = callbackInfoReturnable.getReturnValue();

        if (itemEntity == null)
            return;

        Double itemScale = stack.get(Components.ITEM_SCALE);

        if (itemScale == null) {
            itemScale = Item.resolveScale(stack, () -> (double) ScaleTypes.BASE.getScaleData(Mixin.asEntity(this)).getTargetScale());
            stack.set(Components.ITEM_SCALE, itemScale);
        }

        ScaleData scaleData = ScaleTypes.BASE.getScaleData(itemEntity);
        scaleData.setScale(scaleData.getScale() * itemScale.floatValue());
    }
}
