package cdiv.nano.mixin.pathfinding.animal;

import cdiv.nano.util.helper.MixinHelper;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import virtuoel.pehkui.api.ScaleData;

import java.util.function.DoubleUnaryOperator;

import static cdiv.nano.util.helper.MixinHelper.getPathfindingDoubleConstant;

@org.spongepowered.asm.mixin.Mixin(TemptGoal.class)
public class TemptGoalScalingMixin {
    @Shadow
    @Final
    protected PathAwareEntity mob;

    @Unique MixinHelper.FloatReference nano$previousScale = new MixinHelper.FloatReference();
    @Unique MixinHelper.Reference<ScaleData> nano$cachedScaleData = new MixinHelper.Reference<>();

    @Unique MixinHelper.DoubleReference nano$cachedSquaredNavigationDistance = new MixinHelper.DoubleReference();
    @Unique MixinHelper.DoubleReference nano$cachedSquaredPlayerDistance = new MixinHelper.DoubleReference();

    @Unique
    public double nano$getConstant(double constant, MixinHelper.DoubleReference cachedCalculatedValue, DoubleUnaryOperator scalingFunction) {
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
