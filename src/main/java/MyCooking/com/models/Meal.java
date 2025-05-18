package MyCooking.com.models;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private String name;
    private List<Ingredient> ingredients;
    private int customerId;

    public Meal(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

   
    public Meal(String name, int customerId) {
        this.name = name;
        this.customerId = customerId;
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(String ingredientName) {
        ingredients.removeIf(i -> i.getName().equalsIgnoreCase(ingredientName));
    }

    public boolean containsRestrictedIngredient(List<String> restrictions) {
        for (Ingredient ingredient : ingredients) {
            if (restrictions.contains(ingredient.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Meal: " + name + "\nIngredients:\n");
        for (Ingredient ingredient : ingredients) {
            sb.append("- ").append(ingredient).append("\n");
        }
        sb.append("Ordered by Customer ID: ").append(customerId).append("\n");
        return sb.toString();
    }
}
