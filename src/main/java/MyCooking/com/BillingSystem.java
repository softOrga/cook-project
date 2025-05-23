package MyCooking.com;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import MyCooking.com.models.Invoice;

public class BillingSystem {
    private static final Logger logger = Logger.getLogger(BillingSystem.class.getName());
    private final List<Invoice> invoices = new ArrayList<>();
    private boolean adminLoggedIn = false;
    String adminPassword = "";

    public BillingSystem() {
        loadAdminPassword();
    }

    private void loadAdminPassword() {
        String envPassword = System.getenv("ADMIN_PASSWORD");
        if (envPassword != null && !envPassword.trim().isEmpty()) {
            adminPassword = envPassword.trim();
            logger.info("Admin password loaded from environment variable.");
            return;
        }

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("password.txt")) {
            if (input == null) {
                logger.warning("password.txt not found in resources folder");
                adminPassword = "";
                return;
            }
            try (Scanner scanner = new Scanner(input)) {
                if (scanner.hasNextLine()) {
                    adminPassword = scanner.nextLine().trim();
                    logger.info("Admin password loaded from file.");
                } else {
                    logger.warning("password.txt is empty.");
                    adminPassword = "";
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading admin password file", e);
            adminPassword = "";
        }
    }

    public boolean completeOrder(String customerId, double amount) {
    	 if (customerId == null || customerId.isEmpty()) {
             logger.warning("Invalid customer ID provided.");
             return false;
         }
         if (amount <= 0) {
             logger.warning("Invalid amount provided.");
             return false;
         }
         Invoice invoice = new Invoice(customerId, amount);
         invoices.add(invoice);
         logger.log(Level.INFO, "Order completed for customer: {0}", customerId);
         return true;
     }

     public boolean completeOrder(String customerId) {
         return completeOrder(customerId, 100.0);
     }

     public void finalizeOrder() {
         if (invoices.isEmpty()) {
             logger.info("No invoices to finalize.");
             return;
         }
         double total = invoices.stream().mapToDouble(Invoice::getAmount).sum();
         logger.log(Level.INFO, "Order has been finalized.");
         logger.log(Level.INFO, "Total order: ${0}", total);
     }

     public boolean generateAndSendInvoice(String customerId, double amount) {
    	 if (customerId == null || customerId.isEmpty()) {
             logger.warning("Invalid customer ID provided.");
             return false;
         }
         if (amount <= 0) {
             logger.warning("Invalid amount provided.");
             return false;
         }
         Invoice invoice = new Invoice(customerId, amount);
         invoices.add(invoice);
         logger.log(Level.INFO, "Invoice generated and sent to customer: {0}", customerId);
         return true;
     }

     public boolean generateAndSendInvoice(String customerId) {
         return generateAndSendInvoice(customerId, 100.0);
     }

     public boolean adminLogin(String password) {
         if (password == null) {
             logger.warning("Password is null.");
             return false;
         }
         if (password.equals(adminPassword)) {
             adminLoggedIn = true;
             logger.log(Level.INFO, "Admin logged in successfully.");
             return true;
         } else {
             logger.log(Level.WARNING, "Incorrect password. Access denied.");
             return false;
         }
     }

     public boolean isAdminLoggedIn() {
         return adminLoggedIn;
     }

     public void displayFinancialReport() {
         if (!adminLoggedIn) {
             logger.log(Level.WARNING, "Access denied. Admin login required.");
             return;
         }
         if (invoices.isEmpty()) {
             logger.info("No financial data to report.");
             return;
         }
         double total = invoices.stream().mapToDouble(Invoice::getAmount).sum();
         logger.log(Level.INFO, "--- Financial Report ---");
         logger.log(Level.INFO, "Total invoices: {0}", invoices.size());
         logger.log(Level.INFO, "Total revenue: ${0}", total);
     }

     public List<Invoice> getInvoices() {
         return new ArrayList<>(invoices);
     }

     public void clearInvoices() {
         invoices.clear();
     }

     public void adminLogout() {
         adminLoggedIn = false;
         logger.info("Admin logged out.");
     }
 
     }
    