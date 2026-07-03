package cdiv.nano.blocks.entities;

import cdiv.nano.BlockEntities;
import cdiv.nano.Components;
import cdiv.nano.Nano;
import cdiv.nano.screen.ScaleCombinerScreenHandler;
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

public class ScaleCombinerBlockEntity extends StorageBlockEntity implements SidedInventory, SidedStorageBlockEntity, NamedScreenHandlerFactory {
    private static final int[] SIDE_SLOTS = {0};
    private static final int[] TOP_SLOTS = {1};
    private static final int[] BOTTOM_SLOTS = {2};

    private final SectionStorage topStorage;
    private final SectionStorage sideStorage;
    private final SectionStorage bottomStorage;

    private int combineTime = 0;
    private int combineTimeTotal = 20;

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> ScaleCombinerBlockEntity.this.combineTime;
                case 1 -> ScaleCombinerBlockEntity.this.combineTimeTotal;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    ScaleCombinerBlockEntity.this.combineTime = value;
                    break;
                case 1:
                    ScaleCombinerBlockEntity.this.combineTimeTotal = value;
                    break;
            }
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public ScaleCombinerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.SCALE_COMBINER, pos, state, 3);
        this.sideStorage = new SectionStorage(this, 0);
        this.topStorage = new SectionStorage(this, 1);
        this.bottomStorage = new SectionStorage(this, 2, false, true);
    }


    public static void tick(World world, BlockPos pos, BlockState state, ScaleCombinerBlockEntity blockEntity) {
        ItemStack inputA = blockEntity.getStack(0);
        ItemStack inputB = blockEntity.getStack(1);
        Item item = inputA.getItem();

        int inputACount = inputA.getCount();
        int inputBCount = inputB.getCount();
        int totalCount = inputACount + inputBCount;

        if (inputA.isEmpty() || inputB.isEmpty() // Empty
            || totalCount > item.getMaxCount() || item != inputB.getItem() // Items are not the same or combine to exceed max stack size
            || !blockEntity.getStack(2).isEmpty()) { // Output is not available

            if (blockEntity.combineTime > 0)
                --blockEntity.combineTime;

            return;
        } else if (blockEntity.combineTime < blockEntity.combineTimeTotal) {
            ++blockEntity.combineTime;
            return;
        }

        double inputAScale = inputA.getOrDefault(Components.ITEM_SCALE, 1.0D);
        double inputBScale = inputA.getOrDefault(Components.ITEM_SCALE, 1.0D);

        ItemStack output = new ItemStack(item, totalCount);
        output.set(Components.ITEM_SCALE, (inputACount * inputAScale + inputBCount * inputBScale) / totalCount);

        blockEntity.setStack(2, output);
        blockEntity.removeStack(0);
        blockEntity.removeStack(1);
        blockEntity.combineTime = 0;
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        if (nbt.contains("CombineTime"))
            this.combineTime = nbt.getShort("CombineTime");

        if (nbt.contains("CombineTimeTotal"))
            this.combineTimeTotal = nbt.getShort("CombineTimeTotal");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putShort("CombineTime", (short) this.combineTime);
        nbt.putShort("CombineTimeTotal", (short) this.combineTimeTotal);
    }

    @Override
    public Storage<ItemVariant> getItemStorage(@Nullable Direction side) {
        return switch(side) {
            case UP -> topStorage;
            case NORTH, SOUTH, WEST, EAST -> sideStorage;
            case DOWN -> bottomStorage;
            case null -> null;
        };
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return switch(side) {
            case UP -> TOP_SLOTS;
            case NORTH, SOUTH, WEST, EAST -> SIDE_SLOTS;
            case DOWN -> BOTTOM_SLOTS;
            case null -> new int[0];
        };
    }

    @Override
    public boolean canInsert(int slot, ItemStack insertingStack, @Nullable Direction direction) {
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
        return (stack.isEmpty() || ItemStack.areItemsEqual(insertingStack, stack)) && stack.getCount() < stack.getMaxCount();
    }

    @Override
    public boolean canExtract(int slot, ItemStack extractingStack, @Nullable Direction direction) {
        if (direction == null || slot < 0 || slot > 2)
            return false;

        switch(direction) {
            case UP:
                if (slot != 1)
                    return false;

                break;
            case DOWN:
                if (slot != 2)
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
        return TranslationHelper.screen("scale_combiner.title");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ScaleCombinerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}
