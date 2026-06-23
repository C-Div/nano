package cdiv.nano.api;

/**
 * <p>Similar to {@link cdiv.nano.api.Option}, but provides the ability to lock the option, rejecting attempted updates</p>
 * @see cdiv.nano.api.Option
 * @param <T> The value type
 */
public abstract class BaseLockingOption<T> extends Option<T> {
    protected volatile boolean locked = false;

    /**
     * Constructs a new BaseLockingOption with a null value
     * @see Option#Option()
     */
    protected BaseLockingOption() {
        super();
    }

    /**
     * Constructs a new BaseLockingOption with the given default value
     * @param value The default value
     * @see Option#Option(Object)
     */
    protected BaseLockingOption(final T value) {
        super(value);
    }

    /**
     * Constructs a new BaseLockingOption with the given default priority and value
     * @param priority The default priority
     * @param value The default value
     * @see Option#Option(int, Object)
     */
    protected BaseLockingOption(final int priority, final T value) {
        super(priority, value);
    }

    /**
     * Sets the value based on the given priority and whether the option is locked
     * @param value The new value
     * @param priority The priority of the value
     * @return Whether the value was changed
     */
    @Override
    public boolean set(final T value, final int priority) {
        if (this.locked)
            return false;

        return super.set(value, priority);
    }

    /**
     * @return Whether the option is locked
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Adds the given listener for value or priority changes
     * @param listener The listener
     * @return Whether the listener was added
     */
    @Override
    public boolean addListener(OptionListener<T> listener) {
        if (this.locked)
            return false;

        return super.addListener(listener);
    }

    /**
     * Locks the option preventing its value and priority from being changed
     */
    protected synchronized boolean baseLock() {
        if (this.locked)
            return false;

        listeners.clear();
        this.locked = true;
        return true;
    }

    /**
     * Locks the option and returns its value
     * @return The current value of the LockingOption
     */
    protected T baseLockAndGet() {
        baseLock();
        return get();
    }
}
