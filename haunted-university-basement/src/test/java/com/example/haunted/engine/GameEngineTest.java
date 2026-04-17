package com.example.haunted.engine;

import com.example.haunted.config.DungeonFactory;
import com.example.haunted.events.CombatResult;
import com.example.haunted.events.InteractionResult;
import com.example.haunted.events.MoveResult;
import com.example.haunted.model.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {

    private GameEngine engine;

    // Build a fresh dungeon before every test so each test starts from a clean state
    @BeforeEach
    void setUp() {
        engine = DungeonFactory.createGame();
    }

    @Test // Movement
    void testMoveToValidDirection() {
        // Player starts in Stairwell; EAST connects to Lecture Hall
        MoveResult result = engine.move(Direction.EAST);

        assertTrue(result.isSuccess(), "Move to a connected room should succeed");
        assertEquals("lectureHall", engine.getCurrentRoom().getId(),
                "Player should now be in Lecture Hall");
    }

    @Test
    void testMoveToInvalidDirection() {
        // NORTH from Stairwell has no connection
        MoveResult result = engine.move(Direction.NORTH);

        assertFalse(result.isSuccess(), "Move with no connection should fail");
        assertEquals("stairwell", engine.getCurrentRoom().getId(),
                "Player should still be in Stairwell");
    }

    @Test
    void testMoveIntoLockedRoomWithoutKey() {
        // Exam Archive is locked player does not have the Archive Key yet
        engine.move(Direction.EAST); // stairwell to lectureHall
        MoveResult result = engine.move(Direction.NORTH); // lectureHall examArchive (locked)

        assertFalse(result.isSuccess(), "Moving into a locked room without key should fail");
        assertEquals("lectureHall", engine.getCurrentRoom().getId(),
                "Player should remain in Lecture Hall");
    }

    @Test
    void testMoveIntoTrappedRoomTriggersTrap() {
        // Broken Elevator has an armed electric trap
        engine.move(Direction.EAST);  // stairwell lectureHall
        int healthBefore = engine.getPlayer().getHealth();
        MoveResult result = engine.move(Direction.SOUTH); // lectureHall brokenElevator

        assertTrue(result.isSuccess(), "Move into trapped room should still succeed");
        assertTrue(result.isTrapTriggered(), "Trap should have been triggered");
        assertTrue(result.getTrapDamage() > 0, "Trap should deal damage greater than zero");
        assertTrue(engine.getPlayer().getHealth() < healthBefore,
                "Player health should decrease after trap");
    }

    @Test // Inventory & Items
    void testPickUpItemThatExists() {
        // Coffee Potion is in Lecture Hall
        engine.move(Direction.EAST);
        InteractionResult result = engine.pickUpItem("Coffee Potion");

        assertTrue(result.isSuccess(), "Picking up an existing item should succeed");
    }

    @Test
    void testPickUpItemThatDoesNotExist() {
        // No such item in Stairwell
        InteractionResult result = engine.pickUpItem("Nonexistent Item");

        assertFalse(result.isSuccess(), "Picking up a nonexistent item should fail");
    }

    @Test
    void testEquipWeapon() {
        // Stapler of Justice is in Server Closet
        engine.move(Direction.EAST);  // stairwell lectureHall
        engine.move(Direction.EAST);  // lectureHall labStorage
        engine.move(Direction.NORTH); // labStorage serverCloset
        engine.pickUpItem("Stapler of Justice");

        InteractionResult result = engine.equipItem("Stapler of Justice");

        assertTrue(result.isSuccess(), "Equipping a weapon in inventory should succeed");
        assertNotNull(engine.getPlayer().getEquippedWeapon(),
                "Player should have a weapon equipped");
        assertEquals("Stapler of Justice", engine.getPlayer().getEquippedWeapon().getName());
    }

    @Test
    void testUsePotionRestoresHealth() {
        // Take damage in combat, then heal with Coffee Potion
        engine.move(Direction.EAST); // stairwell lectureHall (Sleep-Deprived TA is here)
        engine.pickUpItem("Coffee Potion");
        engine.attack("Sleep-Deprived TA"); // take counter-attack damage

        int healthAfterCombat = engine.getPlayer().getHealth();
        InteractionResult result = engine.useItem("Coffee Potion");

        assertTrue(result.isSuccess(), "Using a potion should succeed");
        assertTrue(engine.getPlayer().getHealth() > healthAfterCombat,
                "Player health should increase after using a potion");
    }

    @Test // Room Unlocking
    void testUnlockRoomWithCorrectKey() {
        // Get Archive Key from Lab Storage then unlock Exam Archive from Lecture Hall
        engine.move(Direction.EAST);  // stairwell lectureHall
        engine.move(Direction.EAST);  // lectureHall labStorage
        engine.pickUpItem("Archive Key");
        engine.move(Direction.WEST);  // labStorage lectureHall

        InteractionResult result = engine.unlockRoom(Direction.NORTH);

        assertTrue(result.isSuccess(), "Unlocking a room with the correct key should succeed");
    }

    @Test
    void testUnlockRoomWithoutKey() {
        // Try to unlock Exam Archive without picking up the key
        engine.move(Direction.EAST); // stairwell lectureHall

        InteractionResult result = engine.unlockRoom(Direction.NORTH);

        assertFalse(result.isSuccess(), "Unlocking a room without the key should fail");
    }

    @Test // Combat
    void testAttackMonsterThatExists() {
        // Sleep-Deprived TA is in Lecture Hall
        engine.move(Direction.EAST);
        CombatResult result = engine.attack("Sleep-Deprived TA");

        assertTrue(result.isSuccess(), "Attacking an existing monster should succeed");
        assertTrue(result.getDamageToMonster() > 0, "Attack should deal damage to monster");
    }

    @Test
    void testAttackMonsterThatDoesNotExist() {
        // No monster in Stairwell
        CombatResult result = engine.attack("Imaginary Monster");

        assertFalse(result.isSuccess(), "Attacking a nonexistent monster should fail");
    }

    @Test
    void testPlayerTakesCounterAttackDamage() {
        // Monster should hit back when attacked
        engine.move(Direction.EAST); // stairwell lectureHall
        int healthBefore = engine.getPlayer().getHealth();
        engine.attack("Sleep-Deprived TA");

        assertTrue(engine.getPlayer().getHealth() < healthBefore,
                "Player should take counter-attack damage");
    }

    @Test // Win / Loss Conditions
    void testGameNotWonAtStart() {
        // Neither objective is complete at the start
        assertFalse(engine.isGameWon(), "Game should not be won at the start");
    }

    @Test
    void testGameNotOverAtStart() {
        // Player starts with full health
        assertFalse(engine.isGameOver(), "Game should not be over at the start");
    }
}
