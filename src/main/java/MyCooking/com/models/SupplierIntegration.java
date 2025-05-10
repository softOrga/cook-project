package MyCooking.com.models;

import java.util.HashMap;
import java.util.Map;

public class SupplierIntegration {

    public Map<String, Double> getRealTimePrices() {
        Map<String, Double> prices = new HashMap<>();
        prices.put("tomato", 1.2);
        prices.put("onion", 0.8);
        prices.put("cheese", 2.5);
        prices.put("milk", 1.0);
        prices.put("almond milk", 2.0);
        return prices;
    }
}

