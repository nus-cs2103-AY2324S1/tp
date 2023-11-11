---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# Class Manager 2023 Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

- The features `undo`, `redo`, `history` and arrow key command navigation (including the code) was reused with some changes from AddressBook-Level4.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-T11-1/tp/tree/master/src/main/java/seedu/classmanager/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-T11-1/tp/tree/master/src/main/java/seedu/classmanager/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete s/A0249112A`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="600" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-T11-1/tp/tree/master/src/main/java/seedu/classmanager/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-T11-1/tp/tree/master/src/main/java/seedu/classmanager/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-T11-1/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-T11-1/tp/tree/master/src/main/java/seedu/classmanager/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete s/A0249112A")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete s/A0249112A` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `ClassManagerParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `ClassManagerParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `ClassManagerParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-T11-1/tp/tree/master/src/main/java/seedu/classmanager/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" />


The `Model` component,

* stores Class Manager data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-T11-1/tp/tree/master/src/main/java/seedu/classmanager/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="650" />

The `Storage` component,
* can save both Class Manager data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `ClassManagerStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.classmanager.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Tag feature

#### Implementation

Tagging a student with `Tag` is facilitated by 3 commands:
* `TagCommand` will replace all existing tags of a student with input tags.
* `AddTagCommand` will add input tags to existing tags of the student.
* `DeleteTagCommand` will delete input tags from the existing tags of the student.

These commands will be executed based on the users input. The `TagCommandParser` will be responsible for parsing the user input and create the correct command object to execute.
Only 1 of the 3 commands will be executed per user input.

Here is an example step by step of how the 3 different commands might be executed.

Step 1. User inputs

        tag A0245234N t/teamleader

Step 2. `Logic` will receive the input and pass it to a `ClassManagerParser` object which in turn creates a `TagCommandParser` object to parse the command.

Step 3. Next `TagCommandParser` will check for any action identifiers,
`/add` or `/delete`, which will create a `AddTagCommand` object or `DeleteTagCommand` object respectively,
else a `TagCommand` object.

Step 4a. `AddTagCommand` will union the `HashSet<Tag>` with the student's existing `Tag`.

Step 4b. `DeleteTagCommand` will remove all `Tag` that are in the intersection of the student's existing `Tag` and `HashSet<Tag>`.

Step 4c. `TagCommand` will replace all existing `Tag` of the student with `HashSet<Tag>`.

Step 5. All 3 commands will create a new `Student` object with the new `Tag` and copy all other details.

Step 6. All 3 commands will update the `Model` with the new Student by calling `Model#setStudent()`.

The following activity diagram summarizes what happens when a user executes a tag command:

<puml src="diagrams/TagCommand.puml" alt="TagCommand" />

#### Design considerations:
**Aspect: TagCommand**
* **Alternative 1 (current choice):** Use different types of TagCommand to handle add and delete tags.
  * Pros: Able to handle add and delete of tags. Users do not have to retype tags that they want to keep.
  * Cons: Users have to input more details.
* **Alternative 2:** Replace all existing tags with input tags.
  * Pros: Easy to implement.
  * Cons: Users have to always replace the tag even if they want to keep it.

### Undo/redo feature

#### Implementation

The undo/redo feature works similarly to the one implemented in AddressBook-Level 4, but with support for more commands. The undo/redo mechanism is facilitated by `VersionedClassManager`. It extends `ClassManager` with an undo/redo history, stored internally as an `classManagerStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedClassManager#commit()` — Saves the current Class Manager state in its history.
* `VersionedClassManager#undo()` — Restores the previous Class Manager state from its history.
* `VersionedClassManager#redo()` — Restores a previously undone Class Manager state from its history.

These operations are exposed in the `Model` interface as `Model#commitClassManager()`, `Model#undoClassManager()` and `Model#redoClassManager()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedClassManager` will be initialized with the initial Class Manager state, and the `currentStatePointer` pointing to that single Class Manager state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete s/A0123456L` command to delete the student with the Student Number A0123456L in Class Manager. The `delete` command calls `Model#commitClassManager()`, causing the modified state of Class Manager after the `delete s/A0123456L` command executes to be saved in the `classManagerStateList`, and the `currentStatePointer` is shifted to the newly inserted Class Manager state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitClassManager()`, causing another modified Class Manager state to be saved into the `classManagerStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitClassManager()`, so Class Manager state will not be saved into the `classManagerStateList`.

