package me.fivevl.doublejump;

import me.fivevl.doublejump.events.OnFly;
import me.fivevl.doublejump.events.OnJump;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    public static List<Player> doDoubleJump = new ArrayList<>();
    private static Main instance;
    public static Main getInstance() {
        return instance;
    }
    private static MySQL sql;
    public static MySQL getMySQL() {
        return sql;
    }
    @Override
    public void onEnable() {
        instance = this;
        sql = new MySQL();
        try {
            setupDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Bukkit.getPluginManager().registerEvents(new OnFly(), this);
        Bukkit.getPluginManager().registerEvents(new OnJump(), this);
        getLogger().info("DoubleJump enabled!");
    }

    @Override
    public void onDisable() {
        try {
            sql.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getLogger().info("DoubleJump disabled!");
    }

    private void setupDatabase() throws SQLException {
        ConfigurationSection config = getConfig().getConfigurationSection("database");
        if (config == null) {
            getLogger().severe("Database configuration not found!");
            return;
        }
        String[] credentials = {config.getString("host"), config.getString("port"), config.getString("database"), config.getString("username"), config.getString("password")};
        for (String s : credentials) {
            if (s == null) {
                getLogger().severe("Correct database configuration not found!");
                return;
            }
        }
        if (credentials[0].equalsIgnoreCase("12.34.56.78")) {
            getLogger().severe("Please set the database credentials in the config.yml!");
            return;
        }
        sql.setUsername(credentials[3]);
        sql.setPassword(credentials[4]);
        sql.setHost(credentials[0]);
        sql.setPort(credentials[1]);
        sql.setDatabase(credentials[2]);
        sql.connect();
        sql.execute("CREATE TABLE IF NOT EXISTS `settings` (`uuid` VARCHAR(36) NOT NULL, `strength` DOUBLE NOT NULL DEFAULT '1', PRIMARY KEY (`uuid`))");
    }
}
