package com.github.nlkomaru.fireworktax.tax;


import com.github.nlkomaru.fireworktax.FireworkTax;

import com.github.nlkomaru.fireworktax.files.Customconfig;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;


public class BoostCheck implements Listener {

    HashMap<UUID, Float> total = new HashMap<>();
    HashMap<UUID, Integer> d = new HashMap<>();


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Economy eco = FireworkTax.getEconomy();
        Player p = e.getPlayer();
        UUID uuid = e.getPlayer().getUniqueId();
        float f = Customconfig.getFirework();
        int c = Customconfig.getCount();

        if(total.get(uuid) == null){
            d.put(uuid, 0);
            total.put(uuid, (float) 0);
        }

        if (p.isGliding()) {
            if (p.getInventory().getItemInMainHand().getType().equals(Material.FIREWORK_ROCKET) || p.getInventory().getItemInOffHand().getType().equals(Material.FIREWORK_ROCKET)) {
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                    if (eco.getBalance(p) < f) {
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.YELLOW + "お金がないのでブースト飛行できません");
                    } else {
                        EconomyResponse response = eco.withdrawPlayer(p, f);
                        if (c != 0) {
                            total.put(uuid, total.get(uuid) + f);
                            d.put(uuid, d.get(uuid) + 1);
                            if (d.get(uuid) >= c) {
                                p.sendMessage(ChatColor.YELLOW + String.format("%d回ブーストして合計で%.2f円引かれました", d.get(uuid), total.get(uuid)));
                                d.put(uuid, 0);
                                total.put(uuid, (float) 0);
                            }
                        }
                    }
                }
            }
        }
    }
}
