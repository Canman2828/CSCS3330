package assignment2;

public class EventCheckInQuest extends AbstractQuest {
	private String eventName;

	public EventCheckInQuest(int id, String title, int basePoints, String eventName) {
		super(id, title, basePoints);
		if(eventName == null || eventName.trim().isEmpty()) {
			throw new IllegalArgumentException("The Event name cant be blank");
		}
		this.eventName = eventName;
	}
	public String getEventName() {
		return eventName;
	}
	@Override
    public int completeFor(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("student cannot be null");
        }

        
        if (isCompleted()) { // Prevent double completion
            return 0;
        }

        int pointsAwarded = getBasePoints();

        
        markCompleted(); // Mark quest as completed

   
        s.addPoints(pointsAwarded);// Award points to the student

        return pointsAwarded;
    }
	 @Override
	    public String toString() {
	        return super.toString() + ", eventName=" + eventName;
	    }
}
