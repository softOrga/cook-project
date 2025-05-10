package MyCooking.com.models;

public class NotificationService {
    public void sendAlertToChef(Meal meal) {
        Chef chef = new Chef("Default Chef", "General");
        chef.alertForApproval(meal);
    }
    public void sendReminderToCustomer(String customerId, String message) {
        System.out.println("Reminder to customer [" + customerId + "]: " + message);
    }

    public void notifyChef(String chefName, String message) {
        System.out.println("Notification to Chef [" + chefName + "]: " + message);
    }

    public void sendLowStockAlert(String ingredientName, int quantity) {
        System.out.println("ALERT: Ingredient '" + ingredientName + "' is low on stock (" + quantity + " units left)");
    }
}


