---
layout: page
title: George's Project Portfolio Page
---

## Project Name: TaskWise

TaskWise is a project management application used by CS2103/T project managers.

The user interacts with it via the Command Line Interface (CLI) and it has a Graphical User
Interface (GUI) made using JavaFX. It is written in Java, and it has about 14107 LoC.

Given below are my contributions to the project.

### New Features and Components

* **New Feature: Sorting Tasks**
  * **What it does:** Sorts the tasks by the task description, status, deadline and priority, and displays the new sorted
    order to the user. Tasks can also be sorted along these fields, in ascending and descending order.
  * **Justifications:** The user may wish to find tasks that begin with a certain character, tasks that are not completed,
    tasks with the earliest deadline, or tasks that are high priority and need attention immediately.
    Providing users with a feature to sort by these criteria would help enhance user experience and improve their ability
    to organise and manage their projects.
  * **Highlights:** This is a new command that affects only the code files that create and handle Commands within
    the code, as well as code that interfaces the application with the Task List.
    This task proved more difficult than initially expected as a deep understanding of how
    Commands are executed in the app is necessary for this command to work properly.
    Moreover, it was challenging to come up with the necessary logic required for properly comparing two tasks by one
    field as we needed to define different natural orderings for each of the sortable fields in the task.

### Enhancement To Existing Features

* **Enhanced Exception Handling** [#57](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/57)
  * **What it does:** Introduced new exception classes and error messages to provide developers and users with a
    better idea of what exactly went wrong, building upon the old exception handling system and enhancing it with
    the introduction of new Exception classes.
  * **Justifications:** A user who tries to execute an invalid or illegal command needs to be told exactly why the
    task/command used was wrong. Developers who are working on the code base may also be confused by the exceptions
    thrown since there were previously only a few exception classes available for use in the app.
  * **Highlights:** This enhancement affects almost all files within the code base, which uses exceptions to handle
    errors and generate error messages for the users. This proved to be challenging due to the sheer number of methods
    which throws exceptions for vastly different reasons. Methods have to be checked one by one to determine what
    exactly was the cause of the error.
* **Redesigned UI** [#117](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/117)
  * **What it does:** Developed the Side Panel component of the UI, which provides users with useful information on
    any task that they select in their Task List. This component was also made to be as flexible and dynamic as
    possible, allowing content within the Panel to scale up or down with the overall size of the main window.
  * **Justification:** The Task Card has limitations on the size it can expand into to display all information
    of the Task Card. For example, the list of members displayed on the Task Card is limited to at most three members; thereafter,
    a placeholder label is used to alert users of the number of members that are hidden. To showcase all members assigned
    to a task, it is important to provide users with another avenue where this information is not truncated,
    which led to the development of the permanent Side Panel which displays information about a Task.
  * **Highlights:** While I had prior experience working with other UI frameworks which use nesting to describe
    parent-child widget relationships such as `Flutter`, it was still a challenge to find out how to translate UI design
    specifications into the actual UI layout. Figuring out how to use the `GridPane` widget and ensure that it is responsive
    to parent widget changes was challenging and often resulted in regressions in code when one aspect of the UI code is fixed
    (other parts of the UI lose their responsiveness, overflow, clip into the sides of the parent widget, etc.).
  * **Credits:** While developing this feature, I reused some code snippets from other external sources, such as
    * [StackOverflow](https://stackoverflow.com/)
      * [Implementing Global Key Strokes Recognition](https://copyprogramming.com/howto/implementing-a-global-key-press-for-javafx-methods)
      * [Defining Behaviours When ListView Cells are Clicked](https://stackoverflow.com/questions/52184611/javafx-keep-oldvalue-and-newvalue-of-listview-the-same-when-condition-has-not-b)

* **Code contributed:** [link to RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=asdfghjkxd&tabRepo=AY2324S1-CS2103T-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Project Management

* Set up the team organisation on GitHub.
* Set up the team's repository with the relevant issue trackers and tags.
* Created project boards used for [Issue Tracking and User Stories](https://github.com/orgs/AY2324S1-CS2103T-T17-1/projects/1),
  as well as [v1.3 Bugs](https://github.com/orgs/AY2324S1-CS2103T-T17-1/projects/3).
* Managed team DevOps processes such as CI/CD.

### Documentation

* User Guide
  * Added documentation on the new `sort` feature implemented and the limitations of this feature.
  * Created UML diagrams for the `sort` feature.
* Developer Guide
  * Added documentation on the new Exceptions that were added to TaskWise to enhance Exception handling
    for other Developers.
  * Added documentation on the new `sort` feature and the limitations of this feature.
  * Added UML diagrams on the Exceptions enhancements, as well as the `sort` feature and the use cases of the application.
  * Updated User Stories and Use Cases for `sort` feature.

### Contributions To Team-based Tasks

* Set up the relevant administrative and DevOps requirements for the project.
* Maintain said requirements throughout the project by leveraging tools such as GitHub Projects.

### Contributions To Community

* Conduct frequent PR and Code Reviews.
* Helping to answer questions regarding DevOps processes from other team members.
* Assist team members with any queries on other communication channels.
* PRs Reviewed (with non-trivial comments): [#55](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/55),
  [#61](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/61),
  [#62](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/62),
  [#78](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/78),
  [#84](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/84),
  [#105](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/105),
  [#139](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/139)
* Reported Bugs within the Code and Documentation: [#54](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/54),
  [#99](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/99),
  [#126](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/126)
  [#137](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/137)
