package cdiv.nano.server;

import cdiv.nano.SharedInteractions;
import cdiv.nano.access.WidenedServerPlayerInteractionManager;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;

import java.util.Optional;

public class NanoServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        SharedInteractions.initializeMiningPositionGetter(player -> {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

            //noinspection DataFlowIssue
            ServerPlayerInteractionManager interactionManager = serverPlayer.getServer().getPlayerInteractionManager(serverPlayer);

            if (interactionManager == null)
                return Optional.empty();

            return ((WidenedServerPlayerInteractionManager) interactionManager).nano$getMiningPosition();
        });
    }
}