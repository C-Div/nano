package cdiv.nano.api.config;

import cdiv.nano.PackageLockingOption;
import cdiv.nano.api.Option;

/**
 * <p>Configuration related to applied pehkui scale modifiers</p>
 */
public class ScaleModifiers {
    /**
     * <p>Whether scale modifiers are applied</p>
     */
    public static Option<Boolean> scaleModifiersEnabled = new Option<>(true);

    /**
     * <p>Whether the knockback scale modifier is applied</p>
     */
    public static Option<Boolean> knockbackScaleModifierEnabled = new Option<>(true);

    /**
     * <p>Whether the held item scale modifier is applied</p>
     */
    public static Option<Boolean> heldItemScaleModifierEnabled = new Option<>(true);

    /**
     * <p>Whether the drops base scale modifier is applied</p>
     */
    public static Option<Boolean> dropsBaseScaleModifierEnabled = new Option<>(true);

    /**
     * <p>Whether the attack damage scale modifier is applied</p>
     */
    public static Option<Boolean> attackDamageScaleModifierEnabled = new Option<>(true);

    /**
     * <p>Whether the attack speed scale modifier is applied</p>
     */
    public static Option<Boolean> attackSpeedScaleModifierEnabled = new Option<>(true);

    /**
     * <p>Whether the mining speed scale modifier is applied</p>
     */
    public static Option<Boolean> miningSpeedScaleModifierEnabled = new Option<>(true);
}
