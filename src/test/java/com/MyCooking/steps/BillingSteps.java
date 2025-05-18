package com.MyCooking.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;
import MyCooking.com.BillingSystem;
import MyCooking.com.models.*;

public class BillingSteps {

    private BillingSystem billingSystem = new BillingSystem();

    @Given("the customer completes an order")
    public void completeOrder() {
        billingSystem.completeOrder("Customer01"); 
    }

    @When("the order is finalized")
    public void finalizeOrder() {
        billingSystem.finalizeOrder();
    }

    @Then("the system should generate and send an invoice")
    public void generateInvoice() {
        billingSystem.generateAndSendInvoice("Customer01");
        assertTrue(!billingSystem.getInvoices().isEmpty());
    }

    @Given("the administrator logs into the system")
    public void adminLogin() {
        billingSystem.adminLogin("admin01"); 
    }


    @When("financial data is requested")
    public void requestFinancialData() {
        
    }

    @Then("the system should display up-to-date financial reports")
    public void displayFinancialReports() {
        billingSystem.displayFinancialReport();
        assertTrue(true);
    }
}





/*
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
    
    }

    @Then("the system should display up-to-date financial reports")
    public void displayFinancialReports() {
    	BillingSystem.displayFinancialReport();
        assertTrue(true);
    }
}
*/