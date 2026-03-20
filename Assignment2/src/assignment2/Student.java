package assignment2;
import java.util.Objects;


public class Student {
	private String name;
    private int points;
    public Student(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be blank");
        }

        this.name = name;
        this.points = 0;
    }
    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }
    public void addPoints(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be more than 0");
        }

        points += amount;
    }
    @Override
    public String toString() {
        return "Student{name='" + name + "', points=" + points + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Student)) {
            return false;
        }

        Student other = (Student) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
