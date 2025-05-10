package MyCooking.com.models;

public class Supplier {
    private String name;
    private double price;
    private int deliveryDays;

    public Supplier(String name, double price, int deliveryDays) {
        this.name = name;
        this.price = price;
        this.deliveryDays = deliveryDays;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }
}

