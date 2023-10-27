---
layout: page
title: Developer Guide
---

# Developer Guide

Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

### UI Component

**API:**
[`Ui.java`](https://github.com/AY2324S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/application/ui/Ui.java)

**Description:**

The `UI` component is responsible for managing the user interface of the application so that it can respond according to
the user's actions or commands entered.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view folder`. For example, the layout of
the [`MainWindow`](https://github.com/AY2324S1-CS2103T-W12-3/tp/blob/master/src/main/java/seedu/application/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-W12-3/tp/blob/master/src/main/resources/view/MainWindow.fxml).

**Functionality:**

The UI component,

- executes user commands using the `Logic` component.
- listens for changes to `Model` data so that the UI can be updated with the modified data.
- keeps a reference to the `Logic` component, because the UI relies on the Logic to execute commands.
- depends on some classes in the `Model` component, as it displays Person object residing in the Model.

**Component Structure:**

<img src="images/UiClassDiagram.png" width="900" />

The UI consists of a `MainWindow` that is made up of parts like `CommandBox` and `ResultDisplay`.
These parts are always being shown in `MainWindow`, while other parts like `JobListPanel`, `JobDetailsPanel` are only
visible
to the user depending on the state of the application e.g. when the job list if not empty, when a job is selected.

etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities
between classes that represent parts of the visible GUI.


--------------------------------------------------------------------------------------------------------------------

## **Implementation**
This section describes some noteworthy details on how certain features are implemented.

### Add Command

The add command allows the user to add job applications with various attributes.

Compulsory attributes are `Company` and `Role`.

Optional attributes are `Status`, `Industry`, `Deadline` and `JobType`

#### Implementation

It is implemented by `AddCommand` and `AddCommandParser`.

`AddCommandParser` parses the users' input and checks for the presence of compulsory and optional prefixes. 
The information is then used to create a new Job Application through the `AddCommand`.

(insert UML diagram here)

The following sequence/activity diagram illustrates the process of invocation for the AddCommand:

(insert UML diagram here)

#### Design Considerations

--------------------------------------------------------------------------------------------------------------------

### Edit Command

The edit command allows the user to edit any field in their job application.

#### Implementation
It is implemented by `EditCommand` and `EditCommandParser`.

`EditCommandParser` parses the users' input and checks for the valid prefixes to determine the field the user wants to change.
The information is then used to create a new Job Application with the updated fields while retaining the unchanged fields through the `EditCommand`.

The following Class Diagram of EditCommand illustrates the interactions between EditCommand and other classes:

(insert UML diagram here)

The following sequence diagram illustrates the process of invocation for the EditCommand:

(insert UML diagram here)

#### Design Considerations

--------------------------------------------------------------------------------------------------------------------
### Delete Command

The delete command allows the user to delete a job application using its index.

#### Implementation

The following sequence diagram illustrates the process of invocation for the command:

(insert UML sequence diagram)

The `DeleteCommand` class implements this command. It accepts an index and deletes the job application at the specified index.

The `DeleteCommandParser` class is used to parse the arguments for the command from the user input. If the user input does
not conform to the expected format e.g. the index is out of bounds, a `ParseException` is thrown. If the user input is valid,
then `DeleteCommandParser` returns the corresponding `DeleteCommand`.

#### Design considerations

--------------------------------------------------------------------------------------------------------------------

### List Command

The list command allows the user to view the list of all job applications.

#### Implementation

The following sequence diagram illustrates the process of invocation for the command:

(insert UML diagram here)

The `ListCommand` class implements this command. It sets the predicate for the `filteredList` of `Model` to
`PREDICATE_SHOW_ALL_JOBS` which evaluates any `Job` to true.

#### Design Considerations

--------------------------------------------------------------------------------------------------------------------

### Sort Command

The sort command allows the user to sort the current list of job applications based on a specified field.

#### Implementation

The following sequence diagram illustrates the process of invocation for the command:

(insert UML sequence diagram)

The `SortCommand` class implements this command. It accepts a `FieldComparator` which will be set as the comparator when
`Model::sortJobs` is called.

The `SortCommandParser` class is used to parse the arguments for the command from the user input. If the user input does
not conform to the expected format, a `ParseException` is thrown. If the user input is valid, then `SortCommandParser`
generates the corresponding `FieldComparator` which will be set as the comparator when sorting the list.

The overriding `FieldComparator::compare` method compares the field indicated by the specifier. To achieve this, the
relevant field method must be invoked.

* For alphabetically sorted fields (`Company`, `Role`, `Status`, `Industry`, `JobType`), `String::compareToIgnoreCase`
  is used.
* For chronologically sorted fields (`Deadline`), a custom `compareTo()` method is implemented.

#### Design considerations

--------------------------------------------------------------------------------------------------------------------

### FInd Command

The find command allows the user to get a filtered list of job applications.

#### Implementation

The following sequence diagram illustrates the process of invocation for the command:

(insert UML sequence diagram)

The `FindCommand` class implements this command. 
It accepts a `FieldContainsKeywordsPredicate` which will be set as the predicate when `Model::updateFilteredJobList` is called.

The `FindCommandParser` class is used to parse the arguments for the command from the user input. If the user input does
not conform to the expected format, a `ParseException` is thrown. If the user input is valid, then `FindCommandParser`
generates the corresponding `FindCommand` with a `FieldContainsKeywordsPredicate` which will be set as the predicate when filtering through the list.

#### Design considerations

--------------------------------------------------------------------------------------------------------------------
### Clear Command

The clear command allows the user to delete all job applications.

#### Implementation

The following sequence diagram illustrates the process of invocation for the command:

(insert UML sequence diagram)

The `ClearCommand` class implements this command.

#### Design considerations

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* NUS fresh graduates who are looking for jobs
* Has a need to manage a significant number of job applications
* Prefers desktop apps over other types
* Able to type fast
* Prefers typing to mouse interactions
* Reasonably comfortable using CLI apps

**Value proposition**: Manage job applications faster than a typical mouse/GUI-driven app.

### User stories

Priorities: High (must have) - `***`, Medium (nice to have) - `**`, Low (unlikely to have) - `*`

| Priority | As a … | I want to …                                                                       | So that I can…                                   |
|----------|--------|-----------------------------------------------------------------------------------|--------------------------------------------------|
| `***`    | user   | add applications I have applied to, including their names and contact information | keep track and manage my job applications        |
| `***`    | user   | record application deadlines for each job listing                                 | stay organized on application deadlines          |
| `***`    | user   | mark the status of each application                                               | keep track of application progress               |
| `***`    | user   | delete job applications that are no longer relevant                               | update my list according to my current interests |
| `***`    | user   | view all applications I have added                                                |                                                  |

*{More to be added}*

------------------------------------------------------------------------------------------------------------------------

### Use cases

(For all use cases below, the **System** is the `JobFindr` and the **Actor** is the `User`, unless specified otherwise)

**System:** JobFindr

**Actor:** User

---

**Use case: Add an application**

_MSS_

1. User requests to add an application, specifying the company name
2. System adds the application

   Use case ends.

_Extensions_

* 1a. The company is not specified

  Use case ends.

---

**Use case: Delete an application**

_MSS_

1. User requests to list applications
2. System shows a list of applications
3. User requests to delete an application in the list, specifying an index
4. System deletes the application at the index specified

   Use case ends.

_Extensions_

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.
    * 3a1. System shows an error message.

      Use case resumes at step 3.

---

**Use case: List applications**

_MSS_

1. User requests to list applications
2. System shows a list of applications

   Use case ends.

---

*{More to be added}*

------------------------------------------------------------------------------------------------------------------------

### Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. A user with above-average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.
3. The system should respond to the user’s command within two seconds.
4. The user interface should be intuitive enough for users who are first-time users.
5. The project is expected to adhere to a schedule that delivers a milestone set every two weeks.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
