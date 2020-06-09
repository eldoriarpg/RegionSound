package de.eldoria.soundmaster.api;

public interface SoundPlaybackMachine<S> {
    int ONCE = 1;
    int INFINITE = -1;

    void queueRepeatingSound(SoundTarget<S> target, PlayableSound<S> sound, int playCount);

    default void queueSound(SoundTarget<S> target, PlayableSound<S> sound) {
        queueRepeatingSound(target, sound, ONCE);
    }

    default void queueInfiniteSound(SoundTarget<S> target, PlayableSound<S> sound) {
        queueRepeatingSound(target, sound, INFINITE);
    }

    void cancelInfiniteSound(SoundTarget<S> target, PlayableSound<S> sound);
}
