import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private String name;
    private boolean completed;
    private int priority;
    private Date dueDate;

    public Task(String name, int priority) {
        this.name = name;
        this.completed = false;
        this.priority = priority;
        this.dueDate = null;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: Task name cannot be null or empty.");
        }
    }
}
