package de.eldoria.regionsound;

import de.eldoria.regionsound.provider.RegionProviderService;
import de.eldoria.regionsound.worldguard.WorldGuardService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class RegionSoundPlugin extends JavaPlugin {
    private final List<RegionProviderService> regionProviders;

    public RegionSoundPlugin() {
        this.regionProviders = new ArrayList<>();
        if (Bukkit.getPluginManager().getPlugin("WorldGuard") != null) {
            this.regionProviders.add(new WorldGuardService());
        }
    }

    @Override
    public void onLoad() {
        for (RegionProviderService service : this.regionProviders) {
            service.onLoad(this);
        }
    }

    @Override
    public void onEnable() {
        for (RegionProviderService service : this.regionProviders) {
            service.onEnable(this);
        }
    }

    @Override
    public void onDisable() {
        for (RegionProviderService service : this.regionProviders) {
            service.onDisable(this);
        }
    }
}
