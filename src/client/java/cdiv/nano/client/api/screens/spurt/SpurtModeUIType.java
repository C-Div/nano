package cdiv.nano.client.api.screens.spurt;

import cdiv.nano.client.screens.roleplay.SpurtScreen;

import java.util.function.BooleanSupplier;

public record SpurtModeUIType<T extends SpurtModeUI>(
    SpurtModeUIConstructor<T> constructor,
    BooleanSupplier isSelectable
) {}