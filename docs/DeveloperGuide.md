---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# HealthSync Developer Guide

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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where
the user issues the command `delete n/Alex` to delete `Alex` from HealthSync.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

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

<puml src="diagrams/ModelClassDiagram.puml" width="650" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the log book data i.e., all `Person` objects (filtered as `foundPersonsList` by `ModelManager`). 
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)


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

###  Undo feature

#### Description

The Undo feature allows users to revert an undo-able command. The `UndoCommand` serves as the entry point for users to
initiate the undo process.

#### Implentation details

The `UndoCommand` is implemented as follows:
- **Command Word**: `undo` or `u`
- **Usage**: `undo [number]`
- **Command Format**
    - `undo`: Undoes the last undo-able command
    - `undo [number]`: Undoes the specified number of undo-able commands
- **Validation**: The `UndoCommandParser` parses user input, ensuring it adheres to the expected format.
  If the input is invalid or incomplete, appropriate error messages are generated.
- **Execution**: Upon successful validation, the `UndoCommandParser` returns a `UndoCommand` object. The object
  then interacts with the model to identify and undo the desired number of previous commands

<puml src=diagrams/UndoSequenceDiagram.puml width="250"/>

#### Rationale:

In the current implementation, the `UndoCommand` relies on undoable commands storing the previous details of the patient as fields.
For example, the undo of an "add" command stores the details of the added patient and undoing it will simply delete the added patient,
representing a reversal of the addition. This approach prioritizes simplicity and efficiency, ensuring a quick and reliable undo operation.
By leveraging the existing structures and operations in the application, this method avoids the need for additional data storage and processing.

The following activity diagram summarizes what happens when a user executes a new command:

<puml src=diagrams/UndoActivityDiagram.puml width="250"/>


#### Alternative Implementation

**Alternative 1:**: Explore more sophisticated undo strategies, such as storing the state of the address book after each command.
* Pros: Accuracy: This approach ensures a more precise representation of the application's history, capturing nuanced changes in the address book. It is particularly beneficial in scenarios where specific details matter.
* Cons:
    1. Increased Complexity: Implementing a system to capture and manage snapshots of the address book after every command introduces additional complexity to the codebase.
    2. Resource Overhead:  Storing snapshots after each command may increase the application's resource usage,


### Implementation of Singular, Optional Fields

#### Proposed Implementation

A way to add a property to `Person` that could be nullable was needed, to reflect optionality within our
field implementation. While optional fields like `MedicalHistory` exist in HealthSync, these fields are
Collections, and can natively handle the empty state. Singular optional properties can be added this way,
but is hard to distinguish between properties that allow for multiple entries and fields that don't.

<puml src="diagrams/AppointmentClassDiagram0.puml"/>

The optional fields could be implemented directly to `Person` as shown in the partial class diagram above.
However, several other packages depend upon `Person` as well, including `UI` and `Storage`.
These packages already make assumptions on the `non-null` property on the variables of `Person`.

<puml src="diagrams/AppointmentSequence0.puml" />

The diagram above illustrates a possible path that may arise if the optional property of the field is
not explicitly defined. There is a need to explicitly denote that our optional field is possibly an
empty value without having its implementers perform the check themselves, so that the compiler is
able to assist in our coding.

<puml src="diagrams/AppointmentClassDiagram1.puml" />

Therefore, the implementation of optional fields now return its value wrapped in the Java
`Optional` wrapper. In this example, when `getAppointment` is called now, the implementer will be
informed of the type mismatch with the `Appointment` type, explicitly informing the implementer that
this value is potentially `null`. By doing it in this way, we also guarantee to the implementer that
the fields that do not generate an `Optional` wrapper cannot be empty.

#### Design Considerations:

**Aspect: Handling of Optional Fields**

* **Alternative 1 (current choice):** Allow `null` values
  * Pros: Simple to implement, and easy to understand what it means.
  * Cons: Checks for null value required for every implementation of the optional field.
    * This is partially circumvented using the `Optional` wrapper, which caused the team to favor
      this implementation.
* **Alternative 2:** Use abstracted "empty-value" objects that extend the given field
  * Pros: No check of the empty state required by implementers, allows for simple code outside its
          implementation.
  * Cons: Non-negligible abstraction required to create an empty object and have it work in all cases
          where the empty case is needed.

### Implementation of Appointment Field

`Appointment` is a special field belonging to the `Person` model class, as it implicitly stores a temporal
relationship within itself.

#### Proposed Implementation
`Appointment` is distinctly different from other fields in `Person` in that it cannot store its values as
a String directly - otherwise, this would complicate the process of defining temporal relationships within itself.

<puml src="diagrams/AppointmentClassDiagram2.puml" />

Above is a partial class diagram of `Appointment`. Note that several static members were excluded as they are not
relevant to its data-structure properties in `HealthSync`.

The default Java packages provides its implementation of temporal objects in the `java.time` package. In particular,
`LocalDateTime` and its variants were the most relevant to us, as it allows the user to record time without needing
to account for timezone differences. This is powerful, as our target audience is not expected to change locations in
a significant way that causes them to change time regions entirely.

With the use of the `java.time` package, the project could not use only `regex` for `Appointment`.
This is due to the level of checks required to parse a temporal object, with the amount of dependencies that exist
between the day, month and year fields. The provided `DateTimeFormatter` and `DateTimeFormatterBuilder` classes helps
create the parser objects used for `Appointment`. However, the classes do not account for the combined time format that
HealthSync requests of its users.

Therefore, `Appointment` uses a combination of `regex` and `DateTimeFormatter` to resolve its user input.

<puml src="diagrams/AppointmentActivity0.puml" />

A partial activity diagram illustrating the relevant segment of the parse process.

As seen above, `ParserUtil` verifies if the Appointment user input is trivially valid using
`regex`, before passing the input into the `of` constructor. `DateTimeFormatter` cannot fully verify input strings
against its format without creating a `LocalDate`/`LocalTime` object as a side effect,
so `of` handles a portion of the parse.

#### Design Considerations:

**Aspect: Value to store `Appointment` as**

