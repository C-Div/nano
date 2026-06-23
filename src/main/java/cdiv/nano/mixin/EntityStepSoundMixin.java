package cdiv.nano.mixin;

import cdiv.nano.helper.Mixin;
import cdiv.nano.api.config.Stepping;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

@org.spongepowered.asm.mixin.Mixin({
	Entity.class,
	AbstractSkeletonEntity.class,
	EndermiteEntity.class,
	HoglinEntity.class,
	PiglinBruteEntity.class,
	PiglinEntity.class,
	RavagerEntity.class,
	SilverfishEntity.class,
	SpiderEntity.class,
	WardenEntity.class,
	ZoglinEntity.class,
	ZombieEntity.class,
	AbstractHorseEntity.class,
	AllayEntity.class,
	ArmadilloEntity.class,
	BeeEntity.class,
	CamelEntity.class,
	ChickenEntity.class,
	CowEntity.class,
	FishEntity.class,
	FrogEntity.class,
	GoatEntity.class,
	IronGolemEntity.class,
	LlamaEntity.class,
	PandaEntity.class,
	ParrotEntity.class,
	PigEntity.class,
	PolarBearEntity.class,
	SheepEntity.class,
	SnifferEntity.class,
	StriderEntity.class,
	TurtleEntity.class,
	WolfEntity.class,
	PlayerEntity.class
})
public class EntityStepSoundMixin {
	@Inject(
        at = @At("HEAD"),
        method = "playStepSound"
	)
	private void nano$pushAway$stepSoundLogic(BlockPos pos, BlockState state, CallbackInfo callbackInfo) {
		if (!Stepping.soundScalingEnabled.get())
			return;

		Entity entity = Mixin.asEntity(this);
		ScaleData scaleData = ScaleTypes.BASE.getScaleData(entity);

		if (scaleData.isReset())
			return;

		BlockSoundGroup blockSoundGroup = state.getSoundGroup();
		entity.playSound(blockSoundGroup.getStepSound(), blockSoundGroup.getVolume() * 0.15F * scaleData.getScale(), blockSoundGroup.getPitch());
	}
}