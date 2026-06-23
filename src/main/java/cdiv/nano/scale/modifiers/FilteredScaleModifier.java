package cdiv.nano.scale.modifiers;

import cdiv.nano.Nano;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleModifier;
import virtuoel.pehkui.api.ScaleType;

import java.util.Set;

public class FilteredScaleModifier<T extends Entity> extends ScaleModifier {
    protected final Set<ScaleType> targetScaleTypes;
    protected final Set<Class<? extends Entity>> targetEntities;
    protected final FilteredScaleApplier<T> filteredScaleApplier;

    @FunctionalInterface
    public interface FilteredScaleApplier<T extends Entity> {
        float apply(final ScaleData scaleData, final T entity, final float modifiedScale, final float delta);
    }

    public FilteredScaleModifier(Set<ScaleType> targetScaleTypes, Set<Class<? extends Entity>> targetEntities, FilteredScaleApplier<T> filteredScaleApplier) {
        super();
        this.targetScaleTypes = targetScaleTypes;
        this.targetEntities = targetEntities;
        this.filteredScaleApplier = filteredScaleApplier;
    }

    private boolean isModificationDisallowed(final ScaleData scaleData, @Nullable final Entity entity) {
        if (!targetScaleTypes.contains(scaleData.getScaleType()))
            return true;
        else if (entity == null)
            return false;

        for (Class<?> clazz : targetEntities) {
            if (!clazz.isInstance(entity))
                continue;

            return false;
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public float modifyScale(final ScaleData scaleData, final float modifiedScale, final float delta) {
        Entity entity = targetEntities.isEmpty() ? null : scaleData.getEntity();

        if (isModificationDisallowed(scaleData, entity))
            return modifiedScale;

        return filteredScaleApplier.apply(scaleData, (T) entity, modifiedScale, delta);
    }

    @Override
    @SuppressWarnings("unchecked")
    public float modifyPrevScale(final ScaleData scaleData, final float modifiedScale)
    {
        Entity entity = targetEntities.isEmpty() ? null : scaleData.getEntity();

        if (isModificationDisallowed(scaleData, entity))
            return modifiedScale;

        return filteredScaleApplier.apply(scaleData, (T) entity, modifiedScale, 1.0F);
    }
}
