package MyCooking.com;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationManager {

    private final List<String> notifications = new ArrayList<>();

    public void sendDeliveryReminder(String customerId) {
        String message = timestamp() + " Reminder: Dear customer " + customerId + ", your meal will be delivered in 1 hour.";
        System.out.println(message);
        notifications.add(message);
    }

    public void notifyChefTask(String chefName, String task) {
        String message = timestamp() + " Notification: Chef " + chefName + ", you have a scheduled task: " + task;
        System.out.println(message);
        notifications.add(message);
    }

    public void sendLowStockAlert(String ingredient) {
        String message = timestamp() + " ALERT: Low stock detected for ingredient: " + ingredient;
        System.out.println(message);
        notifications.add(message);
    }

    public List<String> getNotifications() {
        return notifications;
    }

    private String timestamp() {
        return "[" + LocalDateTime.now() + "]";
    }
}
