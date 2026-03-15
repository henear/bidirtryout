---
name: remove-bidirectional-dependencies
description: Safely detect and remove bidirectional dependencies between Java classes in Java 21 Maven projects while preserving APIs, keeping build config untouched, and guaranteeing compile/test verification.
tools: codebase_search, open_file, replace_in_file, create_file
---

# Skill: Remove Bidirectional Dependencies in Java Projects

## Purpose

This skill removes **bidirectional dependencies** (cycles) between Java classes while keeping behavior stable and APIs usable.

A bidirectional dependency means:

- Class A depends on B
- Class B depends on A

Examples:

- A calls `B.method()`, B calls `A.method()`
- A references `B.CONSTANT`, B references `A.CONSTANT`
- mutual imports/static references/field coupling

Goal:

- remove all cycles
- preserve public APIs
- keep project compiling and tests passing
- avoid touching build configuration

---

## Scope & Assumptions

- Target stack: Java 21 + Maven project
- Refactor only application code unless tests must be added/updated
- Keep package structure stable unless explicitly required

---

## Non-Negotiable Safety Rules

### Rule 1 — Build config is strictly protected

Do **not** modify any build configuration files:

- `pom.xml`
- `*.gradle`
- `settings.xml`
- Maven wrapper files

### Rule 2 — Preserve public API surface

Do not change:

- public method signatures
- public fields
- package names
- module boundaries

(You may add internal helper/bridge classes.)

### Rule 3 — Compile gate is mandatory

After each cycle refactor:

```bash
mvn -q -DskipTests compile
```

If compile fails:

1. stop
2. fix failure immediately
3. re-run compile
4. continue only after success

### Rule 4 — No placeholder references

Never import/call a class or method that has not been created yet.

### Rule 5 — Task is complete only at zero cycles

Do not stop after first fix. Continue until no bidirectional dependencies remain.

---

## Execution Safety Addendum (Atomic Delivery)

For each selected cycle, refactor atomically in this strict order:

1. Create bridge/helper classes first
2. Update original classes to use them
3. Run compile gate
4. Add/update tests (if needed)
5. Run tests (or at least targeted tests + full suite when feasible)

Never leave the repo in a half-refactored state.

---

## Iterative Refactoring Loop (Must Follow)

Repeat until completion:

1. Detect all cycles
2. Select one cycle
3. Classify dependency type(s)
4. Refactor selected cycle
5. Compile gate
6. Re-scan for remaining cycles

---

## Step 1 — Detect Cycles

Find mutual dependencies via:

- direct imports
- static imports/usages
- method call coupling
- constant coupling
- field type references

Produce explicit pair list, e.g.:

- `OrderService <-> PaymentService`
- `A <-> B`

---

## Step 2 — Refactor One Cycle at a Time

Refactor exactly one pair before moving to the next.

This minimizes blast radius and compile risk.

---

## Step 3 — Choose Refactor Strategy by Coupling Type

### A) Method call cycle

If `A -> B.method` and `B -> A.method`:

- add bridge/helper classes
- naming convention:
  - `AForB`
  - `BForA`
- place in same package as owning side (or nearest logical package)

### B) Constant cycle

If `A` needs `B.CONST` and `B` needs `A.CONST`:

- introduce bridge constant accessors:
  - `AConstantsForB`
  - `BConstantsForA`
- prefer accessor methods over exposing internals

### C) Shared logic cycle

Extract common logic into neutral class:

- `CommonService` / `CommonUtil`
- both A and B depend on shared class, not each other

### D) High-coupling orchestration cycle

Use mediator/coordinator pattern:

- `OrderPaymentCoordinator` style class
- A and B interact through coordinator

### E) Dependency inversion

When appropriate, introduce interface (`APort`) and invert dependency.

---

## Preferred Refactor Priority

Use the simplest strategy that preserves behavior:

1. Extract shared pure logic/util
2. Bridge helpers
3. Dependency inversion
4. Mediator/coordinator

Avoid overengineering.

---

## Verification Protocol

### Mandatory checks per cycle

- imports resolve
- methods/fields resolve
- compile succeeds (`mvn -q -DskipTests compile`)
- no API breakage introduced

### End-of-task checks

Run:

```bash
mvn -q -DskipTests compile
mvn -q test
```

If full tests are expensive/unavailable, run best available targeted tests and report limitation.

---

## Test Requirements

If existing behavior is not covered, add focused unit tests for:

- original public methods involved in decoupling
- edge cases (null/empty/boundary)
- bridge/helper semantic equivalence where applicable

Do not add unrelated tests.

---

## Completion Criteria (All Required)

Task is complete only when all are true:

1. No bidirectional dependencies remain
2. Compile passes
3. Tests pass
4. Public APIs preserved
5. Build config files unchanged (`pom.xml`, etc.)

---

## Failure Handling

If compile/test fails during refactor:

1. capture exact error
2. identify failing reference/class
3. fix immediately
4. re-run gate command
5. proceed only after pass

No “done” status allowed while gates are failing.

---

## Required Final Report Format

Final report must include:

1. **Cycles detected** (full list)
2. **Cycles resolved** (pair-by-pair)
3. **Files changed** (explicit paths)
4. **Verification commands run** + pass/fail
5. **API compatibility note**
6. **Confirmation that protected files were not modified**

---

## Quick Checklist (Operator)

- [ ] Detected all cycles
- [ ] Refactored one cycle at a time
- [ ] Created bridges/helpers before referencing them
- [ ] Compile gate passed after each cycle
- [ ] Tests added/updated where needed
- [ ] Final compile + test passed
- [ ] No protected build file changes
- [ ] Zero cycles remain
