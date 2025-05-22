
package MyCooking.com;

import MyCooking.com.models.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerProfileManagerTest {

    private CustomerProfileManager manager;

    @Before
    public void setup() {
        manager = new CustomerProfileManager();

        Customer customer1 = new Customer("john_doe", "John Doe");
        customer1.setPreferences("Vegan");
        customer1.setAllergy("Peanuts");
        customer1.addOrder("Vegan Salad");
        customer1.addOrder("Fruit Bowl");

        Customer customer2 = new Customer("jane_smith", "Jane Smith");
        customer2.setPreferences("Gluten-Free");
        customer2.setAllergy("None");
        customer2.addOrder("Gluten-Free Bread");

        manager.addCustomer(customer1);
        manager.addCustomer(customer2);
    }

    @Test
    public void testGetCustomerByName_found() {
        Customer customer = manager.getCustomerByName("John Doe");
        assertNotNull(customer);
        assertEquals("john_doe", customer.getUsername());
    }

    @Test
    public void testGetCustomerByName_notFound() {
        Customer customer = manager.getCustomerByName("Non Existent");
        assertNull(customer);
    }

    @Test
    public void testStoreAndViewPreferences() {
        manager.storePreferences("Jane Smith", "Vegetarian", "None");

        Customer jane = manager.getCustomerByName("Jane Smith");
        assertEquals("Vegetarian", jane.getDietaryPreference());
        assertEquals("None", jane.getAllergy());
    }

    @Test
    public void testDisplayOrderHistory() {
        // just call to check no exceptions (prints output)
        manager.displayOrderHistory("John Doe");
    }

    @Test
    public void testSuggestPersonalizedMeals() {
        // just call to check no exceptions (prints output)
        manager.suggestPersonalizedMeals("John Doe");
    }
}
