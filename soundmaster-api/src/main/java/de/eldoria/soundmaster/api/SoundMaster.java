package de.eldoria.soundmaster.api;

public interface SoundMaster<S> {

    SoundPlaybackMachine<S> getSoundPlaybackMachine();
}
