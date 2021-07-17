package com.github.nlkomaru.fireworktax.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class config {

    private final Plugin plugin;
    private FileConfiguration config = null;

    private static int firework;

    public config(Plugin plugin) {
        this.plugin = plugin;
        load();
    }


    public void load() {
        plugin.saveDefaultConfig();
        if (config != null) {
            plugin.reloadConfig();
        }
        config = plugin.getConfig();
        firework = config.getInt("firework");

    }

    public static int getFirework(){
        return firework;
    }
}