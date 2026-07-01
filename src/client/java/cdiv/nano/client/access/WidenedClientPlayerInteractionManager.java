package cdiv.nano.client.access;

import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public interface WidenedClientPlayerInteractionManager {
    Optional<BlockPos> nano$getMiningPosition();
}
