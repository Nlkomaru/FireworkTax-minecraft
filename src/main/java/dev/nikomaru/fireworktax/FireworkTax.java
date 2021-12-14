package dev.nikomaru.fireworktax;



import dev.nikomaru.fireworktax.api.VaultAPI;
import dev.nikomaru.fireworktax.files.Config;
import dev.nikomaru.fireworktax.tax.BoostCheck;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class FireworkTax extends JavaPlugin {

    public Plugin getPlugin(){
        return this;
    }


    @Override
    public void onEnable() {
        // Plugin startup logic
        Config config = new Config(this);
        if (!VaultAPI.setupEconomy() ) {
            getPlugin().getLogger().info(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(new BoostCheck(), this);
    }


}
