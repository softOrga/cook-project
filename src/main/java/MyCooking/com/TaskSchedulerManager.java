package MyCooking.com;

import java.util.*;

import MyCooking.com.models.ChefTask;

public class TaskSchedulerManager {
    private Map<String, ChefTask> chefTasks = new HashMap<>();

    public void assignTaskToChef(String chefName, String task) {
        chefTasks.putIfAbsent(chefName, new ChefTask(chefName));
        chefTasks.get(chefName).addTask(task);
        System.out.println("Task assigned to " + chefName + ": " + task);
    }

    public void chefLogin(String chefName) {
        chefTasks.putIfAbsent(chefName, new ChefTask(chefName));
        System.out.println("Welcome, " + chefName + "! Here are your tasks for today:");
        viewChefTasks(chefName);
    }

    public void viewChefTasks(String chefName) {
        ChefTask chef = chefTasks.get(chefName);
        if (chef == null || chef.getTasks().isEmpty()) {
            System.out.println("No tasks assigned yet.");
        } else {
            for (String task : chef.getTasks()) {
                System.out.println("- " + task);
            }
        }
    }
}
