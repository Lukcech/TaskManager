import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskManagement {
    private ArrayList<Task> tasks;
    private Scanner scanner;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public TaskManagement() {
        this.tasks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addTask() {
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();
        System.out.print("Enter task priority: ");
        int taskPriority = Integer.parseInt(scanner.nextLine());

        Task task = new Task(taskName, taskPriority);

        System.out.print("Enter due date (yyyy-MM-dd) or leave blank: ");
        String dueDateString = scanner.nextLine().trim();
        if (!dueDateString.isEmpty()) {
            try {
                Date dueDate = DATE_FORMAT.parse(dueDateString);
                task.setDueDate(dueDate);
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format. Task added without due date.");
            }
        }

        tasks.add(task);
        System.out.println("Task added: " + taskName);
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Task list is empty.");
        } else {
            System.out.println("Task list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String dueDateStr = (task.getDueDate() != null) ? DATE_FORMAT.format(task.getDueDate()) : "No Due Date";
                System.out.println((i + 1) + ". " + task.getName() +
                        " (Priority: " + task.getPriority() +
                        ", Due Date: " + dueDateStr +
                        ", Completed: " + task.isCompleted() + ")");
            }
        }
    }

    public void displayTasksByDueDate() {
        if (tasks.isEmpty()) {
            System.out.println("Task list is empty.");
        } else {
            Collections.sort(tasks, Comparator.comparing(Task::getDueDate, Comparator.nullsLast(Comparator.naturalOrder())));

            System.out.println("Task list sorted by due date:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String dueDateStr = (task.getDueDate() != null) ? DATE_FORMAT.format(task.getDueDate()) : "No Due Date";
                System.out.println((i + 1) + ". " + task.getName() +
                        " (Priority: " + task.getPriority() +
                        ", Due Date: " + dueDateStr +
                        ", Completed: " + task.isCompleted() + ")");
            }
        }
    }

    public void searchTasksByPriority() {
        System.out.print("Enter priority to search (1-3): ");
        try {
            int searchPriority = Integer.parseInt(scanner.nextLine());

            if (searchPriority >= 1 && searchPriority <= 3) {
                List<Task> matchingTasks = new ArrayList<>();

                for (Task task : tasks) {
                    if (task.getPriority() == searchPriority) {
                        matchingTasks.add(task);
                    }
                }

                if (!matchingTasks.isEmpty()) {
                    System.out.println("Tasks with priority " + searchPriority + ":");
                    for (int i = 0; i < matchingTasks.size(); i++) {
                        Task task = matchingTasks.get(i);
                        System.out.println((i + 1) + ". " + task.getName() +
                                " (Priority: " + task.getPriority() +
                                ", Completed: " + task.isCompleted() + ")");
                    }
                } else {
                    System.out.println("No tasks found with priority " + searchPriority + ".");
                }
            } else {
                System.out.println("Error: Invalid priority entered. Please enter a value between 1 and 3.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter only whole numbers.");
        }
    }

    public void markTaskAsCompleted() {
        System.out.print("Enter task number to mark as completed: ");
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());

            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                Task task = tasks.get(taskNumber - 1);
                task.setCompleted(true);
                System.out.println("Task marked as completed: " + task.getName());
            } else {
                System.out.println("Error: Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter only whole numbers.");
        }
    }

    public void editTask() {
        System.out.print("Enter task number to edit: ");
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());

            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                Task task = tasks.get(taskNumber - 1);

                System.out.print("Enter new task name (or leave blank to keep current name): ");
                String newName = scanner.nextLine().trim();
                if (!newName.isEmpty()) {
                    task.setName(newName);
                    System.out.println("Task name updated.");
                }

                System.out.print("Enter new task priority (or leave blank to keep current priority): ");
                String newPriorityStr = scanner.nextLine().trim();
                if (!newPriorityStr.isEmpty()) {
                    try {
                        int newPriority = Integer.parseInt(newPriorityStr);
                        task.setPriority(newPriority);
                        System.out.println("Task priority updated.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter only whole numbers for priority.");
                    }
                }

                System.out.print("Enter new due date (yyyy-MM-dd) or leave blank to keep current due date: ");
                String newDueDateString = scanner.nextLine().trim();
                if (!newDueDateString.isEmpty()) {
                    try {
                        Date newDueDate = DATE_FORMAT.parse(newDueDateString);
                        task.setDueDate(newDueDate);
                        System.out.println("Due date updated.");
                    } catch (ParseException e) {
                        System.out.println("Error: Invalid date format. Task updated without changing due date.");
                    }
                }

                System.out.println("Task updated: " + task.getName());
            } else {
                System.out.println("Error: Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter only whole numbers.");
        }
    }

    // Other existing methods...

    public Scanner getScanner() {
        return scanner;
    }
}