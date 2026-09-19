public class TaskServiceTest {
    public static void main(String[] args) {
        TaskRepository repo = new MemoryRepository();
        TaskService service = new TaskService(repo);

        System.out.println("Running tests...");

        service.addTask("Test Task 1", java.time.LocalDate.now().plusDays(2), Priority.HIGH, "Desc 1");
        service.addTask("Test Task 2", java.time.LocalDate.now().minusDays(1), Priority.LOW, "Desc 2");

        assert service.getAllTasks().size() == 2 : "TC-01 Failed";
        System.out.println("TC-01: Add valid task ......... PASSED");

        assert service.filterByPriority(Priority.HIGH).size() == 1 : "TC-04 Failed";
        System.out.println("TC-04: Filter HIGH ............ PASSED");

        service.markComplete("T-001");
        assert service.filterByStatus(Status.COMPLETED).size() == 1 : "TC-05 Failed";
        System.out.println("TC-05: Mark complete .......... PASSED");

        service.deleteTask("T-002");
        assert service.getAllTasks().size() == 1 : "TC-06 Failed";
        System.out.println("TC-06: Delete valid ID ........ PASSED");

        System.out.println("All tests passed.");
    }
}
