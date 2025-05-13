package com.gmail.uprial.custombazookas.helpers;

import com.gmail.uprial.custombazookas.CustomBazookasConfig;
import com.gmail.uprial.custombazookas.config.InvalidConfigException;
import com.google.common.collect.Lists;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import static com.gmail.uprial.custombazookas.common.Utils.joinStrings;

@SuppressWarnings("AbstractClassWithoutAbstractMethods")
public abstract class TestConfigBase extends TestServerBase {
    protected static void loadConfig(String content) throws InvalidConfigurationException, InvalidConfigException {
        loadConfig(new String[]{content});
    }

    protected static CustomBazookasConfig loadConfig(String... contents) throws InvalidConfigurationException, InvalidConfigException {
        return loadConfig(getCustomLogger(), contents);
    }

    protected static CustomBazookasConfig loadConfig(TestCustomLogger testCustomLogger, String... contents) throws InvalidConfigurationException, InvalidConfigException {
        return CustomBazookasConfig.getFromConfig(getPreparedConfig(contents), testCustomLogger);
    }

    protected static YamlConfiguration getPreparedConfig(String... contents) throws InvalidConfigurationException {
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        yamlConfiguration.loadFromString(joinStrings(System.lineSeparator(), Lists.newArrayList(contents)));

        return yamlConfiguration;
    }

    protected static TestCustomLogger getCustomLogger() {
        return new TestCustomLogger();
    }

    protected static TestCustomLogger getDebugFearingCustomLogger() {
        TestCustomLogger testCustomLogger = new TestCustomLogger();
        testCustomLogger.doFailOnDebug();

        return testCustomLogger;
    }

    protected static TestCustomLogger getParanoiacCustomLogger() {
        TestCustomLogger testCustomLogger = new TestCustomLogger();
        testCustomLogger.doFailOnAny();

        return testCustomLogger;
    }

    protected static TestCustomLogger getIndifferentCustomLogger() {
        TestCustomLogger testCustomLogger = new TestCustomLogger();
        testCustomLogger.doNotFailOnError();

        return testCustomLogger;
    }
}
