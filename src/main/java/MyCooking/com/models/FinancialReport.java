package MyCooking.com.models;

import java.util.List;

public class FinancialReport {
    private List<Invoice> invoices;

    public FinancialReport(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void displayReport() {
        double totalRevenue = invoices.stream().mapToDouble(Invoice::getAmount).sum();
        System.out.println("--- Financial Report ---");
        System.out.println("Total Invoices: " + invoices.size());
        System.out.println("Total Revenue: $" + totalRevenue);
    }
}
