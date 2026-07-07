package cdiv.nano.client.api.config;

import cdiv.nano.api.PackageLockingOption;
import net.fabricmc.loader.api.FabricLoader;

public class Keybinding {
    /**
     * <p>Whether nano keybinds are enabled</p>
     * @apiNote Locks after each {@link cdiv.nano.api.NanoIntegration} has run
     */
    public static PackageLockingOption<Boolean> keybindsEnabled = new PackageLockingOption<>(true);

    /**
     * <p>Whether nano debug keybinds are enabled</p>
     * @apiNote Locks after each {@link cdiv.nano.api.NanoIntegration} has run
     */
    public static PackageLockingOption<Boolean> debugKeybindsEnabled = new PackageLockingOption<>(FabricLoader.getInstance().isDevelopmentEnvironment());
}
