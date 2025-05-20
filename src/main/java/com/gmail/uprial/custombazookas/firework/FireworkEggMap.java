package com.gmail.uprial.custombazookas.firework;

import com.gmail.uprial.custombazookas.common.CustomLogger;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FireworkEggMap {
    private final Map<Integer, FireworkEgg> uniqueId2egg = new HashMap<>();
    private final Map<EntityType, FireworkEgg> entityType2egg = new HashMap<>();

    FireworkEggMap(final CustomLogger customLogger) {
        put(1, Material.ARMADILLO_SPAWN_EGG, EntityType.ARMADILLO);
        put(2, Material.ALLAY_SPAWN_EGG, EntityType.ALLAY);
        put(3, Material.AXOLOTL_SPAWN_EGG, EntityType.AXOLOTL);
        put(4, Material.BAT_SPAWN_EGG, EntityType.BAT);
        put(5, Material.BEE_SPAWN_EGG, EntityType.BEE);
        put(6, Material.BLAZE_SPAWN_EGG, EntityType.BLAZE);
        put(7, Material.BOGGED_SPAWN_EGG, EntityType.BOGGED);
        put(8, Material.BREEZE_SPAWN_EGG, EntityType.BREEZE);
        put(9, Material.CAT_SPAWN_EGG, EntityType.CAT);
        put(10, Material.CAMEL_SPAWN_EGG, EntityType.CAMEL);
        put(11, Material.CAVE_SPIDER_SPAWN_EGG, EntityType.CAVE_SPIDER);
        put(12, Material.CHICKEN_SPAWN_EGG, EntityType.CHICKEN);
        put(13, Material.COD_SPAWN_EGG, EntityType.COD);
        put(14, Material.COW_SPAWN_EGG, EntityType.COW);
        put(15, Material.CREEPER_SPAWN_EGG, EntityType.CREEPER);
        put(16, Material.DOLPHIN_SPAWN_EGG, EntityType.DOLPHIN);
        put(17, Material.DONKEY_SPAWN_EGG, EntityType.DONKEY);
        put(18, Material.DROWNED_SPAWN_EGG, EntityType.DROWNED);
        put(19, Material.ELDER_GUARDIAN_SPAWN_EGG, EntityType.ELDER_GUARDIAN);
        put(20, Material.ENDER_DRAGON_SPAWN_EGG, EntityType.ENDER_DRAGON);
        put(21, Material.ENDERMAN_SPAWN_EGG, EntityType.ENDERMAN);
        put(22, Material.ENDERMITE_SPAWN_EGG, EntityType.ENDERMITE);
        put(23, Material.EVOKER_SPAWN_EGG, EntityType.EVOKER);
        put(24, Material.FOX_SPAWN_EGG, EntityType.FOX);
        put(25, Material.FROG_SPAWN_EGG, EntityType.FROG);
        put(26, Material.GHAST_SPAWN_EGG, EntityType.GHAST);
        put(27, Material.GLOW_SQUID_SPAWN_EGG, EntityType.GLOW_SQUID);
        put(28, Material.GOAT_SPAWN_EGG, EntityType.GOAT);
        put(29, Material.GUARDIAN_SPAWN_EGG, EntityType.GUARDIAN);
        put(30, Material.HOGLIN_SPAWN_EGG, EntityType.HOGLIN);
        put(31, Material.HORSE_SPAWN_EGG, EntityType.HORSE);
        put(32, Material.HUSK_SPAWN_EGG, EntityType.HUSK);
        put(33, Material.IRON_GOLEM_SPAWN_EGG, EntityType.IRON_GOLEM);
        put(34, Material.LLAMA_SPAWN_EGG, EntityType.LLAMA);
        put(35, Material.MAGMA_CUBE_SPAWN_EGG, EntityType.MAGMA_CUBE);
        put(36, Material.MOOSHROOM_SPAWN_EGG, EntityType.MOOSHROOM);
        put(37, Material.MULE_SPAWN_EGG, EntityType.MULE);
        put(38, Material.OCELOT_SPAWN_EGG, EntityType.OCELOT);
        put(39, Material.PANDA_SPAWN_EGG, EntityType.PANDA);
        put(40, Material.PARROT_SPAWN_EGG, EntityType.PARROT);
        put(41, Material.PHANTOM_SPAWN_EGG, EntityType.PHANTOM);
        put(42, Material.PIG_SPAWN_EGG, EntityType.PIG);
        put(43, Material.PIGLIN_SPAWN_EGG, EntityType.PIGLIN);
        put(44, Material.PIGLIN_BRUTE_SPAWN_EGG, EntityType.PIGLIN_BRUTE);
        put(45, Material.PILLAGER_SPAWN_EGG, EntityType.PILLAGER);
        put(46, Material.POLAR_BEAR_SPAWN_EGG, EntityType.POLAR_BEAR);
        put(47, Material.PUFFERFISH_SPAWN_EGG, EntityType.PUFFERFISH);
        put(48, Material.RABBIT_SPAWN_EGG, EntityType.RABBIT);
        put(49, Material.RAVAGER_SPAWN_EGG, EntityType.RAVAGER);
        put(50, Material.SALMON_SPAWN_EGG, EntityType.SALMON);
        put(51, Material.SHEEP_SPAWN_EGG, EntityType.SHEEP);
        put(52, Material.SHULKER_SPAWN_EGG, EntityType.SHULKER);
        put(53, Material.SILVERFISH_SPAWN_EGG, EntityType.SILVERFISH);
        put(54, Material.SKELETON_SPAWN_EGG, EntityType.SKELETON);
        put(55, Material.SKELETON_HORSE_SPAWN_EGG, EntityType.SKELETON_HORSE);
        put(56, Material.SLIME_SPAWN_EGG, EntityType.SLIME);
        put(57, Material.SNIFFER_SPAWN_EGG, EntityType.SNIFFER);
        put(58, Material.SNOW_GOLEM_SPAWN_EGG, EntityType.SNOW_GOLEM);
        put(59, Material.SPIDER_SPAWN_EGG, EntityType.SPIDER);
        put(60, Material.SQUID_SPAWN_EGG, EntityType.SQUID);
        put(61, Material.STRAY_SPAWN_EGG, EntityType.STRAY);
        put(62, Material.STRIDER_SPAWN_EGG, EntityType.STRIDER);
        put(63, Material.TADPOLE_SPAWN_EGG, EntityType.TADPOLE);
        put(64, Material.TRADER_LLAMA_SPAWN_EGG, EntityType.TRADER_LLAMA);
        put(65, Material.TROPICAL_FISH_SPAWN_EGG, EntityType.TROPICAL_FISH);
        put(66, Material.TURTLE_SPAWN_EGG, EntityType.TURTLE);
        put(67, Material.VEX_SPAWN_EGG, EntityType.VEX);
        put(68 ,Material.VILLAGER_SPAWN_EGG, EntityType.VILLAGER);
        put(69, Material.VINDICATOR_SPAWN_EGG, EntityType.VINDICATOR);
        put(70, Material.WANDERING_TRADER_SPAWN_EGG, EntityType.WANDERING_TRADER);
        put(71, Material.WARDEN_SPAWN_EGG, EntityType.WARDEN);
        put(72, Material.WITCH_SPAWN_EGG, EntityType.WITCH);
        put(73, Material.WITHER_SPAWN_EGG, EntityType.WITHER);
        put(74, Material.WITHER_SKELETON_SPAWN_EGG, EntityType.WITHER_SKELETON);
        put(75, Material.WOLF_SPAWN_EGG, EntityType.WOLF);
        put(76, Material.ZOGLIN_SPAWN_EGG, EntityType.ZOGLIN);
        put(77, Material.ZOMBIE_SPAWN_EGG, EntityType.ZOMBIE);
        put(78, Material.ZOMBIE_HORSE_SPAWN_EGG, EntityType.ZOMBIE_HORSE);
        put(79, Material.ZOMBIE_VILLAGER_SPAWN_EGG, EntityType.ZOMBIE_VILLAGER);
        put(80, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG, EntityType.ZOMBIFIED_PIGLIN);

        try {
            put(81, Material.CREAKING_SPAWN_EGG, EntityType.CREAKING);
        } catch (NoSuchFieldError ignored) {
            customLogger.info("Version < 1.21.2," +
                    " EntityType.CREAKING not found," +
                    " skipping related recipes");
        }
    }

    private void put(final int uniqueId, final Material eggMaterial, final EntityType entityType) {
        final FireworkEgg egg = new FireworkEgg(uniqueId, eggMaterial, entityType);

        putItem(uniqueId2egg, egg.getUniqueId(), egg);
        putItem(entityType2egg, egg.getEntityType(), egg);
    }

    EntityType getEntityTypeByUniqueId(final int uniqueId) {
        return uniqueId2egg.containsKey(uniqueId)
                ? uniqueId2egg.get(uniqueId).getEntityType()
                : null;
    }

    Integer getUniqueIdByEntityType(final EntityType entityType) {
        return entityType2egg.containsKey(entityType)
                ? entityType2egg.get(entityType).getUniqueId()
                : null;
    }

    Collection<FireworkEgg> getAllEggs() {
        return uniqueId2egg.values();
    }

    private <T> void putItem(final Map<T, FireworkEgg> map, final T key, final FireworkEgg egg) {
        if(map.containsKey(key)) {
            throw new FireworkError(String.format("Duplicate %s key: %s", egg, key));
        }
        map.put(key, egg);
    }
}
