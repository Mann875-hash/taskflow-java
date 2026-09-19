import java.util.List;

public interface TaskRepository {
    List<Task> findAll();
    void save(Task task);
    void update(Task task);
    void delete(String id);
}
