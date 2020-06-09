package de.eldoria.soundmaster.api;

public interface PlayableSound<S> {

    default void play(SoundTarget<S> target) {
        target.playSound(getPlatformSound(), getVolume(), getPitch());
    }

    float getVolume();

    float getPitch();

    int getDuration();

    S getPlatformSound();
}
