package MyCooking.com.models;
import MyCooking.com.models.Chef;
import MyCooking.com.models.Ingredient;
import MyCooking.com.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class ChefScheduler {
    private List<Chef> chefs;

    public ChefScheduler() {
        chefs = new ArrayList<>();
    }

    public void addChef(Chef chef) {
        chefs.add(chef);
    }

    public List<Chef> getChefs() {
        return chefs;
    }

    public void assignTaskToChef(Meal meal) {
        String specializationHint = detectSpecializationFromMeal(meal);

        for (Chef chef : chefs) {
            if (chef.getSpecialization().equalsIgnoreCase(specializationHint)) {
                chef.assignMeal(meal);
                System.out.println("Assigned to Chef: " + chef.getName());
                return;
            }
        }

        if (!chefs.isEmpty()) {
            chefs.get(0).assignMeal(meal);
            System.out.println("Assigned to Chef: " + chefs.get(0).getName());
        }
    }

    private String detectSpecializationFromMeal(Meal meal) {
        for (Ingredient ing : meal.getIngredients()) {
            String name = ing.getName().toLowerCase();
            if (name.contains("tofu") || name.contains("almond") || name.contains("vegan") || name.contains("salad")) {
                return "Vegan";
            }
        }
        return "Grill";
    }
}