package cdiv.nano.client.sounds;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class GrowthSound extends PositionedSoundInstance {
    @NotNull final PlayerEntity player;
    @NotNull final Entity entity;

    public GrowthSound(SoundEvent sound, SoundCategory category, @NotNull Entity entity) {
        super(sound, category, 1.0F, 1.0F, Random.create(), BlockPos.ORIGIN);
        final PlayerEntity player = MinecraftClient.getInstance().player;

        if (player == null)
            throw new IllegalStateException("Client Player is null");

        this.player = player;
        this.entity = entity;
    }

    @Override
    public double getX() {
        return entity.getX();
    }

    @Override
    public double getY() {
        return entity.getY();
    }

    @Override
    public double getZ() {
        return entity.getZ();
    }
}
