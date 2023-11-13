---
layout: page
title: Pranavi's Project Portfolio Page
---

### Project: CoordiMate

CoordiMate is a **desktop application** designed specifically for **SoC Computing Club event planners** to help **manage their contacts and tasks** for their events, so that they can focus on the event itself.

Given below are my contributions to the project.

* **New Feature**: Implemented the `deletePerson` command. (PR [#78](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/78))
    * What it does: It allows users to delete a person from the contact list using the index of the task in the list 
      presented to them, filtered or otherwise.
    * Justification: This feature allows users to quickly delete a person from the contact list if they recall their 
      index without having to search for the person in the contact list. Else, they can filter the list of persons 
      to easily find the contact before deleting them.
    * Credits: The `deletePerson` command was adapted from the `delete` command in the AddressBook-Level3 project 
      (AB3) created by the [SE-EDU initiative](https://se-education.org). <br><br>

* **New Feature**: Implemented the `deleteTask` command. (PR [#89](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/89))
    * What it does: It allows users to delete a task from the task list using the index of the task in the list 
  presented to them, filtered or otherwise.
    * Justification: This feature allows users to quickly delete a task from the task list if they recall their index without having to search for the task in the task list. Else, they can filter the list of tasks to easily find the task before deleting it.
    * Credits: The `deleteTask` command was inspired by the `deletePerson` command in AB3.<br><br>

* **New Feature**: Implemented the `addTagPerson` command. (PR [#118](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/118))
    * What it does: It allows users to add one or more tags to a Person in the contact list based on the index which 
      refers to the Person in the contact list presented to them, filtered or otherwise.
    * Justification: This feature allows users to quickly add a tag to the Person instead of having to recall the 
      current list of tags and then adding the new tag to the list of tags for the Person in the current 
      `editPerson` command.
    * Credits: The `addTagPerson` command was inspired by the `editPerson` command in AB3.<br><br>

* **New Feature**: Implemented the `addTagTask` command. (PR [#118](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/118))
  * What it does: It allows users to add one or more tags to a Task in the task list based on the index which
    refers to the Task in the task list presented to them, filtered or otherwise.
  * Justification: This feature allows users to quickly add a tag to the Task instead of having to recall the
    current list of tags and then adding the new tag to the list of tags for the Task in the current
    `editTask` command.
  * Credits: The `addTagTask` command was inspired by the `editTask` command in AB3.<br><br>

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=pra-navi&breakdown=true)

* **Project management**:
    * Closed the `v1.3` milestone.
    * Create issues and PRs while assigning labels to them through `v1.1` to `v1.4` on GitHub to make task 
      management and tracking easier.<br><br>

* **Enhancements to existing features**:
    * UI Updates: (PR [#88](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/88), [#96](https://github.
      com/AY2324S1-CS2103T-T10-2/tp/pull/96))
      * Added the view for Task List and Task Card to the Main Window.
      * Wrapped text in Person Card, Task Card and Result Display Box.
      * Make the divider between the lists and between the result display box adjustable.
    * Add `tags` to the `Task` objects as a field: (PR [#101](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/101))
      * Changed the `addTask` and `editTask` implementations in include this new field.
      * Changed the `Task` class to include this new field.
      * Changed the sample data upon initial start up of the app to include this new field.
      * Added tags to the storage file.
      * Added tags to the `TaskCard` in java and fxml.
      * Added tests for all of the above.

* **Documentation**:
    * User Guide:
        * Added the documentation for the `deletePerson`, `deleteTask`, `addTagPerson` and `addTagTask` commands. 
          (PR [#59](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/59), [#69](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/69), [#107](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/107), 
          [#108](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/108), [#160](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/160), [#172](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/172)) 
          <br><br>
    * Developer Guide:
        * Added user stories and use cases for `deletePerson`, `deleteTask`, `addTagPerson` and `addTagTask` 
          commands. (PR [#55](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/55))
        * Added the implementation details of `deleteTask` and `addTagPerson` commands. (PR [100](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/100), [#109](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/109), 
          [#163](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/163)) 
        * Added Improved UI section. (PR [#174](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/174))<br><br>

* **Community**:
    * PRs reviewed by me can be found [here](https://github.com/AY2324S1-CS2103T-T10-2/tp/pulls?q=is%3Apr+reviewed-by%3Apra-navi).
    * Reported 8 bugs and suggestions during PE Dry Run. ([Repo](https://github.com/pra-navi/ped/issues))<br><br>
