package cdiv.nano.client.sounds;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class GrowthSound extends MovingSoundInstance {
    @NotNull final PlayerEntity player;
    @NotNull final Entity entity;

    public GrowthSound(SoundEvent sound, SoundCategory category, @NotNull Entity entity) {
        super(sound, category, Random.create());
        final PlayerEntity player = MinecraftClient.getInstance().player;

        if (player == null)
            throw new IllegalStateException("Client Player is null");

        this.player = player;
        this.entity = entity;

        this.volume = 0.75F;
        this.relative = false;
        this.attenuationType = AttenuationType.LINEAR;
        this.x = entity.getX();
        this.y = entity.getY();
        this.z = entity.getZ();
    }

    @Override
    public void tick() {
        this.x = entity.getX();
        this.y = entity.getY();
        this.z = entity.getZ();
    }
}
