package cdiv.nano.api.client.config;

import cdiv.nano.api.ConfigurableOption;
import cdiv.nano.integration.MidnightLibIntegration;

/**
 * <p>Configuration related to the Apple Skin mod</p>
 */
public class AppleSkin {
    /**
     * <p>Whether the Apple Skin integration is enabled.</p>
     */
    public static ConfigurableOption<Boolean> integrationEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.appleSkinIntegrationEnabled);
}
