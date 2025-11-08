package com.example;

import com.example.database.DatabaseManager;
import com.example.models.RecipeInformation;
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
@Feature("Recipe Information")
public class RecipeInformationTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(RecipeInformationTest.class);

    @Test
    @DisplayName("Get recipe information by ID")
    @Story("User views recipe details")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test retrieving detailed information about a specific recipe")
    void testGetRecipeInformation() {
        assumeTrue(!isDemoKey(), "Test skipped - using demo API key");

        logger.info("Starting test: Get recipe information");

        DatabaseManager.TestData testData = DatabaseManager.getTestData("get_recipe_info");
        assertNotNull(testData, "Test data should not be null");

        int recipeId = 716429;

        Response response = given()
                .spec(requestSpec)
                .pathParam("id", recipeId)
                .when()
                .get("/recipes/{id}/information")
                .then()
                .statusCode(200)
                .extract()
                .response();

        logger.info("Recipe information response received");

        RecipeInformation recipeInfo = response.as(RecipeInformation.class);

        assertAll(
                () -> assertNotNull(recipeInfo, "Recipe information should not be null"),
                () -> assertEquals(recipeId, recipeInfo.getId(), "Recipe ID should match"),
                () -> assertNotNull(recipeInfo.getTitle(), "Recipe title should not be null"),
                () -> assertTrue(recipeInfo.getServings() > 0, "Servings should be positive"),
                () -> assertTrue(recipeInfo.getReadyInMinutes() > 0, "Ready in minutes should be positive")
        );

        logger.info("Recipe: {} - Servings: {} - Ready in: {} minutes",
                recipeInfo.getTitle(), recipeInfo.getServings(), recipeInfo.getReadyInMinutes());

        Allure.step("Verify recipe nutritional information", step -> {
            assertTrue(recipeInfo.getHealthScore() >= 0, "Health score should be non-negative");
            assertTrue(recipeInfo.getPricePerServing() >= 0, "Price per serving should be non-negative");
        });

        logger.info("Test completed successfully");
    }
}