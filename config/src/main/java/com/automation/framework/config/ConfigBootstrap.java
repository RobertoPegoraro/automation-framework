package com.automation.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.jboss.logging.Logger;

import io.smallrye.config.PropertiesConfigSource;
import io.smallrye.config.SmallRyeConfigBuilder;

public final class ConfigBootstrap {

    private static final Logger LOG = Logger.getLogger(ConfigBootstrap.class);

    private static final String DEFAULT_PROPERTIES = "application.properties";

    private static final String ENV_PROPERTY_KEY = "env";

    private static final String DEFAULT_ENVIRONMENT = "default";

    private static final int DEFAULT_ORDINAL = 100;

    private static final int ENV_ORDINAL = 110;

    private ConfigBootstrap() {

    }

    public static <T> T load(Class<T> mappingClass) {

        SmallRyeConfigBuilder builder = (SmallRyeConfigBuilder) ConfigProviderResolver.instance().getBuilder();

        addPropertyFileIfExists(builder, DEFAULT_PROPERTIES, DEFAULT_ORDINAL);

        String env = System.getProperty(ENV_PROPERTY_KEY);
        if (env == null || env.isBlank()) {
            env = DEFAULT_ENVIRONMENT;
        }

        if (!DEFAULT_ENVIRONMENT.equalsIgnoreCase(env)) {
            String envFile = String.format("application-%s.properties", env.trim());
            addPropertyFileIfExists(builder, envFile, ENV_ORDINAL);
        } else {
            LOG.info("Variable 'env' is not configured");
        }
        builder.withMapping(mappingClass);

        return builder.build().getConfigMapping(mappingClass);
    }

    private static void addPropertyFileIfExists(SmallRyeConfigBuilder builder, String resource, int ordinal) {

        Objects.requireNonNull(builder, "SmallRyeConfigBuilder can't be null");
        Objects.requireNonNull(resource, "resource can't be null");

        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource)) {
            if (in != null) {
                Properties props = new Properties();
                props.load(in);
                PropertiesConfigSource source = new PropertiesConfigSource(props, resource, ordinal);
                builder.withSources(source);
                LOG.infof("Config resource loaded: %s with ordinal %d", resource, ordinal);
            } else {
                LOG.debugf("Config resource not found: %s", resource);
            }
        } catch (IOException e) {
            LOG.errorf(e, "Error to load config resource %s", resource);
        }
    }

}
