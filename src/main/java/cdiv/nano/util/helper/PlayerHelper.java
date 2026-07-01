package cdiv.nano.util.helper;

import cdiv.nano.access.WidenedServerPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

/**
 * @deprecated All available methods are replaced by {@link cdiv.nano.SharedInteractions}
 */
@Deprecated
public class PlayerHelper {
    /**
     * @deprecated Use {@link cdiv.nano.SharedInteractions#getMiningPosition(PlayerEntity)}
     */
    @Deprecated
    @SuppressWarnings("DataFlowIssue")
    public static Optional<BlockPos> getMiningPosition(ServerPlayerEntity player) {
        return ((WidenedServerPlayerInteractionManager) player.getServer().getPlayerInteractionManager(player)).nano$getMiningPosition();
    }

    /**
     * @deprecated Use {@link cdiv.nano.SharedInteractions#getMiningPosition(PlayerEntity)}
     * @implNote Only functional for server.
     */
    @Deprecated
    public static Optional<BlockPos> getMiningPosition(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity serverPlayer)
            return getMiningPosition(serverPlayer);

        return Optional.empty();
    }
}
