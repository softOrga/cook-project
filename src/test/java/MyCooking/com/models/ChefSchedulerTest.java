package MyCooking.com.models;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChefSchedulerTest {

    private ChefScheduler scheduler;

    @Before
    public void setUp() {
        scheduler = new ChefScheduler();
    }

    @Test
    public void testAddChefAndGetChefs() {
        Chef chef = new Chef("Ali", "Grill");
        scheduler.addChef(chef);
        List<Chef> chefList = scheduler.getChefs();
        assertEquals(1, chefList.size());
        assertEquals("Ali", chefList.get(0).getName());
    }

    @Test
    public void testAssignVeganMealToVeganChef() {
        Meal veganMeal = new Meal("Vegan Bowl");
        veganMeal.addIngredient(new Ingredient("Tofu", 1));
        Chef veganChef = new Chef("Sara", "Vegan");
        scheduler.addChef(veganChef);

        scheduler.assignTaskToChef(veganMeal);

        assertEquals(1, veganChef.getAssignedMeals().size());
        assertEquals("Vegan Bowl", veganChef.getAssignedMeals().get(0).getName());
    }

    @Test
    public void testAssignGrillMealToGrillChef() {
        Meal grillMeal = new Meal("BBQ Chicken");
        grillMeal.addIngredient(new Ingredient("Chicken", 2));
        Chef grillChef = new Chef("Khaled", "Grill");
        scheduler.addChef(grillChef);

        scheduler.assignTaskToChef(grillMeal);

        assertEquals(1, grillChef.getAssignedMeals().size());
        assertEquals("BBQ Chicken", grillChef.getAssignedMeals().get(0).getName());
    }

    @Test
    public void testAssignMealToFirstChefIfNoSpecializationMatch() {
        Meal meal = new Meal("Unknown Dish");
        meal.addIngredient(new Ingredient("Fish", 1));
        Chef fallbackChef = new Chef("Fallback", "Italian");
        scheduler.addChef(fallbackChef);

        scheduler.assignTaskToChef(meal);

        assertEquals(1, fallbackChef.getAssignedMeals().size());
        assertEquals("Unknown Dish", fallbackChef.getAssignedMeals().get(0).getName());
    }

    @Test
    public void testNoChefAvailableDoesNotCrash() {
        Meal meal = new Meal("Solo Dish");
        meal.addIngredient(new Ingredient("Beef", 1));
        scheduler.assignTaskToChef(meal);  // Should not crash
        assertTrue(scheduler.getChefs().isEmpty());
    }
}
