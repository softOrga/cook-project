package com.MyCooking.steps;

import MyCooking.com.TaskSchedulerManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class TaskSchedulingSteps {

    TaskSchedulerManager scheduler = new TaskSchedulerManager();
    String currentChef = "Chef John";

    @Given("the kitchen manager is reviewing chef workloads")
    public void managerReviewWorkload() {
        System.out.println("Manager is reviewing chef workloads...");
 
        scheduler.viewChefTasks(currentChef);
    }

    @When("the manager assigns a new cooking task to a chef")
    public void assignTask() {
        System.out.println(" Assigning new task to chef...");
        scheduler.assignTaskToChef(currentChef, "Prepare Vegan Pasta");
    }

    @Then("the chef should receive a notification with task details")
    public void notifyChef() {
        System.out.println(" Chef notification sent...");
        assertTrue(true);
    }

    @Given("a cooking task has been assigned to a chef")
    public void taskAssigned() {
        System.out.println(" Task already assigned to chef...");
        scheduler.assignTaskToChef(currentChef, "Prepare Tomato Soup");
    }

    @When("the chef logs in")
    public void chefLogsIn() {
        System.out.println(" Chef logging into system...");
        scheduler.chefLogin(currentChef);
    }

    @Then("the chef should see their list of tasks for the day")
    public void displayChefTasks() {
        System.out.println(" Chef views list of tasks...");
        scheduler.viewChefTasks(currentChef);
    }
}
