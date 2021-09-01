package dev.nikomaru.fireworktax.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Customconfig {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("FireworkTax").getDataFolder(), "config.yml");
        if (!file.exists()) {
            try {
                file.createNewFile ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }


    public static FileConfiguration get() {
        return customFile;
    }


    public static void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            System.out.println("Couldn't save file");
        }
    }

    public static int getCount() {
        return Customconfig.get ().getInt ("MessageCounter");
    }

    public static float getFirework() {
        return (float) get ().getDouble ("Firework");
    }
}