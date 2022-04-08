package me.fivevl.doublejump.events;

import me.fivevl.doublejump.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class OnFly implements Listener {
    @EventHandler
    public void onFly(PlayerToggleFlightEvent e) throws SQLException {
        Player p = e.getPlayer();
        if (p.hasPermission("doublejump.use") && Main.doDoubleJump.containsKey(p) && (p.getGameMode() == GameMode.ADVENTURE || p.getGameMode() == GameMode.SURVIVAL)) {
            e.setCancelled(true);
            p.setAllowFlight(false);
            Bukkit.getScheduler().cancelTask(Main.doDoubleJump.get(p));
            Main.doDoubleJump.remove(p);
            CachedRowSet rs = Main.getMySQL().query("SELECT * FROM `settings` WHERE `uuid` = '" + p.getUniqueId() + "'");
            rs.next();
            p.setVelocity(p.getLocation().getDirection().multiply(rs.getDouble("strength")));
            p.setFallDistance(0);
        }
    }
}
