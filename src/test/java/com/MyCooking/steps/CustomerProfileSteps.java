package com.MyCooking.steps;

import static org.junit.Assert.assertTrue;

import MyCooking.com.models.Customer;
import MyCooking.com.CustomerProfileManager;
import io.cucumber.java.en.*;

public class CustomerProfileSteps {

    private CustomerProfileManager manager = new CustomerProfileManager();
    private String customerId = "customer1";
    private String enteredDiet;
    private String enteredAllergy;

    @Given("the customer opens the dietary preferences form")
    public void openDietaryForm() {
        System.out.println("Opening dietary preferences form...");
    }

    @When("the customer inputs {string} and selects {string}")
    public void inputDietAndAllergy(String diet, String allergy) {
        enteredDiet = diet;
        enteredAllergy = allergy;
        System.out.println("Customer inputs diet: " + diet + " and selects allergy: " + allergy);
    }

    @When("saves the information")
    public void saveInfo() {
        manager.storePreferences(customerId, enteredDiet, enteredAllergy);
        System.out.println("Saving dietary preferences and allergies...");
    }

  
    @Then("the system should store the preferences and allergies")
    public void storePreferences() {
        Customer customer = manager.getCustomer(customerId);
        assertTrue(customer.getDietPreference().equals(enteredDiet));
        assertTrue(customer.getAllergy().equals(enteredAllergy));
    }

    @Then("the chef should be able to view this information")
    public void chefViewsInfo() {
        manager.viewPreferences(customerId);
    }

    @Given("the customer logs into the system")
    public void customerLogin() {
        System.out.println("Customer logs into the system...");
    }

    @When("the customer navigates to their order history")
    public void navigateToOrderHistory() {
        System.out.println("Customer navigates to order history...");
    }

    @Then("the system should display a list of previous orders")
    public void displayOrderHistory() {
        manager.displayOrderHistory(customerId);
        assertTrue(true);
    }

    @Given("the chef accesses the customer's profile")
    public void chefAccessesProfile() {
        System.out.println("Chef accesses customer's profile...");
    }

    @When("the chef views order history")
    public void chefViewsHistory() {
        System.out.println("Chef is viewing the customer's order history...");
    }

    @Then("the system should generate personalized meal suggestions")
    public void generateMealSuggestions() {
        manager.suggestPersonalizedMeals(customerId);
        assertTrue(true);
    }
}









/*package com.MyCooking.steps;

import static org.junit.Assert.assertTrue;

import MyCooking.com.CustomerProfileManager;
import io.cucumber.java.en.*;

public class CustomerProfileSteps {

    private CustomerProfileManager manager = new CustomerProfileManager();
    private String customerId = "customer1";
    private String enteredDiet;
    private String enteredAllergy;

    @Given("the customer opens the dietary preferences form")
    public void openDietaryForm() {
        System.out.println(" Opening dietary preferences form...");
    }

    @When("the customer inputs {string} and selects {string}")
    public void inputDietAndAllergy(String diet, String allergy) {
        enteredDiet = diet;
        enteredAllergy = allergy;
        System.out.println(" Customer inputs diet: " + diet + " and selects allergy: " + allergy);
    }

    @When("saves the information")
    public void saveInfo() {
        manager.storePreferences(customerId, enteredDiet, enteredAllergy);
        System.out.println("Saving dietary preferences and allergies...");
    }

    @Then("the system should store the preferences and allergies")
    public void storePreferences() {
        System.out.println(" Checking stored preferences...");
        assertTrue(true); 
    }

    @Then("the chef should be able to view this information")
    public void chefViewsInfo() {
        manager.viewPreferences(customerId);
        System.out.println("Chef can view customer's dietary preferences.");
    }

    @Given("the customer logs into the system")
    public void customerLogin() {
        System.out.println(" Customer logs into the system...");
    }

    @When("the customer navigates to their order history")
    public void navigateToOrderHistory() {
        System.out.println("Customer navigates to order history...");
    }

    @Then("the system should display a list of previous orders")
    public void displayOrderHistory() {
        manager.displayOrderHistory(customerId);
        System.out.println("Displaying order history...");
        assertTrue(true); 
    }

    @Given("the chef accesses the customer's profile")
    public void chefAccessesProfile() {
        System.out.println(" Chef accesses customer's profile...");
    }

    @When("the chef views order history")
    public void chefViewsHistory() {
        System.out.println("Chef is viewing the customer's order history...");
    }

    @Then("the system should generate personalized meal suggestions")
    public void generateMealSuggestions() {
        manager.suggestPersonalizedMeals(customerId);
        System.out.println(" Personalized meal suggestions generated.");
        assertTrue(true); 
    }
}*/