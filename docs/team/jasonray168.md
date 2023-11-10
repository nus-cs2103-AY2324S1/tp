---
layout: page
title: Jason Ray's Project Portfolio Page
---

### Project: HouR

HouR is a desktop HR management application used for managing and organising personnel data. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about [number] kLoC.

Given below are my contributions to the project.

* **New Attribute**: [OvertimeHours]
    * What it represents: It keeps track of the number of overtime hours taken by an employee.
    * Justification: Users will need to keep track of employee overtime hours to make changes their bonus salary and performance measures.
* **New Feature**: [Sort]
    * What it does: It sorts the list of all employees based on a field and an order.
    * Justification: Users will want to check for employee with the highest salary, the highest overtime hours taken, etc.
    * Highlights: A big problem encountered during the implementation of this feature is that 
      the list editing technique used in find feature does not work with sorting. Therefore, I was able to make this feature work
      by editing the original employee list in UniqueEmployeeList.
* **New Feature**: [DeleteLeave]
    * What it does: It deletes a leave from the list of leaves an employee has taken.
    * Justification: Users might have to remove an allocated employee leave because of a mis-input, which will need a delete feature for the leave
    * Highlights: In the process of implementing the delete leave feature, I realized that the Leave object cannot be integrated well to the JSON data file
      As a result, I had to implement a JsonAdaptedLeave class that converts the Leave object into its Jackson-friendly version.
      This is where most of the time is spent on this feature implementation.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=JasonRay168&breakdown=true)

* **Project management**: Reviewing and approving pull requests on GitHub, assign bug issues to my account on GitHub.

* **Documentation**:
    * User Guide: Add guides for sort, add leave, and delete leave features.
    * Developer Guide:
      * Add implementation details for sort and delete leave features.
      * Add instructions for manual testing for sort and delete leave features.
      * Add details for planned enhancements.

* **Community**:

* **Tools**: Java, JavaFX, IntelliJ, Git
