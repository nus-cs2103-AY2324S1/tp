---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# Tutorium Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

<div style="page-break-after: always;"></div>

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

<div style="page-break-after: always;"></div>

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

<div style="page-break-after: always;"></div>

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

<div style="page-break-after: always;"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="450"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml"/>

The `Model` component,

* stores the address book data i.e., all `Student` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div style="page-break-after: always;"></div>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

--------------------------------------------------------------------------------------------------------------------

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Filter feature

#### Implementation

The `filter` command allows the user to display a list of students who fulfil all predicates specified within the command.

When the user enters a filter command, the `AddressBookParser` parses the user's input and returns a `FilterCommand`.

Each predicate entered by the user can be modelled by exactly one of the following classes: `StudentHasAddressPredicate`, `StudentHasEmailPredicate`, `StudentHasPhonePredicate`, `StudentIsGenderPredicate`, `StudentIsSecLevelPredicate`, `StudentNearestMrtIsPredicate` and `StudentTakesSubjectPredicate`. These predicates can be added to the `predicateList` field of type `StudentPredicateList` within each `FilterCommand` object.

The following sequence diagram shows how the `filter` command works. In this example, the user is executing the following command: `filter s/Physics g/M`.

<puml src="diagrams/FilterSequenceDiagram.puml" alt="FilterSequenceDiagram" />

When the `FilterCommandParser` parses the arguments to the `FilterCommand`, it creates a `StudentPredicateList`, to which the relevant predicates specified within the command are added. For example, using the example command given above, the `StudentPredicateList` would consist of 2 predicates: a `StudentTakesSubjectPredicate` and a `StudentIsGenderPredicate`.
These predicates are then combined into a single `Predicate<Student>`, using the `and()` method from the `Predicate` interface.

<div style="page-break-after: always;"></div>

The following activity diagram summarizes what happens when a user executes a `filter` command:

<puml src="diagrams/FilterActivityDiagram.puml" alt="FilterActivityDiagram" width="300" />

#### Design considerations:

**Aspect: How the predicates specified within a single `FilterCommand` should be combined:**

* **Alternative 1 (current choice):** Combine predicates using the `and()` method from the `Predicate` interface.
  * Pros:
    * More in line with what the user would expect from a `filter` command.
  * Cons:
    * Users would experience less flexibility when using the command (for instance, the command `filter s/Physics s/Chemistry` cannot be used to display students taking Physics and/or Chemistry at the tuition centre).
* Alternative 2: Combine predicates using the `or()` method from the `Predicate` interface.
  * Pros:
    * Greater flexibility for users when filtering the list of students.
  * Cons:
    * Less in line with users' expectations of a `filter` command; not as intuitive.
* We made the choice of Alternative 1 over Alternative 2 as we found that more intuitive commands would be easier for users to learn and eventually master.

<div style="page-break-after: always;"></div>

### Uplevel, Undolevel feature

#### Implementation

The `uplevel` command allows the user to increase the sec levels of all students by one and remove all sec 4 students.

The `undolevel` command allows the user to undo the `uplevel` command and revert the student records to be before previous `uplevel` operation since open application.

When the user enter an uplevel/undolevel command, the `AddressBookParser` parses the user input and returns a `UpdateSecLevelCommand`.

The following sequence diagram shows how the `UpdateSecLevelCommand` works. In this example, the user is executing `uplevel`.

<puml src="diagrams/UpdateSecLevelSequenceDiagram.puml" alt="UpdateSecLevelSequenceDiagram" />

When the `AddressBookParser` parses the argument, it creates either `UpdateSecLevelCommand(isUndo: false)` for `uplevel` or `UpdateSecLevelCommand(isUndo: true`) for `undolevel`.

The following activity diagram summarizes what happen when a user executes a `UpdateSecLevelCommand`.

<puml src="diagrams/UpdateSecLevelActivityDiagram.puml" alt="UpdateSecLevelActivityDiagram" width="750" />

<div style="page-break-after: always;"></div>

#### Design considerations:

**Aspect: How other commands executed after `uplevel` and before `undolevel` should be addressed.**

* **Alternative 1 (current choice):** The other commands executed after `uplevel` and before `undolevel` will be invalid.
  * Pros:
    * To avoid conflict between those commands and `undolevel` command.
    * More in line with what the user expect from reverting student records state to be before an `uplevel` command.
  * Cons:
    * User will need to perform those "in-between" commands again.
