package com.gmail.uprial.custombazookas;

import com.gmail.uprial.custombazookas.common.CustomLogger;
import com.gmail.uprial.custombazookas.config.ConfigReaderSimple;
import com.gmail.uprial.custombazookas.config.InvalidConfigException;
import com.gmail.uprial.custombazookas.firework.FireworkBooster;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.gmail.uprial.custombazookas.config.ConfigReaderEnums.getSet;

public final class CustomBazookasConfig {
    private final FireworkBooster eggSystem;
    private final List<FireworkBooster> explosionBoosters;

    private CustomBazookasConfig(FireworkBooster eggSystem,
                                 List<FireworkBooster> explosionBoosters) {
        this.eggSystem = eggSystem;
        this.explosionBoosters = explosionBoosters;
    }

    public FireworkBooster getEggSystem() {
        return eggSystem;
    }

    public List<FireworkBooster> getExplosionBoosters() {
        return explosionBoosters;
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

        return new CustomBazookasConfig(eggSystem, explosionBoosters);
    }

    @Override
    public String toString() {
        return String.format("EGG-SYSTEM: %s, explosion-boosters: %s",
                eggSystem, explosionBoosters);
    }
}