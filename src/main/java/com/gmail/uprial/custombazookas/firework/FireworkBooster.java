package com.gmail.uprial.custombazookas.firework;

import com.gmail.uprial.custombazookas.common.CustomLogger;
import com.gmail.uprial.custombazookas.config.ConfigReaderEnums;
import com.gmail.uprial.custombazookas.config.ConfigReaderNumbers;
import com.gmail.uprial.custombazookas.config.InvalidConfigException;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import static com.gmail.uprial.custombazookas.common.Utils.joinPaths;

public class FireworkBooster {
    private final Material material;
    private final FireworkEffect.Type effect;
    private final int durationPerItem;
    private final int powerPerItem;

    static final int MAX_BOOSTERS = 8;

    /*
        According to https://hub.spigotmc.org/javadocs/spigot/org/bukkit/inventory/meta/FireworkMeta.html,
        the power of the firework can be 0-255.
     */
    private static final int MAX_DURATION_PER_BOOSTER
            = 255 / MAX_BOOSTERS;

    private static final int MAX_POWER_PER_BOOSTER
            = FireworkMagicColor.getMaxValue() / MAX_BOOSTERS;

    private FireworkBooster(final Material material,
                            final FireworkEffect.Type effect,
                            final int durationPerItem,
                            final int powerPerItem) {
        this.material = material;
        this.effect = effect;
        this.durationPerItem = durationPerItem;
        this.powerPerItem = powerPerItem;
    }

    Material getMaterial() {
        return material;
    }

    FireworkEffect.Type getEffect() {
        return effect;
    }

    int getDurationPerItem() {
        return durationPerItem;
    }

    int getPowerPerItem() {
        return powerPerItem;
    }

    public static FireworkBooster getFromConfig(FileConfiguration config, CustomLogger customLogger, Material material, String key, String title) throws InvalidConfigException {
        if (config.get(key) == null) {
            throw new InvalidConfigException(String.format("Empty %s", title));
        }

        FireworkEffect.Type effect = ConfigReaderEnums.getEnum(FireworkEffect.Type.class, config,
                joinPaths(key, "effect"), String.format("effect of %s", title));
        int durationPerItem = ConfigReaderNumbers.getInt(config, customLogger,
                joinPaths(key, "duration-per-item"), String.format("duration per item of %s", title), 1, MAX_DURATION_PER_BOOSTER);
        int powerPerItem = ConfigReaderNumbers.getInt(config, customLogger,
                joinPaths(key, "power-per-item"), String.format("power per item of %s", title), 1, MAX_POWER_PER_BOOSTER);

        return new FireworkBooster(material, effect, durationPerItem, powerPerItem);
    }

    public String toString() {
        return String.format("{material: %s, effect: %s, duration-per-item: %d, power-per-item: %d}",
                material, effect, durationPerItem, powerPerItem);
    }
}
