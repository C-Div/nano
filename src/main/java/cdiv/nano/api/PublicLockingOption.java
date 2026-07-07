package cdiv.nano.api;

import org.jetbrains.annotations.Nullable;

/**
 * Similar to {@link BaseLockingOption}, but can only be locked by Nano
 * @see BaseLockingOption
 * @param <T> The value type
 */
public class PublicLockingOption<T> extends BaseLockingOption<T> {
    /**
     * Constructs a new {@link PublicLockingOption} with a null value
     * @see Option#Option()
     */
    public PublicLockingOption() {
        super();
    }

    /**
     * Constructs a new {@link PublicLockingOption} with the given default value
     * @param value The default value
     * @see Option#Option(Object)
     */
    public PublicLockingOption(@Nullable final T value) {
        super(value);
    }

    /**
     * Constructs a new {@link PublicLockingOption} with the given default priority and value
     * @param priority The default priority
     * @param value The default value
     * @see Option#Option(int, Object)
     */
    public PublicLockingOption(final int priority, @Nullable final T value) {
        super(priority, value);
    }

    /**
     * Locks the option preventing its value and priority from being changed
     */
    public synchronized boolean lock() {
        return super.baseLock();
    }

    /**
     * Locks the option and returns its value
     * @return The current value of the {@link PublicLockingOption}
     */
    @Nullable
    public T lockAndGet() {
        return super.baseLockAndGet();
    }
}