</box>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoClassManager()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous Class Manager state, and restores Class Manager to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial ClassManager state, then there are no previous ClassManager states to restore. The `undo` command uses `Model#canundoClassManager()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoClassManager()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores Class Manager to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `classManagerStateList.size() - 1`, pointing to the latest Class Manager state, then there are no undone Class Manager states to restore. The `redo` command uses `Model#canRedoClassManager()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify Class Manager, such as `list`, will usually not call `Model#commitClassManager()`, `Model#undoClassManager()` or `Model#redoClassManager()`. Thus, the `classManagerStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitClassManager()`. Since the `currentStatePointer` is not pointing at the end of the `classManagerStateList`, all Class Manager states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire Class Manager.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
  * Cons: We must ensure that the implementation of each command is correct.

**Aspect: Data structure to support the undo/redo commands**

* **Alternative 1 (current choice):** Use a list to store the history of Class Manager states.
  * Pros: Easy to understand.
  * Cons: Logic is duplicated twice. For example, when a new command is executed, we must remember to update both `HistoryManager` and `VersionedClassManager`.

* **Alternative 2:** Use `HistoryManager` for undo/redo.
  * Pros: We do not need to maintain a separate list, and just reuse what is already in the codebase.
  * Cons: Requires dealing with commands that have already been undone: We must remember to skip these commands. Violates Single Responsibility Principle and Separation of Concerns as `HistoryManager` now needs to do two different things.

**Aspect: Commands that support undo & redo**

* **Alternative 1 (current choice):** Not supporting undo/redo for `load` and `config`
  * Pros: Ensures that Class Manager will not run into issues when undoing `load` for missing saved files. Enforces the immutability of tutorial and assignment count after `config` has been entered.
  * Cons: Unable to change tutorial and assignment count after `config` has been entered.
* **Alternative 2:** Supporting undo/redo for all commands.
  * Pros: Ensures that app is consistent with undo/redo and users will not be unsure if a certain command can be undone.
  * Cons: Can be confusing for the user to use undo/redo with `load`.

### Load feature

#### About this feature

The load feature allows users to load a saved JSON file into the app. Load allows data from the new JSON file to be displayed in Class Manager, while setting the new default save file to be the new JSON file. The status bar footer also updates to show the current file.

This feature is an improvement to the previous method of directly editing the `classmanager.json` file located in `[JAR file location]/data`. Users are now able to have multiple JSON files in `[JAR file location]/data` and choose which file is to be loaded into Class Manager. This allows TAs with multiple courses to have a JSON file for each course, and load the JSON file for the course they are currently teaching.

#### How it is implemented

<puml src="diagrams/LoadSequenceDiagram.puml" alt="LoadSequenceDiagram" />

The `load` command is facilitated by `LoadCommand` and `LoadCommandParser`. `LoadCommand` attempts to read the JSON file and calls `setClassManager` and `setClassManagerFilePath` of `Model` to update the new save file path and Class Manager data to be displayed.

#### Parsing user input

1. The user inputs the `load` command.
2. The `ClassManagerParser` processes the input and creates a new `LoadCommandParser`.
3. The `LoadCommandParser` then calls ArgumentTokenizer#tokenize(String argString, Prefix... prefixes) to extract the file name. If there are duplicate prefixes, a ParseException would be thrown.
4. The file name is then check to ensure that it is valid. If the file name is missing, null or contains a forward slash, a ParseException would be thrown.
5. The `LoadCommandParser` then creates the `LoadCommand` based on the processed input.

### Config feature

The config feature is mandatory for TAs to enter before using Class Manager. It allows TAs to set the number of tutorials and the number of assignments in a module. This allows Class Manager to be able to display the correct number of tutorials and assignments for the TA to enter the grades for each student.


### Class Details feature

#### Implementation

The proposed class details mechanism for each student will be facilitated by `ClassDetails`. It allows for the tracking
of an `Student`'s class details, such as their tutorial group, tutorial attendance, class participation, and assignment
grades. It will be stored as 3 separate classes to model each of the 3 different types of class details (We will
call them "class information"), and a tracker
class to act as the manager for each of the class information, with the trackers composing the `ClassDetails` class.

<puml src="diagrams/ClassInformation.puml" />

The 3 different types of class information are:

