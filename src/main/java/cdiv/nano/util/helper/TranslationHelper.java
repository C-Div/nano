package cdiv.nano.util.helper;

import cdiv.nano.Nano;
import net.minecraft.text.MutableText;

public class TranslationHelper {
    public static MutableText screen(String path) {
        return Nano.translation("screen", path);
    }

    public static MutableText screen(String path, Object... args) {
        return Nano.translation("screen", path, args);
    }

    public static MutableText itemGroup(String path) {
        return Nano.translation("itemGroup", path);
    }

    @SuppressWarnings("unused")
    public static MutableText itemGroup(String path, Object... args) {
        return Nano.translation("itemGroup", path, args);
    }

    public static MutableText command(String path) {
        return Nano.translation("command", path);
    }

    @SuppressWarnings("unused")
    public static MutableText command(String path, Object... args) {
        return Nano.translation("command", path, args);
    }

    @SuppressWarnings("unused")
    public static MutableText component(String path) {
        return Nano.translation("component", path);
    }

    public static MutableText component(String path, Object... args) {
        return Nano.translation("component", path, args);
    }
}
