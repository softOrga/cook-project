package MyCooking.com;

import java.util.*;

public class TaskSchedulerManager {

    private Map<String, List<String>> chefTasks = new HashMap<>();

    public void assignTaskToChef(String chef, String task) {
        chefTasks.computeIfAbsent(chef, k -> new ArrayList<>()).add(task);
        System.out.println("Task assigned to " + chef + ": " + task);
    }

    public void chefLogin(String chef) {
        if (chefTasks.containsKey(chef)) {
            System.out.println("Chef " + chef + " logged in.");
        } else {
            System.out.println("No tasks assigned to chef " + chef + ".");
        }
    }

    public void viewChefTasks(String chef) {
        List<String> tasks = chefTasks.getOrDefault(chef, new ArrayList<>());
        if (tasks.isEmpty()) {
            System.out.println("No tasks found for chef " + chef + ".");
        } else {
            System.out.println("Tasks for chef " + chef + ":");
            for (String task : tasks) {
                System.out.println("- " + task);
            }
        }
    }
}

