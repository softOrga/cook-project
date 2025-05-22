package MyCooking.com;

import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import MyCooking.com.models.Invoice;

public class BillingSystem {
    private static final Logger logger = Logger.getLogger(BillingSystem.class.getName());
    private List<Invoice> invoices = new ArrayList<>();
    private boolean adminLoggedIn = false;
    private final String ADMIN_PASSWORD = "admin123";

    public void completeOrder(String customerId) {
        Invoice invoice = new Invoice(customerId, 100.0);
        invoices.add(invoice);
        logger.log(Level.INFO, "Order completed for customer ID: {0}", customerId);
    }

    public void finalizeOrder() {
        double total = invoices.stream().mapToDouble(Invoice::getAmount).sum();
        logger.log(Level.INFO, "Order has been finalized.");
        logger.log(Level.INFO, "Total order: ${0}", total);
    }

    public void generateAndSendInvoice(String customerId) {
        Invoice invoice = new Invoice(customerId, 100.0);
        invoices.add(invoice);
        logger.log(Level.INFO, "Invoice generated and sent to customer: {0}", customerId);
    }

    public void adminLogin(String password) {
        if (password.equals(ADMIN_PASSWORD)) {
            adminLoggedIn = true;
            logger.log(Level.INFO, "Admin logged in.");
        } else {
            adminLoggedIn = false; 
            logger.log(Level.WARNING, "Incorrect password. Access denied.");
        }
    }

    public void displayFinancialReport() {
        if (!adminLoggedIn) {
            logger.log(Level.WARNING, "Access denied. Admin login required.");
            return;
        }
        double total = invoices.stream().mapToDouble(Invoice::getAmount).sum();
        logger.log(Level.INFO, "--- Financial Report ---");
        logger.log(Level.INFO, "Total Invoices: {0}", invoices.size());
        logger.log(Level.INFO, "Total Revenue: ${0}", total);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void reset() {
        invoices.clear();
        adminLoggedIn = false;
    }

    public boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }
}
