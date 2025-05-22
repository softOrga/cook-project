package MyCooking.com;

import java.util.*;

import MyCooking.com.models.Supplier;
import MyCooking.com.models.SupplierService;

public class InventoryManager {
    private final Map<String, Integer> stock = new HashMap<>();
    private final SupplierService supplierService = new SupplierService();
    private final List<String> purchaseOrders = new ArrayList<>();

    private boolean isMonitoring = false;  
    private List<String> lastCriticalItems = new ArrayList<>();  

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
        isMonitoring = true;
        System.out.println("Stock monitoring initialized.");
    }

    public boolean isMonitoring() {
        return isMonitoring;
    }

    public void viewStock() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public boolean checkStock(String ingredient) {
        int quantity = stock.getOrDefault(ingredient, 0);
        if (quantity < 3) {
            System.out.println("Low stock alert for: " + ingredient);
            return true;
        } else {
            System.out.println("Stock level for " + ingredient + " is sufficient.");
            return false;
        }
    }

    public List<String> checkLowStockAndSuggestRestock() {
        System.out.println("\nChecking for low stock...");
        List<String> lowStockItems = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() < 3) {
                System.out.println("Restock suggested for: " + entry.getKey());
                lowStockItems.add(entry.getKey());
            }
        }
        if (lowStockItems.isEmpty()) {
            System.out.println("All items are sufficiently stocked.");
        }
        return lowStockItems;
    }

    public List<String> suggestRestocking() {
        System.out.println("\nSuggested restocks:");
        List<String> suggested = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() < 3) {
                String ingredient = entry.getKey();
                Supplier supplier = supplierService.getBestSupplier(ingredient);
                System.out.println("- " + ingredient + " is low. Best supplier: " + supplier.getName() +
                        " | Price: $" + supplier.getPrice() +
                        " | Delivery in: " + supplier.getDeliveryDays() + " days");
                suggested.add(ingredient);
            }
        }
        if (suggested.isEmpty()) {
            System.out.println("No restocks needed at this time.");
        }
        return suggested;
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

    public Map<String, Double> fetchSupplierPrices() {
        System.out.println("\nReal-time supplier prices:");
        Map<String, Double> prices = new HashMap<>();
        for (String ingredient : stock.keySet()) {
            double price = supplierService.getRealTimePrice(ingredient);
            prices.put(ingredient, price);
            System.out.println("- " + ingredient + ": $" + price);
        }
        return prices;
    }

    public List<String> detectCriticalStock() {
        lastCriticalItems.clear();
        System.out.println("\nDetecting critically low stock...");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            if (entry.getValue() <= 2) {
                System.out.println("CRITICAL: " + entry.getKey() + " is almost out of stock!");
                lastCriticalItems.add(entry.getKey());
            }
        }
        if (lastCriticalItems.isEmpty()) {
            System.out.println("No critical items found.");
        }
        return new ArrayList<>(lastCriticalItems);
    }

    public List<String> getLastCriticalItems() {
        return new ArrayList<>(lastCriticalItems);
    }

    public List<String> generatePurchaseOrder() {
        purchaseOrders.clear();
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
        if (purchaseOrders.isEmpty()) {
            System.out.println("No purchase orders needed.");
        }
        return new ArrayList<>(purchaseOrders);
    }

    public List<String> getPurchaseOrders() {
        return new ArrayList<>(purchaseOrders);
    }

    public boolean useIngredient(String ingredient) {
        if (stock.containsKey(ingredient)) {
            int quantity = stock.get(ingredient);
            if (quantity > 0) {
                stock.put(ingredient, quantity - 1);
                System.out.println("Used one unit of " + ingredient + ". Remaining: " + (quantity - 1));
                return true;
            } else {
                System.out.println("Warning: Ingredient '" + ingredient + "' is out of stock!");
                return false;
            }
        } else {
            System.out.println("Warning: Ingredient '" + ingredient + "' not found in inventory!");
            return false;
        }
    }
}


