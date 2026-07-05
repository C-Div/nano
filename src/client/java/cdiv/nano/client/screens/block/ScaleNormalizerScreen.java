package cdiv.nano.client.screens.block;

import cdiv.nano.Nano;
import cdiv.nano.screen.ScaleNormalizerScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;


public class ScaleNormalizerScreen extends HandledScreen<ScaleNormalizerScreenHandler> {
    private static final Identifier TEXTURE = Nano.id("textures/gui/container/scale_normalizer.png");
    private static final Identifier NORMALIZE_PROGRESS_TEXTURE = Nano.id("container/scale_normalizer/normalize_progress");
    private static final int NORMALIZE_PROGRESS_WIDTH = 24;
    private static final int NORMALIZE_PROGRESS_HEIGHT = 17;

    public ScaleNormalizerScreen(ScaleNormalizerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        int normalizeProgress = MathHelper.ceil(this.handler.getNormalizeProgress() * (float) NORMALIZE_PROGRESS_WIDTH);

        context.drawGuiTexture(
            NORMALIZE_PROGRESS_TEXTURE,
            NORMALIZE_PROGRESS_WIDTH,
            NORMALIZE_PROGRESS_HEIGHT,
            0,
            0,
            x + 79,
            y + 33,
            normalizeProgress,
            NORMALIZE_PROGRESS_HEIGHT
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
