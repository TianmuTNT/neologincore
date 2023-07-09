package it.frafol.playervisibility.listeners;

import it.frafol.playervisibility.PlayerVisibility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    public final PlayerVisibility plugin;

    public QuitListener(PlayerVisibility plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {

        if (!plugin.isHided()) {
            return;
        }

        Player player = event.getPlayer();

        for (Player players : plugin.getServer().getOnlinePlayers()) {
            player.showPlayer(players);
            players.showPlayer(player);
        }

    }
}
