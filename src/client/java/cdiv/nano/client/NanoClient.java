package cdiv.nano.client;

import cdiv.nano.Components;
import cdiv.nano.ScreenHandlers;
import cdiv.nano.SharedInteractions;
import cdiv.nano.client.access.WidenedClientPlayerInteractionManager;
import cdiv.nano.api.client.config.FirstPersonModel;
import cdiv.nano.api.client.config.Keybinding;
import cdiv.nano.api.client.NanoClientIntegration;
import cdiv.nano.client.screens.block.ScaleCombinerScreen;
import cdiv.nano.client.screens.block.ScaleNormalizerScreen;
import cdiv.nano.client.screens.roleplay.RoleplayScreen;
import cdiv.nano.util.helper.TranslationHelper;
import dev.tr7zw.firstperson.api.FirstPersonAPI;
import dev.tr7zw.firstperson.api.PlayerOffsetHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;
import virtuoel.pehkui.api.ScaleTypes;

import java.util.Optional;

public class NanoClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		SharedInteractions.initializeMiningPositionGetter(player -> {
			ClientPlayerInteractionManager interactionManager = MinecraftClient.getInstance().interactionManager;

			if (interactionManager == null)
				return Optional.empty();

			return ((WidenedClientPlayerInteractionManager) interactionManager).nano$getMiningPosition();
		});

		if (FabricLoader.getInstance().isModLoaded("firstperson")) {
			FirstPersonAPI.registerPlayerHandler(((PlayerOffsetHandler) (player, delta, zero, offset) -> {
				if (FirstPersonModel.bodyOffsetScalingEnabled.getOrDefault())
					return offset;

				return offset.multiply(ScaleTypes.BASE.getScaleData(player).getScale());
			}));
		}

		HandledScreens.register(
			ScreenHandlers.SCALE_COMBINER,
			ScaleCombinerScreen::new
		);

		HandledScreens.register(
			ScreenHandlers.SCALE_NORMALIZER,
			ScaleNormalizerScreen::new
		);

		FabricLoader fabricLoader = FabricLoader.getInstance();

		fabricLoader
			.getEntrypoints("nano-client", NanoClientIntegration.class)
			.forEach(NanoClientIntegration::onNanoInitialize);

		ItemTooltipCallback.EVENT.register((stack, context, type, list) -> {
			Double scale = stack.get(Components.ITEM_SCALE);

			if (scale == null)
				return;

			list.add(1, TranslationHelper
				.component("item_scale.tooltip", scale)
				.formatted(Formatting.GRAY));
		});

		if (Boolean.TRUE.equals(Keybinding.keybindsEnabled.lockAndGet()) && Boolean.TRUE.equals(Keybinding.debugKeybindsEnabled.lockAndGet())) {
			RoleplayScreen roleplay = new RoleplayScreen();

			KeyBinding debug1 = KeyBindingHelper.registerKeyBinding(
				new KeyBinding(
					"key.nano.debug1",
					InputUtil.Type.KEYSYM,
					GLFW.GLFW_KEY_U,
					"key.category.nano"
				)
			);

			ClientTickEvents.END_CLIENT_TICK.register(client -> {
				do {
					if (!debug1.wasPressed())
						return;
				} while(debug1.wasPressed());

				if (roleplay.isOpen())
					return;

				roleplay.open();
			});
		}
	}
}