---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# UniMate Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

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

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

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
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

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

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

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

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

//@@author lihongguang00
### Adding Events

#### Implementation

The addEvent feature is facilitated by the `Calendar` class. It allows the users to block out some time
in their personal `Calendar` with some `Event` that has the following attributes:

* `DESCRIPTION`  —  Brief description of the `Event`
* `START_DATE_TIME`  —  Starting date and time of the `Event`
* `END_DATE_TIME`  —  End date and time of the `Event`

The syntax used to call this command is as follows: `addEvent d/DESCRIPTION ts/START_DATE_TIME te/END_DATE_TIME`,
with the `START_DATE_TIME` and `END_DATE_TIME` in the `yyyy-MM-dd HH:mm` format. If any of the fields are missing
or if the formatting is incorrect, an error message will be thrown along with usage instructions on the correct
formatting.

Given below is an example usage scenario and how the addEvent feature behaves at each step.

Step 1. The user launches the application for the first time. The user's personal `Calendar` will be initialized
as an empty calendar.

Step 2. The user executes `addEvent d/Go to school ts/2023-10-26 08:00 te/2023-10-26 16:00` to add the `Event`
of `Go to school` from `2023-10-26 8am` to `2023-10-26 4pm`. This will call the `UniMateParser#execute()`,
passing in the user input from the user.

Step 3. Since the command is an `addEvent` command, it passes the user input to `AddEventCommandParser#parse()`
for parsing.

Step 4. The `AddEventCommandParser#parse` command will parse the command into 3 argument fields  — 
`DESCRIPTION`, `START_DATE_TIME` and `END_DATE_TIME`. The `DESCRIPTION` is passed into
`ParserUtil#parseEventDescription()` to produce a `EventDescription` object, while the `START_DATE_TIME`
and `END_DATE_TIME` are passed into `ParserUtil#parseEventPeriod()` to produce a `EventPeriod` object.

**Note**: If the `DESCRIPTION` is empty, or the `START_DATE_TIME` or `END_DATE_TIME` are of invalid
format, a `ParseException` will be thrown, displaying the appropriate command usage format.

Step 5. The `EventDescription` and `EventPeriod` objects produced in Step 4 are then passed into
the constructor for `Event`, creating an `Event` object with the respective user-defined attributes. This
`Event` object is then passed into the constructor of the `AddEventCommand` object.

Step 6. `AddEventCommand#execute()` is then called, and the calendar will check if there is an existing
`Event` that has a conflicting timing with the new `Event` to be added. Since the calendar is empty,
no errors will be raised and the user will see his new `Event` displayed in the UI in the `My Calendar`
region.

**Note**: Suppose there is a conflicting `Event` that already exists in the `Calendar`
with the `Event` to be added, the new `Event` will not be added, and a message that states that there
is a timing conflict will be reflected in the UI status bar.

#### Design considerations:

**Aspect: Appropriate time period of an event:**

* **Alternative 1 (Current choice): No restriction on time period**
    * Pros: Allows users to add multi-day `Event` such as multi-day business trips.
    * Cons: For users that rarely have multi-day `Event`, having to type the date twice when calling the command might be troublesome.
* **Alternative 2: Restrict events to a single day**
    * Pros: User only has to type date once when calling command.
    * Cons: For multi-day `Event`, user has to call the command multiple times for all the relevant days

{more aspects and alternatives to be added}

//@@author Fallman2
### Deleting Events

#### Implementation

The deletion of events is facilitated by the `model::deleteEventAt` method and the `model::findEventAt` method. The
former method deletes the event stored in the `Calendar` object which itself is an attribute of the `Model` object by
calling a similar method `Calendar::deleteEventAt`. These methods take in a `LocalDateTime` object and finds the method
within the `Calendar` object, then in the case of `model::deleteEventAt`, deletes the event. Given below is an example
usage scenario of the command.

Step 1. The user launches the application and creates an event.

Step 2. The user executes `deleteEvent 2023-12-09 12:00` command to delete the event at that time. The `deleteEvent`
command calls `Model#findEventAt(LocalDateTime)` to find an event at the specified date and time. This event is then stored as a
variable `toDelete`.

