package com.MyCooking.steps;

import MyCooking.com.NotificationManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

import java.util.List;

public class NotificationSteps {

    private final NotificationManager notificationManager = new NotificationManager();

    @Given("the customer has a meal scheduled for delivery today")
    public void scheduledMeal() {
        // Setup context: simulated
        System.out.println("Customer has a meal scheduled for delivery today.");
    }

    @When("the time is 1 hour before delivery")
    public void oneHourBeforeDelivery() {
        // Send reminder now
        notificationManager.sendDeliveryReminder("Customer01");
    }

    @Then("the system should send a reminder notification")
    public void sendReminder() {
        List<String> notifications = notificationManager.getNotifications();
        boolean found = notifications.stream().anyMatch(msg -> msg.contains("Reminder: Dear customer Customer01"));
        assertTrue("Reminder notification should be sent", found);
    }

    @Given("the stock of an ingredient is low")
    public void lowStockIngredient() {
        // Simulate triggering low stock
        notificationManager.sendLowStockAlert("onion");
    }

    @Then("the kitchen manager should receive a low-stock alert")
    public void sendLowStockAlert() {
        List<String> notifications = notificationManager.getNotifications();
        boolean found = notifications.stream().anyMatch(msg -> msg.contains("ALERT: Low stock detected for ingredient: onion"));
        assertTrue("Low-stock alert should be sent", found);
    }
}
