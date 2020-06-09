package de.eldoria.soundmaster.bukkit;

import de.eldoria.soundmaster.api.PlayableSound;
import de.eldoria.soundmaster.api.SoundPlaybackMachine;
import de.eldoria.soundmaster.api.SoundTarget;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;


public class DefaultSoundPlaybackMachine implements SoundPlaybackMachine, Runnable {
    private final Queue<ScheduledSound> soundQueue = new PriorityQueue<>();

    @Override
    public void run() {
        LocalDateTime now = LocalDateTime.now();
        while (!this.soundQueue.isEmpty()) {
            if (this.soundQueue.peek().when.isAfter(now)) {
                ScheduledSound element = this.soundQueue.element();
                element.playSound();
                // check if reschedule sound
                if (element.playAgain()) {
                    // reuse object
                    element.calculateNext();
                    this.soundQueue.add(element);
                }
            } else {
                break;
            }
        }
    }

    @Override
    public void queueRepeatingSound(SoundTarget target, PlayableSound sound, int playCount) {
        ScheduledSound scheduled = new ScheduledSound(target, sound, playCount);
        this.soundQueue.add(scheduled);
    }

    @Override
    public void cancelInfiniteSound(SoundTarget target, PlayableSound sound) {
        this.soundQueue.removeIf(scheduledSound -> scheduledSound.matches(target, sound));
    }

    private static class ScheduledSound implements Comparable<ScheduledSound> {
        private final SoundTarget target;
        private final PlayableSound sound;
        private LocalDateTime when;
        private int playCount;

        public ScheduledSound(SoundTarget target, PlayableSound sound, int playCount) {
            this.target = target;
            this.sound = sound;
            this.playCount = playCount;
            calculateNext();
        }

        public void calculateNext() {
            this.when = LocalDateTime.now().plusSeconds(this.sound.getDuration());
        }

        @Override
        public int compareTo(ScheduledSound o) {
            return this.when.compareTo(o.when);
        }

        public boolean playAgain() {
            return this.playCount != 0;
        }

        public void playSound() {
            this.playCount--;
            this.sound.play(this.target);
        }

        public boolean matches(SoundTarget target, PlayableSound sound) {
            return this.target.equals(target) && this.sound.equals(sound);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ScheduledSound that = (ScheduledSound) o;
            return target.equals(that.target) &&
                    sound.equals(that.sound);
        }

        @Override
        public int hashCode() {
            return Objects.hash(target, sound);
        }
    }
}
