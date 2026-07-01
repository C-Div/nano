package cdiv.nano.mixin.addition;

import cdiv.nano.access.WidenedServerPlayerInteractionManager;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin implements WidenedServerPlayerInteractionManager {
    @Shadow
    private BlockPos miningPos;

    @Shadow
    private boolean mining;

    @Unique
    public Optional<BlockPos> nano$getMiningPosition() {
        return mining ? Optional.of(miningPos) : Optional.empty();
    }
}
