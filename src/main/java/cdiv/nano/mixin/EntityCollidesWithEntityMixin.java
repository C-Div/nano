package cdiv.nano.mixin;

import cdiv.nano.DamageSources;
import cdiv.nano.Nano;
import cdiv.nano.helper.Mixin;
import cdiv.nano.api.config.Stepping;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@org.spongepowered.asm.mixin.Mixin(LivingEntity.class)
public class EntityCollidesWithEntityMixin {
	@Inject(
        at = @At("HEAD"),
        method = "pushAway"
    )
	private void nano$pushAway$steppingDamageLogic(Entity other, CallbackInfo callbackInfo) {
		if (!Stepping.damageEnabled.get())
			return;

		LivingEntity entity = Mixin.asLivingEntity(this);

		if (entity.getWorld().isClient
			|| entity.isDead()
			|| other == entity // Yes this is a real thing that we have to check
			|| Stepping.immuneEntities.contains(other.getType())
			|| !Mixin.isEntityMoving(entity))
			return;

		if (other instanceof LivingEntity otherAsLiving && (!otherAsLiving.canTakeDamage() || otherAsLiving.isDead()))
			return;

		EntityDimensions dimensions = entity.getDimensions(entity.getPose());
		EntityDimensions otherDimensions = other.getDimensions(other.getPose());

		double relativeHeight = dimensions.height() / otherDimensions.height();

		if (relativeHeight < Stepping.damageRelativeHeightThreshold.get())
			return;

		Nano.LOGGER.info("Entity: {}", entity.getName().getString());

		other.damage(
			DamageSources.getSteppingDamage(
				entity.getWorld(),
				entity
			),
			(float) relativeHeight
		);
	}
}