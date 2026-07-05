package cdiv.nano.client.api.config;

import cdiv.nano.api.Option;

/**
 * <p>Configuration related to the Apple Skin mod</p>
 */
public class AppleSkin {
    /**
     * <p>Whether the {@link cdiv.nano.client.integration.AppleSkinIntegration} is enabled.</p>
     */
    public static Option<Boolean> integrationEnabled = new Option<>(true);
}
