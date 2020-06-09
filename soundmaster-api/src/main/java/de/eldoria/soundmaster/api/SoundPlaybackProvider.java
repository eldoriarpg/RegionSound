package de.eldoria.soundmaster.api;

public interface SoundPlaybackProvider<S> {

    void onLoad(SoundMaster<S> plugin);

    void onEnable(SoundMaster<S> plugin);

    void onDisable(SoundMaster<S> plugin);
}
