package de.eldoria.regionsound;

import org.apache.commons.lang.math.IntRange;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SoundDefinition {
    private final List<Sound> sounds;
    private final IntRange randomIntervalRange;

    public SoundDefinition(Collection<Sound> sounds, IntRange randomIntervalRange) {
        this.sounds = new ArrayList<>(sounds);
        this.randomIntervalRange = randomIntervalRange;
    }
}
