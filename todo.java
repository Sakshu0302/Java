import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ToDoListApp {

    // Task class
    static class Task {
        private int id;
        private String title;
        private String description;

        // Constructor
        public Task(int id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
        }

        // Getters and Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        @Override
        public String toString() {
            return "ID: " + id + ", Title: " + title + ", Description: " + description;
        }
    }

    // TaskManager class
    static class TaskManager {
        private List<Task> tasks = new ArrayList<>();
        private int nextId = 1;

        // Add a new task
        public void addTask(String title, String description) {
            tasks.add(new Task(nextId++, title, description));
        }

        // Edit an existing task
        public boolean editTask(int id, String newTitle, String newDescription) {
            Optional<Task> taskOpt = tasks.stream().filter(task -> task.getId() == id).findFirst();
            if (taskOpt.isPresent()) {
                Task task = taskOpt.get();
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                return true;
            }
            return false;
        }

        // Delete a task
        public boolean deleteTask(int id) {
            return tasks.removeIf(task -> task.getId() == id);
        }

        // List all tasks
        public void listTasks() {
            if (tasks.isEmpty()) {
                System.out.println("No tasks found.");
            } else {
                tasks.forEach(System.out::println);
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("To-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Delete Task");
            System.out.println("4. List Tasks");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(title, description);
                    break;
                case 2:
                    System.out.print("Enter task ID to edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new task title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new task description: ");
                    String newDescription = scanner.nextLine();
                    if (taskManager.editTask(editId, newTitle, newDescription)) {
                        System.out.println("Task updated.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    if (taskManager.deleteTask(deleteId)) {
                        System.out.println("Task deleted.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 4:
                    taskManager.listTasks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}

