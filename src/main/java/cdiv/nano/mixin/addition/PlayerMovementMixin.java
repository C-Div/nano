package cdiv.nano.mixin.addition;

import cdiv.nano.access.MovingPlayer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public class PlayerMovementMixin implements MovingPlayer {
    @Unique public boolean nano$isMoving = false;

    @Unique
    @Override
    public boolean nano$isMoving() {
        return nano$isMoving;
    }

    @Unique
    @Override
    public void nano$setMoving(boolean isMoving) {
        nano$isMoving = isMoving;
    }
}
