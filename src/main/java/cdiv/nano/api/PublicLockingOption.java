package cdiv.nano.api;

/**
 * Similar to {@link BaseLockingOption}, but can only be locked by Nano
 * @see BaseLockingOption
 * @param <T> The value type
 */
public class PublicLockingOption<T> extends BaseLockingOption<T> {
    /**
     * Constructs a new PackageLockingOption with a null value
     * @see Option#Option()
     */
    public PublicLockingOption() {
        super();
    }

    /**
     * Constructs a new PackageLockingOption with the given default value
     * @param value The default value
     * @see Option#Option(Object)
     */
    public PublicLockingOption(final T value) {
        super(value);
    }

    /**
     * Constructs a new PackageLockingOption with the given default priority and value
     * @param priority The default priority
     * @param value The default value
     * @see Option#Option(int, Object)
     */
    public PublicLockingOption(final int priority, final T value) {
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
     * @return The current value of the LockingOption
     */
    public T lockAndGet() {
        return super.baseLockAndGet();
    }
}