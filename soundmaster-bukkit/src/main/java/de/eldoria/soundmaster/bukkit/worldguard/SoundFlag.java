package de.eldoria.soundmaster.bukkit.worldguard;

import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.FlagContext;
import com.sk89q.worldguard.protection.flags.InvalidFlagFormat;
import de.eldoria.soundmaster.api.PlayableSound;
import de.eldoria.soundmaster.bukkit.sounds.RandomSound;
import de.eldoria.soundmaster.bukkit.util.FlagParseUtil;
import org.apache.commons.lang.math.IntRange;
import org.bukkit.Sound;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SoundFlag extends Flag<RandomSound> {
    public static final SoundFlag SOUND_FLAG = new SoundFlag();
    private final static String PARAMETER_DELIMITER = ";";
    private final static String SOUND_DELIMITER = ",";
    private final static String FLAG_NAME = "sound";

    protected SoundFlag() {
        super(FLAG_NAME);
    }

    @Override
    public RandomSound parseInput(FlagContext flagContext) throws InvalidFlagFormat {
        return parse(flagContext.getUserInput());
    }

    // TODO: Finish this
    @Override
    public RandomSound unmarshal(@Nullable Object o) {
        if (o == null) return null;
        String s = (String) o;
        try {
            return parse(s);
        } catch (InvalidFlagFormat invalidFlagFormat) {
            invalidFlagFormat.printStackTrace();
            return null;
        }
    }

    @Override
    public Object marshal(RandomSound playableSound) {
        return join(SOUND_DELIMITER, playableSound.getSounds()) +
                PARAMETER_DELIMITER + intRangeToString(playableSound.getDurationRange());
    }

    private RandomSound parse(String input) throws InvalidFlagFormat {
        List<Sound> sounds = new ArrayList<>();
        String[] splitAll = input.split(PARAMETER_DELIMITER);
        if (splitAll.length == 0) {
            throw new InvalidFlagFormat("No sound provided");
        }
        String[] soundNames = splitAll[0].split(SOUND_DELIMITER);
        for (String soundName : soundNames) {
            Sound sound = FlagParseUtil.soundOf(soundName);
            sounds.add(sound);
        }
        IntRange range;
        if (splitAll.length >= 2) {
            range = FlagParseUtil.intRangeOf(splitAll[1]);
        } else {
            range = new IntRange(1, 20);
        }
        return new RandomSound(sounds, range);
    }

    private static String join(String delimiter, Iterable<Sound> sounds) {
        StringJoiner joiner = new StringJoiner(delimiter);
        sounds.forEach(s -> joiner.add(s.name().toLowerCase()));
        return joiner.toString();
    }

    private static String intRangeToString(IntRange range) {
        return range.getMinimumInteger() + ".." + range.getMaximumInteger();
    }
}
