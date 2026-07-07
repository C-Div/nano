package cdiv.nano.client.util.screen;

import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

public class BoundDimensions<DimensionsType extends Dimensions> extends DimensionsWrapper<BoundDimensions<DimensionsType>, DimensionsType> implements Dimensions {
    @NotNull
    Screen screen;

    public BoundDimensions(@NotNull Screen screen, @NotNull DimensionsType dimensions) {
        super(dimensions);
        this.screen = screen;
    }

    public int getX() {
        return super.getX(screen);
    }

    public int getY() {
        return super.getY(screen);
    }

    public int getWidth() {
        return super.getWidth(screen);
    }

    public int getHeight() {
        return super.getHeight(screen);
    }

    @NotNull
    public Screen getScreen() {
        return screen;
    }

    @SuppressWarnings("unused")
    public void setScreen(@NotNull Screen screen) {
        this.screen = screen;
    }
}
