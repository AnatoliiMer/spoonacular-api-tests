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
@Story("Recipe Search Functionality")
public class RecipeSearchTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(RecipeSearchTest.class);

    @Test
    @DisplayName("Search recipes with query parameters")
    @Story("User searches for recipes with specific parameters")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test search recipes endpoint with query parameters like pasta and limit results to 2")
    void testSearchRecipes() {
        assumeTrue(!isDemoKey(), "Test skipped - using demo API key");

        Allure.step("Prepare test data", step -> {
            logger.info("Starting test: Search recipes with query parameters");

            DatabaseManager.TestData testData = DatabaseManager.getTestData("search_recipes");
            assertNotNull(testData, "Test data should not be null");
            logger.debug("Test data loaded: {}", testData.getTestName());
        });

        Response response = Allure.step("Execute search request", step -> {
            logger.info("Executing search request for 'pasta' with 2 results");
            return given()
                    .spec(requestSpec)
                    .queryParam("query", "pasta")
                    .queryParam("number", 2)
                    .when()
                    .get("/recipes/complexSearch")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();
        });

        Allure.step("Log request/response details", step -> {
            logRequestResponseDetails(response);
        });

        RecipeSearchResponse searchResponse = Allure.step("Parse response", step -> {
            RecipeSearchResponse parsedResponse = response.as(RecipeSearchResponse.class);
            logger.info("Successfully parsed response with {} results",
                    parsedResponse.getResults() != null ? parsedResponse.getResults().size() : 0);
            return parsedResponse;
        });

        Allure.step("Validate response", step -> {
            logger.info("Validating search response");
            assertAll(
                    () -> assertNotNull(searchResponse, "Response should not be null"),
                    () -> assertNotNull(searchResponse.getResults(), "Results should not be null"),
                    () -> assertTrue(searchResponse.getResults().size() <= 2,
                            "Number of results should be 2 or less, but got: " + searchResponse.getResults().size()),
                    () -> assertTrue(searchResponse.getTotalResults() > 0,
                            "Total results should be positive, but got: " + searchResponse.getTotalResults())
            );
        });

        Allure.step("Process results", step -> {
            if (!searchResponse.getResults().isEmpty()) {
                RecipeSearchResponse.Recipe firstRecipe = searchResponse.getResults().get(0);
                logger.info("First recipe details - ID: {}, Title: {}, Image: {}",
                        firstRecipe.getId(), firstRecipe.getTitle(), firstRecipe.getImage());

                Allure.step("Verify first recipe details", subStep -> {
                    assertNotNull(firstRecipe.getId(), "Recipe ID should not be null");
                    assertNotNull(firstRecipe.getTitle(), "Recipe title should not be null");
                    assertNotNull(firstRecipe.getImageType(), "Recipe image type should not be null");
                });

                // Добавляем детали рецепта в Allure отчет
                Allure.addAttachment("First Recipe", "text/plain",
                        String.format("ID: %d\nTitle: %s\nImage: %s",
                                firstRecipe.getId(), firstRecipe.getTitle(), firstRecipe.getImage()));
            } else {
                logger.warn("No recipes found in search results");
            }
        });

        logger.info("Test completed successfully - Found {} recipes", searchResponse.getResults().size());
    }

    @Test
    @DisplayName("Search recipes with demo key limitations")
    @Story("User experiences limited functionality with demo key")
    @Severity(SeverityLevel.MINOR)
    @Description("Test search recipes endpoint behavior when using demo API key")
    void testSearchRecipesWithDemoKey() {
        assumeTrue(isDemoKey(), "Test only runs with demo API key");

        Allure.step("Execute demo key request", step -> {
            logger.info("Starting demo key test: Search recipes with limited access");

            Response response = given()
                    .spec(requestSpec)
                    .queryParam("query", "pasta")
                    .queryParam("number", 1)
                    .when()
                    .get("/recipes/complexSearch")
                    .then()
                    .extract()
                    .response();

            logRequestResponseDetails(response);

            if (response.getStatusCode() == 200) {
                logger.info("Demo key provided limited access");
                RecipeSearchResponse searchResponse = response.as(RecipeSearchResponse.class);
                assertNotNull(searchResponse, "Response should not be null");

                Allure.addAttachment("Demo Access", "text/plain",
                        "Demo key provided limited access with status: " + response.getStatusCode());
            } else {
                logger.warn("Demo key access limited - status code: {}", response.getStatusCode());
                assertTrue(response.getStatusCode() == 401 || response.getStatusCode() == 429,
                        "Expected authorization (401) or rate limit (429) error, but got: " + response.getStatusCode());

                Allure.addAttachment("Demo Limitations", "text/plain",
                        "Demo key limited with status: " + response.getStatusCode());
            }
        });

        logger.info("Demo key test completed");
    }

    @Test
    @DisplayName("Search recipes with different cuisines")
    @Story("User searches for recipes by cuisine type")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test search recipes endpoint with Italian cuisine filter")
    void testSearchRecipesByCuisine() {
        assumeTrue(!isDemoKey(), "Test skipped - using demo API key");

        Allure.step("Search Italian recipes", step -> {
            logger.info("Starting test: Search Italian recipes");

            Response response = given()
                    .spec(requestSpec)
                    .queryParam("cuisine", "italian")
                    .queryParam("number", 3)
                    .when()
                    .get("/recipes/complexSearch")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            logRequestResponseDetails(response);

            RecipeSearchResponse searchResponse = response.as(RecipeSearchResponse.class);

            Allure.step("Validate Italian recipes search", subStep -> {
                assertAll(
                        () -> assertNotNull(searchResponse, "Response should not be null"),
                        () -> assertNotNull(searchResponse.getResults(), "Results should not be null"),
                        () -> assertTrue(searchResponse.getTotalResults() > 0,
                                "Should find Italian recipes")
                );
            });

            logger.info("Found {} Italian recipes", searchResponse.getResults().size());
            Allure.addAttachment("Italian Recipes Count", "text/plain",
                    String.valueOf(searchResponse.getResults().size()));
        });

        logger.info("Italian cuisine search test completed");
    }
}