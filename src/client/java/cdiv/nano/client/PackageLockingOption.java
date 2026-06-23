package cdiv.nano.client;

import cdiv.nano.api.BaseLockingOption;
import cdiv.nano.api.Option;

/**
 * Similar to {@link BaseLockingOption}, but can only be locked by Nano
 * @see BaseLockingOption
 * @param <T> The value type
 */
public class PackageLockingOption<T> extends BaseLockingOption<T> {
    /**
     * Constructs a new PackageLockingOption with a null value
     * @see Option#Option()
     */
    public PackageLockingOption() {
        super();
    }

    /**
     * Constructs a new PackageLockingOption with the given default value
     * @param value The default value
     * @see Option#Option(Object)
     */
    public PackageLockingOption(final T value) {
        super(value);
    }

    /**
     * Constructs a new PackageLockingOption with the given default priority and value
     * @param priority The default priority
     * @param value The default value
     * @see Option#Option(int, Object)
     */
    public PackageLockingOption(final int priority, final T value) {
        super(priority, value);
    }

    /**
     * Locks the option preventing its value and priority from being changed
     */
    synchronized boolean lock() {
        return super.baseLock();
    }

    /**
     * Locks the option and returns its value
     * @return The current value of the LockingOption
     */
    T lockAndGet() {
        return super.baseLockAndGet();
    }
}