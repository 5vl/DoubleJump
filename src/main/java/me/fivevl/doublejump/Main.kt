package me.fivevl.doublejump

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        logger.info("DoubleJump enabled!")
    }
    override fun onDisable() {
        logger.info("DoubleJump disabled!")
    }
}