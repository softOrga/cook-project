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
        logger.info("Order completed for customer ID: " + customerId);
    }

    public void finalizeOrder() {
        double total = invoices.stream().mapToDouble(Invoice::getAmount).sum();
        logger.info("Order has been finalized.");
        logger.info("Total order: $" + total);
    }

    public void generateAndSendInvoice(String customerId) {
        Invoice invoice = new Invoice(customerId, 100.0);
        invoices.add(invoice);
        logger.info("Invoice generated and sent to customer: " + customerId);
    }

    public void adminLogin(String password) {
        if (password.equals(ADMIN_PASSWORD)) {
            adminLoggedIn = true;
            logger.info("Admin logged in.");
        } else {
            logger.warning("Incorrect password. Access denied.");
        }
    }

    public void displayFinancialReport() {
        if (!adminLoggedIn) {
            logger.warning("Access denied. Admin login required.");
            return;
        }
        double total = invoices.stream().mapToDouble(Invoice::getAmount).sum();
        logger.info("--- Financial Report ---");
        logger.info("Total Invoices: " + invoices.size());
        logger.info("Total Revenue: $" + total);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
}
