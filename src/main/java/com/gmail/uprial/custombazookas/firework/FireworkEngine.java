package com.gmail.uprial.custombazookas.firework;

import com.gmail.uprial.custombazookas.CustomBazookas;
import com.gmail.uprial.custombazookas.common.CustomLogger;
import com.gmail.uprial.custombazookas.common.Nuke;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.Recipe;

import java.util.*;
import java.util.function.Consumer;

import static com.gmail.uprial.custombazookas.common.Formatter.format;

public class FireworkEngine {
    private final CustomBazookas plugin;
    private final CustomLogger customLogger;

    private final FireworkCraftBook craftBook;

    public FireworkEngine(final CustomBazookas plugin, final CustomLogger customLogger) {
        this.plugin = plugin;
        this.customLogger = customLogger;

        craftBook = new FireworkCraftBook(plugin, customLogger);
    }

    public void enableCraftBook(final FireworkBooster eggSystem, final List<FireworkBooster> explosionBoosters) {
        craftBook.enable(eggSystem, explosionBoosters);
    }

    public void disableCraftBook() {
        craftBook.disable();
    }

    public Recipe getRecipe(final Material material, final int amount) {
         return craftBook.getRecipe(material, amount);
    }

    public void onExplode(final Firework firework) {
        for(final FireworkEffect effect : firework.getFireworkMeta().getEffects()) {
            Integer type = null;
            Integer amount = null;
            for (final Color color : effect.getColors()) {
                final FireworkMagic magic = FireworkMagicColor.decode(color);
                if (magic != null) {
                    if(magic.getCode() == FireworkMagicCode.TYPE) {
                        type = magic.getValue();
                    } else if (magic.getCode() == FireworkMagicCode.AMOUNT) {
                        amount = magic.getValue();
                    /// } else { -- no way, see FireworkMagicCode
                    }
                }
            }

            if((type != null) && (amount != null)) {
                trigger(firework, type, amount);
            } else if (type != null) {
                customLogger.warning(String.format("Firework at %s has only magic type: %d",
                        format(firework.getLocation()), type));
            } else if (amount != null) {
                customLogger.warning(String.format("Firework at %s has only magic amount: %d",
                        format(firework.getLocation()), amount));
            } else if (customLogger.isDebugMode()) {
                customLogger.debug(String.format("Firework at %s has no magic",
                        format(firework.getLocation())));
            }
        }
    }

    private void trigger(final Firework firework, final int type, final int amount) {
        if(craftBook.isExplosive(type)) {
            explode(firework, amount);
        /*
            The following comparison mostly improves code readability
            and might be replaced to (getEntityTypeByUniqueId(type) == null)
            if it had an internal nullability check.
         */
        } else if (craftBook.isEgg(type)) {
            spawn(firework, craftBook.getEntityTypeByUniqueId(type), amount);
        } else {
            customLogger.warning(String.format("Firework at %s has unrecognized magic %d",
                    format(firework.getLocation()), type));
        }
    }

    private void explode(final Firework firework, final int explosionPower) {
        final Entity source = firework.getShooter() instanceof Entity ? (Entity)firework.getShooter() : null;
        if (explosionPower <= Nuke.MAX_ENGINE_POWER) {
            firework.getWorld().createExplosion(firework.getLocation(), explosionPower, true, true, source);
            if (customLogger.isDebugMode()) {
                customLogger.debug(String.format("Firework exploded at %s with power %d",
                        format(firework.getLocation()), explosionPower));
            }
        } else {
            final String fireworkId = getUniqueId();
            new Nuke(plugin).explode(firework.getLocation(), source, explosionPower,
                    1, () -> 2,
                    (final Long time) -> {
                        if (customLogger.isDebugMode()) {
                            customLogger.debug(String.format("Firework %s explosion took %,d ms.",
                                    fireworkId, time));
                        }
                    });
            customLogger.info(String.format("Firework %s exploded at %s with power %d",
                    fireworkId, format(firework.getLocation()), explosionPower));
        }
    }

    private void spawn(final Firework firework, final EntityType entityType, final int entityAmount) {
        final String fireworkId = getUniqueId();
        schedule(() -> {
            for (int i = 0; i < entityAmount; i++) {
                firework.getWorld().spawnEntity(firework.getLocation(), entityType);
            }
        }, 1, (final Long time) -> {
            if (customLogger.isDebugMode()) {
                customLogger.debug(String.format("Firework %s explosion took %,d ms.",
                        fireworkId, time));
            }
        });

        if (customLogger.isDebugMode()) {
            customLogger.debug(String.format("Firework %s exploded at %s with %d x %s",
                    fireworkId, format(firework.getLocation()), entityAmount, entityType));
        }
    }

    private void schedule(final Runnable task, final long delay, final Consumer<Long> callback) {
        final long start = System.currentTimeMillis();
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            task.run();
            final long end = System.currentTimeMillis();
            callback.accept(end - start);
        }, delay);
    }

    private String getUniqueId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
