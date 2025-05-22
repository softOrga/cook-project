package MyCooking.com.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class NotificationServiceTest {

    private NotificationService service;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        service = new NotificationService();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testSendAlertToChef() {
        Meal meal = new Meal("Test Meal");
        service.sendAlertToChef(meal);
        String output = outContent.toString();
        assertTrue(output.contains("Chef Default Chef has received the meal 'Test Meal' for approval."));
    }

    @Test
    public void testSendReminderToCustomer() {
        service.sendReminderToCustomer("123", "Test message");
        String output = outContent.toString();
        assertTrue(output.contains("Reminder to customer [123]: Test message"));
    }

    @Test
    public void testNotifyChef() {
        service.notifyChef("Chef John", "Task reminder");
        String output = outContent.toString();
        assertTrue(output.contains("Notification to Chef [Chef John]: Task reminder"));
    }

    @Test
    public void testSendLowStockAlert() {
        service.sendLowStockAlert("tomato", 2);
        String output = outContent.toString();
        assertTrue(output.contains("ALERT: Ingredient 'tomato' is low on stock (2 units left)"));
    }
}
