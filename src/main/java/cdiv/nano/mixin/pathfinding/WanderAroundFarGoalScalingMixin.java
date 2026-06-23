package cdiv.nano.mixin.pathfinding;

import cdiv.nano.helper.Mixin;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import virtuoel.pehkui.api.ScaleData;

import java.util.function.DoubleUnaryOperator;

import static cdiv.nano.helper.Mixin.getPathfindingIntConstant;

@org.spongepowered.asm.mixin.Mixin(WanderAroundFarGoal.class)
public class WanderAroundFarGoalScalingMixin {
    @Unique Mixin.FloatReference nano$previousScale = new Mixin.FloatReference();
    @Unique Mixin.Reference<ScaleData> nano$cachedScaleData = new Mixin.Reference<>();

    @Unique Mixin.IntReference nano$cachedWaterHorizontalRange = new Mixin.IntReference();
    @Unique Mixin.IntReference nano$cachedHorizontalRange = new Mixin.IntReference();
    @Unique Mixin.IntReference nano$cachedVerticalRange = new Mixin.IntReference();

    @Unique
    public int nano$getConstant(int constant, Mixin.IntReference cachedCalculatedValue, DoubleUnaryOperator scalingFunction) {
        return getPathfindingIntConstant(((WanderAroundFarGoal) (Object) this).mob, constant, nano$cachedScaleData, nano$previousScale, cachedCalculatedValue, scalingFunction);
    }

    @ModifyConstant(
        method = "getWanderTarget",
        constant = @Constant(intValue = 15)
    )
    private int nano$getWanderTarget$waterHorizontalRange(int constant) {
        return nano$getConstant(constant, nano$cachedWaterHorizontalRange, value -> value);
    }

    @ModifyConstant(
        method = "getWanderTarget",
        constant = @Constant(intValue = 10)
    )
    private int nano$getWanderTarget$horizontalRange(int constant) {
        return nano$getConstant(constant, nano$cachedHorizontalRange, value -> value);
    }

    @ModifyConstant(
        method = "getWanderTarget",
        constant = @Constant(intValue = 7)
    )
    private int nano$getWanderTarget$verticalRange(int constant) {
        return nano$getConstant(constant, nano$cachedVerticalRange, value -> value);
    }
}
