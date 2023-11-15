---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# RTPM Developer Guide

<!-- * Table of Contents -->
<page-nav-print />



## **Acknowledgements**
1. The undo & redo feature and its DG implementation details were reused from 
[Address Book (Level4)](https://github.com/se-edu/addressbook-level4) with minor modifications.
2. Our app uses this snippet to implement Levenshtein distance, which allows us to detect similar but not matching names.
https://rosettacode.org/wiki/Levenshtein_distance#Java
3. The overall format of the DG and project architecture was taken from 
[Address Book (Level3)](https://se-education.org/addressbook-level3/DeveloperGuide.html) with modifications.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

<div style="page-break-after: always;"></div>

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

<div style="page-break-after: always;"></div>

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.


<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `DisplayableListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Displayable` objects residing in the `Model`.

<div style="page-break-after: always;"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

<div style="page-break-after: always;"></div>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("sdelete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteSellerCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteSellerCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteSellerCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a seller).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

<div style="page-break-after: always;"></div>


Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` 
(`XYZ` is a placeholder for the specific command name e.g., `AddSellerCommandParser`) which uses the other classes shown above 
to parse the user command and create a `XYZCommand` object (e.g., `AddSellerCommand`) which the `AddressBookParser` 
returns back as a `Command` object.

* `AddressBookParser` also creates a CommandWarnings object, which is used to handle user errors that do not require the system
to fail execution of the command (e.g. if user is trying to add seller with a non-alphanumeric name.) If the command inherits
from the `Parser` interface, then it will use this object to store warnings to output into the Command and eventually into 
the resulting CommandResult. It is also used by LogicManager to log warnings. Classes which do not implement `Parser` do
not use warnings (because they either execute successfully or fail; there is no potential for user misinput.)

* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...)
inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />

The `Model` component,

* stores the address book data i.e., all `Displayable` objects (which are contained in the appropriate `UniqueDisplayableList<T extends Displayable>` object, in this case only buyers and sellers).
* stores the currently 'selected' `Displayable` objects (e.g., results of a search query) as a separate _filtered_ list which 
is exposed to outsiders as an unmodifiable `ObservableList<Buyer>`/`ObservableList<Seller>` that can be 'observed' e.g. 
the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div style="page-break-after: always;"></div>

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>

<div style="page-break-after: always;"></div>

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

<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Edit feature

#### Implementation

Given below is an example usage scenario and how the edit mechanism behaves at each step.

Step 1. The user types in the `bedit` or `sedit` keyword, followed by the index of the buyer or seller that they want
to edit. Following that, they type one or more of `/PREFIX`, where `PREFIX` is a field that they want to edit. 

<box type="info" seamless>

**Note:** If the index or field is invalid, no command will be executed.

</box>

The following sequence diagram shows how the edit operation works:

<puml src="diagrams/EditBuyerSequenceDiagram.puml" alt="EditBuyerSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `EditBuyerCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

<div style="page-break-after: always;"></div>

#### Design considerations:

**Aspect: How edit executes:**

* **Alternative 1 (current choice):** Creates an EditBuyerDescriptor or an EditSellerDescriptor to abstract away 
* all the Optional functionality
    * Pros: Assigns concern of dealing with optional fields to the Descriptor class, making the code within
  the `edit` commands easier to read and debug.
    * Cons: Requires creation of additional Descriptor class, which makes it harder to trace through the code

* **Alternative 2:** `edit` command parsers pass Optionals directly to the Buyer Command
    * Pros: Avoid creation of a new class that might introduce bugs
    * Cons: Optionals are not intended to be used as inputs to methods, as they introduce additional 
  work to be done in the method to handle the different inputs.

<div style="page-break-after: always;"></div>

### Priority feature

#### Rationale

The priority feature allows the user to assign priority levels to their clients in the address book, using
the `SetBuyerPriority` and `SetSellerPriority` commands. These commands act as a shortcut for conveniently
assigning priority levels to clients, without having to use the edit command (`bedit` or `sedit`).

Also, when the priority feature is used with the sorting command (`bsort` or `ssort`), this allows users to 
view high priority clients at the top of the list first.


#### Implementation

To implement this feature, the `Priority` field is firstly added to `Person`, and its corresponding UI Label is
rendered by modifying the `PersonCard.java` controller and the respective BuyerCard and SellerCard FXML files.
The priority field is optional when instantiating buyers and sellers, and is initially set to a default priority 
level of `nil`.
* Details of how `Priority` is implemented as an optional field is elaborated below under 'Design considerations'.
* The priority FXML Label is conditionally rendered 
in `PersonCard.java` based on the buyer/seller's priority field. For instance, its color is red for `high` priority,
orange for `medium`, green for `low`, and not rendered for `nil`.

To accommodate saving of buyers and sellers with the new priority fields in storage, `JsonAdaptedBuyer` and other
relevant files are modified to include these fields in JSON format, and to be readable and loaded back into `Model` in
subsequent RTPM initialisations.


Given below is an example usage scenario for setting priorities for buyers in the address book's buyer list.

Step 1. The user launches the application and executes the `bprio 2 high` command, which sets the priority level of the
2nd person in the buyer list to `high`. The `bprio` command calls `LogicManager`, which gets `AddressBookParser`
to parse and obtain a `SetBuyerPriorityCommand`, before executing it. The command execution calls `ModelManager` to
update the address book's buyer list with the newly assigned buyer priority, which is reflected on the UI too. 
Finally, `LogicManager` calls `StorageManager` to update the JSON file.

<div style="page-break-after: always;"></div>

The following sequence diagram shows how the undo operation works:
<puml src="diagrams/SetBuyerPrioritySequenceDiagram.puml" alt="SetBuyerPrioritySequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `SetBuyerPriorityCommand` should end at the destroy marker (X), but due to a 
limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Step 2. To unassign the priority level of the 2nd person, the user can execute the `bprio 2 nil` command, which 
runs a similar flow as illustrated in the sequence diagram above.

The same logic can be used for assigning priorities to sellers instead of buyers, by using `sprio` instead 
of `bprio`.



#### Design considerations:

**Aspect: How the optional priority field is implemented**

* **Initial implementation:** Overload the `Buyer`/`Seller` constructors.
    * Pros: Relatively simple to implement and refactor.
    * Cons: Not feasible for implementing various optional fields.

* **Current implementation:** Assign a default value for all non-compulsory fields in `AddBuyer` and `AddSeller`
  (for example, default phone number = 123, default priority = nil, and so on), and only assign these optional 
  fields if the user supplies arguments for them, which would be available in `ArgumentMultimap` after parsing 
  the user input. 
    * Pros: Only a single constructor for `Buyer`/`Seller` is needed for multiple optional fields instead of having
    to overload the constructors.
    * Cons: Address book only adds correctly formatted fields, and may discard the remaining arguments which are
    invalid without the user knowing, so more robust exception handling is required when parsing the user input 
    which may be tedious to implement.


<div style="page-break-after: always;"></div>

### Sort feature

#### Implementation

The sort mechanism is facilitated by `SortedList` in `ModelManager`. A `SortedList` wraps an `ObservableList` and sorts 
its content. In `ModelManager`, we have `SortedList<Buyer>` and `SortedList<Seller>` which wrap around 
`FilteredList<Buyer>` and `FilteredList<Seller>`, allowing the user to filter the buyer and seller lists as well as 
sort them at the same time. Additionally, `ModelManager` implements the following operations:

* `ModelManager#updateFilteredSortedBuyerList(Comparator<Buyer> comparator)` - Sets the buyer `SortedList` with a 
comparator that denotes the order of this list.
* `ModelManager#updateFilteredSortedSellerList(Comparator<Seller> comparator)` - Sets the seller `SortedList` with a
comparator that denotes the order of this list.

These operations are exposed in the `Model` interface as 
`Model#updateFilteredSortedBuyerList(Comparator<Buyer> comparator)` and 
`Model#updateFilteredSortedSellerList(Comparator<Seller> comparator)`, which are executed by `SortBuyerCommand` and 
`SortSellerCommand` respectively to sort the buyer and seller lists.

The `comparator` passed into the methods above defines the way buyers and sellers are sorted. This sorting logic is 
handled by the `BuyerComparator` and `SellerComparator` classes, which come with predefined implementations for the 
`compare` method of `Comparator<T>`. Based on the prefix and sort order (ascending/descending) that is passed after
the `bsort/ssort` keyword, an instance of `BuyerComparator`/`SellerComparator` with the corresponding implementation of
`compare` method will be constructed and passed into the `SortedList` through the sort command. 

<div style="page-break-after: always;"></div>

Given below is an example usage scenario and how the sort mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the 
initial address book state, and the `currentStatePointer` pointing to that single address book state.

Step 2. The user executes `buyer n/Amy`, `buyer n/Bob` and `buyer n/Carla` to add three new buyers.

Step 3. The user executes `bsort n/d` to sort the buyer list by name in descending order. Inputting `bsort n/d` calls 
`LogicManager`, which gets `AddressBookParser` to create an instance of `SortBuyerCommandParser`. If there is a valid
prefix, `SortBuyerCommandParser` parses its `SortOrder`, and creates a `SortBuyerCommand` with a `BuyerComparator` for
the prefix and sort order. If there is no valid prefixes, the `SortBuyerCommand` will have a null `BuyerComparator`. 
The `bsort` command is then executed, updating the `SortedList` in this case by passing the `BuyerComparator`
instance that sorts by name descending. These changes are reflected in the UI, showing a list of buyers that is sorted 
by name in descending order. Finally, `LogicManager` calls `StorageManager` to update the JSON file.

The following sequence diagram shows how the sort operation works:

<puml src="diagrams/SortBuyerSequenceDiagram.puml" alt="SortBuyerSequenceDiagram" />

Step 4. To sort the buyer list by its default order, the user can execute `bsort`, which runs a similar flow as 
illustrated in the sequence diagram above, except passing a null `BuyerComparator` into the `SortedList` for a default 
sorting.

The same logic can be used for sorting sellers instead of buyers, by using `ssort` instead of `bsort`.

<div style="page-break-after: always;"></div>

### Undo & redo feature

#### Implementation

The undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `bdelete 5` command to delete the 5th buyer in the address book. The `bdelete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `bdelete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `buyer n/David …​` to add a new buyer. The `buyer` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the buyer was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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
<div style="page-break-after: always;"></div>
Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `buyer n/David …​` command. This is the behavior that most modern desktop applications follow.

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
    * Pros: Will use less memory (e.g. for `bdelete`, just save the buyer being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

<div style="page-break-after: always;"></div>
    
### Relaxed parameter matching

#### Background
In previous versions of the app and in the original brownfield project AB3, fields such as ```Name``` or ```Email```
had a validation method on instantiation, which would throw an ```IllegalArgumentException``` when 
the provided string did not fit the regex. Although useful, this would often be overzealous, causing potential 
frustration. Furthermore, this exception, as it halts execution, only informs you of the first field that fails 
to pass, so if you had multiple errors you would have to resolve and re-execute each time.
#### Implementation
In 1.3, we implemented a group of static methods for each parameter, per convention
named isAppropriate(*Field*), which has a looser regex. The result of this boolean check, 
if it fails in ```ParserUtil```, then passes a warning string to the```CommandWarnings``` instance, which collects 
and stores them in an internal Set<String>. This CommandWarnings is passed through parsers and their subsequent 
commands (Only parsable commands need this, since non-parsable commands are unambiguous and do not need to warn
the user).

At the end of the execute() method, if the
command encountered any warnings, then they are output through the ```getWarningMessage()``` method
into the returned CommandResult.
This is then passed through LogicManager into MainWindow for display to the user.
LogicManager will also log any such warnings using its logger.

<puml src="diagrams/isAppropriateNameSequenceDiagram.puml" alt="isAppropriateNameSequenceDiagram" />
<box type="info" seamless>

**Note:** The lifeline for `SetBuyerPriorityCommand` should end at the destroy marker (X), but due to a
limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

<div style="page-break-after: always;"></div>

#### Design considerations:

**Aspect: How to implement the warnings**

* **Alternative 1 (current choice):** Use a CommandWarnings class to store strings representing warnings
for inappropriate but valid fields.
    * Pros: Allows us to hold multiple warnings at a time without halting execution.
    * Cons: Using strings means that poor usage of addWarnings by callers may give nonsensical results.

* **Alternative 2 (possible future enhancement):** Have CommandWarnings hold a set of predefined Warning singletons 
instead of Strings.
    * Pros: Constrains the contents of warning messages defensively, ensuring that they are useful.
    * Cons: Not as flexible, not worth the effort of implementing in this early stage of development
    , easy to add as future enhancement.

* **Alternative 3 (proposed):** Use an exception such as InappropriateFieldException, which would be thrown by ParserUtil and 
caught by the add buyer/seller command parsers, which would then pass a String warning to the command for it to output
as the CommandResult.
    * Pros: Doesn't require a new class to be created.
    * Cons: To properly account for every field, you would require a Try/Catch block for every single field parsing.
     Furthermore, using exceptions in the backend require switching to kernel mode, slowing down the application (which may be 
     significant for high-usage cases in the future such as company-wide integration.)
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

This product is for student/junior realtors who have many clients to keep track of. 
They are relatively tech-savvy and prefer the keyboard over the mouse, 
prefer concise commands as opposed to full sentences, 
and would like to customise the software to suit their preferences.


**Value proposition**:

Our free and open-source app helps realtors to keep track of their clients’ preferences and 
details in one place. Unlike generic apps such as Google Sheets, our app is optimised 
for typical realtor workloads and databases.


### User stories

Priorities: High (must have) - * * *, Medium (nice to have) - * *, Low (unlikely to have) - *

Priority level is based on current iteration

| Priority | As a …​                                            | I want to …​                                                                | So that I can…​                                                                        |
|----------|----------------------------------------------------|-----------------------------------------------------------------------------|----------------------------------------------------------------------------------------|
| `* * *`  | realtor                                            | add home-buyer clients into the app                                         | keep track of them and their requirements                                              |
| `* * *`  | realtor                                            | add home owners and their houses into the app                               | keep track of them and relevant details (such as the price they are looking for, etc.) |
| `* * *`  | realtor                                            | view my contacts                                                            | easily find contacts I want to talk to                                                 |
| `* * *`  | user who has been using the app for a long time    | delete/archive old contacts                                                 | declutter my list from outdated information                                            |
| `* * *`  | realtor                                            | save contact data to my computer                                            | refer to it when I reopen my app                                                       |
| `* * *`  | realtor                                            | add houses into the app together with their price, furnishings, etc.        | quickly list the features to my clients                                                |
| `* * *`  | realtor who wants to pack light on the move        | solely use the keyboard and not need to carry a mouse around to use the app | quickly access and update information without the fuss of using a mouse                |
| `* *`    | realtor with many contacts                         | view personal contacts separately from work contacts                        | I can focus on work when I need to                                                     |
| `* *`    | realtor with many client contacts                  | sort my client contacts based on priority (time, importance, etc.)          | I can focus on the most important clients first                                        |
| `* *`    | realtor                                            | add prospective rental clients into the app                                 | keep track of them and their requirements                                              |
| `* *`    | realtor who spends a lot of time at house viewings | I want the app to start up and respond quickly                              | use the app to note down any of my client’s preferences while talking to them          |
| `* *`    | realtor who is flexible with scheduling            | reschedule or postpone my meetings easily in the app                        | so I can avoid the hassle of constantly deleting and making new meetings               |
| `* *`    | realtor                                            | add time to tasks related to each of my clients                             | remember to do them                                                                    |
| `* *`    | busy realtor with other activities in my life      | enter my schedule                                                           | account for overlaps with any meetings                                                 |
| `*`      | forgetful user                                     | be reminded if I have any upcoming or late meetings                         | follow up on my clients                                                                |
| `*`      | realtor                                            | track tasks related to each of my clients                                   | remember what I need to do to follow up on each of them                                |
| `*`      | realtor                                            | be reminded of upcoming tasks or late tasks                                 | do them before meeting clients                                                         |
| `*`      | power user                                         | modify the syntax of (at least some) commands                               | enter them faster                                                                      |
| `*`      | lazy user                                          | be able to automatically match appropriate houses to prospective buyers     | avoid doing it manually                                                                |

<div style="page-break-after: always;"></div>

### Use cases

(For all use cases below, the **System** is our app `RTPM (RealtorTrackerPlusMax)` and the **Actor** is the `user`,
unless specified otherwise)

**Use case: UC1 - Add homeowner and house info**

System: RTPM

Actor: User

**MSS**

1. User enters command to add homeowner and details of the house they are selling.
2. System adds the entry to the list.
3. System saves file.

   Use case ends.

Extensions:
* 1a. User enters invalid parameters.
  * 1a1. System indicates to user that the parameters are invalid.
    Use case restarts from step 1.

* 3a. Failure to update savefile.
  * 3a1. System indicates failure to update.
    Use case restarts from step 1.


<br>

**Use case: UC2 - Add homebuyer and preferences**

System: RTPM

Actor: User


**MSS**

1. User enters command to add homebuyer and preferences.
2. System adds the entry to the list.
3. System saves file.
   Use case ends.

Extensions:
* 1a. User enters invalid parameters.
  * 1a1. System indicates to user that the parameters are invalid.
  Use case restarts from step 1.

* 3a. Failure to update savefile.
  * 3a1. System indicates failure to update.
  Use case restarts from step 1.

<div style="page-break-after: always;"></div>

**Use case: UC3 - View buyers**

System: RTPM

Actor: User

**MSS**

1. User enters the list-b command.
2. System displays list of buyers.

Use case ends.

Extensions:
* 1a. User makes a typo leading to an invalid command.
  * 1a1. System indicates to user that command is invalid, prompting the user for a new input.
  Use case restarts from step 1.


<br> 

**Use case: UC4 - View sellers**

System: RTPM

Actor: User

**MSS**

1. User enters the list-s command.
2. System displays list of sellers.

    Use case ends.

Extensions

* 1a. User makes a typo leading to an invalid command.
  * 1a1. System indicates to user that command is invalid, prompting the user for a new input.<br>
    Use case resumes at step 1.

<div style="page-break-after: always;"></div>

**Use case: UC5 - Delete a buyer/seller**

System: RTPM

Actor: User

**MSS**

1. User enters command to delete a buyer or a seller.
2. System deletes item.
3. System updates savefile.
4. System returns an indicator of execution success.

    Use case ends.

Extensions

* 3a. Failure to update savefile.
  * 3a1. System indicates failure to update.
  * 3a2. System undoes deletion (to prevent desync of storage and application).<br>
    Use case restarts from step 1.

<br>

**Use case: UC6 - Enter an invalid command**

System: RTPM

Actor: User

**MSS:**
1. User enters misspelled command.
2. System displays invalid command error and refers user to help page.


<div style="page-break-after: always;"></div>


### Non-Functional Requirements
*NFRs taken from the given constraints found **[here](https://nus-cs2103-ay2324s1.github.io/website/schedule/week4/project.html)**:*
The marking of NFRs as fulfilled/unfulfilled below is accurate for v1.4.
-[x] The product should be optimized for keyboard users who can type fast and prefer typing over other means of input.
-[x] The data should be stored locally in a human editable text file, instead of in a database.
-[x] The software should primarily follow OOP.
-[x] The software should work on the Windows, Linux, and OS-X platforms (hence shouldn’t depend on OS-specific libraries).
-[x] The software should work on a computer that has version 11 of Java i.e., no other Java version installed.
-[x] The software should work without requiring an installer.
-[x] The use of third-party frameworks/libraries/services is allowed but only if they are free, open-source (this doesn't apply to services), and have permissive license terms.

The GUI should work well (i.e., should not cause any resolution-related inconveniences to the user) for
* [x] standard screen resolutions 1920x1080 and higher, and
* [x] for screen scales 100% and 125%.

In addition, the GUI should be usable (i.e., all functions can be used even if the user experience is not optimal) for
* [x] resolutions 1280x720 and higher, and
* [x] for screen scales 150%.
- [x] The software should be able to be packaged into a single JAR file. 
- [x] The DG and UG should be PDF-friendly (Don't use expandable panels, embedded videos, animated GIFs etc.).

Additional NFRs
- [x] The internal implementation should be readable and adhere to the coding quality guidelines found [here](https://se-education.org/guides/conventions/java/), for maintainability and for peer evaluation.
- [x] The deliverable deadlines should be met with a fully functioning product (hence, most important features should be prioritized and tested to eliminate bugs) to allow for usage as promised.
- [x] The software should be resistant to crashes while running to prevent losing important contact details that realtors need to do business with.
- [x] The software should work fast even on old / low-end laptop so that realtors on the go with their busy days can use our app quickly and efficiently without getting frustrated with lag.
- [x] The software should be free and easy to use as an open source product.


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Appendix: Effort**

We consider that as of the current release (v1.4), substantial effort has been put in by our team to rework the original
AB3 project into a product that is useful for realtors and contains all the features required, including sorting, flexible
command typing, reordering and undoing/redoing commands.

### Difficulties and challenges faced
The first difficulty we encountered was in modifying the UI to fit our requirements. As developers newly introduced 
to the brownfield AB-3 project, we had to spend quite a fair bit of time familiarising ourselves with 
the project architecture, and the initial process of integrating some basic new commands 
and tests (such as the one given in the AB-3 tutorial) was already rather tedious, let alone adding our own features to 
the project and changes to the UI. For example, when trying to separate the initial `Person`s object into `Buyer`s and 
`Seller`s, the app wasn't able to launch due to having a broken test codebase, which required us to refactor at least
20 files across different directories in the project in order for operations to resume. 
After much refactoring and tinkling with the JavaFX GUI, we were finally more familiar
and comfortable making changes to the AB-3 project, and were able to begin system testing of our app in 
preparation for our very first release of RTPM, in the v1.2 release.

### Achievements
One of the things that we believe show the effort that we put into the project was the restructuring in the back-end to
allow Model to hold, and UI to display, multiple lists of different types. In AB3, the application only needed to deal
with one type of object, while in our case, we wanted to add 3 (Houses, Buyers and Sellers, although we only ended up
adding the latter 2). Hence, we decided to abstract out the responsibility for displaying the object to the object itself
(so we would not need a class to hold and display every type we wanted to add. 

Displayable is an interface that allows the UniqueDisplayableList to abstractify the actual displaying and maintaining
of uniqueness to the contained class itself. Thus, we can reduce the number of repetitive classes required to contain 
others.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

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

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. Exiting the program

   4. Test case: `exit`<br>
      Expected: App terminates immediately

### Adding a contact

1. Adding a buyer contact

   1. Prerequisites: Clear the lists with the `clear` command to prevent conflicts from prior testing.
   2. Test case: `buyer n/Bob`<br>
      Expected: A contact with the name "Bob" is added to the buyer list.
   3. Test case: `buyer n/John Doe p/98765432 e/johnd@example.com ah/311, Clementi Ave 2, #02-25 i/Central Area 5 Room Condominium prio/medium t/friends t/owesMoney`<br>
      Expected: A contact with the name "John Doe" and the corresponding particulars is added to the buyer list.
   4. Test case: `buyer n/Bob` (this test case proceeds the one in ii, without clearing)<br>
      Expected: No contact is added. Error details shown in the status message.
   5. Test case: `buyer n/Tom p/phone e/email`
      Expected: Similar to previous.

<div style="page-break-after: always;"></div>

2. Adding a seller contact

    1. Prerequisites: Clear the lists with the `clear` command to prevent conflicts from prior testing.
    2. Test case: `seller n/Bob`<br>
       Expected: A contact with the name "Bob" is added to the seller list.
    3. Test case: `seller n/Ryan p/91234567 e/ryan@gmail.com ah/My Secret Home as/47D Lor Sarhad, Singapore 119164 i/4 Room Flat in Sarhad Ville prio/high`<br>
       Expected: A contact with the name "Ryan" and the corresponding particulars is added to the seller list.
    4. Test case: `seller n/Bob`(this test case proceeds the one in ii, without clearing)<br>
       Expected: No contact is added. Error details shown in the status message.
    5. Test case: `seller n/Tom p/invalidphone e/invalidemail`
       Expected: Similar to previous.


### Editing a contact

1. Editing a buyer contact
   1. Prerequisites: At least one but less than ten thousand contacts present in the buyer list.
   2. Test case: `bedit 1 p/12345 e/example@email.com`<br>
      Expected: First contact in the buyer list has their phone number updated to "12345" and their email updated to "example@email.com".
   3. Test case: `bedit 1 p/invalidphone`<br>
      Expected: No contact is edited. Error details shown in status message.
   5. Test case: `bedit 99999 p/12345`<br>
      Expected: Similar to previous.
   6. Test case: `bedit 0 p/12345`<br>
      Expected: Similar to previous.
   8. Test case: `bedit`, `bedit 1`, `bedit p/12345`<br>
      Expected: Similar to previous.

1. Editing a seller contact
    1. Prerequisites: At least one but less than ten thousand contacts present in the seller list.
    2. Test case: `sedit 1 p/12345 e/example@email.com`<br>
       Expected: First contact in the seller list has their phone number updated to "12345" and their email updated to "example@email.com".
    3. Test case: `sedit 1 p/invalidphone`<br>
       Expected: No contact is edited. Error details shown in status message.
    5. Test case: `sedit 99999 p/12345`<br>
       Expected: Similar to previous.
    6. Test case: `sedit 0 p/12345`<br>
       Expected: Similar to previous.
    8. Test case: `sedit`, `sedit 1`, `sedit p/12345`<br>
       Expected: Similar to previous.

<div style="page-break-after: always;"></div>

### Deleting a contact

1. Deleting a buyer contact while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.


   2. Test case: `bdelete 1`<br>
      Expected: First buyer is deleted from the buyer list. Details of the deleted contact shown in the status message. 

   3. Test case: `bdelete 0`<br>
      Expected: No buyer is deleted. Error details shown in the status message. 

   4. Other incorrect delete commands to try: `bdelete`, `bdelete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

2. Deleting a seller contact while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `sdelete 1`<br>
      Expected: First contact is deleted from the seller list. Details of the deleted contact shown in the status message.

   3. Test case: `sdelete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. 

   4. Other incorrect delete commands to try: `sdelete`, `sdelete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

<div style="page-break-after: always;"></div>

### Setting a contact's priority

1. Setting a buyer's priority

   1. Prerequisites: At least one but less than ten thousand contacts present in the buyer list.
   2. Test case: `bprio 1 high`, `bprio 1 h`<br>
      Expected: First contact in the buyer list has their priority updated to "high".
   3. Test case: `bprio 1 medium`, `bprio 1 m`<br>
      Expected: First contact in the buyer list has their priority updated to "med".
   4. Test case: `bprio 1 low`, `bprio 1 l`<br>
      Expected: First contact in the buyer list has their priority updated to "low".
   5. Test case: `bprio 1 nil`, `bprio 1 n`<br>
      Expected: First contact in the buyer list has their priority updated to "nil".
   6. Test case: `bprio 99999 high`<br>
      Expected: No contact's priority is updated. Error details shown in the status message.
   7. Test case: `bprio 0 high`<br>
      Expected: Similar to previous.
   8. Test case: `bprio 0 invalidprio`<br>
      Expected: Similar to previous.
   9. Test case: `bprio`, `bprio high`, `bprio 1`<br>
      Expected: Similar to previous.

2. Setting a seller's priority

    1. Prerequisites: At least one but less than ten thousand contacts present in the seller list.
    2. Test case: `sprio 1 high`, `sprio 1 h`<br>
       Expected: First contact in the seller list has their priority updated to "high".
    3. Test case: `sprio 1 medium`, `sprio 1 m`<br>
       Expected: First contact in the seller list has their priority updated to "med".
    4. Test case: `sprio 1 low`, `sprio 1 l`<br>
       Expected: First contact in the seller list has their priority updated to "low".
    5. Test case: `sprio 1 nil`, `sprio 1 n`<br>
       Expected: First contact in the seller list has their priority updated to "nil".
    6. Test case: `sprio 99999 high`<br>
       Expected: No contact's priority is updated. Error details shown in the status message.
    7. Test case: `sprio 0 high`<br>
       Expected: Similar to previous.
    8. Test case: `sprio 0 invalidprio`<br>
       Expected: Similar to previous.
    9. Test case: `sprio`, `sprio high`, `sprio 1`<br>
       Expected: Similar to previous.

<div style="page-break-after: always;"></div>

### Filtering the lists

1. Prerequisites: Clear the lists with the `clear` command and add buyers named "John", "John Doe", "JohnDoe", and "Doe" with the `buyer` command.
2. Test case: `filter John`<br>
   Expected: "John" and "John Doe" remain in the buyer list.
3. Test case: `filter Doe`<br>
   Expected: "Doe" and "John Doe" remain in the buyer list.
4. Test case: `filter`<br>
   Expected: The buyer list does not change. Error details shown in the status message.

### Displaying a contact's information

1. Displaying a buyer contact's information
   1. Prerequisites: At least one but less than ten thousand contacts present in the buyer list.
   2. Test case: `blist 1`<br>
      Expected: Information of first contact in buyer list displayed in the status message.
   3. Test case: `blist`, `blist 0`, `blist 99999`<br>
      Expected: No updates occur. Error details shown in the status message.

1. Displaying a seller contact's information
    1. Prerequisites: At least one but less than ten thousand contacts present in the seller list.
    2. Test case: `slist 1`<br>
       Expected: Information of first contact in seller list displayed in the status message.
    3. Test case: `slist`, `slist 0`, `slist 99999`<br>
       Expected: No updates occur. Error details shown in the status message.

<div style="page-break-after: always;"></div>

### Sorting contacts

1. Sorting buyer contacts<br>
   1. Prerequisites: At least one but less than ten thousand contacts present in the buyer list.
   2. Test case: `bsort prio/d`<br>
      Expected: Buyer list is sorted by priority in descending order, with the highest priority at the top of the list.
   3. Test case: `bsort qwerty`, `bsort invalidprefix/invalidorder`<br>
      Expected: The `bsort` command will ignore the invalid parameters and prefixes, and do a default sort.
   4. Test case: `bsort qwerty n/a`, `bsort invalidprefix/invalidorder n/a`<br>
      Expected: The `bsort` command will ignore the invalid parameters and prefixes, and sort by name ascending.
   5. Test case: `bsort n/a qwerty`, `bsort n/a invalidprefix/invalidorder`<br>
      Expected: The buyer list is not updated. Error details shown in the general status message.
   6. Test case: `bsort n/invalidorder`<br>
      Expected: The buyer list is not updated. Error details shown in the general status message.
   7. Test case: `bsort prio/d prio/a`
      Expected: The buyer list is not updated. Error details shown in the status message indicating duplicate prefixes.
   8. Test case: `bsort prio/d n/a`
      Expected: Buyer list is sorted by name in ascending order. `bsort` chooses one of the provided prefixes based on
      this order: **1. Name**, **2. Home address**, **3. House info**, **4. Priority**.

2. Sorting seller contacts<br>
   1. Prerequisites: At least one but less than ten thousand contacts present in the seller list.
   2. Test case: `ssort prio/d`<br>
      Expected: Seller list is sorted by priority in descending order, with the highest priority at the top of the list.
   3. Test case: `ssort qwerty`, `ssort invalidprefix/invalidorder`<br>
      Expected: The `ssort` command will ignore the invalid parameters and prefixes, and do a default sort.
   4. Test case: `ssort qwerty n/a`, `ssort invalidprefix/invalidorder n/a`<br>
      Expected: The `ssort` command will ignore the invalid parameters and prefixes, and sort by name ascending.
   5. Test case: `ssort n/a qwerty`, `ssort n/a invalidprefix/invalidorder`<br>
      Expected: The seller list is not updated. Error details shown in the general status message.
   6. Test case: `ssort n/invalidorder`<br>
      Expected: The seller list is not updated. Error details shown in the general status message.
   7. Test case: `ssort prio/d prio/a`
      Expected: The seller list is not updated. Error details shown in the status message indicating duplicate prefixes.
   8. Test case: `ssort prio/d n/a`
      Expected: Seller list is sorted by name in ascending order. `ssort` chooses one of the provided prefixes based on
      this order: **1. Name**, **2. Home address**, **3. House info**, **4. Priority**.

<div style="page-break-after: always;"></div>

### Saving data

1. Dealing with missing/corrupted data files
  
   1. Prerequisites: Launch the app and run any command so that a `data/rtpm.json` file is created.
   2. Find the JSON file that stores the data (By default, this is in /data/rptm.json. If it has been changed, you can
   find its location by looking at the bottom bar that displays when the app is running.)
   3. Fill it with invalid data.
   4. Expected: The app will recognize that the file is unreadable, and will start with a cleared contact list.
   
2. Extension: missing data
   1. Instead of filling it with invalid data, delete the JSON file.
   2. Expected: The app will recognize that there is no stored file, and will default to providing a 
   typical sample of a contact list.

   3. Test case: Delete the file `data/rtpm.json` or the folder `data`
      Expected: The app will launch with sample data in the buyer and seller lists
   

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Appendix: Planned Enhancements**

Given below are the enhancements that will be implemented in a future version.

#### 1. Better handling of long inputs

* Currently, the UI text is cut off if the entries are too long. While this should not usually happen since the user 
can decide what to enter (nicknames, abbreviations, acronyms, etc.), we plan to accommodate overly long names, 
phone numbers, addresses, emails and house info entries within the UI.
As a current workaround, users can call the `blist`/`slist` commands to display the text representation of the entry in
the results box.

* Extremely long inputs can cause the program to hang or crash. This is a minor issue, since users are unlikely to
enter such long fields into the app. A possible enhancement is to prevent overly long entries by blocking the command
execution.

#### 2. Better command warnings

* Currently, if the user makes a spelling or spacing mistake, the intended prefix of another field is regarded as 
part of the argument for the previous field. We plan to check for misspelled prefixes and prefixes provided as 
arguments of other fields and warn the user.

* Currently, the user is not warned if addresses, names, or house info entries contain only numbers and special 
symbols. We plan to expand warnings to include warnings for addresses, names and house info entries containing 
only non-alphabetical characters.

* As of v1.4, we have received reports that a warning is thrown even when there are no names that users considered similar. 
After testing, we determined that users in fact had two names that were very short, and this caused a discrepancy between commonly expected behavior and actual implementation.
We defined distance between similar names as either one name contains the other entirely,
or the Levenshtein distance between the two names is 2 or less
(It takes 2 or fewer substitutions/additions/removals to turn one of the names into the other.)
An unintended effect was that, for example, if you had short names (e.g "d", "hi", in the original case for us),
the names would match despite normal users probably not defining these two names as similar.
Possible future enhancements would be to make it percentage-based, so that short names are not producing warnings unnecessarily.

<div style="page-break-after: always;"></div>

#### 3. Improvements to set priority command

* Currently, for `bprio` and `sprio`,
   * if the user inputs extra arguments, such as `bprio 1 high low`, the app 
   accepts the input and sets the first buyer's priority level to `high` instead of warning the user about extra 
   arguments which would be ignored. As such, we plan to warn the user if any extra arguments are supplied for the 
   user to double check that their priority input is correct.
   * the current regex for determining if an input is appropriate is as follows:
   <br>`(?i)(h[igh]{0,3}|m[edium]{0,5}|l[ow]{0,2}|n[il]{0,2})$`
     * `(?i)` refers to case-insensitive matching
     * `(h[igh]{0,3}|m[edium]{0,5}|l[ow]{0,2}|n[il]{0,2})` means that the string input can match one of four possible
     options below, with each option separated by a `|`:
       * `h[igh]{0,3}` accepts a string with a first letter 'h', followed by 0 to 3 letters after 'h', which can be
       any of the letters inside the square brackets, so `h`, `hi`, `hhh` and `hggi` are all appropriate inputs.
       * `m[edium]{0,5}` accepts a string with a first letter 'm', followed by 0 to 5 letters after 'm', which can be
       any of the letters inside the square brackets, so `m`, `mii` and `mdmiue` are all appropriate inputs.
       * `l[ow]{0,2}` accepts a string with a first letter 'l', followed by 0 to 2 letters after 'l', which can be
       any of the letters inside the square brackets, so `l`, `lw`, and `lww` are all appropriate inputs.
       * `n[il]{0,2}` accepts a string with a first letter 'n', followed by 0 to 2 letters after 'n', which can be
       any of the letters inside the square brackets, so `n`, `nl`, and `nll` are all appropriate inputs.
     * `$` demarcates the end of the matching 
     
   Initially, the regex above was meant to allow for user typos, such as `hgih` or `meduim`, but in hindsight, 
     this regex is unnecessary as it doesn't value add much to the user experience, 
     and only made it harder to test for invalid priority inputs. 
   <br> As such, we plan to 
     change the validation regex to only accept `h`, `m`, `l`, or `nil` as inputs
     for priority in the future.
  


<div style="page-break-after: always;"></div>

#### 4. Better fields for buyers and sellers

* Currently, we have sellers only having one selling address and one house info. This is not fully representative of all
real-life conditions, since a seller can own and sell multiple houses. Likewise, a buyer could be
theoretically searching for multiple houses (e.g. a rental firm). However we have decided in this early version, 
and in view of our target audience (student/junior realtors) to have a one-to-one correspondence to simplify the 
UI and refine other features. A future enhancement would be to use a House class that can
have a many-to-one relation to buyers and sellers. (In fact, the class is already available and is 
in the repository as an unused .java file; we did not manage to integrate it in time for v1.3 release.)
A workaround for such cases in v1.4 is to add the info about both houses into
the address field and info field (since we do not limit the user from doing this.)

#### 5. Undo/redo commands affect UI-based commands
* Our undo/redo commands do not currently undo/redo UI-based commands, this could be enhanced in later versions. 

#### 6. Improvements to filter command
* Our filter command currently only matches entire sections of a name. A possible enhancement is to allow filter to 
search for partial matches, or have an additional command parameter to enable this; e.g. match `hi` with `Ibrahim`.

* Our filter command also can only search for names as of v1.4. Another enhancement is to allow users to search for the
specific field they want to, by indicating it in the command parameters. One possible format is `filter p/202` if you
wanted to filter by phone number, for example.

#### 7. Better error handling for sort command
* Our sort command currently allows extraneous inputs and invalid prefixes after the `sort` keyword and before the next
valid prefix (`n`, `ah`, `i` or `prio`). Instead of allowing these inputs, the sort command can be changed to not allow
these inputs and show an error message to the user indicating these invalid parameters and prefixes. Moreover, `sort`
currently allows the user to input more than one valid prefix, in which case it'll take the prefix based on the order:
**1. Name**, **2. Home address**, **3. House info**, **4. Priority**. The sort command can be changed to allow only a
single prefix, decreasing ambiguity on which prefix it is sorting by.
