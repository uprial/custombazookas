package com.gmail.uprial.custombazookas.config;

import com.gmail.uprial.custombazookas.helpers.TestConfigBase;
import com.gmail.uprial.custombazookas.helpers.TestEnum;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;

import static com.gmail.uprial.custombazookas.config.ConfigReaderEnums.getSet;
import static com.gmail.uprial.custombazookas.config.ConfigReaderEnums.*;
import static org.junit.Assert.*;

public class ConfigReaderEnumsTest extends TestConfigBase {
    @Rule
    public final ExpectedException e = ExpectedException.none();

    // ==== getSet ====
    @Test
    public void testEmptySet() throws Exception {
        e.expect(RuntimeException.class);
        e.expectMessage("Empty set. Use default value NULL");
        getSet(TestEnum.class, getPreparedConfig(""), getDebugFearingCustomLogger(), "s", "set");
    }

    @Test
    public void testEmptySetValue() throws Exception {
        assertNull(getSet(TestEnum.class, getPreparedConfig(""), getCustomLogger(), "s", "set"));
    }

    @Test
    public void testWrongSetType() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("Invalid com.gmail.uprial.custombazookas.helpers.TestEnum 'Z' in set at pos 0");
        getSet(TestEnum.class, getPreparedConfig("x:", "  entities:", "   - Z"), getParanoiacCustomLogger(),
                "x.entities", "set");
    }

    @Test
    public void testNotUniqueSet() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("com.gmail.uprial.custombazookas.helpers.TestEnum 'A' in set is not unique");
        getSet(TestEnum.class, getPreparedConfig("x:", "  entities:", "   - A", "   - A"),
                getParanoiacCustomLogger(), "x.entities", "set");
    }

    @Test
    public void testNormalSet() throws Exception {
        Set<TestEnum> set = getSet(TestEnum.class, getPreparedConfig("entities:", " - A"),
                getParanoiacCustomLogger(), "entities", "path");
        assertNotNull(set);
        assertEquals("[A]", set.toString());
    }

    @Test
    public void testContentOfSet() throws Exception {
        Set<TestEnum> entities = getSet(TestEnum.class, getPreparedConfig("entities:", " - A", " - B"),
                getParanoiacCustomLogger(), "entities", "path");
        assertNotNull(entities);
        assertEquals(2, entities.size());
        assertTrue(entities.contains(TestEnum.A));
        assertTrue(entities.contains(TestEnum.B));
    }

    // ==== getStringSet ====

    @Test
    public void testEmptyStringSet() throws Exception {
        e.expect(RuntimeException.class);
        e.expectMessage("Empty set. Use default value NULL");
        getStringSet(getPreparedConfig(""), getDebugFearingCustomLogger(), "s", "set");
    }

    @Test
    public void testEmptyStringSetValue() throws Exception {
        assertNull(getStringSet(getPreparedConfig(""), getCustomLogger(), "s", "set"));
    }

    @Test
    public void testNotUniqueStringSet() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("String 'A' in set is not unique");
        getStringSet(getPreparedConfig("x:", "  entities:", "   - A", "   - A"),
                getParanoiacCustomLogger(), "x.entities", "set");
    }

    @Test
    public void testNormalStringSet() throws Exception {
        Set<String> set = getStringSet(getPreparedConfig("entities:", " - A"),
                getParanoiacCustomLogger(), "entities", "path");
        assertNotNull(set);
        assertEquals("[A]", set.toString());
    }

    @Test
    public void testContentOfStringSet() throws Exception {
        Set<String> entities = getStringSet(getPreparedConfig("entities:", " - A", " - B"),
                getParanoiacCustomLogger(), "entities", "path");
        assertNotNull(entities);
        assertEquals(2, entities.size());
        assertTrue(entities.contains("A"));
        assertTrue(entities.contains("B"));
    }

    // ==== getEnum ====
    @Test
    public void testNormalEnum() throws Exception {
        assertEquals(TestEnum.A, getEnum(TestEnum.class, getPreparedConfig("e: A"), "e", "enum"));
    }

    @Test
    public void testWrongEnum() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("Invalid com.gmail.uprial.custombazookas.helpers.TestEnum 'T' in enum");
        getEnum(TestEnum.class, getPreparedConfig("e: T"), "e", "enum");
    }

    // ==== getEnumOrDefault ====
    @Test
    public void testNormalEnumOrDefault() throws Exception {
        assertEquals(TestEnum.A, getEnumOrDefault(TestEnum.class, getPreparedConfig("e: A"),
                getDebugFearingCustomLogger(), "e", "enum", null));
    }

    @Test
    public void testWrongEnumOrDefault() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("Invalid com.gmail.uprial.custombazookas.helpers.TestEnum 'T' in enum");
        getEnumOrDefault(TestEnum.class, getPreparedConfig("e: T"),
                getDebugFearingCustomLogger(), "e", "enum", null);
    }

    @Test
    public void testEmptyEnumOrDefault() throws Exception {
        e.expect(RuntimeException.class);
        e.expectMessage("Empty enum. Use default value NULL");
        getEnumOrDefault(TestEnum.class, getPreparedConfig("?:"),
                getDebugFearingCustomLogger(), "e", "enum", null);
    }

    // ==== getEnumFromString ====
    @Test
    public void testWrongEnumString() throws Exception {
        e.expect(InvalidConfigException.class);
        e.expectMessage("Invalid com.gmail.uprial.custombazookas.helpers.TestEnum 'T' in enum");
        getEnumFromString(TestEnum.class, "T", "enum", "");
    }

    @Test
    public void testNormalEnumString() throws Exception {
        assertEquals(TestEnum.A, getEnumFromString(TestEnum.class, "A", "enum", ""));
    }

    @Test
    public void testNormalEnumCase() throws Exception {
        assertEquals(TestEnum.A, getEnumFromString(TestEnum.class, "a", "enum", ""));
    }

}