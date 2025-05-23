package MyCooking.com;

import MyCooking.com.models.Customer;
import MyCooking.com.models.Order;

import java.util.HashMap;
import java.util.Map;

public class CustomerProfileManager {

    private Map<String, Customer> customers = new HashMap<>();

    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerName(), customer);
    }

    public void storePreferences(String customerId, String diet, String allergy) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            customer.setPreferences(diet);
            customer.setAllergy(allergy);
        }
    }

    public void viewPreferences(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            System.out.println("Dietary Preference: " + customer.getDietaryPreference());
            System.out.println("Allergy: " + customer.getAllergy());
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void displayOrderHistory(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            System.out.println("Order History for " + customer.getCustomerName() + ":");
            for (String order : customer.getOrderHistory()) {
                System.out.println("- " + order);
            }
        } else {
            System.out.println("Customer not found.");
        }
    }
    public void suggestPersonalizedMeals(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            System.out.println("Suggesting personalized meals based on the customer's preferences:");
            System.out.println("Diet: " + customer.getDietaryPreference());
            System.out.println("Allergy: " + customer.getAllergy());
        }
    }
    public Customer getCustomerByName(String name) {
        return customers.get(name);
    }

}
  