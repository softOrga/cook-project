package MyCooking.com.models;

import java.util.*;

public class SupplierService {
    private final Map<String, List<Supplier>> supplierData = new HashMap<>();
    public List<Supplier> getSuppliers(String ingredient) {
        return supplierData.getOrDefault(ingredient, Collections.emptyList());
    }
    public SupplierService() {
        supplierData.put("tomato", Arrays.asList(
            new Supplier("FreshFarms", 0.5, 4),
            new Supplier("OrganicCo", 0.6, 2)
        ));
        supplierData.put("onion", Arrays.asList(
            new Supplier("LocalGrowers", 0.3, 3),
            new Supplier("FarmX", 0.35, 5)
        ));
        supplierData.put("cheese", Arrays.asList(
            new Supplier("DairyKing", 1.2, 6),
            new Supplier("MilkMakers", 1.1, 4)
        ));
        supplierData.put("chicken", Arrays.asList(
            new Supplier("PoultryPros", 2.5, 5),
            new Supplier("FarmMeat", 2.4, 3)
        ));
        supplierData.put("tofu", Arrays.asList(
            new Supplier("VegieCorp", 1.5, 2),
            new Supplier("SoyWorld", 1.4, 4)
        ));
        supplierData.put("milk", Arrays.asList(
            new Supplier("DairyKing", 1.0, 3),
            new Supplier("MilkMakers", 0.7, 2)
        ));
        supplierData.put("almond milk", Arrays.asList(
                new Supplier("DairyKing", 1.5, 3),
                new Supplier("MilkMakers", 0.9, 2)
            ));
    }

    public double getRealTimePrice(String ingredient) {
        List<Supplier> list = supplierData.get(ingredient);
        if (list != null && !list.isEmpty()) {
            return list.get(0).getPrice();
        }
        return 1.0;
    }

    public Supplier getBestSupplier(String ingredient) {
        List<Supplier> list = supplierData.get(ingredient);
        if (list == null || list.isEmpty()) return new Supplier("Unknown", 0.0, 0);
        return list.stream().min(Comparator.comparingDouble(Supplier::getPrice)).orElse(list.get(0));
    }
}
