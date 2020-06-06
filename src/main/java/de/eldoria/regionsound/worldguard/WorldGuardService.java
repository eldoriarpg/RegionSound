package de.eldoria.regionsound.worldguard;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import de.eldoria.regionsound.RegionSoundPlugin;
import de.eldoria.regionsound.provider.RegionProviderService;

public class WorldGuardService implements RegionProviderService {
    public static StateFlag MY_CUSTOM_FLAG;

    @Override
    public void onLoad(RegionSoundPlugin plugin) {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            // create a flag with the name "my-custom-flag", defaulting to true
            StateFlag flag = new StateFlag("my-custom-flag", true);
            registry.register(flag);
            MY_CUSTOM_FLAG = flag; // only set our field if there was no error
        } catch (FlagConflictException e) {
            // some other plugin registered a flag by the same name already.
            // you can use the existing flag, but this may cause conflicts - be sure to check type
            Flag<?> existing = registry.get("my-custom-flag");
            if (existing instanceof StateFlag) {
                MY_CUSTOM_FLAG = (StateFlag) existing;
            } else {
                // types don't match - this is bad news! some other plugin conflicts with you
                // hopefully this never actually happens
            }
        }
    }

    @Override
    public void onEnable(RegionSoundPlugin plugin) {

    }

    @Override
    public void onDisable(RegionSoundPlugin plugin) {

    }
}
