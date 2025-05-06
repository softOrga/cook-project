package MyCooking.com;

import MyCooking.com.models.CustomerManager;
import MyCooking.com.models.Customer;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerManager customerManager = new CustomerManager();

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter customer phone: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(id, name, phone);
        customerManager.addCustomer(customer);

        while (true) {
            System.out.println("\n=== Welcome to Special Cook Project Management System ===");
            System.out.println("Main Menu:");
            System.out.println("1. Manage dietary preferences and allergies");
            System.out.println("2. View past orders");
            System.out.println("3. Suggest personalized meal plan");
            System.out.println("4. Customize meal order");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageProfile(scanner, customerManager);
                    break;
                case "2":
                    viewPastOrders(scanner, customerManager);
                    break;
                case "3":
                    System.out.println("--- Personalized Meal Plan ---");
                    System.out.println("Based on your preferences, here are some meal suggestions...");
                    break;
                case "4":
                    customizeMealOrder(scanner, customerManager);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void manageProfile(Scanner scanner, CustomerManager manager) {
        System.out.println("\n--- Manage Profile ---");
        System.out.println("1. Enter dietary preference and allergy");
        System.out.println("2. View preferences and allergies");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine();

        System.out.print("Enter your customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = manager.getCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        if (choice.equals("1")) {
            System.out.print("Enter dietary preference (e.g., Vegan): ");
            customer.setDietaryPreference(scanner.nextLine());
            System.out.print("Enter allergy (e.g., Peanut allergy): ");
            customer.setAllergy(scanner.nextLine());
            System.out.println("Preferences saved successfully!");
        } else if (choice.equals("2")) {
            System.out.println("Customer Name: " + customer.getCustomerName());
            System.out.println("Customer Phone: " + customer.getCustomerPhone());
            System.out.println("Dietary Preference: " + customer.getDietaryPreference());
            System.out.println("Allergy: " + customer.getAllergy());
        } else {
            System.out.println("Invalid option.");
        }
    }

    public static void customizeMealOrder(Scanner scanner, CustomerManager manager) {
        System.out.println("\n--- Customize Meal Order ---");
        System.out.print("Enter your customer ID: ");
        String customerId = scanner.nextLine();

        Customer customer = manager.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Starting a new meal request...");
        System.out.print("Enter ingredient 1: ");
        String ing1 = scanner.nextLine();
        System.out.print("Enter ingredient 2: ");
        String ing2 = scanner.nextLine();
        System.out.print("Enter ingredient 3: ");
        String ing3 = scanner.nextLine();

        List<String> ingredients = Arrays.asList(ing1, ing2, ing3);
        System.out.println("Ingredients selected: " + String.join(", ", ingredients));
        System.out.println("Meal saved with ingredients: " + ingredients);

        customer.addToOrderHistory("Custom meal with: " + String.join(", ", ingredients));
    }

    public static void viewPastOrders(Scanner scanner, CustomerManager manager) {
        System.out.print("Enter your customer ID: ");
        String customerId = scanner.nextLine();

        Customer customer = manager.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("\n--- View Past Orders ---");
        List<String> orders = customer.getOrderHistory();
        if (orders.isEmpty()) {
            System.out.println("No past orders found.");
        } else {
            for (String order : orders) {
                System.out.println("- " + order);
            }
        }
    }
}


/*package MyCooking.com;

import java.util.Scanner;

import MyCooking.com.models.Customer;
import MyCooking.com.models.CustomerManager;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerManager customerManager = new CustomerManager();
        OrderCustomizationManager customizationManager = new OrderCustomizationManager(customerManager);

        // Sample customer
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter customer phone: ");
        String customerPhone = scanner.nextLine();

        Customer customer = new Customer(customerId, customerName, customerPhone);
        customerManager.addCustomer(customer);

        System.out.println("=== Welcome to Special Cook Project Management System ===");

        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Manage dietary preferences and allergies");
            System.out.println("2. View past orders");
            System.out.println("3. Suggest personalized meal plan");
            System.out.println("4. Customize meal order");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("\n--- Manage Profile ---");
                    System.out.println("1. Enter dietary preference and allergy");
                    System.out.println("2. View preferences and allergies");
                    System.out.print("Choose an option: ");
                    String subChoice = scanner.nextLine();

                    if (subChoice.equals("1")) {
                        System.out.print("Enter dietary preference (e.g., Vegan): ");
                        String diet = scanner.nextLine();
                        System.out.print("Enter allergy (e.g., Peanut allergy): ");
                        String allergy = scanner.nextLine();
                        customer.setDietaryPreference(diet);
                        customer.setAllergy(allergy);
                        System.out.println("Preferences saved successfully!");
                    } else if (subChoice.equals("2")) {
                        customerManager.displayCustomerInfo(customer);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case "2":
                    System.out.println("\n--- View Past Orders ---");
                    customerManager.displayOrderHistory(customer);
                    break;

                case "3":
                    System.out.println("\n--- Personalized Meal Plan ---");
                    System.out.println("Based on your preferences, here are some meal suggestions...");
                    // Add logic for personalized meal plan suggestion
                    break;

                case "4":
                    System.out.println("\n--- Customize Meal Order ---");
                    customizationManager.startMealRequest();
                    System.out.print("Enter ingredient 1: ");
                    String ing1 = scanner.nextLine();
                    System.out.print("Enter ingredient 2: ");
                    String ing2 = scanner.nextLine();
                    System.out.print("Enter ingredient 3: ");
                    String ing3 = scanner.nextLine();
                    customizationManager.selectIngredients(ing1, ing2, ing3);
                    customizationManager.saveMeal();
                    break;

                case "0":
                    running = false;
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
*/

