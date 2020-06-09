package de.eldoria.soundmaster.bukkit;

import de.eldoria.soundmaster.api.SoundTarget;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class BukkitPlayer implements SoundTarget<Sound> {
    private final Player player;

    private BukkitPlayer(Player player) {
        this.player = player;
    }

    public static SoundTarget<Sound> adapt(Player player) {
        return new BukkitPlayer(player);
    }

    @Override
    public void playSound(Sound sound, float volume, float pitch) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }
}
