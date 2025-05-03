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
}