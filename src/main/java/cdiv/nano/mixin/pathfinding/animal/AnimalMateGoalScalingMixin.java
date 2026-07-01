package cdiv.nano.mixin.pathfinding.animal;

import cdiv.nano.util.helper.MixinHelper;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.passive.AnimalEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import virtuoel.pehkui.api.ScaleData;

import java.util.function.DoubleUnaryOperator;

import static cdiv.nano.util.helper.MixinHelper.getPathfindingDoubleConstant;

@org.spongepowered.asm.mixin.Mixin(AnimalMateGoal.class)
public class AnimalMateGoalScalingMixin {
    @Shadow
    @Final
    protected AnimalEntity animal;

    @Unique MixinHelper.FloatReference nano$previousScale = new MixinHelper.FloatReference();
    @Unique MixinHelper.Reference<ScaleData> nano$cachedScaleData = new MixinHelper.Reference<>();

    @Unique MixinHelper.DoubleReference nano$cachedSquaredDistance = new MixinHelper.DoubleReference();
    @Unique MixinHelper.DoubleReference nano$cachedExpandValue = new MixinHelper.DoubleReference();

    @Unique
    public double nano$getConstant(double constant, MixinHelper.DoubleReference cachedCalculatedValue, DoubleUnaryOperator scalingFunction) {
        return getPathfindingDoubleConstant(animal, constant, nano$cachedScaleData, nano$previousScale, cachedCalculatedValue, scalingFunction);
    }

    @ModifyConstant(
        method = "tick",
        constant = @Constant(doubleValue = 9.0D)
    )
    private double nano$tick$scaleBreedDistance(double constant) {
        return nano$getConstant(constant, nano$cachedSquaredDistance, value -> value * value);
    }

    @ModifyConstant(
        method = "findMate",
        constant = @Constant(doubleValue = 8.0D)
    )
    private double nano$findMate$scaleMateBox(double constant) {
        return nano$getConstant(constant, nano$cachedExpandValue, value -> value);
    }
}
