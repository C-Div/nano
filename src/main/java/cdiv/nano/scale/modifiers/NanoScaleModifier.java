package cdiv.nano.scale.modifiers;

import cdiv.nano.api.Option;
import cdiv.nano.api.config.ScaleModifiers;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleModifier;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NanoScaleModifier<T extends ScaleModifier> extends ScaleModifier {
    protected final T scaleModifier;
    protected final Map<Option<Boolean>, Boolean> requiredOptions;

    @SafeVarargs
    public NanoScaleModifier(T scaleModifier, Map.Entry<Option<Boolean>, Boolean>... requiredOptions) {
        this.scaleModifier = scaleModifier;
        this.requiredOptions = Map.ofEntries(requiredOptions);
    }

    private boolean isModificationDisallowed() {
        if (!ScaleModifiers.scaleModifiersEnabled.get())
            return true;

        for (Map.Entry<Option<Boolean>, Boolean> entry : requiredOptions.entrySet()) {
            if (entry.getKey().get() == entry.getValue())
                continue;

            return true;
        }

        return false;
    }

    @Override
    public float modifyScale(final ScaleData scaleData, final float modifiedScale, final float delta) {
        if (isModificationDisallowed())
            return modifiedScale;

        return scaleModifier.modifyScale(scaleData, modifiedScale, delta);
    }

    @Override
    public float modifyPrevScale(final ScaleData scaleData, final float modifiedScale)
    {
        if (isModificationDisallowed())
            return modifiedScale;

        return scaleModifier.modifyPrevScale(scaleData, modifiedScale);
    }
}
