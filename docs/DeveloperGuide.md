---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-F10-4/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-F10-4/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
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

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-F10-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `ScheduleListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-F10-4/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-F10-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-F10-4/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-F10-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the tutee data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-F10-4/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both Tuition Connect data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add feature
The `AddCommand` extends the `Command` class. While mostly similar to `delete` illustrated above, the command contains
checks to prevent any duplicate `Person` object (i.e. same name and phone number) as well as clashes in schedules.
If it passes these checks, the person is added into the system.

`AddCommand` takes in the following fields:
* **Name (Compulsory field)**: String composed of character between A-Z and a-z.
* **Phone number (Compulsory field)**: Any number at least 3 digits long.
* **Email (Compulsory field)** String with restrictions in characters (XXXXXXXX@emaildomain)
* **Address (Compulsory field)**: String without restriction in characters.
* **Subject (Compulsory field)**: String without restriction in characters.
* **Day (Compulsory field)**: String with restrictions in characters, non-case sensitive (Mon/Monday/Tue/Tuesday/Wed/Wednesday/Thu/Thursday/Fri/Friday/Sat/Saturday/Sun/Sunday).
* **Begin (Compulsory field)**: String with restrictions (HHMM).
* **End (Compulsory field)**: String with restrictions (HHMM).
* **PayRate (Compulsory field)**: String with restrictions in characters, only numbers allowed (no negative numbers)

The following sequence diagram shows how the add command works.

![AddSequenceDiagram](images/AddSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command:

![AddActivityDiagram](images/AddActivityDiagram.png)

#### Design considerations:

**Aspect: How add executes:**

* **Alternative 1 (current choice):** All fields must be included in a single command input.
    * Pros: Easy to implement.
    * Cons: Command input may be too long and less user-friendly.

* **Alternative 2**: Allow for optional parameters with default values, with the tutee's name and phone being the compulsory ones.
  * Pros: More user-friendly, command will not be too lengthy.
  * Cons: Harder to implement. 

### List feature

There are three commands that deal with listing tutees:

1. `ListCommand` - Shows the current list of all tutees in the list
2. `ListByDayCommand` - Shows the current list of tutees who have lessons on a specified day
3. `ListUnPaidCommand` - Shows the current list of tutees who have not paid

The `ListCommand` extends the `Command` class. Both the `ListByDayCommand` and the `ListUnPaidCommand` extend the `ListCommand` class. All three commands override `Command#execute`.
The `ListCommandParser` is responsible for returning the appropriate `ListCommand`  based on the command format.



The `ListByDayCommand`  is initialised with a `DayPredicate` and updates the `FilteredPersonList` to only display Persons whose `Day` field matches the specified input.

The following sequence diagram shows how the list by day command works.

![ListByDaySequenceDiagram](images/ListByDaySequenceDiagram.png)

The `ListUnPaidCommand`  follows a similar implementation to `ListByDayCommand`. It is initialised with a `PaidPredicate` instead and updates
the `FilteredPersonList` to only display Persons whose `isPaid` field is false.

#### Design considerations:

**Aspect: How to implement `ListByDayCommand` and `ListUnPaidCommand`:**

* **Alternative 1 (current choice):** Extend the `ListCommand` class.
  * Pros: Greater use of OOP.
  * Cons: Harder to implement.

* **Alternative 2:** Individual command class without extending `ListCommand`.
  * Pros: Easier to implement.
  * Cons: Less abstraction.

### Find feature
The `FindCommand` extends the `Command` class. It allows the user to find for tutees by specifying their names and/or 
subject using their prefixes. Both parameters are optional, but at least one of them must be specified for the `find`
command to work properly.

`FindCommand` takes in the following fields:
* **Name (Optional field)**: String composed of character between A-Z and a-z.
* **Subject (Optional field)**: String without restriction in characters.

The following sequence diagram shows how the edit command works.
![FindSequenceDiagram](images/FindSequenceDiagram.png)

#### Design considerations:

**Aspect: How find executes:**

* **Alternative 1 (current choice):** Find tutees based on inputs for prefixes n/ and sb/.
    * Pros: More sophisticated as it can search for subjects rather than only for name.
    * Cons: Less user-friendly for beginners as it requires an extra step of inputting prefixes.
