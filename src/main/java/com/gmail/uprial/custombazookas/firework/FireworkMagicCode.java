package com.gmail.uprial.custombazookas.firework;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

enum FireworkMagicCode {
    TYPE(0),
    AMOUNT(1);

    private static final Map<Integer,FireworkMagicCode> INT_TO_CODE = ImmutableMap.<Integer,FireworkMagicCode>builder()
            .put(TYPE.getInt(), TYPE)
            .put(AMOUNT.getInt(), AMOUNT)
            .build();

    static int getMaxIntCapacity() {
        return 2;
    }

    private final int code;

    private FireworkMagicCode(final int code) {
        this.code = code;
    }

    int getInt() {
        return code;
    }

    static FireworkMagicCode decode(int code) {
        if (INT_TO_CODE.containsKey(code)) {
            return INT_TO_CODE.get(code);
        } else {
            throw new FireworkError(String.format("Unrecognized code: %d", code));
        }
    }
}
