package cdiv.nano.api.config;

import cdiv.nano.api.ConfigurableOption;
import cdiv.nano.integration.MidnightLibIntegration;

/**
 * <p>Configuration related to items</p>
 */
public class Item {
    /**
     * <p>Whether item scaling is enabled</p>
     */
    public static ConfigurableOption<Boolean> itemScalingEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.itemScalingEnabled);

    /**
     * <p>Whether item drop scaling is enabled</p>
     */
    public static ConfigurableOption<Boolean> itemDropScalingEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.itemDropScalingEnabled);
}
