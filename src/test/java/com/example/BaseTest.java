package com.example;

import com.example.config.TestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static final ObjectMapper objectMapper = new ObjectMapper();
    protected static String API_KEY;
    protected static final String BASE_URL = "https://api.spoonacular.com";

    protected static RequestSpecification requestSpec;

    @BeforeAll
    static void setUp() {
        loadApiKey();
        validateApiKey();

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addQueryParam("apiKey", API_KEY)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        RestAssured.requestSpecification = requestSpec;

        logger.info("Base test setup completed. Using API Key: {}...",
                API_KEY != null ? API_KEY.substring(0, 8) + "..." : "null");
    }

    private static void loadApiKey() {
        // 1. Попробовать получить из системных свойств
        API_KEY = System.getProperty("apiKey");
        if (API_KEY != null && !API_KEY.trim().isEmpty()) {
            logger.info("API Key loaded from system properties");
            return;
        }

        // 2. Попробовать получить из переменных окружения
        API_KEY = System.getenv("SPOONACULAR_API_KEY");
        if (API_KEY != null && !API_KEY.trim().isEmpty()) {
            logger.info("API Key loaded from environment variables");
            return;
        }

        // 3. Попробовать загрузить из конфигурации
        API_KEY = TestConfig.getApiKey();
        if (API_KEY != null && !API_KEY.trim().isEmpty() && !API_KEY.equals("demo_key_limited_use")) {
            logger.info("API Key loaded from configuration");
            return;
        }

        // 4. Fallback - использовать демо ключ (ограниченный)
        API_KEY = "demo_key_limited_use";
        logger.warn("Using demo API key - functionality will be limited");
    }

    private static void validateApiKey() {
        if (API_KEY == null || API_KEY.trim().isEmpty()) {
            throw new IllegalStateException("API Key cannot be null or empty");
        }

        if (API_KEY.equals("demo_key_limited_use")) {
            logger.warn("⚠️  USING DEMO API KEY - PLEASE SET YOUR OWN API KEY");
            logger.warn("Set your API key via:");
            logger.warn("  - System property: -DapiKey=YOUR_API_KEY");
            logger.warn("  - Environment variable: SPOONACULAR_API_KEY");
            logger.warn("  - config.properties file: api.key=YOUR_API_KEY");
        }
    }

    protected static boolean isDemoKey() {
        return "demo_key_limited_use".equals(API_KEY);
    }
}