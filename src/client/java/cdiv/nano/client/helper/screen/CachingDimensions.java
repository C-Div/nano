package cdiv.nano.client.helper.screen;

import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

public class CachingDimensions<DimensionsType extends Dimensions> extends DimensionsWrapper<CachingDimensions<DimensionsType>, DimensionsType> implements Dimensions {
    protected int lastScreenWidth;
    protected int lastScreenHeight;

    protected boolean recalculateX = true;
    protected boolean recalculateY = true;
    protected boolean recalculateWidth = true;
    protected boolean recalculateHeight = true;

    protected int calculatedX;
    protected int calculatedY;
    protected int calculatedWidth;
    protected int calculatedHeight;

    public CachingDimensions(@NotNull DimensionsType dimensions) {
        super(dimensions);
    }

    @Override
    public CachingDimensions<DimensionsType> setX(double scaleX) {
        this.recalculateX = scaleX != dimensions.getScaleX();
        dimensions.setX(scaleX);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setX(int offsetX) {
        this.recalculateX = offsetX != dimensions.getOffsetX();
        dimensions.setX(offsetX);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setX(double scaleX, int offsetX) {
        this.recalculateX = scaleX != dimensions.getScaleX() || offsetX != dimensions.getOffsetX();
        dimensions.setX(scaleX, offsetX);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setY(double scaleY) {
        this.recalculateY = scaleY != dimensions.getScaleY();
        dimensions.setY(scaleY);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setY(int offsetY) {
        this.recalculateY = offsetY != dimensions.getOffsetY();
        dimensions.setY(offsetY);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setY(double scaleY, int offsetY) {
        this.recalculateY = scaleY != dimensions.getScaleY() || offsetY != dimensions.getOffsetY();
        dimensions.setY(scaleY, offsetY);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setPosition(double scaleX, double scaleY) {
        boolean xChanged = scaleX != dimensions.getScaleX();
        boolean yChanged = scaleY != dimensions.getScaleY();

        this.recalculateX = xChanged;
        this.recalculateY = yChanged;

        dimensions.setPosition(scaleX, scaleY);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setPosition(int offsetX, int offsetY) {
        boolean xChanged = offsetX != dimensions.getOffsetX();
        boolean yChanged = offsetY != dimensions.getOffsetY();

        this.recalculateX = xChanged;
        this.recalculateY = yChanged;

        dimensions.setPosition(offsetX, offsetY);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setPosition(double scaleX, int offsetX, double scaleY, int offsetY) {
        boolean xChanged = scaleX != dimensions.getScaleX() || offsetX != dimensions.getOffsetX();
        boolean yChanged = scaleY != dimensions.getScaleY() || offsetY != dimensions.getOffsetY();

        this.recalculateX = xChanged;
        this.recalculateY = yChanged;

        dimensions.setPosition(scaleX, offsetX, scaleY, offsetY);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setWidth(double scaleWidth) {
        boolean widthChanged = scaleWidth != dimensions.getScaleWidth();

        this.recalculateX = widthChanged;
        this.recalculateWidth = widthChanged;

        dimensions.setWidth(scaleWidth);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setWidth(int offsetWidth) {
        boolean widthChanged = offsetWidth != dimensions.getOffsetWidth();

        this.recalculateX = widthChanged;
        this.recalculateWidth = widthChanged;

        dimensions.setWidth(offsetWidth);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setWidth(double scaleWidth, int offsetWidth) {
        boolean widthChanged = scaleWidth != dimensions.getScaleWidth() || offsetWidth != dimensions.getOffsetWidth();

        this.recalculateX = widthChanged;
        this.recalculateWidth = widthChanged;

        dimensions.setWidth(scaleWidth, offsetWidth);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setHeight(double scaleHeight) {
        boolean heightChanged = scaleHeight != dimensions.getScaleHeight();

        this.recalculateY = heightChanged;
        this.recalculateHeight = heightChanged;

        dimensions.setHeight(scaleHeight);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setHeight(int offsetHeight) {
        boolean heightChanged = offsetHeight != dimensions.getOffsetHeight();

        this.recalculateY = heightChanged;
        this.recalculateHeight = heightChanged;

        dimensions.setHeight(offsetHeight);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setHeight(double scaleHeight, int offsetHeight) {
        boolean heightChanged = scaleHeight != dimensions.getScaleHeight() || offsetHeight != dimensions.getOffsetHeight();

        this.recalculateY = heightChanged;
        this.recalculateHeight = heightChanged;

        dimensions.setHeight(scaleHeight, offsetHeight);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setSize(double scaleWidth, double scaleHeight) {
        boolean widthChanged = scaleWidth != dimensions.getScaleWidth();
        boolean heightChanged = scaleHeight != dimensions.getScaleHeight();

        this.recalculateX = widthChanged;
        this.recalculateWidth = widthChanged;
        this.recalculateY = heightChanged;
        this.recalculateHeight = heightChanged;

        dimensions.setSize(scaleWidth, scaleHeight);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setSize(int offsetWidth, int offsetHeight) {
        boolean widthChanged = offsetWidth != dimensions.getOffsetWidth();
        boolean heightChanged = offsetHeight != dimensions.getOffsetHeight();

        this.recalculateX = widthChanged;
        this.recalculateWidth = widthChanged;
        this.recalculateY = heightChanged;
        this.recalculateHeight = heightChanged;

        dimensions.setSize(offsetWidth, offsetHeight);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setSize(double scaleWidth, int offsetWidth, double scaleHeight, int offsetHeight) {
        boolean widthChanged = scaleWidth != dimensions.getScaleWidth() || offsetWidth != dimensions.getOffsetWidth();
        boolean heightChanged = scaleHeight != dimensions.getScaleHeight() || offsetHeight != dimensions.getOffsetHeight();

        this.recalculateX = widthChanged;
        this.recalculateWidth = widthChanged;
        this.recalculateY = heightChanged;
        this.recalculateHeight = heightChanged;

        dimensions.setSize(scaleWidth, offsetWidth, scaleHeight, offsetHeight);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setAnchorX(double anchorX) {
        this.recalculateX = anchorX != dimensions.getAnchorX();
        dimensions.setAnchorX(anchorX);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setAnchorY(double anchorY) {
        this.recalculateY = anchorY != dimensions.getAnchorY();
        dimensions.setAnchorY(anchorY);
        return this;
    }

    @Override
    public CachingDimensions<DimensionsType> setAnchor(double anchorX, double anchorY) {
        this.recalculateX = anchorX != dimensions.getAnchorX();
        this.recalculateY = anchorY != dimensions.getAnchorY();
        dimensions.setAnchor(anchorX, anchorY);
        return this;
    }

    @Override
    public int getX(Screen screen) {
        boolean widthChanged = screen.width != lastScreenWidth;

        if (widthChanged) {
            lastScreenWidth = screen.width;
            recalculateWidth = true;
        }

        if (recalculateX || widthChanged) {
            recalculateX = false;
            calculatedX = dimensions.getX(screen);
        }

        return calculatedX;
    }

    @Override
    public int getY(Screen screen) {
        boolean heightChanged = screen.height != lastScreenHeight;

        if (heightChanged) {
            lastScreenHeight = screen.height;
            recalculateHeight = true;
        }

        if (recalculateY || heightChanged) {
            recalculateY = false;
            calculatedY = dimensions.getY(screen);
        }

        return calculatedY;
    }

    @Override
    public int getWidth(Screen screen) {
        boolean widthChanged = screen.width != lastScreenWidth;

        if (widthChanged) {
            lastScreenWidth = screen.width;
            recalculateX = true;
        }

        if (recalculateWidth || widthChanged) {
            recalculateWidth = false;
            calculatedWidth = dimensions.getWidth(screen);
        }

        return calculatedWidth;
    }

    @Override
    public int getHeight(Screen screen) {
        boolean heightChanged = screen.height != lastScreenHeight;

        if (heightChanged) {
            lastScreenHeight = screen.height;
            recalculateY = true;
        }

        if (recalculateHeight || heightChanged) {
            recalculateHeight = false;
            calculatedHeight = dimensions.getHeight(screen);
        }

        return calculatedHeight;
    }
}
