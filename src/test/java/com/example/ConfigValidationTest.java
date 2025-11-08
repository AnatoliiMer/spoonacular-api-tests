package com.example;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Configuration Tests")
@Feature("API Key Configuration")
public class ConfigValidationTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ConfigValidationTest.class);

    @Test
    @DisplayName("Validate API Key configuration")
    @Severity(SeverityLevel.BLOCKER)
    void testApiKeyConfiguration() {
        logger.info("Validating API Key configuration...");

        assertNotNull(API_KEY, "API Key should not be null");
        assertFalse(API_KEY.trim().isEmpty(), "API Key should not be empty");

        if (isDemoKey()) {
            logger.warn("⚠️  USING DEMO API KEY - Some tests will be skipped");
            logger.warn("Please set your actual API key using one of these methods:");
            logger.warn("  - System property: -DapiKey=YOUR_KEY");
            logger.warn("  - Environment variable: SPOONACULAR_API_KEY");
            logger.warn("  - config.properties file");
        } else {
            logger.info("✅ Valid API Key configured: {}...", API_KEY.substring(0, 8));
        }

        assertTrue(API_KEY.length() >= 8, "API Key should be at least 8 characters long");
    }
}