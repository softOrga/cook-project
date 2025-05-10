package MyCooking.com.models;

import java.util.List;
import MyCooking.com.models.*;

public class ChefTaskNotifier {
    private NotificationService notificationService = new NotificationService();

    public void notifyChefs(List<Chef> chefs) {
        for (Chef chef : chefs) {
            for (Meal task : chef.getAssignedMeals()) {
                notificationService.notifyChef(chef.getName(), "Reminder: You have a task - " + task.getName());
            }
        }
    }
}

