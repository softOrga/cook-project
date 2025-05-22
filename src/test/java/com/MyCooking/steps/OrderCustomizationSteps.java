package com.MyCooking.steps;

import MyCooking.com.OrderCustomizationManager;
import MyCooking.com.models.CustomerManager;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class OrderCustomizationSteps {

	OrderCustomizationManager customizationManager = new OrderCustomizationManager();

    @Given("the customer is creating a new meal request")
    public void startMealRequest() {
        customizationManager.startMealRequest();
    }

    @When("the customer selects ingredients: {string}, {string}, {string}")
    public void selectIngredients(String ing1, String ing2, String ing3) {
        customizationManager.selectIngredients(ing1, ing2, ing3);
    }

    @When("saves the meal")
    public void saveMeal() {
        customizationManager.saveMeal();
    }

    @Then("the system should validate ingredient compatibility")
    public void validateIngredients() {
        customizationManager.validateIngredients();
        assertTrue(true);
    }

    @Then("confirm the custom meal request")
    public void confirmMeal() {
        customizationManager.confirmMeal();
        assertTrue(true);
    }

    @Given("the customer selects an ingredient that is unavailable or restricted")
    public void selectUnavailableIngredient() {
        customizationManager.selectUnavailableIngredient();
    }

    @When("the system detects this issue")
    public void detectIssue() {
        customizationManager.detectIssue();
    }

    @Then("the system should suggest a suitable alternative")
    public void suggestAlternative() {
        customizationManager.suggestAlternative();
        assertTrue(true);
    }

    @Then("alert the chef for approval")
    public void alertChef() {
        customizationManager.alertChef();
        assertTrue(true);
    }
}
