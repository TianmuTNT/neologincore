package it.frafol.playervisibility.listeners;

import it.frafol.playervisibility.PlayerVisibility;
import it.frafol.playervisibility.enums.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class JoinListener implements Listener {

    public final PlayerVisibility plugin;

    public JoinListener(PlayerVisibility plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {

        if (!plugin.isHided()) {
            return;
        }

        Player player = event.getPlayer();

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

        if (Objects.equals(Config.JOIN_MESSAGE.get(String.class), "none")) {
            return;
        }

        player.sendMessage(Config.JOIN_MESSAGE.color());
    }
}
