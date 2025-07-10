package org.astrarails.neologincore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player joined = event.getPlayer();
        for (Player player : joined.getServer().getOnlinePlayers()) {
            if (player != joined) {
                joined.hidePlayer(player);
                player.hidePlayer(joined);
            }
        }
    }
}
