---
layout: page
title: Sebastian's Project Portfolio Page
---

## Project Name: TaskWise

TaskWise is a project management application used by CS2103/T project managers.

The user interacts with it via the Command Line Interface (CLI) and it has a Graphical User
Interface (GUI) made using JavaFX. It is written in Java, and it has about 14107 LoC.

Given below are my contributions to the project.

### New Features and Components

* **New Feature: Completion Status of task**
  * What it does: It shows the project manager whether the specific task is completed or incomplete.
  * Justifications: The project manager would wish to keep track of which tasks are completed and which are incomplete,
    to improve project management.
  * Highlights: This mainly affects the Task Model and requires updates to be made to the TaskListCard FXML file.
* **New Feature: Members assigned to task**
  * What it does: It allows the project manager to keep track of fellow members who are assigned to specific tasks.
  * Justifications: In a group project, keeping track of who is doing whichever tasks helps the project manager to
    ensure that the respective assignees stay accountable for the completion of that task. It also helps reduce possible
    confusion on the ground as to who is doing what, improving the experience of project management using TaskWise.
  * Highlights: This mainly affects the Task Model and requires updates to be made to the TaskListCard FXML file.
  * Credits: The members assigned to the task feature was adapted from the AddressBook's `Tags` feature. The validation REGEX to check for the validity of `Member`'s input by the user was reused from ChatGPT's
    generated code that May has.

### Enhancement To Existing Features

* Enhanced the `add` command to allow for `Member` argument from the user [#127](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/127)
* Enhanced the `edit` command to allow for `Member` argument from the user [#105](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/105)
* Updated the UI to reflect the `Status` field [#125](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/125)
* Updated the UI to reflect the `Member` field [#105](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/105)


* **Code contributed:** [Link to reposense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=Sebtey&tabRepo=AY2324S1-CS2103T-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


### Project Management

* Proposed alternatives that could improve team meetings
* Ensure that the agenda of each meeting is made clear to ensure efficiency in hitting objectives set


### Documentation
* User Guide:
  * Added documentation for "Unmark Task" Command,
  * Added documentation for "Edit Task" command and
  * Added documentation for proposed future enhancements of "Edit" command

* Developer Guide:
  * Added documentation for "Unmark Task" command
  * Added documentation for "Edit Task" command
  * Added documentation for proposed future enhancements of "Edit" command

### Contributions To Team-based Tasks

* Release Management
* Check for documentation bugs
* Check for software bugs
* Contributed to demo video

### Contributions To Community

* **Review/mentoring contributions:**
  * Provides feedback for codebase quality and pull requests
  * Suggests possible alternatives to code, ensuring that codebase adheres to software engineering principles
* PRs Reviewed (with non-trivial comments): [#127](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/127), [#213](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/213)
