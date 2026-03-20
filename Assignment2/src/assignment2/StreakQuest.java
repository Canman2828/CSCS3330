package assignment2;

public class StreakQuest extends AbstractQuest {
	private int days;

	public StreakQuest(int id, String title, int basePoints, int days) {
		super(id, title, basePoints);
		if(days <= 0) {
			throw new IllegalArgumentException("days bust be atleast 1");
		}
		this.days = days;
	}

	public int getDays() {
		return days;
	}
	@Override
    public int completeFor(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("student cannot be null");
        }

        if (isCompleted()) {
            return 0;
        }

        int pointsAwarded = getBasePoints() + (days * 2);// Linear bonus: basePoints + (days * 2)

        markCompleted();

        s.addPoints(pointsAwarded);// Award points to student

        return pointsAwarded;
    }

    @Override
    public String toString() {
        return super.toString() + ", days=" + days;
    }
	
}
