package cdiv.nano.util;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.ApiStatus;

import java.util.Set;
import java.util.function.Predicate;

public interface NanoInventory extends Inventory {
    @ApiStatus.Internal
    DefaultedList<ItemStack> getInventory();

    default void clear() {
        getInventory().clear();
        markDirty();
    }

    default int size() {
        return getInventory().size();
    }

    default boolean isEmpty() {
        for (ItemStack stack : getInventory()) {
            if (!stack.isEmpty())
                return false;
        }

        return true;
    }

    private static boolean isOutBounds(DefaultedList<ItemStack> inventory, int size, int slot) {
        return slot < 0 || slot >= size;
    }

    private static boolean isInBounds(DefaultedList<ItemStack> inventory, int size, int slot) {
        return slot > -1 && slot < size;
    }

    default ItemStack getStack(int slot) {
        DefaultedList<ItemStack> inventory = getInventory();

        if (isOutBounds(inventory, inventory.size(), slot))
            return ItemStack.EMPTY;

        return inventory.get(slot);
    }

    default ItemStack removeStack(int slot, int amount) {
        DefaultedList<ItemStack> inventory = getInventory();

        if (isOutBounds(inventory, inventory.size(), slot))
            return ItemStack.EMPTY;

        ItemStack stack = inventory.get(slot);
        ItemStack splitStack = stack.split(amount);

        if (stack.isEmpty())
            inventory.set(slot, ItemStack.EMPTY);

        markDirty();
        return splitStack;
    }

    default ItemStack removeStack(int slot) {
        DefaultedList<ItemStack> inventory = getInventory();

        if (isOutBounds(inventory, inventory.size(), slot))
            return ItemStack.EMPTY;

        ItemStack stack = inventory.get(slot);
        inventory.set(slot, ItemStack.EMPTY);
        markDirty();
        return stack;
    }

    default void setStack(int slot, ItemStack stack) {
        DefaultedList<ItemStack> inventory = getInventory();

        if (isOutBounds(inventory, inventory.size(), slot))
            return;

        inventory.set(slot, stack);
        markDirty();
    }

    default int getMaxCountPerStack() {
        return 64;
    }

    default int count(Item item) {
        int count = 0;

        for (ItemStack itemStack : getInventory()) {
            if (itemStack.getItem().equals(item)) {
                count += itemStack.getCount();
            }
        }

        return count;
    }

    default boolean containsAny(Set<Item> items) {
        return this.containsAny(stack -> !stack.isEmpty() && items.contains(stack.getItem()));
    }

    default boolean containsAny(Predicate<ItemStack> predicate) {
        for (int i = 0; i < this.size(); i++) {
            ItemStack itemStack = this.getStack(i);
            if (predicate.test(itemStack)) {
                return true;
            }
        }

        return false;
    }

    static boolean canPlayerUse(BlockEntity blockEntity, PlayerEntity player) {
        return canPlayerUse(blockEntity, player, 4.0F);
    }

    static boolean canPlayerUse(BlockEntity blockEntity, PlayerEntity player, float range) {
        World world = blockEntity.getWorld();
        BlockPos blockPos = blockEntity.getPos();

        if (world == null)
            return false;

        return world.getBlockEntity(blockPos) == blockEntity && player.canInteractWithBlockAt(blockPos, range);
    }
}
