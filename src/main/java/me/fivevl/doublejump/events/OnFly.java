package me.fivevl.doublejump.events;

import me.fivevl.doublejump.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class OnFly implements Listener {
    @EventHandler
    public void onFly(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("doublejump.use") && Main.doDoubleJump.contains(p) && (p.getGameMode() == GameMode.ADVENTURE || p.getGameMode() == GameMode.SURVIVAL)) {
            e.setCancelled(true);
            p.setAllowFlight(false);
            p.setVelocity(p.getLocation().getDirection().multiply(2.0D));
            p.setFallDistance(0);
        }
    }
}
