# Haunted University Basement

A deterministic Java 17 Maven backend project designed for students to write JUnit tests.

## Goal
Recover the **Lost Gradebook** and defeat the **Final Exam Phantom**.

## Design notes
- No console-driven gameplay loop is required for grading.
- The primary testing surface is `GameEngine`.
- Combat, traps, loot, and unlocking are deterministic.
- Students should write JUnit tests for movement, combat, inventory, traps, room unlocking, and quest progression.

## Suggested student tasks
- Write unit tests for `DamageCalculator`, `TrapResolver`, and `QuestTracker`
- Write integration tests against `GameEngine`
- Test happy paths and edge cases
- Aim for high line and branch coverage

## Main API
- `move(Direction direction)`
- `pickUpItem(String itemName)`
- `equipItem(String itemName)`
- `useItem(String itemName)`
- `unlockRoom(Direction direction)`
- `attack(String monsterName)`

## Win condition
The game is won only when both are true:
1. The player has obtained the `Lost Gradebook`
2. The `Final Exam Phantom` has been defeated

---

## How to Run the Tests

### In Eclipse
1. Refresh the project: select the project and press **Fn + F5**
2. Right-click the project in **Project Explorer**
3. Select **Run As → JUnit Test**
4. The JUnit view will open and show a green/red bar with pass/fail results for each test

To run a single test class:
- Right-click the file (e.g. `GameEngineTest.java`) → **Run As → JUnit Test**

To run a single test method:
- Open the file, click inside the method, then **Run As → JUnit Test**

### From the Terminal (Maven)
```bash
cd ~/git/CSCS3330/haunted-university-basement
mvn test
```
Results appear in the terminal and are also saved to `target/surefire-reports/`.

---

## Test Classes

| File | Type | What It Tests |
|------|------|---------------|
| `GameEngineTest` | Integration | Movement, items, unlocking, combat, win/loss via `DungeonFactory` |
| `DamageCalculatorTest` | Unit | Damage formulas, minimum-1 rule, boss enrage phase |
| `TrapResolverTest` | Unit | Trap damage, one-time disarm, null/disarmed trap handling |
| `QuestTrackerTest` | Unit | Quest flags, status progression (NOT_STARTED → IN_PROGRESS → COMPLETED) |
