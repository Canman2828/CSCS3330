# AI Usage Disclosure

This file documents all use of AI assistance (Claude Code via Anthropic) for this assignment.

## Tool Used
- **Claude Code** (claude-sonnet-4-6) via the Claude Code CLI / IDE extension

---

## What I Had the AI Do

### 1. Project Setup / Configuration
- Fixed a JRE version mismatch in `.classpath` — the file referenced `JavaSE-17` but the installed JRE was Java 21. The AI updated `.classpath` to use `JavaSE-21`.

- After deciding to target Java 17 (matching the README), the AI installed Temurin JDK 17 via Homebrew and updated both `pom.xml` (`maven.compiler.source/target`) and `.classpath` to target Java 17.

### 2. Git / Eclipse Workspace Setup
- The AI copied the project from `~/eclipse-workspace/haunted-university-basement/` into the course Git repo at `~/git/CSCS3330/haunted-university-basement/` so the project could be committed alongside Assignment 2 without being in the same folder.

### 3. Test File Creation
- The AI read the README and identified the four test files to create:
  - `GameEngineTest.java`
  - `DamageCalculatorTest.java`
  - `TrapResolverTest.java`
  - `QuestTrackerTest.java`
- It first created empty shell files (class declarations with the `@Test` import only).
- After reviewing all source files (`GameEngine`, `DamageCalculator`, `TrapResolver`, `QuestTracker`, `BossMonster`, `Trap`, `Quest`, `Player`, `Monster`, `DungeonFactory`, and all event/result classes), the AI setup test methods, helped with actions, and assertions.

### 4. Comments and Documentation
- The AI wrote this `ai-usage.md` file and the updated `README.md`.

---

## Prompts Used (summarized)

1. "can you look at the readme and make the files for the test cases but dont write the code"
2. "can you add the @test and make the methods I need. basically just format it so I can just write the test"
3. "help me fix the test methods, make a ai-usage.md file and talk about what I had you do, and give instructions on how I can test the testcases"

---

## What we did

- Reviewed the generated tests to understand what each one is checking
- Set up Eclipse, connected to the Git repository, and imported the Maven project
- added comments
- managed git commits
- had ai review code
- Will run the tests and interpret results
