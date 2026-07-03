package cdiv.nano;

import cdiv.nano.loot.functions.ScaledLoot;
import com.mojang.serialization.MapCodec;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class LootFunctions {
    public static final LootFunctionType<ScaledLoot> SCALED_LOOT = register("scaled_loot", ScaledLoot.CODEC);

    public static <T extends LootFunction> LootFunctionType<T>
    register(String name, MapCodec<T> codec) {
        LootFunctionType<T> lootFunctionType = new LootFunctionType<>(codec);

        Registry.register(
            Registries.LOOT_FUNCTION_TYPE,
            Nano.id(name),
            lootFunctionType
        );

        return lootFunctionType;
    }

    public static void initialize() {}
}