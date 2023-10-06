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

### Adding a musician contact: `add`

Adds a musician to the contact book. Name, phone number, email, and instrument can be recorded.

**Format:** `add n/NAME p/PHONE_NUMBER e/EMAIL i/INSTRUMENT`

**Examples:**
* `add n/John Doe p/98765432 e/johnd@example.com i/Violin`
* `add n/Betsy Crowe e/pianistbetsy@smtp.com p/87988039 i/Piano`

**Upon success:**

You will see a message indicating successful addition of the musician like below:
![img.png](images/addJohnDoe.png)

**Upon failure:**

Should you input a musician which is already in your contact book(ie. have the same phone number or email as an existing contact), you will see an error message showing the possible error, please input a different phone/email for the current contact or modify the original contact's relevant details.

### Removing a musician contact: `remove`

Removes a musician contact from the address book.

**Format:** `remove INDEX`

**Examples:**
* `remove 1`

**Upon success:**

You will see a message indicating successful removal of the musician contact like below:

[insert image]

**Upon failure:**

Should you input an index greater than the number of musicians in your address book (e.g. `remove 6` in an address book
containing 5 musicians), or input a non-positive index (e.g. `remove 0` or `remove -1`), you will see a message like below.

[insert image]

Please verify that the index is correct and try again.

### Tagging a musician with genres: `tag genre`

Tags a musician with one or more genres he/she is proficient in.

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.

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

--------------------------------------------------------------------------------------------------------------------


## Command summary


| Action             | Format, Examples                                                                                 |
|--------------------|--------------------------------------------------------------------------------------------------|
| **Add**            | `add n/NAME p/PHONE_NUMBER e/EMAIL`<br> e.g., `add n/John Doe p/98928479 e/johndpiano@xmail.com` |
| **Remove**         | `remove INDEX`<br> e.g., `remove 1`                                                              |
| **Tag Instrument** | `tag instrument n/NAME i/TAG…​`<br> e.g.,`tag instrument n/John Doe i/piano i/guitar`            |
| **Tag Genre**      | `tag genre INDEX g/GENRE…​`<br> e.g., `tag genre 1 g/rock g/pop`                                 |
| **Find**           | `find KEYWORD`                                                                                   |
