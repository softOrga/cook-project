package MyCooking.com.models;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import MyCooking.com.models.User;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User("testUser", "secret123", "admin");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("testUser", user.getUsername());
        assertEquals("secret123", user.getPassword());
        assertEquals("ADMIN", user.getRole());  // لاحظ أنه يتم تحويل role إلى uppercase
    }

    @Test
    public void testCheckPasswordTrue() {
        assertTrue(user.checkPassword("secret123"));
    }

    @Test
    public void testCheckPasswordFalse() {
        assertFalse(user.checkPassword("wrongPassword"));
    }
}
