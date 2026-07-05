package cdiv.nano;

import cdiv.nano.screen.ScaleCombinerScreenHandler;
import cdiv.nano.screen.ScaleNormalizerScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlers {
    public static final ScreenHandlerType<ScaleCombinerScreenHandler> SCALE_COMBINER = register("scale_combiner", ScaleCombinerScreenHandler::new);
    public static final ScreenHandlerType<ScaleNormalizerScreenHandler> SCALE_NORMALIZER = register("scale_normalizer", ScaleNormalizerScreenHandler::new);

    public static void initialize() {}

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(@SuppressWarnings("SameParameterValue") String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, id, new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
    }
}
