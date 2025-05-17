package MyCooking.com.models;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static int idCounter = 1;
    private int id;
    private String username;
    private String customerName;
    private String allergies;
    private String dietaryPreferences;
    private List<String> orderHistory; 

    public Customer(String username, String customerName) {
        this.id = idCounter++; // توليد id تلقائي
        this.username = username;
        this.customerName = customerName;
        this.orderHistory = new ArrayList<>();
    }

    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAllergy() {
        return allergies;
    }

    public String getDietaryPreference() {
        return dietaryPreferences;
    }

    public List<String> getOrderHistory() {
        return orderHistory;
    }

    public void setPreferences(String diet) {
        this.dietaryPreferences = diet;
    }

    public void setAllergy(String allergy) {
        this.allergies = allergy;
    }

    public void addOrder(String orderDetails) {
        orderHistory.add(orderDetails);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", customerName='" + customerName + '\'' +
                ", allergies=" + allergies +
                ", dietaryPreferences=" + dietaryPreferences +
                ", orderHistory=" + orderHistory +
                '}';
    }
}
