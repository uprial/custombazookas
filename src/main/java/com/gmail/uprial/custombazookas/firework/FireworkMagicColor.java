package com.gmail.uprial.custombazookas.firework;

import org.bukkit.Color;

class FireworkMagicColor {
    /*
        Avoid any changes on the already generated map:
        the already generated and crafted fireworks will stop working.
     */
    private static final int MAGIC_RED = Color.RED.getRed() - 1;

    private static final int BYTE = Color.RED.getRed() + 1;

    // 2 is for future headroom
    private static final int CODE_CAPACITY = FireworkMagicCode.getMaxIntCapacity() + 2;

    static int getMaxValue() {
        return BYTE * (BYTE / CODE_CAPACITY) - 1;
    }

    static Color encode(final FireworkMagic magic) {
        if (magic.getValue() > getMaxValue()) {
            throw new FireworkError(String.format("Value too big: %d", magic.getValue()));
        }

        final int green = magic.getValue() / BYTE * CODE_CAPACITY + magic.getCode().getInt();
        final int blue = magic.getValue() % BYTE;

        return Color.fromRGB(MAGIC_RED, green, blue);
    }

    static FireworkMagic decode(final Color color) {
        if((color.getRed() == MAGIC_RED)) {
            final FireworkMagicCode code = FireworkMagicCode.decode(color.getGreen() % CODE_CAPACITY);
            final int value = (color.getGreen() / CODE_CAPACITY) * BYTE + color.getBlue();

            return new FireworkMagic(code, value);
        } else {
            return null;
        }
    }
}
