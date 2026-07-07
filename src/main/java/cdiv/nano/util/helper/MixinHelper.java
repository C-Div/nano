package cdiv.nano.util.helper;

import cdiv.nano.access.MovingPlayer;
import cdiv.nano.api.config.Pathfinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

import java.util.function.DoubleUnaryOperator;

public class MixinHelper {
    public static final float EPSILON = 0.001F;

    public static Entity asEntity(Object object) {
        return (Entity) object;
    }

    public static LivingEntity asLivingEntity(Object object) {
        return (LivingEntity) object;
    }

    public static ItemStack asItemStack(Object object) {
        return (ItemStack) object;
    }

    /**
     * Returns the float constant used for calculating the next step sound distance
     * @param entity The entity
     * @return The float constant
     */
    @SuppressWarnings("unused")
    public static float getBaseStepDistanceAddend(Entity entity) {
        if (entity instanceof StriderEntity)
            return 0.6F;
        else if (entity instanceof TurtleEntity)
            return 0.15F;
        else if (entity instanceof WardenEntity)
            return 0.55F;

        return 1.0F;
    }

    public static boolean isEntityMoving(Entity entity) {
        if (entity instanceof MobEntity mob)
            return !mob.getNavigation().isIdle() || mob.getMoveControl().isMoving();
        else if (entity instanceof PlayerEntity player)
            return ((MovingPlayer) player).nano$isMoving();

        return false;
    }

    @SuppressWarnings("unused")
    public static boolean isPathfindingScalingAllowed(Entity entity) {
        return Pathfinding.pathfindingScalingEnabled.getOrDefault()
            && (!EntityHelper.isModdedEntity(entity)
            || cdiv.nano.registry.Registries.PathfindingScaling.has(entity.getType()));
    }

    public static boolean isPathfindingScalingDisallowed(Entity entity) {
        return !Pathfinding.pathfindingScalingEnabled.getOrDefault()
            || (EntityHelper.isModdedEntity(entity)
            && !cdiv.nano.registry.Registries.PathfindingScaling.has(entity.getType()));
    }

    public static boolean isPathfindingMinimumScalingDisallowed(Entity entity) {
        return !Pathfinding.pathfindingScalingEnabled.getOrDefault()
            || !Pathfinding.pathfindingMinimumScalingEnabled.getOrDefault()
            || (EntityHelper.isModdedEntity(entity)
            && !cdiv.nano.registry.Registries.PathfindingScaling.has(entity.getType()));
    }

    public static class Reference<T> {
        @Nullable
        public T value;

        public Reference() {
            this(null);
        }

        public Reference(@Nullable T value) {
            this.value = value;
        }
    }

    public static class DoubleReference {
        private double value;
        private boolean initialized = false;

        public DoubleReference() {}

        @SuppressWarnings("unused")
        public DoubleReference(double value) {
            this.initialized = true;
            this.value = value;
        }

        @SuppressWarnings("unused")
        public boolean isInitialized() {
            return this.initialized;
        }

        @SuppressWarnings("unused")
        public double get() {
            return this.value;
        }

        public void set(double value) {
            this.initialized = true;
            this.value = value;
        }
    }

    public static class FloatReference {
        private float value;
        private boolean initialized = false;

        public FloatReference() {}

        @SuppressWarnings("unused")
        public FloatReference(float value) {
            this.initialized = true;
            this.value = value;
        }

        @SuppressWarnings("unused")
        public boolean isInitialized() {
            return this.initialized;
        }

        @SuppressWarnings("unused")
        public float get() {
            return this.value;
        }

        public void set(float value) {
            this.initialized = true;
            this.value = value;
        }
    }

    public static class IntReference {
        private int value;
        private boolean initialized = false;

        public IntReference() {}

        @SuppressWarnings("unused")
        public IntReference(int value) {
            this.initialized = true;
            this.value = value;
        }

        @SuppressWarnings("unused")
        public boolean isInitialized() {
            return this.initialized;
        }

        @SuppressWarnings("unused")
        public int get() {
            return this.value;
        }

        public void set(int value) {
            this.initialized = true;
            this.value = value;
        }
    }

