package cdiv.nano.mixin;

import cdiv.nano.util.helper.MixinHelper;
import cdiv.nano.api.config.Xp;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

@org.spongepowered.asm.mixin.Mixin(LivingEntity.class)
public class EntityDropXpMixin {
    @Shadow
    protected int getXpToDrop() {
        throw new AssertionError();
    }

    @Inject(
        at = @At("HEAD"),
        method = "getXpToDrop(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/Entity;)I",
        cancellable = true
    )
    void nano$getXpToDrop$scaleXp(ServerWorld world, Entity attacker, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (!Xp.xpScalingEnabled.getOrDefault())
            return;

        LivingEntity entity = MixinHelper.asLivingEntity(this);
        ScaleData scaleData = ScaleTypes.BASE.getScaleData(entity);

        if (scaleData.isReset())
            return;

        callbackInfoReturnable.setReturnValue(EnchantmentHelper.getMobExperience(world, attacker, entity, Math.round(getXpToDrop() * scaleData.getScale())));
    }
}
