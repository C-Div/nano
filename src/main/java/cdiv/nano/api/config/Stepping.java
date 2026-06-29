package cdiv.nano.api.config;

import cdiv.nano.api.Option;
import net.minecraft.entity.EntityType;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Configuration related to stepping</p>
 */
public class Stepping {
    /**
     * <p>Whether mobs get damaged when bigger mobs walk into them</p>
     */
    public static Option<Boolean> damageEnabled = new Option<>(true);

    /**
     * <p>Whether the amount of blocks traversed between mob step sounds scales with {@link virtuoel.pehkui.api.ScaleData}</p>
     */
    public static Option<Boolean> soundScalingEnabled = new Option<>(true);

    /**
     * <p>The minimum relative height for an entity to be considered big enough to deal stepping damage</p>
     */
    public static Option<Double> damageRelativeHeightThreshold = new Option<>(2.5D);

    /**
     * <p>A set containing entities that should never take step damage.</p>
     */
    public static Set<EntityType<?>> immuneEntities = new HashSet<>(Set.of(
        EntityType.AREA_EFFECT_CLOUD,
        EntityType.ARMOR_STAND,
        EntityType.ARROW,
        EntityType.BLOCK_DISPLAY,
        EntityType.BOAT,
        EntityType.BREEZE_WIND_CHARGE,
        EntityType.CHEST_BOAT,
        EntityType.CHEST_MINECART,
        EntityType.COMMAND_BLOCK_MINECART,
        EntityType.DRAGON_FIREBALL,
        EntityType.END_CRYSTAL,
        EntityType.ENDER_PEARL,
        EntityType.EVOKER_FANGS,
        EntityType.EXPERIENCE_BOTTLE,
        EntityType.EXPERIENCE_ORB,
        EntityType.EYE_OF_ENDER,
        EntityType.FALLING_BLOCK,
        EntityType.FIREWORK_ROCKET,
        EntityType.FURNACE_MINECART,
        EntityType.GLOW_ITEM_FRAME,
        EntityType.HOPPER_MINECART,
        EntityType.INTERACTION,
        EntityType.ITEM,
        EntityType.ITEM_DISPLAY,
        EntityType.ITEM_FRAME,
        EntityType.OMINOUS_ITEM_SPAWNER,
        EntityType.FIREBALL,
        EntityType.LEASH_KNOT,
        EntityType.LIGHTNING_BOLT,
        EntityType.LLAMA_SPIT,
        EntityType.MARKER,
        EntityType.MINECART,
        EntityType.PAINTING,
        EntityType.POTION,
        EntityType.SHULKER_BULLET,
        EntityType.SMALL_FIREBALL,
        EntityType.SNOWBALL,
        EntityType.SPAWNER_MINECART,
        EntityType.SPECTRAL_ARROW,
        EntityType.TEXT_DISPLAY,
        EntityType.TNT,
        EntityType.TNT_MINECART,
        EntityType.TRIDENT,
        EntityType.WIND_CHARGE,
        EntityType.WITHER_SKULL,
        EntityType.FISHING_BOBBER
    ));
}
