---
  layout: page
  title: Developer Guide
  pageNav: 3
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## **Acknowledgements**

* [AB-3](https://nus-cs2103-ay2324s1.github.io/tp/)
* [Waddle](https://ay2223s1-cs2103t-w11-4.github.io/tp/)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [`diagrams`](https://github.com/AY2324S1-CS2103T-W15-4/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.
Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/MainApp.java)
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of five components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.
* [**`StageManager`**](#stageManager-component): Keeps track of the App's current stage.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

StageManager (shown in the diagram above) is accessible by the Logic component for checking and updating the app's current stage.

Each of the other four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface.

Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component),
as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

<div style="page-break-after: always"></div>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Interactions Inside the Ui Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `CourseListPanel`, `CombinedPanel`, `StatusBarFooter` etc.
All these, including the `MainWindow`, inherit from the abstract `UiComponent` class which captures the commonalities between classes that represent parts of the visible GUI.

The `MainWindow` includes a `DisplayPanel`, which has three different states it can toggle between
1. The `SplashPanel` for the opening splash window
2. The `CombinedPanel` that displays the student list and a course list sidebar
3. The `CoursePanel` that displays the course list (this is otherwise known as the `home` screen)

The `UI` component uses the JavaFx UI framework.
The layout of these UI components are defined in matching `.fxml` files that are in the `src/main/resources/view` folder.
For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,
* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the `UI` can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute the commands.
* keeps a reference to the `StageManager` to update the `UI` with its corresponding display panel.
* depends on some classes in the `Model` component, as it displays `Course` and `Student` objects residing in the `Model`.
* depends on some classes in the `Storage` component, as it updates to and retrieves user inputs from the `Storage`.

The typical flow of a user's interaction with the UI is as follows.

![Standard action flow of Ui Component](images/UiActivityDiagram.png)

During startup, the MainWindow will be displayed and all components loaded into it, with a splash screen showing before
entering the course list display.

For user commands, there are two unique commands which cause separate actions to be performed:
* `exit`, which immediately closes the application.
* `help`, which displays the help window pop-up.

After each command (assuming the application is not closed), the UI will check with the StageManager to see which stage it is set to,
(either StageManager.HOME or StageManager.SELECTED_COURSE) and displays the corresponding panel
(CourseListPanel for `HOME` and CombinedPanel for `SELECTED_COURSE`).

This action flow will loop until the user decides to exit the application.
During the application's runtime, the user may  also exit the application through the MainWindow's top panel buttons (File -> Exit)
or the red '**x**' button on the top right of the application screen.

<div style="page-break-after: always"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram bellow ilustrates the interactions within the `Logic` componenet, using `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteCourseSequenceDiagram.png)

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `CodeSphereParser` object to parse the user command. This in turns creates a parser that matches the command (e.g., `DeleteCourseCommandParser`) which will parse the relevant arguments in the user input.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add an item).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCourseCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `CodeSphereParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `CodeSphereParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.
* All `XYZCommandParser` classes throw a ParseException if there are any errors with the arguments.

<div style="page-break-after: always"></div>

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

The `Model` component,

* stores the app data i.e., all `Course` and `Student` objects (which are contained in a `UniqueCourseList` and `UniqueStudentList` objects respectively).
* stores the currently 'selected' `Course` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Course>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores the currently 'selected course's' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own).

<div style="page-break-after: always"></div>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-W15-4/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)
![Interactions Inside the Storage Component](images/StorageClassDiagram.png)

The `Storage` component,
* can save both CourseList data and user preference data in json format, and read them back into corresponding objects.
* can save user inputs and retrieve them in the future
* inherits from both `CourseListStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

The Storage component builds upon the AB-3 Storage component by adding an InputStorage, which encapsulates the concept of
storing user inputs.
These user inputs are stored in chronological order and are accessed through the StorageManager.
All user inputs will be stored in the InputStorage, and will also contain the data whether the input was accepted as a
valid command or not. Handling of this input validity will be done by the UI component.

<div style="page-break-after: always"></div>

### Common classes

Classes used by multiple components are in the `seedu.codesphere.commons` package.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always"></div>

## **Implementation of Home Commands**

This section describes some noteworthy details on how certain features on the Home Page are implemented.

### Add a course
#### About the add course feature
The `add` command on the home page allows users to add a course/class that they teach as a TA. For example,
a user can use `add CS2103T` to add the course CS2103T onto the home page for their management.

#### Implementation Details
The `add` command here is supported by the `AddCourseCommand` and `AddCourseCommandParser`. Users are able to add a
non-duplicate course to the existing course list.

#### Parsing user input
1. The user inputs `add` and provides a `c/COURSENAME` that they teach.
2. The `CodeSphereParser` then does preliminary processing to the user input and creates a new `AddCourseCommandParser`.
3. The `AddCourseCommandParser` parses the user input and checks if the prefix `c/` is present and there is only one such prefix present. If not, a `ParseException` is thrown.
4. `AddCourseCommandParser` will then call `ParserUtil#parseCourseName()` to verify if the input `COURSENAME` is valid. If invalid, a `ParseException` is thrown.
5. An `AddCourseCommand` will then be created with the respective input.

#### Command Execution
1. The `LogicManager` executes the `AddCourseCommand`.
2. The `AddCourseCommand` calls `Model#hasCourse()` to check for duplicate courses. If duplicate present, a `CommandException` is thrown.
3. `Model#addCourse()` will then be called to add the input course to the course list.

#### Displaying of result
1. Lastly, the `AddCourseCommand` creates a `CommandResult` with a success message and return it to the `LogicManager` to complete the command execution.
   The GUI will also be updated accordingly as it calls the `filteredCourseList` which was updated during the execution of the command.

The following sequence diagram shows how the `add` mechanism in the home page works:
![AddCourseSequenceDiagram](images/AddCourseSequenceDiagram.png)

The following activity diagram summaries what happens when a user executes the `add` command:
![AddCourseActivityDiagram](images/AddCourseActivityDiagram.png)

<div style="page-break-after: always"></div>

### Edit a course
#### About the edit course feature
The `edit` command on the home page allows users to edit the course name a course/class that they teach as a TA. For example,
a user can use `edit 1 c/CS2103T` to edit the course at the first index of the displayed list to have course name CS2103T.

#### Implementation Details
The `add` command here is supported by the `EditCourseCommand` and `EditCourseCommandParser`. Users are able to edit
a course as long as the new course name is not a duplicate in the existing course list.

#### Parsing user input
1. The user inputs `edit` and provides the `INDEX` of the course to edit, and the `c/NEW_COURSENAME` of the existing course.
2. The `CodeSphereParser` then does preliminary processing to the user input and creates a new `EditCourseCommandParser`.
3. The `EditCourseCommandParesr` parses the user input and checks if the input `INDEX` is valid by calling `ParserUtil#parseIndex()`.
4. `EditCourseCommandParesr` then checks if the prefix `c/` is present and there is only one such prefix present. If not, a `ParseException` will be thrown.
5. `EditCourseCommandParser` will then call `ParserUtil#parseCourseName` to verify if the input `NEW_COURSENAME` is valid. If invalid, a `ParseException` will be thrown.
6. An `EditCourseDescriptor` is then created with the `NEW_COURSENAME`.
7. A check will be done to see if there was a change in the course name.
8. An `EditCourseCommand` is created with the index and `EditCourseDescriptor`.

#### Command Execution
1. The `LogicManager` executes the `EditCourseCommand`.
2. The `EditCourseCommand` creates an `editedCourse` with the new course name.
3. `EditCourseCommand` then calls `Model#hasCourse()` to check for duplicate courses. If duplicate present, a `CommandException` is thrown.
4. `Model#setCourse()` is called to replace the target course with `editedCourse`.
5. `Model#updateFilteredCourseList()` is called to update the filtered list.

#### Displaying of result
1. Lastly, the `EditCourseCommand` creates a `CommandResult` with a success message and return it to the `LogicManager` to complete the command execution.
   The GUI will also be updated accordingly as it calls the `filteredCourseList` which was updated during the execution of the command.

The following sequence diagram shows how the `edit` mechanism in the home page works:
![EditCourseSequenceDiagram](images/EditCourseSequenceDiagram.png)

The following activity diagram summarises what happens when a user executes the `edit` command:
![EditCourseActivityDiagram](images/EditCourseActivityDiagram.png)

<div style="page-break-after: always"></div>

### Delete a course
#### About the delete course feature
The `delete` command on the home page allows users to delete an existing course in the displayed course list. For example,
a user can use `delete 1` to delete the course at the first index of the displayed list.

#### Implementation Details
The `delete` command here is supported by the `DeleteCourseCommand` and `DeleteCourseCommandParser`. Users are able to delete
a course as long as the index is valid.

#### Parsing user input
1. The user inputs `delete` and provides the `INDEX` of the existing course to delete.
2. The `CodeSphereParser` then does preliminary processing to the user input and creates a new `DeleteCourseCommandParser`.
3. The `DeleteCourseCommandParser` parses the user input and checks if the input `INDEX` is valid by calling `ParserUtil#parseIndex()`. If invalid, a `ParseException` is thrown.
4. A `DeleteCourseCommand` is then created with the respective index.

#### Command Execution
1. The `LogicManager` executes the `DeleteCourseCommand`.
2. The `DeleteCourseCommand` calls `Model#getFilteredCourseList()` to get an unmodifiable view of the filtered course list to determine the course to delete based on the input INDEX.
3. `Model#deleteCourse()` will then be called to delete the target course from the displayed course list.
4. `Model#resetFilteredCourseList()` will then be called to update the filtered course list.

<div style="page-break-after: always"></div>

#### Displaying of result
1. Lastly, the `DeleteCourseCommand` creates a `CommandResult` with a success message and return it to the `LogicManager` to complete the command execution.
   The GUI will also be updated accordingly as it calls the `filteredCourseList` which was updated during the execution of the command.

The following sequence diagram shows how the `delete` mechanism in the home page works:
![DeleteCourseSequenceDiagram](images/DeleteCourseSequenceDiagram.png)

The following activity diagram summarises what happens when a user executes the `delete` command:
![DeleteCourseActivityDiagram](images/DeleteCourseActivityDiagram.png)


<div style="page-break-after: always"></div>

## **Implementation of Course Commands**

### Sort student list for a selected course feature

#### About the sort student list for a selected course feature

The sort student list feature allows users to sort the student list for a selected course in the order specified as the `SORT_CRITERIA`, which can either be `NAME` or `TAG`.

#### Implementation details

The sort mechanism is facilitated mainly by the `SortCommand`, `SortCommandParser` and `SortCriteria`.

#### Parsing user input
1. The user inputs the `sort` command and specifies the sort criteria after the sort command prefix, in the format of `sort s/SORT_CRITERIA` (eg. `sort s/tag` or `sort s/name).
2. The `CodeSphereParser` then does preliminary processing to the user input and creates a new `SortCommandParser`.
3. The `SortCommandParser` then parses the user input and first checks whether the sort prefix `/s` is present. It also checks that there are no prefix duplicates.
   At this stage, if the sort prefix is not present or if the input contains multiple sort prefixes, `ParseException` would be thrown.
4. Next, `ParsetUtil#parseSortCriteria()` is called to check the validity of the input `SORT_CRITERIA` input.
   At this stage, `ParseException` would be thrown if the `SORT_CRITERIA` specified is invalid.
5. The `SortCommandParser` then creates the `SortCommand` based on the processed inputs.

#### Command execution
1. The `LogicManager` executes the `SortCommand`.
2. The `SortCommand` object’s `execute()` method is called. `SortCommand` first gets the singleton instance of `StageManager` through `StageManager#getInstance()`, then gets the current selected `Course` object through `StageManager#getSelectedCourse()`.
3. Finally, `SortCommand` calls `Course#sortStudentsBy()` to sort the student list based on the `SORT_CRITERIA` specified, and updates the displayed student list accordingly.

#### Displaying of result
1. `SortCommand` creates a `CommandResult` with a success message and returns it to the `LogicManager` to complete the command execution.
2. The GUI is updated following the changes in the student list and the display of the student list is updated accordingly.

Below is a sequence diagram that shows how the `sort` mechanism works:
![SortCommandSequenceDiagram](images/SortCommandSequenceDiagram.png)

The following activity diagram summaries what happens when a user executes the `sort` command:
![SortCommandActivityDiagram](images/SortCommandActivityDiagram.png)

#### Design considerations

**Aspect: Command syntax**

- **Alternative 1 (current choice):** `sort s/SORT_CRITERIA`.
    - **Pros:** Simple command with minimal fields, where only a single sort prefix is needed.
    - **Cons:** Users do not have a choice for sorting by ascending or descending order. When the `SORT_CRITERIA` is `NAME`, student names are displayed in ascending order, with names starting with ‘A’ at the top of the list. For `TAG`, students with performance tag `GOOD` are at the top of the list.

- **Alternative 2:** `sort s/SORT_CRITERIA o/SORT_ORDER`.
    - **Pros:** Provides users with more options which allows for greater flexibility.
    - **Cons:** Users are required to use a second `SORT_ORDER` prefix `/o` in addition to the `SORT_CRITERIA` prefix `s/`, which can slow down the use of the command.

- **Alternative 3:** `sort s/SORT_CRITERIA [o/desc]`, where `[o/desc]` is optional.
    - **Pros:** Retains the flexibility provided by Alternative 2, but also the conciseness of Alternative 1 should they wish to leave the `SORT_ORDER` empty.
    - **Cons:** Users who are not aware of the optional `[o/desc]` field may not use it.

**Aspect: Which fields should be considered as a valid sort criteria**

- **Alternative 1 (current choice):** `NAME` and `TAG` are the only two valid criteria.
    - **Pros:** Users can easily remember the valid criteria as there are only two options. `NAME` and `TAG` are also the two criteria that are likely to be the most useful criteria to sort students by.
    - **Cons:** Users do not have the choice to sort by other criteria.

- **Alternative 2:** All student fields are considered to be a valid.
    - **Pros:** Users have the ability to sort by any field.
    - **Cons:** Allowing sorting by the other student fields add very little value to the users. Sorting by `NAME` can be useful for administrative purposes, while sorting by `TAG` can be useful in helping the user identify the group of weaker students. However, it is unlikely that users will need to see the student list sorted by`EMAIL`, `PENDING_QUESTION` or `REMARK` fields.

<div style="page-break-after: always"></div>

### Add a pending question to a student from a selected course

#### About the adding a pending question feature

The `pq` command allows the user to add a pending question to a student based on the index input from the user.
For example, a user could add a pending question such as `Tutorial 1 Question 10` to the second student from the list by using `pq 2 pq/Tutorial 1 Question 10`.
It's important to note that adding a pending question to a student is not cumulative. In other words, adding another pending question to a student with an existing pending question will replace the old pending question with the new one.

#### Implementation Details
The `pq` command here is supported by the `PendingQuestionCommand` and `PendingQuestionCommandParser`. Users are able to add
a pending question to a student as long as the index provided is valid.

#### Parsing user input
1. The user inputs the `pq` command, provides the index of the targeted student, and follows it by the pending question using the prefix `pq`.
2. The `CodeSphereParser` then does preliminary processing to the user input and creates a new `PendingQuestionCommandParser`.
3. The `PendingQuestionCommandParser` parses the user input and checks whether the input is the correct format. For example, the index must be valid and the input after `pq` cannot be empty.
<br>
If the conditions are not met, a `ParseException` is thrown.

#### Command execution
1. The `LogicManager` executes the `PendingQuestionCommand`.
2. `StageManager` is used to retrieve the current `Course` by calling `StageManager#getCurrentCourse()`.
3. The `PendingQuestionCommand` calls the `Course#getFilteredStudentList()` to obtain a list of `Student` belonging to the course object and identify the targeted `Student` from the list.
4. A check for index from the user input will be done.If the index is invalid, a `CommandException` will be thrown.
5. A new `Student` object is created by passing in the `PendingQuestion` instance to the Student constructor.
6. Update the newly created Student instance to replace the old Student instance.

#### Displaying of result
1. The `PendingQuestionCommand` will create a `CommandResult` with a success message and return it to the LogicManager to complete the command execution. The GUI will also be updated accordingly as it calls the `filteredStudentList` which was updated during the execution of the command.

The following sequence diagram shows how the `pq` mechanism works:
![PqSequenceDiagram](images/PendingQuestionSequenceDiagram.png)

The following activity diagram summarises what happens when a user executes the `pq` command:
![PqActivityDiagram](images/PendingQuestionCommandActivityDiagram.png)

<div style="page-break-after: always"></div>

### Finding a student from a selected course

#### About the find feature
The `find` command allows users to search for relevant students based on details of the specified criteria in their input. For example, a user could use `find n/KEYWORDS` to find a students names which contain or match that with the keywords.
They can use keywords in `NAME`, `EMAIL`, `TAG`, `REMARK` to find students with words or phrases containing all of the words mentioned in keywords.
Do note that only 1 criterion can be used at one time.

#### Implementation Details
The partial class diagram of the `find` command can be seen. What is important to note here is the use of a `Predicate` class and the `updateFilteredStudentList` method which utilises the predicate class.

There are multiple different predicate classes created to accurately filter through the student list. Below, is the example usage scenario and how the `find` mechanism behaves at each step.

#### Parsing user input
1. The user inputs the `find` command and provide the input with the attribute of the student contact and its respective prefix (eg. `n/NAME` or `r/REMARK`) in which the user wants to find the student.
2. The `CodeSphereParser` then does preliminary processing to the user input and creates a new `FindCommandParser`.
3. The `FindCommandParser` parses the user inputs and checks for whether the input is the correct format. The input needs to contain only 1 prefix and the keywords cannot be empty.
If the conditions are not met a `ParseException` is thrown.
4. If the format is valid, a predicate class matching the prefix is created. For example, for `NAME`, the predicate object created is `NameContainsKeywordsPredicate`. This class takes in the values of the keywords and the object is passed into the newly created `FindCommand` object.

<div style="page-break-after: always"></div>

#### Command execution
1. The `LogicManager` executes the `FindCommand`.
2. The `FindCommand` calls the `Model#updateFilteredStudentList()` to update the filtered student list based on predicate class.
3. The `FindCommand` then calls the `Model#getFilteredStudentList()#size()` to get the size of the student list. The size will correspond to the number of students listed.

#### Displaying of result
1. The `FindCommand` will create a `CommandResult` with a success message and return it to the `LogicManager` to complete the command execution. The GUI will also be updated accordingly as it calls the `filteredStudentList` which was updated during the execution of the command.

The following sequence diagram shows how the `find` mechanism works:

![FindCommandSequenceDiagram](images/FindCommandSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes the `pq` command:

![FindCommandActivityDiagram](images/FindCommandActivityDiagram.png)

#### Design considerations

**Aspect: How should multiple keywords be considered**

- **Alternative 1 (current choice):** Whether all keywords are contained in any order in the specified criteria
  - **Pros:** Provides a more specific and refined search.
  - **Cons:** May result in fewer matches if all keywords are not present.
- **Alternative 2:** At least 1 keyword is contained
  - **Pros:** Increases the likelihood of finding matches.
  - **Cons:** Might lead to less precise results if only one keyword is present.

**Aspect: How should the filtered list be filtered based on the keywords**

- **Alternative 1 (current choice):** As long as keywords are contained inside
  - **Pros:** More flexible and tolerant to variations in keyword placement.
  - **Cons:** May include irrelevant matches if keywords are part of larger words.
- **Alternative 2:** Instead of contains, do a complete word match
  - **Pros:** Provides more precise matching by requiring complete word matches.
  - **Cons:** May exclude relevant matches if keywords are not complete words.

These considerations and alternatives should be weighed based on the specific requirements and expected user experience in your application.
You can copy and paste this code into a Markdown editor or file to render the formatted text.


<div style="page-break-after: always"></div>

## **Implementation of other notable features**

### Input History

#### About the input history feature
CodeSphere allows all the user's previously entered text (referred to as 'input') to be saved and retrieved at a later date.
Retrieval of this input history is through the 'UP' and 'DOWN' keys on the keyboard.

#### Handling user input
How the input history handles the user input can be seen from the following activity diagram

![Storing Input History](images/StoreInputHistoryActivityDiagram.png)

When the user inputs text through the `CommandBox`, the program will see if the input returns a successful command
or throws an exception (which causes the program to display an error such as 'Unknown command'); the user input will then be 
correspondingly be stored as either a valid or invalid command in `InputHistory`.

#### Internal implementation of Input History
`InputHistory` stores inputs in a data structure that keeps track of the user input as a string,
as well storing the information on whether the input was parsed as a valid command.

`InputHistory` also contains a pointer that will keep track of which command in the history is being displayed so that
it can display the relevant 'previous' and 'next' command when required.
Entering any input will cause the pointer in `InputHistory` to be reset to display the latest command upon the next retrieval.

#### Handling key presses

![Handling Key Presses](images/RetrieveInputHistoryActivityDiagram.png)

When an 'UP' arrow key is registered: The `InputHistory` will display the command that was entered previously (chronologically).
If the command history is at its earliest command, the earliest command will continue to be displayed.

When an 'DOWN' arrow key is registered: The `InputHistory` will display the command that was entered next (chronologically).
If the command history is at its latest command, the `CommandBox` will be set to blank.

#### Displaying of result
When an input is displayed, it will be displayed on the `CommandBox` and overwrite any text previously typed into it.
If the input is recognised as invalid, the text displayed will be coloured red, or otherwise it will be displayed in the default white colour.
Any other changes to the CommandBox will revert the text to white (i.e. after accessing invalid inputs, making changes to them will not cause the text to remain red). 

<div style="page-break-after: always"></div>

#### Design considerations
**Aspect:** Whether we should store and display all user inputs or just valid commands

- *Currently:* All user inputs are stored and displayed, whether invalid or valid commands.
- *Alternative 1:* Only valid user inputs will be stored. Invalid commands will be ignored by `InputHistory`.

**Pros and Cons:**
- *Currently:*
    - **Pros:**
      - Users can easily view and edit any invalid commands previously entered.
    - **Cons:**
      - May result in a larger input history especially if many invalid commands were typed by the user,
      which impedes the user from easily finding the history of valid command inputs.

- *Alternative 1:*
    - **Pros:**
      - Allows the users to view all valid inputs quickly, which acts as a 'command log' to track all their
      previously entered commands.
    - **Cons:**
      - Users are unable to edit or view previously entered inputs, requiring them to re-type their entire command if
      they initially inputted it wrongly


<div style="page-break-after: always"></div>

## **Documentation, logging, testing, configurations, dev-ops**
* [Documentation guide](Documentation.md)
* [Logging guide](Logging.md)
* [Testing guide](Testing.md)
* [Configurations guide](Configuration.md)
* [DevOps guide](DevOps.md)


<div style="page-break-after: always"></div>

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* wants to be able to manage student information
* wants to be able to keep track of student performance
* prefer desktop apps over other types
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: provides an easy way to manage students, allowing for customised support to keep tabs on each student.

<div style="page-break-after: always"></div>

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a ...  | I want to ...                                                           | So that I can ...                                                       |
|----------|-----------|-------------------------------------------------------------------------|-------------------------------------------------------------------------|
| `* * *`  | user      | add a new course                                                        | keep track of the courses I teach                                       |
| `* * *`  | user      | add a new student to an existing course                                 | keep track of the students in each of the courses I teach               |
| `* * *`  | user      | edit a course                                                           | keep accurate and up-to-date information on each course                 |
| `* * *`  | user      | edit a student's profile                                                | keep accurate and up-to-date information on each student                |
| `* * *`  | user      | delete s course                                                         | remove a course I am no longer teaching                                 |
| `* * *`  | user      | delete a student's profile                                              | remove a student if the student is no longer in a course                |
| `* * *`  | user      | assign a performance tag to a student based on how well they are coping | I can easily identify students who may need additional support          |
| `* *`    | user      | add remarks for a student                                               | keep track of miscallaneous things                                      |
| `* *`    | user      | add pending questions for a students                                    | keep track of unanswered queries from students                          |
| `* *`    | user      | search for courses based on the course name                             | easily find courses I need                                              |
| `* *`    | user      | search for students using the specified field                           | easily find students who belong in the criteria that I am interested in |
| `* *`    | user      | sort students by the specified field                                    | view the list of students in the order I require                        |
| `* * `   | user      | view usage instructions                                                 | know how to use the app when I am unfamiliar with it                    |
| `*`      | user      | list out all students in a course with unanswered pending questions     | efficiently manage and respond to queries                               |
| `*`      | user      | reset the student list to its original order                            | view the original student list after filtering or sorting               |

<div style="page-break-after: always"></div>

### Use cases

(For all use cases below, the **System** is the `CodeSphere` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a course**

**MSS**

1. User requests to add a new course.
2. CodeSphere creates the course and provides confirmation to the user.<br>

   Use case ends.

**Extensions**

* 1a. The course name is missing.
    * 1a1. CodeSphere displays an error message.<br>
      Use case resumes at step 1.

* 1b. The course name has an invalid format.
  * 1b1. CodeSphere displays an error message.<br>
    Use case resumes at step 1.

**Use case: UC02 - Edit a student’s information**

**Preconditions**: The student has already been added into CodeSphere.

**Guarantees**: Unchanged fields during the edit will remain the same as before the edit.

**MSS**

1. User requests to edit a student’s information.
2. User enters new details for the field(s) that need to be changed.
3. CodeSphere updates the student’s information and provides confirmation to the user.<br>
    Use case ends.

**Extensions**

* 1a. The given index of the student is invalid.
    * 1a1. CodeSphere displays an error message.<br>
    Use case resumes at step 1.

* 1b. No field to be edited was specified by the user.
    * 1b1. CodeSphere displays an error message.<br>
      Use case resumes at step 1.

* 1c. Invalid format for detail(s) entered.
    * 1c1. CodeSphere displays an error message.<br>
      Use case resumes at step 1.

**Use case: UC03 - Delete a student**

**Preconditions**: The student has already been added into CodeSphere.

**MSS**

1. User requests to delete a student.
2. CodeSphere deletes the specified student and no longer display that student in the student list.<br>
   Use Case Ends.

**Extensions**

* 1a. The given index of the student is invalid.
    * 1a1. CodeSphere displays an error message.<br>
    Use case resumes at step 1.

**Use case: UC04 - Add a pending question for a student**

**Preconditions**: The student has already been added into CodeSphere.

**MSS**

1. User requests to add a pending question to a student.
2. CodeSphere adds a pending question to the student.<br>
   Use Case Ends.

**Extensions**

* 1a. The given index of the student is invalid.
    * 1a1. CodeSphere displays an error message.<br>
      Use case resumes at step 1.

* 1b. Pending question field is left empty.
    * 1b1. CodeSphere displays an error message.<br>
      Use case resumes at step 1.

<div style="page-break-after: always"></div>

**Use case: UC05 - Sort the student list**

**MSS**

1. User requests to sort the student list by tag or by name.
2. CodeSphere sorts the students and displays all students in the order specified by the user.<br>
   Use Case Ends.

**Extensions**

* 1a. User enters an invalid sort criteria.
    * 1a1. CodeSphere displays an error message.<br>
      Use case resumes at step 1.

* 1b. No sort criteria was specified.
    * 1b1. CodeSphere displays an error message.<br>
      Use case resumes at step 1.

<div style="page-break-after: always"></div>

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  The application should be able to hold up to 100 courses and 1000 student contacts without a noticeable sluggishness in performance for typical usage.
3.  The application should be intuitive enough for a beginner who is new to Command Line Interfaces to be able to use easily.
4.  The application should respond to any commands carried out by the user should become visible within 5 seconds.
5.  The application should gracefully handle errors, providing informative error messages to users in case of failures.
6.  Code should be organized into reusable and maintainable modules, making it easier to enhance and extend the application in the future.
7.  Comprehension documentation should be maintained for developers.
8.  The application should run smoothly on different operating systems commonly used by the target users.
9.  A user who can type fast should be able to accomplish tasks faster via a Command Line Interface as compared to a hypothetical Graphical User Interface version of the app.
10. The application should not depend on a remote server so that users can use the application at anytime.
11. The application is not required to support multiple users on a single device.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **CLI**: Command Line Interface. A way to interact with a computer or software by typing text-based commands, rather than using a mouse and graphical icons.


<div style="page-break-after: always"></div>

## **Appendix: Instructions for manual testing**

This is a list of prefixes used for manual testing.

| Prefix | Representation     |
|--------|--------------------|
| `c/`   | `COURSE_NAME`      |
| `n/`   | `NAME`             |
| `e/`   | `EMAIL`            |
| `t/`   | `TAG`              |
| `r/`   | `REMARK`           |
| `pq/`  | `PENDING_QUESTION` |
| `s/`   | `SORT`             |

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

<div style="page-break-after: always"></div>

### Launch and shutdown

1. Initial launch.

   1. Download the jar file and copy it into an empty folder.

   2. Open a command terminal, then `cd` into the folder containing the jar file.

   3. Use the `java -jar CodeSphere.jar` command to run the CodeSphere application.

   Expected: Shows the GUI with a set of sample courses. The window size may not be optimum.<br>

2. Saving window preferences.

    1. Resize the window to an optimum size, then move the window to a different location and close the window.

    2. Re-launch the app by running the `java -jar CodeSphere.jar` command again.<br>
       Expected: The most recent window size and location is retained.

<div style="page-break-after: always"></div>

### Clearing all data
Command: `clear`

More information on usages: [clear courses command](UserGuide.html#clearing-all-courses-clear) and [clear students command](UserGuide.html#clearing-all-students-clear)

1. Clearing all courses while all courses are being shown.

   1. Prerequisites: Ensure the current page is the home page using the `home` command. List all courses using the `reset` command.

   2. Test case: `clear`<br>
      Expected: All courses are cleared from the course list.

2. Clearing all students while all students in the selected course are being shown.

   1. Prerequisites: Ensure the current page is the course page using the `select` command. Filter the student list by a specified field using the `find` command or sort the student list by a specified sort criteria using the `sort` command.

   2. Test case: `clear`<br>
      Expected: All students are cleared from the student list for the selected course.

<div style="page-break-after: always"></div>

### Adding a course
Command: `add`

More information on usage: [add course command](UserGuide.html#adding-a-course--add)

1. Adding a course while all courses are being shown.

   1. Prerequisites: Ensure the current page is the home page using the `home` command. List all courses using the `reset` command.

   2. Test case: `add c/cs2101`<br>
      Expected: A course with course name `CS2101` is added to list of courses.<br>
      The course is added to the last index of the course list. The course card appeared at the last in the list.<br>
      The details of the added course is shown in the status message.

   3. Incorrect add course commands to try: `add`, `add c/cs`, `add cs2106 n/John Doe`, etc.<br>
      Expected: No course added to the course list. Error details shown in the error message.

2. Adding a course while the course list is being filtered.

   1. Prerequisites: Ensure the current page is the home page using the `home` command. Filter the courses by course name using the `find` command.

   2. Test case: Similar to previous.<br>
      Expected: Similar to previous.

<div style="page-break-after: always"></div>

### Deleting a course
Command: `delete`

More information on usage: [delete course command](UserGuide.html#deleting-a-course--delete)

1. Deleting a course while all courses are being shown.

    1. Prerequisites: Ensure the current page is the home page using the `home` command. List all courses using the `reset` command.

    2. Test case: `delete 1`<br>
       Expected: The first course is deleted from the list of courses.<br>
       Details of the deleted course is shown in status message.<br>
       All student information in the deleted course are also deleted.

    3. Test case: `delete 0`<br>
       Expected: No course is deleted. Error details shown in the error message.

    4. Other incorrect delete course commands to try: `delete`, `delete x` (where x is larger than the course list size), etc.<br>
       Expected: Similar to previous.

2. Deleting a course while the course list is being filtered.

    1. Prerequisites: Ensure the current page is the home page using the `home` command. Filter the courses by course name using the `find` command.

    2. Test case: Similar to previous.<br>
       Expected: Similar to previous.

<div style="page-break-after: always"></div>

### Finding courses by course name
Command: `find`

More information on usage: [find course command](UserGuide.html#finding-a-course--find)

1. Finding a course while all courses are being shown.

    1. Prerequisites: Ensure the current page is the home page using the `home` command. List all courses using the `reset` command.

    2. Test case: `find cs` or `find CS`<br>
       Expected: All courses with course name containing `CS` are shown.

    3. Test case: `find 20`<br>
       Expected: All courses with course name containing `20` are shown.

    4. Test case: `find cs 20`<br>
       Expected: All courses with course name containing `CS` and/or `20` are shown.

    4. Test case: `find n/cs`<br>
       Expected: All courses with course name containing `n/cs` are shown.
       Note: The `find` command in the `home` stage does not necessitate any prefixes; therefore, the prefixes used will be interpreted as the search criteria.

    5. Incorrect find commands to try: `find`<br>
       Expected: No course filtered. Error details shown in the error message.

2. Finding a course while the course list is being filtered.

    1. Prerequisites: Ensure the current page is the home page using the `home` command. Filter the courses by course name using the `find` command.

    2. Test case: `find 20`<br>
       Expected: If the course list was initially filtered with `find s` before `find 20`, all courses with course name containing `s` and `20` are shown.

    3. Incorrect find commands to try: `find`, `find c/`, etc.<br>
       Expected: No course filtered.<br>
       Error details shown in the error message.

<div style="page-break-after: always"></div>

### Selecting a course
Command: `select`

More information on usage: [select course command](UserGuide.html#selecting-a-course--select)

1. Selecting a course while all courses are being shown.
    1. Prerequisites: Ensure the current page is the home page using the `home` command. List all courses using the `reset` command.

    2. Test case: `select 1`<br>
       Expected: The first course is selected from the list of courses.<br>
       The display changed to show 2 columns. The course list is displayed in the left column, while all students in the selected course are displayed in the right column.<br>
       Details of the selected course is shown in status message.<br>

    3. Test case: `delete 0`<br>
       Expected: No course is selected. Error details shown in the error message.

    4. Other incorrect select course commands to try: `select`, `delete x` (where x is larger than the course list size), etc.<br>
       Expected: Similar to previous.

2. Selecting a course while the course list is being filtered.
    1. Prerequisites: Ensure the current page is the home page using the `home` command. Filter the courses by course name using the `find` command.

    2. Test case: Similar to previous.<br>
       Expected: Similar to previous.

<div style="page-break-after: always"></div>

### Adding a student
Command: `add`

More information on usage: [add student command](UserGuide.html#adding-a-student--add)

1. Adding a student while all students in the selected course are being shown.
    1. Prerequisites: Ensure the current page is the course page using the `select` command. List all student using the `reset` command.

    2. Test case: `add n/John Doe e/e1234567@u.nus.edu t/good`<br>
       Expected: A student with name `John Doe` with the entered attributes is added to student list.<br>
       The student is added to the last index of the student list. The student card appeared at the last in the student list.<br>
       The details of the added student is shown in the status message.

    3. Test case: `add n/John Doe e/e1111111@u.nus.edu t/good` <br>
       Expected: A student with name `John Doe` with the entered attributes is added to student list.<br>
       Note that the `NAME` of this student is the same as that of test case 2. However, the student contact's `EMAIL` are **different**.<br>
       The student is added to the last index of the student list. The student card appeared at the last in the student list.<br>
       The details of the added student is shown in the status message.

    4. Incorrect add course commands to try: `add`, `add c/cs`, `add n/John Doe`, `add n/John Doe e/john@gmail.com t/good`, `add n/John Doe e/e2222222@u.nus.edu t/friend`, etc.<br>
       Expected: No course added to the course list. Error details shown in the error message.

2. Adding a student with duplicate/identical `EMAIL` attributes.
    1. Prerequisites: There exists a student in student list with `EMAIL` attribute `e1234567@u.nus.edu`.<br>
       Example: `n/John Doe e/e1234567@u.nus.edu t/good`

    2. Test case: `add n/John Doe e/e1234567@u.nus.edu t/good`<br>
       Expected: No student is added to the student list because the student has the same `EMAIL` as an existing student in the list.<br>
       Error details shown in the error message.

3. Adding a student while the student list is being filtered or sorted.

    1. Prerequisites: Ensure the current page is the course page using the `select` command. Filter the student list by a specified field using the `find` command or sort the student list by a specified sort criteria using the `sort` command.

    2. Test case: Similar to previous.<br>
       Expected: Similar to previous.<br>
       But note that the student list is reset to show all students after adding a student successfully.

<div style="page-break-after: always"></div>

### Adding a remark to a student
Command: `remark`

More information on usage: [remark student command](UserGuide.html#adding-a-remark-for-a-student--remark)

1. Adding a remark to a student while all students in the selected course are being shown.

   1. Prerequisites: Ensure the current page is the course page using the `select` command. List all student using the `reset` command.

   2. Test case: `remark 1 r/Needs more help`<br>
      Expected: The remark `Needs more help` is added to the first student in the student list.<br>
      The details of the first student is shown in the status message.

   3. Test case: `remark 0 r/Needs more help`<br>
      Expected: No remark is added.<br>
      Error details shown in the status message.

   4. Test case: `remark 1 r/`<br>
      Expected: No remark is added.<br>
      Error details shown in the status message.

   5. Other incorrect remark commands to try: `remark`, `remark x` (where x is larger than the list size), etc.<br>
      Expected: Similar to previous.

2. Adding a remark to a student while the student list is being filtered or sorted.

   1. Prerequisites: Ensure the current page is the course page using the `select` command. Filter the student list by a specified field using the `find` command or sort the student list by a specified sort criteria using the `sort` command.

   2. Test case: Similar to previous.<br>
      Expected: Similar to previous.

<div style="page-break-after: always"></div>

### Sorting the student list
Command: `sort`

More information on usage: [sort students command](UserGuide.html#sorting-all-students--sort)

1. Sorting the student list by the specified sort criteria while all students in the selected course are being shown.

    1. Prerequisites: Ensure the current page is the course page using the `select` command. List all student using the `reset` command.

    2. Test case: `sort s/tag`<br>
       Expected: The student list is arranged according to performance tags, with students tagged as `GOOD` at the top of the list.<br>
       `NAME` is used as a tiebreaker for students with the same `TAG`. `EMAIL` is used as the next tiebreaker for students with the same `TAG` and the same `NAME`.<br>
       Success message shown as the status message.

    3. Test case: `sort s/name`<br>
       Expected: The student list is arranged in alphabetical order according to names. Students with names starting with 'A' are shown at the top of the list.<br>
       `EMAIL` is used as the tiebreaker for students with the same `NAME`.<br>
       Success message shown as the status message.

    4. Test case: `sort s/gender`<br>
       Expected: Student list is not sorted.<br>
       Error details shown in the status message.

    5. Other incorrect remark commands to try: `sort`, `sort s/`, etc.<br>
       Expected: Similar to previous.

2. Sorting the student list by the specified sort criteria while the student list is already being sorted.

    1. Prerequisites: Ensure the current page is the course page using the `select` command. Sort the student list by a specified sort criteria using the `sort` command.

    2. Test case: Similar to previous.<br>
       Expected: Similar to previous.<br>
       Note that the student list will only be sorted by one sort criteria. The new sort criteria overrides the previous sort criteria.

3. Sorting the student list by the specified sort criteria while the student list is already being filtered.

   1. Prerequisites: Ensure the current page is the course page using the `select` command. Filter the student list by a specified field using the `find` command.

   2. Test case: Similar to previous.<br>
      Expected: Similar to previous.<br>
      Note that sorting and filtering can be stacked. Sorting is done on top of the currently filtered list.

<div style="page-break-after: always"></div>

### Finding students by different criterias
Command: `find`

More information on usage: [find Finding a student](#finding-a-student--find)

1. Finding a student while all students are being shown.

    1. Prerequisites: Ensure the current page is the home page using the `course` command. List all students using the `reset` command.

    2. Test case: `find n/ry` or `find n/RY`<br>
       Expected: All students with name containing `ry` are shown. Casing of letters are ignored.

    3. Test case: `find r/20`<br>
       Expected: All students with remark containing `20` are shown.

    4. Test case: `find r/cs 20`<br>
       Expected: All students with remark containing both `cs` and `20`, in any order, are shown.

    5. Incorrect find commands to try: `find`, `find n/` `find n/John r/homework`, etc.<br>
       Expected: No student filtered. Error details shown in the error message.

2. Finding a student while the student list is being filtered.

    1. Prerequisites: Ensure the current page is the home page using the `course` command. Filter the students using the `find` command.

    2. Test case: `find n/Jason`<br>
       Expected: If the student list was initially filtered with `find r/homework` before `find n/Jason`, all students with their remark containing `homework` and name containing `Jason` are shown.

    3. Incorrect find commands to try: `find`, `find n/` `find n/John r/homework`, etc.<br>
       Expected: No student filtered. Error details shown in the error message.



<div style="page-break-after: always"></div>

## **Appendix: Effort**

### Difficulty Level
We found that the difficulty level of the project was slightly more challenging compared to CS2103T's Individual Project (iP).
While the workload was similar to the iP, there was an additional level of team management required (such as division of roles and
resolving code conflicts) that made meeting target milestones slightly more challenging. 

Furthermore, while AB3 focused on managing a single entity type of `Person` in an address book,
CodeSphere deals with multiple entity types that are linked to each other, namely `Course` and `Student`.

### Challenges faced
One challenge we faced early on was a 'repetitive' command usage, as the simultaneous handling of two classes
`Course` and `Student` meant that a lot of commands were duplicated
(e.g. `AddCourse` vs `AddStudent`, `EditCourse` vs `EditStudent` ) .
This was eventually resolved through utilising a StageManager
(inspired by the above acknowledged project [Waddle](https://ay2223s1-cs2103t-w11-4.github.io/tp/)),
which handles the logic of which class was currently viewed as 'active'; the application will now determine the appropriate action to take
for a command (i.e. `add` will now perform `addStudent` or `addCourse` depending on which stage is active).

### Effort required
- **Program architecture:** CodeSphere built upon the existing project structure of AB3, adding in many additional commands and
    structures (`course`, `pending questions`, `filters`) to support our application.
    Through constant iterative upgrades and feedback, we managed to build and encapsulate our 
    designs into the application that CodeSphere is today.
- **UI / UX improvements:** While working on improving our application architecture and logic, we also simultaneously
  made significant improvements to the graphical user interface (GUI), such as intuitive colour coding schemes and logos,
  as well as the general user experience (UX) such as accessing the user's input history.
- **Bug Testing and Fixing:** During the development of our application, we ensured constant developer testing by
    writing and editing test cases for new features / changed implementations of any code that we wrote. Our goal for
  unit and integration testing was to aim for >70% code coverage (as we do not test for UI classes).  
  After development of our application was finished, we also released an early version to be bug tested in
  Practical Exam's dry run (PE-D) by external reviewers, and then worked hard to find and fix any issues that were raised during
  the PE-D.
- **Documentation:** Throughout our entire project, we have been carefully documenting our changes to be able to provide 
    comprehensive and concise documentation for both users (*User Guide*) and other developers (*Developer Guide*) that may be viewing our application design.
    This included providing relevant screenshots, diagrams and various other supporting text / images to guide the reader 
    to better understand the project and answer any questions they may have. We have also ensured that the project website 
    and our individual portfolios have been kept updated.  
    


<div style="page-break-after: always"></div>

## **Appendix: Planned Enhancements**

### Select from Course Page

While in the course selection page, users are unable to immediately reselect another course by utilising the `select` command. 

While this was originally not intended for our product as the typical workflow for users would be returning to the course page through `home` and re-selecting from there, we recognise that users may wish to immediately reselect
a course while viewing a separate course. 

We plan to make the `select` command work regardless of which page that the user is currently viewing.

### Parsing with / character in edit and find command

The command prefix for `edit` and `find` is unable to properly differentiate when a `/` symbol is used with the character prior to it resembling a prefix character.

For example, `edit 1 r/e/b` should be interpreted as '`edit` index `1` with the `remark` `e/b`' but is currently taken as '`edit` index `1` with the new `email` `/b`'. 
Another example is when using the `find` command for students. `find r/homework answer is e/d` will result in a duplicate prefix command error.

We plan to change the parser of these commands to prioritize the firstmost prefix. This adjustment will resolve the issue for the `find` command. 
For `edit`, we intend to introduce more specific prefixes to allow greater versatility for users editing the fields. For instance, we could change `e/` to `email!/`. The likelihood of such a word being used for the other criteria is minimal and would effectively solve the problem.

### More specific command errors

Our application is currently unable to recognise invalid prefix commands that are written behind a valid prefix command -- instead it produces an 'invalid format' error for the valid prefix command.

For example, `add c/cs2100 t/good` gives the error "Course code should contain a two or three letter prefix, a four digit course code, and an optional one letter suffix", when it should recognise that "t/" should not be included and produce an appropriate error message.

We plan to make the error message accurately reflect the invalid prefix command, such like (in the example above) displaying "Add course command should not contain the prefix `t/`".

### UI Colour Scheme

Our application currently follows a similar 'dark theme' colour scheme as was set by AB3, as we found it to be the most 
aesthetically pleasing to view. However, we recognise that different users may want to customise or view the application 
differently. We would thus like to introduce more colour schemes that may also benefit certain user groups (etc. making our labels dichromatism-friendly).

### UI Font Size

Our application currently has a set default font type and sizing, which may not be readable for all users. In the future,
we plan to introduce an adjustable font types/sizing so that users can adjust it to meet their own individual needs.

### Changing Tag Sets

The tags for a `Student` in our application are set to `GOOD`, `AVERAGE` and `POOR`, and work as a performance indicator.
However, such tags may be too subjective for users if they wish to categorise their students according to a more objective
tag scheme, but the tags are currently unable to be changed to anything other than these 3 values. We plan to allow users to
define their own tags (as well as corresponding colour schemes), so that tags are customisable according to the user's unique situation.