/*package MyCooking.com;

import MyCooking.com.models.Customer;
import MyCooking.com.models.Order;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // إنشاء كائنات مدير ملف التعريف ومدير تخصيص الوجبات
        CustomerProfileManager profileManager = new CustomerProfileManager();
        OrderCustomizationManager customizationManager = new OrderCustomizationManager();

        // طلب بيانات العميل
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();

        // إنشاء العميل وتخزينه
        Customer customer = new Customer(customerId, customerName, "Not Provided");
        profileManager.addCustomer(customer);

        boolean running = true;

        System.out.println("=== Welcome to Special Cook Project Management System ===");

        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Manage dietary preferences and allergies");
            System.out.println("2. View past orders");
            System.out.println("3. Suggest personalized meal plan");
            System.out.println("4. Customize meal order");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // إدارة التفضيلات الغذائية والحساسيات
                    System.out.println("\n--- Manage Profile ---");
                    System.out.println("1. Enter dietary preference and allergy");
                    System.out.println("2. View preferences and allergies");
                    System.out.print("Choose an option: ");
                    String subChoice = scanner.nextLine();

                    if (subChoice.equals("1")) {
                        // إدخال التفضيلات الغذائية والحساسيات
                        System.out.print("Enter dietary preference (e.g., Vegan): ");
                        String diet = scanner.nextLine();
                        System.out.print("Enter allergy (e.g., Peanut allergy): ");
                        String allergy = scanner.nextLine();
                        profileManager.storePreferences(customerId, diet, allergy);
                        System.out.println("Preferences saved successfully!");
                    } else if (subChoice.equals("2")) {
                        // عرض التفضيلات والحساسيات
                        profileManager.viewPreferences(customerId);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case "2":
                    // عرض الطلبات السابقة
                    System.out.println("\n--- View Past Orders ---");
                    profileManager.displayOrderHistory(customerId);
                    break;

                case "3":
                    // اقتراح خطة وجبات مخصصة
                    System.out.println("\n--- Personalized Meal Plan ---");
                    profileManager.suggestPersonalizedMeals(customerId);
                    break;

                case "4":
                    // تخصيص طلب الوجبة
                    System.out.println("\n--- Customize Meal Order ---");
                    customizationManager.customizeMeal(customerId);
                    break;

                case "0":
                    // إنهاء التطبيق
                    running = false;
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}



/*package MyCooking.com;

import MyCooking.com.CustomerProfileManager;
import MyCooking.com.OrderCustomizationManager;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerProfileManager profileManager = new CustomerProfileManager();
        OrderCustomizationManager customizationManager = new OrderCustomizationManager();

        String customerId = "customer1";
        boolean running = true;

        System.out.println("=== Welcome to Special Cook Project Management System ===");

        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Manage dietary preferences and allergies");
            System.out.println("2. View past orders");
            System.out.println("3. Suggest personalized meal plan");
            System.out.println("4. Customize meal order");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("\n--- Manage Profile ---");
                    System.out.println("1. Enter dietary preference and allergy");
                    System.out.println("2. View preferences and allergies");
                    System.out.print("Choose an option: ");
                    String subChoice = scanner.nextLine();

                    if (subChoice.equals("1")) {
                        System.out.print("Enter dietary preference (e.g., Vegan): ");
                        String diet = scanner.nextLine();
                        System.out.print("Enter allergy (e.g., Peanut allergy): ");
                        String allergy = scanner.nextLine();
                        profileManager.storePreferences(customerId, diet, allergy);
                    } else if (subChoice.equals("2")) {
                        profileManager.viewPreferences(customerId);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case "2":
                    profileManager.displayOrderHistory(customerId);
                    break;

                case "3":
                    profileManager.suggestPersonalizedMeals(customerId);
                    break;

                case "4":
                   customizationManager.customizeMeal();
                    break;

                case "0":
                    running = false;
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

/*


/*
package MyCooking.com;


import MyCooking.com.CustomerProfileManager;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerProfileManager profileManager = new CustomerProfileManager();

        String customerId = "customer1";
        boolean running = true;

        System.out.println("=== Welcome to Special Cook Project Management System ===");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Enter dietary preferences and allergies");
            System.out.println("2. View preferences and allergies");
            System.out.println("3. View past orders");
            System.out.println("4. Suggest personalized meal plan");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter dietary preference (e.g., Vegan): ");
                    String diet = scanner.nextLine();
                    System.out.print("Enter allergy (e.g., Peanut allergy): ");
                    String allergy = scanner.nextLine();
                    profileManager.storePreferences(customerId, diet, allergy);
                    break;

                case 2:
                    profileManager.viewPreferences(customerId);
                    break;

                case 3:
                    profileManager.displayOrderHistory(customerId);
                    break;

                case 4:
                    profileManager.suggestPersonalizedMeals(customerId);
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
*/
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