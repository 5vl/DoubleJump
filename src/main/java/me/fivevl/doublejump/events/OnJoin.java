package me.fivevl.doublejump.events;

import me.fivevl.doublejump.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws SQLException {
        Player p = e.getPlayer();
        CachedRowSet rs = Main.getMySQL().query("SELECT * FROM `settings` WHERE `uuid` = '" + p.getUniqueId() + "'");
        if (!rs.next()) {
            Main.getMySQL().execute("INSERT INTO `settings` (`uuid`, `strength`) VALUES ('" + p.getUniqueId() + "', default)");
        }
    }
}
