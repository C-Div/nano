package cdiv.nano.util.transfer;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;

public class SectionStorage implements Storage<ItemVariant> {
    private final Inventory inventory;
    private final int startSlot;
    private final int endSlot;

    private final boolean insertionAllowed;
    private final boolean extractionAllowed;

    public SectionStorage(Inventory inventory, int index) {
        this(inventory, index, index);
    }

    public SectionStorage(Inventory inventory, int startIndex, int endIndex) {
        this(inventory, startIndex, endIndex, true, true);
    }

    public SectionStorage(Inventory inventory, int index, boolean insertionAllowed, boolean extractionAllowed) {
        this(inventory, index, index, insertionAllowed, extractionAllowed);
    }

    public SectionStorage(Inventory inventory, int startIndex, int endIndex, boolean insertionAllowed, boolean extractionAllowed) {
        this.inventory = inventory;
        this.startSlot = startIndex;
        this.endSlot = endIndex;
        this.insertionAllowed = insertionAllowed;
        this.extractionAllowed = extractionAllowed;
    }

    @Override
    public boolean supportsInsertion() {
        return insertionAllowed;
    }

    @Override
    public boolean supportsExtraction() {
        return extractionAllowed;
    }

    @Override
    public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
        if (!insertionAllowed)
            return 0;

        long remainingAmount = maxAmount;
        long totalInsertedAmount = 0L;
        int maxResourceCount = resource.getItem().getMaxCount();

        ItemStack resourceStack = resource.toStack();

        for (int index = startSlot; index <= endSlot; ++index) {
            if (remainingAmount <= 0)
                return totalInsertedAmount;

            ItemStack currentStack = inventory.getStack(index);

            if (!ItemStack.areItemsAndComponentsEqual(currentStack, resourceStack) && !currentStack.isEmpty())
                continue;

            int stackCount = currentStack.getCount();
            int insertedAmount = Math.toIntExact(Math.min(remainingAmount, maxResourceCount - stackCount));

            if (insertedAmount <= 0)
                continue;

            remainingAmount -= insertedAmount;
            totalInsertedAmount += insertedAmount;

            if (currentStack.isEmpty()) {
                inventory.setStack(index, resource.toStack(insertedAmount + stackCount));
                continue;
            }

            currentStack.increment(insertedAmount);
            inventory.setStack(index, currentStack);
        }

        return totalInsertedAmount;
    }

    @Override
    public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
        if (!extractionAllowed)
            return 0;

        long remainingAmount = maxAmount;
        long totalExtractedAmount = 0L;

        ItemStack resourceStack = resource.toStack();

        for (int index = startSlot; index <= endSlot; ++index) {
            if (remainingAmount <= 0)
                return totalExtractedAmount;

            ItemStack currentStack = inventory.getStack(index);

            if (!ItemStack.areItemsAndComponentsEqual(currentStack, resourceStack) || currentStack.isEmpty())
                continue;

            int stackCount = currentStack.getCount();
            int extractedAmount = Math.toIntExact(Math.min(remainingAmount, stackCount));

            if (extractedAmount <= 0)
                continue;

            remainingAmount -= extractedAmount;
            totalExtractedAmount += extractedAmount;

            currentStack.decrement(extractedAmount);
            inventory.setStack(index, currentStack);
        }

        return totalExtractedAmount;
    }

    @Override
    public @NotNull Iterator<StorageView<ItemVariant>> iterator() {
        return Collections.emptyIterator();
    }
}
