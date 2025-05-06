package MyCooking.com;

import MyCooking.com.models.Customer;
import MyCooking.com.models.Order;

import java.util.HashMap;
import java.util.Map;

public class CustomerProfileManager {
    private Map<String, Customer> customers = new HashMap<>();

    public void storePreferences(String customerId, String diet, String allergy) {
        Customer customer = customers.getOrDefault(customerId, new Customer(customerId, "Unknown", "Unknown"));
        customer.setDietPreference(diet);
        customer.setAllergy(allergy);
        customers.put(customerId, customer);
    }

    public Customer getCustomer(String id) {
        return customers.get(id);
    }

    public void displayOrderHistory(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            for (Order order : customer.getOrderHistory()) {
                order.displayOrderDetails();
            }
        } else {
            System.out.println("No order history found.");
        }
    }

    public void suggestPersonalizedMeals(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            System.out.println("Suggested meals for " + customer.getDietPreference() + " and allergy: " + customer.getAllergy());
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void viewPreferences(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            System.out.println("Dietary Preference: " + customer.getDietPreference());
            System.out.println("Allergy: " + customer.getAllergy());
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Utility for test setup
    public void addTestOrders(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            customer.addOrder(new Order("Vegan Salad", customer.getName(), customer.getPhoneNumber()));
            customer.addOrder(new Order("Gluten-Free Pasta", customer.getName(), customer.getPhoneNumber()));
        }
    }
}


/*package MyCooking.com;

import java.util.HashMap;
import java.util.Map;

import MyCooking.com.models.Customer;
import MyCooking.com.models.Order;

public class CustomerProfileManager {
    private Map<String, Customer> customers = new HashMap<>();

    public void storePreferences(String customerId, String diet, String allergy) {
        Customer customer = customers.getOrDefault(customerId, new Customer(customerId));
        customer.setDietPreference(diet);
        customer.setAllergy(allergy);
        customers.put(customerId, customer);
    }

    public Customer getCustomer(String id) {
        return customers.get(id);
    }

    public void displayOrderHistory(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            for (Order order : customer.getOrderHistory()) {
                System.out.println("Order: " + order.getDescription());
            }
        } else {
            System.out.println("No order history found.");
        }
    }

    public void suggestPersonalizedMeals(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            System.out.println("Suggested meals for " + customer.getDietPreference() + " and allergy: " + customer.getAllergy());
            // Logic to suggest meals can be added here
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void viewPreferences(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            System.out.println("Dietary Preference: " + customer.getDietPreference());
            System.out.println("Allergy: " + customer.getAllergy());
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Utility for test setup
    public void addTestOrders(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            customer.addOrder(new Order("Vegan Salad"));
            customer.addOrder(new Order("Gluten-Free Pasta"));
        }
    }
}
*/


/*
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
            System.out.println("Cannot add order. Customer not found.");
            return;
        }
        profile.orderHistory.add(order);
        System.out.println("Order added for " + customerId + ": " + order);
    }

    public void displayOrderHistory(String customerId) {
        CustomerProfile profile = customerProfiles.get(customerId);
        if (profile == null || profile.orderHistory.isEmpty()) {
            System.out.println("No past orders found for customer " + customerId);
            return;
        }
        System.out.println("Order history for customer " + customerId + ":");
        for (String order : profile.orderHistory) {
            System.out.println("- " + order);
        }
    }

    public void viewPreferences(String customerId) {
        CustomerProfile profile = customerProfiles.get(customerId);
        if (profile == null) {
            System.out.println("No preferences found for customer " + customerId);
            return;
        }
        System.out.println("Preferences for customer " + customerId + ":");
        System.out.println("- Diet: " + profile.diet);
        System.out.println("- Allergy: " + profile.allergy);
    }

    public void suggestPersonalizedMeals(String customerId) {
        CustomerProfile profile = customerProfiles.get(customerId);
        if (profile == null || profile.orderHistory.isEmpty()) {
            System.out.println("No data available to generate meal suggestions.");
            return;
        }
        System.out.println("Personalized meal suggestions based on past orders:");
        Set<String> uniqueIngredients = new HashSet<>();
        for (String order : profile.orderHistory) {
            String[] parts = order.split(",\\s*");
            uniqueIngredients.addAll(Arrays.asList(parts));
        }
        System.out.println("Try combining: " + String.join(", ", uniqueIngredients));
    }
}
*/