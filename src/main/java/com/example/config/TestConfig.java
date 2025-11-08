package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static final Logger logger = LoggerFactory.getLogger(TestConfig.class);
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = TestConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
                logger.info("Configuration loaded successfully");
            } else {
                logger.warn("config.properties not found, using default values");
            }
        } catch (IOException e) {
            logger.error("Error loading configuration file", e);
        }
    }

    public static String getApiKey() {
        return getProperty("api.key", "demo_key_limited_use");
    }

    public static String getBaseUrl() {
        return getProperty("base.url", "https://api.spoonacular.com");
    }

    public static String getDbUrl() {
        return getProperty("db.url", "jdbc:sqlite:test.db");
    }

    public static int getTestTimeout() {
        return Integer.parseInt(getProperty("test.timeout", "30000"));
    }

    public static int getTestRetryCount() {
        return Integer.parseInt(getProperty("test.retry.count", "3"));
    }

    public static String getLoggingLevel() {
        return getProperty("logging.level", "INFO");
    }

    private static String getProperty(String key, String defaultValue) {
        // Сначала проверяем системные свойства
        String systemProperty = System.getProperty(key);
        if (systemProperty != null && !systemProperty.trim().isEmpty()) {
            return systemProperty;
        }

        // Затем проверяем переменные окружения
        String envProperty = System.getenv(key.toUpperCase().replace('.', '_'));
        if (envProperty != null && !envProperty.trim().isEmpty()) {
            return envProperty;
        }

        // Затем из properties файла
        String fileProperty = properties.getProperty(key);
        if (fileProperty != null && !fileProperty.trim().isEmpty()) {
            return fileProperty;
        }

        // Возвращаем значение по умолчанию
        return defaultValue;
    }
}