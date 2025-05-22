package MyCooking.com.models;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Map;

public class SupplierIntegrationTest {

    @Test
    public void testGetRealTimePrices_ReturnsCorrectPrices() {
        SupplierIntegration supplier = new SupplierIntegration();
        Map<String, Double> prices = supplier.getRealTimePrices();

        assertEquals(5, prices.size());
        assertEquals(Double.valueOf(1.2), prices.get("tomato"));
        assertEquals(Double.valueOf(0.8), prices.get("onion"));
        assertEquals(Double.valueOf(2.5), prices.get("cheese"));
        assertEquals(Double.valueOf(1.0), prices.get("milk"));
        assertEquals(Double.valueOf(2.0), prices.get("almond milk"));
    }
}

