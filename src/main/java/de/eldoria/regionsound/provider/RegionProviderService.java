package de.eldoria.regionsound.provider;

import de.eldoria.regionsound.RegionSoundPlugin;

public interface RegionProviderService {
    void onLoad(RegionSoundPlugin plugin);

    void onEnable(RegionSoundPlugin plugin);

    void onDisable(RegionSoundPlugin plugin);
}
