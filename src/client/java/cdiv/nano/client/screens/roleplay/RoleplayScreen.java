package cdiv.nano.client.screens.roleplay;

import cdiv.nano.client.util.screen.BoundDimensions;
import cdiv.nano.client.util.screen.CachingDimensions;
import cdiv.nano.client.util.screen.Screen;
import cdiv.nano.client.util.screen.SimpleDimensions;
import cdiv.nano.client.util.screen.widget.BackgroundWidget;
import cdiv.nano.client.util.screen.widget.util.ButtonWidgetBuilder;
import cdiv.nano.util.helper.TranslationHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class RoleplayScreen extends Screen {
    public final BoundDimensions<CachingDimensions<SimpleDimensions<?>>> BACKGROUND_DIMENSIONS;
    public final BoundDimensions<CachingDimensions<SimpleDimensions<?>>> SPURT_DIMENSIONS;

    public RoleplayScreen() {
        super();

        BACKGROUND_DIMENSIONS = new BoundDimensions<>( // Aspect Ratio: 15344.0 / 25893.0
            this,
            new CachingDimensions<>(
                new SimpleDimensions<>()
                    .setPosition(0.5, 0.5)
                    .setSize(0.2, 0.6)
                    .setAnchor(0.5, 0.5)
            )
        );

        SPURT_DIMENSIONS = new BoundDimensions<>(
            this,
            new CachingDimensions<>(
                new SimpleDimensions<>()
                    .setPosition(0.5, 0.27)
                    .setSize(0.16, 0.06)
                    .setAnchor(0.5, 0.5)
            )
        );
    }

    @Override
    protected void addDrawables(@NotNull MinecraftClient client) {
        //noinspection deprecation
        ButtonWidget buttonWidget = new ButtonWidgetBuilder(
            TranslationHelper.screen("roleplay.button.spurt.text"),
            b -> new SpurtScreen().open()
        )
            .dimensions(SPURT_DIMENSIONS)
            .build();

        addDrawable(new BackgroundWidget(BACKGROUND_DIMENSIONS));
        addDrawableChild(buttonWidget);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        // Minecraft doesn't have a "label" widget, so we'll have to draw our own text.
        // We'll subtract the font height from the Y position to make the text appear above the button.
        // Subtracting an extra 10 pixels will give the text some padding.
        // textRenderer, text, x, y, color, hasShadow
        context.drawText(this.textRenderer, "Special Button", 40, 40 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
    }
}
