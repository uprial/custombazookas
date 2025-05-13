package com.gmail.uprial.custombazookas.firework;

import junit.framework.TestCase;
import org.bukkit.Color;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class FireworkMagicCodeTest {
    @Rule
    public final ExpectedException e = ExpectedException.none();

    @Test
    public void testCodeAndDecode() {
        for(int code = 0; code <FireworkMagicCode.getMaxIntCapacity(); code++) {
            assertEquals(code, FireworkMagicCode.decode(code).getInt());
        }
    }

    @Test
    public void tesUnrecognizedCode() {
        e.expect(FireworkError.class);
        e.expectMessage("Unrecognized code: 2");
        FireworkMagicCode.decode(FireworkMagicCode.getMaxIntCapacity());
    }
}