package cdiv.nano.api;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * Similar to {@link BaseLockingOption}, but can only be locked by Nano
 * @see BaseLockingOption
 * @param <T> The value type
 */
public class PackageLockingOption<T> extends BaseLockingOption<T> {
    /**
     * Constructs a new {@link PackageLockingOption} with a null value
     * @see Option#Option()
     */
    @SuppressWarnings("unused")
    @ApiStatus.Internal
    public PackageLockingOption() {
        super();
    }

    /**
     * Constructs a new {@link PackageLockingOption} with the given default value
     * @param value The default value
     * @see Option#Option(Object)
     */
    @ApiStatus.Internal
    public PackageLockingOption(@Nullable final T value) {
        super(value);
    }

    /**
     * Constructs a new {@link PackageLockingOption} with the given default priority and value
     * @param priority The default priority
     * @param value The default value
     * @see Option#Option(int, Object)
     */
    @SuppressWarnings("unused")
    @ApiStatus.Internal
    public PackageLockingOption(final int priority, @Nullable final T value) {
        super(priority, value);
    }

    /**
     * Locks the option preventing its value and priority from being changed
     */
    @SuppressWarnings("unused")
    @ApiStatus.Internal
    public synchronized boolean lock() {
        return super.baseLock();
    }

    /**
     * Locks the option and returns its value
     * @return The current value of the {@link PackageLockingOption}
     */
    @Nullable
    @ApiStatus.Internal
    public T lockAndGet() {
        return super.baseLockAndGet();
    }
}