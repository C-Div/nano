package cdiv.nano.client.screens.roleplay;

import cdiv.nano.client.api.screens.Spurt;
import cdiv.nano.client.api.screens.spurt.SpurtModeUI;
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
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class SpurtScreen extends Screen {
    public final BoundDimensions<CachingDimensions<SimpleDimensions<?>>> BACKGROUND_DIMENSIONS;
    public final BoundDimensions<CachingDimensions<SimpleDimensions<?>>> SPURT_DIMENSIONS;
    public final BoundDimensions<CachingDimensions<SimpleDimensions<?>>> SAVE_DIMENSIONS;
    private SpurtModeUI selectedSpurtModeUI;

    public SpurtScreen() {
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

        SAVE_DIMENSIONS = new BoundDimensions<>(
            this,
            new CachingDimensions<>(
                new SimpleDimensions<>()
                    .setPosition(0.5, 0.75)
                    .setSize(0.16, 0.06)
                    .setAnchor(0.5, 1)
            )
        );
    }

    protected void onModeCycle(CyclingButtonWidget<SpurtModeUI> cyclingButtonWidget, SpurtModeUI newSelected) {
        selectedSpurtModeUI.onDeselected(cyclingButtonWidget, newSelected);
        newSelected.onSelected(cyclingButtonWidget, selectedSpurtModeUI);
        selectedSpurtModeUI = newSelected;
    }

    @Override
    protected void addDrawables(@NotNull MinecraftClient client) {
        //noinspection deprecation
        ButtonWidget saveButton = new ButtonWidgetBuilder(
            TranslationHelper.screen("roleplay.spurt.button.save.text"),
            button -> selectedSpurtModeUI.onSaveRequested(button)
        )
            .dimensions(SAVE_DIMENSIONS)
            .build();

        List<SpurtModeUI> spurtModes = new LinkedList<>();
        Spurt.spurtModes.forEach(type -> {
            if (!type.isSelectable().getAsBoolean())
                return;

            spurtModes.add(type.constructor().construct(this));
        });

        CyclingButtonWidget<SpurtModeUI> modeButton = CyclingButtonWidget.builder(SpurtModeUI::getText)
            .values(spurtModes)
            .build(
                SPURT_DIMENSIONS.getX(), SPURT_DIMENSIONS.getY(),
                SPURT_DIMENSIONS.getWidth(), SPURT_DIMENSIONS.getHeight(),
                TranslationHelper.screen("roleplay.spurt.button.mode.prefix"),
                this::onModeCycle
            );

        selectedSpurtModeUI = modeButton.getValue();
        addDrawable(new BackgroundWidget(BACKGROUND_DIMENSIONS));
        addDrawable(saveButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        selectedSpurtModeUI.render(context, mouseX, mouseY, delta);
    }
}
