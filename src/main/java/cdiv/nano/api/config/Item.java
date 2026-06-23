package cdiv.nano.api.config;

import cdiv.nano.api.Option;

/**
 * <p>Configuration related to items</p>
 */
public class Item {
    /**
     * <p>Whether item scaling is enabled</p>
     */
    public static Option<Boolean> itemScalingEnabled = new Option<>(true);

    /**
     * <p>Whether item drop scaling is enabled</p>
     */
    public static Option<Boolean> itemDropScalingEnabled = new Option<>(true);
}
