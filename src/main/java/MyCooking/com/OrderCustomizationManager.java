

/*package MyCooking.com;

import MyCooking.com.models.ChefApproval;
import MyCooking.com.models.CustomMeal;
import MyCooking.com.models.Ingredient;
import MyCooking.com.models.IngredientSubstitution;
import MyCooking.com.models.IngredientValidator;
import MyCooking.com.models.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderCustomizationManager {
    private IngredientValidator validator = new IngredientValidator();
    private IngredientSubstitution substitution = new IngredientSubstitution();
    private ChefApproval chefApproval = new ChefApproval();
    private NotificationService notification = new NotificationService();

    public void customizeMeal() {
        Scanner scanner = new Scanner(System.in);
        CustomMeal meal = new CustomMeal();
        List<Ingredient> unavailableIngredients = new ArrayList<>();

        System.out.println("\n--- Customize Your Meal ---");
        System.out.println("Started a new custom meal request.");
        System.out.print("Enter ingredients separated by commas (e.g., Chicken,Tomato,Shrimp): ");
        String input = scanner.nextLine();
        String[] items = input.split(",");

        for (String item : items) {
            String name = item.trim();
            boolean available = !name.equalsIgnoreCase("Shrimp"); // simulate unavailability
            boolean restricted = name.equalsIgnoreCase("Peanut");

            Ingredient ingredient = new Ingredient(name, available, restricted);
            meal.addIngredient(ingredient);
            if (!available) {
                unavailableIngredients.add(ingredient);
            }
        }

        System.out.println("Selected ingredients: ");
        meal.displayMeal();

        boolean valid = validator.validate(meal.getIngredients());

        if (!valid) {
            notification.notifyUser("Meal contains restricted ingredients. Cannot proceed.");
            return;
        }

        if (!unavailableIngredients.isEmpty()) {
            notification.notifyUser("Some ingredients are unavailable.");
            System.out.print("Would you like to see a substitution suggestion? (yes/no): ");
            String reply = scanner.nextLine().trim().toLowerCase();
            if (reply.equals("yes")) {
                List<String> suggestions = substitution.suggestSubstitutions(unavailableIngredients);
                for (int i = 0; i < unavailableIngredients.size(); i++) {
                    System.out.println("Alternative for " + unavailableIngredients.get(i).getName() + ": " + suggestions.get(i));
                }
            }
        }

        boolean confirm = false;
        System.out.print("Do you want to confirm this meal? (yes/no): ");
        String confirmInput = scanner.nextLine().trim().toLowerCase();
        confirm = confirmInput.equals("yes");

        boolean approved = chefApproval.approveMeal(valid, confirm);

        if (approved) {
            notification.notifyUser("Custom meal request confirmed successfully!");
        } else {
            notification.notifyUser("Meal request not confirmed.");
        }
    }
}


*/

package MyCooking.com;

import java.util.*;

public class OrderCustomizationManager {

    private List<String> availableIngredients = Arrays.asList("Tomatoes", "Basil", "Pasta", "Olive oil", "Garlic");
    private List<String> customMeal = new ArrayList<>();

    private boolean issueDetected = false;

    public void startMealRequest() {
        customMeal.clear();
        System.out.println(" Starting a new custom meal request...");
    }

    public void selectIngredients(String ing1, String ing2, String ing3) {
        System.out.println("Selecting ingredients: " + ing1 + ", " + ing2 + ", " + ing3);
        customMeal.add(ing1);
        customMeal.add(ing2);
        customMeal.add(ing3);

        // Check if all selected ingredients are available
        for (String ingredient : customMeal) {
            if (!availableIngredients.contains(ingredient)) {
                issueDetected = true;
                System.out.println(" Warning: Ingredient not available or restricted: " + ingredient);
            }
        }
    }

    public void saveMeal() {
        System.out.println(" Saving the custom meal request...");
        if (customMeal.isEmpty()) {
            System.out.println(" No ingredients selected!");
        }
    }

    public void validateIngredients() {
        System.out.println(" Validating ingredient compatibility...");
        boolean allCompatible = true;
        for (String ingredient : customMeal) {
            if (!availableIngredients.contains(ingredient)) {
                allCompatible = false;
                break;
            }
        }
        if (allCompatible) {
            System.out.println(" Ingredients are compatible.");
        } else {
            System.out.println("Ingredients are NOT compatible.");
        }
    }

    public void confirmMeal() {
        System.out.println(" Custom meal confirmed with ingredients: " + String.join(", ", customMeal));
    }

    public void selectUnavailableIngredient() {
        customMeal.clear();
        customMeal.add("Lobster"); 
        issueDetected = true;
        System.out.println(" Customer selected restricted/unavailable ingredient: Lobster");
    }

    public void detectIssue() {
        if (issueDetected) {
            System.out.println(" System detected an issue with selected ingredients!");
        } else {
            System.out.println(" No issues detected with ingredients.");
        }
    }

    public void suggestAlternative() {
        if (issueDetected) {
            System.out.println(" Suggesting alternative: 'Tofu' instead of 'Lobster'");
        } else {
            System.out.println(" No need for alternative ingredients.");
        }
    }

    public void alertChef() {
        if (issueDetected) {
            System.out.println("Chef has been alerted for ingredient approval.");
        } else {
            System.out.println("No chef approval needed.");
        }
    }

	public void customizeMeal(String customerId) {
		// TODO Auto-generated method stub
		
	}


}