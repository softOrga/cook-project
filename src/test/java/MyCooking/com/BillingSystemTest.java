package MyCooking.com;

import MyCooking.com.models.Invoice;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

public class BillingSystemTest {

    private BillingSystem billingSystem;

    @Before
    public void setUp() {
        billingSystem = new BillingSystem("admin123");
    }

    @Test
    public void testAdminLoginCorrectPassword() {
        billingSystem.adminPassword = "admin123";
        assertTrue(billingSystem.adminLogin("admin123"));
        assertTrue(billingSystem.isAdminLoggedIn());
    }

    @Test
    public void testAdminLoginIncorrectPassword() {
        billingSystem.adminPassword = "admin123";
        assertFalse(billingSystem.adminLogin("wrong"));
        assertFalse(billingSystem.isAdminLoggedIn());
    }

    @Test
    public void testAdminLoginNullPassword() {
        assertFalse(billingSystem.adminLogin(null));
        assertFalse(billingSystem.isAdminLoggedIn());
    }

    @Test
    public void testAdminLogout() {
        billingSystem.adminPassword = "admin123";
        billingSystem.adminLogin("admin123");
        billingSystem.adminLogout();
        assertFalse(billingSystem.isAdminLoggedIn());
    }

    @Test
    public void testCompleteOrderValid() {
        assertTrue(billingSystem.completeOrder("customer1", 50.0));
        List<Invoice> invoices = billingSystem.getInvoices();
        assertFalse(invoices.isEmpty());
        assertEquals("customer1", invoices.get(0).getCustomerId());
        assertEquals(50.0, invoices.get(0).getAmount(), 0.01);
    }

    @Test
    public void testCompleteOrderInvalidCustomer() {
        assertFalse(billingSystem.completeOrder(null, 50.0));
        assertFalse(billingSystem.completeOrder("", 50.0));
    }

    @Test
    public void testCompleteOrderWithDefaultAmount() {
        assertTrue(billingSystem.completeOrder("customerDefault"));
        List<Invoice> invoices = billingSystem.getInvoices();
        assertFalse(invoices.isEmpty());
        assertEquals(100.0, invoices.get(0).getAmount(), 0.01);
    }

    @Test
    public void testGenerateAndSendInvoiceValid() {
        assertTrue(billingSystem.generateAndSendInvoice("customer2", 30.0));
        List<Invoice> invoices = billingSystem.getInvoices();
        assertTrue(invoices.stream().anyMatch(inv -> inv.getCustomerId().equals("customer2")));
    }

    @Test
    public void testGenerateAndSendInvoiceInvalidCustomer() {
        assertFalse(billingSystem.generateAndSendInvoice("", 30.0));
        assertFalse(billingSystem.generateAndSendInvoice(null, 30.0));
    }

    @Test
    public void testGenerateAndSendInvoiceDefaultAmount() {
        assertTrue(billingSystem.generateAndSendInvoice("customerDefault"));
    }

    @Test
    public void testFinalizeOrderWithInvoices() {
        billingSystem.completeOrder("c1", 10);
        billingSystem.completeOrder("c2", 20);
        billingSystem.finalizeOrder();
        assertFalse(billingSystem.getInvoices().isEmpty());
    }

    @Test
    public void testFinalizeOrderNoInvoices() {
        billingSystem.clearInvoices();
        billingSystem.finalizeOrder();
        assertTrue(billingSystem.getInvoices().isEmpty());
    }

    @Test
    public void testDisplayFinancialReportWithAdmin() {
        billingSystem.adminPassword = "admin123";
        billingSystem.adminLogin("admin123");
        billingSystem.completeOrder("c1", 100);
        billingSystem.displayFinancialReport();
    }

    @Test
    public void testDisplayFinancialReportWithoutAdmin() {
        billingSystem.clearInvoices();
        billingSystem.adminLogout();
        billingSystem.displayFinancialReport();
    }

    @Test
    public void testClearInvoices() {
        billingSystem.completeOrder("c1", 10);
        billingSystem.clearInvoices();
        assertTrue(billingSystem.getInvoices().isEmpty());
    }

    @Test
    public void testLoadAdminPasswordFromEnvironment() throws Exception {
        System.setProperty("ADMIN_PASSWORD", "envAdmin123");
        BillingSystem system = new BillingSystem();
        Field passwordField = BillingSystem.class.getDeclaredField("adminPassword");
        passwordField.setAccessible(true);
        String password = (String) passwordField.get(system);
        assertEquals("envAdmin123", password);
        System.clearProperty("ADMIN_PASSWORD");
    }

    @Test
    public void testLoadAdminPasswordFromEmptyFile() throws Exception {
        System.clearProperty("ADMIN_PASSWORD");
        BillingSystem system = new BillingSystem();
        Field passwordField = BillingSystem.class.getDeclaredField("adminPassword");
        passwordField.setAccessible(true);
        String password = (String) passwordField.get(system);
        assertEquals("", password);
    }

    @Test
    public void testAdminLoginFailsIfPasswordFileMissing() {
        System.clearProperty("ADMIN_PASSWORD");
        BillingSystem system = new BillingSystem();
        assertFalse(system.adminLogin("anyPassword"));
    }
}




