---
layout: page
title: Developer Guide
---
## Table of Contents
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete_person 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `EventListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` and `Event` object residing in the `Model`.

[Scroll back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete_person 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete_person 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeletePersonCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeletePersonCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddPersonCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPersonCommandParser`, `DeletePersonCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

[Scroll back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="600" />

[Scroll back to Table of Contents](#table-of-contents)

The `Model` component,

* stores the address book data i.e., all `Person`(which are contained in a `UniquePersonList` object) and `Event` objects (which are contained in a `EventList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores the currently 'selected' `Event` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Event>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* stores a `Logger` object that is used to log messages of the application's behaviour.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<img src="images/BetterModelClassDiagram.png" width="600" />

[Scroll back to Table of Contents](#table-of-contents)

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="600" />

<div style="page-break-after: always;"></div>

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

[Scroll back to Table of Contents](#table-of-contents)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Flow of Program Execution**

The way the user interacts with the program is illustrated as follows.

![FlowOfProgram](images/CommandFlowActivityDiagram.png)

The following is a (partial) explanation of the flow of events:
1. The user makes their command by issuing a command in the CommandBox.
2. The command is parsed by the Parser and the corresponding Command object is created.
3. The Command object is executed.
4. The Command is executed and returns a CommandResult.
5. The CommandResult is passed to the UI component to be displayed to the user.

More details are captured in the diagram above.

If the command involves the changing of Models, the Models are updated accordingly at stage 3 during the execution process.
Changes to the models will also be reflected in Storage in the backend.
These model changes will also be reflected in the Ui (e.g when a Person or an Event is changed).

The errors during process and execution are also handled accordingly by displaying an error message to the user.

The object diagram below illustrates a possible state of the Models after some commands have been executed.

![Model state](images/AddEventObjectDiagram.png)

Assume that the user just added a `Meeting` which is a subtype of `Event`. They supplied the meeting with a name, a start date and a start time. The user also added some previous events and persons as shown in the diagram.

This shows how the Models are stored for use in the program.

Note that even though EventList stores a list of Events, currently only Meetings (a subtype of Event) are implemented. This is to allow for future extensibility of the program.

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------


<div style="page-break-after: always;"></div>

## **Implementation**

### Commands
This section explains the general implementation of all commands.

The following activity diagrams shows the overall flow of events that the user will experience.

This section describes some noteworthy details on how certain features are implemented.

#### Parser Commands
This section explains the implementation and execution of commands that have their own specific parser.

Below is the sequence diagram for the execution of these commands (denoted by `XYZCommand`) after user input is sent to `LogicManager`. The execution of each of the command has been omitted due to their inherent differences and will be covered in their respective command sections below.

![Command Parser Sequence Diagram](images/CommandsParserSequenceDiagram.png)

Step 1:
The user enters a command with the necessary parameters which is then passed to the `LogicManager`.

Step 2:
The `LogicManager` calls `AddressBookParser::parseCommand` for it to identify the type of command.

Step 3:
The `AddressBookParser` parses the user input and creates a command parser for that specific command. (denoted by `XYZCommandParser`)

Step 4:
The command parser is returned to the `AddressBookParser` which then calls `XYZCommandParser::parse` to parse the additional parameters.

Step 5:
The `XYZCommandParser` creates its respective command object (denoted by `XYZCommand`) and returns it to `LogicManager`.

Step 6:
The `LogicManager` calls `XYZCommand::execute` where the interaction between the command and the model is handled.

Step 7:
The `XYZCommand` creates a successful `CommandResult` and returns it to the UI.

[Scroll back to Table of Contents](#table-of-contents)

### Ability to add persons
This section explains the implementation of the Add Task feature via the `add_person` command.
The `AddPersonCommand` causes the specified `Person` to be added to the Persons List in the application.
There is only one compulsory field which is the name of the Person. There are several optional fields such as the phone number, email address, address, birthday and remark of the Person.

Below is the sequence diagram outlining the execution of `AddTaskCommand`.

#### Implementation details

The `add_person` feature involves creating a new `Person` object with optional fields and adding it to FumbleLog. 

This is done using `AddPersonCommand` which implements the `Command` interface. The `AddPersonCommand` is then executed by `LogicManager` which calls `ModelManager` to add the person to the address book.

As a result, the existing `Person` class in AB3's implementation is enhanced to have the capacity of storing optional fields.
Below is a class diagram of the enhanced 'Person' class:

<img src="images/PersonClassDiagram.png" alt="PersonClassDiagram" width=500 />

The `Person` object is now composed of the following optional attributes due to the refactored `add` feature:

* `Name`: The name of the person. Compulsory field.
* `Phone`: The phone number of the person. Optional field.
* `Email`: The email address of the person. Optional field.
* `Address`: The address of the person. Optional field.
* `Birthday`: The birthday of the person. Optional field.
* `Remark`: The remark of the person. Optional field.
* `Group`: The groups that the person is associated with. Optional field.

The [**`java.util.Optional<T>`**](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html) class is used to represent the optional attributes of the `Person` object.

To add a person, the user must specify the name of the person using the `n/` prefix. The user can then specify the optional attributes of the person using the following prefixes:

<box type="info">

Except for the `Name`, all the fields given to the `add_person` command are optional.

</box>

The flow for the `add_person` command is described by the following sequence diagram:

<img src="images/AddPersonSequenceDiagram2.png" alt="AddPersonSequenceDiagram2" width=600 />

Step 1:
The `LogicManager` invokes `AddPersonCommand::execute`, which in turn calls `Model::addPerson`.

Step 2:
The `Model` will invoke `addPerson` in `AddressBook`, which in turn calls `add` in `UniquePersonList` to add the person to the list.

Step 3:
The `AddPersonCommand` then continues its execution as defined by [this](#parser-commands) sequence diagram.

#### Feature details
1. The application will validate the arguments supplied by the user; whether the `Name` is unique and supplied, and whether the optional fields follow the correct format. 
2. If the arguments are invalid, an error message will be shown to the user and prompts the user for a corrected input.
3. If the arguments are valid, a `Person` object will be created with the fields supplied and stored in FumbleLog.

#### Design Considerations

**Aspect: Generic Design**

The original implementation of AB3's `Person` class is refactored to have the capacity of storing optional fields. This is done by using the `java.util.Optional<T>` class to represent the optional attributes of the `Person` object.
Furthermore, we have added additional fields into the `Person` class to allow users to store more information about the person, such as their birthday.

As the original `add` command already exists in AB3, this feature can be implemented by enhancing the `add` command. In FumbleLog, the `add` command is further changed to `add_person`.

Furthermore, we accounted for empty/null inputs in the optional fields by generating a NULL_INSTANCE for the optional fields when the user does not specify the optional fields. This design decision allowed us to easily check
for empty/null inputs in the Person object by checking if the optional field is not equal to the NULL_INSTANCE, instead of doing null pointer and empty string checks.

* **Alternative 1 (current choice):** Enhance the existing `add_person` command.
  * Pros: 
    * Easier to implement.
    * Reuses the logic for the `add_person` command.
  * Cons:
    * Have to account for empty/null inputs in the optional fields when saving the data and testing it
    * Have to account for empty/null inputs in the optional fields when displaying the data
* **Alternative 2**: Create a new `add_person_optional` command.
  * Pros: 
    * Do not have to account for empty/null inputs in the optional fields when saving the data and testing it
  * Cons:
    * Inconveniences the user as they have to remember a new command to add a person with optional fields.

[Scroll back to Table of Contents](#table-of-contents)

### Ability to delete persons

This section explains the implementation of the Delete Person feature via the `delete_person` command. The `DeletePersonCommand` causes the specified `Person` identified using the `Index` to be deleted from the UniquePerson List in the application. There is one compulsory field which is the Index of the Person to delete.

Below is the sequence diagram outlining the execution of `DeletePersonCommand`.

![DeletePersonCommand Sequence Diagram](images/DeletePersonSequenceDiagram.png)

Step 1:
The `LogicManager` invokes `DeletePersonCommand::execute`, which in turn calls `Model::deletePerson`.

Step 2:
The `Model` will invoke `removePerson` in `AddressBook`, which in turn calls `remove` in `UniquePersonList` to remove it from the list.

Step 3:
The `DeletePersonCommand` then continues its execution as defined by [this](#parser-commands) sequence diagram.

#### Design Considerations
**Aspect: How we execute the DeletePersonCommand:**
Similar to the `AddPersonCommand`, the main considerations for this command is related to the way that the model is stored.

[Scroll back to Table of Contents](#table-of-contents)

### Ability to track events

This subsection details of how the `Event` class is implemented.

#### Implementation

For this feature, the `Event` class is implemented to store the event's name, date,
start time, end time, persons involved and groups involved.

<img src="images/EventClassDiagram.png" alt="EventClassDiagram" width=600 />

#### Design considerations

- Events stores a list of `Name` and a list of `Group` that are involved in the event. 
This is to facilitate the ability to track persons and groups involved in the event.
The `Name` class is used to represent the name of the person involved in the event, as names are unique in the `UniquePersonList`.
- We have also made `Event` an abstract class so as to increase extensibility of FumbleLog in the future. For now, when an event is created (i.e. using the `AddEventCommand`), it defaults to adding a `Meeting` into FumbleLog's `Event` List. Future support for other kinds of `Event` can be possible (i.e. Recurring event) by directly inheriting from `Event`.
- To track events, we implement an `EventList` to store all events to be displayed in FumbleLog.

[Scroll back to Table of Contents](#table-of-contents)

### Ability to assign/unassign persons from an event

#### Implementation

- The ability to assign a `Person` to an `Event` is facilitated by `ModelManager`.
- Each `Event` stores a list of person assigned to it. 
The person(s) are represented by their `Name` stored in FumbleLog. 
This is because the `Name` is the only unique identifier for a person.
- When a person is assigned to an event, the person's `Name` is added to the `Event`'s list 
of assigned persons. 
  - When a `Person` is unassigned from an `Event`, the person's `Name` is 
removed from the `Event`'s list of assigned persons. 
  - When a person's `Name` is modified, the change is also reflected in the event(s) 
  that they are previously assigned to.
- Users can assign multiple names to an event by using multiple `n/` identifiers following 
with the `Name` specified. The `ModelManager` will perform checks on whether the names supplied are valid, 
i.e the `Name` currently exists in FumbleLog.
- When editing the event, specifying `n/` with a `Name` will append this new name to the current list rather than replace the previous names. 
This is to facilitate the user to assign more persons without accidentally deleting the previous persons assigned. 
  - To un-assign a `Person`, the user must manually specify `u/` with the `Name` to un-assign the `Person` from the `Event`.
  - The `ModelManager` will perform checks on whether the names supplied are valid,
    i.e the `Name` currently exists in FumbleLog, and that the `Name` is currently assigned to the `Event`.

[Scroll back to Table of Contents](#table-of-contents)

### Ability to assign groups to an event

#### Implementation

- The ability to assign a `Group` to an `Event` is facilitated by `ModelManager`.
- Each `Event` stores a list of `Groups` assigned to it. 
  - That is, when a `Group` is assigned to an `Event`, a `Group`object is stored a `Set` of `Group`. 
  - When un-assigned, the corresponding groups are then removed from the `Set`.
- To make it easier for the users to assign groups, this action is done through the `AddEventCommand` and `EditEventCommand`, with the `g/` prefix.
  - Users can assign multiple groups to an event by using multiple `g/` identifiers following
    with the `Group` specified. The `ModelManager` will perform checks on whether the names supplied are valid,
    i.e the `Group` currently exists in FumbleLog.
- To un-assign a `Group`, the user must manually specify `ug/` with the `Group` to un-assign it from the `Event`.
  - The `ModelManager` will perform checks on whether the groups supplied are valid,
    i.e the `Group` currently exists in FumbleLog, and that the `Group` is currently assigned to the `Event`.
- With the `Group` name, person(s) with that specific `Group` in their `Group` list is displayed with the `Event`.
- After a `Group` has been assigned to an `Event`, all `Person` in that `Group` will be displayed with the `Event` on FumbleLog.

A successful `EditEventCommand` that assigns groups should look like this:

<img src="images/AssignGroupsSequenceDiagram.png" alt="AssignGroup" width=600 />

This is a possible object representation of an `Event` with a `Group` and a `Person` assigned to it.

<img src="images/EditEventObjectDiagram.png" alt="AssignGroup" width=600 />

- In this object diagram, the `Event`, `TP meeting` has a `Person`, John, assigned to it and a `Group` CS2103T assigned to it. 
- In this case, TP meeting only stores these information and will use its respectively `Person` list and `Group` list to display:
  - John as assigned to it
  - Bob and Alice as assigned to it within a group.

#### Design considerations

- When adding and displaying groups, persons that has been added individually previously will be displayed twice. To counter that, checks are done to ensure that
when a group is added, duplicate persons will be deleted from the individual persons list
- A person can belong to multiple groups, due to the multiplicity between groups and persons. In this case, we allow multiple persons to be displayed, as it is clear which group they belong to.
- As the persons are searched by their group name only when displaying, adding new persons, editing and deleting persons is simple as the component just reloads and searches for everybody in the groups again.

[Scroll back to Table of Contents](#table-of-contents)

### Ability to find persons and events

The `find_XYZ` commands in our application displays persons and/or events that fit the keyword(s).

#### Implementation

The original `find` command has been split into three new commands, `find_person`, `find_event`, and `find_all`, which
are collectively called `find_XYZ` commands.
* `find_person` command allows the user to find persons with fitting name or groups.
* `find_event` command allows the user to find events with fitting name, assigned persons and groups, or person under assigned groups.
* `find_all` command allows the user to find persons and events, which includes the criteria mentioned above.

The `find_person` command involves checking the current full list of persons and filtering out persons with fitting names 
or groups. This is done using `PersonNameOrGroupContainsKeywordsPredicate`, which enhanced from the original 
`NameContainsKeywordsPredicate` class. The predicate is then passed to `Model#updateFilteredPersonList(Predicate<Person> predicate)`.

Meanwhile, the `find_event` command involves checking the current full list of events and filtering out event with fitting names, groups or persons.
This is done using `EventNameOrGroupContainsKeywordsPredicate`, which also enhanced from `NameContainsKeywordsPredicate` 
class. The predicate is then passed to `Model#updateFilteredEventList(Predicate<Event> predicate)`.

`find_all` command covers the mechanism of both commands mentioned above, which uses both predicates and calls both functions in `Model`.

As a result, the `ObservableList<Person>` and/or `ObservableList<Event>` are updated with the filtered lists of persons and events.
The `UI` component is notified of these new changes to the lists and updates the UI accordingly, which will show the updated lists.

#### Feature details

1. The `find_XYZ` commands can accept one or more parameter `keyword` for searching persons and/or events.
2. A `PersonNameOrGroupContainsKeywordsPredicate` and/or `EventNameOrGroupContainsKeywordsPredicate` will be created and a `FindXYZ` command will be created with the predicates.
3. The `FindXYZ` command will then be executed and the `UI` will be updated with the filtered lists of persons and/or events.

The flow for the `find_XYZ` commands are described by the following sequence diagrams:

#### `find_person`
![FindPersonSequenceDiagram](images/FindPersonSequenceDiagram.png)

#### `find_event`
![FindEventSequenceDiagram](images/FindEventSequenceDiagram.png)

#### `find_all`
![FindAllSequenceDiagram](images/FindAllSequenceDiagram.png)

#### General design considerations

**Aspect: Keyword target differentiation**

- **Alternative 1 (Current choice): Find all persons that fits the keyword.**
    - Pros:
        - Easier to implement.
    - Cons:
        - Might get unwanted results, which decreases overall experience.
- **Alternative 2: Differentiate the target of keyword with syntax.**
    - Pros:
        - User can find exact person or group.
    - Cons:
        - Adding constraint the original command by requiring syntax, which may cause convenience.

[Scroll back to Table of Contents](#table-of-contents)

### Remind feature

The `remind` command in our application displays a birthdays and events that will happen within a specified number of days.

#### Implementation

The `remind` feature involves checking the current filtered list of persons and events and filtering out persons with birthdays and events with starting date
that are within the specified number of days. This is done using `BirthdayWithinDaysPredicate` and `EventWithinDaysPredicate` which implements the `Predicate<T>` interface. These predicates are passed
to `Model#updateFilteredPersonList(Predicate<Person> predicate)` and `Model#updateFilteredEventList(Predicate<Event> predicate)` respectively.

As a result, the `ObservableList<Person>` and `ObservableList<Event>` are updated with the filtered lists of persons and events respectively.
The `UI` component is notified of these new changes to the lists and updates the UI accordingly, which will show the updated persons and events.

The `remind` command is implemented this way as it reuses the logic for the `find` command where it utilises the `Model` component to update the current list of persons based on the given predicate.
Instead of filtering out persons based on names, the `BirthdayWithinDaysPredicate` filters out persons based on their birthdays and the `EventWithinDaysPredicate` filters out events based on their starting dates.

The flow for the `remind` command is described by the following sequence diagram:

![RemindSequenceDiagram](images/RemindSequenceDiagram.png)

#### Feature details
1. The `remind` command can accept an optional parameter `days` which specifies the number of days to search for birthdays and events. If `days` is not specified, the default value of 7 days will be used.
2. The application will validate the argument `days` to ensure that it is a positive integer. If it is not, an error message will be shown to the user and prompts the user for a corrected input.
3. If it is a valid input, a `BirthdayWithinDaysPredicate` and `EventWithinDaysPredicate` will be created and a `Remind` command will be created with the predicates.
4. The `Remind` command will then be executed and the `UI` will be updated with the filtered lists of persons and events.

#### General design considerations

- **Alternative 1 (Current choice): Updating list with predicate.**
    - Pros:
        - Reuses the logic of the original `find` command.
        - The `UI` component is notified of the changes to the list and updates the UI accordingly.
    - Cons:
        - The `Model` component is tightly coupled with the `UI` component.
- **Alternative 2: Checking current list for birthdays and events, and adding to new list.**
    - Pros:
        - Easier to implement.
    - Cons:
        - Performance overhead. New addressbook objects needs to be created.

[Scroll back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

## **Proposed Features**

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete_person 5` command to delete the 5th person in the address book. The `delete_person` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete_person 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add_person n/David …​` to add a new person. The `add_person` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list_all`. Commands that do not modify the address book, such as `list_all`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add_person n/David …​` command. This is the behavior that most modern desktop applications follow.

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
  * Pros: Will use less memory (e.g. for `delete_person`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Appendix: Requirements**

### Product scope

**Target user profile**:

This product is targeted at busy university students who struggle to manage
their interpersonal relationships and commitments due to the demands of their
academic and social lives. They are relatively tech savvy and prefer to use the
keyboard over the mouse, prefer short commands over full sentences. 
These users seek an intuitive and efficient solution to help them stay organized,
prioritize their tasks and manage their time effectively.

**Value proposition**:

We provide students with an easy-to-use application to manage their social lives and 
time better. Our app reminds students of their upcoming commitments and helps them to
prioritize their tasks. It also helps them to keep track of their friends’ birthdays and
other important events.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​            | I want to …​                                                   | So that I can…​                                                                |
|----------|--------------------|----------------------------------------------------------------|--------------------------------------------------------------------------------|
| `* * *`  | university student | see usage instructions                                         | refer to instructions when I forget how to use the App                         |
| `* * *`  | university student | add a new person                                               | keep my address book up to date                                                |
| `* * *`  | university student | include optional fields when adding contacts                   | include comprehensive and personalized information for each contact            |
| `* * *`  | university student | assign contacts to groups                                      | efficiently organise my contacts by grouping them together                     |
| `* * *`  | university student | delete a person                                                | remove contacts that I no longer need                                          |
| `* * *`  | university student | find a person by name                                          | locate details of persons by name without having to go through the entire list |
| `* * *`  | university student | find a person by group                                         | locate details of persons based on which group the person is in                |
| `* * *`  | university student | find an event by event name                                    | locate details of an event without having to go through the entire list        |
| `* * *`  | university student | find an event by people or groups attending                    | locate details of an event based on the people or groups attending             |
| `* * *`  | university student | find contacts and events at the same time                      | quickly search for contacts and events that are related to each other          | 
| `* * *`  | university student | edit a person details                                          | reflect any contact changes accordingly                                        |   
| `* * *`  | university student | create an event                                                | schedule and keep track of important commitments                               |
| `* * *`  | university student | include optional fields when adding events                     | include comprehensive and personalized information for each event              |
| `* * *`  | university student | assign contacts to events                                      | keep track of who is attending an event                                        |
| `* * *`  | university student | edit an event details                                          | modify event details if the details of event has changed                       |
| `* * *`  | university student | delete an event                                                | remove events that are canceled or no longer relevant                          |
| `* * *`  | university student | view all upcoming events on a separate event column in the GUI | simultaneously view contact details and event details                          |
| `* * *`  | university student | be reminded on events and birthdays                            | so that i can remember upcoming social activities                              |

[Scroll back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

### Use cases

For all use cases below, unless specified otherwise:
- the **System** is `FumbleLog`
- the **Person** is the `user`
- the **Actors** are `Computing student`

**Use case: UC01 - Add a person**

**MSS**
1. User requests to add persons
2. FumbleLog adds the person

   Use case ends.

**Extensions**
* 1a. User supplies the wrong type of parameters when adding the person
    
    * 2a1. FumbleLog shows an error message.

      Use case resumes at step 1.
* 2a. Person is added with a group and that group is assigned to an event
  
    * 2a1. FumbleLog reloaded the event component and displays the newly added person in the event.

      Use case ends.

**Use case: UC02 - Edit a person**

**MSS**
1. User requests to list persons
2. FumbleLog shows a list of persons
3. User request to edit a specific person in the list
4. FumbleLog edits the person with the new information

   Use case ends.

**Extensions**
* 2a. List is empty

  Use case ends.

* 3a. User supplies an invalid index to edit
    
    * 3a1. FumbleLog shows an error message.

      Use case resumes at step 2.

* 3b. User modifies the name of the person

    * 3b1. FumbleLog updates the name of the person in all events that the person is <u> assigned </u> to. This includes persons in groups.

      Use case resumes at step 4.

* 3c. User removes a group(s) from the person and that group(s) is assigned to an event.

    * 3c1. FumbleLog removes the person from the corresponding group in all events.

      Use case resumes at step 4.

* 3d. User adds a group(s) to the person and that group(s) is assigned to an event.
    * 3d1. FumbleLog adds the person to the corresponding group(s) in all events.

      Use case resumes at step 4.

**Use case: UC03 - Delete a person**

**MSS**

1.  User requests to list persons
2.  FumbleLog shows a list of persons
3.  User requests to delete a specific person in the list
4.  FumbleLog deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. FumbleLog shows an error message.

      Use case resumes at step 2.

* 3b. The person is assigned to an event.

    * 3b1. The person is removed from the event.

      Use case resumes at step 4.

* 4a. The person is the last member of a group and that group is assigned to an event.

    * 4a1. The group is removed from the event.

      Use case ends.

**Use case: UC04 - Add an event**

**MSS**

1. User requests to add a event
2. FumbleLog adds the event with the supplied information

   Use case ends.

**Extensions**
* 1a. User supplies invalid parameters

    * 1a1. FumbleLog shows an error message

      Use case resumes at step 1.
* 1b. User supplies a date that is before the current date

    * 1b1. FumbleLog shows an error message

      Use case resumes at step 1.
* 1c. User supplies a start time that is after the end time

    * 1c1. FumbleLog shows an error message

      Use case resumes at step 1.
* 1d. User supplies a start time that is before the current time

    * 1d1. FumbleLog shows an error message

      Use case resumes at step 1.

 **Use case: UC05 - Edit an event**

 **MSS**
1. User request to edit a specific event in the list
2. FumbleLog edits the event with the new information

   Use case ends.

**Extensions**
* 1a. List is empty

  Use case ends.

* 1b. User supplies an invalid index to edit
    
    * 1b1. FumbleLog shows an error message.

      Use case resumes at step 1.
* 1c. User supplies an invalid parameter
   * 1c1. FumbleLog shows an error message.

      Use case resumes at step 1.
* 1d. User supplies a date that is before the current date

    * 1d1. FumbleLog shows an error message

      Use case resumes at step 1.
* 1e. User supplies a start time that is after the end time

    * 1e1. FumbleLog shows an error message

      Use case resumes at step 1.
* 1f. User supplies a start time that is before the current time

    * 1f1. FumbleLog shows an error message

      Use case resumes at step 2.
* 2a. User enters a group and certain members of the group is already 
assigned to the event individually.

    * 2a1. For each Event, duplicate members will be removed from the 
    individual Persons list.

      Use case ends

**Use case: UC06 - Delete an event**

**MSS**

1.  User requests to list events 
2.  FumbleLog shows a list of events
3.  User requests to delete a specific event in the list
4.  FumbleLog deletes the event

    Use case ends.

**Extensions**

* 2a. The list is empty

  Use case ends.

* 3a. The given index is invalid

    * 3a1. FumbleLog shows an error message.

      Use case resumes at step 2.

**Use case: UC07 - Assigning a person to an event**

**MSS**
1. User requests to show a list of events
2. FumbleLog shows list of events
3. User requests to assign a person(s) to a specific event in the list
4. FumbleLog assigns the person(s) to the event
5. FumbleLog displays the all person(s) with that group under the event

   Use case ends.

**Extensions**

* 1a. The list is empty

  Use case ends.

* 3a. User tries to assign a person to an invalid event
    * 3a1. FumbleLog shows an error message

      Use case resumes at step 3.
* 3b. User tries to assign an invalid person to an event.
    * 3b1. FumbleLog shows an error message.

      Use case ends.
* 4a. User tries to assign a person to an event that already has the person assigned

  Use case ends.

**Use case: UC08 - Assigning a group to an event**

**MSS**
1. User requests to show a list of events
2. FumbleLog shows list of events
3. User requests to assign a group(s) to a specific event in the list
4. FumbleLog assigns the group(s) to the event
5. FumbleLog displays the all person(s) with that group under the event

   Use case ends.

**Extensions**

* 1a. The list is empty

  Use case ends.

* 3a. User tries to assign a group to an invalid event
    * 3a1. FumbleLog shows an error message

      Use case resumes at step 3.

* 3b. User tries to assign an invalid group to an event.
    * 3b1. FumbleLog shows an error message.

      Use case ends.

* 4a. User tries to assign a group to an event that already has the group assigned

  Use case ends.

* 5a. A person is displayed as an individual and is a member to the group.

    * 5a1. FumbleLog removes the person from the individual list.

      Use case ends.
 
**Use case: UC09 - Find persons**
1. User requests to list all the persons.
2. FumbleLog shows a list of persons
3. User requests to find persons by specifying a group or name
4. FumbleLog shows the list of persons that belong in the specified group

   Use case ends.

**Extensions**

* 3a. The group does not exist

    * 3a1. FumbleLog shows an empty list.

    Use case resumes at step 3.

* 3b. The person does not exist

    * 3b1. FumbleLog shows an empty list. 

    Use case resumes at step 3.

* 4a. The list is empty

  Use case ends.

**Use case: UC10 - Find events**
1. User requests to list all the events.
2. FumbleLog shows a list of events
3. User requests to find events by specifying a group or event name
4. FumbleLog shows the list of events that belong in the specified group

   Use case ends.

**Extensions**

* 3a. The group does not exist
    * 3a1. FumbleLog shows an empty list.

    Use case resumes at step 3.
* 3b. The event does not exist
    * 3b1. FumbleLog shows an empty list. 
* 4a. The list is empty

  Use case ends.


**Use case: UC11 - Show reminders for events/birthdays happening soon**

**MSS**
1. User requests a reminder for events/birthdays happening soon using the 'remind' command.
2. FumbleLog displays a list of events/birthdays happening in the next 7 days by default.

   Use case ends.

**Extensions**

* 1a.  User can specify the number of days to look ahead for events and birthdays after the 'remind' command.
    * 1a1.   If the specified range is valid (e.g., a positive integer), FumbleLog shows the list of events/birthdays happening within the specified range of time (n days).
    * 1a2.   If the specified range is invalid (e.g., a negative integer), FumbleLog shows an error message.

   Use case ends.

* 2a. The list is empty

  Use case ends.

<div style="page-break-after: always;"></div>

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should be able to hold up to 1000 events without a noticeable sluggishness in performance for typical usage.
5.  The data stored on the hard drive should be light-weight and not take too much space.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Person**: A person or entity that is associated with an event. A person contains a name and a list of contact details, such as phone number, email address, etc.
* **Event**:  An encompassing term that refers to any organized occurrence or gathering,  
which can include various types of activities, such as meetings, birthdays, and other scheduled events.
* **Meeting**: A specific type of event that involves the interaction of two or more individuals. Contacts or groups of contacts can be assigned to a single meeting.
* **Group**: A collection of contacts grouped together for organizational purposes. 
Contacts or groups can be assigned to a single meeting, allowing for efficient management and coordination of events and interactions.
* **GUI**: Graphical User Interface
* **CLI**: Command Line Interface
* **UI**: User Interface
* **MSS**: Main Success Scenario
* **UC**: Use Case
* **VCS**: Version Control System
* **CI**: Continuous Integration

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a person

1. Adding a person when all persons are being shown

    1. Test case: `add_person n/James p/93748274 e/james@gmail.com a/computing drive b/2001-10-20`<br>
        Expected: A contact is added to the list with the given parameters. Details of the added person will show in the response box.

2. Adding a person when the contacts list is filtered
    2. Prerequisite: Filter the list using the `find_person` command or the `remind` command

    3. Test case: `add_person n/John`<br>
        Expected: The contacts list shows all persons again. The contact is added to the contact list at the back. The added person's details is shown in the response box.

3. Adding a person with a group that has been assigned to an event. 

    1. Prerequisite: The event list has an event with the group `CS2103T` assigned to it. The contact and event list is not filtered.

    2. Test case: `add_person n/John g/CS2103T`<br>
        Expected: The person is added to the contact list. The added person's details are shown in the response box. The person is added to the event, under the group that it has been assigned to.

### Editing a person

1. Editing a person when all persons are being shown

    1. Prerequisite: List all persons using the `list_all` command. There should be multiple persons in the contact list.

    1. Test case: `edit_person 1 n/John Donuts`<br>
        Expected: The person's name should be updated. The person's details should be displayed in the response box.
2. Editing a person when the contacts list is filtered

    1. Prerequisite: Filter the list using the `find_person` command or the `remind` command. Ensure that there is at least 1 person in the contact list.

    1. Test case: `edit_person 1 n/Johnny`<br>
        Expected: The contact list stays filtered. If the person's name was the search term in `find_person`, the person should disappear. Used `list_all` to view the edited person. The details of the edited person should be displayed in the response box.

3. Editing a person with a group that has already been assigned to an event

    1. Prerequisite: The event list has an event with the group `CS2103T` assigned to it. The contact and event list is not filtered.

    1. Test case: `edit_person 1 n/Johnny g/CS2103T`<br>
        Expected: The person is edited in the contact list. The person's details are shown in the response box. The person is added to the event, under the group that it has been assigned to.

4. Editing a person to unassign a group, given that the group has been assigned to an event

    1. Prerequisite: The event list has an event with the group `CS2103T` assigned to it. The contact and event list is not filtered.

    1. Test case: `edit_person 1 ug/CS2103T`<br>
        Expected: The person is edited in the contact list. The person's details are shown in the response box. The person is removed from the event, under the group that it has been assigned to. If the person is the last member of the group, the group is removed from the event.
       
### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list_all` or `list_persons` command. Multiple persons in the list.

   1. Test case: `delete_person 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete_person 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

2. Deleting a person with a group that has been assigned to an event

    1. Prerequisite: The event list has an event with the group `CS2103T` assigned to it. The contact and event list is not filtered.
    
    1. Test case: `delete_person 1`<br>
        Expected: The person is deleted from the contact list. The person is removed from the event, under the group that it has been assigned to. If the person is the last member of the group, the group is removed from the event.

### Finding a person

1. Find a person while all persons are being shown

    1. Prerequisite: There is a person with the name `Alex` in the contacts list

    1. Test case: `find_person Alex`<br>
       Expected: All persons with the name `Alex` or the group `Alex` (not case sensitive) should be displayed in the newly filtered contacts list. The number of persons listed should be displayed in the response box.

### Adding an event

1. Adding an event while all events are being shown

    1. Prerequisites: List all events using the `list_all` command. Multiple events should be shown.
    
    1. Test case: `add_event m/FumbleLog presentation d/2023-11-30`<br>
       Expected: An event is added to the list with the given parameters. Details of the added event will show in the response box.
2. Adding an event while the events list is filtered

    1. Prerequisite: Filter the list using the `find_event` command or the `remind` command

    1. Test case: `add_event m/CS2103T lecture d/2030-11-30`<br>
       Expected: The event list shows all events again. The event is added to the event list at the back. The added event's details is shown in the response box.

### Editing an event

1. Editing an event while all events are being shown

    1. Prerequisite: List all events using the `list_all` command. There should be multiple events in the event list.

    1. Test case: `edit_event 1 m/CS2103T tutorial`<br>
       Expected: The entire event list will be shown again. The event's name should be updated to the given parameter. The event's details should be displayed in the response box.

2. Editing an event while the events list is filtered

    1. Prerequisite: Filter the list using the `find_event` command or the `remind` command. Ensure that there is at least 1 event in the event list.

    1. Test case: `edit_event 1 m/CS2103T tutorial`<br>
       Expected: The event list stays filtered. If the event's name was the search term in `find_event`, the event should disappear. Use `list_all` to view the disappeared edited event. The details of the edited event should be displayed in the response box.

3. Editing an event with a new group

    1. Prerequisite: The event list has an event with the group `CS2103T` assigned to it. The contact and event list is not filtered.

    1. Test case: `edit_event 1 m/CS2103T tutorial g/CS2103T`<br>
       Expected: The event is edited in the event list. The event's details are shown in the response box. The group is added to the event, under which all persons part of the group is displayed.

### Deleting an event

1. Deleting an event while all events are being shown

   1. Prerequisites: List all events using the `list_all` or `list_events` command. Multiple events in the list.

   1. Test case: `delete_event 1`<br>
      Expected: First event is deleted from the list. Details of the deleted event shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete_event 0`<br>
      Expected: No event is deleted. Error details shown in the status message. Status bar remains the same.

### Finding an event

1. Find an event while all events are being shown

    1. Prerequisite: There is an event with the name `CS2103T` in the events list

    1. Test case: `find_event CS2103T`<br>
       Expected: All events with the name `CS2103T` or the group `CS2103T ` or assigned persons with name `CS2103T` (not case sensitive) should be displayed in the newly filtered events list. The number of events listed should be displayed in the response box.

### Finding a person or event

1. Find a person or event while all persons and events are being shown

    1. Prerequisite: There is a person with the name `Alex` and an event with a person assigned named `Alex` in the contacts list and events list respectively

    1. Test case: `find_all Alex`<br>
       Expected: All persons and events with the name `Alex` or the group `Alex` or for events, with assigned persons with name `Alex` (not case sensitive) should be displayed in the newly filtered contacts list and events list respectively. The number of persons and events listed should be displayed in the response box.

### Remind

1. Remind with default number of days, with all persons and events being shown

    1. Prerequisite: All persons and events are being shown, using the `list_all` command. There should be multiple persons and events in the contact list and event list respectively.

    1. Test case: `remind`<br>
       Expected: All events with dates that are 7 days away and persons with birthdays that are 7 days away will be displayed in the newly filtered contacts list and events list respectively. The number of days used to filter the lists will be displayed in the response box. If there are no persons with birthdays or events with dates that match the search term, then the persons and event list will be empty respectively.

2. Remind with a set number of days

    1. Prerequisite: All persons and events are being shown, using the `list_all` command. There should be multiple persons and events in the contact list and event list respectively.

    1. Test case: `remind 10`<br>
       Expected: All events with dates that are 10 days away and persons with birthdays that are 10 days away will be displayed in the newly filtered contacts list and events list respectively. The number of days used to filter the lists will be displayed in the response box. If there are no persons with birthdays or events with dates that match the search term, then the persons and event list will be empty respectively.

### Saving data

1. Dealing with missing/corrupted data files

   1. FumbleLog will load with empty contacts list and events list in this event.


[Scroll back to Table of Contents](#table-of-contents)
