package cdiv.nano.api;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static cdiv.nano.Nano.LOGGER;

/**
 * <p>An API option that uses the value with the highest priority</p>
 * @param <T> The value type
 */
public class Option<T> { // FUTURE: Rework this annoying shit
    @Nullable
    public final T defaultValue;

    @Nullable
    private T value = null;
    private int priority = 0;

    protected final List<OptionListener<T>> listeners = new CopyOnWriteArrayList<>();

    /**
     * Constructs a new {@link Option} with a null value
     */
    public Option() {
        this.defaultValue = null;
    }

    /**
     * Constructs a new {@link Option} with the given default value
     * @param value The default value
     */
    public Option(final @Nullable T value) {
        this.defaultValue = value;
        this.value = value;
    }

    /**
     * Constructs a new {@link Option} with the given default priority and value
     * @param priority The default priority
     * @param value The default value
     */
    public Option(final int priority, final @Nullable T value) {
        this.priority = priority;
        this.defaultValue = value;
        this.value = value;
    }

    /**
     * @return The priority of the current value
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @return The current value
     */
    @Nullable
    public T get() {
        return value;
    }

    /**
     * @return Whether the current value is null
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * Sets the value based on the given priority
     * @param value The new value
     * @param priority The priority of the value
     * @return Whether the value was changed
     *
     * @apiNote A current priority of 0 specially allows values of priority 0 to override it
     */
    public boolean set(@Nullable final T value, final int priority) {
        final T oldValue;
        final int oldPriority;

        synchronized (this) {
            oldPriority = this.priority;

            if (priority <= oldPriority && (oldPriority != 0 && priority != 0))
                return false;

            oldValue = this.value;

            this.priority = priority;
            this.value = value;
        }

        for (OptionListener<T> listener : listeners) {
            try {
                listener.onChanged(this, oldValue, value, oldPriority, priority);
            } catch (Exception exception) {
                LOGGER.error("Listener for Option listener failed", exception);
            }
        }

        return true;
    }

    /**
     * Adds the given listener for value or priority changes
     * @param listener The listener
     */
    public boolean addListener(final OptionListener<T> listener) {
        return listeners.add(listener);
    }

    /**
     * Removes the given listener from value or priority changes
     * @param listener The listener
     */
    public boolean removeListener(final OptionListener<T> listener) {
        return listeners.remove(listener);
    }
}
