package de.eldoria.soundmaster.bukkit;

import de.eldoria.soundmaster.api.SoundMaster;
import de.eldoria.soundmaster.api.SoundPlaybackProvider;
import de.eldoria.soundmaster.bukkit.worldguard.WorldGuardService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SoundMasterPlugin extends JavaPlugin implements SoundMaster {
    private final List<SoundPlaybackProvider> regionProviders;
    private BukkitTask soundManagerTask;
    private DefaultSoundPlaybackMachine soundPlaybackMachine;

    public SoundMasterPlugin() {
        this.regionProviders = new ArrayList<>();
        if (Bukkit.getPluginManager().getPlugin("WorldGuard") != null) {
            this.regionProviders.add(new WorldGuardService());
            getLogger().info("WorldGuard hook registered as region provider");
        }
    }

    @Override
    public void onLoad() {
        getServer().getServicesManager().getRegistrations(SoundPlaybackProvider.class)
                .forEach(register());
        for (SoundPlaybackProvider service : this.regionProviders) {
            service.onLoad(this);
        }
    }

    @Override
    public void onEnable() {
        for (SoundPlaybackProvider service : this.regionProviders) {
            service.onEnable(this);
        }
        this.soundPlaybackMachine = new DefaultSoundPlaybackMachine();
        this.soundManagerTask = Bukkit.getScheduler().runTaskTimer(this, this.soundPlaybackMachine, 20, 20);
    }

    @Override
    public void onDisable() {
        for (SoundPlaybackProvider service : this.regionProviders) {
            service.onDisable(this);
        }
        this.soundManagerTask.cancel();
    }

    private Consumer<RegisteredServiceProvider<SoundPlaybackProvider>> register() {
        return rsp -> {
            this.regionProviders.add(rsp.getProvider());
            getLogger().info("Plugin " + rsp.getPlugin().getName() + " registered as region provider");
        };
    }

    public DefaultSoundPlaybackMachine getSoundPlaybackMachine() {
        return this.soundPlaybackMachine;
    }
}