* **Alternative 2:** Find tutees based on their name.
    * Pros: More user-friendly as the command format would only be `find [name]`.
    * Cons: Users cannot search for tutees by subject.

### Edit feature
The `EditCommand` extends the `Command` class. It allows the user to edit fields of the tutee by specifying the index
of the tutee. The command contains checks to prevent any duplicate `Person` object (i.e. same name and phone number) 
as well as clashes in schedules. If it passes these checks, the person is edited successfully.

`EditCommand` takes in the following fields:
* **Index (Compulsory Field)**: Numbers between 1 to the number of people inside the list.
* **Name (Optional field)**: String composed of character between A-Z and a-z.
* **Phone number (Optional field)**: Any number at least 3 digits long.
* **Email (Optional field)** String with restrictions in characters (XXXXXXXX@emaildomain)
* **Address (Optional field)**: String without restriction in characters.
* **Subject (Optional field)**: String without restriction in characters.
* **Day (Optional field)**: String with restrictions in characters, non-case sensitive (Mon/Monday/Tue/Tuesday/Wed/Wednesday/Thu/Thursday/Fri/Friday/Sat/Saturday/Sun/Sunday).
* **Begin (Optional field)**: String with restrictions (HHMM).
* **End (Optional field)**: String with restrictions (HHMM).
* **PayRate (Optional field)**: String with restrictions in characters, only numbers allowed (no negative numbers)

