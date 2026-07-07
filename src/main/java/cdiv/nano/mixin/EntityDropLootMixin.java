package cdiv.nano.mixin;

import cdiv.nano.Components;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityDropLootMixin {
    @Inject(
        at = @At("HEAD"),
        method = "dropStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/entity/ItemEntity;"
    )
    private void nano$dropStack$componentRemoval(ItemStack stack, CallbackInfoReturnable<ItemEntity> callbackInfoReturnable) {
        if (Boolean.FALSE.equals(stack.get(Components.LOOT_SCALED)))
            return;

        stack.remove(Components.LOOT_SCALED);
    }
}