* **Alternative 1:** Use of raw `String` format for Appointment
    * Pros: Far easier to parse and store as an object.
    * Cons: Hard to extend upon in future use-cases, such as reminders, etc.


* **Alternative 2 (current choice):** Use of Java Temporal-related objects for Appointment
    * Pros: More direct paths of feature extension, such as searching by time period.
    * Cons: Translation to and from Java Temporal objects can be non-trivial.

**Aspect: Constructor for `Appointment` to manage valid user input**

* **Alternative 1:** Directly use constructors for `Appointment`, and using `isValidAppointment` to verify input
    * Pros: Consistency with construction of other fields
    * Cons:
      * Construction called before user input is verified so exception thrown may not be intuitively understood
      * Concerns over failed appointment creation not getting collected by Java Garbage Collection due to accessing
       `now()` resource
      * User input validation for temporal objects create the relevant object as a side effect, wasting resources to
        construct the temporal object twice.

* **Alternative 2 (current choice):** Use of `of` factory method for `Appointment`, and verifying input inside `of`
    * Pros:
      * Explicit demarcation of `Appointment` as a class that can throw an `Exception` during construction
      * Factory method can double as an explicit user verification method
      * Construction of `Appointment` only performed once user input is verified
    * Cons:
      * "Uniqueness" of private constructor for this field only may cause confusion when extending the app
      * Appointment handles part of `parse` for `ParserUtil`

**Aspect: Parsing of `Appointment` Field as multiple fields or single field**

* **Alternative 1 (current choice):** Use of the single `ap/` flag.
  * Pros: Easy to input on the user-end.
  * Cons: Hard to separate time fields, could be troublesome to implement a parse format string.

* **Alternative 2:** Use of 2 flags to denote start and end time for appointment.
  * Pros: Immediate clarity on what fields to implement, and how to parse input string.
  * Cons: Strong dependence between 2 flags requires more fail-state management.

### Delete Feature

#### Description

The `DeleteCommand` allows users to delete a patient's profile or a specified field from the patient's profile.

#### Implementation Details

The `DeleteCommand` is implemented as follows:
- **Command Word**: The command word for this feature is `delete`
- **Usage**: Users invoke the `DeleteCommand` by specifying the command word, followed by the name or IC of the person they wish to delete and any fields they wish to delete.
- **Command Format**: 
  - `delete n/Name [Fields] ...`
  - `delete id/IC_Number [Fields] ...`
  - `delete n/Name id/IC_Number [Fields] ...`
- **DeletePersonDescriptor**: The `DeleteCommand` relies on an `DeletePersonDescriptor` to capture which fields the user wishes to delete from the patient's profile. The descriptor will be passed to the `DeleteCommand` to execute the deletion. Currently, only `Appointment` and `MedicalHistory` can be deleted as they are optional.
- **Validation**: The `DeleteCommand` performs validation to ensure that the IC or Name provided is valid.
- **Execution**: When executed, the `DeleteCommand` identifies the patient to be deleted based on the provided name or IC. When the patient is found, if no there are no specified fields to delete, the entire patient profile will be deleted from the database. Otherwise, the specified fields will be deleted from the patient's profile.

The following activity diagram summarizes what happens when a user executes a new command:

<puml src=diagrams/DeleteActivityDiagram.puml width="250"/>

#### Rationale

- **Flexibility**: The `DeleteCommand` provides flexibility to users, allowing them to choose what to be deleted from the patient's profile, instead of an "all-or-nothing" approach.
- **Data Accuracy**: The `DeleteCommand` allows users to delete outdated or incorrect information from the patient's profile, ensuring that the database is up-to-date and accurate.
- **Privacy and Compliance**: The `DeleteCommand` supports "right to erasure" under the PDPA, allowing users to delete patient's information from the database when requested.

#### Alternative Implementation

- **Alternative 1**: The `DeleteCommand` could be implemented as a `DeleteFieldCommand` and a `DeletePersonCommand`. The `DeleteFieldCommand` will delete the specified fields from the patient's profile, while the `DeletePersonCommand` will delete the entire patient profile from the database. This approach will require the user to invoke two commands to delete a patient's profile and the specified fields from the patient's profile. This approach is not chosen as it is less intuitive and requires more effort from the user.

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### Improved GUI

_The Graphical User Interface (GUI) is designed using JavaFX and employs a combination of HBox, VBox, and StackPane layouts. It also utilizes specific color choices to create an appealing and user-friendly interface._

_Developers can use this information as a reference when working with the HealthSync GUI code._

#### Layout Structure:

The primary layout structure for the HealthSync GUI is based on HBox and VBox containers, which allow for a flexible arrangement of UI elements.

1. HBox
   Location: The HBox is the top-level container in the GUI and spans the entire application window.

2. VBox
   Location: There are two VBox containers within the HBox, which are responsible for organizing various elements of the GUI.

StackPane
The StackPane is used to organize specific UI elements within the VBox containers. It allows for the layering of elements and effective management of screen real estate.

1. StackPane (PersonListPanel)

   Location: Inside the first VBox (`fx:id="personListPanelPlaceholder"`).
2. StackPane (LoggedPersonlListPanel)

    Location: Also inside the first VBox (`fx:id="loggerPanelPlaceholder"`).
3. StackPane (ResultDisplay, CommandBox, and StatusBarFooter)

    Location: These StackPanes are located inside the second VBox

#### Color Choice:

The HealthSync GUI utilizes specific color choices to create a visually pleasing and organized interface, while still maintaining the original Dark Theme.

1. Primary Colour: `#43314E`
2. Secondary Colour: `#231335`


### Edit Feature

#### Description

The `EditCommand` allows users to modify the details of an existing person within the address book.

#### Implementation Details

The `EditCommand` is implemented as follows:
- **Command Word**: The command word for this feature is `edit`.
- **Usage**: Users invoke the `EditCommand` by specifying the command word, followed by the name or IC of the person they wish to edit and the fields they wish to modify.
- **Command Format**: 
  - `edit n/Name [Fields] ...`
  - `edit id/IC_Number [Fields] ...`
  - `edit n/Name id/IC_Number [Fields] ...`
