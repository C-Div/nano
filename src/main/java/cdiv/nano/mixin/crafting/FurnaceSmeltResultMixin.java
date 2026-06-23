package cdiv.nano.mixin.crafting;

import cdiv.nano.Components;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class FurnaceSmeltResultMixin {
    @SuppressWarnings("DataFlowIssue")
    @Inject(
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/collection/DefaultedList;set(ILjava/lang/Object;)Ljava/lang/Object;",
            shift = At.Shift.AFTER
        ),

        method = "craftRecipe(Lnet/minecraft/registry/DynamicRegistryManager;Lnet/minecraft/recipe/RecipeEntry;Lnet/minecraft/util/collection/DefaultedList;I)Z"
    )
    private static void nano$craftRecipe$applyComponents(DynamicRegistryManager registryManager, @Nullable RecipeEntry<?> recipe, DefaultedList<ItemStack> slots, int count, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        final ItemStack smeltingItemStack = slots.getFirst();
        final ItemStack result = recipe.value().getResult(registryManager).copy();
        final Double smeltingItemScale = smeltingItemStack.getOrDefault(Components.ITEM_SCALE, 1.0D);

        result.set(Components.ITEM_SCALE, smeltingItemScale);
        slots.set(2, result);
    }

    @ModifyArg(
        method = "craftRecipe(Lnet/minecraft/registry/DynamicRegistryManager;Lnet/minecraft/recipe/RecipeEntry;Lnet/minecraft/util/collection/DefaultedList;I)Z",
        index = 1,
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/ItemStack;areItemsAndComponentsEqual(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z"
        )
    )
    private static ItemStack nano$craftRecipe$equalityCheckFix(ItemStack itemStack, @Local(argsOnly = true) DefaultedList<ItemStack> slots) {
        final ItemStack smeltingItemStack = slots.getFirst();
        final Double smeltingItemScale = smeltingItemStack.getOrDefault(Components.ITEM_SCALE, 1.0D);
        final ItemStack result = itemStack.copy();
        result.set(Components.ITEM_SCALE, smeltingItemScale);
        return result;
    }

    @ModifyArg(
        method = "canAcceptRecipeOutput(Lnet/minecraft/registry/DynamicRegistryManager;Lnet/minecraft/recipe/RecipeEntry;Lnet/minecraft/util/collection/DefaultedList;I)Z",
        index = 1,
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/ItemStack;areItemsAndComponentsEqual(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z"
        )
    )
    private static ItemStack nano$canAcceptRecipeOutput$equalityCheckFix(ItemStack itemStack, @Local(argsOnly = true) DefaultedList<ItemStack> slots) {
        final ItemStack smeltingItemStack = slots.getFirst();
        final Double smeltingItemScale = smeltingItemStack.getOrDefault(Components.ITEM_SCALE, 1.0D);
        final ItemStack result = itemStack.copy();
        result.set(Components.ITEM_SCALE, smeltingItemScale);
        return result;
    }
}
