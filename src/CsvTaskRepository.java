import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class CsvTaskRepository implements TaskRepository {

    private final Path filePath;

    public CsvTaskRepository(String filePath) {
        this.filePath = Paths.get(filePath);
        initFile();
    }

    private void initFile() {
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.writeString(filePath, "id,title,dueDate,priority,status,description\n");
            }
        } catch (IOException e) {
            System.out.println("Error creating CSV file: " + e.getMessage());
        }
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 6);
                if (parts.length < 6) continue;
                tasks.add(new Task(
                    parts[0].trim(),
                    parts[1].trim(),
                    LocalDate.parse(parts[2].trim()),
                    Priority.valueOf(parts[3].trim()),
                    parts[5].trim()
                ));
                Task t = tasks.get(tasks.size() - 1);
                t.setStatus(Status.valueOf(parts[4].trim()));
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return tasks;
    }

    @Override
    public void save(Task task) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                filePath, StandardOpenOption.APPEND)) {
            writer.write(toCsv(task));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }

    @Override
    public void update(Task task) {
        List<Task> tasks = findAll();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(task.getId())) {
                tasks.set(i, task);
                break;
            }
        }
        rewriteAll(tasks);
    }

    @Override
    public void delete(String id) {
        List<Task> tasks = findAll();
        tasks.removeIf(t -> t.getId().equals(id));
        rewriteAll(tasks);
    }

    private void rewriteAll(List<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write("id,title,dueDate,priority,status,description");
            writer.newLine();
            for (Task t : tasks) {
                writer.write(toCsv(t));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

    private String toCsv(Task t) {
        return t.getId() + "," + t.getTitle() + "," + t.getDueDate() + ","
                + t.getPriority() + "," + t.getStatus() + "," + t.getDescription();
    }
}