**Note**: If no event is found at the specified date and time at any point of the command execution, an
`EventNotFoundException` is thrown which causes an error message to be displayed.

Step 3. The command then calls `Model#deleteEventAt(LocalDateTime)`. This method calls similar methods of
`Calendar#deleteEventAt(LocalDateTime)` which calls `AllDaysEventListManager#deleteEventAt(LocalDateTime)`.

Step 4. The `AllDaysEventListManager` checks for an event at the specified date and time again, then checks for all days
for which the event lasts for. Then, for each day, the event is removed from the `SingleDayEventList`.

Step 5. The deleted event which was previously stored in as a variable is displayed in the `CommandResult` to show the
user which command was deleted.

**Design considerations**

* The design of the `deleteEvent` command is dependent on the structure of the `Calendar` object. Should the structure
  of how the event objects are stored change, a new implementation will be required for the command.

### Contact Filtering

#### Implementation

The filtering function executed by `FilterCommand` is facilitated by the `PersonFilter` class.
which itself is similar to the `EditPersonDescriptor` class found in `EditCommand.java`. It stores the fields by which
the contacts are to be filtered and creates a predicate to facilitate the filtering. Notably, it implements the
following operation:

* `PersonFilter#matchesFilter(Person)` - Compares the values of the attributes of the `Person` to the strings stored as
  attributes in the `PersonFilter` object. This method is later used as a lambda method to filter the contact list.

Given below is an example of how the filter function works at each step.

Step 1. The user executes `filter n/Bob t/CS` to filter contacts to see only people with "Bob" in their name and have at
least 1 tag with "CS" in it. The input is passed to `UniMateParser` which then parses it with the `FilterCommandParser`.

Step 2. The `FilterCommandParser` parses the input and creates a corresponding `PersonFilter` object with null for all
parameters. It then sets all specified attributes of the created `PersonFilter` while leaving unspecified fields as
null. The `FilterCommandParser` finally returns a newly created `FilterCommand` with the PersonFilter used in the
constructor.

Step 3. `FilterCommand#execute` is called. In this method, `model#updateFilteredPersonList` is called with
`PersonFilter#matchesFilter` being used as the predicate. This updates the GUI and populates the filtered list with
only `Person` objects that match the filter.

Step 4. The number of people displayed is returned as a `CommandResult`.

//@@author nicrandomlee
### Contact Sorting

#### Implementation
The sort function executed by `SortCommand`.

The sort function allows users to sort all persons in `UniMate` based on a given criteria. The following criterion for sort are shown below
- Sort by name (optional: in the reverse order)
- Sort by address (optional: in the reverse order)
- Sort by email (optional: in the reverse order)
- Sort by phone (optional: in the reverse order)

The syntax used to call this command is as follows, without the [ ] braces: `sort [/byname][/byaddress][/byemail][byphone] [/reverse]`. Do note that sorting by reverse is optional.

Given below is an example of how the sort function works at each step. We will simulate a user using the sort function to sort UniMate contacts by name in descending order.
1. The user executes `sort /byname /reverse` to find his friend's contact. The input is passed into `UniMateParser` which then parses it with the `SortCommandParser`.
2. The `SortCommandParser` parses the input and first checks for arguments provided. If the arguments are empty, invalid or in the wrong format, a helper message will appear to allow the user to reference the sample run case. The arguments are then matched by the keywords provided to determine the basis for sorting using a `SortComparator`. All the comparators are added into an ArrayList of `SortComparator` for `SortCommand` to parse.
3. `SortCommand` is initialized parses the array from step 2 to determine the basis of comparison when the command is executed. The `SortCommandParser` finally returns a newly created `SortCommand` consisting of a Person Comparator that decides the method of sorting for the UniMate address book.
4. `SortCommand#execute` is called. In this method, `model#sortPersonList` is called with the Person Comparator created in step 3. This in turn calls `AddressBook#sortPersons` which calls the storage function to save the contacts in the json file based on the sorted order.
5. The GUI then reads in the json file to obtain the order of addresses and populates the sorted list with the sorting criteria provided.
6. The success message is returned as a `CommandResult` and displayed on the GUI result display panel.

