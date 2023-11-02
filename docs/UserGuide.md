---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# TutorMate User Guide

TutorMate is a desktop app targeted to tuition teachers for managing contacts of tuition students.

It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TutorMate can get your administrative tasks done faster than traditional GUI apps.

This project is based on the [AddressBook-Level3 project](https://se-education.org).

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## About
This document describes the main features of TutorMate. The main features of TutorMate are the Student Management 
System, Schedule of Lessons and Task List of each lesson.
With a customised student list and schedule in TutorMate just for you, organising lessons and managing students 
will be the least of your worries.
<br>
<br>
![Ui](images/about.png)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tutormate.jar` from [here](https://github.com/AY2324S1-CS2103T-T11-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TutorMate.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tutormate.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list students` : Lists all students with their `NAME`.

    * In list `STUDENTS`:     

      * `add -name Leah` : Adds a student named `Leah` to the application.

      * `show 3` : Shows the details of the person with the index 3 in TutorMate.

    * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add -name NAME`, `NAME` is a parameter which can be used as `add -name Leah`.

* Items in square brackets are optional.<br>
  e.g `list [KEYWORDs]` can be used as `list` or as `list SUBJECT`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.


### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a student: `addPerson`

Adds a student to the contact list in application.

Format: `addPerson -name NAME [-phone PHONE_NUMBER] [-email EMAIL] [-address ADDRESS] 
[-subject SUBJECT] [-tag TAG] [-remark REMARK]`



<box type="tip" seamless>


**Tips:** 
- A student can have any number of unique tags (including 0)
- If the user is currently in list `STUDENTS`, the command can be shortened to `add`
</box>

Examples:
* `addPerson -name John -phone 91234567`
* `addPerson -name John -phone 91234567 -email test@gmail.com -address 10 Kent Ridge Drive -subject MATHEMATICS`
* In list `STUDENTS`:
  * `add -name John -phone 91234567`
  * `add -name John -phone 91234567 -email test@gmail.com -address 10 Kent Ridge Drive -subject MATHEMATICS`



### Listing all students : `list`

The list command has different behaviours depending on the keywords given.
Lists all the students, lessons and tasks saved in the application, with optional specified information through space-separated keywords.

To show the `SCHEDULE` list:
* Format: `list schedule`
* By default, `list` will also show the `SCHEDULE` list and list all the lessons with their `NAME`.


To show the `STUDENTS` list:
* Format: `list students [KEYWORDs]`
  * displays all the students with their `NAME`.
  * The `[KEYWORDs]` allows for a list of valid space-separated information of the student to be displayed.

To show the `TASKS` list:
* Format: `list tasks`
  * * displays all the tasks with their `DESCRIPTION`.

Examples:
* `list` displays the `SCHEDULE` list with all the lessons.
* `list schedule` displays all the lessons with their `NAME`.
* `list students` displays all the students with their `NAME`.
* `list students subjects` displays all the students with their `NAME` and a list of subjects for each student.
* `list tasks` displays all the tasks with their `DESCRIPTION`.

Acceptable value for the parameter: Any valid property of a student, such as:
* SUBJECT
* PHONE NUMBER


Success Output:
* For the command `list`:
```
Here is the list of students:
1. Leah
2. John
3. Adam
```
* For the command `list SUBJECT,PHONE NUMBER`:
```
Here is the list of students:
1. Leah
   Subjects: Chemistry, Math
   Phone Number: 98765432
2. John
   Subjects: Math, Biology
   Phone Number: 98125132
3. Adam
   Subjects: Physics
   Phone Number: 98777732
```

Failure Output:
* When there are no students saved in the app: `There are no students saved currently.`
* When there are invalid keywords specified as a parameter: `Sorry, please only specify valid keywords.`


### Editing a student : `edit`

Edits an existing student in the application.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.

### Locating students by name: `find`

The find command has different behaviours depending on the current list:

1. In `STUDENTS` list:
    - Finds students whose names contain any of the given keywords.
2. In `SCHEDULE` list:
    - TBC

Format: `find KEYWORD [MORE_KEYWORDS]`

1. In `STUDENTS` list:
   * The search is case-insensitive. e.g `hans` will match `Hans`
   * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
   * Only the name is searched.
   * Only full words will be matched e.g. `Han` will not match `Hans`
   * Persons matching at least one keyword will be returned (i.e. `OR` search).
     e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`
2. In `SCHEDULE` list:
    * TBC

Example Success Output:
```
1 persons listed!
```

Failure Output:
```
0 persons listed!
```

### Showing a student's details : `show`

The show command has different behaviours depending on the current list:

1. In `STUDENTS` list:
   - Shows the details of the specified student from the contact list in the application.
2. In `SCHEDULE` list:
   - Shows the details of the specified lesson from the schedule list in the application.
3. In `TASKS` list:
   - Shows the details of the specified task from the full task list in the application.

Format: `show INDEX`

* Shows the details of the student/lesson/task at the specified `INDEX`.
* The index refers to the index number shown in the displayed student/schedule/task list.

* The index **must be a positive integer** 1, 2, 3, …​

Examples:
1. In `STUDENTS` list:
   * `list STUDENTS` followed by `show 2` shows the details of the 2nd student in the student list.
   * `find Betsy` followed by `show 1` shows the details of the 1st student in the results of the `find` command.
2. In `SCHEDULE` list:
   *  `list SCHEDULE` followed by `show 2` shows the details of the 2nd lesson in the schedule list.
3. In `TASKS` list:
    *  `list TASKS` followed by `show 2` shows the details of the 2nd task in the full task list.

Example Success Output:
```
Showing Person: Alex Yeoh; Phone: 87438807; Email: alexyeoh@example.com; Address: Blk 30 Geylang Street 29, #06-40; Subjects: [BIOLOGY]; Tags: [friends]
```

Failure Output:
```
The person index provided is invalid
```


### Deleting a student : `delete`

The delete command has different behaviours depending on the current list:

1. In `STUDENTS` list:
    - Deletes the specified student from the contact list in the application.
2. In `SCHEDULE` list:
    - Deletes the specified lesson from the lesson list in the application. 
3. In `TASKS` list:
    - the `delete` command is disabled. Adding and Deleting of Tasks can only be done in the `SCHEDULE` list via the `addTask` and `deleteTask` command.

Format: `delete INDEX`

* Deletes the student/lesson at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list students` followed by :
  * `delete 2` deletes the 2nd student in the contact list.
  * `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

* `list schedule` followed by :
* `delete 2` deletes the 2nd lesson in the schedule list.

Success Output:
```
Student Leah has been deleted successfully!
```

Failure Output:
```
Index out of bounds, expected 1 to 8 but got 10.
```

### Adding a task : `addTask`
Adds a task to the specified lesson.

Format: `addTask INDEX [-description TASKDESCRIPTION]`

* Adds the task to the lesson at specified `INDEX`.
* The index refers to the index number shown in the displayed schedule list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list schedule` followed by `addTask 2 -description mark homework` adds a task to the second lesson in the schedule.

Success Output:
```
New task added to lesson with index 2: -mark homework
```

Failure Output:
```
No lesson with index 10!
Lesson index has to be a positive value!
```

### Deleting a task : `deleteTask`
Deletes the specified task from the shown lesson in the application.

Format: `deleteTask INDEX`

* Deletes the task at the specified `INDEX` of the task list in lesson.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `show 1` followed by `delete 2` deletes the 2nd task of the 1st lesson in the schedule.

Success Output:
```
Deleted Task: -mark extra practice questions 
```

Failure Output:
```
Task index do not belong to any tasks!

Invalid command format! 
deleteTask: Deletes the task identified by the task index from the currently displayed lesson .
Parameters: task index (must be a positive integer)
Example: deleteTask 1
```

### Exiting the program : `exit`

Exits the program.

Format: `exit`


### Saving the data

TutorMate data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Editing the data file

TutorMate data is saved automatically as a JSON file `[JAR file location]/data/tutormate.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, TutorMate will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.
</box>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add Person**    | `addPerson -name John -phone 91234567 -email test@gmail.com -address 10 Kent Ridge Drive -subject MATHEMATICS`
**Add Task**    | `addTask -description Do CS2103T Preparation`
**Delete Task** | `delete INDEX`<br> e.g., `delete 3`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com` (To Be Changed)
**Show**   | `show INDEX`
**List**   | `list`, `list schedule`, `list students [KEYWORDs]`, `list tasks`
**Help**   | `help`

