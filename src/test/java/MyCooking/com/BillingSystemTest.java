package MyCooking.com;

import MyCooking.com.models.Invoice;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BillingSystemTest {

    private BillingSystem billingSystem;

    @Before
    public void setUp() {
        billingSystem = new BillingSystem();
    }

    @Test
    public void testAdminLoginSuccess() {
        assertTrue(billingSystem.adminLogin("admin123"));
        assertTrue(billingSystem.isAdminLoggedIn());
    }

    @Test
    public void testAdminLoginFailure() {
        assertFalse(billingSystem.adminLogin("wrongPassword"));
        assertFalse(billingSystem.isAdminLoggedIn());
    }

    @Test
    public void testCompleteOrderWithAmount() {
        boolean result = billingSystem.completeOrder("cust01", 250.0);
        assertTrue(result);
        List<Invoice> invoices = billingSystem.getInvoices();
        assertEquals(1, invoices.size());
        assertEquals(250.0, invoices.get(0).getAmount(), 0.001);
    }

    @Test
    public void testCompleteOrderDefaultAmount() {
        boolean result = billingSystem.completeOrder("cust02");
        assertTrue(result);
        List<Invoice> invoices = billingSystem.getInvoices();
        assertEquals(1, invoices.size());
        assertEquals(100.0, invoices.get(0).getAmount(), 0.001);
    }

    @Test
    public void testCompleteOrderInvalidCustomer() {
        assertFalse(billingSystem.completeOrder(""));
        assertFalse(billingSystem.completeOrder(null));
    }

    @Test
    public void testGenerateAndSendInvoice() {
        boolean result = billingSystem.generateAndSendInvoice("cust03", 180.0);
        assertTrue(result);
    }

    @Test
    public void testGenerateAndSendInvoiceDefaultAmount() {
        boolean result = billingSystem.generateAndSendInvoice("cust04");
        assertTrue(result);
    }

    @Test
    public void testGenerateAndSendInvoiceInvalidCustomer() {
        assertFalse(billingSystem.generateAndSendInvoice(""));
        assertFalse(billingSystem.generateAndSendInvoice(null));
    }

    @Test
    public void testFinalizeOrderWithInvoices() {
        billingSystem.completeOrder("cust05", 300.0);
        billingSystem.finalizeOrder(); // Check logs manually
    }

    @Test
    public void testFinalizeOrderWithoutInvoices() {
        billingSystem.finalizeOrder(); // Should log info about no invoices
    }

    @Test
    public void testDisplayFinancialReportNotLoggedIn() {
        billingSystem.displayFinancialReport(); // Should warn about login
    }

    @Test
    public void testDisplayFinancialReportLoggedIn() {
        billingSystem.adminLogin("admin123");
        billingSystem.completeOrder("cust06", 500.0);
        billingSystem.displayFinancialReport();
    }

    @Test
    public void testAdminLogout() {
        billingSystem.adminLogin("admin123");
        billingSystem.adminLogout();
        assertFalse(billingSystem.isAdminLoggedIn());
    }

    @Test
    public void testClearInvoices() {
        billingSystem.completeOrder("cust07", 400.0);
        billingSystem.clearInvoices();
        assertEquals(0, billingSystem.getInvoices().size());
    }
}
