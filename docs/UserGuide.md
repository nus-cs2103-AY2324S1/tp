---
layout: page
title: User Guide
---

## Table of Contents

1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Key Information](#key-information)
    1. [User Interface](#user-interface)
    2. [Structure of a job application](#structure-of-a-job-application)
    3. [Valid statuses](#valid-statuses)
    4. [Valid job types](#valid-job-types)
    5. [Structure of an interview](#structure-of-an-interview)
    6. [Valid interview types](#valid-interview-types)
    7. [Command Format](#command-format)
4. [Features](#features)
    1. [Command Summary](#command-summary)
    2. [Asking for help: `help`](#asking-for-help-help)
    3. [Listing all applications: `list`](#listing-all-applications--list)
    4. [Adding an application: `add`](#adding-an-application--add)
    5. [Deleting an application: `delete`](#deleting-an-application--delete)
    6. [Editing an application: `edit`](#editing-an-application--edit)
    7. [Finding an application: `find`](#finding-an-application--find)
    8. [Sorting the applications: `sort`](#sorting-all-applications--sort)
    9. [Adding an Interview: `interview add`](#adding-an-interview-interview-add)
    10. [Deleting an Interview: `interview delete`](#deleting-an-interview-interview-delete)
    11. [Editing an Interview: `interview edit`](#editing-an-interview-interview-edit)
    12. [Clearing All Applications: `clear`](#clearing-all-applications-clear)
    13. [Exiting the Programme: `exit`](#exiting-the-programme-exit)
5. [FAQ](#faq)
6. [Glossary](#glossary)

--------------------------------------------------------------------------------------------------------------------

## Introduction

JobFindr is an **Application Book app** for NUS fresh graduates to manage their job applications.

It simplifies _job application management_ , promotes _efficiency_, and enhances _organisation_,
helping you keep track of all your past and upcoming applications.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `JobFindr.jar` from [here](https://github.com/AY2324S1-CS2103T-W12-3/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.
4. Open a command terminal, `cd` into the folder you put the jar file in, and enter the command `java -jar JobFindr.jar`
   to run the application.
5. A GUI similar to the below should appear in a few seconds. Note that the app contains some sample data.<br><br>
   ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. For example, typing **`help`** and pressing Enter
   will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all applications.

    * `add c/Microsoft r/Software Engineer d/Nov 12 2022 1200 i/Technology s/Pending` :
      Adds an application for the company named `Microsoft` to the Application Book.

    * `delete 3` : Deletes the 3rd application shown in the current list.

    * `clear` : Deletes all applications.

    * `exit` : Exits the app.

7. Refer to "[Key Information](#key-information)" below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Key Information

<div markdown="block" class="alert alert-info">

### User interface

![GUI](images/UiStructure.png)

### Structure of a job application

| Field    | Prefix | Remarks                                                                                                             | Optional? | Sort Order    |
|----------|--------|---------------------------------------------------------------------------------------------------------------------|-----------|---------------|
| Company  | `c/`   | No restrictions                                                                                                     | No        | Alphabetical  |
| Role     | `r/`   | Must only contain alphanumeric characters and spaces                                                                | No        | Alphabetical  |
| Status   | `s/`   | Possible values in "[Valid statuses](#valid-statuses)"                                                              | Yes       | Alphabetical  |
| Industry | `i/`   | Must start with an alphanumeric character                                                                           | Yes       | Alphabetical  |
| Deadline | `d/`   | Must be in the format MMM dd yyyy HHmm (e.g. Dec 31 2030 1200) and cannot be earlier than the current date and time | Yes       | Chronological |
| Job Type | `t/`   | Possible values in "[Valid job types](#valid-job-types)"                                                            | Yes       | Alphabetical  |

* Applications with the same company _AND_ role are considered duplicate jobs.
  JobFindr does not allow the creation of duplicate jobs.

* Deadline refers to the application submission deadline.

### Valid statuses

The following are valid statuses:

| Status          | Remarks                                                                   |
|-----------------|---------------------------------------------------------------------------|
| `TO_ADD_STATUS` | The default status if not specified                                       |
| `PENDING`       | An application that you have applied for but have yet to receive a result |
| `APPROVED`      | An application that you have received a job offer for                     |
| `REJECTED`      | An application that you have been rejected for                            |

### Valid job types

The following are valid job types:

| Job Type          | Remarks                               |
|-------------------|---------------------------------------|
| `TO_ADD_JOB_TYPE` | The default job type if not specified |
| `FULL_TIME`       | A full time job                       |
| `PART_TIME`       | A part time job                       |
| `INTERNSHIP`      | An internship                         |
| `TEMPORARY`       | A temporary job                       |
| `CONTRACT`        | A contract job                        |
| `FREELANCE`       | A freelance job                       |
| `VOLUNTEER`       | A volunteered job                     |

### Structure of an interview

| Field    | Prefix | Remarks                                                                                                             | Optional? |
|----------|--------|---------------------------------------------------------------------------------------------------------------------|-----------|
| Type     | `t/`   | Possible values in "[Valid interview types](#valid-interview-types)"                                                | No        |
| DateTime | `d/`   | Must be in the format MMM dd yyyy HHmm (e.g. Dec 31 2030 1200) and cannot be earlier than the current date and time | No        |
| Address  | `a/`   | Must start with an alphanumeric character                                                                           | No        |

* Interviews in the **same application** with the same type _AND_ datetime _AND_ address are considered duplicate interviews.
JobFindr does not allow the creation of duplicate interviews.

* DateTime of interview can be after Deadline of application.

* Multiple interviews with the same DateTime can be added if they are not duplicate interviews.

### Valid interview types

The following are valid interview types:

| Interview Type | Remarks                 |
|----------------|-------------------------|
| `BEHAVIOURAL`  | A behavioural interview |
| `TECHNICAL`    | A technical interview   |
| `CASE`         | A case interview        |
| `GROUP`        | A group interview       |
| `PHONE`        | A phone interview       |
| `VIDEO`        | A video interview       |
| `ONLINE`       | An online interview     |
| `ONSITE`       | An onsite interview     |
| `OTHER`        | Other interviews        |

### Command format

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.

* Words in upper case are the parameters to be supplied by the user.<br>
    * e.g. in `add c/COMPANY`, `COMPANY` is a parameter which can be used as `add c/Google`.

* Items in square brackets are optional.<br>
    * e.g. `c/COMPANY [s/STATUS]` can be used as `c/Google s/Pending` or as `c/Google`.

* Parameters can be in any order.<br>
    * e.g. if the command specifies `c/COMPANY r/ROLE`, `r/ROLE c/COMPANY` is also acceptable.
    * When `INDEX` is required, it has to be the first parameter.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be
  ignored.<br>
    * e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* A _white space_ must be included directly before each [prefix](#prefix).

* The same prefix cannot be used multiple times in the same command.
    * e.g. `add r/Cleaner c/Google c/Microsoft` is an invalid input.

* `INDEX` and `JOB_INDEX` refers to the index number of the chosen application in the displayed application list and
  must be a _positive
  integer_ 1, 2, 3, …

* `INTERVIEW_INDEX` refers to the index number of the chosen interview of an application and must be a _positive
  integer_ 1, 2, 3, …

</div>

--------------------------------------------------------------------------------------------------------------------

## Features

### Command summary

| Action               | Format                                                                                   |
|----------------------|------------------------------------------------------------------------------------------|
| **Add**              | `add c/COMPANY r/ROLE [d/DEADLINE] [s/STATUS] [i/INDUSTRY] [t/JOB_TYPE]`                 |
| **Edit**             | `edit INDEX [c/COMPANY] [r/ROLE] [d/DEADLINE] [s/STATUS] [i/INDUSTRY] [t/JOB_TYPE]`      |
| **Delete**           | `delete INDEX`                                                                           |
| **List**             | `list`                                                                                   |
| **Find**             | `find [KEYWORDS] [c/COMPANY] [r/ROLE] [d/DEADLINE] [s/STATUS] [i/INDUSTRY] [t/JOB_TYPE]` |
| **Sort**             | `sort FIELD_SPECIFIER`                                                                   |
| **Interview add**    | `interview add INDEX t/TYPE d/DATETIME a/ADDRESS`                                        |
| **Interview delete** | `interview delete INTERVIEWINDEX from/JOBINDEX`                                          |
| **Interview edit**   | `interview edit INTERVIEWINDEX from/JOBINDEX`                                            |
| **Help**             | `help`                                                                                   |
| **Clear**            | `clear`                                                                                  |
| **Exit**             | `exit`                                                                                   |

---

### Asking for help: `help`

Shows a list of commands and how they can be used.

**Format:** `help`

***Successful Commands:***

1. Launching the help window: `help`

* This command opens up a separate window, displaying all the availble commands on JobFindr and their formats.

---

### Listing all applications : `list`

Shows a list of all applications in the list in alphabetical order.

**Format:** `list`

**Successful command:**

* List out all applications to companies in the list with index.

**UI mockup:**
![ListCommand](images/user-guide/ListCommand.png)

---

### Adding an application : `add`

Adds a job application to the list.

**Format:** `add c/COMPANY r/ROLE [d/DEADLINE] [s/STATUS] [i/INDUSTRY] [t/JOB_TYPE]`

**Examples:**

***Successful Commands:***

1. Adding a basic job application: `add c/Google r/Software Engineer`

* This command adds a job application for the company "Google" with the role "Software Engineer."
* Optional fields like deadline, status, industry, and job type are not provided, so they will use their default values.

2. Adding a complete job application with all
   fields: `add c/Microsoft r/Project Manager d/Dec 31 2023 1400 i/Technology s/Pending t/FULL_TIME`

* This command adds a job application for the company "Microsoft" with the role "Project Manager" and provides a
  deadline, industry, status, and job type.

***Failed Commands:***

1. Missing required fields: `add r/Data Analyst`

* This command is invalid because it doesn't provide the "c/COMPANY" field, which is required.

2. Invalid Job Type: `add c/Apple r/Software Developer t/Part-Time`

* This command is invalid because "t/Part-Time" is not a valid job type. It should be "t/PART_TIME."

**UI mockup:**
![](images/user-guide/AddCommand.png)

---

### Deleting an application : `delete`

Deletes the specified application from the list.

**Format:** `delete INDEX`

* Deletes the application to the company at the specified `INDEX`.

**Examples:**

* `list`
* followed by `delete 2`

  Deletes the 2nd application in the list.

**UI mockup:**
![](images/user-guide/DeleteCommand.png)

***Successful Commands:***

1. Deleting a job application: `delete 2`

* This command deletes the job application at the 2nd index in the list.
* This is provided that this job application indeed exists.

***Failed Commands:***

1. Invalid job index: `delete 5` when there is no job application at index 5.

* This command is invalid because 5 is not a valid job index.

---

### Editing an application : `edit`

Edits an application in the list.

**Format:** `edit INDEX [c/COMPANY] [r/ROLE] [d/DEADLINE] [s/STATUS] [i/INDUSTRY] [t/JOB_TYPE]`

* Edits the application to the company at the specified `INDEX`.
* At least one of the optional fields must be provided.

**Examples:**

***Successful Commands:***

1. Editing the role of a job application: `edit 1 r/Marketing Manager`

* This command edits the role of the first job application to "Marketing Manager."

2. Editing multiple fields of a job application: `edit 2 c/Amazon r/Product Manager d/Dec 15 2023 1000 s/APPROVED`

* This command edits the company, role, deadline, and status of the second job application.

***Failed Commands:***

1. Missing index: `edit c/Google r/Software Engineer`

* This command is invalid because it doesn't specify the index of the job application to edit.

2. Invalid field: `edit 3 q/Designer`

* This command is invalid because "q/Designer" is not a valid field. It should be "r/Designer."

3. Invalid Job Type: `edit 4 t/Part-Time`

* This command is invalid because "t/Part-Time" is not a valid job type. It should be "t/PART_TIME."

**UI mockup:**
![](images/user-guide/EditCommand.png)
---

### Finding an application : `find`

Finds all applications whose fields match the keywords provided.

**Format:** `find [KEYWORDS] [c/COMPANY] [r/ROLE] [d/DEADLINE] [s/STATUS] [i/INDUSTRY] [t/JOB_TYPE]`

* At least one optional parameter must be provided. Multiple parameters can be provided.
* If a [prefix](#prefix) is given, the search will only find applications containing the given keywords in the specified
    field.
* Multiple keywords can be provided for one parameter.
    * e.g. `find r/Software Engineer` is a valid command.
* Keywords are case-insensitive.
* If `KEYWORDS` is provided, the command will find all applications that contains the `KEYWORDS` in any field.
* An application will be listed only if it contains _ALL_ the keywords provided.
* Applications with partially matching keywords will not be listed.
    * e.g. searching for the keyword "Goo" will not list applications containing "Google".
* Characters not separated by white space are considered _ONE_ word.
    * e.g. searching for the keyword "ADD" will not list applications containing "TO_ADD_DEADLINE".
* Searches for deadline must be formatted correctly. Refer to
  "[Structure of a job application](#structure-of-a-job-application)" for the correct format of a deadline.

**Examples:**

***Successful Commands:***

1. Finding by company: `find c/Google`

* This command searches for all job applications with "Google" in the company name.

2. Finding using multiple conditions: `find Google r/Software Engineer`

* This command searches for all job applicaitons with "Google" in any field and the words "Software" and "Engineer".

***Failed Commands:***

1. Missing keywords: `find`

* This command is invalid because it doesn't specify any keywords to search for.

**UI mockup:**
![](images/user-guide/FindCommand.png)
---

### Sorting all applications : `sort`

Sorts the list based on the prefix provided.

**Format:** `sort PREFIX`

* A single valid `PREFIX` must be provided. Refer to "[Structure of a job application](#structure-of-a-job-application)"
  for the list of valid prefixes.
* The sort order cannot be reversed.
    * e.g. when sorting by company, companies cannot be listed from Z-A.
* For optional fields, applications with empty fields will be listed first.

**Examples:**

***Successful Commands:***

1. Sort by company: `sort c/`

* This command sorts all job applications in ascending alphabetical order of company name.

2. Sort by role: `sort r/`

* This command sorts all job applications in chronological order of deadline, starting from applications with the
  earliest deadline.

***Failed Commands:***

1. Multiple prefixes: `sort r/ c/`

* This command is invalid because only one prefix is accepted.

2. Input following prefix: `sort r/Software`

* This command is invalid because there should be nothing following the prefix provided.

**UI mockup:**
![](images/user-guide/SortCommand.png)
---

### Adding an interview: `interview add`

Adds an interview to the specified application from the list.

**Format:** `interview add INDEX t/TYPE d/DATETIME a/ADDRESS`

**Successful command:**

* When an interview is successfully added, display “New interview added: (`TYPE`) interview; Date and Time: (`DATETIME`); Address: (`ADDRESS`)”.

**Failed command:**

* If any fields or prefixes are not included with the command,
display "Invalid command format!" and the correct format for interview add command.

* If any fields have invalid input, display the possible valid inputs for that field.

**Examples:**

* `interview add 1 t/Technical d/Nov 12 2024 1200 a/Home`

  Adds a technical interview at Nov 12 2024 1200 at Home to the first application in the list.

**UI mockup:**
![](images/user-guide/IntAddCommand.png)
---

### Deleting an interview: `interview delete`

Deletes an interview of the specified application from the list.

**Format:** `interview delete INTERVIEW_INDEX from/JOB_INDEX`

* Deletes the interview at the specified `INTERVIEW_INDEX` of the application at the specified `JOB_INDEX`.

**Successful command:**

* When an interview is successfully deleted, display “Interview deleted: (`TYPE`) interview; Date and Time: (`DATETIME`); Address: (`ADDRESS`)”.

**Failed command:**

* If any fields or prefixes are not included with the command,
  display "Invalid command format!" and the correct format for interview delete command.

**Examples:**

* `interview delete 1 from/2`

  Deletes the 1st interview from the 2nd job application in the list.

**UI mockup:**
![](images/user-guide/IntDeleteCommand.png)
---

### Editing an interview: `interview edit`

Edits an interview of the specified application from the list.

**Format:** `interview edit INTERVIEW_INDEX from/JOB_INDEX [t/TYPE] [d/DATETIME] [a/ADDRESS]`

* Edits the interview at the specified `INTERVIEW_INDEX` of the application at the specified `JOB_INDEX`.
* At least one of the optional fields must be provided.

**Successful command:**

* When an interview is successfully edited, display “Interview successfully edited: (`TYPE`) interview; Date and Time: (`DATETIME`); Address: (`ADDRESS`)”.

**Failed command:**

* If any fields or prefixes are not included with the command,
  display "Invalid command format!" and the correct format for interview edit command.

**Examples:**

* `interview edit 2 from/4 t/GROUP d/Jan 20 2025 1200`

  Edits the interview type and date time for the 2nd interview from the 4th job application in the list.

* `interview edit 4 from/8 a/NTU`

  Edits the address for the 4th interview from the 8th job application in the list.

**UI mockup:**
![](images/user-guide/IntEditCommand.png)
---

### Clearing all applications: `clear`

Clears all applications from the application book.

**Format:** `clear`

---

### Exiting the programme: `exit`

Exits the program.

**Format:** `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Glossary

#### Prefix
* An affix that placed before the field to indicate the type of field
* Examples: c/ r/ s/ from/
