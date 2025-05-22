package MyCooking.com;

import MyCooking.com.models.Supplier;
import MyCooking.com.models.SupplierService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class InventoryManagerTest {

    private InventoryManager inventory;

    @Before
    public void setUp() {
        inventory = new InventoryManager();
    }

    @Test
    public void testMonitorStock() {
        inventory.monitorStock();
        assertTrue(inventory.isMonitoring());
    }

    @Test
    public void testCheckStockLow() {
        assertTrue(inventory.checkStock("cheese")); // initial = 2
    }

    @Test
    public void testCheckStockSufficient() {
        assertFalse(inventory.checkStock("tomato")); // initial = 5
    }

    @Test
    public void testDetectCriticalStock() {
        List<String> critical = inventory.detectCriticalStock();
        assertTrue(critical.contains("cheese")); // cheese = 2
    }

    @Test
    public void testUseIngredientAvailable() {
        assertTrue(inventory.useIngredient("onion")); // initial = 4
    }

    @Test
    public void testUseIngredientUnavailable() {
        // Use all cheese (2 units)
        inventory.useIngredient("cheese");
        inventory.useIngredient("cheese");
        assertFalse(inventory.useIngredient("cheese")); // now 0
    }

    @Test
    public void testGeneratePurchaseOrder() {
        List<String> orders = inventory.generatePurchaseOrder();
        assertTrue(orders.size() > 0); // there should be low stock items
    }

    @Test
    public void testFetchSupplierPrices() {
        Map<String, Double> prices = inventory.fetchSupplierPrices();
        assertNotNull(prices.get("tomato"));
    }

    @Test
    public void testSuggestRestocking() {
        List<String> suggested = inventory.suggestRestocking();
        assertTrue(suggested.contains("cheese"));
    }

    @Test
    public void testGetLastCriticalItemsAfterDetect() {
        inventory.detectCriticalStock();
        List<String> critical = inventory.getLastCriticalItems();
        assertNotNull(critical);
        assertTrue(critical.contains("cheese"));
    }
}
