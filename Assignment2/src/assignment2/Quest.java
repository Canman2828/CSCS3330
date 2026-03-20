package assignment2;

public interface Quest {

	int getId();
	String getTitle();
	int getBasePoints();
	boolean isCompleted();
	int completeFor(Student s);
}
