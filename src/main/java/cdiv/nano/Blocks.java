package cdiv.nano;

import cdiv.nano.blocks.ScaleCombinerBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.jetbrains.annotations.ApiStatus;

public class Blocks {
    public static final Block SCALE_COMBINER = register(
        "scale_combiner",
        new ScaleCombinerBlock(AbstractBlock.Settings.create()
            .strength(4.0F)
            .requiresTool())
    );

    public static void initialize() {
        Nano.LOGGER.info("Registering blocks...");
    }

    @SuppressWarnings("unused")
    @ApiStatus.Obsolete(since = "0.0.1-alpha.17")
    private static Block registerWithItem(String name, Block block) {
        final BlockItem blockItem = new BlockItem(block, new Item.Settings());
        Registry.register(Registries.ITEM, Nano.id(name), blockItem);
        register(name, block);
        return block;
    }

    private static Block register(String name, Block block) {
        return Registry.register(Registries.BLOCK, Nano.id(name), block);
    }
}
