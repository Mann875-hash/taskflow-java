import java.time.LocalDate;
import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int getInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    public static LocalDate getDate(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid date. Use YYYY-MM-DD.");
            }
        }
    }

    public static Priority getPriority(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Priority.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (Exception e) {
                System.out.println("Invalid priority. Use HIGH, MEDIUM, or LOW.");
            }
        }
    }

    public static Status getStatus(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Status.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (Exception e) {
                System.out.println("Invalid status. Use PENDING or COMPLETED.");
            }
        }
    }
}
