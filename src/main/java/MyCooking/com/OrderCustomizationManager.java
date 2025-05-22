package MyCooking.com;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import MyCooking.com.models.CustomerManager;

public class OrderCustomizationManager {
    private static final Logger logger = Logger.getLogger(OrderCustomizationManager.class.getName());

    private List<String> selectedIngredients;
    private CustomerManager customerManager;

    public OrderCustomizationManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
        this.selectedIngredients = new ArrayList<>();
    }

    public void startMealRequest() {
        logger.info("Starting a new meal request...");
        selectedIngredients.clear();
    }

    public void selectIngredients(String ing1, String ing2, String ing3) {
        selectedIngredients.add(ing1);
        selectedIngredients.add(ing2);
        selectedIngredients.add(ing3);
        logger.log(java.util.logging.Level.INFO, "Ingredients selected: {0}, {1}, {2}", new Object[]{ing1, ing2, ing3});
    }

    public void saveMeal() {
        logger.info("Meal saved with ingredients: " + selectedIngredients);
    }

    public void validateIngredients() {
        logger.info("Validating ingredients...");
    }

    public void confirmMeal() {
        logger.info("Confirming the custom meal request...");
    }

    public void selectUnavailableIngredient() {
        logger.info("Ingredient is unavailable or restricted.");
    }

    public void detectIssue() {
        logger.info("Detecting issue with the selected ingredients...");
    }

    public void suggestAlternative() {
        logger.info("Suggesting an alternative ingredient...");
    }

    public void alertChef() {
        logger.info("Alerting the chef for ingredient approval...");
    }
}
