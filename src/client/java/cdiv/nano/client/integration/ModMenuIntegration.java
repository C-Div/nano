package cdiv.nano.client.integration;

import cdiv.nano.Nano;
import cdiv.nano.client.screens.integration.NoMidnightLib;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (FabricLoader.getInstance().isModLoaded("midnightlib"))
                return MidnightConfig.getScreen(parent, Nano.MOD_ID);

            return new NoMidnightLib(parent);
        };
    }
}
