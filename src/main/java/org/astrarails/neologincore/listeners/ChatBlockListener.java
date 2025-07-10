package org.astrarails.neologincore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatBlockListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
    }
} 