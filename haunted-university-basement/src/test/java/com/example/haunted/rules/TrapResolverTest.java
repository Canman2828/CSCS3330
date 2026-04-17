package com.example.haunted.rules;

import com.example.haunted.events.InteractionResult;
import com.example.haunted.model.Inventory;
import com.example.haunted.model.Player;
import com.example.haunted.model.Trap;
import com.example.haunted.model.TrapType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrapResolverTest {

    private TrapResolver resolver;
    private Player player;

    // Fresh resolver and full-health player (50 HP) before each test
    @BeforeEach
    void setUp() {
        resolver = new TrapResolver();
        player = new Player("Test Student", 50, 7, 2, new Inventory(8));
    }

    @Test
    void testArmedTrapDamagesPlayer() {
        // Trap deals 8 damage; player starts at 50 HP so should end at 42
        Trap trap = new Trap("Loose Wires Trap", TrapType.ELECTRIC, 8, true, true);

        resolver.resolveTrap(player, trap);

        assertEquals(42, player.getHealth(), "Player should take 8 damage from the trap");
    }

    @Test
    void testArmedTrapReturnsSuccess() {
        // A triggered trap should report success=true in its result
        Trap trap = new Trap("Loose Wires Trap", TrapType.ELECTRIC, 8, true, true);

        InteractionResult result = resolver.resolveTrap(player, trap);

        assertTrue(result.isSuccess(), "Triggered trap should return success=true");
    }

    @Test
    void testOneTimeTrapIsDisarmedAfterTrigger() {
        // After triggering a one-time trap it should no longer be armed
        Trap trap = new Trap("Loose Wires Trap", TrapType.ELECTRIC, 8, true, true);

        resolver.resolveTrap(player, trap);

        assertFalse(trap.isArmed(), "One-time trap should be disarmed after triggering");
    }

    @Test
    void testDisarmedTrapDoesNotDamagePlayer() {
        // armed=false means trap is inactive and should not hurt the player
        Trap trap = new Trap("Loose Wires Trap", TrapType.ELECTRIC, 8, false, true);

        resolver.resolveTrap(player, trap);

        assertEquals(50, player.getHealth(), "Disarmed trap should deal no damage");
    }

    @Test
    void testDisarmedTrapReturnsNoTrigger() {
        // Inactive trap should report success=false
        Trap trap = new Trap("Loose Wires Trap", TrapType.ELECTRIC, 8, false, true);

        InteractionResult result = resolver.resolveTrap(player, trap);

        assertFalse(result.isSuccess(), "Disarmed trap should return success=false");
    }

    @Test
    void testNullTrapReturnsNoTrigger() {
        // Passing null should be handled gracefully and return success=false
        InteractionResult result = resolver.resolveTrap(player, null);

        assertFalse(result.isSuccess(), "Null trap should return success=false");
        assertEquals(50, player.getHealth(), "Null trap should not damage the player");
    }
}