* Alternative 2: Keep track of what the user have done after any `uplevel` command.
  * Pros:
    * User will not need to perform those "in-between" commands again after doing `undolevel` to undo the sec level update.
  * Cons:
    * Less in line with users' expectations of reverting student records state to be before an `uplevel` command.
    * May have conflict between those "in-between" commands and `undolevel` command. E.g., edit a student's sec level and then perform `undolevel`.
    * Decline in performance due to the need to keep track every operation after `uplevel`.
* We made the choice of Alternative 1 over Alternative 2 as undolevel is provided in case a user accidentally perform `uplevel` that the user didn't intend to. As such, we found that Alternative 1 is more in line of users' expectations and will not mess up the logic.

<div style="page-break-after: always;"></div>

### Sort feature

#### Implementation

The `sort` command allows the user to sort the list of students by name in alphabetical order to enhance efficiency in searching.

When the user enters a sort command, the `AddressBookParser` parses the user's input and returns a `SortCommand`.

The predicate entered by the user can be modelled by the following class: `SortIn`.

The following sequence diagram shows how the `sort` command works. In this example, the user is executing the following command: `sort in/ASC`.

<puml src="diagrams/SortSequenceDiagram.puml" alt="SortSequenceDiagram" />

When the `SortCommandParser` parses the argument to the `SortCommand`, the argument is stored as an attribute of type SortIn in `SortCommand`.
This predicate is then passed into the current model, using the `updateSortedPersonList()` method.

The following activity diagram summarizes what happens when a user executes a `sort` command:

<puml src="diagrams/SortActivityDiagram.puml" alt="SortActivityDiagram" width="250" />

<div style="page-break-after: always;"></div>

#### Design considerations:

**Aspect: How the student list is sorted internally:**

* **Alternative 1 (current choice):** Sort the student list in class `UniquePersonList` using method `sort`.
  * Pros:
    * Student list is sorted permanently, ensuring no repeated sorting needed in the next launch provided no new student is added or student's name is changed.
    * Enhance efficiency of looking through the student list, ensure no repeated sorting needed when doing consecutive commands such as `filter`.
  * Cons:
    * Users would be unable to view the unsorted student list again.
* Alternative 2: Sort the student list in class `ModelManager` using method `updateSortedPersonList`.
  * Pros:
    * Enable users to view the unsorted student list for every launch.
  * Cons:
    * Users have to re-sort the student list for every launch.
* We made the choice of Alternative 1 over Alternative 2 as we insist on providing greater convenience.

<div style="page-break-after: always;"></div>

### Import feature

#### Implementation

The `import` command allows the user to import .csv files containing their students' data in one go so that they do not need to add them one-by-one.

When the user enters an import command, the `AddressBookParser` parses the user's input using `ImportCommandParser` and returns a `ImportCommand`.

The following sequence diagram shows how the `import` command works. In this example, the user is executing the following command: `import student_data.csv`.

<puml src="diagrams/ImportSequenceDiagram.puml" alt="ImportSequenceDiagram" />

When the `ImportCommandParser` parses the arguments, it creates a list of `Student` objects using the data in the .csv file and passes the argument and the list into the `ImportCommand`. `ImportCommand` will then add the `Student` into the `AddressBook`.

The following activity diagram summarizes what happens when a user executes a `import` command:

<puml src="diagrams/ImportActivityDiagram.puml" alt="ImportActivityDiagram" width="300" />

<div style="page-break-after: always;"></div>

#### Design considerations:

**Aspect: How to separate the attributes correctly from the imported data**

* **Alternative 1 (current choice):** Fixed column sequence for data in the imported .csv files.
  * Pros:
    * Students' data with comma such as address can be detected more easily and will not be split wrongly.
  * Cons:
    * Users would experience less flexibility when using the command (for instance, users need to ensure their column in their .csv files matches the sequence).
* Alternative 2: Flexible column sequence for data in the imported .csv files.
  * Pros:
    * Greater flexibility for users when importing students' data.
  * Cons:
    * Higher chance in wrong a splitting of students' data.
* We made the choice of Alternative 1 over Alternative 2 as we found that a fixed format would be easier for users to remember and use in the .csv files.

<div style="page-break-after: always;"></div>

### Table feature

#### Implementation
The `table` command allows users to generate a statistical table categorised by `gender`, `subject`, `sec level` or `enrol date`.

