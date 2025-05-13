package com.gmail.uprial.custombazookas.firework;

class FireworkMagic {
    private final FireworkMagicCode code;
    private final int value;

    FireworkMagic(final FireworkMagicCode code, final int value) {
        this.code = code;
        this.value = value;
    }

    FireworkMagicCode getCode() {
        return code;
    }

    int getValue() {
        return value;
    }
}
