package cdiv.nano.client.helper.screen;

import cdiv.nano.Nano;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class Screen extends net.minecraft.client.gui.screen.Screen {
    @Nullable
    private net.minecraft.client.gui.screen.Screen parent;
    private boolean open = false;

    protected Screen() {
        this(Text.empty());
    }

    protected Screen(@Nullable net.minecraft.client.gui.screen.Screen parent) {
        this(parent, Text.empty());
    }

    protected Screen(@NotNull Text title) {
        this(null, title);
    }

    protected Screen(@Nullable net.minecraft.client.gui.screen.Screen parent, @NotNull Text title) {
        super(title);
        this.parent = parent;
    }

    @Override
    public void onDisplayed() {
        this.open = true;
    }

    @Override
    public void removed() {
        this.open = false;
    }

    @Override
    final protected void init() {
        assert this.client != null;
        addDrawables(this.client);
    }

    protected void addDrawables(@NotNull MinecraftClient client) {}

    public boolean isOpen() {
        return this.open;
    }

    @Nullable
    public net.minecraft.client.gui.screen.Screen getParent() {
        return this.parent;
    }

    public void open() {
        if (this.open)
            Nano.LOGGER.error("Opening an already open {} screen!", this.getClass().getSimpleName());

        MinecraftClient minecraftClient = MinecraftClient.getInstance();

        final net.minecraft.client.gui.screen.Screen currentScreen = minecraftClient.currentScreen;

        if (currentScreen == this)
            Nano.LOGGER.error("Opening an already open {} screen!", this.getClass().getSimpleName());

        this.parent = currentScreen;
        minecraftClient.setScreen(this);
    }

    @Override
    public void close() {
        close(parent);
    }

    public void close(@Nullable net.minecraft.client.gui.screen.Screen newScreen) {
        if (!this.open)
            Nano.LOGGER.error("Closing an already closed {} screen!", this.getClass().getSimpleName());

        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        minecraftClient.setScreen(newScreen);
    }

    public void forceClose() {
        close(null);
    }
}
