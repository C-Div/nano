package cdiv.nano.api.config;

import cdiv.nano.api.Option;

/**
 * <p>Configuration related to stepping</p>
 */
public class Stepping {
    /**
     * <p>Whether mobs get damaged when bigger mobs walk into them</p>
     */
    public static Option<Boolean> damageEnabled = new Option<>(true);

    /**
     * <p>Whether the amount of blocks traversed between mob step sounds scales with {@link virtuoel.pehkui.api.ScaleData}</p>
     */
    public static Option<Boolean> soundScalingEnabled = new Option<>(true);

    /**
     * <p>The minimum relative height for an entity to be considered big enough to deal stepping damage</p>
     */
    public static Option<Double> damageRelativeHeightThreshold = new Option<>(2.5D);
}
