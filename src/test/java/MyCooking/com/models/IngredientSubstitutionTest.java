package MyCooking.com.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientSubstitutionTest {

    private IngredientSubstitution substitution;

    @Before
    public void setup() {
        substitution = new IngredientSubstitution();
    }

    @Test
    public void testSuggestSubstitutionExisting() {
        String substitute = substitution.suggestSubstitution("milk");
        assertEquals("almond milk", substitute);
    }

    @Test
    public void testSuggestSubstitutionNonExisting() {
        String substitute = substitution.suggestSubstitution("unknown");
        assertEquals("No substitution available", substitute);
    }

    @Test
    public void testAddAndSuggestSubstitution() {
        substitution.addSubstitution("sugar", "honey");
        String substitute = substitution.suggestSubstitution("sugar");
        assertEquals("honey", substitute);
    }
}
