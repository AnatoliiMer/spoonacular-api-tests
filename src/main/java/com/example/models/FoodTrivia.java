// src/main/java/com/example/models/FoodTrivia.java
package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodTrivia {
    @JsonProperty("text")
    private String text;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}