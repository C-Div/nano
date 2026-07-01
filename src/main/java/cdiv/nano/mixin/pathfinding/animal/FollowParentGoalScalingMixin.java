package cdiv.nano.mixin.pathfinding.animal;

import cdiv.nano.util.helper.MixinHelper;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.passive.AnimalEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import virtuoel.pehkui.api.ScaleData;

import java.util.function.DoubleUnaryOperator;

import static cdiv.nano.util.helper.MixinHelper.getPathfindingDoubleConstant;

@org.spongepowered.asm.mixin.Mixin(FollowParentGoal.class)
public class FollowParentGoalScalingMixin {
    @Shadow
    @Final
    private AnimalEntity animal;

    @Unique MixinHelper.FloatReference nano$previousScale = new MixinHelper.FloatReference();
    @Unique MixinHelper.Reference<ScaleData> nano$cachedScaleData = new MixinHelper.Reference<>();

    @Unique MixinHelper.DoubleReference nano$cachedExpandHorizontalValue = new MixinHelper.DoubleReference();
    @Unique MixinHelper.DoubleReference nano$cachedExpandVerticalValue = new MixinHelper.DoubleReference();

    @Unique MixinHelper.DoubleReference nano$cachedMinimumContinueDistance = new MixinHelper.DoubleReference();
    @Unique MixinHelper.DoubleReference nano$cachedMaximumContinueDistance = new MixinHelper.DoubleReference();
    @Unique MixinHelper.DoubleReference nano$cachedSquaredDistance = new MixinHelper.DoubleReference();

    @Unique
    public double nano$getConstant(double constant, MixinHelper.DoubleReference cachedCalculatedValue, DoubleUnaryOperator scalingFunction) {
        return getPathfindingDoubleConstant(animal, constant, nano$cachedScaleData, nano$previousScale, cachedCalculatedValue, scalingFunction);
    }

    @ModifyConstant(
        method = "canStart",
        constant = @Constant(doubleValue = 8.0D)
    )
    private double nano$canStart$scaleParentBoxHorizontalExpansion(double constant) {
        return nano$getConstant(constant, nano$cachedExpandHorizontalValue, value -> value);
    }

    @ModifyConstant(
        method = "canStart",
        constant = @Constant(doubleValue = 4.0D)
    )
    private double nano$canStart$scaleParentBoxVerticalExpansion(double constant) {
        return nano$getConstant(constant, nano$cachedExpandVerticalValue, value -> value);
    }

    @ModifyConstant(
        method = "canStart",
        constant = @Constant(doubleValue = 9.0D)
    )
    private double nano$canStart$scaleParentDistance(double constant) {
        return nano$getConstant(constant, nano$cachedSquaredDistance, value -> value * value);
    }

    @ModifyConstant(
        method = "shouldContinue",
        constant = @Constant(doubleValue = 9.0D)
    )
    private double nano$shouldContinue$scaleMinimumContinueDistance(double constant) {
        return nano$getConstant(constant, nano$cachedMinimumContinueDistance, value -> value * value);
    }

    @ModifyConstant(
        method = "shouldContinue",
        constant = @Constant(doubleValue = 256.0D)
    )
    private double nano$shouldContinue$scaleMaximumContinueDistance(double constant) {
        return nano$getConstant(constant, nano$cachedMaximumContinueDistance, value -> value * value);
    }
}
