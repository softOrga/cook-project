package MyCooking.com.models;

import java.util.List;

public class TaskScheduler {
    private List<Chef> chefs;

    public TaskScheduler(List<Chef> chefs) {
        this.chefs = chefs;
    }

    public void assignTaskToChef(Meal meal) {
        String type = detectMealType(meal);
        for (Chef chef : chefs) {
            if (chef.getSpecialization().equalsIgnoreCase(type)) {
                chef.assignMeal(meal);
                System.out.println("Assigned to Chef: " + chef.getName());
                return;
            }
        }

        for (Chef chef : chefs) {
            if (chef.getName().equalsIgnoreCase("Default Chef")) {
                chef.assignMeal(meal);
                System.out.println("Assigned to Default Chef: " + chef.getName());
                return;
            }
        }
    }

    public String assignTaskToChefAndReturnName(Meal meal) {
        String type = detectMealType(meal);
        for (Chef chef : chefs) {
            if (chef.getSpecialization().equalsIgnoreCase(type)) {
                chef.assignMeal(meal);
                System.out.println("Assigned to Chef: " + chef.getName());
                return chef.getName();
            }
        }

        for (Chef chef : chefs) {
            if (chef.getName().equalsIgnoreCase("Default Chef")) {
                chef.assignMeal(meal);
                System.out.println("Assigned to Default Chef: " + chef.getName());
                return chef.getName();
            }
        }

        return "Unknown";
    }

    private String detectMealType(Meal meal) {
        for (Ingredient ing : meal.getIngredients()) {
            String name = ing.getName().toLowerCase();
            if (name.contains("tofu") || name.contains("vegan") || name.contains("almond milk") || name.contains("carrot") || name.contains("lettuce")) {
                return "Vegan";
            } else if (name.contains("chicken") || name.contains("beef") || name.contains("grill")) {
                return "Grill";
            }
        }
        return "General";
    }
}


