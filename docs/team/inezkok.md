---
layout: page
title: Inez Kok's Project Portfolio Page
---

### Project: HouR

HouR is a desktop HR management application used for managing and organising personnel data.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 9 kLoC.

Given below are my contributions to the project.

* **New Feature**: Reset Feature `reset`
    * What it does: Allows users to reset fields (overtime hours and number of leaves taken) for all employees by resetting them to their default value of o (0 overtime hours and an empty LeaveList).
    * Justification: This allows HR managers to efficiently reset overtime hours and number of leaves taken of every employee in just one command, as opposed to have to manually resetting them for each employee one by one.

* **New Feature**: EditLeave Feature `editleave`
  * What it does: Allows users to edit existing leave dates.
  * Justification: This allows HR managers to correct their previous mistakes in keying in leave dates (e.g. from a typo).

* **New Feature**: AddRemark Feature `addremark`
  * What it does: Allows users to add remarks to employees.
  * Justification: This allows HR managers to add and keep track of remarks made by supervisors for different employees, which can be used later to assess employee's performance.
  * Highlights: Originally, we wanted to add in a date field to keep track of when the remark was added. However, when trying it caused a lot of trouble due to the saving and loading of remarks into the json file.

* **New Feature**: DeleteRemark Feature `deleteremark`
  * What it does: Allows users to delete remarks from employees.
  * Justification: This allows HR managers to delete outdated or incorrect remarks made by supervisors for different employees, which ensures that the remarks kept are an accurate representation of each employee.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=inezkok&breakdown=true)

* **Project management**:
  * Reviewing, approving and merging pull requests on GitHub (some pull requests [#216](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/216), [#150](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/150), [#146](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/146))
  * Creating and assigning bugs and tasks to everyone on GitHub Issue Tracker
  * Maintaining milestones and deadlines on GitHub
  * Managed releases `v1.2` and `v1.3.2` (2 releases) on GitHub
  * Created and maintained Team Project Notes on Google Docs

* **Enhancements to existing features**:
  * Updated colour scheme of GUI (Pull request [#151](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/151))
  * Added **Employee ID attribute** to Employee class (Pull request [#89](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/89))
  * Added **RemarkList attribute** to Employee class (Pull request [#144](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/144))

* **Documentation**:
    * User Guide:
      * Added guides for edit leave, add remark, delete remark and reset features
      * Updated guide for help feature to include tip and caution
      * Added all the UI screenshots in UG

    * Developer Guide:
      * Added implementation guide for reset, editleave, addremark and deleteremark feature (including their UML diagrams)
      * Added instructions for manual testing for reset, editleave, addremark and deleteremark feature
      * Added and updated user stories
      * Added efforts required and achievements of the project under the Appendix: Effort

* **Community**:

* **Tools**: Java, JavaFX, IntelliJ, Github, Sourcetree
