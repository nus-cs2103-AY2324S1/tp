---
layout: page
title: Developer Guide
---

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

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

**Use case: Set deadline for an application**

_MSS_

1. User requests to list applications
2. System shows a list of applications
3. User requests to set a deadline for an application, specifying an index and deadline
4. System adds deadline to the application at the index specified

   Use case ends.

_Extensions_

* 2a. The list is empty.

  Use case ends.

* 3a. The given index/deadline is invalid.
    * 3a1. System shows an error message.

      Use case resumes at step 3.

---

**Use case: Mark an application**

_MSS_

1. User requests to list applications
2. System shows a list of applications
3. User requests to mark an application in the list, specifying an index and status
4. System marks the application at the index specified

   Use case ends.

_Extensions_

* 2a. The list is empty.

  Use case ends.

* 3a. The given index/deadline is invalid.
    * 3a1. System shows an error message.

      Use case resumes at step 3.

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
