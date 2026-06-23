package cdiv.nano.helper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class Entity {
    @Nullable
    public static ItemStack getHeldItemStack(LivingEntity entity) {
        return entity.getStackInHand(entity.getActiveHand());
    }
}
