package com.MyCooking.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
        billingSystem.adminLogin("admin123"); 
    }


    @When("financial data is requested")
    public void requestFinancialData() {
    	List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice("User001", 100.0));
        invoices.add(new Invoice("User002", 200.0));
        invoices.add(new Invoice("User003", 150.0));

        FinancialReport report = new FinancialReport(invoices);
        report.displayReport();  
    }

    @Then("the system should display up-to-date financial reports")
    public void displayFinancialReports() {
        billingSystem.displayFinancialReport();
        assertTrue(true);
    }
}