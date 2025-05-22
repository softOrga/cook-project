package MyCooking.com.models;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SupplierServiceTest {

    private SupplierService service;

    @Before
    public void setup() {
        service = new SupplierService();
    }

    @Test
    public void testGetSuppliersKnownIngredient() {
        List<Supplier> tomatoSuppliers = service.getSuppliers("tomato");
        assertFalse(tomatoSuppliers.isEmpty());
        assertEquals("FreshFarms", tomatoSuppliers.get(0).getName());
    }

    @Test
    public void testGetSuppliersUnknownIngredient() {
        List<Supplier> unknownSuppliers = service.getSuppliers("unknownIngredient");
        assertNotNull(unknownSuppliers);
        assertTrue(unknownSuppliers.isEmpty());
    }

    @Test
    public void testGetRealTimePriceKnownIngredient() {
        double price = service.getRealTimePrice("milk");
        assertTrue(price > 0);
    }

    @Test
    public void testGetRealTimePriceUnknownIngredient() {
        double price = service.getRealTimePrice("unknownIngredient");
        assertEquals(1.0, price, 0.001);
    }

    @Test
    public void testGetBestSupplierKnownIngredient() {
        Supplier bestSupplier = service.getBestSupplier("cheese");
        assertNotNull(bestSupplier);
        assertTrue(bestSupplier.getPrice() > 0);
        assertTrue(bestSupplier.getName().equals("MilkMakers") || bestSupplier.getName().equals("DairyKing"));
    }

    @Test
    public void testGetBestSupplierUnknownIngredient() {
        Supplier bestSupplier = service.getBestSupplier("unknownIngredient");
        assertNotNull(bestSupplier);
        assertEquals("Unknown", bestSupplier.getName());
        assertEquals(0.0, bestSupplier.getPrice(), 0.001);
    }
}
