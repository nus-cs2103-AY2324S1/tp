---
layout: default.md
title: "Chang Xian's Project Portfolio Page"
---

### Project: Pawfection

Pawfection is a desktop app for managing animals in a pet shelter, optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI). It aims to provide a platform for volunteers to
store and retrieve information about animals in a shelter easily.

Given below are my contributions to the project:

* **Code contributed**: [Chang Xian's RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=euchangxian&breakdown=true)

* **Project management**:
    * Assigned Issues for v1.2

* **New features**:
  - Implemented an improved `Help` command [#117](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/117).
    - What it does: Allow users to specify a command name as argument
    - Justification: This additional feature allows users to quickly look up the usage guide of a specific command.
      This gitis especially helpful and improves the user experience, since the generic help message would just be
      redirecting the user to the User Guide webpage, which makes for an incredibly frustrating experience.
    - Highlights: This implementation will be affected if new commands are added. `CommandEnum` will have to be updated
      with the newly added command's name and help message.

* **Enhancements to existing features**:
  - Changed `Person` to `Animal` to reflect usage of Pawfection [#51](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/51), [#92](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/92).
  - Updated `Storage` components to persist new `Animal` model [#87](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/87).
    - Includes `JsonAdaptedAnimal` etc.
  - Refactored existing implementation of `AddAnimalCommand` [#94](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/94),
    setting the direction for other command implementations.
  - Wrote non-trivial test cases for `Storage` components, `DeleteAnimalCommand`, `DeleteAnimalCommandParser`,
    `CliAnimalSyntax`, `TimeUtil`, `HelpAnimalCommand`, `HelpAnimalCommandParser` etc.

* **Documentation**:
  - User Guide:
    - Migrated User Guide from Google Docs to MarkDown
    - Migrated User Guide from Jekyll to MarkBind [#2](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/2)
    - Add table illustrating attributes of an `Animal` for ease of reference.
    - Refined User Guide commands' decription for less ambiguity [#218](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/218).
  - Developer Guide:
    - Updated `Storage` section [#91](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/91), along with the [Class Diagram](https://github.com/euchangxian/tp/blob/master/docs/diagrams/StorageClassDiagram.puml)
    - Added `Help` command section [#213](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/213), with an [Activity Diagram](https://github.com/euchangxian/tp/blob/master/docs/diagrams/HelpActivityDiagram.puml)
    - Refined descriptions for less ambiguity [#213](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/213).

* **Team Tasks**:
    * Updated AboutUs
    * Migrated and updated User Guide
    * Improved CI pipeline to ensure higher code quality in [#13](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/3), [#4](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/4),
      [#29](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/29), [#46](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/46)
    * Refactored various classes for Pawfection, unblocking the team and enabling them to work on separate features concurrently.
    * Reviewed PRs and gave constructive feedback

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#95](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/95),
      [#107](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/107), [#75](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/75),
      [#40](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/40), [#69](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/69),
      [#74](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/74).
