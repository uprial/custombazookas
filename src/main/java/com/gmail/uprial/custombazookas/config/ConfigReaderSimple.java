package com.gmail.uprial.custombazookas.config;

import com.gmail.uprial.custombazookas.common.CustomLogger;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public final class ConfigReaderSimple {
    public static String getString(FileConfiguration config, String key, String title) throws InvalidConfigException {
        String string = config.getString(key);

        if(string == null) {
            throw new InvalidConfigException(String.format("Null %s", title));
        }
        if(string.length() < 1) {
            throw new InvalidConfigException(String.format("Empty %s", title));
        }

        return string;
    }

    public static List<String> getStringList(FileConfiguration config, CustomLogger customLogger, String key, String title) {
        List<?> lines = config.getList(key);
        if(lines != null) {
            List<String> strings = new ArrayList<>();
            for (Object line : lines) {
                strings.add(line.toString());
            }

            return strings;
        } else {
            customLogger.debug(String.format("Empty %s. Use default value NULL", title));
            return null;
        }
    }

    public static boolean getBoolean(FileConfiguration config, CustomLogger customLogger, String key, String title, boolean defaultValue) throws InvalidConfigException {
        return getBooleanInternal(config, customLogger, key, title, defaultValue);
    }

    public static boolean getBoolean(FileConfiguration config, CustomLogger customLogger, String key, String title) throws InvalidConfigException {
        return getBooleanInternal(config, customLogger, key, title, null);
    }

    private static boolean getBooleanInternal(FileConfiguration config, CustomLogger customLogger, String key, String title, Boolean defaultValue) throws InvalidConfigException {
        String strValue = config.getString(key);

        if(strValue == null) {
            if (defaultValue == null) {
                throw new InvalidConfigException(String.format("Empty %s", title));
            } else {
                customLogger.debug(String.format("Empty %s. Use default value %b", title, defaultValue));
                return defaultValue;
            }
        } else if(strValue.equalsIgnoreCase("true")) {
            return true;
        } else if(strValue.equalsIgnoreCase("false")) {
            return false;
        } else {
            throw new InvalidConfigException(String.format("Invalid %s", title));
        }
    }
}
