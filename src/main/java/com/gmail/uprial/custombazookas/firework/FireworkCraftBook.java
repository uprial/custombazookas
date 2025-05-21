package com.gmail.uprial.custombazookas.firework;

import com.gmail.uprial.custombazookas.CustomBazookas;
import com.gmail.uprial.custombazookas.common.CustomLogger;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FireworkCraftBook {
    final static int UNIQUE_ID_EXPLOSIVE = 0;

    private final CustomBazookas plugin;
    private final FireworkEggMap eggMap;

    private final Set<NamespacedKey> addedKeys = new HashSet<>();

    FireworkCraftBook(final CustomBazookas plugin,
                      final CustomLogger customLogger) {
        this.plugin = plugin;

        eggMap = new FireworkEggMap(customLogger);

        if(eggMap.containsUniqueId(UNIQUE_ID_EXPLOSIVE)) {
            throw new FireworkError("Duplicate UNIQUE_ID_EXPLOSIVE");
        }
    }

    void enable(final FireworkBooster eggSystem, final List<FireworkBooster> explosionBoosters) {
        for(int i = 1; i <= FireworkBooster.MAX_BOOSTERS; i++) {
            for(final FireworkBooster fireworkBooster : explosionBoosters) {
                addExplosionRecipe(fireworkBooster.getMaterial(), i,
                        fireworkBooster.getEffect(),
                        fireworkBooster.getDurationPerItem() * i,
                        fireworkBooster.getPowerPerItem() * i);
            }

            for(FireworkEgg egg : eggMap.getAllEggs()) {
                addEggRecipe(egg.getEggMaterial(), i,
                        eggSystem.getEffect(),
                        eggSystem.getDurationPerItem() * i,
                        egg.getEntityType(),
                        eggSystem.getPowerPerItem() * i);
            }
        }
    }

    void disable() {
        for(NamespacedKey key : addedKeys) {
            plugin.getServer().removeRecipe(key);
        }
        addedKeys.clear();
    }

    boolean isExplosive(final int uniqueId) {
        return (UNIQUE_ID_EXPLOSIVE == uniqueId);
    }

    boolean isEgg(final int uniqueId) {
        return eggMap.containsUniqueId(uniqueId);
    }

    EntityType getEntityTypeByUniqueId(final int uniqueId) {
        return eggMap.getEntityTypeByUniqueId(uniqueId);
    }

    private void addEggRecipe(
            final Material material,
            final int amount,
            final FireworkEffect.Type type,
            final int duration,
            final EntityType entityType,
            final int entityAmount) {

        final ItemStack itemStack = new ItemStack(Material.FIREWORK_ROCKET);
        enchant(itemStack, type, duration,
                eggMap.getUniqueIdByEntityType(entityType),
                entityAmount,
                String.format("Spawns: %d x %s", entityAmount, entityType));

        addRecipe(material, amount, itemStack);
    }

    private void addExplosionRecipe(
            final Material material,
            final int amount,
            final FireworkEffect.Type type,
            final int duration,
            final int explosionPower) {

        final ItemStack itemStack = new ItemStack(Material.FIREWORK_ROCKET);
        enchant(itemStack, type, duration,
                UNIQUE_ID_EXPLOSIVE,
                explosionPower,
                String.format("Explosion power: %d", explosionPower));

        addRecipe(material, amount, itemStack);
    }

    private void enchant(
            final ItemStack itemStack,
            final FireworkEffect.Type effect,
            final int duration,
            final int type,
            final int amount,
            final String description) {

        final FireworkMeta fireworkMeta = (FireworkMeta) itemStack.getItemMeta();

        fireworkMeta.addEffect(FireworkEffect.builder()
                .with(effect)
                .withColor(FireworkMagicColor.encode(new FireworkMagic(FireworkMagicCode.TYPE, type)))
                .withColor(FireworkMagicColor.encode(new FireworkMagic(FireworkMagicCode.AMOUNT, amount)))
                .build());
        fireworkMeta.setPower(duration);

        fireworkMeta.setLore(Collections.singletonList(description));

        itemStack.setItemMeta(fireworkMeta);

        /*
            The only purpose of this enhancement is
            to create a visual effect
            to differentiate fireworks and bazookas.
         */
        itemStack.addUnsafeEnchantment(Enchantment.FLAME,  1);
    }

    private void addRecipe(
            final Material material,
            final int amount,
            final ItemStack itemStack) {

        final String key = String.format("e-f-%s-%d", material.toString().toLowerCase(), amount);
        final NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        final ShapelessRecipe recipe = new ShapelessRecipe(namespacedKey, itemStack);

        recipe.addIngredient(1, Material.FIREWORK_ROCKET);
        recipe.addIngredient(amount, material);

        plugin.getServer().addRecipe(recipe);
        addedKeys.add(namespacedKey);
    }
}