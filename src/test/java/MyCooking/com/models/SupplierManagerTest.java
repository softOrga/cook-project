package MyCooking.com.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class SupplierManagerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private SupplierManager supplierManager;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        supplierManager = new SupplierManager();
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testRequestRestock_PrintsExpectedMessages() {
        String ingredient = "tomato";

        supplierManager.requestRestock(ingredient);

        String output = outContent.toString();

        assertTrue(output.contains("Low stock detected for ingredient: " + ingredient));
        assertTrue(output.contains("Sending automatic restock request to supplier for: " + ingredient));
        assertTrue(output.contains("Supplier has confirmed restock of: " + ingredient));
    }
}

