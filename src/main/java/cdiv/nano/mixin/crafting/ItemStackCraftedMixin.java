package cdiv.nano.mixin.crafting;

import cdiv.nano.Components;
import cdiv.nano.helper.Item;
import cdiv.nano.helper.Mixin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import virtuoel.pehkui.api.ScaleTypes;

@org.spongepowered.asm.mixin.Mixin(ItemStack.class)
public class ItemStackCraftedMixin {
    @Inject(
        at = @At("HEAD"),
        method = "onCraftByPlayer(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;I)V"
    )
    public void nano$onCraftByPlayer$resultScaler(World world, PlayerEntity player, int amount, CallbackInfo callbackInfo) {
        ItemStack itemStack = Mixin.asItemStack(this);

        if (itemStack.contains(Components.ITEM_SCALE))
            return;

        double crafterScale = Item.resolveScale(itemStack, () -> ScaleTypes.BASE.getScaleData(player).getTargetScale());
        itemStack.set(Components.ITEM_SCALE, crafterScale);
    }
}
