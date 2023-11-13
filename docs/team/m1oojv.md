---
layout: page
title: Megan Loo's Project Portfolio Page
---

### Project: CoordiMate

CoordiMate is a **desktop application** designed specifically for **SoC Computing Club event planners** to help **manage their contacts and tasks** for their events, so that they can focus on the event itself.

Given below are my contributions to the project.

* **New Feature**: `editPerson` (PR [#76](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/76))
  * What it does: Allows users to modify existing person records.
  * Justification: Able to update contacts, ensuring up-to-date information.
  * Credits: The `editPerson` command was adapted from the `edit` command in the AddressBook-Level3 project (AB3) created by the [SE-EDU initiative](https://se-education.org).<br><br>

* **New Feature**: `deleteAllTask` (PR [#90](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/90))
  * What it does: Enables users to delete all tasks and start with a new task list.
  * Justification: Provides a quick way to clear all tasks, decluttering the interface.
  * Highlights: This feature required me to have an in-depth understanding of the execution pipeline for commands when manipulating a task list based on existing `UniquePersonList`.
  * Credits: The `deleteAllTask` command was inspired by the `clear` command in AB3.<br><br>

* **New Feature**: `markTask` & `unmarkTask` (PR [#86](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/86))
  * What it does: Allows users to mark tasks as completed or not completed, keeping track of task progress.
  * Justification: Ensuring users stay on top of their to-do list, aiding in prioritization.
  * Highlights: This feature involved creating a new `Status` field and `TaskStatus` enumeration. Utilizing `STATUS_DONE` and `STATUS_NOT_DONE` constants further enhanced consistency within the codebase. <br><br>
  
* **New Feature**: `findDone` & `findNotDone` (PR [#99](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/99))
  * What it does: Facilitates quick search and display of all completed or pending tasks.
  * Justification: Aiding users in task prioritization and effective task analysis.
  * Highlights: This feature required to know how lists are displayed in JavaFX. New predicates were created based on existing `PREDICATE_SHOW_ALL_PERSONS` in AB3. <br><br>

* **New Feature**: `deleteAllDone` (PR [#115](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/115))
  * What it does: Provides the option to delete all completed tasks.
  * Justification: Reduces clutter, ensuring users focus on upcoming tasks.
  * Highlights: This feature required an understanding on the dynamic nature of list. The loop iteration from the back was found to be essential for proper deletion, ensuring the accurate removal of completed tasks.<br><br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=m1oojv&breakdown=true)

* **Project management**:
  * Set up assertions for gradle (PR [#110](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/110))
  * Addressed javafx plugin + problem that was brought up in the forum (PR [#18](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/18))
  * Opened issues for each milestones (Issue [#41](https://github.com/AY2324S1-CS2103T-T10-2/tp/issues/41), [#98](https://github.com/AY2324S1-CS2103T-T10-2/tp/issues/98), [#114](https://github.com/AY2324S1-CS2103T-T10-2/tp/issues/114))<br><br>

* **Enhancements to existing features**:
  * Revise help messages with authentic event planning terminology and context. (PR [#108](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/108)) <br><br> 

* **Documentation**:
  * User Guide:
    * Add documentation for the `editPerson`, `deleteAllTask`, `markTask`, `unmarkTask`, `findDone`, `findNotDone` and `deleteAllDone` commands.
    * Add list of features of CoordiMate (PR [#108](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/108))
    * Add Glossary (PR [#160](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/160))
    * Add self drawn mockups of GUI for User Guide (Commit [#0a25954](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/20/commits/0a25954d0d7a5b4d0a5b5da7ddf313bd0861c78b))
  * Developer Guide:
    * Add user stories and use cases for `editPerson`, `deleteAllTask`, `markTask`, `unmarkTask`, `findDone`, `findNotDone` and `deleteAllDone` commands.
    * Add implementation details and design considerations of `markTask` feature with sequence diagram and activity diagram (PR [#100](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/100))
    * Add Planned enhancements (PR [#163](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/163)) <br><br>

* **Community**:
  * PRs reviewed (with non-trivial review comments): PR [#56](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/56), [#91](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/91), [#89](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/89), [#88](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/88), [#84](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/84)
  * Contributed to forum discussions: Forum [#6](https://github.com/nus-cs2103-AY2324S1/forum/issues/6)
  * Reported 5 bugs and suggestions during PE Dry Run. ([Repo](https://github.com/m1oojv/ped/issues))<br><br>

* **Tools**:
  * Created mockup templates for teammates to use when adding mockups to the UG ([link](https://docs.google.com/presentation/d/1fXBqhAU6SSoSZdYVog3G9v5H6gXFMCzPAQ2BjzzWzM4/edit#slide=id.p))
