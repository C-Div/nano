package cdiv.nano.blocks.entities;

import cdiv.nano.BlockEntities;
import cdiv.nano.Components;
import cdiv.nano.screen.ScaleNormalizerScreenHandler;
import cdiv.nano.util.helper.TranslationHelper;
import cdiv.nano.util.transfer.SectionStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SidedStorageBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ScaleNormalizerBlockEntity extends StorageBlockEntity implements SidedInventory, SidedStorageBlockEntity, NamedScreenHandlerFactory { // FUTURE: Make this into an inheritable class
    private static final int[] TOP_SLOTS = {0};
    private static final int[] BOTTOM_SLOTS = {1};

    private final SectionStorage topStorage;
    private final SectionStorage bottomStorage;

    private int normalizeTime = 0;
    private int normalizeTimeTotal = 60;

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> ScaleNormalizerBlockEntity.this.normalizeTime;
                case 1 -> ScaleNormalizerBlockEntity.this.normalizeTimeTotal;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    ScaleNormalizerBlockEntity.this.normalizeTime = value;
                    break;
                case 1:
                    ScaleNormalizerBlockEntity.this.normalizeTimeTotal = value;
                    break;
            }
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public ScaleNormalizerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.SCALE_NORMALIZER, pos, state, 2);
        this.topStorage = new SectionStorage(this, 1);
        this.bottomStorage = new SectionStorage(this, 2, false, true);
    }


    @SuppressWarnings("unused")
    public static void tick(World world, BlockPos pos, BlockState state, ScaleNormalizerBlockEntity blockEntity) {
        ItemStack input = blockEntity.getStack(0);
        Item item = input.getItem();
        int maxCount = item.getMaxCount();

        if (input.isEmpty() || maxCount <= 1 || !blockEntity.getStack(1).isEmpty()) {
            if (blockEntity.normalizeTime > 0)
                --blockEntity.normalizeTime;

            return;
        } else if (blockEntity.normalizeTime < blockEntity.normalizeTimeTotal) {
            ++blockEntity.normalizeTime;
            return;
        }

        blockEntity.normalizeTime = 0;

        int inputCount = input.getCount();
        double inputScale = input.getOrDefault(Components.ITEM_SCALE, 1.0D);
        double totalValue = inputCount * inputScale;

        int outputCount = (int) Math.min(Math.floor(totalValue), maxCount);

        if (outputCount <= 0 || outputCount == inputCount) {
            blockEntity.setStack(1, input.copy());
            blockEntity.removeStack(0);
            return;
        }

        double remainingValue = totalValue - outputCount;

        ItemStack output = new ItemStack(item, outputCount);
        output.set(Components.ITEM_SCALE, 1.0);

        double itemsConsumed = outputCount / inputScale;
        int consumedWhole = (int) Math.floor(itemsConsumed);
        int remainingCount = inputCount - consumedWhole;

        if (remainingCount <= 0)
            blockEntity.removeStack(0);
        else {
            double remainingScale = remainingValue / remainingCount;
            input.set(Components.ITEM_SCALE, remainingScale);
            input.setCount(remainingCount);
            blockEntity.setStack(0, input);
        }

        blockEntity.setStack(1, output);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        if (nbt.contains("NormalizeTime"))
            this.normalizeTime = nbt.getShort("NormalizeTime");

        if (nbt.contains("NormalizeTimeTotal"))
            this.normalizeTimeTotal = nbt.getShort("NormalizeTimeTotal");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putShort("NormalizeTime", (short) this.normalizeTime);
        nbt.putShort("NormalizeTimeTotal", (short) this.normalizeTimeTotal);
    }

    @Override
    public Storage<ItemVariant> getItemStorage(@Nullable Direction side) {
        return switch(side) {
            case UP -> topStorage;
            case NORTH, SOUTH, WEST, EAST -> null;
            case DOWN -> bottomStorage;
            case null -> null;
        };
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return switch(side) {
            case UP -> TOP_SLOTS;
            case NORTH, SOUTH, WEST, EAST -> new int[0];
            case DOWN -> BOTTOM_SLOTS;
            case null -> new int[0];
        };
    }

    @Override
    public boolean canInsert(int slot, ItemStack insertingStack, @Nullable Direction direction) {
        if (direction == null || slot != 0)
            return false;

        switch(direction) {
            case NORTH, SOUTH, WEST, EAST:
                return false;
        }

        ItemStack stack = getStack(slot);
        return (stack.isEmpty() || ItemStack.areItemsEqual(insertingStack, stack)) && stack.getCount() < stack.getMaxCount();
    }

    @Override
    public boolean canExtract(int slot, ItemStack extractingStack, @Nullable Direction direction) {
        if (direction == null || slot < 0 || slot > 1)
            return false;

        switch(direction) {
            case UP, DOWN:
                if (slot != 1)
                    return false;

                break;
            case NORTH, SOUTH, WEST, EAST:
                if (slot != 0)
                    return false;

                break;
        }

        ItemStack stack = getStack(slot);
        return ItemStack.areItemsEqual(extractingStack, stack) && extractingStack.getCount() < extractingStack.getMaxCount();
    }

    @Override
    public Text getDisplayName() {
        return TranslationHelper.screen("scale_normalizer.title");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ScaleNormalizerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}