* `Attendance` - Stores the details for a student's attendance in a specific tutorial. Attendance will be stored as
a boolean value.
* `ClassParticipation` - Stores the details for a student's participation in a specific tutorial. Class participation
will be stored as a boolean value.
* `Assignment` - Stores the details for a student's assignment grades for a specific tutorial. Assignment grades will be
stored as an integer value, with the total marks standardized to 100 marks.

These components will be stored in their respective tracker classes, using Java Arrays to store the objects. The position
of the classes in the array will correspond to the index of the tutorial or assignment. For example, the first index of
the array will correspond to either the first tutorial or assignment, depending on the tracker class.

<puml src="diagrams/ClassDetails.puml" width="500" />

The tracker classes will be stored in the `ClassDetails` class, which will be composed of the following classes:
* `AttendanceTracker` - Stores the `Attendance` objects for a specific student.
* `ClassParticipationTracker` - Stores the `ClassParticipation` objects for a specific student.
* `AssignmentTracker` - Stores the `Assignment` objects for a specific student.

These tracker classes will inherit from a `tracker` *interface*. They will also support the following operations:
* `getPercentage()` - Returns the average grade of the student for the specific tracker class. For example, the average
tutorial attendance percentage, or the average assignment score.

Each of these tracker classes will be able to be initialized with a specific size, which will be the number of tutorials
or assignment grade.

#### Design considerations:

**Aspect: 'class information' classes**

* **Alternative 1 (current choice):** Use a class for each type of class information.
  * Pros: Easy to implement, follows OOP principle. If we want to edit the implementation of each of the classes or
  change the data structure / details of each instance, it can be easily done.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Store class values as a primitive type (String or Integer).
  * Pros: Will use less memory.
  * Cons: We must ensure that the implementation of each class is correct. Implementation will be more
  complicated as different class detail types will require different implementations for the same operation.

**Aspect: Tracker classes**

* **Alternative 1 (current choice):** Use a tracker class for each type of class information.
  * Pros: Easy to implement. Shared functions can be abstracted out and polymorphism can be applied.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Store class values as an Object Array.
  * Pros: Will use less memory.
  * Cons: Will need to implement different functions for each type of class details. Implementation will be more
  complicated. SLAP principle might not be able to be adhered to.

### Present feature

#### About this feature

The present feature allows users to mark a specific student to be present in a specific tutorial in the app.

This feature builds upon the current design of Student and ClassDetails.

#### How it is implemented

<puml src="diagrams/MarkPresentSequenceDiagram.puml" alt="MarkPresentSequenceDiagram" />

<box type="info" seamless>

**Note:** The diagram above only shows part of the interactions within the model component. The interactions within the logic component are similar to other commands.

</box>

#### Design considerations:

The feature should be implemented upon the current design of Student and ClassDetails. Alternative designs may exist, such as treating the attendance and participation as association classes.

<box type="info" seamless>

**Note:** Other similar features, such as `absent`, `class-part`, and `grade`, are implemented in a similar way.

</box>

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

* NUS Teaching Assistants
* has a need to manage student information across different classes
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

