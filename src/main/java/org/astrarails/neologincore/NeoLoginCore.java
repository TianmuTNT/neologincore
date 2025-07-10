package org.astrarails.neologincore;

import org.astrarails.neologincore.listeners.ChatBlockListener;
import org.astrarails.neologincore.listeners.JoinListener;
import org.astrarails.neologincore.listeners.WorldChangeListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class NeoLoginCore extends JavaPlugin {
    @Override
    public void onEnable() {
        // 插件加载时让所有玩家互相不可见
        for (Player player : getServer().getOnlinePlayers()) {
            for (Player target : getServer().getOnlinePlayers()) {
                if (player != target) {
                    player.hidePlayer(target);
                    target.hidePlayer(player);
                }
            }
        }
        // 注册监听器
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new WorldChangeListener(), this);
        getServer().getPluginManager().registerEvents(new ChatBlockListener(), this);
    }

    @Override
    public void onDisable() {
        // 插件卸载时让所有玩家互相可见
        for (Player player : getServer().getOnlinePlayers()) {
            for (Player target : getServer().getOnlinePlayers()) {
                if (player != target) {
                    player.showPlayer(target);
                    target.showPlayer(player);
                }
            }
        }
    }
}
