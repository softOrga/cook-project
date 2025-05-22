package MyCooking.com.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MealTest {

    private Meal meal;
    private Ingredient cheese;
    private Ingredient tomato;

    @Before
    public void setUp() {
        meal = new Meal("Pizza", 101);
        cheese = new Ingredient("cheese", 50);
        tomato = new Ingredient("tomato", 30);
    }

    @Test
    public void testGetName() {
        assertEquals("Pizza", meal.getName());
    }

    @Test
    public void testAddAndGetIngredients() {
        meal.addIngredient(cheese);
        meal.addIngredient(tomato);
        List<Ingredient> ingredients = meal.getIngredients();
        assertEquals(2, ingredients.size());
        assertTrue(ingredients.contains(cheese));
        assertTrue(ingredients.contains(tomato));
    }

    @Test
    public void testRemoveIngredient() {
        meal.addIngredient(cheese);
        meal.addIngredient(tomato);
        meal.removeIngredient("cheese");
        List<Ingredient> ingredients = meal.getIngredients();
        assertEquals(1, ingredients.size());
        assertFalse(ingredients.contains(cheese));
    }

    @Test
    public void testContainsRestrictedIngredient() {
        meal.addIngredient(cheese);
        List<String> restrictions = Arrays.asList("cheese", "peanut");
        assertTrue(meal.containsRestrictedIngredient(restrictions));
    }

    @Test
    public void testDoesNotContainRestrictedIngredient() {
        meal.addIngredient(tomato);
        List<String> restrictions = Arrays.asList("cheese", "peanut");
        assertFalse(meal.containsRestrictedIngredient(restrictions));
    }

    @Test
    public void testCustomerId() {
        assertEquals(101, meal.getCustomerId());
        meal.setCustomerId(202);
        assertEquals(202, meal.getCustomerId());
    }

    @Test
    public void testToString() {
        meal.addIngredient(cheese);
        String output = meal.toString();
        assertTrue(output.contains("Meal: Pizza"));
        assertTrue(output.contains("cheese"));
        assertTrue(output.contains("Ordered by Customer ID: 101"));
    }
}

