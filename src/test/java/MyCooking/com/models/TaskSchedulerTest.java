package MyCooking.com.models;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class TaskSchedulerTest {

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

        
        Chef veganChef = new Chef("Alice", "Vegan");
        Chef grillChef = new Chef("Bob", "Grill");
        Chef defaultChef = new Chef("Default Chef", "General");

        
        List<Chef> chefs = Arrays.asList(veganChef, grillChef, defaultChef);
        TaskScheduler scheduler = new TaskScheduler(chefs);

        
        String assignedVeganChef = scheduler.assignTaskToChefAndReturnName(veganMeal);
        assertEquals("Alice", assignedVeganChef);

        String assignedGrillChef = scheduler.assignTaskToChefAndReturnName(grillMeal);
        assertEquals("Bob", assignedGrillChef);

        String assignedDefaultChef = scheduler.assignTaskToChefAndReturnName(generalMeal);
        assertEquals("Default Chef", assignedDefaultChef);
    }
}
