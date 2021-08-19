package com.github.nlkomaru.fireworktax;


import com.github.nlkomaru.fireworktax.files.Customconfig;
import com.github.nlkomaru.fireworktax.tax.BoostCheck;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;


public final class FireworkTax extends JavaPlugin {

    private static Economy econ = null;


    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Customconfig.setup();
        Customconfig.get().options().copyDefaults(true);
        Customconfig.save();


        if (!setupEconomy()) {
            System.out.println("No economy plugin found. Disabling Vault");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(new BoostCheck(), this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;

    }


    public static Economy getEconomy() {
        return econ;
    }


}