Here's a sequence diagram to summarise the steps above:

<puml src="diagrams/SortSequenceDiagram.puml" width="550"/>

**Design considerations**

* The design of the `sort` command is dependent on the structure of the `AddressBookStorage` object. Should the structure
  of how the AddressBook objects are stored change, a new implementation will be required for the command.

//@@author junhonglow
### TaskList Feature (Work in Progress)

#### Implementation

The proposed tasklist feature is facilitated by 'TaskList'. It extends a ReadOnlyTaskList that will be used for the
saving of the users' tasks. Additionally, it implements the following operations:

*`TaskList#addTask()` -- Adds a task to the current tasklist and saves it to memory.
*`TaskList#deleteTask()` -- Delete an existing task from the current tasklist and saves it to memory.
*`TaskList#editTask()` -- Edits an existing task from the current tasklist and saves it to memory.

These operations are exposed in the `Model` interface as `Model#addTask()`, `Model#deleteTask()` and `Model#editTask()`
respectively.

##### Adding Task

The adding of tasks is facilitated by the `model#addTask()` method.
The method adds a task to the `TaskList` object which itself is an attribute of the `ModelManager` object by
calling a similar method `Model::addTask()`.

These methods take in a `Description` and `Optional(Deadline)` object and finds the method within the `TaskList` object,
then adds the `Task` object to the TaskList.
Given below is an example usage scenario of the command.

Step 1. The user launches the application and creates a task.

Step 2. The user executes `addTask d/CS2105 Assignment te/2023-12-12 12:00 ` command to add the task.
The `addTask` method in the model is called, adding the task to the tasklist, and saving the tasklist to memory.

**Note**: There is no limit to the number of tasks of the same description or deadline that can be created.

**Design considerations**

* The design of the `addTask` command is such that a deadline is made optional.
* This current implementation allows for more freedom to the user but might be more difficult to manage with the addition of Optionals.

##### Delete Task (To be added)

##### Edit Task (To be added)
//@@author

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


* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* is an NUS student
* has a need to organize and coordinate schedules


**Value proposition**: manage contacts faster than a typical mouse/GUI driven app

