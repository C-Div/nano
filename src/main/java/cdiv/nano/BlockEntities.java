package cdiv.nano;

import cdiv.nano.blocks.entities.ScaleCombinerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.jetbrains.annotations.NotNull;

public class BlockEntities {
    public static final BlockEntityType<ScaleCombinerBlockEntity> SCALE_COMBINER =
        register("scale_combiner", ScaleCombinerBlockEntity::new, Blocks.SCALE_COMBINER);

    public static void initialize() {
        Nano.LOGGER.info("Registering blocks entities...");
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends BlockEntity>
    BlockEntityType<T> register(String name, BlockEntityType.BlockEntityFactory<? extends @NotNull T> entityFactory, Block... blocks) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Nano.id(name), BlockEntityType.Builder.<T>create(entityFactory, blocks).build());
    }
}