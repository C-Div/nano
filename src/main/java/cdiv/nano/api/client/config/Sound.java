package cdiv.nano.api.client.config;

import cdiv.nano.api.ConfigurableOption;
import cdiv.nano.api.Option;
import cdiv.nano.integration.MidnightLibIntegration;

/**
 * <p>Configuration related to sound</p>
 */
public class Sound {
    /**
     * <p>Whether growth sounds are enabled</p>
     */
    public static ConfigurableOption<Boolean> growthSoundsEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.growthSoundsEnabled);

    /**
     * <p>Whether prolonged growth sounds are enabled</p>
     */
    public static ConfigurableOption<Boolean> longGrowthSoundsEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.longGrowthSoundsEnabled);
}
