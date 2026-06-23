package cdiv.nano.api;

@FunctionalInterface
public interface OptionListener<T> {
    void onChanged(Option<T> option, final T oldValue, final T newValue, int oldPriority, int newPriority);
}