package MyCooking.com.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChefApprovalTest {

    private ChefApproval chefApproval;

    @Before
    public void setUp() {
        chefApproval = new ChefApproval();
    }

    @Test
    public void testApproveMealWithTwoIngredients() {
        Meal meal = new Meal("Pasta");
        meal.addIngredient(new Ingredient("Tomato", 1));
        meal.addIngredient(new Ingredient("Cheese", 2));

        assertTrue(chefApproval.approveMeal(meal));
    }

    @Test
    public void testRejectMealWithOneIngredient() {
        Meal meal = new Meal("Toast");
        meal.addIngredient(new Ingredient("Bread", 1));

        assertFalse(chefApproval.approveMeal(meal));
    }

    @Test
    public void testRejectMealWithNoIngredients() {
        Meal meal = new Meal("Empty Dish");

        assertFalse(chefApproval.approveMeal(meal));
    }
}