When the user enters a table command, the `AddressBookParser` parses the user's input and return a `TableCommand`.

Note that there is no specific TableCommandParser for `TableCommand` just like `ListCommand`, `ExitCommand` and `HelpCommand`. The `AddressBookParser` can parse and return a `TableCommand`directly.

The parameters entered by user expected for a table command are either `g/`, `s/`, `l/` or `d/`. When the `TableCommand` instance created by `AddressBookParser` executes, it will return the corresponding CommandResult. e.g. `GenderTableCommandResult` created when `table g/` is entered. This `XXXTableCommandResult` carries the counts for each category that will be used for generating the table.

The following sequence diagram shows how the `table` command works. In this example, the user is executing the following command: `table s/`

<puml src="diagrams/TableSequenceDiagram.puml" alt="TableSequenceDiagram" />

As shown in the sequence diagram, when the `AddressBookParser` parses the arguments to the TableCommand, it creates a TableCommand instance by passing in `s/` as argument so that when this `TableCommand` execute, it will return a `SubjectTableCommandResult` instance as specified by `s/`.

<div style="page-break-after: always;"></div>

The following activity diagram summarizes what happens when a user executes a `table` command:

<puml src="diagrams/TableActivityDiagram.puml" alt="TableActivityDiagram" width="300" />

#### Design considerations:
**Aspect: How to parse the argument for table internally:**

* **Alternative 1 (current choice):** parse inside `TableCommand` and return the corresponding `XXXTableCommandResult`.
  * Pros: Easy to implement, more straightforward when the number of possible arguments is less.
  * Cons: May not be suitable when we want to create a complex statistical table, e.g. a two-dimensional table.

* **Alternative 2:** Create a CommandParser specifically for TableCommand.
  * Pros: Provides a good abstraction when we are dealing with two-dimensional table.
  * Cons: May be redundant when we only want to create a one-dimensional table and the number of possible categories is smaller.

* We made the choice of Alternative 1 over Alternative 2 as we found that the table we intend to create so far is one-dimensional table and there are only four possible categories (`g/` for gender, `s/` for subject, `l/` for sec level and `d/` for enrol date).
  _{more aspects and alternatives to be added}_

<div style="page-break-after: always;"></div>

### Trend feature

#### Implementation
The `trend` command allows users to generate a statistical line graph, showing the trend of tuition enrolments of a specific year.

When the user enters a trend command, the `AddressBookParser` parses the user's input using `TrendCommandParser` and returns a `TrendCommand`.

The parameters entered by user for a trend command are expected to be only `y/`. When the `TrendCommand` instance created by `AddressBookParser` executes, it will return a TrendCommandResult.

The following sequence diagram shows how the `trend` command works. In this example, the user is executing the following command: `trend y/2023`

<puml src="diagrams/TrendSequenceDiagram.puml" alt="TrendSequenceDiagram" />

As shown in the sequence diagram, when the `TrendCommandParser` parses the arguments to the TrendCommand, it creates a TrendCommand instance by passing in `2023` as argument so that when this `TrendCommand` executes, the trend of tuition enrolments of 2023 will be shown.

<div style="page-break-after: always;"></div>

The following activity diagram summarizes what happens when a user executes a `trend` command:

<puml src="diagrams/TrendActivityDiagram.puml" alt="TrendActivityDiagram" width="300" />

#### Design considerations:
**Aspect: How to parse the argument for trend internally:**

* **Alternative 1 (current choice):** Create a CommandParser specifically for TrendCommand.
  * Pros: Provides better abstraction.
  * Cons: May be redundant as we only need to parse a year string.

* **Alternative 2: Parse inside `TrendCommand`.
  * Pros: Easier to implement, more straightforward and more intuitive.
  * Cons: Code might be messier and longer, errors are harder to be handled.

* We made the choice of Alternative 1 over Alternative 2 as we found that the creating a TrendCommandParser specifically for TrendCommand has cleaner code, handles error better, and it is generally a better practice.

<div style="page-break-after: always;"></div>

### Export feature

#### Implementation

The `export` command allows the user to export a visual representation recently created, be it a table, bar chart or line graph.

When the user enters an export command, the `AddressBookParser` parses the user's input and returns a `ExportCommand`.

The predicate entered by the user can be modelled by the following class: `Visual`.

The following sequence diagram shows how the `export` command works. In this example, the user is executing the following command: `sort in/ASC`.

