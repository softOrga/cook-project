package MyCooking.com.models;

import org.junit.*;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class CustomerManagerTest {

    private CustomerManager manager;
    private final String TEST_DIR = "customer_profiles/";

    @Before
    public void setUp() {
        manager = new CustomerManager();
    }

    @After
    public void tearDown() {
      
        File dir = new File(TEST_DIR);
        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                file.delete();
            }
            dir.delete();
        }
    }

    @Test
    public void testCreateNewProfile() {
        String input = "John Doe\nNuts,Lactose\nVegan\n";
        Scanner scanner = new Scanner(input);
        Customer customer = manager.getOrCreateCustomerProfile(scanner, "john_test");

        assertEquals("John Doe", customer.getCustomerName());
        assertEquals("Nuts,Lactose", customer.getAllergy());
        assertEquals("Vegan", customer.getDietaryPreference());
    }

    @Test
    public void testLoadExistingProfile() throws IOException {
       
        File profile = new File(TEST_DIR + "jane_test.txt");
        profile.getParentFile().mkdirs();
        try (PrintWriter out = new PrintWriter(profile)) {
            out.println("Jane Smith");
            out.println("Gluten");
            out.println("Vegetarian");
        }

        Scanner scanner = new Scanner("");
        Customer customer = manager.getOrCreateCustomerProfile(scanner, "jane_test");

        assertEquals("Jane Smith", customer.getCustomerName());
        assertEquals("Gluten", customer.getAllergy());
        assertEquals("Vegetarian", customer.getDietaryPreference());
    }

    @Test
    public void testCreateCustomMeal() {
        String input = "Healthy Bowl\nTomato\nLettuce\nCheese\ndone\n";
        Scanner scanner = new Scanner(input);
        Customer customer = new Customer("user1", "Test User");

        Meal meal = manager.createCustomMeal(scanner, customer);

        assertEquals("Healthy Bowl", meal.getName());
        assertEquals(3, meal.getIngredients().size());
        assertEquals("Tomato", meal.getIngredients().get(0).getName());
    }

    @Test
    public void testCollectCustomerPreferences() {
        Customer customer = new Customer("user1", "Test User");
        customer.setAllergy("Nuts");
        customer.setPreferences("Vegan");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));

        manager.collectCustomerPreferences(new Scanner(""), customer);

        System.setOut(originalOut);
        String output = out.toString();
        assertTrue(output.contains("Nuts"));
        assertTrue(output.contains("Vegan"));
    }
}
 