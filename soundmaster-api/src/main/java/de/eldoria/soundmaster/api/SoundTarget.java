package de.eldoria.soundmaster.api;

public interface SoundTarget<S> {

    default void playSound(S sound) {
        playSound(sound, 0.5f, 1f);
    }

    void playSound(S sound, float volume, float pitch);
}
