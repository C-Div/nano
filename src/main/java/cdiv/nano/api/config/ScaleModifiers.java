package cdiv.nano.api.config;

import cdiv.nano.api.ConfigurableOption;
import cdiv.nano.integration.MidnightLibIntegration;

/**
 * <p>Configuration related to applied pehkui scale modifiers</p>
 */
public class ScaleModifiers {
    /**
     * <p>Whether scale modifiers are applied</p>
     */
    public static ConfigurableOption<Boolean> scaleModifiersEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.scaleModifiersEnabled);

    /**
     * <p>Whether the knockback scale modifier is applied</p>
     */
    public static ConfigurableOption<Boolean> knockbackScaleModifierEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.knockbackScaleModifierEnabled);

    /**
     * <p>Whether the held item scale modifier is applied</p>
     */
    public static ConfigurableOption<Boolean> heldItemScaleModifierEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.heldItemScaleModifierEnabled);

    /**
     * <p>Whether the drops base scale modifier is applied</p>
     */
    public static ConfigurableOption<Boolean> dropsBaseScaleModifierEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.dropsBaseScaleModifierEnabled);

    /**
     * <p>Whether the attack damage scale modifier is applied</p>
     */
    public static ConfigurableOption<Boolean> attackDamageScaleModifierEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.attackDamageScaleModifierEnabled);

    /**
     * <p>Whether the attack speed scale modifier is applied</p>
     */
    public static ConfigurableOption<Boolean> attackSpeedScaleModifierEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.attackSpeedScaleModifierEnabled);

    /**
     * <p>Whether the mining speed scale modifier is applied</p>
     */
    public static ConfigurableOption<Boolean> miningSpeedScaleModifierEnabled = new ConfigurableOption<>(true,
        () -> MidnightLibIntegration.miningSpeedScaleModifierEnabled);
}
