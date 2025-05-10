package MyCooking.com.models;

public class Task {
    private String taskName;
    private String assignedChefId;

    public Task(String taskName, String assignedChefId) {
        this.taskName = taskName;
        this.assignedChefId = assignedChefId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getAssignedChefId() {
        return assignedChefId;
    }
}

