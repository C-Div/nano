package cdiv.nano.mixin.pathfinding;

import cdiv.nano.helper.Mixin;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.injection.At;


@org.spongepowered.asm.mixin.Mixin(FuzzyTargeting.class)
public class FuzzyTargetingScalingMixin {
    @ModifyExpressionValue(
        method = "method_31531(Lnet/minecraft/entity/mob/PathAwareEntity;IIZ)Lnet/minecraft/util/math/BlockPos;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/ai/FuzzyPositions;localFuzz(Lnet/minecraft/util/math/random/Random;II)Lnet/minecraft/util/math/BlockPos;"
        )
    )
    private static BlockPos nano$find_localFuzz$minimumScaling(BlockPos original, PathAwareEntity entity, int horizontalRange, int verticalRange) {
        return Mixin.getPathfindingLocalFuzz(original, entity, horizontalRange, verticalRange);
    }
}