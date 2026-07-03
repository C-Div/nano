package cdiv.nano;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
public class Sounds {
    public static final SoundEvent STOMP_1 = register("stomp-1");
    public static final SoundEvent STOMP_2 = register("stomp-2");
    public static final SoundEvent STOMP_3 = register("stomp-3");
    public static final SoundEvent GROWTH_1 = register("growth-1");
    public static final SoundEvent GROWTH_2 = register("growth-2");
    public static final SoundEvent LONG_GROWTH_1 = register("long-growth-1");
    public static final SoundEvent LONG_GROWTH_2 = register("long-growth-2");

    public static final List<SoundEvent> SHORT_GROWTH_SOUNDS = List.of(GROWTH_1, GROWTH_2);
    public static final List<SoundEvent> LONG_GROWTH_SOUNDS = List.of(LONG_GROWTH_1, LONG_GROWTH_2);
    public static final List<SoundEvent> ALL_GROWTH_SOUNDS = List.of(GROWTH_1, GROWTH_2, LONG_GROWTH_1, LONG_GROWTH_2);
    private static final Random RANDOM = new Random();

    public static SoundEvent getRandomShortGrowthSound() {
        int index = RANDOM.nextInt(SHORT_GROWTH_SOUNDS.size());
        return SHORT_GROWTH_SOUNDS.get(index);
    }

    public static SoundEvent getRandomLongGrowthSound() {
        int index = RANDOM.nextInt(LONG_GROWTH_SOUNDS.size());
        return LONG_GROWTH_SOUNDS.get(index);
    }

    public static SoundEvent register(String name) {
        Identifier identifier = Nano.id(name);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void initialize() {}
}
