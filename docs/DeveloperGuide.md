---
layout: page
title: Developer Guide
---

# ClubMembersContact

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
  original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [
_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create
and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of
classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is
in charge of the app launch and shut down.

* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts
e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the
visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API
** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API
call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates
   a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which
   is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API
** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

### Storage component

**API
** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

* can save both address book data and user preference data in JSON format, and read them back into corresponding
  objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add `Member`/`Applicant` feature

The `addMember` and `addApplicant` command is used to add a member or an applicant to the address book.
The commands are implemented in the `AddMemberCommand` and `AddApplicantCommand` class, which extends the `Command`
class.

The add mechanism is facilitated by `LogicManager` which parses the command input from the user to determine the
appropriate
command to execute. The execute function checks whether the `member`/`applicant` is present in the `AddressBook`.
The `member`/`applicant` is added into the `AddressBook` if it is not present. Otherwise, an error message is returned.

### Delete an applicant

The applicant at the specific `applicantIndex` in the applicant list will be deleted. Compulsory fields for the
`DeleteApplicantCommand` include: `applicantIndex`. An example of how this feature can be used is as follows:

* Step 1. The `DeleteApplicantCommand` object's `execute()` method is called.
* Step 2. The `applicantIndex` is checked to be within the valid range of the applicant list. If the `applicantIndex`
  given
  is invalid, a `CommandException` is thrown.
* Step 3. The applicant at the given `applicantIndex` is referenced.
* Step 4. The model object's `deleteApplicant()` method is called. The input parameter is the referenced applicant.
* Step 5. The applicant is deleted from the applicant list.

### Delete a member

The member at the specific memberIndex in the member list will be deleted. Compulsory fields for
the `DeleteMemberCommand`
include: `memberIndex`. An example of how this feature can be used is as follows:

* Step 1. The `DeleteMemberCommand` object's `execute()` method is called.
* Step 2. The `memberIndex` is checked to be within the valid range of the member list. If the `memberIndex` given is
  invalid, a `CommandException` is thrown.
* Step 3. The member at the given `memberIndex` is referenced.
* Step 4. The model object's `deleteMember()` method is called. The input parameter is the referenced member.
* Step 5. The member is deleted from the member list.

### Delete an applicant

The applicant at the specific `applicantIndex` in the applicant list will be deleted. Compulsory fields for the
`DeleteApplicantCommand` include: `applicantIndex`. An example of how this feature can be used is as follows:

* Step 1. The `DeleteApplicantCommand` object's `execute()` method is called.
* Step 2. The `applicantIndex` is checked to be within the valid range of the applicant list. If the `applicantIndex`
  given
  is invalid, a `CommandException` is thrown.
* Step 3. The applicant at the given `applicantIndex` is referenced.
* Step 4. The model object's `deleteApplicant()` method is called. The input parameter is the referenced applicant.
* Step 5. The applicant is deleted from the applicant list.

### Find an applicant

All applicants that contains any field with the specified keyword will be listed. Compulsory fields for the
`FindApplicantCommand` include: `keyword`. An example of how this feature can be used is as follows:

* Step 1. The `FindApplicantCommand` object's `execute()` method is called.
* Step 2. The model object's `updateFilteredApplicantList()` is called, with the keyword as the predicate.
* Step 3. The applicant list is filtered to only reflect applicants with fields (name, phone number) that contain the
  keyword.
* Step 4. The filtered member list is shown.

### Find a member

All members that contains any field with the specified keyword will be listed. Compulsory fields for
`FindApplicantCommand` include: `keyword`. An example of how this feature can be used is as follows:

* Step 1. The `FindMemberCommand` object's `execute()` method is called.
* Step 2. The model object's `updateFilteredMemberList()` is called, with the keyword as the predicate.
* Step 3. The member list is filtered to only reflect members with fields (name, email, phone number, telegram handle,
  tag) that contain the keyword.
* Step 4. The filtered member list is shown.

### Edit a member

Edits the details of an existing member identified by their index number in the displayed member list. Existing values
will be overwritten by the input values.

* Step 1: The `EditMemberCommand` object's `execute()` method is called.
* Step 2: The member index is checked to be within the valid range of the member list. If the member index given is
  invalid (e.g., out of range), a `CommandException` is thrown.
* Step 3: The member at the given index is referenced based on the provided member index.
* Step 4: The `EditMemberCommand` calls the model object's `setMember()` method. It updates the member with the new
  details
  provided, effectively modifying the existing member's information.
* Step 5: After the execution of the `EditMemberCommand`, the member's details are successfully edited in the member
  list.

### Edit an applicant

Edits the details of an existing applicant identified by their index number in the displayed applicant list. Existing
values will be overwritten by the input values.

* Step 1: The `EditApplicantCommand` object's `execute()` method is called.
* Step 2: The applicant index is checked to be within the valid range of the applicant list. If the applicant index
  given is invalid (e.g., out of range), a `CommandException` is thrown.
* Step 3: The applicant at the given index is referenced based on the provided applicant index.
* Step 4: The `EditApplicantCommand` calls the model object's `setApplicant()` method. It updates the member with the
  new
  details provided, effectively modifying the existing applicant's information.
* Step 5: After the execution of the `EditApplicantCommand`, the applicant's details are successfully edited in the
  applicant list.

### View all members

Lists all members in the address book to the user; e.g., If previous list was filtered (say by `FindMemberCommand`),
then set it to unfiltered again.

* Step 1: The `ViewMembersCommand` object's execute() method is called.
* Step 2: The model object's `updateFilteredMemberList()` method is called with the predicate as always returning true.
* Step 3: All members in the address book are shown to the user in the members list.

### View all applicants

Lists all applicants in the address book to the user; e.g., If previous list was filtered (say
by `FindApplicantCommand`), then set it to unfiltered again.

* Step 1: The `ViewApplicantsCommand` object's execute() method is called.
* Step 2: The model object's `updateFilteredApplicantList()` method is called with the predicate as always returning
  true.
* Step 3: All applicants in the address book are shown to the user in the applicants list.

### Copy a member

Copies the details of an existing member identified by their index number in the displayed member list into the
clipboard.

* Step 1: The `CopyMemberCommand` object's execute() method is called.
* Step 2: The member index is checked to be within the valid range of the member list. If the member index given is
  invalid (e.g., out of range), a `CommandException` is thrown.
* Step 3: The member at the given index is referenced based on the provided member index.
* Step 4: The `CopyMemberCommand` calls the copies the member's details given by the `Member` `detailsToCopy()` method
  into
  the clipboard.

### Copy an applicant

Copies the details of an existing applicant identified by their index number in the displayed applicant list into the
clipboard.

* Step 1: The `CopyApplicantCommand` object's `execute()` method is called.
* Step 2: The applicant index is checked to be within the valid range of the applicant list. If the applicant index
  given is invalid (e.g., out of range), a `CommandException` is thrown.
* Step 3: The applicant at the given index is referenced based on the provided applicant index.
* Step 4: The `CopyApplicantCommand` calls the copies the applicant's details given by the `Applicant` `detailsToCopy()`
  method into the clipboard.

### \[Proposed\] View all available tags

The view tags mechanism lists all available tags in the address book that a user can use to tag a member.
The `ViewTagsCommand` object's `execute()` method is called. All available tags in the address book are shown to the 
user in the tags list.

<img src="images/TagListClassDiagram.png" width="282" alt="TagListClassDiagram"/>

The implementation will follow the Observer design pattern, where the UI will observe the changes made to the list of tags
available for the user to tag other members. The `TagsListPanel` will implement the `Observer` interface and the 
`TagList` class will have a method `notifyUis()` to notify the `Observer` of its changes and an `addUi()` method to
add classes implementing `Observer` that will be updated of its changes.

### \[Proposed\] Allocating tasks to Members

#### Proposed Implementation

The proposed allocating tasks to `Member` objects is implemented using either `ToDo` or `Deadline` or `Events` object.
They extend from the `Tasks` class. A `Tasklist` object will be instantiated for each `Member` object, used to store the
list of tasks assigned to each individual. Additionally, it implements the following operations:

* `ToDo#markAsDone()`— Will be used to mark the todo of each Member as done
* `ToDo#markAsUnDone()`— Will be used to mark the todo of each Member as undone
* `Deadline#markAsDone()`— Will be used to mark the deadline of each Member as done
* `Deadline#markAsUnDone()`— Will be used to mark the deadline of each Member as done
* `Event#markAsDone()`— Will be used to mark the deadline of each Member as done
* `Event#markAsUnDone()`— Will be used to mark the deadline of each Member as done

These operations are exposed in the `Task` parent class as `Task#markAsDone()` and `Task#markAsUnDone()` to execute the
above-mentioned operations.

Step 1: The user adds a new `Member` using the `addMember` command. At this point, a `TaskList` instance will be 
assigned to that member.

Step 2: When the user uses the `addToDo` command, a `ToDo` object containing the details parsed in through the code will
be stored and under the user identified by their telegram handle, which is passed as a parameter. It namely stores the 
`Tasks.taskName`.

Step 3: When the user uses the `addDeadline` command, a `Deadline` object containing the details parsed in through the code will
be stored and under the user identified by their telegram handle, which is passed as a parameter. It namely stores the 
`Tasks.taskName`, `Deadline.dueDate` and `Deadline.dueTime`.

Step 4: When the user uses the `addEvent` command, an `Event` object containing the details parsed in through the code will
be stored and under the user identified by their telegram handle, which is passed as a parameter. It namely stores the
`Tasks.taskName`, `Event.startDate`, `Deadline.startTime`, `Event.endDate` and `Deadline.endTime`.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo
history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedAddressBook#commit()`— Saves the current address book state in its history.
* `VersionedAddressBook#undo()`— Restores the previous address book state from its history.
* `VersionedAddressBook#redo()`— Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()`
and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command
calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes
to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also
calls `Model#commitAddressBook()`, causing another modified address book state to be saved into
the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing
the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once
to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such
as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`.
Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not
pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

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

* is a member of an organisation
* has a responsibility in managing applicants who are applying into the organisation
* has a need to manage a significant number of contacts
* prefers data to be organized and separated into categories
* prefers desktop apps over other types
* prefers typing to mouse interactions
* prefers to use a separate app that is made to manage CCA-related contacts efficiently
* is reasonably comfortable using CLI apps

**Value proposition**: categorises contacts into 'members' and 'applicants' groups, allowing for easier management of
contacts

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​                                                | So that I can…​                                                   |
|----------|---------|-------------------------------------------------------------|-------------------------------------------------------------------|
| `* * *`  | user    | add existing members' contact                               | get the contact of existing members to contact them               |
| `* * *`  | user    | view existing members' contact                              | see a list of all current members in the organisation             |
| `* * *`  | user    | delete an existing members' contact                         | remove existing members' contacts that should be removed          |
| `* * *`  | user    | add prospective members' contact                            | get the contact of applicants to contact them for further updates |
| `* * *`  | user    | view prospective members' contact                           | see a list of all applicants                                      |
| `* * *`  | user    | delete a prospective members' contact                       | remove applicants' contacts that should be removed                |
| `* *  `  | user    | schedule a time and date for an interview with an applicant | mark out a time period of a specific date for an interview        |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `ClubMembersContact` and the **Actor** is the `user`, unless specified
otherwise)

**Use case: Schedule a date for an interview**

**MSS**

1. User requests to list applicants
2. ClubMembersContact shows a list of applicants
3. User requests to schedule a date and time for an interview for a specific applicant in the list
4. ClubMembersContact marks out the time of that date

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The requested time and date is unavailable.

    * 3a1. ClubMembersContact shows an error message.

      Use case resumes at step 2.

*{More to be added}*

### Non-Functional Requirements

1. The system should respond to user requests within 2 seconds for all functions.
2. The user interface should be consistent across all modules and functions of the application.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
