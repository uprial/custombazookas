package com.gmail.uprial.custombazookas;

import com.gmail.uprial.custombazookas.helpers.TestConfigBase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CustomBazookasTest extends TestConfigBase {
    @Rule
    public final ExpectedException e = ExpectedException.none();

    @Test
    public void testLoadException() throws Exception {
        e.expect(RuntimeException.class);
        e.expectMessage("[ERROR] Empty 'EGG-SYSTEM'");
        CustomBazookas.loadConfig(getPreparedConfig(""), getCustomLogger());
    }
}