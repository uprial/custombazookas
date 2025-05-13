package com.gmail.uprial.custombazookas.firework;

import org.bukkit.Color;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class FireworkMagicColorTest {
    @Rule
    public final ExpectedException e = ExpectedException.none();

    private static final int BORDER_VALUE = FireworkMagicColor.getMaxValue();

    @Test
    public void testMaxTypeAndAmount() {
        final Color color = FireworkMagicColor.encode(new FireworkMagic(FireworkMagicCode.TYPE, BORDER_VALUE));
        final FireworkMagic fmc = FireworkMagicColor.decode(color);

        assertEquals(fmc.getCode(), FireworkMagicCode.TYPE);
        assertEquals(fmc.getValue(), BORDER_VALUE);
    }

    @Test
    public void testAmountTooBig() {
        e.expect(FireworkError.class);
        e.expectMessage("Value too big: 16384");
        FireworkMagicColor.encode(new FireworkMagic(FireworkMagicCode.TYPE, BORDER_VALUE + 1));
    }

    @Test
    public void testIdempotency() {
        for(int type = 0; type < FireworkMagicCode.getMaxIntCapacity(); type++) {
            final FireworkMagicCode code = FireworkMagicCode.decode(type);
            for(int value = 0; value < 256 * 3; value++) {
                final Color color = FireworkMagicColor.encode(new FireworkMagic(code, value));
                final FireworkMagic fmc = FireworkMagicColor.decode(color);

                assertEquals(code, fmc.getCode());
                assertEquals(value, fmc.getValue());
            }
        }
    }
}