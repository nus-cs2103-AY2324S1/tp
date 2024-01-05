---
layout: default.md
title: "Rayson Yeap's Project Portfolio Page"
---

### Project: F.A.K.E.J.A.R.V.I.S.

F.A.K.E.J.A.R.V.I.S. is a desktop application to help CS1101S Avengers manage their time and job more effectively by creating a one-stop platform that aggregates information from multiple sources. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 27 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add new tasks.
    * What it does: enables users to create and input new tasks into the platform, allowing for easy tracking and organisation of the Avenger's to-do lists.
    * Justification: This feature improves the product significantly because it provides Avengers with a centralised task management system, eliminating the need to switch between multiple task management tools. This helps to streamline workflows and save time. Additionally, this reinforces the platform's vision to be a comprehensive, all-in-one solution for Avengers and their responsibilities.
    * Highlights: This enhancement was designed to accommodate future implementations of new fields. A thorough analysis was done to ensure its versatility. There was also some design thought given to how we were going to store the model. The model was chosen to be stored separately so that the corruption of the data in the address book would not affect the data in the task list.
    * Credits: No reuse of code or third-party libraries.


* **New Feature**: Added the ability to delete existing tasks.
    * What it does: enables users to remove unwanted or completed tasks from their task list.
    * Justification: This feature improves the product significantly because it provides Avengers with a way to maintain their task list. Otherwise, the list would just get longer and longer. Avengers might need to remove tasks that are no longer relevant or have been completed already. This maintains the organised and efficient task management process.
    * Credits: No reuse of code or third-party libraries.


* **New Feature**: Added the ability to view tasks.
    * What it does: enables users to access and filter their existing tasks within the application, providing a comprehensive overview of their to-do list.
    * Justification: This feature is a significant enhancement to the product, as it addresses a fundamental user need – the ability to effortlessly access and assess their tasks within the platform. Users need a convenient way to see their tasks and their details, enabling them to stay organized and make informed decisions about task prioritization and completion.
    * Highlights: This enhancement needs to be able to filter the tasks based on every single field. This implementation was challenging as it required coming up with a way to make one command general enough to use any predicate. This is where Java generics came in handy, namely the Predicate<Task>. By accepting a generic predicate, we can use multiple different predicates that apply to different fields of the tasks. And all this can be done in one command.
    * Credits: No reuse of code or third-party libraries.


* **Code contributed**: [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=respirayson&breakdown=true)


* **Project management**:
    * Managed release `v1.2`
    * Helped to set up the organisation and repository
    * Reviewed and approved pull requests
    * Set up the GitHub issues and different types
    * Updated the `README.md` ([#25](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/25))
    * Maintained team [weekly meeting notes](https://docs.google.com/document/d/1QKJGL88ciMlyb5MfS2XyThnuUBGdwFIULFgP9MLTFEA/edit)


* **Enhancements to existing features**:
    * Fixed the GUI resizing issue ([#100](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/100))
    * Updated the `clear` function to delete all data from the application. ([#259](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/259))
    * Updated existing tests in `ModelManager` and `StorageManager` to accommodate the `Task` functionalities ([#44](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/44))


* **Documentation**:
    * User Guide:
        * Added documentation for the Task Management Section and Tab feature ([#86](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/86), [#163](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/163))
        * Added the initial table of contents ([#100](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/100))
        * Did up the Command Format section, how to use the user guide and the errors table ([#86](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/86))
        * Expanded on the command summary with some examples
        * Formatted the features of the user guide section by changing the command parameters to a table, adding some notes to each command and reorganising the example block ([#161](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/161), [#151](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/151))
        * Added the encountering errors section ([#219](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/219))
        * Fixed bugs located after the Practical dry run ([#214](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/214))
        * Updated the User Guide to be more user-centric ([#238](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/238))
        * Fixed a bug where the user guide was unable to render markdown within the div elements ([#152](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/152))
        * Took screenshots of the outputs and formatted the entire user guide by adding page breaks in relevant locations ([#309](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/309))
        * Fixed general bugs related to the User Guide ([#295](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/295))

    * Developer Guide:
        * Added implementation details of the `addtask`, `deletetask`, `viewtasks` and `updatetaskprogress` features. ([#128](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/128))
        * Designed the class diagram for `Task`. ([#107](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/107))
        * Designed the sequence diagram for `CommandParser` and the reference frames for `AddTaskCommand`, `DeleteTaskCommand`, `ViewTasksCommand` and `UpdateTaskProgressCommand`. ([#107](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/107))
        * Added UML activity diagram for `DeleteCommand`. ([#252](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/252))
        * Added UML sequence diagram for `EditCommand`. ([#252](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/252))
        * Updated the common sections like the `Model` inherited from AB3. ([#252](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/252))
        * Added some non-functional requirements and use cases ([#47](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/47), [#31](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/31))
        * Updated the Manual Testing section in the appendix, Model and Storage class diagrams ([#311](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/311))


* Some examples of UML diagrams:
    * Activity diagram: ![Delete Activity Diagram](../images/DeleteStudentActivityDiagram.png)
    * Sequence diagram: ![Edit Sequence Diagram](../images/EditStudentSequenceDiagram.png)


* **Review/mentoring contributions**:
    * Contributed to forum discussions which helped to solve some of their issues (examples: [1](https://github.com/nus-cs2103-AY2324S1/forum/issues/50#issuecomment-1697613064), [2](https://github.com/nus-cs2103-AY2324S1/forum/issues/68#issuecomment-1702871519), [3](https://github.com/nus-cs2103-AY2324S1/forum/issues/146#issuecomment-1720759103), [4](https://github.com/nus-cs2103-AY2324S1/forum/issues/285#issuecomment-1774793066))
    * PRs reviewed (with non-trivial review comments): [#50](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/50), [#72](https://github.com/AY2324S1-CS2103T-T15-1/tp/pull/72)
    * All code reviews available [here](https://github.com/AY2324S1-CS2103T-T15-1/tp/pulls?q=is%3Apr+reviewed-by%3ARespirayson)


* **Tools**:
    * Java
    * PlantUML
    * FXML
    * Git
    * CSS
    * Markdown
    * MarkBind


* **Contributions beyond the project team**:
    * Helped to identify 13 bugs during the practical dry run, which improved the quality of their product. PED-Repo available [here](https://github.com/Respirayson/ped)
