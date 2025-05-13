package com.gmail.uprial.custombazookas.firework;

import com.gmail.uprial.custombazookas.config.InvalidConfigException;
import com.gmail.uprial.custombazookas.helpers.TestConfigBase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.gmail.uprial.custombazookas.firework.FireworkBooster.getFromConfig;
import static org.junit.Assert.*;

public class FireworkBoosterTest extends TestConfigBase {
    @Rule
    public final ExpectedException e = ExpectedException.none();

    @Test
    public void testWhole() throws Exception {
        FireworkBooster fireworkBooster = getFromConfig(getPreparedConfig(
                        "fb:",
                        "  effect: STAR",
                        "  duration-per-item: 1",
                        "  power-per-item: 2"),
                getParanoiacCustomLogger(), null, "fb", "'fb'");
        assertNotNull(fireworkBooster);
        assertEquals("{material: null, effect: STAR, duration-per-item: 1, power-per-item: 2}",
                fireworkBooster.toString());
    }

    @Test
    public void testEmptyEffect() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("Empty 'fb'");
        getFromConfig(getPreparedConfig(
                        "?:"),
                getCustomLogger(), null, "fb", "'fb'");
    }

    @Test
    public void testWrongEffect() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("Invalid org.bukkit.FireworkEffect$Type 'v' in effect of 'fb'");
        getFromConfig(getPreparedConfig(
                        "fb:",
                        " effect: v"),
                getCustomLogger(), null, "fb", "'fb'");
    }

    @Test
    public void testEmptyDurationPerItem() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("Empty duration per item of 'as'");
        getFromConfig(getPreparedConfig(
                        "as:",
                        " effect: STAR"),
                getCustomLogger(), null, "as", "'as'");
    }

    @Test
    public void testWrongDurationPerItem() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("A duration per item of 'as' is not an integer");
        getFromConfig(getPreparedConfig(
                        "as:",
                        " effect: STAR",
                        " duration-per-item: v"),
                getCustomLogger(), null, "as", "'as'");
    }

    @Test
    public void testTooBigDurationPerItem() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("A duration per item of 'as' should be at most 31");
        getFromConfig(getPreparedConfig(
                        "as:",
                        " effect: STAR",
                        " duration-per-item: 32"),
                getCustomLogger(), null, "as", "'as'");
    }

    @Test
    public void testEmptyPowerPerItem() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("Empty power per item of 'as'");
        getFromConfig(getPreparedConfig(
                        "as:",
                        " effect: STAR",
                        " duration-per-item: 1"),
                getCustomLogger(), null, "as", "'as'");
    }

    @Test
    public void testWrongPowerPerItem() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("A power per item of 'as' is not an integer");
        getFromConfig(getPreparedConfig(
                        "as:",
                        " effect: STAR",
                        " duration-per-item: 1",
                        " power-per-item: v"),
                getCustomLogger(), null, "as", "'as'");
    }

    @Test
    public void testTooBigPowerPerItem() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("A power per item of 'as' should be at most 2047");
        getFromConfig(getPreparedConfig(
                        "as:",
                        " effect: STAR",
                        " duration-per-item: 31",
                        " power-per-item: 2048"),
                getCustomLogger(), null, "as", "'as'");
    }
}