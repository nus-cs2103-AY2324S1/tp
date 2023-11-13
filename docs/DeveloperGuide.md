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

UniMate is based off the [AddressBook-Level3](https://github.com/se-edu/addressbook-level3) project created by the [SE-EDU initiative](https://se-education.org/).

--------------------------------------------------------------------------------------------------------------------

## **Note about terms used in DG**

In this document, `UniMateCalendar` and `Calendar` are used synonymously.

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
<puml src="diagrams/BottomListPanelClassDiagram.puml" alt="Structure of the UI Component"/>


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

1. When `Logic` is called upon to execute a command, it is passed to an `UniMateParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `UniMateParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `UniMateParser` returns back as a `Command` object.
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
* can save address book data, calendar data, task manager data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from `AddressBookStorage`,`CalendarStorage`, `TaskManagerStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
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

**Aspect: Appropriate input:**

* **Alternative 1: Use Event start time as input:**
  * Pros: Allows the user to more specifically select which event is deleted.
  * Cons: The exact start time of the event has to be used to delete the event.
* **Alternative 2 (Current Choice): Use any time within the event as input:**
  * Pros: Allows for more flexibility in the time given for the event to be deleted.
  * Cons: User may be confused on which event is being deleted.

### Clearing all events within a time frame

All events within a time frame can be cleared using the `ClearEvents` command.
The command requires the start and end time of the time frame to be cleared as inputs. 
All events that fall within or intersect with the time frame (start time inclusive, end time exclusive) are selected to be deleted.
This command also has an optional confirmation as input. If the confirmation is not present in the input, the deletion will not be executed and instead, the events
that would have been executed are displayed in the result. The command can then be reentered with the confirmation to confirm the deletion.

#### Implementation

Given below is an example usage scenario of the command.

Step 1. The user launches the application and creates multiple events.

Step 2. The user executes `clearEvents ts/2023-02-03 12:00 te/2023-02-03 14:00` to view all events within the time range.

Step 3. The `ClearEventsCommandParser` creates an `EventPeriod` representing the indicated time frame and creates a new `ClearEventsCommand` with this `EventPeriod`.

**Note**: At this step, if no events are found within the `EventPeriod` given, a `CommandException` is thrown and an error message is displayed.

Step 4. The `ClearEventsCommand` calls the `Model#eventsInRange(EventPeriod)` method to find all events in the specified time range.

Step 5. All events within the time range are returned and displayed in the command result.

Step 6. The user views all events that would be deleted.

Step 7. The user executes `clearEvents ts/2023-02-03 12:00 te/2023-02-03 14:00 c/CONFIRMED` to confirm the deletion.

Step 8. The `ClearEventsCommand` calls the `Model#eventsInRange(EventPeriod)` method to find all events in the specified time range.

Step 9. For each event in range, the command calls the `Model#deleteEventsInRange(EventPeriod)` which deletes all events within the range.

Step 10. All deleted events are displayed in the command result.

<puml src="diagrams/ClearEventsSequenceDiagram.puml" alt="ClearEventsSequenceDiagram"/>

### Calendar Comparison (compareCalendars)

#### Implementation

When the command `compareCalendars` is called, the arguments passed by the users are converted into a list of `Index` in `CompareCalendarByIndexCommandParser#parse()`.
The list of `Index` is then passed as an argument into the constructor `CompareCalendarByIndexCommand#new()`. Subsequently, when `CompareCalendarByIndexCommand#execute()` is called,
the list of `Person` stored in the AddressBook is retrieved using `ModelManger#getFilteredPersonList()`, and the `Person` associated with each `Index` is extracted
with `List#get()`. Their respective `UniMateCalendar` is then extracted with `Person#getCalendar()`, and all the `UniMateCalendar` of the user and relevant contacts are
merged and reduce into a single `UniMateCalendar` with `UniMateCalendar#combineCalendar()`. This combined `UniMateCalendar` is then used to produce the grey event cards
seen in the pop-up comparison calendar window displayed to the user with `CalendarEventSpace#addSolidEventCards()`.

Step 1. The user launches the application and creates an event in the current week.

Step 2. The user creates an event in the current week for one of their contact. For this example, let's assume the contact is `Alex Yeoh` at `Index` 1 of the AddressBook.

Step 3. The user executed `compareCalendars 1`. The `CompareCalendarByIndexCommandParser#parse()` creates a list of `Index` containing a single `Index` object from
 created from the user's argument `1`, and passed it into `CompareCalendarByIndexCommand#new()`.

Step 4. The `CompareCalendarByIndexCommand#execute()` will turn the `Index` list into a `Stream` with `Arrays#stream()`, and each `Index` in the stream will be 
converted into the `Person` with the given `Index` with `Stream#map()` and `List#get()` as the `map()` function input. 

Step 5. The stream is further converted into a `Stream` of `UniMateCalendar` with `Stream#map()` and `Person#getCalendar()` as the `map()` function input. 

Step 6. Eventually, we call `Stream#reduce()` with the user's `UniMateCalendar` as seed, merging all of the `UniMateCalendar` in the `Stream` into a single one with `UniMateCalendar#combineCalendar()`.

Step 7. The resultant `UniMateCalendar` then has the `Event` stored in it converted into grey event cards with `CalendarEventSpace#addSolidEventCards()`, which
reflects in the resultant pop-up comparison calendar window.

<puml src="diagrams/CompareCalendarsSequenceDiagram.puml" alt="CompareCalendarsSequenceDiagram"/>

**Design Consideration**

**Aspect: Whether to show calendar on a new pop-up or in the main GUI:**

* **Alternative 1 (Current choice): Pop-up window**
    * Pros: Looks cleaner since the comparison calendar is in an isolated window.
    * Cons: User needs to close the pop-up, which might be inconvenient.
* **Alternative 2: Display the calendar on the main application GUI**
    * Pros: Less application tabs for the user to manage.
    * Cons: More clutter on the main GUI.


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

The sort function allows users to sort all persons in UniMate based on a given criteria, and has the following attributes:

* `COMPARATOR`  —  AddressBook sorting criteria
* `reverse`  —  Determines if sorting is by descending order

The syntax used to call this command is as follows: `sort /COMPARATOR [/reverse]`,
with the `COMPARATOR` being one of `/byname`, `/byemail`, `/byphone`, `/byaddress` to sort by name, email, phone and address respectively. If any of the fields are missing or if the formatting is incorrect, an error message will be thrown along with usage instructions on the correct formatting. The `/reverse` parameter is optional to sort in descending order instead.

Given below is an example of how the sort function works at each step. We will simulate a user using the sort function to sort UniMate contacts by name in descending order.

Step 1. The user executes `sort /byname /reverse` to find his friend's contact. The input is passed into `UniMateParser` which then parses it with the `SortCommandParser`.

Step 2. The `SortCommandParser` parses the input and first checks for arguments provided. If the arguments are empty, invalid or in the wrong format, a helper message will appear to allow the user to reference the sample run case. The arguments are then matched by the keywords provided to determine the basis for sorting using a `SortComparator`. All the comparators are added into an ArrayList of `SortComparator` for `SortCommand` to parse.

Step 3. `SortCommand` is initialized parses the array from step 2 to determine the basis of comparison when the command is executed. The `SortCommandParser` finally returns a newly created `SortCommand` consisting of a Person Comparator that decides the method of sorting for the UniMate address book.

Step 4. `SortCommand#execute` is called. In this method, `model#sortPersonList` is called with the Person Comparator created in step 3. This in turn calls `AddressBook#sortPersons` which calls the storage function to save the contacts in the json file based on the sorted order.

Step 5. The GUI then reads in the json file to obtain the order of addresses and populates the sorted list with the sorting criteria provided.

Step 6. The success message is returned as a `CommandResult` and displayed on the GUI result display panel.

Here's a sequence diagram to summarise the steps above:

<puml src="diagrams/SortSequenceDiagram.puml" alt="SortSequenceDiagram"/>

**Design considerations**

* The design of the `sort` command is dependent on the structure of the `AddressBookStorage` object. Should the structure
  of how the AddressBook objects are stored change, a new implementation will be required for the command.

//@@author

### TaskList Feature

The task list feature is facilitated by 'TaskManager'. It extends a ReadOnlyTaskManager that will be used for 
saving users' tasks. The data of the TaskManager is contained in a `TaskList` object. Additionally, it implements the following operations:

* `TaskManager#addTask(Task)` -- Adds a task to the current task list and saves it to memory.
* `TaskManager#deleteTask(int)` -- Delete an existing task from the current task list as indicated by its index and saves the change to memory.
* `TaskManager#sortTasksBy(String)` -- Sets the comparator by which the internal TaskList is sorted to one of two preset options. 
This method only accepts the strings `"Description"` or `"Deadline"` as input and throws an error otherwise.
* `TaskManager#getTaskList()` -- Returns and exposes the internal `TaskList` as an unmodifiable `ObservableList<Task>` that can be 'observed'.

These operations are exposed in the `Model` interface as `Model#addTask(Task)`, `Model#deleteTask(int)`, `Model#sortTasksBy` and `Model#getTaskList` respectively.

A `Task` object consists of a `TaskDescription` and can have up to 1 `Deadline`.

#### Adding Tasks

The adding of tasks is facilitated by the `TaskManager#addTask(Task)` method.
The method adds a `Task` to the `TaskList` object which itself is an attribute of the `TaskManager` object by
calling a similar method `TaskList#addTask(Task)`.

#### Implementation

These methods take in a `Task` object and adds the `Task` object to the `TaskList`.
Given below is an example usage scenario of the command.

Step 1. The user launches the application.

Step 2. The user executes `addTask d/CS2105 Assignment te/2023-12-12 12:00 ` command to add the task.

**Note**: While `Tasks` can have the same `TaskDescription` or `Deadline`, they cannot have both the same `TaskDescription` and `Deadline`.

Step 3. The `addTask` method in the model is called, which calls the `addTask` method in the TaskManager, adding the task to the `TaskList`.

Step 4. The `TaskList` is saved to the memory.

**Design considerations**

* The design of the `addTask` command is such that a deadline is made optional since some tasks are recurring or do not have a specific deadline.

### Viewing Tasks

Tasks can only be viewed in the bottom panel of the GUI which, by default, shows the events list instead.
The viewing of tasks is facilitated by the `SwitchListCommand` which interacts with the UI to swap the bottom list between the event list and the task list.

#### Implementation

Given below is an example usage scenario of the command.

Step 1: The user launches the application.

Step 2: The user executes `switchList` command to view the task list.

**Note**: If the task list is already displayed, the command switches the list back to the event list instead.

Step 3: The `MainWindow` class of the UI switches the bottom list to the task list. The task list will now display the task list, allowing the user to view all tasks.

**Design considerations**

**Aspect: Mode of display:**

* **Alternative 1 (Current Choice): Display the task list in the same area as another UI component:**
  * Pros: The UI will be less cluttered and can have smaller minimum space.
  * Cons: Both the event list and the task list cannot be viewed at the same time.
* **Alternative 2: Display the task list in a separate area of the UI:**
  * Pros: Both the event list and the task list can be viewed at the same time.
  * Cons: The UI may be more cluttered.

#### Deleting Tasks

The deletion of tasks is facilitated by the `TaskManager#deleteTask(int)` method.
The method deletes a `Task` from the `TaskList` object which is an attribute of the `TaskManager` object by calling a similar method `TaskList#deleteTask(int)`.
This in turn deletes the task in `TaskList` that is indicated by its index within the `TaskList`.

#### Implementation

Given below is an example usage scenario of the command.

Step 1. The user launches the application.

Step 2. The user executes `switchList` command to view the task list on the GUI.

Step 3. The user executes `deleteTask 1` command to delete the first task shown in the task list as displayed in the GUI.

Step 4. The `deleteTask` method in the model is called, which calls the `deleteTask` method in the TaskManager, deleting the task from the `TaskList`.

Step 5. The `TaskList` is saved to the memory.

**Design considerations**

**Aspect: Appropriate Input:**

* **Alternative 1: Use Task description and deadline as input:**
  * Pros: User does not need to refer to the GUI to confirm which task is being deleted.
  * Cons: The exact description and deadlines have to be provided since tasks can have the same description or deadline.
* **Alternative 2 (Current Choice): Use displayed index as input:**
  * Pros: Much less input is required from the user.
  * Cons: The user has to view the task list displayed in the GUI to confirm which task is being deleted.

### Sorting Tasks

#### Implementation

Tasks in the TaskList can be sorted either alphabetically by description or by deadlines. The accepted inputs for these are `sortTasks Description` and `sortTasks Deadline` respectively.
The sorting of tasks is facilitated by the `sortTasks` command.
Given below is an example usage scenario of the command.

Step 1. The user launches the application.

Step 2. The user switches to the task list with the `switchList` command.

Step 3. The user executes the `sortTasks Description` command to sort tasks by description alphabetically.

Step 4. A `SortTasksCommand` is created after the command is parsed by the `SortTasksCommandParser`.

Step 5. Upon execution, the `SortTasksCommand` calls `Model#sortTasks(String)` and passes the sorting method of `Description` represented by a String to it.

Step 6. The `TaskManager#sortTasks(String)` method is called and the `sortingOrder` attribute of type `Comparator<Task>` of the `TaskManager` is set to the appropriate type.

Step 7. The internal `TaskList` is sorted by the `sortingOrder`.

//@@author nicrandomlee
### Edit Contact Event

#### Implementation
The Edit Contact Event function executed by `editContactEvent` allows users to edit all person's calendar events in UniMate, and has the following attributes:

* `PERSON_INDEX`  —  Index of the target person
* `EVENT_INDEX`  —  Index of the target person's calendar event
* `DESCRIPTION`  —  Brief description of the edited `Event`
* `NEW_START_DATE_TIME`  —  Starting date and time of the edited `Event`
* `NEW_END_DATE_TIME`  —  End date and time of the edited `Event`

The syntax used to call this command is as follows: `editContactEvent PERSON_INDEX EVENT_INDEX [d/DESCRIPTION] [ts/NEW_START_DATE_TIME][te/NEW_END_DATE_TIME]`, with the `START_DATE_TIME` and `END_DATE_TIME` in the `yyyy-MM-dd HH:mm` format. If any of the fields are missing or if the formatting is incorrect, an error message will be thrown along with usage instructions on the correct formatting.

Given below is an example of how the editContactEvent function works at each step. We will simulate a user using the editContactEvent function to sort UniMate contacts by name in descending order. 

Step 1. The user executes `editContactEvent 1 1 d/CS2103 meeting ts/2023-11-11 10:00 te/2023-11-11 12:00` to reschedule the meeting with his CS2103 module group mate. The input is passed into `UniMateParser` which then parses it with the `EditContactEventCommandParser`.

Step 2. The `EditContactEventCommandParser` parses the input and first checks for arguments provided. If the arguments are empty, invalid or in the wrong format, a helper message will appear to allow the user to reference the sample run case. The arguments are then matched by delimiters `d/`, `ts/` amd `te/` to determine the fields to be edited. If certain fields are empty (for example, the user just wants to change the time start and time end of the meeting), the event description from the edited event will be retained when `EditContactEventCommand#execute` is executed in step 4.

Step 3. `EditContactEventCommandParser` creates a temporary event `EditEventDescriptor` as well as an array consisting of two elements, that is the PERSON_INDEX and EVENT_INDEX to be parsed. The `EditContactEventCommandParser` finally returns a newly created `EditContactEventCommand` consisting of the array and the temporary event.

Step 4. `EditContactEventCommand#execute` is called. In this method, a new person object is created with the same attributes except an updated calendar with the updated event instead. `model#setPerson` is called to replace the target person with the new person. The observable list is then refreshed to show the new calendar event.

Step 5. The success message is returned as a `CommandResult` and displayed on the GUI result display panel.

Here's a sequence diagram to summarise the steps above:

<puml src="diagrams/EditContactEventSequenceDiagram.puml" alt="EditContactEventSequenceDiagram"/>

**Design considerations**

* The design of the `EditContactEvent` command is dependent on the structure of the `CalendarStorage` object. Should the structure of how the Calendar objects are stored change, a new implementation will be required for the command.
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


//@@author junhonglow<br>
**Use case: UC8 View events**


**MSS**


1. User requests to view events.
2. UniMate displays all events.


Use Case ends.


**Extensions**
* 2a. There are no events recorded.
  * 2a1. UniMate displays a message indicating that there are no events.

    Use case ends.


**Use case: UC9 Add an event**


**MSS**


1. User requests to add an event.
2. UniMate adds the event.


Use case ends.


**Extensions**
* 2a. UniMate detects a conflict with an existing event.
  *  2a1. UniMate shows conflicted timings and requests to modify one of the timings.
  *  2a2. User modifies the timing and submits the new timings.
  *  Steps 2a1 and 2a2 are repeated until there are no conflicts in timings.

    Use case ends.


**Use case: UC10 Delete an event**


**MSS**


1. User requests to view events.(UC 8)
2. User requests to delete an event.
3. UniMate deletes the event.


Use case ends.


**Extensions**
* 2a. Event does not exist.
  * 2a1. UniMate shows an error message.


    Use case ends.


**Use case: UC11 Edit an event**


**MSS**


1. User requests to view events. (UC 8)
2. User requests to edit an event.
3. Unimate edits the event.


**Extensions**
* 2a. Event does not exist.
  * 2a1. UniMate shows an error message.


    Use case ends.


* 3a. UniMate detects a conflict with an existing event.
  *  3a1. UniMate shows conflicted timings and requests to modify one of the timings.
  *  3a2. User modifies the timing and submits the new timings.
  *  Steps 3a1 and 3a2 are repeated until there are no conflicts in timings.


    Use case ends.

//@author Fallman2
**Use case: UC12 Filter by fields.**


**MSS**


1. User requests to list persons with specific words in specific fields.
2. UniMate displays persons matching all provided keywords in specified fields and the number of people matching all
   specified fields.

   Use case ends.


**Extensions**


* 2a. There are no persons matching the specified keywords.
    * UniMate displays a message that 0 people have been displayed.

      Use case ends.
* 2b. All indicated fields are left empty instead.
    * UniMate displays all available contacts.

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

//@author Fallman2

**Use case: UC15 Adding a Task.**

**MSS**

1. User requests to add a Task.
2. UniMate adds the task.

  Use case ends.

**Extensions**

* 1a. User leaves the description field empty.
  * UniMate displays a message to indicate that the description cannot be empty.
  
  Use case ends.
* 1b. User attempts to create a Task that is identical to another Task in the Task Manager.
  * UniMate displays a message to indicate that there is already a duplicate task present.

  Use case ends.

**Use case: UC16 Viewing the task list.**

**MSS**

1. User requests to switch the list from the event list to the task list.
2. UniMate switches the list.

  Use case ends.

**Extensions**

* 1a. The list displayed is already the task list.
  * UniMate displays the event list instead.

  Use case ends.

**Use case: UC17 Deleting a task.**

**MSS**

1. User switches the event list to the task list to view it (UC17).
2. User requests to delete a task.
3. UniMate deletes the task from the TaskManager.

  Use case ends.

**Extensions**

* 2a. There are no tasks to delete .
  * UniMate displays an error message indicating that the index indicated is invalid.

  Use case ends.

**Use case: UC18 Sorting Tasks.**

**MSS**

1. User switches the event list to the task list to view it (UC17).
2. User requests to sort the tasks by description.
3. UniMate sorts the tasks displayed by description alphabetically.

  Use case ends.

**Extensions**

* 2a. User requests to sort the tasks by deadline instead.
  * UniMate sorts the tasks displayed by deadline from earliest to latest.
  
  Use case ends.

**Use case: UC19 Clearing Events within a Time Range.**

**MSS**

1. User requests to clear all events within a time range.
2. UniMate deletes all events that overlap with the time range.

  Use case ends.

**Extensions**

* 1a. Time range provided has a start time after the end time.
  * UniMate displays an error message indicating that the start time must be before the end time.

  Use case ends.
* 1b. There are no events within the time range.
  * UniMate displays an error message indicating that there are no events within the indicated times.

  Use case ends.

//@author

**Use case: UC14 Compare calendars**

**MSS**
1. User requests to compare calendars
2. User inputs and confirms the indices of the contacts they want to compare their calendars with
3. UniMate displays the calendar showing the time periods where both the contacts and the user are free 

  Use case ends.

**Extensions**

- 2a. User inputs and confirms the tags of the contacts to identify who they want to compare their calendars with.
  
  Use case resumes from step 3  

- 2b. User inputs the invalid arguments for the command.
  - UniMate displays the user's calendar only, ignoring all the invalid arguments
  - Use case ends.

### Non-Functional Requirements

Project Constraints:
1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed. 
2. The product is offered as an offline application.
3. System should be beginner-friendly and easily picked up by a freshman at NUS with just the application's website.
4. Should be able to hold up to 200 events without a noticeable sluggishness in performance for typical usage. 
5. Calendar should have the current week's event schedule displayed.
6. Calendar should be able to check if there are any event timing conflicts within 1 second.
7. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse, except viewing contact events as a pop-up.
8. System should be beginner-friendly and easily picked up by a freshman at NUS with just the application's website.


Process Requirements:
1. The product is expected to adhere to the set Milestones.
2. User can only execute actions using at most 1 command at a time.

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

  2. Re-launch the app by double-clicking the jar file.<br>
     Expected: The most recent window size and location is retained.

3. Shutting down via command

1. Launch the app.

2. Type `exit` into the input box and press `ENTER`.

4. Shutting down via `file`

1. Launch the app.

2. Click on `file` in the top menu bar.

3. Click on `exit` in the dropdown menu.

### Deleting a person

1. Deleting a person while all persons are being shown

  1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

  2. Test case: `delete 1`<br>
     Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

  3. Test case: `delete 0`<br>
     Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

  4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
     Expected: Similar to previous.

### Filtering all persons while all persons are being shown.

Prerequisites: List all persons using the `list` command. Multiple persons in the list.

1Test case: `filter n/ p/ e/` <br>
Expected: All persons displayed.

1. Test case: `filter n/o` <br>
   Expected: All persons with an "o" in their name displayed.

2. Test case: `filter n/o p/8 e/@ t/CS t/10` <br>
   Expected: All persons with an "o" in their name, an "8" in their phone number, a tag that contains "CS" and a tag
   that contains "10" displayed.

3. Test case: `filter` <br>
   Expected: Error message displayed that states that the command format is invalid. all persons shown.

### Sorting all persons while all persons are shown.

Prerequisites: List all persons using the `list` command. Multiple persons in the list.

1. Test case: `sort /byname` <br>
   Expected: All persons sorted alphabetically by name.

2. Test case: `sort /byname /reverse` <br>
   Expected: All persons sorted in reverse alphabetical order by name.

3. Test case: `sort /byemail` <br>
   Expected: All persons sorted by email.

4. Test case: `sort /byphone` <br>
   Expected: All persons sorted by phone number.

5. Test case: `sort /byaddress` <br>
   Expected: All persons sorted by address.

6. Test case: `sort` <br>
   Expected: Error message displayed indicating invalid command format.

### Adding an event

Prerequisite: Empty event list.

1. Test case: `addEvent d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Expected: New event added with description "Cry about deadlines", start date and time "2023-01-01 00:01" and end date
   and time "2023-12-31 23:59".
2. Test case: `addEvent d/Laugh about deadlines ts/2023-01-02 00:01 te/2023-01-01 23:59` <br>
   Expected: Error message displayed indicating invalid event period.
3. Test case: `addEvent d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Then, re-enter: `addEvent d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Expected: Error message displayed indicating conflicting event timing.

### Deleting an event

Prerequisite: Empty event list.

1. Test case: Enter `addEvent d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Then enter: `deleteEvent 2023-02-02 00:01` <br>
   Expected: Event is created and then successfully deleted
2. Test case: `deleteEvent 2023-02-02 00:01` <br>
   Expected: Error message displayed indicating no event found at specified time.

### Clearing events within time range

Prerequisite: Empty event list.

1. Test case: Enter `addEvent d/Cry about deadlines ts/2023-01-01 00:01 te/2023-01-02 23:59` <br>
   Enter `addEvent d/Cry about deadlines ts/2023-01-03 00:01 te/2023-01-04 23:59` <br>
   Enter `clearEvents ts/2023-01-01 00:02 te/2023-01-04 23:00` <br>
   Expected: All events in Calendar deleted.
2. Test case: `clearEvents ts/2023-01-01 00:02 te/2023-01-04 23:00` <br>
   Expected: Error message displayed indicating no events found in time range.
3. Test case: Enter `addEvent d/Cry about deadlines ts/2023-01-01 00:01 te/2023-01-02 23:59` <br>
   Enter `clearEvents ts/2023-01-01 00:02 te/2023-01-04 23:00` <br>
   Expected: All events in Calendar deleted.

### Switching the bottom list display

Prerequisite: Event list currently being displayed.

1. Test case: `switchList` <br>
   Expected: Task list is displayed.
2. Test case: `switchList 123` <br>
   Expected: Task list is displayed.

### Adding an event for another person

Prerequisite: 1 person in AddressBook, person currently has no events.

1. Test case: `addContactEvent 1 d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Expected: New event added with description "Cry about deadlines", start date and time "2023-01-01 00:01" and end date
   and time "2023-12-31 23:59".
2. Test case: `addContactEvent 1 d/Laugh about deadlines ts/2023-01-02 00:01 te/2023-01-01 23:59` <br>
   Expected: Error message displayed indicating invalid event period.
3. Test case: `addContactEvent 1 d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Then, re-enter: `addContactEvent 1 d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Expected: Error message displayed indicating conflicting event timing.

### Deleting an event for another person

Prerequisite: 1 person in AddressBook, person currently has no events.

1. Test case: Enter `addContactEvent 1 d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Then enter: `deleteContactEvent 1 2023-02-02 00:01` <br>
   Expected: Event is created and then successfully deleted
2. Test case: `deleteContactEvent 1 2023-02-02 00:01` <br>
   Expected: Error message displayed indicating no event found at specified time.

### Editing an event for another person

Prerequisite: 1 person in AddressBook, person currently has no events.

1. Test case: `addContactEvent 1 d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Then enter: `editContactEvent 1 1 d/Edited Description` <br>
   Expected: A new event is added for the person in the AddressBook, then the description of that contact is edited to be "Edited Description".
2. Test case: `addContactEvent 1 d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Then enter: `editContactEvent 1 1 ts/2023-01-01 00:20` <br>
   Expected: A new event is added for the person in the AddressBook, then the start time is edited to be 2023-01-01 00:20
3. Test case: `addContactEvent 1 d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Then enter: `editContactEvent 1 1 ts/2023-01-03 00:20` <br>
   Expected: Error message is displayed that indicates that the start date should be before the end date.
4. Test case: `addContactEvent 1 d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Then enter: `editContactEvent 1 1 te/2023-01-04 00:20` <br>
   Expected: A new event is added for the person in the AddressBook, then the end time is edited to be 2023-01-04 00:20.
5. Test case: `addContactEvent 1 d/Cry about deadlines ts/2023-01-02 00:01 te/2023-12-31 23:59` <br>
   Then enter: `editContactEvent 1 1 t3/2023-01-01 00:20` <br>
   Expected: Error message is displayed that indicates that the start date should be before the end date.
6. Test case: `addContactEvent` <br>
   Expected: Error message displayed indicating that the command format is invalid.

### Viewing events of another person

Prerequisite: 1 person in AddressBook, person currently has no events.

1. Test case: `viewContactEvents 1` <br>
   Expected: Empty event list is displayed in a pop-up
2. Test case: Enter `addContactEvent 1 d/Cry about deadlines ts/2023-01-01 00:01 te/2023-12-31 23:59` <br>
   Then enter: `viewContactEvents 1` <br>
   Expected: Event list displayed in a pop-up with 1 event listed.
3. Test case: `viewContactEvents 2` <br>
   Expected: Error message displayed indicating that the index indicated is invalid.
4. Test case: `viewContactEvents` <br>
   Expected: Error message displayed indicating that the command format is invalid.

### Comparing Calendars of multiple people

Prerequisite: 3 persons in AddressBook.

1. Test case: `compareCalendars` <br>
   Expected: User's calendar is shown.
2. Test case: `compareCalendars 1 3` <br>
   Expected: Calendar comparison is shown in a pop-up with the comparison being between the user, the person of index 1 and the person of index 3.
3. Test case: `compareCalendars 4` <br>
   Expected: Error message displayed indicating that the index indicated is invalid.

### Comparing Calendars of multiple people via tags

Prerequisite: 3 persons in AddressBook, 2 persons have the tag "CS2103".

1. Test case: `compareGroupCalendars` <br>
   Expected: User's calendar is shown.
2. Test case: `compareGroupCalendars CS2103` <br>
   Expected: Calendar comparison is shown in a pop-up with the comparison being between the user and the two people with the "CS2103" tag.

### Adding a task

Prerequisite: No tasks in the task list.

1. Test case: `addTask d/Hydrate te/2023-11-13 22:30` <br>
   Expected: A new task is added with description "Hydrate" and deadline of 2023-11-13 22:30.
2. Test case: `addTask d/Hydrate` <br>
   Expected: A new task is added with description "Hydrate" and no deadline.
3. Test case: Enter `addTask d/Hydrate te/2023-11-13 22:30` <br>
   Then enter: `addTask d/Hydrate te/2023-11-13 22:30` <br>
   Expected: Error message displayed indicating that there is a conflicting task.

### Deleting a task

Prerequisite: No tasks in the task list.

1. Test case: Enter `addTask d/Hydrate te/2023-11-13 22:30` <br>
   Then enter: `deleteTask 1` <br>
   Expected: Task added and then deleted successfully.
2. Test case: `deleteTask 1` <br>
   Expected: Error message displayed indicating that the index indicated is invalid.

### Sorting Tasks

Prerequisite: Multiple tasks in the task list.

1. Test case: `sortTasks DESCRIPTION`
   Expected: Tasks are sorted alphabetically by description.
2. Test case: `sortTasks DEADLINE`
   Expected: Tasks are sorted by deadline.
3. Test case: `sortTasks 1askdj`
   Expected: Error message displayed indicating that the sorting order indicated is invalid.

### Saving data
Prerequisites: Have all three json data files present.
  * addressbook.json
  * calendar.json
  * taskmanager.json
    <br><br>

Some sample storage json files for the tests can be found in the link [here](https://github.com/AY2324S1-CS2103-F13-4/tp/tree/master/src/test/data/ManualTestingSampleStorageFiles).<br>
Rename the files to their respective data files and replace the original files (if any) found in the original directory.<br>
1. Test case: Navigate to the addressbook.json file and delete the file.<br>
   Expected: The AddressBook (Contact List) in UniMate should be populated with sample data.

2. Test case: Single or multiple deletion of the different data files.<br>
   Expected: Similar to previous.

3. Test case: Navigate to the addressbook.json file and edit any of the fields to become an invalid field.<br>
   Example: Modify the events field of a contact to the following. <br>
   {<br>
   "persons" : [ {<br>
   "name" : "Alex Yeoh",<br>
   "phone" : "87438807",<br>
   "email" : "alexyeoh@example.com",<br>
   "address" : "Blk 30 Geylang Street 29, #06-40",<br>
   "tags" : [ "friends" ],<br>
   "events" : [ {<br>
   "description" : "Nap",<br>
   "eventPeriod" : "Invalid Period - 2024-01-03 18:00"<br>
   } ]<br>
   }<br>
   Expected: The AddressBook (Contact List) in UniMate should be blank as UniMate will discard all data.<br>
   Note: In this scenario, the event period is invalid.

4. Test case: Single or multiple corruption of the different data files.<br>
   Expected: Similar to previous.

5. Test case: Navigate to the addressbook.json file and duplicate a person in the file.<br>
   Example:<br>
   {<br>
   "persons" : [ {<br>
   "name" : "Bernice Yu",<br>
   "phone" : "99272758",<br>
   "email" : "berniceyu@example.com",<br>
   "address" : "Blk 30 Lorong 3 Serangoon Gardens, #07-18",<br>
   "tags" : [ "colleagues", "friends" ],<br>
   "events" : [ ]<br>
   }, {<br>
   "name" : "Bernice Yu",<br>
   "phone" : "99272758",<br>
   "email" : "berniceyu@example.com",<br>
   "address" : "Blk 30 Lorong 3 Serangoon Gardens, #07-18",<br>
   "tags" : [ "colleagues", "friends" ],<br>
   "events" : [ ]<br>
   } ]<br>
   }<br>
   Expected: The AddressBook (Contact List) in UniMate should be blank as UniMate will discard all data.<br>

6. Test case: Duplication of the fields in the different data files.<br>
   Expected: Similar to previous.

7. Test case: Other invalid storage formats: Not a JSON format.
   Expected: Similar to previous.

## **Appendix: Effort**
### Main Difficulty: Building User Calendar
This part was especially difficult because we wanted to conform to the structure of the base `AB3` code, knowing it is a brownfield project. 
Thus, we wanted to ensure the abstraction granularity of the classes matched that of `AB3` (e.g. Separate classes for every single attribute of `Person`), 
which explains why the `UniMateCalendar` class is so granular, as seen in the [model class diagram](#model-component).

Furthermore, we knew from the beginning that we wanted to allow users to compare their `Calendar` with that of their contacts.
Therefore, from the start, we had to write code that would make this feature possible.

The UI for the calendar was also challenging because nobody in the group had experience in desktop application UI development and JavaFx.
Understanding JavaFx, before even implementing it in the creation of new GUI elements, required a few hours and much trial and error.
It was also challenging to implement GUI components while ensuring that all elements that were already previously implemented worked as well.

Lastly, the feature where the calendar would "grow" and "shrink" according to the events that the user have in real time
took a lot of careful problem-solving to avoid breaking abstraction barrier and introducing cyclic dependencies, since
we had to establish a line of communication between the `UniMateCalendar` of the `Model` interface and the `UI` interface,
and the `Observable` and `Observer` interfaces were only introduced later into the module, by which we had already implemented
this feature.

### Other Difficulty: Contact Calendar
Integrating the calendar into the contacts in the AddressBook was challenging, but was slightly easier as we could just reuse the
code that we have written for the `UniMateCalendar`. However, we still had to learn how to make the calendar of the contacts show up
in a new window rather than in the main application GUI.

### Other Difficulty: Task Manager/Event List
For the Task Manager, the main difficulty lies in following the structure of the `AB3` code.
While the implementation of the TaskManager was not complicated, we wanted to properly abstract the different classes from each other similarly to the
structure of AB3. Thus, the implementation required a large number of lines of code and methods, all of which required even more JUnit tests, multiplying
the number of hours spent on implementing the TaskManager. However, the implementation of the GUI for the TaskManager did not take as long as that
of the Calendar as the TaskManager used the same area of the GUI as the event list. That being said, given that no one in our team had experience working
on a GUI, especially with JavaFx, a few hours had to be spent in researching what would be the best way to carry out the switching of the lists from one to the other.

Additionally, for both `Task List` and `Event List`, making the list update in real time when new `Task`/`Event` are added was also difficult. When implementing
the GUI, we had wanted to ensure that commands executed in the main window would reflect appropriately in both the user's own task and event lists but also
for the pop-up event list of individual persons in the AddressBook. This proved to be another challenge as we would encounter several bugs while implementing
this feature. However, we managed to work together as a group to fix most of them.

### Achievements:
- Calendar UI that will update in real time when user adds/deletes event
- Working calendar comparison feature
- We were able to integrate 2 separate and very different components, `Task manager` and `Calendar`, successfully into the base `AB3` code
- Live updates for the `Task List` and `Event List`


## **Appendix: Planned Enhancements**

### Better Confirmation for ClearEventsCommand
Currently, for the ClearEventsCommand, the user has to re-enter the command entirely with the confirmation text in order
to confirm the deletion of the events. This implementation has an advantage of being able to bypass the confirmation 
check if the user wishes to delete the events more quickly but is otherwise clumsier to use. One way we tried to 
circumvent this is by displaying the message with the confirmation in the result shown but this would not be as convenient
for users who do not wish to use their mouse. A better implementation would be to have the confirmation only require the
user to key in Y or N to confirm or deny the deletion, allowing the events to still be deleted quickly for users who wish
to do so but also allowing for less text to be keyed in to confirm.

### More Ways to Sort Tasks
Currently, there are only two ways to sort tasks. This can cause some tasks to be buried under others if they are not 
prioritized in either sorting method. Being able to sort tasks by proximity to the current date or being able to reverse
the order of sorting of the tasks may help in making all tasks visible to the user so as to ensure that all tasks are 
completed on time.

### Allow user to view beyond the current week restriction for the calendar
Currently, the calendar for the user and contacts is restricted to viewing the current week's events. Therefore, the
user has no means of navigating looking at their events for other weeks other than the current one. This might impede
the user experience as the user might want to check their timetable for some timeframe in the near future (e.g. proceeding week).
Therefore, allowing the user to view their events for any week beyond just the current one might improve user experience,
reducing the dependency the user has on the `Event List` to view events beyond this chronological restriction.

### Allow users to compare calendars with contacts beyond the current week
Currently, the user can only compare calendars with their contacts for the current week. Therefore, if the user wants to
schedule a meeting in a week beyond the current one, they might have difficulty as they might not be able to compare
their calendars with their contacts for that specific week. Therefore, allowing the user to navigate beyond the current
week restriction for the compare calendar feature will be hugely beneficial to the user.

### Allow users to import .ics format files
Currently, UniMate has no support for importing of external files. Allowing users to import .ics files, which is a common export
format of other calendar platforms, will allow UniMate to integrate more seamlessly with other calendar applications.
