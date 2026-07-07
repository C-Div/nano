package cdiv.nano.client.util.screen.widget.util;

import cdiv.nano.client.util.screen.BoundDimensions;
import cdiv.nano.client.util.screen.Dimensions;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

/**
 * @deprecated Not compatible with class builder format
 */
@Deprecated
@SuppressWarnings("DeprecatedIsStillUsed")
public class ButtonWidgetBuilder extends ButtonWidget.Builder {
    public ButtonWidgetBuilder(Text message, ButtonWidget.PressAction onPress) {
        super(message, onPress);
    }

    public <T extends Dimensions> ButtonWidgetBuilder position(Screen screen, T dimensions) {
        super.position(dimensions.getX(screen), dimensions.getY(screen));
        return this;
    }

    @SuppressWarnings("unused")
    public <T extends BoundDimensions<?>> ButtonWidgetBuilder position(T dimensions) {
        return position(dimensions.getScreen(), dimensions);
    }

    @SuppressWarnings("unused")
    public <T extends Dimensions> ButtonWidgetBuilder width(Screen screen, T dimensions) {
        super.width(dimensions.getWidth(screen));
        return this;
    }

    public <T extends Dimensions> ButtonWidgetBuilder size(Screen screen, T dimensions) {
        super.size(dimensions.getWidth(screen), dimensions.getHeight(screen));
        return this;
    }

    @SuppressWarnings("unused")
    public <T extends BoundDimensions<?>> ButtonWidgetBuilder size(T dimensions) {
        return size(dimensions.getScreen(), dimensions);
    }

    public <T extends Dimensions> ButtonWidgetBuilder dimensions(Screen screen, T dimensions) {
        super.dimensions(
            dimensions.getX(screen),
            dimensions.getY(screen),
            dimensions.getWidth(screen),
            dimensions.getHeight(screen)
        );

        return this;
    }

    public <T extends BoundDimensions<?>> ButtonWidgetBuilder dimensions(T dimensions) {
        return dimensions(dimensions.getScreen(), dimensions);
    }

    public ButtonWidget.Builder tooltip(@Nullable Tooltip tooltip) {
        super.tooltip(tooltip);
        return this;
    }

    public ButtonWidget.Builder narrationSupplier(ButtonWidget.NarrationSupplier narrationSupplier) {
        super.narrationSupplier(narrationSupplier);
        return this;
    }
}
