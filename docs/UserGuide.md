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
    2. [Asking for help: `help`](#asking-for-help--help)
    3. [Listing all applications: `list`](#listing-all-applications--list)
    4. [Adding an application: `add`](#adding-an-application--add)
    5. [Deleting an application: `delete`](#deleting-an-application--delete)
    6. [Editing an application: `edit`](#editing-an-application--edit)
    7. [Finding an application: `find`](#finding-an-application--find)
    8. [Sorting the applications: `sort`](#sorting-all-applications--sort)
    9. [Adding an interview: `interview add`](#adding-an-interview--interview-add)
    10. [Deleting an interview: `interview delete`](#deleting-an-application--delete)
    11. [Editing an interview: `interview edit`](#editing-an-interview--interview-edit)
    12. [Clearing all applications: `clear`](#clearing-all-applications--clear)
    13. [Exiting the programme: `exit`](#exiting-the-programme--exit)
5. [FAQ](#faq)
6. [Glossary](#glossary)

--------------------------------------------------------------------------------------------------------------------

## Introduction

JobFindr is a **Contact Book app** for NUS fresh graduates who are looking for jobs.

It simplifies _contact management_, provides _reminders_ and enhances _organisation_, helping users _stay competitive_
in the job market.
The project simulates an ongoing software project for a desktop application (called _JobFindr_) used for managing job
applications.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `jobfindr.jar` from [here](https://github.com/AY2324S1-CS2103T-W12-3/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.
4. Open a command terminal, `cd` into the folder you put the jar file in, and enter the command `java -jar jobfindr.jar`
   to run the application.
5. A GUI similar to the below should appear in a few seconds. Note that the app contains some sample data.<br><br>
   ![Ui](images/Ui.png)

6. Type the command in the command box and press Enter to execute it. For example, typing **`help`** and pressing Enter
   will
   open the help window.<br>
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
| Status   | `s/`   | Possible values in "[Valid statuses](#valid-statuses)"                                                              | No        | Alphabetical  |
| Industry | `i/`   | Must start with an alphanumeric character                                                                           | Yes       | Alphabetical  |
| Deadline | `d/`   | Must be in the format MMM dd yyyy HHmm (e.g. Dec 31 2030 1200) and cannot be earlier than the current date and time | Yes       | Chronological |
| Type     | `t/`   | Possible values in "[Valid job types](#valid-job-types)"                                                            | Yes       | Alphabetical  |

### Valid statuses

The following are valid statuses:

| Status            | Remarks                                                                                 |
|-------------------|-----------------------------------------------------------------------------------------|
| `TO_BE_SUBMITTED` | An application that you plan to apply for. The default status if no status is specified |
| `PENDING`         | An application that you have applied for but have yet to receive a result               |
| `APPROVED`        | An application that you have received a job offer for                                   |
| `REJECTED`        | An application that you have been rejected for                                          |

### Valid job types

The following are valid job types:

| Job Type     | Remarks           |
|--------------|-------------------|
| `FULL_TIME`  | A full time job   |
| `PART_TIME`  | A part time job   |
| `INTERNSHIP` | An internship     |
| `TEMPORARY`  | A temporary job   |
| `CONTRACT`   | A contract job    |
| `FREELANCE`  | A freelance job   |
| `VOLUNTEER`  | A volunteered job |

### Structure of an interview

| Field    | Prefix | Remarks                                                                                                             | Optional? |
|----------|--------|---------------------------------------------------------------------------------------------------------------------|-----------|
| Type     | `t/`   | Possible values in "[Valid interview types](#valid-interview-types)"                                                | No        |
| DateTime | `d/`   | Must be in the format MMM dd yyyy HHmm (e.g. Dec 31 2030 1200) and cannot be earlier than the current date and time | No        |
| Address  | `a/`   | Must start with an alphanumeric character                                                                           | No        |

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

---

### Listing all applications : `list`

Shows a list of all applications in the list in alphabetical order.

**Format:** `list`

**UI mockup:**
![ListCommand](images/user-guide/ListCommand.png)

---

### Adding an application : `add`

Adds a job application to the list.

**Format:** `add c/COMPANY r/ROLE [d/DEADLINE] [s/STATUS] [i/INDUSTRY] [t/JOB_TYPE]`

**Examples:**

* `add c/Microsoft r/Software Engineer d/Nov 12 2022 1200 i/Technology s/Pending`
  Adds a company called Microsoft, with the role Software Engineer in the technology industry,
  deadline Nov 12 2022 1200 and status as pending.
* `add c/Google r/Cleaner`
  Adds a company called Google, with the role Cleaner and status `TO_BE_SUBMITTED`.

**UI mockup:**
![](images/user-guide/AddCommand.png)

---

### Deleting an application : `delete`

Deletes the specified application from the list.

**Format:** `delete INDEX`

* Deletes the application to the company at the specified `INDEX`.

**Examples:**

* `list` followed by `delete 2`
  Deletes the 2nd application in the list.

**UI mockup:**
![](images/user-guide/DeleteCommand.png)

---

### Editing an application : `edit`

Edits an application in the list.

**Format:** `edit INDEX [c/COMPANY] [r/ROLE] [d/DEADLINE] [s/STATUS] [i/INDUSTRY] [t/JOB_TYPE]`

* Edits the application to the company at the specified `INDEX`.
* At least one of the optional fields must be provided.

**Examples:**

* `edit 1 r/Announcer`
  Changes the role of the 1st job application to an announcer.
* `edit 5 s/approved t/volunteer`
  Changes the status and job type of the 5th job application to `APPROVED` and volunteer respectively.

**UI mockup:**
![](images/user-guide/EditCommand.png)
---

### Finding an application : `find`

Finds all applications whose fields match the keywords provided.

**Format:** `find [KEYWORDS] [c/COMPANY] [r/ROLE] [d/DEADLINE] [s/STATUS] [i/INDUSTRY] [t/JOB_TYPE]`

* At least one optional field must be provided.
* More than one `KEYWORDS` can be provided.
* If `KEYWORDS` is provided, the command will find all applications that contains the keywords in any field.
* An application will be listed only if all the keywords match. The keywords are case-insensitive.
* Applications with partially matching keywords will not be listed.
    * e.g. searching for the keyword "Goo" will not list applications with "Google".
* Searches for Deadline must be in the format `MMM DD YYYY HHMM` (e.g. Dec 31 2030 1200).

**Examples:**

* `find c/Google`
  Finds all applications with "Google" in the company name.
* `find Google r/Software Engineer`
  Finds all applications with "Software Engineer" in the role and "Google" in any field.
* `find Google AI`
  Finds all applications with _both_ "Google" and "AI" in any fields.

**UI mockup:**
![](images/user-guide/FindCommand.png)
---

### Sorting all applications : `sort`

Sorts the list based on the prefix provided.

**Format:** `sort PREFIX`

* A single valid `PREFIX` must be provided. Refer to "[Structure of a job application](#structure-of-a-job-application)"
  for the list of valid prefixes.
* For optional fields, applications with empty fields will be listed first.

**Examples:**

* `sort d/`
  Lists all applications, starting from the one with the earliest deadline.

* `sort r/`
  Lists all applications sorted by role, in alphabetical order.

**UI mockup:**
![](images/user-guide/SortCommand.png)
---

### Adding an interview: `interview add`

Adds an interview to the specified application from the list.

**Format:** `interview add INDEX t/TYPE d/DATETIME a/ADDRESS`

**Examples:**

* `interview add 1 t/Technical d/Nov 12 2022 1200 a/Home`
  Adds a technical interview at Nov 12 2022 1200 at Home to the first application in the list.

**UI mockup:**
![](images/user-guide/IntAddCommand.png)
---

### Deleting an interview: `interview delete`

Deletes an interview to the specified application from the list.

**Format:** `interview delete INTERVIEW_INDEX from/JOB_INDEX`

* Edits the interview at the specified `INTERVIEW_INDEX` of the application at the specified `JOB_INDEX`.

**Examples:**

* `interview delete 1 from/2`
  Deletes the 1st interview from the 2nd job application in the list.

**UI mockup:**
![](images/user-guide/IntDeleteCommand.png)
---

### Editing an interview: `interview edit`

Edits an interview to the specified application from the list.

**Format:** `interview edit INTERVIEW_INDEX from/JOB_INDEX [t/TYPE] [d/DATETIME] [a/ADDRESS]`

* Edits the interview at the specified `INTERVIEW_INDEX` of the application at the specified `JOB_INDEX`.
* At least one of the optional fields must be provided.

**Examples:**

* `interview edit 2 from/4 t/GROUP`
  Edits the interview type to a group interview for the 2nd interview from the 4th job application in the list.
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