* provide fast access to student’s contact information
* provide easy ways to compare and visualise student's grades across classes
* optimised for users who prefer CLI

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`


| Priority | As a …​  | I want to …​                                                 | So that I can…​                                                         |
|----------|----------|--------------------------------------------------------------|-------------------------------------------------------------------------|
| `* * *`  | TA       | manually enter the details of students into my Class Manager | track the information                                                   |
| `* * *`  | TA       | be able to delete students from the class                    | manage students who drop out                                            |
| `* * *`  | TA       | edit the contact information of students                     | maintain the correct information if it changes                          |
| `* *`    | TA       | keep track of the attendance percentage of each student      | use it for their participation grade                                    |
| `* *`    | TA       | be able to add the grades of each student in each tutorial   | use it to view students that need help                                  |
| `*`      | TA       | be able to keep records of my students in past semesters     | refer to the history of student performance                             |
| `*`      | TA       | add comments to specific students                            | attach additional information to them                                   |
| `* * *`  | TA       | tag students with certain labels                             | filter and search more conveniently                                     |
| `* * *`  | TA       | lookup all students in a particular tutorial group           | obtain their contact information                                        |
| `* *`    | TA       | search particular students across different tutorial groups  | contact them easily                                                     |
| `*`      | TA       | know the students I searched recently                        | minimize repeated searches                                              |
| `* *`    | TA       | filter students by tags/labels                               | categorize and organize students                                        |
| `* *`    | TA       | filter students by their year of study                       | gain a better understanding of the whole tutorial                       |
| `*`      | TA       | have a composite filter for tutorial group attendance        | look up the attendance of a particular tutorial group on a certain week |
| `*`      | TA       | randomly select a specific number of students                | use as a feature during teaching                                        |
| `* *`    | TA       | sort students by coding experience                           | group students with similar experience levels                           |
| `* *`    | TA       | sort students based on their individual score marks          | understand the performance of the whole class                           |
| `* *`    | TA       | sort students by alphabetical order                          | compare to the attendance sheet                                         |
| `* *`    | TA       | sort students by their overall grades                        | identify students falling behind in my class                            |
| `* `     | TA       | customise my GUI                                             | use a theme that suits my desktop theme                                 |
| `* `     | TA       | enable dark mode for my device                               | use it at night                                                         |
| `* `     | TA       | choose different layouts                                     | select a comfortable layout                                             |
| `* *`    | TA       | have keyboard shortcuts for commonly used features           | save time and fit my habit                                              |
| `* *`    | TA       | customise commands                                           | save time and fit my habit                                              |
| `* * *`  | TA       | export and import app’s data                                 | work on different devices                                               |
| `* * *`  | TA       | save queries and searches to the application                 | not lose progress                                                       |
| `* * *`  | new user | see usage instructions                                       | refer to instructions when I forget how to use the App                  |

### Use cases

(For all use cases below, the **System** is the `Class Manager` and the **Actor** is the `user`, unless specified otherwise)

---
**Use case: Delete a student**

**MSS**

1.  User requests to delete a specific student with the student's student number
2.  Class Manager deletes the student

    Use case ends.

**Extensions**

* 1a. The given student number is invalid.

    * 1a1. Class Manager shows an error message.

      Use case resumes at step 1.

* 1b. The given student number does not exist in Class Manager.

    * 1b1. Class Manager shows an error message.

      Use case resumes at step 1.

---
**Use case: Tag a student with a label**

**MSS**

1.  User requests to list students
2.  Class Manager shows a list of students
3.  User requests to tag a specific student in the list
4.  Class Manager tags the student

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given student number is invalid.

    * 3a1. Class Manager shows an error message.

      Use case resumes at step 2.

* 3b. The student already has the given tag.

    * 3b1. Class Manager shows an error message.

      Use case resumes at step 2.

---
**Use case: Loading a saved file**

**MSS**

1.  User copies saved JSON file to data folder
2.  User requests to load JSON file
3.  Class Manager reads and loads the JSON file
4.  Class Manager updates the app to show the new data

    Use case ends.

**Extensions**

* 3a. The JSON file cannot be found.

  * 3a1. Class Manager shows an error message.

    Use case resumes at step 2.

* 4a. The JSON file data is invalid.

    * 4a1. Class Manager shows an error message.

      Use case resumes at step 3.

---
**Use case: Look up a list of students**

**MSS**

1.  User requests to look up students with a given criteria.
2.  Class Manager check each student in the list with the given criteria.
3.  Class Manager shows a list of students that match the criteria.

    Use case ends.

**Extensions**

* 1a. There are no criteria given.

    * 1a1. Class Manager shows an error message.

      Use case ends.

* 2a. There are no students in the list that match the criteria.

    * 2a1. Class Manager shows an error message.

      Use case ends.

---
**Use case: Randomly select a specific number of students**

**MSS**

1.  User requests to randomly select a specific number of students.
2.  Class Manager randomly selects the students from the list.
3.  Class Manager shows a list of students that are randomly selected.

    Use case ends.

**Extensions**

* 1a. The number of students to be selected is more than the number of students in the list.

    * 1a1. Class Manager shows an error message.

      Use case ends.

* 1b. The number of students to be selected is less than 1.

    * 1b1. Class Manager shows an error message.

      Use case ends.

---
**Use case: Modifying a student's class information**

**MSS**

1.  User requests to modify a student's class information.
2.  Class Manager modifies the student's class information.
3.  Class Manager shows the student's updated class information.

    Use case ends.

**Extensions**

* 1a. The student does not exist in Class Manager.

    * 1a1. Class Manager shows an error message.

      Use case ends.

* 1b. The modifying request is invalid.

    * 1b1. Class Manager shows an error message.

      Use case ends.

---
*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The Application should be secure (with password) as sensitive information is stored.
5.  The Application needs to have proper documentation and user guide so that users can understand how to use the application.
6.  The Application should allow users to create and customize their profiles, including preferences for shortcuts, views, and layouts, to enhance the user experience.
7.  The Application should be able to handle multiple windows at the same time.
8.  The Application should comply with the specific policies and regulations of the university regarding data storage, security, and access control.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Student Number**: Matriculation number of NUS student. It must begin with capital letter 'A', followed by any number of alphanumeric characters. It must not be blank.
* **Email**: Any valid email address, such as NUS email address (eXXXXXXX@u.nus.edu).
* **CLI**: Command Line Interface.
* **GUI**: Graphical User Interface.
* **JSON**: JavaScript Object Notation, a lightweight data-interchange format.
* **JAR**: Java Archive, a package file format typically used to aggregate many Java class files and associated metadata and resources (text, images, etc.) into one file to distribute application software or libraries on the Java platform.
* **Class details / Class information**: The grades, attendance and class participation details of a student in Class Manager.

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

   1. Double-click the jar file<br>
      Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.


### Deleting a student

1. Deleting a student from Class Manager.

   1. Test case: `delete s/STUDENT_NUMBER`<br>
      Expected: STUDENT_NUMBER is a valid Student Number that exists in the Class Manager. The student with STUDENT_NUMBER is deleted from the list. Details of the deleted student shown in the result display box.

2. Deleting a student that is not in Class Manager.

   1. Test case: `delete s/vnqvq1924`<br>
      Expected: No student is deleted. Student Number error details shown in the result display box.

3. Deleting a student with an invalid student number.

   1. Other incorrect delete commands to try: `delete`, `delete s/x`, `...` (where x is an invalid student number)<br>
      Expected: No student is deleted. Command format error details shown in the result display box.
   2. Test case: `delete`<br>
      Expected: No student is deleted. Command format error details shown in the result display box.


### Loading data files
###### Setup
- Move the JAR file to a fresh directory. 
- Run and close the app before starting this test. (This is to ensure a fresh `classmanager.json` and `preferences.json`)<br>
- Copy the sample data file `classmanager.json`. And paste 2 copies of it in the same directory as the `classmanager.json`. Rename the copies to `t1.json` and `t2.json`.
- Do not delete the data file `classmanager.json` as it will be used as the starting default file.

###### Test cases
1. Loading a valid data file
   - Enter: `load f/t1`<br>
        Expected: The data in `t1.json` is loaded into the app. The status bar on the bottom left changed to the new file path. 
        The list of students shown in the GUI is the same as the one in `classmanager.json`.
   <br><br>
2. Loading a corrupted data file
   - Open and edit `t2.json` with a text editor. Add some random text to the file or delete some text from the file.
   - Enter: `load f/t2`<br>
        Expected: The data in `t2.json` is not loaded into the app. The address bar on the bottom left is unchanged.
        File error details shown in the result display box.
    <br><br> 
3. Loading a missing data file
   - Enter: `load f/t3`<br>
       Expected: The status bar on the bottom left is unchanged. File error details shown in the result display box.

### Undo/redo

1. Undoing a command
    1. Test case: `clear` -> `undo`<br>
        Expected: The `clear` command is undone. The list of students shown in the GUI is the same as the one before the `clear` command.
    
    2. Test case: `add` -> `undo`<br>
        Expected: The `add` command is undone. The newly added student is removed from the list of students.

2. Redoing a command
    1. Test case: `clear` -> `undo` -> `redo`<br>
        Expected: The `clear` command is redone. The list of students shown in the GUI is empty.
   
    2.  Test case: `add` -> `add` -> `undo` -> `undo` -> `redo` (Add 2 students, and then 2 undo with 1 redo)<br>
        Expected: The first `add` command is redone. The first student is added back to the list of students.


### Launch with erroneous data files
###### Setup
- Move the JAR file to a fresh directory.
- Run and close the app before starting this test. (This is to ensure a fresh `classmanager.json` and `preferences.json`)<br>
- Copy the sample data file `classmanager.json`. And paste 2 copies of it in the same directory as the `classmanager.json`. Rename the copies to `corrupt.json` and `wrong.json`.

###### Test cases
1. Dealing with missing data files
    - Edit the `preferences.json` to have the entry: 
    ```
    "classManagerFilePath" : "data\\missing.json"
    ```
   (Ensure that there is **no** file named `missing.json`)
   - Launch the app<br>
      Expected: The app will be populated with sample students. The app will create a new data file when it is next closed.
      <br><br>
2. Dealing with corrupted data files
    - Edit the data file `corrupt.json`, by randomly add or delete lines of data. <br>
    - Edit the `preferences.json` to have the entry:
    ```
    "classManagerFilePath" : "data\\corrupt.json"
    ```
    - Launch the app<br>
      Expected: The app will launch with an empty student list. The app will create a new data file when it is next closed.
      <br><br>
3. Dealing with valid data file but with the wrong configuration
    - Edit the data file `wrong.json`. (Do not change anything) <br>
    - Edit the `preferences.json` to have the entries:
    ```
    "classManagerFilePath" : "data\\wrong.json",
    "tutorialCount" : 1,
    "assignmentCount" : 1,
    ```
   (Ensure that the `tutorialCount` and `assignmentCount` are **changed**)
    - Launch the app<br>
      Expected: The app will launch with an empty student list. The app will create a new data file when it is next closed.
      <br><br>

### Adding a student

1. Adding a new student to Class Manager.

   1. Test case: `add n/NAME s/STUDENT_NUMBER e/EMAIL`<br>
      Expected: The student with NAME, STUDENT_NUMBER and EMAIL is added to the list. Details of the added student shown in the result display box.
      <br><br>
   
2. Adding an already existing student to Class Manager.

   1. Test case: Student Number that is already present in the list <br>
      Expected: No student is added. Error details shown in the result display box.
      <br><br>
   
3. Adding a student without some required fields <br>
   1. Test Case: `add n/NAME s/STUDENT_NUMBER e/EMAIL`, `add n/NAME s/PHONE e/EMAIL`<br>
      Expected: No student is added. Error details shown in the result display box.

### Editing a student

1. Editing a student's details in the current students list.

   1. Test case: `edit STUDENT_NUMBER n/NAME`<br>
      Expected: The student with STUDENT_NUMBER is edited to have the new NAME. 
   2. Test case: `edit STUDENT_NUMBER s/NEW_STUDENT_NUMBER`<br>
      Expected: The student with STUDENT_NUMBER is edited to have the NEW_STUDENT_NUMBER.
      <br><br>
2. Editing a student's details where the student is not in the list (Invalid Student Number). 

   1. Test case: Edit command with Student Number that is not present in the list <br>
      Expected: No student is edited. Error details shown in the result display box.

### Adding a comment to a student

1. Adding a comment to a student in Class Manager.

   1. Test case: `comment s/STUDENT_NUMBER cm/COMMENT`<br>
      Expected: The student with STUDENT_NUMBER is edited to have the new COMMENT.
      <br><br>   
2. Adding a comment to a student where the student is not in Class Manager (Invalid Student Number). 

   1. Test case: Comment command with Student Number that is not present in the list <br>
      Expected: No student is edited. Error details shown in the result display box.
      <br><br>
   
3. Adding a comment to a student where the new comment is empty.

   1. Test case: `comment s/STUDENT_NUMBER cm/`<br>
      Expected: Student is edited to have an empty comment. 

### Tagging a student

1. Tagging an existing student in the current students list.

   1. Test case: `tag s/STUDENT_NUMBER t/TAG`<br>
      Expected: The student with STUDENT_NUMBER is tagged with the new TAG.
      <br><br>
2. Adding a new student with tags.

   1. Test case: `add n/NAME p/PHONE e/EMAIL s/STUDENT_NUMBER c/CLASS_NUMBER [t/TAG]...`<br>
      Expected: The student with NAME, STUDENT_NUMBER, EMAIL and TAG is added to the list. Details of the added student shown in the result display box.

### Listing students

1. Listing all students in Class Manager.

   1. Test case: `list`<br>
      Expected: The list of students is shown in the result display box.
      <br><br>

### Display help

1. Displaying help.

   1. Test case: `help`<br>
      Expected: The help window with list of command is shown.
      <br><br>

### Exiting the app

1. Exiting the app.

   1. Test case: `exit`<br>
      Expected: The app closes and all data is saved.
      <br><br>

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancements**

1. The current keywords are case-sensitive. We plan to make keywords not case-sensitive in the future iteration. For example, currently `add` is case-sensitive. We will accept keywords such as `Add` in the future.
2. Class Numbers are currently limited to tutorials that begin with T. We plan to allow Class Numbers to be any alphanumeric string in the future.
3. Clicking on a student in the student list will highlight the student. We plan to prevent this in the future as it affects the visibility of the student's contact details and visualised graphs.
