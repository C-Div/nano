package cdiv.nano.mixin.pathfinding;

import cdiv.nano.util.helper.MixinHelper;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import virtuoel.pehkui.api.ScaleData;

import java.util.function.DoubleUnaryOperator;

import static cdiv.nano.util.helper.MixinHelper.getPathfindingIntConstant;

@org.spongepowered.asm.mixin.Mixin(WanderAroundFarGoal.class)
public class WanderAroundFarGoalScalingMixin {
    @Unique MixinHelper.FloatReference nano$previousScale = new MixinHelper.FloatReference();
    @Unique MixinHelper.Reference<ScaleData> nano$cachedScaleData = new MixinHelper.Reference<>();

    @Unique MixinHelper.IntReference nano$cachedWaterHorizontalRange = new MixinHelper.IntReference();
    @Unique MixinHelper.IntReference nano$cachedHorizontalRange = new MixinHelper.IntReference();
    @Unique MixinHelper.IntReference nano$cachedVerticalRange = new MixinHelper.IntReference();

    @Unique
    public int nano$getConstant(int constant, MixinHelper.IntReference cachedCalculatedValue, DoubleUnaryOperator scalingFunction) {
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
