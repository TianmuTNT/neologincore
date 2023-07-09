package it.frafol.playervisibility.listeners;

import it.frafol.playervisibility.PlayerVisibility;
import it.frafol.playervisibility.enums.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChangeListener implements Listener {

    public final PlayerVisibility plugin;

    public WorldChangeListener(PlayerVisibility plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWorldChange(PlayerChangedWorldEvent event) {

        Player player = event.getPlayer();

        if (Config.WORLD_ENABLED.get(Boolean.class)) {
            for (String blacklisted_world_names : plugin.getConfigTextFile().getConfig().getStringList("world.blacklisted_worlds")) {

                if (!player.getWorld().getName().equalsIgnoreCase(blacklisted_world_names)) {
                    continue;
                }

                if (!plugin.isHided()) {
                    return;
                }

                plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                    for (Player players : plugin.getServer().getOnlinePlayers()) {
                        player.showPlayer(players);
                        players.showPlayer(player);
                    }
                }, 5L);

                return;

            }
        }

        if (!plugin.isHided()) {
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                for (Player players : plugin.getServer().getOnlinePlayers()) {
                    player.showPlayer(players);
                    players.showPlayer(player);
                }
            }, 5L);
            return;
        }

        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            for (Player players : plugin.getServer().getOnlinePlayers()) {

                if (!player.hasPermission(Config.BYPASS_PERMISSION.get(String.class))) {
                    player.hidePlayer(players);
                }

                if (players.hasPermission(Config.BYPASS_PERMISSION.get(String.class))) {
                    continue;
                }

                players.hidePlayer(player);

            }
        }, 2L);
    }
}
