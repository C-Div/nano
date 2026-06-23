package cdiv.nano.client;

import cdiv.nano.Components;
import cdiv.nano.client.api.config.Keybinding;
import cdiv.nano.client.api.NanoClientIntegration;
import cdiv.nano.client.screens.roleplay.RoleplayScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class NanoClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		FabricLoader
			.getInstance()
			.getEntrypoints("nano-client", NanoClientIntegration.class)
			.forEach(NanoClientIntegration::onNanoInitialize);

		ItemTooltipCallback.EVENT.register((stack, context, type, list) -> {
			Double scale = stack.get(Components.ITEM_SCALE);

			if (scale == null)
				return;

			list.add(1, Text
				.translatable("component.nano.item_scale.tooltip", scale)
				.formatted(Formatting.GRAY));
		});

		if (Keybinding.keybindsEnabled.lockAndGet() && Keybinding.debugKeybindsEnabled.lockAndGet()) {
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