import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(String title, java.time.LocalDate dueDate, Priority priority, String description) {
        TaskValidator.validate(title, dueDate.toString());
        String id = generateId();
        Task task = new Task(id, title, dueDate, priority, description);
        repository.save(task);
        System.out.println("Task saved. ID: " + id);
    }

    private String generateId() {
        List<Task> all = repository.findAll();
        int max = 0;
        for (Task t : all) {
            try {
                int num = Integer.parseInt(t.getId().replace("T-", ""));
                if (num > max) max = num;
            } catch (Exception ignored) {}
        }
        return String.format("T-%03d", max + 1);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public List<Task> filterByPriority(Priority p) {
        return repository.findAll().stream()
                .filter(t -> t.getPriority() == p)
                .collect(Collectors.toList());
    }

    public List<Task> filterByStatus(Status s) {
        return repository.findAll().stream()
                .filter(t -> t.getStatus() == s)
                .collect(Collectors.toList());
    }

    public void updateTask(String id, String newTitle, java.time.LocalDate newDate, Priority newPriority, String newDesc) {
        List<Task> tasks = repository.findAll();
        for (Task t : tasks) {
            if (t.getId().equals(id)) {
                TaskValidator.validate(newTitle, newDate.toString());
                t.setTitle(newTitle);
                t.setDueDate(newDate);
                t.setPriority(newPriority);
                t.setDescription(newDesc);
                repository.update(t);
                System.out.println("Task updated.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void deleteTask(String id) {
        List<Task> tasks = repository.findAll();
        boolean exists = tasks.stream().anyMatch(t -> t.getId().equals(id));
        if (exists) {
            repository.delete(id);
            System.out.println("Task deleted.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public void markComplete(String id) {
        List<Task> tasks = repository.findAll();
        for (Task t : tasks) {
            if (t.getId().equals(id)) {
                t.setStatus(Status.COMPLETED);
                repository.update(t);
                System.out.println("Task marked as complete.");
                return;
            }
        }
        System.out.println("Task not found.");
    }
}
