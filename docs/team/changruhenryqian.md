---
layout: default.md
title: "Qian Changru's Project Portfolio Page"
---

### Project: Class Manager 2023

Class Manager 2023 aims to provide fast access to CS2103/T TAs who need help in maintaining student information across multiple classes. It also aims to help TAs visualize students' data. Class Manager 2023 is written in Java, and has about 26 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=changruhenryqian&breakdown=true)

* **New Feature**: Mark a student as present for one tutorial.
  * What it does: allows the user to mark a student as present for one tutorial. This is one of the key features of our application.
  * Highlights: This feature cooperates with other commands to enable data visualization.

* **New Feature**: Mark a student as absent for one tutorial.

* **New Feature**: Mark all students displayed as present for one tutorial.
  * What it does: allows the user to all students displayed as present for one tutorial.
  * Justification: This feature improves the product significantly because a user may want to mark many students using one command and this feature saves huge amount of time.
  * Highlights: Using together with other commands, such as look up and list, it is extremely powerful through allowing the user to mark multiple students and creating flexibility for the user.

* **New Feature**: Mark all students displayed as absent for one tutorial.

* **New Feature**: Randomly selects a specific number of students from all students displayed.
  * What it does: allows the user to randomly selects a specific number of students.
  * Justification:  This feature improves the product significantly because a common situation in class is when the user wants to select students and no one volunteers. It allows the user to choose students fairly.

* **Enhancements to existing features: `edit`**:
  * Changed the `edit` feature to fit into our product, such as 
    * Editing by student number
    * Changing the student's class number

* **Enhancements to existing features: `delete`**:
  * Changed the `delete` feature to allow deleting by student number.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `present`, `absent`, `present-all`, `absent-all`, and `random`.
    * Edited documentation for the features `edit`, and `delete`.
  * Developer Guide:
    * Added implementation details for the `present` feature.
    * Added user stories and user cases.
    * Added manual testing for `present`, `absent`, `present-all`, `absent-all`, `random`, `edit`, and `delete`.
    * Added Planned Enhancements section.(PR: [#205](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/205))
    * Edited AB-3 UML diagrams to fit with our implementation.

* **Team-based tasks**:
  * Reviewed and merged pull requests.
    * Some non-trivial reviews: [#52](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/52), [#82](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/82), [#86](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/86)
  * Maintained the issue tracker.
  * Tested the product and fixed bugs frequently.

* **Tools**:
  * Integrated MarkBind.(PR: [#1](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/1))
