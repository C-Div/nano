package cdiv.nano.client.api.screens;

import cdiv.nano.api.Option;
import cdiv.nano.client.api.screens.spurt.SpurtModeUI;
import cdiv.nano.client.api.screens.spurt.SpurtModeUIType;
import cdiv.nano.client.screens.roleplay.spurt.TestMode;

import java.util.LinkedList;
import java.util.List;

public class Spurt {
    public static Option<Boolean> defaultModesEnabled = new Option<>(true);

    /**
     * <p>Types for modes that are cycled between in the Spurt Roleplay Screen</p>
     */
    public static LinkedList<SpurtModeUIType<? extends SpurtModeUI>> spurtModes = new LinkedList<>(
        List.of(
            TestMode.TYPE
        )
    );
}
