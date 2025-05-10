package MyCooking.com.models;

import java.io.*;
import java.util.*;

public class CustomerManager {
    private static final String PROFILE_DIR = "customer_profiles/";

    public CustomerManager() {
        new File(PROFILE_DIR).mkdirs();
    }

    public Customer getOrCreateCustomerProfile(Scanner scanner, String username) {
        File profileFile = new File(PROFILE_DIR + username + ".txt");
        if (profileFile.exists()) {
            return loadProfile(profileFile, username);
        } else {
            return createNewProfile(scanner, profileFile, username);
        }
    }

    private Customer loadProfile(File profileFile, String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(profileFile))) {
            String name = reader.readLine();
            String allergies = reader.readLine();
            String preferences = reader.readLine();
            return new Customer(username, name);
        } catch (IOException e) {
            System.out.println("Failed to load profile for " + username + ". Creating a new one.");
            return new Customer(username, "");
        }
    }

    private Customer createNewProfile(Scanner scanner, File profileFile, String username) {
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your allergies (comma-separated): ");
        List<String> allergies = parseList(scanner.nextLine());

        System.out.print("Enter your dietary preferences (comma-separated): ");
        List<String> preferences = parseList(scanner.nextLine());

        Customer customer = new Customer(username, name);
        saveProfile(profileFile, customer);
        return customer;
    }

    private void saveProfile(File profileFile, Customer customer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(profileFile))) {
            writer.println(customer.getUsername());
            writer.println(String.join(",", customer.getAllergy()));
            writer.println(String.join(",", customer.getDietaryPreference()));
        } catch (IOException e) {
            System.out.println("Error saving profile for " + customer.getUsername());
        }
    }

    private List<String> parseList(String input) {
        String[] parts = input.split(",");
        if (parts.length == 1 && parts[0].isEmpty()) {
            return new ArrayList<>(); // إذا كانت المدخلات فارغة
        }
        return new ArrayList<>(Arrays.asList(parts));
    }

    public void collectCustomerPreferences(Scanner scanner, Customer customer) {
        System.out.println("Hello " + customer.getUsername() + "! Your preferences and allergies are loaded.");
    }

    public Meal createCustomMeal(Scanner scanner, Customer customer) {
        System.out.print("Enter meal name: ");
        String name = scanner.nextLine();
        List<Ingredient> ingredients = new ArrayList<>();
        while (true) {
            System.out.print("Add ingredient (or type 'done'): ");
            String ingredientName = scanner.nextLine();
            if (ingredientName.equalsIgnoreCase("done")) break;
            ingredients.add(new Ingredient(ingredientName));
        }
        return new Meal(name);
    }
}
