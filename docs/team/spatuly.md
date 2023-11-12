---
layout: page
title: Liyan's Project Portfolio Page
---

### Project: TAvigator

TAvigator is a desktop address book application used by tutors to keep track of students' contact details and attendance. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about (TBA) kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the list attendance command
    * What it does: Displays a summary of attendance records and list of absentees from the chosen tutorial group in the course for the week chosen. If tutorial group is not specified, displays the summary for the whole course. If there are students whose attendances are not marked for the week, it displays the list of students with unmarked attendances.
    * Justification: The feature allows the user to quickly obtain a summary of attendance records, the list of absentees for the week and their contact details and/or the students with unmarked attendances, without having to manually look for them.
    * Highlights: The implementation of the ListAttendanceCommand was rather tedious because it underwent many iterations, as its implementation was closely related to that of `Attendance` and how course codes and tutorial group IDs were handled, both of which were changed a number of times.

* **New Feature**: Added the delete all command
    * What it does: Allows the user to delete all students from the chosen tutorial group in a course at once. If the tutorial group is not specified, it deletes all students in the course.
    * Justification: The feature allows the user to easily delete a group of students, reducing the hassle of having to do so one by one.
    * Highlights: The implementation of this feature was tricky as it was implemented as part of an existing DeleteCommand. As such, more problems were encountered when implementing the parser. Also, a large variety of errors had to be handled properly.

* **New Enhancement**: Implemented attendance saving
    * What it does: Allows the marked attendance to be saved into the tavigator.json file.
    * Justification: Without this, the marked attendances will be lost upon closing TAvigator.
    * Highlights: The implementation of this feature was straightforward but required changes to many aspects of the codebase.

* **Code contributed**: [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=zoom&zA=spatuly&zR=AY2324S1-CS2103T-T09-4%2Ftp%5Bmaster%5D&zACS=238.84021845679877&zS=2023-09-22&zFS=&zU=2023-11-09&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Contributions to team-based tasks**:
    * Managed issues on the issue tracker.
    * Updated UserGuide with screenshots of the product.
    * Documented the target user profile, value proposition and user stories in the DeveloperGuide.

* **Enhancements to existing features**:
    * Edited typicalPersons to the implementation of TAvigator. (PR: [#99](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/99))
    * Enhanced message for list (students) command. (PR: [#99](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/99))
    * Modified test cases for existing features to ensure validity. (PR: [#78](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/78), [#88](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/88), [#99](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/99))

* **Documentation**:
    * User Guide:
        * Added details of `list attendance`, `list students`, `delete all` commands. (PR: [#67](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/67), [#78](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/78))
        * Modified and added additional details for other commands to improve cohesiveness of UserGuide. (PR: [#99](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/99), [#104](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/104))
        * Updated UserGuide with up-to-date screenshots of the product. (PR: [#99](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/99), [#104](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/104))
    * Developer Guide:
        * Added implementation details of the `list` features, which contains `list students` and `list attendance`. (PR: [#88](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/88))
        * Added use cases. (PR: [#39](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/39))
        * Documented the target user profile, value proposition and user stories. (PR: [#39](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/39))
        * Added planned enhancements. (PR: [#158](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/158))

* **Review / mentoring contributions**:
  * PRs reviewed (with non-trivial comments): [#85](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/85), [#155](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/155)

* **Contributions beyond the project team**:
  * Reported 17 bugs and suggestions for other teams during PE-D.
