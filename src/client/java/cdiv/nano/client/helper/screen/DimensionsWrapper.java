package cdiv.nano.client.helper.screen;

import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

public abstract class DimensionsWrapper<BuilderReturnType extends DimensionsWrapper<BuilderReturnType, DimensionsType>, DimensionsType extends Dimensions> implements Dimensions {
    @NotNull
    Dimensions dimensions;
    
    public DimensionsWrapper(@NotNull DimensionsType dimensions) {
        this.dimensions = dimensions;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setX(double scaleX) {
        dimensions.setX(scaleX);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setX(int offsetX) {
        dimensions.setX(offsetX);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setX(double scaleX, int offsetX) {
        dimensions.setX(scaleX, offsetX);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setY(double scaleY) {
        dimensions.setY(scaleY);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setY(int offsetY) {
        dimensions.setY(offsetY);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setY(double scaleY, int offsetY) {
        dimensions.setY(scaleY, offsetY);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setPosition(double scaleX, double scaleY) {
        dimensions.setPosition(scaleX, scaleY);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setPosition(int offsetX, int offsetY) {
        dimensions.setPosition(offsetX, offsetY);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setPosition(double scaleX, int offsetX, double scaleY, int offsetY) {
        dimensions.setPosition(scaleX, offsetX, scaleY, offsetY);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setWidth(double scaleWidth) {
        dimensions.setWidth(scaleWidth);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setWidth(int offsetWidth) {
        dimensions.setWidth(offsetWidth);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setWidth(double scaleWidth, int offsetWidth) {
        dimensions.setWidth(scaleWidth, offsetWidth);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setHeight(double scaleHeight) {
        dimensions.setHeight(scaleHeight);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setHeight(int offsetHeight) {
        dimensions.setHeight(offsetHeight);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setHeight(double scaleHeight, int offsetHeight) {
        dimensions.setHeight(scaleHeight, offsetHeight);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setSize(double scaleWidth, double scaleHeight) {
        dimensions.setSize(scaleWidth, scaleHeight);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setSize(int offsetWidth, int offsetHeight) {
        dimensions.setSize(offsetWidth, offsetHeight);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setSize(double scaleWidth, int offsetWidth, double scaleHeight, int offsetHeight) {
        dimensions.setSize(scaleWidth, offsetWidth, scaleHeight, offsetHeight);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setAnchorX(double anchorX) {
        dimensions.setAnchorX(anchorX);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setAnchorY(double anchorY) {
        dimensions.setAnchorY(anchorY);
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setAnchor(double anchorX, double anchorY) {
        dimensions.setAnchor(anchorX, anchorY);
        return (BuilderReturnType) this;
    }

    @Override
    public double getAnchorX() {
        return dimensions.getAnchorX();
    }

    @Override
    public double getAnchorY() {
        return dimensions.getAnchorY();
    }

    @Override
    public double getScaleWidth() {
        return dimensions.getScaleWidth();
    }

    @Override
    public int getOffsetWidth() {
        return dimensions.getOffsetWidth();
    }

    @Override
    public double getScaleHeight() {
        return dimensions.getScaleHeight();
    }

    @Override
    public int getOffsetHeight() {
        return dimensions.getOffsetHeight();
    }

    @Override
    public double getScaleX() {
        return dimensions.getScaleX();
    }

    @Override
    public int getOffsetX() {
        return dimensions.getOffsetX();
    }

    @Override
    public double getScaleY() {
        return dimensions.getScaleY();
    }

    @Override
    public int getOffsetY() {
        return dimensions.getOffsetY();
    }

    @Override
    public int getX(Screen screen) {
        return dimensions.getX(screen);
    }

    @Override
    public int getY(Screen screen) {
        return dimensions.getY(screen);
    }

    @Override
    public int getWidth(Screen screen) {
        return dimensions.getWidth(screen);
    }

    @Override
    public int getHeight(Screen screen) {
        return dimensions.getHeight(screen);
    }
}
