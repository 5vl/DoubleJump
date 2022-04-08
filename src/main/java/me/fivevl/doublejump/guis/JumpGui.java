package me.fivevl.doublejump.guis;

import me.fivevl.doublejump.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.Collections;

public class JumpGui {
    @SuppressWarnings("deprecation")
    public static Inventory getGui(Player p) throws SQLException {
        Inventory inv = Bukkit.createInventory(null, 27, Main.color("&8Jump Strength Menu"));
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, glass);
        }

        ItemStack decreaseStrength = new ItemStack(Material.ARROW, 1);
        ItemMeta decreaseStrengthMeta = decreaseStrength.getItemMeta();
        decreaseStrengthMeta.setDisplayName(Main.color("&9Decrease Strength"));
        decreaseStrength.setItemMeta(decreaseStrengthMeta);
        inv.setItem(12, decreaseStrength);

        ItemStack increaseStrength = new ItemStack(Material.ARROW, 1);
        ItemMeta increaseStrengthMeta = increaseStrength.getItemMeta();
        increaseStrengthMeta.setDisplayName(Main.color("&9Increase Strength"));
        increaseStrength.setItemMeta(increaseStrengthMeta);
        inv.setItem(14, increaseStrength);


        CachedRowSet rs = Main.getMySQL().query("SELECT * FROM `settings` WHERE `uuid` = '" + p.getUniqueId() + "'");
        if (rs.next()) {
            ItemStack currentStrength = new ItemStack(Material.FEATHER, 1);
            ItemMeta currentStrengthMeta = currentStrength.getItemMeta();
            currentStrengthMeta.setDisplayName(Main.color("&2Current Jump Strength"));
            currentStrengthMeta.setLore(Collections.singletonList(Main.color("&e" + rs.getInt("strength"))));
            currentStrength.setItemMeta(currentStrengthMeta);
            inv.setItem(13, currentStrength);
        }

        return inv;
    }
}
