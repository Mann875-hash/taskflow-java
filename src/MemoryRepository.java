import java.util.ArrayList;
import java.util.List;

public class MemoryRepository implements TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public void save(Task task) {
        tasks.add(task);
    }

    @Override
    public void update(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(task.getId())) {
                tasks.set(i, task);
                return;
            }
        }
    }

    @Override
    public void delete(String id) {
        tasks.removeIf(t -> t.getId().equals(id));
    }
}
