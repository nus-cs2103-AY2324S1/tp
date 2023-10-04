---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

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


### Adding a new contact: `new contact`

Creates a new contact with the specified name and module code(s).

Format: `new contact /name <Name> /mod <Optional: Module Code> /grp <Optional: Tutorial Group Number>`

Examples:
* `new contact /name Fu Yiqiao`
* `new contact /name Fu Yiqiao /mod CS2103T /grp T09`

### Viewing summary of attendance : `list attendance`

Shows a summary of attendance records.

Format: `list attendance [/tg ID]`
*  Shows a summary of the attendance records of the tutorial group corresponding to the specified ID.
*  ID must be made up of **alphabetical characters and numbers** only, with no special characters.
*  ID must correspond to an existing tutorial group.

Examples:
*  `list attendance` Shows a summary of attendance records of all students.
*  `list attendance /tg T09` Shows a summary of attendance records of the students in tutorial group T09.

### Listing students : `list students`

Shows a list of students.

Format: `list students [/tg ID]`
*  Shows a list of students in the tutorial group corresponding to the specified ID.
*  ID must be made up of **alphabetical characters and numbers** only, with no special characters.
*  ID must correspond to an existing tutorial group.

Examples:
*  `list students` Shows a list of all students.
*  `list students /tg T09` Shows a list of the students in tutorial group T09.

### Editing a contact name : `edit name`

Edits the contact name.

Format: `edit name /oldname <oldName> /newname <newName>`

Examples:
*  `edit name /oldname Fu Yiqiao /newname Tan Liyan` Edits the student with name Fu Yiqiao to Tan Liyan

### Editing a contact module : `edit mod`

Edits the module associated with the contact.

Format: `edit mod <Name> /oldmod <oldMod> <newMod>`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To remove a module, simply leave the newMod section empty
</div>

Examples:
*  `edit mod Fu Yiqiao /oldmod CS2103T CS2101` Edits the module with code CS2103T to CS2101
*  `edit mod Fu Yiqiao /oldmod CS2103T` Removes the module with code CS2103T from the contact

### Editing tutorial group number : `edit grp`

Edits the tutorial group number associated with the contact.

Format: `edit grp <Name> /oldgrp <oldgrp> /newgrp <newgrp>`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To remove a group number, simply leave out the newgrp section
</div>

Examples:
*  `edit grp Fu Yiqiao /oldgrp T09 /newgrp T10` Edits the group from T09 to T10
*  `edit grp Fu Yiqiao /oldgrp T09` Removes the group number from the contact


### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

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

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

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
**Add** | `new contact /name <Name> /mod <Optional: Module Code> /grp <Optional: Tutorial Group Number>` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit name /oldname <oldName> /newname <newName>`<br> `edit mod <Name> /oldmod <oldMod> <newMod>`<br> `edit grp <Name> /oldgrp <oldgrp> /newgrp <newgrp>`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
