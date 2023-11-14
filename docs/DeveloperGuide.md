---
layout: page
title: Developer Guide
---

## Table of Contents

1. [Acknowledgements](#acknowledgements)
2. [Setting Up](#setting-up)
3. [Design](#design)
    1. [Architecture](#architecture)
    2. [UI Component](#ui-component)
    3. [Logic Component](#logic-component)
    4. [Model Component](#model-component)
    5. [Storage Component](#storage-component)
    6. [Common Classes](#common-classes)
4. [Implementation](#implementation)
    1. [Add Command](#add-command)
    2. [Edit Command](#edit-command)
    3. [Delete Command](#delete-command)
    4. [List Command](#list-command)
    5. [Sort Command](#sort-command)
    6. [Find Command](#find-command)
   7. [Interview and Interview Commands](#interview-and-interview-commands)
5. [Other Relevant Documentation](#other-relevant-documentation)
6. [Appendix A: Requirements](#appendix-a-requirements)
    1. [Product scope](#product-scope)
    2. [User stories](#user-stories)
    3. [Use cases](#use-cases)
    4. [Non-Functional Requirements](#non-functional-requirements)
    5. [Glossary](#glossary)
7. [Appendix B: Instructions for Manual Testing](#appendix-b-instructions-for-manual-testing)
   1. [B.1. Launch and Shutdown](#b1-launch-and-shutdown)
   2. [B.2. View Sample Job Application List](#b2-view-sample-job-application-list)
   3. [B.3. View Job Application List](#b3-view-job-application-list)
   4. [B.4. Add Job Application](#b4-add-job-application)
   5. [B.5. Delete Job Application](#b5-delete-job-application)
   6. [B.6. Edit Job Application](#b6-edit-job-application)
   7. [B.7. Add Interview to Job Application](#b7-add-interview-to-job-application)
   8. [B.8. Delete Interview from Job Application](#b8-delete-interview-from-job-application)
   9. [B.9. Edit Interview from Job Application](#b9-edit-interview-from-job-application)
   10. [B.10. Sort Job Application List](#b10-sort-job-application-list)
   11. [B.11. Find Job Application](#b11-find-job-application)
   12. [B.12. Clear Job Application List](#b12-clear-job-application-list)
   13. [B.13. Exit JobFindr](#b13-exit-jobfindr)
   14. [B.14. Help](#b14-help)
8. [Appendix C: Effort](#appendix-c-effort)
9. [Appendix D: Planned Enhancements](#appendix-d-planned-enhancements)
   1. [D.1. Warning for Clear Command](#d1-warning-for-clear-command)
   2. [D.2. Enhanced Sort Feature](#d2-enhanced-sort-feature)
   3. [D.3. Arrange Interviews in Chronological Order](#d3-arrange-interviews-in-chronological-order)
   4. [D.4. Allow Interviews to have Multiple Types](#d4-allow-interviews-to-have-multiple-types)
   5. [D.5. Enhanced User Customization](#d5-enhanced-user-customization)
   6. [D.6. Improve Keyboard Functionality](#d6-improve-keyboard-functionality)
   7. [D.7. Implement an Integrated Dashboard](#d7-implement-an-integrated-dashboard)

<div style="page-break-after: always;"></div>

## **Acknowledgements**

All code and documentation are either written by ourselves or adopted from the original AB3 implementation.

---

## **Setting Up**

Refer to the guide "[Setting up and getting started](SettingUp.md)".

---

## **Design**

<div markdown="span" class="alert alert-primary">

The `.puml` files used to create diagrams in this document can be found in the [diagrams](diagrams) folder. Refer to the [PlantUML Tutorial](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/developer-guide/ArchitectureDiagram.png" style="display: block; margin: auto;" width="300" />

The architecture diagram above explains the high-level design of the App.

A quick overview of the main components and how they interact with each other is as follows:

#### Main Components

`Main` has two classes: `Main` and `MainApp` which are the entry and exit points of the application.

* At launch: They initialize the components in the correct sequence and connect them together.
* At exit: They ensure the components are shut down properly and invoke cleanup operations if necessary.

`Commons` represents the common classes used by all components.

The other four components of the App are as follows:
* `UI`: Handles the user interface of the application.
* `Logic`: Handles command execution and data manipulation.
* `Model`: Stores the data of the application.
* `Storage`: Handles the saving and loading of data on the hard disk.

#### Component Interactions

The sequence diagram below shows the interactions between components for the `delete 1` command:

<img src="images/developer-guide/ArchitectureSequenceDiagram.png" width="500" />

Each of the four main components:

* Defines its API in an `interface` named after the component.
* Implements its functionality using a `{Component Name}Manager` class, following the corresponding API interface.

For example, the `Logic` component's API is defined in `Logic.java`, and its functionality is implemented in `LogicManager.java`. 

Other components interact with a given component (e.g. `Logic`) by calling methods defined in the corresponding API interface (e.g. `Logic.java`) instead of calling methods directly on the implementation class (e.g. `LogicManager.java`). This is to ensure that the caller does not depend on the implementation details of the component. This is illustrated in the class diagram below:

<img src="images/developer-guide/ComponentManagers.png" style="display: block; margin: auto;" width="300" />

### UI Component

**API:**
[`Ui.java`](https://github.com/AY2324S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/application/ui/Ui.java)


The `UI` component is responsible for managing the user interface of the application so that it can respond according to
the user's actions or commands entered.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view folder`. For example, the layout of
the [`MainWindow`](https://github.com/AY2324S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/application/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-W12-3/tp/blob/master/src/main/resources/view/MainWindow.fxml).

The UI component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.
* Keeps a reference to the `Logic` component, because the UI relies on the Logic to execute commands.
* Depends on some classes in the `Model` component, as it displays `Job` objects residing in the Model.

The following is a class diagram of the `UI` component:

<img src="images/developer-guide/UiClassDiagram.png" style="display: block; margin: auto;" width="900" />

The UI consists of a `MainWindow` that is made up of parts like `CommandBox` and `ResultDisplay`. These parts are always
being shown in `MainWindow`, while other parts like `JobListPanel`, `JobDetailsPanel` are only visible to the user
depending on the state of the application (e.g. when the job list is not empty, or when a job is selected). All these,
including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes
that represent parts of the visible GUI.

### Logic Component

**API:**
[`Logic.java`](https://github.com/AY2324S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/application/logic/Logic.java)

The `Logic` component is responsible for handling the execution of commands so that the application book can be updated
according to the user's instructions.

The following is a partial class diagram of the `Logic` component:

<img src="images/developer-guide/LogicClassDiagram.png" width="600"/>

The following depicts the sequence of interactions within the `Logic` component taking the `execute("delete 1")` API
call as an example:

<img src="images/developer-guide/LogicSequenceDiagram.png" style="display: block; margin: auto;" width="700"/>

The Logic component,

* Reads the user input with the `ApplicationBookParser` class and creates a parser which matches the command
  (e.g. `DeleteCommandParser`) and uses it to parse the command.
* This results in a `Command` object (more precisely, an object of one of its subclasses e.g. `DeleteCommand`) which is
  executed by the `LogicManager`.
* The command can communicate with the `Model` when it is executed (e.g. to delete a person).
* After making changes to the `Model`, the `Storage` is updated to reflect these changes.
* The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The following are other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user
command:

<img src="images/developer-guide/ParserClasses.png" style="display: block; margin: auto;" width="600"/>

For more details about command-specific parsing and execution, refer to "[Implementation](#implementation)".

### Model Component

**API:**
[`Model.java`](https://github.com/AY2324S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/application/model/Model.java)

The `Model` component is reponsible for encapsulating data and providing methods to modify the data.

The `Model` component,
* Stores the application book data i.e., all `Job` objects (which are contained in a `UniqueJobList` object).
* stores the currently 'selected' `Job` objects (e.g., results of a `FindCommand`) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Job>` that can be 'observed' (e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* Stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a
  `ReadOnlyUserPref` object.
* Does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components).

The following is a class diagram of the `Model` component:

<img src="images/developer-guide/ModelClassDiagram.png" style="display: block; margin: auto;" width="450" />


### Storage Component
**API:**
[`Storage.java`](https://github.com/AY2324S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/application/storage/Storage.java)

The `Storage` component is responsible for storing the job application data in JSON format.

The `Storage` component,

* Saves both address book data and user preference data in JSON format, and read them back into corresponding
  objects.
* Inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* Depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`).

The following is a class diagram of the `Storage` component:

<img src="images/developer-guide/StorageClassDiagram.png" style="display: block; margin: auto;" width="600"/>

### Common Classes

Classes used by multiple components are in the `seedu.applicationbook.commons` package.

<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add Command

The `add` command allows the user to add job applications with various attributes. Attributes can be categorized into
* Compulsory attributes such as `Company` and `Role`.
* Optional attributes such as `Status`, `Industry`, `Deadline` and `JobType`


#### Implementation

The following sequence diagrams illustrate the process of parsing and execution for the `add` command:

<img src="images/developer-guide/AddCommandParserSequenceDiagram.png" style="display: block; margin: auto;" width="600" />
<br>
<img src="images/developer-guide/AddCommandSequenceDiagram.png" style="display: block; margin: auto;" width="600" />

`AddCommandParser` takes in a user input, checking for necessary parameters and their validity. A `Job` object is
instantiated during `AddCommandParser::parse` and returned to the `AddCommand`. `AddCommand::execute` adds the new job
application to the `UniqueJobList` in the `ApplicationBook`.

#### Design Considerations

1. **Handling of optional parameters**: 
   * *Chosen implementation*: The `AddCommandParser` checks for the presence of optional parameters. If the
     parameters are not present, default parameters are used to instantiate the `Job` object. This is a simple
     implementation which does not require any changes to the `AddCommand` class, but is not flexible as the default
     parameters are fixed. If the user wants to add a job application with a different set of default parameters, the
     code has to be changed.
   * *Alternative*: If the parameters are not present, a different constructor is used to instantiate the `Job` object.
     This increases flexibility as the user can specify the parameters they want to add, but the implementation is more
     complicated as the `AddCommand` class has to be modified to handle the different constructors.

---

### Edit Command

The edit command allows the user to edit any field in their job application.

#### Implementation

The Edit feature is implemented through the `EditCommand` class along with `EditCommandParser` to parse the arguments
for the command from the user input. It utilises a nested static class, `EditJobDescriptor`, to store the new values for
the job's attributes.

The following sequence diagrams illustrate the process of parsing and execution for the `edit` command:

<img src="images/developer-guide/EditCommandParserSequenceDiagram.png" style="display: block; margin: auto;" width="600"/>
<br>
<img src="images/developer-guide/EditCommandSequenceDiagram.png" style="display: block; margin: auto;" width="600"/>

The `EditCommandParser` class parses the user input and creates an `EditCommand` object with the specified index and an
`EditJobDescriptor` containing the new field values.

The `execute` method in `EditCommand` is then called, which retrieves the current list of jobs and creates a new Job
object `editedJob` with updated field value that is contained by `EditJobDescriptor`. Replaces the old job with the new
one in the job list with `Model::setJob`.

#### Design considerations

1. **How to edit multiple fields with one command**
   * *Chosen implementation*: To enable the `edit` command to parse and edit multiple fields simultaneously, nested a
     static class, `EditJobDescriptor`, within the `EditCommand` itself. This design abstracts the logic of setting new
     values for each field away from the `ParserUtil` and `EditCommandParser` classes. It allows a single instance of
     `EditJobDescriptor` to be carried through the execution sequence, instead of creating a separate instance for each
     field edited (e.g. a new Deadline instance if the deadline is edited).
   * *Alternative*: Editing each attribute with separate commands could simplify the command structure and reduce user
     errors. However, this might lead to increased added complexity in managing multiple instances of edited fields as
     described above.

---

### Delete Command

The delete command allows the user to delete a job application using its index.

#### Implementation

The Delete command is implemented through the `DeleteCommand` class along with `DeleteCommandParser` to parse the
arguments for the command from the user input.

The following sequence diagrams illustrate the process of executing a valid `delete` command:

<img src="images/developer-guide/DeleteCommandParserSequenceDiagram.png" style="display: block; margin: auto;" width="600"/>
<br>
<img src="images/developer-guide/DeleteCommandSequenceDiagram.png" style="display: block; margin: auto;" width="600"/>

The `DeleteCommandParser` class parses the user input and creates a `DeleteCommand` object with the specified index.
If the user input does not conform to the expected format (e.g. the index is out of bounds), a `ParseException` is
thrown.

`DeleteCommand::execute` is then called, which retrieves the current list of jobs and deletes the job at the specified
index in the job list with `Model::deleteJob`.

#### Design considerations

1. **How to delete fields**
   * *Chosen implementation*: Only supports deleting the entire Job Application including all its fields.
   * *Alternative*: To enable the `delete` command to parse and delete specific optional fields of the application at the 
     specified index, allow users to input specifiers (e.g. `delete 1 s/`) and modify `DeleteCommandParser` to parse the 
     specifiers, removing the ones corresponding to the specifiers.
       * *Pros*: More flexibility in changing the fields of an application.
       * *Cons*: Good to have but not a necessary feature as users can simply `edit` the fields and set them to 
    the default (eg. TO_ADD_STATUS).

---

### List Command

The list command allows the user to view the list of all job applications.

#### Implementation

The following sequence diagram illustrates the process of execution for the command:

<img src="images/developer-guide/ListCommandSequenceDiagram.png" style="display: block; margin: auto;" width="600" />

The `ListCommand` class implements this command. It sets the predicate for the `filteredList` of `Model` to
`PREDICATE_SHOW_ALL_JOBS` which evaluates any `Job` to true.

#### Design Considerations

1. **Implementing listing by `sort` order**
    * Lists all applications by the date added if no `sort` command was performed, 
      otherwise lists by the most recent `sort` order. 
      * *Pros*: User is able to maintain the list in the preferred sorted order even after performing other commands such 
      as `delete` and `find`.
      * *Cons*: The sorting order by date added is lost once `sort` by other fields is performed.

---

### Sort Command

The sort command allows the user to sort the current list of job applications based on a specified field.

#### Implementation

The `SortCommand` class implements this command. It accepts a `FieldComparator` which will be set as the comparator when
`Model::sortJobs` is called.

The following sequence diagrams illustrate the process of parsing and execution for the command:

<img src="images/developer-guide/SortCommandParserSequenceDiagram.png"style="display: block; margin: auto;" width="600"/>
<br>
<img src="images/developer-guide/SortCommandSequenceDiagram.png" style="display: block; margin: auto;" width="400"/>



The `SortCommandParser` class uses the `ArgumentTokenizer` class to parse the arguments for the command from the user
input. If the user input does not conform to the expected format, a `ParseException` is thrown. If the user input is
valid, then `SortCommandParser` generates the corresponding `FieldComparator` which will be set as the comparator when
sorting the list.

The `FieldComparator::compare` method compares the field indicated by the specifier. To achieve this, the
relevant field method must be invoked.

* For alphabetically sorted fields (`Company`, `Role`, `Status`, `Industry`, `JobType`), `String::compareToIgnoreCase`
  is used.
* For chronologically sorted fields (`Deadline`), a custom `compareTo()` method is implemented.

#### Design considerations

1. **Implementing sorting in reverse order**: Create a command which, when called, would reverse the order of the list
   sorted.
    * *Pros*: It is relatively simple to implement, as the `Comparator::reversed` method allows the ordering of the list
      to be reversed easily.
    * *Cons*: This is not an important feature which users will need frequently, and it requires additional time to be
      spent on implementing the command and creating test cases. In light of the tight schedule and other more important
      features, we decided that this feature was not a priority.
      <br><br>
2. **Implementing sorting by multiple fields**: When multiple prefixes are provided, sort by the prefix listed first,
   and if two applications have the same value in that field, sort by the next prefix listed.
    * *Pros*: This is slightly more complicated to implement, requiring a `Comparator` which can sort fields according
      to the desired order of importance.
    * *Cons*: Once again, we decided that this is not an important feature, as the primary purpose of the command is to
      allow users to group similar applications together. Grouping by one field should be sufficient for the user to
      have an organised list.

---

### Find Command

The find command allows the user to get a filtered list of job applications.

#### Implementation

The following sequence diagrams illustrate the process of parsing and invocation for the command:

<img src="images/developer-guide/FindCommandParserSequenceDiagram.png" style="display: block; margin: auto;" width="600"/>
<br>
<img src="images/developer-guide/FindCommandSequenceDiagram.png" style="display: block; margin: auto;" width="600"/>

The `FindCommand` class implements this command. Its constructor accepts a `CombinedPredicate` which will be set as the
predicate when `Model::updateFilteredJobList` is called.

The `FindCommandParser` class uses the `ArgumentTokenizer` class to parse the arguments for the command from the user
input. If the user input does not conform to the expected format, a `ParseException` is thrown. If the user input is
valid, then `FindCommandParser` generates the corresponding `FindCommand`.

The `CombinedPredicate` simply represents the logical AND of multiple `Predicate<Job>` objects generated by
`FindCommandParser`. Each can be either:

1. A `FieldContainsKeywordsPredicate` which represents a search
   within a specific field, or
2. Another `Predicate<Job>` which represents a search in any field. This is generated using the
   `FieldContainsKeywordsPredicate::getPreamblePredicate` method.

#### Design considerations

1. **How to combine multiple conditions**
    * *Chosen implementation*: The constructor of a `FindCommand` accepts a `CombinedPredicate`. This predicate is
      contains multiple other `Predicate` objects chained together with `Predicate::and` and `Predicate::or`. This
        assigns the responsibility of handling multiple search conditions solely to the `FindCommandParser` class. The
        `FindCommand` class behaves the same way regardless of the number of conditions specified by the user.
    * *Alternative*: Allow the constructor of `FindCommand` to accept a list of `FieldContainsKeywordsPredicates`, each
      representing a condition specified by the user. This implementation was not adopted as it would involve changing
      the `FindCommand` class to handle multiple conditions. However, this should be handled purely by the
      `FindCommandParser` class as how the conditions are chained depends on the user input.

---

### Interview and Interview Commands

#### Implementation
This feature allows users to add interviews. It is mainly implemented through adding `Interview` to the `Model`
component and implementing several interview commands in the `Logic` component. 

Each `Job` contains a list of interviews. `Interview` objects can be added, edited and deleted accordingly with the
interview sub-commands.

#### Interview
An `Interview` consists of:
* `InterviewType`: There are 9 types of interview types specified by using enumerations
* `InterviewDateTime`: Uses `LocalDateTime` and `DateTimeFormatter` to store the date and time of interview.
* `InterviewAddress`

The following class diagram illustrates the structure of an `Interview`:

<img src="images/developer-guide/InterviewClassDiagram.png" style="display: block; margin: auto;" width="600"/>

#### Interview Commands
The Interview commands are implemented with `InterviewCommand` and `InterviewCommandParser`.

During parsing of user input in `ApplicationBookParser`, if the input starts with "interview", the remaining input
is passed as an argument to the `InterviewCommandParser` which parses it and invokes the respective sub-command parsers.

The abstract `InterviewCommand` class extends the `Command` class to hide the internal logic
and execution of the interview sub-commands.

The `InterviewCommand::getJob` method retrieves the job of an interview so that the execution of the sub commands 
can be carried out on the `Job` that contains the `Interview` to be modified.

There are 3 sub-commands to access and modify an `Interview`:
* `interview add` - To add an interview to a `Job`.
* `interview delete` - To delete an interview from a `Job`.
* `interview edit`- To edit an interview from a `Job`.

The following class diagram illustrates the structure of an `InterviewCommand` and the sub-commands it is associated
with:

<img src="images/developer-guide/InterviewCommandClassDiagram.png" style="display: block; margin: auto;" width="600"/>

#### Interview Add Command
The adding of an interview to a `Job` is implemented with `InterviewAddCommand` and `InterviewAddCommandParser`.

The following sequence diagrams illustrate the process of parsing and execution for the `interview add` command:

<img src="images/developer-guide/InterviewAddCommandParserSequenceDiagram.png" style="display: block; margin: auto;" width="600" />
<br>
<img src="images/developer-guide/InterviewAddCommandSequenceDiagram.png" style="display: block; margin: auto;" width="600" />

When the `InterviewAddCommandParser::parse` is invoked by `InterviewCommandParser`, the `ArgumentTokenizer` class parses
the arguments to determine the index of the `Job`, `interviewType`, `interviewDateTime` and `interviewAddress`. 

If the user input does not conform to the expected prefixes, a `ParseException` is thrown. If the user input is valid,
a new `Interview` is created with the `interviewType`,`interviewDateTime` and `interviewAddress` parsed. An
`InterviewAddCommand` is generated with the job `index` and the created `Interview`.

During execution of `InterviewAddCommand`, the new `Interview` is passed to the `Job` to handle the adding of the 
`Interview` to it's list of interviews.

#### Interview Delete Command
Deleting of an interview from a specified `Job` is implemented with `InterviewDeleteCommand` and 
`InterviewDeleteCommandParser`.

The following sequence diagrams illustrate the process of parsing and invocation for the command:
<img src="images/developer-guide/InterviewDeleteCommandParserSequenceDiagram.png" style="display: block; margin: auto;" width="600"/>
<br>
<img src="images/developer-guide/InterviewDeleteCommandSequenceDiagram.png" style="display: block; margin: auto;" width="600"/>

When the `InterviewDeleteCommandParser::parse` is invoked by `InterviewCommandParser`, the `ArgumentTokenizer` class
parses the arguments to determine the index of the `Interview` to be deleted and the index of the `Job` it 
is to be deleted from.

If the user input does not conform to the expected prefixes, a `ParseException` is thrown. If the user input is valid,
an `InterviewDeleteCommand` is generated with the `jobIndex` and `interviewindex`.

During execution of `InterviewDeleteCommand`, the `Job` and `Interview` objects are passed to the `Model` to handle the
deletion of the `Interview` from the `Job`.

#### Interview Edit Command
Editing of an interview from a specified `Job` is implemented with `InterviewEditCommand` and 
`InterviewEditCommandParser`.

The following sequence diagrams illustrate the process of parsing and invocation for the command:
<img src="images/developer-guide/InterviewEditCommandParserSequenceDiagram.png" width="700"/>
<br>
<img src="images/developer-guide/InterviewEditCommandSequenceDiagram.png" width="500"/>

When the `InterviewEditommandParser::parse` is invoked by `InterviewCommandParser`, the `ArgumentTokenizer` class
parses the arguments to determine the index of the `Interview` to be edited, index of the `Job` it
is to be edited from and the fields to be edited.

If the user input does not conform to the expected prefixes, a `ParseException` is thrown. If the user input is valid,
an `EditInterviewDescriptor` is created to store the details to edit the interview with. An `InterviewEditCommand` is
then generated with the `jobIndex`, `interviewindex` and the `editInterviewDescriptor`.

During execution of `InterviewEditCommand`, the `interviewToBeEdited` and `editedInterview` created is passed to the
`Job` to handle the modification of the `Interview`.

#### Design considerations
1. **How to implement multiple interviews in for a Job Application**
    * *Chosen implementation*: Each `Job` stores a list of interviews as `List<Interviews>`. This was easier to
      implement, but had less abstraction. Due to the tight timeline as interviews was a feature implemented only in
      v1.3, we decided to choose this implementation.
    * *Alternative*: Create an `InterviewList` class to store the list of interviews and handle the changes to the
      interviews. Each `Job` would have an `InterviewList` instead. This provided a higher level of abstraction,
      enabling us to easily add more commands to manipulate the list of interviews in the future. However, this would
      take more time. Additionally, there are currently minimal commands to manage the interviews and adding an extra 
      layer may add unnecessary complication to the codebase.

<div style="page-break-after: always;"></div>

## **Other Relevant Documentation**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

---

## **Appendix A: Requirements**

### Product scope

**Target user profile**:

* NUS fresh graduates who are looking for jobs
* Has a need to manage a significant number of job applications
* Prefers desktop apps over other types
* Able to type fast
* Prefers typing to mouse interactions
* Reasonably comfortable using CLI apps

**Value proposition**: Manage job applications faster than a typical mouse/GUI-driven app.

---

### User stories

Priorities: High (must have) - `***`, Medium (nice to have) - `**`, Low (unlikely to have) - `*`

| Priority | As a … | I want to …                                                                       | So that I can…                                   |
|----------|--------|-----------------------------------------------------------------------------------|--------------------------------------------------|
| `***`    | user   | add applications I have applied to, including their names and contact information | keep track and manage my job applications        |
| `***`    | user   | record application deadlines for each job listing                                 | stay organized on application deadlines          |
| `***`    | user   | mark the status of each application                                               | keep track of application progress               |
| `***`    | user   | delete job applications that are no longer relevant                               | update my list according to my current interests |
| `***`    | user   | view all applications I have added                                                |                                                  |

---

### Use cases

For all use cases below, the **System** is the `JobFindr` (JobFindr) and the **Actor** is the `User`, unless specified
otherwise. Furthermore, any reference to the `list` refers to the main list of job applications, unless specified
otherwise.

**System:** JobFindr

**Actor:** User

**Application:** Job Application

#### Use case: UC1 - Add an application

**MSS**

1. User requests to add a job application to the list.
2. System adds the application.

   Use case ends.

**Extensions**

* 1a. System detects an error in the input.
    * 1a1. System shows an error message.

      Use case resumes at step 1.

#### Use case: UC2 - Delete an application

**MSS**

1. User requests to delete an application in the list, specifying an index.
2. System deletes the application at the index specified.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.
    * 3a1. System shows an error message.

      Use case resumes at step 3.

#### Use case: UC3 - List applications

**MSS**

1. User requests to list applications
2. System shows a list of applications

   Use case ends.

#### Use case: UC4 - Find an application

**MSS**

1. User lists application UC3.
2. User requests to find a list of applications based on the given keywords.
3. System shows a list of applications with any of the
   fields (`Company`, `Role`, `Status`, `Deadline`, `JobType`, `Industry`) containing the given keywords.

   Use case ends.

**Extensions**

* 2a. No applications match the given keywords.

  Use case ends.

#### Use case: UC5 - Find an application by specific field(s)

**MSS**

1. User lists application UC3.
2. User requests to find a list of applications based on the given keywords for specific field(s).
3. System shows a list of applications with the given field(s) containing the given keywords for each field.

   Use case ends.

**Extensions**

* 2a. No applications match the given keywords.

  Use case ends.

#### Use case: UC6 - Edit an application

**MSS**

1. User finds application UC4.
2. User requests to edit an application in the list, specifying an index.
3. System updates the relevant fields in the application at the index specified.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.
    * 3a1. System shows an error message.

      Use case resumes at step 2.

#### Use case: UC7 - Sort applications

**MSS**

1. User requests to sort the list of applications by a specific field.
2. System sorts the list of applications by the given field.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given field is invalid.
    * 2a1. System shows an error message.

      Use case resumes at step 1.

#### Use case: UC8 - Select an application

**MSS**

1. User requests to select an application in the list by clicking on it.
2. System displays details of the selected application.

   Use case ends.

#### Use case: UC9 - Add interview

**MSS**

1. User finds application UC4.
2. User requests to add an interview to the application, specifying an index.
3. System creates an Interview object.
4. System adds the interview to the application at the index specified.

   Use case ends.

**Extensions**

* 1a. The application does not exist.
    * 1a1. System shows an error message.

      Use case resumes at step 2.
* 2a. The given index is invalid.
    * 2a1. System shows an error message.

      Use case resumes at step 2.
* 2b. The interview already exists.
    * 2b1. System shows an error message.

      Use case resumes at step 2.
* 2c. The interview has invalid fields.
    * 2c1. System shows an error message.

      Use case resumes at step 2.

#### Use case: UC10 - Delete interview

**MSS**

1. User requests to delete an interview from the application, specifying the interview index and the application index.
2. System deletes the interview from the application at the index specified.

   Use case ends.

**Extensions**

* 1a. The application does not exist.
    * 1a1. System shows an error message.

      Use case resumes at step 2.
* 1b. The interview to be deleted does not exist.
    * 1b1. System shows an error message.

      Use case resumes at step 2.

#### Use case: UC11 - Edit interview

**MSS**

1. User requests to edit an interview from the application, specifying the interview index and the application index.
2. System updates the relevant fields in the interview at the index specified.

   Use case ends.

**Extensions**

* 1a. The application does not exist.
    * 1a1. System shows an error message.

      Use case resumes at step 2.
* 1b. The interview to be edited does not exist.
    * 1b1. System shows an error message.

      Use case resumes at step 2.
* 1c. The interview to be created has invalid fields.
    * 1c1. System shows an error message.

      Use case resumes at step 2.

#### Use case: UC12 - Clear all applications

**MSS**

1. User requests to clear all applications.
2. System clears all applications.

   Use case ends.

#### Use case: UC13 - Exit application

**MSS**

1. User requests to exit the application.
2. System exits the application.

   Use case ends.

#### Use case: UC14 - Open help window

**MSS**

1. User requests to open the help window.
2. System opens the help window.

   Use case ends.

---

### Non-Functional Requirements

**Availability**

- The application is available for download on our GitHub release page in the form of a JAR file.

**Capacity**

- The application should be able to store up to 1000 job applications.

**Performance**

- Response time to any user command is within 3 seconds.
- The application should be able to contain and handle up to 300 job applications before facing any form of performance
  bottleneck issues.

**Reliability**

- The application should guide the user if it is unable to execute any of the user actions for various reasons.

**Compatibility**

- The application should work as intended on any mainstream operating systems.
- The application is guaranteed to work on Java version 11.

**Usability**

- A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
  able to accomplish most of the tasks faster using commands than using the mouse.

**Robustness**

- The application should remain highly relevant to job applications at any point in the future.

**Integrity**

- There should be user updates to the job applications to ensure its integrity.
- When there is an application update, it should not compromise the integrity of the save file.

**Maintainability**

- The application should be compliant with the coding standard and best coding practices highlighted in CS2103T.
- The application should be designed such that any programmer with at least a year of experience should be able to read,
  maintain, and contribute to the source code easily.

**Process**

- The project features are to be in line with any changes to real world job application process.

**Project Scope**

- The application requires manual addition of job applications into the system.

**Privacy**

- The application should not store any information related to the user's job applications in remote storage.

---

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **CLI**: Command Line Interface. A user interface that allows users to interact with the software using text commands via a console or terminal.
* **GUI**: Graphical User Interface. A user interface that allows users to interact with the software through graphical icons and visual indicators, as opposed to text-based interfaces.
* **UI**: User Interface
* **MSS**: Main Success Scenario
* **Job Application**: A record of a job application that contains relevant information.
* **Fields**: The attributes of a job application, namely company, role, status, deadline, job type, industry, and
  interview details.

<div style="page-break-after: always;"></div>

## **Appendix B: Instructions for Manual Testing**

Given below are instructions to test the app manually.

> Note: These instructions only provide a starting point for testers to work on; testers are expected to do more
*exploratory* testing.

### B.1. Launch and Shutdown

1. Initial launch
    1. Download the jar file and copy into an empty folder
    2. Double-click the jar file

       Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.


2. Saving window preferences
    1. Resize the window to an optimum size. Move the window to a different location. Close the window.
    2. Re-launch the app by double-clicking the jar file.

       Expected: The most recent window size and location is retained.

### B.2. View Sample Job Application List

1. Close JobFindr.
2. Delete the file `./ApplicationBook.json` in the data folder (if applicable).
3. Launch JobFindr.

   Expected: A sample job application list with 7 job applications is loaded.

### B.3. View Job Application List

1. Viewing the list of job application(s).
    1. Test case: `list`

       Expected: The list of job applications is shown in the GUI.

### B.4. Add Job Application

1. Adding a job application.
    1. Test case: `add c/Microsoft r/Project Manager d/Dec 31 2023 1400 i/Technology s/Pending t/FULL_TIME`

       Expected: New job application is added to the bottom of the list. Details of the newly-added application is shown
       in the feedback box.

    2. Test case: `add r/Data Analyst`

       Expected: Job application is not added. Error details are shown in feedback box.

### B.5. Delete Job Application

1. Deleting a job application by index.
    1. Prerequisites: At least one job application is displayed.
        1. Test case: `delete 1`

           Expected: The first job application is deleted from the list. Details of the deleted application is shown in
           the feedback box.
        2. Test case: `delete 0`

           Expected: No job application is deleted. Error details are shown in feedback box.
        3. Other test cases: `delete`, `delete x` (where x is larger than the list size)

           Expected: Similar to test case 2.

### B.6. Edit Job Application

1. Editing a job application by index.
    1. Prerequisites: At least one job application is displayed.
        1. Test case: `edit 1 c/Google r/Software Engineer`

           Expected: The first job application is edited. Details of the edited application is shown in the feedback
           box.
        2. Test case: `edit 0 c/Google r/Software Engineer`

           Expected: No job application is edited. Error details are shown in feedback box.
        3. Test case: `edit c/Google r/Software Engineer`

           Expected: Similar to test case 2.
        4. Test case: `edit 1 d/Nov 31 2023 1400`

           Expected: Invalid date format. Error details are shown in feedback box.
        5. Test case: `edit 1 s/`

           Expected: Invalid status format. Error details are shown in feedback box.
        6. Test case: `edit 1 q/`

           Expected: Invalid prefix. Error details are shown in feedback box.'

### B.7. Add Interview to Job Application

1. Adding an interview to a job application by index.
    1. Prerequisites: List all job applications using `list`.
        1. Test case: `interview add 1 t/Technical a/Changi Business Park Central 1, 486036 d/Nov 30 2023 1400`

           Expected: Interview is added to the job application. Details of the interview is shown in the job details
           panel.
        2. Test case: `interview add 0 t/Technical a/Changi Business Park Central 1, 486036 d/Nov 30 2023 1400`

           Expected: No interview is added. Error details are shown in feedback box.
        3. Test case: `interview add 1 t/Technical d/Nov 30 2023 1400`

           Expected: No interview is added. All fields are compulsory. Error details are shown in feedback box.

### B.8. Delete Interview from Job Application

1. Deleting an interview from a job application by index.
    1. Prerequisites: List all job applications using `list`.
        1. Test case: `interview delete 1 from/1`

           Expected: Interview is deleted from the job application. Details of the interview is removed from the job
           details panel.
        2. Test case: `interview delete 0 from/1`

           Expected: No interview is deleted. Error details are shown in feedback box.
        3. Test case: `interview delete 1 from/0`

           Expected: Similar error to test case 2.

### B.9. Edit Interview from Job Application

1. Editing an interview from a job application by index.
    1. Prerequisites: List all job applications using `list`.
        1. Test case: `interview edit 1 from/1 t/Technical a/Changi Business Park Central 1, 486036 d/Nov 30 2023 1200`

           Expected: Interview is edited. Details of the interview is updated in the job details panel.
        2. Test case: `interview edit 0 from/1 t/Technical a/Changi Business Park Central 1, 486036 d/Nov 30 2023 1400`

           Expected: No interview is edited. Error details are shown in feedback box.
        3. Test case: `interview edit 1 from/0 t/Technical a/Changi Business Park Central 1, 486036 d/Nov 30 2023 1400`

           Expected: Similar error to test case 2.
        4. Test case: `interview edit 1 from/1 t/Technical d/Nov 30 2023 1400`

           Expected: Interview is edited. Details of the interview is updated in the job details panel.
        5. Test case: `interview edit 1 from/1 t/Home`

           Expected: No interview is edited. Invalid interview type. Error details are shown in feedback box.

### B.10. Sort Job Application List

1. Sorting the list of job application(s).
    1. Prerequisites: At least one job application is displayed.
        1. Test case: `sort c/`

           Expected: The list of job applications is sorted by company name.
        2. Test case: `sort r/`

           Expected: The list of job applications is sorted by role.
        3. Test case: `sort s/`

           Expected: The list of job applications is sorted by status.
        4. Test case: `sort d/`

           Expected: The list of job applications is sorted by deadline.
        5. Test case: `sort t/`

           Expected: The list of job applications is sorted by job type.
        6. Test case: `sort i/`

           Expected: The list of job applications is sorted by industry.
        7. Test case: `sort x/`

           Expected: No sorting is done. Error details are shown in feedback box.
    2. Prerequisites: Use `find` command to reduce size of job application list without deleting any job applications.
       For example, `find r/program`.
        1. Test case: `sort c/`

           Expected: No change to the number of job applications displayed.
    3. Prerequisites: Ensure current job applications list has multiple applications with fields of the same value. For
       example, multiple applications with the `role` of `software developer`.
        1. Test case: `sort r/`

           Expected: No change in order of job applications with identical roles.

### B.11. Find Job Application

1. Finding a job application by keywords.
    1. Prerequisites: Starting with the sample job application list. Refer to B.2. for steps to load the sample job
       application list.
        1. Test case: `find`

           Expected: No change in list. Error details shown in the feedback box as at least one of the optional
           parameters must be entered.
        2. Test case: `find full_time`

           Expected: Applications with the keyword `full_time` in any field are shown in the list.
        3. Test case: `find s/Pending`

           Expected: All applications with `PENDING` status are shown in the list.
        4. Test case: `find Dec`

           Expected: All applications with `Dec` in the deadline are shown in the list.
        5. Test case: `find Shop`

           Expected: No application is listed. `find` does not work with partial keywords.

### B.12. Clear Job Application List

1. Clearing the list of job application(s).
    1. Prerequisites: At least one job application is displayed.
        1. Test case: `clear`

           Expected: The list of job applications is cleared. The list is empty.

### B.13. Exit JobFindr

1. Exiting the application.
    1. Test case: `exit`

       Expected: The application window closes.

### B.14. Help

1. Opening the help window.
    1. Test case: `help`

       Expected: The help window opens.

<div style="page-break-after: always;"></div>

## **Appendix C: Effort**

### Working with JavaFX

JavaFX is a GUI library that was briefly taught as part of our individual project. While the tutorial was helpful in giving us a basic understanding of JavaFX, we still had to spend a significant amount of time learning how to use JavaFX to implement the GUI for JobFindr. This was especially challenging as we had to learn how to use JavaFX while implementing the GUI for JobFindr at the same time. Challenges include:
* **Learning how to use SceneBuilder:** SceneBuilder was introduced as a useful framework to expedite the process of using JavaFX to implement the GUI. However, SceneBuilder proved to be difficult to use as it was not intuitive and lacks the flexibility needed to implement certain features that we wanted to include. For example, we wanted to include a `JobDetailsPanel` to display the fields and interviews of a job application. However, SceneBuilder lacked the necessary tools to allow us to customize the `JobDetailsPanel` to our liking. We ended up having to implement the `JobDetailsPanel` manually using JavaFX.
* **Learning how to use CSS:** We wanted to use CSS to customize the look and feel of the GUI. However, we had no prior experience with CSS and had to spend a significant amount of time learning how to use CSS to customize the GUI. We also had to learn how to use CSS with JavaFX as there were some differences between using CSS with JavaFX and using CSS with HTML.
* **Overall Steep Learning Curve:** There are many intricacies within JavaFX that were not extensively covered in the tutorials provided. We had to do a lot of research to figure out how to implement JavaFX features. For example, we had to modify the `CommandResult` class to support updating the `JobDetailsPanel` whenever an interview is added, edited or deleted. 

<div style="page-break-after: always;"></div>

## **Appendix D: Planned Enhancements**

The current implementation of JobFindr allows users to manage their job applications relatively efficiently through a CLI. However, we have identified several areas for improvement in terms of flexibility, efficiency and organization. As such, our proposed enhancements are targeted at improving these areas.

### D.1. Warning for Clear Command
   
#### Current State
The `clear` command executes without any prior warning, risking accidental deletion of all job applications without warning.

#### Planned Enhancement
We plan to introduce a confirmation step before the execution of the `clear` command.

#### Implementation Details
* **Confirmation Prompt:** Introduce an interactive prompt requiring explicit user confirmation before executing the `clear` command.
* **Command-Line Argument:** Optionally, provide a command-line argument to bypass the confirmation for automated scripts.

### D.2. Enhanced Sort Feature

#### Current State

The `sort` command places all the empty optional fields at the end of the job application field after sorting based on an optional field. For example, if the user sorts the job application list by `status`, all the job applications with no status (i.e. `TO_ADD_STATUS`) will be placed at the end of the list. This is not ideal as the user may want to view all the job applications with no status at the top of the list.

#### Planned Enhancement

We plan to make `sort` show all the job applications with empty optional fields at the top of the list after sorting based on an optional field.

#### Implementation Details

* **Sorting Algorithm:** Modify the sorting algorithm to place all the job applications with empty optional fields at the top of the list after sorting based on an optional field.
* **Updated User Interface:** Update the user interface to show all the job applications with empty optional fields at the top of the list after sorting based on an optional field.
* **Performance Considerations:** The sorting algorithm should be efficient enough to handle a large number of job applications.

### D.3. Arrange Interviews in Chronological Order

#### Current State

The `interview` command arranges interviews based on the order they are added. This may lead to confusion as the user is unable to view the interviews in chronological order.

#### Planned Enhancement

We plan to arrange interviews in chronological order for each job application.

#### Implementation Details

* **Add Field Comparator:** Add a field comparator to sort the interviews in chronological order, much like how `deadline` is sorted.

### D.4. Allow Interviews to have Multiple Types

#### Current State

The `interview` command only allows users to add one type of interview to each job application. For example, an interview can be labelled as `Technical` or `Online`, but not both. This is not flexible enough as it is common for interviews to have multiple types associated with them.

#### Planned Enhancement

We plan to allow users to add multiple types to each interview. An interview can have as many types as the user wants as long as they are valid.

#### Implementation Details

* **Modify Interview Type Field:** Modify the interview type field to be a `Set` instead of a `String`. This allows users to add multiple types to each interview.
* **Update User Interface:** Update the user interface to support displaying the multiple interview types as a `String`.

### D.5. Enhanced User Customization

#### Current State

The`add` command only allows users to add fields that are already defined in the `Job` class. This is not flexible enough as users may want to add their own fields to the job application. Moreover, users are unable to choose which fields are shown in the job application list.

#### Planned Enhancement

We plan to allow users to add their own fields to the job application and choose which fields are shown in the job application list.

#### Implementation Details

* **Add Custom Fields:** Allow users to add their own fields to the job application.
* **Choose Fields to Show:** Add a toggle that allows users to choose which fields are shown in the job application list.

### D.6. Improve Keyboard Functionality

#### Current State

The `JobFindr` application currently does not support keyboard shortcuts. This is not ideal as experienced users may want to execute commands using keyboard shortcuts for faster execution.

#### Planned Enhancement

We plan to allow users to use keyboard shortcuts to execute commands for faster execution.

#### Implementation Details

* **Add Keyboard Shortcuts:** Add specific listeners to the `JobFindr` application to listen for keyboard shortcuts. When a keyboard shortcut is detected, the corresponding command is executed.
* **Update User Interface:** Update the user interface to support keyboard shortcuts.

### D.7. Implement an Integrated Dashboard

#### Current State

The `JobFindr` application currently only displays a list of job applications. This is not ideal as users may want to view their job applications in a more organized manner. For example, users may want to view their job applications categorized by a specified field.

#### Planned Enhancement

We plan to implement an integrated dashboard that allows users to view their job applications in a more organized manner.

#### Implementation Details

* **Update User Interface:** Update the user interface to support the integrated dashboard. This involves adding panels to display multiple lists of job applications categorized by a specified field.
* **Modify `Sort` command:** Modify the `sort` command to support sorting by multiple fields. This allows users to sort the job applications in the integrated dashboard by multiple fields.
