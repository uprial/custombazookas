package com.gmail.uprial.custombazookas;

import com.gmail.uprial.custombazookas.config.InvalidConfigException;
import com.gmail.uprial.custombazookas.helpers.TestConfigBase;
import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CustomBazookasConfigTest extends TestConfigBase {
    @Rule
    public final ExpectedException e = ExpectedException.none();

    @Test
    public void testEmptyDebug() throws Exception {
        e.expect(RuntimeException.class);
        e.expectMessage("Empty 'debug' flag. Use default value false");
        CustomBazookasConfig.isDebugMode(getPreparedConfig(""), getDebugFearingCustomLogger());
    }

    @Test
    public void testNormalDebug() throws Exception {
        assertTrue(CustomBazookasConfig.isDebugMode(getPreparedConfig("debug: true"), getDebugFearingCustomLogger()));
    }

    @Test
    public void testEmpty() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("Empty 'EGG-SYSTEM'");
        loadConfig(getDebugFearingCustomLogger(), "");
    }

    @Test
    public void testNotMap() throws Exception {
        e.expect(InvalidConfigurationException.class);
        e.expectMessage("Top level is not a Map.");
        loadConfig("x");
    }

    @Test
    public void testEmptyExplosionBoosters() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("Empty 'explosion-boosters'");
        loadConfig(getDebugFearingCustomLogger(), "debug: false",
                "EGG-SYSTEM:",
                "  effect: STAR",
                "  duration-per-item: 1",
                "  power-per-item: 2");
    }

    @Test
    public void testEmptyExplosionBoosterContent() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("Empty 'TNT' explosion booster");
        loadConfig(getDebugFearingCustomLogger(), "debug: false",
                "EGG-SYSTEM:",
                "  effect: STAR",
                "  duration-per-item: 1",
                "  power-per-item: 2",
                "explosion-boosters:",
                "  - TNT");
    }

    @Test
    public void testTooSmallNukeBroadcastThreshold() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("A 'nuke-broadcast-threshold' should be at least 17");
        loadConfig(getDebugFearingCustomLogger(), "debug: false",
                "EGG-SYSTEM:",
                "  effect: STAR",
                "  duration-per-item: 1",
                "  power-per-item: 2",
                "explosion-boosters:",
                "  - TNT",
                "TNT:",
                "  effect: BURST",
                "  duration-per-item: 1",
                "  power-per-item: 2",
                "nuke-broadcast-threshold: 16");
    }

    @Test
    public void testNormalConfig() throws Exception {
        assertEquals(
                "EGG-SYSTEM: {material: null, effect: STAR, duration-per-item: 1, power-per-item: 2}, " +
                        "explosion-boosters: [{material: TNT, effect: BURST, duration-per-item: 1, power-per-item: 2}], " +
                        "nuke-broadcast-threshold: 17",
                loadConfig(getCustomLogger(), "debug: false",
                        "EGG-SYSTEM:",
                        "  effect: STAR",
                        "  duration-per-item: 1",
                        "  power-per-item: 2",
                        "explosion-boosters:",
                        "  - TNT",
                        "TNT:",
                        "  effect: BURST",
                        "  duration-per-item: 1",
                        "  power-per-item: 2",
                        "nuke-broadcast-threshold: 17").toString());
    }
}