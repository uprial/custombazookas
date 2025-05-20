package com.gmail.uprial.custombazookas.firework;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class FireworkEgg {
    private final Integer uniqueId;
    private final Material eggMaterial;
    private final EntityType entityType;

    FireworkEgg(final Integer uniqueId,
                final Material eggMaterial,
                final EntityType entityType) {
        this.uniqueId = uniqueId;
        this.eggMaterial = eggMaterial;
        this.entityType = entityType;
    }

    Integer getUniqueId() {
        return uniqueId;
    }

    Material getEggMaterial() {
        return eggMaterial;
    }

    EntityType getEntityType() {
        return entityType;
    }

    @Override
    public String toString() {
        return String.format("FireworkEgg{%d:%s->%s}", uniqueId, eggMaterial, entityType);
    }
}