<puml src="diagrams/ExportSequenceDiagram.puml" alt="ExportSequenceDiagram" />

When the `ExportCommandParser` parses the argument to the `ExportCommand`, the argument is stored as an attribute of type Visual in `ExportCommand`.
This predicate is then passed into the current model, using the `export()` method.

The following activity diagram summarizes what happens when a user executes a `export` command:

<puml src="diagrams/ExportActivityDiagram.puml" alt="ExportActivityDiagram" width="300" />

<div style="page-break-after: always;"></div>

#### Design considerations:

**Aspect: Where to export the image to:**

* **Alternative 1 (current choice):** Allow users to manually choose the destination in GUI to export the image to using method `exportAsPng`.
  * Pros:
    * Users could confidently navigate into the intended folder to save the image.
  * Cons:
    * Users have to use touchpad or a mouse to navigate around the folders.
* Alternative 2: Allow users to type in the path to the intended folder to save the image.
  * Pros:
    * Users would not need to find a touchpad or a mouse to use the GUI.
  * Cons:
    * It takes more time for a user to find out the exact path to save the image.
    * Getting the exact path is troublesome and significantly drains convenience.
    * Getting a small typo in the path is frustrating.
* We made the choice of Alternative 1 over Alternative 2 as we insist on providing greater convenience.

<div style="page-break-after: always;"></div>

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

Step 2. The user executes `delete 5` command to delete the 5th student in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

<div style="page-break-after: always;"></div>

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
  * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

<div style="page-break-after: always;"></div>

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

* works as a tuition centre administrative staff
* can type fast
* prefers typing to mouse interactions
* prefers using software to manage students' contacts
* wants to make use of statistics and technology to make marketing decisions

**Value proposition**: Our product will take in data as inputs and return statistical analysis.
Instead of showing information of independent individuals, our product aims to provide quantitative data analysis of
students. This allows users to draw conclusions on commonalities among students and their demographics, offering insights on marketing strategies.



### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                            | I want to …​                                                                 | So that I can…​                                                                                  |
|----------|------------------------------------|------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|
| `* * *`  | As a tuition centre admin staff    | I can insert a new student's data into Tutorium                              | so that my student list is updated with new students.                                            |
| `* * *`  | As a tuition centre admin staff    | I can delete data from Tutorium                                              | so that I can remove irrelevant student data.                                                    |
| `* * *`  | As a tuition centre admin staff    | I can edit student information                                               | so that my student data is correctly updated.                                                    |
| `* * *`  | As a tuition centre admin staff    | I can view student information                                               | so that I can know the details of my students.                                                   |
| `* *`    | As a tuition centre admin staff    | I can import student data from various sources                               | so that it is more convenient to add data of many students into Tutorium.                        |
| `* *`    | As a tuition centre admin staff    | I can save all student data into a database                                  | so that I can access it again in the future.                                                     |
| `* *`    | As a tuition centre admin staff    | I can archive data                                                           | so that my data will be more organized.                                                          |
| `* *`    | As a tuition centre admin staff    | I can select multiple data from student list                                 | so that it is more convenient for me to perform an action on these students' data.               |
| `* *`    | As a tuition centre admin staff    | I can filter student data by demographics                                    | so that I can easily search for students by demographics.                                        |
| `* *`    | As a tuition centre admin staff    | I can filter student data by nearest MRT station                             | so that I can easily search for student by their nearest MRT station.                            |
| `* *`    | As a tuition centre admin staff    | I can sort student data in alphabetical order (ascending and descending)     | so that I can easily organize my data.                                                           |
| `* *`    | As a tuition centre admin staff    | I can view the total number of students in the same age group                | so that I can tailor my marketing strategies by age group.                                       |
| `* *`    | As a tuition centre admin staff    | I can view the total number of students with same gender                     | so that I can tailor my marketing strategies by gender.                                          |
| `* *`    | As a tuition centre admin staff    | I can view the total number of students in the same location                 | so that I can tailor my marketing strategies by location.                                        |
| `* *`    | As a tuition centre admin staff    | I can view the total number of students who are taking the same subject(s)   | so that I can tailor my marketing strategies by subject taken.                                   |
| `* *`    | As a tuition centre admin staff    | I can see the total number of students                                       | so that I can know how many students are in our tuition center.                                  |
| `* *`    | As a new user of Tutorium          | I can read the user guide                                                    | so that I can familiarize myself with  this application.                                         |
| `* *`    | As a new user of Tutorium          | I can easily download and use the app                                        | so that I do not have to spend too much time and effort in figuring out setup settings.          |
| `* *`    | As a new user of Tutorium          | I have access to a help command                                              | so that I can get help for any problems faced when using the app.                                |
| `* *`    | As a tuition centre admin staff    | I can visualize data by creating different charts/tables                     | so that I can easily visualize my students' demographics.                                        |
| `* *`    | As a tuition centre admin staff    | I can get the correlation between two factors that are related to my service | so that I can have a quantitative analysis on the relationship between these factors.            |
| `* *`    | As a tuition centre admin staff    | I can see the five number summary (min, Q1, mean, Q3, max) of my user data   | so that I can compare different groups of students' data.                                        |
| `* *`    | As a tuition centre admin staff    | I can export charts and tables as images                                     | so that I can present my insights to my colleagues more easily.                                  |
| `* *`    | As a tuition centre admin staff    | I can view the trend of student attributes                                   | so that I can make predictions to target potential students.                                     |
| `* *`    | As a tuition centre admin staff    | I can save historical analyzed statistics                                    | so that I can learn from my past successes and failures and improve future marketing strategies. |
| `* `     | As a tuition centre admin staff    | I can collect data on online engagement                                      | so that I can conduct further analysis on digital marketing strategies.                          |
| `* `     | As an experienced user of Tutorium | I can use shortcuts to perform tasks in the app                              | so that I save time which I can spend on other activities.                                       |
| `* `     | As a tuition centre admin staff    | I can collect feedback(s) from my students                                   | so that I can improve my service further based on this feedback.                                 |
| `* `     | As a tuition centre admin staff    | I can schedule my marketing campaigns and events                             | so that I know when my marketing events are.                                                     |

