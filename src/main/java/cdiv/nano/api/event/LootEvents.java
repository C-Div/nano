package cdiv.nano.api.event;

import cdiv.nano.api.config.Loot;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;

/**
 * <p>Events related to mob loot</p>
 */
public class LootEvents {
    public interface Scale {
        double scaleLoot(ItemStack stack, LootContext context);
    }

    /**
     * <p>This event can be used to modify how the mod scales loot for mob entities</p>
     * <h4>Example: Doubling the scaling of loot</h4>
     * {@snippet :
     * LootEvents.SCALE.register((ItemStack stack, LootContext context) -> 2.0F);
     * }
     * <br>
     * @apiNote Use {@link Loot#lootScalingEnabled} to disable loot scaling
     */
    public static final Event<Scale> SCALE = EventFactory.createArrayBacked(Scale.class, listeners -> (itemStack, context) -> {
        double finalMultiplier = 1.0F;

        for (Scale listener : listeners) {
            finalMultiplier *= listener.scaleLoot(itemStack, context);

            if (finalMultiplier <= 0.0F)
                break;
        }

        return finalMultiplier;
    });
}
