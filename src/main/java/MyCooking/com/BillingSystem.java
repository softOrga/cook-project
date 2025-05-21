package MyCooking.com;

import java.util.*;
import MyCooking.com.models.Invoice;

public class BillingSystem {
    private List<Invoice> invoices = new ArrayList<>();
    private boolean adminLoggedIn = false;
    private final String ADMIN_PASSWORD = "admin123";

    public void completeOrder(String customerId) {
        Invoice invoice = new Invoice(customerId, 100.0);
        invoices.add(invoice);
        System.out.println("Order completed for customer ID: " + customerId);
    }

    public void finalizeOrder() {
        double total = invoices.stream().mapToDouble(Invoice::getAmount).sum();
        System.out.println("Order has been finalized.");
        System.out.println("Total order: $" + total);
    }
    public void requestFinancialData() {
        if (!adminLoggedIn) {
            System.out.println("Access denied. Admin login required.");
            return;
        }

        System.out.println("Fetching up-to-date financial data...");
        double total = invoices.stream().mapToDouble(Invoice::getAmount).sum();
        System.out.println("Current Total Revenue: $" + total);
    }


    public void generateAndSendInvoice(String customerId) {
        Invoice invoice = new Invoice(customerId, 100.0); 
        invoices.add(invoice);
        System.out.println("Invoice generated and sent to customer: " + customerId);
    }

    public void adminLogin(String password) {
        if (password.equals(ADMIN_PASSWORD)) {
            adminLoggedIn = true;
            System.out.println("Admin logged in.");
        } else {
            System.out.println("Incorrect password. Access denied.");
        }
    }

    public void displayFinancialReport() {
        if (!adminLoggedIn) {
            System.out.println("Access denied. Admin login required.");
            return;
        }

        double total = invoices.stream().mapToDouble(Invoice::getAmount).sum();
        System.out.println("--- Financial Report ---");
        System.out.println("Total Invoices: " + invoices.size());
        System.out.println("Total Revenue: $" + total);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
}



/*
package MyCooking.com;

import java.util.*;

import MyCooking.com.models.Invoice;

public class BillingSystem {
    private List<Invoice> invoices = new ArrayList<>();
    private boolean adminLoggedIn = false;
    private final String ADMIN_PASSWORD = "admin123";

    public void completeOrder(String customerId) {
        Invoice invoice = new Invoice(customerId, 100.0);
        invoices.add(invoice);
        System.out.println("Order completed for customer ID: " + customerId);
    }

    public void finalizeOrder() {
    	  double total = invoices.stream().mapToDouble(Invoice::getAmount).sum();
        System.out.println("Order has been finalized.");
        System.out.println("Total order: $" + total);
    }

    public void generateAndSendInvoice(String customerId) {
        Invoice invoice = new Invoice(customerId, 100.0); 
        invoices.add(invoice);
        System.out.println("Invoice generated and sent to customer: " + customerId);
    }

    public void adminLogin(String password) {
        if (password.equals(ADMIN_PASSWORD)) {
            adminLoggedIn = true;
            System.out.println("Admin logged in.");
        } else {
            System.out.println("Incorrect password. Access denied.");
        }
    }

    public void displayFinancialReport() {
        if (!adminLoggedIn) {
            System.out.println("Access denied. Admin login required.");
            return;
        }

        double total = invoices.stream().mapToDouble(Invoice::getAmount).sum();
        System.out.println("--- Financial Report ---");
        System.out.println("Total Invoices: " + invoices.size());
        System.out.println("Total Revenue: $" + total);
    }
    public void completeOrder() {
        System.out.println("Order completed.");
    }
    public List<Invoice> getInvoices() {
        return invoices;
    }
    private boolean isAdminLoggedIn = false;
    public void adminLogin() {
        isAdminLoggedIn = true;
        System.out.println("Admin successfully logged in.");
    }
}
*/

