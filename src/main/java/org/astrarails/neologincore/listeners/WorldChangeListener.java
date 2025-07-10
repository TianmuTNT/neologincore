package org.astrarails.neologincore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChangeListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        for (Player target : player.getServer().getOnlinePlayers()) {
            if (player != target) {
                player.hidePlayer(target);
                target.hidePlayer(player);
            }
        }
    }
}
