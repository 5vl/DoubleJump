package me.fivevl.doublejump;

import me.fivevl.doublejump.guis.JumpGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JumpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("doublejump.gui")) {
            p.sendMessage("You don't have permission to use this command!");
            return true;
        }
        p.openInventory(JumpGui.getGui(p));
        Main.inGui.add(p);
        return true;
    }
}
