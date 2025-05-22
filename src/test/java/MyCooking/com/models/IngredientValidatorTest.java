package MyCooking.com.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientValidatorTest {

    private IngredientValidator validator;

    @Before
    public void setUp() {
        validator = new IngredientValidator();
    }

    @Test
    public void testIsValid_ExistingIngredient() {
        assertTrue(validator.isValid("chicken"));
    }

    @Test
    public void testIsValid_ExistingIngredientCaseInsensitive() {
        assertTrue(validator.isValid("ChIcKeN"));
    }

    @Test
    public void testIsValid_NonExistingIngredient() {
        assertFalse(validator.isValid("dragonfruit"));
    }
}
