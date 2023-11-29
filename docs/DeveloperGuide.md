---
layout: page
title: Developer Guide
show-toc: true
---
## About ToothTracker

ToothTracker is a **Dental Clinic Administrative Management System** designed for **dental clinic administration assistants**.
From adding new patient and dentist profiles to seamlessly creating appointment schedules, ToothTracker is
engineered to simplify every aspect of your dental clinic administrative responsibilities.

This Developer Guide provides an in-depth documentation on how ToothTracker is designed and implemented.

{% include toc.md header=true show-in-toc=true ordered=true %}

## Preface

### Acknowledgements

* Java dependencies:
  * [JavaFX](https://openjfx.io/) for GUI
  * [Jackson](https://github.com/FasterXML/jackson) for JSON-related operations
  * [JUnit5](https://github.com/junit-team/junit5) for testing
  * [CalendarFX](https://github.com/dlsc-software-consulting-gmbh/CalendarFX) for CalendarView
* User Guide drew inspiration from:
  * [FoodRem](https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/2a34fab143ace403adeb4b923258468140b66200/docs/UserGuide.md)
  * [ArtBuddy](https://github.com/AY2223S1-CS2103T-W11-3/tp/blob/master/docs/UserGuide.md)
* Developer Guide drew inspiration from:
  * [FoodRem](https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/2a34fab143ace403adeb4b923258468140b66200/docs/UserGuide.md)
* Code Acknowledgements:
  * [Pure liquid HTML table-of-contents](https://github.com/AY2324S1-CS2103T-W10-3/tp/blob/master/docs/_includes/toc.html) from [@allejo on GitHub](https://github.com/allejo/jekyll-toc)
  * [Pure CSS auto-incrementing heading counters](https://github.com/AY2324S1-CS2103T-W10-3/tp/blob/master/docs/_sass/toc.scss) adapted from [@gvgramazio on StackOverflow](https://stackoverflow.com/a/51007932/9311854)

### Setting Up, Getting Started

Refer to the guide [_Setting up and getting started_](SettingUp.md).


## Design

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
  API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

{% include page-break.html %}

### UI component
The UI component handles the user-interface portion of the application.

The **API** of this component is specified
in [`Ui.java`](https://github.com/AY2324S1-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts
e.g.`CommandBox`, `ResultDisplay`, `PatientListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the
visible GUI.

The `UI` component uses the JavaFX UI framework. The layout of these UI parts is defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2324S1-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-W10-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component
The Logic component handles the execution of commands.

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates
   a parser that matches the command (e.g., `DeletePatientCommandParser`) and uses it to parse the command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeletePatientCommand`) which
   is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete-patient 1")` API
call as an example.

![Interactions Inside the Logic Component for the `delete-patient 1` Command](images/DeletePatientSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeletePatientCommandParser` should end at the destroy marker (X) but due to the limitation of PlantUML, the lifeline reaches the end of diagram.
</div>


Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddPatientCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddPatientCommand`) which the `AddressBookParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPatientCommandParser`, `DeletePatientCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

{% include page-break.html %}

### Model component

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ToothTrackerModelClassDiagram.png" width="700"/>

The `Model` component,

* stores the ToothTracker address book data i.e., all `Patient`, `Dentist`, `Appointment`, and `Treatment` objects
(which are contained in a `UniquePatientList`, `UniqueDentistList`, `UniqueAppointmentList`, and `UniqueTreatmentList` objects respectively).
* stores the currently 'selected' `Patient`, `Dentist`, or `Appointment` objects (e.g., results of a `search-patient`, `search-dentist`, or `filter-appointment`)
  as corresponding separate _filtered_ lists which are exposed to outsiders as unmodifiable `ObservableList<Patient>`, `ObservableList<Dentist>` and `Observable<Appointment>`
  that can be 'observed' e.g. the UI can be bound to these lists so that the UI automatically updates when the data in the lists change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` object.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components).

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/ToothTrackerStorage.png" width="650" />

The `Storage` component,

* can save both address book data and user preference data in JSON format, and read them back into corresponding
  objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.
**API** : [`Commons`](https://github.com/AY2324S1-CS2103T-W10-3/tp/tree/master/src/main/java/seedu/address/commons)

--------------------------------------------------------------------------------------------------------------------

## Implementation

This section describes some noteworthy details on how certain features are implemented.

The features implemented are categorized into 5 sections:

1. [Dentist Features](#dentist-features)
2. [Patient Features](#patient-features)
3. [Appointment Features](#appointment-features)
4. [Treatment Features](#treatment-features)
5. [General Features](#general-features)

### Dentist Features

#### Adding a Dentist

The `add-dentist` command creates a new dentist record in ToothTracker. This command forms the fundamental business logic to represent dentists.

The activity diagram for creating a new dentist is illustrated as follows:

![AddDentistActivityDiagram](images/AddDentistActivityDiagram.png)

This sequence diagram shows the interactions between the various components during the execution of the `add-dentist` command:

![AddDentistSequenceDiagram](images/AddDentistSequenceDiagram.png)

##### Feature Details

1. Users provide essential dentist information, such as their name, phone number, specialization, years of experience and other optional details like email, address and tags.
2. In case of missing or invalid command arguments, the system prompts users with an error message to enter the command correctly.
3. The system cross-references the new dentist's name with existing records in the `Model` to prevent duplicate entries. If a duplicate is found, an error message informs the user.
4. If step 3 is completed without any exceptions, the new patient record is created and stored in the system.

##### Feature Considerations

For dentist specialization, broader terms like "orthodontics" are used instead of specifying the exact type of treatment (e.g., root canal, braces, scaling).
This approach prevents the "add-dentist" command from becoming excessively long.

The working hours of a dentist is not an attribute in the `add-dentist` command as dentists might not immediately know their
shifts when they first join, and it might change frequently.

We handle duplicates by not allowing multiple dentists of the same name to be created (eg. only 1 John Tan can exist in ToothTracker). We will allow multiple dentists of
the same name to be created in future implementations. For now, if there are multiple dentists with the same name, add in additional information such as their last 3 digits of NRIC
as part of their name attribute.

#### Deleting a Dentist

The `delete-dentist` command deletes a dentist record in ToothTracker. This command forms the fundamental business logic to represent dentists.

The activity diagram for deleting a dentist is illustrated as follows:

![DeleteDentistActivityDiagram](images/DeleteDentistActivityDiagram.png)

This sequence diagram shows the interactions between the various components during the execution of the `delete-dentist` command:

![DeleteDentistSequenceDiagram](images/DeleteDentistSequenceDiagram.png)

##### Feature Details

1. The user specifies a `DENTIST_ID` that represents a `Dentist` to be edited.
2. If an invalid `DENTIST_ID` is provided, an error is thrown and the user is prompted to enter the command correctly via an error message.
3. The Dentist is cross-referenced in the `Model` to check if it exists. If it does not, then an error is raised to inform the user.
4. If step 3 completes without any exceptions, then the `Dentist` is successfully deleted.

##### Feature Considerations

In implementing the delete feature, we needed proper error handling and validation to ensure ToothTracker's robustness and provide clear guidance to the user.
Our approach validates the `DENTIST_ID` and shows an error message if the dentist does not exist.
This is in comparison to allowing commands to fail silently if the dentist specified does not exist.

- Pros: Prevents invalid operations and provides immediate feedback to the user, helping to correct mistakes.
- Cons: Additional validation checks add complexity to the code.


#### Searching for a dentist

The `search-dentist` command finds dentist records in ToothTracker by allowing users to enter a specific `DENTIST_ID` or
name-related keywords.

The activity diagram for searching for a dentist is illustrated as follows:

![SearchDentistActivityDiagram](images/SearchDentistActivityDiagram.png)

This sequence diagram shows the interactions between the various components during the execution of the `search-dentist` command:

![SearchDentistSequenceDiagram](images/SearchDentistSequenceDiagram.png)

##### Feature Details
1. Users initiate a search for a dentist using either a unique `DENTIST_ID` or by inputting specific `KEYWORDS` that might match a dentist's name.
2. If the user opts for an ID-based search, the system processes the request to return a single record that matches the provided `DENTIST_ID`.
3. If keywords are used, the system performs a broader search by comparing the keywords as substrings with the names in the dentist records.
4. In scenarios where the search criteria do not correspond with any existing records (either no matching ID or keywords), the system generates an error message informing the user of the unsuccessful search attempt.
5. When matches are found, the system displays a list of dentists whose records meet the search criteria.

##### Feature Considerations

The `search-dentist` feature in ToothTracker focuses on searching using either a unique `DENTIST_ID` or keywords matching a dentist's name,
prioritizing speed and simplicity in accessing dentist records. For more complex searching which requires additional dentist attributes, users
are recommended to use the `filter-dentist` command instead. This approach ensures a balanced functionality within ToothTracker, offering a balance
between quick searches for immediate needs while also accommodating more complex and attribute-specific inquiries.

#### Filtering a dentist

The `filter-dentist` command in ToothTracker provides users with a more refined search functionality, allowing them to filter dentist records based on
specific criteria beyond just `DENTIST_ID` or name-related keywords. This feature offers a versatile and detailed search capability for users who
require precise results from the dentist records database.

The activity diagram for filtering dentists is illustrated as follows:

![FilterDentistActivityDiagram](images/FilterDentistActivityDiagram.png)

This sequence diagram shows the interactions between the various components during the execution of the `filter-dentist` command:

![FilterDentistSequenceDiagram](images/FilterDentistSequenceDiagramOld.png)

##### Feature Details
1. Users initiate a filter for a dentist by providing various filter criteria such as SPECIALIZATION, EXPERIENCE, TAGS, and more.
These criteria allow users to search for dentists with specific attributes.
2. ToothTracker processes the user's filter criteria and matches them against the dentist records in the database.
3. Dentists that meet the filter criteria are displayed as search results, providing users with a list of dentists that fulfill their specific requirements.
4. If no matches are found for the given filter criteria, the system informs the user that no results were found based on the specified filters.

##### Feature Considerations

The `filter-dentist` feature in ToothTracker is tailored for users who require precise control over their dentist searches. Unlike the `search-dentist` command,
which primarily relies on `DENTIST_ID` and name-related keywords, the `filter-dentist` command operates by filtering based on specific attributes within a dentist's record.

To ensure the validity of the filter criteria, the `filter-dentist` command conducts validation checks to confirm that the selected attribute for filtering is a valid attribute
associated with a dentist's record.

While the `filter-dentist` command contains validation checks if the attribute to filter by is valid, it is important to note that there is no validation checks within each attribute to verify whether the entered
keyword is of a valid type for that particular attribute. Users are responsible for inputting keywords that are meaningful and applicable to the chosen attribute.


### Patient Features

#### Adding a Patient

The `add-patient` command creates a new patient record in ToothTracker. This command forms the fundamental business logic to represent patients.

The activity diagram for creating a new patient is illustrated as follows:

![AddPatientActivityDiagram](images/AddPatientActivityDiagram.png)

This sequence diagram shows the interactions between the various components during the execution of the `add-patient` command:

![AddPatientSequenceDiagram](images/AddPatientSequenceDiagram.png)

##### Feature Details

1. Users provide essential patient information, such as their name, phone number, birth date, gender and other optional details like remark, treatment, email and address.
2. In case of missing or invalid command arguments, the system prompts users with an error message to enter the command correctly.
3. The system cross-references the new patient's name with existing records in the `Model` to prevent duplicate entries. If a duplicate is found, an error message informs the user.
4. If step 3 is completed without any exceptions, the new patient record is created and stored in the system.

##### Feature Considerations

For the optional Treatment field, should the user opt to enter a treatment, it is mandatory that the specified treatment already exists within ToothTracker.
If this condition is not met (the treatment specified does not exist in ToothTracker), the user will receive an error message.

#### Deleting a Patient

The `delete-patient` command deletes a patient record in ToothTracker. This command forms the fundamental business logic to represent patients.

The activity diagram for deleting a patient is illustrated as follows:

![DeletePatientActivityDiagram](images/DeletePatientActivityDiagram.png)

This sequence diagram shows the interactions between the various components during the execution of the `delete-patient` command:

![DeletePatientSequenceDiagram](images/DeletePatientSequenceDiagram.png)

##### Feature Details

1. The user specifies a `PATIENT_ID` that represents a `Patient` to be edited.
2. If an invalid `PATIENT_ID` is provided, an error is thrown and the user is prompted to enter the command correctly via an error message.
3. The Patient is cross-referenced in the `Model` to check if it exists. If it does not, then an error is raised to inform the user.
4. If step 3 completes without any exceptions, then the `Patient` is successfully deleted.

##### Feature Considerations

In implementing the delete feature, we needed proper error handling and validation to ensure ToothTracker's robustness and provide clear guidance to the user.
Our approach validates the `PATIENT_ID` and shows an error message if the patient does not exist.
This is in comparison to allowing commands to fail silently if the patient specified does not exist.

- Pros: Prevents invalid operations and provides immediate feedback to the user, helping to correct mistakes.
- Cons: Additional validation checks add complexity to the code.


#### Searching for a patient

The `search-patient` command finds patient records in ToothTracker by allowing users to enter a specific `PATIENT_ID` or
name-related keywords.

The activity diagram for searching for a patient is illustrated as follows:

![SearchPatientActivityDiagram](images/SearchPatientActivityDiagram.png)

This sequence diagram shows the interactions between the various components during the execution of the `search-patient` command:

![SearchPatientSequenceDiagram](images/SearchPatientSequenceDiagram.png)

##### Feature Details
1. Users initiate a search for a patient using either a unique `PATIENT_ID` or by inputting specific `KEYWORDS` that might match a patient's name.
2. If the user opts for an ID-based search, the system processes the request to return a single record that matches the provided `PATIENT_ID`.
3. If keywords are used, the system performs a broader search by comparing the keywords as substrings with the names in the patient records.
4. In scenarios where the search criteria do not correspond with any existing records (either no matching ID or keywords), the system generates an error message informing the user of the unsuccessful search attempt.
5. When matches are found, the system displays a list of patients whose records meet the search criteria.

##### Feature Considerations

The `search-patient` feature in ToothTracker focuses on searching using either a unique `PATIENT_ID` or keywords matching a patient's name,
prioritizing speed and simplicity in accessing patient records. For more complex searching which requires additional patient attributes, users
are recommended to use the `filter-patient` command instead. This approach ensures a balanced functionality within ToothTracker, offering a balance
between quick searches for immediate needs while also accommodating more complex and attribute-specific inquiries.

#### Filtering a patient

The `filter-patient` command in ToothTracker provides users with a more refined search functionality, allowing them to filter patient records based on
specific criteria beyond just `PATIENT_ID` or name-related keywords. This feature offers a versatile and detailed search capability for users who
require precise results from the patient records database.

The activity diagram for filtering patients is illustrated as follows:

![FilterPatientActivityDiagram](images/FilterPatientActivityDiagram.png)

This sequence diagram shows the interactions between the various components during the execution of the `filter-patient` command:

![FilterPatientSequenceDiagram](images/FilterPatientSequenceDiagram.png)

##### Feature Details
1. Users initiate a filter for a patient by providing various filter criteria such as PHONE, ADDRESS, GENDER, TREATMENT and more.
   These criteria allow users to search for patients with specific attributes.
2. ToothTracker processes the user's filter criteria and matches them against the patient records in the database.
3. Patients that meet the filter criteria are displayed as search results, providing users with a list of patients that fulfill their specific requirements.
4. If no matches are found for the given filter criteria, the system informs the user that no results were found based on the specified filters.

##### Feature Considerations

The `filter-patient` feature in ToothTracker is tailored for users who require precise control over their patient searches. Unlike the `search-patient` command,
which primarily relies on `PATIENT_ID` and name-related keywords, the `filter-patient` command operates by filtering based on specific attributes within a patient's record.

To ensure the validity of the filter criteria, the `filter-patient` command conducts validation checks to confirm that the selected attribute for filtering is a valid attribute
associated with a patient's record.

While the `filter-patient` command contains validation checks if the attribute to filter by is valid, it is important to note that there is no validation checks within each attribute to verify whether the entered
keyword is of a valid type for that particular attribute. Users are responsible for inputting keywords that are meaningful and applicable to the chosen attribute.

### Appointment Features

#### Adding an Appointment

The `add-appointment` command creates a new appointment record in ToothTracker.

The activity diagram for creating a new appointment is illustrated as follows:

![AddAppointmentActivityDiagram](images/AddAppointmentActivityDiagram.png)

The sequence diagram shows the interactions between the various components during the execution of the `add-appointment` command:

![AddAppointmentSequenceDiagram](images/AddAppointmentSequenceDiagram.png)

##### Feature Details

1. Users provide essential appointment information, such as the `DENTIST_ID`, `PATIENT_ID`, appointment start time and treatment name.
2. In case of missing or invalid command arguments, the system prompts users with an error message to enter the command correctly.
3. The system retrieves information about the treatment cost, duration, dentist and patient from the `Model` using the information provided by the user.
4. The system checks the new appointment's time slot with existing appointments in the `Model` to prevent clashing appointments.
If a timing clash is found, an error message informs the user.
5. If step 4 is completed without any exceptions, the new appointment record is created and stored in the system.

##### Feature Considerations

For the `DENTIST_ID`, `PATIENT_ID` and treatment field, it is mandatory that the specified dentist, patient and treatment exists in ToothTracker.
If these conditions are not met, the user will receive an error message.

Users should use the command `list-appointment` first before adding an appointment due to the way `add-appointment` is implemented.
When adding an appointment, the list of appointments might get filtered if the new appointment clashes with existing
ones due to the check for clashes. The filtered list will remain until `list-appointment` is called.
Thus, if a new appointment is immediately added after this, the new appointment may not show up on the list of
appointments in the UI. Users would have to use `list-appointment` to view the newly added appointment on the list.

After adding an appointment, the details of the appointment can no longer be changed even if the details of the dentist,
patient or treatment are changed as we did not implement cascading. Users would have to delete the appointment and add
a new one if they wish to change the details of the appointment.

#### Deleting an Appointment

The `delete-appointment` command deletes an appointment record from ToothTracker.

The activity diagram for deleting an appointment is illustrated as follows:

![DeleteAppointmentActivityDiagram](images/DeleteAppointmentActivityDiagram.png)

The sequence diagram shows the interactions between the various components during the execution of the `delete-appointment` command:

![DeleteAppointmentSequenceDiagram](images/DeleteAppointmentSequenceDiagram.png)

##### Feature Details

1. The user specifies an `APPOINTMENT_ID` that represents an `Appointment` to be deleted.
2. If an invalid `APPOINTMENT_ID` is provided, an error is thrown and the user is prompted to enter the command correctly via an error message.
3. The Appointment is cross-referenced in the `Model` to check if it exists. If it does not, then an error is raised to inform the user.
4. If step 3 completes without any exceptions, then the `Appointment` is successfully deleted.

##### Feature Considerations

In implementing the `delete-appointment` feature, we needed proper error handling and validation to ensure ToothTracker's robustness and provide clear guidance to the user.
Our approach validates `APPOINTMENT_ID` and shows an error message if the appointment does not exist.
This is in comparison to allowing commands to fail silently if an appointment does not exist.

- Pros: Prevents invalid operations and provides immediate feedback to the user, helping to correct mistakes.
- Cons: Additional validation checks add complexity to the code.

#### Filtering an Appointment

The `filter-appointment` command filters appointments by `DENTIST_ID` or `PATIENT_ID`.

The activity diagram for filtering an appointment is illustrated as follows:

![FilterAppointmentActivityDiagram](images/FilterAppointmentActivityDiagram.png)

The sequence diagram shows the interactions between the various components during the execution of the `filter-appointment` command:

![DeleteAppointmentSequenceDiagram](images/FilterAppointmentSequenceDiagram.png)

##### Feature Details

1. Users initiate a filter for appointments using either a unique `DENTIST_ID` or a unique `PATIENT_ID`.
2. If an invalid `DENTIST_ID` or `PATIENT_ID` is provided, an error is thrown and the user is prompted to enter the command correctly via an error message.
3. If the user opts to filter by `DENTIST_ID`, the system processes the request to return a list of appointments with the specific dentist.
4. If the user opts to filter by `PATIENT_ID`, the system processes the request to return a list of appointments with the specific patient. 
5. If there are no appointments with the specific dentist or patient, the system informs the user that no appointments were found with the specific dentist or patient.

##### Feature Considerations

Validation checks are performed to ensure that the `DENTIST_ID` or `PATIENT_ID` is valid and is clearly stated.
Otherwise, the user would receive an error message that prompts them to input the correct command and details.

If no appointments with the specified dentist or patient are found in ToothTracker, it should be clearly
communicated to the user instead of just displaying an empty list. A message stating that no appointments are found with the
specified `DENTIST_ID` or `PATIENT_ID` should be displayed to the user.

#### Listing all Appointments

The `list-appointment` command lists all appointment records saved in ToothTracker.

##### Feature Details

1. Users would key in `list-appointment`.
2. All appointment records saved in ToothTracker would be displayed in the appointment list area.

##### Feature Considerations

Since `filter-appointment` and `add-appointment` may alter the appointment list displayed, we implemented
`list-appointment` so that users can view all the appointments saved in ToothTracker.


### Treatment Features

#### Adding a Treatment

The `add-treatment` command creates a new treatment record in ToothTracker.

The activity diagram for creating a new treatment is illustrated as follows:

![AddTreatmentActivityDiagram](images/AddTreatmentActivityDiagram.png)

The sequence diagram of the `add-treatment` command:

![AddTreatmentSequenceDiagram](images/AddTreatmentSequenceDiagram.png)

##### Feature Details
1. Users would key in the available treatments in their clinic, specifying the treatment name, cost and its duration.
2. In the event of missing or invalid command arguments, the system prompts users with an error message to enter the command correctly.
3. The system cross-references the new treatment name with existing records in the `Model` to prevent duplicate entries. If a duplicate is found, an error message informs the user.
4. If step 3 is completed without any exceptions, the new treatment record is created and stored in the system.


#### Listing a Treatment

The `list-treatment` command would list all treatments recorded in ToothTracker.

##### Feature Details
1. Users would key in `list-treatment`.
2. All available treatments would be listed in the command box.
3. In the event that there are no treatments, ToothTracker would display a message in the command box.

##### Feature Considerations
To optimize the user interface within the limited space constraints of ToothTracker, we have chosen to only display the treatment names to the user.
While it would be ideal to present the cost and duration of each treatment directly, this can easily lead to clutter within ToothTracker.

To address this problem, we will consider implementing a separate window in future implementations that provides a comprehensive overview of all available treatments,
along with their respective costs and durations. This additional window will ensure that users can still access detailed information without overwhelming the main interface.
By doing so, we maintain a clean and user-friendly design while offering detailed information regarding treatment types.

#### Deleting a Treatment

The `delete-treatment` command deletes a treatment record in ToothTracker.

The activity diagram for deleting a treatment is illustrated as follows:

![DeleteTreatmentActivityDiagram](images/DeleteTreatmentActivityDiagram.png)

This sequence diagram shows the interactions between the various components during the execution of the `delete-treatment` command:

![DeleteTreatmentSequenceDiagram](images/DeleteTreatmentSequenceDiagram.png)

### General Features

##### Exit ToothTracker
The `exit` command is used to close or exit the ToothTracker application. This command provides a way to leave the application.

##### Clear
The `clear` command is used to refresh the display within ToothTracker. It removes any unnecessary information from the command box, providing a clean slate for a better user experience.

##### Help
The `help` command is used to access a guide or information on available commands and their usage within ToothTracker. This command serves as a quick reference to assist users in navigating the application.

--------------------------------------------------------------------------------------------------------------------
## Future Implementation

### Filter Feature Enhancement
#### Overview
As a future improvement to the filter feature, we are considering implementing validation for keyword arguments to ensure that they are valid
for the specified attribute. This enhancement aims to provide a more robust and user-friendly filtering mechanism, preventing the use of irrelevant or incorrect keywords.

#### Validation of Keywords

Currently, the filter feature checks the validity of the attribute to filter by but does not validate the keywords provided. For instance, if filtering by the attribute "birthday," users can enter nonsensical or invalid keywords,
potentially leading to undesired results.

To address this, the following steps can be taken:

1. **Define Valid Keywords:**
    - Establish a set of valid keywords for each attribute that can be used in the filter command.

2. **Keyword Validation Check:**
    - Modify the filter feature to validate the provided keywords against the predefined set of valid keywords for the specified attribute.

3. **Informative Error Messages:**
    - Provide informative error messages if users attempt to filter using invalid keywords, guiding them on the correct usage.

4. **User Guide Update:**
    - Update the User Guide to include information about valid keywords for each attribute, ensuring users are aware of the allowed values.

This enhancement contributes to the overall reliability and user experience of the filter feature, making it more robust and user-friendly.

### Command History

#### Overview
Introduce a command history feature to enhance the user experience by allowing users to retrieve and reuse their last entered commands.
This functionality is similar to the up arrow functionality in most IDEs or terminals, providing users with a convenient way to recall and execute previous commands.

#### Key Features

1. **Command History Navigation:**
    - Allow users to navigate through their command history using arrow keys (up and down) within the CLI interface.

2. **Display Previous Commands:**
    - Implement a mechanism to display a list of previous commands as the user navigates through the history. This can be shown either in-place or in a separate section of the CLI.

3. **Immediate Execution:**
    - Enable users to immediately execute a command from the history by selecting it. This streamlined process saves time and enhances user efficiency.

4. **Persistent History Across Sessions:**
    - Ensure that the command history is persistent across different sessions of the application, allowing users to access their command history even after closing and reopening the CLI.

5. **Configurable History Size:**
    - Provide users with the ability to configure the size of their command history. This allows customization based on individual preferences and workflow.

#### Usage Example
```plaintext
$ user@ToothTracker:~$ [User enters a command]
$ user@ToothTracker:~$ [User presses the up arrow]
$ user@ToothTracker:~$ [Previous command from history is displayed]
$ user@ToothTracker:~$ [User presses Enter to execute the displayed command]
```

#### Benefits

1. **Improved Efficiency:**
Users can quickly access and reuse previously entered commands, reducing the need to type repetitive commands manually.


2. **User-Friendly Interaction:**
Enhances the overall user experience by providing a familiar and intuitive command history navigation, similar to other CLI interfaces.


### List-Treatment Feature Enhancement
#### Overview
The current <code>list-treatment</code> command outputs all available treatment names in a text box due to time and UI constraints. Including additional details such as duration and cost would overcrowd the command box.
To address these limitations, a future enhancement for the <code>list-treatment</code> command could be the introduction of a new popup window. This window would present a table with columns for treatment name, cost, and duration when the command is entered.

#### Benefits

1. **Improved Readability**
   - A table format allows users to quickly scan and compare options, making it easier to digest information at a glance. Clear separation of data into columns would significantly enhance the readability of the information.

2. **Enhanced User Experience**
   -  A popup window would declutter the main command box, leading to a cleaner and more focused user interface. This would allow users to stay in the context of the command while accessing detailed information without being overwhelmed.


### Quick Notes Feature Enhancement
#### Overview
The current quick notes box features white as the text selection color, which causes selected text to blend into the white background,
rendering it invisible. A proposed enhancement is to change the selection color to grey to maintain the visibility of the selected text.

#### Benefits
1. **Enhanced Visibility**
   - Grey text selection would stand out against a white background, making it easier for users to see what text they have selected. This would prevent the problem of text 'disappearing' upon selection.
2. **User Accessibility**
   - The change would be a significant improvement for users with visual impairments or those working in brightly lit environments where screen glare can make it hard to distinguish between colors with low contrast.


### Appointment Name Updater
#### Overview
The current implementation of the appointments class stores all data as strings, leading to inconsistencies when a patient or dentist's name is changed, as this change is not automatically reflected in existing appointments. To address this, we could implement an update script for the 'edit-patient' and 'edit-dentist' commands that would:

1. Search through the list of appointments.
2. Identify appointments that contain the ID of the patient or dentist whose name has been edited.
3. Update the relevant name field to reflect the new name.

This enhancement would ensure that name changes are consistently carried out across all records, thereby maintaining data integrity and coherence.

### Future Appointment Updater
#### Overview
In the current implementation of ToothTracker, the deletion of a patient or dentist does not automatically remove their association with future appointments, which leads to obsolete records. To rectify this, we could integrate a script into the `delete-patient` or `delete-dentist` command with the following steps:

1. Conduct a search through the appointments list.
2. Locate any future appointments linked to the ID of the patient or dentist who has been deleted.
3. Proceed to remove these identified future appointments from the system.

This would ensure the automatic removal of any future appointments related to patients or dentists who have been deleted, thereby maintaining an accurate and up-to-date schedule within ToothTracker.


--------------------------------------------------------------------------------------------------------------------
## Documentation, logging, testing, configuration, dev-ops

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## Appendix: Requirements

### Product scope

**Target user profile**:
Front Desk Dental Clinic Administrative Staff who

* need to obtain patient/dentist personal details quickly
* need find out the most recent appointments
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Easily obtain patient/dentist records through a CLI

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​      | I want to …​                                          | So that I can…​                                                           |
|----------|--------------|-------------------------------------------------------|---------------------------------------------------------------------------|
| `* * *`  | receptionist | create a new patient profile                          | keep track and maintain patient records                                   |
| `* * *`  | receptionist | create a new dentist profile                          | keep track and maintain dentist records                                   |
| `* * *`  | receptionist | delete a patient/dentist                              | remove individuals who are no longer associated with the clinic           |
| `* * *`  | receptionist | list all patients/dentists                            | view all patients/dentists in the clinic                                  |
| `* * *`  | receptionist | edit a dentist/patient profile                        | keep my records up to date                                                |
| `* * *`  | receptionist | search for patients by name or ID                     | access patient profiles quickly                                           |
| `* * *`  | receptionist | search for dentists by name or ID                     | access dentist profiles quickly                                           |
| `* * *`  | receptionist | filter for patients using any attributes of a patient | view all patients that match filter criteria based on specific attributes |
| `* * *`  | receptionist | filter for dentists using any attributes of a dentist | view all dentists that match filter criteria based on specific attributes |
| `* * *`  | receptionist | create a new treatment                                | record the treatment performed in an appointment                          |
| `* * *`  | receptionist | list all treatments available                         | check what available treatments in the clinic                             |
| `* * *`  | receptionist | delete a treatment                                    | remove treatments that are no longer provided in the clinic               |
| `* * *`  | receptionist | create a new appointment                              | assign a patient and a dentist for an appointment                         |
| `* * *`  | receptionist | list all appointments                                 | view all appointments available in the clinic                             |
| `* * *`  | receptionist | delete an appointment                                 | remove appointments cancelled or postponed in the clinic                  |
| `* * *`  | receptionist | filter for appointments using Patient ID              | view all appointments that patient has in the clinic                      |
| `* * *`  | receptionist | filter for appointments using Dentist ID              | view all appointments that dentist has in the clinic                      |
| `* * *`  | receptionist | view clinic schedule in a calendar                    | have an overview of the clinic's schedules for admin management           |



### Use cases

(For all use cases below, the **System** is  `ToothTracker` and the **Actor** is the `user`, unless specified
otherwise)

---

**Use case: Add a Dentist**

**MSS**

1. User submits a request to create a new dentist, and provides information about the dentist.
2. ToothTracker acknowledges the request to add a new dentist.

Use case ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `add-dentist` command is provided.

      Use case continues from step 2.


- **1b. ToothTracker finds a pre-existing dentist entry.**
    - ToothTracker alerts the user about the duplicate entry.
    - Steps within 1b loop until a new, unique entry is provided.

      Use case continues from step 2.

**Use Case: Search for a Dentist**

**MSS**

1. User submits a request to search for a dentist:
    - User specifies search criteria, which can be either a `DENTIST_ID` or keywords that matches the name of a dentist.

2. ToothTracker searches for a dentist based on the criteria:
    - If the request specifies a `DENTIST_ID`:
        - ToothTracker looks for a dentist with the matching ID.
    - If the request specifies keywords to match a dentist name:
        - ToothTracker searches for a dentist with a name that matches the specified keywords.

3. ToothTracker displays the search results:
    - If one or more matching dentists are found:
        - ToothTracker lists the matching dentists and their details.

   Use Case Ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `search-dentist` command is provided.

      Use case continues from step 2.

    
- **3a. The list of dentists is empty.**
    - ToothTracker displays a message indicating that there are no dentists listed.

      Use Case Ends.


- **3b. No matching dentist found.**
    - ToothTracker displays a message indicating no matching dentists were found with the corresponding `DENTIST_ID`.

      Use Case Ends.


**Use Case: Delete a Dentist**

**MSS**

1. User submits a request to delete a dentist:
    - User specifies the `DENTIST_ID` to delete.

2. ToothTracker searches for the dentist entry with the matching `DENTIST_ID`.

3. ToothTracker deletes the dentist:
    - Dentist entry is removed from the database.

      Use Case Ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `delete-dentist` command is provided.

      Use case continues from step 2.


- **3a. The list of dentists is empty.**
    - ToothTracker displays a message indicating that there are no dentists listed.

      Use Case Ends.

    
- **3b. No matching dentist found.**
    - ToothTracker displays a message indicating no matching dentists were found with the corresponding `DENTIST_ID`.

      Use Case Ends.



**Use Case: Filter Dentist**

**MSS**

1. User submits a request to filter dentist records:
    - User specifies the filter criteria, which requires a valid attribute and a keyword.

2. ToothTracker filters dentist records based on the criteria:

3. ToothTracker displays the filtered results:
    - If one or more matching dentists are found:
        - ToothTracker lists the matching dentists and their details.

   Use Case Ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `filter-dentist` command is provided.

      Use case continues from step 2.


- **1b. User inputs an invalid attribute.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide a valid attribute.
    - Steps within 1b repeat until a valid attribute is provided.

      Use case continues from step 2.


- **3a. The list of dentists is empty.**
    - ToothTracker displays a message indicating that there are no dentists listed.

      Use Case Ends.


- **3b. No matching dentist found.**
    - ToothTracker displays a message indicating no matching dentists were found with the provided keyword for the specified attribute.

      Use Case Ends.


    
**Use case: List Dentist Data**

**MSS**

1. User submits a request to list all dentist data.
2. ToothTracker retrieves the list of all dentist data saved in the system.
3. ToothTracker displays the list of dentist data to the user.

   Use Case Ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `list-dentist` command is provided.

      Use case continues from step 2.


- **2a. No dentist data available.**
    - ToothTracker checks and finds that there are no dentist records in the system.
    - ToothTracker informs the user that no dentist data is available.

      Use case continues from step 2.


**Use case: Add a Patient**

**MSS**

1. User submits a request to create a new patient, and provides information about the patient.
2. ToothTracker acknowledges the request to add a new patient.

Use case ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `add-patient` command is provided.

      Use case continues from step 2.


- **1b. ToothTracker finds a pre-existing patient entry.**
    - ToothTracker alerts the user about the duplicate entry.
    - Steps within 1b loop until a new, unique entry is provided.
      Use case continues from step 2.
     

- **1c. User inputs a treatment that does not exist in ToothTracker**
    - ToothTracker checks ToothTracker and finds that the treatment provided does not exist.
    - ToothTracker alerts the user that the treatment is not provided in the clinic.
    - Steps within 1c loop until an existing treatment is provided.

      Use case resumes from step 2.

    
**Use Case: Search for a Patient**

**MSS**

1. User submits a request to search for a patient:
    - User specifies search criteria, which can be either a `PATIENT_ID` or keywords that matches the name of a patient.

2. ToothTracker searches for a patient based on the criteria:
    - If the request specifies a `PATIENT_ID`:
        - ToothTracker looks for a patient with the matching ID.
    - If the request specifies keywords to match a patient name:
        - ToothTracker searches for a patient with a name that matches the specified keywords.

3. ToothTracker displays the search results:
    - If one or more matching patients are found:
        - ToothTracker lists the matching patients and their details.

   Use Case Ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `search-patient` command is provided.

      Use case continues from step 2.


- **3a. The list of dentists is empty.**
    - ToothTracker displays a message indicating that there are no patients listed.

      Use Case Ends.


- **3b. No matching dentist found.**
    - ToothTracker displays a message indicating no matching patients were found with the corresponding `PATIENT_ID`.

      Use Case Ends.


**Use Case: Delete a Patient**

**MSS**

1. User submits a request to delete a patient:
    - User specifies the `PATIENT_ID` to delete.

2. ToothTracker searches for the patient entry with the matching `PATIENT_ID`.

3. ToothTracker deletes the patient:
    - Patient entry is removed from the database.

      Use Case Ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `delete-patient` command is provided.

      Use case continues from step 2.

- **3a. The list of patients is empty.**
    - ToothTracker displays a message indicating that there are no patients listed.

      Use Case Ends.


- **3b. No matching patient found.**
    - ToothTracker displays a message indicating no matching patients were found with the corresponding `PATIENT_ID`.

      Use Case Ends.


**Use Case: Filter Patient**

**MSS**

1. User submits a request to filter patient records:
    - User specifies the filter criteria, which requires a valid attribute and a keyword.

2. ToothTracker filters patient records based on the criteria:

3. ToothTracker displays the filtered results:
    - If one or more matching patients are found:
        - ToothTracker lists the matching patients and their details.

   Use Case Ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `filter-patient` command is provided.

      Use case continues from step 2.


- **1b. User inputs an invalid attribute.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide a valid attribute.
    - Steps within 1b repeat until a valid attribute is provided.

      Use case continues from step 2.


- **3a. The list of patients is empty.**
    - ToothTracker displays a message indicating that there are no patients listed.

      Use Case Ends.


- **3b. No matching patients found.**
    - ToothTracker displays a message indicating no matching patients were found with the provided keyword for the specified attribute.

      Use Case Ends.


**Use case: List Patient Data**

**MSS**

1. User submits a request to list all patient data.
2. ToothTracker retrieves the list of all patient data saved in the system.
3. ToothTracker displays the list of patients to the user.

   Use case ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `list-patient` command is provided.

      Use case continues from step 2.


- **2a. No patient data available.**
    - ToothTracker checks and finds that there are no patient records in the system.
    - ToothTracker informs the user that no patient data is available.

      Use case continues from step 2.


**Use case: Add an Appointment**

**MSS**

1. User submits a request to add a new future appointment, providing information about the appointment.
 Information includes `DENTIST_ID`, `PATIENT_ID`, appointment start time and treatment provided during the appointment. 
2. ToothTracker acknowledges the request to add the new appointment.

   Use case ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `add-appointment` is provided.

      Use case continues from step 2.


- **1b. User inputs a treatment that does not exist in the database**
    - ToothTracker checks the database and finds that the treatment provided does not exist.
    - ToothTracker alerts the user that the treatment is not provided in the clinic.
    - Steps within 1b loop until an existing treatment is provided.


- **1c. User inputs a dentist or patient ID that does not exist in the database**
    - ToothTracker checks the database and finds that the dentist or patient ID provided does not exist.
    - ToothTracker alerts the user that the patient or dentist with the provided patient or dentist ID does not exist in this clinic.
    - Steps within 1c loop until valid dentist and patient IDs are provided.


- **1d. User inputs an appointment time slot that clashes with an existing one in the database**
    - ToothTracker checks the database and finds that the appointment to be added clashes with an existing one.
    - ToothTracker alerts the user about the clashing appointments.
    - Steps within 1d loop until an appointment time slot that does not clash with an existing appointment is provided.

      Use case resumes at step 1.


**Use Case: Delete an Appointment**

**MSS**

1. User submits a request to delete an appointment:
    - User specifies the `APPOINTMENT_ID` to delete.

2. ToothTracker searches for the appointment entry:
   - ToothTracker looks for an appointment with the matching ID.

3. ToothTracker deletes the appointment:
    - Appointment entry is removed from the database.

      Use Case Ends.

**Extensions**

- **2a. The list is empty.**
    - ToothTracker displays a message indicating no appointments are saved in its system.

      Use Case Ends.


- **3a. No matching appointment found.**
    - ToothTracker displays an error message.

      Use Case resumes at step 2.


- **3b. Invalid `APPOINTMENT_ID` or `APPOINTMENT_ID` not found in ToothTracker.**
    - ToothTracker displays an error message.

      Use Case ends.


**Use case: List Appointment Data**

**MSS**

1. User submits a request to list all appointment data.
2. ToothTracker retrieves the list of all appointment data saved in the system.
3. ToothTracker displays the list of appointments to the user.

   Use case ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `list-appointment` command is provided.

      Use case continues from step 2.


- **2a. No appointment data available.**
    - ToothTracker checks and finds that there are no appointment records in the system.
    - ToothTracker informs the user that no appointment data is available.

      Use case continues from step 2.


**Use Case: Filter Appointments**

**MSS**

1. User submits a request to filter appointments:
    - User specifies filter criteria, which can be either a dentist ID [DENTIST ID] or a patient ID [PATIENT ID].

2. ToothTracker filters appointment list based on the criteria:
    - If the request specifies [DENTIST ID]:
        - ToothTracker filters the appointment list by the specified dentist ID.
    - If the request specifies [PATIENT ID]:
        - ToothTracker filters the appointment list by the specified patient ID.

3. ToothTracker displays the filter results:
   - ToothTracker lists the matching appointments and their details.

   Use Case Ends.

**Extensions**

* 2a. The list of appointment is empty:
    - ToothTracker displays a message indicating that no appointments are saved in its system.

      Use Case Ends.


* 3a. No matching appointments found:
    - ToothTracker displays a message indicating no matching appointments were found.

      Use Case Ends.


* 3b. Invalid appointment ID:
    - ToothTracker displays an error message.

      Use Case Ends.


**Use case: Add Treatment**

**MSS**

1. User submits a request to add a new type of dental treatment, providing information about the treatment name and its
   cost.
2. ToothTracker acknowledges the request to add the new treatment.

   Use case ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `add-treatment` is provided.

      Use case continues from step 2.


- **1b. User inputs incomplete or missing information.**
    - ToothTracker detects that the user did not provide all required information, such as the treatment name or cost.
    - ToothTracker prompts the user to provide both the treatment name and its cost.

      Use case resumes at step 1.


- **1c. User inputs an invalid cost.**
    - ToothTracker checks the provided cost and determines that it is not a valid numeric value.
    - ToothTracker prompts the user to provide a valid numeric value.

      Use case resumes at step 1.


- **1d. Duplicate treatment name.**
    - ToothTracker checks the database and finds that a treatment with the same name.
    - ToothTracker alerts the user about the duplicate entry.
    - Steps within 1d loop until a new, unique treatment name is provided.

      Use case resumes at step 1.


**Use Case: Delete a Treatment**

**MSS**

1. User submits a request to delete a treatment:
    - User specifies the [treatment_name] to delete.

2. ToothTracker searches for the treatment entry.

3. If a match is found, ToothTracker deletes the treatment.
      - Use Case Ends.

**Extensions**

* 2a. No treatments match the given treatment name:
    - ToothTracker displays a message indicating no treatments are available.
    - Repeat step 1 till the user enters a treatment which exists.
    - Use Case Ends.


**Use case: List Treatment Data**

**MSS**

1. User submits a request to list all treatment data.
2. ToothTracker retrieves the list of all treatments saved in the system.
3. ToothTracker displays all available treatments in the command result box.

   Use case ends.

**Extensions**

- **1a. User inputs an invalid command.**
    - ToothTracker identifies the command error.
        - ToothTracker prompts the user to make the necessary adjustments and provide the command in the correct format.
    - Steps within 1a repeat until a valid `list-treatment` command is provided.

      Use case continues from step 2.


- **2a. No treatment data available.**
    - ToothTracker checks and finds that there are no treatments in the system.
    - ToothTracker informs the user that no treatments are available.

      Use case continues from step 2.




### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 Dentists and 1000 Patients without any major performance issues.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.
4. Should not use more than 2GB of RAM
5. Jar file size should not exceed 150MB

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **CLI**: command line interface
* **GUI**: graphical user interface, a visual way of interacting with a computer program
* **PlantUML**: A tool which is used to create diagrams
* **API**: Application Programming Interface
* **Dentist List**: The list of dentists displayed in ToothTracker
* **Patient List**: The list of patients displayed in ToothTracker
* **Appointment List**: The list of appointments displayed in ToothTracker

--------------------------------------------------------------------------------------------------------------------

## Appendix: Instructions for manual testing

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

#### Initial launch

1. Download the jar file and copy into an empty folder
2. Double-click the jar file.<br>
   Expected: Shows the GUI with a set of sample patients and dentists. The window size may not be
   optimum. It is recommended to use ToothTracker at full screen.

#### Saving window preferences

1. Resize the window to an optimum size. Move the window to a different location. Close the window.
2. Re-launch ToothTracker by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.
3. _{ more test cases …​ }_

### Dentist

#### Adding a Dentist

Adding a dentist into ToothTracker's Dentist List.

`add-dentist n/Xander Chua p/98986443 s/Endodontics y/8 `

Expected Output in the Dentist List: New dentist added into the Dentist List. The email and address will contain Default placeholders.

Expected Output in the Command Output Box: New dentist added message is displayed with the dentist credentials.
<br><br>

`add-dentist n/Oliver Lim`

Expected Output in the Dentist List: No new dentist added.

Expected Output in the Command Output Box: Error message for invalid command format.
<br><br>

#### Listing all Dentists

Prerequisite: There is at least 1 Dentist stored in ToothTracker
`list-dentist`

Expected Output in the Dentist List. All Dentists stored in ToothTracker are displayed.

Expected Output in the Command Output Box: Listed all dentists!
<br><br>

#### Editing a Dentist

Prerequisite: There is at least 1 Dentist stored in ToothTracker. 
In this example, we assume there are two dentists with the following attributes:
<br><br>
**Dentist 1**
* Name: `Xavier Tan`
* Phone: `90895772`
* Email: `xaviertan@hotmail.com`
* Address: `Blk 33 #11-132, Serangoon Road, S335291`
* Specialization: `Endodontics`
* Yoe (Years of Experience): `8`
* Dentist ID: `1`
* Tag: `Professional`

**Dentist 2**
* Name: `Bernard Tan`
* Phone: `98983492`
* Email: `bernardtan@hotmail.com`
* Address: `No Address Provided.`
* Specialization: `Orthodontics`
* Yoe (Years of Experience): `2`
* Dentist ID: `4`
* Tag: `Trainee`

<br>
In each of the test case below, we further assume that the state of Dentist objects are always starting from the above attributes.
<br><br>

`edit-dentist 1 n/Xaveric Tan Ming Yuan`

Expected Output in the Dentist List: The name of dentist with ID 1 is changed to `Xaveric Tan Ming Yuan`.

Expected Output in the Command Output Box: Dentist successfully edited message, along with the updated attributes.
<br><br>

`edit-dentist 4 p/98984477 e/btan@yahoo.com`

Expected Output in the Dentist List: The phone of the dentist with ID 4 is changed to `98984477`, and the email is changed to `btan@yahoo.com`.

Expected Output in the Command Output Box: Similar to above.
<br><br>

`edit-dentist 4 h/Blk 653 #03-44, Bishan Ave 4, S622653`

Expected Output in the Dentist List: The address of the dentist with ID 4 is changed to `Blk 653 #03-44, Bishan Ave 4, S622653`.

Expected Output in the Command Output Box: Similar to above.
<br><br>

`edit-dentist 99`

Expected Output in the Dentist List: No dentist is edited.

Expected Output in the Command Output Box: Error details shown for invalid ID provided.
<br><br>

`edit-dentist`

Expected Output in the Dentist List: No dentist is edited.

Expected Output in the Command Output Box: Error details shown for invalid command format.
<br><br>

`edit-dentist 1 n/Bernard Tan`

Expected Output in the Dentist List: No dentist is edited.

Expected Output in the Command Output Box: Error details shown for attempting to edit a dentist into another existing dentist.
<br><br>

#### Searching for a Dentist by Keyword
Prerequisite: There are only two dentists named `Xavier Tan` and `Bernard Tan` stored in ToothTracker.

`search-dentist Xavier`

Expected Output in the Dentist List: `Xavier Tan` dentist is displayed.

Expected Output in the Command Output Box: 1 dentists listed!
<br><br>

`search-dentist Bernard`

Expected Output in the Dentist List: `Bernard Tan` dentist is displayed.

Expected Output in the Command Output Box: 1 dentists listed!
<br><br>

`search-dentist Tan`

Expected Output in the Dentist List: `Xavier Tan` and `Bernard Tan` dentists are displayed.

Expected Output in the Command Output Box: 2 dentists listed!
<br><br>

#### Searching for a dentist by Dentist ID

Prerequisite: There are only two dentists named `Xavier Tan` and `Bernard Tan` stored in ToothTracker.
Xavier Tan's Dentist ID is `1` and Bernard Tan's Dentist ID is 4 (Dentists with ID 2 and 3 are assumed to be removed previously).

`search-dentist 1`

Expected Output in the Dentist List: `Xavier Tan` dentist is displayed.

Expected Output in the Command Output Box: Dentist with dentist ID 1 found.
<br><br>

`search-dentist 4`

Expected Output in the Dentist List: `Bernard Tan` dentist is displayed.

Expected Output in the Command Output Box: 1 dentists listed!
<br><br>

`search-dentist 999`

Expected Output in the Dentist List: No dentist displayed.

Expected Output in the Command Output Box: No dentist found with dentist ID 999.
<br><br>

#### Filtering for dentists with a given attribute

Filter dentists with a given attribute and a corresponding keyword.
The attributes are preceded with `a/` while the keywords are preceded with `k/`.

`filter-dentist a/name k/Tan`

Expected Success Output in Dentist List: dentists who have `Tan` in their full name will be shown. <br> For instance, `Xavier Tan` and `Xaveric Tan` will be shown if they exist in ToothTracker Dentist List.

Expected Success Output in the Command Output Box: Filtered dentists by `ATTRIBUTE` with keyword: `KEYWORD`. <br> For the given example here, it will be "Filtered dentists by name with keyword: Tan."

Expected Failure Output in Dentist List: No dentists will be shown in the list, since the filtered list contains nothing.

Expected Failure Output in Command Out Box: No dentists found with `ATTRIBUTE`: `KEYWORD`. <br> For the given example above, it will be "No dentists found with name: Tan."
<br><br>

`filter-dentist a/specialization k/Orthodontics`

Expected Success Output in Dentist List: dentists who have `Orthodontics` as their Specialization will be shown. <br>

Expected Success Output in the Command Output Box: Filtered dentists by `ATTRIBUTE` with keyword: `KEYWORD`. <br> For the given example here, it will be "Filtered dentists by specialization with keyword: Orthodontics."

Expected Failure Output in Dentist List: No dentists will be shown in the list, since the filtered list contains nothing.

Expected Failure Output in Command Out Box: No dentists found with `ATTRIBUTE`: `KEYWORD`. <br> For the given example above, it will be "No dentists found with email: Orthodontics."
<br><br>

`filter-dentist a/email a/name k/gmail`

Expected Output in Dentist List: No changes. <br>

Expected Output in the Command Output Box: An error message for invalid command format.
<br><br>

`filter-dentist a/invalidAttribute k/randomValues`

Expected Output in Dentist List: No changes.

Expected Output in the Command Output Box: A message saying that an invalid attribute has been given, and lists out the valid attributes for `filter-dentist`.
<br><br>

<div markdown="span" class="alert alert-info">:information_source: **Valid attributes for <code>filter-dentist</code>:** 
<code>name</code> , <code>phone</code>, <code>email</code>, <code>address</code>, <code>specialization</code>, <code>experience</code>, <code>tags</code>.
</div>
<br><br>

#### Deleting a Dentist

Deleting a dentist while all dentists are being shown

Prerequisites: List all dentists using the `list-dentist` command. Multiple dentists may be shown in the dentist list.

`delete-dentist 1`

Expected Output in the Dentist List: Dentist with DENTIST_ID 1 is deleted from the dentist list.

Expected Output in Command Output Box: Details of the deleted dentist shown.
<br><br>

`delete-dentist -1`

Expected Output in the Dentist List: No dentist is deleted.

Expected Output in Command Output Box: Error details shown for invalid ID provided.
<br><br>

Other incorrect delete commands to try:<br>
`delete-dentist`, `delete-dentist x`, `...` <br>(where x is not a valid Dentist ID)

Expected Output in the Dentist List: No dentist is deleted.

Expected Output in Command Output Box:  Error details shown in the Command Output Box to show if it is an Invalid Dentist ID, or if it is an invalid command format.

### Patient

#### Adding a Patient

Adding a patient into ToothTracker's Patient List.

`add-patient n/Tommy Chua Chi Yang p/97793115 b/25-10-1998 g/M`

Expected Output in the Patient List: New patient added into the Dentist List. The email, address, remark and treatment of the patient will contain Default placeholders.

Expected Output in the Command Output Box: New patient added message is displayed with the patient credentials.
<br><br>

`add-patient n/Oliver Lim`

Expected Output in the Patient List: No new patient added.

Expected Output in the Command Output Box: Error message for invalid command format.
<br><br>

#### Listing all Patients

Prerequisite: There is at least 1 Patient stored in ToothTracker
`list-patient`

Expected Output in the Patient List. All Patients stored in ToothTracker are displayed.

Expected Output in the Command Output Box: Listed all patients!
<br><br>

#### Editing a Patient

Prerequisite: There is at least 1 Patient stored in ToothTracker.
In this example, we assume there are two patients with the following attributes:
<br><br>
**Patient 1**
* Name: `Tommy Tan Chuk Yong`
* Phone: `90895772`
* Email: `tommytancy@hotmail.com`
* Address: `Blk 51, Ang Mo Kio Ave 3, S712151`
* Gender: `M`
* Birthday: `18-05-1989`
* Remark: `Cannot see blood.`
* Treatment: `NIL`
* Patient ID: `1`
* Tag: `New`

**Patient 2**
* Name: `Bernard Tan`
* Phone: `93375448`
* Email: `bernardtan@hotmail.com`
* Address: `No Address Provided.`
* Gender: `M`
* Birthday: `19-07-2001`
* Remark: `NIL`
* Treatment: `Braces`
* Patient ID: `4`
* Tag: `Ending soon`

<br>
In each of the test case below, we further assume that the state of Patient objects are always starting from the above attributes.
<br><br>

`edit-patient 1 n/Xaveric Tan Ming Yuan`

Expected Output in the Patient List: The name of patient with ID 1 is changed to `Xaveric Tan Ming Yuan`.

Expected Output in the Command Output Box: Patient successfully edited message, along with the updated attributes.
<br><br>

`edit-patient 4 p/98984477 e/btan@yahoo.com`

Expected Output in the Patient List: The phone of the patient with ID 4 is changed to `98984477`, and the email is changed to `btan@yahoo.com`.

Expected Output in the Command Output Box: Similar to above.
<br><br>

`edit-patient 4 h/Blk 653 #03-44, Bishan Ave 4, S622653`

Expected Output in the Patient List: The address of the patient with ID 4 is changed to `Blk 653 #03-44, Bishan Ave 4, S622653`.

Expected Output in the Command Output Box: Similar to above.
<br><br>

`edit-patient 99`

Expected Output in the Patient List: No patient is edited.

Expected Output in the Command Output Box: Error details shown for invalid ID provided.
<br><br>

`edit-patient`

Expected Output in the Patient List: No patient is edited.

Expected Output in the Command Output Box: Error details shown for invalid command format.
<br><br>

`edit-patient 1 n/Bernard Tan`

Expected Output in the Patient List: No patient is edited.

Expected Output in the Command Output Box: Error details shown for attempting to edit a patient into another existing patient.
<br><br>

<div markdown="span" class="alert alert-info">:information_source: **Special Note for editing treatments:** Please ensure the treatments exists in ToothTracker before editing patients. Otherwise, a message about Invalid Treatment will be given.
</div>
<br><br>

#### Searching for a Patient by Keyword
Prerequisite: There are only two patients named `Tommy Tan Chuk Yong` and `Bernard Tan` stored in ToothTracker.

`search-patient Tommy`

Expected Output in the Patient List: `Tommy Tan Chuk Yong` patient is displayed.

Expected Output in the Command Output Box: 1 patients listed!
<br><br>

`search-patient Bernard`

Expected Output in the Patient List: `Bernard Tan` patient is displayed.

Expected Output in the Command Output Box: 1 patients listed!
<br><br>

`search-patient Tan`

Expected Output in the Patient List: `Tommy Tan Chuk Yong` and `Bernard Tan` patients are displayed.

Expected Output in the Command Output Box: 2 patients listed!
<br><br>

#### Searching for a patient by Patient ID

Prerequisite: There are only two patients named `Tommy Tan Chuk Yong` and `Bernard Tan` stored in ToothTracker.
Tommy Tan Chuk Yong's Patient ID is `1` and Bernard Tan's Patient ID is 4 (Patients with ID 2 and 3 are assumed to be removed previously).

`search-patient 1`

Expected Output in the Patient List: `Tommy Tan Chuk Yong` patient is displayed.

Expected Output in the Command Output Box: Patient with patient ID 1 found.
<br><br>

`search-patient 4`

Expected Output in the Patient List: `Bernard Tan` patient is displayed.

Expected Output in the Command Output Box: 1 patients listed!
<br><br>

`search-patient 999`

Expected Output in the Patient List: No patient displayed.

Expected Output in the Command Output Box: No patient found with patient ID 999.
<br><br>

#### Filtering for patients with a given attribute

Filter patients with a given attribute and a corresponding keyword. 
The attributes are preceded with `a/` while the keywords are preceded with `k/`.

`filter-patient a/name k/Tan`

Expected Success Output in Patient List: patients who have `Tan` in their full name will be shown. <br> For instance, `Tan Ming Yuan` and `Bernard Tan` will be shown if they exist in ToothTracker Patient List.

Expected Success Output in the Command Output Box: Filtered patients by `ATTRIBUTE` with keyword: `KEYWORD`. <br> For the given example here, it will be "Filtered patients by name with keyword: Tan."

Expected Failure Output in Patient List: No patients will be shown in the list, since the filtered list contains nothing.

Expected Failure Output in Command Out Box: No patients found with `ATTRIBUTE`: `KEYWORD`. <br> For the given example above, it will be "No patients found with name: Tan."
<br><br>

`filter-patient a/email k/gmail`

Expected Success Output in Patient List: patients who have `gmail` in their emails will be shown. <br>

Expected Success Output in the Command Output Box: Filtered patients by `ATTRIBUTE` with keyword: `KEYWORD`. <br> For the given example here, it will be "Filtered patients by email with keyword: gmail."

Expected Failure Output in Patient List: No patients will be shown in the list, since the filtered list contains nothing.

Expected Failure Output in Command Out Box: No patients found with `ATTRIBUTE`: `KEYWORD`. <br> For the given example above, it will be "No patients found with email: gmail."
<br><br>

`filter-patient a/email a/name k/gmail`

Expected Output in Patient List: No changes. <br>

Expected Output in the Command Output Box: An error message for invalid command format.
<br><br>

`filter-patient a/invalidAttribute k/randomValues`

Expected Output in Patient List: No changes.

Expected Output in the Command Output Box: A message saying that an invalid attribute has been given, and lists out the valid attributes for `filter-patient`.
<br><br>

<div markdown="span" class="alert alert-info">:information_source: **Valid attributes for `filter-patient`:** 
<code>name</code> , <code>phone</code>, <code>birthday</code>, <code>gender</code>, <code>remark</code>, <code>treatment</code>, <code>email</code>, <code>address</code>, <code>tags</code>.
</div>
<br><br>

#### Deleting a Patient

Deleting a patient while all patients are being shown.

Prerequisites: List all patients using the `list-patient` command. Multiple patients may be shown in the Patient List.

`delete-patient 1`

Expected Output in the Patient List: Patient with PATIENT_ID 1 is deleted from the Patient List.

Expected Output in Command Output Box: Details of the deleted patient shown.
<br><br>

`delete-patient -1`

Expected Output in the Patient List: No patient is deleted.

Expected Output in Command Output Box: Error details shown for invalid ID provided.
<br><br>

Other incorrect delete commands to try:<br>
`delete-patient`, `delete-patient x`, `...` <br>(where x is not a valid Patient ID)

Expected Output in the Patient List: No dentist is deleted.

Expected Output in Command Output Box:  Error details shown in the Command Output Box to show if it is an Invalid Patient ID, or if it is an invalid command format.


### Appointment

#### Adding an Appointment

Prerequisite: There is at least 1 Patient `Tommy Tan Chuk Yong`, at least 1 Dentist `Bernard Tan` and at least 1 Treatment `Braces` stored in ToothTracker.
Tommy Tan Chuk Yong's Patient ID is 1 and Bernard Tan's Dentist ID is 4.

`add-appointment dentist/4 patient/1 start/2023-11-29 16:00 tr/Braces`

Expected Output in the Appointment List: New appointment added into the Appointment List. 


`add-appointment patient/1 start/2023-11-29 16:00 tr/Braces`

Expected Output in the Command Output Box: Error message for invalid command format, prompting users with correct attributes to include.


#### Listing all Appointments

Prerequisite: There is at least 1 Appointment stored in ToothTracker.

`list-appointment`

Expected Output in the Appointment List: All Appointments stored in ToothTracker is displayed.

Expected Output in the Command Output Box: Listed all appointments!


#### Filtering Appointments by Dentist ID

Prerequisite: There is at least one Appointment stored in ToothTracker with Dentist `Bernard Tan`. Bernard Tan's Dentist ID is 4.

`filter-appointment dentist 4`

Expected Output in the Appointment List: Appointments with Dentist `Bernard Tan` is displayed.

Expected Output in the Command Output Box: Appointments with dentist whose dentist ID is 4 listed.

`filter-appointment dentist -1`
Expected Output in the Command Output Box: Error message for invalid ID provided.


#### Filtering Appointments by Patient ID

Prerequisite: There is at least one Appointment stored in ToothTracker with Patient `Tommy Tan Chuk Yong`. Tommy Tan Chuk Yong's Patient ID is 2.

`filter-appointment patient 2`

Expected Output in the Appointment List: Appointments with Patient `Tommy Tan Chuk Yong` is displayed.

Expected Output in the Command Output Box: Appointments with patient whose patient ID is 2 listed.

`filter-appointment patient -1`


Expected Output in the Command Output Box: Error message for invalid ID provided.


#### Deleting an Appointment

Prerequisite: There is at least 1 Appointment stored in ToothTracker.

`delete-appointment 1`

Expected Output in the Appointment List: Appointment with APPOINTMENT_ID 1 is deleted from the appointment list.

Expected Output in the Command Output Box: Details of the deleted appointment shown.

`delete-appointment -1`

Expected Output in the Command Output Box: Error details shown for invalid APPOINTMENT_ID provided.

### Treatment

#### Adding a Treatment

Prerequisite: There is no Treatment, named `Braces`, stored in ToothTracker.

`add-treatment tr/Braces cs/1000 ti/03:00`

Expected Output in the Command Output Box: New treatment added with the details displayed.

`add-treatment tr/Tooth Extraction`

Expected Output in the Command Output Box: Error message for invalid command format, prompting users with correct attributes to include.


#### Listing all Treatments

Prerequisite: There is at least one Treatment stored in ToothTracker.

`list-treatment`

Expected Output in the Command Output Box: Names of all Treatments stored in ToothTracker listed.


#### Deleting a Treatment

Prerequisite: There is at least one Treatment, named `Braces`, stored in ToothTracker.

`delete-treatment Braces`

Expected Output in the Command Output Box: Details of the deleted Treatment shown.

`delete-treatment nasojadsak`

Expected Output in the Command Output Box: Error message for deleting treatment. No Treatment found with name "nasojadsak".


### Calendar 

#### Viewing all appointments

`view-calendar`

Expected Output: The Calendar Window pops out and shows all appointments (if any).

Expected Output in the Command Output Box: Calendar displayed success message.

### Help

`help`

Expected Output: The Help Window pops out and shows a general help message.

Expected Output in the Command Output Box: Opened help window.


### Clear

`clear`

Expected Output in the Patient, Dentist and Appointment List: All Patients, Dentists and Appointments are cleared.

Expected Output in the Command: ToothTracker cleared success message.

### Exit

`exit`

Expected Output: ToothTracker application closes.
