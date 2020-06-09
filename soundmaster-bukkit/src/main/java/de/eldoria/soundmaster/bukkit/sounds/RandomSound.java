package de.eldoria.soundmaster.bukkit.sounds;

import org.apache.commons.lang.math.IntRange;
import org.bukkit.Sound;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSound extends BukkitSound {
    private final IntRange durationRange;
    private final List<Sound> sounds;

    public RandomSound(List<Sound> sounds, IntRange durationRange) {
        this.sounds = sounds;
        this.durationRange = durationRange;
    }

    @Override
    public float getVolume() {
        return 1f;
    }

    @Override
    public float getPitch() {
        return 1f;
    }

    @Override
    public int getDuration() {
        return randomInRange(this.durationRange);
    }

    @Override
    public Sound getPlatformSound() {
        int index = ThreadLocalRandom.current().nextInt(sounds.size());
        return this.sounds.get(index);
    }

    private static int randomInRange(IntRange range) {
        return ThreadLocalRandom.current().nextInt(range.getMinimumInteger(), range.getMaximumInteger());
    }
}
