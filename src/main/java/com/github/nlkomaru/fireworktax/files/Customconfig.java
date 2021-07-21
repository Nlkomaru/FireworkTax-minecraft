package com.github.nlkomaru.fireworktax.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
public class Customconfig {

    private static File file;
    private static FileConfiguration customFile;
    private static float Firework ;
    private static int Count;


    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("FireworkTax").getDataFolder(), "config.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                //temp
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

    public static int getCount(){
        Count = Customconfig.get().getInt("MessageCounter");
        return Count;
    }
    public static float getFirework(){
        Firework = (float) get().getDouble("Firework");
        return Firework;
    }
}
