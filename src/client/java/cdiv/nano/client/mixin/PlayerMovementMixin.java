package cdiv.nano.client.mixin;

import cdiv.nano.payload.IsMovingC2SPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class PlayerMovementMixin {
    @Unique private boolean nano$previouslyMoving = false;
    @Shadow protected abstract boolean hasMovementInput();

    @Inject(
        at = @At("HEAD"),
        method = "tickMovement"
    )
    public void nano$tickMovement$isMovingPacketSender(CallbackInfo callbackInfo) {
        boolean isMoving = hasMovementInput();

        if (isMoving == nano$previouslyMoving)
            return;

        nano$previouslyMoving = isMoving;
        ClientPlayNetworking.send(new IsMovingC2SPayload(isMoving));
    }
}
