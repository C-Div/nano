package cdiv.nano.client.screens.block;

import cdiv.nano.Nano;
import cdiv.nano.client.util.screen.BoundDimensions;
import cdiv.nano.client.util.screen.CachingDimensions;
import cdiv.nano.client.util.screen.SimpleDimensions;
import cdiv.nano.screen.ScaleCombinerScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ScaleCombinerScreen extends HandledScreen<ScaleCombinerScreenHandler> {
    private static final Identifier TEXTURE = Nano.id("textures/gui/container/scale_combiner.png");
    private static final Identifier COMBINE_PROGRESS_TEXTURE = Nano.id("container/scale_combiner/combine_progress");
    private static final int COMBINE_PROGRESS_WIDTH = 24;
    private static final int COMBINE_PROGRESS_HEIGHT = 16;

    public final BoundDimensions<CachingDimensions<SimpleDimensions<?>>> BACKGROUND_DIMENSIONS;

    public ScaleCombinerScreen(ScaleCombinerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        BACKGROUND_DIMENSIONS = new BoundDimensions<>( // Aspect Ratio: 15344.0 / 25893.0
            this,
            new CachingDimensions<>(
                new SimpleDimensions<>()
                    .setPosition(0.5, 0.5)
                    .setSize(0.2, 0.6)
                    .setAnchor(0.5, 0.5)
            )
        );
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        int combineProgress = MathHelper.ceil(this.handler.getCombineProgress() * 24.0F);

        context.drawGuiTexture(
            COMBINE_PROGRESS_TEXTURE,
            COMBINE_PROGRESS_WIDTH,
            COMBINE_PROGRESS_HEIGHT,
            0,
            0,
            x + 97,
            y + 34,
            combineProgress,
            COMBINE_PROGRESS_HEIGHT
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
