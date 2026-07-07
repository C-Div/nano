package cdiv.nano.mixin;

import cdiv.nano.api.config.Sound;
import cdiv.nano.util.helper.MixinHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

@org.spongepowered.asm.mixin.Mixin({
	Entity.class,
	PlayerEntity.class
})
public class EntitySoundScalingMixin {
	@ModifyVariable(
		method = "playSound(Lnet/minecraft/sound/SoundEvent;FF)V",
		at = @At("HEAD"),
		ordinal = 0,
		argsOnly = true
	)
	private float nano$playSound$soundScaling(float volume) {
		if (!Sound.soundScalingEnabled.getOrDefault())
			return volume;

		Entity entity = MixinHelper.asEntity(this);
		ScaleData scaleData = ScaleTypes.BASE.getScaleData(entity);

		if (scaleData.isReset())
			return volume;

		return volume * scaleData.getScale();
	}
}