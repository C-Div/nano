package cdiv.nano.client.api.screens.spurt;

import java.util.function.BooleanSupplier;

public record SpurtModeUIType<T extends SpurtModeUI>(
    SpurtModeUIConstructor<T> constructor,
    BooleanSupplier isSelectable
) {}