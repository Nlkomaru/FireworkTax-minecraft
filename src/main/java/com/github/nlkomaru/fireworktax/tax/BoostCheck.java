package com.github.nlkomaru.fireworktax.tax;


import com.github.nlkomaru.fireworktax.FireworkTax;

import com.github.nlkomaru.fireworktax.files.Customconfig;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;


public class BoostCheck implements Listener {

    HashMap<Player, Float> total = new HashMap<>();
    HashMap<Player, Integer> d = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        d.put(e.getPlayer(), 0);
        total.put(e.getPlayer(), (float) 0);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Economy eco = FireworkTax.getEconomy();
        Player p = e.getPlayer();
        float f = Customconfig.getFirework();
        int c = Customconfig.getCount();

        if (p.isGliding()) {
            if (p.getInventory().getItemInMainHand().getType().equals(Material.FIREWORK_ROCKET) || p.getInventory().getItemInOffHand().getType().equals(Material.FIREWORK_ROCKET)) {
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (eco.getBalance(p) < f) {
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.YELLOW + "お金がないのでブースト飛行できません");
                    } else {
                        EconomyResponse response = eco.withdrawPlayer(p, f);
                        if (c != 0) {
                            total.put(p, total.get(p) + f);
                            d.put(p, d.get(p) + 1);
                            if (d.get(p) >= c) {
                                p.sendMessage(ChatColor.YELLOW + String.format("%d回ブーストして合計で%.2f円引かれました", d.get(p), total.get(p)));
                                d.put(p, 0);
                                total.put(p, (float) 0);
                            }
                        }
                    }
                }
            }
        }
    }
}
