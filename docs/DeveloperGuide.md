---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# TutorMate Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the [AddressBook-Level3 project](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above illustrates the high-level design of the App. 

You can see that the bulk of the app's works are done by the UI, Logic, Model and Storage components. Below are the general duties of each component and how they interact with each other.

#### Duties of each component
[**`UI`**](#ui-component) is responsible for
* Rendering visuals to the user.
* Handling and responding to user interactions.

[**`Logic`**](#logic-component) is responsible for 
* Interpreting the user command to find the corresponding strategies to execute.
* Parsing the user command to construct the corresponding parameters for the strategies.
* Executing the strategies to modify the data and state of the app.
* Updating the UI to reflect the changes in the data and state of the app.

[**`Model`**](#model-component) is responsible for
* Providing accurate representation of the current data and state of the app
* Providing APIs for other components to access and modify the data and state.

[**`Storage`**](#storage-component): is responsible for
* Loading the user data from the hard disk.
* Saving the user data to the hard disk.

#### How the architecture components interact with each other

[**`UI`**](#ui-component):
* Delegates the interpretation of user command to the `Logic` component. 
* Relies on the `Model` component to obtain the appropriate data to display.

[**`Logic`**](#logic-component):
* Informs `UI` what to display.
* Utilises `Model` to access and modify the current data and state <br>
* Instructs `Storage` to save data to the hard disk.

[**`Model`**](#model-component): 
* Provides APIs for `Logic` and `UI` to access and modify the data. 
* Depends on `Storage` to load data from the hard disk to initialise.

[**`Storage`**](#storage-component):
* Provides APIs for `Logic` to save data to the hard disk.
* Provides APIs for `Model` to load data from the hard disk to initialise.

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

#### Other notes

1. Each of the four main components (also shown in the diagram above)
   * Defines its *API* in an `interface` with the same name as the Component.
   * Implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point. 
   
   For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class.
2. The team has decided to force all components to interact via interfaces rather than the concrete classes as illustrated in the (partial) class diagram below (reason: to prevent outside component's being coupled to the implementation of a component).
   <puml src="diagrams/ComponentManagers.puml" width="300" />
   If you are to contribute to this project, please align with this group decision.
3. There are other components that are not shown in the diagram above. These components are: 
    * `Commons` component: contains classes that are used by multiple other components.
    * `Entry Point` classes: `Main` and `MainApp` classes that are in charge of the app launch and shut down.
4. Please be reminded that, despite similar naming, our architecture is not the [Model-View-Controller (MVC)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) architecture. The major difference is that there is not a clear "Controller" component in our architecture. Instead, the role of the "Controller" is played by all the main components (UI, Logic, Model and Storage) working together.

The sections below give more details of each component.

## Components

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.
* consists of 2 sides: the left side being the lists section and the right side being the details section.
* depends on the `State` of the app currently to show the appropriate list panels.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

At high level, Logic Component is responsible for making sense of the user inputs, and modify storage and ui accordingly.
It is like a controller that glue the other components together.

To be more specific, it interacts with ui component by taking the user input from it, and setting the ui display accordingly.
It also calls APIs (addPerson, deleteLesson for example) from model component to modify the data representation, and call APIs
from storage component to save the data to local storage each time the data is modified.

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.
<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

Inside the `Logic` component, there are 4 main components: LogicManager, AddressBookParser, ScheduleParser and Command.

The command class will do the actual modification of the data, on been executed by the LogicManager, and communicate with logic
manager its execution result via the CommandResult class. 

Parser classes are responsible for parsing the user input and create
the corresponding command object. 

AddressBookParser is responsible for parsing the user input for finding the corresponding parser and return the corresponding command object. 

And logicManager will do the actual execution of command, and update to ui and storage.
The sequence diagram below

Here's a (partial) class diagram of the `Logic` component:
<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="600" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the schedule data i.e., all `lesson` objects (which are contained in a `Schedule` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* stores the task data of all the lessons i.e., all `task` objects (which are contained in the `TaskList` objects of each `Lesson` object).
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components).
* links to the UI component to display the Show Details Panel in the UI (to reduce code complexity).
* stores a `State` object that represents the current state of the app. Currently, the only 3 possible states are `STUDENT`, `SCHEDULE` and `NONE`.

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data, user preference data, and schedule data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage`, `UserPrefStorage` and `ScheduleStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Entry point classes
**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

### Common classes

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components. They are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

## List feature

### Purpose

The list feature is the core feature that handles the current state of the app. Depending on what the user specifies to list out, the user interface will change accordingly to show the appropriate list panels.
It also handles displaying the relevant student details specified by the user when the student list is shown, in order to not overcrowd the interface.

#### Implementation

The list feature is facilitated by `ListCommand` which extends the abstract `Command` class. The `ListCommand` will change the current state of the Model (either `STUDENT`, `SCHEDULE` or `NONE`) when the `execute` method is called.
The `MainWindow` receives changes to the app's state and renders the required panels accordingly.
It also stores an array of display parameters which will specifies what student details are shown by the `Ui` component in the student list. The `LogicManager` will detect changes in display parameters and set the required displayed fields.


### Show feature

#### Implementation

The show feature is facilitated by `ShowCommand` which extends the abstract `Command` class. The `ShowCommand` will check the current state of the Model (either `STUDENT`, `SCHEDULE` or `NONE`) when the `execute` method is called and see whether it is currently showing a `STUDENT` list or a `SCHEDULE` list. 

Additionally, the `ModelManager` class implements the following operations for the show command:

`ModelManager#linkUi()` — Links the Ui of TutorMate to the Model to display the Show Panel
`ModelManager#showPerson()` — Shows the details of the specified person in the Ui.
`ModelManager#showLesson()` — Shows the details of the specified lesson in the Ui.

The Show Command has different behaviours based on the current state in the `Model`:
- The show Command will show the Person Details if the current state is `STUDENT`
- The show Command will show the Lesson Details if the current state is `SCHEDULE`

The `execute` method of `ShowCommand` will be called by the logicManager when the `show` command is input.

Given below is an example usage scenario and how the show feature behaves at each step.

Step 1. The user launches the application for the first time. The initial state of the Model will be set to `SCHEDULE`. The schedule list will be initialized with the initial schedule.

Step 2. The user executes show 5 command to show the details of the 5th lesson in the schedule list. The `execute` method of the `ShowCommand` will be called by the logicManager. The `execute` command will call the `showLesson` method in the ModelManager which displays the `LessonDetailListPanel` in the Ui.

Step 3. The user wants to display the details of a person and switches to the student list with the `list students` command in the Command Line Interface(CLI) Ui. The current list will display the students and the Model state will change to `STUDENT`.

Step 4. The user executes show 1 command to show the details of the 1st person in the student list. The `execute` method of the `ShowCommand` will be called by the logicManager. The `execute` command will call the `showPerson` method in the ModelManager which displays the `StudentDetailListPanel` in the Ui.

The following sequence diagram shows how the show operation works for showing a person from the `STUDENT` list:

<puml src="diagrams/ShowSequenceDiagram.puml" alt="ShowSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `ShowCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

#### Design considerations:

**Aspect: How the Show Command calls the Ui to display the Show Details Panel:**

* **Alternative 1 (current choice):** Link the Model with the Ui to display the Show Details Panel.
    * Pros: Easy to implement.
    * Cons: May increase coupling between the Model and Ui

* **Alternative 2:** Pass the Ui call from the Show Command around the currently linked files.
    * Pros: No additional coupling created.
    * Cons: Many files will have to be changed and will increase the complexity of the code.

### Task List Feature

#### Implementation

The task list functionality is facilitated by multiple related classes. The `TaskList` can contain any number of `Task` objects. The `TaskList` is implemented as a component of each Lesson so that each lesson will have its own individual Task List.

The `FullTaskList` class is implemented as a list to view all the collective tasks of all the lessons. It uses a similar structure to ScheduleList to display the list.

#### Design considerations:

**Aspect: How the Task List structure should be implemented:**

* **Alternative 1 (current choice):** Each individual Lesson will contain a Task List.
    * Pros: Easy to implement.
    * Cons: May be hard to increase navigability from students to lessons and tasks.

* **Alternative 2:** Create a central Task List and filter the tasks for each lesson.
    * Pros: May be easier to increase navigability.
    * Cons: May increase code complexity due to filtering the respective tasks.


### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Private Tuition Teachers
* has a need to manage a significant number of students
* Teaches multiple subjects
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Helps private tuition teachers manage their students more easily. Faster access and storage of students’ contacts and data than a typical mouse/GUI driven app, provides better time management and productivity. The app will help to manage students for a single tuition teacher only. The app will only provide simple student analytics (without detailed student progress analysis).



### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​          | I want to …​                                                            | So that I can…​                                                                    |
|----------|------------------|-------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| `* * *`  | new user         | see usage instructions                                                  | refer to instructions when I forget how to use the App                             |
| `* * *`  | new user         | purge all existing sample data                                          | get rid of the experimental data when exploring the app                            |
| `* * *`  | lazy tutor       | add students by name                                                    | include new students into our app                                                  |
| `* * *`  | user             | delete a person                                                         | remove entries that I no longer need                                               |
| `* * *`  | user             | find a student by name or subject                                       | locate details of persons without having to go through the entire list             |
| `* * *`  | tutor            | update my students details as they progress through learning in remarks | keep track of their information e.g. test scores                                   |
| `* * *`  | tutor            | quickly edit student details                                            | save time re-adding students if I have added the wrong details by accident         |
| `* * *`  | flexible tutor   | edit my student’s lesson timings                                        | accommodate any changes in timing requested by them                                |
| `* * *`  | private tutor    | install the app on my device with one click                             | use the app with ease without much trouble building the environment                |
| `* * *`  | private tutor    | see a specific student’s data from the contact list                     | get a more concise and detailed view of the student                                |
| `* *`    | user             | hide private contact details                                            | minimize chance of someone else seeing them by accident                            |
| `* *`    | efficient tutor  | add lessons to my schedule quickly                                      | maximize my time usage                                                             |
| `* *`    | tutor            | see my teaching schedule                                                | complete lesson preparation that is catered to the student before tuition sessions |                                                                                                                |                                                                                    |
| `* *`    | efficient tutor  | filter lessons to view lessons on a specific dates                      | plan my activities with the free time that is not occupied by lessons.             |
| `* *`    | tutor            | link students to a lesson                                               | prepare for the lesson according to the students' weaknesses and strengths.        |
| `*`      | forgetful tutor  | include certain tasks to do for each lesson                             | remember to do them in preparation for the tuition lesson                          |
| `*`      | regular app user | hide information that is not relevant to me currently                   | have a cleaner User Interface                                                      |



### Use cases

(For all use cases below, the **System** is `TutorMate` and the **Actor** is the `Tutor`, unless specified otherwise)

### Use case: Install the app [UC01]

**MSS**
1. User executes the installation package
2. The app asks to continue or cancel the installation
3. User responds by indicating continue
4. The app installs the app
5. The app informs the user that the installation is successful
Use Case ends

**Extensions**
* 3a. User responds by indicating cancel
  * 3a1. The app closes the installation package
  * Use case ends

* 4a. An error occurs during installation
  * 4a1. The app informs the user that the installation is unsuccessful and displays reasons
  * Use case ends

### Use case: Add a student [UC02]

**Preconditions**
1. User has installed the app

**MSS**
1. User chooses to add a new student.
2. User enters the required details to create a student.
3. TutorMate creates the student.

Use Case ends

**Extensions**
* 2a. Student's name is not specified and / or details specified are incorrect.
  * 2a1. The app informs the user of the error.
  * 2a2. The user enters new data.
  * Use case resumes from step 2.


### Use case: Delete a student [UC03]

**MSS**

1. User requests to list students.
2. TutorMate shows a list of persons.
3. User requests to delete a specific person in the list.
4. TutorMate deletes the person.

Use case ends.

**Extensions**

* 2a. The list is empty (No students in student list).
      Use case ends.
* 3a. The given index is invalid.
  * 3a1. TutorMate shows an error message. 
  Use case resumes at step 2.


### Use case: Show a student [UC04]

**MSS**

1. User requests to list students.
2. TutorMate shows a list of students.
3. User requests to see the details of a specific student.
4. TutorMate shows the student.

Use case ends.

**Extensions**

* 2a. The list is empty (No students found).
  Use case ends.
* 3a. The given index is invalid.
    * 3a1. TutorMate shows an error message.
      Use case resumes at step 2.


### Use case: Find & see a student with their details [UC05]

**MSS**
1. User requests to find a student by a specific characteristic e.g. Name.
2. TutorMate shows a list of students that matches the user's input.
3. User <u>shows a student (UC04)</u>

Use Case ends

**Extensions**

* 2a. The list is empty (No students found that matches the user's input).
  <br> Use case ends.


### Use case: Edit a student's details [UC06]

**MSS**
1. User requests to list students.
2. TutorMate shows the student list.
3. User chooses to edit a student and enters the data.
4. TutorMate edits the student's details.

Use Case ends

**Extensions**

* 3a. Some details are incorrect/ overlapping with existing app data.
    * 3a1. The app informs the user of the error.
    * 3a2. The user enters new data.
    * Use case resumes from step 3.
* 3b. The given index is invalid.
    * 3b1. The app informs the user of the error.
    * 3b2. The user enters new data.
    * Use case resumes from step 3.

### Use case: Add a lesson [UC07]

**MSS**
1. User requests to list schedule.
2. TutorMate shows the schedule list.
3. User enters the required details to create a lesson.
4. TutorMate creates the lesson.

Use Case ends

**Extensions**

* 3a. Lesson's name is not specified and / or details specified are incorrect.
    * 3a1. The app informs the user of the error.
    * 3a2. The user enters new data.
    * Use case resumes from step 3.

### Use case: Add a task to a lesson [UC08]

**MSS**
1. User requests to list schedule.
2. TutorMate shows the schedule list.
3. User enters the required details to create a task to a lesson.
4. TutorMate creates the task in the task list of the lesson.

Use Case ends

**Extensions**

* 2a. The list is empty (No lessons in schedule list).
    Use case ends.
* 3a. Description details are missing/ overlapping with existing app data.
    * 3a1. The app informs the user of the error.
    * 3a2. The user enters new data.
    * Use case resumes from step 3.
* 3b. The given index is invalid.
    * 3b1. The app informs the user of the error.
    * 3b2. The user enters new data.
    * Use case resumes from step 3.

### Use case: Delete a task to a lesson [UC09]

**MSS**
1. User requests to list schedule.
2. TutorMate shows the schedule list.
3. User requests to see the details of a lesson.
4. TutorMate shows the lesson.
5. User requests to delete a specific task in the lesson.
6. TutorMate deletes the task.

Use Case ends.

**Extensions**

* 2a. The list is empty (No lessons in schedule list).
  Use case ends.
* 3a. The given lesson index is invalid.
    * 3a1. The app informs the user of the error.
    * 3a2. The user enters new data.
    * Use case resumes from step 3.
* 5a. The given task index is invalid.
    * 3a1. The app informs the user of the error.
    * 3a2. The user enters new data.
    * Use case resumes from step 5.


### Non-Functional Requirements

1.  Should work on any Windows, Linux, and MacOs as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable(> 1 second) sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The application should respond and load all data within three seconds.
5.  The response time for adding, updating, or deleting student records should be within two seconds.
6.  The system should be usable with a user interface that is intuitive enough for users who have not used similar applications before.
7.  Users will only have access to their own students' data.
8.  Should there be an irregular termination of the app, the data should not be corrupted.
9.  The app should respond to user text input within 0.2 second.




### Glossary

* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files
    1. If a missing file is detected, the system will automatically create a .json file.
   2.  If a corrupted file is detected (i.e. missing any key properties), the system will delete the corrupted file, and replace it with a new one.
   3. The .json file will be populated with sample content loaded from `SampleDataUtil.java`.

2. Saving
   1. Data is written to the file after every command.


1. Test cases
