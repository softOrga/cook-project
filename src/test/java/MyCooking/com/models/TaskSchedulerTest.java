package MyCooking.com.models;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

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
        TaskScheduler scheduler = new TaskScheduler(Collections.emptyList());

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

    @Test
    public void testAssignTaskToChef_withEmptyChefsList() {
        TaskScheduler emptyScheduler = new TaskScheduler(Collections.emptyList());
        Meal meal = new Meal("Any Meal");
        meal.addIngredient(new Ingredient("rice", 1));

        emptyScheduler.assignTaskToChef(meal);
    }

    @Test
    public void testAssignTaskToChef_assignsMealCorrectly() {
        Meal veganMeal = new Meal("Vegan Salad");
        veganMeal.addIngredient(new Ingredient("tofu", 1));
        scheduler.assignTaskToChef(veganMeal);

        assertTrue(veganChef.getAssignedMeals().contains(veganMeal));
    }

    @Test
    public void testAssignTaskToChef_noMatchingSpecialization_noDefaultChef() {
        Chef chefWithoutDefault = new Chef("Charlie", "Dessert");
        TaskScheduler schedulerNoDefault = new TaskScheduler(Arrays.asList(chefWithoutDefault));

        Meal unknownMeal = new Meal("Unknown Dish");
        unknownMeal.addIngredient(new Ingredient("bread", 1));

        schedulerNoDefault.assignTaskToChef(unknownMeal);
        assertTrue(chefWithoutDefault.getAssignedMeals().isEmpty());
    }

    @Test
    public void testAssignTaskToChefAndReturnName_withNullIngredients() {
        Meal meal = new Meal("Empty Meal");
        String assignedChef = scheduler.assignTaskToChefAndReturnName(meal);
        assertEquals("Default Chef", assignedChef);
    }

    @Test
    public void testAssignTaskToChefAndReturnName_multipleMatchingChefs() {
        Chef veganChef2 = new Chef("Eve", "Vegan");
        TaskScheduler schedulerWithTwoVegans = new TaskScheduler(Arrays.asList(veganChef, veganChef2, grillChef, defaultChef));

        Meal veganMeal = new Meal("Vegan Delight");
        veganMeal.addIngredient(new Ingredient("tofu", 1));

        String assignedChef = schedulerWithTwoVegans.assignTaskToChefAndReturnName(veganMeal);
        assertEquals("Alice", assignedChef);
        assertTrue(veganChef.getAssignedMeals().contains(veganMeal));
        assertFalse(veganChef2.getAssignedMeals().contains(veganMeal));
    }

    // ------------- إضافات جديدة --------------

    @Test
    public void testAssignMultipleMealsToChefs() {
        Meal veganMeal1 = new Meal("Vegan Meal 1");
        veganMeal1.addIngredient(new Ingredient("tofu", 1));
        Meal veganMeal2 = new Meal("Vegan Meal 2");
        veganMeal2.addIngredient(new Ingredient("lettuce", 1));
        Meal grillMeal = new Meal("Grill Meal");
        grillMeal.addIngredient(new Ingredient("chicken", 1));

        scheduler.assignTaskToChef(veganMeal1);
        scheduler.assignTaskToChef(veganMeal2);
        scheduler.assignTaskToChef(grillMeal);

        assertTrue(veganChef.getAssignedMeals().contains(veganMeal1));
        assertTrue(veganChef.getAssignedMeals().contains(veganMeal2));
        assertTrue(grillChef.getAssignedMeals().contains(grillMeal));
    }

    @Test
    public void testAssignMealWithNullIngredientList() {
        Meal meal = new Meal("Null Ingredients");
        meal.setIngredients(null); 

        String assignedChef = scheduler.assignTaskToChefAndReturnName(meal);
        assertEquals("Default Chef", assignedChef);
    }

    @Test
    public void testAssignMealWithEmptyIngredientList() {
        Meal meal = new Meal("Empty Ingredients");
        meal.setIngredients(Collections.emptyList());

        String assignedChef = scheduler.assignTaskToChefAndReturnName(meal);
        assertEquals("Default Chef", assignedChef);
    }

    @Test
    public void testNoAssignmentToOtherChefs() {
        Meal veganMeal = new Meal("Vegan Salad");
        veganMeal.addIngredient(new Ingredient("tofu", 1));

        scheduler.assignTaskToChef(veganMeal);

        assertFalse(grillChef.getAssignedMeals().contains(veganMeal));
        assertFalse(defaultChef.getAssignedMeals().contains(veganMeal));
    }

    @Test
    public void testAssignChefWhenMultipleSpecializationsExist() {
        Chef grillChef2 = new Chef("Dave", "Grill");
        TaskScheduler schedulerWithTwoGrills = new TaskScheduler(Arrays.asList(veganChef, grillChef, grillChef2, defaultChef));

        Meal grillMeal = new Meal("Grill Feast");
        grillMeal.addIngredient(new Ingredient("chicken", 1));

        String assignedChef = schedulerWithTwoGrills.assignTaskToChefAndReturnName(grillMeal);
        assertEquals("Bob", assignedChef);
        assertTrue(grillChef.getAssignedMeals().contains(grillMeal));
        assertFalse(grillChef2.getAssignedMeals().contains(grillMeal));
    }

    @Test
    public void testAssignTaskWithIngredientThatMatchesMultipleSpecializations() {
        Chef multiSpecialistChef = new Chef("Frank", "Vegan, Grill");
        TaskScheduler schedulerWithMultiSpecialist = new TaskScheduler(Arrays.asList(multiSpecialistChef, defaultChef));

        Meal complexMeal = new Meal("Complex Dish");
        complexMeal.addIngredient(new Ingredient("tofu", 1));
        complexMeal.addIngredient(new Ingredient("chicken", 1));

        String assignedChef = schedulerWithMultiSpecialist.assignTaskToChefAndReturnName(complexMeal);
        assertEquals("Frank", assignedChef);
        assertTrue(multiSpecialistChef.getAssignedMeals().contains(complexMeal));
    }
}
