---
layout: page
title: User Guide
---

WellNUS is a **desktop application used by NUS Counsellors to manage and schedule appointments with their student clients**
It is optimised for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, WellNUS can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. Getting started

####  1.1 Accessing the app

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `wellnus.jar` from [here](https://github.com/AY2324S1-CS2103T-W13-4/tp).

3. Copy the file to the folder you want to use as the _home folder_ for WellNUS.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar wellnus.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `exit` : Exits the app.
6. Refer to the [Features](#features) below for details of each command.

#### 1.2 Prefixes and parameters used in commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME c/CONTACT_NUMBER`, `NAME` and `CONTACT_NUMBER` are parameters
  which can be used as `add n/John Doe c/98172645`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

--------------------------------------------------------------------------------------------------------------------

## 2. Features

### 2.1 Utility Commands

#### 2.1.1 Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### 2.2 Student Commands

#### 2.2.1 Adding a Student: `add`

Adds a student with his/her relevant details.

Format: `add n/NAME c/CONTACT_NUMBER a/HOME_ADDRESS`

**Parameters**:
1. Name
   - Alphabetical characters only
2. Contact Number
   - Numerical characters only, must be 8 characters long
3. Home Address
   - No restrictions

Examples:
* `add n/John c/81349705 a/Yishun Street 56 Blk 21 #05-07`
* `add n/Sally c/94149785 a/Woodlands Street 11 Blk 888 #08-08`

#### 2.2.2 Deleting a Student: `delete`

Deletes an existing student.

Format: `delete <INDEX>`

**Parameters**:
1. Appointment Index
   - Index must be an integer more than 0

### 2.2.3 Adding notes for a student: `note`

Adds a note to an existing student, overwrites any existing note.

Format: `note <INDEX> note/[NOTE]`

**Parameters**:
1. Student Index
   - Index must be an integer more than 0
2. Note
   - Must be 200 characters or less, can be empty

#### 2.2.4 Editing a Student: `edit`

#### 2.2.5 Viewing all Students: `view`
Shows a list of all available students or appointments, depending on specified input.

Format: `view g/CATEGORY`

**Parameters**:
1. Category
    - Only 'students', 'appointments' or 'all'



#### 2.2.6 Finding Students by Name: `find`

Find students and their related appointments based on their name. Can choose to find student based on their first name, last name or full name
If the name does not match entirely, the student will not be shown. Refer to the examples below for a better understanding

Format: `find <NAME>`

Example Scenario:

Student Name: Roy Lee
* "find Roy" works
* "find Lee" works
* "find Roy Lee" works
* "find Ro" does not work
* "find Le" does not work
* "find Roy L" does not work

### 2.2.6 Assigning risk status to student (to be implemented)

Tags a student with a risk level based on their current mental health status, categorizing them as high, medium, low tag_student <index> <RISK_LEVEL>

Format: `tag_student <INDEX> <RISK_LEVEL>`

**Parameters**:
1. Student Index
   - The index of an existing student in the system
2. Risk Level
   - One of the following HIGH, MEDIUM, LOW

### 2.3 Appointment Commands

#### 2.3.1 Scheduling an Appointment: `schedule`

Schedules a new appointment for a student.

Format: `schedule n/NAME_OF_STUDENT date/DATE from/START_TIME to/END_TIME d/DESCRIPTION`

**Parameters**:
1. Name
    - Alphabetical characters only
2. Date
    - Date should follow yyyy-MM-dd format
3. Start time
    - Time should follow HH:mm
4. End time 
    - Time should follow HH:mm
5. Description 
    - Description should have a minimum length of 1 character and maximum length of 100 characters


Examples:
- `schedule d/monthly check-up s/2023-12-31 16:30 n/Jon`
- `schedule d/family issues consultation s/18:30 n/Kiat`

#### 2.3.2 Cancelling an Appointment: `cancel`

Cancels an existing appointment.

Format: `cancel <INDEX>`

**Parameters**:
1. Appointment Index
   - Numerical characters only

Examples:
* `cancel 2`

#### 2.3.3 Viewing all Appointments: `view`

Shows a list of all available students or appointments, depending on specified input.

Format: `view g/CATEGORY`

**Parameters**:
1. Category
   - Only 'students', 'appointments' or 'all'

#### 2.3.4 Filtering Appointments by Date: `filter`


### 2.4 Others
#### 2.4.1 Exiting the program: `exit`

Exits the program.

Format: `exit`

#### 2.4.2 Clearing storage: `clear`

#### 2.4.3 Saving the data

WellNUS data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 2.4.4 Editing the data file

WellNUS data is saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file
<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, WellNUS will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### 2.5 Tracking TODOS `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## 3. Frequently Asked Questions (FAQ)

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous WellNUS home folder.

--------------------------------------------------------------------------------------------------------------------

## 4. Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

| Action                     | Format, Examples                                                                                                                                                      |
|----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Add Student]()            | `add n/NAME c/PHONE_NUMBER a/ADDRESS` <br> e.g., `add n/John c/81349705 a/Yishun Street 56 Blk 21 #05-07`                                                             |
| [Delete Student]()         | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| [Add Student Note]()       | `note INDEX note/[NOTE]` <br> e.g., `note 1 note/Likes dogs`                                                                                                          |
| [Edit Student]()           |                                                                                                                                                                       |
| [View Students]()          |                                                                                                                                                                       |
| [Find Students]()          |                                                                                                                                                                       |
| [Assign Risk to Student]() | `tag_student INDEX RISK_LEVEL`<br> e.g.,`tag_student 4 HIGH`                                                                                                          |
| [Schedule Appointment]()   | `schedule n/NAME_OF_STUDENT date/DATE from/START_TIME to/END_TIME d/DESCRIPTION`<br> e.g., `schedule n/John Doe date/2023-12-31 from/16:30 to/17:30 d/First Session`  |
| [Cancel Appointment]()     | `cancel INDEX`<br> e.g., `cancel 3`                                                                                                                                   |
| [View Appointments]()      | `view g/CATEGORY` <br> e.g., `view g/appointments`                                                                                                                    |
| [Filter Appointments]()    | `filter DATE` <br> e.g., `filter 2023-10-16`                                                                                                                          |
| [Help]()                   | `help`                                                                                                                                                                |
| [Exit]()                   | `exit`                                                                                                                                                                |

