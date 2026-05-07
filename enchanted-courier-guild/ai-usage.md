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

### 2. Reviewed Production Source Code Implementation
- The AI read all 10 provided test files under `src/test/java` to infer the required behavior of every class and method.
- The AI fixed files that had errors.
- The AI derived pricing formulas for each cost strategy by working backwards from the expected values in the test cases:
  - `StandardDeliveryStrategy`: `weight + 2 * distance + 10 + (fragile ? 5 : 0)`
  - `ExpressDeliveryStrategy`: `2 * weight + 4 * distance + 25 + (fragile ? 10 : 0)`
  - `CarefulDeliveryStrategy`: `7 * weight * distance + packageType.carefulSurcharge()`

### 3. Verification
- After writing all files, the AI ran `mvn clean test` (87/87 tests passed, 0 failures) and `mvn clean verify` (all JaCoCo 100% line and instruction coverage checks met).

### 5. Documentation
- The AI wrote this `ai-usage.md` file.

---

## Prompts Used

1. "im doing this assignment... I added the enchanted-courier-guild to the eclipse-workspace folder, can you set up eclipse so I can start working on the assignment"
2. "why did I get these errors: The import org.junit.jupiter.api.Test is never used"
3. "for the last assignment, haunted-university-basement I got half off points... I want you to find what went wrong in the last assignment and make sure I don't do those mistakes again in this assignment"
4. "Now I want you to make an ai usage.md file for this assignment like how you did assignment 3"

---

## What We Did

- Reviewed all provided test files to understand the expected behavior before writing any code
- Wrote test case
- Derived strategy pricing formulas mathematically from the test expected values
- Verified the implementation by running `mvn clean verify` — 87 tests passed, 100% JaCoCo coverage confirmed
- Set up Eclipse and connected the project to the Git repository
- Investigated what went wrong in the previous assignment (HW3) — empty test assertions caused 0/247 PIT mutation kills
- Managed the Git repository setup via Eclipse's "Share with Team"
