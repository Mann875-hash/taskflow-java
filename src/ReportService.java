import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {
    public static void generateReport(List<Task> tasks) {
        long total = tasks.size();
        long completed = tasks.stream().filter(t -> t.getStatus() == Status.COMPLETED).count();
        long pending = total - completed;
        long overdue = tasks.stream()
                .filter(t -> t.getStatus() == Status.PENDING && t.getDueDate().isBefore(LocalDate.now()))
                .count();

        double completionRate = total == 0 ? 0 : (completed * 100.0 / total);

        System.out.println("\n--- PRODUCTIVITY REPORT ---");
        System.out.println("Total Tasks      : " + total);
        System.out.println("Completed        : " + completed);
        System.out.println("Pending          : " + pending);
        System.out.println("Overdue          : " + overdue);
        System.out.printf("Completion Rate  : %.1f%%\n", completionRate);

        Map<Priority, Long> byPriority = tasks.stream()
                .collect(Collectors.groupingBy(Task::getPriority, Collectors.counting()));
        System.out.println("HIGH: " + byPriority.getOrDefault(Priority.HIGH, 0L)
                + " | MEDIUM: " + byPriority.getOrDefault(Priority.MEDIUM, 0L)
                + " | LOW: " + byPriority.getOrDefault(Priority.LOW, 0L));
    }
}
