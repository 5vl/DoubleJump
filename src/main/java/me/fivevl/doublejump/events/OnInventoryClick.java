package me.fivevl.doublejump.events;

import me.fivevl.doublejump.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.sql.rowset.CachedRowSet;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;

public class OnInventoryClick implements Listener {
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onClick(InventoryClickEvent e) throws SQLException {
        Player p = (Player) e.getWhoClicked();
        if (!Main.inGui.contains(p)) return;
        e.setCancelled(true);
        CachedRowSet rs = Main.getMySQL().query("SELECT * FROM `settings` WHERE `uuid` = '" + p.getUniqueId() + "'");
        rs.next();
        int strength = rs.getInt("strength");
        int newStrength = strength;
        if (e.getRawSlot() == 12) newStrength = strength - 1;
        if (e.getRawSlot() == 14) newStrength = strength + 1;
        if (newStrength < 1) newStrength = 1;
        if (newStrength > 5) newStrength = 5;
        if (strength == newStrength) return;
        Inventory inv = e.getClickedInventory();
        ItemStack item = inv.getItem(13);
        ItemMeta currentStrengthMeta = item.getItemMeta();
        currentStrengthMeta.setLore(Collections.singletonList(Main.color("&e" + newStrength)));
        item.setItemMeta(currentStrengthMeta);
        inv.setItem(13, item);
        Main.getMySQL().execute("UPDATE `settings` SET `strength` = '" + newStrength + "' WHERE `uuid` = '" + p.getUniqueId() + "'");
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Main.inGui.remove((Player) e.getPlayer());
    }
}