/*package MyCooking.com;

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
    public void testAdminLoginWithEmptyPassword() {
        assertFalse(billingSystem.adminLogin(""));
        assertFalse(billingSystem.isAdminLoggedIn());
    }

    @Test
    public void testAdminLoginWithNullPassword() {
        assertFalse(billingSystem.adminLogin(null));
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
    public void testCompleteOrderWithZeroAmount() {
        boolean result = billingSystem.completeOrder("custZero", 0.0);
        assertTrue(result);
        List<Invoice> invoices = billingSystem.getInvoices();
        assertFalse(invoices.isEmpty());
        assertEquals(0.0, invoices.get(invoices.size() - 1).getAmount(), 0.001);
    }

    @Test
    public void testCompleteOrderWithNegativeAmount() {
        boolean result = billingSystem.completeOrder("custNeg", -100.0);
        
        assertTrue(result);
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
    public void testGenerateAndSendInvoiceWithZeroAmount() {
        boolean result = billingSystem.generateAndSendInvoice("custZero", 0.0);
        assertTrue(result);
    }

    @Test
    public void testGenerateAndSendInvoiceWithNegativeAmount() {
        boolean result = billingSystem.generateAndSendInvoice("custNeg", -50.0);
        assertTrue(result);
    }

    @Test
    public void testFinalizeOrderWithInvoices() {
        billingSystem.completeOrder("cust05", 300.0);
        billingSystem.completeOrder("cust06", 200.0);
        billingSystem.finalizeOrder();
        
    }

    @Test
    public void testFinalizeOrderWithoutInvoices() {
        billingSystem.finalizeOrder();
        
    }

    @Test
    public void testDisplayFinancialReportNotLoggedIn() {
        billingSystem.displayFinancialReport();
        
    }

    @Test
    public void testDisplayFinancialReportLoggedIn() {
        billingSystem.adminLogin("admin123");
        billingSystem.completeOrder("cust06", 500.0);
        billingSystem.displayFinancialReport();
    }

    @Test
    public void testDisplayFinancialReportWithMultipleInvoices() {
        billingSystem.adminLogin("admin123");
        billingSystem.completeOrder("cust1", 100.0);
        billingSystem.completeOrder("cust2", 150.0);
        billingSystem.displayFinancialReport();
       
    }

    @Test
    public void testAdminLogout() {
        billingSystem.adminLogin("admin123");
        billingSystem.adminLogout();
        assertFalse(billingSystem.isAdminLoggedIn());
    }

    @Test
    public void testAdminLogoutWithoutLogin() {
        billingSystem.adminLogout();
        assertFalse(billingSystem.isAdminLoggedIn());
    }

    @Test
    public void testClearInvoices() {
        billingSystem.completeOrder("cust07", 400.0);
        billingSystem.clearInvoices();
        assertEquals(0, billingSystem.getInvoices().size());
    }

    @Test
    public void testClearInvoicesMultipleTimes() {
        billingSystem.completeOrder("custClear", 200.0);
        billingSystem.clearInvoices();
        billingSystem.clearInvoices();
        assertEquals(0, billingSystem.getInvoices().size());
    }

    @Test
    public void testAdminLoginMultipleTimes() {
        assertTrue(billingSystem.adminLogin("admin123"));
        assertTrue(billingSystem.adminLogin("admin123")); 
        assertTrue(billingSystem.isAdminLoggedIn());
    }
    
    @Test
    public void testAdminLoginWithNullPassword() {
        assertFalse(billingSystem.adminLogin(null));
        assertFalse(billingSystem.isAdminLoggedIn());
    }

    @Test
    public void testCompleteOrderWithZeroAndNegativeAmounts() {
        assertTrue(billingSystem.completeOrder("custZero", 0.0));
        assertTrue(billingSystem.completeOrder("custNegative", -50.0)); // if allowed
        List<Invoice> invoices = billingSystem.getInvoices();
        assertTrue(invoices.stream().anyMatch(i -> i.getAmount() == 0.0));
        assertTrue(invoices.stream().anyMatch(i -> i.getAmount() == -50.0));
    }

    @Test
    public void testGenerateAndSendInvoiceWithZeroAndNegativeAmounts() {
        assertTrue(billingSystem.generateAndSendInvoice("custZero", 0.0));
        assertTrue(billingSystem.generateAndSendInvoice("custNegative", -10.0));
    }

    @Test
    public void testFinalizeOrderWithInvoicesAssertions() {
        billingSystem.completeOrder("custFinal", 123.45);
        billingSystem.finalizeOrder();
        // Since finalizeOrder() only logs, consider using a log-capturing tool (e.g. LogCaptor)
        // For now just ensure invoices list is not empty
        assertFalse(billingSystem.getInvoices().isEmpty());
    }

    @Test
    public void testClearInvoicesThenCheckEmpty() {
        billingSystem.completeOrder("custClear", 100.0);
        billingSystem.clearInvoices();
        assertEquals(0, billingSystem.getInvoices().size());
    }

    @Test
    public void testAdminLogoutAfterLogin() {
        billingSystem.adminLogin("admin123");
        billingSystem.adminLogout();
        assertFalse(billingSystem.isAdminLoggedIn());
    }


} 
*/
