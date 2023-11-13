---
layout: page
title: Teh De Sheng Sean's Project Portfolio Page
---

## Project Name: TaskWise

TaskWise is a project management application used by CS2103/T project managers.

The user interacts with it via the Command Line Interface (CLI) and it has a Graphical User
Interface (GUI) made using JavaFX. It is written in Java, and it has about 14107 LoC.

Given below are my contributions to the project.

### New Features and Components

* **New Feature:** Mark Command: [#61](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/61)
    * What it does: Marks a task within the task list as completed.
    * Justifications: Allows the user to mark tasks as completed to allow the task manager to keep track of
      tasks and whether they are finished or not.
    * Highlights: The task reduces in opacity when it is marked as completed.
    * Credits: Sebastian for implementing the `Status` class and field necessary for tracking whether the
      task has been completed or not, as well changing the opacity of the task card depending on its `Status`.
* **New Feature:** Unmark Command: [#61](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/61)
    * What it does: Marks a task within the task list as incomplete.
    * Justifications: Allows the user to mark tasks as incomplete to allow the task manager to keep track of
      tasks and whether they are finished or not.
    * Highlights: The task becomes opaque when it is marked as incomplete.
    * Credits: Sebastian for implementing the `Status` class and field necessary for tracking whether the
      task has been completed or not, as well changing the opacity of the task card depending on its `Status`.
* **New Component:** Deadline: [#78](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/78)
    * What it does: A class that encapsulates the specified deadline of a given task within TaskWise.
    * Justifications:
        * User: Allows users to track the deadlines of each task, which would be displayed along with the task within TaskWise.
        * Developers: By creating a `Deadline` class, the functionality and expected behaviour of the deadlines within each task would be easier to maintain as well as extend by future developers.
    * Credits: George for assisting with the creation of default deadlines for newly created tasks

### Enhancement To Existing Features
* Updated the Add Command to allow for a variable number of optional arguments from the user: [#127](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/127)
  * Before 1.3, the Add Command was only able to take in a description input from the user in order to create a task within TaskWise. This would result in a task being created with all other fields, like deadlines and priorities being initialised to their default values.
  * As a group, we believed that the user experience would be enhanced if users were given an option to create a task with certain specified fields already initialised to values already given by the user's input.
  * Therefore, I updated the Add Command from 1.2 to be able to take in additional arguments that are optional, and create a task with these fields already initialised with these given inputs.


* **Code contributed**: [Link to RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=seantehds&tabRepo=AY2324S1-CS2103T-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Project Management

**Team Leader** :
  * Ensures goals of the team are being met and keeps the group focused on tasks that need to be done.
  * Facilitate discussions among group members
  * Arranges for meetings, and comes up with the meeting agenda before each meeting
  * Ensures that the team is on track to meet the deadlines set by the module coordinator

### Documentation

* User Guide:
  * Added documentation on the Mark, Unmark, Find and List command.
  * Added planned enhancements to the checking and adding of deadlines, as well as allowing for larger task indices.
* Developer Guide:
  * Added documentation on the Mark, Unmark, Find and List command.
  * Added corresponding UML diagrams for these commands. 
  * Updated the use cases and user stories for these commands.
  * Added documentation on the Deadline class.
  * Added planned enhancements to the checking and adding of deadlines, as well as allowing for larger task indices.

### Contributions To Team-based Tasks

* Created skeletal PPP for the group.
* Added issues on the GitHub Project board for the group.
* Uploaded updated UG to the GitHub repo on behalf of the team.
* Wrote the section on Prefixes under the Features section in the User Guide.
* Wrote about the marking of tasks in the Tutorial section in the User Guide.
* Ensure the formatting and the hyperlinks work in the User Guide and Developer Guide.
* Updated the section on the generic sequence diagram, as well as the partial class diagram of the Logic component in the Developer Guide.
* Contributed to demo video.

### Contributions To Community

* Helped out with the concerns of other members, such as when they were not sure about certain implementations about code that I wrote as well as existing AB3 code.
* Helped review some PRs on GitHub.
