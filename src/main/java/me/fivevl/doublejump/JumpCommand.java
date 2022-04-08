package me.fivevl.doublejump;

import me.fivevl.doublejump.guis.JumpGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.sql.SQLException;

public class JumpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.color("&cOnly players can use this command!"));
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("doublejump.gui")) {
            p.sendMessage(Main.color("You don't have permission to use this command!"));
            return true;
        }
        try {
            p.openInventory(JumpGui.getGui(p));
            Main.inGui.add(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
