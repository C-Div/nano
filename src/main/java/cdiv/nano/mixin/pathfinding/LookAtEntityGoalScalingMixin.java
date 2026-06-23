package cdiv.nano.mixin.pathfinding;

import cdiv.nano.helper.Mixin;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import virtuoel.pehkui.api.ScaleData;

import java.util.function.DoubleUnaryOperator;

import static cdiv.nano.helper.Mixin.getPathfindingDoubleConstant;

@org.spongepowered.asm.mixin.Mixin(LookAtEntityGoal.class)
public class LookAtEntityGoalScalingMixin {
    @Shadow
    @Final
    protected MobEntity mob;

    @Unique Mixin.FloatReference nano$previousScale = new Mixin.FloatReference();
    @Unique Mixin.Reference<ScaleData> nano$cachedScaleData = new Mixin.Reference<>();

    @Unique Mixin.DoubleReference nano$cachedVerticalExpansion = new Mixin.DoubleReference();

    @Unique
    public double nano$getConstant(double constant, Mixin.DoubleReference cachedCalculatedValue, DoubleUnaryOperator scalingFunction) {
        return getPathfindingDoubleConstant(mob, constant, nano$cachedScaleData, nano$previousScale, cachedCalculatedValue, scalingFunction);
    }

    @ModifyConstant(
        method = "canStart",
        constant = @Constant(doubleValue = 3.0D)
    )
    private double nano$tick$scaleBreedDistance(double constant) {
        return nano$getConstant(constant, nano$cachedVerticalExpansion, value -> value * value);
    }
}
