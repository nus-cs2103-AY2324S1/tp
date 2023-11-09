---
layout: default.md
title: "Wenzhong's Project Portfolio Page"
---

### Project: Class Manager 2023

Class Manager 2023 aims to provide fast access to NUS TAs in maintaining and managing student information across multiple classes. It also helps TAs visualise students’ grades, attendance and class participation. Class Manager 2023 is written in Java, and has about 26 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=lwz19&breakdown=true)

* **New feature**: `grade` and `class-part` command (PR: [#86](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/86))
    * What it does: Allows users to record the assignment grades and class participation of students.
    * Justification: This feature allows users to record the assignment grades and class participation of students. This is especially useful for TAs as they can record and visualize the assignment grades and class participation of students in a single application.
    * Highlights: Implementing this feature requires sufficient understanding of the `Model` component of the application. In order for the `grade` and `class-part` command to be executed, I had to refactor the `Student` class to allow for the assignment grades and class participation of students to be recorded.

* **New feature**: `theme` command (PR: [#107](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/107))
    * What it does: Allows users to customize the Class Manager by toggling light and dark theme.
    * Justification: This feature allows users to toggle light and dark theme. This is useful for TAs who prefer to use a specific theme for their application.
    * Highlights: Implementing this feature requires sufficient understanding of the `Model` and `UI` components of the application. In order for the `theme` to be applied to the GUI, I had to refactor the `MainWindow` class to allow for the different stylesheets to be loaded. In addition, I had to refactor the `UserPrefs` class to allow for the theme to be saved and loaded.

* **Enhancement to existing feature**:
  * `lookup` command (PR: [#54](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/54))
    * What it does: Allows users to search and filter students based on the given criteria.
    * Justification: This feature allows users to effectively find students based on the given criteria. This is especially useful for TAs who teach multiple tutorial classes within a specific module as they can search for students based on their tutorial class number and other information.
    * Highlights: Implementing this feature requires sufficient understanding of the `Model` component of the application. In order for the `lookup` command to be executed, I had to refactor the `Predicate` for the `Student` class.
  * `Storage` components to fit the new features (PR: [#86](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/86))
    * What it does: Allows the new "class details" features to be saved and loaded.
    * Justification: This enhancement allows the new "class details" features to be saved and loaded. This is essential for TAs who want to save and continue editing their data from where they left off.
    * Highlights: Implementing this feature requires sufficient understanding of the `Model` and `Storage` components of the application. I had to add new classes to the `Storage` component to allow for the "class details" features to be saved and loaded.

* **Documentation**:
  * User Guide:
    * Added documentation for the following features: `lookup`, `grade`, `class-part`, `theme`.
    * Added hyperlinks to the command summary section.
    * Edit the features section and class number description in the User Guide.
    * Handled image formatting of the User Guide.
    * Added some FAQ.
  * Developer Guide:
    * Edited the implementation details in `Class Details` and `Modify Class Details` features.
    * Added use cases.
    * Added more manual testing.
    * Added some NFRs.
    * Edited all the AB-3 UML diagrams to fit with our implementation.

* **Team-based tasks**:
  * Set up the GitHub website.
  * Monitored releases and milestones.
  * Frequently tested the application and fixed bugs.
  * Helped to review and merge PRs.
