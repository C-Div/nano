package cdiv.nano.api.config;

import cdiv.nano.api.Option;

/**
 * <p>Configuration related to food</p>
 */
public class Food {
    /**
     * <p>Whether food scaling is enabled</p>
     */
    public static Option<Boolean> foodScalingEnabled = new Option<>(true);

    /**
     * <p>Whether food nutrition scaling is enabled</p>
     */
    public static Option<Boolean> foodNutritionScalingEnabled = new Option<>(true);

    /**
     * <p>Whether food saturation scaling is enabled</p>
     */
    public static Option<Boolean> foodSaturationScalingEnabled = new Option<>(true);

    /**
     * <p>Whether food overeating is enabled</p>
     */
    public static Option<Boolean> overeatingEnabled = new Option<>(true);

    /**
     * <p>The minimum relative scale of food to the eater to be considered overeating</p>
     */
    public static Option<Double> overeatingThreshold = new Option<>(1.0D);
}
