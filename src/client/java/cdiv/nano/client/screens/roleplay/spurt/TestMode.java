package cdiv.nano.client.screens.roleplay.spurt;

import cdiv.nano.client.api.screens.spurt.SpurtModeUI;
import cdiv.nano.client.api.screens.spurt.SpurtModeUIType;
import cdiv.nano.client.screens.roleplay.SpurtScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class TestMode extends SpurtModeUI {
    public static final SpurtModeUIType<TestMode> TYPE = new SpurtModeUIType<>(
        TestMode::new,
        TestMode::isSelectable
    );

    @NotNull
    public final SpurtScreen SCREEN;

    public TestMode(@NotNull SpurtScreen screen) {
        this.SCREEN = screen;
    }

    public static boolean isSelectable() {
        return true;
    }

    @Override
    public SpurtModeUIType<? extends SpurtModeUI> getType() {
        return TYPE;
    }

    @Override
    public void onSelected(CyclingButtonWidget<SpurtModeUI> buttonWidget, SpurtModeUI newSelected) {

    }

    @Override
    public void onDeselected(CyclingButtonWidget<SpurtModeUI> buttonWidget, SpurtModeUI previousSelected) {

    }

    @Override
    public void onSaveRequested(ButtonWidget buttonWidget) {

    }

    @Override
    public Text getText() {
        return Text.literal("Test");
    }
}
