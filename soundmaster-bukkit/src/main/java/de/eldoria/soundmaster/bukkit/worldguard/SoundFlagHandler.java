package de.eldoria.soundmaster.bukkit.worldguard;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.FlagValueChangeHandler;
import com.sk89q.worldguard.session.handler.Handler;
import de.eldoria.soundmaster.api.PlayableSound;
import de.eldoria.soundmaster.api.SoundPlaybackMachine;
import de.eldoria.soundmaster.api.SoundTarget;
import de.eldoria.soundmaster.bukkit.BukkitPlayer;
import org.bukkit.Sound;

public class SoundFlagHandler extends FlagValueChangeHandler<PlayableSound<Sound>> {
    private final SoundPlaybackMachine<Sound> soundPlaybackMachine;

    protected SoundFlagHandler(Session session, SoundPlaybackMachine<Sound> soundPlaybackMachine) {
        super(session, SoundFlag.SOUND_FLAG);
        this.soundPlaybackMachine = soundPlaybackMachine;
    }

    @Override
    protected void onInitialValue(LocalPlayer player, ApplicableRegionSet set, PlayableSound<Sound> value) {

    }

    @Override
    protected boolean onSetValue(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet,
                                 PlayableSound<Sound> currentValue, PlayableSound<Sound> lastValue, MoveType moveType) {
        this.soundPlaybackMachine.queueInfiniteSound(adapt(player), currentValue);
        return true;
    }

    @Override
    protected boolean onAbsentValue(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet,
                                    PlayableSound<Sound> lastValue, MoveType moveType) {
        this.soundPlaybackMachine.cancelInfiniteSound(adapt(player), lastValue);
        return true;
    }

    private static SoundTarget<Sound> adapt(LocalPlayer player) {
        return BukkitPlayer.adapt(BukkitAdapter.adapt(player));
    }

    public static class Factory extends Handler.Factory<SoundFlagHandler> {
        private final SoundPlaybackMachine<Sound> soundPlaybackMachine;

        public Factory(SoundPlaybackMachine<Sound> soundPlaybackMachine) {
            this.soundPlaybackMachine = soundPlaybackMachine;
        }

        @Override
        public SoundFlagHandler create(Session session) {
            return new SoundFlagHandler(session, this.soundPlaybackMachine);
        }
    }
}
