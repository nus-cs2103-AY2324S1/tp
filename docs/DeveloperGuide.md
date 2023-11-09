---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and
third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** above provides a high-level overview of the
application's architecture.

Below, you'll find a concise summary of the main components and their
interactions.

#### Main Components of Architecture

**`Main`** (comprising classes
[`Main`](https://github.com/AY2324S1-CS2103T-W17-2/tp/blob/master/src/main/java/seedu/letsgethired/Main.java)
and [`MainApp`](https://github.com/AY2324S1-CS2103T-W17-2/tp/blob/master/src/main/java/seedu/letsgethired/MainApp.java)
is responsible for launching and shutting down the application.

* On app launch, it initializes the other components in the correct sequence,
  and establishes connections between them.
* During shutdown, it closes other components and triggers cleanup operations
  when necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): Handles the interaction with User.
* [**`Logic`**](#logic-component): Executes user commands.
* [**`Model`**](#model-component): Manages the in-memory data of the app.
* [**`Storage`**](#storage-component): Handles reading and writing of data to
  the hard disk.

[**`Commons`**](#common-classes) encompasses a set of classes shared across
multiple components.

#### How Architecture Components Interact

The **Sequence Diagram** below illustrates the interactions between components
when the user issues the delete 1 command.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four primary components (as depicted in the diagram above),

* Defines its *API* through an `interface` bearing the same name as the
  component.
* Implements its functionality using a concrete `{Component Name}Manager`
  class that follows the corresponding API `interface` mentioned in the
  previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface
and implements its functionality using the `LogicManager.java` class which
adheres to the `Logic` interface. Other components interact with a given
component through its *interface* rather than the concrete class.
The reason for this practice is to decouple outside components from the
internal implementation of the component.
This decoupling is illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

For more comprehensive details on each component, please refer to the
sections below.

### UI Component

**API**:
[`Ui.java`](https://github.com/AY2324S1-CS2103T-W17-2/tp/blob/master/src/main/java/seedu/letsgethired/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI component comprises a `MainWindow` composed of various parts, such as the
`CommandBox`, `ResultDisplay`, `InternApplicationListPanel`, `StatusBarFooter`,
and more.
All of these elements, including the `MainWindow`, inherit from the
abstract `UiPart`class which encapsulates common characteristics among classes
representing different parts of the visible GUI.

The `UI` component leverages the JavaFx UI framework.
The layout of these UI parts are defined in matching `.fxml` files located in
`src/main/resources/view`.
For example, the layout of
the [`MainWindow`](https://github.com/AY2324S1-CS2103T-W17-2/tp/blob/master/src/main/java/seedu/letsgethired/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-W17-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands by interacting with
  the [`Logic` component](#logic-component).
* Monitors changes in the data within the `Model` to update the UI
  accordingly.
* Maintains a reference to the `Logic` component, since the `UI` relies on
  it for command execution.
* Depends on certain classes within the `Model` component, as it
  displays `InternApplication` objects residing in the `Model`.

### Logic component

**API**:
[`Logic.java`](https://github.com/AY2324S1-CS2103T-W17-2/tp/blob/master/src/main/java/seedu/letsgethired/logic/Logic.java)

The class diagram below provides a *partial* view of the `Logic` component.

<puml src="diagrams/LogicClassDiagram.puml" width="550"></puml>

To illustrate the interactions within the `Logic` component, we'll use the
`execute("delete 1")` API call as an example in the sequence diagram below:

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command"></puml>

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` and `DeleteCommand` 
should end at the destroy marker (X) but due to a limitation in PlantUML, 
the lifeline extends to the end of the diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to
   an `InternTrackerParser` object.
   The `InternTrackerParser` then creates a specific parser (
   e.g., `DeleteCommandParser`)
   tailored to match and parse the command.
2. This results in the creation of a `Command` object, specifically an object
   of one of its subclasses (e.g. `DeleteCommand`).
   This command is then executed by the `LogicManager`.
3. The executed command may interact with the `Model` when it is executed,
   such as to delete an `InternApplication` object from the `Model`.
4. The result of the command execution is encapsulated as a `CommandResult`
   object which is returned by `Logic`.

In addition to the class diagram above,
there are other classes (omitted from the class diagram above)
within the Logic component that are used for parsing user commands:

<puml src="diagrams/ParserClasses.puml" width="600"></puml>

How commands are parsed:

* When called upon to parse a user command, the `InternTrackerParser` class
  creates an `XYZCommandParser`
  (where `XYZ` is a placeholder for the specific command name
  e.g., `AddCommandParser`).
  The `XYZCommandParser` then uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`).
  This `XYZCommand` is then returned as a `Command` object by
  the `InternTrackerParser`.
* All `XYZCommandParser` classes (
  e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from
  the `Parser` interface, allowing them to be treated similarly where possible,
  especially during testing.

### Model component

**API**:
[`Model.java`](https://github.com/AY2324S1-CS2103T-W17-2/tp/blob/master/src/main/java/seedu/letsgethired/model/Model.java)

The class diagram below provides an overview of the `Model` component:

<puml src="diagrams/ModelClassDiagram.puml" width="450"></puml>

The `Model` component has the following responsibilities:

* Stores the intern tracker data, which includes all `InternApplication`
  objects.
  These objects are stored in a `UniqueApplicationList` container.
* Maintains a separate filtered, sorted list of 'selected'
  `InternApplication` objects, which is exposed to outsiders as an
  unmodifiable `ObservableList<InternApplication>`.
  This list can be observed, allowing the UI to automatically update
  when its contents change (e.g. after a `find` or `sort` command).
* Stores a `UserPref` object that represents the user’s preferences. This is
  exposed as a `ReadOnlyUserPref` object.
* Importantly , the `Model` does not depend on any of the other 3 components
  (`UI`, `Logic`, `Storage`).
  Since the `Model` represents data entities of the domain, it's designed
  to stand independently of the other components.

### Storage component

**API**:
[`Storage.java`](https://github.com/AY2324S1-CS2103T-W17-2/tp/blob/master/src/main/java/seedu/letsgethired/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550"></puml>

The `Storage` component,

* Can save both intern tracker data and user preference data in JSON format
* Read data from JSON files and convert them into corresponding objects within
  the application.
* Inherits from both `InternTrackerStorage` and `UserPrefStorage`, which allows
  it to be used as either one, depending on the functionality needed.
* Depends on some classes in the `Model` component (because the `Storage`
  component's job is to save/retrieve objects that belong to the `Model`).

### Common classes

Classes that are used across multiple components are located within the
`seedu.letsgethired.commons` package.
These common classes served as shared utilities and functionalities that
contribute to the application's overall functionality.

For example, the `seedu.letsgethired.commons.util.StringUtil` class provides
commonly used string manipulation methods.

--------------------------------------------------------------------------------------------------------------------

## **Noteworthy Feature Implementations**

This section describes some noteworthy details on how certain features are
implemented.

### Add command

The `add` command enables users to add new internship application into the
Intern Tracker.

#### Implementation

Adding a new internship application involves executing the `AddCommand`.

* The `AddCommand` is parsed by the `AddCommandParser`.
* `AddCommandParser#parse()` parses the user input and returns an `AddCommand`
  object for execution.

Given below is an example usage scenario outlining how the `add` command
behaves at each step.

**Step 1.** The user executes

```shell
`add n/Jane Street r/Full Stack Developer c/Summer 2024`
```

This command adds a new `InternshipApplication` into the `InternTracker`.

<box type="info" seamless>

**Note:** Some fields for the `add` command are optional. For more information
on the `add` command, refer to
the [User Guide](UserGuide.md#adding-your-new-internship-application-add).
</box>

**Step 2.** The `AddCommandParser` parses the arguments, ensuring that
compulsory fields are present and valid.
It then creates a new `InternApplication` object with all the provided fields
and returns an `AddCommand` object containing the
`InternApplication` object ready to be added to the `Model`.

**Step 3.** The `AddCommand#execute()` method is invoked by the `LogicManager`.
The `AddCommand#execute()` calls the `Model#addInternApplication` method to add
the newly created `InternApplication` object to the `Model`.

**Step 4.** The `AddCommand#execute()` method returns a new `CommandResult`
object that contains feedback and display messages for the user.
This result is then handed back to the `LogicManager`.

The sequence diagram below shows the process of adding a new internship
application:

<box type="info" seamless>

**Note:** The input for the `AddCommand` has been truncated for brevity.

</box>

<puml src=diagrams/AddSequenceDiagram.puml></puml>

<box type="info" seamless>

**Note:** The lifeline for `AddCommandParser` and `AddCommand` 
should end at the destroy marker (X)
but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

#### Design Considerations

**Aspect:** Criteria for a identifying duplicate internship application entry:

* **Alternative 1 (current choice):** The combination of
  (`COMPANY_NAME`, `ROLE` and `CYCLE`) must be unique.
    * Pros: Allow users to add an internship application to the same company for
      different roles or during different cycles.
    * Cons: Users are required to input additional compulsory fields.
* **Alternative 2:** Enforce uniqueness based only on `COMPANY_NAME`
    * Pros: Allow users to add a new internship application quickly.
    * Cons: It restricts users to having only 1 internship application with a
      given company.

### Find command

The `find` command enables the users to search internship applications by
looking up a keyword in any of the fields associated with
an internship application.

#### Implementation

Performing a `Find` operation involves executing the `FindCommand.

* The `FindCommand` is parsed by the `FindCommandParser`.
* `FindCommandParser#parse()` parses the user input to return a `FindCommand`
  object that will be executed.

Given below is an example usage scenario and how the mechanism behaves at each
step.

**Step 1.** The user keys in the command word to find an internship
application, `find`.

```shell
find n/Jane c/Summer
```

<box type="info" seamless>

**Note:** At least one field to be searched and the respective keyword
for searching should be provided.

</box>


**Step 2.**
The `FindCommandParser` will execute to get the field and keyword pairs
using the `ArgumentTokenizer`.
The parser creates a new `CompanyContainsFieldKeywordsPredicate` object,
which acts as a predicate that will be used to obtain a filtered list of
internship applications that have the specified keywords in respective fields.
The parser returns a `FindCommand` object with
the `Predicate<InternApplication>`, specifically the
`CompanyContainsFieldKeywordsPredicate`.

**Step 3.** The `FindCommand#execute()` method is invoked by the `LogicManager`.
The `FindCommand` calls the
`Model#updateFilteredInternApplicationList()` to update the filtered internship
application list using the
`Predicate<InternApplication>`, and then
the `Model#getFilteredInternApplicationList()` to obtain the filtered, sorted
internship application list.

**Step 4.** Finally, the `FindCommand` creates a `CommandResult` object that
contains feedback and displays the filtered internship application list to the
user.
The result is then returned to the `LogicManager`.

The sequence diagram below shows the process of finding internship applications.

<puml src="diagrams/FindCommandSequenceDiagram.puml" alt="Find Command Sequence Diagram"></puml>

<box type="info" seamless>

**Note:** The lifeline for `FindCommandParser` and `FindCommand` 
should end at the destroy marker (X)
but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

### Note command

The note command enables the users to add or delete notes to an internship
application.

#### Implementation

To add or delete a `Note`, the `NoteCommand` must be executed.

It is worth noting that the NoteCommand is implemented differently from the
other Commands.

<puml src="diagrams/NoteCommandClassDiagram.puml" alt="Note Insert Command Class Diagram"></puml>

From the above class diagram, the `NoteCommand` is an abstract class that is
inherited by the `NoteInsertCommand` class
and the `NoteDeleteCommand` class. This is done so that
the `NoteCommand#execute()` method can behave accordingly while
still having both `NoteCommand` classes share the command word that is 'note'.

The `NoteCommand` is parsed by
the `NoteCommandParser`.`NoteCommandParser#parse()` parses the user input to
return
either a `NoteInsertCommand` or a `NoteDeleteCommand` object that will be
executed.

Given below is an example usage scenario and how the mechanism behaves at each
step for **note addition**.

**Step 1.** The user keys in the command word to add a note, `note`.

```shell
note 1 i/Need to revise Rust
```

This command appends the note to the internship application with the index `1`.

<box type="info" seamless>

**Note:** The `INDEX` for the `note` command is compulsory.
The `i/` prefix is compulsory for adding a note, and cannot appear at the same
time as the `o/` prefix for deleting a note.
For more information on the `note` command, refer to the
[User Guide](UserGuide.md#adding-notes-to-your-internship-application--note).
</box>

**Step 2** The `NoteCommandParser` parses the arguments, ensuring that
the compulsory fields are present.
In particular, the parser checks that the `i/` prefix is present **and**
the `o/` prefix is absent.
The parser then returns a `NoteInsertCommand` object containing the` Note`
object ready to be added into `Model`

**Step 3.** The `NoteCommand#execute()` method is invoked by the `LogicManager`.
The `NoteCommand#execute()`
method creates a new `InternshipApplication` with the note appended to its
`ArrayList` of notes.

**Step 4.** The `NoteCommand` calls the `Model#setInternApplication()`
and `Model#updateFilteredInternApplicationList()`
methods to add the new internship application with the note to the model, and
replace the old internship application.

**Step 5.** The `NoteCommand` creates a `CommandResult` object that contains the
feedback and `InternApplication` to the user,
which is returned to the `LogicManager`.

The sequence diagram below shows the process of adding a note:

<puml src="diagrams/NoteInsertCommandSequenceDiagram.puml" alt="Note Insert Command Sequence Diagram"></puml>

<box type="info" seamless>

**Note:** The lifeline for `NoteCommandParser` and `NoteInsertCommand` 
should end at the destroy marker (X)
but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

---

Given below is an example usage scenario and how the mechanism behaves at each
step for **note deletion**.

**Step 1.** The user keys in the command word to add a note, `note`.

```shell
note 1 o/2
```

This command deletes the note with index `2` from the internship application
with index `1`.

<box type="info" seamless>

**Note:** The `INDEX` for the `note` command is compulsory.
The `o/` prefix is compulsory for deleting a note, and cannot appear at the same
time as the `i/` prefix for adding a note.
For more information on the `note` command, refer to the
[User Guide](UserGuide.md#deleting-a-note).
</box>

**Step 2.** The `NoteCommandParser` parses the arguments and ensures
that the compulsory fields are present.
In particular, the parser needs to check if the `o/` prefix
is present **and** the `i/` prefix is absent.
The parser then returns a `NoteDeleteCommand` object with the `Index` of the
object to be deleted from the `Model`.

**Step 3.** The `NoteCommand#execute()` method is called by the `LogicManager`.
The `NoteCommand#execute()`
method creates a new `InternshipApplication` with the corresponding note removed
from its `ArrayList` of notes.

**Step 4.** The `NoteCommand` calls the `Model#setInternApplication()`
and `Model#updateFilteredInternApplicationList()`
methods to add the new internship application with the note to the model, and
replace the old internship application.

**Step 5.** The `NoteCommand` creates a `CommandResult` object that contains
feedback and `InternApplication` to the user,
which is returned to the `LogicManager`.

The sequence diagram below shows the process of deleting a note.

<puml src="diagrams/NoteDeleteCommandSequenceDiagram.puml" alt="Note Delete Command Sequence Diagram"></puml>

<box type="info" seamless>

**Note:** The lifeline for `NoteCommandParser` and `NoteDeleteCommand` 
should end at the destroy marker (X)
but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

### Sort Command

The `sort` command enables the users to sort the internship applications by
a specific field.

<box type="info" seamless>

**Note:** This implementation does not allow for sorting by multiple fields.

</box>

#### Implementation

To introduce the `sort` feature, we make the following changes to the codebase:

1. **Extension of the Model Interface**: We enhance the `Model` interface to
   include a new method:
    * `Model#updateFilteredSortedInternApplicationList(Comparator<InternApplication> comparator))`
    * This method sorts the intern application list by the given comparator.

2. **ModelManager Enhancements**: In the `ModelManager` class, we wrap the
   existing `filteredInternApplications` in a
   JavaFX `SortedList` to create a new `filteredSortedInternApplications` field.
   This new field, allows us to sort the intern applications to be displayed
   based on the specified comparator.

3. **Update existing methods:
    * `Model#getFilteredInternApplicationList()` is updated to return
      the filtered and/or sorted intern application list.

<box type="info" seamless>

**Note:** JavaFX's `SortedList` class extends the `ObservableList` class, which
ensures that any changes made to either the `FilteredList` or the original
`UniqueApplicationList` will automatically propagate to the UI.

</box>


To understand how these changes are integrated into the application, refer to
the sequence diagram below:

<puml src="diagrams/SortSequenceDiagram.puml" alt="SortSequenceDiagram"></puml>

<box type="info" seamless>

**Note:** The lifeline for `SortCommandParser` and `SortCommand`
should end at the destroy marker (X)
but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

#### Design Considerations

**Aspect: How sorting is done**

* **Alternative 1 (current choice):** Wrap the existing `FilteredList` in
  a `SortedList`.
    * Pros: More flexible, as we can both sort and filter without modifying the
      original `UniqueApplicationList`.
* **Alternative 2:** Sort the applications in the `UniqueApplicationList` in
  the `InternTracker`.
    * Cons: Requires us to revert the sorting done by
      the `UniqueApplicationList` before saving the data to
      preserve the initial order in which the intern applications are added.

**Aspect: Comparator**

* **Alternative 1:** Use a `Comparator<InternApplication>` object.
    * Pros: More flexible, as we can sort the intern applications by different
      comparators.
    * Cons: Requires us to create a new `Comparator<InternApplication>` object
      for each sorting operation.
    * Difficult to test for functional equality, as `equals()` method
      for `Comparator` only tests for referential equality.
* **Alternative 2 (current choice):** Wrap the comparator in
  an `InternApplicationComparator` class
    * Pros: Allows us to reuse the same comparator for different sorting
      operations, which makes it easier to test for equality.
    * Allows us to strictly define which comparators are allowed.

**Aspect: How feedback and details are returned from CommandResult**

* **Alternative 1 (current choice):** Separate feedback and details into 2
  separate strings `feedbackToUser` and `detailsToUser`.
    * Pros: Clearer and intuitive for future developers to know the content
      which each string parameter should contain.
    * Cons: Additional parameters in the arguments might make code look
      complicated.
* **Alternative 2:** Have the feedback String contain the content for both
  feedbackToUser and detailsToUser through parsing.
    * Pros: Easier to implement.
    * Cons: Requires future developers working on the code to be mindful of how
      the String input should be structured for successful parsing

### Undo command

#### Implementation

The undo mechanism is facilitated by `VersionedInternTracker`. It
extends `InternTracker` and maintains an undo history internally as a
stack named `savedStates`.

Additionally, it implements the following operations:

* `VersionedInternTracker#commit()`— Saves the current intern tracker state in
  its history.
* `VersionedInternTracker#undo()`— Restores the previous intern tracker state
  from its history.

VersionedInternTracker#undo() is exposed in the `Model` interface
as `Model#undoAction()`.

Given below is an example usage scenario and how the undo mechanism behaves at
each step.

**Step 1.** The user launches the application.
The `VersionedInternTracker` will be initialized with an empty `savedStates`.

<puml src="diagrams/UndoState0.puml" alt="UndoState0"></puml>

**Step 2.** The user executes `delete 5` command to delete the 5th
internApplication
from the intern tracker.
The `delete` command calls `Model#deleteInternApplication()`, which
calls `VersionedInternTracker#commit()`,
capturing a copy of the current `internApplications` to `savedStates` before
executing the delete action.

<puml src="diagrams/UndoState1.puml" alt="UndoState1"></puml>

**Step 3.** The user adds a new internship application.

```shell
add n/Google r/Software Engineer c/Summer 2024
```

The `add` command calls `Model#add()` which also calls
`VersionedInternTracker#commit()`,
adding a copy of the current `internApplications` to `savedStates`
before executing the add action.

<puml src="diagrams/UndoState2.puml" alt="UndoState2"></puml>

<box type="info" seamless>

**Note:** If a command execution fails, the
`internApplications` state will not be added to `savedStates`
(i.e. `VersionedInternTracker#commit()` will not be called).

</box>

**Step 4.** The user now decides that adding the internApplication was a
mistake,
and decides to undo that action
by executing the `undo` command. The `undo` command will
call `Model#undoAction()`, which pops the latest
`internApplications` state from `savedStates` and assigns it to the current
internApplications.

<puml src="diagrams/UndoState3.puml" alt="UndoState3"></puml>


<box type="info" seamless>

**Note:** If the size of `savedStates` is 0, meaning the stack is empty, then
there are no previous
internApplications states to restore. The `undo` command
calls `VersionedInternTracker#undo()`, which returns
False if there are no states to restore, and displays a message to the user that
the latest change has already been reached.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram"></puml>

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X)
but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

**Step 5.** The user then decides to execute the command `list`. Commands that
do
not modify the intern tracker,
such as `list`, will usually not
call `VersionedInternTracker.commit()`, `Model#undoAction()`
Thus, the `savedStates` remains unchanged.

<puml src="diagrams/UndoState4.puml" alt="UndoState4" />

The following activity diagram summarizes what happens when a user executes a
new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="600"></puml>

#### Design considerations:

**Aspect: How undo executes:**

* **Alternative 1 (current choice):** Saves the entire intern tracker.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage, especially
      if many operations are carried out.

* **Alternative 2:** Implement an `undo` operation
  for each individual command.
    * Pros: This method conserves memory (e.g. for `delete`, it only saves the
      `internApplication` being deleted).
    * Cons: It requires updating the interface for `Command` and an
      implementation for each individual command.

**Aspect: How history is implemented:**

* **Alternative 1:** Using a list and pointer to implement version control
    * Pros: Will allow for more feature-rich commands, such as `redo`.
    * Cons: Uses more memory than a stack, as saved states are still stored
      after the undo operation.

* **Alternative 2:** Store the saved state in a stack
    * Pros: Will use less memory, as saved states are popped off the stack
      after `undo`.

### Click InternApplication Card

This feature allows the user to click on an `InternApplicationCard` in the
`InternApplicationListPanel`to display the details of the `InternApplication`.

#### Implementation

The Card click mechanism is implemented by creating a new TextArea
widget beside the `InternApplicationListPanel`
and a handler function to handle the event of a card click.

The following class is created:

* `SelectView` - This class represents the SelectView Text Area responsible for
  displaying the details of the card.

The following methods are added:

* `SelectView#displayDetails(String details))` -
  sets the provided string details as text in the `TextArea`.
* `Messages#formatDisplay(InternApplication internApplication)` -
  returns a customised string summarising the details of the `InternApplication`
* `SelectView#handleCardClick()` - extract the details from the
  `InternApplication` object and sets them in the `SelectView` widget.

Additionally, the following method is renamed for clarity:

* `Messages#format(InternApplication internApplication)` is renamed
  to `Messages#formatDisplay(InternApplication internApplication)` - returns the
  feedback from an executed command

The following sequence diagram shows how Card Click feature works:

<puml src="diagrams/SelectViewSequenceDiagram.puml" alt="SelectViewSequenceDiagram"></puml>
 
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

* CS undergraduate
* is looking for tech internships
* needs something to organise their internship applications
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: provides a fast and organized way to see internships and
its progress, optimized for users who prefer a CLI

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (
unlikely to have) - `*`

| Priority | As a ...                    | I want to ...                                                | So that I can...                                                              |
|----------|-----------------------------|--------------------------------------------------------------|-------------------------------------------------------------------------------|
| `* * *`  | user                        | add internship applications                                  | keep a record of my internship application                                    |
| `* * *`  | user                        | see a list of internships that I have applied for            | keep track of all companies/roles I have applied for                          |
| `* * *`  | user                        | view a specific internship application                       | easily access details about a specific internship application                 |
| `* * *`  | user                        | delete an internship application                             | remove internship applications I do not want to track anymore                 |
| `* * *`  | user                        | update the status of the internships that I have applied for | keep track of the progress of the roles I have applied for                    |
| `* * *`  | user                        | open the app with a click of a button or an exe/batch file   | save time and easily access the internship tracker                            |
| `* *`    | user with many applications | quickly search for a application                             | efficiently find the entry I am looking for                                   |
| `* *`    | conscientious user          | attach notes to each application                             | I can jot down important information about the company or application process |
| `* *`    | organised user              | sort my applications                                         | I can easily get an organised view of my applications                         |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `LetsGetHired` application
and the **Actor** is the `User`, unless specified otherwise)

**UC1: Add an internship application**

**MSS**

1. User inputs the details of the internship application.
2. LetsGetHired adds the internship application to the list of internship
   applications and displays it.

   Use case ends.

**Extensions**

* 1a. The command format is incorrect.

    * 1a1. LetsGetHired notifies the user of the error.
    * 1a2. User enters the correct details for the internship application.
      Use case resumes from Step 2.

* 1b. The internship application already exists in the list of internship
  applications.

    * 1b1. LetsGetHired notifies the user that the internship application
      already exists.
      Use case ends.

**UC2: View the list of internship applications**

**MSS**

1. User requests to list all internship applications.
2. LetsGetHired displays all the internship applications.

   Use case ends.

**UC3: View an internship application**

**MSS**

1. User requests to <ins>list all internship applications (UC2)</ins>
2. User requests to view a specific internship application.
3. LetsGetHired displays the required internship application.

   Use case ends.

**Extensions**

* 1a. LetsGetHired displays an empty list.

  Use case ends.

* 2a. The command format is incorrect.

    * 2a1. LetsGetHired notifies the user of the error.
      Use case resumes from Step 2.

* 2b. The index of internship application entered does not exist.

    * 2b1. LetsGetHired shows an error message.
      Use case resumes from Step 2.

**UC4: Delete an internship application**

**MSS**

1. User requests to <ins>list all internship applications (UC2)</ins>.
2. User requests to delete a specific internship application.
3. LetsGetHired deletes the required internship application.

Use case ends.

**Extensions**

* 1a. The list is empty

  Use case ends.

* 2a. The command format is incorrect.

    * 2a1. LetsGetHired notifies the user of the error.
      Use case resumes from Step 2.

* 2b. The index of internship application entered does not exist

    * 2b1. LetsGetHired shows an error message.
      Use case ends.

**UC5: Update the status of an internship applications**

**MSS**

1. User requests to <ins>list all internship applications (UC2)</ins>.
2. User requests to update the status of a specific internship application.
3. LetsGetHired updates the required internship application.
4. LetsGetHired displays the updated internship application.

Use case ends.

**Extensions**

* 1a. The list is empty

  Use case ends.

* 2a. The command format is incorrect.

    * 2a1. LetsGetHired notifies the user of the error.
      Use case resumes from Step 2.

* 2b. The index of internship application entered does not exist

    * 2b1. LetsGetHired shows an error message.
      Use case ends.

* 2c. The entered status is invalid.

    * 2c1. LetsGetHired shows an error message guiding the user on acceptable
      status values.

      Use case ends.

**UC6: Search for an application**

**MSS**

1. User requests for a search by specific criteria.
2. LetsGetHired shows a list of matching applications

* 1a. The command format is incorrect.

    * 1a1. LetsGetHired notifies the user of the error.

      Use case resumes from Step 1.

**UC7: Edit the details of an application**

**MSS**

1. User requests to <ins>list all internship applications (UC2)</ins>.
2. User provides new information to edit the chosen application.
3. LetsGetHired edits the application and displays it.

   Use case ends.

**Extensions**

* 1a. The list is empty

  Use case ends.

* 2a. The command format is incorrect.

    * 2a1. LetsGetHired notifies the user of the error.
      Use case resumes from Step 2.

* 2b. The index of internship application entered does not exist

    * 2b1. LetsGetHired shows an error message.
      Use case ends.

**UC8: Add a note to an application**

**MSS**

1. User requests to <ins>list all internship applications (UC2)</ins>.
2. User provides note to add to specific application.
3. LetsGetHired adds the note to the application and displays the application.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The command format is incorrect.

    * 2a1. LetsGetHired notifies the user of the error.
      Use case resumes from Step 2.

* 2b. The index of internship application entered does not exist.

    * 2b1. LetsGetHired shows an error message.
      Use case ends.

**UC9: Delete a note on an application**

**MSS**

1. User requests to <ins>view a specific application (UC3)</ins>.
2. User requests to delete a specific note from the application.
3. LetsGetHired deletes the note from the application and displays
   the updated application.

   Use case ends.

**Extensions**

* 1a. The application has no notes.

  Use case ends.


* 2a. The command format is incorrect.

    * 2a1. LetsGetHired notifies the user of the error.
      Use case resumes from Step 2.

* 2b. The index of internship application entered does not exist.

    * 2b1. LetsGetHired shows an error message.
      Use case ends.

* 2c. The given note index is invalid.

    * 2c1. LetsGetHired shows an error message.
      Use case resumes from step 2.

**UC10: Sort Applications**

**MSS**

1. User requests to sort the applications by provided category and order.
2. LetsGetHired sorts and displays the sorted applications.
   Use case ends.

**Extensions**

* 1a. Provided category or order is invalid

    * 1a1 LetsGetHired shows an error message and shows the valid categories and
      orders to choose from.
      Use case ends.

### Non-Functional Requirements

#### Technical

* Should work on any _mainstream OS_ as long as it has Java `11` or above
  installed.
* The system should not require an internet connection to operate.

#### Performance

* Should be able to hold up to 1000 internships without noticeable
  sluggishness in performance for typical usage.
* The system should respond to user interactions within 1 second for normal
  operations (e.g. adding an internship, searching for an internship) for a
  smooth user experience.

#### Usability

* A user with above average typing speed for regular English text (i.e. not
  code, not system admin commands) should be able to accomplish most of the
  tasks faster using commands than using the mouse.
* Users should be able to use shortcuts for frequently performed tasks to save
  time.

#### Privacy

* A user's application info should be stored offline, and not uploaded to any
  form of cloud storage.

*{More to be added}*

### Glossary

* **Application Status:** The current stage or status of an internship
  application.
* **Internship Cycle:** The period of time during which an internship
  is set to take place (e.g. Summer 2024, Winter 2023).
* **Internship Application:** A record or entry in the system that represents
  information about an internship opportunity. It includes details such as
  company name, application status, deadline, and notes.
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Sample Data:** Pre-populated data used for demonstration purposes, enabling
  new users to see how the application functions with realistic examples.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work
on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    2. Type the following command in your terminal:

```shell
java -jar letsgethired.jar
```

Expected: Shows the GUI with a set of sample
internship applications. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different
       location. Close the window.

    1. Re-launch the application by typing the following command in your
       terminal:

```shell
java -jar letsgethired.jar
```

Expected: The most recent window size and location is retained.

1. _{ more test cases ... }_

### Deleting an `internApplication`

1. Deleting an `internApplication` while all `internApplication`s are being
   shown

    1. Prerequisites: List all `internApplication`s using the `list` command.
       Multiple `internApplication`s in the list.

    1. Test case: `delete 1`<br>
       Expected: First intern application is deleted from the list.
       Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No `internApplication` is deleted. Error details shown in the
       status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (
       where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases ... }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected
       behavior}_

1. _{ more test cases ... }_
