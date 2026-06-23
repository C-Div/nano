package cdiv.nano.mixin.pathfinding;

import cdiv.nano.helper.Mixin;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

@org.spongepowered.asm.mixin.Mixin(NoPenaltyTargeting.class)
public class NoPenaltyTargetingScalingMixin { // Note - Switch this to more specific injections if extra performance is needed
    @ModifyVariable(
        method = "find",
        at = @At("HEAD"),
        ordinal = 0,
        name = "horizontalRange",
        argsOnly = true
    )
    private static int nano$find$horizontalRangeScaling(int horizontalRange, PathAwareEntity entity) {
        if (Mixin.isPathfindingScalingDisallowed(entity))
            return horizontalRange;

        ScaleData scaleData = ScaleTypes.BASE.getScaleData(entity);

        if (scaleData.isReset())
            return horizontalRange;

        return Math.round(horizontalRange * scaleData.getScale());
    }

    @ModifyVariable(
        method = "find",
        at = @At("HEAD"),
        ordinal = 1,
        name = "verticalRange",
        argsOnly = true
    )
    private static int nano$find$verticalRangeScaling(int verticalRange, PathAwareEntity entity) {
        if (Mixin.isPathfindingScalingDisallowed(entity))
            return verticalRange;

        ScaleData scaleData = ScaleTypes.BASE.getScaleData(entity);

        if (scaleData.isReset())
            return verticalRange;

        return Math.round(verticalRange * scaleData.getScale());
    }

    @ModifyExpressionValue(
        method = "method_31515(Lnet/minecraft/entity/mob/PathAwareEntity;IIZ)Lnet/minecraft/util/math/BlockPos;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/ai/FuzzyPositions;localFuzz(Lnet/minecraft/util/math/random/Random;II)Lnet/minecraft/util/math/BlockPos;"
        )
    )
    private static BlockPos nano$find_localFuzz$minimumScaling(BlockPos original, PathAwareEntity entity, int horizontalRange, int verticalRange) {
        return Mixin.getPathfindingLocalFuzz(original, entity, horizontalRange, verticalRange);
    }
}
