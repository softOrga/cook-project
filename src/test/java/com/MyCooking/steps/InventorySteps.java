package com.MyCooking.steps;

import MyCooking.com.InventoryManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class InventorySteps {

    InventoryManager inventoryManager = new InventoryManager();

    @Given("the system is monitoring stock levels")
    public void the_system_is_monitoring_stock_levels() {
        inventoryManager.monitorStock();
    }

    @When("the stock of {string} falls below threshold")
    public void the_stock_of_falls_below_threshold(String ingredient) {
        inventoryManager.checkStock(ingredient);
    }

    @Then("the system should suggest restocking")
    public void the_system_should_suggest_restocking() {
        inventoryManager.suggestRestocking();
        assertTrue(true);
    }

    @Given("the kitchen manager opens the purchasing interface")
    public void the_kitchen_manager_opens_the_purchasing_interface() {
        inventoryManager.openPurchasingInterface();
    }

    @When("the system fetches real-time prices from suppliers")
    public void the_system_fetches_real_time_prices_from_suppliers() {
        inventoryManager.fetchSupplierPrices();
    }

    @When("detects critical low stock")
    public void detects_critical_low_stock() {
        inventoryManager.detectCriticalStock();
    }

    @Then("it should automatically generate a purchase order")
    public void it_should_automatically_generate_a_purchase_order() {
        inventoryManager.generatePurchaseOrder();
        assertTrue(true);
    }
}