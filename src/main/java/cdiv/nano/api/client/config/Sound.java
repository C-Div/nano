package cdiv.nano.api.client.config;

import cdiv.nano.api.Option;

/**
 * <p>Configuration related to sound</p>
 */
public class Sound {
    /**
     * <p>Whether growth sounds are enabled</p>
     */
    public static Option<Boolean> growthSoundsEnabled = new Option<>(true);

    /**
     * <p>Whether prolonged growth sounds are enabled</p>
     */
    public static Option<Boolean> longGrowthSoundsEnabled = new Option<>(true);
}
