package cdiv.nano.api.config;

import cdiv.nano.api.ConfigurableOption;
import cdiv.nano.integration.MidnightLibIntegration;

/**
 * <p>Configuration related to sound</p>
 */
public class Sound {
    /**
     * <p>Whether entity sounds scale with {@link virtuoel.pehkui.api.ScaleData}</p>
     */
    public static ConfigurableOption<Boolean> soundScalingEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.soundScalingEnabled);
}
