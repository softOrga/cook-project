package MyCooking.com;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        CustomerProfileManager customerManager = new CustomerProfileManager();
        OrderCustomizationManager orderManager = new OrderCustomizationManager();
        TaskSchedulerManager taskScheduler = new TaskSchedulerManager();
        InventoryManager inventoryManager = new InventoryManager(); 
        Scanner scanner = new Scanner(System.in);

        System.out.println("**** Welcome to Special Cook Project Management System ****");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Store dietary preferences and allergies");
            System.out.println("2. Add a new order to order history");
            System.out.println("3. View customer's order history");
            System.out.println("4. View customer's dietary preferences");
            System.out.println("5. Suggest personalized meals");
            System.out.println("6. Start new custom meal request");
            System.out.println("7. Select ingredients for custom meal");
            System.out.println("8. Save custom meal and validate ingredients");
            System.out.println("9. Simulate selecting unavailable ingredient");
            System.out.println("10. Detect issue and suggest alternative");
            System.out.println("11. Assign a new task to a chef");
            System.out.println("12. Chef login and view tasks");
            System.out.println("13. View current stock levels");     
            System.out.println("14. Use an ingredient");          
            System.out.println("15. Check if restocking is needed");  
            System.out.println("16. Fetch supplier prices");          
            System.out.println("17. Generate automatic purchase order");
            System.out.println("18. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter dietary preference (e.g., Vegan, Vegetarian): ");
                    String diet = scanner.nextLine();
                    System.out.print("Enter allergy (e.g., Peanut allergy, Gluten allergy): ");
                    String allergy = scanner.nextLine();
                    customerManager.storePreferences(customerId, diet, allergy);
                    break;

                case 2:
                    System.out.print("Enter customer ID: ");
                    String customerIdForOrder = scanner.nextLine();
                    System.out.print("Enter order details (ingredients separated by commas): ");
                    String order = scanner.nextLine();
                    customerManager.addOrder(customerIdForOrder, order);
                    break;

                case 3:
                    System.out.print("Enter customer ID: ");
                    String customerIdForHistory = scanner.nextLine();
                    customerManager.displayOrderHistory(customerIdForHistory);
                    break;

                case 4:
                    System.out.print("Enter customer ID: ");
                    String customerIdForPrefs = scanner.nextLine();
                    customerManager.viewPreferences(customerIdForPrefs);
                    break;

                case 5:
                    System.out.print("Enter customer ID: ");
                    String customerIdForSuggestions = scanner.nextLine();
                    customerManager.suggestPersonalizedMeals(customerIdForSuggestions);
                    break;

                case 6:
                    orderManager.startMealRequest();
                    break;

                case 7:
                    System.out.print("Enter first ingredient: ");
                    String ing1 = scanner.nextLine();
                    System.out.print("Enter second ingredient: ");
                    String ing2 = scanner.nextLine();
                    System.out.print("Enter third ingredient: ");
                    String ing3 = scanner.nextLine();
                    orderManager.selectIngredients(ing1, ing2, ing3);
                    break;

                case 8:
                    orderManager.saveMeal();
                    orderManager.validateIngredients();
                    orderManager.confirmMeal();
                    break;

                case 9:
                    orderManager.selectUnavailableIngredient();
                    break;

                case 10:
                    orderManager.detectIssue();
                    orderManager.suggestAlternative();
                    orderManager.alertChef();
                    break;

                case 11:
                    System.out.print("Enter chef name: ");
                    String chefName = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String task = scanner.nextLine();
                    taskScheduler.assignTaskToChef(chefName, task);
                    break;

                case 12:
                    System.out.print("Enter chef name: ");
                    String loginChef = scanner.nextLine();
                    taskScheduler.chefLogin(loginChef);
                    taskScheduler.viewChefTasks(loginChef);
                    break;

                case 13:
                    inventoryManager.monitorStock();
                    break;

                case 14:
                    System.out.print("Enter ingredient name: ");
                    String ingredient = scanner.nextLine();
                    System.out.print("Enter quantity to use: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); 
                    inventoryManager.useIngredient(ingredient, quantity);
                    break;

                case 15:
                    inventoryManager.checkForRestocking();
                    break;

                case 16:
                    inventoryManager.fetchSupplierPrices();
                    break;

                case 17:
                    inventoryManager.generatePurchaseOrder();
                    break;

                case 18:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}