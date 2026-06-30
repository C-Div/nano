package cdiv.nano.api.config;

import cdiv.nano.api.Option;

/**
 * <p>Configuration related to sound</p>
 */
public class Sound {
    /**
     * <p>Whether entity sounds scale with {@link virtuoel.pehkui.api.ScaleData}</p>
     */
    public static Option<Boolean> soundScalingEnabled = new Option<>(true);
}
