---
layout: page
title: Devanshu Bisht's Project Portfolio Page
---

### Project: CodeSphere

**CodeSphere** is a **desktop contact management app, optimised for use via a Command Line Interface (CLI)**
while still having the benefits of a Graphical User Interface (GUI).
It is an app targeted at enabling Teaching Assistants in NUS School of Computing to better manage their students.

Given below are my contributions to the project.

* **New Feature**: Find Command.
  * What it does: Locate students in a course using various criteria. The search is case-insensitive and order-agnostic.
  * Justification: This feature aids teaching assistants in quickly locating students based on partial information. Enables efficient searches for any attribute, enhancing overall workflow.
  * Highlights: Development included distinct predicates for student attributes, integration with the `Course` class for proper list updates, and `StringUtil` class enhancements for tailored logic.

* **New Feature**: StageManager & Stages.
  * What it does: Determines the user's stage to parse commands accurately and guides `UiManager` in displaying relevant components.
  * Justification: This feature eliminates the need for lengthy, section-specific commands. For example, our application can detect if the `add` command should add a student or course. The logic also prevents bugs from happening as we restrict the usage of some commands based on which stage they are at.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to classes such as `LogicManager` and `CodeSphereParser`.

* **New Feature**: Home.
  * What it does: Allows users to visit back home when they are viewing a student list of a selected course.
  * Justification: A user-friendly command to allow users to navigate back to the `home` page which contains all the courses.
  * Highlights: This command needed to be implemented appropriately such that our application Stage would be implemented correctly.

* **New Feature**: Add Course.
  * What it does: Allows users to add a new course to their taught classes while ensuring it is of the correct format.
  * Justification: TAs should be allowed to add any number of courses which they are managing. There should also be a validation to ensure the course is correctly entered into the appropriate format of NUS Courses.
  * Highlights: This command needed to be implemented carefully to align with the updated `Model` component of the app.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=devanshu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=devanshubisht&tabRepo=AY2324S1-CS2103T-W15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

* **Enhancements to existing features**:
    * Update StringUtil component for the app (PR [#165](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/165)).
    * Update CodeSphereParser component for the app (PR [#77](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/77)).
    * Added and updated test files for several classes and existing features to align with the app's functions ([#278](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/278), [#281](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/#281), [#282](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/#282), [#280](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/#280), [#328](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/#328)).
    * Added multiple predicate files in model which are used for the logic in `find` (PR [#165](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/165)).
    * Implemented an auto `reset` when user goes from a Course stage to a Home stage (PR [#165](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/165)).
    * Added more sample data in `SampleDataUtil` for first time users to test with (PR [#325](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/325)).

* **Documentation**:
    * User Guide:
        * Added documentation for the features `find` (Student), `find` (Course) `pq`, `remove`  (PR [#195](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/195), PR [#127](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/127))
        * Fixed user guide links in the table of content (PR [#311](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/311))
        * Added page breaks for good readability of the UG's PDF format.
    * Developer Guide:
        * Edited UML diagram for `Model` component (PR [#341](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/341/)).
        * Described the implementation and logic flow of `find` command (PR [#341](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/341/)).
        * Added activity, sequence and class diagrams for `find` command (PR [#341](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/341/), PR [#361](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/361)).
        * Added manual testing walkthrough for `find` (PR [#370](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/370/)).
        * Added the points under Planned Enhancements (PR [#370](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/370/)).

* **Team-tasks**:
  * Set up MarkBind initially and then changed to Jerkyll for product website.(PR [#71](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/71)).

* **Community**:
    * Reviewed PRs, and suggested improvements for Javadoc style and code quality.
    * Reported bugs for other teams during PE-D and suggested possible improvements. Can be seen [here](https://github.com/devanshubisht/ped).
