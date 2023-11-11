---
layout: default.md
title: "Richie's Project Portfolio Page"
---

### Project: TutorMate

TutorMate helps private tuition teachers manage their students more easily. It allows faster access and storage of students’ contacts and data, providing better time management and productivity. The app will help to manage students for a single tuition teacher only. 

Given below are my contributions to the project.

* **New Feature**: Added the ability to show the details of the selected student/lesson/task. (Pull request [#53](https://github.com/AY2324S1-CS2103T-T11-3/tp/pull/53), [#138](https://github.com/AY2324S1-CS2103T-T11-3/tp/pull/138))
  * What it does: allows the user to display the selected item in the show details panel at the right of the User Interface. It allows the user to show students while in the ___STUDENT list___, show lessons while in the ___SCHEDULE list___ and show tasks while in the ___TASK list___.
  * Justification: This feature improves the product significantly because this de-clutters the details in the list of students and lessons at the left of the User Interface. It allows the user to view the details of the selected item in a clearer and more detailed view.
  * Highlights: This enhancement affects the coupling between the Model and the UI classes. The current implementation requires the linking between them and required certain considerations as elaborated in the [Developer Guide](https://ay2324s1-cs2103t-t11-3.github.io/tp/DeveloperGuide.html).
  
* **New Feature**: Added the Task class and the structure of the TaskList.(Pull request [#91](https://github.com/AY2324S1-CS2103T-T11-3/tp/pull/91))
  * What it does: enables developers to implement additional functionality regarding the Task class. The Task List class enables developers to have a structure to contain the respective Task objects.
  * Justification: This structure improves the product significantly because this enables the Task List functionality of the Lessons. 

* **New Feature**: Added the Tasks List view. (Pull request [#138](https://github.com/AY2324S1-CS2103T-T11-3/tp/pull/138))
  * What it does: allows the user to display the Full Task List view in the left of the User Interface. It displays all the tasks of all the lessons currently in the TutorMate application.
  * Justification: This feature improves the product significantly because this allows users to view all the current tasks in the TutorMate application. This gives them a convenient view of the tasks that they need to take note of. The tasks are sorted accordingly in the order of the lessons (increasing order by date).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=richiehx&breakdown=true)

* **Enhancements implemented**:
    * Created the Show Detail Panel, Lesson Detail Panel and Task Detail Panel to display and show the details of the respective item specified.
    * Created the Full Task List panel to display all the tasks of all the lessons.

* **Contributions to User Guide**:
    * Added documentation for the `addPerson`, `show`, `delete` and `list tasks` features.
    * Added and updated the outdated documentation for other features.

* **Contributions to Developer Guide**:
    * Added implementation details of the `show` feature.
    * Added implementation details of the Task, TaskList and Full Task List structure and functionality.
    * Updated diagrams to include the Task, TaskList and FullTaskList classes.
    * Updated diagrams to include the show command classes.
    * Added target user, value proposition and user stories. 

* **Contributions to team-based tasks**:
    * tP Team Organization Setup
    * tP Team Repo Setup

* **Review/mentoring contributions**:
    * 42 PRs reviewed (with non-trivial review comments)
    * Reported bugs and suggestions for other teams


