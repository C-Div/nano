package cdiv.nano.registry;

import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class UniqueRegistry<T> {
    private final Function<T, Identifier> IDENTIFIER_FROM_OBJECT;
    final Set<Identifier> ENTRIES = new HashSet<>();

    public UniqueRegistry(Function<T, Identifier> identifierFromObject) {
        IDENTIFIER_FROM_OBJECT = identifierFromObject;
    }

    public boolean has(Identifier identifier) {
        return ENTRIES.contains(identifier);
    }

    public boolean has(T object) {
        return ENTRIES.contains(IDENTIFIER_FROM_OBJECT.apply(object));
    }

    public boolean register(Identifier identifier) {
        return ENTRIES.add(identifier);
    }
}
