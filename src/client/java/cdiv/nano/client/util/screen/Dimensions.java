package cdiv.nano.client.util.screen;

import net.minecraft.client.gui.screen.Screen;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface Dimensions {
    Dimensions setX(double scaleX);
    Dimensions setX(int offsetX);
    Dimensions setX(double scaleX, int offsetX);

    Dimensions setY(double scaleY);
    Dimensions setY(int offsetY);
    Dimensions setY(double scaleY, int offsetY);

    Dimensions setPosition(double scaleX, double scaleY);
    Dimensions setPosition(int offsetX, int offsetY);
    Dimensions setPosition(double scaleX, int offsetX, double scaleY, int offsetY);

    Dimensions setWidth(double scaleWidth);
    Dimensions setWidth(int offsetWidth);
    Dimensions setWidth(double scaleWidth, int offsetWidth);

    Dimensions setHeight(double scaleHeight);
    Dimensions setHeight(int offsetHeight);
    Dimensions setHeight(double scaleHeight, int offsetHeight);

    Dimensions setSize(double scaleWidth, double scaleHeight);
    Dimensions setSize(int offsetWidth, int offsetHeight);
    Dimensions setSize(double scaleWidth, int offsetWidth, double scaleHeight, int offsetHeight);

    Dimensions setAnchorX(double anchorX);
    Dimensions setAnchorY(double anchorY);
    Dimensions setAnchor(double anchorX, double anchorY);

    double getAnchorX();
    double getAnchorY();

    double getScaleWidth();
    int getOffsetWidth();

    double getScaleHeight();
    int getOffsetHeight();

    double getScaleX();
    int getOffsetX();

    double getScaleY();
    int getOffsetY();

    int getX(Screen screen);
    int getY(Screen screen);

    int getWidth(Screen screen);
    int getHeight(Screen screen);
}
