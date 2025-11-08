package com.example;

import com.example.database.DatabaseManager;
import com.example.models.RecipeSearchResponse;
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
@Feature("Recipe Search")
public class RecipeSearchTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(RecipeSearchTest.class);

    @Test
    @DisplayName("Search recipes with query parameters")
    @Story("User searches for recipes")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test search recipes endpoint with various parameters")
    void testSearchRecipes() {
        assumeTrue(!isDemoKey(), "Test skipped - using demo API key");

        logger.info("Starting test: Search recipes");

        DatabaseManager.TestData testData = DatabaseManager.getTestData("search_recipes");
        assertNotNull(testData, "Test data should not be null");

        Response response = given()
                .spec(requestSpec)
                .queryParam("query", "pasta")
                .queryParam("number", 2)
                .when()
                .get("/recipes/complexSearch")
                .then()
                .statusCode(200)
                .extract()
                .response();

        logger.info("Response received: {}", response.asPrettyString());

        RecipeSearchResponse searchResponse = response.as(RecipeSearchResponse.class);

        assertAll(
                () -> assertNotNull(searchResponse, "Response should not be null"),
                () -> assertNotNull(searchResponse.getResults(), "Results should not be null"),
                () -> assertTrue(searchResponse.getResults().size() <= 2, "Number of results should be 2 or less"),
                () -> assertTrue(searchResponse.getTotalResults() > 0, "Total results should be positive")
        );

        if (!searchResponse.getResults().isEmpty()) {
            RecipeSearchResponse.Recipe firstRecipe = searchResponse.getResults().get(0);
            logger.info("First recipe: ID={}, Title={}", firstRecipe.getId(), firstRecipe.getTitle());

            Allure.step("Verify first recipe details", step -> {
                assertNotNull(firstRecipe.getId(), "Recipe ID should not be null");
                assertNotNull(firstRecipe.getTitle(), "Recipe title should not be null");
            });
        }

        logger.info("Test completed successfully");
    }

    @Test
    @DisplayName("Search recipes with demo key")
    @Story("User searches for recipes with limited access")
    @Severity(SeverityLevel.MINOR)
    @Description("Test search recipes endpoint behavior with demo API key")
    void testSearchRecipesWithDemoKey() {
        assumeTrue(isDemoKey(), "Test only runs with demo API key");

        logger.info("Starting test: Search recipes with demo key");

        Response response = given()
                .spec(requestSpec)
                .queryParam("query", "pasta")
                .queryParam("number", 1)
                .when()
                .get("/recipes/complexSearch")
                .then()
                .extract()
                .response();

        if (response.getStatusCode() == 200) {
            logger.info("Demo key provided limited access");
            RecipeSearchResponse searchResponse = response.as(RecipeSearchResponse.class);
            assertNotNull(searchResponse, "Response should not be null");
        } else {
            logger.warn("Demo key access limited - status code: {}", response.getStatusCode());
            assertTrue(response.getStatusCode() == 401 || response.getStatusCode() == 429,
                    "Expected authorization or rate limit error");
        }

        logger.info("Demo key test completed");
    }
}