*{More to be added}*

<div style="page-break-after: always;"></div>

### Use cases

(For all use cases below, the **System** is the `Tutorium` and the **Actor** is the `Tuition Administrative Staff`, unless specified otherwise)

**Use case: UC01 - Add student data**

**MSS**

1. Staff chooses to add a new student's data.
2. Tutorium stores the new student's data. <br>
   Use case ends.

**Extensions**

* 1a. Tutorium detects a formatting error or missing attributes in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

**Use case: UC02 - Import student data**

**MSS**

1. Staff chooses to import student data.
2. Tutorium stores the new student data. <br>
   Use case ends.

**Extensions**

* 1a. Tutorium detects a formatting error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

* 1b. Tutorium detects an invalid value for a field in the file used for the import.
  * 1b1. Tutorium requests for a valid value for that field.
  * 1b2. User enters a new command. <br>
    Steps 1b1-1b2 are repeated until the values in the file used for the import are valid. <br>
    Use case resumes from step 2.

* 1c. Tutorium detects that the filepath for the import is invalid.
  * 1c1. Tutorium requests for a valid filepath.
  * 1c2. User enters a new filepath. <br>
    Steps 1c1-1c2 are repeated until the filepath provided is valid. <br>
    Use case resumes from step 2.

**Use case: UC03 - Delete student data**

**MSS**

1. Staff chooses to delete a student's data.
2. Tutorium deletes the student's data. <br>
   Use case ends.

**Extensions**

* 1a. Tutorium detects a formatting error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

* 1b. Tutorium could not find any student data that matches with the entered student’s name.
  * 1b1. Tutorium requests for a valid student’s name.
  * 1b2. User enters a new student’s name. <br>
    Steps 1b1-1b2 are repeated until the name entered matches a student in the list.  <br>
    Use case resumes from step 2.

* 1c. Tutorium finds that the index entered is invalid.
  * 1c1. Tutorium requests for a valid index.
  * 1c2. User enters a new index. <br>
    Steps 1c1-1c2 are repeated until a valid index is entered.  <br>
    Use case resumes from step 2.

**Use case: UC04 - Edit student data**

**MSS**

1. Staff chooses to edit a student's data.
2. Tutorium edits the student’s data. <br>
   Use case ends.

**Extensions**

* 1a. Tutorium detects a formatting error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command.  <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

* 1b. Tutorium could not find any student data that matches with the entered student’s name.
  * 1b1. Tutorium requests for a valid student’s name.
  * 1b2. User enters a new student’s name. <br>
    Steps 1b1-1b2 are repeated until the name entered matches a student in the list.  <br>
    Use case resumes from step 2.

