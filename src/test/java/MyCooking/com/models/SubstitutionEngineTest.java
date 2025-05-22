package MyCooking.com.models;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import MyCooking.com.models.SubstitutionEngine;

public class SubstitutionEngineTest {

    private SubstitutionEngine engine;

    @Before
    public void setUp() {
        engine = new SubstitutionEngine();
    }

    @Test
    public void testSuggestSubstitution_IngredientHasSubstitution_ReturnsSubstitution() {
        assertEquals("almond milk", engine.suggestSubstitution("milk"));
        assertEquals("vegan cheese", engine.suggestSubstitution("cheese"));
        assertEquals("chia seeds", engine.suggestSubstitution("egg"));
        assertEquals("sunflower seeds", engine.suggestSubstitution("butter"));
    }

    @Test
    public void testSuggestSubstitution_IngredientNoSubstitution_ReturnsSame() {
        assertEquals("chicken", engine.suggestSubstitution("chicken"));
        assertEquals("beef", engine.suggestSubstitution("beef"));
    }

    @Test
    public void testSuggestSubstitution_CaseInsensitive() {
        assertEquals("almond milk", engine.suggestSubstitution("Milk"));
        assertEquals("chia seeds", engine.suggestSubstitution("EGG"));
    }
}
