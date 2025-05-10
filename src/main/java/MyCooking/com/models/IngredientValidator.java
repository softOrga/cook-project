package MyCooking.com.models;

import java.util.Arrays;
import java.util.List;

public class IngredientValidator {
    private static final List<String> allowedIngredients = Arrays.asList(
        "chicken", "beef", "carrot", "potato", "onion", "garlic",
        "tomato", "spinach", "rice", "pasta", "milk", "egg", "butter",
        "cheese", "pepper", "salt", "olive oil", "almond milk",
        "chia seeds", "vegan cheese", "sunflower seeds"
    );

    public boolean isValid(String ingredientName) {
        return allowedIngredients.contains(ingredientName.toLowerCase());
    }
}
