package assignment2;

public class VolunteerQuest extends AbstractQuest {
	private int hours;

	public VolunteerQuest(int id, String title, int basePoints, int hours) {
		super(id, title, basePoints);
		if(hours <= 0) {
			throw new IllegalArgumentException("hours cant be below or equal to 0");
		}
		this.hours = hours;
	}

	public int getHours() {
		return hours;
	}

	@Override
    public int completeFor(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("student cannot be null");
        }
        if (isCompleted()) {
            return 0;
        }
        int pointsAwarded = getBasePoints() * hours;
        markCompleted();
        s.addPoints(pointsAwarded);// Award points to student

        return pointsAwarded;
    }

    @Override
    public String toString() {
        return super.toString() + ", hours=" + hours;
    }
	
}
