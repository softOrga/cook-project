package MyCooking.com.models;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class ChefTest {

    private Chef chef;
    private Meal meal;

    @Before
    public void setUp() {
        chef = new Chef("Ahmed", "Grill");
        meal = new Meal("Grilled Chicken", 25);
    }

    @Test
    public void testGetName() {
        assertEquals("Ahmed", chef.getName());
    }

    @Test
    public void testGetSpecialization() {
        assertEquals("Grill", chef.getSpecialization());
    }

    @Test
    public void testAssignMealAndGetAssignedMeals() {
        chef.assignMeal(meal);
        List<Meal> meals = chef.getAssignedMeals();
        assertEquals(1, meals.size());
        assertEquals("Grilled Chicken", meals.get(0).getName());
    }

    @Test
    public void testAlertForApproval() {
     
        chef.alertForApproval(meal);
         
    }
}
