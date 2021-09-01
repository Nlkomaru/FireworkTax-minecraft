package dev.nikomaru.fireworktax;


import dev.nikomaru.fireworktax.API.VaultAPI;
import dev.nikomaru.fireworktax.files.Customconfig;
import dev.nikomaru.fireworktax.tax.BoostCheck;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;


public final class FireworkTax extends JavaPlugin {




    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Customconfig.setup();
        Customconfig.get().options().copyDefaults(true);
        Customconfig.save();


        if (!VaultAPI.setupEconomy()) {
            System.out.println("No economy plugin found. Disabling Vault");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(new BoostCheck(), this);
    }

}
