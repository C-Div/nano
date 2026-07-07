package cdiv.nano.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Similar to {@link BaseLockingOption}, but can only be locked by Nano
 * @see BaseLockingOption
 * @param <T> The value type
 */
public class PublicLockingOption<T> extends BaseLockingOption<T> {
    /**
     * Constructs a new {@link PublicLockingOption} with the given default value
     * @param value The default value
     * @see Option#Option(Object)
     */
    @SuppressWarnings("unused")
    public PublicLockingOption(@NotNull final T value) {
        super(value);
    }

    /**
     * Constructs a new {@link PublicLockingOption} with the given default priority and value
     * @param priority The default priority
     * @param value The default value
     * @see Option#Option(int, Object)
     */
    @SuppressWarnings("unused")
    public PublicLockingOption(final int priority, @NotNull final T value) {
        super(priority, value);
    }

    /**
     * Locks the option preventing its value and priority from being changed
     */
    @SuppressWarnings("unused")
    public synchronized boolean lock() {
        return super.baseLock();
    }

    /**
     * Locks the option and returns its value
     * @return The current value of the {@link PublicLockingOption}
     */
    @Nullable
    @SuppressWarnings("unused")
    public T lockAndGet() {
        return super.baseLockAndGet();
    }
}