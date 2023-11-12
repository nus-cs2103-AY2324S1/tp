---
layout: page
title: User Guide
---

![HouR](images/ug-pics/HouR.png)
## Welcome to HouR

HouR is a **desktop app for managing employee records, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HouR can get your HR management tasks done faster than traditional GUI apps.

If you are new here, visit our [quick start](#quick-start) guide to onboard onto HouR smoothly!

<div style="page-break-after: always;"></div>

## Table of Contents

- [Quick Start](#quick-start)
- [Navigating the GUI](#navigating-the-graphical-user-interface--gui-)
- [Features](#features)
    - [Viewing Help](#viewing-help--help): `help`
    - [Adding an employee](#adding-an-employee-add): `add`
    - [Deleting an employee](#deleting-an-employee--delete): `delete`
    - [Editing an employee](#editing-an-employee--edit): `edit`
    - [Listing all employees](#listing-all-employees--list): `list`
    - [Locating employees](#finding-employees--find): `find`
    - [Sorting employees](#sorting-all-employees--sort): `sort`
    - [Adding a leave period](#adding-a-leave-period-of-an-employee-addleave): `addleave`
    - [Deleting a leave period](#deleting-a-leave-period-of-an-employee-deleteleave): `deleteleave`
    - [Editing a leave date](#editing-a-leave-date-of-an-employee--editleave): `editleave`
    - [Listing employees on leave](#listing-the-employees-on-leave-on-a-specified-date--listleave) `listleave`
    - [Adding remarks](#adding-a-remark-for-an-employee--addremark): `addremark`
    - [Deleting remarks](#deleting-a-remark-of-an-employee--deleteremark): `deleteremark`
    - [Updating overtime hours](#updating-overtime-hours-of-an-employee--overtime): `overtime`
    - [Generating an employee report](#generating-a-report--report): `report`
    - [Resetting fields](#resetting-fields--reset): `reset`
    - [Clearing all employees](#clearing-all-entries--clear): `clear`
    - [Exiting the program](#exiting-the-program--exit): `exit`
- [FAQ](#faq)
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `hour.jar` from [here](https://github.com/AY2324S1-CS2103T-W12-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your employee list.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar hour.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/ug-pics/Ui.png)

5. Type a command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `help`: Shows help window with link to user guide.

   * `add n/John Doe pos/Senior Software Enginner id/EID2023-7890 p/81239876 e/johndoe@test.com s/5000` : Adds an employee named `John Doe` to the employee list.

   * `list` : Lists all employees.
   
   * `edit 1 n/Alex Yeoh`: Edits the name of the 1st employee shown in the current list to `Alex Yeoh`.
   
   * `find Alex Manager`: Lists all employees with the keywords `Alex` or `Manager`.

   * `delete EID2023-1234` : Deletes the employee with employee id EID2023-1234 shown in the list.

   * `sort f/salary in/asc`: Sorts the employees by their salaries in ascending order.

   * `addleave id/EID1234-5678 from/2023-12-26 to/2023-12-28`: Adds leave dates from 26 to 28 December 2023 inclusive for employee with id EID1234-5678.

   * `deleteleave id/EID1234-5678 from/2023-12-27 to/2023-12-27`: Deletes leave date 27 December 2023 for employee with id EID1234-5678.
   
   * `editleave id/EID1234-5678 old/2023-12-26 new/2023-12-29`: Edits the old leave date on 26 December 2023 of employee with id EID1234-5678 to new leave date on 29 December 2023.
   
   * `listleave on/2023-12-28`: Lists all employees on leave on 28 December 2023.
   
   * `addremark id/EID1234-5678 r/good worker`: Adds the remark `good worker` to the employee with id EID1234-6788.
   
   * `deleteremark id/EID1234-5678 r/good worker`: Deletes the remark `good worker` from the employee with id EID1234-5678.
   
   * `overtime id/EID1234-5678 o/inc a/10`: Increases the overtime hours of employee with id EID1234-5678 by 10 hours.
   
   * `report EID1234-5678`: Generates a report with details of the employee with id EID1234-5678.
   
   * `reset f/overtime`: Resets the overtime hours field of all employees to the default value 0.
   
   * `clear` : Deletes all employees.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Navigating the Graphical User Interface (GUI)

HouR comes with a GUI to allow for nice visual feedback for our users. Here is a quick run-through of the different sections of our GUI, as well as some notes regarding the use of the GUI.

### Quick Orientation

![Quick Orientation](images/ug-pics/quickOrientation.png)

### Employee Card

![Employee Card](images/ug-pics/employeeCard.png)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Features

- View list of commands: `help`
- Add an employee: `add`
- Delete an employee: `delete`
- Edit an employee: `edit`
- List all employees: `list`
- Find employees: `find`
- Sort employees by attribute: `sort`
- Add leave dates for an employee: `addleave`
- Delete leave dates of an employee: `deleteleave`
- Edit leave of an employee: `editleave`
- List employees on leave: `listleave`
- Add remarks for an employee: `addremark`
- Delete remarks of an employee: `deleteremark`
- Update overtime hours of an employee: `overtime`
- Generate an employee report: `report`
- Reset specified field to default number: `reset`
- Clear all employees: `clear`
- Exit the program: `exit`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format and commands**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `edit INDEX [n/NAME] [pos/POSITION] [id/EMPLOYEE_ID] [p/PHONE_NUMBER] [e/EMAIL] [s/SALARY] [d/DEPARTMENT]...` can be used as `edit 1 n/John Doe` or as `edit 1 pos/Software Engineer`.

* Parameters with `...` after them can be used multiple times (or omitted completely).<br>
  e.g. `add . . . [d/DEPARTMENT]...` can be used as `add . . . d/IT` or `add . . .` (where `. . .` is used in place of the compulsory parameters)

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/POSITION`, `p/POSITION n/NAME` is also acceptable.

* All command words are **case-sensitive** (i.e. `add` is valid and `ADD` is invalid)

* Date parameters should be in the form of `yyyy-MM-dd`.<br>
  e.g. `2023-10-31` is a valid date parameter

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* For all commands except for `find`, `sort` and `listleave`, the entire employee list will be called after the command is executed **successfully**.<br>
  e.g. if the employee list was previously filtered through the `find` or `listleave` command, the employee list panel will change to show the entire employee list once another command (excluding `find`, `sort` or `listleave`) is run successfully.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

## General Commands

This section contains commands that are not related to any specific feature in HouR.

### Viewing help : `help`

Shows a window with link to access the help page.

Format: `help`

![help message](images/ug-pics/helpMessage.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
For Windows users, you can use keyboard shortcut **F1** to open up the help window.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Note that if you are running HouR on another monitor, the help window defaults to the centre of the primary monitor.
</div>

### Clearing all entries : `clear`

Clears all entries from the employee book.

Format: `clear`

![clear](images/ug-pics/clear.png)

### Exiting the program : `exit`

Exits the program.

Format: `exit`

## Employee Commands

This section contains commands relating to the employee list and/or its employees.

### Adding an employee: `add`

Adds an employee to the employee list.

Format: `add n/NAME pos/POSITION id/EMPLOYEE_ID p/PHONE_NUMBER e/EMAIL s/SALARY [d/DEPARTMENT]...`

* Adds the employee with the specified details.
* The `NAME`, `POSITION`, `PHONE_NUMBER`, `EMAIL` and `DEPARTMENT` parameters are **case-sensitive**.
* The `EMPLOYEE_ID` refers to each employee's **unique** employee id (must not already exist) and must follow the **EID format** (EID[4 digits]-[4 digits])
* The `SALARY` parameter only takes in **positive integers**.

Examples:
* `add n/Alex Yeoh pos/Software Engineer id/EID2023-1234 p/87428807 e/alexyeoh@example.com s/8500 d/IT`
* `add n/Jane Doe pos/Manager id/EID2023-7891 p/81234567 e/janedoe@test.com s/5000`

![add success](images/ug-pics/addSuccess.png)

* `add n/Charlotte Oliveiro pos/Sales Associate id/EID2023-1234 p/98561234 e/charlotte@example.com s/7300 d/Sales` is
  invalid because `EMPLOYEE_ID` already exists in the records.

![add failure](images/ug-pics/addFailure.png)

### Deleting an employee : `delete`

Deletes an employee from the employee list.

Format: `delete EMPLOYEE_ID`

* Deletes the employee at the specified `EMPLOYEE_ID`.
* The `EMPLOYEE_ID` refers to each employee's **unique** employee id and must follow the **EID format** (EID[4 digits]-[4 digits]).

Examples:
* `delete EID2023-1234` deletes the employee with employee id EID2023-1234 in the employee list.

![delete success](images/ug-pics/deleteSuccess.png)

* `delete EID0000-0000` is invalid because the `EMPLOYEE_ID` does not exist in the records.

![delete failure](images/ug-pics/deleteFailure.png)

### Editing an employee : `edit`

Edits an existing employee in the employee list.

Format: `edit INDEX [n/NAME] [pos/POSITION] [id/EMPLOYEE_ID] [p/PHONE_NUMBER] [e/EMAIL] [s/SALARY] [d/DEPARTMENT]...`

* Edits the employee at the specified `INDEX`. The index refers to the index number shown in the displayed employee list. 
* The `INDEX` must be a **positive integer less than or equals to** the number of employees shown in the displayed employee list 1, 2, 3, …​
* **At least one** of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 pos/Senior Software Engineer` edits the position of the 1st employee to be `Senior Software Engineer`.

![edit success](images/ug-pics/editSuccess.png)

*  `edit 100 pos/Senior Software Engineer` is invalid because the index does not exist.

![edit failure](images/ug-pics/editFailure.png)

### Listing all employees : `list`

Shows a list of all employees in the employee list.

Format: `list`

![list success](images/ug-pics/list.png)

### Finding employees: `find`

Finds employees whose name, position, department, phone number, email, or ID contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is **case-insensitive**. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only **full words** will be matched e.g. `Han` will not match `Hans`
* Employees matching **at least one keyword** will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber` and `Bo Yang`.

Examples:
* `find Roy EID1234-5678` returns `Roy Balakrishnan` and `Alex Yeoh`.

![find success](images/ug-pics/findSuccess.png)

* `find EID0000-0000` returns an empty list of employees since no employee has that ID.

![find failure](images/ug-pics/findEmpty.png)

### Sorting all employees : `sort`

Sorts employees in the employee list by a given field.

Format: `sort f/FIELD in/ORDER`

* Sorts the employee list by the specified `FIELD` in the given `ORDER`.
* The `FIELD` has to be **non-empty** and can only be one of these 4 values: `name`, `salary`, `overtime`, or `leaves`.
* The `FIELD` and `ORDER` parameters are **case-insensitive**. (e.g. `name` and `NAME` are taken to be the same)
* The `ORDER` is either ascending (`asc`) or descending (`desc`).

Examples:
* `sort f/salary in/asc` sorts the employee list such that their salaries are arranged 
  in ascending order from top to bottom

![sort success](images/ug-pics/sortSuccess.png)

* `sort f/blah in/desc` is invalid because field `blah` is not one of the 4 mentioned values.

![sort failure](images/ug-pics/sortFailure.png)

## Employee Metrics Commands

You can use the following commands to keep track of and gauge employee performance.

### Adding a leave period of an employee: `addleave`

Adds the dates between a specified period of time to the leaves taken by the specified employee.

Format: `addleave id/EMPLOYEE_ID from/START_DATE to/END_DATE`

* Add dates between `START_DATE` and `END_DATE` inclusive into the leaves taken by employee with id `EMPLOYEE_ID`.
* `EMPLOYEE_ID` must follow the **EID format** (EID[4 digits]-[4 digits]).
* `START_DATE` and `END_DATE` must be in the **YYYY-MM-DD** format.
* `START_DATE` must not be after `END_DATE`.
* Dates between `START_DATE` and `END_DATE` must not already exist in the leave list.
* The **total number of annual leaves** used cannot exceed the **maximum of 14** as per the [Singapore Ministry of Manpower's guidelines](https://www.mom.gov.sg/employment-practices/leave/annual-leave/eligibility-and-entitlement).

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the current date falls within the leave period, the **leave status** of the employee will change from **"Present"** to **"On Leave"**.
</div>

Examples:
* `addleave id/EID1234-5678 from/2023-12-26 to/2023-12-28` adds the dates 26, 27, and 28 December 2023 to the leaves taken
  by employee with id EID1234-5678.

![addleave success](images/ug-pics/addLeaveSuccess.png)

* `addleave id/EID1234-5678 from/2023-12-31 to/2023-12-28` is invalid because the start date 2023-12-31 is after the end date 2023-12-28, which is impossible.

![addleave failure 1](images/ug-pics/addLeaveFailure1.png)

* `addleave id/EID1234-5678 from/2023-12-28 to/2023-12-30`is invalid because the date 28 December 2023 already exists in the leave list.

![addleave failure 2](images/ug-pics/addLeaveFailure2.png)

### Deleting a leave period of an employee: `deleteleave`

Deletes the specified leave dates of an employee.

Format: `deleteleave id/EMPLOYEE_ID from/START_DATE to/END_DATE`

* Delete any leave dates that are between `START_DATE` and `END_DATE` inclusive from the leaves taken by employee with id `EMPLOYEE_ID`.
* `EMPLOYEE_ID` must follow the **EID format** (EID[4 digits]-[4 digits]).
* `START_DATE` and `END_DATE` must be in the **YYYY-MM-DD** format.
* `START_DATE` must not be after `END_DATE`.
* There should be **at least one existing leave taken** by the employee that falls within the period between `START_DATE` and `END_DATE`.
* If the employee does not have any leaves taken that fall anytime during the period between `START_DATE`and `END_DATE`,
  the command will output an error and will not change anything.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the current date is deleted, the **leave status** of the employee will change from **"On Leave"** to **"Present"**.
</div>

Examples:
* `deleteleave id/EID1234-5678 from/2023-12-26 to/2023-12-28` deletes all leave dates
  of employee with id EID1234-5678 that fall on 26, 27, and 28 December 2023.

![deleteleave success](images/ug-pics/deleteLeaveSuccess.png)

* `deleteleave id/EID1234-5678 from/2023-12-31 to/2023-12-28` is invalid because the start date 2023-12-31 is after the end date 2023-12-28, which is impossible.

![deleteleave failure 1](images/ug-pics/deleteLeaveFailure1.png)

* `deleteleave id/EID1234-5678 from/2023-12-28 to/2023-12-28` is invalid because there is no existing leave on 28 December 2023.

![deleteleave failure 2](images/ug-pics/deleteLeaveFailure2.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
There is currently no feature for viewing the full list of leaves taken by an employee. 
However, you can curb this by adding a "dummy" leave using **addleave** before deleting the added "dummy" leave using **deleteleave**.
You should be able to view the list of leaves taken by the employee in the Result Display.
</div>

### Editing a leave date of an employee : `editleave`

Edits the old leave date to the new leave date of the specified employee.

Format: `editleave id/EMPLOYEE_ID old/OLD_DATE new/NEW_DATE`

* Edits leave on `OLD_DATE` to `NEW_DATE` of the specified employee with id `EMPLOYEE_ID`.
* `EMPLOYEE_ID` must follow the **EID format** (EID[4 digits]-[4 digits]).
* `OLD_DATE` and `NEW_DATE` must be in the **YYYY-MM-DD** format.
* `OLD_DATE` must already exist and `NEW_DATE` must not already exist.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If **OLD_DATE** is the current date, the **leave status** of the employee will change from **"On Leave"** to **"Present"**.
Conversely, if **NEW_DATE** is the current date, the **leave status** of the employee will change from **"Present"** to **"On Leave"**.
</div>

Examples:
* `editleave id/EID1234-5678 old/2023-12-26 new/2023-12-28` edits the leave on 26 December 2023 to 28 December 2023
  for employee with id EID1234-5678.

![editleave success](images/ug-pics/editLeaveSuccess.png)

* `editleave id/EID1234-5678 old/2023-12-31 new/2023-12-28` is invalid because there is no existing leave on 31 December 2023.

![editleave failure](images/ug-pics/editLeaveFailure1.png)

* `editleave id/EID1234-5678 old/2023-12-27 new/2023-12-28` is invalid because there is an existing leave on 28 December 2023.

![editleave failure](images/ug-pics/editLeaveFailure2.png)

### Listing the employees on leave on a specified date : `listleave`

Lists all the employees on leave on the specified date.

Format: `listleave on/DATE`

* Lists all the employees who are on leave on the specified `DATE`.
* `DATE` must be in the **YYYY-MM-DD** format.

Examples:
* `listleave on/2023-12-28` lists all the employees that are on leave on 28 December 2023.

![listleave success](images/ug-pics/listLeaveSuccess.png)

* `listleave on/2023-12-29` displays an empty list because there are no employees that are on leave on 29 December 2023.

![listleave empty](images/ug-pics/listLeaveEmpty.png)

### Adding a remark for an employee : `addremark`

Adds the specified remark to the specified employee.

Format: `addremark id/EMPLOYEE_ID r/REMARK`

* Adds the `REMARK` to the employee with the specified `EMPLOYEE_ID`.
* `EMPLOYEE_ID` must follow the **EID format** (EID[4 digits]-[4 digits]).
* `REMARK` must be **unique**. Duplicate remarks are not allowed.
* `REMARK` is **case-insensitive** (e.g. `good remark` and `GOOD REMARK` are taken to be equal).

Examples:
* `addremark id/EID1234-5678 r/good worker` adds the remark `good worker` to employee with id EID1234-5678.

![addremark success](images/ug-pics/addRemarkSuccess.png)

* `addremark id/EID1234-5678 r/GOOD WORKER` is invalid because there is already a remark `good worker` for the employee.

![addremark_failure](images/ug-pics/addRemarkFailure.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To view the entire list of remarks of an employee use the **report** command with their employee id.
</div>

### Deleting a remark of an employee : `deleteremark`

Deletes the specified remark from the specified employee.

Format: `deleteremark id/EMPLOYEE_ID r/REMARK`

* Adds the `REMARK` to the employee with the specified `EMPLOYEE_ID`.
* `EMPLOYEE_ID` must follow the **EID format** (EID[4 digits]-[4 digits]).
* `REMARK` must already exist in the remark list.
* `REMARK` is **case-insensitive** (e.g. `good remark` and `GOOD REMARK` are taken to be equal).

Examples:
* `deleteremark id/EID1234-5678 r/good worker` deletes the remark `good worker` from
  employee with id EID1234-5678.

![deleteremark success](images/ug-pics/deleteRemarkSuccess.png)

* `deleteremark id/EID1234-5678 r/team player` is invalid because the remark `team player` does not exist under 
  the employee with id EID1234-5678.

![deleteremark failure](images/ug-pics/deleteRemarkFailure.png)

### Updating overtime hours of an employee : `overtime`

Updates the overtime hours of an employee.

Format: `overtime id/EMPLOYEE_ID o/OPERATION a/AMOUNT`

* Updates the overtime hours of the employee with the specified `EMPLOYEE_ID` in **EID format** (EID[4 digits]-[4 digits]).
* `OPERATION` can be either `inc` or `dec` to increase or decrease the overtime hours respectively.
* `OPERATION` is **case-sensitive** (i.e. `INC` is invalid).
* `AMOUNT` is the number of hours to increase or decrease the overtime hours by.
* `AMOUNT` must be a **positive integer** (i.e. greater than 0).
* The **total number of overtime hours** worked in a month must not exceed the **maximum of 72** as per the [Singapore Ministry of Manpower's guidelines](https://www.mom.gov.sg/employment-practices/hours-of-work-overtime-and-rest-days).

Examples:
* `overtime id/EID1234-5678 o/inc a/10` increases the overtime hours of employee with id EID1234-5678 by 10 hours.

![overtime success](images/ug-pics/overtimeSuccess.png)

* `overtime id/EID1234-5678 o/inc a/0` is invalid because the given amount is not a positive integer.

![overtime failure 1](images/ug-pics/overtimeFailure1.png)

* `overtime id/EID1234-5678 o/dec a/20` is invalid because it will result in negative overtime hours.

![overtime failure 2](images/ug-pics/overtimeFailure2.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To view the total number of overtime hours and overtime pay of an employee use the **report** command with their employee id.
</div>

### Generating a report : `report`

Generates a report with details on leaves, overtime hours, overtime pay, and remarks for an employee.

Format: `report EMPLOYEE_ID`

* Generates and downloads a report for the employee with the specified `EMPLOYEE_ID`.
* The `EMPLOYEE_ID` must follow the **EID format** (EID[4 digits]-[4 digits])
* The report is downloaded in a `.txt` file, located in the `reports` folder in the location of `hour.jar`.
  * The `.txt` file follows the naming convention `DATE_NAME` where `DATE` is the date the report is created, 
    and `NAME` is the name of the corresponding employee.
* The overtime pay is calculated based on the overtime hours and the salary of the employee.
  * The following [Singaporean Ministry of Manpower's prescribed formula](https://www.mom.gov.sg/employment-practices/hours-of-work-overtime-and-rest-days) is used to calculate overtime pay.

      ![overtime pay formula](https://latex.codecogs.com/png.latex?1.5\times%20\frac{12%20\times%20\text{Monthly%20Salary}}{52\times%2044}\times{\text{number%20of%20overtime%20hours}})


Examples:
* `report EID1234-5678` generates and downloads a report for the employee with employee id EID1234-5678.

![report success](images/ug-pics/reportSuccess.png)

![report txt file](images/ug-pics/reportTxtFile.png)

* `report EID0000-0000` is invalid because the id does not exist.

![report failure](images/ug-pics/reportFailure.png)

### Resetting fields : `reset`

Resets the specified field to its default value.

Format: `reset f/FIELD`

* Resets the specified `FIELD` of all employees in the employee book.
* The `FIELD` has to be **non-empty** and can only be either `overtime` or `leaves`.
* The `FIELD` parameter is **case-insensitive**. (e.g. `overtime` and `OVERTIME` are taken to be the same)

Examples:
* `reset f/overtime` resets the overtime hours of all employees in the list to the default value 0.

![reset success](images/ug-pics/resetOvertimeSuccess.png)

* `reset f/leaves` resets the number of leaves taken by all employees in the list to the default value 0.

![reset success](images/ug-pics/resetLeavesSuccess.png)

* `reset f/name` is an invalid field and cannot be reset.

![reset failure](images/ug-pics/resetFailure.png)

## Miscellaneous Features

The following are miscellaneous features available to users.

### Saving the data

HouR data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HouR data are saved automatically as a JSON file `[JAR file location]/data/hour.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Warning to all users:**
DO NOT modify data directly as it might result in the malfunction of the application.If your changes to the data file makes its format invalid, HouR will discard all data and start with an empty data file at the next run. We recommend that users download a backup of the file before editing it.
</div>

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

### Launching HouR

**Q**: How can I launch HouR if clicking on the JAR file does not work?<br>
**A**: There are two possible methods to launch HouR.

**Method 1**: For users familiar with the Command Prompt
1. Open the Command Prompt
2. Navigate to the directory where the JAR file is located using `cd [JAR file location]`
3. Type `java -jar hour.jar` and press Enter 
4. HouR should launch

**Method 2**: For users that wish to create a script to launch HouR (Recommended)
1. Create a new text file 
2. Copy and paste the following into the text file: `java -jar [JAR file location]/hour.jar` 
3. Save the text file as `hour.bat` (Windows) or `hour.sh` (MacOS/Linux)
4. Change the admin settings of the script to allow it to run as program:
   * Windows: Right-click on the script and select **Properties**. Under **General**, check the box that says `Allow this file to run as a program`. 
   * MacOS/Linux: Open the Terminal and navigate to the directory where the script is located. Type `chmod +x [script name]` and press Enter.<br> 
     Note: (`chmod +x` changes permissions of the script to allow it to be executed)
5. Double-click on the script to launch HouR 
6. HouR should launch

If you have any further issues, please raise an issue on our [Github page](https://github.com/AY2324S1-CS2103T-W12-1/tp/issues). We will attend to you as soon as we can.

### Checking Java Version

**Q**: How can I check my Java version?<br>
**A**: Open a Command Prompt and type `java -version`. If you do not have Java installed, you can install Java 11 using the Oracle guide [here](https://www.oracle.com/java/technologies/downloads/#java11). Alternatively, you can install the OpenJDK version.

For Mac users, you may wish to follow the instructions listed [here](https://nus-cs2103-ay2324s1.github.io/website/admin/programmingLanguages.html).

### Loading Data from Another Computer

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install HouR in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HouR home folder.

**Q**: How can I load data from one computer into HouR installed on another computer?<br>
**A**: This can be done by following the three steps below.
1. Delete the `hour.json` file (stored at `[hour.jar file location]/data/hour.json`) from the computer that you wish to use HouR on.
2. Copy over the `hour.json` file from the computer which you no longer wish to use HouR on.
3. Boot up HouR to check whether your employee information is properly loaded into the new computer

### Using HouR

**Q**: What are the available commands?<br>
**A**: Refer to the Command Summary for the list of available commands. Alternatively, from the main application window, you can type `help` to view the list of available commands.

**Q**: Do I need an internet connection to use HouR?<br>
**A**: All of HouR’s functionality can be used offline! No internet connection is required.

**Q**: How do I save my data?<br>
**A**: Data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action           | Format, Examples                                                                                                                                                                           |
|------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**          | `add n/NAME pos/POSITION id/EMPLOYEE_ID p/PHONE_NUMBER e/EMAIL s/SALARY [d/DEPARTMENT]...` <br> e.g., `add n/James Ho pos/Auditor id/EID2023-0928 p/87651234 e/jamesho@example.com s/8000` |
| **Delete**       | `delete EMPLOYEE-ID`<br> e.g., `delete EID1234-5678`                                                                                                                                       |
| **Edit**         | `edit INDEX [n/NAME] [p/POSITION] [id/EMPLOYEE_ID] [p/PHONE_NUMBER] [e/EMAIL] [s/SALARY] [d/DEPARTMENT]...`<br> e.g.,`edit 2 n/James Lee pos/Head Auditor`                                 |
| **List**         | `list`                                                                                                                                                                                     |
| **Find**         | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Manager`                                                                                                                              |
| **Sort**         | `sort f/FIELD in/ORDER`                                                                                                                                                                    |
| **AddLeave**     | `addleave id/EMPLOYEE_ID from/START_DATE to/END_DATE` <br/> e.g., `addleave id/EID1234-5678 from/2023-12-26 to/2023-12-28`                                                                 |
| **DeleteLeave**  | `deleteleave id/EMPLOYEE_ID from/START_DATE to/END_DATE` <br/> e.g., `deleteleave id/EID1234-5678 from/2023-12-26 to/2023-12-28`                                                           |
| **EditLeave**    | `editleave id/EMPLOYEE_ID old/OLD DATE new/NEW DATE` <br/> e.g., `editleave id/EID1234-5678 old/2023-12-26 new/2023-12-28`                                                                 |
| **ListLeave**    | `listleave on/DATE` <br/> e.g., `listleave on/2023-11-30`                                                                                                                                  |
| **Overtime**     | `overtime id/EMPLOYEE_ID o/OPERATION a/AMOUNT` <br/> e.g., `overtime id/EID1234-5678 o/inc a/10`                                                                                           |
| **AddRemark**    | `addremark id/EMPLOYEE_ID r/REMARK` <br/> e.g., `addremark id/EID1234-5678 r/Good worker`                                                                                                  |
| **DeleteRemark** | `deleteremark id/EMPLOYEE_ID r/REMARK` <br/> e.g., `deleteremark id/EID1234-5678 r/Good worker`                                                                                            |
| **Report**       | `report EMPLOYEE_ID` <br/> e.g., `report EID1234-5678`                                                                                                                                     |
| **Reset**        | `reset f/FIELD` <br/> e.g., `reset f/overtime`                                                                                                                                             |
| **Help**         | `help`                                                                                                                                                                                     |
| **Clear**        | `clear`                                                                                                                                                                                    |
| **Exit**         | `exit`                                                                                                                                                                                     |

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Parameter information

The table below provides information on the usage of the allowed parameters in HouR. Do note that the examples given are not exhaustive

| Parameter, Description                                        | Case sensitive | Constraints                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | Valid Examples                                                                             | Invalid Examples                                         |
|---------------------------------------------------------------|----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|----------------------------------------------------------|
| **n/NAME** <br/> Name of the employee                         | Yes            | Alphanumeric characters (a to z, A to Z, 0 to 9)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | `John Doe`, `Elizabeth 2`                                                                  | `A.P.J. Abdul Kalam`, <br/> `Jane Doe-Smith`             |
| **pos/POSITION** <br/> Position of the employee               | Yes            | Alphanumeric characters (a to z, A to Z, 0 to 9)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | `Senior Manager`, `CEO`                                                                    | `Chief Financial Officer (CFO)`                          |
| **id/EMPLOYEE_ID** <br/> ID of the employee                   | Yes            | Prefix EID followed by 4 digits separated by a hyphen followed by 4 more digits                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | `EID1234-5678`, <br/> `EID2023-1010`                                                       | `1234-5678`, <br/> `eid1234-5678`, `EID12345678`         |
| **p/PHONE** <br/> Phone number of the employee                | NA             | 8 numeric characters (0 to 9), begins with 8 or 9, following IMDA’s [National Numbering Plan](https://www.imda.gov.sg/regulations-and-licensing-listing/numbering/national-numbering-plan-and-allocation-process)                                                                                                                                                                                                                                                                                                                                                            | `90005000`, <br/> `85852023`                                                               | `12345678`, `999`, <br/> `6001567a`                      |
| **e/EMAIL** <br/> Email of the employee                       | Yes            | local-part@domain<br/>1. The local-part should only contain alphanumeric characters and `+`, `_`, `.`, `-`. It may not start or end with any special characters.<br/>2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.<br/>The domain name must:<br/>    - end with a domain label at least 2 characters long<br/>    - have each domain label start and end with alphanumeric characters<br/>    - have each domain label consist of alphanumeric characters, separated only by hyphens (`-`), if any. | `john@gmail.com`, `eliz2@mit.edu`, <br/> `john-doe@nus-edu.sg`                             | `johndoe@, eliz2`, `_janedoe@nus.edu`, `johndoe@nus+edu` |
| **s/SALARY** <br/> Salary of the employee                     | NA             | Numeric characters (0 to 9) <br/> Non-negative integer                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | `0`, `1000`, `10000`, `850`                                                                | `$1000`, `-1`, `8500.00`                                 |
| **d/DEPARTMENT** <br/> Department of the employee (optional)  | Yes            | Alphanumeric characters (a to z, A to Z, 0 to 9)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | `IT`, `Finance`                                                                            | `I/T`                                                    |
| **f/FIELD** <br/> Field of the employee                       | No             | Alphabetic characters (a to z, A to Z)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | For `sort`: `name`, `salary`, `overtime`, `leaves` <br/> For `reset`: `overtime`, `leaves` | `employee`, `age`                                        |
| **in/ORDER** <br/> Order that the employee is to be sorted in | No             | Alphabetic characters (a to z, A to Z)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | `asc`, `desc` or `ASC`, `DESC`                                                             | `ascending`, `descending`                                |
| **o/OPERATION** <br/> Operation to be applied                 | Yes            | Alphanumeric characters (a to z, A to Z, 0 to 9)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | `inc`, `dec`                                                                               | `INC`, `DEC`, `increase`, `decrease`                     |
| **a/AMOUNT** <br/> Amount to be changed by                    | NA             | Positive integer                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | `1`, `12`                                                                                  | `0`, `-3`, `1.5`                                         |
| **r/REMARK** <br/> Remark for an employee                     | No             | No constraints                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | `Good worker`, `efficient worker!`, `:)`                                                   | NA                                                       |
| **from/START_DATE** <br/> Start date of the leave             | NA             | yyyy-MM-dd format <br/> `START_DATE` cannot be after `END_DATE`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | `2023-12-11`, <br/> `2023-12-21`                                                           | `2023-13-11`, <br/> `1 Dec 2023`, <br/> `01/12/2023`     |
| **to/END_DATE** <br/> End date of the leave                   | NA             | yyyy-MM-dd format <br/> `START_DATE` cannot be after `END_DATE`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | `2023-12-11`, <br/> `2023-12-21`                                                           | `2023-13-11`, <br/> `1 Dec 2023`, <br/> `01/12/2023`     |
| **old/OLD_DATE** <br/> Old date of the leave                  | NA             | yyyy-MM-dd format <br/> `NEW_DATE` cannot be the same as `OLD_DATE`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | `2023-12-11`, <br/> `2023-12-21`                                                           | `2023-13-11`, <br/> `1 Dec 2023`, <br/> `01/12/2023`     |
| **new/NEW_DATE** <br/> New date of the leave                  | NA             | yyyy-MM-dd format <br/> `NEW_DATE` cannot be the same as `OLD_DATE`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | `2023-12-11`, <br/> `2023-12-21`                                                           | `2023-13-11`, <br/> `1 Dec 2023`, <br/> `01/12/2023`     |
| **INDEX** <br/> Index in the employee list                    | NA             | Positive integer less than or equals the number of employees                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | `1`, `3`                                                                                   | `0`, `-3`, `1.5`                                         |
| **KEYWORD** <br/> Keyword to be searched                      | No             | No constraints                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | `royb@example.com`, `EID1234-5678`, `IT`, `engineer`                                       | NA                                                       |
