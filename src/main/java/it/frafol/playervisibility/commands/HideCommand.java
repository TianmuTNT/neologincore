package it.frafol.playervisibility.commands;

import it.frafol.playervisibility.PlayerVisibility;
import it.frafol.playervisibility.enums.Config;
import it.frafol.playervisibility.objects.TextFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class HideCommand implements Listener {

    public final PlayerVisibility plugin;

    public HideCommand(PlayerVisibility plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {

        final String message = event.getMessage();

        if (!message.startsWith("/")) {
            return;
        }

        final String command = event.getMessage().replace("/", "");

        for (String alias : plugin.getConfigTextFile().getConfig().getStringList("command.aliases")) {

            if (!command.startsWith(alias)) {
                continue;
            }

            event.setCancelled(true);

            String[] words = command.split(" ");
            final Player player = event.getPlayer();

            if (!player.hasPermission(Config.PERMISSION.get(String.class))) {
                player.sendMessage("§dThis server is using PlayerVisibility by frafol.");
                return;
            }

            if (words.length != 2) {
                player.sendMessage(Config.USAGE.color());
                return;
            }

            String secondWord = words[1];

            switch (secondWord) {
                case "reload":

                    if (!player.hasPermission(Config.RELOAD_PERMISSION.get(String.class))) {
                        player.sendMessage(Config.NO_PERMISSION.color());
                        return;
                    }

                    TextFile.reloadAll();
                    player.sendMessage(Config.RELOADED.color());
                    return;

                case "hide":

                    if (plugin.isHided()) {
                        player.sendMessage(Config.ALREADY_HIDDEN.color());
                        return;
                    }

                    plugin.hidePlayers();
                    player.sendMessage(Config.HIDDEN_MESSAGE.color());

                    return;

                case "show":

                    if (!plugin.isHided()) {
                        player.sendMessage(Config.ALREADY_SHOWN.color());
                        return;
                    }

                    plugin.showPlayers();
                    player.sendMessage(Config.SHOWN_MESSAGE.color());

                    return;

                default:

                    player.sendMessage(Config.USAGE.color());
                    return;

            }
        }
    }
}
