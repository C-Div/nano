package cdiv.nano;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static cdiv.nano.Nano.LOGGER;

public class Items {
    public static final BlockItem SCALE_COMBINER = registerBlock("scale_combiner", Blocks.SCALE_COMBINER);

    public static void initialize() {
        LOGGER.debug("Registering items...");
    }

    public static BlockItem registerBlock(String name, Block block) {
        final BlockItem blockItem = new BlockItem(block, new Item.Settings());
        return register(name, blockItem);
    }

    public static <GenericItem extends Item>
    GenericItem register(String name, GenericItem item) {
        Registry.register(Registries.ITEM, Nano.id(name), item);
        return item;
    }
}
