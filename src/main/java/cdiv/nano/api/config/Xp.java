package cdiv.nano.api.config;

import cdiv.nano.api.Option;

/**
 * <p>Configuration related to xp</p>
 */
public class Xp {
    /**
     * <p>Whether mob xp scales based on scale when they die</p>
     */
    public static Option<Boolean> xpScalingEnabled = new Option<>(true);
}
