package assignment2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuestBoard board = new QuestBoard();

        Student s1 = new Student("Canady");
        Student s2 = new Student("Ethan");
        Student s3 = new Student("Nehemiah");
        Student s4 = new Student("Canady"); 
        Student s5 = new Student("Ethan");

        Set<Student> studentSet = new HashSet<>();
        studentSet.add(s1);
        studentSet.add(s2);
        studentSet.add(s3);
        studentSet.add(s4);
        studentSet.add(s5);

        System.out.println("=== HashSet Student Demo ===");
        System.out.println("Students in set:");
        for (Student s : studentSet) {
            System.out.println(s);
        }
        System.out.println("Set size should show duplicate name behavior: " + studentSet.size());
        System.out.println();
        Quest q1 = new EventCheckInQuest(1, "Attend Kickoff", 10, "Welcome Event");
        Quest q2 = new EventCheckInQuest(2, "Check In at Workshop", 15, "XR Workshop");

        Quest q3 = new VolunteerQuest(3, "Volunteer at Booth", 5, 3);
        Quest q4 = new VolunteerQuest(4, "Help with Setup", 4, 2);

        Quest q5 = new StreakQuest(5, "Daily Study Streak", 8, 5);
        Quest q6 = new StreakQuest(6, "Coding Practice Streak", 6, 4);

        // Adding quests to board
        board.addQuest(q1);
        board.addQuest(q2);
        board.addQuest(q3);
        board.addQuest(q4);
        board.addQuest(q5);
        board.addQuest(q6);

        System.out.println("=== All Quests ===");
        board.printAllQuests();
        System.out.println();
        board.assignQuest(s1, 1);
        board.assignQuest(s1, 3);
        board.assignQuest(s1, 5);

        board.assignQuest(s2, 2);
        board.assignQuest(s2, 4);
        board.assignQuest(s3, 6);

        System.out.println("=== Assignments ===");
        board.printAssignmentsFor(s1);
        board.printAssignmentsFor(s2);
        board.printAssignmentsFor(s3);
        System.out.println();
        System.out.println("=== Completing Quests ===");
        System.out.println(s1.getName() + " earned " + board.completeQuest(s1, 1) + " points");
        System.out.println(s1.getName() + " earned " + board.completeQuest(s1, 3) + " points");
        System.out.println(s2.getName() + " earned " + board.completeQuest(s2, 2) + " points");
        System.out.println(s3.getName() + " earned " + board.completeQuest(s3, 6) + " points");
        System.out.println();
        System.out.println("=== Final Student Totals ===");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        RewardUtil.printAll(students);
        System.out.println();
        System.out.println("=== Total Points Across Students ===");
        int totalPoints = RewardUtil.sumPoints(students);
        System.out.println("Total points = " + totalPoints);
        System.out.println();
        System.out.println("=== Fail Fast Demo ===");
        try {
            Quest duplicateQuest = new EventCheckInQuest(1, "Duplicate Quest", 20, "Bad Event");
            board.addQuest(duplicateQuest);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

	}

}
