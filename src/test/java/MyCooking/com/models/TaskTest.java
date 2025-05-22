package MyCooking.com.models;

import static org.junit.Assert.*;
import org.junit.Test;

public class TaskTest {

    @Test
    public void testTaskConstructorAndGetters() {
        String expectedTaskName = "Prepare Salad";
        String expectedChefId = "chef123";

        Task task = new Task(expectedTaskName, expectedChefId);

        assertEquals(expectedTaskName, task.getTaskName());
        assertEquals(expectedChefId, task.getAssignedChefId());
    }
}
