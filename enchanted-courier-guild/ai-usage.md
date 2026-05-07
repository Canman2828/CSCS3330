# AI Usage Disclosure

This file documents all use of AI assistance (Claude Code via Anthropic) for this assignment.

## Tool Used
- **Claude Code** (claude-sonnet-4-6) via the Claude Code CLI / IDE extension

---

## What I Had the AI Do

### 1. Project Setup / Eclipse Configuration
- The AI read the assignment PDF and explored the provided Maven project structure.
- Confirmed that the Eclipse project files (`.classpath`, `.project`, `.settings/`) were already present and the project was ready to import.
- Installed Maven via Homebrew so that `mvn clean test` and `mvn clean verify` could be run from the terminal to verify results.

### 2. Production Source Code Implementation
- The AI read all 10 provided test files under `src/test/java` to infer the required behavior of every class and method.
- It created all 19 missing production source files under `src/main/java` across 8 packages:
  - `edu.oop.guild.model` — `PackageType`, `RealmType`, `DeliveryRequest`, `DeliveryPlan`
  - `edu.oop.guild.creature` — `Creature` (abstract class), `CloudDragon`, `TunnelMole`
  - `edu.oop.guild.strategy` — `DeliveryCostStrategy`, `StandardDeliveryStrategy`, `ExpressDeliveryStrategy`, `CarefulDeliveryStrategy`
  - `edu.oop.guild.seal` — `PackageSeal`, `SkyRibbonSeal`, `GlowStoneSeal`
  - `edu.oop.guild.factory` — `RealmFactory`, `SkyRealmFactory`, `UndergroundRealmFactory`, `RealmFactoryProvider`
  - `edu.oop.guild.log` — `GuildLog` (Singleton pattern)
  - `edu.oop.guild.notification` — `NotificationChannel`, `LegacyOwlScroll`, `OwlScrollNotificationAdapter` (Adapter pattern)
  - `edu.oop.guild.service` — `DeliveryPlanner`
- The AI derived pricing formulas for each cost strategy by working backwards from the expected values in the test cases:
  - `StandardDeliveryStrategy`: `weight + 2 * distance + 10 + (fragile ? 5 : 0)`
  - `ExpressDeliveryStrategy`: `2 * weight + 4 * distance + 25 + (fragile ? 10 : 0)`
  - `CarefulDeliveryStrategy`: `7 * weight * distance + packageType.carefulSurcharge()`

### 3. Verification
- After writing all files, the AI ran `mvn clean test` (87/87 tests passed, 0 failures) and `mvn clean verify` (all JaCoCo 100% line and instruction coverage checks met).

### 4. Git / Eclipse Workspace
- Explained that Eclipse's "Share with Team" moved the project from `~/eclipse-workspace/enchanted-courier-guild/` to `~/git/CSCS3330/enchanted-courier-guild/`, which is now the active working copy.
- Confirmed that all source files and test results transferred correctly to the new location.

### 5. Documentation
- The AI wrote this `ai-usage.md` file.

---

## Prompts Used

1. "im doing this assignment... I added the enchanted-courier-guild to the eclipse-workspace folder, can you set up eclipse so I can start working on the assignment"
2. "implement the code so all tests pass, and give me a step by step how I can open the project in eclipse"
3. "implement all the production code so all tests pass"
4. "why did I get these errors: The import org.junit.jupiter.api.Test is never used"
5. "for the last assignment, haunted-university-basement I got half off points... I want you to find what went wrong in the last assignment and make sure you don't do those mistakes again in this assignment"
6. "in eclipse when I right click on the file and I do share with team does that take you to a different file?"
7. "so it didn't take me here instead? /Users/canadymitchem/git/CSCS3330/enchanted-courier-guild"
8. "Now I want you to make an ai usage.md file for this assignment like how you did assignment 3"

---

## What We Did

- Reviewed all provided test files to understand the expected behavior before writing any code
- Derived strategy pricing formulas mathematically from the test expected values
- Verified the implementation by running `mvn clean verify` — 87 tests passed, 100% JaCoCo coverage confirmed
- Set up Eclipse and connected the project to the Git repository
- Investigated what went wrong in the previous assignment (HW3) — empty test assertions caused 0/247 PIT mutation kills
- Managed the Git repository setup via Eclipse's "Share with Team"
