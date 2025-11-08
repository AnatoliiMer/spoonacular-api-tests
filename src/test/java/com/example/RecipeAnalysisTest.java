package com.example;

import com.example.database.DatabaseManager;
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
@Feature("Recipe Analysis")
public class RecipeAnalysisTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(RecipeAnalysisTest.class);

    @Test
    @DisplayName("Analyze recipe instructions")
    @Story("User analyzes recipe")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test analyzing recipe instructions and ingredients")
    void testAnalyzeRecipe() {
        assumeTrue(!isDemoKey(), "Test skipped - using demo API key");

        logger.info("Starting test: Analyze recipe");

        DatabaseManager.TestData testData = DatabaseManager.getTestData("analyze_recipe");
        assertNotNull(testData, "Test data should not be null");

        String requestBody = """
            {
                "title": "Test Recipe",
                "ingredients": [
                    "1 cup flour",
                    "2 eggs",
                    "100ml milk"
                ],
                "instructions": "Mix all ingredients and bake for 30 minutes."
            }
            """;

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/recipes/analyze")
                .then()
                .statusCode(200)
                .extract()
                .response();

        logger.info("Recipe analysis response received");

        assertAll(
                () -> assertNotNull(response, "Response should not be null"),
                () -> assertNotNull(response.jsonPath().getString("title"), "Title should be present"),
                () -> assertNotNull(response.jsonPath().getString("instructions"), "Instructions should be present")
        );

        Allure.step("Verify analysis results", step -> {
            String analyzedTitle = response.jsonPath().getString("title");
            assertEquals("Test Recipe", analyzedTitle, "Recipe title should match");
        });

        logger.info("Test completed successfully");
    }
}