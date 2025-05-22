package MyCooking.com;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import MyCooking.com.models.Invoice;

public class BillingSystem {
    private static final Logger logger = Logger.getLogger(BillingSystem.class.getName());
    private List<Invoice> invoices = new ArrayList<>();
    private boolean adminLoggedIn = false;
    private String adminPassword = "";

    public BillingSystem() {
        loadAdminPassword();
    }

    private void loadAdminPassword() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("password.txt")) {
            if (input == null) {
                logger.warning("password.txt not found in resources folder");
                adminPassword = "";
                return;
            }
            Scanner scanner = new Scanner(input);
            if (scanner.hasNextLine()) {
                adminPassword = scanner.nextLine().trim();
                logger.info("Admin password loaded from file.");
            } else {
                logger.warning("password.txt is empty.");
                adminPassword = "";
            }
            scanner.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading admin password file", e);
            adminPassword = "";
        }
    }

    public void completeOrder(String customerId) {
        Invoice invoice = new Invoice(customerId, 100.0);
        invoices.add(invoice);
        logger.log(Level.INFO, "Order completed for customer: {0}", customerId);
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
        if (password.equals(adminPassword)) {
            adminLoggedIn = true;
            logger.log(Level.INFO, "Admin logged in successfully.");
        } else {
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
        logger.log(Level.INFO, "Total invoices: {0}", invoices.size());
        logger.log(Level.INFO, "Total revenue: ${0}", total);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
}
