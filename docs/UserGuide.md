---
layout: page
title: User Guide
---

Interview Hub (IH) is a **desktop app for scheduling job interviews and managing applicants,
optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, IH can get your Interview management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Placeholder TBD

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding an applicant: `add-applicant`

Adds an applicant to the address book.

Format: `add-applicant JOB_INDEX n/NAME t/INTERVIEW_DATETIME p/PHONE_NUMBER e/EMAIL a/ADDRESS`

Examples:
* `add-applicant 1 n/John Doe t/12/03/2023 3pm p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

### Adding a job: `add-job`

Adds a job to the address book.

Format: `add-job j/JOB_TITLE d/DESCRIPTION`

Examples:
* `add-job j/software engineer d/develop software`

### Listing all applicants : `list-applicants`

Shows a list of all applicants in the address book.

Format: `list-applicants`

### Listing all jobs : `list-jobs`

Shows a list of all jobs in the address book.

Format: `list-jobs`

### Editing an applicant : `edit-applicant`

Edits an existing applicant in the address book.

Format: `edit-applicant APPLICANT_INDEX [n/NAME] [t/INTERVIEW_DATETIME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

* Edits the applicant at the specified `APPLICANT_INDEX`. The index refers to the index number shown in the displayed applicant list.
* The index **must be a positive integer** 1, 2, 3, …​
* Job index is fixed and cannot be edited, to change the job of an applicant, delete the applicant and add a new one.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit-applicant 1 n/John Doe` Edits the name of the 1st applicant to be `John Doe`.
*  `edit-applicant 2 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 2nd applicant to be `91234567` and `johndoe@example.com` respectively

### Editing a job : `edit-job`

Edit an existing job in the address book.

Format: `edit-job JOB_INDEX [j/JOB_TITLE] [d/DESCRIPTION]`

* Edits the job at the specified `JOB_INDEX`. The index refers to the index number shown in the displayed job list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit-job 1 j/software engineer` Edits the job title of the 1st job to be `software engineer`.
*  `edit-job 2 d/develop software` Edits the description of the 2nd job to be `develop software`.

### Deleting an applicant : `delete-applicant`

Deletes the specified applicant from the address book.

Format: `delete-applicant INDEX`

* Deletes the applicant at the specified `INDEX`.
* The index refers to the index number shown in the displayed applicant list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete-applicant 1` deletes the 1st applicant in the address book.

### Deleting a job : `delete-job`

Deletes the specified job from the address book.

Format: `delete-job INDEX`

* Deletes the job at the specified `INDEX`.
* The index refers to the index number shown in the displayed job list.
* The index **must be a positive integer** 1, 2, 3, …​
* Deleting a job will also delete all applicants applying for the job.

Examples:
* `delete-job 1` deletes the 1st job in the address book, along with all applicants applying for the job.

### Clearing all applicants : `clear-applicants`

Clears all applicants from the address book.

Format: `clear-applicants`

### Clearing all jobs : `clear-jobs`

Clears all jobs from the address book.

Format: `clear-jobs`

* Clearing all jobs will also clear all applicants.

### Clearing all data : `nuke`

Clears all data from the address book.

Format: `nuke`

* BOOM.

### Find applicants for a given job: `find-applicants-for-job`

Finds applicants applying for a job with the given job index.

Format: `find-applicants-for-job JOB_INDEX`

* The index refers to the index number shown in the displayed job list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `find-applicants-for-job 1` returns all applicants for the 1st job in the address book.

### Finding applicants (and their index in address book) by name: `find-applicants`

Finds applicants whose names contain any of the given keywords.

Format: `find-applicants KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Applicants matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Finding jobs (and their index in address book) by title: `find-jobs`

Find jobs whose titles contain any of the given keywords.

Format: `find-jobs KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `ANALYST` will match `analyst`
* The order of the keywords does not matter. e.g. `Software Engineer` will match `Engineer Software`
* Only the job description is searched.
* Only full words will be matched e.g. `Analyst` will not match `Analysts`
* Applicants matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Software Engineer` will return `Software Developer`, `System Engineer`

Examples:
* `find-jobs software data` returns `Software Engineer` and `Data Analyst`.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

InterviewHub data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

InterviewHub data are saved automatically as a JSON file `[JAR file location]/data/interviewhub.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, InterviewHub will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

### Entering times

Accept multiple date formats

Example usage: add interview with Naruto time/ 12/03/2023 3pm

Format: `time/ <Accepted Time Format(see list for a list of accepted time formats)>`

List of accepted date formats:
* Day, Month & Year formats(only DD*MM*YY/YYYY formats accepted):
  * 12/03/2023
  *  12/03/23
  *  12/03/2023 5pm
  *  12/03/2023 1700
  *  12/03/2023 5.03pm
  *  12/03/23 5pm
  *  12-03-2023
  *  12-03-2023 5pm
  *  12-03-23
  *  12-03-23 5pm
  *  12th December 2023
  *  12th December 2023 5pm
* Day & Month formats
  * 12/03
  *  12/03 5pm
  *  12-03 5pm
  *  12-03
  *  12th December
  *  12th December 5pm
  *  12th Dec
  *  12th Dec 5pm
* Day formats
  *  Mon/tues/wed/…/sun
  *  Monday/Tuesday/Wednesday/…/Sunday
  *  Today
  *  Tomorrow
  *  Next week
  *  Next month

Other features:
  * When the user enters the date properly: `added <interview description> at <time>`
  * When the user does not input the date properly: `“Oops! Please enter a valid date String!”`
  * When there is an interview clash: `“Oops! You have an <insert interview object> scheduled at <from date & by date>`


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InterviewHub home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Applicant** | `add-applicant JOB_INDEX n/NAME t/INTERVIEW_DATETIME p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g., `add-applicant 1 n/John Doe t/12/03/2023 3pm p/98765432
**Add Job** | `add-job j/JOB_TITLE d/DESCRIPTION` <br> e.g., `add-job j/software engineer d/develop software`
**Clear Applicants** | `clear-applicants`
**Clear Jobs** | `clear-jobs`
**Clear All** | `nuke`
**Delete Applicant** | `delete-applicant INDEX`<br> e.g., `delete-applicant 3`
**Delete Job** | `delete-job INDEX`<br> e.g., `delete-job 3`
**Edit Applicant** | `edit-applicant APPLICANT_INDEX [n/NAME] [t/INTERVIEW_DATETIME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`<br> e.g.,`edit-applicant 2 n/John Doe
**Edit Job** | `edit-job JOB_INDEX [j/JOB_TITLE] [d/DESCRIPTION]`<br> e.g.,`edit-job 2 j/software engineer`
**Find Applicants** | `find-applicants KEYWORD [MORE_KEYWORDS]`<br> e.g., `find-applicants John`
**Find Applicants for Job** | `find-applicants-for-job JOB_INDEX`<br> e.g., `find-applicants-for-job 1`
**Find Jobs** | `find-jobs KEYWORD [MORE_KEYWORDS]`<br> e.g., `find-jobs software`
**Help** | `help`
**List Applicants** | `list-applicants`
**List Jobs** | `list-jobs`
**Exit** | `exit`
