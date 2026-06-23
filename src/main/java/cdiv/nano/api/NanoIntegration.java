package cdiv.nano.api;

/**
 * A Nano Integration
 *
 * <p>In {@code fabric.mod.json}, the entrypoint is defined with {@code nano} key</p>
 */
@FunctionalInterface
public interface NanoIntegration {
    void onNanoInitialize();
}
