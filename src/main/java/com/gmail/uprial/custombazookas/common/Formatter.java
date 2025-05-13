package com.gmail.uprial.custombazookas.common;

import org.bukkit.Location;

public class Formatter {
    public static String format(final Location location) {
        return String.format("%s:%.0f:%.0f:%.0f",
                (location.getWorld() != null) ? location.getWorld().getName() : "empty",
                location.getX(), location.getY(), location.getZ());
    }
}
