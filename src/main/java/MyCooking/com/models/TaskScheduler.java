package MyCooking.com.models;

import java.util.List;

public class TaskScheduler {
    private List<Chef> chefs;

    public TaskScheduler(List<Chef> chefs) {
        this.chefs = chefs;
    }

    public void assignTaskToChef(Meal meal) {
        if (meal == null) return; 

        String type = detectMealType(meal);
        Chef assignedChef = findChefBySpecialization(type);

        if (assignedChef == null) {
            assignedChef = findDefaultChef();
        }

        if (assignedChef != null) {
            assignedChef.assignMeal(meal);
            System.out.println("Assigned to Chef: " + assignedChef.getName());
        }
    }

    public String assignTaskToChefAndReturnName(Meal meal) {
        if (meal == null) return "Unknown";

        String type = detectMealType(meal);
        Chef assignedChef = findChefBySpecialization(type);

        if (assignedChef == null) {
            assignedChef = findDefaultChef();
        }

        if (assignedChef != null) {
            assignedChef.assignMeal(meal);
            System.out.println("Assigned to Chef: " + assignedChef.getName());
            return assignedChef.getName();
        }

        return "Unknown";
    }

    private Chef findChefBySpecialization(String type) {
        if (type == null) return null;

        for (Chef chef : chefs) {
            if (chef.getSpecialization() == null) continue;

            String[] specs = chef.getSpecialization().split(",");
            for (String spec : specs) {
                if (spec.trim().equalsIgnoreCase(type)) {
                    return chef;
                }
            }
        }
        return null;
    }

    private Chef findDefaultChef() {
        for (Chef chef : chefs) {
            if (chef.getName() != null && chef.getName().equalsIgnoreCase("Default Chef")) {
                return chef;
            }
        }
        return null;
    }

    private String detectMealType(Meal meal) {
        if (meal == null || meal.getIngredients() == null || meal.getIngredients().isEmpty()) {
            return "General";  
        }

        for (Ingredient ing : meal.getIngredients()) {
            if (ing == null || ing.getName() == null) continue;

            String name = ing.getName().toLowerCase();
            if (name.contains("tofu") || name.contains("vegan") || name.contains("almond milk") 
                || name.contains("carrot") || name.contains("lettuce")) {
                return "Vegan";
            } else if (name.contains("chicken") || name.contains("beef") || name.contains("grill")) {
                return "Grill";
            }
        }
        return "General";
    }
}