* 1c. Tutorium finds that the index entered is invalid.
  * 1c1. Tutorium requests for a valid index.
  * 1c2. User enters a new index. <br>
    Steps 1c1-1c2 are repeated until a valid index is entered.  <br>
    Use case resumes from step 2.

* 1d. Tutorium finds that no fields were edited.
  * 1d1. Tutorium requests for a field to edit.
  * 1d2. User enters a new command. <br>
    Steps 1c1-1c2 are repeated until a command with at least one field to edit is provided.  <br>
    Use case resumes from step 2.

**Use case: UC05 - Search for student data**

**MSS**

1. Staff chooses to search student data with a particular keyword.
2. Tutorium shows the list of student data that contain the keyword. <br>
   Use case ends.

**Use case: UC06 - Filter student data**

**MSS**

1. Staff chooses to filter student data based on some condition(s).
2. Tutorium shows the list of student data that fulfil the condition(s). <br>
   Use case ends.

**Extensions**

* 1a. Tutorium detects a formatting error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

**Use case: UC07 - Sort student data**

**MSS**

1. Staff chooses to sort student data in a specified order.
2. Tutorium sorts student data based on the specified order. <br>
   Use case ends.

**Extensions**

* 1a. Tutorium detects a formatting error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

**Use case: UC08 - Visualize data in a statistical table**

**MSS**

1. Staff chooses to generate a table by either gender, sec level, subject or enrol date category.
2. Tutorium generates the table with students categorised based on the category chosen. <br>

**Extensions**

* 1a. Tutorium detects a formatting error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

* 1b. Tutorium could not find any category that matches with the entered category.
  * 1b1. Tutorium requests for a valid category.
  * 1b2. User enters a new category. <br>
    Steps 1b1-1b2 are repeated until the data entered are correct. <br>
    Use case resumes from step 2.

**Use case: UC09 - Visualize data in a chart**

**MSS**

1. Staff chooses to generate a chart by either gender, sec level, subject or enrol date category.
2. Tutorium generates the chart with students categorised based on the category chosen. <br>
   Use case ends.

**Extensions**

* 1a. Tutorium detects a formatting error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

* 1b. Tutorium could not find any category that matches with the entered category.
  * 1b1. Tutorium requests for a valid category.
  * 1b2. User enters a new category. <br>
    Steps 1b1-1b2 are repeated until the data entered are correct. <br>
    Use case resumes from step 2.

**Use case: UC10 - Export charts/tables**

**MSS**

1. Staff chooses to <ins> visualize data in a statistical table (UC06) <ins> or <ins> visualize data in a chart (UC07) <ins>.
2. Staff chooses to export the chosen chart/table as an image.
3. Tutorium exports the chart/table as an image. <br>
   Use case ends.

**Extensions**

* 2a. Tutorium detects a formatting error in the entered command.
  * 2a1. Tutorium requests for the correctly formatted command.
  * 2a2. User enters a new command. <br>
    Steps 2a1-2a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 3.

* 2b. Tutorium could not find any visualization of the chosen visualization type.
  * 2b1. Tutorium requests for a visualization of that type to be generated.
  * 2b2. User enters a new command to create a visualization of that type. <br>
    Steps 2b1-2b2 are repeated until a visualization of the chosen visualization type exists. <br>
    Use case resumes from step 3.

**Use case: UC11 - Visualize the data trend in a line graph**

**MSS**

1. Staff chooses to generate a line graph based on enrol date.
2. Tutorium generates the chart with students categorised based on their enrol date. <br>
   Use case ends.

**Extensions**

* 1a. Tutorium detects a formatting error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

<div style="page-break-after: always;"></div>

**Use case: UC12 - Update the sec levels of students**

**MSS**

1. Staff chooses to update the sec levels of students.
2. Tutorium increments the sec levels of all students by 1, and deletes students whose sec levels are already 4. <br>
   Use case ends.

**Extensions**

* 1a. Tutorium detects that there are no students left to update.
  * 1a1. Tutorium informs the user that there are no students left to update.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until there are students available to update. <br>
    Use case resumes from step 2.

**Use case: UC13 - Undo sec level update**

**MSS**

1. Staff chooses to <ins> update the sec levels of students (UC12) <ins>.
2. Staff chooses to undo the sec level update.
3. Tutorium undoes the sec level update. <br>
   Use case ends.

