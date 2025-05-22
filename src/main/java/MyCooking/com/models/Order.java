package MyCooking.com.models;

public class Order {
    private String description;
    private String customerName;
    private String customerPhoneNumber;

    public Order(String description, String customerName, String customerPhoneNumber) {
        this.description = description;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getDescription() {
        return description;
    }
    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void displayOrderDetails() {
        System.out.println("Order Description: " + description);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Customer Phone: " + customerPhoneNumber);
    }
}
