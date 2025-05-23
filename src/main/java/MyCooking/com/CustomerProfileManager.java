package MyCooking.com;

import MyCooking.com.models.Customer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerProfileManager {
    private static final Logger logger = Logger.getLogger(CustomerProfileManager.class.getName());
    private Map<String, Customer> customers = new HashMap<>();

    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerName(), customer);
    }

    public void storePreferences(String customerId, String diet, String allergy) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            customer.setPreferences(diet);
            customer.setAllergy(allergy);
            logger.log(Level.INFO, "Stored preferences for customer {0}: diet={1}, allergy={2}",
                       new Object[]{customerId, diet, allergy});
        } else {
            logger.log(Level.WARNING, "Attempted to store preferences for non-existing customer ID: {0}", customerId);
        }
    }

    public void viewPreferences(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            logger.log(Level.INFO, "Dietary Preference: {0}", customer.getDietaryPreference());
            logger.log(Level.INFO, "Allergy: {0}", customer.getAllergy());
        } else {
            logger.log(Level.WARNING, "Customer not found with ID: {0}", customerId);
        }
    }

    public void displayOrderHistory(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            logger.log(Level.INFO, "Order History for {0}:", customer.getCustomerName());
            for (String order : customer.getOrderHistory()) {
                logger.log(Level.INFO, "- {0}", order);
            }
        } else {
            logger.log(Level.WARNING, "Customer not found with ID: {0}", customerId);
        }
    }

    public void suggestPersonalizedMeals(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            logger.log(Level.INFO, "Suggesting personalized meals based on the customer's preferences:");
            logger.log(Level.INFO, "Diet: {0}", customer.getDietaryPreference());
            logger.log(Level.INFO, "Allergy: {0}", customer.getAllergy());
        } else {
            logger.log(Level.WARNING, "Customer not found with ID: {0}", customerId);
        }
    }

    public Customer getCustomerByName(String name) {
        return customers.get(name);
    }
}
