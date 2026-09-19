import java.time.LocalDate;

public class Task {
    private String id;
    private String title;
    private LocalDate dueDate;
    private Priority priority;
    private Status status;
    private String description;

    public Task(String id, String title, LocalDate dueDate, Priority priority, String description) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = Status.PENDING;
        this.description = description;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + dueDate + " | " + priority + " | " + status;
    }
}
