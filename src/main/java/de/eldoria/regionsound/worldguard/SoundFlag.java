package de.eldoria.regionsound.worldguard;

import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.FlagContext;
import com.sk89q.worldguard.protection.flags.InvalidFlagFormat;
import de.eldoria.regionsound.SoundDefinition;
import de.eldoria.regionsound.util.FlagParseUtil;
import org.apache.commons.lang.math.IntRange;
import org.bukkit.Sound;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SoundFlag extends Flag<SoundDefinition> {
    private final static String PARAMETER_DELIMITER = ";";
    private final static String SOUND_DELIMITER = ",";
    private final static String FLAG_NAME = "sound";
    public static final SoundFlag SOUND_FLAG = new SoundFlag();

    protected SoundFlag() {
        super(FLAG_NAME);
    }

    @Override
    public SoundDefinition parseInput(FlagContext flagContext) throws InvalidFlagFormat {
        List<Sound> sounds = new ArrayList<>();
        String[] splitAll = flagContext.getUserInput().split(PARAMETER_DELIMITER);
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
        return new SoundDefinition(sounds, range);
    }

    // TODO: Finish this
    @Override
    public SoundDefinition unmarshal(@Nullable Object o) {
        return null;
    }

    @Override
    public Object marshal(SoundDefinition soundDefinition) {
        return null;
    }
}
