package cdiv.nano;

import cdiv.nano.util.helper.TranslationHelper;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Formatting;

public class Commands {
    @SuppressWarnings("unused")
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, RegistrationEnvironment environment) {
        if (FabricLoader.getInstance().isModLoaded("nano-commands"))
            return;

        LiteralArgumentBuilder<ServerCommandSource> nanoCommand = CommandManager.literal("nano");
        nanoCommand.executes(context -> {
            context.getSource().sendFeedback(
                () -> TranslationHelper.command("nano.fail").formatted(Formatting.RED),
                false
            );

            return 0;
        });

        dispatcher.register(nanoCommand);
    }
}
