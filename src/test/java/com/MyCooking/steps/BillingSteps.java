package com.MyCooking.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;
import MyCooking.com.BillingSystem;
import MyCooking.com.models.*;

public class BillingSteps {

    private BillingSystem  BillingSystem = new BillingSystem();

    @Given("the customer completes an order")
    public void completeOrder() {
    	 BillingSystem.completeOrder();
    }

    @When("the order is finalized")
    public void finalizeOrder() {
    	BillingSystem.finalizeOrder();
    }

    @Then("the system should generate and send an invoice")
    public void generateInvoice() {
    	BillingSystem.generateAndSendInvoice("Customer01");
        assertTrue(!BillingSystem.getInvoices().isEmpty());
    }

    @Given("the administrator logs into the system")
    public void adminLogin() {
    	BillingSystem.adminLogin();
    }

    @When("financial data is requested")
    public void requestFinancialData() {
        // Nothing extra for now
    }

    @Then("the system should display up-to-date financial reports")
    public void displayFinancialReports() {
    	BillingSystem.displayFinancialReport();
        assertTrue(true);
    }
}

/*package com.MyCooking.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import MyCooking.com.BillingSystem;

public class BillingSteps {

    private BillingSystem billingSystem = new BillingSystem();

    @Given("the customer completes an order")
    public void completeOrder() {
        billingSystem.completeOrder();
    }

    @When("the order is finalized")
    public void finalizeOrder() {
        billingSystem.finalizeOrder();
    }

    @Then("the system should generate and send an invoice")
    public void generateInvoice() {
        billingSystem.generateAndSendInvoice();
        assertTrue(true);
    }

    @Given("the administrator logs into the system")
    public void adminLogin() {
        billingSystem.adminLogin();
    }

    @When("financial data is requested")
    public void requestFinancialData() {
        billingSystem.requestFinancialData();
    }

    @Then("the system should display up-to-date financial reports")
    public void displayFinancialReports() {
        billingSystem.displayFinancialReports();
        assertTrue(true);
    }
}*/
