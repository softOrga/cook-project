package MyCooking.com;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class NotificationManagerTest {

    private NotificationManager notificationManager;

    @Before
    public void setup() {
        notificationManager = new NotificationManager();
    }

    @Test
    public void testSendDeliveryReminder() {
        notificationManager.sendDeliveryReminder("cust123");
        List<String> notes = notificationManager.getNotifications();
        assertFalse(notes.isEmpty());
        String lastNote = notes.get(notes.size() - 1);
        assertTrue(lastNote.contains("Reminder: Dear customer cust123"));
    }

    @Test
    public void testNotifyChefTask() {
        notificationManager.notifyChefTask("Chef Bob", "Prepare salad");
        List<String> notes = notificationManager.getNotifications();
        assertFalse(notes.isEmpty());
        String lastNote = notes.get(notes.size() - 1);
        assertTrue(lastNote.contains("Notification: Chef Chef Bob, you have a scheduled task: Prepare salad"));
    }

    @Test
    public void testSendLowStockAlert() {
        notificationManager.sendLowStockAlert("Tomatoes");
        List<String> notes = notificationManager.getNotifications();
        assertFalse(notes.isEmpty());
        String lastNote = notes.get(notes.size() - 1);
        assertTrue(lastNote.contains("ALERT: Low stock detected for ingredient: Tomatoes"));
    }

    @Test
    public void testGetNotificationsReturnsAllMessages() {
        notificationManager.sendDeliveryReminder("cust1");
        notificationManager.notifyChefTask("Chef A", "Task1");
        notificationManager.sendLowStockAlert("Salt");

        List<String> notes = notificationManager.getNotifications();
        assertEquals(3, notes.size());
    }

    @Test
    public void testTimestampFormat() {
        notificationManager.sendDeliveryReminder("cust2");
        List<String> notes = notificationManager.getNotifications();
        String message = notes.get(notes.size() - 1);
        
        assertTrue(message.startsWith("["));
        assertTrue(message.contains("] Reminder: Dear customer cust2"));
    }
}
