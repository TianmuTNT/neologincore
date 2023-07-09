package it.frafol.playervisibility.enums;

import it.frafol.playervisibility.PlayerVisibility;

public enum Config {

    STATS("settings.stats"),
    UPDATE_CHECKER("settings.update_check"),
    AUTO_UPDATE("settings.auto_update"),

    PERMISSION("settings.usage_permission"),
    RELOAD_PERMISSION("settings.reload_permission"),
    BYPASS_PERMISSION("settings.bypass_permission"),

    USAGE("messages.usage"),

    WORLD_ENABLED("worlds.enabled"),

    HIDDEN_MESSAGE("messages.hidden"),
    SHOWN_MESSAGE("messages.shown"),

    ALREADY_HIDDEN("messages.already_hidden"),
    ALREADY_SHOWN("messages.already_shown"),

    JOIN_MESSAGE("messages.join_message"),

    RELOADED("messages.reloaded"),
    NO_PERMISSION("messages.no_permission");

    private final String path;
    public static final PlayerVisibility instance = PlayerVisibility.getInstance();

    Config(String path) {
        this.path = path;
    }

    public String color() {
        return get(String.class).replace("&", "§");
    }

    public <T> T get(Class<T> clazz) {
        return clazz.cast(instance.getConfigTextFile().getConfig().get(path));
    }

}