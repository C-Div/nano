package cdiv.nano.client.mixin;

import cdiv.nano.Sounds;
import cdiv.nano.api.client.config.Sound;
import cdiv.nano.client.sounds.GrowthSound;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import virtuoel.pehkui.api.ScaleData;

@Mixin(ScaleData.class)
public abstract class EntityGrowthSoundMixin {
    @Shadow @Final private @Nullable Entity entity;
    @Shadow private float prevBaseScale;
    @Shadow private float baseScale;

    @Shadow private int totalScaleTicks;
    @Shadow private int scaleTicks;

    @Shadow
    public abstract float getTargetScale();

    @Unique public MovingSoundInstance nano$growthSound = null;

    @Inject(
        method = "tick()V",
        at = @At(
            value = "INVOKE",
            target = "Lvirtuoel/pehkui/api/ScaleData;getTargetScale()F"
        )
    )
    public void nano$tick$playGrowthSound(CallbackInfo callbackInfo, @Local(name = "currScale") float currentScale) {
        if (!Sound.growthSoundsEnabled.get() || entity == null || !entity.getWorld().isClient || prevBaseScale >= baseScale)
            return;

        if (scaleTicks >= totalScaleTicks || currentScale == getTargetScale()) {
            if (nano$growthSound != null)
                MinecraftClient.getInstance().getSoundManager().stop(nano$growthSound);

            return;
        }

        SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();

        if (nano$growthSound != null && soundManager.isPlaying(nano$growthSound))
            return;

        SoundEvent sound;

        do {
            sound = (Sound.longGrowthSoundsEnabled.get() && totalScaleTicks - scaleTicks >= 80)
                ? Sounds.getRandomLongGrowthSound()
                : Sounds.getRandomShortGrowthSound();
        } while(nano$growthSound != null && sound.getId() == nano$growthSound.getId());

        SoundCategory category = SoundCategory.NEUTRAL;

        if ((entity.isPlayer()))
            category = SoundCategory.PLAYERS;
        else if (entity instanceof HostileEntity)
            category = SoundCategory.HOSTILE;

        nano$growthSound = new GrowthSound(sound, category, entity);
        soundManager.play(nano$growthSound);
    }
}
