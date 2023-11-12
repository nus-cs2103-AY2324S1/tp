---
layout: page
title: Saumya Shah's Project Portfolio Page
---

### Project: HouR

HouR is a desktop HR management application used for managing and organising personnel data.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 8 kLoC.

Given below are my contributions to the project.

* **Modification**: Change `Person` to `Employee`
    * What it does: It prepares the change from the Person class to Employee class by renaming all instances of Person to Employee, removing unused attributes, and changing methods, docs, and test data accordingly.
    * Justification: The Person class was not suitable to store the data required for the HouR application.

* **New Attribute**: Employee attribute `Position`
    * What it represents: It keeps track of the position of an employee.
    * Justification: Users will need to keep track of employee positions to make changes to their salary and performance metrics.

* **New Feature**: Report command `Report`
    * What it does: It generates and downloads a report of the number of leaves, overtime hours, and overtime pay of an employee in the application.
    * Justification: This allows HR managers to view and store the performance metrics of an employee, allowing them to assess an employee's performance and make decisions regarding the employee's salary and position.
    * Highlights: This feature was challenging to implement as it necessitated the involvement of all of the main 'components' of the system, including the logic, model, UI, and storage.

* **Modification**: Find command `Find`
    * What it does: It extends the find command to allows managers to find employees by other fields, including ID, Name, Department(s), Position, Email, and Phone Number.
    * Justification: This allows managers to find employees by other fields, allowing them to find employees more easily.
    * Highlights: This feature was initially partially implemented by creating separate predicate classes for all of the fields, but was later refactored to use a single predicate class.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=LordSaumya&breakdown=true)

* **Project management**:
  * Setting up and maintaining Github Organisation with the appropriate permissions, team roles, and repository settings.
  * Reviewing, approving and merging pull requests on GitHub (some pull requests: [#239](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/239), [#140](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/140), [#119](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/119), [#95](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/95))
  * Maintaining issues assigned to me on GitHub.
  * Managed releases [v.1.3 Trial](https://github.com/AY2324S1-CS2103T-W12-1/tp/releases/tag/v1.3.trial) and [v.1.3.1](https://github.com/AY2324S1-CS2103T-W12-1/tp/releases/tag/v1.3.1) on GitHub.

* **Enhancements to existing features**:
  * Created `ID` class for employee (Pull request [#78](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/78/))
  * Removed unused attribute Address from `Employee` class (Pull request [#80](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/80))
  * Edited `Tag` attribute to `Department` attribute in `Employee` class (Pull request [#87](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/87))

* **Documentation**:
    * User Guide:
        * Modified the guide for the extended `find` command to include the new fields that can be used to find employees.
        * Added guides and examples for the `report` command.
    * Developer Guide:
        * Add implementation details including UML sequence and activity diagrams and  for the `add`, `delete`, `find`, and `report` commands.
        * Updated the product scope.
        * Updated the non-functional requirements.
        * Added testing instructions for launch and shutdown.
        * Added testing instructions for the `find` and `report` commands.
        * Added the challenges faced and the difficulty level of the project.

* **Community**:

* **Tools**: VSCode, Git, GitHub, IntelliJ, JavaFX SceneBuilder
