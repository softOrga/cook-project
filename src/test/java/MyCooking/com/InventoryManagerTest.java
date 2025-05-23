package MyCooking.com;

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
    public void testCheckStockLowAndSufficient() {
        assertTrue(inventory.checkStock("cheese")); 
        assertFalse(inventory.checkStock("tomato")); 
        assertTrue(inventory.checkStock("nonexistent"));
    }


    @Test
    public void testCheckLowStockAndSuggestRestock() {
        List<String> lowStock = inventory.checkLowStockAndSuggestRestock();
        assertTrue(lowStock.contains("cheese"));
    }

    @Test
    public void testSuggestRestocking() {
        List<String> suggested = inventory.suggestRestocking();
        assertTrue(suggested.contains("cheese"));
    }

    @Test
    public void testOpenPurchasingInterface() {
        inventory.openPurchasingInterface();
       
    }

    @Test
    public void testFetchSupplierPrices() {
        Map<String, Double> prices = inventory.fetchSupplierPrices();
        assertNotNull(prices.get("tomato"));
    }

    @Test
    public void testDetectCriticalStock() {
        List<String> critical = inventory.detectCriticalStock();
        assertTrue(critical.contains("cheese"));
    }

    @Test
    public void testGetLastCriticalItemsAfterDetect() {
        inventory.detectCriticalStock();
        List<String> critical = inventory.getLastCriticalItems();
        assertNotNull(critical);
        assertTrue(critical.contains("cheese"));
    }

    @Test
    public void testGeneratePurchaseOrder() {
        List<String> orders = inventory.generatePurchaseOrder();
        assertTrue(orders.size() > 0);
    }

    @Test
    public void testUseIngredientSuccessAndFail() {
        String ingredient = "milk"; 
        assertTrue(inventory.useIngredient(ingredient));
        assertTrue(inventory.useIngredient(ingredient));
        assertFalse(inventory.useIngredient(ingredient));  

        assertFalse(inventory.useIngredient("nonexistent")); 
    }

    @Test
    public void testEmptyStockCases() {
         
        inventory.stock.clear();

        List<String> lowStock = inventory.checkLowStockAndSuggestRestock();
        assertTrue(lowStock.isEmpty());

        List<String> suggested = inventory.suggestRestocking();
        assertTrue(suggested.isEmpty());

        List<String> critical = inventory.detectCriticalStock();
        assertTrue(critical.isEmpty());

        List<String> orders = inventory.generatePurchaseOrder();
        assertTrue(orders.isEmpty());
    }
}



/*package MyCooking.com;

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
       
        assertTrue(inventory.checkStock("cheese"));
    }

    @Test
    public void testCheckStockSufficient() {
       
        assertFalse(inventory.checkStock("tomato"));
    }

    @Test
    public void testCheckStockNonExistentIngredient() {
        
        assertTrue(inventory.checkStock("nonexistent"));
    }

    @Test
    public void testCheckLowStockAndSuggestRestock() {
        List<String> lowStock = inventory.checkLowStockAndSuggestRestock();
        assertTrue(lowStock.contains("cheese"));
        assertTrue(lowStock.contains("milk"));
        assertFalse(lowStock.contains("tomato"));
    }

    @Test
    public void testSuggestRestocking() {
        List<String> suggested = inventory.suggestRestocking();
        assertTrue(suggested.contains("cheese"));
        assertTrue(suggested.contains("milk"));
        assertFalse(suggested.contains("tomato"));
    }

    @Test
    public void testOpenPurchasingInterface() {
        
        inventory.openPurchasingInterface();
    }

    @Test
    public void testFetchSupplierPrices() {
        Map<String, Double> prices = inventory.fetchSupplierPrices();
        assertNotNull(prices);
       
        assertTrue(prices.containsKey("tomato"));
        assertTrue(prices.containsKey("cheese"));
        assertTrue(prices.get("tomato") >= 0);
    }

    @Test
    public void testDetectCriticalStock() {
        List<String> critical = inventory.detectCriticalStock();
        assertTrue(critical.contains("cheese")); 
        assertTrue(critical.contains("milk"));    
        assertFalse(critical.contains("tomato")); 
    }

    @Test
    public void testGetLastCriticalItemsAfterDetect() {
        inventory.detectCriticalStock();
        List<String> critical = inventory.getLastCriticalItems();
        assertNotNull(critical);
        assertTrue(critical.contains("cheese"));
        assertTrue(critical.contains("milk"));
    }

    @Test
    public void testGeneratePurchaseOrder() {
        List<String> orders = inventory.generatePurchaseOrder();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        boolean containsCheeseOrder = false;
        for (String order : orders) {
            if (order.contains("cheese")) {
                containsCheeseOrder = true;
                break;
            }
        }
        assertTrue(containsCheeseOrder);
    }

    @Test
    public void testGetPurchaseOrdersAfterGenerate() {
        inventory.generatePurchaseOrder();
        List<String> orders = inventory.getPurchaseOrders();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    public void testUseIngredientAvailable() {
       
        assertTrue(inventory.useIngredient("onion"));
        // Quantity should decrease by 1
        assertEquals(3, inventory.stock.get("onion").intValue());
    }

    @Test
    public void testUseIngredientUnavailable() {
      
        assertTrue(inventory.useIngredient("cheese"));
        assertTrue(inventory.useIngredient("cheese"));
        
        assertFalse(inventory.useIngredient("cheese"));
    }

    @Test
    public void testUseIngredientNotFound() {
        assertFalse(inventory.useIngredient("nonexistent"));
    }

    @Test
    public void testViewStock() {
      
        inventory.viewStock();
    }
    
    @Test
    public void testUseIngredientUntilEmptyAndBeyond() {
        String ingredient = "milk"; // initial 2 units

        // Use twice, should succeed
        assertTrue(inventory.useIngredient(ingredient));
        assertTrue(inventory.useIngredient(ingredient));
        // Now zero units left, next use should fail
        assertFalse(inventory.useIngredient(ingredient));
    }

    @Test
    public void testDetectCriticalStockWhenEmpty() {
        // Empty all stock
        inventory.stock.clear();
        List<String> critical = inventory.detectCriticalStock();
        assertTrue(critical.isEmpty());
    }

    @Test
    public void testCheckLowStockAndSuggestRestockWhenEmpty() {
        inventory.stock.clear();
        List<String> lowStock = inventory.checkLowStockAndSuggestRestock();
        assertTrue(lowStock.isEmpty());
    }

    @Test
    public void testSuggestRestockingWhenEmpty() {
        inventory.stock.clear();
        List<String> suggested = inventory.suggestRestocking();
        assertTrue(suggested.isEmpty());
    }

    @Test
    public void testGeneratePurchaseOrderWhenEmpty() {
        inventory.stock.clear();
        List<String> orders = inventory.generatePurchaseOrder();
        assertTrue(orders.isEmpty());
    }

}
*/
