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

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tutormate.jar` from [here](https://github.com/AY2324S1-CS2103T-T11-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TutorMate.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tutormate.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all students with their `NAME`.

    * `add n/Leah p/98765432 e/leah@example.com a/Evergreen street, block 123, #01-01` : Adds a student named `Leah` to the application.

    * `show 3` : Shows the details of the person with the index 3 in TutorMate.

    * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Leah`.

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


### Adding a student: `add`

Adds a student to the contact list in application.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A student can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`


### Listing all students : `list`

Lists all the students saved in the application, with optional specified information through comma-separated keywords.

Format: `list [KEYWORDs]`

* By default, without any keywords the app lists all the students with their `NAME`.
* The `[KEYWORDs]` allows for a list of comma-separated information.

Examples:
* `list` displays all the students with their `NAME`.
* `list SUBJECT` displays all the students with their `NAME` and a list of subjects for each student.

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


### Showing a student's details : `show`

Shows the details of the specified student from the contact list in the application.

Format: `show INDEX`

* Shows the details of the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `show 2` shows the details of the 2nd student in the contact list.
* `find Betsy` followed by `show 1` shows the details of the 1st student in the results of the `find` command.

Success Output:
```
Leah
Subjects: Chemistry, Math
Phone Number: 98765432
Email: leah@example.com
Address: Evergreen street, block 123, #01-01
```

Failure Output:
```
Invalid Index entered, please try showing the contact again with the correct Index!
```


### Deleting a student : `delete`

Deletes the specified student from the contact list in the application.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the contact list.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

Success Output:
```
Student Leah has been deleted successfully!
```

Failure Output:
```
Invalid Index entered, please try deleting the contact again with the correct Index!
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
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Show**   | `show INDEX`
**List**   | `list [KEYWORDs]`
**Help**   | `help`
