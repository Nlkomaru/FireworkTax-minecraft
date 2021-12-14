package dev.nikomaru.fireworktax.tax;

import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import dev.nikomaru.fireworktax.api.VaultAPI;
import dev.nikomaru.fireworktax.files.Config;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BoostCheck implements Listener {

    HashMap<UUID, Float> total = new HashMap<> ();
    HashMap<UUID, Integer> d = new HashMap<> ();

    @EventHandler public void onPlayerBoost (PlayerElytraBoostEvent e) {
        Economy eco = VaultAPI.getEconomy ();
        Player p = e.getPlayer ();
        UUID uuid = e.getPlayer ().getUniqueId ();
        double f = Config.getFirework ();
        int c = Config.getCount ();

        if (total.get (uuid) == null || d.get (uuid) == null) {
            d.put (uuid, 0);
            total.put (uuid, (float) 0);
        }

        if (Objects.requireNonNull (eco).getBalance (p) < f) {
            e.setCancelled (true);
            p.sendMessage (ChatColor.YELLOW + "お金がないのでブースト飛行できません");
        } else {
            eco.withdrawPlayer (p, f);
            if (c > 0) {
                total.put (uuid, (float) (total.get (uuid) + f));
                d.put (uuid, d.get (uuid) + 1);
                if (d.get (uuid) >= c) {
                    p.sendMessage (ChatColor.YELLOW + String.format ("%d回ブーストして合計で%.2f円引かれました", d.get (uuid),
                            total.get (uuid)));
                    d.put (uuid, 0);
                    total.put (uuid, (float) 0);
                }
            }
        }
    }
}
