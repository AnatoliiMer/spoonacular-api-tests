// src/main/java/com/example/models/IngredientSearch.java
package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientSearch {
    @JsonProperty("results")
    private List<Ingredient> results;

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("number")
    private int number;

    @JsonProperty("totalResults")
    private int totalResults;

    public List<Ingredient> getResults() { return results; }
    public void setResults(List<Ingredient> results) { this.results = results; }
    public int getOffset() { return offset; }
    public void setOffset(int offset) { this.offset = offset; }
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public int getTotalResults() { return totalResults; }
    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }

    public static class Ingredient {
        @JsonProperty("id")
        private int id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("image")
        private String image;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
    }
}