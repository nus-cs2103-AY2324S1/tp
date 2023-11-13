---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# Developer Guide

<!-- * Table of Contents -->

<page-nav-print/>

## Acknowledgements

This project has not used any third-party libraries.

---

## Setting up and Getting Started

For initial setup and getting started with the development, please refer to the guide: [_Setting up and getting started_](SettingUp.md).

---

## Design Overview

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" alt="Architecture Diagram"/>

The **_Architecture Diagram_** above provides a high-level design overview of the application.

Below is a quick overview of the main components and their interactions:

#### Main Components:

- **`Main`**: Responsible for initializing other components in the correct sequence upon application launch, and ensures proper shut down procedures are followed. It consists of [`Main`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/MainApp.java) classes.

- **`UI`** ([Details](#ui-component)): Manages the User Interface of the app.
- **`Logic`** ([Details](#logic-component)): Handles command execution.
- **`Model`** ([Details](#model-component)): Manages in-memory data.
- **`Storage`** ([Details](#storage-component)): Handles reading from and writing to the hard disk.

- **`Commons`**: A collection of classes used by multiple components.

#### Component Interactions:

The sequence diagram below shows the interactions between components for the `delete 1` command:

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" alt="Architecture Sequence Diagram" />

Each of the four main components:

- Defines its API in an interface named after the component.
- Implements its functionality using a `{Component Name}Manager` class, following the corresponding API interface.

For example, the `Logic` component's API is defined in `Logic.java`, and its functionality is implemented in `LogicManager.java`.

---

### Detailed Component Descriptions

#### UI Component

- **API**: [`Ui.java`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)
- The UI component is responsible for handling all user interface operations.

- Class Diagram - Core UI Components:
  <puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

- Class Diagram - Supplementary UI Components:
  <puml src="diagrams/UiDetailedClassDiagram.puml" alt=""/>

The UI is composed of various components such as `MainWindow`, `CommandBox`, `ResultDisplay`, `PersonListPanel`, and `StatusBarFooter`, all of which inherit from the `UiPart` class.

The UI layouts are defined in corresponding `.fxml` files located in the `src/main/resources/view` folder.

Key responsibilities include:

- Executing user commands via the `Logic` component.
- Listening for data changes in the `Model` and updating the `UI` accordingly.
- Maintaining a reference to the `Logic` component for command execution.

---

#### Logic Component

- **API**: [`Logic.java`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)
- The Logic component is responsible for interpreting and executing user commands.

Here's a partial class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550" alt="Logic Component Class Diagram"/>

Key functionalities include:

- Interpreting user input and generating the corresponding `Command` object.
- Executing the command and generating a `CommandResult` object.
- Depending on the `Model` component to perform data operations.
- Managing various command parsers to handle specific command formats.

---

#### Model Component

- **API**: [`Model.java`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)
- The Model component manages the application's in-memory data.

<puml src="diagrams/ModelClassDiagram.puml" width="450" alt="Model Component Class Diagram"/>

Key responsibilities include:

- Storing address book data.
- Managing user preferences.
- Providing an unmodifiable view of lists of `Person`, `Record` and `Appointment` objects for UI binding.

---

#### Storage Component

- **API**: [`Storage.java`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)
- The Storage component manages data persistence.

<puml src="diagrams/StorageClassDiagram.puml" width="600" alt="Storage Component Class Diagram"/>

Key functionalities include:

- Saving and retrieving address book data and user preferences in JSON format.
- Implementing both `AddressBookStorage` and `UserPrefStorage` for flexibility.
- Depending on certain `Model` classes for data object definitions.

---

## Common Classes

Classes used by multiple components are housed in the `seedu.addressbook.commons` package.

---

## Patient Features

### General Implementation Details

<puml src="diagrams/PersonClassDiagram.puml"/>

A `Person` object encapsulates various attributes:

- `Name`: Patient's first and last name (and middle name, if applicable)
- `Nric`: Patient's NRIC
- `Email`: Patient's email address
- `Phone`: Patient's phone number
- `Gender`: Patient's gender
- `Age`: Patient's age
- `BloodType`: Patient's blood type
- `Set<Allergy>`: Patient's allergies
- `UniqueRecordList`: Patient's records from past visits to the clinic.
- `UniqueAppointmentList`: Patient's scheduled appointments.
- `isPinned`: Patient's pin status.

Uniqueness of person is maintained through the `UniquePersonList`.

### Adding a Patient

#### Overview

The `addpatient` command integrates a new `Person` object with the patient's details in MedBook.

#### Related Classes and Methods

- `AddCommandParser#parse(String)`
- `AddCommand#execute(Model)`
- `Model#addPerson(Person)`, `AddressBook#addPerson(Person)`, `UniquePersonList#add(Person)`
- `PersonListPanel`, `PersonCard`

#### Implementation Steps

1. **Parse User Input**: `AddCommandParser` checks for necessary parameters and their validity.
2. **Create Person Object**: A `Person` object is instantiated during `AddCommandParser#parse(String)` and passed over to the `AddCommand`.
3. **Execute Command**: `AddPersonCommand#execute(Model)` adds the new `Person` to the `UniquePersonList` in the `Model`.

<puml src="diagrams/AddPatientSequenceDiagram.puml"/>

### Editing a Patient's Details

#### Overview

The `editpatient` command facilitates the modification of patient information by updating the fields of a `Person` object with new details.

#### Related Classes and Methods

- `EditCommandParser#parse(String)`
- `EditPersonDescriptor`
- `EditCommand#execute(Model)`
- `ModelManager#setPerson(Person,Person)`, `AddressBook#setPerson(Person,Person)`, `UniquePersonList#setPerson(Person,Person)`
- `PersonListPanel`, `PersonCard`

#### Implementation Steps

1. **Parse User Input**: `EditCommandParser` checks for necessary parameters and their validity.
2. **Create Person Object**: A `Person` object with the edited details is instantiated during `EditCommandParser#parse(String)` and passed over to the `EditCommand`.
3. **Execute Command**: `EditCommand#execute(Model)` replaces the `Person` object in `Model` with a new `Person` object that has edited details.

<puml src="diagrams/EditPatientSequenceDiagram.puml" alt=”EditPatientSequenceDiagram” />

#### Design Considerations

**Alternative 1 (Current Choice)**: Implement an Edit-by-Cloning Strategy

- Pros :
  - **Scalability:** By cloning the `Person` object before editing, the system is better equipped to handle future enhancements that may require complex transactional operations.
  - **Data Integrity:** This method ensures that the original `Person` object remains unaltered during the edit process, which reduces the risk of data corruption in the event of an operation failure.
- Cons :
  - Adds complexity and has potential performance issues.

**Alternative 2**: Modify the `Person` object directly

- Pros:
  - **Simplicity:** This straightforward approach requires less code, making it easier to implement and understand.
  - **Efficiency:** Operating directly on the `Person` object without cloning can be more performant, especially when dealing with simple edits that do not span multiple data fields.
- Cons:
  - **Limited Flexibility:** Direct modification constrains the ability to extend the system with complex transactional features or undo/redo capabilities without significant refactoring.
  - **Data Risk:** Without the safeguard of working on a cloned instance, there's a higher risk of inadvertently corrupting data during edit operations.

By considering these alternatives, the development team has chosen to prioritize a robust foundation for future development and data integrity, despite the trade-offs in complexity and potential impact on performance.

### Deleting a Patient

#### Overview

The `delete` command deletes an existing `Person` object from MedBook.

#### Related Classes and Methods

- `DeleteCommandParser#parse(String)`
- `DeleteCommand#execute(Model)`
- `ModelManager#deletePerson(Person)`, `AddressBook#removePerson(Person)`, `UniquePersonList#remove(Person)`
- `PersonListPanel`, `PersonCard`

#### Implementation Steps

1. **Parse User Input**: `DeleteCommandParser` checks for the validity of the `Person` index.
2. **Create Person Object**: An `Index` object of the `Person` is instantiated during `DeleteCommandParser#parse(String)` and passed over to the `DeleteCommand`.
3. **Execute Command**: `DeleteCommand#execute(Model)` deletes the `Person` object from `Model`.

<puml src="diagrams/DeletePatientSequenceDiagram.puml" alt=”DeletePatientSequenceDiagram” />

### Searching a Patient

#### Overview

The `search` command filters the list of patients using one or more keywords.

#### Related Classes and Methods

- `FindCommandParser#parse(String)`
- `FindCommand#execute(Model)`
- `Model#updateFilteredList(Predicate)`
- `NameContainsKeywordsPredicate#test(Person)`
- `PersonListPanel`, `PersonCard`

#### Implementations Steps

1. **Parse User Input**: `FindCommandParser` checks for existence of the keyword(s) and creates an array of keywords.
2. **Create Predicate Object**: A `NameContainsKeywordsPredicate` object is instantiated during `FindCommandParser#parse(String)` and passed over to the `FindCommand`.
3. **Execute Command**: `FindCommand#execute(Model)` finds patients containing keywords using `NameContainsKeywordsPredicate#test(Record)` and updates `FilteredPersonList`.

<puml src="diagrams/SearchSequenceDiagram.puml"/>

### Pinning a Patient

#### Overview

The `pin` command pins a patient to the **Pinned Patient List**

#### Related Classes and Methods

- `PinCommandParser#parse(String)`
- `PinCommand#execute(Model)`
- `Model#setPerson(Person, Person)`, `AddressBook#setPerson(Person, Person)`, `UniquePersonList#setPerson(Person, Person)`
- `PinnedPersonListPanel`, `PersonCard`

#### Implementations Steps

1. **Parse User Input**: `PinCommandParser` checks for the validity of the `Person` index.
2. **Create Index Object**: An `Index` object of the `Person` is instantiated during `PinCommandParser#parse(String)` and passed over to the `PinCommand`.
3. **Execute Command**: `PinCommand#execute(Model)` replaces the `Person` object in `Model` with a new `Person` object that has `isPinned` set to `true`.

<puml src="diagrams/PinSequenceDiagram.puml"/>

### Unpinning a Patient

#### Overview

The `unpin` command unpins a patient from the **Pinned Patient List**

#### Related Classes and Methods

- `UnpinCommandParser#parse(String)`
- `UnpinCommand#execute(Model)`
- `Model#setPerson(Person, Person)`, `AddressBook#setPerson(Person, Person)`, `UniquePersonList#setPerson(Person, Person)`
- `PinnedPersonListPanel`, `PersonCard`

#### Implementations Steps

1. **Parse User Input**: `UnpinCommandParser` checks for the validity of the pinned `Person` index.
2. **Create Index Object**: An `Index` object is instantiated during `UnpinCommandParser#parse(String)` and passed over to the `UnpinCommand`.
3. **Execute Command**: `UnpinCommand#execute(Model)` replaces the `Person` object in `Model` with a new `Person` object that has `isPinned` set to `false`.

<puml src="diagrams/UnpinSequenceDiagram.puml"/>

## Records Feature

### General Implementation Details

<puml src="diagrams/RecordClassDiagram.puml"/>

A `Record` object encapsulates various attributes:

- `DateTime`: Date and time of the patient's clinic visit
- `List<Condition>`: Patient's health conditions during the visit
- `List<Medication>`: Medications prescribed to the patient

Uniqueness of record is maintained through the `UniqueRecordList`.

### Adding a Record

#### Overview

The `addrecord` command integrates a new `Record` object with the record's details in MedBook.

#### Related Classes and Methods

- `AddRecordCommandParser#parse(String)`
- `AddRecordCommand#execute(Model)`
- `UniqueRecordList#add(Record)`
- `Model#setPerson(Person, Person)`, `AddressBook#setPerson(Person, Person)`, `UniquePersonList#setPerson(Person, Person)`
- `Model#updateRecordList(Person)`, `AddressBook#setRecords(Person)`, `UniqueRecordList#setRecords(UniqueRecordList)`
- `RecordListPanel`, `RecordCard`

#### Implementation Steps

1. **Parse User Input**: `AddRecordCommandParser` checks for necessary parameters and their validity.
2. **Create Record Object**: A `Record` object is instantiated during `AddRecordCommandParser#parse(String)` and passed over to the `AddRecordCommand`.
3. **Execute Command**: `AddRecordCommand#execute(Model)` replaces the `Person` object in `Model` with a new `Person` object that has the added `Record`. `Model#updateRecordList(Person)` replaces the `UniqueRecordList` in `Model` with the `UniqueRecordList` of the new `Person` object.

<puml src="diagrams/AddRecordSequenceDiagram.puml"/>

### Editing a Record's Details

#### Overview

The `editpatient` command facilitates the modification of record information by updating the fields of a `Record` object with new details.

#### Related Classes and Methods

- `EditRecordCommandParser#parse(String)`
- `EditRecordDescriptor`
- `EditRecordCommand#execute(Model)`
- `ModelManager#setPerson(Person,Person)`, `AddressBook#setPerson(Person,Person)`, `UniquePersonList#setPerson(Person,Person)`
- `Model#updateRecordList(Person)`, `AddressBook#setRecords(Person)`, `UniqueRecordList#setRecords(UniqueRecordList)`, `UniqueRecordList#setRecord(Record)`
- `RecordListPanel`, `RecordCard`

#### Implementation Steps

1. **Parse User Input**: `EditRecordCommandParser` checks for necessary parameters and their validity.
2. **Create Record Object**: A `Record` object with the edited details is instantiated during `EditRecordCommandParser#parse(String)` and passed over to the `EditRecordCommand`.
3. **Execute Command**: `EditRecordCommand#execute(Model)` replaces the `Person` object in `Model` with a new `Person` object that has the new edited `Record`. `Model#updateRecordList(Person)` replaces the `UniqueRecordList` in `Model` with the `UniqueRecordList` of the new `Person` object.

<puml src="diagrams/EditRecordSequenceDiagram.puml" alt=”EditRecordSequenceDiagram” />

#### Design Considerations

Similar to editing patient, a clone is created and modified, and then replaces the original.

**Alternative 1 (Current Choice):** Clone the `Record` object, modify the clone, and then replace the original.

- _Pros_:

  - **Data Integrity:** By working on a clone, we minimize the risk of corrupting the original data in case of an error during the update process.
  - **Undo/Redo Capability:** This approach allows for an easier implementation of undo/redo functionalities as we have distinct before and after states.
  - **Consistency:** It maintains a consistent methodology with the editpatient command, which uses a similar approach for updating patient details.

- _Cons_:
  - **Performance Overhead:** Cloning objects can introduce a performance hit, especially if the record is large or if there are many fields to update.
  - **Complexity:** The codebase complexity increases due to the additional steps required to manage the cloning and replacement process.

**Alternative 2:** Update the `Record` object directly.

- _Pros_:
  - **Performance:** This approach is more performant since it involves direct manipulation of the object without the need to create a copy.
  - **Simplicity:** The logic is more straightforward, as it doesn't involve cloning, making the code easier to understand and maintain.
- _Cons_:
  - **Risk to Data Integrity:** Any errors during the update can corrupt the original data, as changes are made in place.
  - **Difficulty in Extending Functionality:** Future features such as undo/redo or real-time collaboration could be harder to implement as changes are not isolated.

In conclusion, the decision to proceed with Alternative 1 was made to prioritize the application's long-term robustness and maintainability, accepting the trade-offs in performance and immediate simplicity for the sake of a safer and more extensible editing feature.

### Deleting a Record

#### Overview

The `deleterecord` command deletes an existing `Record` object from MedBook.

#### Related Classes and Methods

- `DeleteRecordCommandParser#parse(String)`
- `DeleteRecordCommand#execute(Model)`
- `UniqueRecordList#remove(Record)`
- `Model#setPerson(Person, Person)`, `AddressBook#setPerson(Person, Person)`, `UniquePersonList#setPerson(Person, Person)`
- `Model#updateRecordList(Person)`, `AddressBook#setRecords(Person)`, `UniqueRecordList#setRecords(UniqueRecordList)`
- `RecordListPanel`, `RecordCard`

##### Implementation Steps

1. **Parse User Input**: `DeleteRecordCommandParser` checks for the validity of the patient and record indices.
2. **Create Index Object**: Two `Index` objects, patient index and record index, are instantiated during `DeleteRecordCommandParser#parse(String)` and passed over to the `DeleteRecordCommand`.
3. **Execute Command**: `DeleteRecordCommand#execute(Model)` replaces the `Person` object in `Model` with a new `Person` object that has the updated `UniqueRecordList`. `Model#updateRecordList(Person)` replaces the `UniqueRecordList` in `Model` with the `UniqueRecordList` of the new `Person` object.

<puml src="diagrams/DeleteRecordSequenceDiagram.puml"/>

### Viewing a patient's medical records

The `view` command displays the list of records of the patient being viewed.

#### Related class and methods

- `ViewCommandParser#parse(String)`
- `ViewCommand#execute(Model)`
- `Model#updateRecordList(Person)`, `AddressBook#setRecords(Person)`, `UniqueRecordList#setRecords(UniqueRecordList)`
- `Model#getPersonBeingViewed()`, `AddressBook#getPersonBeingViewed()`, `Logic#getPersonBeingViewed()`
- `RecordListPanel`, `RecordCard`

#### Implementation steps

1. **Parse User Input**: `ViewCommandParser` checks for validity of the patient index.
2. **Create Index Object**: An `Index` object is instantiated during `ViewCommandParser#parse(String)` and passed over to the `ViewCommand`.
3. **Update and Display**: `ViewCommand#execute(Model)` invokes the `Model#updateRecordList(Person)` to update the record list of the `Model`. The record list of the specific patient is displayed.

<puml src="diagrams/ViewSequenceDiagram.puml" alt="ViewSequenceDiagram" />

### Searching a Record

#### Overview

The `searchrecord` command filters the list of records of the patient being viewed using one or more keywords.

#### Related Classes and Methods

- `FindRecordCommandParser#parse(String)`
- `FindRecordCommand#execute(Model)`
- `Model#updateFilteredRecordList(Predicate)`
- `RecordContainsKeywordsPredicate#test(Record)`
- `RecordListPanel`, `RecordCard`

#### Implementations Steps

1. **Parse User Input**: `FindRecordCommandParser` checks for existence of the keyword(s) and creates an array of keywords.
2. **Create Predicate Object**: A `RecordContainsKeywordsPredicate` object is instantiated during `FindRecordCommandParser#parse(String)` and passed over to the `FindRecordCommand`.
3. **Execute Command**: `FindRecordCommand#execute(Model)` finds records containing keywords using `RecordContainsKeywordsPredicate#test(Record)` and updates `FilteredRecordList`.

   <puml src="diagrams/FindRecordSequenceDiagram.puml"/>

### Attaching Files to Patient Records

#### Overview

The feature allows users to associate files with patient records.

#### Related Class and Methods

- `RecordCard#handleAttachFile(ActionEvent)`
- `Record#setFilePath(FilePath, Index)`
- `EditRecordCommand#setFilePath(FilePath)`
- `StorageManager#saveAddressBook(Model)`
- `RecordCard#handleOpenFile(ActionEvent)`

#### Implementation Steps

1. **User Button Press**: `RecordCard#handleAttachFile(ActionEvent)` opens file explorer, prompting user to select the file to attach.
2. **Saving Filepath**: `EditRecordCommand#setFilePath(FilePath)` and `Record#setFilePath(FilePath, Index)` updates the filepath of the corresponding record.
3. **User File Press**: `RecordCard#handleOpenFile(ActionEvent)` opens the file.

#### Alternative Considerations

In designing this feature, we considered two primary approaches:

1. **Current Implementation (GUI)**:

   - _Pros_: The graphical user interface (GUI) for file attachments is user-friendly, especially for individuals less familiar with command-line interfaces (CLI).
   - _Cons_: It may be relatively slower for users proficient with CLI-based interactions.

2. **Command-Line Interface (CLI)**:
   - _Pros_: CLI-based file attachment would cater to users who prefer efficient, command-driven workflows.
   - _Cons_: May require a learning curve for users less experienced with command-line interactions.

Ultimately, the decision was made to implement the feature with a GUI to ensure accessibility and ease of use for a broader range of users, while still allowing for efficient management of patient records.

## Appointments Feature

### General Implementation Details

<puml src="diagrams/AppointmentClassDiagram.puml"/>

An `Appointment` object encapsulates various attributes:

- `Name`: Appointment title.
- `DateTime`: Date and Time of the Appointment.
- `Nric`: Nric of the patient involved in the Appointment.

Uniqueness is enforced through a `UniqueAppointmentList`.

### Adding an Appointment

#### Overview

The `addappointment` command integrates a new `Appointment` object with the appointment's details in MedBook.

#### Related Classes and Methods

- `AddAppointmentCommandParser#parse(String)`
- `AddAppointmentCommand#execute(Model)`
- `UniqueAppointmentList#setAppointments(UniqueAppointmentList)`, `UniqueAppointmentList#add(Appointment)`
- `Model#setPerson(Person, Person)`, `AddressBook#setPerson(Person, Person)`, `UniquePersonList#setPerson(Person, Person)`
- `Model#resetAppointmentList()`
- `AppointmentWindow`, `AppointmentCalendarPanel`, `AppointmentListPanel`, `AppointmentCard`

#### Implementation Steps

1. **Parse User Input**: `AddAppointmentCommandParser` checks for necessary parameters and their validity.
2. **Create Appointment Object**: An `Appointment` object is instantiated during `AddAppointmentCommandParser#parse(String)` and passed over to the `AddAppointmentCommand`.
3. **Execute Command**: `AddAppointmentCommand#execute(Model)` replaces the `Person` object in `Model` with a new `Person` object that has the added `Appointment`. `Model#resetAppointmentList()` resets the `UniqueAppointmentList` of the `Model`.

<puml src="diagrams/AddAppointmentSequenceDiagram.puml"/>

### Deleting an Appointment

#### Overview

The `deleteappointment` command deletes an existing `Appointment` from MedBook.

#### Related Classes and Methods

- `DeleteAppointmentCommandParser#parse(String)`
- `DeleteAppointmentCommand#execute(Model)`
- `UniqueAppointmentList#setAppointments(UniqueAppointmentList)`, `UniqueAppointmentList#remove(Appointment)`
- `Model#setPerson(Person, Person)`, `AddressBook#setPerson(Person, Person)`, `UniquePersonList#setPerson(Person, Person)`
- `Model#resetAppointmentList()`
- `AppointmentWindow`, `AppointmentCalendarPanel`, `AppointmentListPanel`, `AppointmentCard`

#### Implementation Steps

1. **Parse User Input**: `DeleteAppointmentCommandParser` checks for the validity of the appointment index.
2. **Create Index Object**: An `Index` object is instantiated during `DeleteAppointmentCommandParser#parse(String)` and passed over to the `DeleteAppointmentCommand`.
3. **Execute Command**: `DeleteAppointmentCommand#execute(Model)` replaces the `Person` object in `Model` with a new `Person` object that has the updated `UniqueAppointmentList`. `Model#resetAppointmentList()` resets the `UniqueAppointmentList` of the `Model`.

<puml src="diagrams/DeleteAppointmentSequenceDiagram.puml"/>

### Viewing the Appointment Window

#### Overview

The `viewappointment` command opens/focuses the `AppointmentsWindow`.

#### Related Classes and Methods

- `ViewAppointmentCommand#execute(Model)`
- `MainWindow#handleAppointments()`, `AppointmentsWindow#show()`, `AppointmentsWindow#fillInnerParts()`
- `AppointmentWindow`, `AppointmentCalendarPanel`, `AppointmentListPanel`, `AppointmentCard`

#### Implementation Steps

1. **Execute Command**: `ViewAppointmentCommand#execute(Model)` returns a new `CommandResult` with `showAppointments` set to `true`.
2. **Show Appointments Window**: `MainWindow#handleAppointments()` calls the `AppointmentsWindow#show()` method which opens/focuses the `AppointmentsWindow`.
3. **Populate Appointments Data**: `AppointmentsWindow#fillInnerParts()` populates the `AppointmentCalendarPanel`, `AppointmentListPanel` with a `UniqueAppointmentList`.

<puml src="diagrams/ViewAppointmentSequenceDiagram.puml"/>

### Design Considerations

- **Alternative 1 (Current Choice)**: `Model` holds a `UniqueAppointmentList` consisting of all `Appointment` objects, each `Person` also has a `UniqueAppointmentList` consisting of all `Appointment` objects assigned to the person. Each `Appointment` object has the corresponding `Person` `NRIC` as a field.
  - _Pros_: Operations like searching and filtering for all appointments are easier when a centralised list is available.
  - _Cons_: Keeping the central `UniqueAppointmentList` in `Model` and individual lists in each `Person` synchronized can be challenging and might lead to data inconsistencies if not managed properly. Any change in an `Appointment` requires updates in two places, adding to the complexity and processing time.
- **Alternative 2**: Each `Person` holds their own `UniqueAppointmentList` consisting of all `Appointment` objects assigned to the person.
  - _Pros_: This approach simplifies the data model by avoiding the need for a centralised appointment list.
  - _Cons_: Operations that require knowledge of all appointments, like finding available slots or generating reports, become more complex, as they need to aggregate data from each Person.

## User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​       | I can …​                                                                                                      | So that I can …​                                                             |
| -------- | ------------- | ------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------- |
| `* * *`  | user          | _add_ a patient's detail including age, gender, or blood type etc. to the application                         | keep track of my patients                                                    |
| `* * *`  | user          | _add_ a patient’s medical records with details including date, time, condition and prescribed medication etc. | manage patients' medical records efficiently                                 |
| `* * *`  | user          | _add_ a patient's appointments with details including date, time and title etc. with a patient                | manage patients' appointments efficiently                                    |
| `* * *`  | user          | _view_ a list of all the patients in the app                                                                  | have an overview of all my patients                                          |
| `* * *`  | user          | _view_ a list of all the medical records of a patient                                                         | have an overview of all the medical records of my patients                   |
| `* * *`  | user          | _view_ a list of all the upcoming appointments                                                                | have an overview of all my upcoming medical appointments                     |
| `* * *`  | user          | _edit_ an existing patient’s details                                                                          | keep the patient's details accurate and up-to-date                           |
| `* * *`  | user          | _edit_ an existing medical record's details                                                                   | keep the medical record's details accurate and up-to-date                    |
| `* * *`  | user          | _delete_ a specific patient from the app                                                                      | remove patients that are no longer relevant or needed                        |
| `* * *`  | user          | _delete_ a specific medical record from the app                                                               | remove erroneous medical records.                                            |
| `* * *`  | user          | _delete_ a specific appointment from the app                                                                  | remove erroneous appointments.                                               |
| `* * *`  | new user      | _see_ the app populated with sample data                                                                      | see how the app will look when it is in use                                  |
| `* * *`  | new user      | _access_ a “help” page to view the app’s basic commands                                                       | conveniently view basic commands within the app                              |
| `* * *`  | user          | _save_ the address book automatically                                                                         | prevent accidental loss of data                                              |
| `* *`    | user          | _search_ for specific patients using keywords such as the patient’s name or blood type etc.                   | find and filter specific patients from a long list of patients               |
| `* *`    | user          | _search_ for a specific medical record of a patient using keywords such as date, condition or medication      | find and filter specific medical records from a long list of medical records |
| `* *`    | user          | _pin_ a specific patient                                                                                      | conveniently view patients details                                           |
| `* *`    | user          | _unpin_ a specific patient                                                                                    |                                                                              |
| `* *`    | user          | _attach_ files such as lab reports and prescription images to a patient's medical records                     | keep documents in an organised manner                                        |
| `* *`    | advanced user | _directly edit_ the MedBook data stored in the JSON file                                                      | make specific and controlled changes to the data                             |
| `* *`    | user          | _edit_ an existing appointment's details                                                                      | keep the appointment's details accurate and up-to-date                       |
| `*`      | user          | _receive_ regular updates and bug fixes for the app                                                           |                                                                              |
| `*`      | user          | _receive_ reminders for upcoming patient appointments                                                         | be punctual for upcoming appointments                                        |
| `*`      | user          | _export_ patient data                                                                                         | share or transfer data between different systems                             |
| `*`      | user          | _leave_ patient data encrypted                                                                                | prevent unauthorised access to the data                                      |
| `*`      | user          | _sort_ existing appointments by date                                                                          | quickly view the appointments which are soon upcoming                        |

## Use Cases

### UC01 - Viewing Help

- **Actor**: User
- **System**: MedBook
- **Main Success Scenario (MSS)**:
  1. User requests for help.
  2. MedBook displays help information.<br>
     Use case ends.

### UC02 - Adding a Patient

- **Actor**: User
- **System**: MedBook
- **Main Success Scenario (MSS)**:
  1. User requests to add a new patient.
  2. User enters the patient's details.
  3. MedBook adds the patient to the system.<br>
     Use case ends.
- **Extensions**:
  - 2a. MedBook detects an error in the entered patient's details.
    - 2a1. MedBook shows an error message.
    - 2a2. User enters new patient details.
    - Steps 2a1-2a2 are repeated until the patient details entered is correct.
    - Use case resumes from step 3.

### UC03 - Listing All Patients

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one patient entry in the system.
- **Main Success Scenario (MSS)**:
  1. User requests to list patients.
  2. MedBook shows a list of patients.<br>
     Use case ends.

### UC04 - Editing a Patient's Details

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one patient entry in the system.
- **Main Success Scenario (MSS)**:
  1. User lists all patients (UC03).
  2. User requests to edit a patient's details.
  3. User enters the new patient details.
  4. MedBook updates the patient details.<br>
     Use case ends.
- **Extensions**:
  - 3a. MedBook detects an error in the entered patient's details.
    - 3a1. MedBook shows an error message.
    - 3a2. User enters new patient details.
    - Steps 3a1-3a2 are repeated until the patient details entered is correct.
    - Use case resumes from step 4.

### UC05 - Searching for a Specific Patient

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one patient entry in the system.
- **Main Success Scenario (MSS)**:
  1. User lists all patients (UC03).
  2. User requests to search for a specific patient.
  3. User enters search criteria.
  4. MedBook performs a search and displays matching patients.<br>
     Use case ends.
- **Extensions**:
  - 4a. No matches found.
    - 4a1. MedBook informs the user that there were no matches.
    - Use case ends.

### UC06 - Delete a Patient

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one patient entry in the system.
- **Main Success Scenario (MSS)**:
  1. User lists all patients (UC03).
  2. User requests to delete a specific patient.
  3. User enters patient ID.
  4. MedBook deletes the patient.<br>
     Use case ends.
- **Extensions**:
  - 3a. MedBook detects an error in the entered patient's ID.
    - 3a1. MedBook shows an error message.
    - 3a2. User enters new patient ID.
    - Steps 3a1-3a2 are repeated until the patient ID entered is correct.
    - Use case resumes from step 4.

### UC07 - Pin a Patient

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one patient entry in the system.
- **Main Success Scenario (MSS)**:
  1. User lists all patients (UC03).
  2. User requests to pin a specific patient.
  3. User enters patient ID.
  4. MedBook pins the patient.<br>
     Use case ends.
- **Extensions**:
  - 3a. MedBook detects an error in the entered patient's ID.
    - 3a1. MedBook shows an error message.
    - 3a2. User enters new patient's ID.
    - Steps 3a1-3a2 are repeated until the patient ID entered is correct.
    - Use case resumes from step 4.

### UC08 - Unpin a Patient

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one pinned patient.
- **Main Success Scenario (MSS)**:
  1. User requests to unpin a specific patient.
  2. User enters the patient's PINNEDID.
  3. MedBook unpins the patient.<br>
     Use case ends.
- **Extensions**:
  - 2a. MedBook detects an error in the entered PINNEDID.
    - 2a1. MedBook shows an error message.
    - 2a2. User enters new PINNEDID.
    - Steps 2a1-2a2 are repeated until the PINNEDID entered is correct.
    - Use Case resumes from step 3.

### UC09 - Adding a Record to a Patient

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one patient entry in the system.
- **Main Success Scenario (MSS)**:
  1. User lists all patients (UC03).
  2. User requests to add a new record to a patient.
  3. User enters the record's details.
  4. MedBook adds the record to the patient.<br>
     Use case ends.
- **Extensions**:
  - 3a. MedBook detects an error in the entered record's details.
    - 3a1. MedBook shows an error message.
    - 3a2. User enters new record details.
    - Steps 3a1-3a2 are repeated until the record details entered is correct.
    - Use case resumes from step 4.

### UC10 - View Patient's Medical Records

- **Actor**: User
- **System**: MedBook
- **Main Success Scenario (MSS)**:
  1. User lists all patients (UC03).
  2. User requests to view a specific patient's records.
  3. User enters the patient ID.
  4. MedBook displays the records of the patient.<br>
     Use case ends.
- **Extensions**:
  - 3a. MedBook detects an error in the entered patient's ID.
    - 3a1. MedBook shows an error message.
    - 3a2. User enters new patient ID.
    - Steps 3a1-3a2 are repeated until the patient ID entered is correct.
    - Use case resumes from step 4.

### UC11 - Editing a Record

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one record entry in the patient.
- **Main Success Scenario (MSS)**:
  1. User views a patient’s medical records (UC10).
  2. User requests to edit a patient’s record’s details.
  3. User enters new record details.
  4. MedBook updates the record’s details.<br>
     Use case ends.
- **Extensions**:
  - 3a. MedBook detects an error in the entered record’s details.
    - 3a1. MedBook shows an error message.
    - 3a2. User enters new record details.
    - Steps 3a1-3a2 are repeated until the record details entered is correct.
    - Use case resumes from step 4.

### UC12 - Deleting a Record under a Patient

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one record entry in the patient.
- **Main Success Scenario (MSS)**:
  1. User views a patient’s medical records (UC10).
  2. User requests to delete a patient’s record.
  3. User enters the patient ID and record ID.
  4. MedBook deletes the record.<br>
     Use case ends.
- **Extension**:
  - 3a. MedBook detects an error in the entered patient ID and/or record ID.
    - 3a1. MedBook shows an error message.
    - 3a2. User enters new patient ID and record ID.
    - Steps 3a1-3a2 are repeated until the patient ID and record ID entered is correct.
    - Use case resumes from step 4.

### UC13 - Searching for Records

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one record entry in the patient.
- **Main Success Scenario (MSS)**:
  1. User views a patient (UC10).
  2. User requests to search for a specific records.
  3. User enters search keywords.
  4. MedBook performs a search and displays matching record.<br>
     Use case ends.
- **Extension**:
  - 4a. No matches found.
    - 4a1. MedBook informs the user that there were no matches.
    - Use case ends.

### UC14 - Adding an Appointment to a Patient

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one patient entry in the system.
- **Main Success Scenario (MSS)**:
  1. User requests to add a new appointment to a patient.
  2. User enters the appointment's details.
  3. MedBook adds the appointment to the patient.<br>
     Use case ends.
- **Extensions**:
  - 2a. MedBook detects an error in the entered appointment's details.
    - 2a1. MedBook shows an error message.
    - 2a2. User enters new appointment details.
    - Steps 2a1-2a2 are repeated until the appointment details entered is correct.
    - Use Case resumes from step 3.

### UC15 - Viewing Appointments

- **Actor**: User
- **System**: MedBook
- **Main Success Scenario (MSS)**:
  1. User requests to view appointments.
  2. MedBook shows the user all appointments.<br>
     Use case ends.

### UC16 - Deleting an Appointment

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one appointment entry in the system.
- **Main Success Scenario (MSS)**:
  1. User views all appointments (UC15).
  2. User requests to delete a new appointment.
  3. User enters the appointment's ID.
  4. MedBook deletes the appointment.<br>
     Use case ends.
- **Extensions**:
  - 3a. MedBook detects an error in the entered appointment ID.
    - 3a1. MedBook shows an error message.
    - 3a2. User enters new appointment ID.
    - Steps 3a1-3a2 are repeated until the appointment ID entered is correct.
    - Use Case resumes from step 4.

### UC17 - Attaching Files

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one record entry in the patient
- **Main Success Scenario (MSS)**:

1.  User views a patient’s medical records (UC10).
2.  User requests to attach a file to a record of a patient.
3.  User selects a file.
4.  MedBook saves the file to the medical record.<br>
    Use case ends.

- **Extension**:
- 3a. User does not choose a file.
  - 3a1. MedBook displays an error message.
  - Use case ends.

### UC18 - Opening Files

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one record entry with a file attached in the patient
- **Main Success Scenario (MSS)**:

1.  User requests to view a file attached to a record.
2.  MedBook opens the file on the user’s default launcher.<br>
    Use case ends.

- **Extension**:
- 1a. File does not exist in user’s local storage.
  - 1a1. MedBook displays an error message.
  - Use case ends.

---

## Non-Functional Requirements

1. Should work on any mainstream OS with Java 11 or above.
   - Rationale: Ensures accessibility for users on different platforms.
   - Metric: Application functions correctly on Windows, macOS, and Linux.
2. Supports up to 1000 patients without performance issues.
   - Rationale: Ensures scalability for larger clinics.
   - Metric: Application performs smoothly with a database of 1000 patients.
3. Faster operation with commands than mouse for proficient typists.
   - Rationale: Enhances productivity for users familiar with the command line.
   - Metric: Common tasks are completed faster using keyboard shortcuts than GUI.
4. Backward compatible with data from previous versions.
   - Rationale: Ensures smooth transition for existing users upgrading to a new version.
   - Metric: Users can open and interact with data files from previous versions without issues.
5. Usable by novices.
   - Rationale: Ensures the application is accessible to new users.
   - Metric: New users can perform basic tasks without having to keep referring to the user manual.
6. Provides comprehensive error messages and guidance for recovery.
   - Rationale: Helps users understand what went wrong and how to fix it.
   - Metric: Error messages include a description of the issue and steps for resolution.
7. Comprehensive documentation and user guides available.
   - Rationale: Provides users with resources to understand and use the application effectively.
   - Metric: Documentation covers all features, includes screenshots, and is easy to navigate.

---

## Glossary

- **Mainstream OS**: Popular operating systems such as Windows, Linux, Unix, and OS-X.

- **CLI (Command Line Interface)**: A user interface that allows users to interact with the software using text commands via a console or terminal.

- **GUI (Graphical User Interface)**: A user interface that allows users to interact with the software through graphical icons and visual indicators, as opposed to text-based interfaces.

- **Backward Compatibility**: The ability of the system to work with data and interfaces from earlier versions of the software.

- **Healthcare Professional**: An individual who provides healthcare services, such as doctors, nurses, and medical staff.

- **Performance Issues**: Any lag, delay, or inefficiency in the software’s response or processing time, especially noticeable when handling a large amount of data.

- **Patient Information**: Data related to a patient, including but not limited to their personal details, medical history, contact information, and any other relevant information.

---

## Documentation, Logging, Testing, Configuration, Dev-Ops

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

---

## Appendix: Requirements

### Product Scope

**Target User Profile**:

- **Primary Users**: Healthcare professionals in private clinics who handle patient information on a regular basis.
- **Platform Preference**: Has a strong inclination towards using desktop applications, particularly those that support Command Line Interfaces.
- **Technical Proficiency**: Is adept at typing.

**Value Proposition**:

- **Efficient Patient Management**: Provides a streamlined and efficient solution for managing patient information, ensuring that healthcare professionals can access and modify patient data quickly.
- **Speed and Accessibility**: Designed to be significantly faster than conventional GUI applications, allowing users to execute commands and retrieve patient information in a matter of seconds.
- **Command Line Efficiency**: Leverages the power of CLI to offer advanced users the ability to perform tasks in a more direct and efficient manner, while still maintaining accessibility for those who prefer graphical interfaces.

## Appendix: Instructions for Manual Testing

<box type="info" seamless>
    
**Note**: These instructions only provide a starting point for testers to work on; testers are expected to do more *exploratory* testing.
    </box>

### Launch and Shutdown

#### Initial Launch

1. Download and place the jar file in an empty folder.
2. Launch the jar application via the terminal.
   - Expected: GUI opens with sample contacts.

---

#### Saving Window Preferences

1. Resize and reposition the window, then close it.
2. Reopen the application.
   - Expected: Window retains its size and position.

---

### Adding a New Patient

1. Test Case: `addpatient n/John Doe i/T0000000Z e/johndoe@gmail.com p/98765432 g/M a/30 bt/AB+ al/Dust`

   - Expected: New patient "John Doe" is added to the list, details are shown in the status message.

2. Test Case: `addpatient n/John Doe i/0000000 e/johndoe@gmail.com p/98765432 g/M a/30 bt/AB+ al/Dust`
   - Expected: Error message displayed, patient not added due to incorrect format of NRIC
3. Test Case: `addpatient n/John Doe i/T0000000Z e/johndoegmail.com p/98765432 g/M a/30 bt/AB+ al/Dust`
   - Expected: Error message displayed, patient not added due to incorrect format of email.
4. Test Case: `addpatient n/John Doe i/T0000000Z e/johndoe@gmail.com p/12 g/M a/30 bt/AB+ al/Dust`
   - Expected: Error message displayed, patient not added due to incorrect format of phone number.
5. Test Case: `addpatient n/John Doe i/T0000000Z e/johndoe@gmail.com p/98765432 g/T a/30 bt/AB+ al/Dust`
   - Expected: Error message displayed, patient not added because gender can only be `M` or `F`.
6. Test Case: `addpatient n/John Doe i/T0000000Z e/johndoe@gmail.com p/98765432 g/M a/-1 bt/AB+ al/Dust`
   - Expected: Error message displayed, patient not added because age can only be a non-negative integer.
7. Test Case: `addpatient n/John Doe i/T0000000Z e/johndoe@gmail.com p/98765432 g/M a/30 bt/AP al/Dust`
   - Expected: Error message displayed, patient not added due to incorrect blood type.

---

### Editing a Patient's Details

1. Prerequisites: Ensure the patient list is displayed and contains the entry you wish to edit.
2. Test Case: `editpatient 1 a/35`

   - Expected: Patient at index 1 has their age updated to 35. Details shown in the status message.

3. Test Case: `editpatient x a/35` (where x > list size)
   - Expected: Error message displayed, patient's details unchanged.

---

### Deleting a Patient

1. Prerequisites: Ensure the patient list is displayed and contains the entry you wish to delete.
2. Test Case: `delete 1`

   - Expected: Patient at index 1 is deleted. Details shown in status message.

3. Test Case: `delete 0`
   - Expected: Error message displayed, **Patient List** unchanged.
4. Other Test Cases: `delete`, `delete x` (where x > list size)
   - Expected: Error message displayed, **Patient List** unchanged.

---

### Pinning a Patient

1. Prerequisites: Ensure the patient list is displayed and contains the entry you wish to pin.
2. Test Case: `pin 1`

   - Expected: Patient at index 1 is pinned to the **Pinned Patient List**. Details shown in the status message.

3. Test Case: `pin x` (where x > list size)
   - Expected: Error message displayed, **Pinned Patient List** unchanged.

---

### Unpinning a Patient

1. Prerequisites: Ensure the **Pinned Patient List** contains the entry you wish to unpin.
2. Test Case: `unpin 1`

   - Expected: Patient at index 1 of the **Pinned Patient List** is unpinned and no longer displayed in the **Pinned Patient List**. Details shown in the status message.

3. Test Case: `unpin x` (where x > list size)
   - Expected: Error message displayed, **Pinned Patient List** unchanged.

---

### Searching for Patients

1. Prerequisites: Ensure the patient list contains entries that will match your search term.
2. Test Case: `search John`

   - Expected: List of patients with details matching exactly "John" is displayed.

---

### Adding a Record under Patient

1. Prerequisites: Ensure the patient list is displayed
2. Test Case: `addrecord 1 d/12-11-2023 2200 c/Fever m/Ibuprofen`

   - Expected: Adds the specified record to the first patient.

3. Test Case: `addrecord x d/12-11-2023 2200 c/Fever m/Ibuprofen` (where x > size of patient list)
   - Expected: Error message displayed, record not added.
4. Test Case: `addrecord 1 d/12112023 c/Fever m/Ibuprofen`
   - Expected: Error message displayed suggesting date and time should in the form of dd-mm-yyyy hhmm

---

### View Patient's Medical Records

1. Prerequisites: Ensure the patient list is displayed and contains the entry you wish to view.
2. Test Case: `view 1`

   - Expected: Medical records of the first patient are displayed, details shown in the status message.

3. Test Case: `view 0`

   - Expected: Error message displayed, **Medical Record List** unchanged.

4. Other Test Cases: `view`, `view x` (where x > list size)

   - Expected: Error message displayed, **Medical Record List** unchanged.

---

### Editing a Record's Details

1. Prerequisites: Ensure the record list of the patient is displayed and contains the entry you wish to edit.
2. Test Case: `editrecord 1/1 c/Fever`

   - Expected: The record at index 1 of the Patient at index 1 has its conditions updated to only "Fever". Details shown in the status message.

3. Test Case: `editrecord 1/1 c/Fever m/Paracetamol`

   - Expected: The record at index 1 of the Patient at index 1 has its conditions updated to only "Fever" and medications to only "Paracetamol". Details shown in the status message.

4. Test Case: `editrecord x/1 c/Fever` (where x > patient list size)

   - Expected: Error message displayed, record's details unchanged.

5. Test Case: `editrecord 1/x c/Fever` (where x > record list size)

   - Expected: Error message displayed, record's details unchanged.

6. Test Case: `editrecord 1/1 d/12112023`

   - Expected: Error message displayed suggesting date and time should in the form of "dd-mm-yyyy hhmm".

7. Test Case: `editrecord 1/1 d/12-11-2023 2200 d/13-11-2023 2200`
   - Expected: Error message displayed suggesting multiple inputs of date are not allowed.

---

### Deleting a Record

1. Prerequisites: Ensure the record list of the patient is displayed and contains the entry you wish to edit.
2. Test Case: `deleterecord 1/1`

   - Expected: Deletes the first record of the first patient.

3. Test Case: `deleterecord x/1` (where x > size of patient list)

   - Expected: Error message displayed, **Medical Record List** unchanged.

4. Test Case: `deleterecord 1/y` (where y > size of record list of the first patient)
   - Expected: Error message displayed, **Medical Record List** unchanged.

---

### Searching Records of the Patient Being Viewed

1. Prerequisites: Ensure the record list of the patient is displayed.
2. Test Case: `searchrecord Ibuprofen`

   - Expected: List of records with "Ibuprofen" in the details is displayed.

---

### Attaching File to Record

1. Prerequisites: Ensure the record list of the patient is displayed and it is not empty.
2. Test Case: Click on “Attach Files” and select file from local storage.

   - Expected: “File successfully attached” is displayed and file link is added to record.

3. Test Case: Click on “Attach Files” and cancel the file explorer without selecting a file

   - Expected: Error message displayed as no file was selected.

---

### Opening File in Record

1. Prerequisites: Ensure the record list of the patient is displayed and it is not empty. Ensure the record has a file attached.
2. Test Case: Click on Filepath link.

   - Expected: File is opened using the user’s default launcher.

3. Test Case: Delete or relocate file in local storage. Click on Filepath link.

   - Expected: Error message displayed as file path no longer exists

---

### Adding an Appointment

1. Prerequisites: Ensure the patient list is displayed and contains the entry you wish to add an appointment to.
2. Test Case: `addappointment 1 n/Eye Exam d/18-10-2023 1900`

   - Expected: New appointment, "Eye Exam" is added to the patient with index 1, details are shown in the status message.

3. Test Case: `addappointment x n/Eye Exam d/18-10-2023 1900` (where x > list size)
   - Expected: Error message displayed, appointment not added.
4. Test Case: `addappointment 1 d/18-10-2023 1900`
   - Expected: Error message displayed, appointment not added.
5. Test Case: `addappointment 1 n/Eye Exam`
   - Expected: Error message displayed, appointment not added.
6. Test Case: `addappointment 1 n/Eye Exam d/18/10/2023 1900`
   - Expected: Error message displayed, appointment not added.

---

### Viewing Appointments

#### Appointment Window is closed

1. Test Case: `viewappointment`
   - Expected: Appointment Window opens.

#### Appointment Window is open but not in focus

1. Test Case: `viewappointment`
   - Expected: Appointment Window focuses.

---

### Deleting an Appointment

1. Prerequisites: Ensure the appointment list is displayed and contains the entry you wish to delete.
2. Test Case: `deleteappointment 1`
   - Expected: Appointment with index 1 is deleted, details are shown in status message.
3. Test Case: `deleteappointment x` (where x > list size)
   - Expected: Error message displayed, **Appointment List** unchanged.

---

### Verifying Patient Data Integrity

#### After Operations

1. Prerequisites: Perform operations like adding, deleting, and editing patients.
2. Navigate to the folder where data is stored and open the data file.
   - Expected: All changes should be accurately reflected in the data file.

---

### Handling Invalid Commands

#### Input Mistakes

1. Test Case: `addreocrd n/John Doe i/A0000000A a/30 g/M e/jd@example.com p/98776543 bt/AB+ al/Dust`

   - Expected: Error message displayed stating unknown command.

2. Test Case: `delet 1`
   - Expected: Error message displayed stating unknown command.

## Appendix: Planned Enhancements

### Additional Navigation in Appointment Calender

#### Current Implementation

The current version of the Appointment Calendar allows users to view appointments in a calendar format. Navigation through the calendar is limited to sequential month traversal using 'Next' and 'Previous' buttons. This implementation, while functional, can be cumbersome for users who wish to view appointments that are several months away from the current date.

#### Proposed Enhancement

We plan to enhance the calendar navigation by introducing a more efficient way for users to view appointments. The enhancement will allow users to:

**Manual Selection of Month and Year**: A dropdown menu or a picker control will be integrated into the UI, enabling users to quickly jump to a specific month and year without sequentially navigating through each month.

**`viewcalender MM YYYY` command**: For users who prefer typing through the CLI, we will implement a command that allows them to view the calendar for a specific month and year. Users will be able to enter a command in the format viewcalendar MM YYYY (e.g., viewcalendar 12 2023 to view December 2023), and the calendar will update to display the selected month and year.

### Enhanced Appointment Calender UI

#### Current Implementation

The existing UI of the Appointment Calendar displays a truncated version of the appointment name and limits the visible appointments to only two per day. This restriction can lead to incomplete information visibility which is not optimal for efficiency and user experience.

#### Proposed Enhancement

To improve the user interface and overall user experience of the Appointment Calendar, the following enhancements are proposed:

**Expanded Appointment Visibility**: Modify the calendar layout to allow for the display of more than two appointments per day. This could involve redesigning the day cells to accommodate more entries or implementing a scrolling mechanism within each day cell for days with numerous appointments.
Ensure that the UI remains uncluttered and user-friendly, even with the inclusion of more appointment entries per day.

**Full Appointment Name Display**: Adjust the UI to display the full name of each appointment, rather than a truncated version. This will enable users to quickly identify appointments at a glance without needing to hover over or click into the appointment for full details.
Implement a dynamic text resizing or wrapping feature within each calendar entry to ensure that longer appointment names fit within the allocated space without compromising readability.

**Responsive and Adaptive Design**: Enhance the calendar’s responsive design so it adapts effectively to different screen sizes and resolutions. This ensures that the increased information density does not negatively impact users on smaller screens or mobile devices.

### Enhanced NRIC Parameter for Patients

#### Current Implementation

In the current implementation the NRIC parameter for patient identification is limited in its format. It accepts an entry consisting of an alphabet, followed by seven digits, and then another alphabet. This format, while broadly useful, does not align completely with real-world scenarios, particularly in Singapore, and lacks the flexibility required for foreign patients.

#### Proposed Enhancement

To make the NRIC parameter more inclusive and reflective of real-world use cases, especially in Singapore, we propose enhancing the NRIC parameter with the following features:

**Restricted First Alphabet**: The first alphabet in the NRIC will now be restricted to specific letters, such as 'S' and 'T', which are currently used in Singapore. This change aligns the system more closely with the actual format of NRICs in Singapore.

**Optional Passport Number Support**: To accommodate foreign patients who do not have an NRIC, the system will be enhanced to accept passport numbers as an alternative identifier. This feature is particularly important for private clinics that cater to a diverse patient base, including non-residents and tourists.

### Accepting `/` in Name parameter

#### Current Implementation

Due to current constraints in the Parser which causes / to be parsed as tags, the "/" character cannot be entered into the name parameter. As such, users would currently not be able to enter "Muhammed Ali s/o Muhammed Ali".

#### Proposed Enhancement

Modify the parsing logic to differentiate between command tags and legitimate use of special characters in names. This could involve implementing an escape character mechanism or a more advanced parsing algorithm that can contextually understand the use of "/" in different scenarios.

### Patient Index Alignment

#### Current Implementation

The Patient ID in the patients in the **Pinned Patient List** and **Patient Being Viewed** is unaligned with the **Patient List** which might lead to confusion in users for which Patient ID to follow.

#### Proposed Enhancement

Modify the way panels create new person card entries. This could involve creating new lists to keep track of the patient index and update commands associated with the Pinned Patients and Patient Being Viewed.

## Appendix: Effort

This appendix aims to an insight into the total effort that went into the development of Medbook.

### Effort Overview

**Duration:** The project spanned approximately 2 months from initial conception to final release.
**Team:** Consisted of 5 members.

#### Technical Complexity and Challenges

- **Data Security:** Implementing robust security measures such as data encryption to protect sensitive patient information was paramount and required extensive research and testing. (It was omitted due to constraints related to the Project)
- **Defensive Coding:** We enforced immutability of `Person`, `Record`, and `Appointment` to maintain data integrity.
- **User Interface:** Crafting an intuitive GUI that also supported a CLI for efficiency demanded iterative design and usability testing.

#### Effort Quantification

- **Development:** The team invested over 300 hours in coding, with a focused effort on crafting intuitive and responsive GUI and CLI interfaces. This investment reflects our commitment to usability and accessibility, ensuring that both novice and experienced users can navigate the application with ease.

- **Testing:** More than 100 hours were dedicated to a combination of manual and automated testing. This rigorous testing protocol was critical in validating the application's reliability and optimizing its performance across various user scenarios and system environments.

- **Documentation:** We allocated 50 hours to developing thorough user documentation. This comprehensive guide is pivotal for facilitating a quick adoption of the application by new users and serves as a reliable reference for existing users to leverage the application's full potential.

#### Reuse of Existing Solutions

- **Library Reuse:** Utilized the JavaFX library for the GUI, which saved much effort for front-end development.

#### Effort Comparison with Reference Projects:

- **Compared to AB3:** the development of Medbook demanded considerably more effort, primarily due to its capability to manage multiple entity types such as patients, records, and appointments. This multifaceted approach contrasts with AB3's design, which is centered around handling a single entity type.
  Moreover, Medbook was built upon the AB3 architecture as a foundational base, necessitating a deep understanding of the existing complex framework. Even though certain features in Medbook were adapted from AB3 to suit our specific needs, significant effort was required to modify and extend these features. Tailoring pre-existing functionalities to fit into our more comprehensive application model involved intricate work, ensuring seamless integration and functionality within Medbook's broader scope.

#### Achievements

- Despite the high complexity, the team managed to deliver Medbook on schedule.
- User feedback has been overwhelmingly positive, especially regarding the ease of use and performance of the application.

#### Conclusion

The successful development of Medbook is a testament to the well-coordinated effort, rigorous testing, and effective project management that adapted to challenges and complexity with innovative solutions and strategic planning.
