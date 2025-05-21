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
        boolean running = true;
        while (running ) {
            System.out.println("\n----- Welcome to Special Cook Project -----");
            System.out.println("Login as:");
            System.out.println("1. Customer");
            System.out.println("2. Chef");
            System.out.println("3. Manager");
            System.out.println("0. Exit");
            System.out.print("Choose your role: ");
            String roleChoice = scanner.nextLine().trim();

            switch (roleChoice) {
                case "1":
                    customerMenu();
                    break;
                case "2":
                    chefMenu();
                    break;
                case "3":
                    managerMenu();
                    break;
                case "0":
                    System.out.println("----- Exit System -----");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeSubstitutions() {
        substitutionMap.put("milk", "almond milk");
        substitutionMap.put("cheese", "vegan cheese");
        substitutionMap.put("beef", "tofu");
    }

    // ===== Customer Section =====
    private static void customerMenu() {
        System.out.print("\nEnter your Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        customers.putIfAbsent(customerId, new Customer(String.valueOf(customerId), "User" + customerId));

        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Manage Profile (Preferences & Allergies)");
            System.out.println("2. View Past Orders");
            System.out.println("3. Suggest Personalized Meal Plan");
            System.out.println("4. Customize Meal Order");
            System.out.println("5. Request Delivery Reminder");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    managePreferences();
                    break;
                case "2":
                    viewPastOrders();
                    break;
                case "3":
                    suggestMealPlan();
                    break;
                case "4":
                    customizeMeal();
                    break;
                case "5":
                    notificationManager.sendDeliveryReminder(String.valueOf(customerId));
                    break;
                case "0":
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ===== Chef Section =====
    private static void chefMenu() {
        boolean active = true;
        while (active) {
            System.out.println("\n--- Chef Menu ---");
            System.out.println("1. View Assigned Meals");
            System.out.println("2. View Customer Preferences");
            System.out.println("3. View Customer Order History");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewAssignedMeals();
                    break;
                case "2":
                    viewCustomerPreferences();
                    break;
                case "3":
                    viewCustomerOrderHistory();
                    break;
                case "0":
                    active = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }


    // ===== Manager Section =====
    private static void managerMenu() {
        boolean managing = true;
        while (managing) {
            System.out.println("\n--- Manager Menu ---");
            System.out.println("1. Manage Inventory & Suppliers");
            System.out.println("2. Billing & Financial Reports");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    manageInventoryAndSuppliers();
                    break;
                case "2":
                    billingAndReportsMenu();
                    break;
                case "0":
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    private static void viewAssignedMeals() {
        for (Chef chef : chefs) {
            System.out.println("Chef: " + chef.getName() + " [" + chef.getSpecialization() + "]");
            List<Meal> tasks = chef.getAssignedMeals();
            if (tasks.isEmpty()) {
                System.out.println("  No meals assigned.");
            } else {
                for (Meal meal : tasks) {
                    System.out.println("  - Meal: " + meal.getName());
                    System.out.println("    Ingredients: " + meal.getIngredients());
                }
            }
        }
    }

    private static void viewCustomerPreferences() {
        System.out.print("Enter Customer ID to view preferences: ");
        int id = Integer.parseInt(scanner.nextLine());
        Customer customer = customers.get(id);
        if (customer != null) {
            System.out.println("Customer Preferences:");
            System.out.println("- Dietary Preference: " + customer.getDietaryPreference());
            System.out.println("- Allergy: " + customer.getAllergy());
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void viewCustomerOrderHistory() {
        System.out.print("Enter Customer ID to view order history: ");
        int id = Integer.parseInt(scanner.nextLine());
        Customer customer = customers.get(id);
        if (customer != null) {
            List<String> orders = customer.getOrderHistory();
            if (orders.isEmpty()) {
                System.out.println("No orders found for this customer.");
            } else {
                System.out.println("Order History:");
                for (String order : orders) {
                    System.out.println("- " + order);
                }
            }
        } else {
            System.out.println("Customer not found.");
        }
    }


    // ====== Existing Methods (unchanged) ======
    private static void managePreferences() {
        System.out.println("\n--- Manage Profile ---");
        System.out.print("Enter your customer ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        customers.putIfAbsent(id, new Customer(String.valueOf(id), "User" + id));
        Customer customer = customers.get(id);

        System.out.print("Enter dietary preference (e.g., Vegan): ");
        String pref = scanner.nextLine();
        System.out.print("Enter allergy (e.g., Peanut allergy): ");
        String allergy = scanner.nextLine();

        customer.setPreferences(pref);
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

        Meal meal = new Meal("Custom Meal", id);

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

            Ingredient ing = new Ingredient(ingName);
            meal.addIngredient(ing);
            inventoryManager.useIngredient(ingName);
        }

        String assignedChefName = scheduler.assignTaskToChefAndReturnName(meal);
        notificationManager.notifyChefTask(assignedChefName, "Prepare " + meal.getName());

        customer.addOrder("Ordered: " + meal.getName() +
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
                    System.out.println("  - Meal: " + task.getName());

                    // عرض معلومات العميل
                    int custId = task.getCustomerId();
                    Customer customer = customers.get(custId);
                    if (customer != null) {
                        System.out.println("    Customer ID: " + custId);
                        System.out.println("    Preference: " + customer.getDietaryPreference());
                        System.out.println("    Allergy: " + customer.getAllergy());
                    } else {
                        System.out.println("    Customer information not found.");
                    }

                    System.out.println("    Ingredients: " + task.getIngredients());
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
            System.out.println("0. Back");
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
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter Customer ID: ");
                    String completeId = scanner.nextLine();
                    billingSystem.completeOrder(completeId);
                    break;
                case "2":
                    billingSystem.finalizeOrder();
                    break;
                case "3":
                    System.out.print("Enter Customer ID: ");
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
