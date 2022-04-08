package me.fivevl.doublejump.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class JumpGui {
    @SuppressWarnings("deprecation")
    public static Inventory getGui(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "Jump Strength");

        return inv;
    }
}
