package assignment2;
import java.util.List;
public class RewardUtil {
    public static <T> void printAll(List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("items cannot be null");
        }

        for (T item : items) {
            System.out.println(item); // generic method printing items and summing student points
        }
    }

    public static int sumPoints(List<? extends Student> students) {
        if (students == null) {
            throw new IllegalArgumentException("students cannot be null");
        }

        int total = 0;
        for (Student s : students) {
            total += s.getPoints();
        }

        return total;
    }
}
