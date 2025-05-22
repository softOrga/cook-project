package MyCooking.com.models;

import java.util.logging.Logger;

public class Order {
    private static final Logger logger = Logger.getLogger(Order.class.getName());

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
        logger.info("Order Description: " + description);
        logger.info("Customer Name: " + customerName);
        logger.info("Customer Phone: " + customerPhoneNumber);
    }
}
