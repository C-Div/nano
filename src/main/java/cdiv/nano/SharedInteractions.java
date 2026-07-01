package cdiv.nano;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;
import java.util.function.Function;

public class SharedInteractions {
    private static Function<PlayerEntity, Optional<BlockPos>> serverGetMiningPosition;
    private static Function<PlayerEntity, Optional<BlockPos>> clientGetMiningPosition;

    public static void initializeMiningPositionGetter(Function<PlayerEntity, Optional<BlockPos>> getter) {
        switch (FabricLoader.getInstance().getEnvironmentType()) {
            case SERVER:
                if (serverGetMiningPosition != null)
                    return;

                serverGetMiningPosition = getter;
                break;
            case CLIENT:
                if (clientGetMiningPosition != null)
                    return;

                clientGetMiningPosition = getter;
                break;
        }
    }

    public static Optional<BlockPos> getMiningPosition(PlayerEntity player) {
        return switch (FabricLoader.getInstance().getEnvironmentType()) {
            case SERVER -> serverGetMiningPosition.apply(player);
            case CLIENT -> clientGetMiningPosition.apply(player);
        };
    }
}
