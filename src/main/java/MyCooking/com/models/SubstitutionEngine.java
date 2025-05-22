package MyCooking.com.models;

import java.util.HashMap;
import java.util.Map;

public class SubstitutionEngine {
    private IngredientValidator validator;
    private Map<String, String> substitutionMap;

    public SubstitutionEngine() {
        validator = new IngredientValidator();
        substitutionMap = new HashMap<>();
        initializeSubstitutions();
    }

    private void initializeSubstitutions() {
        substitutionMap.put("milk", "almond milk");
        substitutionMap.put("cheese", "vegan cheese");
        substitutionMap.put("egg", "chia seeds");
        substitutionMap.put("butter", "sunflower seeds");
    }

    public String suggestSubstitution(String ingredientName) {
        String lowerName = ingredientName.toLowerCase();

        if (substitutionMap.containsKey(lowerName)) {
            return substitutionMap.get(lowerName);
        }

        return ingredientName;
    }
}
