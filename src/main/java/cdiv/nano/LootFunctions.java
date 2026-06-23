package cdiv.nano;

import cdiv.nano.loot.functions.ScaledLoot;
import com.mojang.serialization.MapCodec;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static cdiv.nano.Nano.MOD_ID;

public class LootFunctions {
    public static final LootFunctionType<ScaledLoot> SCALED_LOOT = register("scaled_loot", ScaledLoot.CODEC);

    public static <T extends LootFunction> LootFunctionType<T>
    register(String name, MapCodec<T> codec) {
        LootFunctionType<T> lootFunctionType = new LootFunctionType<>(codec);

        Registry.register(
            Registries.LOOT_FUNCTION_TYPE,
            Identifier.of(MOD_ID, name),
            lootFunctionType
        );

        return lootFunctionType;
    }

    public static void initialize() {}
}