package MyCooking.com.models;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class InvoiceTest {

    private Invoice invoice;

    @Before
    public void setUp() {
        invoice = new Invoice("cust100", 299.99);
    }

    @Test
    public void testInvoiceIdIncrements() {
        int id1 = invoice.getInvoiceId();
        Invoice another = new Invoice("cust200", 100.0);
        int id2 = another.getInvoiceId();
        assertTrue(id2 > id1);
    }

    @Test
    public void testAmount() {
        assertEquals(299.99, invoice.getAmount(), 0.001);
    }

    @Test
    public void testCustomerId() {
        assertEquals("cust100", invoice.getCustomerId());
    }

    @Test
    public void testDateIsToday() {
        assertEquals(LocalDate.now(), invoice.getDate());
    }

    @Test
    public void testToStringContainsImportantInfo() {
        String output = invoice.toString();
        assertTrue(output.contains("Invoice #"));
        assertTrue(output.contains("cust100"));
        assertTrue(output.contains("299.99"));
        assertTrue(output.contains(LocalDate.now().toString()));
    }
}
