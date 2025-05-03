package MyCooking.com;


import java.util.*;

public class TaskSchedulerManager {

    private Map<String, List<String>> chefTasks = new HashMap<>();
    private List<String> pendingTasks = new ArrayList<>();

    public void reviewChefWorkloads() {
        System.out.println(" Reviewing chef workloads...");
        if (chefTasks.isEmpty()) {
            System.out.println(" No chefs assigned yet.");
        } else {
            for (Map.Entry<String, List<String>> entry : chefTasks.entrySet()) {
                System.out.println(" Chef: " + entry.getKey() + " has " + entry.getValue().size() + " tasks.");
            }
        }
    }

    public void assignTaskToChef(String chefName, String task) {
        chefTasks.putIfAbsent(chefName, new ArrayList<>());
        chefTasks.get(chefName).add(task);
        System.out.println(" Task assigned to " + chefName + ": " + task);
    }

    public void chefLogin(String chefName) {
        System.out.println("Chef " + chefName + " has logged in...");
    }

    public void viewChefTasks(String chefName) {
        List<String> tasks = chefTasks.getOrDefault(chefName, new ArrayList<>());
        if (tasks.isEmpty()) {
            System.out.println(" No tasks assigned for today.");
        } else {
            System.out.println(" Tasks for " + chefName + ":");
            for (String task : tasks) {
                System.out.println("- " + task);
            }
        }
    }
}
