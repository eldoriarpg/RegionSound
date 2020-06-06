package de.eldoria.regionsound.worldguard;

import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.FlagValueChangeHandler;
import de.eldoria.regionsound.SoundDefinition;

public class SoundFlagHandler extends FlagValueChangeHandler<SoundDefinition> {
    protected SoundFlagHandler(Session session) {
        super(session, SoundFlag.SOUND_FLAG);
    }

    @Override
    protected void onInitialValue(LocalPlayer player, ApplicableRegionSet set, SoundDefinition value) {

    }

    @Override
    protected boolean onSetValue(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet, SoundDefinition currentValue, SoundDefinition lastValue, MoveType moveType) {
        return true;
    }

    @Override
    protected boolean onAbsentValue(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet, SoundDefinition lastValue, MoveType moveType) {
        return true;
    }
}
