package MyCooking.com.models;

import java.util.ArrayList;
import java.util.List;

public class Chef {
    private String name;
    private String specialization;
    private List<Meal> assignedMeals;

    public Chef(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
        this.assignedMeals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void assignMeal(Meal meal) {
        assignedMeals.add(meal);
    }

    public List<Meal> getAssignedMeals() {
        return assignedMeals;
    }

    public void alertForApproval(Meal meal) {
        System.out.println("Chef " + name + " has received the meal '" + meal.getName() + "' for approval.");
    }
}

