package com.gmail.uprial.custombazookas;

import com.gmail.uprial.custombazookas.common.CustomLogger;
import com.gmail.uprial.custombazookas.common.Nuke;
import com.gmail.uprial.custombazookas.config.ConfigReaderNumbers;
import com.gmail.uprial.custombazookas.config.ConfigReaderSimple;
import com.gmail.uprial.custombazookas.config.InvalidConfigException;
import com.gmail.uprial.custombazookas.firework.FireworkBooster;
import com.gmail.uprial.custombazookas.firework.FireworkEngine;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.gmail.uprial.custombazookas.config.ConfigReaderEnums.getSet;

public final class CustomBazookasConfig {
    private final FireworkBooster eggSystem;
    private final List<FireworkBooster> explosionBoosters;
    private final int nukeBroadcastThreshold;

    private CustomBazookasConfig(final FireworkBooster eggSystem,
                                 final List<FireworkBooster> explosionBoosters,
                                 final int nukeBroadcastThreshold) {
        this.eggSystem = eggSystem;
        this.explosionBoosters = explosionBoosters;
        this.nukeBroadcastThreshold = nukeBroadcastThreshold;
    }

    public FireworkBooster getEggSystem() {
        return eggSystem;
    }

    public List<FireworkBooster> getExplosionBoosters() {
        return explosionBoosters;
    }

    public int getNukeBroadcastThreshold() {
        return nukeBroadcastThreshold;
    }

    static boolean isDebugMode(FileConfiguration config, CustomLogger customLogger) throws InvalidConfigException {
        return ConfigReaderSimple.getBoolean(config, customLogger, "debug", "'debug' flag", false);
    }

    public static CustomBazookasConfig getFromConfig(FileConfiguration config, CustomLogger customLogger) throws InvalidConfigException {
        FireworkBooster eggSystem = FireworkBooster.getFromConfig(config, customLogger,
                null, "EGG-SYSTEM", "'EGG-SYSTEM'");

        if(config.getList("explosion-boosters") == null) {
            throw new InvalidConfigException("Empty 'explosion-boosters'");
        }

        Set<Material> materials = getSet(Material.class, config, customLogger,
                "explosion-boosters", "'explosion-boosters'");

        List<FireworkBooster> explosionBoosters = new ArrayList<>();
        for(Material material : materials) {
            explosionBoosters.add(FireworkBooster.getFromConfig(config, customLogger,
                    material, material.toString(), String.format("'%s' explosion booster", material)));
        }

        int nukeBroadcastThreshold = ConfigReaderNumbers.getInt(config, customLogger,
                "nuke-broadcast-threshold", "'nuke-broadcast-threshold'", (int)Nuke.MAX_ENGINE_POWER + 1, FireworkEngine.MAX_POWER);

        return new CustomBazookasConfig(eggSystem, explosionBoosters, nukeBroadcastThreshold);
    }

    @Override
    public String toString() {
        return String.format("EGG-SYSTEM: %s, explosion-boosters: %s, nuke-broadcast-threshold: %d",
                eggSystem, explosionBoosters, nukeBroadcastThreshold);
    }
}