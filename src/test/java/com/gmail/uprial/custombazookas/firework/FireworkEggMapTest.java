package com.gmail.uprial.custombazookas.firework;

import com.gmail.uprial.custombazookas.helpers.TestConfigBase;
import org.bukkit.Material;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class FireworkEggMapTest extends TestConfigBase {
    @Rule
    public final ExpectedException e = ExpectedException.none();

    @Test
    public void testForgottenEggs() throws Exception {
        final Set<Material> recipes = new HashSet<>();
        for(final FireworkEgg egg: new FireworkEggMap(getParanoiacCustomLogger()).getAllEggs()) {
            recipes.add(egg.getEggMaterial());
        }

        boolean foundAny = false;
        for(final Material material : Material.values()) {
            if(material.toString().toUpperCase().contains("SPAWN_EGG")) {
                foundAny = true;
                assertTrue(String.format("Not found %s in recipes", material),
                        recipes.contains(material));
            }
        }

        assertTrue("Not found any egg material", foundAny);
    }
}