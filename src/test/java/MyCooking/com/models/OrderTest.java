package MyCooking.com.models;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class OrderTest {

    private Order order;

    @Before
    public void setUp() {
        order = new Order("2 Vegan Meals", "Sara Ahmed", "0791234567");
    }

    @Test
    public void testOrderAttributes() {
        assertEquals("2 Vegan Meals", order.getDescription());
        assertEquals("Sara Ahmed", order.getCustomerName());
        assertEquals("0791234567", order.getCustomerPhoneNumber());
    }

    @Test
    public void testDisplayOrderDetails_NoException() {
 
        try {
            order.displayOrderDetails();
        } catch (Exception e) {
            fail("displayOrderDetails should not throw an exception");
        }
    }
}
