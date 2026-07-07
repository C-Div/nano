package cdiv.nano.api.config;

import cdiv.nano.api.ConfigurableOption;
import cdiv.nano.integration.MidnightLibIntegration;

/**
 * <p>Configuration related to xp</p>
 */
public class Xp {
    /**
     * <p>Whether mob xp scales based on scale when they die</p>
     */
    public static ConfigurableOption<Boolean> xpScalingEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.xpScalingEnabled);
}
