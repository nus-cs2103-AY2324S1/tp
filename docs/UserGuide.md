---
layout: page
title: User Guide
---

ManaGease is a **desktop app for managing full time staff in any workplace, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ManaGease can get your contact management tasks done faster than traditional apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start (Coming soon)

1. Ensure you have Java `11` or above installed in your Computer. 
2. Download the latest `ManaGease.jar` from [here](https://github.com/se-edu/addressbook-level3/releases)
3. Copy the file to the folder you want to use as the _home folder_ for your ManaGease. 
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ManaGease.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add /n Jane Smith /e jane@email.com /p 12345678 /a 123 Main St /b 123456789 /jd 2023-09-12 /s 1000 /al 10`: Adds an employee named `John Doe` to ManaGease

   * `delete 3` : Deletes the 3rd employee shown in the current list.

   * `clear` : Deletes all employees.

   * `exit` : Exits the app.

Refer to the [Features](#features) below for details of each command.

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

## Viewing help : `help` [Coming soon]
**What it does:**
Shows a message explaning how to access the help page.

### Command Format: 
`help`


## Adding a person: `add`

**What it does**

This feature allows users to add a new employee to the ManaGease app, creating a profile with essential details.

### Command format: 
```
add /n NAME /e EMAIL /p PHONE /a ADDRESS /b BANK_ACCOUNT /jd JOIN_DATE /s SALARY /al ANNUAL_LEAVE
```

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person must have at least the NAME parameter, but can have any number of parameters.
</div>

Example:
* `add /n Jane Smith /e jane@email.com /p 12345678 /a 123 Main St /b 123456789
  /jd 2023-09-12 /s 1000 /al 10`

Output:
* The ScheduleEase app should display a confirmation message indicating that the employee has been successfully added to the database. (e.g. Employee "Jane Smith" successfully added!)

* The employee's profile should be updated with the provided information.
  
* “Employee “Jane Smith” successfully added”
* If tag used is not defined, an error message will appear and say "Tag not found, please use any of the following tags: /n, /e, /p, /a, /b, /jd, /s, /al"

![result for adding employee]('images/addSuccess.png)

### Editing a person : `edit`

**What it does**

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] `

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower ` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Deleting a person : `delete`

**What it does**
This feature allows users to delete an employee based on index or name.

# Command Format: 
`delete INDEX` or `delete /n NAME`


* Deletes the person at the specified `INDEX` or with the name `NAME`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer**.

Examples:
* `list` followed by `delete 2` deletes the 2nd employee in the employee directory.
* `find Betsy` followed by `delete 1` deletes the 1st employee in the results of the `find` command.

### Listing all persons : `list` [Coming soon]

**What it does**

Shows a list of all employees in the workplace.

**Command format:**

`list`

### Clearing all entries : `clear` [Coming soon]

**What it does**

Clears all entries from the address book.

**Command format:**
`clear`

### Locating employees by name: `find` [Coming soon]

**What it does**

Finds employees whose names contain any of the given keywords.

**Command Format**

`find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)


### Exiting the program : `exit` 

**What it does**

Exits the program.

**Command format:**

`exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
