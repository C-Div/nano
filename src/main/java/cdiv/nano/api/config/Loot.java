package cdiv.nano.api.config;

import cdiv.nano.api.ConfigurableOption;
import cdiv.nano.api.Option;
import cdiv.nano.integration.MidnightLibIntegration;

/**
 * <p>Configuration related to mob loot</p>
 */
public class Loot {
    /**
     * <p>Whether mob loot scales based on {@link virtuoel.pehkui.api.ScaleData} when they die</p>
     */
    public static ConfigurableOption<Boolean> lootScalingEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.lootScalingEnabled);
}
