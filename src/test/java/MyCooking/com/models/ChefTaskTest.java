package MyCooking.com.models;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChefTaskTest {

    @Test
    public void testChefTaskInitialization() {
        ChefTask task = new ChefTask("Chef Zain");
        assertEquals("Chef Zain", task.getChefName());
        assertTrue(task.getTasks().isEmpty());
    }

    @Test
    public void testAddTask() {
        ChefTask task = new ChefTask("Chef Amani");
        task.addTask("Prepare Salad");
        task.addTask("Bake Cake");

        List<String> tasks = task.getTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains("Prepare Salad"));
        assertTrue(tasks.contains("Bake Cake"));
    }
}