The following sequence diagram shows how the edit command works.
![EditSequenceDiagram](images/EditSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes an edit command:
![EditActivityDiagram](images/EditActivityDiagram.png)

#### Design considerations:

**Aspect: How edit executes:**

* **Alternative 1 (current choice):** User specify which fields to edit by their prefixes.
    * Pros: User can edit the fields that require changes by specifying their prefix.
    * Cons: Command input may be too long and less user-friendly.
* **Alternative 2:** Users cannot edit tutees that are already added, and can only do delete and re-adding 
of tutees whenever changes are necessary.
    * Pros: Less prone to bugs, and is simpler for developers to implement.
    * Cons: Not user-friendly and takes multiple steps for the user.

#### Design considerations:

**Aspect: How to implement `ListByDayCommand` and `ListUnPaidCommand`:**

* **Alternative 1 (current choice):** Extend the `ListCommand` class.
    * Pros: Greater use of OOP.
    * Cons: Harder to implement.

* **Alternative 2:** Individual command class without extending `ListCommand`.
    * Pros: Easier to implement.
    * Cons: Less abstraction.

### Find Free Time feature

The `freeTime` Command extends the `Command` class.

`freeTime` takes in the following fields:
* **Day (Compulsory field)**: String with restrictions in characters, non-case sensitive (Mon/MondayTue/Tuesday/Wed/Wednesday/Thu/Thursday/Fri/Friday/Sat/Saturday/Sun/Sunday).
* **Duration (Compulsory field)**: Positive Integer to represent duration in **minutes**.
* **Begin (Compulsory field)**: String with restrictions (HHMM).
* **End (Compulsory field)**: String with restrictions (HHMM).

It displays a list of timeslots where the user is free on that _Day_, starting from _Begin_ to _End_. The timeslots listed down
must also be greater than the duration provided.

The following sequence diagram shows how the `freeTime` command works.
![FreeTimeSequenceDiagram](images/FreeTimeSequenceDiagram.png)

Variables inside the sequence diagram:
* toFind, Interval: Both are instances of the `Interval` Class which encapsulates the `Day`, `Duration`, `Begin`, `End` fields
* results: A list of strings where the user is busy on the specified given `Interval` class.
* timeslots: A list of timeslots after parsing the list of strings.
* availableTime: A list of timeslots where the user is free.

The following activity diagram summarizes what happens when a user executes a `freeTime` command:
![FreeTimeActivityDiagram](images/FreeTimeActivityDiagram.png)

#### Design Considerations
**Aspect: How `freeTime` executes:**

* **Alternative 1 (current choice):** The command first finds timeslots when the user is busy on that _Day_ by looking at the tutees' schedules inside the
  `UniquePersonList`. The TimeSlot Class finds free time based on the list of
  timeslots when the user is busy, and then returns a list of timeslots where the user is free. (Each timeslot is between _Begin_ and _End_,
and is at least _Duration_ long)
  * Pros: Command is short and simple to use.
  * Cons: During the first round of user-testing, some new users were confused on how to use the command.


### Calculate monthly revenue


The `RevenueCommand` extends the `command class`. The command first gets a list containing all tutees.
The total monthly revenue can be calculated now by iterating through the list and calling `Person#getMonthlyFee`. <br>

The total monthly revenue is calculated as such: <br> 
*Total Monthly Revenue* = Sum of every tutee's `monthlyFee`

The following sequence diagram shows how the `RevenueCommand` works:
![RevenueSequenceDiagram.png](images/RevenueSequenceDiagram.png)

#### Design Considerations
**Aspect: How `monthlyFee` is calculated:**

* **Alternative 1 (current choice):** Calculate `monthlyFee` only when executing `RevenueCommand`.
    * Pros: Up-to-date revenue figure as `PayRate` value and number of lessons monthly may change over time.
    * Cons: Potentially more method calls to generate same value.

* **Alternative 2:** Calculate `monthlyFee` when instantiating `Person` and include it as a field in `Person`.
    * Pros: Readily accessible `monthlyFee` value.
    * Cons: Have to implement logic to update `monthlyFee` when `PayRate` value and number of lessons monthly changes.


### Undo/redo feature

The undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current tutee list state in its history.
* `VersionedAddressBook#undo()` — Restores the previous tutee list state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone tutee list state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial tutee list state, and the `currentStatePointer` pointing to that single tutee list state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the tutee list. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the tutee list after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted tutee list state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified tutee list state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the tutee list state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous tutee list state, and restores the tutee list to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the tutee list to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest tutee list state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the tutee list, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all tutee list states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes**

* **Alternative 1 (current choice):** Saves the entire tutee list.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

### Mark paid/unpaid features
The proposed mark paid/check paid mechanism can check whether the person has paid or not by implementing a new boolean field 'paid' in the person object, it implements the following operations:

* `paid [index]` — Mark the person at the index as paid.
* `unpaid [index]` — Mark the person at the index as not paid.
* `list unpaid` — List all the persons who haven't paid in the list.
* `unpaidAll` — Reset all students' payment status to not paid.

The following sequence diagram shows how paid command works:

![PaidSequenceDiagram.png](images/PaidSequenceDiagram.png)

The unpaid command works similar to the paid command.

The following sequence diagram shows how unpaidAll command works:

![UnpaidAllSequenceDiagram.png](images/UnpaidAllSequenceDiagram.png)

#### Design considerations:

**Aspect: The choice of paid data type:**

* **Alternative 1 (current choice):** Use simple boolean value.
    * Pros: Easy to implement, fits the requirement: two status (paid, not paid).
    * Cons: Different from all other fields in the person class, hard to maintain.

* **Alternative 2:** Create a new paid class.
    * Pros: Fits the other fields in the class.
    * Cons: Hard to implement, waste of source (such as code storage, might affect the efficiency of the code).

**Aspect: How to implement mark paid features:**

* **Alternative 1 (current choice):** Create a new person, set everything else the same as before, and set paid as true.
    * Pros: Since we created a new person, the command works individually and not depends on the other commands.
    * Cons: Hard to implement.

* **Alternative 2:** Use the edit command to edit the paid status of the person.
    * Pros: Easy to implement.
    * Cons: The paid command will rely on the edit commands, which violates the principle to reduce correlation between classes.



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

**Target user profile**: Private tutors not affiliated to any tuition organisations

* has a need to manage multiple tutees
* has a need for managing personal tutoring schedule 
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: It is tedious for tutors to keep track of multiple students and this is done conventionally through calendar applications. Simplify tutoring business with TuitionConnect. Effortlessly manage students, schedules and progress tracking while ensuring financial organization in an all in one product at a faster rate than non CLI applications.

x

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`


| Priority | As a …​ | I want to …​                                            | So that I can…​                                               |
|----------|---------|---------------------------------------------------------|------------------------------------------------------------------------|
| `* * *`  | tutor   | view a list of all tutees                               | see whoever are my tutees that I teach                                 |
| `* * *`  | tutor   | view the specific details of a single tutee             | see the different informations tailored to the tutee                   |
| `* * *`  | tutor   | add a new tutee                                         | keep track of my tutees that I teach                                   |
| `* * *`  | tutor   | find a tutee                                            | search for a specific tutee that I teach                               |
| `* * *`  | tutor   | edit their details                                      | account for changes in their information e.g. change in address        |
| `* *`    | tutor   | remove tutees from the list                             | keep track of tutees that I have stopped teaching                      |
| `* *`    | tutor   | mark students that have already paid                    | keep track of students' payment statuses                               |
| `* *`    | tutor   | check all students who haven't paid                     | easily remind students who haven't paid                                |
| `* *`    | tutor   | undo and redo commands I made in the application        | easily revert any mistakes                                             |
| `* *`    | tutor   | calculate my total monthly revenue                      | better financially plan for my tutoring business                       |
| `* *`    | tutor   | view a list tutees whose lessons fall on a specific day | be reminded if I have any classes on that particular day               |


### Use cases

(For all use cases below, the **System** is the `TuitionConnect` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - List all tutees**

**MSS**

1.  User requests to list all tutees.
2.  System shows all tutees.
3.  System displays the success message.
	
    Use case ends.

**Extensions**

- 2a. The list of tutees is empty.
    - 2a1. System informs the user that the list is empty.
	
    Use case ends.
    <br>
    <br>

**Use case: UC02 - List tutees whose lessons are on a specified day**

**MSS**

1.  User requests to list all tutees whose lessons are on Monday.
2.  System shows all tutees whose lessons are on Monday.

    Use case ends.

**Extensions**

- 2a. The list of tutees is empty.
    - 2a1. System informs the user that the list is empty.

  Use case ends.
  <br>
  <br>

**Use case: UC03 - Add a tutee**

**MSS**

1. User requests to add a tutee.
2. System adds a tutee.
3. System displays the success message.

   Use case ends.

**Extensions**

- 1a. User inputs incomplete tutee data. <br>
    - 1a1. System informs user of the incomplete tutee data.

      Use case resumes at 1.

- 1b. User inputs name and phone number that already exists in the tutee list.
    - 1b1. System informs user of duplicate tutees.

      Use case resumes at 1.
  
- 1c. User inputs a clash in schedule.
    - 1c1. System informs user of the clash in schedules.

      Use case resumes at 1.
  
- 1d. User inputs begin time which is greater than the end time.
  - 1d1. System informs that begin time must be smaller than the end time.

    Use case resumes at 1.

    <br>
    <br>
  
**Use case: UC04 - Delete a tutee**

**MSS**

1.  User views the list of tutees.
2.  User requests to delete a tutee.
3.  System deletes the tutee.
4.  System displays the success message.

    Use case ends.

**Extensions**

- 2a. The tutee that the user is trying to delete does not exist in the list.
    - 2a1. System informs that user does not exist.
      <br>
      <br>
  
**Use case: UC05 - Edit a tutee**

**MSS**

1.  User views the list of tutees.
2.  User requests to edit a tutee.
3.  System edits the tutee.
4.  System displays the success message.

    Use case ends.

**Extensions**
  
- 2a. The schedule of the edited tutee clashes with an existing schedule.
  - 2a1. System informs that there is a clash in schedules.

    Use case resumes at 2.
  
- 2b. The edited begin time is after than the original end time.
  - 2b1. System informs that begin time must be smaller than the end time.

    Use case resumes at 2.
  
- 2c. The edited end time is before the original begin time.
  - System informs that begin time must be smaller than the end time.

    Use case resumes at 2.
  
- 2d. The edited begin time is after the edited begin time.
  - 2d1. System informs that begin time must be smaller than the end time.
    
    Use case resumes at 2.
    <br>
    <br>

**Use case: UC06 - Find a tutee**

**MSS**

1. User requests to find a tutee.
2. System finds the tutee.
3. System displays the success message.

**Extensions**

- 2a. The user inputs more than one word for name field.
  - 2a1. System informs that name can only take one word.

    Use case resumes at 2.
  
- 2b. The user inputs more than one word for subject field.
  - 2b1. System informs that subject can only take one word.

    Use case resumes at 2.
  
- 2c. The user inputs more than one word for name field and one word for subject fields.
  - 2c1. System informs that name can only take one word.

    Use case resumes at 2.
  
- 2d. The user inputs one word for name field and more than one word for subject field.
  - 2d1. System informs that subject can only take one word.

    Use case resumes at 2.
  
- 2e. The user inputs more than one word for both name and subject fields.
  - 2e1. System informs that name can only take one word.

    Use case resumes at 2.
    <br>
    <br>
  
**Use case: UC07 - Mark a tutee as paid**

**MSS**

1.  User views the list of tutees.
2.  User requests mark the specific tutee as paid.
3.  System marks the tutee as paid.
4.  System displays the success message.

    Use case ends.

**Extensions**

- 2a. The tutee that the user is trying to mark as paid does not exist in the list.
    - 2a1. System informs that user does not exist.
      <br>
      <br>

**Use case: UC08 - Reset all tutees in the list to not paid**

**MSS**

1.  User views the list of tutees.
2.  User requests mark all the tutees in the current list as not paid.
3.  System marks all the tutee in the list as not paid.
4.  System displays the success message.

    Use case ends.
    <br>
    <br>

**Use case: UC09 - Undo a command**

**MSS**
1. User requests to undo.
2. System updates the tutee data to the previous state.
3. System informs user that the command is undone.

   Use case ends.

**Extensions**
* 1a. No command to be undone.
    * 1a1. System informs user that there is nothing to undo.

  Use case ends.
  <br>
  <br>

**Use case: UC10 - Redo a command**

**MSS**
1. User requests to redo.
2. System updates the tutee data to the next state.
3. System informs user that the command is redone.

   Use case ends.

**Extensions**
* 1a. No command to be redone.
    * 1a1. System informs user that there is nothing to redo.

  Use case ends.
<br>
<br>

**Use case: UC11 - Finding free time**

**MSS**
1. User requests to find free time
2. System shows the list of available free time

**Extensions**
- 2a. The user does not have any free slots available.
  - 2a1. System informs user that there is no available timeslots.
    <br>
    <br>
  
**Use case: UC12 - Calculating monthly revenue**

**MSS**

1. User requests for monthly revenue.
2. System displays the monthly revenue figure.

   Use case ends.

*{More to be added}*
   <br>
   <br>

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  The software should be platform independent (i.e. work on the Windows, Linux, and OS-X platforms).
3. Should be able to hold up to 1000 tutees without a noticeable sluggishness in performance for typical usage.
4. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
5. The product is a single user product (i.e. The data file created by one user cannot be accessed by another user during regular operations)
6. The data should be stored locally and should be in a human editable text file.
7. The product should not use a DBMS to store data.
8. The software should work without requiring an installer.
9. The software should not depend on a remote server.
10. The GUI should work well (i.e., should not cause any resolution-related inconveniences to the user) for, standard screen resolutions 1920x1080 and higher, and, for screen scales 100% and 125%.
11. the GUI should be usable (i.e., all functions can be used even if the user experience is not optimal) for, resolutions 1280x720 and higher, and, for screen scales 150%.
12. The product should be packaged into a single JAR file.

### Glossary

* Mainstream OS (Operating System): Windows, Linux, Unix, OS-X
* CLI: Command Line Interface, receives commands from user in the form of lines of text
* GUI: Graphical User Interface, a system of interactive user components for computer software
* Command: An instruction for the application to execute
* Timeslot: An interval of time from HH:MM to HH:MM
* Prefix: An abbreviation for the name of the parameter. Prefix should be entered before the actual parameter in a command and always ends with a slash (/).
* MSS: Main success scenario
<br>
--------------------------------------------------------------------------------------------------------------------

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

### List tutee(s)

1. Listing tutee(s)

    1. Prerequisites: At least one tutee in the tutee list.

    2. Test case: `list`<br>
       Expected: Shows the full list of tutees in the tutee list.

    3. Test case: `list mon`<br>
       Expected: Shows tutees whose lessons fall on Monday in the tutee list.

    4. Test case: `list unpaid`<br>
       Expected: Shows tutees who have yet to pay for their lessons in the tutee list.

### Adding a tutee

1. Adding a tutee into the list.

   1. Prerequisites: None
   2. Test case: `add n/Betsy Crowe p/92939402 e/betsycrowe@example.com a/Newgate Prison sb/Secondary 3 Physics d/mon b/1900 end/1930 pr/35.00`<br>
      Expected: The tutee is added into the bottom of the list. Details of the added tutee is shown in the status message.


2. Adding a duplicate tutee into the list


    1. Prerequisites: Completing the first test case for [Adding a tutee](#adding-a-tutee) 
    2. Test case: `add n/Betsy Crowe p/92939402 e/betsycrowe@example.com a/Newgate Prison sb/Secondary 3 Physics d/mon b/1900 end/1930 pr/35.00`<br>
       Expected: The error message _This tutee already exists_ should be displayed in the status message.

### Deleting a tutee

1. Deleting a tutee while all tutees are being shown

   1. Prerequisites: List all tutees using the `list` command. Multiple tutees in the list.

   1. Test case: `delete 1`<br>
      Expected: First tutee is deleted from the list. Details of the deleted tutee shown in the status message. Timestamp in the status bar is updated.
=======

3. Adding a tutee that has clashing schedules.
   1. Prerequisites: Completing the first test case for [Adding a tutee](#adding-a-tutee)
   2. Test case: `add n/Jason Antonius p/12345678 e/test@gmail.com a/PGPR Residences sb/CS1101S d/mon b/1900 end/1930 pr/20` <br>
      Expected: The error message _This date and time clashes with an existing schedule_ should be displayed in the status message.

### Editing a tutee

1. Editing a tutee while all tutees are being shown

   1. Prerequisites: Have the default tutee data and list all tutees using the `list` command.
   2. Test case: `edit 2 n/Betsy Crower a/Betsy street, block 110, #03-02` <br>
      Expected: Tutee is successfully edited, and the details of the edited tutee is shown in the status message.


2. Editing a tutee that causes duplicate tutees
   1. Prerequisites: Have the default tutee data and list all tutees using the `list` command.
   2. Test case: `edit 2 n/Alex Yeoh p/87438807` <br>
      Expected: The error message _This tutee already exists_ should be displayed in the status message.


3. Editing a tutee that causes clashing schedules.
   1. Prerequisites: Have the default tutee data and list all tutees using the `list` command.
   2. Test case: `edit 2 d/Mon b/2000 end/2200` <br>
      Expected: The error message _This date clashes with an existing schedule_ should be displayed in the status message.

### Find free time

1. Finding free time that results in no available timeslots

    1. Prerequisites: Have the default tutee data.
    2. Test case: `freeTime d/Mon dur/30 b/2000 end/2100` <br>
       Expected: The result <br>
   _Here is your list of free time:_ <br>
   _There are no available timeslots._ <br>
   should be displayed in the status message.


2. Finding free time that results in available timeslots

    1. Prerequisites: Have the default tutee data.
    2. Test case: `freeTime d/Mon dur/30 b/1930 end/2130` <br>
       Expected: The result <br>
         _Here is your list of free time:_ <br>
         _Free from 19:30 - 20:00_ <br>
         _Free from 21:00 - 21:30_ <br>
         should be displayed in the status message.
 

### Marking a tutee as paid

1. Marking a tutee as paid while all tutees are being shown

    1. Prerequisites: List all tutees using the `list` command. Multiple tutees in the list.

    1. Test case: `paid 2`<br>
       Expected: Second tutee is from the list is marked as paid. The message of marking person paid success will be shown. Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No tutee is marked as paid. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect paid commands to try: `paid`, `paid x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Marking a tutee as not paid

1. Marking a tutee as not paid while all tutees are being shown

    1. Prerequisites: List all tutees using the `list` command. Multiple tutees in the list.

    1. Test case: `unpaid 3`<br>
       Expected: Second tutee is from the list is marked as not paid. The message of marking person not paid success will be shown. Timestamp in the status bar is updated.

    1. Test case: `unpaid 0`<br>
       Expected: No tutee is marked as not paid. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect unpaid commands to try: `unpaid`, `unpaid x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Listing all unpaid tutees

1. All tutees who haven't paid will be shown

    1. There are tutees in the list.

    1. Test case: `list unpaid`<br>
       Expected: All tutees who haven't paid will be shown. The message of how many tutees are unpaid will be shown. Timestamp in the status bar is updated.

### Undo command

1. Undo previous commands that can modify the data of tutees.

    1. Prerequisites: At least one tutee is present in the tutee list. Execute any command that modify tutee data. In this instruction, `clear` is used.

    2. Test case: `undo`<br>
       Expected: Restore all tutees that were cleared. A message informing the user that the command is successfully undone is displayed.

2. Undo when there are no previous commands that modify the data of tutees.

    1. Prerequisites: Launch the application. Ensure no commands that modify the tutee data is executed.

    2. Test case: `undo`<br>
       Expected: No command is undone. Error details shown in the status message.

### Redo command

1. Redo a command when there is a undo command executed previously.

    1. At least one tutee is present in the tutee list. Execute any command that modify tutee data. In this instruction, `clear` is used followed by `undo`.

    2. Test case: `redo`<br>
       Expected: Clear the tutee list again. 

2. Redo a command when there is no undo command executed previously to redo.

    1. Prerequisites: Ensure no `undo` command is executed after launching the application.

    2. Test case: `redo`<br>
       Expected: No command is redone. Error details shown in the status message.

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

## **Planned Enhancements**

### Batch Processing for Paid Command

Reason: This enhancement allows users to mark multiple persons as paid in a single command, improving efficiency.

Idea: Modify the paid command parser to accept a list of person identifiers (e.g., "paid 1, 2, 3"), and update the command execution logic to iterate through the list and mark each person as paid.

### Scheduled Unpaid Marking

Reason: Introduce a scheduling feature within the unpaid command to set future unpaid statuses for individuals. This would be beneficial for scenarios where payments should automatically lapse after a set period.

Idea: Add a scheduling mechanism within the command execution to mark individuals as unpaid after a specified future date or duration.

### Find using multiple keywords

Reason: To create a more sophisticated find feature for the best results. This enhancement allows users to get more specific results tailored to their criteria.

Idea: Modify the NameContainsKeywordPredicate and SubjectContainsKeywordPredicate to accept multiple word inputs (e.g. "find n/Alex Yeoh sb/Maths Chemistry). 

### Maximum PayRate

Reason: PayRate that are extremely high may not be displayed properly by GUI and are unlikely to be realistic PayRates per hour anyway.

Idea: Modify the VALIDATION_REGEX of PayRate such that it only accepts values up to 9999.99.

### Prevent Commands meant to Modify Tutee Data from Not Changing the Data

Reason: `clear`,`edit`, `paid`, `delete`,`unpaidAll` are commands that deal with modifying tutee data. If the tutee's `isPaid` status is true,
the system permits the user to execute the `paid` command even though this will not change the `isPaid` status of the tutee. If these commands
do not change the tutee data in any way but still allowed to be executed, when the user executes `undo`, there will be no changes since there are now duplicate states of the `VersionedAddressBook`
is saved. The system should inform the user that this command will not modify any data and prevent the command from executing. 

Idea: Create a `Model#isSameData()` to compare whether the state of the tutee data before and after the command execution will be the same. If
`Model#isSameData()` returns true, a `CommandException` should be thrown and the system should inform the user that this command will not modify any data.

### Enable Group Lessons

Reason: Current `Lesson` implementation prevents any lessons clashes, that is no two `Person` objects can have lessons that fall in same timeslots.
However, this is based on the assumption that lessons are carried out on a one-on-one basis. Given the possibility of group lessons, having such a feature
would allow for more flexibility in the application.

Idea: Create a `GroupTag` field for `Lesson` class which contain an ID for each `Lesson` object if they are group lessons. When checking if two 
lesson clashes, allow for lesson clash if `Lesson` objects have the same IDs which means they are the same group lesson.


