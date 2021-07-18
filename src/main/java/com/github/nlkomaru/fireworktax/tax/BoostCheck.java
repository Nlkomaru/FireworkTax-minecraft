package com.github.nlkomaru.fireworktax.tax;


import com.github.nlkomaru.fireworktax.FireworkTax;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class BoostCheck implements Listener {




    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Economy eco = FireworkTax.getEconomy();
        Player p = e.getPlayer();
        int f = FireworkTax.getFirework();
        if (p.isGliding()) {
            if (p.getInventory().getItemInMainHand().getType().equals(Material.FIREWORK_ROCKET) || p.getInventory().getItemInOffHand().getType().equals(Material.FIREWORK_ROCKET)) {
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (eco.getBalance(p) <= f-1) {
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.YELLOW + "お金がないのでブースト飛行できません");
                    } else {
                        EconomyResponse response = eco.withdrawPlayer(p, f);
                        p.sendMessage(ChatColor.YELLOW + String.format("ブーストにより%d円惹かれました",f));
                    }
                }
            }
        }
    }
}

