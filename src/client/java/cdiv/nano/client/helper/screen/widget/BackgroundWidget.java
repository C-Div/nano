package cdiv.nano.client.helper.screen.widget;

import cdiv.nano.client.helper.screen.BoundDimensions;
import cdiv.nano.client.helper.screen.Dimensions;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static cdiv.nano.Nano.MOD_ID;

public class BackgroundWidget extends ClickableWidget {
    public static final Identifier backgroundTextureId = Identifier.of(MOD_ID, "background");
    public BackgroundWidget(int x, int y, int width, int height) {
        super(x, y, width, height, Text.empty());
    }

    public <T extends Dimensions> BackgroundWidget(Screen screen, T dimensions) {
        this(
            dimensions.getX(screen),
            dimensions.getY(screen),
            dimensions.getWidth(screen),
            dimensions.getHeight(screen)
        );
    }

    public <T extends BoundDimensions<?>> BackgroundWidget(T dimensions) {
        this(dimensions.getScreen(), dimensions);
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawGuiTexture(
            backgroundTextureId,
            getX(),
            getY(),
            width,
            height
        );
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {}

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        return false;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return false;
    }
}
