package cdiv.nano.mixin;

import cdiv.nano.helper.Mixin;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@org.spongepowered.asm.mixin.Mixin(Entity.class)
public class DebugMixin {
    @Unique
    float nano$debug1 = 0;

    @Inject(
        at = @At("HEAD"),
        method = "tick"
    )
    public void tick(CallbackInfo callbackInfo) {
        Entity entity = Mixin.asEntity(this);

        if (entity.distanceTraveled == nano$debug1)
            return;

        nano$debug1 = entity.distanceTraveled;
//        LOGGER.warn("\nEntity: {}\nDistance Traveled: {}", entity.getName().getString(), entity.distanceTraveled);
    }
}
