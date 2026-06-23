package cdiv.nano.api.config;

import cdiv.nano.api.Option;

/**
 * <p>Configuration related to entity pathfinding</p>
 */
public class Pathfinding {
    /**
     * <p>Whether mob pathfinding scales with {@link virtuoel.pehkui.api.ScaleData}</p>
     * @implNote Applies to a limited amount of pathfinding
     */
    public static Option<Boolean> pathfindingScalingEnabled = new Option<>(true);

    /**
     * <p>Whether mob pathfinding has the minimum amount of blocks to travel scale with {@link virtuoel.pehkui.api.ScaleData}</p>
     * @implNote May have compatibility issues with other mods
     * @implNote Applies to a limited amount of pathfinding
     */
    public static Option<Boolean> pathfindingMinimumScalingEnabled = new Option<>(true);
}
