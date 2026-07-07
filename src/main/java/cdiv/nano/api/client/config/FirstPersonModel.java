package cdiv.nano.api.client.config;

import cdiv.nano.api.Option;

/**
 * <p>Configuration related to the First Person Model mod</p>
 */
public class FirstPersonModel {
    /**
     * <p>Whether the body offset scales with {@link virtuoel.pehkui.api.ScaleData}.</p>
     */
    public static Option<Boolean> bodyOffsetScalingEnabled = new Option<>(true);
}