**Extensions**

* 2a. Tutorium could not find any sec level updates that have not been undone since the application was opened.
  * 2a1. Tutorium informs the user that there are no sec level updates left to undo.
  * 2a2. User enters a new command. <br>
    Steps 2a1-2a2 are repeated until a sec level update left to undo exists. <br>
    Use case resumes from step 3.

<div style="page-break-after: always;"></div>

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
3.  A user with above-average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The students' data format should be persistent.
5.  The application should be usable by a novice who has never interacted with command line interface before.
6.  The project is expected to adhere to a schedule that delivers several features implemented by 4 to 5 team members every week.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Student data**: Name, phone number, email, address, gender, sec level, nearest MRT station and subject(s) for each student

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch
  1. Download the jar file and copy into an empty folder.
  2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences
  1. Resize the window to an optimum size. Move the window to a different location. Close the window.
  2. Re-launch the app by double-clicking the jar file.<br>
     Expected: The most recent window size and location is retained.

### Listing all students
1. Listing all students.
  1. Prerequisites: Student list contains at least 1 student.
  2. Test case: `list`<br>
     Expected: A list of all students is displayed.
  3. Test case: `list 123`<br>
     Expected: A list of all students is displayed. Note that the extraneous parameter `123` is ignored.

### Adding a student

1. Adding a student.
  1. Prerequisites: None.
  2. Test case: `add n/John Doe p/12345678 e/johnd@example.com a/John street, block 123, #01-01 g/M l/2 m/KR mrt s/Chemistry s/Physics`<br>
     Expected: Adds a student named `John Doe` to the list.
  3. Test case: `add n/Jane Smith p/98765432 e/janes@example.com a/Josh street, block 456, #02-02 l/3 m/Clementi mrt s/English`<br>
     Expected: Since the `gender` field is missing, an error message stating that the command format is invalid is displayed.

### Importing student data

