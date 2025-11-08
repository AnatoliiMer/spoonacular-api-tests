// src/main/java/com/example/models/RecipeSearchResponse.java
package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeSearchResponse {
    @JsonProperty("results")
    private List<Recipe> results;

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("number")
    private int number;

    @JsonProperty("totalResults")
    private int totalResults;

    // Дополнительные поля из реального ответа
    @JsonProperty("baseUri")
    private String baseUri;

    @JsonProperty("expires")
    private Long expires;

    @JsonProperty("isStale")
    private Boolean isStale;

    public List<Recipe> getResults() { return results; }
    public void setResults(List<Recipe> results) { this.results = results; }
    public int getOffset() { return offset; }
    public void setOffset(int offset) { this.offset = offset; }
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public int getTotalResults() { return totalResults; }
    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }
    public String getBaseUri() { return baseUri; }
    public void setBaseUri(String baseUri) { this.baseUri = baseUri; }
    public Long getExpires() { return expires; }
    public void setExpires(Long expires) { this.expires = expires; }
    public Boolean getIsStale() { return isStale; }
    public void setIsStale(Boolean isStale) { this.isStale = isStale; }

    public static class Recipe {
        @JsonProperty("id")
        private int id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("image")
        private String image;

        @JsonProperty("imageType")
        private String imageType;

        // Дополнительные поля
        @JsonProperty("servings")
        private Integer servings;

        @JsonProperty("readyInMinutes")
        private Integer readyInMinutes;

        @JsonProperty("license")
        private String license;

        @JsonProperty("sourceName")
        private String sourceName;

        @JsonProperty("sourceUrl")
        private String sourceUrl;

        @JsonProperty("spoonacularSourceUrl")
        private String spoonacularSourceUrl;

        @JsonProperty("healthScore")
        private Double healthScore;

        @JsonProperty("spoonacularScore")
        private Double spoonacularScore;

        @JsonProperty("pricePerServing")
        private Double pricePerServing;

        @JsonProperty("analyzedInstructions")
        private List<Object> analyzedInstructions;

        @JsonProperty("cheap")
        private Boolean cheap;

        @JsonProperty("creditsText")
        private String creditsText;

        @JsonProperty("cuisines")
        private List<String> cuisines;

        @JsonProperty("dairyFree")
        private Boolean dairyFree;

        @JsonProperty("diets")
        private List<String> diets;

        @JsonProperty("gaps")
        private String gaps;

        @JsonProperty("glutenFree")
        private Boolean glutenFree;

        @JsonProperty("instructions")
        private String instructions;

        @JsonProperty("ketogenic")
        private Boolean ketogenic;

        @JsonProperty("lowFodmap")
        private Boolean lowFodmap;

        @JsonProperty("occasions")
        private List<String> occasions;

        @JsonProperty("sustainable")
        private Boolean sustainable;

        @JsonProperty("vegan")
        private Boolean vegan;

        @JsonProperty("vegetarian")
        private Boolean vegetarian;

        @JsonProperty("veryHealthy")
        private Boolean veryHealthy;

        @JsonProperty("veryPopular")
        private Boolean veryPopular;

        @JsonProperty("whole30")
        private Boolean whole30;

        @JsonProperty("weightWatcherSmartPoints")
        private Integer weightWatcherSmartPoints;

        @JsonProperty("dishTypes")
        private List<String> dishTypes;

        @JsonProperty("extendedIngredients")
        private List<Object> extendedIngredients;

        @JsonProperty("summary")
        private String summary;

        @JsonProperty("winePairing")
        private Object winePairing;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
        public String getImageType() { return imageType; }
        public void setImageType(String imageType) { this.imageType = imageType; }
        public Integer getServings() { return servings; }
        public void setServings(Integer servings) { this.servings = servings; }
        public Integer getReadyInMinutes() { return readyInMinutes; }
        public void setReadyInMinutes(Integer readyInMinutes) { this.readyInMinutes = readyInMinutes; }
        public String getLicense() { return license; }
        public void setLicense(String license) { this.license = license; }
        public String getSourceName() { return sourceName; }
        public void setSourceName(String sourceName) { this.sourceName = sourceName; }
        public String getSourceUrl() { return sourceUrl; }
        public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }
        public String getSpoonacularSourceUrl() { return spoonacularSourceUrl; }
        public void setSpoonacularSourceUrl(String spoonacularSourceUrl) { this.spoonacularSourceUrl = spoonacularSourceUrl; }
        public Double getHealthScore() { return healthScore; }
        public void setHealthScore(Double healthScore) { this.healthScore = healthScore; }
        public Double getSpoonacularScore() { return spoonacularScore; }
        public void setSpoonacularScore(Double spoonacularScore) { this.spoonacularScore = spoonacularScore; }
        public Double getPricePerServing() { return pricePerServing; }
        public void setPricePerServing(Double pricePerServing) { this.pricePerServing = pricePerServing; }
        public List<Object> getAnalyzedInstructions() { return analyzedInstructions; }
        public void setAnalyzedInstructions(List<Object> analyzedInstructions) { this.analyzedInstructions = analyzedInstructions; }
        public Boolean getCheap() { return cheap; }
        public void setCheap(Boolean cheap) { this.cheap = cheap; }
        public String getCreditsText() { return creditsText; }
        public void setCreditsText(String creditsText) { this.creditsText = creditsText; }
        public List<String> getCuisines() { return cuisines; }
        public void setCuisines(List<String> cuisines) { this.cuisines = cuisines; }
        public Boolean getDairyFree() { return dairyFree; }
        public void setDairyFree(Boolean dairyFree) { this.dairyFree = dairyFree; }
        public List<String> getDiets() { return diets; }
        public void setDiets(List<String> diets) { this.diets = diets; }
        public String getGaps() { return gaps; }
        public void setGaps(String gaps) { this.gaps = gaps; }
        public Boolean getGlutenFree() { return glutenFree; }
        public void setGlutenFree(Boolean glutenFree) { this.glutenFree = glutenFree; }
        public String getInstructions() { return instructions; }
        public void setInstructions(String instructions) { this.instructions = instructions; }
        public Boolean getKetogenic() { return ketogenic; }
        public void setKetogenic(Boolean ketogenic) { this.ketogenic = ketogenic; }
        public Boolean getLowFodmap() { return lowFodmap; }
        public void setLowFodmap(Boolean lowFodmap) { this.lowFodmap = lowFodmap; }
        public List<String> getOccasions() { return occasions; }
        public void setOccasions(List<String> occasions) { this.occasions = occasions; }
        public Boolean getSustainable() { return sustainable; }
        public void setSustainable(Boolean sustainable) { this.sustainable = sustainable; }
        public Boolean getVegan() { return vegan; }
        public void setVegan(Boolean vegan) { this.vegan = vegan; }
        public Boolean getVegetarian() { return vegetarian; }
        public void setVegetarian(Boolean vegetarian) { this.vegetarian = vegetarian; }
        public Boolean getVeryHealthy() { return veryHealthy; }
        public void setVeryHealthy(Boolean veryHealthy) { this.veryHealthy = veryHealthy; }
        public Boolean getVeryPopular() { return veryPopular; }
        public void setVeryPopular(Boolean veryPopular) { this.veryPopular = veryPopular; }
        public Boolean getWhole30() { return whole30; }
        public void setWhole30(Boolean whole30) { this.whole30 = whole30; }
        public Integer getWeightWatcherSmartPoints() { return weightWatcherSmartPoints; }
        public void setWeightWatcherSmartPoints(Integer weightWatcherSmartPoints) { this.weightWatcherSmartPoints = weightWatcherSmartPoints; }
        public List<String> getDishTypes() { return dishTypes; }
        public void setDishTypes(List<String> dishTypes) { this.dishTypes = dishTypes; }
        public List<Object> getExtendedIngredients() { return extendedIngredients; }
        public void setExtendedIngredients(List<Object> extendedIngredients) { this.extendedIngredients = extendedIngredients; }
        public String getSummary() { return summary; }
        public void setSummary(String summary) { this.summary = summary; }
        public Object getWinePairing() { return winePairing; }
        public void setWinePairing(Object winePairing) { this.winePairing = winePairing; }
    }
}