- **EditPersonDescriptor**: The `EditCommand` relies on an `EditPersonDescriptor` to capture the details to edit the person with. This descriptor allows for updating various attributes of the person, such as phone, email, address, appointment, and medical histories.
- **Validation**: The `EditCommand` performs validation to ensure at least one field to edit is provided. It also checks for consistency when both a name and IC are provided.
- **Execution**: When executed, the `EditCommand` identifies the person to edit based on the provided name and/or IC. If the person is found, it creates an `editedPerson` with the desired changes. The person is then updated with the new details.

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/EditActivityDiagram.puml" width="250" />

#### Rationale

- **Flexibility**: The `EditCommand` provides flexibility to users by allowing them to choose whether to edit a person by name or IC, as per their convenience.
- **Maintaining Data Integrity**: The feature is designed to maintain the integrity of the address book by updating existing entries rather than creating new ones.

#### Alternatives Considered

- **Alternative 1**: Using Numbering Index to specify the person to edit. In this approach, users would provide the index of the person based on the list instead of specifying a name or IC. For example, they could use a command like `edit 1 p/93029393` to edit the first person of the list with the phone number.
    - **Pros**:
        - **Simplicity**: Using an index is straightforward and doesn't require specifying a name or IC.
        - **Reduced Ambiguity**: Using an index avoids potential ambiguity when multiple individuals have the same name.

    - **Cons**:
         - **Lack of Context**: Users might find it challenging to remember the index of a particular person, especially in a large address book.
         - **Potential Errors**: If the list of persons changes (e.g., due to deletions or additions), the numbering index could become outdated, leading to errors.
         - **Limited Identifiability**: Index numbers do not provide any context about the person, which may be confusing when there are multiple people with the same name or similar information.

### Find Feature and its related Predicate classes

The `FindCommand` allows users to find existing person(s) within the patient list,
using their Name, NRIC and/or Appointment, and view their field data. This is done with aid by the concreted `Predicate`
classes, that directly implement the `Predicate` functional interface given by the Java package.

#### Implementation Details

The `FindCommand` is implemented as follows:
- **Command Word**: The command word for this feature is `find`.
- **Usage**: Users invoke the `FindCommand` by specifying the command word,
  followed by the Name, NRIC and/or Appointment period of the person(s) they wish to find.
- **Command Format**:
    - `find n/Name [Fields] ...`
    - `find id/IC_Number [Fields] ...`
    - `find n/Name id/IC_Number [Fields] ...`
- **`execute` method**: The `FindCommand` executes the search by using the specified predicates generated from
  the fields (`NameContainsKeywordsPredicate`/`IdContainsKeywordsPredicate`/`AppointmentOverlapsPredicate`).
  These predicates are composited into `CompositePredicate` to filter and list all persons matching the search criteria.
    - `CompositePredicate` is a collection of `Predicate<Person>` objects that itself implements the `Predicate<Person>`
  interface. The collection is stored in a Set, and `test()` calls will perform a logical AND on all values in its
  collection. By default, `CompositePredicate` will have an instance of `IdentityPredicate` which always returns `true`.
        - <puml src="diagrams/PredicateClassDiagram.puml"/>
    - The following object diagram illustrates how `FindCommand` instances handles its `Predicate` values internally.

<puml src="diagrams/FindWithPredicateObjectDiagram.puml" width="950" />

As observed in the diagram above, each field provided will generate the appropriate `Predicate` value and add it to the
`CompositePredicate` collection, and each field not provided adds an instance of an `IdentityPredicate` instead.
As the Set implementation enforces no duplicate Predicates, this means that collections with fewer fields provided
will also store less values in the collection overall.

- **Validation**: The `FindCommand` performs validation to ensure at least one field is provided. For Appointment,
  it asks for a valid Appointment format to be given.

The following sequence diagram shows how the find operation works in the successful case:

<puml src="diagrams/FindSequenceDiagram.puml"/>

<box type="info" seamless>

**Note:** The lifeline for `FindCommandParser` and `FindCommand` should end at the destroy marker (X) but due to a
limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/FindActivityDiagram.puml"/>

#### Rationale

- **Flexibility**: The `FindCommand` provides flexibility to users by allowing them to choose whether to find a person
  by name, NRIC or Appointment timeslot, whichever is faster or available.
- **User Experience**: The keyword matching is case-insensitive, making the search faster and more user-friendly.
- **Data Integrity**: The feature is designed to maintain the integrity of the patient list
  by not changing any of the patient data.

#### Alternatives Considered

What Fields to use as part of query
- **Alternative 1**: Using existing implementation of only name to find patients
    - **Pros**:
        - The command format is fixed and will always only be `find n/NAME` which is easier to remember.
        - Searching primarily by name is a common way to look up a patient in a healthcare system
          and users may be more familiar with this method.
    - **Cons**:
        - If multiple patients share the same name for a future extension, they will be indistinguishable name-wise and
          other identifiers, like ID, may be needed.
        - Lack of flexibility in search term may become a pain point for a service that provides for a lot of people.

- **Alternative 2**: Search through all fields
    - **Pros**:
        - Extreme flexibility in searching for patients
        - Ease of use by users, as there will not be a specific format to adhere to if all fields are allowed
    - **Cons**:
        - Have to account for multiple field queries
        - Searching through some fields are irrelevant - it is rare to search for a patient via address for example - so
          it might not be worthwhile to implement

- **Alternative 3 (current choice)**: Searching through ID, Name and Appointment
  - **Pros**:
    - More precise searching for a particular person
    - Added flexibility to searching without using some less relevant fields

  - **Cons**:
    - Have to account for multiple field queries

