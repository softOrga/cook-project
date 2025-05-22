package MyCooking.com.models;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChefSchedulerTest {

    @Test
    void testAssignSpecializedChef() {
        Chef veganChef = new Chef("Alice", "Vegan");
        ChefScheduler scheduler = new ChefScheduler();
        scheduler.addChef(veganChef);

        Meal meal = new Meal("Tofu Delight", 1);
        meal.addIngredient(new Ingredient("Tofu"));

        scheduler.assignTaskToChef(meal);

        List<Meal> assigned = veganChef.getAssignedMeals();
        assertEquals(1, assigned.size());
        assertEquals("Tofu Delight", assigned.get(0).getName());
    }

    @Test
    void testAssignDefaultChef() {
        Chef chef = new Chef("Bob", "Grill");
        ChefScheduler scheduler = new ChefScheduler();
        scheduler.addChef(chef);

        Meal meal = new Meal("Burger", 1);
        meal.addIngredient(new Ingredient("Beef"));

        scheduler.assignTaskToChef(meal);

        List<Meal> assigned = chef.getAssignedMeals();
        assertEquals(1, assigned.size());
    }
}
