package dev.nikomaru.fireworktax.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
    private final Plugin plugin;
    private FileConfiguration config = null;
    private static float fireworkTax;
    private static int messageCounter;

    public Config (Plugin plugin) {
        this.plugin = plugin;
        load ();
    }

    public void load () {
        plugin.saveDefaultConfig ();
        if (config != null) {
            plugin.reloadConfig ();
        }
        config = plugin.getConfig ();

        if (!config.isDouble ("Firework") || config.getDouble ("Firework") < 0) {
            config.set ("Firework",10.0D);
        }
        if (!config.isInt ("MessageCounter")) {
            config.set ("MessageCounter",0);
        }
        fireworkTax = (float) config.getDouble ("Firework");

        messageCounter = config.getInt ("MessageCounter");
    }

    public static int getCount () {
        return messageCounter;
    }

    public static float getFirework () {
        return fireworkTax;
    }
}
