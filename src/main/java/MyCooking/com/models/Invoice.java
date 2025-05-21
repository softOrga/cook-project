package MyCooking.com.models;

import java.time.LocalDate;

public class Invoice {
    private static int counter = 1;
    private int invoiceId;
    private double amount;
    private String customerId;
    private LocalDate date;

    public Invoice(String customerId, double amount) {
        this.invoiceId = counter++;
        this.amount = amount;
        this.customerId = customerId;
        this.date = LocalDate.now();
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public double getAmount() {
        return amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Invoice #" + invoiceId +
               " | Customer: " + customerId +
               " | Amount: $" + amount +
               " | Date: " + date;
    }
}