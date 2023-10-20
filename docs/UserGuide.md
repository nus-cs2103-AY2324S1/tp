---
layout: page
title: User Guide
---

TAvigator is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still 
having the benefits of a Graphical User Interface (GUI). If you can type fast, TAvigator can get your contact management 
tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tavigator.jar` from [here](https://github.com/AY2324S1-CS2103T-T09-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TAvigator.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tavigator.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to TAvigator.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `|` indicate that the command accepts either parameters.<br>
  e.g `mark /name STUDENTNAME | /id STUDENTID` takes in `STUDENTNAME` or `STUDENTID` as its first argument.

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


### Adding a new contact: `add`

Creates a new contact with the specified name and course code.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL id/STUDENT_ID [t/COURSE_CODE TUTORIAL_GROUP]...`

Examples:
* `add n/Fu Yiqiao p/91234567 e/fyq@gmail.com id/A1234567E`
* `add n/Fu Yiqiao p/91234567 e/fyq@gmail.com id/A1234567E t/CS2103T G2`

### Marking attendance of student: `mark`

Format: `mark /name STUDENTNAME | /id STUDENTID /attendance ATTENDANCE`
* Marks the attendance of a student corresponding to the student name or student ID.
* `STUDENTNAME` should be a string made up of alphabetical characters, with no numbers or special characters.
* `STUDENTID` should be a string made up of alphabetical characters, with no special characters or space.
* `ATTENDANCE` should only be 0 or 1, where 0 indicates student is absent and 1 indicates student is present.

Examples:
* `mark /name Zong Jin /attendance 1` Marks student named, Zong Jin, as present.
* `mark /name Zong Jin /attendance 0` Marks student named, Zong Jin, as absent.
* `mark /id A0123456E /attendance 1` Marks student with student ID, A0123456E, as present.
* `mark /id A0123456E /attendance 0` Marks student with student ID, A0123456E, as absent.

### Viewing summary of attendance : `list attendance`

Shows a summary of attendance records including list of absentees.

Format: `list attendance tn/TUTORIALNO [coursetg/TAG]`
*  Shows a list of absentees and summary of the attendance records of students corresponding to the tag for the specified tutorial number.

Examples:
*  `list attendance tn/1` Shows a summary of attendance records of all students for Tutorial #1.
*  `list attendance tn/3 coursetg/CS2103T` Shows a summary of attendance records of the students in CS2103T for Tutorial #3.

### Searching for student's contact via keyword : `find`

Finds a student's contact either via their name or student ID.

Format: `find /name STUDENTNAME | /id STUDENTID`

* Finds a student's contact either via their name or student ID.
* `STUDENTNAME` should be a string made up of alphabetical characters, with no numbers or special characters.
* `STUDENTID` should be a string made up of alphabetical characters and numbers, with no special characters or space.

Examples:
*  `find /name Anthony` Finds all contacts with the name "Anthony".
*  `find /id A0123456H` Finds all contacts with the student ID "A0123456H".

### Listing students : `list students`

Shows a list of students.

Format: `list students`
*  Shows a list of all students.

### Editing a contact : `edit`

Edits the contact details.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [id/STUDENT_ID] [t/COURSE_CODE TUTORIAL_GROUP]...`

* Edits the person at the specified INDEX. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing t/ without specifying any tags after it.

Examples:
*  `edit 1 n/Tan Liyan` Edits the name of the first person to be Tan Liyan.
*  `edit 2 p/92345678 t/` Edits the phone number of the second person and removes all tags.

### Adding a filter: `filter add`

Shows a list of students from a specified tutorial group

Format: `filter add coursetg/COURSECODE [tn/TUTORIALGROUPID]`

* Filters students that are in the tutorial group specified by `TUTORIALGROUPID` or course specified by `COURSECODE`
* `COURSECODE` should be a string made up of alphabetical characters and numbers, with no special characters.
* `TUTORIALGROUPID` should be a string made up of alphabetical characters and numbers, with no special characters.
* `TUTORIALGROUPID` must correspond to an existing tutorial group.
* `COURSECODE` must correspond to an existing course.
* `COURSECODE` must be specified.
* `TUTORIALGROUPID` is optional.

Examples:
* `filter add coursetg/CS2103T tn/G08` returns a list of students from tutorial group G08 for course CS2103T.
* `filter add coursetg/CS2103T` returns a list of students in the course CS2103T.

### Removing filters: `filter remove`

Removes specified applied filter

Format: `filter remove coursetg/COURSECODE [tn/TUTORIALGROUPID]`

* Remove the tutorial group filter specified by `TUTORIALGROUPID` or course filter specified by `COURSECODE`
* `COURSECODE` should be a string made up of alphabetical characters and numbers, with no special characters.
* `COURSECODE` must correspond to an existing course.
* `TUTORIALGROUPID` should be a string made up of alphabetical characters and numbers, with no special characters.
* `TUTORIALGROUPID` must correspond to an existing tutorial group.
* `COURSECODE` must be specified.
* `TUTORIALGROUPID` is optional.

Examples:
* `filter remove` returns the list of all students
* `filter remove coursetg/CS2103T tn/G08` returns a list of students containing those from tutorial group G08 for course CS2103T.
* `filter remove coursetg/CS2103T` returns a list of students containing those in the course CS2103T.

### Removing all filters: `filter clear`

Removes all applied filters

Format: `filter clear`

Examples:
* `filter clear` returns the list of all students

### Deleting a person : `delete`

Deletes the specified person from TAvigator.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in TAvigator.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the TAvigator.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TAvigator data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TAvigator data are saved automatically as a JSON file `[JAR file location]/data/tavigator.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TAvigator will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAvigator home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary
| Action     | Format, Examples                                                                                |
|------------|-------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL id/STUDENT_ID [t/COURSE_CODE TUTORIAL_GROUP]...` <br>        |
| **Clear**  | `clear`                                                                                         |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                             |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [id/STUDENT_ID] [t/COURSE_CODE TUTORIAL_GROUP]...`<br> |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                      |
| **List**   | `list attendance tn/TUTORIALNO [coursetg/TAG]` `list students`                                  |                                                           |
| **Help**   | `help`                                                                                          |
| **Filter**   | `filter [add/delete/clear] [coursetg/COURSECODE] [tn/TUTORIALGROUPID]`                                                                                          |
