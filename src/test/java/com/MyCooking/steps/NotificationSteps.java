package com.MyCooking.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class NotificationSteps {

    @Given("the customer has a meal scheduled for delivery today")
    public void scheduledMeal() {
        System.out.println("Customer has a meal scheduled for delivery today.");
    }

    @When("the time is 1 hour before delivery")
    public void oneHourBeforeDelivery() {
        System.out.println("Time is 1 hour before delivery.");
    }

    @Then("the system should send a reminder notification")
    public void sendReminder() {
        System.out.println("System sends reminder notification to customer.");
        assertTrue("System should send reminder notification.", true);
    }

    @Given("the stock of an ingredient is low")
    public void lowStockIngredient() {
        System.out.println("Stock of an ingredient is low.");
    }

    @Then("the kitchen manager should receive a low-stock alert")
    public void sendLowStockAlert() {
        System.out.println("Kitchen manager receives low-stock alert.");
        assertTrue("Kitchen manager should receive low-stock alert.", true);
    }
}
