package MyCooking.com.models;
import java.util.ArrayList;
import java.util.List;

public class ChefTask {
    private String chefName;
    private List<String> tasks = new ArrayList<>();

    public ChefTask(String chefName) {
        this.chefName = chefName;
    }

    public String getChefName() {
        return chefName;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void addTask(String task) {
        tasks.add(task);
    }
}
