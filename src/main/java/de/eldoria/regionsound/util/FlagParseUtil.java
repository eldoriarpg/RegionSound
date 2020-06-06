package de.eldoria.regionsound.util;

import com.sk89q.worldguard.protection.flags.InvalidFlagFormat;
import org.apache.commons.lang.math.IntRange;
import org.bukkit.Sound;

import java.util.HashMap;
import java.util.Map;

public final class FlagParseUtil {
    private static Map<String, Sound> soundMap = new HashMap<>();

    static {
        for (Sound sound : Sound.values()) {
            soundMap.put(sound.name(), sound);
        }
    }

    private FlagParseUtil() {
        throw new AssertionError();
    }

    public static Sound soundOf(String soundName) throws InvalidFlagFormat {
        Sound result;
        if ((result = soundMap.get(soundName.toUpperCase())) == null) {
            throw new InvalidFlagFormat("Sound " + soundName + " does not exist");
        }
        return result;
    }

    public static IntRange intRangeOf(String intRange) throws InvalidFlagFormat {
        String[] range = intRange.split("\\.\\.");
        if (range.length != 2) {
            throw new InvalidFlagFormat("Invalid range: " + intRange);
        }
        int start;
        try {
            start = Integer.parseInt(range[0]);
        } catch (NumberFormatException e) {
            throw new InvalidFlagFormat("Not an integer: " + range[0]);
        }
        int end;
        try {
            end = Integer.parseInt(range[1]);
        } catch (NumberFormatException e) {
            throw new InvalidFlagFormat("Not an integer: " + range[1]);
        }
        return new IntRange(start, end);
    }
}
