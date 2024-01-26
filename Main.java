public class Main {
    public static void main(String[] args) {
        TaskManagement taskManagement = new TaskManagement();
        int choice;

        do {
            System.out.println("Task Manager");
            System.out.println("-----------------------------------");
            System.out.println("1. Add task");
            System.out.println("2. Display tasks");
            System.out.println("3. Display tasks by due date");
            System.out.println("4. Search tasks by priority");
            System.out.println("5. Edit task");
            System.out.println("6. Mark task as completed");
            System.out.println("0. Exit");
            System.out.println("-----------------------------------");

            System.out.print("Choose an option: ");
            try {
                choice = Integer.parseInt(taskManagement.getScanner().nextLine());
                switch (choice) {
                    case 1:
                        taskManagement.addTask();
                        break;
                    case 2:
                        taskManagement.displayTasks();
                        break;
                    case 3:
                        taskManagement.displayTasksByDueDate();
                        break;
                    case 4:
                        taskManagement.searchTasksByPriority();
                        break;
                    case 5:
                        taskManagement.editTask();
                        break;
                    case 6:
                        taskManagement.markTaskAsCompleted();
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter only whole numbers.");
                choice = -1;
            }
        } while (choice != 0);
    }
}