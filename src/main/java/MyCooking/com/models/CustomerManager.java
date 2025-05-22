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

            if (name == null) name = "Unknown";
            if (allergies == null) allergies = "";
            if (preferences == null) preferences = "";

            Customer customer = new Customer(username, name);
            customer.setAllergy(allergies);
            customer.setPreferences(preferences);
            return customer;
        } catch (IOException e) {
            System.out.println("Failed to load profile for " + username + ". Creating a new one.");
            return new Customer(username, "Unknown");
        }
    }

    private Customer createNewProfile(Scanner scanner, File profileFile, String username) {
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();
        if (name == null || name.trim().isEmpty()) name = "Unknown";

        System.out.print("Enter your allergies (comma-separated): ");
        String allergies = scanner.nextLine();
        if (allergies == null) allergies = "";

        System.out.print("Enter your dietary preferences (comma-separated): ");
        String preferences = scanner.nextLine();
        if (preferences == null) preferences = "";

        Customer customer = new Customer(username, name);
        customer.setAllergy(allergies);
        customer.setPreferences(preferences);
        saveProfile(profileFile, customer);
        return customer;
    }

    private void saveProfile(File profileFile, Customer customer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(profileFile))) {
            writer.println(customer.getCustomerName());
            writer.println(customer.getAllergy());
            writer.println(customer.getDietaryPreference());
        } catch (IOException e) {
            System.out.println("Error saving profile for " + customer.getUsername());
        }
    }

    public void collectCustomerPreferences(Scanner scanner, Customer customer) {
        System.out.println("Hello " + customer.getUsername() + "! Your allergies and preferences have been loaded:");
        System.out.println("Allergies: " + (customer.getAllergy().isEmpty() ? "None" : customer.getAllergy()));
        System.out.println("Preferences: " + (customer.getDietaryPreference().isEmpty() ? "None" : customer.getDietaryPreference()));
    }

    public Meal createCustomMeal(Scanner scanner, Customer customer) {
        System.out.print("Enter meal name: ");
        String name = scanner.nextLine();

        List<Ingredient> ingredients = new ArrayList<>();
        while (true) {
            System.out.print("Add ingredient (or type 'done'): ");
            String ingredientName = scanner.nextLine();
            if (ingredientName == null || ingredientName.trim().isEmpty()) {
                continue;
            }
            if (ingredientName.equalsIgnoreCase("done")) break;
            ingredients.add(new Ingredient(ingredientName.trim()));
        }

        Meal meal = new Meal(name, customer.getId());
        for (Ingredient ingredient : ingredients) {
            meal.addIngredient(ingredient);
        }
        return meal;
    }
}
