package MyCooking.com.models;

public class SupplierManager {

    public void requestRestock(String ingredientName) {
        System.out.println("Low stock detected for ingredient: " + ingredientName);
        System.out.println(" Sending automatic restock request to supplier for: " + ingredientName);
        simulateSupplierResponse(ingredientName);
    }

    private void simulateSupplierResponse(String ingredientName) {
        System.out.println(" Supplier has confirmed restock of: " + ingredientName + " (expected delivery in 24 hours)");
    }
}
