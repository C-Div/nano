package cdiv.nano.mixin.pathfinding.animal;

import cdiv.nano.helper.Mixin;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import virtuoel.pehkui.api.ScaleData;

import java.util.function.DoubleUnaryOperator;

import static cdiv.nano.helper.Mixin.getPathfindingDoubleConstant;

@org.spongepowered.asm.mixin.Mixin(TemptGoal.class)
public class TemptGoalScalingMixin {
    @Shadow
    @Final
    protected PathAwareEntity mob;

    @Shadow
    protected PlayerEntity closestPlayer;

    @Unique Mixin.FloatReference nano$previousScale = new Mixin.FloatReference();
    @Unique Mixin.Reference<ScaleData> nano$cachedScaleData = new Mixin.Reference<>();

    @Unique Mixin.DoubleReference nano$cachedSquaredNavigationDistance = new Mixin.DoubleReference();
    @Unique Mixin.DoubleReference nano$cachedSquaredPlayerDistance = new Mixin.DoubleReference();

    @Unique
    public double nano$getConstant(double constant, Mixin.DoubleReference cachedCalculatedValue, DoubleUnaryOperator scalingFunction) {
        return getPathfindingDoubleConstant(mob, constant, nano$cachedScaleData, nano$previousScale, cachedCalculatedValue, scalingFunction);
    }

    @ModifyConstant(
        method = "shouldContinue",
        constant = @Constant(doubleValue = 36.0D)
    )
    private double nano$shouldContinue$scaleMinimumPlayerDistance(double constant) {
        return nano$getConstant(constant, nano$cachedSquaredPlayerDistance, value -> value * value);
    }

    @ModifyConstant(
        method = "tick",
        constant = @Constant(doubleValue = 6.25D)
    )
    private double nano$tick$scaleMinimumNavigationDistance(double constant) {
        return nano$getConstant(constant, nano$cachedSquaredNavigationDistance, value -> value * value);
    }
}
