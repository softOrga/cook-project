package MyCooking.com;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private Map<String, Integer> inventory = new HashMap<>();

    public InventoryManager() {
        inventory.put("Tomatoes", 5);
        inventory.put("Basil", 2);
        inventory.put("Pasta", 10);
    }

    public void monitorStock() {
        System.out.println("Monitoring current inventory levels...");
        inventory.forEach((ingredient, quantity) ->
                System.out.println("- " + ingredient + ": " + quantity + " units"));
    }

    public void checkStock(String ingredient) {
        Integer quantity = inventory.getOrDefault(ingredient, 0);
        if (quantity <= 3) { 
            System.out.println("Stock of " + ingredient + " is low (" + quantity + " units)!");
        } else {
            System.out.println(" Stock of " + ingredient + " is sufficient (" + quantity + " units).");
        }
    }

    public void suggestRestocking() {
        System.out.println("Checking ingredients that need restocking...");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() <= 3) {
                System.out.println("Suggest restocking: " + entry.getKey());
            }
        }
    }

    public void openPurchasingInterface() {
        System.out.println("Purchasing interface is now open for manager.");
    }

    public void fetchSupplierPrices() {
        System.out.println("Fetching real-time supplier prices...");
    }

    public void detectCriticalStock() {
        System.out.println("Detecting critical stock levels...");
        for (String ingredient : inventory.keySet()) {
            if (inventory.get(ingredient) <= 2) {
                System.out.println("Critical low stock detected for: " + ingredient);
            }
        }
    }

    public void generatePurchaseOrder() {
        System.out.println("Generating automatic purchase order for low stock ingredients...");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() <= 2) {
                System.out.println("Purchase order generated for: " + entry.getKey());
            }
        }
    }
    public void useIngredient(String ingredient, int quantity) {
        if (inventory.containsKey(ingredient)) {
            int currentStock = inventory.get(ingredient);
            if (currentStock >= quantity) {
                inventory.put(ingredient, currentStock - quantity);
                System.out.println(" Used " + quantity + " units of " + ingredient + ".");
            } else {
                System.out.println(" Not enough " + ingredient + " in stock to use " + quantity + " units.");
            }
        } else {
            System.out.println("Ingredient not found in inventory: " + ingredient);
        }
    }

    public void checkForRestocking() {
        boolean restockingNeeded = false;
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() <= 3) {
                System.out.println(" Restocking needed for: " + entry.getKey());
                restockingNeeded = true;
            }
        }
        if (!restockingNeeded) {
            System.out.println(" No restocking needed at the moment.");
        }
}}