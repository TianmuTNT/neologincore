package it.frafol.playervisibility.enums;

import it.frafol.playervisibility.PlayerVisibility;

public enum Data {

    HIDDEN("hidden");

    private final String path;
    public static final PlayerVisibility instance = PlayerVisibility.getInstance();

    Data(String path) {
        this.path = path;
    }

    public <T> T get(Class<T> clazz) {
        return clazz.cast(instance.getConfigTextFile().getConfig().get(path));
    }
}