---
layout: page
title: Anthony's Project Portfolio Page
---

### Project: TAvigator

TAs sometimes have many tutorial groups to manage, it could be chaotic to manage the attendance and work submission of
each individual student. TAvigator aims to provide a one-stop platform for National University of Singapore
Computer Science modules’ Teaching Assistants (TAs) to keep track of the work submission and attendance of each student
via a contact management application.

Given below are my contributions to the project.
* **New Feature**: Added the ability to view detailed attendance records of a student
  * What it does: Allows the user to view the week by week attendance records of a student whether they were present or absent.
  * Justification: This feature improves the product by allowing the user to view which weeks the students were absent for and the reason why they were absent, giving the user detailed information about their attendance.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. The implementation itself was rather straightforward as it only involved the addition of `ViewCommand` and the corresponding parser as well as the addition of appropriate test cases. 
* **New Feature**: Added reason of attendance to `Attendance` class.
  * What it does: Allows the user to indicate reason of absence if the student is marked as absent for the class.
  * Justification: This feature improves the product by allowing the user to add more details to the attendance taking of students, where they may be many reason of absences for different students that they may have to take down.
  * Highlights: The implementation was straightforward but tedious as it required minor adjustments to other classes and test cases as students who were marked as absent needed a reason of absence, if not, the program will throw an error.
* **New Enhancement**: Added the ability to find a student by their student ID or name.
  * What it does: Allows the user to find a student by their student ID instead of the student's name.
  * Justification: Sometimes, the student's name might be hard to spell or quite lengthy, leading to the user preferring to search for the student via their student ID which is typically less than 10 letters.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of the design alternatives.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=anthonytamzil&tabRepo=AY2324S1-CS2103T-T09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* **Project Management**:
  * Set up 43 issues and managed 21 issues on Github.
  * Managed 5 milestones, `v1.1` to `v1.4`.
  * Tracked project tasks and deliverables.
* **Enhancements to existing features**: 
  * Wrote additional test cases for new and existing features. (Pull request [#75](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/75), [#76](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/76), [#85](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/85), [#92](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/92))
  * Added minor enhancements and changes to the GUI. (Pull request [#57](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/57), [#75](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/75))
* **Documentation**:
  * About Us:
    * Added personal documentation for AboutUs. (Pull request [#42](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/42))
  * User Guide: 
    * Standardized UG overall formatting for the individual commands and for the command table. (Pull request [#54](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/54), [#57](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/57), [#85](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/85))
    * Added pictures of each individual command to enhance user-friendliness of the user guide. (Pull request [#85](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/85))
  * Developer Guide: 
    * Added implementation details for `view` and `find` command including the appropriate UML diagrams.
    * Contributed use cases and future enhancements for future development.
* **Contributions to team-based tasks**
  * Set up the Gradle, Codecov, GitHub team organization and repository.
  * General code enhancements in terms of refactoring and product morphing.
  * Maintained issue tracker by labelling tasks appropriately and ensuring that it is up to date.
  * Did release management for `v1.1` to `v1.3`.
  * Updated `index.md` to fit team product. (Pull request [#101](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/101))
* **Review / mentoring contributions**: 
  * Reviewed 11 pull requests (with non-trivial review comments): [#91](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/91), [#98](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/98)
* **Contributions beyond the project team**: 
  * Reported bugs and suggestions for other teams in the class. (examples: [1](https://github.com/AY2324S1-CS2103T-W09-2/tp/issues/158), [2](https://github.com/AY2324S1-CS2103T-W09-2/tp/issues/155), [3](https://github.com/AY2324S1-CS2103T-W09-2/tp/issues/182), [4](https://github.com/AY2324S1-CS2103T-W09-2/tp/issues/187), [5](https://github.com/AY2324S1-CS2103T-W09-2/tp/issues/189))
