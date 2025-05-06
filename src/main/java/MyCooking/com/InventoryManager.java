package MyCooking.com;

import java.util.*;

public class InventoryManager {

    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Double> supplierPrices = new HashMap<>();

    public InventoryManager() {
        stock.put("Tomato", 50);
        stock.put("Cheese", 30);
        stock.put("Olive Oil", 20);
        supplierPrices.put("Tomato", 0.5);
        supplierPrices.put("Cheese", 1.2);
        supplierPrices.put("Olive Oil", 2.5);
    }

    public void monitorStock() {
        System.out.println("Current Stock Levels:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void useIngredient(String ingredient, int quantity) {
        if (stock.containsKey(ingredient)) {
            int currentStock = stock.get(ingredient);
            if (currentStock >= quantity) {
                stock.put(ingredient, currentStock - quantity);
                System.out.println(quantity + " units of " + ingredient + " used.");
            } else {
                System.out.println("Not enough " + ingredient + " in stock.");
            }
        } else {
            System.out.println("Ingredient not found.");
        }
    }

    public void checkForRestocking() {
        System.out.println("Checking for ingredients to restock:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() < 10) {
                System.out.println(entry.getKey() + " needs restocking.");
            }
        }
    }

    public void fetchSupplierPrices() {
        System.out.println("Supplier Prices:");
        for (Map.Entry<String, Double> entry : supplierPrices.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }

    public void generatePurchaseOrder() {
        System.out.println("Generating Purchase Order:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() < 10) {
                String ingredient = entry.getKey();
                int orderQty = 50 - entry.getValue();
                double price = supplierPrices.getOrDefault(ingredient, 1.0);
                double cost = price * orderQty;
                System.out.println("Ordering " + orderQty + " units of " + ingredient + " for $" + cost);
                stock.put(ingredient, 50);
            }
        }
    }

    public void checkStock(String ingredient) {
        if (stock.containsKey(ingredient)) {
            int qty = stock.get(ingredient);
            System.out.println("Stock for " + ingredient + ": " + qty + " units.");
        } else {
            System.out.println("Ingredient " + ingredient + " not found in stock.");
        }
    }

    public void suggestRestocking() {
        System.out.println("Suggesting restocking for low stock items:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() < 15) {
                System.out.println("- " + entry.getKey());
            }
        }
    }

    public void openPurchasingInterface() {
        System.out.println("Opening purchasing interface... Ready to place orders.");
    }

    public void detectCriticalStock() {
        System.out.println("Detecting critical stock levels:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() < 5) {
                System.out.println("CRITICAL: " + entry.getKey() + " is below critical level!");
            }
        }
    }
}
