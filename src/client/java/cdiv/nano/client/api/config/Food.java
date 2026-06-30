package cdiv.nano.client.api.config;

import cdiv.nano.api.Option;

/**
 * <p>Configuration related to food</p>
 */
public class Food {
    /**
     * <p>Whether the {@link cdiv.nano.client.integration.AppleSkinIntegration} is enabled.</p>
     */
    public static Option<Boolean> appleSkinIntegrationEnabled = new Option<>(true);
}
