---
layout: page
title: May's Project Portfolio Page
---

## Project Name: TaskWise

TaskWise is a project management application used by CS2103/T project managers.

The user interacts with it via the Command Line Interface (CLI) and it has a Graphical User
Interface (GUI) made using JavaFX. It is written in Java, and it has about 14107 LoC.

Given below are my contributions to the project.

### New Features and Components

* **New Feature:** Add Tasks
  * **What it does:** Adds a task to the task list.
  * **Justifications:** Allows project managers to be able to add tasks to the list of tasks so that they can
    be tracked easily. I have made **ONLY** the `description` of the task compulsory, since it
    defines the task, and it is our unique identifier. Other parameters of a task such as `deadline`, `priority`,
    `status` and `member` are made optional to be included when using the `add` command, this part was mostly
    implemented by Sean.
  * **Highlights:** The `add` command is extremely easy and intuitive to use, due to its nature of only having 1
    compulsory parameter. Furthermore, its parameters are easily editable with the `edit` command. Originally, `add` was
    built by AB3, and I have edited, refactored and tweaked it to fit TaskWise.
  * **Credits:** The validation REGEX to check for validity of `description`, `note` and `member` input by the user
    was reused from ChatGPT's generated code suggestions. I have edited and tried a few variations before using the one that we
    currently have. It checks that the user input for these parameters under the Add command is not blank, only
    whitespaces and does not contain the "/" character.

* **New Component:** Priority of Tasks
  * **What it does:** Allow tasks to have a 1 priority level of NONE, LOW, MEDIUM, HIGH.
  * **Justifications:** Initially, I considered using the original AB3's implementation of `Tag` to implement the Priority
    of a task. However, I felt that for `Tag`, there could be more than 1 tag assigned to a task, which is not the
    behaviour intended for a Priority. Furthermore, a priority should be an enumeration class since it has constant
    values. Lastly, there was a need to have a default priority level, NONE, for tasks that had no priority level yet
    or at all.
  * **Highlights:** With tasks of different deadlines and importance, project managers may want to be able to
    categorize how important each task is to them. Therefore, they can associate priority levels with those tasks.
    TaskWise allows the priority level to be assigned at the level of adding a task with the `add` command, or to change
    default or existing priority level with the `edit` command.

### Enhancement To Existing Features

* **Redesigned User Interface**: [#64](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/64)
  * **What it does:** Changed the user interface to be more user-friendly with a different colour scheme.
  * **Justifications:** We wanted a more vibrant and bright user interface that is readable by users. The use of different
    colours in the tags also highlights different significance such as priority levels.
* **Priority, Status Tags on Task Cards** [#114](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/114)
  * **What it does:** The priority levels are displayed in a little tag form with colours to indicate the different levels
    of priority. The status is also displayed in a tag form. This makes it more user-friendly when tracking tasks as
    project managers can see their tasks' level of importance and status to manage those tasks better.
  * **Justifications:** A red coloured tag for priority level HIGH, a yellow tag for priority MEDIUM, green for a
    priority of LOW and lastly, a grey tag for a priority level of NONE. The colours used are in with accordance to the
    colour scheme that we are used to when associating very important to not important tasks. The light purple colour
    to represent status was decided to differentiate from other tags in a Task Card.
  * **Highlights:** I thought that the implementation of the tags would be easy, considering how the AB3 already had
    similar tags. However, it was difficult to have the different colours associated with the different text of
    priority, especially. I had to explore the use of visible and managed labels in FXML.
  * **Credits:** While implementing the priority display on the User Interface, I took some suggestions from ChatGPT on
    how to make different levels of priorities be associated with the different strings of priority. The code generated
    in TaskListCard.fxml and DarkTheme.css regarding priority IDs and status are my own while I tweaked and played
    around with some suggestions.


* **Code contributed:** [Link to RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=maypfv&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=maypfv&tabRepo=AY2324S1-CS2103T-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Project Management

* Ensures that meetings' discussions are properly documented.
* Keeps track of deadlines and upcoming meeting dates and agendas.
* Regularly update meeting dates and times to allow members to stay in the loop.

### Documentation

* **User Guide:**
  * Added the introduction and `add` feature in the user guide.
  * Worked heavily on the `add` feature and `clear` feature documentation, under implementations.
  * Contributed to the `edit` feature component, mostly reused the `add` command's parameter descriptions in the `edit`
    command.
  * Proposed Enhancement for the `add` feature to allow the same `description` but different other parameters to be added to
    the task list.
  * Formatting of all implementations for the different features.
* **Developer Guide:**
  * Added the introduction and some sections at the start of the developer guide.
  * Model Component's diagrams.
  * Model Component's refactoring of any mentions of AB3 to TaskWise.
  * `Add` feature process descriptions.
  * `Add` feature sequence and activity diagrams.
  * `Delete` feature process descriptions.
  * Edited the `Delete` feature's sequence diagram.
  * `Clear` feature process descriptions.
  * `Clear` feature sequence diagram.
  * Update user story and use cases for `add`, `delete`, and `clear`.
  * Propose Enhancement for `add` feature.

### Contributions To Team-based Tasks

* Created the UI mockup
* Added some issues on the GitHub Project Board for the group
* Updated UG on behalf of the team to the GitHub repo
* Contributed to demo video
* Checking of all documentation's links and grammar mistakes
* Clean up some test cases and its fields

### Contributions To Community

* Reviewed some PRs on GitHub