Handling multiple field queries
- **Alternative 1 (current choice)**: Logical AND
    - **Pros**:
        - Allows user to narrow down a search in a large `find` result
        - In line with most filter functions in searches
        - Scales up better when queries are done on less unique fields
    - **Cons**:
        - May be confusing when comparing with internal implementations of ID and Name `find`
        - Low value when 2 of the queries are unique (this is mitigated by the
          [extension proposed in a later update](#appendix-planned-enhancements))

- **Alternative 2**: Logical OR
  - **Pros**:
    - Allows a user to cast out a wider net
    - Potentially lets a user find multiple fields at once
    - Consistent with individual keyword matches for internal implementation of ID and Name `find`.
  - **Cons**:
    - For larger databases, Logical OR adds little value to a filter function


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

* has a need to manage a large database of patient details, which includes health records, contact details, and appointment schedules
* cannot spend more than 2-3 minutes registering/accessing a database system
* work is fast-paced and requires quick access to patient details
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

HealthSync caters to clinic assistants in small private clinic, enabling them to register and access patient information within 2-3 minutes. It offers a user-friendly platform, optimizing contact management, patient tracking, and health record access, ensuring efficient patient management, appointment scheduling, and comprehensive health record retrieval, enhancing care delivery and saving time.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a...                                        | I can...                                           | So that I can...                                                         |
|----------|------------------------------------------------|----------------------------------------------------|--------------------------------------------------------------------------|
| `* * *`  | beginner of the app for an important operation | auto-save all my data                              | not lose my data when something goes wrong                               |
| `* * *`  | frontdesk worker                               | retrieve patient information                       | make use of the patient data when I need them                            |
| `* * *`  | frontdesk worker                               | create patient entries                             | add entries when new patients visit                                      |
| `* * *`  | frontdesk worker                               | find a patient by some field they possess          | locate details of persons without having to go through the entire list   |
| `* * *`  | frontdesk worker                               | delete a patient entry                             | clean and update the database when patient no longer exist               |
| `* * *`  | frontdesk worker                               | edit patient entries                               | update their details, especially for upcoming appointment dates          |
| `* * *`  | frontdesk worker                               | store patient appointment information              | track when they next come to the clinic                                  |
| `* * `   | a new user of the app                          | view preloaded sample data                         | know how the basic UI look like when it is populated                     |
| `* * `   | a new user of the app                          | purge all sample data from the app                 | add my own data easily when I want to use the app                        |
| `* * `   | a new user of the app                          | have easy access to a help sheet                   | check what commands I can and cannot use in a situation                  |
| `* * `   | frontdesk worker                               | add medical histories to patients                  | view and filter patients accordingly                                     |
| `* * `   | frontdesk worker                               | document patient encounters                        | maintain up-to-date records of patient information                       |
| `* * `   | frontdesk worker                               | use app with shortcuts                             | get my task done very quickly                                            |
| `* * `   | frontdesk worker                               | see conflicts in appointment schedules             | seamlessly schedule appointments for patients                            |
| `* * `   | frontdesk worker                               | reminder when patient's appointment is coming soon | call-up and remind patients, and prepare for the day accordingly         |
| `* `     | a new user of the app                          | have physical UI Buttons                           | use them to execute tasks before I'm familiar with shortcuts             |
| `* `     | a new user of the app                          | access an in-app help sheet                        | easily see what to do when I need help without opening another program   |
| `* `     | a new user of the app                          | get in-app hints as I use the app                  | quickly acclimatise to using the app as I use it                         |
| `* `     | frontdesk worker                               | have a very optimised app                          | do my task and have data reading almost instantly                        |
| `* `     | frontdesk worker                               | leverage on statistics in my patient list          | analyse data (ie. how many appointments booked/ month for doctors)       |
| `* `     | frontdesk worker                               | save back-up or archive patient details somewhere  | maintain a fast application while still having data securely stored      |
| `* `     | frontdesk worker                               | have calendar-like UI to create appointments       | show calendar to patients and allow smoother appointment booking process |

### Use cases

(For all use cases below, the **System** is the `HealthSync` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC1 - Add a patient**

**MSS**

1.  User requests to add a patient into the list.
2.  HealthSync adds the target patient into the list
    and displays the patient inside the updated list.
3.  HealthSync <u>performs an auto-save (UC0A)</u>.

    Use case ends.

**Extensions**

  * 1a. HealthSync detects an error in the input.
  
    * 1a1. HealthSync shows an error message.
    * 1a2. User corrects the input.
    
      Steps 1a1-1a2 repeat until the user inputs all the fields correctly.
      
      Use case continues from step 2.

**Use case: UC2 - Delete a patient**

**MSS**

1.  User requests to delete a specific patient based on an identifier from the list.
2.  HealthSync searches for the patient in the list.
3.  HealthSync deletes the specified patient from the list.
4.  HealthSync <u>performs an auto-save (UC0A)</u>.

    Use case ends.

**Extensions**

* 1a. HealthSync detects an error in the identifier input.

    * 1a1. HealthSync shows an error message.
    * 1a2. User corrects the input.

    Steps 1a1-1a2 repeat until the user inputs all the fields correctly.
  
    Use case continues from step 2.

* 1b. User also inputs non-identifier fields as well.

    * 1b1. HealthSync <u>deletes the patient fields from the patient instead (UC3).</u>

      Use case ends.

* 2a. The patient does not exist in the list.

    * 2a1. HealthSync shows an error message.

      Use case ends.

**Use case: UC3 - Delete fields from a patient**

**MSS**

1.  User requests to delete fields from a specific patient based
    on an identifier from the list.
2.  HealthSync searches for the patient in the list.
3.  HealthSync deletes the fields of a specified patient from the list.
4.  HealthSync <u>performs an auto-save (UC0A)</u>.

    Use case ends.

**Extensions**

* 1a. HealthSync detects an error in the identifier input.

    * 1a1. HealthSync shows an error message.
    * 1a2. User corrects the input.

      Steps 1a1-1a2 repeat until the user inputs all the fields correctly.
    
      Use case continues from step 2.

* 1b. The user does not specify any fields they want to delete.

    * 1b1. HealthSync <u>deletes the patient from the list instead (UC2).</u>

      Use case ends.

* 2a. The user does not exist in the list.

    * 2a1. HealthSync shows an error message.

      Use case ends.

**Use case: UC4 - Edit a patient**

**MSS**

1.  User requests to change a specific user's fields based on an identifier
    with a new value in the list.
2.  HealthSync searches for the patient in the list.
3.  HealthSync edits the specified patient's fields in the list.
4.  HealthSync <u>performs an auto-save (UC0A)</u>.

    Use case ends.

**Extensions**

* 1a. HealthSync detects an error in the input.

    * 1a1. HealthSync shows an error message.
    * 1a2. User corrects the input.

      Steps 1a1-1a2 repeat until the user inputs all the fields correctly.

      Use case continues from step 2.

* 2a. The user does not exist in the list.

    * 2a1. HealthSync shows an error message.

      Use case ends.

**Use case: UC5 - Find a patient**

**MSS**

1.  User requests for matches to the given query for a particular field.
2.  HealthSync displays the list of patients matching the query.

    Use case ends.

**Extensions**

* 1a. HealthSync detects an error in the input.

    * 1a1. HealthSync shows an error message.
    * 1a2. User corrects the input.

      Steps 1a1-1a2 repeat until the user inputs all the fields correctly.

      Use case continues from step 2.

* 1b. User specifies multiple fields to query.

    * 1b1. HealthSync combines the fields into a query that matches all the conditions given.
  
      Use case continues from step 2.

**Use case: UC0A - Auto-save**

**Actors:** Operating System (OS)

**MSS**

1.  HealthSync requests for permissions from the OS to access its save location.
2.  OS grants HealthSync permission to access its save location.
3.  HealthSync saves the session data into the save location.

    Use case ends.

**Extensions**

* 1a. OS does not grant HealthSync save location permissions.

    * 1a1. HealthSync shows an error message.

    Use case ends.

*{More to be added}*

### Non-Functional Requirements

1. The application should be compatible with the designated operating systems and hardware configurations, as specified 
   in the system requirements ie Mac, Windows and Linux systems running Java 11.
2. The application should respond promptly to user inputs, with minimal latency and loading times for data retrieval
   and processing.
3. The user interface should be user-friendly and intuitive for fast typists, designed to optimize the workflow of
   front-desk staff who need to complete tasks within 2-3 minutes.
4. The application should be stable for a clientele of roughly 300 to 500 patients (estimated from ~20 patients per day
   for a GP, for a month with 50% returning patients).
5. The application should be designed to handle an increasing volume of patient records efficiently
   without noticeable performance degradation.
6. The application should comply with healthcare regulations.
7. The application is not required to handle communications on a local database/internet.
8. The application is designed to be used by exactly 1 front-desk staff.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Architecture Diagram**: A visual representation that illustrates the high-level design of the application
* **Main**: The function responsible for launching the application
* **UI**: Stands for User Interface
* **API**: Stands for Application Programming Interface, it defines the methods and protocols of the application
* **ObservableList**: A list implementation that allows other objects to observe and be notified when there is changes
* **JSON**: Stands for JavaScript Object Notation, it is a lightweight data interchange format
* **Classes**: Defines an object in the application
* **CLI**: Stands for Command-Line Interface, it is a text-based interface for interaction with computer system or software applications through use of commands
* **IC**: Stands for Identity Card
* **Database**: A structured collection of data organized and stored in computer system
* **Latency**: The time delay between user's action or request and the system's response
* **PDPA**: Stands for Personal Data Protection Act, it is the legislation related to the protection of personal data and privacy


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on.
Testers are expected to do more *exploratory* testing.

</box>

### Correctness of Fields

**Note:** The instructions below assume that the fields provided to the commands are all valid. 
Here is how the correctness of the fields is defined.

Fields that are compulsory are **bolded**. Fields that are optional are *italicized*.

1. **Name**
   - The patient's Name must be a standard unique alphanumeric identifier.
   - Each patient should have a unique Name assigned to them.
   - Invalid names include: X Æ A-12, エレン・イェーガー, Nagaratnam s/o Suppiah.

2. **ID**
   - The patient's ID is a unique alphanumeric identifier for the patient.
   - Each patient should have a unique ID assigned to them.
   - No verification system is in place for ID, allowing custom identifiers.

3. **Phone number**
   - The patient's Phone Number must be numeric and at least 3 digits long
   - It does not need to be unique

4. **Email address**
   - The patient's Email Address should be of the form `local-part@domainname`.
      - `local-part` is alphanumeric, and may also contain these symbols: `+` `_` `.` `-`
      - `domainname`  should be the site that the email leads to, such as `gmail.com`, `mail`.
      - These must be separated by an `@` symbol.
   - It does not need to be unique

5. **Address**
   - The patient's Address does not have any format to adhere to.
   - It does not need to be unique

6. *Medical History*
   - The patient's Medical History does not have any format to adhere to.
   - It does not need to be unique
   - A patient can have more than one medical history

7. *Appointment*
   - Appointments should be given in this sequence: `Date, Start Time, End Time`. For example,
   `1-Aug-2023, 11:00 13:00` is a valid appointment denoting an appointment on 1st August 2023, from 11am
   to 1pm.
   * The month and day of the appointment should always be included.
   * Day can be given as a 1 to 2-digit number. It will only be accepted if the day can exist in that month or year.
   * Month can be given as a 1 to 2-digit number or a 3-letter word. Example: `Jun` and `6` both represent June.
   * The year is optional. If not included, HealthSync assumes it to be this year.
   * The date should be hyphenated.
   * The time should be given in 24-hour clock format, with 00:00 as 12am.
   * Colons are optional when time is given with hours and minutes. If no colons are given, you need to pad the hour with
     a zero when necessary. Example: `1200` for 12 noon, `0900` for 9am.
   * You may exclude minutes if you wish. Example: `15` will be interpreted as 3pm.
   * Date and the 2 Times needs to be separated by a comma or a space.
   * Appointment with the start and end time being the same is valid (ie. 0-minute appointment). HealthSync will register the start time as it is, and register end time as unconfirmed. This is useful for when you are unsure of the end time of an appointment.

**Note:**: If any field is invalid, a `MESSAGE_CONSTRAINT` conforming to the format of the fields will be generated as output and the command will not be executed. Invalid fields will not be considered in the test cases below.

### Shortcuts

**Note:** HealthSync provides shortcuts for commands to allow faster typing for users. In the test cases provided below, you have the flexibility to use either the original command or its shortcut interchangeably.

Here is the mapping between original command keywords and shortcuts:

| Original Command | Shortcut |
|-------------------|---------|
| help              | h       |
| list              | ls      |
| add               | a       |
| edit              | e       |
| delete            | d       |
| clear             | c       |
| find              | f       |
| log               | l       |
| alog              | al      |
| clog              | cl      |
| undo              | u       |
| exit              | ex      |


### Launch and Shutdown

1. Initial launch
   1. Download the jar file and copy into an empty folder
   2. Double-click the jar file <br>
   Expected: Shows the GUI with a set of sample patients. The window size may not be optimum.

2. Saving window preferences
   1. Resize the window to an optimum size. Move the window to a different location. Close the window.
   2. Re-launch the app by double-clicking the jar file<br>
   Expected: The most recent window size and location is retained


### Viewing Help: help or h

1. Successful Help command Execution
   1. Prerequisites: No specific preconditions.
   2. Test case: `help` <br>
      Expected: The help page is opened.

2. Help URL Generation
   1. Prerequisites: The help command is successfully executed with the help page opened
   2. Test case: Click the `Copy URL` button or copy the shown URL <br>
      Expected: The URL should lead to the User Guide after the users have pasted it in their preferred browser

### Listing all Patients: list or ls

1. Successful Listing
   1. Prerequisites: No specific preconditions
   2. Test case: `list` <br>
      Expected: A list of all existing patients in HealthSync is displayed.
   3. Test case: Perform a valid `find` command before executing the `list` command <br>
      Expected: A list of all existing patients in healthSync is displayed again.

### Adding a Patient: add or a

1. Successful Patient Addition
   1. Prerequisites: No existing patient with the provided Name or ID in the test case exists
   2. Test case: `add n/John Doe id/S1234567A p/91234567 e/johndoe@gmail.com a/123 Main st` <br>
      Expected: Patient is added with output message: `New patient added: ...`
   3. Test case: `add n/Alex Bee id/T9876543A p/97438807 e/alexb@gmail.com a/123 Minor st ap/13-Nov 09:00 11:00 m/Diabetes` <br>
      Expected: Patient is added with output message: `New patient added: ...`
   4. Test case: `add n/Alice May id/A135791B p/98765432 e/alicem@gmail.com a/456 Main st ap/09-09-2023 17:00 19:00` <br>
      Expected: Patient is added with output message: `New patient added: ...`

2. Duplicate Patient Addition
   1. Prerequisites: An existing patient with the Name `John Doe` and ID `S7654321B` exists
   2. Test case: `add n/John Doe id/S7654321B p/98876543 e/johndoe2@email.com a/123 Oak St` <br>
      Expected: Error message: `Patient already exists in HealthSync.`
   3. Test case: `add n/John Doe id/T9876543A p/94224432 e/johndoe3@gmail.com a/456 Oak St` <br>
      Expected: Error message: `Patient already exists in HealthSync.`
   4. Test case: `add n/Bob Lee id/S7654321B p/92224432 e/bobl@gmail.com a/789 Oak St` <br>
      Expected: Error message: `Patient already exists in HealthSync.`

3. Undo Adding of Patient
    1. Prerequisites: No existing patient with the provided Name or ID in the test case exists
    2. Test case: Perform a valid `add` command before doing an `undo` command. <br>
    Expected: The newly added patient is deleted.

4. Invalid Command Format / Usage
   1. Prerequisites: No specific preconditions
   2. Test case: Execute an incomplete or improperly formatted command, i.e. missing **compulsory** fields 
      Expected: Error message: `Invalid command format! ...`

### Editing a patient: edit or e

**Note:** For editing using both n/NAME and id/ID, both the Name and ID must match to the same patient, otherwise an error message will be outputted.

1. Successful Editing of one field
   1. Prerequisites: An existing patient with the Name `John Doe` and ID `S7654321B` exists
   2. Test case: `edit n/John Doe p/99998888` <br>
      Expected: The phone number of the patient `John Doe` is updated to `99998888`
   3. Test case: `edit id/S765321B e/johndoee@gmail.com` <br>
      Expected: The email address of the patient `John Doe` is updated to `johndoee@gmail.com`
   4. Test case: `edit n/John Doe id/S765321B a/123 Oak Street` <br>
      Expected: The address of the patient `John Doe` is updated to `123 Oak Street`
   5. Other valid commands to try: editing `m/` and `ap/`
      Expected: The corresponding field is updated

2. Successful Editing of multiple fields
   1. Prerequisites: An existing patient with the Name `John Doe` and ID `S7654321B` exists
   2. Test case: `edit n/John Doe p/98765432 ap/17-Nov-2023 09:00 11:00`
      Expected: The phone number of John Doe is updated to `98765432` and appointment is updated to `17-Nov-2023, 09:00 to 11:00`
   3. Test case: `edit id/S7654321B m/cancer m/diabetes`
      Expected: The medical histories of John Doe is updated to `cancer` and `diabetes`
   4. Other valid commands to try:  Edit command with any combination of editable fields, as long as at most one prefix of the fields to be edited is 
   provided. Note that for the medical history (m/) prefix, multiple prefixes can be provided.
      Expected: The corresponding fields are updated

3. Undo Editing of Patient
    1. Prerequisites: An existing patient with the provided Name or ID in the test case exists
    2. Test case: Perform a valid `edit` command before doing an `undo` command.
       Expected: The edit on the patient is reverted.

4. Invalid Command Format / Usage
   1. Prerequisites: An existing patient with the Name `John Doe` and ID `S7654321B` exists
   2. Test case: No fields provided for editing i.e. `edit n/John Doe id/S7654321B`
      Expected: Error message: `At least one field to edit must be provided.`
   3. Test case: Editing a non-existant patient `edit n/NONEXISTANT p/98765432`
      Expected: Error message: `INVALID name and/or ID!`

### Deleting a Patient: delete or d

**Note:** For deletion using both n/NAME and id/ID, both the Name and ID must match to the same patient, otherwise an error message will be outputted.

1. Successful Patient Deletion
   1. Prerequisites: An existing patient with the provided Name or ID in the test case exists.
   2. Test case: `delete n/John Doe` <br>
      Expected: Patient is deleted with output message: `Deleted Patient: ...`
   3. Test case: `delete id/S9876678A` <br>
      Expected: Patient is deleted with output message: `Deleted Patient: ...`
   4. Test case: `delete n/Alice Tan id/S7654321B` <br>
      Expected: Patient is deleted with output message: `Deleted Patient: ...`


2. Successful Field Deletion
   1. An existing patient with the provided Name or ID in the test case exists, along with the specified field (Appointment or Medical History).
   2. Test case: `delete n/John Doe ap/` <br>
      Expected: Patient's appointment field is deleted with output message: `Deleted Patient's field: ...`
   3. Test case: `delete id/S1234567A m/`
      Expected: Patient's medical history field (all of it) is deleted with output message: `Deleted Patient's field: ...`
   4. Test case: `delete n/Alice Tan id/S7654321B ap/`
      Expected: Patient's appointment field is deleted with output message: `Deleted Patient's field: ...`

3. Invalid Patient Deletion
   1. Prerequisites: No existing patient with the provided Name or ID in the test case exists.
   2. Test case: `delete n/Nonexistent Patient` <br>
   Expected: Error message: `The given combination of Name and ID does not match any patient in the Patients list.`
   3. Test case: `delete id/IDNotFound` <br>
   Expected: Error message: `The given combination of Name and ID does not match any patient in the Patients list.`

4. Invalid Field Deletion
   1. Prerequisites: An existing patient with the provided Name or ID in the test case exists, but the specified field (Appointment or Medical History) does not.
   2. Test case: `delete n/John Doe ap/` <br>
   Expected: Error message: `The patient does not have an appointment to delete.`
   3. Test case: `delete id/S1234567A m/` <br>
   Expected: Error message: `The patient does not have a medical history to delete.`
   4. Test case: `delete n/Alice Tan id/S7654321B m/Asthma` <br>
   Expected: Error message: `The patient does not have a medical history of Asthma to delete.`

5. Undo Deletion of Patient and Patient fields
    1. Prerequisites: An existing patient with the provided Name or ID in the test case exists
    2. Test case: Perform a valid `delete` command on a patient before doing an `undo` command. <br>
       Expected: The newly deleted patient is added back
    3. Test case: Perform a valid `delete` command on a deletable field of a patient before doing an `undo` command. <br>
       Expected: The newly deleted field of the patient is added back.

6. Invalid Command Format
   1. Prerequisites: No specific preconditions.
   2. Test case: Execute an incomplete or improperly formatted command, e.g. `delete` <br>
   Expected: Error message: `Invalid command format! ...`

### Deleting all Patients: clear or c

1. Successful Deletion of All Patients
   1. Prerequisites: No specific preconditions.
   2. Test case: `clear` <br>
   Expected: All patients deleted from HealthSync.
   3. Test case: `clear someRandomStringThatWillBeIgnored` <br>
   Expected: All patients deleted from HealthSync.

2. Undo clearing of HealthSync
   1. Prerequisites: No specific preconditions.
   2. Test case: `clear` and then execute `undo` <br>
   Expected: The clearing of HealthSync is reverted.

### Locating Patients by Name, ID or Appointment: Find

1. Successful Patient Search by Name
   1. Prerequisites: At least one patient with the specified Name in the test case.
   2. Test case: `find n/Alex` <br>
   Expected: List of patients matching the Name Alex (Alex A, A Alex and Alex B are also returned) and their related information.
   
2. Successful Patient Search by ID
   1. Prerequisites: At least one patient with the specified ID in the test case.
   2. Test case: `find id/T0123436F` <br>
   Expected: List of patients matching the ID T0123436F and their related information.

3. Successful Patient Search by Appointment
   1. Prerequisites: At least one patient with the specified Appointment in the test case.
   2. Test case: `find ap/12-Dec 0000 2359` <br>
   Expected: List of patients with appointments on 12th December, covering the entire day, and their related information.

4. Successful Patient Search by Name, ID and Appointment
   1. Prerequisites: At least one patient with the specified Name and ID in the test case. 
   2. Test case: `find n/Alex Yeoh id/T0123436F ap/10-Dec 0000 2359` <br>
   Expected: List of patients matching both the Name Alex Yeoh, ID T0123436F and having an appointment on 10th December, covering the entire day, and their related information.
   
5. No Matching Patients Found
   1. Prerequisites: Ensure the Patient with the specificed Name, ID or overlap in Appointment does not exist.
   2. Test case: `find id/T0123456F` <br>
   Expected: Error message: `No such patient found`
   3. Test case: `find n/DoesNotExit` <br>
   Expected: Error message: `No such patient found`
   4. Test case: `find ap/10-dec-1989 00:00 23:59` <br>
   Expected: Error message: `No such patient found`

### Preserving a Find Command Result in the Log: log or l

1. Successful Log After Find Command
   1. Prerequisites: At least one patient in the result of the previous find command.
   2. Test case: `log` <br>
   Expected: The last filtered values have overridden the logger tab.
   3. Test case: Perform another find command and then execute log. <br>
   Expected: The new result completely clears the current saved result from the logger tab and replaces it.
   
2. Failed Log Due to Empty List
   1. Prerequisites: There is no previous find command result
   2. Test case: `log`. <br>
   Expected: Error message: `Cannot log an empty list.`

3. Undo logging of Patient(s)
    1. Prerequisites: At least one patient in the result of the previous find command.
    2. Test case: Perform a valid `log` command before doing an `undo` command. <br>
       Expected: The recent `log` command is reverted.
   
4. Log After Closing HealthSync
    1. Prerequisites: At least one patient in the log
    2. Test case: Close HealthSync and then restart. <br>
       Expected: The log tab is empty

### Adding a New find Command Result to the current Log: alog or al

1. Successful Append to Log
   1. Prerequisites: At least one patient in the result of the previous find command.
   2. Test case: `alog` after performing a find command. <br>
   Expected: The last filtered values have been added onto the logger tab.
   3. Test case: Perform multiple valid find commands and execute `alog` after each. <br>
   Expected: Each time, the new and non-duplicated result is added below the previously-saved log.

2. Append Duplicate Patients
    1. Prerequisites: At least one patient in the result of the previous find command and in the log.
    2. Test case: Perform the same find command again and then execute `alog`. <br>
    Expected: Duplicate patients are not appended to the log.

3. Undo alog of Patient(s)
    1. Prerequisites: At least one patient in the result of the previous find command.
    2. Test case: Perform a valid `alog` command before doing an `undo` command. <br>
       Expected: The recent `alog` command is reverted.
   
4. Failed Append Due to Empty List
   1. Prerequisites: There is no previous find command result
   2. Test case: `alog` <br>
    Expected: Error message: `Cannot log an empty list.`

### Clearing Data from the Log: clog

1. Successful Log Clear
   1. Prerequisites: No specific preconditions
   2. Test case: `clog` <br>
   Expected: Logger tab has been cleared.

2. Undo clog 
    1. Prerequisites: No specific preconditionis
    2. Test case: Perform a valid `clog` command before doing an `undo` command. <br>
       Expected: The recent `clog` command is reverted.

### Undoing a Command: Undo

1. Successful Undo of Undo-able Command(s)
   1. Prerequisites: At least ten undo-able command has been executed.
   2. Test case: `undo` <br>
   Expected: The last command is undone with output message: `Undoing the last command: ...`
   3. Test case: `undo 3` <br>
   Expected: The last 3 commands are undone with output message: `Undoing 3 command(s): ...`

2. Failed Undo Due to Invalid Number
   1. Prerequisites: Only one undo-able command has been executed.
   2. Test case: `undo -2` <br>
   Expected: Error message: `Undo step count cannot be a negative number or zero.`
   3. Test case: `undo 0` <br>
   Expected: Error message: `Undo step count cannot be a negative number or zero.`
   4. Test case: `undo 999` <br>
   Expected: Error message: `Please provide a valid number of commands to undo, not exceeding the available command history size (Currently max is: 1) ...`
 
3. Undo After Closing HealthSync
   1. Prerequisites: At least one undo-able command has been executed.
   2. Test case: Close HealthSync and then restart. Execute `undo`. <br>
   Expected: Error message: `There is no history of undo-able commands to be undone.`

### Exiting HealthSync: exit

1. Exiting HealthSync
   1. Prerequisites: No specific preconditions.
   2. Test case: `exit` <br>
   Expected: HealthSync is closed.

### Editing the Data File

1. Editing Patient Details Directly
    1. Prerequisites: The healthsync.json file must exist
    2. Test case: Locate the healthsync.json data file, open it with a text editor, and manually edit patient details. Save the file. <br>
    Expected: Changes are reflected in HealthSync upon reopening.
   
2. Automatic Save Caution
   1. Prerequisites: Edit healthsync.json and intentionally make its format invalid.
   2. Test case: Edit healthsync.json to have an invalid format. This can be done by removing a parenthesis or editing a field to be invalid. Close and reopen HealthSync. <br>
   Expected: HealthSync discards all data and starts with an empty data file.

3. Making a Backup
   1. Prerequisites: At least one existing patient in HealthSync.
   2. Test case: Follow the provided instructions in the UG to make a backup of healthsync.json. <br>
   Expected: A backup copy of healthsync.json is created in the chosen location.
   
4. Restoring Data from Backup
   1. Prerequisites: Backup copy of healthsync.json is available.
   2. Test case: Replace the original or missing .json file in data/ with the backup copy. <br>
   Expected: Original data is restored upon reopening HealthSync.

   
## **Appendix: Planned Enhancements**

1. **Handling Patients with Same Name:**
Currently, HealthSync cannot manage scenarios where patients share the same name. 
We plan to enhance this by allowing users to add patients with identical names, 
providing a more robust and flexible patient management system.

2. **Improved UI Text Wrapping:**
HealthSync currently struggles with text wrapping on the UI, leading to obscured 
labels for long user inputs. Our plan is to explicitly denote labels on individual UI 
cards and enable inputs to stretch their containers, ensuring better visibility for 
users, even with lengthy inputs.

3. **Enhanced Edit Feature Feedback:**
The current edit feature does not explicitly handle cases where no fields are 
edited after a change. For instance, if a patient's phone number remains the same 
after an edit operation, HealthSync doesn't provide a special message indicating that no 
fields were edited. We plan to improve this feedback for a more informative user experience.

4. **Standardized Empty Optional Fields:**
HealthSync currently displays empty optional fields inconsistently in particular, for Appointment and Medical History.
We intend to standardize the display of empty optional fields to be 'N/A' uniformly, 
enhancing clarity and consistency in the user interface.

5. **Improved Email Validation:** HealthSync's current email validation is not 
stringent enough, accepting invalid email formats. We plan to implement more thorough 
email validation to ensure only valid email addresses are accepted.

6. **Consistent Capitalization of Patient IDs:**
While IDs are case-insensitive, HealthSync does not enforce consistent capitalization 
of patient IDs in the patient list. This is a cosmetic enhancement to maintain 
uniformity in the application.

7. **Case-Insensitive Find Feature for Appointments:** 
The current Find feature for appointments is case-sensitive, potentially causing 
inconvenience for users. We plan to enhance this feature to be case-insensitive, 
ensuring a smoother and more user-friendly experience.

8. **Improved Messaging for Empty Find Results:**
When a Find command results in an empty set, the output message currently 
reads '0 patients found.' We propose an enhancement to provide a clearer message 
such as 'No patients found,' offering better clarity and user understanding.

9. **Improved Patient Clearing Process:**
Currently, HealthSync allows users to use the shortcut `c` to clear all patients in the
HealthSync application. While this provides a quick method for clearing patient data, it lacks
a safeguard against accidental data loss. We propose an enhancement to the patient clearing
process by introducing a warning popup. When a user triggers the patient clearing shortcut 'c',
a confirmation popup will appear, prompting the user to double-check their intention before
proceeding with the clearance.