package MyCooking.com.models;

import org.junit.Test;
import java.util.Arrays;

public class ChefTaskNotifierTest {

    @Test
    public void testNotifyChefs() {
        Chef chef = new Chef("Chef Layla", "Dessert");
        Meal meal1 = new Meal("Chocolate Cake");
        Meal meal2 = new Meal("Vanilla Tart");

        chef.assignMeal(meal1);
        chef.assignMeal(meal2);

        ChefTaskNotifier notifier = new ChefTaskNotifier();
        notifier.notifyChefs(Arrays.asList(chef));

        
     
    }
}
