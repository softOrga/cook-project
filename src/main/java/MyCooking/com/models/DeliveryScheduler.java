package MyCooking.com.models;


import java.time.*;
import java.util.*;

public class DeliveryScheduler {
    private Map<String, LocalDateTime> customerDeliveries = new HashMap<>();
    private NotificationService notificationService = new NotificationService();

    public void scheduleDelivery(String customerId, LocalDateTime deliveryTime) {
        customerDeliveries.put(customerId, deliveryTime);
    }

    public void checkAndSendReminders() {
        LocalDateTime now = LocalDateTime.now();
        for (Map.Entry<String, LocalDateTime> entry : customerDeliveries.entrySet()) {
            if (Duration.between(now, entry.getValue()).toMinutes() <= 60 &&
                Duration.between(now, entry.getValue()).toMinutes() > 0) {
                notificationService.sendReminderToCustomer(entry.getKey(), "Your meal will be delivered in 1 hour.");
            }
        }
    }
}