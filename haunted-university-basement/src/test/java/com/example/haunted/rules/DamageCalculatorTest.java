package com.example.haunted.rules;

import com.example.haunted.model.BossMonster;
import com.example.haunted.model.Inventory;
import com.example.haunted.model.Monster;
import com.example.haunted.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DamageCalculatorTest {

    private DamageCalculator calculator;
    private Player player;
    private Monster monster;

    // Standard player: attack=7, defense=2
    // Standard monster: attack=6, defense=1
    @BeforeEach
    void setUp() {
        calculator = new DamageCalculator();
        player = new Player("Test Student", 50, 7, 2, new Inventory(8));
        monster = new Monster("Test Monster", 20, 6, 1, List.of());
    }

    @Test
    void testPlayerDamageNormal() {
        // player attack (7) - monster defense (1) = 6 expected damage
        int damage = calculator.calculatePlayerDamage(player, monster);

        assertEquals(6, damage, "Player damage should be attack minus monster defense");
    }

    @Test
    void testPlayerDamageMinimumIsOne() {
        // Monster with defense far exceeding player attack should still take 1 damage
        Monster tankMonster = new Monster("Tank", 20, 6, 100, List.of());

        int damage = calculator.calculatePlayerDamage(player, tankMonster);

        assertEquals(1, damage, "Damage should never fall below 1");
    }

    @Test
    void testMonsterDamageNormal() {
        // monster attack (6) - player defense (2) = 4 expected damage
        int damage = calculator.calculateMonsterDamage(monster, player);

        assertEquals(4, damage, "Monster damage should be attack minus player defense");
    }

    @Test
    void testMonsterDamageMinimumIsOne() {
        // Player with very high defense should still take at least 1 damage
        Player tankPlayer = new Player("Tank", 50, 7, 100, new Inventory(8));

        int damage = calculator.calculateMonsterDamage(monster, tankPlayer);

        assertEquals(1, damage, "Monster damage should never fall below 1");
    }

    @Test
    void testBossMonsterNormalPhaseAttack() {
        // Boss above 50% health uses base attack (10); 10 - player defense (2) = 8
        BossMonster boss = new BossMonster("Final Exam Phantom", 40, 10, 4, List.of(), 3);

        int damage = calculator.calculateMonsterDamage(boss, player);

        assertEquals(8, damage, "Boss in normal phase should use base attack");
    }

    @Test
    void testBossMonsterEnragedPhaseAttack() {
        // Boss at or below 50% health gets +3 enraged bonus; (10+3) - player defense (2) = 11
        BossMonster boss = new BossMonster("Final Exam Phantom", 40, 10, 4, List.of(), 3);
        boss.takeDamage(20); // drop to exactly 50% to trigger enrage

        int damage = calculator.calculateMonsterDamage(boss, player);

        assertEquals(11, damage, "Enraged boss should use attack + enraged bonus");
    }
}