//@author lihongguang00
| Priority | As a …                                                              | I want to …                                                                  | So that I can…                                                                          |
| -------- | ------------------------------------------------------------------- | ---------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| `* * *`  | NUS student                                                         | search for the contacts of other students within my university               | contact them for group projects                                                         |
| `* * *`  | NUS student                                                         | search for a name in the contact                                             | easily find the person’s contact                                                        |
| `* * *`  | NUS student                                                         | add contacts into my address book easily                                     | retrieve the saved contact                                                              |
| `* * *`  | NUS student living on campus                                        | save the contacts of my neighbors                                            | contact them in case of any emergencies                                                 |
| `* * *`  | NUS student living on campus                                        | save the addresses of my neighbours                                          | locate them easily when necessary                                                       |
| `* * *`  | NUS student in multiple CCAs                                        | filter my contacts by tags to identify all people in a group                 | find the relevant contacts in a certain group quickly                                   |
| `* * *`  | student staying on campus                                           | label multiple tags to my contacts                                           | locate my friends taking the same module and staying in the same campus residence as me |
| `* * *`  | user that is familiar with the keyboard                             | use the keyboard to type commands in the applications                        | access the features of the application                                                  |
| `* * *`  | user with bad memory                                                | save a short description of my contact                                       | identify my contacts better                                                             |
| `* * *`  | visual-reliant user                                                 | save a photo of the person into my contacts                                  | quickly recognise and find them                                                         |
| `* * *`  | non-tech-savvy user                                                 | use the help feature of the app                                              | navigate about the app easily                                                           |
| `* *`    | NUS student                                                         | sort all contacts by name                                                    | easily find a person's contact                                                          |
| `* *`    | NUS student                                                         | sort all contacts by name in reverse                                         | easily find a person's contact                                                          |
| `* *`    | NUS student                                                         | sort all contacts by email                                                   | easily find a person's contact                                                          |
| `* *`    | NUS student                                                         | sort all contacts by email in reverse                                        | easily find a person's contact                                                          |
| `* *`    | NUS student                                                         | sort all contacts by address                                                 | easily find a person's contact                                                          |
| `* *`    | NUS student                                                         | sort all contacts by address in reverse                                      | easily find a person's contact                                                          |
| `* *`    | NUS student                                                         | sort all contacts by phone                                                   | easily find a person's contact                                                          |
| `* *`    | NUS student                                                         | sort all contacts by phone in reverse                                        | easily find a person's contact                                                          |
| `* *`    | NUS student                                                         | import the NUS calendar into the application                                 | view all academic commitments more conveniently                                         |
| `* *`    | NUS Student                                                         | compare timetables/calendars with my peers easily                            | plan meetings more conveniently                                                         |
| `* *`    | NUS student                                                         | allocate tasks and responsibilities within a project or CCA group            | tasks can be done efficiently                                                           |
| `* *`    | NUS Student in multiple CCAs                                        | group my contacts                                                            | identify which group my contacts belong to                                              |
| `* *`    | NUS student doing a group project                                   | export my calendar in my application                                         | send it to my teammates to coordinate meeting times                                     |
| `* *`    | forgetful user                                                      | view a password hint                                                         | I can recall my password when I forget it                                               |
| `* *`    | forgetful student                                                   | be reminded of upcoming assignments                                          | prioritize which assignment to work on first                                            |
| `* *`    | forgetful student                                                   | be reminded of upcoming examinations                                         | prioritize which exam module to study on first                                          |
| `* *`    | forgetful student                                                   | be reminded of upcoming projects                                             | prioritize which project to work on first                                               |
| `* *`    | forgetful student                                                   | be reminded of upcoming tutorials                                            | so that I can prioritize which tutorial to complete first                               |
| `* *`    | security conscious user                                             | password-protect my appliation                                               | prevent others from accessing my application profile easily                             |
| `* *`    | data conscious user                                                 | backup my data                                                               | so that I can recover it in the case my data is corrupted                               |
| `* *`    | user who cares about user experience                                | change the color of my user interface                                        | modify the interface to my liking                                                       |
| `* *`    | user with a strong urge for aesthetics                              | interact with a clean user interface                                         | feel at ease when using the application                                                 |
| `* *`    | non-tech-savvy user that does not know how to use terminal commands | use buttons around the application to navigate around the application easily |
| `*`      | student who prefers using a tablet                                  | I can use the application on my tablet                                       | access contacts easily on my tablet                                                     |
| `*`      | student who prefers mobile devices                                  | I can use the application on my mobile phone                                 | sync my contacts with those in the application                                          |
| `*`      | student in a group project                                          | send a QR code of my application contact details                             | have my teammates add my contact details on the application much faster                 |
| `*`      | user who prefers cloud storage                                      | sync the contacts in my application with Google Contacts                     | save it in cloud-based storage                                                          |
| `*`      | user accustomed to a PC                                             | view all commands at once                                                    | explore features quickly                                                                |
| `*`      | user with a tendency to open many applications at once              | have the application time out and exit                                       | ensure my computer would not be cluttered by too many applications                      |

_{More to be added}_
//@@author nicrandomlee
### Use cases


