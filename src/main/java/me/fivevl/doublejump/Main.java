package me.fivevl.doublejump;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("DoubleJump enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("DoubleJump disabled!");
    }
}
