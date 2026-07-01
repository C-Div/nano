package cdiv.nano.mixin.pathfinding;

import cdiv.nano.util.helper.MixinHelper;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import virtuoel.pehkui.api.ScaleData;

import java.util.function.DoubleUnaryOperator;

import static cdiv.nano.util.helper.MixinHelper.getPathfindingDoubleConstant;

@org.spongepowered.asm.mixin.Mixin(LookAtEntityGoal.class)
public class LookAtEntityGoalScalingMixin {
    @Shadow
    @Final
    protected MobEntity mob;

    @Unique MixinHelper.FloatReference nano$previousScale = new MixinHelper.FloatReference();
    @Unique MixinHelper.Reference<ScaleData> nano$cachedScaleData = new MixinHelper.Reference<>();

    @Unique MixinHelper.DoubleReference nano$cachedVerticalExpansion = new MixinHelper.DoubleReference();

    @Unique
    public double nano$getConstant(double constant, MixinHelper.DoubleReference cachedCalculatedValue, DoubleUnaryOperator scalingFunction) {
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
