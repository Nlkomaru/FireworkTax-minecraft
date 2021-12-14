package dev.nikomaru.fireworktax.api;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.Nullable;

import static org.bukkit.Bukkit.getServer;

public class VaultAPI {
    private static @Nullable Economy econ = null;

    public static boolean setupEconomy () {
        if (getServer ().getPluginManager ().getPlugin ("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer ().getServicesManager ().getRegistration (Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider ();
        return true;
    }

    public static @Nullable Economy getEconomy () {

        return econ;
    }
}
