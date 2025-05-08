package MyCooking.com;

import MyCooking.com.models.*;
import java.util.*;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<Integer, Customer> customers = new HashMap<>();
    private static Map<String, String> substitutionMap = new HashMap<>();
    private static final Set<String> ALLOWED_INGREDIENTS = new HashSet<>(Arrays.asList(
        "tomato", "potato", "onion", "lettuce", "cheese", "chicken", "beef",
        "milk", "almond milk", "vegan cheese", "tofu", "carrot", "pepper", "egg"
    ));
    private static List<Chef> chefs = Arrays.asList(
        new Chef("Ali", "Grill"),
        new Chef("Sara", "Vegan"),
        new Chef("Default Chef", "General")
    );
    private static TaskScheduler scheduler = new TaskScheduler(chefs);
    private static InventoryManager inventoryManager = new InventoryManager();
    private static BillingSystem billingSystem = new BillingSystem();
    private static NotificationManager notificationManager = new NotificationManager();

    public static void main(String[] args) {
        initializeSubstitutions();
        while (true) {
            System.out.println("\n=== Welcome to Special Cook Project Management System ===");
            System.out.println("Main Menu:");
            System.out.println("1. Manage dietary preferences and allergies");
            System.out.println("2. View past orders");
            System.out.println("3. Suggest personalized meal plan");
            System.out.println("4. Customize meal order");
            System.out.println("5. Chef Interface (View Assigned Tasks)");
            System.out.println("6. Inventory and Supplier Management");
            System.out.println("7. Billing and Financial Reports");
            System.out.println("8. Send delivery reminder to customer");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    managePreferences();
                    break;
                case 2:
                    viewPastOrders();
                    break;
                case 3:
                    suggestMealPlan();
                    break;
                case 4:
                    customizeMeal();
                    break;
                case 5:
                    chefInterface();
                    break;
                case 6:
                    manageInventoryAndSuppliers();
                    break;
                case 7:
                    billingAndReportsMenu();
                    break;
                case 8:
                    System.out.print("Enter Customer ID: ");
                    String custId = scanner.nextLine();
                    notificationManager.sendDeliveryReminder(custId);
                    break;
                case 0:
                    System.out.println("-----Exit System-----");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private static void initializeSubstitutions() {
        substitutionMap.put("milk", "almond milk");
        substitutionMap.put("cheese", "vegan cheese");
        substitutionMap.put("beef", "tofu");
    }

    private static void managePreferences() {
        System.out.println("\n--- Manage Profile ---");
        System.out.print("Enter your customer ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        customers.putIfAbsent(id, new Customer(String.valueOf(id), "User" + id, "0000000000"));
        Customer customer = customers.get(id);

        System.out.print("Enter dietary preference (e.g., Vegan): ");
        String pref = scanner.nextLine();
        System.out.print("Enter allergy (e.g., Peanut allergy): ");
        String allergy = scanner.nextLine();

        customer.setDietaryPreference(pref);
        customer.setAllergy(allergy);
        System.out.println("Preferences saved successfully!");
    }

    private static void viewPastOrders() {
        System.out.print("Enter your customer ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Customer customer = customers.get(id);
        if (customer != null) {
            System.out.println("\n--- View Past Orders ---");
            List<String> orders = customer.getOrderHistory();
            if (orders.isEmpty()) {
                System.out.println("No past orders found.");
            } else {
                for (String order : orders) {
                    System.out.println("- " + order);
                }
            }
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void suggestMealPlan() {
        System.out.print("Enter your customer ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Customer customer = customers.get(id);
        if (customer != null) {
            System.out.println("--- Personalized Meal Plan ---");
            System.out.println("Based on your preferences, here are some meal suggestions...");
            System.out.println("- Vegan Salad (lettuce, tomato, carrot)");
            System.out.println("- Tofu Stir Fry (tofu, pepper, onion)");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void customizeMeal() {
        System.out.println("\n--- Customize Meal Order ---");
        System.out.print("Enter your customer ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Customer customer = customers.get(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        Meal meal = new Meal("Custom Meal");
        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter ingredient " + i + ": ");
            String ingName = scanner.nextLine();

            if (!ALLOWED_INGREDIENTS.contains(ingName)) {
                System.out.println("Invalid or unavailable ingredient: " + ingName);
                continue;
            }

            if ((customer.getDietaryPreference() != null && customer.getDietaryPreference().toLowerCase().contains("vegan")) ||
                (customer.getAllergy() != null && customer.getAllergy().toLowerCase().contains("lactose"))) {
                if (substitutionMap.containsKey(ingName)) {
                    String substitute = substitutionMap.get(ingName);
                    System.out.println("Substituting '" + ingName + "' with '" + substitute + "' due to restrictions.");
                    ingName = substitute;
                    new NotificationService().sendAlertToChef(meal);
                }
            }

            Ingredient ing = new Ingredient(ingName, true, false);
            meal.addIngredient(ing);
            inventoryManager.useIngredient(ingName);
        }

        String assignedChefName = scheduler.assignTaskToChefAndReturnName(meal);
        notificationManager.notifyChefTask(assignedChefName, "Prepare " + meal.getMealName());

        customer.addToOrderHistory("Ordered: " + meal.getMealName() +
            " with ingredients: " + meal.getIngredients() +
            " | Assigned Chef: " + assignedChefName);

        System.out.println("Order saved and assigned to chef.");
    }

    private static void chefInterface() {
        System.out.println("\n--- Chef Interface ---");
        for (Chef chef : chefs) {
            System.out.println("Chef: " + chef.getName() + " [" + chef.getSpecialization() + "]");
            List<Meal> tasks = chef.getAssignedMeals();
            if (tasks.isEmpty()) {
                System.out.println("  No tasks assigned.");
            } else {
                for (Meal task : tasks) {
                    System.out.println("  - Meal: " + task.getMealName());
                }
            }
        }
    }

    private static void manageInventoryAndSuppliers() {
        boolean managing = true;
        while (managing) {
            System.out.println("\nInventory & Supplier Management Menu:");
            System.out.println("1. View current stock and low ingredients");
            System.out.println("2. Suggest restocking");
            System.out.println("3. Open purchasing interface");
            System.out.println("4. Fetch real-time prices from suppliers");
            System.out.println("5. Detect critically low stock");
            System.out.println("6. Automatically generate purchase orders");
            System.out.println("7. View all generated purchase orders");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    inventoryManager.viewStock();
                    break;
                case "2":
                    inventoryManager.suggestRestocking();
                    break;
                case "3":
                    inventoryManager.openPurchasingInterface();
                    break;
                case "4":
                    inventoryManager.fetchSupplierPrices();
                    break;
                case "5":
                    List<String> lowStock = inventoryManager.detectCriticalStock();
                    for (String ingredient : lowStock) {
                        notificationManager.sendLowStockAlert(ingredient);
                    }
                    break;
                case "6":
                    inventoryManager.generatePurchaseOrder();
                    break;
                case "7":
                    System.out.println("\n=== Purchase Orders ===");
                    inventoryManager.viewPurchaseOrders();
                    break;
                case "0":
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void billingAndReportsMenu() {
        boolean managing = true;
        while (managing) {
            System.out.println("\n--- Billing & Financial Reports ---");
            System.out.println("1. Complete order (simulate)");
            System.out.println("2. Finalize order");
            System.out.println("3. Generate and send invoice");
            System.out.println("4. Admin login");
            System.out.println("5. Request financial report");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter Customer ID for completing order: ");
                    String completeId = scanner.nextLine();
                    billingSystem.completeOrder(completeId);
                    break;
                case "2":
                    billingSystem.finalizeOrder();
                    break;
                case "3":
                    System.out.print("Enter Customer ID for Invoice: ");
                    String cid = scanner.nextLine();
                    billingSystem.generateAndSendInvoice(cid);
                    break;
                case "4":
                    System.out.print("Enter admin password: ");
                    String password = scanner.nextLine();
                    billingSystem.adminLogin(password);
                    break;
                case "5":
                    billingSystem.displayFinancialReport();
                    break;
                case "0":
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}


/*import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        CustomerProfileManager customerManager = new CustomerProfileManager();
        OrderCustomizationManager orderManager = new OrderCustomizationManager();
        TaskSchedulerManager taskScheduler = new TaskSchedulerManager();
        InventoryManager inventoryManager = new InventoryManager(); 
        BillingSystem billingSystem = new BillingSystem(); 

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
            System.out.println("18. Complete Order (Billing)");
            System.out.println("19. Finalize Order (Billing)");
            System.out.println("20. Generate and Send Invoice");
            System.out.println("21. Admin Login for Financial Reports");
            System.out.println("22. Request Financial Data");
            System.out.println("23. Display Financial Reports");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter dietary preference: ");
                    String diet = scanner.nextLine();
                    System.out.print("Enter allergy: ");
                    String allergy = scanner.nextLine();
                    customerManager.storePreferences(customerId, diet, allergy);
                    break;
                case 2:
                    System.out.print("Enter customer ID: ");
                    String customerIdForOrder = scanner.nextLine();
                    System.out.print("Enter order details: ");
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
                    billingSystem.completeOrder();
                    break;
                case 19:
                    billingSystem.finalizeOrder();
                    break;
                case 20:
                    billingSystem.generateAndSendInvoice();
                    break;
                case 21:
                    billingSystem.adminLogin();
                    break;
                case 22:
                    billingSystem.requestFinancialData();
                    break;
                case 23:
                    billingSystem.displayFinancialReports();
                    break;
                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

*/