package com.gmail.uprial.custombazookas.listeners;

import com.gmail.uprial.custombazookas.firework.FireworkEngine;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ExplosiveFireworkListener implements Listener {
    private final FireworkEngine fireworkEngine;

    public ExplosiveFireworkListener(FireworkEngine fireworkEngine) {
        this.fireworkEngine = fireworkEngine;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onFireworkLaunch(ProjectileLaunchEvent event) {
        if(!event.isCancelled()
                && (event.getEntity() instanceof Firework)
                && (event.getEntity().getShooter() instanceof Entity)) {
            fireworkEngine.onLaunch(
                    (Firework)event.getEntity(),
                    (Entity)event.getEntity().getShooter());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onFireworkExplode(FireworkExplodeEvent event) {
        if(!event.isCancelled()) {
            fireworkEngine.onExplode(event.getEntity());
        }
    }
}