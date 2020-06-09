package de.eldoria.soundmaster.api;

public interface SoundTarget<S> {

    default void playSound(S sound) {
        playSound(sound, 1f, 1f);
    }

    void playSound(S sound, float volume, float pitch);
}
