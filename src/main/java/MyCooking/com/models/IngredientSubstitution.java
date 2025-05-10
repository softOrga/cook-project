package MyCooking.com.models;

import java.util.HashMap;
import java.util.Map;

public class IngredientSubstitution {
    private Map<String, String> substitutionMap;

    public IngredientSubstitution() {
        substitutionMap = new HashMap<>();
        substitutionMap.put("milk", "almond milk");
        substitutionMap.put("egg", "chia seeds");
        substitutionMap.put("butter", "olive oil");
        substitutionMap.put("cheese", "vegan cheese");
        substitutionMap.put("peanut", "sunflower seeds");
    }

    public String suggestSubstitution(String restrictedIngredient) {
        return substitutionMap.getOrDefault(restrictedIngredient.toLowerCase(), "No substitution available");
    }

    public void addSubstitution(String original, String substitute) {
        substitutionMap.put(original.toLowerCase(), substitute);
    }
}
