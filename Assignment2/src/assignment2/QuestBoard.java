package assignment2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestBoard {
	private Map<Integer, Quest> questsById;
    private Map<Student, List<Quest>> assignments;
    public QuestBoard() {
        questsById = new HashMap<>();
        assignments = new HashMap<>();
    }

    public void addQuest(Quest q) {
        if (q == null) {
            throw new IllegalArgumentException("quest cannot be null");
        }

        if (questsById.containsKey(q.getId())) {
            throw new IllegalArgumentException("duplicate quest id: " + q.getId());
        }

        questsById.put(q.getId(), q);
    }

    public Quest findQuest(int id) {
        return questsById.get(id);
    }

    public void assignQuest(Student s, int questId) {
        if (s == null) {
            throw new IllegalArgumentException("student cannot be null");
        }

        Quest q = findQuest(questId);
        if (q == null) {
            throw new IllegalArgumentException("quest id not found: " + questId);
        }

        assignments.putIfAbsent(s, new ArrayList<>());
        assignments.get(s).add(q);
    }

    public int completeQuest(Student s, int questId) {
        if (s == null) {
            throw new IllegalArgumentException("student cannot be null");
        }

        List<Quest> studentQuests = assignments.get(s);
        if (studentQuests == null) {
            throw new IllegalArgumentException("student has no assigned quests");
        }

        for (Quest q : studentQuests) {
            if (q.getId() == questId) {
                return q.completeFor(s);
            }
        }

        throw new IllegalArgumentException("quest not assigned to this student: " + questId);
    }

    public void printAllQuests() {
    	for (Integer id : questsById.keySet()) {
    	    System.out.println(questsById.get(id));
    	}
    }

    public void printAssignmentsFor(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("student cannot be null");
        }

        List<Quest> studentQuests = assignments.get(s);

        if (studentQuests == null || studentQuests.isEmpty()) {
            System.out.println("No assignments for " + s.getName());
            return;
        }

        System.out.println("Assignments for " + s.getName() + ":");
        for (Quest q : studentQuests) {
            System.out.println(q);
        }
    }
}
