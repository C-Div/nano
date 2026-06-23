package cdiv.nano.api.config;

import cdiv.nano.api.Option;

/**
 * <p>Configuration related to mob loot</p>
 */
public class Loot {
    /**
     * <p>Whether mob loot scales based on {@link virtuoel.pehkui.api.ScaleData} when they die</p>
     */
    public static Option<Boolean> lootScalingEnabled = new Option<>(true);
}
