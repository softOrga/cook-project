package MyCooking.com.models;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collections;

public class DeliverySchedulerTest {

    private DeliveryScheduler scheduler;

    @Before
    public void setup() {
        scheduler = new DeliveryScheduler();
    }

    @Test
    public void testScheduleDeliveryAndSendReminder() {
        String customerId = "cust123";
        
        LocalDateTime deliveryTime = LocalDateTime.now().plusMinutes(30);
        scheduler.scheduleDelivery(customerId, deliveryTime);

        
        scheduler.checkAndSendReminders();
    }

    @Test
    public void testNoReminderForDistantDelivery() {
        String customerId = "cust456";
        
        LocalDateTime deliveryTime = LocalDateTime.now().plusHours(2);
        scheduler.scheduleDelivery(customerId, deliveryTime);

        
        scheduler.checkAndSendReminders();
    }

    @Test
    public void testNoReminderForPastDelivery() {
        String customerId = "cust789";
        
        LocalDateTime deliveryTime = LocalDateTime.now().minusMinutes(10);
        scheduler.scheduleDelivery(customerId, deliveryTime);

        
        scheduler.checkAndSendReminders();
    }
}

