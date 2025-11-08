// src/test/java/com/example/FoodTriviaTest.java (обновленный)
package com.example;

import com.example.database.DatabaseManager;
import com.example.models.FoodTrivia;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Epic("Spoonacular API Tests")
@Feature("Food Trivia")
public class FoodTriviaTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(FoodTriviaTest.class);

    @Test
    @DisplayName("Get random food trivia")
    @Story("User reads food trivia")
    @Severity(SeverityLevel.MINOR)
    @Description("Test retrieving random food trivia")
    void testGetFoodTrivia() {
        assumeTrue(!isDemoKey(), "Test skipped - using demo API key");

        logger.info("Starting test: Get food trivia");

        DatabaseManager.TestData testData = DatabaseManager.getTestData("food_trivia");
        assertNotNull(testData, "Test data should not be null");

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/food/trivia/random")
                .then()
                .statusCode(200)
                .extract()
                .response();

        logger.info("Food trivia response received");

        // Используем модель для десериализации
        FoodTrivia trivia = response.as(FoodTrivia.class);

        assertAll(
                () -> assertNotNull(trivia, "Trivia should not be null"),
                () -> assertNotNull(trivia.getText(), "Trivia text should be present"),
                () -> assertTrue(trivia.getText().length() > 10, "Trivia text should be meaningful")
        );

        logger.info("Food trivia: {}", trivia.getText());

        logger.info("Test completed successfully");
    }
}