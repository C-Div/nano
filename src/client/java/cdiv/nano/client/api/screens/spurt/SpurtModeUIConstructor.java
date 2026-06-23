package cdiv.nano.client.api.screens.spurt;

import cdiv.nano.client.screens.roleplay.SpurtScreen;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface SpurtModeUIConstructor<T extends SpurtModeUI> {
    T construct(@NotNull SpurtScreen screen);
}
