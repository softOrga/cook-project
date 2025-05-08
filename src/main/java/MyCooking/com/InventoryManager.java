package MyCooking.com;

import MyCooking.com.models.*;

import java.util.*;

public class InventoryManager {
    private final Map<String, Integer> stock = new HashMap<>();
    private final SupplierService supplierService = new SupplierService();
    private final List<String> purchaseOrders = new ArrayList<>();

    public InventoryManager() {
        stock.put("tomato", 5);
        stock.put("onion", 4);
        stock.put("cheese", 2);
        stock.put("chicken", 3);
        stock.put("tofu", 6);
        stock.put("milk", 2);
        stock.put("almond milk", 8);
    }

    public void monitorStock() {
        System.out.println("Stock monitoring initialized.");
    }

    public void viewStock() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public void checkStock(String ingredient) {
        int quantity = stock.getOrDefault(ingredient, 0);
        if (quantity < 3) {
            System.out.println("Low stock alert for: " + ingredient);
        } else {
            System.out.println("Stock level for " + ingredient + " is sufficient.");
        }
    }

    public void checkLowStockAndSuggestRestock() {
        System.out.println("\nChecking for low stock...");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() < 3) {
                System.out.println("Restock suggested for: " + entry.getKey());
            }
        }
    }

    public void suggestRestocking() {
        System.out.println("\nSuggested restocks:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() < 3) {
                String ingredient = entry.getKey();
                Supplier supplier = supplierService.getBestSupplier(ingredient);
                System.out.println("- " + ingredient + " is low. Best supplier: " + supplier.getName() +
                        " | Price: $" + supplier.getPrice() +
                        " | Delivery in: " + supplier.getDeliveryDays() + " days");
            }
        }
    }

    public void openPurchasingInterface() {
        System.out.println("\n--- Purchasing Interface ---");
        for (String ingredient : stock.keySet()) {
            Supplier supplier = supplierService.getBestSupplier(ingredient);
            System.out.println("Ingredient: " + ingredient +
                    " | Price: $" + supplier.getPrice() +
                    " | Supplier: " + supplier.getName() +
                    " | Delivery in: " + supplier.getDeliveryDays() + " days");
        }
    }

    public void fetchSupplierPrices() {
        System.out.println("\nReal-time supplier prices:");
        for (String ingredient : stock.keySet()) {
            double price = supplierService.getRealTimePrice(ingredient);
            System.out.println("- " + ingredient + ": $" + price);
        }
    }

    public List<String> detectCriticalStock() {
        List<String> criticalItems = new ArrayList<>();
        System.out.println("\nDetecting critically low stock...");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() <= 2) {
                System.out.println("CRITICAL: " + entry.getKey() + " is almost out of stock!");
                criticalItems.add(entry.getKey());
            }
        }
        return criticalItems;
    }

    public void generatePurchaseOrder() {
        System.out.println("\nGenerating purchase order for low/critical ingredients...");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() < 3) {
                String ingredient = entry.getKey();
                Supplier supplier = supplierService.getBestSupplier(ingredient);
                String order = "Order: " + ingredient +
                        " | From: " + supplier.getName() +
                        " | Price: $" + supplier.getPrice() +
                        " | ETA: " + supplier.getDeliveryDays() + " days";
                purchaseOrders.add(order);
                System.out.println(order);
            }
        }
    }

    public void viewPurchaseOrders() {
        System.out.println("\nAll Generated Purchase Orders:");
        if (purchaseOrders.isEmpty()) {
            System.out.println("No purchase orders have been generated.");
        } else {
            for (String order : purchaseOrders) {
                System.out.println("- " + order);
            }
        }
    }

    public void useIngredient(String ingredient) {
        if (stock.containsKey(ingredient)) {
            int quantity = stock.get(ingredient);
            if (quantity > 0) {
                stock.put(ingredient, quantity - 1);
            } else {
                System.out.println("Warning: Ingredient '" + ingredient + "' is out of stock!");
            }
        } else {
            System.out.println("Warning: Ingredient '" + ingredient + "' not found in inventory!");
        }
    }
}


