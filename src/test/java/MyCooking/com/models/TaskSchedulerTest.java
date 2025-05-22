package MyCooking.com.models;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

public class TaskSchedulerTest {

    private Chef veganChef;
    private Chef grillChef;
    private Chef defaultChef;
    private TaskScheduler scheduler;

    @Before
    public void setup() {
        veganChef = new Chef("Alice", "Vegan");
        grillChef = new Chef("Bob", "Grill");
        defaultChef = new Chef("Default Chef", "General");
        scheduler = new TaskScheduler(Arrays.asList(veganChef, grillChef, defaultChef));
    }

    @Test
    public void testAssignTaskToChef() {
        Ingredient tofu = new Ingredient("tofu", 2);
        Ingredient lettuce = new Ingredient("lettuce", 1);
        Ingredient chicken = new Ingredient("chicken breast", 1);
        Ingredient rice = new Ingredient("rice", 1);
        Ingredient salt = new Ingredient("salt", 1);

        Meal veganMeal = new Meal("Vegan Salad");
        veganMeal.addIngredient(tofu);
        veganMeal.addIngredient(lettuce);

        Meal grillMeal = new Meal("Grilled Chicken");
        grillMeal.addIngredient(chicken);
        grillMeal.addIngredient(rice);

        Meal generalMeal = new Meal("Rice and Salt");
        generalMeal.addIngredient(rice);
        generalMeal.addIngredient(salt);

        String assignedVeganChef = scheduler.assignTaskToChefAndReturnName(veganMeal);
        assertEquals("Alice", assignedVeganChef);
        assertTrue(veganChef.getAssignedMeals().contains(veganMeal));

        String assignedGrillChef = scheduler.assignTaskToChefAndReturnName(grillMeal);
        assertEquals("Bob", assignedGrillChef);
        assertTrue(grillChef.getAssignedMeals().contains(grillMeal));

        String assignedDefaultChef = scheduler.assignTaskToChefAndReturnName(generalMeal);
        assertEquals("Default Chef", assignedDefaultChef);
        assertTrue(defaultChef.getAssignedMeals().contains(generalMeal));
    }

    @Test
    public void testAssignReturnsUnknownWhenNoDefaultChef() {
        TaskScheduler schedulerWithoutDefault = new TaskScheduler(Arrays.asList(veganChef, grillChef));

        Ingredient rice = new Ingredient("rice", 1);
        Ingredient salt = new Ingredient("salt", 1);

        Meal generalMeal = new Meal("Rice and Salt");
        generalMeal.addIngredient(rice);
        generalMeal.addIngredient(salt);

        String assignedChef = schedulerWithoutDefault.assignTaskToChefAndReturnName(generalMeal);
        assertEquals("Unknown", assignedChef);
    }

    @Test
    public void testDetectMealType() throws Exception {
        TaskScheduler scheduler = new TaskScheduler(Arrays.asList());

        java.lang.reflect.Method method = TaskScheduler.class.getDeclaredMethod("detectMealType", Meal.class);
        method.setAccessible(true);

        Meal veganMeal = new Meal("Vegan Dish");
        veganMeal.addIngredient(new Ingredient("tofu", 1));
        assertEquals("Vegan", method.invoke(scheduler, veganMeal));

        Meal grillMeal = new Meal("Grill Dish");
        grillMeal.addIngredient(new Ingredient("chicken", 1));
        assertEquals("Grill", method.invoke(scheduler, grillMeal));

        Meal generalMeal = new Meal("General Dish");
        generalMeal.addIngredient(new Ingredient("bread", 1));
        assertEquals("General", method.invoke(scheduler, generalMeal));
    }

    @Test
    public void testAssignToChefWithCaseInsensitiveMatching() {
        Chef chef = new Chef("Alice", "vEgAn");
        TaskScheduler scheduler = new TaskScheduler(Arrays.asList(chef));

        Meal veganMeal = new Meal("Salad");
        veganMeal.addIngredient(new Ingredient("Tofu", 1));

        String assignedChef = scheduler.assignTaskToChefAndReturnName(veganMeal);
        assertEquals("Alice", assignedChef);
        assertTrue(chef.getAssignedMeals().stream().anyMatch(m -> m.getName().equals("Salad")));
    }
}
