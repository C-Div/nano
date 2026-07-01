package cdiv.nano.client.mixin.addition;

import cdiv.nano.client.access.WidenedClientPlayerInteractionManager;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin implements WidenedClientPlayerInteractionManager {
    @Shadow
    private boolean breakingBlock;

    @Shadow
    private BlockPos currentBreakingPos;

    @Unique
    public Optional<BlockPos> nano$getMiningPosition() {
        return breakingBlock ? Optional.of(currentBreakingPos) : Optional.empty();
    }
}
