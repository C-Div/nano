package cdiv.nano.api;

import org.jetbrains.annotations.Nullable;

/**
 * <p>Similar to {@link cdiv.nano.api.Option}, but provides the ability to lock the option, rejecting attempted updates</p>
 * @see cdiv.nano.api.Option
 * @param <T> The value type
 */
public abstract class BaseLockingOption<T> extends Option<T> {
    protected volatile boolean locked = false;

    /**
     * Constructs a new {@link BaseLockingOption} with a null value
     * @see Option#Option()
     */
    protected BaseLockingOption() {
        super();
    }

    /**
     * Constructs a new {@link BaseLockingOption} with the given default value
     * @param value The default value
     * @see Option#Option(Object)
     */
    protected BaseLockingOption(@Nullable final T value) {
        super(value);
    }

    /**
     * Constructs a new {@link BaseLockingOption} with the given default priority and value
     * @param priority The default priority
     * @param value The default value
     * @see Option#Option(int, Object)
     */
    protected BaseLockingOption(final int priority, @Nullable final T value) {
        super(priority, value);
    }

    /**
     * Sets the value based on the given priority and whether the option is locked
     * @param value The new value
     * @param priority The priority of the value
     * @return Whether the value was changed
     */
    @Override
    public boolean set(@Nullable final T value, final int priority) {
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
     * @return The current value of the {@link BaseLockingOption}
     */
    @Nullable
    protected T baseLockAndGet() {
        baseLock();
        return get();
    }
}