1. Importing student data.
  1. Prerequisites: A [sample CSV file](https://github.com/AY2324S1-CS2103T-W13-2/tp/files/13331810/student_data_test.csv) has been downloaded into the same folder as Tutorium.
  2. Test case: `import student_data_test.csv`<br>
     Expected: All students in the CSV file are added to the list.

### Deleting a student

1. Deleting a student.
  1. Prerequisites: Student list currently shown is not empty, and contains a student named John Doe.
  2. Test case: `delete 1`<br>
     Expected: Deletes the first student on the student list currently shown.
  3. Test case: `delete John Doe`<br>
     Expected: Deletes data of the student named John Doe.

### Editing a student

1. Editing a student.
  1. Prerequisites: Student list currently shown is not empty, and contains a student named John Doe.
  2. Test case: `edit 1 p/97533948`<br>
     Expected: Edits the phone number of the first student on the student list to 97533948.
  3. Test case: `edit John Doe n/Josh Doe`<br>
     Expected: Edits John Doe's name to Josh Doe.

### Searching for students

1. Searching for students.
  1. Prerequisites: None.
  2. Test case: `search Mary Tan`<br>
     Expected: A list of all students whose names contain the `Mary Tan` is shown.

### Filtering the list of students

1. Filtering the list of students.
  1. Prerequisites: None.
  2. Test case: `filter s/Physics`<br>
     Expected: A list of all students who study Physics is shown.
  3. Test case: `filter l/3 s/Physics`<br>
      Expected: A list of all Secondary 3 students who study Physics is shown.

<div style="page-break-after: always;"></div>

### Sorting the list of students

1. Sorting the list of students.
  1. Prerequisites: None.
  2. Test case: `sort in/ASC`<br>
   Expected: The students in the list currently shown are sorted in alphabetical order (ascending).

### Creating a statistical table

1. Creating a statistical table.
  1. Prerequisites: None.
  2. Test case: `table g/`<br>
     Expected: A table showing student counts, categorized by gender, is displayed.

### Creating a bar chart

1. Creating a bar chart.
  1. Prerequisites: None.
  2. Test case: `bar g/`<br>
     Expected: A bar chart showing student counts, categorized by gender, is displayed.

### Exporting data visualizations

1. Exporting data visualizations.
  1. Prerequisites: A bar chart has been generated prior to this command, but not a table.
  2. Test case: `export v/BAR`<br>
     Expected: The most recently created bar chart is exported as an image.
  3. Test case: `export v/TABLE`<br>
     Expected: Since no table has been generated prior to this command, an error message stating that this visual
     representation must be created before it can be exported is displayed.

### Creating a line graph

1. Creating a line graph.
  1. Prerequisites: None.
  2. Test case: `trend y/2023`<br>
     Expected: A line graph showing the trend of student enrolment in year 2023 is displayed.

### Updating sec levels

1. Updating sec levels.
  1. Prerequisites: The list of students in Tutorium is not empty.
  2. Test case: `uplevel` <br>
     Expected: The sec levels of students below Secondary 3 are increased by 1, while Secondary 4 students'
     data is deleted.

### Undoing a sec level update

1. Undoing a sec level update.
  1. Prerequisites: There is a sec level update that has previously been conducted, and has not been undone yet.
  2. Test case: `undolevel` <br>
     Expected: The state of the student level is reverted to how it was prior to the sec level update that was undone.

### Saving data

1. Dealing with missing data files
  1. Delete the data file. <br>
     Expected: The app launches normally and is pre-populated with a set of sample student data.

1. Dealing with corrupted data files
  1. Delete a random chunk of lines from the data file. <br>
     Expected: The app launches normally with an empty data file.

<div style="page-break-after: always;"></div>

## **Appendix: Effort**
If the implementation effort required to create AB3 from scratch is 10, we estimate that the effort we spent to create
Tutorium is 11.

### Difficulty level
Over the course of this project, each of us had to make design decisions when implementing the features we were
responsible for. For instance, the implementation of the filter feature involved the creation of several classes, each
representing a single predicate which could be used to filter the list of students. This was something we found challenging
as it was the first time most of us needed to make such choices. Hence, creating Tutorium was not an easy task by any means.

### Challenges faced
At the beginning of the project, we needed to refactor existing code from AB-3 by adding additional fields in order to
create our Student class from the original Person class. This resulted in dependencies, as we needed to wait for
the relevant refactoring to be complete before we could work on further enhancements and new features.
Learning how to use JavaFX to create new windows to display visuals such as graphs, charts and tables was
something we had to grapple with as well due to the steep learning curve. Furthermore, it involved UI changes,
which we could only test manually.

### Effort required
Tutorium was built upon AB-3 over a period of 5-6 weeks by our team. Overall, the 5 of us put in
our best effort to build a product that was optimised for our target user, tuition centre administrative staff, by
brainstorming suitable features and enhancements to add.
Every week, each of us would spend around 1-2 days adding our respective features. Furthermore, we also wrote automated
JUnit tests at each stage, expending plenty of effort on crafting a good variety of test cases to improve the effectiveness and efficiency of our tests.

### Achievements
Listed below are the enhancements we managed to add into Tutorium.
* Sort and filter features to aid users in organising student data.
* Import feature to help users transition easily from other student management applications.
* Data visualisation features (bar charts, tables and line graphs) added to enable users to conduct analysis on student data.
* Secondary school level (sec level) updating, to optimise the process of increasing students' sec levels at the beginning of each academic year.

<div style="page-break-after: always;"></div>

## **Appendix: Planned Enhancements**
* Improving the phrasing of messages displayed to the user.
  * Currently, some messages displayed to the user (such as error messages or success messages) are
    phrased awkwardly. We plan to rephrase these messages to provide a smoother experience for the user.
* Include validity checks for the nearest MRT station field.
  * At the moment, Tutorium accepts any user input for the nearest MRT station field, which can lead to
    incorrectly-entered data in the case of user error. In the future, we will introduce validity checks
    to reduce the likelihood of such mistakes.
* Disallow the use of invalid character inputs in the search command.
  * Although only alphanumeric names are allowed, the search command allows the user to search for names
     containing non-alphanumeric characters. Our team plans to improve the design of this feature by
     disallowing such inputs in a future version of the product.
* Add a validation check to the phone number field to accept only phone numbers with 8 digits.
  * Valid phone numbers in Singapore are 8 digits long, but Tutorium accepts any number that is longer than 3 digits, even
    if it would be considered an invalid phone number. We will add a validation check to restrict inputs to 8-digit phone numbers
    in future versions of the app.
* Standardise prefix for year in data visualisation features.
  * In the table and chart feature, the prefix used to indicate the year is `d/`, but for the line graph feature, it is `y/`. We
    plan to standardise the prefix used to `y/` to make these commands more intuitive and easier to learn.
