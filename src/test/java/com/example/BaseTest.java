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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static final ObjectMapper objectMapper = new ObjectMapper();
    protected static String API_KEY;
    protected static final String BASE_URL = "https://api.spoonacular.com";

    protected static RequestSpecification requestSpec;
    private ByteArrayOutputStream requestLog;
    private ByteArrayOutputStream responseLog;
    private PrintStream originalOut;

    @BeforeAll
    static void setUp() {
        loadApiKey();
        validateApiKey();

        // Настройка RestAssured для детального логирования
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addQueryParam("apiKey", API_KEY)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured()) // Allure фильтр
                .addFilter(new RequestLoggingFilter()) // Логирование запросов
                .addFilter(new ResponseLoggingFilter()) // Логирование ответов
                .build();

        RestAssured.requestSpecification = requestSpec;

        logger.info("=== TEST SETUP COMPLETED ===");
        logger.info("Base URL: {}", BASE_URL);
        logger.info("Using API Key: {}...", API_KEY != null ? API_KEY.substring(0, 8) + "..." : "null");
        logger.info("Demo Key Mode: {}", isDemoKey());
    }

    @BeforeEach
    void setUpTest() {
        logger.info("=== STARTING TEST: {} ===",
                this.getClass().getSimpleName() + "." +
                        new Throwable().getStackTrace()[1].getMethodName());

        // Настройка потоков для логирования RestAssured
        requestLog = new ByteArrayOutputStream();
        responseLog = new ByteArrayOutputStream();
        originalOut = System.out;

        System.setOut(new PrintStream(requestLog));
        RestAssured.filters(new RequestLoggingFilter(new PrintStream(requestLog)));
        System.setOut(new PrintStream(responseLog));
        RestAssured.filters(new ResponseLoggingFilter(new PrintStream(responseLog)));
        System.setOut(originalOut);
    }

    @AfterEach
    void tearDownTest() {
        // Восстанавливаем стандартный вывод
        System.setOut(originalOut);

        logger.info("=== COMPLETED TEST: {} ===",
                this.getClass().getSimpleName() + "." +
                        new Throwable().getStackTrace()[1].getMethodName());
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

    // Метод для логирования деталей запроса/ответа в Allure
    protected void logRequestResponseDetails(io.restassured.response.Response response) {
        String requestDetails = requestLog.toString();
        String responseDetails = responseLog.toString();

        if (!requestDetails.isEmpty()) {
            logger.debug("Request Details:\n{}", requestDetails);
            io.qameta.allure.Allure.addAttachment("Request Details", "text/plain", requestDetails);
        }

        if (!responseDetails.isEmpty()) {
            logger.debug("Response Details:\n{}", responseDetails);
            io.qameta.allure.Allure.addAttachment("Response Details", "text/plain", responseDetails);
        }

        // Логируем основные детали ответа
        logger.info("Response Status: {} {}", response.getStatusCode(), response.getStatusLine());
        logger.info("Response Time: {} ms", response.getTime());
        logger.info("Response Content Type: {}", response.getContentType());

        if (response.getBody() != null) {
            String responseBody = response.getBody().asPrettyString();
            if (responseBody.length() > 1000) {
                logger.debug("Response Body (truncated):\n{}", responseBody.substring(0, 1000) + "...");
                io.qameta.allure.Allure.addAttachment("Response Body (truncated)", "application/json",
                        responseBody.substring(0, 1000) + "...");
            } else {
                logger.debug("Response Body:\n{}", responseBody);
                io.qameta.allure.Allure.addAttachment("Response Body", "application/json", responseBody);
            }
        }
    }
}