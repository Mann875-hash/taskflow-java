public class Main {
    public static void main(String[] args) {
        TaskRepository repo = new CsvTaskRepository("data/tasks.csv");
        TaskService service = new TaskService(repo);

        while (true) {
            System.out.println("\n===== TaskFlow Menu =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Filter by Priority");
            System.out.println("4. Filter by Status");
            System.out.println("5. Update Task");
            System.out.println("6. Delete Task");
            System.out.println("7. Mark Complete");
            System.out.println("8. Report");
            System.out.println("9. Exit");
            int choice = InputHelper.getInt("Choose: ");

            switch (choice) {
                case 1:
                    String title = InputHelper.getString("Title: ");
                    var date = InputHelper.getDate("Due Date (YYYY-MM-DD): ");
                    Priority p = InputHelper.getPriority("Priority (HIGH/MEDIUM/LOW): ");
                    String desc = InputHelper.getString("Description: ");
                    service.addTask(title, date, p, desc);
                    break;
                case 2:
                    service.getAllTasks().forEach(System.out::println);
                    break;
                case 3:
                    Priority fp = InputHelper.getPriority("Priority: ");
                    service.filterByPriority(fp).forEach(System.out::println);
                    break;
                case 4:
                    Status fs = InputHelper.getStatus("Status: ");
                    service.filterByStatus(fs).forEach(System.out::println);
                    break;
                case 5:
                    String uid = InputHelper.getString("Task ID: ");
                    String ut = InputHelper.getString("New Title: ");
                    var ud = InputHelper.getDate("New Due Date: ");
                    Priority up = InputHelper.getPriority("New Priority: ");
                    String udesc = InputHelper.getString("New Description: ");
                    service.updateTask(uid, ut, ud, up, udesc);
                    break;
                case 6:
                    service.deleteTask(InputHelper.getString("Task ID: "));
                    break;
                case 7:
                    service.markComplete(InputHelper.getString("Task ID: "));
                    break;
                case 8:
                    ReportService.generateReport(service.getAllTasks());
                    break;
                case 9:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
