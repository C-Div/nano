package cdiv.nano.api;

import org.jetbrains.annotations.NotNull;

/**
 * @see PublicLockingOption
 */
@SuppressWarnings("unused")
public class LockingOption<T> extends PublicLockingOption<T> {
    /**
     * Constructs a new {@link LockingOption} with the given default value
     *
     * @param value The default value
     * @see Option#Option(Object)
     */
    public LockingOption(@NotNull T value) {
        super(value);
    }

    /**
     * Constructs a new {@link LockingOption} with the given default priority and value
     *
     * @param priority The default priority
     * @param value    The default value
     * @see Option#Option(int, Object)
     */
    public LockingOption(int priority, @NotNull T value) {
        super(priority, value);
    }
}
