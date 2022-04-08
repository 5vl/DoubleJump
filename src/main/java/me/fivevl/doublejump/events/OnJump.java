package me.fivevl.doublejump.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.fivevl.doublejump.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnJump implements Listener {
    @EventHandler
    public void onJump(PlayerJumpEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("doublejump.use") && (p.getGameMode() == GameMode.ADVENTURE || p.getGameMode() == GameMode.SURVIVAL)) {
            p.setAllowFlight(true);
            int id = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                p.setAllowFlight(false);
                Main.doDoubleJump.remove(p);
            }, 20L);
            Main.doDoubleJump.put(p, id);
        }
    }
}
