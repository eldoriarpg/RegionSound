package de.eldoria.soundmaster.bukkit.worldguard;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.session.SessionManager;
import de.eldoria.soundmaster.api.SoundMaster;
import de.eldoria.soundmaster.api.SoundPlaybackProvider;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;

import java.util.HashMap;
import java.util.Map;

public class WorldGuardService implements SoundPlaybackProvider<Sound> {
    public static SoundFlag SOUND_FLAG_INSTANCE;

    @Override
    public void onLoad(SoundMaster<Sound> plugin) {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            // create a flag with the name "my-custom-flag", defaulting to true
            registry.register(SoundFlag.SOUND_FLAG);
            SOUND_FLAG_INSTANCE = SoundFlag.SOUND_FLAG;
        } catch (FlagConflictException e) {
            // some other plugin registered a flag by the same name already.
            // you can use the existing flag, but this may cause conflicts - be sure to check type
            Flag<?> existing = registry.get(SoundFlag.SOUND_FLAG.getName());
            if (existing instanceof SoundFlag) {
                SOUND_FLAG_INSTANCE = (SoundFlag) existing;
            } else {
                // types don't match - this is bad news! some other plugin conflicts with you
                // hopefully this never actually happens
            }
        }
    }

    @Override
    public void onEnable(SoundMaster<Sound> plugin) {
        SessionManager sessionManager = WorldGuard.getInstance().getPlatform().getSessionManager();
        sessionManager.registerHandler(new SoundFlagHandler.Factory(plugin.getSoundPlaybackMachine()), null);
    }

    @Override
    public void onDisable(SoundMaster<Sound> plugin) {

    }
}
