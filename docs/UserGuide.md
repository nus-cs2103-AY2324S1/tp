---
layout: page
title: User Guide
---

# Table of Contents

- [Introduction](#introduction)
- [How to use this user guide](#how-to-use-this-user-guide)
- [Quick Start](#quick-start)
- [GUI Summary](#gui-summary)
- [Features](#features)
  - [General Features](#general-features)
    - [Viewing help: `help`](#viewing-help--help)
    - [Clearing all the data: `clear`](#clearing-all-applicants-and-interviews--clear)
    - [Exiting the program: `exit`](#exiting-the-program--exit)
    - [Saving the data](#saving-the-data)
  - [Application Management Features](#applicant-management-features)
    - [Adding an applicant: `add-a`](#adding-an-applicant--add-a)
    - [Deleting an applicant: `delete-a`](#deleting-an-applicant--delete-a)
    - [Editing an applicant: `edit-a`](#editing-an-applicant--edit-a)
    - [Finding applicants from the list: `find-a`](#finding-applicants--find-a)
    - [Listing all applicants: `list-a`](#listing-all-applicants--list-a)
  - [Interview Management Features](#interview-management-features)
    - [Adding an interview: `add-i`](#adding-an-interview--add-i)
    - [Deleting an interview: `delete-i`](#deleting-an-interview--delete-i)
    - [Editing an interview: `edit-i`](#editing-an-interview--edit-i)
    - [Finding interviews from the list: `find-i`](#finding-interview-by-job-title--find-i)
    - [Listing all interviews: `list-i`](#listing-all-interviews--list-i)
    - [Listing all free timing for the given day: `list-freetime`](#listing-all-free-time-for-the-given-day--list-freetime)
    - [Listing all interviews for today: `list-i-today`](#listing-all-interviews-for-today--list-i-today)
    - [Marking an interview as done: `mark`](#marking-an-interview--mark)
    - [Rating an interview: `rate`](#rating-an-interview--rate)
    - [Listing all completed interview: `list-i-done`](#listing-all-completed-interview--list-i-done)
    - [Listing all incomplete interview: `list-i-not-done`](#listing-all-incomplete-interview--list-i-not-done)
    - [Sorting the interview list by rating: `sort-rate`](#sorting-interviews-by-rating--sort-rate)
    - [Sorting the interview list by start-time: `sort-time`](#sorting-interviews-by-start-time--sort-time)
- [Frequently Asked Questions](#frequently-asked-questions)
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)
  - [General Commands](#general-commands)
  - [Application Management Commands](#applicant-management-commands)
  - [Interview Management Commands](#interview-management-commands)
- [Glossary](#glossary)

--------------------------------------------------------------------------------------------------------------------

# Introduction

Tired of sending out offers to the best candidates, just to receive a disappointing reply that they have already accepted another offer that was sent out before yours?

**InterviewHub**  is a desktop app for engineering manager to schedule job interviews and manage applicants.
By optimizing recruitment workflows, we enable faster decision-making, helping you secure top talent before your competitors.

It is optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a **Graphical User Interface** (GUI).
If you can type fast, **InterviewHub** can get your Interview management tasks done faster than traditional GUI apps.

What are you waiting for? Let's get started using **InterviewHub** by following the [How to use this user guide](#how-to-use-this-user-guide) section!

--------------------------------------------------------------------------------------------------------------------

# How to use this user guide

To quickly find the information you need, refer to the [Table of Contents](#table-of-contents) located at the beginning of this guide. 
It lists all the chapters and sections, allowing you to jump directly to the relevant content.

Next, to get you started, we've included a [Quick Start](#quick-start) section in this user guide to ensure that you have no trouble setting up **InterviewHub**.

To get a better understanding of what you see, you can head over to [GUI Summary](#gui-summary) for a quick overview of the User-Interface.

Along the way if you encounter unfamiliar terms, our [Glossary](#glossary) provides definitions and explanations for product-specific terminology. Refer to it whenever needed.

Before we begin, the table below describes some of the symbols and syntax you may see throughout our guide:

| Symbol                                    | Meaning                                                                                          |
|-------------------------------------------|--------------------------------------------------------------------------------------------------|
| :warning: **Warning**                     | Important information that must be understood as the action may leads to unexpected consequences |
| :information_source: **Note**             | Important information that you should pay attention to                                           |
| :bulb: **Tip**                            | Helpful information to improve your experience                                                   |
| `Highlighted text block`                  | Commands or parameters relevant to the application                                               |
| [Hyperlinks](#how-to-use-this-user-guide) | When clicked, you will be led to a designated section within this document or an external link   |

--------------------------------------------------------------------------------------------------------------------

# Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `InterviewHub.jar` from [here](https://github.com/AY2324S1-CS2103T-T11-2/tp/releases).

3. Copy the file to the folder you want to use as the home folder for InterviewHub.

4. Double-click the file to start the app. The Graphical User Interface(GUI) should appear in a few seconds.

5. To get a better understanding of what you see. Please refer to the [GUI Summary](#gui-summary) for more details.

--------------------------------------------------------------------------------------------------------------------

# GUI Summary

![GUI Summary](images/GuiSummary.png)

For each applicant, we see the following details:

| Applicant |
|-----------|
| Name      |
| Tags      |
| Phone     |
| Address   |
| Email     |

For each interview, we see the following details:

| Interview        |
|------------------|
| Applicant's Name |
| Job Title        |
| Start Time       |
| End Time         |
| Rating           |

[Back to the Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
# Features

<div markdown="block" class="alert alert-info">
**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list-i`, `list-a`, `list-i-done`, `list-i-not-done`,
`list-today`, `sort-rate`, `sort-time`, `exit`, and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
</div>

[Back to the Table of Contents](#table-of-contents)

## General Features

### Viewing help : `help`

A pop up window containing the link as shown below will appear to lead you to **InterviewHub** User Guide.

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits **InterviewHub**.

Format: `exit`

### Clearing all applicants and interviews : `clear`

Clears all applicants and interviews from **InterviewHub**.

<div markdown="block" class="alert alert-warning">
**:exclamation: Warning:** <br>
* This action is irreversible, therefore do proceed with caution!
</div>

Format: `clear`

### Saving the data

InterviewHub data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[Back to the Table of Contents](#table-of-contents)

## Applicant Management Features

### Adding an applicant : `add-a`

Adds an applicant to **InterviewHub**.

Format: `add-a n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]`

Examples:
* `add-a n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`.

[Back to the Table of Contents](#table-of-contents)

### Deleting an applicant : `delete-a`

Deletes the specified applicant from **InterviewHub**.

Format: `delete-a INDEX`

* Deletes the applicant at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed applicant list.
* The `INDEX` **must be a positive unsigned integer** 1, 2, 3, …​ The upper limit of valid integers is the number of applicants currently displayed in the applicant list

Examples:
* `delete-a 1` deletes the 1st applicant in the address book.

[Back to the Table of Contents](#table-of-contents)

### Editing an applicant : `edit-a`

Edits an existing applicant in **InterviewHub**.

Format: `edit-a APPLICANT_INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

* Edits the person at the specified `APPLICANT_INDEX`. The index refers to the index number shown in the displayed applicant list.
* The `INDEX` **must be a positive unsigned integer** 1, 2, 3, …​ The upper limit of valid integers is the number of applicants currently displayed in the applicant list
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit-a 1 n/John Doe` Edits the name of the 1st applicant to be `John Doe`.
*  `edit-a 2 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 2nd applicant to be `91234567` and `johndoe@example.com` respectively.

[Back to the Table of Contents](#table-of-contents)

### Finding applicants : `find-a`

Finds applicants whose attributes contain any of the given keywords.

Format: ``find-a [n/KEYWORDS...] [p/NUMBER]
[e/KEYWORDS...] [a/KEYWORDS...] [t/KEYWORDS...]``

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* At least one of the optional fields must be provided
* Any of the fields (name, phone, email, address, tags) can be searched
* Only full words will be matched e.g. `Han` will not match `Hans` for name, address and tags
* For phone, partial numbers will match e.g. `987` will match `98765432`
* Applicants matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find-a n/Alex` returns `Alex` and `Alex Yeoh`

Before find-a command:

* `find-a n/alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find-a n/alex david'](images/findAlexDavidResult.png)
* `find-a p/874 a/serangoon ang` returns `97438807`, `Serangoon Gardens`,
  `Serangoon Gardens Street`, `Ang Mo Kio`<br>
  ![result for 'find-a p/874 a/serangoon ang'](images/findPhoneAddress.png)

[Back to the Table of Contents](#table-of-contents)

### Listing all applicants : `list-a`

Shows a list of all applicants in **InterviewHub** onto the GUI.

Format: `list-a`

[Back to the Table of Contents](#table-of-contents)

## Interview Management Features

### Adding an interview : `add-i`

Adds an interview to **InterviewHub**.

Format: `add-i app/APPLICANT_ID jr/JOB_ROLE start/START_DATE_AND_TIME end/END_DATE_AND_TIME`

| Parameter             | Representation                                            | Constraints                                                                                                                                             |
|-----------------------|-----------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| `APPLICANT_ID`        | The index of the applicant as shown in the applicant list | Must be a positive unsigned integer 1, 2, 3, …​ The upper limit of valid integers is the number of applicants currently displayed in the applicant list |
| `JOB_ROLE`            | The role the applicant is interviewing for                | Must contain only alphanumeric characters and spaces                                                                                                    |
| `START_DATE_AND_TIME` | Starting time of the interview                            | Must be in one of the accepted formats stated below AND must be before the end time AND must be on the same day as the end time                         |
| `END_DATE_AND_TIME`   | Ending time of the interview                              | Must be in one of the accepted formats stated below AND must be after the start time AND must be on the same day as the start time                      |

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** JOB_ROLE allows empty strings to be entered to handle situations where the applicant is applying
to the company in general.
</div>

List of accepted date formats:
  * DD/MM/YYYY and time:
    * `16 May 2024 1515`
    * `16 May 2024 3.15pm`
    * `16 May 2024 3pm`
    * `16-05-2024 1515`
    * `16-05-2024 3.15pm`
    * `16-05-2024 3pm`
    * `16-05-24 1515`
    * `16-05-24 3.15pm`
    * `16-05-24 3pm`
    * `16/05/2024 1515`
    * `16/05/2024 3.15pm`
    * `16/05/2024 3pm`
    * `16/05/24 1515`
    * `16/05/24 3.15pm`
    * `16/05/24 3pm`
  * MM, DD and time:
    * `16 May 1515`
    * `16 May 3.15pm`
    * `16 May 3pm`
    * `16 January 1515`
    * `16 January 3.15pm`
    * `16 January 3pm`
    * `16/5 1515`
    * `16/5 3.15pm`
    * `16/5 3pm`
    * `16/05 1515`
    * `16/05 3.15pm`
    * `16/05 3pm`

Example:
* `add-i app/3 jr/software engineer start/12-12-2023 1400 end/12-12-2023 1500`

[Back to the Table of Contents](#table-of-contents)

### Deleting an interview : `delete-i`

Deletes the interview at the specified `INTERVIEW_INDEX` from **InterviewHub**

Format: `delete-i INTERVIEW_INDEX`

| Parameter         | Representation                                                   | Constraints                                                                                                                                             |
|-------------------|------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INTERVIEW_INDEX` | The index of the target interview as shown in the interview list | Must be a positive unsigned integer 1, 2, 3, …​ The upper limit of valid integers is the number of interviews currently displayed in the interview list |

Examples:
* `delete-i 1` deletes the 1st interview in **InterviewHub**.

[Back to the Table of Contents](#table-of-contents)

### Editing an interview : `edit-i`

Edits an existing interview at the specified `INTERVIEW_INDEX`.

Format: `edit-i INTERVIEW_INDEX [jr/JOB_ROLE] [start/START_DATE_AND_TIME] [end/END_DATE_AND_TIME]`

| Parameter             | Representation                                            | Constraints                                                                                                                                             |
|-----------------------|-----------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INTERVIEW_INDEX`     | The index of the interview as shown in the interview list | Must be a positive unsigned integer 1, 2, 3, …​ The upper limit of valid integers is the number of interviews currently displayed in the interview list |
| `JOB_ROLE`            | The role the applicant is interviewing for                | Must contain only alphanumeric characters and spaces                                                                                                    |
| `START_DATE_AND_TIME` | Starting time of the interview                            | Must be one of the accepted formats stated below AND must be before the end time AND must be on the same day as the end time                            |
| `END_DATE_AND_TIME`   | Ending time of the interview                              | Must be one of the accepted formats stated below AND must be after the start time AND must be on the same day as the start time                         |

* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* To edit the `RATING` field (re-rating an interview), please refer to the [rate command](#rating-an-interview--rate)
* List of accepted date formats:
    * DD/MM/YYYY and time:
        * `16 May 2024 1515`
        * `16 May 2024 3.15pm`
        * `16 May 2024 3pm`
        * `16-05-2024 1515`
        * `16-05-2024 3.15pm`
        * `16-05-2024 3pm`
        * `16-05-24 1515`
        * `16-05-24 3.15pm`
        * `16-05-24 3pm`
        * `16/05/2024 1515`
        * `16/05/2024 3.15pm`
        * `16/05/2024 3pm`
        * `16/05/24 1515`
        * `16/05/24 3.15pm`
        * `16/05/24 3pm`
    * MM, DD and time:
        * `16 May 1515`
        * `16 May 3.15pm`
        * `16 May 3pm`
        * `16 January 1515`
        * `16 January 3.15pm`
        * `16 January 3pm`
        * `16/5 1515`
        * `16/5 3.15pm`
        * `16/5 3pm`
        * `16/05 1515`
        * `16/05 3.15pm`
        * `16/05 3pm`

Examples:
*  `edit-i 1 jr/software-engineer` Edits the job title of the 1st interview to be `software-engineer`.
*  `edit-i 2 jr/data-analyst` Edits the job title of the 2nd interview to be `data-analyst`.

[Back to the Table of Contents](#table-of-contents)

### Finding interview by job title : `find-i`

Find interviews which jobs title contain any of the given keywords.

Format: `find-i KEYWORD [MORE_KEYWORDS]`

| Parameter         | Representation                             | Constraints                                                                                 |
|-------------------|--------------------------------------------|---------------------------------------------------------------------------------------------|
| `KEYWORD`         | The job title the interview is assigned of | Each keyword must not contain any spaces, otherwise it will be treated as multiple keywords |

* The search is case-insensitive. e.g. `ANALYST` will match `analyst`
* The order of the keywords does not matter. e.g. `Software Engineer` will match `Engineer Software`
* Only the job title is searched.
* Only full words will be matched e.g. `Analyst` will not match `Analysts`
* Interviews matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Software Engineer` will return interviews with the job title of `Software Developer` and `System Engineer`

Examples:
* `find-i software data` returns `Software Engineer` and `Data Analyst`.

[Back to the Table of Contents](#table-of-contents)

### Listing all interviews : `list-i`

Shows a list of all interviews in the address book onto the GUI.

Format: `list-i`

[Back to the Table of Contents](#table-of-contents)

### Listing all free time for the given day : `list-freetime`

Lists all the free time for the given `DATE`.

Format: `list-freetime DATE`

| Parameter | Representation                                                   | Constraints                                                                                                                                                                  |
|-----------|------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `DATE`    | The date which the user wants to view the list of free times for | The input date must have a **4-digit year**, and cannot be in the past. Must follow the format of the table of accepted date formats, shown [below](#accepted-date-formats). | 

* Displays a list of all the blocks of free time the user has in
  the given day, within the 9am to 5pm window of that day

* If there is no free time, there will be no blocks of 
free time that will be displayed
  * This indicates that the entire day is not free

* If there are no interviews on that day, the block of free time
listed will be: `from: 09:00 to: 17:00`
  * This indicates that the entire day is free

* If the `DATE` not valid at all, this error message will
be shown:`Please specify a valid date!`

* If the `DATE` is valid but in the past, this error message will
be shown:`Input date cannot be in the past!`

#### Accepted date formats
| Format               | Example      | Constraints                                                                                                                                                                                                                                                                                                                                                                                                   |
|----------------------|--------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `dd/mm/yyyy`         | `12/12/2099` | - If the input string is a **valid** time string with day, month and year, adding additional parameters separated by a non-number string will be accepted. <br/><br/>- The year must be **valid**, else the current year will be taken as the year of the date, or the next year of the current year in the case where the day and month when the command was executed has since passed for the current year. | 
| `dd-mm-yyyy`         | `12-12-2099` | - If the input string is a **valid** time string with day, month and year, adding additional parameters separated by a non-number string will be accepted. <br/><br/>- The year must be **valid**, else the current year will be taken as the year of the date, or the next year of the current year in the case where the day and month when the command was executed has since passed for the current year. | 
| `dd/mm`              | `12/12`      | - Adding additional parameters separated by a non-number string will be accepted. <br/><br/>- The month and year must be **valid**.                                                                                                                                                                                                                                                                           |
| `other time formats` | `12/01-2024` | - Will be accepted within reasonable bounds <br/><br/>- **Will lead to unexpected/unintended behaviour**.<br/><br/>- _Use with caution_                                                                                                                                                                                                                                                                       |

[Back to the Table of Contents](#table-of-contents)

### Listing all interviews for today : `list-i-today`

Displays all the interviews that the user has on the day the 
command is executed.

Format: `list-i-today`

* Lists all interviews that have a start date that falls on the 
day on which the user executed the command
  * For example, if the user executed this command on `12/12/2023`, 
the app will display all the interviews that the user has scheduled
on `12/12/2023`
* If there are no interviews scheduled on the day on which the command
was executed, the app will not display any interviews 
* Upon successful execution of the command, this message will be 
shown: `Listed all interviews today`

Example: `list-i-today`

[Back to the Table of Contents](#table-of-contents)

### Marking an interview : `mark`

Mark the specified `INTERVIEW_INDEX` in the **InterviewHub** as done.

Format: `mark INTERVIEW_INDEX`

| Parameter         | Representation                                                     | Constraints                                                                                                                                                                                                            |
|-------------------|--------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INTERVIEW_INDEX` | - The index of the target interview as shown in the interview list | Must be a positive unsigned integer within the range 1 to n inclusive, where n is the number of interviews currently displayed in the interview list.<br/><br/>- Trailing zeros before a valid index will be accepted. |

Examples:
* `mark 1` marks the first interview shown on the list as done.
* `mark 3` marks the third interview shown on the list as done.

[Back to the Table of Contents](#table-of-contents)

### Rating an interview : `rate`

Rate the specified `INTERVIEW_INDEX` in the **InterviewHub** with the indicated `RATING`.

Format: `rate INTERIVEW_INDEX RATING`

| Parameter         | Representation                                                   | Constraints                                                                                                                                             |
|-------------------|------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INTERVIEW_INDEX` | The index of the target interview as shown in the interview list | Must be a positive unsigned integer 1, 2, 3, …​ The upper limit of valid integers is the number of interviews currently displayed in the interview list |
| `RATING`          | The rating number to be assigned to the interview indicated      | Must be a positive unsigned one decimal place number between 0.0 to 5.0 inclusive                                                                       |

<div markdown="block" class="alert alert-info">
**:information_source: Note about the command usage.**<br>
* The interview has to be marked done before it is can be rated.
* The new rating will always replace the existing rating.
</div>

Examples:
* `rate 1 3.0` rates the first interview with a rating of 3.0.

Before rating the interview at `INTERVIEW_INDEX` 1:
![beforerate](images/beforerate.png)

After rating the interview at `INTERVIEW_INDEX` 1 with a `RATING` of 4.0:
![afterrate](images/afterrate.png)

[Back to the Table of Contents](#table-of-contents)

### Listing all completed interview : `list-i-done`

Shows a list of all the interviews in **InterviewHub** that are done onto the GUI.

Format: `list-i-done`

[Back to the Table of Contents](#table-of-contents)

### Listing all incomplete interview : `list-i-not-done`

Show a list of all the interviews in **InterviewHub** that are not done onto the GUI.

Format:`list-i-not-done`

Before `list-i-not-done`:
![beforelistinotdone](images/beforelistinotdone.png)

After `list-i-not-done`:
![afterlistinotdone](images/afterlistinotdone.png)

[Back to the Table of Contents](#table-of-contents)

### Sorting interviews by rating : `sort-rate`

Sort the shown interview list by rating in descending order (highest to the lowest rating).

Format: `sort-rate`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** 
To sort the full unfiltered interview list, use the command `list-i` before using `sort-rate`.
</div>

Before `sort-rate`:
![beforesort](images/beforesort.png)

After `sort-rate`:
![aftersort](images/aftersort.png)

[Back to the Table of Contents](#table-of-contents)

### Sorting interviews by start-time : `sort-time`

Sort the shown interview list by interview start time
in chronologically ascending order (interviews with earlier start times
will be shown first)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:** 
To sort the full unfiltered interview list, use the command `list-i` before using `sort-time`.
</div>

Format: `sort-time`

[Back to the Table of Contents](#table-of-contents)


### Editing the data file

InterviewHub data are saved automatically as a JSON file `[JAR file location]/data/interviewhub.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Warning:**
If your changes to the data file makes its format invalid, InterviewHub will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

[Back to the Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

# Frequently Asked Questions

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InterviewHub home folder.

[Back to the Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

# Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

[Back to the Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

# Command Summary

## General Commands

| Action                                  | Format, Examples |
|-----------------------------------------|------------------|
| **Clear all applicants and interviews** | `clear`          |
| **Help**                                | `help`           |
| **Exit**                                | `exit`           |

## Applicant Management Commands

| Action                   | Format, Examples                                                                                                                                                                                                 |
|--------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add applicant**        | `add-a n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]` <br> e.g., `add-a n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`                                          |
| **Delete applicant**     | `delete-a APPLICANT_INDEX`<br> e.g., `delete-a 3`                                                                                                                                                                |
| **Edit applicant**       | `edit-a APPLICANT_INDEX [n/NAME] [t/INTERVIEW_DATETIME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`<br> e.g.,`edit-a 2 n/John Doe`                                                                                   |
| **Find applicant**       | `find-a [n/KEYWORDS [MORE_KEYWORDS]...] [p/NUMBER] [e/KEYWORDS [MORE_KEYWORDS]...] [a/KEYWORDS [MORE_KEYWORDS]...] [t/KEYWORDS [MORE_KEYWORDS]...]` <br> e.g., `find-a n/John Bob p/98765432 e/John@example.com` |
| **List applicants**      | `list-a`                                                                                                                                                                                                         |

## Interview Management Commands

| Action                           | Format, Examples                                                                                                                                         |
|----------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add interview**                | `add-i app/APPLICANT_INDEX jr/JOB_TITLE time/INTERVIEW_DATETIME` <br> e.g., `add-i app/3 jr/Software Engineer start/03-11-2024 1500 end/03-11-2024 1600` |
| **Delete interview**             | `delete-i INTERVIEW_INDEX`<br> e.g., `delete-i 3`                                                                                                        |
| **Edit interview**               | `edit-i INTERVIEW_INDEX [app/APPLICANT_INDEX] [jr/JOB_TITLE] [time/INTERVIEW_DATETIME]`<br> e.g.,`edit-i 2 jr/software-engineer`                         |
| **Find interview by job**        | `find-i KEYWORD [MORE_KEYWORDS]`<br> e.g., `find-i software-engineer`                                                                                    |
| **List interview**               | `list-i`                                                                                                                                                 |
| **List free time**               | `list-freetime INTERVIEW_DATETIME` <br> e.g, `list-freetime 12-12-2024`                                                                                  |
| **List interview for today**     | `list-i-today`                                                                                                                                           |
| **Mark interview as done**       | `mark INTERVIEW_INDEX` <br> e.g., `mark 3`                                                                                                               |
| **Rate interview**               | `rate INTERVIEW_INDEX RATING` <br> e.g., `rate 1 3.0`                                                                                                    |
| **List completed interview**     | `list-i-done`                                                                                                                                            |
| **List incomplete interview**    | `list-i-not-done`                                                                                                                                        |
| **Sort interview by rating**     | `sort-rate`                                                                                                                                              |
| **Sort interview by start time** | `sort-time`                                                                                                                                              |

[Back to the Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

# Glossary

| Term          | Definition                                                                                                                                                                                               |
|---------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **JAR File**  | JAR (Java Archive) files are archive files that include a Java-specific manifest file. They are built on the ZIP format and typically have a `.jar` file extension.                                      |
| **JSON File** | JSON (JavaScript Object Notation) is a lightweight data interchange format that is easy to understand and use.                                                                                           |
| **CLI**       | A CLI (Command Line Interface) is a means of interacting with a computer program by inputting lines of text called command-lines.                                                                        |
| **GUI**       | A GUI (Graphical User Interface) is user interface that allows users to interact with the program with interactive visual components instead of text-based UIs, typed command labels or text navigation. |

[Back to the Table of Contents](#table-of-contents)