    public static double getPathfindingDoubleConstant(
        Entity entity,
        double constant,
        Reference<ScaleData> cachedScaleData,
        FloatReference cachedPreviousScale,
        DoubleReference cachedCalculatedValue,
        DoubleUnaryOperator scalingFunction
    ) { // This method does direct field access for speed
        if (MixinHelper.isPathfindingScalingDisallowed(entity))
            return constant;

        ScaleData scaleData = cachedScaleData.value;

        if (scaleData == null) {
            scaleData = ScaleTypes.BASE.getScaleData(entity);
            cachedScaleData.value = scaleData;
        }

        if (scaleData.isReset())
            return constant;

        float newScale = scaleData.getScale();

        if (cachedPreviousScale.initialized && cachedCalculatedValue.initialized && Math.abs(newScale - cachedPreviousScale.value) <= EPSILON)
            return cachedCalculatedValue.value;

        cachedPreviousScale.set(newScale);

        double calculatedValue = scalingFunction.applyAsDouble(constant * newScale);
        cachedCalculatedValue.set(calculatedValue);
        return calculatedValue;
    }

    public static int getPathfindingIntConstant(
        Entity entity,
        int constant,
        Reference<ScaleData> cachedScaleData,
        FloatReference cachedPreviousScale,
        IntReference cachedCalculatedValue,
        DoubleUnaryOperator scalingFunction
    ) {
        if (MixinHelper.isPathfindingScalingDisallowed(entity))
            return constant;

        ScaleData scaleData = cachedScaleData.value;

        if (scaleData == null) {
            scaleData = ScaleTypes.BASE.getScaleData(entity);
            cachedScaleData.value = scaleData;
        }

        if (scaleData.isReset())
            return constant;

        float newScale = scaleData.getScale();

        if (cachedPreviousScale.initialized && cachedCalculatedValue.initialized && Math.abs(newScale - cachedPreviousScale.value) <= EPSILON)
            return cachedCalculatedValue.value;

        cachedPreviousScale.set(newScale);

        int calculatedValue = (int) Math.round(scalingFunction.applyAsDouble(constant * newScale));
        cachedCalculatedValue.set(calculatedValue);
        return calculatedValue;
    }

    public static BlockPos getPathfindingLocalFuzz(BlockPos original, PathAwareEntity entity, int horizontalRange, int verticalRange) {
        if (isPathfindingMinimumScalingDisallowed(entity))
            return original;

        ScaleData scaleData = ScaleTypes.BASE.getScaleData(entity);

        if (scaleData.isReset())
            return original;

        int horizontalBound = 2 * horizontalRange + 1;
        int verticalBound = 2 * verticalRange + 1;
        Random random = entity.getRandom();
//        LOGGER.info("\nH: {}\nV: {}\nScale: {}\nMinimum: {}", horizontalRange, verticalRange, scale, minimum);

        int x = random.nextBetween(-horizontalBound, horizontalBound);
        int y = random.nextBetween(-verticalBound, verticalBound);
        int z = random.nextBetween(-horizontalBound, horizontalBound);
//        LOGGER.info("X: {}, Y: {}, Z: {}", x, y, z);

        return new BlockPos(x, y, z);
    }

    @Nullable
    public static BlockPos getPathfindingTowardTarget(PathAwareEntity entity, int horizontalRange, Random random, BlockPos fuzz) {
        if (isPathfindingMinimumScalingDisallowed(entity))
            return null;

        ScaleData scaleData = ScaleTypes.BASE.getScaleData(entity);

        if (scaleData.isReset())
            return null;

        float scale = scaleData.getScale();

        if (scale < 1.5F) // The scale doesn't affect the calculation until 1.5 where it rounds to 2
            return null;

        int roundedScale = Math.round(scale);
        int minimum = roundedScale - 1;
        int x = fuzz.getX();
        int z = fuzz.getZ();

        if (!entity.hasPositionTarget() || horizontalRange <= roundedScale)
            return BlockPos.ofFloored(x + entity.getX(), fuzz.getY() + entity.getY(), z + entity.getZ());

        BlockPos targetPosition = entity.getPositionTarget();

        int xAddend = random.nextBetween(minimum, horizontalRange / 2);
        int zAddend = random.nextBetween(minimum, horizontalRange / 2);

        if (xAddend == minimum)
            xAddend = 0;

        if (zAddend == minimum)
            zAddend = 0;

        x += (entity.getX() > targetPosition.getX()) ? -xAddend : xAddend;
        z += (entity.getZ() > targetPosition.getZ()) ? -zAddend : zAddend;

        return BlockPos.ofFloored(x + entity.getX(), fuzz.getY() + entity.getY(), z + entity.getZ());
    }
}