(For all use cases below, the **System** is the `UniMate` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC1 List persons**


**MSS**


1. User requests to list persons
2. UniMate shows a list of persons

Use case ends.


**Extensions**


* 2a. The list is empty.


* 2b. UniMate displays a message to the user.


Use case ends.


**Use case: UC2 Delete a person**


**MSS**


1.  User requests to list persons (UC1)
2.  User requests to delete a specific person in the list
3.  UniMate deletes the person


Use case ends.


* 3a. The given index is invalid.


* 3a1. UniMate shows an error message.


     Use case resumes at step 2.


**Use case: UC3 Add a person**




**MSS**




1.  User requests to add in a person into the UniMate
2.  UniMate adds the person
3.  UniMate displays a success message


Use case ends.

**Extensions**


* 1a. The given format is wrong

    * 1a1. UniMate shows an error message


Use case ends.


* 1b. Person is already in UniMate


      Use case ends.

//@author Fallman2
**Use case: UC4 Clear All Entries**


**MSS**


1.  User requests to clear all entries in UniMate
2.  UniMate deletes all entries


      Use Case Ends.

**Extensions**


* 1a. UniMate has no data


    Use Case Ends


**Use case: UC5 Edit persons**


**MSS**


1. Use requests to list all persons(UC1).
2. User requests to edit person at a specified index with new parameters.
3. UniMate updates the person at specified index with new parameters.
   Use case ends.


**Extensions**


* 2a. The given index is invalid.
    * 2a1. UniMate displays an error message.
      Use case ends.
* 2b. The given parameters are invalid.
    * 2b1. UniMate displays an error message.
      Use case ends.


**Use case: UC6 Exit application**


**MSS**


1. User requests to exit the application.
2. UniMate closes the application.
   Use case ends.


**Use case: UC7 Search by name**


**MSS**


1. User requests to search persons by specified keywords
2. UniMate displays persons with names that match keywords
   Use case ends.


//@@author junhonglow
**Use case: UC8 View events**


**MSS**


1. User requests to view events.
2. UniMate displays all events.


Use Case ends.


**Extensions**
* 2a. There are no events recorded.
  2a1. Unimate displays a message indicating that there are no events.

  Use case ends.


**Use case: UC9 Add an event**


**MSS**


1. User requests to add an event.
2. UniMate adds the event.


Use case ends.


**Extensions**
*  2a. UniMate detects a conflict with an existing event.
   2a1. UniMate shows conflicted timings and requests to modify one of the timings.
   2a2. User modifies the timing and submits the new timings.
   Steps 2a1 and 2a2 are repeated until there are no conflicts in timings.


    Use case ends.


**Use case: UC10 Delete an event**


**MSS**


1. User requests to view events.(UC 8)
2. User requests to delete an event.
3. UniMate deletes the event.


Use case ends.


**Extensions**
* 2a. Event does not exist.
  2a1. UniMate shows an error message.


    Use case ends.


**Use case: UC11 Edit an event**


**MSS**


1. User requests to view events. (UC 8)
2. User requests to edit an event.
3. Unimate edits the event.


**Extensions**
* 2a. Event does not exist.
  2a1. UniMate shows an error message.


    Use case ends.


*  3a. UniMate detects a conflict with an existing event.
   3a1. UniMate shows conflicted timings and requests to modify one of the timings.
   3a2. User modifies the timing and submits the new timings.
   Steps 3a1 and 3a2 are repeated until there are no conflicts in timings.


    Use case ends.

//@author Fallman2
**Use case: UC12 Filter by fields.**


**MSS**


1. User requests to list persons with specific words in specific fields.
2. UniMate displays persons matching all provided keywords in specified fields and the number of people matching all
   specified fields.

   Use case ends.


**Extensions**


* 2a. There are no persons matching the specified keywords
    * UniMate displays a message that 0 people have been displayed.
      Use case ends.

//@author nicrandomlee
**Use case: UC13 Sort by fields.**

**MSS**

1. User requests to sort persons
2. UniMate displays persons in specified order and by specified parameter

   Use case ends.

**Extensions**

- 1a. User uses the wrong delimiter or makes a spelling mistake, or provides incorrect number of arguments
    - UniMate displays a message to show a helper message outlining the correct syntax and available sort options
//@author

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 200 events without a noticeable sluggishness in performance for typical usage.
3.  Calendar should have a combined 1 academic year look forward and look back period (In AY22/23, can look through calendar for this academic year)
4. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
5. Calendar should be able to check if there are any event timing conflicts within 1 second
6. System should be easily picked up by a freshman at NUS with just the application's website


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* * **Event**: An activity to be marked on the calendar with a specified time frame.

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

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
