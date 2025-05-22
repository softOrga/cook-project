package MyCooking.com.models;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerManagerTest {

    @Test
    void testLoadExistingCustomerProfile() throws IOException {
        String username = "testuser";
        File file = new File("customer_profiles/" + username + ".txt");
        file.getParentFile().mkdirs();
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("John Doe");
            writer.println("Peanuts");
            writer.println("Vegan");
        }

        CustomerManager manager = new CustomerManager();
        Customer customer = manager.getOrCreateCustomerProfile(new Scanner(System.in), username);

        assertEquals("testuser", customer.getUsername());
        assertEquals("John Doe", customer.getCustomerName());
        assertEquals("Peanuts", customer.getAllergy());
        assertEquals("Vegan", customer.getDietaryPreference());

        file.delete(); // cleanup
    }

    @Test
    void testCreateNewCustomerProfile() {
        String username = "newuser";
        String input = "Jane Doe\nDairy\nVegetarian\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        CustomerManager manager = new CustomerManager();
        Customer customer = manager.getOrCreateCustomerProfile(scanner, username);

        assertEquals("newuser", customer.getUsername());
        assertEquals("Jane Doe", customer.getCustomerName());
        assertEquals("Dairy", customer.getAllergy());
        assertEquals("Vegetarian", customer.getDietaryPreference());

        new File("customer_profiles/" + username + ".txt").delete(); // cleanup
    }

    @Test
    void testCreateCustomMeal() {
        String input = "Special Salad\nLettuce\nTomato\ndone\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Customer customer = new Customer("john", "John Doe");
        CustomerManager manager = new CustomerManager();
        Meal meal = manager.createCustomMeal(scanner, customer);

        assertEquals("Special Salad", meal.getName());
        assertEquals(2, meal.getIngredients().size());
    }
}
