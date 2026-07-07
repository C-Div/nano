package cdiv.nano.api.config;

import cdiv.nano.api.ConfigurableOption;
import cdiv.nano.api.NotImplemented;
import cdiv.nano.api.Option;
import cdiv.nano.integration.MidnightLibIntegration;
import net.minecraft.item.Item;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Configuration related to food</p>
 */
public class Food {
    /**
     * <p>Whether food scaling is enabled</p>
     */
    public static ConfigurableOption<Boolean> foodScalingEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.foodScalingEnabled);

    /**
     * <p>Whether food nutrition scaling is enabled</p>
     */
    public static ConfigurableOption<Boolean> foodNutritionScalingEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.foodNutritionScalingEnabled);

    /**
     * <p>Whether food saturation scaling is enabled</p>
     */
    public static ConfigurableOption<Boolean> foodSaturationScalingEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.foodSaturationScalingEnabled);

    /**
     * <p>A set containing food items that should never have their nutrition or saturation values changed.</p>
     * @implNote Not implemented
     */
    @NotImplemented
    @SuppressWarnings("unused")
    public static Set<Item> immuneItems = new HashSet<>();

    /**
     * <p>Whether food overeating is enabled</p>
     */
    public static ConfigurableOption<Boolean> overeatingEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.overeatingEnabled);

    /**
     * <p>The minimum relative scale of food to the eater to be considered overeating</p>
     */
    public static ConfigurableOption<Double> overeatingThreshold = new ConfigurableOption<>(1.0D,
        () -> MidnightLibIntegration.overeatingThreshold);
}
