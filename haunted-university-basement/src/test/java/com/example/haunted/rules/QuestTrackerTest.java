package com.example.haunted.rules;

import com.example.haunted.model.Monster;
import com.example.haunted.model.Quest;
import com.example.haunted.model.QuestItem;
import com.example.haunted.model.QuestStatus;
import com.example.haunted.model.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class QuestTrackerTest {

    private QuestTracker tracker;
    private Quest quest;

    // Fresh tracker and quest before each test
    @BeforeEach
    void setUp() {
        tracker = new QuestTracker();
        quest = new Quest("Escape the Basement",
                "Recover the Lost Gradebook and defeat the Final Exam Phantom.");
    }

    @Test
    void testGradebookRecoveredFlagSet() {
        // Picking up the Lost Gradebook should mark gradebookRecovered on the quest
        QuestItem gradebook = new QuestItem("Lost Gradebook", "The legendary missing gradebook.");

        tracker.updateQuestForItem(quest, gradebook);

        assertTrue(quest.isGradebookRecovered(), "Gradebook recovered flag should be true");
    }

    @Test
    void testNonQuestItemDoesNotSetGradebookFlag() {
        // A regular weapon should have no effect on the quest
        Weapon sword = new Weapon("Stapler of Justice", "A heavy-duty stapler.", 4);

        tracker.updateQuestForItem(quest, sword);

        assertFalse(quest.isGradebookRecovered(),
                "Non-quest item should not set gradebook recovered flag");
    }

    @Test
    void testNullItemDoesNotThrow() {
        // Passing null should not crash — quest state should remain unchanged
        assertDoesNotThrow(() -> tracker.updateQuestForItem(quest, null));
        assertFalse(quest.isGradebookRecovered(), "Quest should be unchanged after null item");
    }

    @Test
    void testPhantomDefeatedFlagSet() {
        // Killing the Final Exam Phantom should mark phantomDefeated on the quest
        Monster phantom = new Monster("Final Exam Phantom", 40, 10, 4, List.of());
        phantom.takeDamage(40); // reduce health to 0

        tracker.updateQuestForMonster(quest, phantom);

        assertTrue(quest.isPhantomDefeated(), "Phantom defeated flag should be true");
    }

    @Test
    void testLivingPhantomDoesNotSetDefeatedFlag() {
        // Phantom must actually be dead before the flag is set
        Monster phantom = new Monster("Final Exam Phantom", 40, 10, 4, List.of());

        tracker.updateQuestForMonster(quest, phantom); // still alive

        assertFalse(quest.isPhantomDefeated(),
                "Living phantom should not set phantom defeated flag");
    }

    @Test
    void testQuestCompletedWhenBothConditionsMet() {
        // Quest should reach COMPLETED only when both objectives are done
        QuestItem gradebook = new QuestItem("Lost Gradebook", "The legendary missing gradebook.");
        Monster phantom = new Monster("Final Exam Phantom", 40, 10, 4, List.of());
        phantom.takeDamage(40);

        tracker.updateQuestForItem(quest, gradebook);
        tracker.updateQuestForMonster(quest, phantom);

        assertEquals(QuestStatus.COMPLETED, quest.getStatus(),
                "Quest should be COMPLETED when both objectives are met");
        assertTrue(quest.isComplete(), "isComplete() should return true");
    }

    @Test
    void testQuestInProgressWithOnlyGradebook() {
        QuestItem gradebook = new QuestItem("Lost Gradebook", "The legendary missing gradebook.");

        tracker.updateQuestForItem(quest, gradebook);

        assertEquals(QuestStatus.IN_PROGRESS, quest.getStatus(),
                "Quest should be IN_PROGRESS with only one objective complete");
        assertFalse(quest.isComplete(), "Quest should not be complete yet");
    }

    @Test
    void testQuestNotStartedInitially() {
        // Quest starts in NOT_STARTED state before any objectives are completed
        assertEquals(QuestStatus.NOT_STARTED, quest.getStatus(),
                "Quest should be NOT_STARTED before any objectives");
    }
}
