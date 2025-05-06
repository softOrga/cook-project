package MyCooking.com;

import java.util.ArrayList;
import java.util.List;

import MyCooking.com.models.CustomerManager;

public class OrderCustomizationManager {
    private List<String> selectedIngredients;
    private CustomerManager customerManager;

    public OrderCustomizationManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
        this.selectedIngredients = new ArrayList<>();
    }

    public void startMealRequest() {
        System.out.println("Starting a new meal request...");
        selectedIngredients.clear();  
    }

    public void selectIngredients(String ing1, String ing2, String ing3) {
        selectedIngredients.add(ing1);
        selectedIngredients.add(ing2);
        selectedIngredients.add(ing3);
        System.out.println("Ingredients selected: " + ing1 + ", " + ing2 + ", " + ing3);
    }

    public void saveMeal() {
        System.out.println("Meal saved with ingredients: " + selectedIngredients);
    }

    public void validateIngredients() {
        System.out.println("Validating ingredients...");
    }

    public void confirmMeal() {
        System.out.println("Confirming the custom meal request...");
    }

    public void selectUnavailableIngredient() {
        System.out.println("Ingredient is unavailable or restricted.");
    }

    public void detectIssue() {
        System.out.println("Detecting issue with the selected ingredients...");
    }

    public void suggestAlternative() {
        System.out.println("Suggesting an alternative ingredient...");
    }

    public void alertChef() {
        System.out.println("Alerting the chef for ingredient approval...");
    }
}


 
