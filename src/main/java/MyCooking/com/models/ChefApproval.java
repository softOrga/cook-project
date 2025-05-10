package MyCooking.com.models;

public class ChefApproval {
    public boolean approveMeal(Meal meal) {
        return meal.getIngredients().size() >= 2;
    }
}

