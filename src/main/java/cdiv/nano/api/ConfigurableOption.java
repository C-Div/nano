package cdiv.nano.api;

import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * <p>Similar to {@link Option}, but is linked to a midnight configuration option.</p>
 * @param <T> The value type
 */
public class ConfigurableOption<T> extends Option<T> {
    private final Supplier<T> configurationSupplier;

    /**
     * Constructs a new {@link ConfigurableOption} with the given default value
     * @param value The default value
     */
    public ConfigurableOption(final @Nullable T value, final Supplier<T> configurationSupplier) {
        super(value);
        this.configurationSupplier = configurationSupplier;
    }

    /**
     * @return The current value
     */
    @Nullable
    @Override
    public T get() {
        if (getPriority() <= 0)
            return configurationSupplier.get();

        return super.get();
    }
}
