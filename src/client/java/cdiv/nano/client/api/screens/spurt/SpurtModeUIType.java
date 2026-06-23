package cdiv.nano.client.api.screens.spurt;

import cdiv.nano.client.screens.roleplay.SpurtScreen;

import java.util.function.BooleanSupplier;
import java.util.function.Function;

public record SpurtModeUIType<T extends SpurtModeUI>(
    SpurtModeUIConstructor<T> constructor,
    BooleanSupplier isSelectable
) {}