package com.gmail.uprial.custombazookas.firework;

import com.gmail.uprial.custombazookas.helpers.TestConfigBase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FireworkCraftBookTest extends TestConfigBase {
    @Rule
    public final ExpectedException e = ExpectedException.none();

    @Test
    public void testInitSuccess() throws Exception {
        new FireworkCraftBook(null, getParanoiacCustomLogger());
    }
}