package cdiv.nano.mixin.pathfinding;

import cdiv.nano.util.helper.MixinHelper;
import net.minecraft.entity.ai.FuzzyPositions;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@org.spongepowered.asm.mixin.Mixin(FuzzyPositions.class)
public class FuzzyPositionsTowardScalingMixin {
    @Inject(
        at = @At("HEAD"),
        method = "towardTarget",
        cancellable = true
    )
    private static void nano$towardTarget$minimumScaling(PathAwareEntity entity, int horizontalRange, Random random, BlockPos fuzz, CallbackInfoReturnable<BlockPos> callbackInfoReturnable) {
        BlockPos blockPos = MixinHelper.getPathfindingTowardTarget(entity, horizontalRange, random, fuzz);

        if (blockPos == null)
            return;

        callbackInfoReturnable.setReturnValue(blockPos);
    }
}
