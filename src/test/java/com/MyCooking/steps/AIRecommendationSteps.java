package com.MyCooking.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;

public class AIRecommendationSteps {

    private boolean isVegan = false;
    private boolean hasTomatoes = false;
    private boolean hasBasil = false;
    private boolean hasPasta = false;
    private int timeAvailable = 0;
    private String recommendedRecipe = "";
    private String recommendationExplanation = "";

   
    @Given("the customer is vegan")
    public void isVegan() {
        this.isVegan = true;
    }

 
    @Given("has tomatoes, basil, and pasta")
    public void hasIngredients() {
        this.hasTomatoes = true;
        this.hasBasil = true;
        this.hasPasta = true;
    }

   
    @Given("has 30 minutes available")
    public void hasTime() {
        this.timeAvailable = 30;
    }

 
    @When("the AI assistant is asked for a recipe")
    public void askAI() {
        if (isVegan && hasTomatoes && hasBasil && hasPasta && timeAvailable >= 30) {
            recommendedRecipe = "Spaghetti with Tomato Sauce";
            recommendationExplanation = "This recipe is vegan and takes less than 30 minutes to prepare, fitting dietary and time needs.";
        }
    }

   
    @Then("it should recommend {string}")
    public void recommendRecipe(String recipe) {
        assertEquals(recipe, recommendedRecipe);
    }

    
    @Then("explain that it fits dietary needs and time limit")
    public void explainRecommendation() {
        assertEquals("This recipe is vegan and takes less than 30 minutes to prepare, fitting dietary and time needs.", recommendationExplanation);
    }
}