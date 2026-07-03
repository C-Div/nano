package cdiv.nano;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static cdiv.nano.Nano.LOGGER;

public class Components {
    public static final ComponentType<Boolean> LOOT_SCALED = register("loot_scaled", Codec.BOOL);
    public static final ComponentType<Double> ITEM_SCALE = register("scale", Codec.DOUBLE);

    public static <T> ComponentType<T> register(String name, Codec<T> codec) {
        return Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Nano.id(name),
            ComponentType.<T>builder().codec(codec).build()
        );
    }

    protected static void initialize() {
        LOGGER.info("Registering components");
    }
}