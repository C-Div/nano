package cdiv.nano.api;

import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public abstract class SpurtMode<ConfigurationType extends SpurtModeConfig> {
    @NotNull ConfigurationType configuration;

    public SpurtMode(@NotNull ConfigurationType initialConfiguration) {
        this.configuration = initialConfiguration;
    }

    public abstract void onTick(PlayerEntity player);

    public abstract void applyConfig(@NotNull ConfigurationType configuration);
    public abstract @NotNull ConfigurationType getConfig();
}
