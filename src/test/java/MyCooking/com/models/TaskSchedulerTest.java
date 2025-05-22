package MyCooking.com.models;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

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
    public void testAssignToVeganChef() {
        Meal meal = new Meal("Salad", Arrays.asList(
            new Ingredient("Carrot"),
            new Ingredient("Lettuce")
        ));
        String assignedChef = scheduler.assignTaskToChefAndReturnName(meal);
        assertEquals("Alice", assignedChef);
        assertTrue(veganChef.getAssignedMeals().contains(meal));
    }

    @Test
    public void testAssignToGrillChef() {
        Meal meal = new Meal("BBQ Chicken", Arrays.asList(
            new Ingredient("Chicken"),
            new Ingredient("Spices")
        ));
        String assignedChef = scheduler.assignTaskToChefAndReturnName(meal);
        assertEquals("Bob", assignedChef);
        assertTrue(grillChef.getAssignedMeals().contains(meal));
    }

    @Test
    public void testAssignToDefaultChefWhenNoSpecialization() {
        Meal meal = new Meal("Pasta", Arrays.asList(
            new Ingredient("Pasta"),
            new Ingredient("Tomato Sauce")
        ));
        String assignedChef = scheduler.assignTaskToChefAndReturnName(meal);
        assertEquals("Default Chef", assignedChef);
        assertTrue(defaultChef.getAssignedMeals().contains(meal));
    }

    @Test
    public void testAssignReturnsUnknownWhenNoDefaultChef() {
        TaskScheduler schedulerWithoutDefault = new TaskScheduler(Arrays.asList(veganChef, grillChef));
        Meal meal = new Meal("Pasta", Arrays.asList(
            new Ingredient("Pasta"),
            new Ingredient("Tomato Sauce")
        ));
        String assignedChef = schedulerWithoutDefault.assignTaskToChefAndReturnName(meal);
        assertEquals("Unknown", assignedChef);
    }
}
