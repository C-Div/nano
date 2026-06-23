package cdiv.nano.client.api.screens.spurt;

import cdiv.nano.client.screens.roleplay.SpurtScreen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.text.Text;

public abstract class SpurtModeUI {
    public abstract SpurtModeUIType<? extends SpurtModeUI> getType();

    public abstract void onSelected(CyclingButtonWidget<SpurtModeUI> buttonWidget, SpurtModeUI newSelected);

    public abstract void onDeselected(CyclingButtonWidget<SpurtModeUI> buttonWidget, SpurtModeUI previousSelected);

    public abstract void onSaveRequested(ButtonWidget buttonWidget);

    public abstract Text getText();

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {}
}
