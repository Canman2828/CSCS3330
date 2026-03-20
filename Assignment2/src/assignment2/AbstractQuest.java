package assignment2;

public abstract class AbstractQuest implements Quest {
	private int Id;
	private String title;
	private int basePoints;
	private boolean completed;
	public AbstractQuest(int id, String title, int basePoints) {
		if (id <= 0) {
            throw new IllegalArgumentException("id must be > 0");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or blank");
        }
        if (basePoints <= 0) {
            throw new IllegalArgumentException("basePoints must be > 0");
        }
        this.Id = id;
        this.title = title;
        this.basePoints = basePoints;
        this.completed = false;
	}
	@Override
    public int getId() {
        return Id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getBasePoints() {
        return basePoints;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    protected void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "Quest{id=" + Id + ", title='" + title + '\'' + ", basePoints=" + basePoints +
               ", completed=" + completed +'}';
    }
	
}
