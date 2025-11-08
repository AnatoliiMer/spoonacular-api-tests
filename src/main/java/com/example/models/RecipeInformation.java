// src/main/java/com/example/models/RecipeInformation.java
package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeInformation {
    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("image")
    private String image;

    @JsonProperty("servings")
    private int servings;

    @JsonProperty("readyInMinutes")
    private int readyInMinutes;

    @JsonProperty("license")
    private String license;

    @JsonProperty("sourceName")
    private String sourceName;

    @JsonProperty("sourceUrl")
    private String sourceUrl;

    @JsonProperty("spoonacularSourceUrl")
    private String spoonacularSourceUrl;

    @JsonProperty("healthScore")
    private double healthScore;

    @JsonProperty("pricePerServing")
    private double pricePerServing;

    @JsonProperty("analyzedInstructions")
    private List<AnalyzedInstruction> analyzedInstructions;

    @JsonProperty("extendedIngredients")
    private List<ExtendedIngredient> extendedIngredients;

    // Дополнительные поля из реального ответа
    @JsonProperty("vegetarian")
    private boolean vegetarian;

    @JsonProperty("vegan")
    private boolean vegan;

    @JsonProperty("glutenFree")
    private boolean glutenFree;

    @JsonProperty("dairyFree")
    private boolean dairyFree;

    @JsonProperty("veryHealthy")
    private boolean veryHealthy;

    @JsonProperty("cheap")
    private boolean cheap;

    @JsonProperty("veryPopular")
    private boolean veryPopular;

    @JsonProperty("sustainable")
    private boolean sustainable;

    @JsonProperty("lowFodmap")
    private boolean lowFodmap;

    @JsonProperty("weightWatcherSmartPoints")
    private int weightWatcherSmartPoints;

    @JsonProperty("gaps")
    private String gaps;

    @JsonProperty("preparationMinutes")
    private Integer preparationMinutes;

    @JsonProperty("cookingMinutes")
    private Integer cookingMinutes;

    @JsonProperty("aggregateLikes")
    private int aggregateLikes;

    @JsonProperty("creditsText")
    private String creditsText;

    @JsonProperty("summary")
    private String summary;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public int getServings() { return servings; }
    public void setServings(int servings) { this.servings = servings; }
    public int getReadyInMinutes() { return readyInMinutes; }
    public void setReadyInMinutes(int readyInMinutes) { this.readyInMinutes = readyInMinutes; }
    public String getLicense() { return license; }
    public void setLicense(String license) { this.license = license; }
    public String getSourceName() { return sourceName; }
    public void setSourceName(String sourceName) { this.sourceName = sourceName; }
    public String getSourceUrl() { return sourceUrl; }
    public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }
    public String getSpoonacularSourceUrl() { return spoonacularSourceUrl; }
    public void setSpoonacularSourceUrl(String spoonacularSourceUrl) { this.spoonacularSourceUrl = spoonacularSourceUrl; }
    public double getHealthScore() { return healthScore; }
    public void setHealthScore(double healthScore) { this.healthScore = healthScore; }
    public double getPricePerServing() { return pricePerServing; }
    public void setPricePerServing(double pricePerServing) { this.pricePerServing = pricePerServing; }
    public List<AnalyzedInstruction> getAnalyzedInstructions() { return analyzedInstructions; }
    public void setAnalyzedInstructions(List<AnalyzedInstruction> analyzedInstructions) { this.analyzedInstructions = analyzedInstructions; }
    public List<ExtendedIngredient> getExtendedIngredients() { return extendedIngredients; }
    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) { this.extendedIngredients = extendedIngredients; }
    public boolean isVegetarian() { return vegetarian; }
    public void setVegetarian(boolean vegetarian) { this.vegetarian = vegetarian; }
    public boolean isVegan() { return vegan; }
    public void setVegan(boolean vegan) { this.vegan = vegan; }
    public boolean isGlutenFree() { return glutenFree; }
    public void setGlutenFree(boolean glutenFree) { this.glutenFree = glutenFree; }
    public boolean isDairyFree() { return dairyFree; }
    public void setDairyFree(boolean dairyFree) { this.dairyFree = dairyFree; }
    public boolean isVeryHealthy() { return veryHealthy; }
    public void setVeryHealthy(boolean veryHealthy) { this.veryHealthy = veryHealthy; }
    public boolean isCheap() { return cheap; }
    public void setCheap(boolean cheap) { this.cheap = cheap; }
    public boolean isVeryPopular() { return veryPopular; }
    public void setVeryPopular(boolean veryPopular) { this.veryPopular = veryPopular; }
    public boolean isSustainable() { return sustainable; }
    public void setSustainable(boolean sustainable) { this.sustainable = sustainable; }
    public boolean isLowFodmap() { return lowFodmap; }
    public void setLowFodmap(boolean lowFodmap) { this.lowFodmap = lowFodmap; }
    public int getWeightWatcherSmartPoints() { return weightWatcherSmartPoints; }
    public void setWeightWatcherSmartPoints(int weightWatcherSmartPoints) { this.weightWatcherSmartPoints = weightWatcherSmartPoints; }
    public String getGaps() { return gaps; }
    public void setGaps(String gaps) { this.gaps = gaps; }
    public Integer getPreparationMinutes() { return preparationMinutes; }
    public void setPreparationMinutes(Integer preparationMinutes) { this.preparationMinutes = preparationMinutes; }
    public Integer getCookingMinutes() { return cookingMinutes; }
    public void setCookingMinutes(Integer cookingMinutes) { this.cookingMinutes = cookingMinutes; }
    public int getAggregateLikes() { return aggregateLikes; }
    public void setAggregateLikes(int aggregateLikes) { this.aggregateLikes = aggregateLikes; }
    public String getCreditsText() { return creditsText; }
    public void setCreditsText(String creditsText) { this.creditsText = creditsText; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public static class AnalyzedInstruction {
        @JsonProperty("name")
        private String name;

        @JsonProperty("steps")
        private List<Step> steps;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public List<Step> getSteps() { return steps; }
        public void setSteps(List<Step> steps) { this.steps = steps; }
    }

    public static class Step {
        @JsonProperty("number")
        private int number;

        @JsonProperty("step")
        private String step;

        @JsonProperty("ingredients")
        private List<StepIngredient> ingredients;

        @JsonProperty("equipment")
        private List<StepEquipment> equipment;

        public int getNumber() { return number; }
        public void setNumber(int number) { this.number = number; }
        public String getStep() { return step; }
        public void setStep(String step) { this.step = step; }
        public List<StepIngredient> getIngredients() { return ingredients; }
        public void setIngredients(List<StepIngredient> ingredients) { this.ingredients = ingredients; }
        public List<StepEquipment> getEquipment() { return equipment; }
        public void setEquipment(List<StepEquipment> equipment) { this.equipment = equipment; }
    }

    public static class StepIngredient {
        @JsonProperty("id")
        private int id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("localizedName")
        private String localizedName;

        @JsonProperty("image")
        private String image;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getLocalizedName() { return localizedName; }
        public void setLocalizedName(String localizedName) { this.localizedName = localizedName; }
        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
    }

    public static class StepEquipment {
        @JsonProperty("id")
        private int id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("localizedName")
        private String localizedName;

        @JsonProperty("image")
        private String image;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getLocalizedName() { return localizedName; }
        public void setLocalizedName(String localizedName) { this.localizedName = localizedName; }
        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
    }

    public static class ExtendedIngredient {
        @JsonProperty("id")
        private int id;

        @JsonProperty("aisle")
        private String aisle;

        @JsonProperty("image")
        private String image;

        @JsonProperty("consistency")
        private String consistency;

        @JsonProperty("name")
        private String name;

        @JsonProperty("nameClean")
        private String nameClean;

        @JsonProperty("original")
        private String original;

        @JsonProperty("originalName")
        private String originalName;

        @JsonProperty("amount")
        private double amount;

        @JsonProperty("unit")
        private String unit;

        @JsonProperty("meta")
        private List<String> meta;

        @JsonProperty("measures")
        private Measures measures;

        // Getters and Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getAisle() { return aisle; }
        public void setAisle(String aisle) { this.aisle = aisle; }
        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
        public String getConsistency() { return consistency; }
        public void setConsistency(String consistency) { this.consistency = consistency; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getNameClean() { return nameClean; }
        public void setNameClean(String nameClean) { this.nameClean = nameClean; }
        public String getOriginal() { return original; }
        public void setOriginal(String original) { this.original = original; }
        public String getOriginalName() { return originalName; }
        public void setOriginalName(String originalName) { this.originalName = originalName; }
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public String getUnit() { return unit; }
        public void setUnit(String unit) { this.unit = unit; }
        public List<String> getMeta() { return meta; }
        public void setMeta(List<String> meta) { this.meta = meta; }
        public Measures getMeasures() { return measures; }
        public void setMeasures(Measures measures) { this.measures = measures; }
    }

    public static class Measures {
        @JsonProperty("us")
        private Measure us;

        @JsonProperty("metric")
        private Measure metric;

        public Measure getUs() { return us; }
        public void setUs(Measure us) { this.us = us; }
        public Measure getMetric() { return metric; }
        public void setMetric(Measure metric) { this.metric = metric; }
    }

    public static class Measure {
        @JsonProperty("amount")
        private double amount;

        @JsonProperty("unitShort")
        private String unitShort;

        @JsonProperty("unitLong")
        private String unitLong;

        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public String getUnitShort() { return unitShort; }
        public void setUnitShort(String unitShort) { this.unitShort = unitShort; }
        public String getUnitLong() { return unitLong; }
        public void setUnitLong(String unitLong) { this.unitLong = unitLong; }
    }
}