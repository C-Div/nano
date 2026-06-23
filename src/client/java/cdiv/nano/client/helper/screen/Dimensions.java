package cdiv.nano.client.helper.screen;

import net.minecraft.client.gui.screen.Screen;

public interface Dimensions {
    public Dimensions setX(double scaleX);
    public Dimensions setX(int offsetX);
    public Dimensions setX(double scaleX, int offsetX);

    public Dimensions setY(double scaleY);
    public Dimensions setY(int offsetY);
    public Dimensions setY(double scaleY, int offsetY);

    public Dimensions setPosition(double scaleX, double scaleY);
    public Dimensions setPosition(int offsetX, int offsetY);
    public Dimensions setPosition(double scaleX, int offsetX, double scaleY, int offsetY);

    public Dimensions setWidth(double scaleWidth);
    public Dimensions setWidth(int offsetWidth);
    public Dimensions setWidth(double scaleWidth, int offsetWidth);

    public Dimensions setHeight(double scaleHeight);
    public Dimensions setHeight(int offsetHeight);
    public Dimensions setHeight(double scaleHeight, int offsetHeight);

    public Dimensions setSize(double scaleWidth, double scaleHeight);
    public Dimensions setSize(int offsetWidth, int offsetHeight);
    public Dimensions setSize(double scaleWidth, int offsetWidth, double scaleHeight, int offsetHeight);

    public Dimensions setAnchorX(double anchorX);
    public Dimensions setAnchorY(double anchorY);
    public Dimensions setAnchor(double anchorX, double anchorY);

    public double getAnchorX();
    public double getAnchorY();

    public double getScaleWidth();
    public int getOffsetWidth();

    public double getScaleHeight();
    public int getOffsetHeight();

    public double getScaleX();
    public int getOffsetX();

    public double getScaleY();
    public int getOffsetY();

    public int getX(Screen screen);
    public int getY(Screen screen);

    public int getWidth(Screen screen);
    public int getHeight(Screen screen);
}
