package MyCooking.com.models;

public class Ingredient {
    private String name;
    private int quantity;

    public Ingredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return name + " (x" + quantity + ")";
    }
}
