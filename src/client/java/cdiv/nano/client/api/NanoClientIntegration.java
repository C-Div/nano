package cdiv.nano.client.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * A Nano Client Integration
 *
 * <p>In {@code fabric.mod.json}, the entrypoint is defined with {@code nano-client} key</p>
 */
@FunctionalInterface
@Environment(EnvType.CLIENT)
public interface NanoClientIntegration {
    void onNanoInitialize();
}
