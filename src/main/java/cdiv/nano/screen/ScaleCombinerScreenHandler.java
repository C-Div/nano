package cdiv.nano.screen;

import cdiv.nano.Blocks;
import cdiv.nano.ScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.MathHelper;

public class ScaleCombinerScreenHandler extends ScreenHandler {
    private final ScreenHandlerContext context;
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public ScaleCombinerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(2));
    }

    public ScaleCombinerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        this(syncId, playerInventory, inventory, propertyDelegate, ScreenHandlerContext.EMPTY);
    }

    public ScaleCombinerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate, ScreenHandlerContext context) {
        super(ScreenHandlers.SCALE_COMBINER, syncId);
        this.context = context;
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;

        addProperties(propertyDelegate);
        inventory.onOpen(playerInventory.player);

        this.addSlot(new Slot(inventory, 0, 23, 35));
        this.addSlot(new Slot(inventory, 1, 72, 35));
        this.addSlot(new Slot(inventory, 2, 134, 35));

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }

        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
        }
    }

    public float getCombineProgress() {
        int combineTime = this.propertyDelegate.get(0);
        int combineTimeTotal = this.propertyDelegate.get(1);
        return (combineTimeTotal != 0 && combineTime != 0)
            ? MathHelper.clamp((float) combineTime / combineTimeTotal, 0.0F, 1.0F)
            : 0.0F;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        Slot movingSlot = this.slots.get(slotIndex);

        if (!movingSlot.hasStack())
            return ItemStack.EMPTY;

        ItemStack originalStack = movingSlot.getStack();
        ItemStack newStack = originalStack.copy();

        if (slotIndex < 3) {
            if (!insertItem(originalStack, 3, 39, false))
                return ItemStack.EMPTY;
        } else if (!insertItem(originalStack, 0, 2, false)) {
            if (slotIndex < 30) {
                if (!insertItem(originalStack, 30, 39, false))
                    return ItemStack.EMPTY;
            } else if (!insertItem(originalStack, 3, 29, false))
                return ItemStack.EMPTY;
        }

        if (originalStack.isEmpty()) // Original stack was completely consumed, replace with empty
            movingSlot.setStack(ItemStack.EMPTY); // setStack() marks dirty
        else
            movingSlot.markDirty();

        movingSlot.onTakeItem(player, originalStack);
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(context, player, Blocks.SCALE_COMBINER);
    }
}
