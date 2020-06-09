package de.eldoria.soundmaster.api;

public interface SoundPlaybackProvider {

    void onLoad(SoundMaster plugin);

    void onEnable(SoundMaster plugin);

    void onDisable(SoundMaster plugin);
}
