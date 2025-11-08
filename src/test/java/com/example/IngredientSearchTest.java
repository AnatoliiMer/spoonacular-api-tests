// src/test/java/com/example/IngredientSearchTest.java (обновленный)
package com.example;

import com.example.database.DatabaseManager;
import com.example.models.IngredientSearch;
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
@Feature("Ingredient Search")
public class IngredientSearchTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(IngredientSearchTest.class);

    @Test
    @DisplayName("Search ingredients by query")
    @Story("User searches for ingredients")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test searching for food ingredients")
    void testSearchIngredients() {
        assumeTrue(!isDemoKey(), "Test skipped - using demo API key");

        logger.info("Starting test: Search ingredients");

        DatabaseManager.TestData testData = DatabaseManager.getTestData("ingredient_search");
        assertNotNull(testData, "Test data should not be null");

        Response response = given()
                .spec(requestSpec)
                .queryParam("query", "apple")
                .queryParam("number", 1)
                .when()
                .get("/food/ingredients/search")
                .then()
                .statusCode(200)
                .extract()
                .response();

        logger.info("Ingredient search response received");

        // Используем модель для десериализации
        IngredientSearch ingredientSearch = response.as(IngredientSearch.class);

        assertAll(
                () -> assertNotNull(ingredientSearch, "Ingredient search response should not be null"),
                () -> assertNotNull(ingredientSearch.getResults(), "Results should not be null"),
                () -> assertTrue(ingredientSearch.getResults().size() <= 1, "Should return 1 or fewer results"),
                () -> assertTrue(ingredientSearch.getTotalResults() > 0, "Total results should be positive")
        );

        if (!ingredientSearch.getResults().isEmpty()) {
            IngredientSearch.Ingredient firstIngredient = ingredientSearch.getResults().get(0);
            logger.info("Found ingredient: {} (ID: {})", firstIngredient.getName(), firstIngredient.getId());

            Allure.step("Verify ingredient details", step -> {
                assertNotNull(firstIngredient.getName(), "Ingredient name should not be null");
                assertTrue(firstIngredient.getName().toLowerCase().contains("apple"),
                        "Ingredient should contain 'apple'");
            });
        }

        logger.info("Test completed successfully");
    }
}