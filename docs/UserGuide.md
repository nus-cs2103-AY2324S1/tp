---
layout: page
title: User Guide
---
# BandConnect++ User Guide
BandConnect++ is a powerful desktop app that helps musicians manage contact with other musicians to easily form a band. You can use it to manage musician contacts, select band members, and form a band of your preference, all within a few clicks or a few seconds of typing!

--------------------------------------------------------------------------------------------------------------------
## About this guide
Welcome to the *BandConnect++ User Guide*! This user guide provides an in-depth documentation on everything about *BandConnect++*, including installation, set up, features, common FAQ and troubleshooting recommendations. 

For first-time users, please go to the [Quick start](#quick-start) section below to start an end-to-end tutorial that gets you onboard. Should you encounter any difficulty understanding the terminology, don't forget to refer to the [Glossary](#glossary)!

### Table of Contents
* [Glossary](#glossary)
* [Quick start](#quick-start)
* [Features](#features)
* [Command Summary](#command-summary)
* [Troubleshooting](#troubleshooting)


## Glossary
To be added.

--------------------------------------------------------------------------------------------------------------------

## Quick start
To be added.

--------------------------------------------------------------------------------------------------------------------

## Features

### Formatting Convention

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [g/GENRE]` can be used as `n/John Doe g/rock` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[i/instrument]…​` can be used as ` ` (i.e. 0 times), `i/guitar` or `i/guitar i/violin i/piano`.

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


### Adding a musician contact: `add`

Adds a musician to the contact book. Name, phone number and email can be recorded.

**Format:** `add n/NAME p/PHONE_NUMBER e/EMAIL`

**Examples:**
* `add n/John Doe p/98928479 e/johndpiano@xmail.com`
* `add n/Betsy Crowe e/violinistbetsy@smtp.com p/87988039`

**Upon success:**

You will see a message indicating successful addition of the musician like below:
[insert image]

**Upon failure:**

Should you input a musician which is already in your contact book(ie. have the same phone number or email as an existing contact), you will see a message like below, please input a different phone/email for the current contact or modify the original contact's relevant details.

[insert image]


### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.


### Tagging a musician with genres: `tag genre`

Tags a musician with one or more genres he/she is proficient in.

**Format:** `tag genre INDEX g/GENRE...`

**Constraints:**
* `INDEX` must be a positive integer 1, 2, 3, …​
* The genre tag must be *non-empty*.
* You have to supply *at least one genre tag* to the musician you are tagging.

**Examples:**
* `tag 1 g/rock g/pop`
* `tag 2 g/jazz`

**Upon success:**

You will see a message indicating successful addition of the musician like below:

[insert image]

**Upon failure:**

Should you try to tag a musician with zero genre tags or empty tags, i.e., `tag 1 g/` or `tag 1`, you will see a message like below: 

[insert image]

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

## Troubleshooting

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
