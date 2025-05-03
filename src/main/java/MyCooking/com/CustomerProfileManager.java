package MyCooking.com;

import java.util.*;

public class CustomerProfileManager {

    private static class CustomerProfile {
        String diet;
        String allergy;
        List<String> orderHistory = new ArrayList<>();

        public CustomerProfile(String diet, String allergy) {
            this.diet = diet;
            this.allergy = allergy;
        }
    }

    private Map<String, CustomerProfile> customerProfiles = new HashMap<>();

    public void storePreferences(String customerId, String diet, String allergy) {
        CustomerProfile profile = customerProfiles.getOrDefault(customerId, new CustomerProfile(diet, allergy));
        profile.diet = diet;
        profile.allergy = allergy;
        customerProfiles.put(customerId, profile);
        System.out.println("Preferences and allergies saved for customer " + customerId);
    }

    public void addOrder(String customerId, String order) {
        CustomerProfile profile = customerProfiles.get(customerId);
        if (profile == null) {
            System.out.println(" Cannot add order. Customer not found.");
            return;
        }
        profile.orderHistory.add(order);
        System.out.println(" Order added for " + customerId + ": " + order);
    }

    public void displayOrderHistory(String customerId) {
        CustomerProfile profile = customerProfiles.get(customerId);
        if (profile == null || profile.orderHistory.isEmpty()) {
            System.out.println(" No past orders found for customer " + customerId);
            return;
        }
        System.out.println(" Order history for customer " + customerId + ":");
        for (String order : profile.orderHistory) {
            System.out.println("- " + order);
        }
    }

    public void viewPreferences(String customerId) {
        CustomerProfile profile = customerProfiles.get(customerId);
        if (profile == null) {
            System.out.println(" No preferences found for customer " + customerId);
            return;
        }
        System.out.println(" Preferences for customer " + customerId + ":");
        System.out.println("- Diet: " + profile.diet);
        System.out.println("- Allergy: " + profile.allergy);
    }

    public void suggestPersonalizedMeals(String customerId) {
        CustomerProfile profile = customerProfiles.get(customerId);
        if (profile == null || profile.orderHistory.isEmpty()) {
            System.out.println(" No data available to generate meal suggestions.");
            return;
        }

        System.out.println(" Personalized meal suggestions based on past orders:");
        Set<String> uniqueIngredients = new HashSet<>();
        for (String order : profile.orderHistory) {
            String[] parts = order.split(", ");
            uniqueIngredients.addAll(Arrays.asList(parts));
        }
        System.out.println(" Try combining: " + String.join(", ", uniqueIngredients));
    }
}