/**
 * Copyright (C) 2026 CDiv
 *
 * This file is part of Nano.
 *
 * Nano is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * Nano is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Nano. If not, see <https://www.gnu.org/licenses/>.
 */
package cdiv.nano;

import cdiv.nano.access.MovingPlayer;
import cdiv.nano.api.config.Loot;
import cdiv.nano.loot.functions.ScaledLoot;
import cdiv.nano.payload.IsPlayerMovingC2SPayload;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import virtuoel.pehkui.api.ScaleTypes;

import java.util.List;

import cdiv.nano.api.*;

public class Nano implements ModInitializer {
	public static final String MOD_ID = "nano";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	public static MutableText translation(String key, String path) {
		return Text.translatable(key + '.' + MOD_ID + '.' + path);
	}

	public static MutableText translation(String key, String path, Object... args) {
		return Text.translatable(key + '.' + MOD_ID + '.' + path, args);
	}

	@Override
	public void onInitialize() {
		// Run Nano integrations
		FabricLoader
			.getInstance()
			.getEntrypoints(MOD_ID, NanoIntegration.class)
			.forEach(NanoIntegration::onNanoInitialize);

		ScaleTypes.KNOCKBACK.getDefaultBaseValueModifiers().add(ScaleModifiers.KNOCKBACK_MULTIPLIER);
		ScaleTypes.HELD_ITEM.getDefaultBaseValueModifiers().add(ScaleModifiers.HELD_ITEM_MULTIPLIER);
		ScaleTypes.ATTACK.getDefaultBaseValueModifiers().add(ScaleModifiers.ATTACK_DAMAGE_MULTIPLIER);
		ScaleTypes.ATTACK_SPEED.getDefaultBaseValueModifiers().add(ScaleModifiers.ATTACK_SPEED_MULTIPLIER);
		ScaleTypes.MINING_SPEED.getDefaultBaseValueModifiers().add(ScaleModifiers.MINING_SPEED_MULTIPLIER);
		ScaleTypes.DROPS.getDefaultBaseValueModifiers().add(ScaleModifiers.DROPS_BASE_MULTIPLIER);
		ScaleTypes.DROPS.getDefaultBaseValueModifiers().remove(virtuoel.pehkui.api.ScaleModifiers.BASE_MULTIPLIER);

		Components.initialize();
		Blocks.initialize();
		BlockEntities.initialize();
		Items.initialize();
		ItemGroups.initialize();
		ScreenHandlers.initialize();
		LootFunctions.initialize();
		DamageSources.initialize();
		Sounds.initialize();

		PayloadTypeRegistry.playC2S().register(IsPlayerMovingC2SPayload.ID, IsPlayerMovingC2SPayload.CODEC);
		CommandRegistrationCallback.EVENT.register(Commands::register);

		ServerPlayNetworking.registerGlobalReceiver(IsPlayerMovingC2SPayload.ID, (payload, context) -> ((MovingPlayer) context.player()).nano$setMoving(payload.isMoving()));

		LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			if (!source.isBuiltin()
				|| !key.getValue().getPath().startsWith("entities/")
				|| !Loot.lootScalingEnabled.get())
				return;

			tableBuilder.apply(() -> new ScaledLoot(List.of()));
		});
	}
}