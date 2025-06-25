package com.gmail.uprial.custombazookas;

import com.gmail.uprial.custombazookas.common.CustomLogger;
import com.gmail.uprial.custombazookas.config.InvalidConfigException;
import com.gmail.uprial.custombazookas.firework.FireworkEngine;
import com.gmail.uprial.custombazookas.listeners.*;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static com.gmail.uprial.custombazookas.CustomBazookasCommandExecutor.COMMAND_NS;

public final class CustomBazookas extends JavaPlugin {
    private final String CONFIG_FILE_NAME = "config.yml";
    private final File configFile = new File(getDataFolder(), CONFIG_FILE_NAME);

    private CustomLogger consoleLogger = null;

    private FireworkEngine fireworkEngine = null;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        consoleLogger = new CustomLogger(getLogger());

        fireworkEngine = new FireworkEngine(this, consoleLogger);
        register(loadConfig(getConfig(), consoleLogger));

        getServer().getPluginManager().registerEvents(new ExplosiveFireworkListener(fireworkEngine), this);

        getCommand(COMMAND_NS).setExecutor(new CustomBazookasCommandExecutor(this));
        consoleLogger.info("Plugin enabled");
    }

    private void register(final CustomBazookasConfig customBazookasConfig) {
        fireworkEngine.enableCraftBook(customBazookasConfig.getEggSystem(), customBazookasConfig.getExplosionBoosters());
    }

    private void unregister() {
        // If onEnable() didn't finish, this variable is highly probably null.
        if(fireworkEngine != null) {
            fireworkEngine.disableCraftBook();
        }
    }

    public Player getPlayerByName(final String playerName) {
        for(Player player : getServer().getOnlinePlayers()) {
            if(player.getName().equalsIgnoreCase(playerName)) {
                return player;
            }
        }

        return null;
    }

    public Recipe getRecipe(final Material material, final int amount) {
        return fireworkEngine.getRecipe(material, amount);
    }

    public boolean reloadCustomBazookasConfig(CustomLogger userLogger) {
        reloadConfig();

        final CustomBazookasConfig customBazookasConfig = loadConfig(getConfig(), userLogger, consoleLogger);
        if(customBazookasConfig != null) {
            /*
                Most other plugins will throw exceptions after a wrong configuration load,
                but this one is specifically careful because it changes player assets.
             */
            unregister();
            register(customBazookasConfig);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDisable() {
        unregister();
        HandlerList.unregisterAll(this);
        consoleLogger.info("Plugin disabled");
    }

    @Override
    public void saveDefaultConfig() {
        if (!configFile.exists()) {
            saveResource(CONFIG_FILE_NAME, false);
        }
    }

    @Override
    public FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(configFile);
    }

    static CustomBazookasConfig loadConfig(FileConfiguration config, CustomLogger customLogger) {
        return loadConfig(config, customLogger, null);
    }

    private static CustomBazookasConfig loadConfig(FileConfiguration config, CustomLogger mainLogger, CustomLogger secondLogger) {
        CustomBazookasConfig customBazookasConfig = null;

        try {
            final boolean isDebugMode = CustomBazookasConfig.isDebugMode(config, mainLogger);
            mainLogger.setDebugMode(isDebugMode);
            if(secondLogger != null) {
                secondLogger.setDebugMode(isDebugMode);
            }

            customBazookasConfig = CustomBazookasConfig.getFromConfig(config, mainLogger);
        } catch (InvalidConfigException e) {
            mainLogger.error(e.getMessage());
            if(secondLogger != null) {
                secondLogger.error(e.getMessage());
            }
        }

        return customBazookasConfig;
    }
}
