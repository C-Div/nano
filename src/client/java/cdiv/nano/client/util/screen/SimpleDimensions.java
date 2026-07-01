package cdiv.nano.client.util.screen;

import net.minecraft.client.gui.screen.Screen;

public class SimpleDimensions<BuilderReturnType extends SimpleDimensions<BuilderReturnType>> implements Dimensions {
    protected double scaleX = 0;
    protected double scaleY = 0;
    protected int offsetX = 0;
    protected int offsetY = 0;

    protected double scaleWidth = 0;
    protected double scaleHeight = 0;
    protected int offsetWidth = 0;
    protected int offsetHeight = 0;

    protected double anchorX = 0;
    protected double anchorY = 0;

    @SuppressWarnings("unused")
    public SimpleDimensions(double scaleX, double scaleY, int offsetX, int offsetY, double scaleWidth, double scaleHeight, int offsetWidth, int offsetHeight, double anchorX, double anchorY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.scaleWidth = scaleWidth;
        this.scaleHeight = scaleHeight;
        this.offsetWidth = offsetWidth;
        this.offsetHeight = offsetHeight;
        this.anchorX = anchorX;
        this.anchorY = anchorY;
    }

    public SimpleDimensions() {}

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setX(double scaleX) {
        this.scaleX = scaleX;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setY(double scaleY) {
        this.scaleY = scaleY;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setX(int offsetX) {
        this.offsetX = offsetX;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setY(int offsetY) {
        this.offsetY = offsetY;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setWidth(double scaleWidth) {
        this.scaleWidth = scaleWidth;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setHeight(double scaleHeight) {
        this.scaleHeight = scaleHeight;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setWidth(int offsetWidth) {
        this.offsetWidth = offsetWidth;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setHeight(int offsetHeight) {
        this.offsetHeight = offsetHeight;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setX(double scaleX, int offsetX) {
        this.scaleX = scaleX;
        this.offsetX = offsetX;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setY(double scaleY, int offsetY) {
        this.scaleY = scaleY;
        this.offsetY = offsetY;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setPosition(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setPosition(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setPosition(double scaleX, int offsetX, double scaleY, int offsetY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setWidth(double scaleWidth, int offsetWidth) {
        this.scaleWidth = scaleWidth;
        this.offsetWidth = offsetWidth;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setHeight(double scaleHeight, int offsetHeight) {
        this.scaleHeight = scaleHeight;
        this.offsetHeight = offsetHeight;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setSize(double scaleWidth, double scaleHeight) {
        this.scaleWidth = scaleWidth;
        this.scaleHeight = scaleHeight;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setSize(int offsetWidth, int offsetHeight) {
        this.offsetWidth = offsetWidth;
        this.offsetHeight = offsetHeight;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setSize(double scaleWidth, int offsetWidth, double scaleHeight, int offsetHeight) {
        this.scaleWidth = scaleWidth;
        this.scaleHeight = scaleHeight;
        this.offsetWidth = offsetWidth;
        this.offsetHeight = offsetHeight;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setAnchorX(double anchorX) {
        this.anchorX = anchorX;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setAnchorY(double anchorY) {
        this.anchorY = anchorY;
        return (BuilderReturnType) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BuilderReturnType setAnchor(double anchorX, double anchorY) {
        this.anchorX = anchorX;
        this.anchorY = anchorY;
        return (BuilderReturnType) this;
    }

    @Override
    public double getAnchorX() {
        return anchorX;
    }

    @Override
    public double getAnchorY() {
        return anchorY;
    }

    @Override
    public double getScaleWidth() {
        return scaleWidth;
    }

    @Override
    public int getOffsetWidth() {
        return offsetWidth;
    }

    @Override
    public double getScaleHeight() {
        return scaleHeight;
    }

    @Override
    public int getOffsetHeight() {
        return offsetHeight;
    }

    @Override
    public double getScaleX() {
        return scaleX;
    }

    @Override
    public int getOffsetX() {
        return offsetX;
    }

    @Override
    public double getScaleY() {
        return scaleY;
    }

    @Override
    public int getOffsetY() {
        return offsetY;
    }

    @Override
    public int getX(Screen screen) {
        return (int) ((screen.width * scaleX) - (((scaleWidth * screen.width + offsetWidth) + offsetX) * anchorX));
    }

    @Override
    public int getY(Screen screen) {
        return  (int) ((screen.height * scaleY) - (((scaleHeight * screen.height + offsetHeight) + offsetY) * anchorY));
    }

    @Override
    public int getWidth(Screen screen) {
        return (int) (screen.width * scaleWidth) + offsetWidth;
    }

    @Override
    public int getHeight(Screen screen) {
        return (int) (screen.height * scaleHeight) + offsetHeight;
    }
}
